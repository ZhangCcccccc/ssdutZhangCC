package net.xidlims.service.tcoursesite;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkUpload;

public interface TCourseSiteService {

	/****************************************************************************
	 * Description:查找所有的自建课程信息
	 * 
	 * @author：魏诚
	 * @date：2015-07-24
	 ****************************************************************************/
	public List<TCourseSite> findAllTCourseSites(int curr,
			int size) ;
	
	/****************************************************************************
	 * Description:获取查找课程站点信息记录数
	 * 
	 * @author：魏诚
	 * @date：2015-07-24
	 ****************************************************************************/
	public int getTCourseSiteTotalRecords();
	
	/****************************************************************************
	 * Description:获取课程站点信息的分页列表信息
	 * 
	 * @author：魏诚
	 * @date：2015-07-24
	 ****************************************************************************/
	public List<TCourseSite> findAllTCourseSites(
			TCourseSite tCourseSite, int curr, int size) ;

	/****************************************************************************
	 * Description:保存 新建课程站点
	 * 
	 * @author：魏诚
	 * @date：2015-07-31
	 ****************************************************************************/
	public String saveTCourseSite(HttpServletRequest request,TCourseSite tCourseSite,
			int flagID) ;

	/*************************************************************************************
	 * Description:根据站点Id查询课程站点
	 * 
	 * @author： 黄崔俊
	 * @date：2015-8-6 
	 *************************************************************************************/
	public TCourseSite findTCourseSiteById(String tCourseSiteId);
	
	/*************************************************************************************
	 * Description:根据站点Id查询课程站点
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	public TCourseSite findCourseSiteById(Integer id);

	/*************************************************************************************
	 * Description:根据站点Id和状态id查询该课程站点下的栏目
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	public List<TCourseSiteChannel> findChannelsBySiteIdAndState(Integer id,
			int i);

	/*************************************************************************************
	 * Description:根据站点Id和标签id查询该课程站点下对应栏目
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	public TCourseSiteChannel findChannelBySiteIdAndTag(Integer id, int i);

	/*************************************************************************************
	 * Description:根据是否开放查找站点
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	public List<TCourseSite> findOpenTCourseSite(Integer s);
	
	/*************************************************************************************
	 * Description:根据站点Id查询已选学生
	 * 
	 * @author：裴继超
	 * @date：2015-9-24
	 *************************************************************************************/
	public List<TCourseSiteUser> findCourseSiteUserBysiteId(Integer id);
	
	/*************************************************************************************
	 * Description:查找所有站点
	 * 
	 * @author：裴继超
	 * @date：2015-9-25
	 *************************************************************************************/
	public List<TCourseSite> findAllTCourseSite();

	/*************************************************************************************
	 * Description:当前登录人是学生时，根据学生ID查找学生选课
	 * 
	 * @author：姜新剑
	 * @date：2015-10-15
	 *************************************************************************************/
	public List<TCourseSite> findStudentTCourseSite(String username);

	/*************************************************************************************
	 * Description:当前登录人是老师权限时，根据老师ID查找老师增加的课程
	 * 
	 * @author：姜新剑
	 * @date：2015-10-15
	 *************************************************************************************/
	public List<TCourseSite> findTeacherTCourseSite(String username);

	/*************************************************************************************
	 * Description:根据标签id和是否开放查询该课程站点下对应栏目
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-13
	 *************************************************************************************/
	public TCourseSiteChannel findChannelByTagAndIsOpen(Integer tagId,Integer state);

	/*************************************************************************************
	 * Description:根据标签id和是否开放查询该课程站点下对应栏目列表
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-19
	 *************************************************************************************/
	public List<TCourseSiteChannel> findChannelListByTagAndIsOpen(Integer tagId, Integer state);

	/*************************************************************************************
	 * Description:查询开放的课程数量
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-19
	 *************************************************************************************/
	public int getTCourseSiteTotalRecords(Integer isOpen);

	/*************************************************************************************
	 * Description:查询开放的课程列表
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-19
	 *************************************************************************************/
	public List<TCourseSite> findTCourseSites(Integer isOpen, Integer currpage,int pageSize);

	/*************************************************************************************
	 * Description:课程所属学生列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteStudents(Integer cid);
	/*************************************************************************************
	 * Description:导入学生信息（excel格式）
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public String importStudentsXls(String filePath, Integer cid);

	/*************************************************************************************
	 * Description:根据学期查询选课组记录数
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public int getTCourseSiteTotalRecordsByTerm(Integer isOpen, Integer termId, TCourseSite tCourseSite);

	/*************************************************************************************
	 * Description:根据学期查询选课组列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public List<TCourseSite> findTCourseSitesByTerm(Integer isOpen, Integer termId,
			Integer currpage, int pageSize, TCourseSite tCourseSite);

	/*************************************************************************************
	 * Description:根据登陆人判断可选老师列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public Map<Object, Object> getCourseSiteTearcherMap(User user);
	/*************************************************************************************
	 * Description:根据ID查询tCourseSiteUser
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public TCourseSiteUser findCourseSiteUserById(Integer id);
	/*************************************************************************************
	 * Description:删除tCourseSiteUser
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public void deleteCourseSiteUser(TCourseSiteUser tCourseSiteUser);
	/*************************************************************************************
	 * Description:根据ID删除tCourseSiteUser
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public void deleteCourseSiteUserByIds(String ids);

	/*************************************************************************************
	 * Description:根据是否登录来查询课程列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public List<TCourseSite> findAllTCourseSiteByUser(User user);
	/*************************************************************************************
	 * Description:根据是否开放来查询课程列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public List<TCourseSite> findOpenTCourseSite(Integer isOpen, int currpage,
			int pageSize);
	/*************************************************************************************
	 * Description:统计开放的课程数量
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public int countOpenTCourseSite(Integer isOpen);

	/*************************************************************************************
	 * Description: 查看登陆用户的课程（老师和学生身份）
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public List<TCourseSite> findMyCourseList(User user);
	
	/*************************************************************************************
	 * Description:  根据排课来源及相应主键创建教学课程
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public TCourseSite createTCourseSite(String type, String courseNo,
			Integer timetableSelfCourseId);
	
	/*************************************************************************************
	 * Description:  根据排课来源及相应主键创建教学课程(西电模式)
	 * @author：罗璇
	 * @date：2017年3月21日
	 *************************************************************************************/
	public TCourseSite createTCourseSiteForXD(String type, String courseNo,
			Integer timetableSelfCourseId);

	/*************************************************************************************
	 * Description:  根据创建课程添加相应学生
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	public TCourseSite addTCourseSiteUsers(TCourseSite tCourseSite);
	
	/*************************************************************************************
	 * Description:  根据创建课程添加相应学生(西电模式)
	 * @author：罗璇
	 * @date：2017年3月22日
	 *************************************************************************************/
	public TCourseSite addTCourseSiteUsersForXD(TCourseSite tCourseSite);

	/*************************************************************************************
	 * Description:  复制课程资源
	 * 
	 * @author： 裴继超
	 * @date：2016-6-15
	 *************************************************************************************/
	public TCourseSite copyTCourseSite(String allIdsString,Integer tCourseSiteId,
			HttpServletRequest request) ;
	/***********************************************************************************************
	 * description:课程-查询查找课程
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public List<TCourseSite> findTCourseSitesByTCourseSite(
			TCourseSite tCourseSite, Integer currpage, int pageSize);
	/***********************************************************************************************
	 * description:课程-查询查找课程结果数量
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public int getTCourseSiteTotalRecordsByTCourseSite(TCourseSite tCourseSite,
			Integer currpage, int pageSize);
 
	/*************************************************************************************
	 * Description:上传文件
	 * 
	 * @author： 裴继超
	 * @date：2016-7-14
	 *************************************************************************************/
	@SuppressWarnings({ "rawtypes" })
	public void uploadImageForSite(HttpServletRequest request, String path, Integer type, int id) ;
	
	/*************************************************************************************
	 * Description:课程-查找和我相关的课程
	 * 
	 * @author： 裴继超
	 * @date：2016-7-20
	 *************************************************************************************/
	public List<TCourseSite> findMyTCourseSitesByTCourseSite(
			TCourseSite tCourseSite, Integer currpage, int pageSize);

	/*************************************************************************************
	 * Description:课程-查找和我相关的课程数量
	 * 
	 * @author： 裴继超
	 * @date：2016-7-20
	 *************************************************************************************/
	public int getMyTCourseSiteTotalRecordsByTCourseSite(TCourseSite tCourseSite,
			Integer currpage, int pageSize);

	/*************************************************************************************
	 * Description:课程复制-查找当前学期及以后的课程
	 * 
	 * @author： 裴继超
	 * @date：2016-8-11
	 *************************************************************************************/
	public List<TCourseSite> findTCourseSiteByTerm();

	/*************************************************************************************
	 * Description:查询记录数量
	 * 
	 * @author： 储俊
	 * @date：2016-8-23
	 *************************************************************************************/
	public int getSelectCourseTotalRecords();

	/*************************************************************************************
	 * Description:列出所有课程
	 * 
	 * @author： 储俊
	 * @date：2016-8-23
	 *************************************************************************************/
	public List<TCourseSite> findTCourseSite(int currpage, int pageSize);
	/**************************************************************************
	 * Description:删除课程
	 * 
	 * @author：储俊
	 * @date ：2016-8-24
	 **************************************************************************/
	public void delete(TCourseSite tCourseSite);
	
	/**************************************************************************
	 * description:新建课程-添加助教{根据输入内容（学生名称）查询}
	 * 
	 * @author:陈乐为
	 * @date：2016-8-29
	 **************************************************************************/
	@SuppressWarnings({ "rawtypes" })
    public Map<String, List> getCEduTypeDataSetMap(String results,int currpage, int pageSize);
	
	/**************************************************************************
	 * description：新建课程-添加助教{根据学生名称查询}
	 * 
	 * @author：陈乐为
	 * @date：2016-8-29
	 **************************************************************************/
	public List<User> findUsersByQuery(String cName,int currpage, int pageSize);
	
	/*************************************************************************************
	 * description:新建课程-添加助教{返回根据足交名称查找的结果数量}
	 * 
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	public int countUsersByQuery(String cName);
	
	/*************************************************************************************
	 * description:新建课程-添加课程{根据课程名称查找}
	 *
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	@SuppressWarnings({ "rawtypes" })
	public Map<String, List> getCourseNumberSetMap(String results,int currpage, int pageSize);
	
	/*************************************************************************************
	 * description:新建课程-添加课程{根据课程名称查找}
	 * 
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	public List<SchoolCourseInfo> getCourseNumberByQuery(String courseName,int currpage, int pageSize);
	
	/*************************************************************************************
	 * description:新建课程-添加课程{返回根据课程名称查找的结果数量}
	 * 
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	public int countCourseNumberByQuery(String courseName);
	
	/***********************************************************************************************
	 * description:课程--查询查找课程(无论是否open)
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public List<TCourseSite> findAllTCourseSitesByTCourseSite(
			TCourseSite tCourseSite, Integer currpage, int pageSize);
	
	/***********************************************************************************************
	 * description:课程--查询查找课程结果数量(无论是否open)
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public int getAllTCourseSiteTotalRecordsByTCourseSite(TCourseSite tCourseSite,
			Integer currpage, int pageSize);
	
	/***********************************************************************************************
	 * description:课程--查询学生id是否存在于该课程中
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public int isUserNameInCourseSite(int tCourseSiteId,String id);
	/*************************************************************************************
	 * Description:  根据创建课程添加相应实验项目(西电模式)
	 * @return 
	 * @author：戴昊宇
	 * @date：2017年7月03日
	 *************************************************************************************/
	public TCourseSite addOperationForXD(String type, String courseNo,
			Integer timetableSelfCourseId,HttpServletRequest request,TCourseSite tCourseSite) throws ParseException;
	/**************************************************************************
	 * Description:教学平台-获取权限
	 * 
	 * @author：裴继超
	 * @date ：2017-1-3
	 **************************************************************************/
	public Integer getFlagByUserAndSite(User user,TCourseSite tCourseSite,
			HttpSession httpSession);
	/***********************************************************************************************
	 * description:课程--站点查询根据id，站点课程组，站点名
	 * 
	 * @author:李雪腾
	 * @date:2017-7-10
	 ***********************************************************************************************/
	public int getTCourseSiteTotalRecordsByTCourseSiteWithMore(TCourseSite tCourseSite,
			Integer currpage, int pageSize,Integer allCourseFlag,String username);
	/***********************************************************************************************
	 * description:课程-查询查找课程
	 * 
	 * @author:李雪腾
	 * @date:2017-7-10
	 ***********************************************************************************************/
	public List<TCourseSite> findTCourseSitesByTCourseSiteWithMore(
			TCourseSite tCourseSite, Integer currpage, int pageSize,Integer allCourseFlag,String username);
	/*************************************************************************************
	 * Description:知识模块多文件上传
	 * 
	 * @author： 马帅
	 * @date：2018-8-11
	 *************************************************************************************/
	public Map<String,String> uploadMultiFile(HttpServletRequest request, String path, Integer type,Integer siteId);
	/*************************************************************************************
	 * Description:保存文件 
	 * 
	 * @author： 马帅
	 * @date：2018-8-11
	 *************************************************************************************/
	public int saveUpload(WkUpload upload);
	
	/*************************************************************************************
	 * Description:分页查询课程所属学生列表
	 * 
	 * @author：张佳鸣
	 * @date：2017-12-04
	 *************************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteStudentsWithMore(Integer cid,int currPage,int pageSize);
	/***********************************************************************************************
	 * description:实验项目-查询知识章节中是否有实验项目
	 * 
	 * @author:马帅
	 * @date:2017-8-24
	 ***********************************************************************************************/
	public WkChapter findWkChapterBySiteId(Integer id);
}