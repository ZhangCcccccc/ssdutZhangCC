package net.xidlims.web.timetable;

import java.util.Map;

public class CheckAttendancetable {
   private   String classgroup; 
   private   String studentnumber;
   private   String studentname;
   private   String classnumber;
   
   /**
    * 周次考勤和学期成绩
    */
   private Map<Integer,String> map;
   
   public String getClassgroup() {
	return classgroup;
}
public void setClassgroup(String classgroup) {
	this.classgroup = classgroup;
}
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
public String getClassnumber() {
	return classnumber;
}
public void setClassnumber(String classnumber) {
	this.classnumber = classnumber;
}


public Map<Integer, String> getMap() {
	return map;
}
public void setMap(Map<Integer, String> map) {
	this.map = map;
}


}
