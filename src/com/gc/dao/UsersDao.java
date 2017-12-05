/**
 * 
 */
package com.gc.dao;

import java.util.List;

import com.gc.dto.UserDto;

/**
 * @author Manu
 *
 */
public interface UsersDao {

	void insertUser(UserDto newUser);

	List<UserDto> getMatches(UserDto newUser);

}


