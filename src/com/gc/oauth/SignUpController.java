/**
 * 
 */
package com.gc.oauth;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dao.UsersDao;
import com.gc.dto.UserDto;
import com.gc.factory.DaoFactory;

/**
 * @author timjohnson
 *
 */

@Controller
public class SignUpController {

//	@RequestMapping({"/signup"})
//	public ModelAndView home2(Model model){
//		
//		//binding form to pojo
//		
//		
//		return new ModelAndView("signup", "command", new UserDto());//Since there is no model I could return string with view name
//	}
	
//	@RequestMapping(value = {"/signup"}, method= RequestMethod.POST)
//	public ModelAndView signupPost(@ModelAttribute("command") UserDto newUser){
//		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
//		
//		System.out.println(newUser.getFirstname());
//		dao.insertUser(newUser);
//		
//		List<UserDto> matches = dao.getMatches(newUser);
//		return new ModelAndView("matches", "matchmodel", matches);//Since there is no model I could return string with view name
//	}
	
	@RequestMapping(value = {"/signupMentor"}, method= RequestMethod.POST)
	public ModelAndView signupPost(@ModelAttribute("mentor") UserDto newUser){
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
		
		System.out.println(newUser.getLinkedInFirstName());
		dao.insertUser(newUser);
		
		List<UserDto> matches = dao.getMatches(newUser);
		return new ModelAndView("matches", "matchmodel", matches);//Since there is no model I could return string with view name
	}
	
}
