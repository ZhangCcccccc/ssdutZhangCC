package net.xidlims.service.tcoursesite;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.sf.json.JSONArray;


public interface TCourseSiteUserService {
	
	/*************************************************************************************
	 * Description:课程站点-查询课程下的学生
	 * 
	 * @author： 裴继超
	 * @date：2016-6-16
	 *************************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteUserBySiteId(Integer id, 
			Integer currpage, Integer pageSize);
	/**************************************************************************
	 * Description:班级成员-查看课程下的学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	public List<TCourseSiteGroup> findTCourseSiteGroupBySiteId(Integer id, Integer currpage, 
			Integer pageSize);
	/**************************************************************************
	 * Description:班级成员-查寻学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	public TCourseSiteGroup findTCourseSiteGroupById(Integer id);
	/**************************************************************************
	 * Description:班级成员-保存学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	public TCourseSiteGroup saveTCourseSiteGroup(TCourseSiteGroup tCourseSiteGroup);
	/**************************************************************************
	 * Description:班级成员-查寻课程学生总数
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public int findTCourseSiteGroupTotalRecordsBySite(Integer tCourseSiteId,Integer tCourseSiteGroupId);
	/**************************************************************************
	 * Description:班级成员-删除学生分组
	 *  
	 * @author：李军凯
	 * @date ：2016-09-14
	 **************************************************************************/
	public void  deleteTCourseSiteGroup(TCourseSiteGroup tCourseSiteGroup);
	/**************************************************************************
	 * Description:班级成员-查寻分组下的学生总数
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public int findTCourseSiteGroupTotalRecords(Integer id);
	/**************************************************************************
	 * Description:班级成员-查寻分组下的学生总数
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteUserByTCourseSiteId(Integer id, Integer currpage, 
			Integer pageSize);
	/**************************************************************************
	 * Description:班级成员-查寻课程学生列表
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public List<TCourseSiteUser> findtCourseSiteUsersList(Integer tCourseSiteId,Integer tCourseSiteGroupId,int curr, int size);
	/**************************************************************************
	 * Description:班级成员-保存分组添加的学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-18
	 **************************************************************************/
	public void saveTCourseSiteGroupUser(Integer id,Integer tCourseSiteGroupId);
	/**************************************************************************
	 * Description:班级成员-查寻课程下的所有学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	public List<TCourseSiteUser> findAlltCourseSiteUsers(Integer tCourseSiteId);
	/**************************************************************************
	 * Description:班级成员-通过id查询课程下的某个学生
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	public TCourseSiteUser findTCourseSiteUserById(Integer id);
	/*************************************************************************************
	 * Description:课程站点-班级成员-获取在校用户user的分页列表信息
	 * 
	 * @author： 裴继超
	 * @date：2016-7-25
	 *************************************************************************************/
	public List<User> findUsersList(User user,int tCourseSiteId, int curr, int size) ;
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-获取用户的总记录数
	 * 
	 * @author： 裴继超
	 * @date：2016-7-25
	 *************************************************************************************/
	public int getUsersRecords(User user,int tCourseSiteId);
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-根据username获取学生
	 * 
	 * @author： 裴继超
	 * @date：2016-7-25
	 *************************************************************************************/
	public User findUserByUsername(String username);
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-保存班级成员
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	public TCourseSiteUser saveTCourseSiteUser(TCourseSiteUser tCourseSiteUser);
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-保存班级成员列表
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	public void saveTCourseSiteUsers(TCourseSite tCourseSite,String[] usernames);

	/*************************************************************************************
	 * Description:课程站点-班级成员-通过school_course生成班级成员
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	public void saveTCourseSiteUsersByCourseNo(TCourseSite tCourseSite,String courseNo);
	
	/*************************************************************************************
	 * Description:课程站点-班级成员-删除班级成员
	 * 
	 * @author： 裴继超
	 * @date：2016-7-28
	 *************************************************************************************/
	public void deleteTCourseSiteUser(Integer id);

	/*************************************************************************************
	 * Description:课程站点-班级成员-查询所有专业
	 * 
	 * @author： 裴继超
	 * @date：2016-8-5
	 *************************************************************************************/
	public List<SchoolMajor> getAllSchoolMajor(Integer currpage, Integer pageSize);

	/*************************************************************************************
	 * Description:课程站点-班级成员-查询所有班级
	 * 
	 * @author： 裴继超
	 * @date：2016-8-5
	 *************************************************************************************/
	public List<SchoolClasses> getAllSchoolClass(Integer currpage, Integer pageSize);
	
	/*************************************************************************************
	 * description：班级成员-切换角色{保存成员}
	 * 
	 * @author：陈乐为
	 * @date：2016-8-26
	 *************************************************************************************/
	public void saveTCourseSiteUsersForRole(TCourseSiteUser tCourseSiteUser);
	
	/**************************************************************************************
	 * description:查询课程下的所有助教
	 * 
	 * @author:于侃
	 * @date:2016年8月31日
	 *************************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteSTeacherBySiteId(Integer id);
	/*************************************************************************************
	 * Description:课程站点-班级成员-根据课程id和username判断其是否是该课程的助教
	 * 
	 * @author：于侃
	 * @date：2016-09-14
	 *************************************************************************************/
	public boolean isSTeacherBySiteId(Integer id,String username);
	/**************************************************************************
	 * Description:班级成员-查看课程下的学生分组（不分页）
	 *  
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	public List<TCourseSiteGroup> findTCourseSiteGroupsBySiteId(Integer id);
	/**************************************************************************
	 * Description:班级成员-查寻分组下的学生（不分页）
	 *  
	 * @author：李军凯
	 * @date ：2016-09-29
	 **************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteUserByGroupId(Integer id);
	/**************************************************************************
	 * Description:消息-生成联系组的目录
	 * @author：李军凯
	 * @date ：2016-09-29
	 **************************************************************************/
	public JSONArray findtCourseSiteUserJSONArray(int tCourseSiteId);
	
	/**************************************************************************
	 * Description:班级成员-通过学生username和课程site_id获得小组
	 *  
	 * @author：于侃
	 * @date ：2016年10月18日 17:06:51
	 **************************************************************************/
	public TCourseSiteGroup findGroupByUser(String username,Integer id);
	/**************************************************************************
	 * Description:判断这个老师是否参与这门可
	 *  
	 * @author：李雪腾
	 * @date ：2017.7.10
	 **************************************************************************/
	public boolean isUserBelongToTeacher(Integer cid);
	
	/**************************************************************************
	 * Description:根据课程号和username查找
	 *  
	 * @author：张佳鸣
	 * @date ：2017-10-19
	 **************************************************************************/
	public TCourseSiteUser findTCourseSiteUserBySiteIdAndUsername(int tCourseSiteId,String username);
	
	/*************************************************************************************
	 * Description:课程站点-查询课程下的学生，不分页
	 * 
	 * @author： 张佳鸣
	 * @date：2017-11-30
	 *************************************************************************************/
	public List<TCourseSiteUser> findAlltCourseSiteUserBySiteId(Integer id);
}


