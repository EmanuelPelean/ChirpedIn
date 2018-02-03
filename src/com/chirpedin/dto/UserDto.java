/**
 * 
 */
package com.chirpedin.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 *
 */
public class UserDto {

	
	// temporary variables to be calculated with every search
	private double totalMatchPercent;
	private int totalMatchPercentForDisplay;
	
	private double mentorMatchPercent;
	private double menteeMatchPercent;
	private double networkingMatchPercent;

	private boolean isMentorMatch = false;
	private boolean isMenteeMatch = false;
	private boolean isNetworkingMatch = false;

	private int matchingMentorSkillCount = 0;
	private int matchingMenteeSkillCount = 0;
	private int matchingNetworkingSkillCount = 0;
	
	private int totalSkillCount = 0;
	private int totalMatchingSkillCount = 0;
	
	private List<UserDto> matchResults;
	

	// these could be permanent but would need to add to database and plus we run
	// the risk that it there may be a disconnect between updating and adjusting
	// skills

	private String matchingMentorSkills = "";
	private String matchingMenteeSkills = "";
	private String matchingNetworkingSkills = "";

	private String haveSkills = "";
	private String needSkills = "";
	private String networkingSkills = "";

	private boolean mentorMatch;
	private boolean menteeMatch;
	private boolean networkingMatch;

	private int haveSkillCount;
	private int needSkillCount;
	private int networkingSkillCount;

	// LinkedIn Data
	private String linkedInFirstName;
	private String linkedInLastName;
	private String linkedInHeadline;
	private String linkedInLocation;
	private String linkedInPictureUrl;
	private String linkedInLargePictureUrl;
	private String linkedInPublicProfileUrl;
	private String linkedInEmail;
	private String linkedInId;

	// ChirpedIn Data

	// MENTOR FRONTEND skills you need (want a mentor to have)
	private Boolean mentorSkillsCSS;
	private Boolean mentorSkillsHTML;
	private Boolean mentorSkillsJavaScript;
	private Boolean mentorSkillsPhp;
	
	// MENTOR BACKEND 
	private Boolean mentorSkillsHibernate;
	private Boolean mentorSkillsJava;
	private Boolean mentorSkillsJdbc;
	private Boolean mentorSkillsJsp;
	private Boolean mentorSkillsJstl;
	private Boolean mentorSkillsSpringMVC;
	private Boolean mentorSkillsSql;
	
	
	// MENTEE FRONTEND skills you are have (are qualified to mentor in)
	private Boolean menteeSkillsPhp;
	private Boolean menteeSkillsJavaScript;
	private Boolean menteeSkillsHTML;
	private Boolean menteeSkillsCSS;	
	
	// MENTEE BACKEND 
	private Boolean menteeSkillsHibernate;
	private Boolean menteeSkillsJava;
	private Boolean menteeSkillsJdbc;
	private Boolean menteeSkillsJsp;
	private Boolean menteeSkillsJstl;
	private Boolean menteeSkillsSpringMVC;
	private Boolean menteeSkillsSql;

	
	// NETWORKING SOCIAL
	private Boolean networkingAnime;
	private Boolean networkingFoodie;
	private Boolean networkingFun;
	private Boolean networkingGaming;
	private Boolean networkingSports;

	// NETWORKING FRONTEND
	private Boolean networkingSkillsPhp;
	private Boolean networkingSkillsJavaScript;
	private Boolean networkingSkillsHTML;
	private Boolean networkingSkillsCSS;
	
	// NETWORKING BACKEND
	private Boolean networkingSkillsHibernate;
	private Boolean networkingSkillsJava;
	private Boolean networkingSkillsJdbc;
	private Boolean networkingSkillsJsp;
	private Boolean networkingSkillsJstl;
	private Boolean networkingSkillsSpringMVC;
	private Boolean networkingSkillsSql;


	
	public UserDto() {
		super();
	}
	
	public UserDto(String name) {
		super();
		this.linkedInFirstName = name;
		this.linkedInLargePictureUrl = "https://i.imgur.com/27CDISy.jpg";
	}

	
	@Override
	public String toString() {
		return "UserDto: " + linkedInFirstName + " " + linkedInLastName + " linkedIn ID: " + linkedInId
				+ "\n Total Match: " + String.format("%3.3f", totalMatchPercent)
				+ "\n percentMentorMatch: " + String.format("%3.3f", mentorMatchPercent) + ", percentMenteeMatch: " + String.format("%3.3f", menteeMatchPercent) + ", percentNetworkingMatch: " + String.format("%3.3f", networkingMatchPercent)
				+ "\n\n haveSkills: " + haveSkills 
				+ "\n needSkills: " + needSkills 
				+ "\n networkingSkills: " + networkingSkills
				+ "\n haveSkillCount: " + haveSkillCount + ", needSkillCount: " + needSkillCount + ", networkingSkillCount: " + networkingSkillCount 
				+ "\n totalSkillCount: " + totalSkillCount
				
				+ "\n\n matchingMentorSkills: " + matchingMentorSkills 
				+ "\n matchingMenteeSkills: " + matchingMenteeSkills 
				+ "\n matchingNetworkingSkills: " + matchingNetworkingSkills 
				+ "\n matchingMentorCount: " + matchingMentorSkillCount + ", matchingMenteeCount: " + matchingMenteeSkillCount  + ", matchingNetworkingCount: " + matchingNetworkingSkillCount
				+ "\n totalMatchingSkillCount: " + totalMatchingSkillCount
				
				+ "\n\n mentorMatch: " + mentorMatch + ", menteeMatch: " + menteeMatch + ", networkingMatch: " + networkingMatch 
				+ "\n\n\n";
	}

	public String getLinkedInFirstName() {
		return linkedInFirstName;
	}

	public void setLinkedInFirstName(String linkedInFirstName) {
		this.linkedInFirstName = linkedInFirstName;
	}

	public String getLinkedInLastName() {
		return linkedInLastName;
	}

	public void setLinkedInLastName(String linkedInLastName) {
		this.linkedInLastName = linkedInLastName;
	}

	public String getLinkedInHeadline() {
		return linkedInHeadline;
	}

	public void setLinkedInHeadline(String linkedInHeadline) {
		this.linkedInHeadline = linkedInHeadline;
	}

	public String getLinkedInLocation() {
		return linkedInLocation;
	}

	public void setLinkedInLocation(String linkedInLocation) {
		this.linkedInLocation = linkedInLocation;
	}

	public String getLinkedInPictureUrl() {
		return linkedInPictureUrl;
	}

	public void setLinkedInPictureUrl(String linkedInPictureUrl) {
		this.linkedInPictureUrl = linkedInPictureUrl;
	}

	public String getLinkedInLargePictureUrl() {
		return linkedInLargePictureUrl;
	}

	public void setLinkedInLargePictureUrl(String linkedInLargePictureUrl) {
		this.linkedInLargePictureUrl = linkedInLargePictureUrl;
	}

	public String getLinkedInPublicProfileUrl() {
		return linkedInPublicProfileUrl;
	}

	public void setLinkedInPublicProfileUrl(String linkedInPublicProfileUrl) {
		this.linkedInPublicProfileUrl = linkedInPublicProfileUrl;
	}

	public String getLinkedInEmail() {
		return linkedInEmail;
	}

	public void setLinkedInEmail(String linkedInEmail) {
		this.linkedInEmail = linkedInEmail;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	public Boolean getMentorSkillsJava() {
		return mentorSkillsJava;
	}

	public void setMentorSkillsJava(Boolean mentorSkillsJava) {
		this.mentorSkillsJava = mentorSkillsJava;
	}

	public Boolean getMentorSkillsJsp() {
		return mentorSkillsJsp;
	}

	public void setMentorSkillsJsp(Boolean mentorSkillsJsp) {
		this.mentorSkillsJsp = mentorSkillsJsp;
	}

	public Boolean getMentorSkillsJstl() {
		return mentorSkillsJstl;
	}

	public void setMentorSkillsJstl(Boolean mentorSkillsJstl) {
		this.mentorSkillsJstl = mentorSkillsJstl;
	}

	public Boolean getMentorSkillsSql() {
		return mentorSkillsSql;
	}

	public void setMentorSkillsSql(Boolean mentorSkillsSql) {
		this.mentorSkillsSql = mentorSkillsSql;
	}

	public Boolean getMentorSkillsJdbc() {
		return mentorSkillsJdbc;
	}

	public void setMentorSkillsJdbc(Boolean mentorSkillsJdbc) {
		this.mentorSkillsJdbc = mentorSkillsJdbc;
	}

	public Boolean getMentorSkillsSpringMVC() {
		return mentorSkillsSpringMVC;
	}

	public void setMentorSkillsSpringMVC(Boolean mentorSkillsSpringMVC) {
		this.mentorSkillsSpringMVC = mentorSkillsSpringMVC;
	}

	
	public Boolean getMentorSkillsHibernate() {
		return mentorSkillsHibernate;
	}

	public void setMentorSkillsHibernate(Boolean mentorSkillsHibernate) {
		this.mentorSkillsHibernate = mentorSkillsHibernate;
	}

	public Boolean getMentorSkillsPhp() {
		return mentorSkillsPhp;
	}

	public void setMentorSkillsPhp(Boolean mentorSkillsPhp) {
		this.mentorSkillsPhp = mentorSkillsPhp;
	}

	public Boolean getMentorSkillsJavaScript() {
		return mentorSkillsJavaScript;
	}

	public void setMentorSkillsJavaScript(Boolean mentorSkillsJavaScript) {
		this.mentorSkillsJavaScript = mentorSkillsJavaScript;
	}

	public Boolean getMentorSkillsHTML() {
		return mentorSkillsHTML;
	}

	public void setMentorSkillsHTML(Boolean mentorSkillsHTML) {
		this.mentorSkillsHTML = mentorSkillsHTML;
	}

	public Boolean getMentorSkillsCSS() {
		return mentorSkillsCSS;
	}

	public void setMentorSkillsCSS(Boolean mentorSkillsCSS) {
		this.mentorSkillsCSS = mentorSkillsCSS;
	}

	public Boolean getMenteeSkillsJava() {
		return menteeSkillsJava;
	}

	public void setMenteeSkillsJava(Boolean menteeSkillsJava) {
		this.menteeSkillsJava = menteeSkillsJava;
	}

	public Boolean getMenteeSkillsJsp() {
		return menteeSkillsJsp;
	}

	public void setMenteeSkillsJsp(Boolean menteeSkillsJsp) {
		this.menteeSkillsJsp = menteeSkillsJsp;
	}

	public Boolean getMenteeSkillsJstl() {
		return menteeSkillsJstl;
	}

	public void setMenteeSkillsJstl(Boolean menteeSkillsJstl) {
		this.menteeSkillsJstl = menteeSkillsJstl;
	}

	public Boolean getMenteeSkillsSql() {
		return menteeSkillsSql;
	}

	public void setMenteeSkillsSql(Boolean menteeSkillsSql) {
		this.menteeSkillsSql = menteeSkillsSql;
	}

	public Boolean getMenteeSkillsJdbc() {
		return menteeSkillsJdbc;
	}

	public void setMenteeSkillsJdbc(Boolean menteeSkillsJdbc) {
		this.menteeSkillsJdbc = menteeSkillsJdbc;
	}

	public Boolean getMenteeSkillsSpringMVC() {
		return menteeSkillsSpringMVC;
	}

	public void setMenteeSkillsSpringMVC(Boolean menteeSkillsSpringMVC) {
		this.menteeSkillsSpringMVC = menteeSkillsSpringMVC;
	}

	public Boolean getMenteeSkillsHibernate() {
		return menteeSkillsHibernate;
	}

	public void setMenteeSkillsHibernate(Boolean menteeSkillsHibernate) {
		this.menteeSkillsHibernate = menteeSkillsHibernate;
	}

	public Boolean getMenteeSkillsPhp() {
		return menteeSkillsPhp;
	}

	public void setMenteeSkillsPhp(Boolean menteeSkillsPhp) {
		this.menteeSkillsPhp = menteeSkillsPhp;
	}

	public Boolean getMenteeSkillsJavaScript() {
		return menteeSkillsJavaScript;
	}

	public void setMenteeSkillsJavaScript(Boolean menteeSkillsJavaScript) {
		this.menteeSkillsJavaScript = menteeSkillsJavaScript;
	}

	public Boolean getMenteeSkillsHTML() {
		return menteeSkillsHTML;
	}

	public void setMenteeSkillsHTML(Boolean menteeSkillsHTML) {
		this.menteeSkillsHTML = menteeSkillsHTML;
	}

	public Boolean getMenteeSkillsCSS() {
		return menteeSkillsCSS;
	}

	public void setMenteeSkillsCSS(Boolean menteeSkillsCSS) {
		this.menteeSkillsCSS = menteeSkillsCSS;
	}

	public Boolean getNetworkingFoodie() {
		return networkingFoodie;
	}

	public void setNetworkingFoodie(Boolean networkingFoodie) {
		this.networkingFoodie = networkingFoodie;
	}

	public Boolean getNetworkingGaming() {
		return networkingGaming;
	}

	public void setNetworkingGaming(Boolean networkingGaming) {
		this.networkingGaming = networkingGaming;
	}

	public Boolean getNetworkingSports() {
		return networkingSports;
	}

	public void setNetworkingSports(Boolean networkingSports) {
		this.networkingSports = networkingSports;
	}

	public Boolean getNetworkingAnime() {
		return networkingAnime;
	}

	public void setNetworkingAnime(Boolean networkingAnime) {
		this.networkingAnime = networkingAnime;
	}

	public Boolean getNetworkingFun() {
		return networkingFun;
	}

	public void setNetworkingFun(Boolean networkingFun) {
		this.networkingFun = networkingFun;
	}

	public Boolean getNetworkingSkillsJava() {
		return networkingSkillsJava;
	}

	public void setNetworkingSkillsJava(Boolean networkingSkillsJava) {
		this.networkingSkillsJava = networkingSkillsJava;
	}

	public Boolean getNetworkingSkillsJsp() {
		return networkingSkillsJsp;
	}

	public void setNetworkingSkillsJsp(Boolean networkingSkillsJsp) {
		this.networkingSkillsJsp = networkingSkillsJsp;
	}

	public Boolean getNetworkingSkillsJstl() {
		return networkingSkillsJstl;
	}

	public void setNetworkingSkillsJstl(Boolean networkingSkillsJstl) {
		this.networkingSkillsJstl = networkingSkillsJstl;
	}

	public Boolean getNetworkingSkillsSql() {
		return networkingSkillsSql;
	}

	public void setNetworkingSkillsSql(Boolean networkingSkillsSql) {
		this.networkingSkillsSql = networkingSkillsSql;
	}

	public Boolean getNetworkingSkillsJdbc() {
		return networkingSkillsJdbc;
	}

	public void setNetworkingSkillsJdbc(Boolean networkingSkillsJdbc) {
		this.networkingSkillsJdbc = networkingSkillsJdbc;
	}

	public Boolean getNetworkingSkillsSpringMVC() {
		return networkingSkillsSpringMVC;
	}

	public void setNetworkingSkillsSpringMVC(Boolean networkingSkillsSpringMVC) {
		this.networkingSkillsSpringMVC = networkingSkillsSpringMVC;
	}

	public Boolean getNetworkingSkillsHibernate() {
		return networkingSkillsHibernate;
	}

	public void setNetworkingSkillsHibernate(Boolean networkingSkillsHibernate) {
		this.networkingSkillsHibernate = networkingSkillsHibernate;
	}

	public Boolean getNetworkingSkillsPhp() {
		return networkingSkillsPhp;
	}

	public void setNetworkingSkillsPhp(Boolean networkingSkillsPhp) {
		this.networkingSkillsPhp = networkingSkillsPhp;
	}

	public Boolean getNetworkingSkillsJavaScript() {
		return networkingSkillsJavaScript;
	}

	public void setNetworkingSkillsJavaScript(Boolean networkingSkillsJavaScript) {
		this.networkingSkillsJavaScript = networkingSkillsJavaScript;
	}

	public Boolean getNetworkingSkillsHTML() {
		return networkingSkillsHTML;
	}

	public void setNetworkingSkillsHTML(Boolean networkingSkillsHTML) {
		this.networkingSkillsHTML = networkingSkillsHTML;
	}

	public Boolean getNetworkingSkillsCSS() {
		return networkingSkillsCSS;
	}

	public void setNetworkingSkillsCSS(Boolean networkingSkillsCSS) {
		this.networkingSkillsCSS = networkingSkillsCSS;
	}

	public String getMatchingMentorSkills() {
		return matchingMentorSkills;
	}

	public void setMatchingMentorSkills(String matchingMentorSkills) {
		this.matchingMentorSkills = matchingMentorSkills;
	}

	public String getMatchingMenteeSkills() {
		return matchingMenteeSkills;
	}

	public void setMatchingMenteeSkills(String matchingMenteeSkills) {
		this.matchingMenteeSkills = matchingMenteeSkills;
	}

	public boolean isMentorMatch() {
		return mentorMatch;
	}

	public void setMentorMatch(boolean mentorMatch) {
		this.mentorMatch = mentorMatch;
	}

	public boolean isMenteeMatch() {
		return menteeMatch;
	}

	public void setMenteeMatch(boolean menteeMatch) {
		this.menteeMatch = menteeMatch;
	}

	public boolean isNetworkingMatch() {
		return networkingMatch;
	}

	public void setNetworkingMatch(boolean networkingMatch) {
		this.networkingMatch = networkingMatch;
	}

	public String getHaveSkills() {
		return haveSkills;
	}

	public void setHaveSkills(String haveSkills) {
		this.haveSkills = haveSkills;
	}

	public String getNeedSkills() {
		return needSkills;
	}

	public void setNeedSkills(String needSkills) {
		this.needSkills = needSkills;
	}

	public int getHaveSkillCount() {
		return haveSkillCount;
	}

	public void setHaveSkillCount(int haveSkillCount) {
		this.haveSkillCount = haveSkillCount;
	}

	public int getNeedSkillCount() {
		return needSkillCount;
	}

	public void setNeedSkillCount(int needSkillCount) {
		this.needSkillCount = needSkillCount;
	}

	public int getNetworkingSkillCount() {
		return networkingSkillCount;
	}

	public void setNetworkingSkillCount(int networkingSkillCount) {
		this.networkingSkillCount = networkingSkillCount;
	}

	public String getMatchingNetworkingSkills() {
		return matchingNetworkingSkills;
	}

	public void setMatchingNetworkingSkills(String matchingNetworkingSkills) {
		this.matchingNetworkingSkills = matchingNetworkingSkills;
	}

	public String getNetworkingSkills() {
		return networkingSkills;
	}

	public void setNetworkingSkills(String networkingSkills) {
		this.networkingSkills = networkingSkills;
	}

	public double getTotalMatchPercent() {
		return totalMatchPercent;
	}

	public void setTotalMatchPercent(double totalMatchPercent) {
		this.totalMatchPercent = totalMatchPercent;
	}

	public double getMentorMatchPercent() {
		return mentorMatchPercent;
	}

	public void setMentorMatchPercent(double mentorMatchPercent) {
		this.mentorMatchPercent = mentorMatchPercent;
	}

	public double getMenteeMatchPercent() {
		return menteeMatchPercent;
	}

	public void setMenteeMatchPercent(double menteeMatchPercent) {
		this.menteeMatchPercent = menteeMatchPercent;
	}

	public double getNetworkingMatchPercent() {
		return networkingMatchPercent;
	}

	public void setNetworkingMatchPercent(double networkingMatchPercent) {
		this.networkingMatchPercent = networkingMatchPercent;
	}

	public int getMatchingMentorSkillCount() {
		return matchingMentorSkillCount;
	}

	public void setMatchingMentorSkillCount(int matchingMentorSkillCount) {
		this.matchingMentorSkillCount = matchingMentorSkillCount;
	}

	public int getMatchingMenteeSkillCount() {
		return matchingMenteeSkillCount;
	}

	public void setMatchingMenteeSkillCount(int matchingMenteeSkillCount) {
		this.matchingMenteeSkillCount = matchingMenteeSkillCount;
	}

	public int getMatchingNetworkingSkillCount() {
		return matchingNetworkingSkillCount;
	}

	public void setMatchingNetworkingSkillCount(int matchingNetworkingSkillCount) {
		this.matchingNetworkingSkillCount = matchingNetworkingSkillCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linkedInId == null) ? 0 : linkedInId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (linkedInId == null) {
			if (other.linkedInId != null)
				return false;
		} else if (!linkedInId.equals(other.linkedInId))
			return false;
		return true;
	}


	public int getTotalSkillCount() {
		return totalSkillCount;
	}


	public void setTotalSkillCount(int totalSkillCount) {
		this.totalSkillCount = totalSkillCount;
	}


	public int getTotalMatchingSkillCount() {
		return totalMatchingSkillCount;
	}


	public void setTotalMatchingSkillCount(int totalMatchingSkillCount) {
		this.totalMatchingSkillCount = totalMatchingSkillCount;
	}


	public int getTotalMatchPercentForDisplay() {
		return totalMatchPercentForDisplay;
	}


	public void setTotalMatchPercentForDisplay(int totalMatchPercentForDisplay) {
		this.totalMatchPercentForDisplay = totalMatchPercentForDisplay;
	}


	public List<UserDto> getMatchResults() {
		return matchResults;
	}


	public void setMatchResults(List<UserDto> matchResults) {
		this.matchResults = matchResults;
	}
	
	/*
	 * https://stackoverflow.com/questions/13429119/get-unique-values-from-arraylist-in-java
	 */

}
