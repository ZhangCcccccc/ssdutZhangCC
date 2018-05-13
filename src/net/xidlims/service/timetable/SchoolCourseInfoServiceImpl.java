package net.xidlims.service.timetable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SchoolCourseInfoService")
public class SchoolCourseInfoServiceImpl implements SchoolCourseInfoService {
	@Autowired
	private ShareService shareService;
	@Autowired
	private SchoolCourseInfoDAO schoolCourseInfoDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@PersistenceContext
	private EntityManager entityManager;
	
	/*************************************************************************************
	 * @內容：进行获取计数获取课程信息CourseInfo的分页列表信息
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountCourseInfoByQuery(SchoolCourseInfo schoolCourseInfo) {
		String query = "";
		// 如果查询条件为所有，者id为-1，否则增加查询条件
		if (schoolCourseInfo.getCourseNumber()!=null) {
			query = query + " and c.courseNumber like '%" + schoolCourseInfo.getCourseNumber() + "%'";
		}
		if (schoolCourseInfo.getCourseName()!=null) {
			query = query + " and c.courseName like '%" + schoolCourseInfo.getCourseName() + "%'";
		}
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select count(*)  from SchoolCourseInfo c where c.flag=1 and c.academyNumber like '"	+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber() + "'");
		// 将query添加到sb1后
		sql.append(query);
		return ((Long) schoolCourseInfoDAO.createQuerySingleResult(
				sql.toString()).getSingleResult()).intValue();
	}
	
	/*************************************************************************************
	 * @內容：获取CourseInfo的分页列表信息,flag标记位-1为所有
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseInfo> getCourseInfoByQuery(SchoolCourseInfo schoolCourseInfo,int cid, int flag,int curr, int size) {
		String academyNumber="";
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (cid != -1) {
    		//获取选择的实验中心
        	academyNumber = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		String query = "";
		if (schoolCourseInfo!=null&&schoolCourseInfo.getCourseNumber()!=null) {
			query = query + " and c.courseNumber like '%" + schoolCourseInfo.getCourseNumber() + "%'";
		}
		if (schoolCourseInfo!=null&&schoolCourseInfo.getCourseName()!=null) {
			query = query + " and c.courseName like '%" + schoolCourseInfo.getCourseName() + "%'";
		}
		//flag标记位-1为所有
		if(flag!=-1){
			query = query + " and flag=1 ";
		}
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer(
				"select c from SchoolCourseInfo c where  c.academyNumber like '%"	+ academyNumber + "%'");
		// 将query添加到sb1后
		sql.append(query);
	    // 执行sb语句
		List<SchoolCourseInfo> courses = schoolCourseInfoDAO.executeQuery(sql.toString(),(curr-1)*size,size);
		return courses;
	}
	
	/*************************************************************************************
	 * @內容：获取查找自建课程信息记录数
	 * @作者： 魏诚
	 * @日期：2014-08-25
	 *************************************************************************************/
	@Transactional
	public int getSelfSchoolCourseInfoTotalRecords() {
		// 得出用户数量（由于用户的数据量比较多，不能够使用userDAO.findAllUsers()方法查找用户）
	/*	String sql = "select max(REPLACE(courseNumber, concat('self-',c.academyNumber,'-'),''))+0 from SchoolCourseInfo c where 1=1 and flag=1 and c.academyNumber like '"
				+ shareService.getUserDetail()
				.getSchoolAcademy().getAcademyNumber()
		+ "'";*/
		String sql="select count(c) from SchoolCourseInfo c where 1=1";
		sql+=" and c.flag=1";
		sql+=" and c.academyNumber like '"+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()+ "'";
		sql+=" and c.courseNumber like '%self-%' ";
		if(schoolCourseInfoDAO
				.createQuerySingleResult(sql).getSingleResult()==null){
			return 0;
		}else{
			return ((Long) schoolCourseInfoDAO
					.createQuerySingleResult(sql).getSingleResult()).intValue();
		}
	}

	/*************************************************************************************
	 * @內容：获取CourseInfo的分页列表信息,flag标记位-1为所有
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<SchoolCourseInfo> getCourseInfoByQuery(SchoolCourseInfo schoolCourseInfo, int flag, int curr, int size) {
		StringBuffer hql = new StringBuffer("select c from SchoolCourseInfo c where 1=1 ");
		if(flag != -1)
		{
			hql.append(" and c.flag=1");
		}
		if(schoolCourseInfo!=null && schoolCourseInfo.getCourseNumber()!=null && !"".equals(schoolCourseInfo.getCourseNumber()))
		{
			hql.append(" and c.courseNumber='"+schoolCourseInfo.getCourseNumber()+"'");
		}
	/*	if(schoolCourseInfo!=null&&schoolCourseInfo.getAcademyNumber()!=null && !"".equals(schoolCourseInfo.getAcademyNumber()))
		{
			hql.append(" and c.academyNumber='"+schoolCourseInfo.getAcademyNumber()+"'");
		}*/ //2015-11-11  不区分学院
		
		List<SchoolCourseInfo> courses = schoolCourseInfoDAO.executeQuery(hql.toString(), (curr-1)*size, size);
		return courses;
	}
	
	@Override
	public List<Object> findAllSchoolCourseInfo() {
		// TODO Auto-generated method stub
		String sql = "select c.course_number,c.course_name from school_course_info c";
		List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
		return objects;
	}
	
	/**
	 * 查询所有课程(map)
	 * @return
	 */
	public Map<String,String> findAllSchoolCourseInfoMap() {
		
		String sql = " select c from SchoolCourseInfo c where 1=1 ";
		List<SchoolCourseInfo> sci = schoolCourseInfoDAO.executeQuery(sql,0,-1);
		Map<String, String> map = new LinkedHashMap();
		for(SchoolCourseInfo s:sci){
			map.put(s.getCourseNumber(),s.getCourseName());
		}
		return map;
	}
}
