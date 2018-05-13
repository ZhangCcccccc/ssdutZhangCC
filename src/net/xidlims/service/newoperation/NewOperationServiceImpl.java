package net.xidlims.service.newoperation;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.COperationOutlineCreditDAO;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.OperationItemDeviceDAO;
import net.xidlims.dao.OperationItemMaterialRecordDAO;
import net.xidlims.dao.OperationOutlineDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDeviceStatus;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.COperationOutlineCredit;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationItemDevice;
import net.xidlims.domain.OperationItemMaterialRecord;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.timetable.SchoolCourseService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("NewOperationService")
public class NewOperationServiceImpl implements NewOperationService {

	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private OperationItemMaterialRecordDAO operationItemMaterialRecordDAO;
	@Autowired
	private OperationItemDeviceDAO operationItemDeviceDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private OperationOutlineDAO  operationOutlineDAO;
	@Autowired
	private SchoolCourseInfoDAO  schoolCourseInfoDAO;
	@Autowired
	private SystemMajor12DAO  systemMajor12DAO;
	@Autowired
	private COperationOutlineCreditDAO  cOperationOutlineCreditDAO;
	@Autowired
	private SchoolAcademyDAO  SchoolAcademyDAO;
	@Autowired
	private SchoolCourseService  schoolCourseService;
	@Autowired
	private CDictionaryDAO  cDictionaryDAO;
	@Autowired
	private CommonDocumentDAO  commonDocumentDAO;
	@Autowired
	private SchoolTermDAO  schoolTermDAO;
	
	
	/*************************************************************************************
	 * description：查找所有的实验大纲名称
	 * @authot:郑昕茹
	 * @date:2017-04-11
	 ***************************************************************************************/
	public List<OperationOutline> getOutlineNames(HttpServletRequest request) {
		String sql="select o from OperationOutline o where 1=1";
		if(!request.getSession().getAttribute("authorityName").equals("SUPERADMIN") && !request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER")){
			 sql+=" and o.schoolCourseInfoByClassId.academyNumber like '%"+shareService.getUser().getSchoolAcademy().getAcademyNumber()+"%'" ; 
		 }
		sql += " group by o.labOutlineName";
		return  operationOutlineDAO.executeQuery(sql,0,-1);
	}


	/*************************************************************************************
	 * description：根据实验大纲生成要新建的实验项目的编号
	 * @authot:郑昕茹
	 * @date:2017-04-11
	 ***************************************************************************************/
	public String getItemCodeByOutline(OperationOutline o) {
		String courseNumber = o.getSchoolCourseInfoByClassId().getCourseNumber();
		
		String itemCodeRight = "";
		if(this.findMaxCodeCustom(o.getId()) != null){
			String lastCodeCustom = this.findMaxCodeCustom(o.getId()).getLpCodeCustom();
			itemCodeRight ="0000"+(Integer.parseInt(lastCodeCustom.substring(lastCodeCustom.length()-4,lastCodeCustom.length()))+1);
		}
		else{
			itemCodeRight ="0001";
		}
		itemCodeRight = itemCodeRight.substring(itemCodeRight.length()-4,itemCodeRight.length());
		return courseNumber+"-"+itemCodeRight;
	}
	
	
	/***********************************************************************************
	 * @功能：根据属性orderNumber找到对于的operationItem
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public OperationItem findOperationItemByOrderNumberAndOutlineId(Integer order, Integer outlineId){
		//查询表中statusOrder为order的记录
		String hql = "select o from OperationItem o where 1=1";
		hql += "and o.orderNumber =" + order.toString();
		hql += " and o.operationOutline.id ="+outlineId;
		List<OperationItem> items = operationItemDAO.executeQuery(hql,0,-1);
		if(items != null && items.size() > 0)
		{
			return items.get(0);
		}
		return null;
	}
	
	
	/***********************************************************************************
	 * @功能：找到 实验项目中orderNumber最大的那条记录
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public OperationItem findMaxOrderNumber(Integer outlineId){
		String hql = "select o from OperationItem o where 1=1";
		hql += " and o.orderNumber = (select max(s.orderNumber) from OperationItem s where 1=1 and s.operationOutline.id ="+outlineId+")";
		hql += " and o.operationOutline.id ="+outlineId;
		List<OperationItem> items = operationItemDAO.executeQuery(hql,0,-1);
		if(items != null && items.size() > 0)
		{
			return items.get(0);
		}
		return null;
	}
	
	
	/***********************************************************************************
	 * @功能：找到实验大纲下的全部实验项目并按照orderNumber排序
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public List<OperationItem> findOperationItemOutlineIdOrderByNumber( Integer outlineId){
		//查询表中statusOrder为order的记录
		String hql = "select o from OperationItem o where 1=1";
		hql += " and o.operationOutline.id ="+outlineId;
		hql += " order by o.orderNumber";
		List<OperationItem> items = operationItemDAO.executeQuery(hql,0,-1);
		return items;
	}
	
	
	/***********************************************************************************
	 * @功能：找到 实验项目中编号最大的那条记录
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public OperationItem findMaxCodeCustom(Integer outlineId){
		String hql = "select o from OperationItem o where 1=1";
		hql += " and o.lpCodeCustom = (select max(s.lpCodeCustom) from OperationItem s where 1=1 and s.operationOutline.id ="+outlineId+")";
		hql += " and o.operationOutline.id ="+outlineId;
		List<OperationItem> items = operationItemDAO.executeQuery(hql,0,-1);
		if(items != null && items.size() > 0)
		{
			return items.get(0);
		}
		return null;
	}
	
	/***********************************************************************************
	 * @功能：根据属性orderNumber找到orderNumber大于它的operationItem
	 * @author 郑昕茹
	 * @日期：2017-04-12
	 * **********************************************************************************/
	public List<OperationItem> findOperationItemsLargerOrderNumberByOutlineId(Integer order, Integer outlineId){
		//查询表中statusOrder为order的记录
		String hql = "select o from OperationItem o where 1=1";
		hql += "and o.orderNumber >" + order.toString();
		hql += " and o.operationOutline.id ="+outlineId;
		return operationItemDAO.executeQuery(hql,0,-1);
	}
	
	
	/***********************************************************************************
	 * @功能：找到所有审核中的实验大纲
	 * @author 郑昕茹
	 * @日期：2017-04-13
	 * **********************************************************************************/
	public List<OperationOutline> getAuditOutlinelistpage(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize,int sid) {
		Integer dictionaryTermId = null;
		if(request.getParameter("searchterm")!=null&&!request.getParameter("searchterm").equals("")){
			dictionaryTermId = Integer.valueOf(request.getParameter("searchterm"));
		}
		String sql="select o from OperationOutline o left join o.studyStages oc where 1=1 ";
		if(dictionaryTermId!=null){
			sql += " and oc.id = " + dictionaryTermId;
		}
		
		//sql+="and o.schoolAcademy.academyNumber like  '%"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"%'";
		 if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseNumber  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		   
		  }
		 if(operationOutline.getStatus() != null && !operationOutline.getStatus().equals("")){
			 sql += " and o.status = "+operationOutline.getStatus();
		 }
		 sql += " and o.status = 1";
		 sql += " group by o.id";
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲
	 * @author 郑昕茹
	 * @日期：2017-04-13
	 * **********************************************************************************/
	public List<OperationOutline> getAllOutlinelistpage(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize,int sid) {
		Integer dictionaryTermId = null;
		if(request.getParameter("searchterm")!=null&&!request.getParameter("searchterm").equals("")){
			dictionaryTermId = Integer.valueOf(request.getParameter("searchterm"));
		}
		String sql="select o from OperationOutline o left join o.studyStages oc where 1=1 ";
		if(dictionaryTermId!=null){
			sql += " and oc.id = " + dictionaryTermId;
		}
		
		//sql+="and o.schoolAcademy.academyNumber like  '%"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"%'";
		 if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseNumber  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		   
		  }
		 if(operationOutline.getStatus() != null && !operationOutline.getStatus().equals("")){
			 sql += " and o.status = "+operationOutline.getStatus();
		 }
		 sql += " group by o.id";
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	
	
	
	/***********************************************************************************
	 * @功能：查找当前登陆人所在学院下的所有课程
	 * @author 郑昕茹
	 * @日期：2017-04-13
	 * **********************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getschoolcouresMapByAcademy(HttpServletRequest request) {
		Map attributesMap=new HashMap();
		 String sql="select s from SchoolCourseInfo s left join s.schoolCourses sc where 1=1 ";
		 //sql +="and sc.schoolCourseInfo.courseNumber=s.courseNumber ";
		 if(!request.getSession().getAttribute("authorityName").equals("SUPERADMIN") && !request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER")){
			 sql+=" and s.academyNumber like '%"+shareService.getUser().getSchoolAcademy().getAcademyNumber()+"%'" ; 
		 }		 
		
	List<SchoolCourseInfo> d=	schoolCourseInfoDAO.executeQuery(sql,1,-1);
		for(SchoolCourseInfo schoolCourseInfo: d)
			attributesMap.put(schoolCourseInfo.getCourseNumber(), schoolCourseInfo.getCourseName()+" "+schoolCourseInfo.getCourseNumber());
		return attributesMap;	
	}
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲（学生相关）
	 * @author 郑昕茹
	 * @日期：2017-05-02
	 * **********************************************************************************/
	public List<OperationOutline> getListStudentsOperationOutlines(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize) {

		String sql="select o from OperationOutline o left join o.operationItems oi left join oi.timetableItemRelateds tir"
				+" left join tir.timetableAppointment ta left join ta.timetableGroups tg left join tg. timetableGroupStudentses tgs where 1=1 ";

		sql += " and tgs.user.username = '"+shareService.getUser().getUsername()+"'";
		//sql+="and o.schoolAcademy.academyNumber like  '%"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"%'";
		 if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseNumber  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		   
		  }
		 
		 sql += " group by o.id";
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	
	
	/***********************************************************************************
	 * @功能：根据课程编号找到其下全部实验大纲
	 * @author 郑昕茹
	 * @日期：2017-05-02
	 * **********************************************************************************/
	public List<OperationOutline> findOperationOutlinesByCourseCodeAndType(
			OperationOutline operationOutline, int currpage, int pagesize, String courseCode, Integer type) {
		String sql = "select o from OperationOutline o left join o.schoolCourseInfoByClassId sci left join sci.schoolCourses sc left join sc.schoolCourseDetails scd left join scd.schoolCourseMerge scm  where 1=1";
		if(type == 21 || type == 22 || type == 28 || type == 23){
			sql += " and scd.courseDetailNo ='"+courseCode+"'";
		}
		else{
			sql += " and scm.id ="+"'"+courseCode+"'";
		}
		 if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseNumber  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		   
		  }
		 
		 sql += " group by o.id";
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲（教师相关）
	 * @author 郑昕茹
	 * @日期：2017-05-02
	 * **********************************************************************************/
	public List<OperationOutline> getListTeachersOperationOutlines(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize) {

		String sql="select o from OperationOutline o left join o.operationItems oi left join oi.timetableItemRelateds tir"
				+" left join tir.timetableAppointment ta left join ta.timetableTeacherRelateds ttr where 1=1 ";

		sql += " and ttr.user.username = '"+shareService.getUser().getUsername()+"'";
		//sql+="and o.schoolAcademy.academyNumber like  '%"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"%'";
		 if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseNumber  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		   
		  }
		 
		 sql += " group by o.id";
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	
	

	/***********************************************************************************
	 * @description：根据课程找到其下所有实验项目
	 * @author 郑昕茹
	 * @date：2017-05-14
	 * **********************************************************************************/
	public List<OperationItem> findOperationItemsByCourseNumber(String courseNumber){
		String s = "select o from OperationItem o where 1=1";
		s += " and o.operationOutline.schoolCourseInfoByClassId.courseNumber ='"+courseNumber+"'";
		s += " order by o.id";
		return operationItemDAO.executeQuery(s, 0,-1);
	}
	
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲
	 * @author 郑昕茹
	 * @日期：2017-05-28
	 * **********************************************************************************/
	public List<OperationOutline> getListAllOperationOutlines(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize) {

		String sql="select o from OperationOutline o where 1=1";
		if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
			   sql+=" and o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
			   
		}
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	
}