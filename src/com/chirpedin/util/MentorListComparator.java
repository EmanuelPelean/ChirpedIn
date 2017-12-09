/**
 * 
 */
package com.chirpedin.util;

import java.util.Comparator;

import com.chirpedin.dto.UserDto;

/**
 * @author Manu
 *
 */
public class MentorListComparator implements Comparator {

	
	private UserDto newUser;

	public MentorListComparator(UserDto newUser) {
		this.newUser = newUser;
	}

	@Override
	public int compare(Object prod1, Object prod2) {
		if (((UserDto) prod1).getRank(newUser) < (((UserDto) prod2).getRank(newUser))) {
			return -1;
		} else if (((UserDto) prod1).getRank(newUser) == (((UserDto) prod2).getRank(newUser))) {
			return 0;
		} else {
			return 1;
		}

	}

}