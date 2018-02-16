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
import com.chirpedin.dto.FavoriteDto;
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
@SessionAttributes("user")
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
					//http://localhost:8080/ChirpedIn/result
					//http://chirpedin-env2.us-east-2.elasticbeanstalk.com/result
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
		// return new ModelAndView("signup2", "newUserDto", new UserDto());
		return new ModelAndView("signup", "newUserDto", new UserDto());
	}

	// called when the signup page is opened without going through the "results"
	// mapping
	@RequestMapping({ "/signup" })
	public ModelAndView signupPage(Model model) {
		System.out.println("Regular Signup");

		// binding form to UserDto

		return new ModelAndView("signup", "newUserDto", new UserDto());
	}

	/**
	 * called when the user submits form data on the startup page; does three
	 * things: 1) sends user-supplied info to the database 2) finds matches in the
	 * database 3) returns matches to the jsp page
	 * 
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView signupPost(@ModelAttribute("newUserDto") UserDto user, Model model, HttpSession session) {
		System.out.println("Entering sign up method called a form mapped to '/signup'");

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		// populate user DTO skills
		ChirpedIn.setPersonalFields(user);

		System.out.println("This is the new user's info: " + user);

		// Insert the user DTO into our MySQL database
		dao.insertUser(user);

		session.setAttribute("user", user);
		// List<UserDto> matches = dao.getMatches(user, model);

		// TODO create separate pulls for each match type
		// TODO create unique users list from all the matches (i.e. erase duplicates)
		// List<UserDto> mentorList = dao.findMentor(user);
		// find mentors based on criteria

		List<UserDto> mentorMatchesList = dao.findMentor(user); // find mentors based on criteria

		ChirpedIn.setPersonalAndMatchFields(user, mentorMatchesList);

		mentorMatchesList.sort(new MentorListComparator());
		
		List<UserDto> matchListFiltered = ChirpedIn.filterUserArrayListForTotalMatchPercentGreaterThan0(mentorMatchesList);	
		
		model.addAttribute("matchresults", matchListFiltered); // send data to view

		model.addAttribute("user", user);

		return new ModelAndView("matches2", "newUserDto", new UserDto());
		//return new ModelAndView("matches", "newUserDto", new UserDto());
		//return new ModelAndView("dashboard", "newUserDto", new UserDto());

	}

	
	// called when the signup page is opened without going through the "results"
	// mapping
	@RequestMapping({ "/signup2" })
	public ModelAndView signupPage2(Model model) {
		System.out.println("Regular Signup");

		// binding form to UserDto

		return new ModelAndView("signup2", "newUserDto", new UserDto());
	}

	/**
	 * called when the user submits form data on the startup page; does three
	 * things: 1) sends user-supplied info to the database 2) finds matches in the
	 * database 3) returns matches to the jsp page
	 * 
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/signup2" }, method = RequestMethod.POST)
	public ModelAndView signupPost2(@ModelAttribute("newUserDto") UserDto user, Model model, HttpSession session) {
		System.out.println("Entering sign up method called by '/signup'");

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		// populate user DTO skills
		ChirpedIn.setPersonalFields(user);

		System.out.println("This is the new user's info: " + user);

		// Insert the user DTO into our MySQL database
		dao.insertUser(user);

		session.setAttribute("user", user);
		// List<UserDto> matches = dao.getMatches(user, model);

		// TODO create separate pulls for each match type
		// TODO create unique users list from all the matches (i.e. erase duplicates)
		// List<UserDto> mentorList = dao.findMentor(user);
		// find mentors based on criteria

		List<UserDto> matchList = dao.findMentor(user); // find mentors based on criteria

		ChirpedIn.setPersonalAndMatchFields(user, matchList);

		matchList.sort(new MentorListComparator());
		
		List<UserDto> matchListFiltered = ChirpedIn.filterUserArrayListForTotalMatchPercentGreaterThan0(matchList);	
		
		model.addAttribute("matchresults", matchListFiltered); // send data to view

		model.addAttribute("user", user);

		return new ModelAndView("matches2", "newUserDto", new UserDto());
		//return new ModelAndView("matches", "newUserDto", new UserDto());
		//return new ModelAndView("dashboard", "newUserDto", new UserDto());

	}
	
	
	

	@RequestMapping("/matches")
	public String showMatches(@ModelAttribute("user") UserDto user,Model model) {
		
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		List<UserDto> matchList = dao.findMentor(user); // find mentors based on criteria
		
		ChirpedIn.setPersonalAndMatchFields(user, matchList);
		
		matchList.sort(new MentorListComparator());

		List<UserDto> matchListFiltered = ChirpedIn.filterUserArrayListForTotalMatchPercentGreaterThan0(matchList);	
		
		model.addAttribute("matchresults", matchListFiltered); // send data to view
		
		
		return "matches";
	}
	
	@RequestMapping("/matches2")
	public String showMatches2 (@ModelAttribute("user") UserDto user,Model model) {		
		
		System.out.println("Entering matches2 method'");
		
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		
		// print out matches again
		List<UserDto> matchList = dao.findMentor(user);
		ChirpedIn.setPersonalAndMatchFields(user, matchList);

		matchList.sort(new MentorListComparator());


		List<UserDto> matchListFiltered = ChirpedIn.filterUserArrayListForTotalMatchPercentGreaterThan0(matchList);	
		
		model.addAttribute("matchresults", matchListFiltered); // send data to view

		return "matches2";
	}

	// called when the dashboard.jsp page is opened without parameters
	@RequestMapping({ "/dashboard2" })
	public String dashboardPage(@ModelAttribute("user") UserDto user, Model model) {
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		
	
		int maxResults = 12; 
		int size;
		ArrayList<UserDto> topMatchList = new ArrayList<UserDto>(); 
		
		
		List<UserDto> matchList = dao.findMentor(user);
		
		ChirpedIn.setPersonalAndMatchFields(user, matchList);
		
		// sort to get the highest ranking matches
		matchList.sort(new MentorListComparator());
		
		size = Integer.min(maxResults, matchList.size()); 
		
		for(int i = size; i > 0; i--) {
			topMatchList.add(matchList.get(i));
		}

		
		// pull the favorites
		List<FavoriteDto> favorites = dao.getFavorites(user);
			System.out.println("method: dashboardPage \n favorites has size of: " + favorites.size() +"\n is empty? "+ favorites.isEmpty());
		
		
		if(!favorites.isEmpty()) {
		List<UserDto> favoriteDtoList = dao.convertListOfFavDtosToListOfUserDtos(favorites);
		ChirpedIn.setPersonalAndMatchFields(user, favoriteDtoList);
		model.addAttribute("favorites", favoriteDtoList);
		}
		
		List<UserDto> matchListFiltered = ChirpedIn.filterUserArrayListForTotalMatchPercentGreaterThan0(topMatchList);	
		
		model.addAttribute("matchresults", matchListFiltered); // send data to view
		
		return "dashboard2";
	}

	// called when the dashboard is opened via a link
	@RequestMapping(value = { "/dashboardclick" },  method = RequestMethod.POST)
	public String userDashboard(@ModelAttribute("user") UserDto user, Model model) {

		// send recent matches to view
		// model.addAttribute("matchresults", matchList);

		// send favorites to view
		// populate favorites with pic, name, and percent match
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		List<FavoriteDto> favorites = dao.getFavorites(user);
		List<UserDto> favoriteDtoList = dao.convertListOfFavDtosToListOfUserDtos(favorites);
		
		model.addAttribute("favorites", favoriteDtoList);

		return "dashboard2";
	}

	@RequestMapping(value = "/addFavorites")
	public String favoriteButton(@RequestParam("favoriteLinkedInId") String favoriteLinkedInId,
			@ModelAttribute("user") UserDto user, Model model) {

		FavoriteDto newFavorite = new FavoriteDto();

		newFavorite.setLinkedInId(user.getLinkedInId());
		newFavorite.setFavoriteLinkedInId(favoriteLinkedInId);

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		// TODO fix IdentifierGenerationException: ids for this class must be
		// manually assigned beforeQuery calling save(): com.chirpedin.dto.FavoriteDto
		//
		// dao.addFavorites(newFavorite);

		// for testing delete
		dao.addFavorites(favoriteLinkedInId, user.getLinkedInId());

		// print out matches again
		List<UserDto> uniqueMatchesList = dao.findMentor(user);
		ChirpedIn.setPersonalAndMatchFields(user, uniqueMatchesList);

		uniqueMatchesList.sort(new MentorListComparator());

		model.addAttribute("matchresults", uniqueMatchesList);

		return "matches2";

	}


	
	
	@RequestMapping("/getFavorites")
	public ModelAndView getFavorites(@ModelAttribute("user") UserDto user) {

		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		List<FavoriteDto> favorites = dao.getFavorites(user);
		System.out.println(favorites);

		return null;

	}

	/***
	 * sends email to selected user expressing an interest in connecting
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/chirp")
	public String chirpUserButton(@RequestParam("fName") String firstName, @RequestParam("lName") String lastName,
			@RequestParam("Email") String email, Model model, @ModelAttribute("user") UserDto user) {

		boolean sendActualEmail = false; // (dis)/(e)nable for testing
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

		if (sendActualEmail) {
			String userURL = user.getLinkedInPublicProfileUrl();
			String name = user.getLinkedInFirstName() + " " + user.getLinkedInLastName();

			String subject = "ChirpedIn Chirp Chirp";
			String body = "You've been chirped by " + name + " who is currently seeking a mentor!\n"
					+ "If interested in mentoring " + name + " please visit their LinkedIn profile at " + userURL
					+ " and connect with them now!";

			dao.chirp(email, subject, body);
			// model.addAttribute("test", firstName + " " + lastName);
		}

		// print out matches again
		List<UserDto> uniqueMatchesList = dao.findMentor(user);
		ChirpedIn.setPersonalAndMatchFields(user, uniqueMatchesList);

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

		return "matches2";

	}
	
	/***
	 * called when updateuserinfo page is opened directly
	 * takes the Session user, displays a page very similar to the sign up page
	 * that is pre-populated with the users current selections 
	 */
		@RequestMapping({ "/searchcriteria" })
		public ModelAndView searchCriteria(@ModelAttribute("user") UserDto user, Model model) {
			System.out.println("calling Update User Info Page");
			
			// TODO print out long list of selections and send the info to the view
	
			model.addAttribute("data", user);
			
			// MENTOR FRONTEND skills		
			if(user.getMentorSkillsCSS()) {
				model.addAttribute("mentorSkillsCSS", "true");
			}
			if(user.getMentorSkillsHTML()) {
				model.addAttribute("mentorSkillsHTML", "true");
			}
			if(user.getMentorSkillsJavaScript()) {
				model.addAttribute("mentorSkillsJavaScript", "true");
			}
			if(user.getMentorSkillsPhp()) {
				model.addAttribute("mentorSkillsPhp", "true");
			}
			
			// MENTOR BACKEND skills			
			if(user.getMentorSkillsHibernate()) {
				model.addAttribute("mentorSkillsHibernate", "true");
			}
			if(user.getMentorSkillsJava()) {
				model.addAttribute("mentorSkillsJava", "true");
			}
			if(user.getMentorSkillsJdbc()) {
				model.addAttribute("mentorSkillsJdbc", "true");
			}
			if(user.getMentorSkillsJsp()) {
				model.addAttribute("mentorSkillsJsp", "true");
			}
			if(user.getMentorSkillsJstl()) {
				model.addAttribute("mentorSkillsJstl", "true");
			}
			if(user.getMentorSkillsSpringMVC()) {
				model.addAttribute("mentorSkillsSpringMVC", "true");
			}
			if(user.getMentorSkillsSql()) {
				model.addAttribute("mentorSkillsSql", "true");
			}
			
			// MENTEE FRONTEND skills		
			if(user.getMenteeSkillsCSS()) {
				model.addAttribute("menteeSkillsCSS", "true");
			}
			if(user.getMentorSkillsHTML()) {
				model.addAttribute("menteeSkillsHTML", "true");
			}
			if(user.getMentorSkillsJavaScript()) {
				model.addAttribute("menteeJavaScript", "true");
			}
			if(user.getMentorSkillsPhp()) {
				model.addAttribute("menteeSkillsPhp", "true");
			}
			
			// MENTEE BACKEND skills			
			if(user.getMenteeSkillsHibernate()) {
				model.addAttribute("menteeSkillsHibernate", "true");
			}
			if(user.getMenteeSkillsJava()) {
				model.addAttribute("menteeSkillsJava", "true");
			}
			if(user.getMenteeSkillsJdbc()) {
				model.addAttribute("menteeSkillsJdbc", "true");
			}
			if(user.getMenteeSkillsJsp()) {
				model.addAttribute("menteeSkillsJsp", "true");
			}
			if(user.getMenteeSkillsJstl()) {
				model.addAttribute("menteeSkillsJstl", "true");
			}
			if(user.getMenteeSkillsSpringMVC()) {
				model.addAttribute("menteeSkillsSpringMVC", "true");
			}
			if(user.getMenteeSkillsSql()) {
				model.addAttribute("menteeSkillsSql", "true");
			}



			// binding form to UserDto

			return new ModelAndView("searchcriteria", "newUserDto", new UserDto());
		}

		/**
		 * called when the user submits form data on the startup page; does three
		 * things: 1) sends user-supplied info to the database 2) finds matches in the
		 * database 3) returns matches to the jsp page
		 * 
		 * @param user
		 * @param model
		 * @param session
		 * @return
		 */
		@RequestMapping(value = { "/updatesearchcriteria" }, method = RequestMethod.POST)
		public ModelAndView updateSearchCriteria(@ModelAttribute("newUserDto") UserDto user, Model model, HttpSession session) {
			System.out.println("Entering userUpdateCriteria method");
			
			UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);

			// populate user DTO skills
			ChirpedIn.setPersonalFields(user);
			System.out.println("This is the new user's info: " + user);

			// Insert the user DTO into our MySQL database
			dao.updateUser(user);

			
			return new ModelAndView("searchcriteria", "newUserDto", new UserDto());
		}

}
