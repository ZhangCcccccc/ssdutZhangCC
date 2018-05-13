package net.xidlims.service.lab;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import sun.util.logging.resources.logging_fr;

import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabReservationAudit;
import net.xidlims.domain.LabReservationTimeTable;
import net.xidlims.domain.LabReservationTimeTableStudent;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SchoolWeekday;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.User;



public interface LabReservationService {
	/****************************************************************************
	 * 功能：查询出所有的实验室类别
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	public List<CDictionary> findAllCLabRoomType();
	
	/****************************************************************************
	 * 功能：查询出所有的实验室
	 * 作者：薛帅
	 * 时间：2014-08-6
	 ****************************************************************************/
	public List<LabRoom> findLabRoom(LabRoom labRoom,int sid);
	
	/****************************************************************************
	 * 功能：查询出所有的实验室分页
	 * 作者：薛帅
	 * 时间：2014-08-6
	 ****************************************************************************/
	public List<LabRoom> findLabRoompage(LabRoom labRoom,int currpage,int pageSize,int sid);
	
	/****************************************************************************
	 * 功能：查询y预约类型
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map appointmenttype();
	
	/****************************************************************************
	 * 功能：获取周次
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map   getallweek();
	
	
	/****************************************************************************
	 * 功能：获取星期
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map  getalldate();
	
	
	/****************************************************************************
	 * 功能：获取节次
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map  getallfestivaltimes();
	
	/****************************************************************************
	 * 功能：获取活动类别
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map  getallactivitycategory();
	
	
	/****************************************************************************
	 * 功能：获取联系人
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map  getUsersMap();
	
	/****************************************************************************
	 * 功能：获取选课组编号
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map  getallclassgruop();
	
	/****************************************************************************
	 * 功能：获取选课程
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public Map getallclass();
	
	/****************************************************************************
	 * 功能：保存实验室预约
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public int savelabReservation(LabReservation labReservation,int iskey,String schoolTermid,String[]  Week,String[] systemTime,String[]  jieci);
	
	/****************************************************************************
	 * 功能：查找实验室预约信息
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public	List<LabReservation>  findLabreservationmanage(LabReservation labReservation,int tage,int sid);
	
	/****************************************************************************
	 * 功能：查找实验室预约信息分页
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/
	public	Set<LabReservation>  findLabreservationmanage(LabReservation labReservation,int tage,int currpage,int pageSize,int sid );
	
	/****************************************************************************
	 * 功能：查找实验室预约详细信息
	 * 作者：薛帅
	 * 时间：2014-08-11
	 ****************************************************************************/
	public	LabReservation getlabReservationinfor(int idkey);
	
	/****************************************************************************
	 * 功能:保存实验室审核
	 * 作者：薛帅
	 * 时间：2014-08-12
	 ****************************************************************************/
	public	void 	auditsavelabreservtion(LabReservationAudit labReservationAudit,int idkey);
	
	
	
	public List<LabReservation> findLabreservationmanage(
			LabReservation labReservation, int tage, int currpage, int pageSize ,String username);
	
	public List<LabReservation> findLabreservationmanage(LabReservation labReservation, int tage , String username,int sid);
	
	public List<LabReservation> findLabreservationmanage(String username);
	
	
	public List<LabReservation> getLabReservationByUserId(String username);
	
	public List<LabReservation> getLabReservationByUserId(String username , int page , int pageSize);
	
	
	public List<LabReservation> findLabReservationByLabReservation(LabReservation labReservation);
	
	public List<LabReservation> findLabReservationByLabReservation(LabReservation labReservation,int page, int pageSize);

	public List<LabReservation> findLabReservationByUserId(String username,int pageSize, int currpage);
	
	
	/****************************************************************************
	 * 功能:查找周次
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public	Set<SystemTime>  getscreeningtake(int idkey,int labid,int time);
	

	/****************************************************************************
	 * 功能:查找学期
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public	List<SchoolTerm>  getschoolterm();
	
	/****************************************************************************
	 * 功能:查找周
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public	List<CDictionary>  getscreeningxueqi(int idkey,int labid);
	
	/****************************************************************************
	 * 功能:查找星期
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public	Set<SchoolWeekday>  getscreeningtaketime(int idkey);
	
	/****************************************************************************
	 * 功能:查找学生
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public	void  savelabReservationstudent(int s,String student);
	/****************************************************************************
	 * 功能:查找周次
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public 	List<CDictionary>  getscreeningtakexingqi(int idkey);
	
	
	/****************************************************************************
	 * 功能:查找节次  根据星期id
	 * 作者：薛帅
	 * 时间：2014-08-20
	 ****************************************************************************/
	public 	Set<SystemTime> getscreeningtakejicebyxingqi(int idkey);
	
	
	/****************************************************************************
	 * 功能：保存实验室预约
	 * 作者：薛帅
	 * 时间：2014-08-7
	 ****************************************************************************/                 
	public int savelabReservation1(LabReservation labReservation,int iskey,String schoolTermid,String[]  zhouci,String xingqi,String[]  jieci);

	/****************************************************************************
	 * 功能：保存实验室预约
	 * 作者：薛帅
	 * 时间：2014-08-26
	 ****************************************************************************/                 
	public   List<LabReservationTimeTableStudent>   getstudentforlabreation(LabReservationTimeTableStudent labReservationTimeTableStudent,int idkey);
	/****************************************************************************
	 * 功能：保存实验室预约分页
	 * 作者：薛帅
	 * 时间：2014-08-26
	 ****************************************************************************/                 
	public   List<LabReservationTimeTableStudent>    getstudentforlabreationpage(LabReservationTimeTableStudent labReservationTimeTableStudent,Integer idkey,Integer currpage,Integer pageSize);
	public List<LabReservation> getAllLabReservation(LabReservation labReservation);
	
	public List<LabReservation> getAllLabReservation(
			LabReservation labReservation,int page,int pageSize);
	
	//idkey 星期     time学期   jie 节次   labid实验室
	public List<CDictionary>  getzhouci(int xing,int time,int labid,int[]  jie);
	
	public void labreationdelectitem(int idkey);
	//获取实现
	public List<SchoolTerm>  timemap();
	
	/*************************************************************************************
	 * @throws ParseException 
	 * @內容：保存实验室预约信息
	 * @作者： 魏誠
	 * @日期：2015-05-29
	 *************************************************************************************/
	public LabReservation saveLabReservationProc(LabReservation labReservation, HttpServletRequest request) throws ParseException;
	
	/*************************************************************************************
	 * @內容：返回可用的星期信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getValidWeeksMap(int term, int weekday, int[] labrooms, int[] classes);

	/**
	 * 分页查询实验室预约信息列表
	 * @param currpage
	 * @param pageSize
	 * @return
	 */
	public List<LabReservation> findLabreservationList(int currpage,
			int pageSize);
	/****************************************************************************
	 * description：获得课程视图记录
	 * @author：戴昊宇
	 * @date: 2017-12-14
	 ****************************************************************************/
	public List<Object[]> getListLabCalendar(int roomId);
	/****************************************************************************
	 * description：获得排课具体记录方法
	 * @author：戴昊宇
	 * @date: 2017-12-14
	 ****************************************************************************/
   public List<Object[]>  getAllCalendar(String classesNumber);
}