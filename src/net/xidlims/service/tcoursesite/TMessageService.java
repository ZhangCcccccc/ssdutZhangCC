package net.xidlims.service.tcoursesite;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.TMessageAttachment;
import net.xidlims.domain.TMessageUser;
import net.xidlims.domain.User;


public interface TMessageService {

	/**
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 * @功能：查询对应站点下的通知数量
	 * @作者：黄崔俊
	 * @时间：2015-8-5 16:10:07
	 */
	public int getCountTMessageList(String tCourseSiteId, Integer queryType, String titleQuery);

	/**
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索
	 * @功能：查询通知列表
	 * @作者：黄崔俊
	 * @时间：2015-8-5 16:12:32
	 */
	public List<TMessage> findTMessageList(String tCourseSiteId, Integer currpage,String titleQuery, Integer queryType, int pageSize);
	/*******************************************************
	 * Description:获取通知列表
	 * @author：李军凯
	 * @date ：2016-08-30
	 *********************************************************/
	public List<TMessage> findTMessageListBytCourseSiteId(String tCourseSiteId,
			Integer currpage, int pageSize);
	/*******************************************************
	 * Description:获取通知数量
	 * @author：李军凯
	 * @date ：2016-08-30
	 *********************************************************/
	public int getCountTMessageListBytCourseSiteId(String tCourseSiteId);
	/**
	 * @功能：保存通知
	 * @作者：黄崔俊
	 * @时间：2015-8-6 14:33:44
	 */
	public void saveTMessage(TMessage tMessage);
	/**************************************************************************
	 * Description:消息-保存消息
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	public TMessage saveTMessageInfo(TMessage tMessage);
	/**************************************************************************
	 * Description:消息-保存消息与发送对象
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	public void saveTMessageuser(int tMessageId,String username);
	/**
	 * @功能：根据主键查询通知
	 * @作者：黄崔俊
	 * @时间：2015-8-6 15:08:10
	 */
	public TMessage findTMessageByPrimaryKey(Integer id);

	/*************************************************************************************
	 * @內容：删除查询出的通知
	 * @作者：黄崔俊
	 * @日期：2015-8-6 15:10:50
	 *************************************************************************************/
	public void deleteTMessage(TMessage tMessage);

	/*************************************************************************************
	 * @內容：根据班级名称和学生姓名查询记录数
	 * @作者：黄崔俊
	 * @日期：2015-8-10 10:07:31
	 *************************************************************************************/
	public int getCountUserListByClassnameAndCname(String classname,String cname);
	/*************************************************************************************
	 * @內容：根据关键字查询班级名称记录数
	 * @作者：裴继超
	 * @日期：2015-9-22
	 *************************************************************************************/

	public int getCountClassnameBykeyword(String classname);
	/*************************************************************************************
	 * @內容：根据关键字查询班级名称列表
	 * @作者：裴继超
	 * @日期：2015-9-22
	 *************************************************************************************/

	public List<SchoolClasses> findClassnameBykeyword(String classname, Integer page, int pageSize);

	/*************************************************************************************
	 * @內容：根据班级名称和学生姓名查询用户列表
	 * @作者：黄崔俊
	 * @日期：2015-8-10 10:08:05
	 *************************************************************************************/
	public List<User> findUserListByClassnameAndCname(String classname,String cname, Integer page, int pageSize);

	/**************************************************************************************
	 * @功能：根据通知信息和用户信息保存通知发布的记录
	 * @作者：黄崔俊
	 * @日期：2015-8-10 14:18:31
	 *************************************************************************************/
	//public void saveUserMessage(String[] messageIds, String[] usernames);
	/**************************************************************************************
	 * @功能：根据通知信息和用户信息保存通知发布的记录
	 * @作者：裴继超
	 * @日期：2015-9-22
	 *************************************************************************************/

	//public void publishClassMessage(String[] messageIds, List<User> usernames);
	/**************************************************************************************
	 * @功能：根据通知信息和用户信息保存通知发布的记录
	 * @作者：裴继超
	 * @日期：2015-9-22
	 *************************************************************************************/

	public List<User> findUserListByClassnumber(String[] classnumbers);

	/**************************************************************************************
	 * @功能：根据当前课程和登陆人查询通知列表
	 * @作者：黄崔俊
	 * @日期：2015-10-28 15:05:14
	 *************************************************************************************/
	//public List<TMessage> findTMessageListByUserAndSite(Integer id, User user);

	/**************************************************************************
	 * Description:消息-发送邮件
	 * @author：李军凯
	 * @date ：2016-09-22
	 **************************************************************************/
	public int sendMail(TMessage tMessage,String email,HttpServletRequest request)throws ParseException;
	/*******************************************************
	 * Description:获取已发邮件列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 *********************************************************/
	public List<TMessage> findAllTMessageListBytCourseSiteId(String tCourseSiteId);
	/*******************************************************
	 * Description:获取已发邮件对象列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 *********************************************************/
	public List<TMessageUser> findAllTMessageUserListByMessageId( Integer messageId);
	/**************************************************************************
	 * Description:消息-定时发送邮件
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 **************************************************************************/
	public void sendMassageAtTiming(String curPath);
	/**************************************************************************
	 * Description:消息-上传附件
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	public int processUpload(Integer type,HttpServletRequest request);
	/**************************************************************************
	 * Description:保存附件 
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	public int saveUpload(TMessageAttachment upload);
	/**************************************************************************
	 * Description:查找附件
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	public TMessageAttachment findUpload(int id);
	/**************************************************************************
	 * Description:查找通知与学生的对应关系 
	 * 
	 * @author：李军凯
	 * @date ：2016-09-30
	 **************************************************************************/
	public List<TMessageUser> findAllTMessageUserByMessageId(int tMessageId);
	/*******************************************************
	 * Description:通过登录人获取登录人的通知列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-30
	 *********************************************************/
	public List<TMessage> findTMessageListByUsername(String userName,int tCourseSiteId);
	/*******************************************************
	 * Description:通知设置为已读
	 * 
	 * @author：李军凯
	 * @date ：2016-09-30
	 *********************************************************/
	public void setTMessageIsread(int messageId);
	/*******************************************************
	 * Description:通过登录人获取登录人的消息栏
	 * 
	 * @author：李军凯
	 * @date ：2016-10-8
	 *********************************************************/
	public List findTMessageShowViewList(String userName,int tCourseSiteId);
	
	/**************************************************************************
	 * Description:查找通知与Message的对应关系 
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-17
	 **************************************************************************/
	public List<Message> findAllMessageByTMessageId(int tMessageId);
}

