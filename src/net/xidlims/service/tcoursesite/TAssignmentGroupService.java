package net.xidlims.service.tcoursesite;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentGradingAttachment;

public interface TAssignmentGroupService {

	/**************************************************************************
	 * Description:查询小组作业附件
	 * 
	 * @author：于侃
	 * @date ：2016年10月18日 10:15:19
	 **************************************************************************/
	public List<TAssignmentGradingAttachment> findGradeUrls(Integer id);
	
	/**************************************************************************
	 * Description:保存学生提交的小组作业
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 09:59:00
	 **************************************************************************/
	public TAssignmentGrading saveGroupTAssignmentGrading(TAssignmentGrading tAssignmentGrade,Integer cid,Integer groupId, HttpServletRequest request);
	
	/**************************************************************************
	 * Description:根据groupId查询小组作业
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 14:15:09
	 **************************************************************************/
	public List<TAssignmentGrading> findTAssignmentGradingList(Integer tAssignmentId,Integer groupId);
	
	/**************************************************************************
	 * Description:根据tAssignmentId,username,groupId查询该学生小组作业成绩
	 * 
	 * @author：于侃
	 * @date ：2016年10月20日 09:33:49
	 **************************************************************************/
	public TAssignmentGrading findGroupTAssignmentGrading(Integer tAssignmentId,String username,Integer groupId);
	
	/**************************************************************************
	 * Description:处理ajax上传过来文件，支持多文件 
	 * 
	 * @author：于侃
	 * @date ：2016年10月20日 13:57:07
	 **************************************************************************/
	public int processUpload(Integer tAssignmentId,Integer tcoursesiteId,HttpServletRequest request);
	
	/**************************************************************************
	 * Description:批量下载附件（先打包）
	 * 
	 * @author：于侃
	 * @date ：2016年10月21日 10:23:46
	 **************************************************************************/
	public String downloadAttachments(Integer tAssignmentId,Integer tcoursesiteId,String attachList,HttpServletRequest request,HttpServletResponse response);
	
}


