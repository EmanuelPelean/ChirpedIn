/**
 * 
 */
package com.gc.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dao.UserDaoImpl;
import com.gc.dao.UsersDao;
import com.gc.dto.UserDto;
import com.gc.factory.DaoFactory;
import com.gc.util.APICredentials;
import com.gc.util.MentorListComparator;
import com.sun.jersey.core.util.Base64;
import com.sun.xml.internal.ws.api.message.Header;



/**
 * @author 
 * 
 * LinkedIn
 *
 */
@Controller
public class HomeController {
	
	public static void main(String[] args) {
		ArrayList<String> skillsNeed = new ArrayList<String>();
		
		skillsNeed.add("php");
		skillsNeed.add("java");
		skillsNeed.add("css");
		
		
		ArrayList<String> skillsHave = new ArrayList<String>();
		
		skillsHave.add("php");
		//skillsHave.add("java");
		//skillsHave.add("css");
		int skillsCount = 0;
		for(String skill: skillsNeed) {
			if(skillsHave.contains(skill)) {				
				skillsCount++;
			}
		}
		
		if(skillsCount == skillsNeed.size()) {
			System.out.println("#1");
		}else if(skillsCount < skillsNeed.size()) {
			System.out.println("#2");
		}
		
				
	}
	
	/**
	 * Home page mapping (you can use "/" if you want)
	 * 1)Redirect users to request LinkedIn access – (provide a value
	 * 	for the state and scopes you need)
	 * home page has the hyperlink to do a get request to LinkedIN endpoit
	 * @return
	 */
	@RequestMapping({"/","/home"})
	public ModelAndView home(){
		
		return new ModelAndView("home", "","");//Since there is no model I could return string with view name
	}
	
	//this is called when the signup page is first opened
	@RequestMapping({"/signup"})
	public ModelAndView signupPage(Model model){
		System.out.println("Regular Signup");

		//binding form to pojo
				
		return new ModelAndView("signup", "command", new UserDto());
	}
	
	//this is called when the user clicks to submit form data
	@RequestMapping(value = {"/signup"}, method= RequestMethod.POST)
	public ModelAndView signupPost(@ModelAttribute("command") UserDto newUser, Model model){
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		
		dao.insertUser(newUser);
		
		
//		List<UserDto> matches = dao.getMatches(newUser, model);
//		System.out.println("Form Signup");
		List<UserDto> mentorList = dao.findMentor(newUser, model);
		mentorList.sort(new MentorListComparator(newUser));
		
		model.addAttribute("mentorresults", mentorList);
		
		return new ModelAndView("matches", "", "");
		
	}
	/**
	 *	3. Linked redirects back to this controller with a temporary code – 
		and (returned state/once should match state passed in step 2)
		4. Exchange temporary code for an access token
		5. Use access tokens to make calls to the LinkedIn Api on behalf of the user		
	 * @param code - code from https://LinkedIn.com/login/oauth/authorize call
	 * @param state - once value received from https://LinkedIn.com/login/oauth/authorize call to guard against cross site forgery attacks
	 * @return
	 */
	@RequestMapping("/result")
	public ModelAndView result(Model model, @RequestParam("code")String code, @RequestParam("state")String state){
		
		
		
		String jsonString = "";
		String accessToken ="";
		
		UserDto userDataDto = null;
		
		try {
			//4. Exchange temporary code for an access token
			URL url = new URL( 
					"https://www.linkedin.com/oauth/v2/accessToken?" +
					"grant_type=authorization_code&" +
					"code=" + code + "&" +
					"client_id=" + APICredentials.CLIENT_ID + "&" +
					"redirect_uri=http://localhost:8080/ChirpedIn/result&" +
					"client_secret=" + APICredentials.CLIENT_SECRET);
		
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
			con.setDoOutput(true); // Must add
			con.setRequestMethod("POST");
			con.setFixedLengthStreamingMode(0); // Must add
			con.setRequestProperty("Accept", "application/json");
		    
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String currentLine ="";
			
			while((currentLine  = reader.readLine()) != null ){
				jsonString += currentLine;
			}
			
			JSONObject jsonObj = new JSONObject(jsonString);
			accessToken = jsonObj.getString("access_token");
			con.disconnect();

			//5. Use access token to make calls to the LinkedIn Api on behalf of the user
			
			url = new URL("https://api.linkedin.com/v1/people/~:(id,email-address,first-name,last-name,headline,location,summary,picture-url,picture-urls::(original),public-profile-url)?format=json&oauth2_access_token=" + accessToken);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			currentLine ="";
			jsonString ="";
			
			while((currentLine  = reader.readLine()) != null ){
				jsonString += currentLine;
			}		
			
			//Get the user data we want from the json string
			System.out.println(jsonString);
			jsonObj = new JSONObject(jsonString);
			
			
			String userID = (String) jsonObj.get("id");
			String firstName = (String) jsonObj.get("firstName");
			String lastName = (String) jsonObj.get("lastName");
			String headline = (String) jsonObj.get("headline");
//			String location = (String) jsonObj.get("name");
//			String summary = (String) jsonObj.get("summary");
			String smallPic = (String) jsonObj.get("pictureUrl");
//			String largePic = (String) jsonObj.get("values");
			String profileUrl = (String) jsonObj.get("publicProfileUrl");
//			String email = (String) jsonObj.get("email-address");
			
			userDataDto = new UserDto();
			
			userDataDto.setLinkedInId(userID);
			userDataDto.setLinkedInFirstName(firstName);
			userDataDto.setLinkedInLastName(lastName);
			userDataDto.setLinkedInHeadline(headline);
//			userDataDto.setLinkedInLocation(location);
//			userDataDto.setLinkedInSummary(summary);
			userDataDto.setLinkedInPictureUrl(smallPic);
//			userDataDto.setLinkedInLargePictureUrl(largePic);
			userDataDto.setLinkedInPublicProfileUrl(profileUrl);
//			userDataDto.setLinkedInEmail(email);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * TODO Change to return the name and avatar image in the instead of the raw json data the model
		 */
		model.addAttribute("data", userDataDto);
		return new ModelAndView("signup", "command", new UserDto());	
	}	
	
	@RequestMapping("/matches")
	public String showMatches(Model model) {
		
		
		return "matches";
	}
	
}
