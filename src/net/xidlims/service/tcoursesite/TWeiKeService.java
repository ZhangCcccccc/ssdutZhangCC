package net.xidlims.service.tcoursesite;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkCourse;
import net.xidlims.domain.WkUpload;



public interface TWeiKeService {
	
	/*************************************************************************************
	 * Description:课程-我的课程与开放课程
	 * 
	 * @author： 裴继超
	 * @date：2016-11-24
	 *************************************************************************************/
	public List<TCourseSite> getSiteByUserAndIsOpen(TCourseSite tCourseSite, 
			Integer currpage, int pageSize);
}