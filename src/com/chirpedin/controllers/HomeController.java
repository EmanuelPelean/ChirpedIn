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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;
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

	/**
	 * Home page mapping (you can use "/" if you want) 1)Redirect users to request
	 * LinkedIn access (provide a value for the state and scopes you need) home page
	 * has the hyperlink to do a get request to LinkedIN endpoit
	 * 
	 * @return
	 */
	@RequestMapping({ "/", "/home" })
	public ModelAndView home() {

		return new ModelAndView("home", "", "");// Since there is no model I could return string with view name
	}

	/**
	 * 3. Linked redirects back to this controller with a temporary code and
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
		/*
		 * we then go to the "signup.jsp" page and pass in a new UserDto this UserDto is
		 * attached to the form on the signup.jsp page by "command"
		 */
		return new ModelAndView("signup", "command", new UserDto());
	}

	// this is called when the signup page is opened without going through the
	// "results" mapping
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
		
		
		/**
		 * removed for DEMO day
		 */
		
		
		//List<UserDto> mentorList = dao.findMentor(newUser); // find mentors based on criteria

		List<UserDto> uniqueMatchesList = dao.findMentor(newUser); // find mentors based on criteria

		
		 
		/*List<UserDto> menteeList = dao.findMentee(newUser); // find mentees based on criteria
		List<UserDto> networkingList = dao.matchNetworking(newUser); // find networking matches based on criteria

		List<UserDto> uniqueMatchesList = new ArrayList<UserDto>();

		System.out.println("This is mentorList before modification :" + mentorList.size()); // Debugging
		System.out.println("This is menteeList before modification :" + menteeList.size()); // DEMO 
		System.out.println("This is networkingList before modification :" + networkingList.size()); // DEMO

		List<UserDto> mentorAndMenteeDtoList = ListUtils.union(mentorList, menteeList);
		List<UserDto> networkingMentorAndMenteeDtoList = ListUtils.union(networkingList, mentorAndMenteeDtoList);

		System.out.println("mentorAndMenteeList size: " + mentorAndMenteeDtoList.size());
		System.out.println("networkingMentorAndMenteeList size: " + networkingMentorAndMenteeDtoList.size());

		ArrayList<String> listOfAllIds = new ArrayList<String>();

		for (UserDto userDto : networkingMentorAndMenteeDtoList) {
			listOfAllIds.add(userDto.getLinkedInId());
		}

		System.out.println("listOfAllIds size: " + listOfAllIds.size());
		System.out.println("converting to unique set...");

		Set<String> hashList = new HashSet<String>(listOfAllIds);
		ArrayList<String> uniqueListOfIds = new ArrayList<String>();

		for (String value : hashList) {
			uniqueListOfIds.add(value);
			for (UserDto userDto : networkingMentorAndMenteeDtoList) {
				if (value.equals(userDto.getLinkedInId())) {
					uniqueMatchesList.add(userDto);
					break;
				}
			}
		}

		System.out.println("uniqueListOfIds size: " + uniqueListOfIds.size());
		System.out.println("uniqueMatchesList size: " + uniqueMatchesList.size());



		//
		// // Adds all the UserDto's from menteeList to allMatchesList
		// for (UserDto userDto : mentorList) {
		// uniqueMatchesList.add(userDto);
		// }
		// uniqueMatchesList.add(mentorList.get(0));
		//
		// System.out.println("This is unique matching before modification :" +
		// uniqueMatchesList.size());
		//
		// for (int j = 0; j < menteeList.size(); j++) {
		// for (int i = 0; i < uniqueMatchesList.size(); i++) {
		// if (!(menteeList.get(j).getLinkedInId() ==
		// (uniqueMatchesList.get(i).getLinkedInId()))) {
		// uniqueMatchesList.add(menteeList.get(i));
		// }
		// }
		// }

		// System.out.println("This is unique matching after modification :" +
		// uniqueMatchesList.size());

		// Adds all the UserDto's from menteeList to allMatchesList
		// for (UserDto userDto : menteeList) {
		// if (!uniqueMatchesList.contains(userDto))
		// uniqueMatchesList.add(userDto);
		// }
		//
		// for (UserDto userDto : networkingList) {
		// if (!uniqueMatchesList.contains(userDto))
		// uniqueMatchesList.add(userDto);
		// }
		//
		// System.out.println("Unique match count before modification :" +
		// uniqueMatchesList.size());

		UserDto ourUser = null;

		for (UserDto userDto : uniqueMatchesList) {
			if (userDto.getLinkedInId().contains(newUser.getLinkedInId())) {
				ourUser = userDto;
			}
		}

		uniqueMatchesList.remove(ourUser);

		System.out.println("Unique match count after modification :" + uniqueMatchesList.size());
		*/

		// do work to display the list in order

		for (int i = 0; i < uniqueMatchesList.size(); i++) {// for each mentor, print matching skills

			ChirpedIn.populateHaveSkills(uniqueMatchesList.get(i));
			ChirpedIn.populateNeedSkills(uniqueMatchesList.get(i));
			ChirpedIn.populateNetworkingSkills(uniqueMatchesList.get(i));
			ChirpedIn.populateAllMatchingSkills(newUser, uniqueMatchesList.get(i));
			ChirpedIn.setMatchingSkillCounts(uniqueMatchesList.get(i));
			ChirpedIn.calculateMatchPercentages(newUser, uniqueMatchesList.get(i));

			//System.out.println("This is our request field DTO:\n" + newUser);

			System.out.println(uniqueMatchesList.get(i));

		}

		uniqueMatchesList.sort(new MentorListComparator());

		// System.out.println(uniqueMatchesList);

		model.addAttribute("mentorresults", uniqueMatchesList); // send data to view

		model.addAttribute("newUser", newUser);

		return new ModelAndView("matches", "command", new UserDto());

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

	// @RequestMapping(value = { "/faved" }, method = RequestMethod.POST)
	// public ModelAndView faved(@ModelAttribute("command") UserDto selectedDto,
	// UserDto userDto, Model model) {
	//
	// System.out.println("This is the userDto fav before: " +
	// userDto.getFavorites());
	//
	// System.out.println("This is the userDtoLinkedInID: " +
	// userDto.getLinkedInId());
	// System.out.println("This is the selectedDto LinkedInID: " +
	// selectedDto.getLinkedInId());
	//
	// System.out.println("This is the userDto: ");
	// System.out.println(userDto);
	//
	// System.out.println("This is the selectedDto: ");
	// System.out.println(selectedDto);
	//
	// userDto.setFavorites(userDto.getFavorites() + " " +
	// selectedDto.getLinkedInId());
	//
	// System.out.println("This is fav after fav field update: " +
	// userDto.getFavorites());
	//
	// // UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
	//
	// // dao.updateUser(userDto);
	//
	// System.out.println("This is fav after dao update: " +
	// userDto.getFavorites());
	//
	// return new ModelAndView("matches", "command", new UserDto());
	//
	// }

	@RequestMapping(value = { "/addFavorites" }, method = RequestMethod.POST)
	public ModelAndView favorites(@RequestParam("linkedInId") String matchedDto,
			@RequestParam("newUserlinkedInId") String userDto) {

		// UserDto user1 = new UserDto();
		// user1.setLinkedInId("dani");
		//
		// UserDto user2 = new UserDto();
		// user2.setLinkedInId("format");

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		dao.addFavorites(userDto, matchedDto);

		return null;

	}

	@RequestMapping("/getFavorites")
	public ModelAndView getFavorites() {

		UserDto user1 = new UserDto();
		user1.setLinkedInId("dani");

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		List<UserDto> favorites = dao.getFavorites(user1);
		System.out.println(favorites);

		return null;

	}

}
