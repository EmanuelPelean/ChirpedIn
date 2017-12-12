/**
 * 
 */
package com.chirpedin.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chirpedin.dao.UserDaoImpl;
import com.chirpedin.dao.UsersDao;
import com.chirpedin.dto.UserDto;
import com.chirpedin.factory.DaoFactory;
import com.chirpedin.util.APICredentials;
import com.chirpedin.util.ChirpedIn;
import com.chirpedin.util.MentorListComparator;
import com.sun.jersey.core.util.Base64;
import com.sun.xml.internal.ws.api.message.Header;

/**
 * @author
 * 
 * 		LinkedIn
 *
 */
@Controller
public class HomeController {

	// public static void main(String[] args) {
	// ArrayList<String> skillsNeed = new ArrayList<String>();
	//
	// skillsNeed.add("php");
	// skillsNeed.add("java");
	// skillsNeed.add("css");
	//
	// ArrayList<String> skillsHave = new ArrayList<String>();
	//
	// skillsHave.add("php");
	// // skillsHave.add("java");
	// // skillsHave.add("css");
	// int skillsCount = 0;
	// for (String skill : skillsNeed) {
	// if (skillsHave.contains(skill)) {
	// skillsCount++;
	// }
	// }
	//
	// if (skillsCount == skillsNeed.size()) {
	// System.out.println("#1");
	// } else if (skillsCount < skillsNeed.size()) {
	// System.out.println("#2");
	// }
	//
	// }

	/**
	 * Home page mapping (you can use "/" if you want) 1)Redirect users to request
	 * LinkedIn access – (provide a value for the state and scopes you need) home
	 * page has the hyperlink to do a get request to LinkedIN endpoit
	 * 
	 * @return
	 */
	@RequestMapping({ "/", "/home" })
	public ModelAndView home() {

		return new ModelAndView("home", "", "");// Since there is no model I could return string with view name
	}

	// this is called when the signup page is first opened
	@RequestMapping({ "/signup" })
	public ModelAndView signupPage(Model model) {
		System.out.println("Regular Signup");

		// binding form to pojo

		return new ModelAndView("signup", "command", new UserDto());
	}

	// this is called when the user clicks to submit form data
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView signupPost(@ModelAttribute("command") UserDto newUser, Model model) {
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		// Create strings for HaveSkills, NeedSkills, and Networking Skills and add them
		// to the newUser DTO
		ChirpedIn.populateHaveSkills(newUser);
		ChirpedIn.populateNeedSkills(newUser);
		ChirpedIn.populateNetworkingSkills(newUser);
		ChirpedIn.setSkillCount(newUser);

		// Insert the newUser DTO into our MySQL database
		dao.insertUser(newUser);

		// List<UserDto> matches = dao.getMatches(newUser, model);
		// System.out.println("Form Signup");

		List<UserDto> mentorList = dao.findMentor(newUser); // find mentors based on criteria
		List<UserDto> menteeList = dao.findMentee(newUser); // find mentees based on criteria
		List<UserDto> networkingList = dao.matchNetworking(newUser); // find networking matches based on criteria

		System.out.println("This is mentorList before modification :" + mentorList); // Debugging

		List<UserDto> allMatchesList = new ArrayList<UserDto>();

		for (UserDto userDto : menteeList) {
			if (!allMatchesList.contains(userDto))
				allMatchesList.add(userDto);
		}

		for (UserDto userDto : menteeList) {
			if (!allMatchesList.contains(userDto))
				allMatchesList.add(userDto);
		}

		for (UserDto userDto : networkingList) {
			if (!allMatchesList.contains(userDto))
				allMatchesList.add(userDto);
		}

		for (UserDto userDto : networkingList) {
			if (userDto.getLinkedInId().contains(newUser.getLinkedInId())) {
				allMatchesList.remove(userDto);
			}
		}

		System.out.println("This is mentorList after modification :" + allMatchesList);

		// do work to display the list in order

		for (int i = 0; i < allMatchesList.size(); i++) {// for each mentor, print matching skills

			ChirpedIn.populateHaveSkills(allMatchesList.get(i));
			ChirpedIn.populateNeedSkills(allMatchesList.get(i));
			ChirpedIn.populateNetworkingSkills(allMatchesList.get(i));
			ChirpedIn.populateAllMatchingSkills(newUser, allMatchesList.get(i));
			ChirpedIn.setMatchingSkillCounts(allMatchesList.get(i));
			ChirpedIn.calculateMatchPercentages(newUser, allMatchesList.get(i));

			System.out.println("This is our request field DTO:\n" + newUser);

			System.out.println(allMatchesList.get(i));

		}
		mentorList.sort(new MentorListComparator(newUser));

		model.addAttribute("mentorresults", allMatchesList); // send data to view

		return new ModelAndView("matches", "", "");

	}

	/**
	 * 3. Linked redirects back to this controller with a temporary code – and
	 * (returned state/once should match state passed in step 2) 4. Exchange
	 * temporary code for an access token 5. Use access tokens to make calls to the
	 * LinkedIn Api on behalf of the user
	 * 
	 * @param code
	 *            - code from https://LinkedIn.com/login/oauth/authorize call
	 * @param state
	 *            - once value received from
	 *            https://LinkedIn.com/login/oauth/authorize call to guard against
	 *            cross site forgery attacks
	 * @return
	 */
	@RequestMapping("/result")
	public ModelAndView result(Model model, @RequestParam("code") String code, @RequestParam("state") String state) {

		String jsonString = "";
		String accessToken = "";
		UserDto userDataDto = null;

		try {
			// 4. Exchange temporary code for an access token
			URL url = new URL("https://www.linkedin.com/oauth/v2/accessToken?" + "grant_type=authorization_code&"
					+ "code=" + code + "&" + "client_id=" + APICredentials.CLIENT_ID + "&"
					+ "redirect_uri=http://localhost:8080/ChirpedIn/result&" + "client_secret="
					+ APICredentials.CLIENT_SECRET);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setDoOutput(true); // Must add
			con.setRequestMethod("POST");
			con.setFixedLengthStreamingMode(0); // Must add
			con.setRequestProperty("Accept", "application/json");

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String currentLine = "";

			while ((currentLine = reader.readLine()) != null) {
				jsonString += currentLine;
			}

			JSONObject jsonObj = new JSONObject(jsonString);
			accessToken = jsonObj.getString("access_token");
			con.disconnect();

			// 5. Use access token to make calls to the LinkedIn Api on behalf of the user

			url = new URL(
					"https://api.linkedin.com/v1/people/~:(id,email-address,first-name,last-name,headline,location,summary,picture-url,picture-urls::(original),public-profile-url)?format=json&oauth2_access_token="
							+ accessToken);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			currentLine = "";
			jsonString = "";

			while ((currentLine = reader.readLine()) != null) {
				jsonString += currentLine;
			}

			// Get the user data we want from the json string
			System.out.println(jsonString);
			jsonObj = new JSONObject(jsonString);
			JSONObject jsonLocation = (JSONObject) jsonObj.get("location");
			JSONObject jsonLargePic = (JSONObject) jsonObj.get("pictureUrls");

			// String userID = (String) jsonObj.get("id");
			// String firstName = (String) jsonObj.get("firstName");
			// String lastName = (String) jsonObj.get("lastName");
			// String headline = (String) jsonObj.get("headline");
			// String location = (String) jsonLocation.get("name");
			// String smallPic = (String) jsonObj.get("pictureUrl");
			// JSONObject jsonLargePicValues = (JSONObject) jsonLargePic.get("values");
			// String largePic = (String) jsonLocation.get("values");
			// String largePic = (String) jsonObj.get("values");
			// String profileUrl = (String) jsonObj.get("publicProfileUrl");
			// String email = (String) jsonObj.get("email-address");

			userDataDto = new UserDto();

			userDataDto.setLinkedInId((String) jsonObj.get("id"));
			userDataDto.setLinkedInFirstName((String) jsonObj.get("firstName"));
			userDataDto.setLinkedInLastName((String) jsonObj.get("lastName"));
			userDataDto.setLinkedInHeadline((String) jsonObj.get("headline"));
			userDataDto.setLinkedInLocation((String) jsonLocation.get("name"));
			userDataDto.setLinkedInPictureUrl((String) jsonObj.get("pictureUrl"));
			userDataDto.setLinkedInLargePictureUrl(jsonLargePic.optString("values"));
			userDataDto.setLinkedInPublicProfileUrl((String) jsonObj.get("publicProfileUrl"));
			userDataDto.setLinkedInEmail((String) jsonObj.get("emailAddress"));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * TODO Change to return the name and avatar image in the instead of the raw
		 * json data the model
		 */
		model.addAttribute("data", userDataDto);
		return new ModelAndView("signup", "command", new UserDto());
	}

	@RequestMapping("/matches")
	public String showMatches(Model model) {

		return "matches";
	}

	// this is called when the dashboard.jsp page is first opened
	@RequestMapping({ "/dashboard" })
	public ModelAndView userDashboard(Model model) {

		// binding form to pojo

		return new ModelAndView("dashboard", "", "");
	}

	@RequestMapping(value = { "/faved" }, method = RequestMethod.POST)
	public ModelAndView faved(@ModelAttribute("command") UserDto selectedDto, UserDto userDto, Model model) {

		userDto.setFavorites(userDto.getFavorites() + " " + selectedDto.getFavorites());

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		dao.updateUser(userDto);

		return new ModelAndView("matches", "", "");

	}

}
