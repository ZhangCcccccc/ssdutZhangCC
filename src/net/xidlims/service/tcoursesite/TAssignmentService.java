package net.xidlims.service.tcoursesite;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentItemComponent;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkFolder;
import net.xidlims.view.ViewTAssignment;

public interface TAssignmentService {
	
	/**************************************************************************
	 * Description:根据id查询作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-12
	 **************************************************************************/
	public TAssignment findTAssignmentById(int assignId);

	/**************************************************************************
	 * Description:保存作业
	 * 
	 * @author： 裴继超
	 * @date ：2016-5-30
	 **************************************************************************/
	public TAssignment saveTAssignment( TAssignment tAssignment, HttpServletRequest request) throws ParseException;

	/**************************************************************************
	 * Description:删除作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-12
	 **************************************************************************/
	public void deleteTAssignment(TAssignment tAssignment);

	/**************************************************************************
	 * Description:查询作业列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-18
	 **************************************************************************/
	public List<ViewTAssignment> findViewTAssignmentList(User nowUser,Integer cid, Integer flag);

	/**************************************************************************
	 * Description:修改作业发布状态
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-19
	 **************************************************************************/
	public void changeAsignmentStatus(TAssignment tAssignment);

	/**************************************************************************
	 * Description:根据发布状态查询测验列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-28
	 **************************************************************************/
	public List<TAssignment> findTAssignmentList(Integer cid, String type, Integer status);

	/**************************************************************************
	 * Description:查询作业列表
	 * 
	 * @author： 裴继超
	 * @date ：2015-9-25
	 **************************************************************************/
	public List<ViewTAssignment> findTAssignmentList(User nowUser,
			Integer cid, Integer flag, String chapterOrLesson);

	/**************************************************************************
	 * Description:获取站点下的总学生人数
	 * 
	 * @author： 裴继超
	 * @date ：2015-9-28
	 **************************************************************************/
	public int findCourseStudentsSize(Integer id);

	/**************************************************************************
	 * Description:获取提交作业人数
	 * 
	 * @author： 裴继超
	 * @date ：2015-9-28
	 **************************************************************************/
	public List<TCourseSiteUser> findNotSubmitAssignmentStudents(Integer id);

	/**************************************************************************
	 * Description:查询测试列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-15
	 **************************************************************************/
	public List<ViewTAssignment> findViewExamList(User nowUser, Integer cid, 
			Integer flag, String chapterOrLesson);

	/**************************************************************************
	 * Description:根据登陆身份从作业当前位置上查找在本课程下的下一条作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-19
	 **************************************************************************/
	public TAssignment querybigSeqByTAssignment(TAssignment tAssignment, User user);

	/**************************************************************************
	 * Description:保存作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-19
	 **************************************************************************/
	public void saveTAssignment(TAssignment tAssignment);

	/**************************************************************************
	 * Description:根据登陆身份从作业当前位置上查找在本课程下的上一条作业
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-19
	 **************************************************************************/
	public TAssignment queryminByTAssignment(TAssignment tAssignment, User user);

	/**************************************************************************
	 * Description:根据登陆身份上移作业顺序
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-26
	 **************************************************************************/
	public TAssignment moveupTAssignment(TAssignment tAssignment, User user);

	/**************************************************************************
	 * Description:根据登陆身份下移作业顺序
	 * 
	 * @author：黄崔俊
	 * @date ：2016-1-26
	 **************************************************************************/
	public TAssignment movedownTAssignment(TAssignment tAssignment, User user);
	/**************************************************************************
	 * Description:查询作业记录数
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-10 
	 **************************************************************************/
	public int countViewTAssignmentList(User nowUser, Integer cid, int flag);
	/**************************************************************************
	 * Description:查询作业记录
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-10 
	 **************************************************************************/
	public List<ViewTAssignment> findViewTAssignmentList(User nowUser,
			Integer cid, int flag, Integer currpage, int pageSize);

	/**************************************************************************
	 * Description:作业-下载教师附件
	 * 
	 * @author： 裴继超
	 * @date ：2016-8-5
	 **************************************************************************/
	public void downloadTeacherFile(TAssignment tAssignment,
			HttpServletRequest request, HttpServletResponse response);

	/**************************************************************************
	 * Description:保存考试
	 * 
	 * @author： 裴继超
	 * @date ：2016-6-1
	 **************************************************************************/
	public TAssignment saveTAssignmentForTest(Integer folderId,
			TAssignment tAssignment, HttpServletRequest request) throws ParseException ;
	
	/*************************************************************************************
	 * Description：根据站点编号和测验内容保存测验
	 * 
	 * @author： 裴继超
	 * @date ：2016-6-2
	 *************************************************************************************/
	public TAssignment saveTAssignmentForExam(Integer folderId,TAssignment tAssignment
			, HttpServletRequest request)throws ParseException ;

	/*************************************************************************************
	 * Description：复制作业，考试，测试
	 * 
	 * @author： 裴继超
	 * @date ：2016-6-15
	 *************************************************************************************/
	public TAssignment copyTAssignment(WkFolder wkFolder,WkFolder newWkFolder,Integer tCourseSiteId) ;
	
	/**************************************************************************
	 * Description:测试-发布-设置status为1
	 * 
	 * @author：于侃
	 * @date ：2016-09-06
	 **************************************************************************/
	public void releaseExam(int assignmentId);
	
	/*************************************************************************************
	 * Description：传递考试
	 * 
	 * @author： 储俊
	 * @date：2016-09-07
	 *************************************************************************************/
	public List<TAssignmentItemComponent> findTAssignmentItemComponent(TAssignment tAssignment);
	
	/**************************************************************************
	 * Description:保存考勤
	 * 
	 * @author： 李军凯
	 * @date ：2016-10-17
	 **************************************************************************/
	public TAssignment saveAttendence(TAssignment tAssignment,HttpServletRequest request) throws ParseException;
}





