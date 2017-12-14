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


	public MentorListComparator() {
	}

	@Override
	public int compare(Object user1, Object user2) {
		if (((UserDto) user1).getMentorMatchPercent() < (((UserDto) user2).getMentorMatchPercent())) {
			return 1;
		} else if (((UserDto) user1).getMentorMatchPercent() == (((UserDto) user2).getMentorMatchPercent())) {
			return 0;
		} else {
			return -1;
		}

	}

}