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
public class UserDaoImpl implements UsersDao {

	/* (non-Javadoc)
	 * @see com.gc.dao.UsersDao#insertUser(com.gc.dto.UserDto)
	 */
	@Override
	public void insertUser(UserDto newUser) {
		// TODO hibernate code will go in here
		
	}

	/* (non-Javadoc)
	 * @see com.gc.dao.UsersDao#getMatches(com.gc.dto.UserDto)
	 */
	@Override
	public List<UserDto> getMatches(UserDto newUser) {
		// TODO matching algorythm
		return null;
	}

}
