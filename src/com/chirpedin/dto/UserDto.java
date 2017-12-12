/**
 * 
 */
package com.chirpedin.dto;

import java.util.ArrayList;

/**
 * @author
 *
 */
public class UserDto {

	private double percentMatch;

	// temporary variables to be calculated with every search
	private double totalMatchPercent;
	private double mentorMatchPercent;
	private double menteeMatchPercent;
	private double networkingMatchPercent;

	private boolean isMentorMatch = false;
	private boolean isMenteeMatch = false;
	private boolean isNetworkingMatch = false;

	private int matchingMentorSkillCount = 0;
	private int matchingMenteeSkillCount = 0;
	private int matchingNetworkingSkillCount = 0;

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
	private String linkedInSummary;
	private String linkedInPictureUrl;
	private String linkedInLargePictureUrl;
	private String linkedInPublicProfileUrl;
	private String linkedInEmail;
	private String linkedInId;

	// ChirpedIn Data

	// MENTOR BACKEND skills you want a mentor to have
	private Boolean mentorSkillsJava;
	private Boolean mentorSkillsJsp;
	private Boolean mentorSkillsJstl;
	private Boolean mentorSkillsSql;
	private Boolean mentorSkillsJdbc;
	private Boolean mentorSkillsSpringMVC;
	private Boolean mentorSkillsHibernate;

	// MENTOR FRONTEND
	private Boolean mentorSkillsPhp;
	private Boolean mentorSkillsJavaScript;
	private Boolean mentorSkillsHTML;
	private Boolean mentorSkillsCSS;

	// MENTEE BACKEND skills you are qualified to mentor in
	private Boolean menteeSkillsJava;
	private Boolean menteeSkillsJsp;
	private Boolean menteeSkillsJstl;
	private Boolean menteeSkillsSql;
	private Boolean menteeSkillsJdbc;
	private Boolean menteeSkillsSpringMVC;
	private Boolean menteeSkillsHibernate;

	// MENTEE FRONTEND
	private Boolean menteeSkillsPhp;
	private Boolean menteeSkillsJavaScript;
	private Boolean menteeSkillsHTML;
	private Boolean menteeSkillsCSS;

	// NETWORKING
	private Boolean networkingFoodie;
	private Boolean networkingGaming;
	private Boolean networkingSports;
	private Boolean networkingAnime;
	private Boolean networkingFun;

	private Boolean networkingSkillsJava;
	private Boolean networkingSkillsJsp;
	private Boolean networkingSkillsJstl;
	private Boolean networkingSkillsSql;
	private Boolean networkingSkillsJdbc;
	private Boolean networkingSkillsSpringMVC;
	private Boolean networkingSkillsHibernate;

	// networking FRONTEND
	private Boolean networkingSkillsPhp;
	private Boolean networkingSkillsJavaScript;
	private Boolean networkingSkillsHTML;
	private Boolean networkingSkillsCSS;
	
	// favorites
	private String favorites;  //added by Pratima

	public String getFavorites() {     //added by Pratima
		return favorites;
	}

	public void setFavorites(String favorites) {       //added by Pratima
		this.favorites = favorites;
	}

	public UserDto() {
		super();
	}

	public int getRank(UserDto need) {
		// System.out.println("Entering getRank()...");

		int skillsCount = 0;
		int rank = 0;

		ArrayList<String> skillsNeed = new ArrayList<String>();

		if (need.mentorSkillsJava) {
			skillsNeed.add("java");
			// System.out.println("adding java skill");
		}

		if (need.mentorSkillsJsp) {
			skillsNeed.add("jsp");
			// System.out.println("adding jsp skill");
		}

		if (need.mentorSkillsCSS) {
			skillsNeed.add("css");
			// System.out.println("adding css skill");
		}

		ArrayList<String> skillsHave = new ArrayList<String>();

		if (menteeSkillsJava) {
			skillsHave.add("java");
		}

		if (menteeSkillsJsp) {
			skillsHave.add("jsp");
		}
		if (menteeSkillsCSS) {
			skillsHave.add("css");
		}

		for (String skill : skillsNeed) {

			if (skillsHave.contains(skill)) {
				skillsCount++;
				this.matchingMentorSkills += " " + skill;
			}

		}

		if (skillsNeed.size() > 1) {
			rank = (skillsCount / skillsNeed.size()) * 100;
		}

		return rank;
	}
	
	@Override
	public String toString() {
		return "UserDto: " + linkedInFirstName + " " + linkedInLastName 
				+ "\n Total Match: " + String.format("%3.3f", totalMatchPercent)
				+ "\n  percentMentorMatch: " + String.format("%3.3f", mentorMatchPercent) + " percentMenteeMatch: " + String.format("%3.3f", menteeMatchPercent) + " percentNetworkingMatch: " + String.format("%3.3f", networkingMatchPercent)
				+ "\n matchingMentorSkills: " + matchingMentorSkills + "\n matchingMenteeSkills: "
				+ matchingMenteeSkills + "\n matchingNetworkingSkills: " + matchingNetworkingSkills + "\n haveSkills: "
				+ haveSkills + "\n needSkills: " + needSkills + "\n networkingSkills: " + networkingSkills
				+ "\n mentorMatch: " + mentorMatch + ", menteeMatch: " + menteeMatch + ", networkingMatch: "
				+ networkingMatch + "\n haveSkillCount: " + haveSkillCount + ", needSkillCount: " + needSkillCount
				+ ", networkingSkillCount: " + networkingSkillCount + "\n\n";
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

	public String getLinkedInSummary() {
		return linkedInSummary;
	}

	public void setLinkedInSummary(String linkedInSummary) {
		this.linkedInSummary = linkedInSummary;
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

	public double getPercentMatch() {
		return percentMatch;
	}

	public void setPercentMatch(double percentMatch) {
		this.percentMatch = percentMatch;
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

	/*public String getMatchingNetworkingSkills() {
		return matchingNetworkingSkills;
	}

	public void setMatchingNetworkginSkills(String matchingNetworkginSkills) {
		this.matchingNetworkingSkills = matchingNetworkginSkills;
	}
*/
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

}
