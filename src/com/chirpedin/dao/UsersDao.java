/**
 * 
 */
package com.chirpedin.dao;

import java.util.List;

import org.springframework.ui.Model;

import com.chirpedin.dto.FavoriteDto;
import com.chirpedin.dto.UserDto;

/**
 * @author Manu
 *
 */
public interface UsersDao {

	void insertUser(UserDto newUser);
	void updateUser(UserDto newUser);

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

	List<UserDto> matchMentor(UserDto newUser, Model model);

	List<UserDto> findMentee(UserDto userCriteriaDto);
	List<UserDto> findMentor(UserDto userCriteriaDto);
	void chirp(String receiverEmailID, String subject, String body);
	
	void addFavorites(UserDto userDto, UserDto selectedDTO);
	List<FavoriteDto> getFavorites(UserDto user1);
	void addFavorites(String userDto, String selectedDTO);
}
