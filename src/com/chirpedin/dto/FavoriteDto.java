/**
 * 
 */
package com.chirpedin.dto;

import java.util.ArrayList;

/**
 * @author
 *
 */
public class FavoriteDto {

	
	// temporary variables to be calculated with every search
	private String linkedInId;
	private String favoriteLinkedInId;
	private String dateTimeStamp;
	
	
	public FavoriteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getLinkedInId() {
		return linkedInId;
	}
	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}
	public String getFavoriteLinkedInId() {
		return favoriteLinkedInId;
	}
	public void setFavoriteLinkedInId(String favoriteLinkedInId) {
		this.favoriteLinkedInId = favoriteLinkedInId;
	}
	public String getDateTimeStamp() {
		return dateTimeStamp;
	}
	public void setDateTimeStamp(String dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}


	@Override
	public String toString() {
		return "User linkedInId:" + linkedInId + ", favoriteLinkedInId:" + favoriteLinkedInId
				+ ", dateTimeStamp:" + dateTimeStamp + "]";
	}
	
	
	
	
}