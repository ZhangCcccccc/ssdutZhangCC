package net.xidlims.view;

import java.io.Serializable;
import java.util.List;

public class ViewTAssignmentAllGrade implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String cname;
	
	private List<String> scores;
	
	private String totalGradeForOneUser;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<String> getScores() {
		return scores;
	}

	public void setScores(List<String> scores) {
		this.scores = scores;
	}

	public String getTotalGradeForOneUser() {
		return totalGradeForOneUser;
	}

	public void setTotalGradeForOneUser(String totalGradeForOneUser) {
		this.totalGradeForOneUser = totalGradeForOneUser;
	}

}
