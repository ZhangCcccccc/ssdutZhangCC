package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.TCourseSiteChannelDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.TExperimentSkillDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.dao.WkChapterDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.timetable.SchoolCourseService;

@Service("TCourseSiteService")
public class TCourseSiteServiceImpl implements TCourseSiteService {
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	
	@Autowired
	private TCourseSiteChannelDAO tCourseSiteChannelDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;
	
	@Autowired
	private WkChapterService wkChapterService;
	
	@Autowired
	private WkLessonService wkLessonService;
	
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private WkChapterDAO wkChapterDAO;
	@Autowired
	private SchoolCourseInfoDAO schoolCourseInfoDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TExperimentSkillDAO tExperimentSkillDAO;
	@Autowired
	private TExperimentSkillService tExperimentSkillService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private SchoolCourseService schoolCourseService;
	@Autowired
	private WkUploadDAO wkUploadDAO;
	
	/****************************************************************************
	 * Description:查找所有的自建课程信息
	 * 
	 * @author：魏诚
	 * @date：2015-07-24
	 ****************************************************************************/
	public List<TCourseSite> findAllTCourseSites(int curr,
			int size) {
		// 利用sql语句从用户表中查找出所有的用户，并赋给StringBuffer类型的sb变量
		StringBuffer sb = new StringBuffer(
				"select c from TCourseSite c where 1=1 and c.status = '1' and c.schoolAcademy.academyNumber like '"
						+ shareService.getUserDetail().getSchoolAcademy()
								.getAcademyNumber() + "'");
		// 给语句添加分页机制
		List<TCourseSite> tCourseSites = tCourseSiteDAO
				.executeQuery(sb.toString(), curr * size, size);
		return tCourseSites;
	}

	/****************************************************************************
	 * Description:获取课程站点信息的分页列表信息
	 * 
	 * @author：魏诚
	 * @date：2015-07-24
	 ****************************************************************************/
	public List<TCourseSite> findAllTCourseSites(
			TCourseSite tCourseSite, int curr, int size) {
		String query = "";
		// 判断获取的用户的信息是否为空
		if (tCourseSite.getSchoolCourseInfo() != null
				&& tCourseSite.getSchoolCourseInfo().getCourseNumber() != null) {
			query = query
					+ " and c.schoolCourseInfo.courseNumber like '%"
					+ tCourseSite.getSchoolCourseInfo()
							.getCourseNumber() + "%'";
		}
		// 查询表；
		StringBuffer sql = new StringBuffer(
				"select c from TCourseSite c where  1=1 and c.status = '1' and c.schoolAcademy.academyNumber like '"
						+ shareService.getUserDetail().getSchoolAcademy()
								.getAcademyNumber() + "' ");
		// 将query添加到sb1后
		sql.append(query);
		// sql.append(" order by c.username");
		// 执行sb语句
		List<TCourseSite> tTCourseSites = tCourseSiteDAO
				.executeQuery(sql.toString(), curr * size, size);
		return tTCourseSites;
	}
	
	/****************************************************************************
	 * Description:获取查找课程站点信息记录数
	 * 
	 * @author：魏诚
	 * @date：2015-07-24
	 ****************************************************************************/
	@Transactional
	public int getTCourseSiteTotalRecords() {
		// 得出用户数量（由于用户的数据量比较多，不能够使用userDAO.findAllUsers()方法查找用户）
		return ((Long) tCourseSiteDAO
				.createQuerySingleResult(
						"select count(c) from TCourseSite c where 1=1 and c.status = '1' and c.schoolAcademy.academyNumber like '"
								+ shareService.getUserDetail()
										.getSchoolAcademy().getAcademyNumber()
								+ "'").getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * Description:保存 新建课程站点
	 * 
	 * @author：魏诚
	 * @date：2015-07-31
	 ****************************************************************************/
	public String saveTCourseSite(HttpServletRequest request,TCourseSite tCourseSite,
			int flagID) {
		String returnString = "";
		if (flagID == -1) {//新增
			//文件上传
			String filePath="/upload/courseSite/siteImage";
			//String siteImage=shareService.fileUpload("file",request, filePath);
			//String fileUrl="/upload/courseSite/teacherImage";
			//String teacherImage=shareService.fileUpload("teacherfile",request, fileUrl);
			//tCourseSite.setSiteImage(siteImage);
			tCourseSite.setType("2");
			tCourseSite.setStatus(1);
			tCourseSite.setSchoolAcademy(shareService.getUserDetail().getSchoolAcademy());//根据当前用户获取所在学院信息
			
			//保存相应的SchoolCourse课程信息
//			schoolCourseService.
			
//			tCourseSite.setSchoolCourse(tCourseSite.getSchoolCourseInfo().getSchoolCourses());
			//tCourseSite.setTeacherImage(teacherImage);
			
			tCourseSite = tCourseSiteDAO.store(tCourseSite);
			tCourseSiteDAO.flush();
			String userNames = request.getParameter("userName");
			String[] sourceStrArray=userNames.split(",");
			//标志位：助教是否已存在该课程中(不包括学生)
			int isExist = 0;
			for(int i=0;i<sourceStrArray.length;i++) {
				if(sourceStrArray[i] != null && !sourceStrArray[i].equals("")) {
					isExist=isUserNameInCourseSite(tCourseSite.getId(),sourceStrArray[i]);
					if(isExist == 0)
					{
						TCourseSiteUser tCourseSiteUser = new TCourseSiteUser();
						User user = tCourseSiteUserService.findUserByUsername(sourceStrArray[i]);
						tCourseSiteUser.setUser(user);
						tCourseSiteUser.setTCourseSite(tCourseSite);
						tCourseSiteUser.setRole(1);
						Authority authority = authorityDAO.findAuthorityByPrimaryKey(2);
						tCourseSiteUser.setAuthority(authority);
						tCourseSiteUser.setIncrement(0);
						tCourseSiteUserDAO.store(tCourseSiteUser);
					}
				}
			}
			
			returnString = "redirect:/teaching/coursesite/newCourseSite?id="+ tCourseSite.getId();
		} else {//编辑
			
			TCourseSite tCourseSiteEdit = tCourseSiteDAO.findTCourseSiteById(flagID);
			//文件上传
			//String filePath="/upload/courseSite/siteImage";
			//String siteImage=shareService.fileUpload("file",request, filePath);
			//String fileUrl="/upload/courseSite/teacherImage";
			//String teacherImage=shareService.fileUpload("teacherfile",request, fileUrl);
			//if(siteImage!=null&&!siteImage.equals("")){
			//	tCourseSiteEdit.setSiteImage(siteImage);
			//}
			//if(teacherImage!=null&&!teacherImage.equals("")){
			//	tCourseSiteEdit.setTeacherImage(teacherImage);
			//}
			tCourseSiteEdit.setIsOpen(tCourseSite.getIsOpen());
			tCourseSiteEdit.setSiteCode(tCourseSite.getSiteCode());
			tCourseSiteEdit.setTitle(tCourseSite.getTitle());
			tCourseSiteEdit.setSchoolCourseInfo(tCourseSite.getSchoolCourseInfo());
			tCourseSiteEdit.setUserByCreatedBy(tCourseSite.getUserByCreatedBy());
			tCourseSiteEdit.setSchoolTerm(tCourseSite.getSchoolTerm());
			tCourseSiteDAO.store(tCourseSiteEdit);
			returnString = "redirect:/teaching/coursesite/newCourseSite?id="+flagID;
		}
		
		
		
		
		
		return returnString;
	}

	/*************************************************************************************
	 * Description:根据站点Id查询课程站点
	 * 
	 * @author： 黄崔俊
	 * @date：2015-8-6 
	 *************************************************************************************/
	@Override
	public TCourseSite findTCourseSiteById(String tCourseSiteId) {
		// TODO Auto-generated method stub
		return tCourseSiteDAO.findTCourseSiteByPrimaryKey(Integer.valueOf(tCourseSiteId));
	}
	/*************************************************************************************
	 * Description:根据站点Id查询课程站点
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	@Override
	public TCourseSite findCourseSiteById(Integer id) {
		return tCourseSiteDAO.findTCourseSiteByPrimaryKey(id);
	}

	/*************************************************************************************
	 * Description:根据站点Id和状态id查询该课程站点下的栏目
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	@Override
	public List<TCourseSiteChannel> findChannelsBySiteIdAndState(Integer id,int i) {
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteChannel c where c.TCourseSite.id="+id+" and c.CDictionary.CCategory='"+CommonConstantInterface.STR_TCOURSESITE_CHANNELSTATE+"' and c.CDictionary.CNumber = "+i+" order by c.id ");
		return tCourseSiteChannelDAO.executeQuery(sbf.toString(), 0,-1);
	}

	/*************************************************************************************
	 * Description:根据站点Id和标签id查询该课程站点下对应栏目
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	@Override
	public TCourseSiteChannel findChannelBySiteIdAndTag(Integer id, int i) {
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteChannel c where c.TCourseSite.id="+id+" and c.TCourseSiteTag.id="+i);
		List<TCourseSiteChannel> channels=tCourseSiteChannelDAO.executeQuery(sbf.toString(), 0,-1);
		if(channels.size()>0){
			return channels.get(0);
		}else{
			return null;
		}
		
	}
	/*************************************************************************************
	 * Description:根据是否开放查找站点
	 * 
	 * @author： 李小龙
	 * @date：2015-8-11 
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findOpenTCourseSite(Integer s) {
		StringBuffer sbf=new StringBuffer("select t from TCourseSite t where t.status = '1' and t.isOpen='"+s+"'");
		return tCourseSiteDAO.executeQuery(sbf.toString(), 0,-1);
	}
	/*************************************************************************************
	 * Description:根据站点Id查询已选学生
	 * 
	 * @author：裴继超
	 * @date：2015-9-24
	 *************************************************************************************/
	@Override
	public List<TCourseSiteUser> findCourseSiteUserBysiteId(Integer id) {
		String sql="from TCourseSiteUser c where c.TCourseSite.id='"+id+"' and c.authority.id = '1'";
		return tCourseSiteUserDAO.executeQuery(sql, 0,-1);
	}
	/*************************************************************************************
	 * Description:查找所有站点
	 * 
	 * @author：裴继超
	 * @date：2015-9-25
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findAllTCourseSite() {
		StringBuffer allsites=new StringBuffer(" from TCourseSite t where t.status = '1'");
		return tCourseSiteDAO.executeQuery(allsites.toString(), 0,-1);
	}
	
	/*************************************************************************************
	 * Description:当前登录人是学生时，根据学生ID查找学生选课
	 * 
	 * @author：姜新剑
	 * @date：2015-10-15
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findStudentTCourseSite(String username) {
		//String sql=" select c from TCourseSite c where c.id in ( select siteid from TCourseSiteUser where User.username ='"+username+"')";
		String sql = "select c from TCourseSiteUser c where c.user.username = '"+username+"'";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql, 0, -1);
		List<TCourseSite> tCourseSites = new ArrayList<TCourseSite>();
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			if(tCourseSiteUser.getTCourseSite().getStatus()==1){
				tCourseSites.add(tCourseSiteUser.getTCourseSite());
			}
		}
		return tCourseSites;
	}
	/*************************************************************************************
	 * Description:当前登录人是老师权限时，根据老师ID查找老师增加的课程
	 * 
	 * @author：姜新剑
	 * @date：2015-10-15
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findTeacherTCourseSite(String username) {
		String sql=" select c from TCourseSite c where c.status = '1' and c.userByCreatedBy.username like '"+username+"'";
		return tCourseSiteDAO.executeQuery(sql, 0,-1);
	}

	
	/*************************************************************************************
	 * Description:根据标签id和是否开放查询该课程站点下对应栏目
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-13
	 *************************************************************************************/
	@Override
	public TCourseSiteChannel findChannelByTagAndIsOpen(Integer tagId,Integer state) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteChannel c where c.TCourseSiteTag.id='"+tagId+"' c.CDictionary.CCategory='"+CommonConstantInterface.STR_TCOURSESITE_CHANNELSTATE+"' and c.CDictionary.CNumber =  '"+state+"'");
		List<TCourseSiteChannel> channels=tCourseSiteChannelDAO.executeQuery(sbf.toString(), 0,-1);
		if(channels.size()>0){
			return channels.get(0);
		}else{
			return null;
		}
	}
	/*************************************************************************************
	 * Description:根据标签id和是否开放查询该课程站点下对应栏目列表
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-19
	 *************************************************************************************/
	@Override
	public List<TCourseSiteChannel> findChannelListByTagAndIsOpen(Integer tagId, Integer state) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteChannel c where c.TCourseSiteTag.id='"+tagId+"' c.CDictionary.CCategory='"+CommonConstantInterface.STR_TCOURSESITE_CHANNELSTATE+"' and c.CDictionary.CNumber = '"+state+"'");
		List<TCourseSiteChannel> channels=tCourseSiteChannelDAO.executeQuery(sbf.toString(), 0,-1);
		return channels;
	}
	/*************************************************************************************
	 * Description:查询开放的课程数量
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-19
	 *************************************************************************************/
	@Override
	public int getTCourseSiteTotalRecords(Integer isOpen) {
		// TODO Auto-generated method stub
		String sql = "select count(c) from TCourseSite c where c.status = '1' and c.isOpen = '"+isOpen+"'";
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	/*************************************************************************************
	 * Description:查询开放的课程列表
	 * 
	 * @author：黄崔俊
	 * @date：2015-11-19
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findTCourseSites(Integer isOpen, Integer currpage,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSite c where c.status = '1' and c.isOpen = '"+isOpen+"'";
		List<TCourseSite> tCourseSites = tCourseSiteDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return tCourseSites;
	}
	/*************************************************************************************
	 * Description:课程所属学生列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public List<TCourseSiteUser> findTCourseSiteStudents(Integer cid) {
		// TODO Auto-generated method stub
		User user = shareService.getUser();
		String hql = "from TCourseSiteUser c where c.TCourseSite.id = '"+cid+"' and c.authority.id = '1'";
		if (user.getAuthorities().toString().contains("STUDENT")) {//学生身份则只查自己
			hql += " and c.user.username = '"+ user.getUsername() +"'";
		}
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(hql, 0, -1);
		return tCourseSiteUsers;
	}
	/*************************************************************************************
	 * Description:导入学生信息（excel格式）
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Transactional
	@Override
	public String importStudentsXls(String filePath, Integer cid) {
		// TODO Auto-generated method stub
		String result = null;//用于判断结果
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (filePath.endsWith("xlsx")) {
			isE2007 = true;
		}
		try {
			InputStream input = new FileInputStream(filePath); // 建立输入流
			Workbook wb = null;
			// 根据文件格式(2003或者2007)来初始化
			if (isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
		    Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
		    Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
		    String sql =  "select username from user where enabled = '1'";
		    List<String> userList = entityManager.createNativeQuery(sql).getResultList();
		    
		    sql = "select username from t_course_site_user where site_id = '"+cid+"' and permission = '1'";
			List<String> tCouserSiteUserList = entityManager.createNativeQuery(sql).getResultList();

		    String insertUser = "";
		    String insertUserAuthority = "";
		    String insertTCourseSiteUser = "";
		    while (rows.hasNext()) {
				Row row = rows.next(); // 获得行数据
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				String username = row.getCell(0).getStringCellValue();// 学生学号
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				String cname = row.getCell(1).getStringCellValue();// 学生姓名
				/*row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				String password = row.getCell(1).getStringCellValue();*/
				if (!userList.contains(username)) {
					insertUser += "('"+username+"','"+cname+"','"+username+"',true,'0'),";
					insertUserAuthority += "('"+username+"','1'),";
				}
				if (!tCouserSiteUserList.contains(username)) {
					insertTCourseSiteUser += "('"+cid+"','"+username+"','1'),";
				}
			}
		    if (insertUser.length()>0) {
				insertUser = "insert into `user` (`username`,`cname`,`password`,`enabled`,`user_role`) values "+insertUser.substring(0, insertUser.length()-1);
				entityManager.createNativeQuery(insertUser).executeUpdate();
				insertUserAuthority = "insert into `user_authority` (`user_id`,`authority_id`) values "+insertUserAuthority.substring(0, insertUserAuthority.length()-1);
				entityManager.createNativeQuery(insertUserAuthority).executeUpdate();
		    }
		    if (insertTCourseSiteUser.length()>0) {
		    	insertTCourseSiteUser = "insert into `t_course_site_user` (`site_id`,`username`,`permission`) values "+insertTCourseSiteUser.substring(0, insertTCourseSiteUser.length()-1);
		    	entityManager.createNativeQuery(insertTCourseSiteUser).executeUpdate();
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/*************************************************************************************
	 * Description:根据学期查询选课组记录数
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public int getTCourseSiteTotalRecordsByTerm(Integer isOpen, Integer termId,TCourseSite tCourseSite) {
		// TODO Auto-generated method stub
		String sql = "select count(c) from TCourseSite c where c.status = '1'";
		if (isOpen!=-1) {//isOpen为-1则查询所有选课组
			sql +=" and c.isOpen = '"+isOpen+"'";
		}
		if (termId != null) {
			sql += " and c.schoolTerm.id = '"+termId+"'";
		}
		if (tCourseSite!=null&&tCourseSite.getSchoolCourseInfo()!=null&&!"".equals(tCourseSite.getSchoolCourseInfo().getCourseName())) {
			sql += " and c.schoolCourseInfo.courseName like '%"+tCourseSite.getSchoolCourseInfo().getCourseName().trim()+"%'";
		}
		//如果不是管理员身份登录，则默认为老师身份，仅可查询自己的选课组列表
		User user = shareService.getUser();
		if (!user.getAuthorities().toString().contains("SUPERADMIN")) {
			sql += " and c.userByCreatedBy.username = '"+user.getUsername()+"'";
		}
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	/*************************************************************************************
	 * Description:根据学期查询选课组列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findTCourseSitesByTerm(Integer isOpen,
			Integer termId, Integer currpage, int pageSize, TCourseSite tCourseSite) {
		// TODO Auto-generated method stub
		String sql = "";
		User user = shareService.getUser();
		if (user.getAuthorities().toString().contains("STUDENT")) {//如果是学生身份,根据学期查询选课组列表
			sql = "select c from TCourseSite c join c.TCourseSiteUsers u where c.status = '1' and c.isOpen = '1'";
			if (termId != null) {
				sql += " and c.schoolTerm.id = '"+termId+"'";
			}
			sql += " and u.user.username = '"+user.getUsername()+"'";
		}else {//不是学生身份
			sql = "from TCourseSite c where c.status = '1' ";
			if (isOpen!=-1) {//isOpen为-1则查询所有选课组
				sql +=" and c.isOpen = '"+isOpen+"'";
			}
			if (termId != null) {
				sql += " and c.schoolTerm.id = '"+termId+"'";
			}
			if (tCourseSite!=null&&tCourseSite.getSchoolCourseInfo()!=null&&!"".equals(tCourseSite.getSchoolCourseInfo().getCourseName().trim())) {
				sql += " and c.schoolCourseInfo.courseName like '%"+tCourseSite.getSchoolCourseInfo().getCourseName()+"%'";
			}
			//如果不是管理员身份登录，则默认为老师身份，仅可查询自己的选课组列表
			
			if (!user.getAuthorities().toString().contains("SUPERADMIN")) {
				sql += " and c.userByCreatedBy.username = '"+user.getUsername()+"'";
			}
		}
		List<TCourseSite> tCourseSites = tCourseSiteDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return tCourseSites;
	}
	/*************************************************************************************
	 * Description:根据登陆人判断可选老师列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public Map<Object, Object> getCourseSiteTearcherMap(User user) {
		// TODO Auto-generated method stub
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (user.getAuthorities().toString().contains("SUPERADMIN")) {//如果是超管则查询所有老师
			String sql = "SELECT u.username,u.cname from `user` u LEFT JOIN user_authority a" +
					" ON u.username = a.user_id" +
					" WHERE a.authority_id = '2'" +
					" AND u.user_role = '1'";
			List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
			for (Object object : objects) {
				Object[] objs = (Object[])object;
				map.put(objs[0], objs[1]);
			}
		}else {//否则就只有当前教师
			map.put(user.getUsername(), user.getCname());
		}
		return map;
	}
	/*************************************************************************************
	 * Description:根据ID查询tCourseSiteUser
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public TCourseSiteUser findCourseSiteUserById(Integer id) {
		// TODO Auto-generated method stub
		TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserById(id);
		return tCourseSiteUser;
	}
	/*************************************************************************************
	 * Description:删除tCourseSiteUser
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public void deleteCourseSiteUser(TCourseSiteUser tCourseSiteUser) {
		// TODO Auto-generated method stub
		tCourseSiteUserDAO.remove(tCourseSiteUser);
	}
	/*************************************************************************************
	 * Description:根据id删除tCourseSiteUser
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Transactional
	@Override
	public void deleteCourseSiteUserByIds(String ids) {
		// TODO Auto-generated method stub
		String[] ss= ids.split(",");//用“，”将ids分割成id数组
		for (String string : ss) {
			TCourseSiteUser tCourseSiteUser = tCourseSiteUserDAO.findTCourseSiteUserById(Integer.valueOf(string));
			tCourseSiteUserDAO.remove(tCourseSiteUser);
		}
	}
	/*************************************************************************************
	 * Description:根据是否登录来查询课程列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findAllTCourseSiteByUser(User user) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" from TCourseSite t where t.status = '1'");
		if (user == null) {
			sql.append(" and t.isOpen = '1'");
		}
		return tCourseSiteDAO.executeQuery(sql.toString(), 0,-1);
	}
	/*************************************************************************************
	 * Description:根据是否开放来查询课程列表
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findOpenTCourseSite(Integer isOpen, int currpage,
			int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select t from TCourseSite t where t.status = '1' and t.isOpen='"+isOpen+"'");
		return tCourseSiteDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
	}
	/*************************************************************************************
	 * Description:统计开放的课程数量
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public int countOpenTCourseSite(Integer isOpen) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select count(t) from TCourseSite t where t.status = '1' and t.isOpen='"+isOpen+"'");
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;

	}
	/*************************************************************************************
	 * Description: 查看登陆用户的课程（老师和学生身份）
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findMyCourseList(User user) {
		// TODO Auto-generated method stub
		//老师身份
		String hql = "from TCourseSite c where c.status = '1' and c.userByCreatedBy.username = '"+user.getUsername()+"'";
		List<TCourseSite> tCourseSites = tCourseSiteDAO.executeQuery(hql, 0, -1);
		//学生身份
		hql = "select c from TCourseSite c join c.TCourseSiteUsers t where t.user.username = '"+user.getUsername()+"' and t.authority.id = '1'";
		List<TCourseSite> courseSites = tCourseSiteDAO.executeQuery(hql, 0, -1);
		tCourseSites.addAll(courseSites);
		return tCourseSites;
	}
	
	/*************************************************************************************
	 * Description:  根据排课来源及相应主键创建教学课程
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Override
	public TCourseSite createTCourseSite(String type, String courseNo,
			Integer timetableSelfCourseId) {
		// TODO Auto-generated method stub
		TCourseSite tCourseSite = new TCourseSite();
		tCourseSite.setCreatedTime(Calendar.getInstance());
		tCourseSite.setModifiedTime(Calendar.getInstance());
		tCourseSite.setIsOpen(1);//默认开放
		tCourseSite.setStatus(1);
		
		tCourseSite.setType(type);
		//"1"表示数据从教务排课来
		if ("1".equals(type)) {
			SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(courseNo);
			tCourseSite.setTitle(schoolCourse.getCourseName());
			tCourseSite.setMajorNumber(schoolCourse.getSystemMajor12()==null?null:schoolCourse.getSystemMajor12().getMNumber());
			tCourseSite.setSchoolAcademy(schoolCourse.getSchoolAcademy());
			tCourseSite.setSchoolCourseInfo(schoolCourse.getSchoolCourseInfo());
			tCourseSite.setSchoolTerm(schoolCourse.getSchoolTerm());
			tCourseSite.setUserByCreatedBy(schoolCourse.getUserByTeacher());
			tCourseSite.setSchoolCourse(schoolCourse);
		}
		if("2".equals(type)) {
			TimetableSelfCourse timetableSelfCourse = timetableSelfCourseDAO.findTimetableSelfCourseByPrimaryKey(timetableSelfCourseId);
			tCourseSite.setTitle(timetableSelfCourse.getSchoolCourseInfo().getCourseName());
			//tCourseSite.setMajorNumber(timetableSelfCourse.getSchoolCourseInfo().get);
			tCourseSite.setSchoolAcademy(timetableSelfCourse.getSchoolAcademy());
			tCourseSite.setSchoolCourseInfo(timetableSelfCourse.getSchoolCourseInfo());
			tCourseSite.setSchoolTerm(timetableSelfCourse.getSchoolTerm());
			tCourseSite.setUserByCreatedBy(timetableSelfCourse.getUser());
			tCourseSite.setTimetableSelfCourse(timetableSelfCourse);
		}
		tCourseSite = tCourseSiteDAO.store(tCourseSite);
		return tCourseSite;
	}
	
	/*************************************************************************************
	 * Description:  根据排课来源及相应主键创建教学课程(西电模式)
	 * @author：罗璇
	 * @date：2017年3月21日
	 *************************************************************************************/
	@Override
	public TCourseSite createTCourseSiteForXD(String type, String courseNo,
			Integer timetableSelfCourseId) {
		// TODO Auto-generated method stub
		TCourseSite tCourseSite = new TCourseSite();
		tCourseSite.setCreatedTime(Calendar.getInstance());
		tCourseSite.setModifiedTime(Calendar.getInstance());
		tCourseSite.setIsOpen(1);//默认开放
		tCourseSite.setStatus(1);
		
		tCourseSite.setType(type);
		//"1"表示数据从教务排课来
		if ("1".equals(type)) {
			SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(courseNo);
			tCourseSite.setTitle(schoolCourse.getCourseName());
			tCourseSite.setMajorNumber(schoolCourse.getSystemMajor12()==null?null:schoolCourse.getSystemMajor12().getMNumber());
			tCourseSite.setSchoolAcademy(schoolCourse.getSchoolAcademy());
			tCourseSite.setSchoolCourseInfo(schoolCourse.getSchoolCourseInfo());
			tCourseSite.setSchoolTerm(schoolCourse.getSchoolTerm());
			tCourseSite.setUserByCreatedBy(shareService.getUser());//设定当前操作人为创建人
			tCourseSite.setSchoolCourse(schoolCourse);
		}
		if("2".equals(type)) {
			TimetableSelfCourse timetableSelfCourse = timetableSelfCourseDAO.findTimetableSelfCourseByPrimaryKey(timetableSelfCourseId);
			tCourseSite.setTitle(timetableSelfCourse.getSchoolCourseInfo().getCourseName());
			//tCourseSite.setMajorNumber(timetableSelfCourse.getSchoolCourseInfo().get);
			tCourseSite.setSchoolAcademy(timetableSelfCourse.getSchoolAcademy());
			tCourseSite.setSchoolCourseInfo(timetableSelfCourse.getSchoolCourseInfo());
			tCourseSite.setSchoolTerm(timetableSelfCourse.getSchoolTerm());
			tCourseSite.setUserByCreatedBy(timetableSelfCourse.getUser());
			tCourseSite.setTimetableSelfCourse(timetableSelfCourse);
		}
		tCourseSite = tCourseSiteDAO.store(tCourseSite);
		return tCourseSite;
	}
	
	/*************************************************************************************
	 * Description:  根据创建课程添加相应学生
	 * 
	 * @author：黄崔俊
	 * @date：2016-1-7
	 *************************************************************************************/
	@Transactional
	@Override
	public TCourseSite addTCourseSiteUsers(TCourseSite tCourseSite) {
		// TODO Auto-generated method stub
		//课程由教务排课推送而来
		if("1".equals(tCourseSite.getType())){
			SchoolCourse schoolCourse = tCourseSite.getSchoolCourse();
			List<String> teacherList = new ArrayList<String>();
			
			List<String> studentList = new ArrayList<String>();
			for (SchoolCourseDetail schoolCourseDetail : schoolCourse.getSchoolCourseDetails()) {
				for (SchoolCourseStudent schoolCourseStudent : schoolCourseDetail.getSchoolCourseStudents()) {
					if (!teacherList.contains(schoolCourseStudent.getUserByTeacherNumber().getUsername())) {
						teacherList.add(schoolCourseStudent.getUserByTeacherNumber().getUsername());
					}
					if (!studentList.contains(schoolCourseStudent.getUserByStudentNumber().getUsername())) {
						studentList.add(schoolCourseStudent.getUserByStudentNumber().getUsername());
					}
				}
			}
			String insertSql = "INSERT INTO `t_course_site_user` (`site_id`, `username`, `permission`) VALUES ";
			String sql = "";
			for (String teacherUsername : teacherList) {//教师身份
				sql = "('"+tCourseSite.getId()+"', '"+teacherUsername+"', '2'),";
			}
			for (String studentUsername : studentList) {//学生身份
				sql = "('"+tCourseSite.getId()+"', '"+studentUsername+"', '1'),";
			}
			if (!"".equals(sql)) {
				insertSql += sql.substring(0, sql.length()-1);
				entityManager.createNativeQuery(insertSql).executeUpdate();
			}
			
		}
		
		//课程由自主排课推送而来
		if("2".equals(tCourseSite.getType())){
			TimetableSelfCourse timetableSelfCourse = tCourseSite.getTimetableSelfCourse();
			
			List<String> studentList = new ArrayList<String>();
			for (TimetableCourseStudent timetableCourseStudent: timetableSelfCourse.getTimetableCourseStudents()) {
				if (!studentList.contains(timetableCourseStudent.getUser().getUsername())) {
					studentList.add(timetableCourseStudent.getUser().getUsername());
				}
			}
			String insertSql = "INSERT INTO `t_course_site_user` (`site_id`, `username`, `permission`) VALUES ";
			String sql = "";
			
			//教师身份
			sql = "('"+tCourseSite.getId()+"', '"+timetableSelfCourse.getUser().getUsername()+"', '2'),";
			
			for (String studentUsername : studentList) {//学生身份
				sql = "('"+tCourseSite.getId()+"', '"+studentUsername+"', '1'),";
			}
			if (!"".equals(sql)) {
				insertSql += sql.substring(0, sql.length()-1);
				entityManager.createNativeQuery(insertSql).executeUpdate();
			}
			
		}
		return tCourseSiteDAO.findTCourseSiteById(tCourseSite.getId());
	}
	
	/*************************************************************************************
	 * Description:  根据创建课程添加相应学生(西电模式)
	 * @author：罗璇
	 * @date：2017年3月22日
	 *************************************************************************************/
	@Transactional
	@Override
	public TCourseSite addTCourseSiteUsersForXD(TCourseSite tCourseSite) {
		// TODO Auto-generated method stub
		//课程由教务排课推送而来
		if("1".equals(tCourseSite.getType())){
			SchoolCourse schoolCourse = tCourseSite.getSchoolCourse();
			List<String> teacherList = new ArrayList<String>();
			
			List<String> studentList = new ArrayList<String>();
			for (SchoolCourseDetail schoolCourseDetail : schoolCourse.getSchoolCourseDetails()) {
				//添加老师
				for(User currTeacher : schoolCourseDetail.getUsers()){
					if (!teacherList.contains(currTeacher.getUsername())) {
						teacherList.add(currTeacher.getUsername());
					}
				}
				for (SchoolCourseStudent schoolCourseStudent : schoolCourseDetail.getSchoolCourseStudents()) {
					
					if (!studentList.contains(schoolCourseStudent.getUserByStudentNumber().getUsername())) {
						studentList.add(schoolCourseStudent.getUserByStudentNumber().getUsername());
					}
				}
			}
			String insertSql = "INSERT INTO `t_course_site_user` (`site_id`, `username`, `permission`) VALUES ";
			String sql = "";
			for (String teacherUsername : teacherList) {//教师身份
				sql += "('"+tCourseSite.getId()+"', '"+teacherUsername+"', '2'),";
			}
			for (String studentUsername : studentList) {//学生身份
				sql += "('"+tCourseSite.getId()+"', '"+studentUsername+"', '1'),";
			}
			if (!"".equals(sql)) {
				insertSql += sql.substring(0, sql.length()-1);
				entityManager.createNativeQuery(insertSql).executeUpdate();
			}	
		}	
		//课程由自主排课推送而来
		if("2".equals(tCourseSite.getType())){
			TimetableSelfCourse timetableSelfCourse = tCourseSite.getTimetableSelfCourse();
			
			List<String> studentList = new ArrayList<String>();
			for (TimetableCourseStudent timetableCourseStudent: timetableSelfCourse.getTimetableCourseStudents()) {
				if (!studentList.contains(timetableCourseStudent.getUser().getUsername())) {
					studentList.add(timetableCourseStudent.getUser().getUsername());
				}
			}
			String insertSql = "INSERT INTO `t_course_site_user` (`site_id`, `username`, `permission`) VALUES ";
			String sql = "";
			
			//教师身份
			sql = "('"+tCourseSite.getId()+"', '"+timetableSelfCourse.getUser().getUsername()+"', '2'),";
			
			for (String studentUsername : studentList) {//学生身份
				sql = "('"+tCourseSite.getId()+"', '"+studentUsername+"', '1'),";
			}
			if (!"".equals(sql)) {
				insertSql += sql.substring(0, sql.length()-1);
				entityManager.createNativeQuery(insertSql).executeUpdate();
			}
			
		}
		return tCourseSiteDAO.findTCourseSiteById(tCourseSite.getId());
	}
	/*************************************************************************************
	 * Description:  根据创建课程添加相应实验项目(西电模式)
	 * @throws ParseException 
	 * @author：戴昊宇
	 * @date：2017年7月03日
	 *************************************************************************************/
	@Override
	public TCourseSite addOperationForXD(String type, String courseNo,
			Integer timetableSelfCourseId,HttpServletRequest request,TCourseSite tCourseSite2) throws ParseException {
		// TODO Auto-generated method stub
	       
		SchoolCourse schoolCourse = schoolCourseDAO.findSchoolCourseByCourseNo(courseNo);
		SchoolCourseInfo courseInfo = schoolCourse.getSchoolCourseInfo();
		//"1"表示数据从教务排课来
		    String hql = "from OperationItem c where c.schoolCourseInfo.courseNumber like '"+courseInfo.getCourseNumber()+"'";
			List<OperationItem> operationItem = operationItemDAO.executeQuery(hql, 0, -1);
			String sql="from TCourseSite c where c.schoolCourseInfo.courseNumber like '"+courseInfo.getCourseNumber()+"'";
			List<TCourseSite> tCourseSite = tCourseSiteDAO.executeQuery(sql, 0, -1);
			int id= -1;
			if(tCourseSite.size()>0)
			{
				
				id =tCourseSite.get(0).getId();
			}
			List<TExperimentSkill> expList=new ArrayList<TExperimentSkill>();
			for(OperationItem operation :operationItem)
			{
				TExperimentSkill tExperimentSkill = new TExperimentSkill();
				tExperimentSkill.setExperimentName(operation.getLpName());
				tExperimentSkill.setExperimentNo(operation.getLpCodeCustom());
				tExperimentSkill.setExperimentIsopen(1);
				tExperimentSkill.setExperimentGoal(operation.getBasicRequired());
				tExperimentSkill.setCreatedTime(operation.getCreatedAt());
				tExperimentSkill.setSort(operation.getOrderNumber());
				tExperimentSkill.setSiteId(tCourseSite2.getId());
				tExperimentSkill.setStartdate(Calendar.getInstance());
				tExperimentSkill.setDuedate(Calendar.getInstance());
				tExperimentSkill.setWeight(new BigDecimal(1));
				//设置chapterid
				WkChapter chapter = new WkChapter();
				chapter.setName(tExperimentSkill.getExperimentName());//chapter名称
				chapter.setSeq(tExperimentSkill.getSort());//chapter排序
				chapter.setTCourseSite(tCourseSite2);//chapter所属站点
				chapter.setType(200);//chapter类型为实验技能
				chapter = wkChapterService.saveChapter(chapter);//保存chapter
				//实验指导书文件夹
				WkFolder quideFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验指导书",201);
				//实验图片文件夹
				WkFolder imageFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验图片",202);
				//实验视频文件夹
				WkFolder videoFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验视频",203);
				//实验工具文件夹
				WkFolder toolFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验工具",204);
				//保存实验指导书
				tExperimentSkillService.saveTExperimentSkillWkUpload(quideFolder,"experimentalQuidesList",request);
				//保存实验图片
				tExperimentSkillService.saveTExperimentSkillWkUpload(imageFolder,"experimentalImagesList",request);
				//保存实验视频
				tExperimentSkillService.saveTExperimentSkillWkUpload(videoFolder,"experimentalVideosList",request);
				//保存实验工具
				tExperimentSkillService.saveTExperimentSkillWkUpload(toolFolder,"experimentalToolsList",request);
				//保存对应章节id
				tExperimentSkill.setChapterId(chapter.getId());
				//保存实验技能
				tExperimentSkill = tExperimentSkillDAO.store(tExperimentSkill);
				expList.add(tExperimentSkill);
			}
			for(TExperimentSkill exp:expList){
				//保存一个实验报告
				TAssignment tAssignment = tExperimentSkillService.saveReportTAssignment(exp, request);
				//更新chapterid
				//exp.setChapterId(tAssignment.getW)
				//根据实验报告创建成绩册
				tGradebookService.createGradebook(tCourseSite2.getId(), tAssignment);
			}
			
			return null;
		  
	}
	/*************************************************************************************
	 * Description:  复制课程资源
	 * 
	 * @author： 裴继超
	 * @date：2016-6-15
	 *************************************************************************************/
	@Override
	public TCourseSite copyTCourseSite(String allIdsString,Integer tCourseSiteId,
			HttpServletRequest request) {
		
		TCourseSite tCourseSite = findCourseSiteById(tCourseSiteId);
		
		allIdsString = allIdsString.substring(1,allIdsString.length()); 
		
		String[] chapters = allIdsString.split("!");
		System.out.println(allIdsString);
		for(String chapterString:chapters){
			String[] lessons = chapterString.split(";");
			int newWkChapterId = 0;
			//System.out.println("c_"+chapter);
			for(String lessonString:lessons){
				String[] folders = lessonString.split(",");
				int newWkLessonId = 0;
				//System.out.println("l_"+lesson);
				for(String folderString:folders){
					if(folderString.contains("chapter")){
						int chapterId = Integer.parseInt(folderString.substring(7,folderString.length()));
						newWkChapterId = wkChapterService.copyWkChapter(tCourseSiteId,chapterId);
					}
					if(folderString.contains("lesson")){
						int lessonId = Integer.parseInt(folderString.substring(6,folderString.length()));
						newWkLessonId = wkLessonService.copyWkLesson(tCourseSiteId,lessonId,newWkChapterId);
					}
					if(folderString.contains("folder")){
						int folderId = Integer.parseInt(folderString.substring(6,folderString.length()));
						wkFolderService.copyWkFolder(tCourseSiteId,folderId,newWkChapterId,newWkLessonId,request);
					}
					
					
					System.out.println("f_"+folderString);
				}
			}
		}
		
		return tCourseSite;
	}

	/***********************************************************************************************
	 * description:课程-查询查找课程
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	@Override
	public List<TCourseSite> findTCourseSitesByTCourseSite(
			TCourseSite tCourseSite, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select t from TCourseSite t where t.status = '1' and t.isOpen = '1' ");
		if (tCourseSite!=null) {
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}else{//学期查询条件为空，则默认显示当前所在学期数据
				SchoolTerm currTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
				sbf.append(" and t.schoolTerm.id ='"+currTerm.getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
			}
		}
		sbf.append(" order by t.id desc");
		return tCourseSiteDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
	}

	/***********************************************************************************************
	 * description:课程-查询查找课程结果数量
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	@Override
	public int getTCourseSiteTotalRecordsByTCourseSite(TCourseSite tCourseSite,
			Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select count(t) from TCourseSite t where t.status = '1' and t.isOpen = '1' ");
		if (tCourseSite!=null) {
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}else{//学期查询条件为空，则默认显示当前所在学期数据
				SchoolTerm currTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
				sbf.append(" and t.schoolTerm.id ='"+currTerm.getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
			}
		}
		sbf.append(" order by t.id desc");
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;
	}
	
	/*************************************************************************************
	 * Description:课程-查找和我相关的课程
	 * 
	 * @author： 裴继超
	 * @date：2016-7-20
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findMyTCourseSitesByTCourseSite(
			TCourseSite tCourseSite, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub	
		StringBuffer sbf=new StringBuffer("select t from TCourseSite t where t.status = '1' and t.isOpen = '1' ");
		if (tCourseSite!=null) {
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				if(tCourseSite.getUserByCreatedBy().getCname()!=null&&tCourseSite.getUserByCreatedBy().getCname()!=""){
					sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
				}
			}
			User user = shareService.getUser();
			sbf.append(" and ( t.userByCreatedBy.username LIKE '"+user.getUsername()+"'" +
					" or t.id in (select u.TCourseSite.id from TCourseSiteUser u WHERE u.user.username LIKE '"+user.getUsername()+"'))");
	
		}
		sbf.append(" order by t.id desc");
		return tCourseSiteDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
	}

	/*************************************************************************************
	 * Description:课程-查找和我相关的课程数量
	 * 
	 * @author： 裴继超
	 * @date：2016-7-20
	 *************************************************************************************/
	@Override
	public int getMyTCourseSiteTotalRecordsByTCourseSite(TCourseSite tCourseSite,
			Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select count(t) from TCourseSite t where t.status = '1' and t.isOpen = '1' ");

		if (tCourseSite!=null) {
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				if(tCourseSite.getUserByCreatedBy().getCname()!=null&&tCourseSite.getUserByCreatedBy().getCname()!=""){
					sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
				}
			}
			User user = shareService.getUser();
			sbf.append(" and ( t.userByCreatedBy.username LIKE '"+user.getUsername()+"'" +
					" or t.id in (select u.TCourseSite.id from TCourseSiteUser u WHERE u.user.username LIKE '"+user.getUsername()+"'))");
		}
		sbf.append(" order by t.id desc");
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;
	}
	
	
	/*************************************************************************************
	 * Description:上传文件
	 * 
	 * @author： 裴继超
	 * @date：2016-7-14
	 *************************************************************************************/
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void uploadImageForSite(HttpServletRequest request, String path, Integer type, int id) {
		// TODO Auto-generated method stub
		String sep = System.getProperty("file.separator");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 构建文件保存的目录* */
		// String PathDir = "/upload/"+ dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String RealPathDir = request.getSession().getServletContext().getRealPath("/") + sep + path;
		// System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
		/** 根据真实路径创建目录* */
		File SaveFile = new File(RealPathDir);
		if (!SaveFile.exists()) {
			SaveFile.mkdirs();
		}
		Iterator fileNames = multipartRequest.getFileNames();
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			MultipartFile file = multipartRequest.getFile(filename);
			String fileTrueName = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					file.transferTo(new File(RealPathDir + sep + fileTrueName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (type==1) {
					TCourseSite tCourseSite = findCourseSiteById(id);
					tCourseSite.setSiteImage("/upload/tcoursesite/site"+id+"/courseInfo/"+fileTrueName);
					tCourseSiteDAO.store(tCourseSite);
				} else if (type==2) {
					TCourseSite tCourseSite = findCourseSiteById(id);
					tCourseSite.setTeacherImage("/upload/tcoursesite/site"+id+"/courseInfo/"+fileTrueName);
					tCourseSiteDAO.store(tCourseSite);
				}

			}
		}
		MultipartFile multipartFile = multipartRequest.getFile("file");

		/** 判断文件不为空 */
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			MultipartFile file = multipartRequest.getFile(filename);
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			/** 使用UUID生成文件名称* */
			String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件* */
			String fileName = RealPathDir + File.separator + logImageName;
			File thisFile = new File(RealPathDir);
			if (!thisFile.exists()) {
				thisFile.mkdirs();
			} else {
				try {
					file.transferTo(new File(fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	/*************************************************************************************
	 * Description:课程复制-查找当前学期及以后的课程
	 * 
	 * @author： 裴继超
	 * @date：2016-8-11
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findTCourseSiteByTerm() {
		//获取当前时间 
		Calendar now=Calendar.getInstance();
		//获取当前及以后的学期
		List<SchoolTerm> terms = shareService.getNowAndAfterSchoolTerm(now);
		String termIds = "";
		for(SchoolTerm t:terms){
			termIds += t.getId()+",";
		}
		if(termIds.length()>0){
			termIds = termIds.substring(0,termIds.length()-1);
		}else{
			List<TCourseSite> ss = new ArrayList(); //新建集合
			return ss;
		}
		
		StringBuffer allsites=new StringBuffer(" from TCourseSite t where t.status = '1'");
		allsites.append(" and t.schoolTerm.id in (" + termIds +") ");
		return tCourseSiteDAO.executeQuery(allsites.toString(), 0,-1);
	}

	/*************************************************************************************
	 * Description:查询记录数量
	 * 
	 * @author： 储俊
	 * @date：2016-8-23
	 *************************************************************************************/
	public int getSelectCourseTotalRecords() {
		String sql = "select count(t) from TCourseSite t where t.status=1";
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
			return result;
		}
	
	/*************************************************************************************
	 * Description:列出所有课程
	 * 
	 * @author： 储俊
	 * @date：2016-8-23
	 *************************************************************************************/
	@Override
	public List<TCourseSite> findTCourseSite(int currpage, int pageSize) {
		String sql = "from TCourseSite t where t.status=1";
		return tCourseSiteDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
	}
	/**************************************************************************
	 * Description:删除课程
	 * 
	 * @author：储俊
	 * @date ：2016-8-24
	 **************************************************************************/
	@Override
	public void delete(TCourseSite tCourseSite) {
		// TODO Auto-generated method stub
		tCourseSite.setStatus(0);
		tCourseSiteDAO.store(tCourseSite);
	}
	
	/**************************************************************************
	 * description:新建课程-添加助教{根据输入内容（学生名称）查询}
	 * 
	 * @author:陈乐为
	 * @date：2016-8-29
	 **************************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, List> getCEduTypeDataSetMap(String results,int currpage, int pageSize) {

		// 创建一个新的map对象；
		Map<String, List> josnMap = new HashMap<String, List>();
		List<User> users = this.findUsersByQuery(results,currpage,pageSize);
		// 创建一个新的数组对象；
		List list = new ArrayList();
		// 如果users的大小大于0；
		if (users.size() > 0) {
			// 循环users；
			for (User user : users) {
				// 创建一个新的map对象给变量userMap；
				Map<String, Object> userMap = new HashMap<String, Object>();
				// 获取user的userName赋值给userName；
				String userName = user.getUsername();
				//财政分类
				String cName = user.getCname();
				// 将userName映射给userName；
				userMap.put("userName", userName);
				// 将cName赋值给cName；
				userMap.put("cName", cName);
				// 将添加的href映射给do；
				userMap.put("do", "<a href='#' onclick='searchUserSelected(\"" + userName + "\",\"" + cName + "\")'>添加</a>");
				// 将userMap添加到list中；
				list.add(userMap);
			}
		}
		josnMap.put("rows", list);
		return josnMap;
	}
	
	/**************************************************************************
	 * description：新建课程-添加助教{根据学生名称查询}
	 * 
	 * @author：陈乐为
	 * @date：2016-8-29
	 **************************************************************************/
	public List<User> findUsersByQuery(String cName,int currpage, int pageSize) {
		String sql = "where 1=1 and u.cname like'%" + cName + "%')";
		// 如果输入查询条件为空，查询结果也设置为空
		if ("".equals(cName)) {
			sql = "where 1=1 ";
		}
		// 
		StringBuffer sb = new StringBuffer("select u from User u " + sql);
		// 根据名称查找
		List<User> users = userDAO.executeQuery(sb.toString(), (currpage-1)*pageSize,pageSize);
		return users;
	}
	
	/*************************************************************************************
	 * description:新建课程-添加助教{返回根据足交名称查找的结果数量}
	 *
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	public int countUsersByQuery(String cName) {
		String sql = "where 1=1 and u.cname like'%" + cName + "%')";
		// 如果输入查询条件为空，查询结果也设置为空
		if ("".equals(cName)) {
			sql = "where 1=1 ";
		}
		// 
		StringBuffer sb = new StringBuffer("select count(u) from User u " + sql);
		// 根据名称查找
		int result = ((Long)userDAO.createQuerySingleResult(sb.toString()).getSingleResult()).intValue();
		//List<SchoolCourseInfo> users = schoolCourseInfoDAO.executeQuery(sb.toString(),-1,-1);
		return result;
	}
	
	/*************************************************************************************
	 * description:新建课程-添加课程{根据课程名称查找}
	 * 
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, List> getCourseNumberSetMap(String results,int currpage, int pageSize) {
		
		// 创建一个新的map对象；
		Map<String, List> josnMap = new HashMap<String, List>();
		List<SchoolCourseInfo> schoolCourseInfos = this.getCourseNumberByQuery(results,currpage,pageSize);
		// 创建一个新的数组对象；
		List list = new ArrayList();
		// 如果schoolCourseInfos的大小大于0；
		if (schoolCourseInfos.size() > 0) {
			// 循环schoolCourseInfos；
			for (SchoolCourseInfo schoolCourseInfo : schoolCourseInfos) {
				// 创建一个新的map对象给变量schoolCourseInfoMap；
				Map<String, Object> schoolCourseInfoMap = new HashMap<String, Object>();
				// 获取schoolCourseInfo的schoolCourseInfoName赋值给schoolCourseInfoName；
				String courseNumber = schoolCourseInfo.getCourseNumber();
				//财政分类
				String courseName = schoolCourseInfo.getCourseName();
				// 将courseNumber映射给courseNumber；
				schoolCourseInfoMap.put("courseNumber", courseNumber);
				// 将courseName赋值给courseName；
				schoolCourseInfoMap.put("courseName", courseName);
				// 将添加的href映射给do；
				schoolCourseInfoMap.put("do", "<a href='#' onclick='searchCourseNumberSelected(\"" + courseNumber + "\",\"" + courseName + "\")'>添加</a>");
				// 将userMap添加到list中；
				list.add(schoolCourseInfoMap);
			}
		}
		josnMap.put("rows", list);
		return josnMap;
	}
	
	/*************************************************************************************
	 * description:新建课程-添加课程{根据课程名称查找}
	 *
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	public List<SchoolCourseInfo> getCourseNumberByQuery(String courseName,int currpage, int pageSize) {
		String sql = "where 1=1 and s.courseName like'%" + courseName + "%')";
		// 如果输入查询条件为空，查询结果也设置为空
		if ("".equals(courseName)) {
			sql = "where 1=1 ";
		}
		// 
		StringBuffer sb = new StringBuffer("select s from SchoolCourseInfo s " + sql);
		// 根据名称查找
		List<SchoolCourseInfo> schoolCourseInfos = schoolCourseInfoDAO.executeQuery(sb.toString(), (currpage-1)*pageSize,pageSize);
		return schoolCourseInfos;
	}
	
	/*************************************************************************************
	 * description:新建课程-添加课程{返回根据课程名称查找的结果数量}
	 * 
	 * @author:于侃
	 * @date：2016-8-31
	 *************************************************************************************/
	public int countCourseNumberByQuery(String courseName) {
		String sql = "where 1=1 and s.courseName like'%" + courseName + "%')";
		// 如果输入查询条件为空，查询结果也设置为空
		if ("".equals(courseName)) {
			sql = "where 1=1 ";
		}
		// 
		StringBuffer sb = new StringBuffer("select count(s) from SchoolCourseInfo s " + sql);
		// 根据名称查找
		int result = ((Long)schoolCourseInfoDAO.createQuerySingleResult(sb.toString()).getSingleResult()).intValue();
		//List<SchoolCourseInfo> schoolCourseInfos = schoolCourseInfoDAO.executeQuery(sb.toString(),-1,-1);
		return result;
	}
	
	/***********************************************************************************************
	 * description:课程--查询查找课程(无论是否open)
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public List<TCourseSite> findAllTCourseSitesByTCourseSite(
			TCourseSite tCourseSite, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select t from TCourseSite t where t.status = '1' ");
		if (tCourseSite!=null) {
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
			}
		}
		sbf.append(" order by t.id desc");
		return tCourseSiteDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
	}

	/***********************************************************************************************
	 * description:课程--查询查找课程结果数量(无论是否open)
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public int getAllTCourseSiteTotalRecordsByTCourseSite(TCourseSite tCourseSite,
			Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select count(t) from TCourseSite t where t.status = '1' ");
		if (tCourseSite!=null) {
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
			}
		}
		sbf.append(" order by t.id desc");
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;
	}
	
	/***********************************************************************************************
	 * description:课程--查询学生id是否存在于该课程中
	 * 
	 * @author:于侃
	 * @date:2016-8-31
	 ***********************************************************************************************/
	public int isUserNameInCourseSite(int tCourseSiteId,String id) {
		StringBuffer sbf=new StringBuffer("select count(t) from TCourseSiteUser t where t.role = '1' ");
		sbf.append("and t.TCourseSite.id = '"+tCourseSiteId+"' and t.user.username = '"+id+"'");
		int result = ((Long)tCourseSiteUserDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;
	}
	
	/**************************************************************************
	 * Description:教学平台-获取权限
	 * 
	 * @author：裴继超
	 * @date ：2017-1-3
	 **************************************************************************/
	public Integer getFlagByUserAndSite(User user,TCourseSite tCourseSite,
			HttpSession httpSession){
		 //角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = -1;
		if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSite.getId(),user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){//学生权限
			if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSite.getId(),user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else{//学生身份
				flag = 0;
			}
		}
		//超级管理员权限
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("COURSETEACHER")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 2;
		}
		//教师身份 查看学生视角
		if(httpSession.getAttribute("viewflag")!=null){
			if((Integer)httpSession.getAttribute("viewflag") == 2){
				flag = 0;
			}
		}
		return flag;
	}

	/***********************************************************************************************
	 * description:课程--站点查询根据id，站点课程组，站点名
	 * 
	 * @author:李雪腾
	 * @date:2017-7-10
	 ***********************************************************************************************/
	@Override
	public int getTCourseSiteTotalRecordsByTCourseSiteWithMore(
			TCourseSite tCourseSite, Integer currpage, int pageSize,Integer allCourseFlag,String username) {
		StringBuffer sbf=new StringBuffer("select count(distinct t) from TCourseSite t,TCourseSiteUser u where t.id = u.TCourseSite.id and t.status = '1' and t.isOpen = '1' ");
		if (tCourseSite!=null) {
			if(tCourseSite.getId()!=null&&!"".equals(tCourseSite.getId())){
				sbf.append(" and t.id='"+tCourseSite.getId()+"'");
			}
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}else{//学期查询条件为空，则默认显示当前所在学期数据
				SchoolTerm currTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
				sbf.append(" and t.schoolTerm.id ='"+currTerm.getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
			}
			if(tCourseSite.getSchoolCourse()!=null&&!"".equals(tCourseSite.getSchoolCourse())){
				if(tCourseSite.getSchoolCourse().getCourseNo()!=null&&!"".equals(tCourseSite.getSchoolCourse().getCourseNo())){
					sbf.append(" and t.schoolCourse.courseNo like '"+tCourseSite.getSchoolCourse().getCourseNo()+"'");
				}
			}
			if(tCourseSite.getSchoolCourseInfo()!=null&&!"".equals(tCourseSite.getSchoolCourseInfo())){
				if(tCourseSite.getSchoolCourseInfo().getCourseNumber()!=null&&!"".equals(tCourseSite.getSchoolCourseInfo().getCourseNumber())){
					sbf.append(" and t.schoolCourseInfo.courseNumber like '"+tCourseSite.getSchoolCourseInfo().getCourseNumber()+"'");
				}
			}
			if(allCourseFlag==null || allCourseFlag!=1){
				sbf.append(" and u.user.username = '" + username + "'");
			}
			
		}
		sbf.append(" order by t.id desc");
		int result = ((Long)tCourseSiteDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;
	}
	/***********************************************************************************************
	 * description:课程-查询查找课程
	 * 
	 * @author:李雪腾
	 * @date:2017-7-10
	 ***********************************************************************************************/
	@Override
	public List<TCourseSite> findTCourseSitesByTCourseSiteWithMore(
			TCourseSite tCourseSite, Integer currpage, int pageSize,Integer allCourseFlag,String username) {
		StringBuffer sbf=new StringBuffer("select distinct t from TCourseSite t,TCourseSiteUser u where t.id = u.TCourseSite.id and t.status = '1' and t.isOpen = '1' ");
		if (tCourseSite!=null) {
			if(tCourseSite.getId()!=null&&!"".equals(tCourseSite.getId())){
				sbf.append(" and t.id='"+tCourseSite.getId()+"'");
			}
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sbf.append(" and t.title like '%"+tCourseSite.getTitle()+"%'");
			}
			if (tCourseSite.getSchoolTerm()!=null&&tCourseSite.getSchoolTerm().getId()!=null&&!"".equals(tCourseSite.getSchoolTerm().getId())) {
				sbf.append(" and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'");
			}else{//学期查询条件为空，则默认显示当前所在学期数据
				SchoolTerm currTerm = shareService.getBelongsSchoolTerm(Calendar.getInstance());
				sbf.append(" and t.schoolTerm.id ='"+currTerm.getId()+"'");
			}
			if (tCourseSite.getUserByCreatedBy()!=null){
				sbf.append(" and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'");
			}
			if(tCourseSite.getSchoolCourse()!=null&&!"".equals(tCourseSite.getSchoolCourse())){
				if(tCourseSite.getSchoolCourse().getCourseNo()!=null&&!"".equals(tCourseSite.getSchoolCourse().getCourseNo())){
					sbf.append(" and t.schoolCourse.courseNo like '"+tCourseSite.getSchoolCourse().getCourseNo()+"'");
				}
			}
			if(tCourseSite.getSchoolCourseInfo()!=null&&!"".equals(tCourseSite.getSchoolCourseInfo())){
				if(tCourseSite.getSchoolCourseInfo().getCourseNumber()!=null&&!"".equals(tCourseSite.getSchoolCourseInfo().getCourseNumber())){
					sbf.append(" and t.schoolCourseInfo.courseNumber like '"+tCourseSite.getSchoolCourseInfo().getCourseNumber()+"'");
				}
			}
			if(allCourseFlag==null || allCourseFlag!=1){
				sbf.append(" and u.user.username = '" + username + "'");
			}
			
		}
		sbf.append(" order by t.id desc");
		return tCourseSiteDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
	}

	/*************************************************************************************
	 * Description:知识模块多文件上传
	 * 
	 * @author： 马帅
	 * @date：2017-8-11
	 *************************************************************************************/
	@Override
	public Map<String,String> uploadMultiFile(HttpServletRequest request, String path,
			Integer type, Integer siteId) {
		// TODO Auto-generated method stub
		//List<Object> list = new ArrayList<Object>();
		if(type==203){
			type=0;
		}
		Map<String,String> map = new HashMap<String, String>();
		String sep = System.getProperty("file.separator");
		//上传文件的类型
		String name = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 构建文件保存的目录* */
		// String PathDir = "/upload/"+ dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String RealPathDir = request.getSession().getServletContext().getRealPath("/") + path;
		// //System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
		/** 根据真实路径创建目录* */
		File SaveFile = new File(RealPathDir);
		if (!SaveFile.exists()) {
			SaveFile.mkdirs();
		}		
		Iterator fileNames = multipartRequest.getFileNames();
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			List<MultipartFile> files = multipartRequest.getFiles(filename);
			for (MultipartFile file : files){
				String fileTrueName = file.getOriginalFilename();			
				//取得上传文件后缀
				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
				// 使用UUID生成文件名称
				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称	
				if (!file.isEmpty()) {
					try {
						file.transferTo(new File(RealPathDir + sep + logImageName));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (type==1) {
						name = "image";
						WkUpload wkupload = new WkUpload();
						String size = "";
						long length = file.getSize();
						if (length > 1024 * 1024) {
							int a = (int) ((length / 1000000f) * 100);
							size = a / 100f + " MB";
						} else if (length > 1024) {
							int a = (int) ((length / 1000f) * 100);
							size = a / 100f + " KB";
						} else {
							size = size + " bytes";
						}
						
						wkupload.setSize(size);
						wkupload.setUrl("/upload/tcoursesite/site"+siteId+"/"+name+"/"+logImageName);
						wkupload.setName(fileTrueName);
						wkupload.setUpTime(Calendar. getInstance());
						wkupload.setUser(shareService.getUserDetail());
						wkupload.setType(101);
						//wkupload.setSiteId(siteId);
						//list.add(wkupload.getName());
						int id = saveUpload(wkupload);
						map.put(id+"", wkupload.getName());
					} else if (type==2) {
						name = "document";
						WkUpload wkupload = new WkUpload();
						String size = "";
						long length = file.getSize();
						if (length > 1024 * 1024) {
							int a = (int) ((length / 1000000f) * 100);
							size = a / 100f + " MB";
						} else if (length > 1024) {
							int a = (int) ((length / 1000f) * 100);
							size = a / 100f + " KB";
						} else {
							size = size + " bytes";
						}
						
						wkupload.setSize(size);
						wkupload.setUrl("/upload/tcoursesite/site"+siteId+"/"+name+"/"+logImageName);
						wkupload.setName(fileTrueName);
						wkupload.setUpTime(Calendar. getInstance());
						wkupload.setUser(shareService.getUserDetail());
						wkupload.setType(101);
						/*wkupload.setSiteId(siteId);*/
						int id = saveUpload(wkupload);
						map.put(id+"", wkupload.getName());
					} else if (type==0){
						name = "voide";
						WkUpload wkupload = new WkUpload();
						String size = "";
						long length = file.getSize();
						if (length > 1024 * 1024) {
							int a = (int) ((length / 1000000f) * 100);
							size = a / 100f + " MB";
						} else if (length > 1024) {
							int a = (int) ((length / 1000f) * 100);
							size = a / 100f + " KB";
						} else {
							size = size + " bytes";
						}
						
						wkupload.setSize(size);
						wkupload.setUrl("/upload/tcoursesite/site"+siteId+"/"+name+"/"+logImageName);
						wkupload.setName(fileTrueName);
						wkupload.setUpTime(Calendar. getInstance());
						wkupload.setUser(shareService.getUserDetail());
						wkupload.setType(101);
						/*wkupload.setSiteId(siteId);*/
						int id = saveUpload(wkupload);
						map.put(id+"", wkupload.getName());
					}
				}
			}
		}
		MultipartFile multipartFile = multipartRequest.getFile("file");

		/** 判断文件不为空 */
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			MultipartFile file = multipartRequest.getFile(filename);
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			/** 使用UUID生成文件名称* */
			String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件* */
			String fileName = RealPathDir + "/" + logImageName;
			File thisFile = new File(RealPathDir);
			if (!thisFile.exists()) {
				thisFile.mkdirs();
			} else {
				try {
					file.transferTo(new File(fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return map;
	}	

	/**************************************************************************
	 * Description:保存文件 
	 * 
	 * @author：于侃
	 * @date ：2016-09-13
	 **************************************************************************/
	@Override
	public int saveUpload(WkUpload upload) {
		//设置上传时间为当前时间
		upload.setUpTime(Calendar.getInstance());
		//获取当前登录人
		User user = shareService.getUserDetail();
		upload.setUser(user);
		wkUploadDAO.flush();
		//保存文件
		WkUpload up=wkUploadDAO.store(upload);
		//刷新数据库
		wkUploadDAO.flush();
		//返回上传文件的id
		return up.getId();
	}
	
	/*************************************************************************************
	 * Description:分页查询课程所属学生列表
	 * 
	 * @author：张佳鸣
	 * @date：2017-12-04
	 *************************************************************************************/
	public List<TCourseSiteUser> findTCourseSiteStudentsWithMore(Integer cid,int currPage,int pageSize){
		
		// TODO Auto-generated method stub
	    User user = shareService.getUser();
	    String hql = "from TCourseSiteUser c where c.TCourseSite.id = '"+cid+"' and c.authority.id = '1'";
		if (user.getAuthorities().toString().contains("STUDENT")) {//学生身份则只查自己
			hql += " and c.user.username = '"+ user.getUsername() +"'";
		}
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(hql, (currPage-1)*pageSize, pageSize);
		return tCourseSiteUsers;
	}
	
	/***********************************************************************************************
	 * description:实验项目
	 * 
	 * @author:马帅
	 * @date:2017-8-24
	 ***********************************************************************************************/
	@Override
	public WkChapter findWkChapterBySiteId(Integer id) {
		StringBuffer sbf=new StringBuffer("select c from WkChapter c where c.name='实验项目' and c.type=1 ");
		List<WkChapter> wkChapter=wkChapterDAO.executeQuery(sbf.toString(), 0,-1);
		if(wkChapter.size()>0){
			return wkChapter.get(0);
		}else{
			return null;
		}
	}
}