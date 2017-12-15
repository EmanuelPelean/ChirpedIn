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
		skillsString += skill + ",";

		return skillsString;
	}

	/***
	 * Takes a UserDto, determines if the user is a Mentor, Mentee, or Networker
	 * based on the matchingSkills set
	 * 
	 * @param userDto
	 */
	public static void setConnectionTypeFlags(UserDto userDto) {
		// TODO this seems to provide false negatives for users logging in via LinkedIn
		// (as opposed to our testing) propose a different method to check for
		// negatives. Maybe split to an array and then count?

		if (userDto.getMatchingMentorSkillCount() > 0) {
			userDto.setMentorMatch(true);
		}

		if (userDto.getMatchingMenteeSkillCount() > 0) {
			userDto.setMenteeMatch(true);
		}
//
//		if (userDto.getMatchingNetworkingSkillCount() > 0) {
//			userDto.setNetworkingMatch(true);
//		}
	}

	/**
	 * sets the number of matching skills for Mentor, Mentee, and Networking, this
	 * will help with the matchPercent calculations
	 * 
	 * @param userDto
	 */
	public static void setMatchingSkillCounts(UserDto userDto) {
		userDto.setMatchingMenteeSkillCount(userDto.getMatchingMenteeSkills().split(",").length);
		userDto.setMatchingMentorSkillCount(userDto.getMatchingMentorSkills().split(",").length);
//		userDto.setMatchingNetworkingSkillCount(userDto.getMatchingNetworkingSkills().split(",").length);

//		System.out.println(userDto.getLinkedInFirstName() + "\nMatchingMenteeSkillCount: "
//				+ userDto.getMatchingMenteeSkillCount());
//		System.out.println("MatchingMentorSkillCount: " + userDto.getMatchingMentorSkillCount());
//		System.out.println("MatchingNetworkingSkillCount: " + userDto.getMatchingNetworkingSkillCount() + "\n");
	}

	public static void setSkillCount(UserDto userDto) {
		String[] haveSkillArr = userDto.getHaveSkills().split(",");
		userDto.setHaveSkillCount(haveSkillArr.length);
		
		String[] needSkillArr = userDto.getNeedSkills().split(",");
		userDto.setNeedSkillCount(needSkillArr.length);
//		System.out.println("IMPORTANT!!! needSkillArr.length = " +needSkillArr.length );
		
//		userDto.setHaveSkillCount(userDto.getHaveSkills().split(",").length);
//		userDto.setNeedSkillCount(userDto.getNeedSkills().split(",").length);
//		userDto.setNetworkingSkillCount(userDto.getNetworkingSkills().split(",").length);
		
	}
	
	
	/***
	 * populates all three skill strings at all at once
	 * 
	 * @param userDto
	 */
	public static void populateAllSkills(UserDto userDto) {
		populateHaveSkills(userDto);
		populateNeedSkills(userDto);
//		populateNetworkingSkills(userDto);
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

		
//		userDto.setMatchingMenteeSkills(
//				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNeedSkills(), userDto.getHaveSkills()));
//		userDto.setMatchingMentorSkills(
//				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getHaveSkills(), userDto.getNeedSkills()));
		
		
		userDto.setMatchingMenteeSkills(
				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getHaveSkills(), userDto.getNeedSkills()));

		userDto.setMatchingMentorSkills(
				createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNeedSkills(), userDto.getHaveSkills()));
//
//		userDto.setMatchingNetworkingSkills(createStringOfCommonWordsInTwoStrings(criteriaUserDto.getNetworkingSkills(),
//				userDto.getNetworkingSkills()));

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
			return  ((double) numerator / (double) denominator) * 100 ;
		} else {
			return 0.0;
		}
	}

	/***
	 * takes in criteriaDto, calculates a percent match for a usderDto and stores it the user's match percentage fields 
	 * @param criteriaDto
	 * @param userDto
	 */
	public static void calculateMatchPercentages(UserDto criteriaDto, UserDto userDto) {
		double counter = 0.0;
		
		userDto.setMentorMatchPercent((calculatePercentMatch( userDto.getMatchingMenteeSkillCount(), criteriaDto.getNeedSkillCount() ) ) );
		userDto.setMenteeMatchPercent((calculatePercentMatch( userDto.getMatchingMentorSkillCount(), criteriaDto.getHaveSkillCount() ) ));
		
		
//		userDto.setMentorMatchPercent((calculatePercentMatch( userDto.getMatchingMenteeSkillCount(), criteriaDto.getNeedSkillCount() ) ) );
//		if (userDto.getMentorMatchPercent() > 0.00) { counter++;  }
//
//		userDto.setMenteeMatchPercent((calculatePercentMatch(userDto.getMatchingMentorSkillCount(), criteriaDto.getHaveSkillCount() ) ) ); 
//		if (userDto.getMenteeMatchPercent() > 0.00) {counter++; }
//
//		userDto.setNetworkingMatchPercent(( calculatePercentMatch( userDto.getMatchingNetworkingSkillCount(), criteriaDto.getNetworkingSkillCount() ) ) ); 
//		if (userDto.getNetworkingMatchPercent() > 0.00) { counter++; }
//
//		if (counter > 0.00) {
//			System.out.println(counter + ": " + userDto.getMentorMatchPercent() + " " + userDto.getMenteeMatchPercent() + " " + userDto.getNetworkingMatchPercent());
//			
//			userDto.setTotalMatchPercent((double) ((double)userDto.getMentorMatchPercent() + (double) userDto.getMenteeMatchPercent()
//					+ (double) userDto.getNetworkingMatchPercent()) / (double) counter); 
//		} else {
//			System.out.println("Skipped straight to zero loop.");
//			userDto.setTotalMatchPercent(0.0);
//		}

	}

	private static String createStringOfCommonWordsInTwoStrings(String firstString, String secondString) {
		String commonElementsString = "";
		String[] firstArr = firstString.split(",");
		String[] secondArr = secondString.split(",");

		for (String word : firstArr) {
			for (int i = 0; i < secondArr.length; i++) {
				if (secondArr[i].equals(word))
					commonElementsString += word + ",";
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

//		System.out.println("This is the populateHaveSkills method, haveSkills: " + userDto.getHaveSkills());
	}

	/***
	 * Takes in a userDto, checks its fields and populates its needSkills field with
	 * a string of all the skills this the UserDto needs
	 * 
	 * @param userDto
	 * @return void
	 */
	public static void populateNeedSkills(UserDto userDto) {
		userDto.setNeedSkills(createNeedSkills(userDto));

	}

	/***
	 * Takes in a userDto, checks its fields and populates its networking Skills
	 * field with a string of all the skills this the UserDto has
	 * 
	 * @param userDto
	 * @return void
	 */
//	public static void populateNetworkingSkills(UserDto userDto) {
//		userDto.setNetworkingSkills(createNetworkingSkills(userDto));
//
//	}

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
	public static String createNeedSkills(UserDto userDto) {
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
//	public static String createNetworkingSkills(UserDto userDto) {
//		ArrayList<String> skillsNetworkingArrayList = new ArrayList<String>();
//		String skillsNetworkingString = "";
//
//		// Populating Skills section first, then below, populates interest section
//
//		if (userDto.getNetworkingSkillsJava()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "java");
//		}
//
//		if (userDto.getNetworkingSkillsJsp()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "jsp");
//		}
//
//		if (userDto.getNetworkingSkillsJstl()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "jstl");
//		}
//
//		if (userDto.getNetworkingSkillsSql()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "sql");
//		}
//
//		if (userDto.getNetworkingSkillsJdbc()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "jdbc");
//		}
//
//		if (userDto.getNetworkingSkillsSpringMVC()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "springMVC");
//		}
//
//		if (userDto.getNetworkingSkillsHibernate()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "hibernate");
//		}
//
//		if (userDto.getNetworkingSkillsPhp()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "php");
//		}
//
//		if (userDto.getNetworkingSkillsJavaScript()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "javascript");
//		}
//
//		if (userDto.getNetworkingSkillsHTML()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "html");
//		}
//
//		if (userDto.getNetworkingSkillsCSS()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "css");
//		}
//
//		// populating Networking interest sections
//
//		if (userDto.getNetworkingFoodie()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "foodie");
//		}
//
//		if (userDto.getNetworkingGaming()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "gaming");
//		}
//
//		if (userDto.getNetworkingSports()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "sports");
//		}
//
//		if (userDto.getNetworkingAnime()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "anime");
//		}
//
//		if (userDto.getNetworkingFun()) {
//			skillsNetworkingString = updateArrayListAndStringWithNewSkill(skillsNetworkingArrayList,
//					skillsNetworkingString, "fun");
//		}
//
//		return skillsNetworkingString;
//
//	}
	
	

}
