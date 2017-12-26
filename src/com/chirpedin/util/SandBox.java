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
		UserDto criteriaDto = new UserDto();
		UserDto matchedDto = new UserDto();
		
		
		criteriaDto.setLinkedInFirstName("Criteria");
		matchedDto.setLinkedInFirstName("Matched");
		
		criteriaDto.setHaveSkills("html css java");
		matchedDto.setHaveSkills("html css java");
		
		criteriaDto.setNeedSkills("spring OOD something else");
		matchedDto.setNeedSkills("spring OOD something else");
		
		criteriaDto.setNetworkingSkills("networking skill is criteria");
		matchedDto.setNetworkingSkills(" ");
		
		/*ChirpedIn.setHaveSkills(matchedDto);
		ChirpedIn.setNeedSkills(matchedDto);
		ChirpedIn.setNetworkingSkills(matchedDto);
		*/ChirpedIn.setUserSkillCount(matchedDto);

		ChirpedIn.setAllMatchingSkills(criteriaDto, matchedDto);
		ChirpedIn.setMatchingSkillCounts(matchedDto);

		ChirpedIn.setConnectionTypeFlags(matchedDto);
		ChirpedIn.calculateMatchPercentages(criteriaDto, matchedDto);

		
		
		System.out.println(criteriaDto + "\n" + matchedDto);
		
	}
}
