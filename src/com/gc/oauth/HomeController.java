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

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.util.APICredentials;
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
	/**
	 *	3. Linked redirects back to this controller with a temporary code – 
		and (returned state/once should match state passed in step 2)
		4. Exchange temporary code for an access token
		5. Use access tokens to make calls to the LinkedIn Api on behalf of the user		
	 * @param code - code from https://LinkedIn.com/login/oauth/authorize call
	 * @param state - nonce value received from https://LinkedIn.com/login/oauth/authorize call to guard against cross site forgery attacks
	 * @return
	 */
	@RequestMapping("/result")
	public ModelAndView result(@RequestParam("code")String code, @RequestParam("state")String state){
		
		String jsonString = "";


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
			String accessToken = jsonObj.getString("access_token");
			con.disconnect();

			//5. Use access token to make calls to the LinkedIn Api on behalf of the user
			
			url = new URL("https://api.linkedin.com/v1/people/~:(id,num-connections,picture-url,location)?format=json&oauth2_access_token=" + accessToken);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			currentLine ="";
			jsonString ="";
			
			while((currentLine  = reader.readLine()) != null ){
				jsonString += currentLine;
			}		
			
			//Get the user data we want from the json string
			jsonObj = new JSONObject(jsonString);
			String name = "No name available";
			if(jsonObj.isNull("name") == false){
				name = (String) jsonObj.get("name");
			}
			
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
		return new ModelAndView("result", "data",jsonString);		
	}	
	
	@RequestMapping("/matches")
	public String showMatches(Model model) {
		
		
		return "matches";
	}
	
}
