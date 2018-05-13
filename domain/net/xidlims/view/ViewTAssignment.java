package net.xidlims.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;

public class ViewTAssignment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;

	private String description;

	private Calendar createdTime;

	private Integer status;
	
	private String type;
	
	private String content;

	private java.util.Set<net.xidlims.domain.TGradeObject> TGradeObjects;

	private WkFolder wkFolder;
	
	private User user;

	Set<TAssignmentGrading> TAssignmentGradings;

	private TAssignmentControl  TAssignmentControl;

	private java.util.Set<TAssignmentSection> TAssignmentSections;

	private TAssignmentAnswerAssign TAssignmentAnswerAssign;

	private Integer tAssignGradeSubmitCount;//已提交的作业(老师身份查看)
	
	private Integer tAssignGradeNotCorrectCount;//已提交未批改的作业(老师身份查看)
	
	private Integer submitTimeForStudent;//学生提交次数
	
	private Integer noSubmitStudents;//未提交学生数
	
	private String scoreForStudent;//该条记录所指学生的得分
	
	


	public Integer getNoSubmitStudents() {
		return noSubmitStudents;
	}

	public void setNoSubmitStudents(Integer noSubmitStudents) {
		this.noSubmitStudents = noSubmitStudents;
	}

	public Integer getSubmitTimeForStudent() {
		return submitTimeForStudent;
	}

	public void setSubmitTimeForStudent(Integer submitTimeForStudent) {
		this.submitTimeForStudent = submitTimeForStudent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Set<net.xidlims.domain.TGradeObject> getTGradeObjects() {
		return TGradeObjects;
	}

	public void setTGradeObjects(
			java.util.Set<net.xidlims.domain.TGradeObject> tGradeObjects) {
		TGradeObjects = tGradeObjects;
	}


	
	public WkFolder getWkFolder() {
		return wkFolder;
	}

	public void setWkFolder(WkFolder wkFolder) {
		wkFolder = wkFolder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<TAssignmentGrading> getTAssignmentGradings() {
		return TAssignmentGradings;
	}

	public void setTAssignmentGradings(Set<TAssignmentGrading> tAssignmentGradings) {
		TAssignmentGradings = tAssignmentGradings;
	}
	
	public TAssignmentControl getTAssignmentControl() {
		return TAssignmentControl;
	}

	public void setTAssignmentControl(TAssignmentControl tAssignmentControl) {
		TAssignmentControl = tAssignmentControl;
	}

	public java.util.Set<TAssignmentSection> getTAssignmentSections() {
		return TAssignmentSections;
	}

	public void setTAssignmentSections(
			java.util.Set<TAssignmentSection> tAssignmentSections) {
		TAssignmentSections = tAssignmentSections;
	}

	public TAssignmentAnswerAssign getTAssignmentAnswerAssign() {
		return TAssignmentAnswerAssign;
	}

	public void setTAssignmentAnswerAssign(
			TAssignmentAnswerAssign tAssignmentAnswerAssign) {
		TAssignmentAnswerAssign = tAssignmentAnswerAssign;
	}

	public Integer gettAssignGradeSubmitCount() {
		return tAssignGradeSubmitCount;
	}

	public void settAssignGradeSubmitCount(Integer tAssignGradeSubmitCount) {
		this.tAssignGradeSubmitCount = tAssignGradeSubmitCount;
	}

	public Integer gettAssignGradeNotCorrectCount() {
		return tAssignGradeNotCorrectCount;
	}

	public void settAssignGradeNotCorrectCount(Integer tAssignGradeNotCorrectCount) {
		this.tAssignGradeNotCorrectCount = tAssignGradeNotCorrectCount;
	}

	public String getScoreForStudent() {
		return scoreForStudent;
	}

	public void setScoreForStudent(String scoreForStudent) {
		this.scoreForStudent = scoreForStudent;
	}
	
}
