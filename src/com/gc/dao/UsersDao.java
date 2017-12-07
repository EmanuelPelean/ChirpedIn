/**
 * 
 */
package com.gc.dao;

import java.util.List;

import org.springframework.ui.Model;

import com.gc.dto.UserDto;

/**
 * @author Manu
 *
 */
public interface UsersDao {

	void insertUser(UserDto newUser);

	List<UserDto> getMatches(UserDto newUser);

	// method to return mentors, returns array of matches
	List<UserDto> matchMentor(UserDto newUser);

	// method to return mentees, returns array of matches
	List<UserDto> matchMentee(UserDto newUser);

	// method to return networking matches, returns array of matches
	List<UserDto> matchNetworking(UserDto newUser);

	// method to return mentees, returns array of matches
	List<UserDto> getChirped(UserDto newUser);

	// method do chirp
	void chirp();

	// method to save a member to our user's favorites
	void saveAsFav(String userID);

	String deleteFav(String userID);

	// return favorites
	List<UserDto> getFavs(UserDto NewUser);

	UserDto getUser();

	List<UserDto> getMatches(UserDto newUser, Model model);
}
