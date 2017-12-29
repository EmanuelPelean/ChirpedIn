/**
 * Just an area to test out code snippets
 */
package com.chirpedin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;

import com.chirpedin.dao.UsersDao;
import com.chirpedin.dto.FavoriteDto;
import com.chirpedin.dto.UserDto;
import com.chirpedin.factory.DaoFactory;

/**
 * @author timjohnson
 *
 */
public class SandBox {
	public static void main(String[] args) {
		UsersDao dao = DaoFactory.getInstance(DaoFactory.USERSDAO);
	    UserDto user1 = new UserDto();
		
	    
	    user1.setLinkedInId("27_0934");
	    System.out.println("User ID: " + user1.getLinkedInId());
	    
		List<FavoriteDto> favorites = dao.getFavorites(user1);
		System.out.println("Results: " + favorites.size());
		for(FavoriteDto dto: favorites) {
			System.out.println(dto);
		}
		
		
		user1.setLinkedInId("26_1750");
	    System.out.println("User ID: " + user1.getLinkedInId());
		favorites = dao.getFavorites(user1);
		System.out.println("Results: " + favorites.size());		
		for(FavoriteDto dto: favorites) {
			System.out.println(dto);
		}
		
	}
}
