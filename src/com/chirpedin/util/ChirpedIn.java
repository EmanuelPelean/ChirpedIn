package com.chirpedin.util;

import java.util.ArrayList;

import com.chirpedin.dto.UserDto;

/**
 * @author timjohnson This class contains static methods to manipulate DTO data
 */
public class ChirpedIn {
	
	/**
	 * appends a string to a string and ArrayList
	 * 
	 * @param skillArrayList
	 * @param skillsString
	 * @return
	 */
	private static String updateArrayListAndStringWithNewSkill(ArrayList<String> skillArrayList,
			String skillsString, String skill) {
		skillArrayList.add(skill);
		skillsString += " " + skill;

		return skillsString;
	}

	/***
	 * Takes in a userDto, checks its fields and populates its haveSkills field with
	 * a string of all the skills this the UserDto has
	 * 
	 * @param userDto
	 * @return
	 */
	public static void populateHaveSkills(UserDto userDto) {
		userDto.setHaveSkills(createHaveSkills(userDto));

	}
	
	/***
	 * Takes in a userDto, checks its fields and populates its needSkills field with
	 * a string of all the skills this the UserDto needs
	 * 
	 * @param userDto
	 * @return
	 */
	public static void populateNeedSkills(UserDto userDto) {
		userDto.setNeedSkills(createNeedSkills(userDto));

	}
	
	
	
	/***
	 * loop through UserDto menteeSkills fields, return string of haveSkills the UserDto
	 * contains
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String createHaveSkills(UserDto userDto) {
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

	
	/***
	 * loop through UserDto mentorSkills fields, return string of skills the UserDto
	 * has
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String createNeedSkills(UserDto userDto) {
		ArrayList<String> skillsHaveArrayList = new ArrayList<String>();
		String skillsNeedString = "";

		if (userDto.getMentorSkillsJava()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "java");
		}

		if (userDto.getMentorSkillsJsp()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "jsp");
		}

		if (userDto.getMentorSkillsJstl()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "jstl");
		}

		if (userDto.getMentorSkillsSql()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "sql");
		}

		if (userDto.getMentorSkillsJdbc()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "jdbc");
		}

		if (userDto.getMentorSkillsSpringMVC()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "springMVC");
		}
		
		if (userDto.getMentorSkillsHibernate()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "hibernate");
		}
		
		if (userDto.getMentorSkillsPhp()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "php");
		}
		if (userDto.getMentorSkillsJavaScript()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "javascript");
		}
		
		if (userDto.getMentorSkillsHTML()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "html");
		}
		
		if (userDto.getMentorSkillsCSS()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsNeedString, "css");
		}

		return skillsNeedString;

	}

}
