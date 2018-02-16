package com.chirpedin.util;

import java.util.ArrayList;
import java.util.List;

import com.chirpedin.dto.UserDto;

/**
 * @author timjohnson This class contains static methods to manipulate DTO data
 */
public class ChirpedIn {

	/**
	 * takes in a User ArrayList returns a User ArrayList filtered based on the totalMatchPercenForDisplay field  
	 * 
	 * @param unfilteredMatchList
	 * @return
	 */
	public static ArrayList<UserDto> filterUserArrayListForTotalMatchPercentGreaterThan0(List<UserDto> unfilteredMatchList){
		ArrayList<UserDto> filteredList = new ArrayList<UserDto>();
		
		for(int i = 0; i< unfilteredMatchList.size(); i++) {
			if(unfilteredMatchList.get(i).getTotalMatchPercentForDisplay() > 0) {
				filteredList.add(unfilteredMatchList.get(i));
			}
		}
		
		System.out.println("\n\nMatch List BEFORE");
		System.out.println("(match percent, match percent for display)");
		for(int i = 0; i< unfilteredMatchList.size(); i++) {
			System.out.println(i + ". " + unfilteredMatchList.get(i).getLinkedInFirstName() 
					+  "(" + unfilteredMatchList.get(i).getTotalMatchPercent() + ", " 
					+ unfilteredMatchList.get(i).getTotalMatchPercentForDisplay() + ")");
		}
		
		
		System.out.println("\n\nMatch List FILTERED");
		System.out.println("(match percent, match percent for display)");
		for(int i = 0; i< filteredList.size(); i++) {
			System.out.println(i + ". " + filteredList.get(i).getLinkedInFirstName() 
					+  "(" + filteredList.get(i).getTotalMatchPercent() + ", " 
					+ filteredList.get(i).getTotalMatchPercentForDisplay() + ")");
		}
		return filteredList;
	}
	
	
	
	/**
	 * for a list of UserDtos, set their personal and matching fields
	 * 
	 * @param user
	 * @param uniqueMatchesList
	 */
	public static void setPersonalAndMatchFields(UserDto user, List<UserDto> uniqueMatchesList) {
		for (UserDto userDto : uniqueMatchesList) {

			ChirpedIn.setPersonalFields(userDto);
			ChirpedIn.setMatchFields(user, userDto);

			System.out.println(userDto);

		}
	}

	
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
		skillsString += skill + " ";

		return skillsString;
	}

	/***
	 * Takes a UserDto, determines if the user is a Mentor, Mentee, or Networker
	 * based on the matchingSkills set
	 * 
	 * @param userDto
	 */
	public static void setConnectionTypeFlags(UserDto userDto) {

		if (userDto.getMatchingMentorSkillCount() > 0) {
			userDto.setMentorMatch(true);
		}

		if (userDto.getMatchingMenteeSkillCount() > 0) {
			userDto.setMenteeMatch(true);
		}

		if (userDto.getMatchingNetworkingSkillCount() > 0) {
			userDto.setNetworkingMatch(true);
		}
	}

	/**
	 * sets the number of matching skills for Mentor, Mentee, and Networking from
	 * the perspective of the user console
	 * 
	 * this will help with the matchPercent calculations
	 * 
	 * 
	 * 
	 * @param userDto
	 */
	public static void setMatchingSkillCounts(UserDto userDto) {
		String[] matchingMentorSkillCountArr = userDto.getMatchingMentorSkills().trim().split(" ");
		String[] matchingMenteeSkillCountArr = userDto.getMatchingMenteeSkills().trim().split(" ");
		String[] matchingNetworkingSkillCountArr = userDto.getMatchingNetworkingSkills().trim().split(" ");

		if (userDto.getMatchingMentorSkills().trim().isEmpty()) {
			userDto.setMatchingMentorSkillCount(0);
		} else {
			userDto.setMatchingMentorSkillCount(matchingMentorSkillCountArr.length);
		}

		if (userDto.getMatchingMenteeSkills().trim().isEmpty()) {
			userDto.setMatchingMenteeSkillCount(0);
		} else {
			userDto.setMatchingMenteeSkillCount(matchingMenteeSkillCountArr.length);
		}

		if (userDto.getMatchingNetworkingSkills().trim().isEmpty() ) {
			userDto.setMatchingNetworkingSkillCount(0);
		} else {
			userDto.setMatchingNetworkingSkillCount(matchingNetworkingSkillCountArr.length);
		}
		
		userDto.setTotalMatchingSkillCount(userDto.getMatchingMentorSkillCount() + userDto.getMatchingMenteeSkillCount() + userDto.getMatchingNetworkingSkillCount());

	}

	/***
	 * converts string of skills to counts, sets the totalSkillsCount field
	 * @param userDto
	 */
	public static void setUserSkillCount(UserDto userDto) {
		String[] haveSkillArr = userDto.getHaveSkills().trim().split(" ");
		String[] needSkillArr = userDto.getNeedSkills().trim().split(" ");
		String[] networkingSkillArr = userDto.getNetworkingSkills().trim().split(" ");

		if (userDto.getHaveSkills().trim().isEmpty()) {
			userDto.setHaveSkillCount(0);
		} else {
			userDto.setHaveSkillCount(haveSkillArr.length);
		}

		if (userDto.getNeedSkills().trim().isEmpty()) {
			userDto.setNeedSkillCount(0);
		} else {
			userDto.setNeedSkillCount(needSkillArr.length);
		}

		if (userDto.getNetworkingSkills().trim().isEmpty()) {
			userDto.setNetworkingSkillCount(0);
		} else {
			userDto.setNetworkingSkillCount(networkingSkillArr.length);
		}
		
		userDto.setTotalSkillCount(userDto.getHaveSkillCount() + userDto.getNeedSkillCount() + userDto.getNetworkingSkillCount());
	}	

	/***
	 * populates the personal skills and set the skill count for a userDto
	 * 
	 * @param userDto
	 */
	public static void setPersonalFields(UserDto userDto) {
		setHaveSkills(userDto);
		setNeedSkills(userDto);
		setNetworkingSkills(userDto);
		setUserSkillCount(userDto);
	}

	/***
	 * takes criteriaUserDto and compares its haveSkills, needsSkills, and
	 * networkingSkills against another UserDto and populates the strings in the matching fields
	 * 
	 * @param criteriaUserDto
	 * @param userDto
	 * @return
	 * 
	 */
	public static void setMatchingSkills(UserDto criteriaUserDto, UserDto userDto) {

		userDto.setMatchingMenteeSkills(
				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getHaveSkills(), userDto.getNeedSkills()));

		userDto.setMatchingMentorSkills(
				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNeedSkills(), userDto.getHaveSkills()));

		userDto.setMatchingNetworkingSkills(createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNetworkingSkills(),
				userDto.getNetworkingSkills()));

	}
	
	/***
	 * takes a criteriaUserDto and set all the matching fields (skills, skill counts, connection types, % match) for a userDto
	 * @param criteriaUserDto
	 * @param userDto
	 */
	public static void setMatchFields(UserDto criteriaUserDto, UserDto userDto) {
	
		setMatchingSkills(criteriaUserDto, userDto);
		
		ChirpedIn.setMatchingSkillCounts(userDto);
		ChirpedIn.setConnectionTypeFlags(userDto);
		
		ChirpedIn.calculateMatchPercentages(criteriaUserDto, userDto);

		
	}
	/***
	 * takes a numerator and denominator and returns a percent
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static Double calculatePercentMatch(int numerator, int denominator) {
		if (denominator > 0) {
			return ((double) numerator / (double) denominator);
		} else {
			return 0.0;
		}
	}

	/***
	 * takes in criteriaDto, calculates a percent match for a usderDto and stores it
	 * the user's match percentage fields
	 * 
	 * @param criteriaDto
	 * @param userDto
	 */
	public static void calculateMatchPercentages(UserDto criteriaDto, UserDto userDto) {
		
		userDto.setMentorMatchPercent(calculatePercentMatch(userDto.getMatchingMentorSkillCount(), criteriaDto.getNeedSkillCount()));
		userDto.setMenteeMatchPercent(calculatePercentMatch(userDto.getMatchingMenteeSkillCount(), criteriaDto.getHaveSkillCount()));
		userDto.setNetworkingMatchPercent(calculatePercentMatch(userDto.getMatchingNetworkingSkillCount(), criteriaDto.getNetworkingSkillCount()));

		userDto.setTotalMatchPercent(calculatePercentMatch(userDto.getTotalMatchingSkillCount(), criteriaDto.getTotalSkillCount()));
		userDto.setTotalMatchPercentForDisplay((int) Math.round(userDto.getTotalMatchPercent() * 100));

	}

	public static String createStringOfCommonWordsInTwoStrings(String firstString, String secondString) {
		String commonElementsString = "";
		String[] firstArr = firstString.trim().split(" ");
		String[] secondArr = secondString.trim().split(" ");

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
	public static void setHaveSkills(UserDto userDto) {
		userDto.setHaveSkills(convertHaveSkillsBooleansToStrings(userDto));

	}

	/***
	 * Takes in a userDto, checks its fields and populates its needSkills field with
	 * a string of all the skills this the UserDto needs
	 * 
	 * @param userDto
	 * @return void
	 */
	public static void setNeedSkills(UserDto userDto) {
		userDto.setNeedSkills(convertNeedSkillsBooleansToStrings(userDto));

	}

	/***
	 * Takes in a userDto, checks its fields and populates its networking Skills
	 * field with a string of all the skills this the UserDto has
	 * 
	 * @param userDto
	 * @return void
	 */
	public static void setNetworkingSkills(UserDto userDto) {
		userDto.setNetworkingSkills(convertNetworkingSkillsBooleansToStrings(userDto));

	}

	/***
	 * loop through UserDto menteeSkills fields, return string of haveSkills the
	 * UserDto contains
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String convertHaveSkillsBooleansToStrings(UserDto userDto) {
		ArrayList<String> skillsHaveArrayList = new ArrayList<String>();
		String skillsHaveString = "";
		int totalItems = 0;

		if (userDto.getMenteeSkillsJava()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "java");
			totalItems++;
		}

		if (userDto.getMenteeSkillsJsp()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "jsp");
			totalItems++;
		}

		if (userDto.getMenteeSkillsJstl()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "jstl");
			totalItems++;
		}

		if (userDto.getMenteeSkillsSql()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "sql");
			totalItems++;
		}

		if (userDto.getMenteeSkillsJdbc()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "jdbc");
			totalItems++;
		}

		if (userDto.getMenteeSkillsSpringMVC()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "springMVC");
			totalItems++;
		}

		if (userDto.getMenteeSkillsHibernate()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "hibernate");
			totalItems++;
		}

		if (userDto.getMenteeSkillsPhp()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "php");
			totalItems++;
		}
		if (userDto.getMenteeSkillsJavaScript()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString,
					"javascript");
			totalItems++;
		}

		if (userDto.getMenteeSkillsHTML()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "html");
			totalItems++;
		}

		if (userDto.getMenteeSkillsCSS()) {
			skillsHaveString = updateArrayListAndStringWithNewSkill(skillsHaveArrayList, skillsHaveString, "css");
			totalItems++;
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
	public static String convertNeedSkillsBooleansToStrings(UserDto userDto) {
		ArrayList<String> skillsNeedArrayList = new ArrayList<String>();
		String skillsNeedString = "";
		int totalItems = 0;

		if (userDto.getMentorSkillsJava()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "java");
			totalItems++;
		}

		if (userDto.getMentorSkillsJsp()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "jsp");
			totalItems++;
		}

		if (userDto.getMentorSkillsJstl()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "jstl");
			totalItems++;
		}

		if (userDto.getMentorSkillsSql()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "sql");
			totalItems++;
		}

		if (userDto.getMentorSkillsJdbc()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "jdbc");
			totalItems++;
		}

		if (userDto.getMentorSkillsSpringMVC()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "springMVC");
			totalItems++;
		}

		if (userDto.getMentorSkillsHibernate()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "hibernate");
			totalItems++;
		}

		if (userDto.getMentorSkillsPhp()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "php");
			totalItems++;
		}
		if (userDto.getMentorSkillsJavaScript()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString,
					"javascript");
			totalItems++;
		}

		if (userDto.getMentorSkillsHTML()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "html");
			totalItems++;
		}

		if (userDto.getMentorSkillsCSS()) {
			skillsNeedString = updateArrayListAndStringWithNewSkill(skillsNeedArrayList, skillsNeedString, "css");
			totalItems++;
		}

		userDto.setNeedSkillCount(totalItems);
		return skillsNeedString;

	}

	/***
	 * loop through UserDto networkingSkills and other networking fields (i.e.
	 * foodie, gaming, sportsing, etc), return string of skills the UserDto has
	 * 
	 * @param userDto
	 * @return String
	 */
	public static String convertNetworkingSkillsBooleansToStrings(UserDto userDto) {
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
