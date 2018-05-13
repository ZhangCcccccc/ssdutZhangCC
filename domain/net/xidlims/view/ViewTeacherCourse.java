package net.xidlims.view; 

import java.io.Serializable;
  
public class ViewTeacherCourse implements Serializable {  
      
    /**
	 * 
	 */
	private static final long serialVersionUID = -5923444263796317197L;

	/** 
     *  
     */  
  
    private Integer id;  
      
    private String timetable_number;  
      
    private String course_code;  

      
    public ViewTeacherCourse(){}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTimetable_number() {
		return timetable_number;
	}


	public void setTimetable_number(String timetable_number) {
		this.timetable_number = timetable_number;
	}


	public String getCourse_code() {
		return course_code;
	}


	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
    
   
      
}  