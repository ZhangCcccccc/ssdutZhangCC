package net.xidlims.service.tcoursesite;

import java.util.List;

import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.User;


public interface TDiscussService {

	/**
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 * @功能：查询对应站点下的讨论数量
	 * @作者：裴继超
	 * @时间：2015-8-5 16:10:07
	 */
	public int getCountTDiscussList(String tCourseSiteId);

	/**
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索
	 * @功能：查询讨论列表
	 * @作者：裴继超
	 * @时间：2016年6月27日16:27:20
	 */
	public List<TDiscuss> findTDiscussList(String tCourseSiteId, Integer currpage,
			int pageSize);
	
	/**
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索
	 * @功能：查询讨论列表
	 * @作者：裴继超
	 * @时间：2016年6月27日16:27:20
	 */
	public List<TDiscuss> findTDiscussListByPartent(Integer partentDiscussId, Integer currpage,
			int pageSize);

	/**
	 * @功能：保存讨论
	 * @作者：黄崔俊
	 * @时间：2015-8-6 14:33:44
	 */
	public void saveTDiscuss(TDiscuss tDiscuss);

	/**
	 * @功能：根据主键查询讨论
	 * @作者：黄崔俊
	 * @时间：2015-8-6 15:08:10
	 */
	public TDiscuss findTDiscussByPrimaryKey(Integer id);

	/*************************************************************************************
	 * @內容：删除查询出的讨论
	 * @作者：黄崔俊
	 * @日期：2015-8-6 15:10:50
	 *************************************************************************************/
	public void deleteTDiscuss(TDiscuss tDiscuss);

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

	public List<SchoolClasses> findClassnameBykeyword(String classname, Integer page, 
			int pageSize);

	/*************************************************************************************
	 * @內容：根据班级名称和学生姓名查询用户列表
	 * @作者：黄崔俊
	 * @日期：2015-8-10 10:08:05
	 *************************************************************************************/
	public List<User> findUserListByClassnameAndCname(String classname,String cname, 
			Integer page, int pageSize);



}

