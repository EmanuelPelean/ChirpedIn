/**
 * 
 */
package com.chirpedin.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * @author
 *
 */

@Entity
@Table(name="favorites")

public class FavoriteDto implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long primaryKey;
	
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


	public Long getPrimaryKey() {
		return primaryKey;
	}


	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}


	@Override
	public String toString() {
		return "FavoriteDto [primaryKey=" + primaryKey + ", linkedInId=" + linkedInId + ", favoriteLinkedInId="
				+ favoriteLinkedInId + ", dateTimeStamp=" + dateTimeStamp + "]";
	}


	
	
	
}