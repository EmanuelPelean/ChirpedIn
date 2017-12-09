/**
 * 
 */
package com.chirpedin.util;

import java.util.ArrayList;

import com.chirpedin.dto.UserDto;

/**
 * @author timjohnson This class contains static methods to manipulate DTO data
 */
public class ChirpedIn {
	/***
	 * loop through UserDto menteeSkills fields, return string of skills the UserDto
	 * contains
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String createSkillsHave(UserDto userDto) {
		ArrayList<String> skillsHaveArrayList = new ArrayList<String>();
		String skillsHaveString = "";

		if (userDto.getMenteeSkillsJava()) {
			skillsHaveArrayList.add("java");
			skillsHaveString += " " + "java";
		}

		if (userDto.getMenteeSkillsJsp()) {
			skillsHaveArrayList.add("jsp");
			skillsHaveString += " " + "jsp";
		}

		if (userDto.getMenteeSkillsCSS()) {
			skillsHaveArrayList.add("css");
			skillsHaveString += " " + "css";
		}

		return skillsHaveString;

	}

	/***
	 * Takes in a userDto, checks its fields and populates its skillsHave field with
	 * a string of all the skills this the UserDto has
	 * 
	 * @param userDto
	 * @return
	 */
	public static void populateSkillsHave(UserDto userDto) {
		userDto.setHaveSkills(createSkillsHave(userDto));

	}

}
