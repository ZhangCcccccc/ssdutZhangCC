package net.xidlims.service.timetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseMergeDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SchoolCourseService")
public class SchoolCourseServiceImpl implements SchoolCourseService {
	 
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private SchoolCourseMergeDAO schoolCourseMergeDAO;
	@PersistenceContext
	private EntityManager entityManager;
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-09-16
	 *************************************************************************************/
	public List<SchoolCourse> getCourseCodeListAll(int termId,int iLabCenter) {
		String sql="select c from SchoolCourse c where 1=1";
		sql+=" and c.schoolTerm.id ="+termId;
		String academyNumber="";
		
		/**
         * 判断是否只是老师角色
        **/     
        User user = shareService.getUserDetail();
        String judge = ",";
        for(Authority authority:user.getAuthorities()){
            judge = judge + "," + authority.getId() + "," ;
        }
        boolean isSuper = false;
        //如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if(judge.indexOf(",11,")>-1){
            isSuper = true;
        }
		
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
        
        if (!isSuper) {// 如果不是管理员，加学院筛选条件
        	sql+=" and schoolAcademy.academyNumber like '"+academyNumber+"'";
		}
        
		sql+=" and c.state=1";
		sql+=" and c.schoolCourseDetails.size>0";
		List<SchoolCourse> list = schoolCourseDAO.executeQuery(sql,0,-1);
		return list;
	}
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 贺子龙
	 * @日期：2017-01-02
	 *************************************************************************************/
	public Map<String, String> getCourseCodeListMap(int termId,int iLabCenter){
		String sql="select c from SchoolCourse c where 1=1";
		sql+=" and c.schoolTerm.id ="+termId;
		String academyNumber="";
		
		/**
         * 判断是否只是老师角色
        **/     
        User user = shareService.getUserDetail();
        String judge = ",";
        for(Authority authority:user.getAuthorities()){
            judge = judge + "," + authority.getId() + "," ;
        }
        boolean isSuper = false;
        //如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
        if(judge.indexOf(",11,")>-1){
            isSuper = true;
        }
		
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
        
        if (!isSuper) {// 如果不是管理员，加学院筛选条件
        	sql+=" and schoolAcademy.academyNumber like '"+academyNumber+"'";
		}
        
		sql+=" and c.state=1";
		sql+=" and c.schoolCourseDetails.size>0";
		List<SchoolCourse> list = schoolCourseDAO.executeQuery(sql,0,-1);
		
		Map<String, String> courseMap = new HashMap<String, String>();
		
		if (list!=null && list.size()>0) {
			for (SchoolCourse schoolCourse : list) {
				// value需要判空
				String courseValue = schoolCourse.getSchoolCourseInfo().getCourseNumber();
				if (!EmptyUtil.isObjectEmpty(schoolCourse.getUserByTeacher())) {
					courseValue = courseValue+
							schoolCourse.getUserByTeacher().getUsername()+","+schoolCourse.getUserByTeacher().getCname()+",";
				}
				courseMap.put(schoolCourse.getCourseNo(), courseValue);
			}
		}
		return courseMap;
	}
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public String getCourseCodeList(int term,int iLabCenter) {

        String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (iLabCenter != -1) {
    		//获取选择的实验中心
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		// 返回可用的星期信息
		List<SchoolCourse> list = schoolCourseDAO.executeQuery("select c from SchoolCourse c where c.schoolTerm.id =" + term + " and schoolAcademy.academyNumber like '" + academyNumber + "'and c.state=1 and c.schoolCourseDetails.size>0",0,-1);
		String jsonWeek = "[";
		// 遍历实验分室
		for (SchoolCourse schoolCourse : list) {
				jsonWeek = jsonWeek + "{\"courseNo\":\"" + schoolCourse.getCourseNo() + "\",\"value\":\"" + schoolCourse.getSchoolCourseInfo().getCourseNumber() + schoolCourse.getUserByTeacher().getCname() + schoolCourse.getCourseName()+"；选课组编号："+ schoolCourse.getCourseCode()+"\"},";
		}
		jsonWeek.substring(0,jsonWeek.length()-1);
		jsonWeek = jsonWeek + "]";
		return jsonWeek;

	}
	
	/*************************************************************************************
	 * @內容：进行termid,获取教务选课组编号
	 * @作者： 魏誠
	 * @日期：2014-09-16
	 *************************************************************************************/
	public List<SchoolCourse> getCourseCodeList(int termId) {
		String sql;
		String academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		sql="select c from SchoolCourse c,SchoolCourseDetail d where 1=1 and d.schoolCourse.courseNo=c.courseNo" +
				" and c.schoolTerm.id =" + termId 
				+ " and c.state=1 and c.schoolCourseDetails.size<>0" +
				" and d.endClass>0 and d.state=1";
		//2015-11-27  所有选课组放开学院
	/*	+" and schoolAcademy.academyNumber like '" + 
		shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()+"'" */
		sql+=" group by c.courseNo order by " + "case when c.schoolAcademy.academyNumber like '" + academyNumber + "' then 0 else 1 end, "+
				"c.courseCode";//group by c.courseNo  为了防止教务排课管理页面--选课组：查询条件中出现看似重复的两条记录
		List<SchoolCourse> list = schoolCourseDAO.executeQuery(sql,0,-1);
		return list;
	}
    /*************************************************************************************
 * @內容：根据username找到学生信息
 * @作者： 戴昊宇
 * @日期：2017-09-27
 *************************************************************************************/
   public List<SchoolCourseStudent> findSchoolCourseStudentByStudentNumber(String studentNumber){
    String sql="select c from SchoolCourseStudent c where 1=1";
    sql+=" and c.userByStudentNumber.username ="+studentNumber;
    List<SchoolCourseStudent> list = schoolCourseStudentDAO.executeQuery(sql,0,-1);
    return list;
}
   /*************************************************************************************
    * @內容：新建课程时保存相应的详细SchoolCourse课程信息
    * @作者： 张佳鸣
    * @日期：2017-10-18
    *************************************************************************************/
   public void saveSchoolCourseByTCourseSite(TCourseSite tCourseSite){
	   
	   //new一个新的schoolCourse
	   SchoolCourse schoolCourse = new SchoolCourse();
	   //定义课程序号
	   String courseNo = tCourseSite.getSchoolCourseInfo().getCourseNumber();
	   //根据课程序号查找所有的SchoolCourse
	   List<SchoolCourse> schoolCourses = this.findSchoolCoursesByCourseNumber(courseNo);
	   //循环遍历查找出的SchoolCourse，取出其courseNo最大编号
	    for(SchoolCourse s:schoolCourses){
	    	
	    	//new一个新的list集合，存放所有courseNo编号
	    	List<Integer> lists = new ArrayList<Integer>(100);
	    	//根据课程序号长度截取courseNo编号，共两位
	    	int size = courseNo.length();
	    	int num = Integer.parseInt(s.getCourseNo().substring(size,size+2));
	    	lists.add(num);
	    	
	    }
	   
	   
	   schoolCourse.setCourseNo(courseNo);
	   
   }
   
   /*************************************************************************************
    * @內容：根据CourseNumber查找所有的SchoolCourse
    * @作者： 张佳鸣
    * @日期：2017-10-18
    *************************************************************************************/
   public List<SchoolCourse> findSchoolCoursesByCourseNumber(String courseNumber){
	   
	   String sql = "select c from SchoolCourse c where c.schoolCourseInfo.courseNumber ='"+courseNumber+"'";
	   List<SchoolCourse> list = schoolCourseDAO.executeQuery(sql,0,-1);
	   return list;
   }

   /*************************************************************************************
    * @內容：根据课程代码查找合班信息并返回课序号
    * @作者： 张佳鸣
    * @日期：2017-10-23
    *************************************************************************************/
   public String findSchoolCourseMergeCourseNumberByCourseCode(String courseCode){
	   
	   String sql ="select s from SchoolCourseMerge s where s.courseNumber like '%"+courseCode+"%'";
	   
	   List<SchoolCourseMerge> list = schoolCourseMergeDAO.executeQuery(sql,0,-1);
	   
	   if (list!=null && !list.isEmpty()){
		   
		   return list.get(0).getCourseNumber();
	   }else{
		   
		   return null;
	   }
   }
   /*************************************************************************************
	 * Description:学生查询的ajax调用
	 *
	 * @author：戴昊宇
	 * @date：2017-10-25
	 *************************************************************************************/
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> coolSuggestTuser(String userName) {
		//获取批次列表
		String sql= "select sd.username ,sd.cname from user sd "
				+ "where username like '%" + userName + "%'  limit 20";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list =query.getResultList();
		// 返回可用的信息
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		// 遍历学生
		for (Object[] object : list) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", (String) object[0]);
			map.put("data",  (String) object[1]+(String) object[0]);
			returnList.add(map);
		}
		return returnList;
	}
	   /*************************************************************************************
	 * @內容：根据courseNo找到学生
	 * @作者： 戴昊宇
	 * @日期：2017-09-27
	 *************************************************************************************/
	   public List<SchoolCourseStudent> findSchoolCourseStudentByCourseNo(String courseDetailNo){
	    String sql="select c from SchoolCourseStudent c where c.schoolCourseDetail.courseDetailNo='"
				+ courseDetailNo + "'  and c.state=1";
	    List<SchoolCourseStudent> list = schoolCourseStudentDAO.executeQuery(sql,0,-1);
	    return list;
    }
}