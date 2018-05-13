package excelTools;

import net.xidlims.domain.LabRoom;
import net.xidlims.domain.User;

public class Labreservationlist {

	private int id;
	// 预约类型
	private String nametype;
	// 活动名称
	private String name;
	// 周
	private String[] week;
	// 星期
	private String[] day;
	// 节次
	private String[] time;
	// 实验室
	private String lab;
	// 实验室
	private LabRoom labRoom;
	// 用户
	private User user;
	// 预约内容
	private String remarks;
	//审核人
	private String auditUser;
	//当前用户是否有权限审核
	private int isAudit;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LabRoom getLabRoom() {
		return labRoom;
	}

	public void setLabRoom(LabRoom labRoom) {
		this.labRoom = labRoom;
	}

	// 内容
	private int cont;
	// 状态
	private String start;

	// 内容
	private Integer fabu;

	public Integer getFabu() {
		return fabu;
	}

	public void setFabu(Integer fabu) {
		this.fabu = fabu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNametype() {
		return nametype;
	}

	public void setNametype(String nametype) {
		this.nametype = nametype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getWeek() {
		return week;
	}

	public void setWeek(String[] week) {
		this.week = week;
	}

	public String[] getDay() {
		return day;
	}

	public void setDay(String[] day) {
		this.day = day;
	}

	public String[] getTime() {
		return time;
	}

	public void setTime(String[] time) {
		this.time = time;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public int getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(int isAudit) {
		this.isAudit = isAudit;
	}

	
}
