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

import com.chirpedin.dto.UserDto;

/**
 * @author timjohnson
 *
 */
public class SandBox {
	public static void main(String[] args) {

		ArrayList<UserDto> dtoList1 = new ArrayList<UserDto>();
		ArrayList<UserDto> dtoList2 = new ArrayList<UserDto>();
		ArrayList<UserDto> uniqueDtoList = new ArrayList<UserDto>();
		
		dtoList1 = fillDtoArrayList(dtoList1, 0);
		dtoList2 = fillDtoArrayList(dtoList2, 3);
		
		List<UserDto> dtoList3 = ListUtils.union(dtoList1, dtoList2);
		
		System.out.println("dtoList1 size: " +  dtoList1.size());
		System.out.println("dtoList2 size: " +  dtoList2.size());
		System.out.println("dtoList3 size: " +  dtoList3.size());
		
		//create a list of strings for each DtoArrayList
		ArrayList<String> idList1 = new ArrayList<String>();		

		for (UserDto userDto : dtoList3) {
			idList1.add(userDto.getLinkedInId());
		}

		idList1.sort(null);
		
		System.out.println("\nNumber of LinkedIn IDs: " + idList1.size());
		System.out.println(idList1);

		Set<String> hashList = new HashSet<String>(idList1);
		ArrayList<String> uniqueList = new ArrayList<String>();
 
		for (String value : hashList) {
			uniqueList.add(value);
			for(UserDto userDto : dtoList3) {
				if(value.equals(userDto.getLinkedInId())){
					uniqueDtoList.add(userDto);
					break;
				}
				
			} 
		}

		System.out.println("\nNumber of unique LinkedIn IDs: " + uniqueList.size());
		System.out.println(uniqueList);

		System.out.println("\nNumber of unique LinkedIn Dtos: " + uniqueDtoList.size());
		System.out.println(uniqueDtoList);
	}

	private static ArrayList<UserDto> fillDtoArrayList(ArrayList<UserDto> dtoList1, int overlap) {

		for (int i = 0; i < 6; i++) {

			dtoList1.add(new UserDto());
			dtoList1.get(i).setLinkedInId("linkedInID_" + (i + overlap));
			// System.out.println(dtoList1.get(i));
		}
 
		return dtoList1;
	}
	// TODO Auto-generated method stub

	private static String createListOfCommonWordsInTwoStrings(String firstString, String secondString) {
		String commonElementsString = "";

		String[] firstArr = firstString.split(" ");
		String[] secondArr = secondString.split(" ");

		for (String word : firstArr) {
			for (int i = 0; i < secondArr.length; i++) {
				if (secondArr[i].equals(word))
					commonElementsString += word + " ";
			}
		}
		return commonElementsString;
	}

	/*
	 * String firstString = "jsp dog cat HTML jss"; String secondString =
	 * "jsp HTML jss";
	 * 
	 * System.out.println(createListOfCommonWordsInTwoStrings(firstString,
	 * secondString)); System.out.println(firstString.split(" ").length);
	 * System.out.println(secondString.split(" ").length);
	 */

	/*
	 * for (int i = 0; i < 6; i++) {
	 * 
	 * dtoList2.add(new UserDto()); dtoList2.get(i).setLinkedInId("linkedInID_" + (i
	 * + 3)); System.out.println(dtoList2.get(i)); }
	 */


	/*ArrayList<String> joinedList = new ArrayList<String>();
	System.out.println("joinedList size: " + joinedList.size());

	for (UserDto userDto : dtoList2) {
		joinedList.add(userDto.getLinkedInId());
	}

	for (UserDto userDto : dtoList1) {
		joinedList.add(userDto.getLinkedInId());
	}*/
}
