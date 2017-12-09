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
	private static String updateArrayListAndStringWithNewSkill(ArrayList<String> skillArrayList, String skillsString,
			String skill) {
		skillArrayList.add(skill);
		skillsString += " " + skill;

		return skillsString;
	}



	/***
	 * takes criteriaUserDto and compares its haveSkills, needsSkills, and
	 * networkingSkills against another UserDto
	 * 
	 * @param criteriaUserDto
	 * @param userDto
	 * @return
	 * 
	 */
	public static void populateAllMatchingSkills(UserDto criteriaUserDto, UserDto userDto) {

		userDto.setMatchingMenteeSkills(
				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getHaveSkills(), userDto.getNeedSkills()));

		userDto.setMatchingMentorSkills(
				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNeedSkills(), userDto.getHaveSkills()));

		userDto.setMatchingNetworkingSkills(createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNetworkingSkills(),
				userDto.getNetworkingSkills()));

	}

	private static String createStringOfCommonWordsInTwoStrings(String firstString, String secondString) {
		String commonElementsString = "";
		String[] firstArr = firstString.split(" ");
		String[] secondArr = secondString.split(" ");

		for (String word : firstArr) {
			for (int i = 0; i < secondArr.length; i++) {
				if (secondArr[i].equals(word))
					commonElementsString += word + " ";
			}
		}
		return commonElementsString;
	}

	
	/***
	 * Takes in a userDto, checks its fields and populates its haveSkills field with
	 * a string of all the skills this the UserDto has
	 * 
	 * @param userDto
	 * @return void
	 */
	public static void populateHaveSkills(UserDto userDto) {
		userDto.setHaveSkills(createHaveSkills(userDto));

	}
	
	
	/***
	 * Takes in a userDto, checks its fields and populates its needSkills field with
	 * a string of all the skills this the UserDto needs
	 * 
	 * @param userDto
	 * @return void
	 */
	public static void populateNeedSkills(UserDto userDto) {
		userDto.setNeedSkills( createNeedSkills( userDto) );

	}
	
	/***
	 * Takes in a userDto, checks its fields and populates its networking Skills field with
	 * a string of all the skills this the UserDto has
	 * 
	 * @param userDto
	 * @return void
	 */
	public static void populateNetworkingSkills(UserDto userDto) {
		userDto.setNetworkingSkills(createNetworkingSkills(userDto));

	}
	

	/***
	 * loop through UserDto menteeSkills fields, return string of haveSkills the
	 * UserDto contains
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
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString,
					"javascript");
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
	 * loop through UserDto mentorSkills fields, return string of needSkills the
	 * UserDto contains
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String createNeedSkills(UserDto userDto) {
		ArrayList<String> skillsNeedArrayList = new ArrayList<String>();
		String skillsNeedString = "";

		if (userDto.getMentorSkillsJava()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "java");
		}

		if (userDto.getMentorSkillsJsp()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "jsp");
		}

		if (userDto.getMentorSkillsJstl()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "jstl");
		}

		if (userDto.getMentorSkillsSql()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "sql");
		}

		if (userDto.getMentorSkillsJdbc()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "jdbc");
		}

		if (userDto.getMentorSkillsSpringMVC()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "springMVC");
		}

		if (userDto.getMentorSkillsHibernate()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "hibernate");
		}

		if (userDto.getMentorSkillsPhp()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "php");
		}
		if (userDto.getMentorSkillsJavaScript()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString,
					"javascript");
		}

		if (userDto.getMentorSkillsHTML()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "html");
		}

		if (userDto.getMentorSkillsCSS()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "css");
		}

		return skillsNeedString;

	}
	

	/***
	 * loop through UserDto networkingSkills and other networking fields (i.e.
	 * foodie, gaming, sportsing, etc), return string of skills the UserDto has
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String createNetworkingSkills(UserDto userDto) {
		ArrayList<String> skillsNetworkingArrayList = new ArrayList<String>();
		String skillsNetworkingString = "";

		// Populating Skills section first, then below, populates interest section

		if (userDto.getNetworkingSkillsJava()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "java");
		}

		if (userDto.getNetworkingSkillsJsp()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "jsp");
		}

		if (userDto.getNetworkingSkillsJstl()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "jstl");
		}

		if (userDto.getNetworkingSkillsSql()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "sql");
		}

		if (userDto.getNetworkingSkillsJdbc()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "jdbc");
		}

		if (userDto.getNetworkingSkillsSpringMVC()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "springMVC");
		}

		if (userDto.getNetworkingSkillsHibernate()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "hibernate");
		}

		if (userDto.getNetworkingSkillsPhp()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "php");
		}

		if (userDto.getNetworkingSkillsJavaScript()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "javascript");
		}

		if (userDto.getNetworkingSkillsHTML()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "html");
		}

		if (userDto.getNetworkingSkillsCSS()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "css");
		}

		// populating Networking interest sections

		if (userDto.getNetworkingFoodie()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "foodie");
		}

		if (userDto.getNetworkingGaming()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "gaming");
		}

		if (userDto.getNetworkingSports()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "sports");
		}

		if (userDto.getNetworkingAnime()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "anime");
		}

		if (userDto.getNetworkingFun()) {
			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
					skillsNetworkingString, "fun");
		}

		return skillsNetworkingString;

	}

}
