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

	// user functions
	void insertUser(UserDto newUser);
	void updateUser(UserDto newUser);
	UserDto getUser();

	
	// get matches
	List<UserDto> getMatches(UserDto newUser);
	List<UserDto> getMatches(UserDto newUser, Model model);

	
	// get matching mentors
	List<UserDto> matchMentor(UserDto newUser);
	List<UserDto> matchMentor(UserDto newUser, Model model);
	List<UserDto> findMentor(UserDto userCriteriaDto);

	
	// get matching mentees
	List<UserDto> matchMentee(UserDto newUser);
	List<UserDto> findMentee(UserDto userCriteriaDto);
	
	// get matching networkers
	List<UserDto> matchNetworking(UserDto newUser);

	List<UserDto> getChirped(UserDto newUser);

	// save match to user's favorites
	void saveAsFav(String userID);
	void addFavorites(UserDto userDto, UserDto selectedDto);
	void addFavorites(String userDto, String selectedDto);
	void addFavorites(FavoriteDto newFavorite);

	// return favorites
	List<UserDto> getFavs(UserDto NewUser);
	List<FavoriteDto> getFavorites(UserDto user);
	
	// convert list of LinkedIds into an array list of Dtos
	List<UserDto> convertListOfFavDtosToListOfUserDtos(List<FavoriteDto> listOfFavoriteDtos);
	
	// delete 
	String deleteFav(String userID);

	// chirp matches
	void chirp(String receiverEmailID, String subject, String body);
	void chirp();


}
