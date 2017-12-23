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

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.ListUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@SessionAttributes("newUserTest")
public class HomeController {

	/**
	 * This home page mapping redirects users to request LinkedIn access
	 * 
	 * @return
	 */
	@RequestMapping({ "/", "/home" })
	public ModelAndView home() {

		return new ModelAndView("home", "", "");// Since there is no model I could return string with view name
	}

	/**
	 * LinkedIn authenticates user and redirects them back to this controller with a
	 * temporary code and returned state. This should match state passed in step 2.
	 * ChirpedIn exchange the temporary code for an access token and authorizes the
	 * ChirpedIn to make calls to the LinkedIn API on behalf of the user.
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
			// Exchange temporary code for an access token
			URL url = new URL("https://www.linkedin.com/oauth/v2/accessToken?" + "grant_type=authorization_code&"
					+ "code=" + code + "&" + "client_id=" + APICredentials.CLIENT_ID + "&"
					+ "redirect_uri=http://localhost:8080/ChirpedIn/result&" + "client_secret="
					+ APICredentials.CLIENT_SECRET);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setDoOutput(true);
			con.setRequestMethod("POST"); // keeps data safe from prying eyes
			con.setFixedLengthStreamingMode(0);
			con.setRequestProperty("Accept", "application/json"); // tells format of the data we'll be receiving

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream())); // receives data
																										// from HTTP
																										// connection

			String currentLine = "";

			while ((currentLine = reader.readLine()) != null) { // keeps reading until there is no more data to read
				jsonString += currentLine; // stores data as jsonString
			}

			JSONObject jsonObj = new JSONObject(jsonString);
			accessToken = jsonObj.getString("access_token");
			con.disconnect();

			// Use access token to make calls to the LinkedIn API on behalf of the user

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
			JSONObject jsonLocation = (JSONObject) jsonObj.get("location"); // special way of handling nested Json
																			// Objects
			JSONObject jsonLargePic = (JSONObject) jsonObj.get("pictureUrls"); // special way of handling nested Json
																				// Objects

			userDataDto = new UserDto();

			userDataDto.setLinkedInId((String) jsonObj.get("id"));
			userDataDto.setLinkedInFirstName((String) jsonObj.get("firstName"));
			userDataDto.setLinkedInLastName((String) jsonObj.get("lastName"));
			userDataDto.setLinkedInHeadline((String) jsonObj.get("headline"));
			userDataDto.setLinkedInLocation((String) jsonLocation.get("name"));
			userDataDto.setLinkedInPictureUrl((String) jsonObj.get("pictureUrl"));
			userDataDto.setLinkedInLargePictureUrl(jsonLargePic.getJSONArray("values").getString(0));
			userDataDto.setLinkedInPublicProfileUrl((String) jsonObj.get("publicProfileUrl"));
			userDataDto.setLinkedInEmail((String) jsonObj.get("emailAddress"));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// save the data collected from LinkedIn into the "data" expression language tag
		model.addAttribute("data", userDataDto);

		// create a new (blank) userDto that we can populate with information collected
		// from the user
		return new ModelAndView("signup", "command", new UserDto());
	}

	// called when the signup page is opened without going through the "results" mapping
	@RequestMapping({ "/signup" })
	public ModelAndView signupPage(Model model) {
		System.out.println("Regular Signup");

		// binding form to UserDto

		return new ModelAndView("signup", "command", new UserDto());
	}

	/**
	 * called when the user submits form data on the startup page; does three things: 
	 * 1) sends user-supplied info to the database 
	 * 2) finds matches in the database
	 * 3) returns matches to the jsp page
	 * 
	 * @param newUser
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView signupPost(@ModelAttribute("command") UserDto newUser, Model model, HttpSession session) {
		System.out.println("Entering sign up method called by '/signup'");

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		// Create strings for HaveSkills, NeedSkills, and Networking Skills and add them
		// to the newUser DTO
		ChirpedIn.setHaveSkills(newUser);
		ChirpedIn.setNeedSkills(newUser);
		ChirpedIn.setNetworkingSkills(newUser);
		ChirpedIn.setUserSkillCount(newUser);

		System.out.println("This is the new user's info: " + newUser);
		
		// Insert the newUser DTO into our MySQL database
		dao.insertUser(newUser);

		session.setAttribute("newUserTest", newUser);
		// List<UserDto> matches = dao.getMatches(newUser, model);
		// System.out.println("Form Signup");

		/**
		 * removed for DEMO day
		 */

		// List<UserDto> mentorList = dao.findMentor(newUser); // find mentors based on
		// criteria

		List<UserDto> uniqueMatchesList = dao.findMentor(newUser); // find mentors based on criteria

	
		System.out.println("Unique match list count BEFORE: " + uniqueMatchesList.size());

		for (UserDto userDto : uniqueMatchesList) {// for each mentor, print matching skills

			ChirpedIn.setHaveSkills(userDto);
			ChirpedIn.setNeedSkills(userDto);
			ChirpedIn.setNetworkingSkills(userDto);
			ChirpedIn.setUserSkillCount(userDto);
			
			ChirpedIn.setAllMatchingSkills(newUser, userDto);
			ChirpedIn.setMatchingSkillCounts(userDto);

			ChirpedIn.setConnectionTypeFlags(userDto);			
			ChirpedIn.calculateMatchPercentages(newUser, userDto);

			System.out.println(userDto);
	
		}

		List<UserDto> tempArray = new ArrayList<UserDto>();

		for (UserDto userDto : uniqueMatchesList) {
			tempArray.add(userDto);
		}

		for (UserDto userDto : tempArray) {

			if (userDto.getMatchingMentorSkills().isEmpty()) {
				uniqueMatchesList.remove(userDto);
			}

		}
		
		uniqueMatchesList.sort(new MentorListComparator());


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

	@RequestMapping(value = "/chirp")
	public String chirpUserButton(@RequestParam("fName") String firstName, @RequestParam("lName") String lastName,
			@RequestParam("Email") String email, Model model, @ModelAttribute("newUserTest") UserDto newUser) {

		String userURL = newUser.getLinkedInPublicProfileUrl();
		String name = newUser.getLinkedInFirstName() + " " + newUser.getLinkedInLastName();

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		String subject = "ChirpedIn Chirp Chirp";
		String body = "You've been chirped by " + name + " who is currently seeking a mentor!\n"
				+ "If interested in mentoring " + name + " please visit their LinkedIn profile at " + userURL
				+ " and connect with them now!";

		dao.chirp(email, subject, body);
		// model.addAttribute("test", firstName + " " + lastName);

		List<UserDto> uniqueMatchesList = dao.findMentor(newUser);
		for (UserDto userDto : uniqueMatchesList) {// for each mentor, print matching skills

			ChirpedIn.setHaveSkills(userDto);
			ChirpedIn.setNeedSkills(userDto);
			// ChirpedIn.populateNetworkingSkills(uniqueMatchesList.get(i));
			// ChirpedIn.setMatchingSkillCounts(userDto);
			ChirpedIn.setAllMatchingSkills(newUser, userDto);

			// Used to calculate haveSkills and needSkills length
			String[] haveSkillArr = userDto.getHaveSkills().split(",");
			userDto.setHaveSkillCount(haveSkillArr.length);

			String[] needSkillArr = userDto.getNeedSkills().split(",");
			userDto.setNeedSkillCount(needSkillArr.length);

			// Used to calculate matchingMentor and matchingMentee
			String[] matchingMentorArr = userDto.getMatchingMentorSkills().split(",");
			userDto.setMatchingMentorSkillCount(matchingMentorArr.length);

			String[] matchingMenteeArr = userDto.getMatchingMenteeSkills().split(",");
			userDto.setMatchingMenteeSkillCount(matchingMenteeArr.length);

			ChirpedIn.setConnectionTypeFlags(userDto);
			ChirpedIn.calculateMatchPercentages(newUser, userDto);

			double mentorMatchPercent = ((double) userDto.getMatchingMentorSkillCount()
					/ (double) newUser.getNeedSkillCount()) * 100;
			userDto.setMentorMatchPercent(mentorMatchPercent);

		}

		List<UserDto> tempArray = new ArrayList<UserDto>();

		for (UserDto userDto : uniqueMatchesList) {
			tempArray.add(userDto);
		}

		for (UserDto userDto : tempArray) {

			if (userDto.getMatchingMentorSkills().isEmpty()) {
				uniqueMatchesList.remove(userDto);
			}

		}

		uniqueMatchesList.sort(new MentorListComparator());

		model.addAttribute("mentorresults", uniqueMatchesList);

		return "matches";

	}

}
