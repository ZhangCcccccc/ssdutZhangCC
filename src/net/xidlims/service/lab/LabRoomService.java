package net.xidlims.service.lab;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.LabRoomLimitTime;
import net.xidlims.constant.LabAttendance;
import net.xidlims.domain.CommonHdwlog;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomAgent;
import net.xidlims.domain.LabRoomCourseCapacity;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabWorker;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.User;

public interface LabRoomService {

	/**
	 * 根据主键获取实验室对象
	 * @author hly
	 * 2015.07.28
	 */
	public LabRoom findLabRoomByPrimaryKey(Integer labRoomId);
	
	/**
	 * 根据查询条件获取实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	public List<LabRoom> findAllLabRoomByQuery(Integer currpage, Integer pageSize, LabRoom labRoom, int cid);
	/**
	 * 根据查询条件获取实验室数据--增加排序
	 * @author hly
	 * 2015.07.28
	 */
	public List<LabRoom> findAllLabRoomByQuery(Integer currpage, Integer pageSize, LabRoom labRoom,int orderBy, int cid);
	
	/**
	 * 保存实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	public LabRoom saveLabRoom(LabRoom labRoom);
	
	/**
	 * 删除实验室数据
	 * @author hly
	 * 2015.07.28
	 */
	public boolean deleteLabRoom(Integer labRoomId);
	
	/**
	 * 根据主键获取实验室工作人员对象
	 * @author hly
	 * 2015.07.29
	 */
	public LabWorker findLabWorkerByPrimaryKey(Integer labWorkerId);
	
	/**
	 * 根据查询条件获取实验室工作人员数据
	 * @author hly
	 * 2015.07.29
	 */
	public List<LabWorker> findAllLabWorkerByQuery(Integer currpage, Integer pageSize, LabWorker labWorker);
	
	/**
	 * 保存实验室工作人员数据
	 * @author hly
	 * 2015.07.29
	 */
	public LabWorker saveLabWorker(LabWorker labWorker);
	
	/**
	 * 删除实验室工作人员数据
	 * @author hly
	 * 2015.07.29
	 */
	public boolean deleteLabWorker(Integer labWorkerId);
	
	/**
	 * 获取指定实验中心下的实验室
	 * @author hly
	 * 2015.08.18
	 */
	public List<LabRoom> findLabRoomByLabCenterid(Integer cid, Integer isReservation);
	
	/****************************************************************************
	 * 功能：根据实验室id和用户判断用户是否为该实验室的实验室管理员
	 * 作者：贺子龙
	 * 时间：2015-09-03
	 ****************************************************************************/
	public boolean getLabRoomAdminReturn(Integer id, User user);
	/****************************************************************************
	 * 功能：查询出所有的实验项目卡
	 * 作者：贺子龙
	 * 时间：2015-09-03
	 ****************************************************************************/
	public List<OperationItem> findAllOperationItem(String number);
	/****************************************************************************
	 * 功能：根据实验室查询实验室硬件
	 * 作者：贺子龙
	 * 时间：2015-09-04
	 ****************************************************************************/
	public List<LabRoomAgent> findLabRoomAgentByRoomId(Integer id);
	/****************************************************************************
	 * 功能：保存实验室的实验项目
	 * 作者：贺子龙
	 * 时间：2015-09-07
	 ****************************************************************************/
	public void saveLabRoomOperationItem(LabRoom room, String[] str);
	/****************************************************************************
	 * 功能：删除实验室的实验项目
	 * 作者：贺子龙
	 * 时间：2015-09-07
	 ****************************************************************************/
	public void deleteLabRoomOperationItem(LabRoom room, OperationItem m);
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询用户并分页
	 * 作者：贺子龙
	 * 修改：2015-09-08
	 ****************************************************************************/
	public List<User> findUserByUserAndSchoolAcademy(User user,Integer roomId,
			String academyNumber, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询用户数量
	 * 作者：贺子龙
	 * 修改：2015-09-08
	 ****************************************************************************/
	public int findUserByUserAndSchoolAcademy(User user,Integer roomId,
			String academyNumber);
	/****************************************************************************
	 * 功能：根据roomId查询该实验室的门禁
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomAgent> findLabRoomAgentAccessByRoomId(Integer roomId);
	/*************************************************************************************
	 * @功能：根据学院查询实验室并分页--默认显示当前学院的
	 * @作者： 贺子龙 
	 *************************************************************************************/
	public List<LabRoom> findLabRoomBySchoolAcademyDefault(
			LabRoom labRoom, int page, int pageSize,int type, int iLabCenter);
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录数量--增加查询功能
	 * @作者：贺子龙
	 *************************************************************************************/
	public int findLabRoomAccessByIpCount(CommonHdwlog commonHdwlog,String ip,String port,HttpServletRequest request);
	/*************************************************************************************
	 * @功能：根据ip查询刷卡记录并分页--增加查询功能
	 * @作者：贺子龙
	 *************************************************************************************/
	public List<LabAttendance> findLabRoomAccessByIp(CommonHdwlog commonHdwlog,String ip,String port, Integer page,
			int pageSize,HttpServletRequest request);
	
	/****************************************************************************
	 * 功能：根据中心id和查询条件查询该中心的实验室数量
	 * 作者：李小龙
	 ****************************************************************************/
	public int findAllLabRoom(LabRoomDevice device,Integer cid, Integer isReservation) ;
	
	/****************************************************************************
	 * 功能：根据中心id查询该中心的实验室（分页）
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoom> findLabRoomByLabCenterid(LabRoomDevice device,Integer cid, Integer page, int pageSize);

	/**
	 * 统计实验室数量
	 * @param labRoom
	 * @param username
	 * @return
	 */
	public int countLabRoomListByQuery(LabRoom labRoom, String username);

	/**
	 * 查询实验室列表
	 * @param labRoom
	 * @param username
	 * @param currpage
	 * @param pageSize
	 * @return
	 */
	public List<LabRoom> findLabRoomListByQuery(LabRoom labRoom,
			String username, Integer currpage, int pageSize);

	/**
	 * 根据实验室和类型查询管理员
	 * @return
	 */
	public List<User> findAllLabRoomAdmins(LabRoom room,Integer type);
	
	/****************************************************************************
	 * 功能：保存实验分室的视频
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	public void saveLabRoomVideo(String fileTrueName, Integer labRoomid);
	
	/****************************************************************************
	 * 功能：保存实验分室的文档
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	public void saveLabRoomDocument(String fileTrueName, Integer labRoomid,Integer type);
	
	/****************************************************************************
	 * 功能：查询某一实验中心下有设备的实验室
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoom> findLabRoomWithDevices(Integer cid, Integer isReservation);
	
	/****************************************************************************
	 * 功能：根据中心id查询该中心存放有设备的实验室（分页）
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoom> findLabRoomWithDevices(LabRoomDevice device,Integer cid, Integer page, int pageSize,Integer isReservation);

	/**
	  *@comment：根据user对象和学院编号查询用户数量
	  *@param user、cid、academyNumber
	  *@return：
	  *@author：叶明盾
	  *@date：2015-10-28 下午10:35:05
	 */
	public int findUserByUserAndAcademy(User user,Integer cid,String academyNumber);
	
	/**
	  *@comment：根据user对象和学院编号查询用户并分页
	  *@param user、cid、academyNumber、page、pageSize
	  *@return：
	  *@author：叶明盾
	  *@date：2015-10-28 下午10:35:05
	 */
	public List<User> findUserByUserAndAcademy(User user,Integer cid,String academyNumber, Integer page, int pageSize);

	/****************************************************************************
	 * 功能：删除排课相关的实验室禁用记录
	 * 作者：贺子龙
	 * 时间：2016-05-28
	 ****************************************************************************/
	public void deleteLabRoomLimitTimeByAppointment(int id);
	
	/*******************************************************************************
	 * description:根据查询条件获取实验室容量与课程的关系
	 * @author 郑昕茹
	 * @date:2017-05-28
	 ************************************************************************************/
	public List<LabRoomCourseCapacity> findAllLabRoomCourseCapacityByQuery(Integer currpage, Integer pageSize, LabRoomCourseCapacity labRoomCourseCapacity);
	
	/*******************************************************************************
	 * description 根据查询条件获取实验室
	 * 
	 * @param courseDetailNo 课程编号
	 * @author 陈乐为
	 * @date 2017-9-21
	 ************************************************************************************/
	public List<LabRoom> findAllLabRoomCourseCapacityByQuery(String courseDetailNo);
	
}