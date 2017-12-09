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
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "java");
		}

		if (userDto.getMenteeSkillsJsp()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "jsp");
		}

		if (userDto.getMenteeSkillsJstl()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "jstl");
		}

		if (userDto.getMenteeSkillsSql()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "sql");
		}

		if (userDto.getMenteeSkillsJdbc()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "jdbc");
		}

		if (userDto.getMenteeSkillsSpringMVC()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "springMVC");
		}
		
		if (userDto.getMenteeSkillsHibernate()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "hibernate");
		}
		
		if (userDto.getMenteeSkillsPhp()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "php");
		}
		if (userDto.getMenteeSkillsJavaScript()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "javascript");
		}
		
		if (userDto.getMenteeSkillsHTML()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "html");
		}
		
		if (userDto.getMenteeSkillsCSS()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "css");
		}

		return skillsHaveString;

	}

	/**
	 * @param skillsHaveArrayList
	 * @param skillsHaveString
	 * @return
	 */
	private static String updateArrayListAndStringWithNewSkill(ArrayList<String> skillsHaveArrayList,
			String skillsHaveString, String skill) {
		skillsHaveArrayList.add(skill);
		skillsHaveString += " " + skill;

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
