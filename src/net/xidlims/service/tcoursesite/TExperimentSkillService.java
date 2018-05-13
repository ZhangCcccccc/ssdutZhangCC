package net.xidlims.service.tcoursesite;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.TExperimentLabDevice;
import net.xidlims.domain.TExperimentLabRoom;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TExperimentSkillUser;
import net.xidlims.web.aop.SystemServiceLog;

public interface TExperimentSkillService {

	/**************************************************************************
	 * Description:查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	public int getCountTExperimentSkillList(String tCourseSiteId);

	/**************************************************************************
	 * Description:查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	public List<TExperimentSkill> findTExperimentSkillListBySiteId(Integer tCourseSiteId, Integer currpage,
			int pageSize,String type);
	

	/**************************************************************************
	 * Description:保存实验技能
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	public TExperimentSkill saveTExperimentSkill(TExperimentSkill tExperimentSkill,
			HttpServletRequest request)throws ParseException;

	/**************************************************************************
	 * Description:查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	public TExperimentSkill findTExperimentSkillByPrimaryKey(Integer id);

	/**************************************************************************
	 * Description:查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	public void deleteTExperimentSkill(TExperimentSkill TExperimentSkill);
	
	/**************************************************************************
	 * Description:查询实验技能完成情况
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	public List<Integer> findTExperimentSkillStringList(Integer tCourseSiteId);
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能概要
	 * 
	 * @author：裴继超
	 * @date ：2016-9-23
	 **************************************************************************/
	public void saveExperimentSkillProfile(@RequestParam Integer tCourseSiteId,
			HttpServletRequest request) ;
	
	/**************************************************************************
	 * Description:保存实验技能相关资源
	 * 
	 * @author：裴继超
	 * @date ：2016-9-28
	 **************************************************************************/
	public void saveTExperimentSkillWkUpload(WkFolder folder,String name,HttpServletRequest request);
	
	/**************************************************************************
	 * Description:实验技能-根据实验室获取实验工具json
	 * 
	 * @author：裴继超
	 * @date ：2016-9-29
	 **************************************************************************/
	public String findToolsJsonByLabRooms(String labRoomIds) ;
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能所属实验室
	 * 
	 * @author：裴继超
	 * @date ：2016-9-30
	 **************************************************************************/
	public void saveExperimentLabRooms(Integer skillId,HttpServletRequest request);
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能的实验工具
	 * 
	 * @author：裴继超
	 * @date ：2016-9-30
	 **************************************************************************/
	public void saveExperimentDevices(Integer deviceId,HttpServletRequest request);
	
	/**************************************************************************
	 * Description:实验技能-查询实验技能所属实验室
	 * 
	 * @author：裴继超
	 * @date ：2016-10-8
	 **************************************************************************/
	public String findExperimentLabRoomsString(Integer skillId);
	
	/**************************************************************************
	 * Description:实验技能-根据类别获取实验技能资源
	 * 
	 * @author：裴继超
	 * @date ：2016-10-9
	 **************************************************************************/
	public List<WkUpload> findWkUploadBySkillIdAndType(Integer skillId,Integer type) ;
	
	/**************************************************************************
	 * Description:实验技能-获取实验设备
	 * 
	 * @author：裴继超
	 * @date ：2016-10-9
	 **************************************************************************/
	public List<LabRoomDevice> findDeviceBySkillId(Integer skillId);
	
	/**************************************************************************
	 * Description:实验技能-查询实验问答数量
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 **************************************************************************/
	public int getCountSkillTDiscussList(String tCourseSiteId);
	
	/**************************************************************************
	 * Description:实验技能-查询实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索
	 **************************************************************************/
	public List<TDiscuss> findSkillTDiscussListByPartent(Integer partentDiscussId, 
			Integer currpage,int pageSize);
	
	/**************************************************************************
	 * Description:实验技能-查询实验问答列表
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	public List<TDiscuss> findSkillTDiscussList(String tCourseSiteId,
			Integer currpage, int pageSize) ;
	
	/**************************************************************************
	 * Description:实验技能-保存实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	public void saveSkillTDiscuss(TDiscuss tDiscuss) ;
	
	/**************************************************************************
	 * Description:实验技能-根据主键查询实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	public TDiscuss findSkillTDiscussByPrimaryKey(Integer id) ;
	
	/**************************************************************************
	 * Description:实验技能-删除实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	public void deleteSkillTDiscuss(TDiscuss tDiscuss) ;
	
	/**************************************************************************
	 * Description:实验技能-新建一个实验报告
	 * 
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	public TAssignment saveReportTAssignment(TExperimentSkill tExperimentSkill,HttpServletRequest request)
			throws ParseException;
	
	/**************************************************************************
	 * Description:实验技能-根据实验技能查找实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	public TAssignment findReportTAssignmentBySkill(TExperimentSkill tExperimentSkill);
	
	/**************************************************************************
	 * Description:实验技能-学生获取自己的实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-12
	 **************************************************************************/
	public List<TAssignmentGrading> findReportTAssignmentGradingBySkillAndStudent(
			TExperimentSkill tExperimentSkill);
	
	/**************************************************************************
	 * Description:实验技能-学生提交实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	public TAssignmentGrading saveReportByStudent(TAssignmentGrading tAssignmentGrade,
			Integer tCourseSiteId, HttpServletRequest request);

	/*************************************************************************************
	 * description:实验项目-设置预习测试可答题时间
	 * 
	 * @author:裴继超
	 * @date：2016-11-18
	 *************************************************************************************/
	public void newPrepareExam(int flag,int skillId);
	/**************************************************************************
	 * Description:实验技能-根据实验技能查找实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	public TAssignment findTAssignmentBySkillAndType(TExperimentSkill tExperimentSkill,Integer type);
	/*************************************************************************************
	 * description:实验项目-预习测试-查找学生答题记录
	 * 
	 * @author:裴继超
	 * @date：2016-11-21
	 *************************************************************************************/
	public TAssignmentGrading findExamGradingBySubmitTime(int examId,int submitTime);
	/*************************************************************************************
	 * description:实验项目-设置预习测试可答题时间
	 * 
	 * @author:裴继超
	 * @date：2016-11-18
	 *************************************************************************************/
	public Integer getExamCan(int flag,int skillId);
	/**************************************************************************
	 * Description:实验技能-获取实验项目的科目列表
	 * 
	 * @author：裴继超
	 * @date ：2016-11-29
	 **************************************************************************/
	public List<TGradeObject> findTGradeObjectsByType(
			Integer cid,Integer skillId, String type);
	/**************************************************************************
	 * Description:实验技能-获取学生某个实验分数
	 * 
	 * @author：裴继超
	 * @date ：2016-11-29
	 **************************************************************************/
	public TExperimentSkillUser findSkillUser(Integer skillId, String username);
	
	/**************************************************************************
	 * Description:实验技能-查询所有学生所有实验分数
	 * 
	 * @author：裴继超
	 * @date ：2016-11-30
	 **************************************************************************/
	public List<List<Object>> gradeAllExperimentSkill(
			List<TCourseSiteUser> tCourseSiteUsers,Integer cid);
	
	/**************************************************************************
	 * Description:查询实验技能下所属实验室
	 * 
	 * @author：于侃
	 * @date ：2016年10月31日 16:58:13
	 **************************************************************************/
	public List<TExperimentLabRoom> getLabRoomList(Integer id);
	
	/**************************************************************************
	 * Description:查询实验技能下所属实验室下所属实验设备
	 * 
	 * @author：于侃
	 * @date ：2016年11月1日 10:07:29
	 **************************************************************************/
	public List<TExperimentLabDevice> getLabDeviceList(Integer id);
	/**************************************************************************
	 * Description:根据当前的courseNo来判断当前的 tcourse_site中是否生成过对应课程
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.11
	 **************************************************************************/
	public boolean isExitTcourseSiteWithCourseNo(String courseNo,String groupNumber,String courseDetailNo);
	/**************************************************************************
	 * Description:实验项目-成绩管理-查寻分组下的学生总数
	 *  
	 * @author：裴继超
	 * @date ：2016-12-12
	 **************************************************************************/
	public int findStudentRecords(Integer id);
	
	/*************************************************************************************
	 * Description:课程站点-查询课程下的学生(分页)
	 * 
	 * @author： 裴继超
	 * @date：2016-12-12
	 *************************************************************************************/
	public List<TCourseSiteUser> findStudentBySiteId(Integer id, Integer currpage, 
			Integer pageSize);
	/**************************************************************************
	 * Description:获取当前课程下面每个学生的实验项目的实验报告所获得的成绩
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	public List<List<Object>> getGradingWithSkillForAll(List<TCourseSiteUser> tCourseSiteUsers,Integer cid);
	/**************************************************************************
	 * Description:根据当前的实验报告和学生学号来获取学生的成绩
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	public Object getRecordByUserName(String userName,TAssignment tAssigment);
	/**************************************************************************
	 * Description:获取每个实验项目对应的权重
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	public List<List<Object>> getWeightWithSkill(Integer cid);
	/**************************************************************************
	 * Description:获取实验项目以及对应的名称
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	public List<List<Object>> getWeightAndNameWithSkill(Integer cid);

	/**************************************************************************
	 * Description:实验技能-根据实验技能新增实验报告
	 * @throws ParseException 
	 * 
	 * @author：李雪腾
	 * @date ：2017-7-18
	 **************************************************************************/
	public TAssignment saveExpReport(TExperimentSkill tExperimentSkill);
	/**************************************************************************
	 * Description:根据当前的实验报告和学生学号来获取学生成绩（整条数据）
	 * 
	 * @author：张佳鸣
	 * @date ：2017.9.29
	 **************************************************************************/
	public TAssignmentGrading getTAssignmentGradingByUserName(String userName, TAssignment tAssigment);

    /**************************************************************************
    * Description:实验技能-保存实验数据
    * @throws ParseException 
    * 
    * @author：张佳鸣
    * @date ：2017-10-11
    **************************************************************************/
    public TAssignment saveDataTAssignment(TExperimentSkill tExperimentSkill,
		   HttpServletRequest request)throws ParseException ;
    
    /**************************************************************************
    * Description:实验技能-保存预习测试
    * @throws ParseException 
    * 
    * @author：张佳鸣
    * @date ：2017-10-11
    **************************************************************************/
    public TAssignment savePrepareExam(TExperimentSkill tExperimentSkill,HttpServletRequest request) 
		   throws ParseException ;
    
    /**************************************************************************
	 * Description:查询讨论
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	public List<TExperimentSkill> findTExperimentSkillListBySiteId(Integer tCourseSiteId, Integer currpage,
			int pageSize);
	
	/**************************************************************************
	 * Description:实验技能-计算学生实验总分并保存
	 * 
	 * @author：裴继超
	 * @date ：2016-11-30
	 **************************************************************************/
	public void saveSkillGrade(Integer tCourseSiteId,Integer skillId, String username);
	
}


