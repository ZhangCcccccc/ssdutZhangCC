package net.xidlims.web.timetable;

import java.math.BigDecimal;

public class AttendancetableByWeek {
	 private   int attendancetimeid;
	 private   String studentnumber;
	 private   String studentname;
	 private   String attendancetime;
	 private   int actual_attendance;
	 private   int attendance_machine;
	 private   String memo;
	 private   BigDecimal score;
	public String getStudentnumber() {
		return studentnumber;
	}
	public void setStudentnumber(String studentnumber) {
		this.studentnumber = studentnumber;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getAttendancetime() {
		return attendancetime;
	}
	public void setAttendancetime(String attendancetime) {
		this.attendancetime = attendancetime;
	}
	public int getAttendancetimeid() {
		return attendancetimeid;
	}
	public void setAttendancetimeid(int attendancetimeid) {
		this.attendancetimeid = attendancetimeid;
	}
	public int getActual_attendance() {
		return actual_attendance;
	}
	public void setActual_attendance(int actual_attendance) {
		this.actual_attendance = actual_attendance;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getAttendance_machine() {
		return attendance_machine;
	}
	public void setAttendance_machine(int attendance_machine) {
		this.attendance_machine = attendance_machine;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	
}
