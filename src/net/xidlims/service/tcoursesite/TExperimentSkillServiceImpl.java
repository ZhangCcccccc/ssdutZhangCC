package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.TAssignmentAnswerAssignDAO;
import net.xidlims.dao.TAssignmentControlDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.TDiscussDAO;
import net.xidlims.dao.TExperimentLabDeviceDAO;
import net.xidlims.dao.TExperimentLabRoomDAO;
import net.xidlims.dao.TExperimentSkillDAO;
import net.xidlims.dao.TExperimentSkillUserDAO;
import net.xidlims.dao.TGradeObjectDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.dao.WkChapterDAO;
import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.WkUploadDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswerAssign;
import net.xidlims.domain.TAssignmentControl;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.TExperimentLabDevice;
import net.xidlims.domain.TExperimentLabRoom;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.TExperimentSkillUser;
import net.xidlims.domain.TGradeObject;
import net.xidlims.domain.TGradeRecord;
import net.xidlims.domain.User;
import net.xidlims.domain.WkChapter;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.teaching.TAssignmentGradingService;
@Service("TExperimentSkillService")
public class TExperimentSkillServiceImpl implements TExperimentSkillService {

	@Autowired
	private TExperimentSkillDAO tExperimentSkillDAO;
	@Autowired
	private SchoolClassesDAO schoolClassesDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private WkChapterService wkChapterService;
	@Autowired
	private WkFolderService wkFolderService;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private TExperimentLabRoomDAO tExperimentLabRoomDAO;
	@Autowired
	private TExperimentLabDeviceDAO tExperimentLabDeviceDAO;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private WkUploadDAO wkUploadDAO;
	@Autowired
	private WkFolderDAO wkFolderDAO;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private TDiscussDAO tDiscussDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private TAssignmentControlDAO tAssignmentControlDAO;
	@Autowired
	private WkChapterDAO wkChapterDAO;
	@Autowired
	private TAssignmentAnswerAssignDAO tAssignmentAnswerAssignDAO;
	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TGradebookService tGradebookService;
	@Autowired
	private TGradeObjectDAO tGradeObjectDAO;
	@Autowired
	private TExperimentSkillUserDAO tExperimentSkillUserDAO;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	
	/**************************************************************************
	 * Description:查询对应站点下的讨论数量
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	@Override
	public int getCountTExperimentSkillList(String tCourseSiteId) {
		//查询对应站点下的讨论数量
		StringBuffer sql = new StringBuffer("select count(*) from TExperimentSkill c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		return ((Long) tExperimentSkillDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/**************************************************************************
	 * Description:查询实验技能（通过站点id）
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	@Override
	public List<TExperimentSkill> findTExperimentSkillListBySiteId(Integer tCourseSiteId, Integer currpage,
			int pageSize,String type){
		//查询讨论
		StringBuffer sql = new StringBuffer("from TExperimentSkill c where c.siteId = "+ tCourseSiteId);
		sql.append(" order by c.sort asc");
		return tExperimentSkillDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}


	/**************************************************************************
	 * Description:保存实验技能
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-9-18
	 **************************************************************************/
	@Override
	public TExperimentSkill saveTExperimentSkill(TExperimentSkill tExperimentSkill,
			HttpServletRequest request) throws ParseException {
		//获取当前课程站点
		TCourseSite tCourseSite = (TCourseSite)request.getSession().getAttribute("currsite");
		//新建一个实验技能对应的chapter
		WkChapter chapter = new WkChapter();
		if(tExperimentSkill.getId()!=null&&tExperimentSkill.getId()!=-1){
			chapter = wkChapterService.findChapterByPrimaryKey(tExperimentSkill.getChapterId());
			if(chapter==null){
				chapter = new WkChapter();
			}
			chapter.setName(tExperimentSkill.getExperimentName());//chapter名称
			chapter.setSeq(tExperimentSkill.getSort());//chapter排序
			chapter.setTCourseSite(tCourseSite);//chapter所属站点
			chapter.setType(200);//chapter类型为实验技能
			chapter = wkChapterService.saveChapter(chapter);//保存chapter
		}else{//如果为 编辑则获取原chapter
			chapter.setName(tExperimentSkill.getExperimentName());//chapter名称
			chapter.setSeq(tExperimentSkill.getSort());//chapter排序
			chapter.setTCourseSite(tCourseSite);//chapter所属站点
			chapter.setType(200);//chapter类型为实验技能
			chapter = wkChapterService.saveChapter(chapter);//保存chapter
		}
		//实验指导书文件夹
		WkFolder quideFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验指导书",201);
		//实验图片文件夹
		WkFolder imageFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验图片",202);
		//实验视频文件夹
		WkFolder videoFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验视频",203);
		//实验工具文件夹
		WkFolder toolFolder = wkFolderService.saveTExperimentSkillWkFolder(chapter,"实验工具",204);
		//保存实验指导书
		this.saveTExperimentSkillWkUpload(quideFolder,"experimentalQuidesList",request);
		//保存实验图片
		this.saveTExperimentSkillWkUpload(imageFolder,"experimentalImagesList",request);
		//保存实验视频
		this.saveTExperimentSkillWkUpload(videoFolder,"experimentalVideosList",request);
		//保存实验工具
		this.saveTExperimentSkillWkUpload(toolFolder,"experimentalToolsList",request);
		//设置开始时间和结束时间
		String startdate = request.getParameter("startdate");
		String duedate = request.getParameter("duedate");
		//获取日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//string格式时间转化为Date
		Date startdateDate = sdf.parse(startdate);
		Date duedateDate = sdf.parse(duedate);
		//Date格式时间转化为Calendar
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startdateDate);
		Calendar dueCalendar = Calendar.getInstance();
		dueCalendar.setTime(duedateDate);
		//保存起止时间
		tExperimentSkill.setStartdate(startCalendar);
		tExperimentSkill.setDuedate(dueCalendar);
		//保存对应章节id
		tExperimentSkill.setChapterId(chapter.getId());
		//保存实验技能成绩所占权重
		if(tExperimentSkill.getWeight() ==null) {
			tExperimentSkill.setWeight(new BigDecimal(1));
		}
		//保存实验技能
		return tExperimentSkillDAO.store(tExperimentSkill);
	}

	/**************************************************************************
	 * Description:实验技能-根据主键查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2016-10-12
	 **************************************************************************/
	@Override
	public TExperimentSkill findTExperimentSkillByPrimaryKey(Integer id) {
		//根据主键查询讨论
		return tExperimentSkillDAO.findTExperimentSkillByPrimaryKey(id);
	}

	/**************************************************************************
	 * Description:实验技能-删除实验技能
	 * 
	 * @author：裴继超
	 * @date ：2016-10-12
	 **************************************************************************/
	@Override
	public void deleteTExperimentSkill(TExperimentSkill TExperimentSkill) {
		//删除查询出的讨论
		tExperimentSkillDAO.remove(TExperimentSkill);
	}
	
	/**************************************************************************
	 * Description:实验技能-查询实验技能完成情况
	 * 
	 * @author：裴继超
	 * @date ：2016-10-12
	 **************************************************************************/
	@Override
	public List<Integer> findTExperimentSkillStringList(Integer tCourseSiteId){
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//初始化默认时间,当前学期
		Calendar duedate = Calendar.getInstance();
		SchoolTerm term = shareService.getBelongsSchoolTerm(duedate);
		//新建空的string集合
		List<Integer> skillDoList = new ArrayList();
		Integer done = 0;
		Integer toDo = 0;
		Integer doing = 0;
		//获取当前课程所有实验技能
		List<TExperimentSkill> skillList = this.findTExperimentSkillListBySiteId(tCourseSiteId, 1, -1,"skill");
		for(int i = 0; i < skillList.size(); i++){//循环所有实验技能
			if(term.getId()>tCourseSite.getSchoolTerm().getId()){//如果当前学期大于课程学期
				done += 1;
			}else if(term.getId()<tCourseSite.getSchoolTerm().getId()){//如果当前学期小于课程学期
				toDo += 1;
			}else if(duedate.compareTo(skillList.get(i).getStartdate())==-1){//如果当前时间小于实验开始时间
				toDo += 1;
			}else if(i+1==skillList.size()&&duedate.compareTo(skillList.get(i).getStartdate()) > -1){
				doing += 1;
			}else if(i+1<skillList.size()&&duedate.compareTo(skillList.get(i).getStartdate()) > -1
					&&duedate.compareTo(skillList.get(i+1).getStartdate()) == -1){
				doing += 1;
			}else{
				done += 1;
			}
		}
		skillDoList.add(done);//已完成的实验技能
		skillDoList.add(doing);//正在进行的实验技能
		skillDoList.add(toDo);//未开始的实验技能
		return skillDoList;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能概要
	 * 
	 * @author：裴继超
	 * @date ：2016-9-23
	 **************************************************************************/
	@Override
	public void saveExperimentSkillProfile(@RequestParam Integer tCourseSiteId,
			HttpServletRequest request) {
		
        //获取实验技能概要
		String experimentSkillProfile = request.getParameter("experimentSkillProfile");
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		tCourseSite.setExperimentSkillProfile(experimentSkillProfile);
		//删除查询出的讨论
		tCourseSiteDAO.store(tCourseSite);
		tCourseSiteDAO.flush();
	}

	/**************************************************************************
	 * Description:保存实验技能相关资源
	 * 
	 * @author：裴继超
	 * @date ：2016-9-28
	 **************************************************************************/
	@Override
	public void saveTExperimentSkillWkUpload(WkFolder folder,String name,HttpServletRequest request) {

		//获取资源id列表
		String uploadIdStringList = request.getParameter(name);
		if(uploadIdStringList!=null){
		if(uploadIdStringList.length()!=0){
			uploadIdStringList = uploadIdStringList.substring(0,uploadIdStringList.length()-1);//去掉最后一个逗号
			String[] uploadIds = uploadIdStringList.split(",");//截取实验指导书id为数组
			for(String s:uploadIds){//循环资源
				WkUpload upload = wkUploadService.findUploadByPrimaryKey(Integer.parseInt(s));//根据主键获取对应资源
				upload.setWkFolder(folder);//设置资源所属文件夹
				wkUploadService.saveUpload(upload);//保存资源
			}
		}
		}
		
		
	}
	
	/**************************************************************************
	 * Description:实验技能-根据实验室获取实验工具json
	 * 
	 * @author：裴继超
	 * @date ：2016-9-29
	 **************************************************************************/
	@Override
	public String findToolsJsonByLabRooms(String labRoomIds) {
		String result = "{\"devices\":\"";
		String[] roomIds = labRoomIds.split(",");//所选实验室的id数组
		
		for(String s:roomIds){//遍历所有实验室
			//System.out.println(s);
			LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(Integer.parseInt(s));//获取实验室对象
			for(LabRoomDevice l:labRoom.getLabRoomDevices()){//遍历实验室下属实验设备
				result += "<option value ='"+l.getId()+"'>"+l.getSchoolDevice().getDeviceName()+"</option>";
			}
		}
		result += "\"}";
		return result;
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能所属实验室
	 * 
	 * @author：裴继超
	 * @date ：2016-9-30
	 **************************************************************************/
	@Override
	public void saveExperimentLabRooms(Integer skillId,HttpServletRequest request) {
		//获取实验技能所属实验室的id数组
				String[] labRoomIds = request.getParameterValues("labRoomIds");
				List<TExperimentLabRoom> labRooms = getLabRoomList(skillId);
				for (TExperimentLabRoom tExperimentLabRoom : labRooms) {
					tExperimentLabRoomDAO.remove(tExperimentLabRoom);
				}
				if(labRoomIds!=null){
					TExperimentLabRoom tExperimentLabRoom = null;//空的实验技能实验室对象
					for(String s:labRoomIds){
						tExperimentLabRoom = new TExperimentLabRoom();
						tExperimentLabRoom.setExperimentSkillId(skillId);//所属实验技能
						tExperimentLabRoom.setLabRoomId(Integer.parseInt(s));//所属实验室
						tExperimentLabRoomDAO.store(tExperimentLabRoom);//保存
						tExperimentLabRoomDAO.flush();
					}
				}
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验技能的实验工具
	 * 
	 * @author：裴继超
	 * @date ：2016-9-30
	 **************************************************************************/
	@Override
	public void saveExperimentDevices(Integer skillId,HttpServletRequest request) {
		//获取实验技能实验设备的id数组
		String[] devices = request.getParameterValues("devices");
		List<TExperimentLabDevice> labDevices = getLabDeviceList(skillId);
		for (TExperimentLabDevice tExperimentLabDevice : labDevices) {
			tExperimentLabDeviceDAO.remove(tExperimentLabDevice);
		}
		if(devices!=null){
			TExperimentLabDevice device = null;//空的实验技能设备对象
			for(String s:devices){
				device = new TExperimentLabDevice();
				device.setExperimentSkillId(skillId);//实验技能
				device.setLabDeviceId(Integer.parseInt(s));//实验设备
				tExperimentLabDeviceDAO.store(device);//保存
				tExperimentLabDeviceDAO.flush();
			}
		}
	}
	
	/**************************************************************************
	 * Description:实验技能-获取实验技能所属实验室名字列表
	 * 
	 * @author：裴继超
	 * @date ：2016-10-8
	 **************************************************************************/
	@Override
	public String findExperimentLabRoomsString(Integer skillId) {
		String labRoomNames = "";//新建实验室名字string
		//根据实验技能id查询相关实验室
		String sql = "select l.lab_room_name from lab_room l where l.id in " +
				"(select t.lab_room_id from t_experiment_lab_room t " +
				"where t.experiment_skill_id = " + skillId + ")";
		List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
		for(Object o:objects){//遍历查询结果
			labRoomNames += o.toString()+",";
		}
		if(labRoomNames.length()!=0){//去最后一个,
			labRoomNames = labRoomNames.substring(0,labRoomNames.length()-1);
		}
		return labRoomNames;
	}
	
	/**************************************************************************
	 * Description:实验技能-根据类别获取实验资源
	 * 
	 * @author：裴继超
	 * @date ：2016-10-9
	 **************************************************************************/
	@Override
	public List<WkUpload> findWkUploadBySkillIdAndType(Integer skillId,Integer type) {
		//获取所选实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillDAO.findTExperimentSkillById(skillId);
		//查询实验资源
		String sql = "select u from WkUpload u where u.wkFolder.WkChapter.id = " +
				tExperimentSkill.getChapterId() +
						" and u.type = " + type;
		List<WkUpload> uploads = wkUploadDAO.executeQuery(sql.toString(), 0, -1);
		return uploads;
	}
	
	/**************************************************************************
	 * Description:实验技能-获取实验设备
	 * 
	 * @author：裴继超
	 * @date ：2016-10-9
	 **************************************************************************/
	@Override
	public List<LabRoomDevice> findDeviceBySkillId(Integer skillId) {
		//获取所选实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillDAO.findTExperimentSkillById(skillId);
		String sql = "select d from LabRoomDevice d where d.id in " +
				" (select t.labDeviceId from TExperimentLabDevice t " +
				"where t.experimentSkillId = " + skillId + ") ";
		List<LabRoomDevice> devices = labRoomDeviceDAO.executeQuery(sql.toString(), 0, -1);
		return devices;
	}
	
	
	
	
	
	
	/**************************************************************************
	 * Description:实验技能-查询实验问答数量
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 **************************************************************************/
	@Override
	public int getCountSkillTDiscussList(String tCourseSiteId) {
		//查询对应站点下的讨论数量
		StringBuffer sql = new StringBuffer("select count(*) from TDiscuss c " +
				"where c.TCourseSite.id = '"+ tCourseSiteId +"' " +
						" and c.type > 99");
		return ((Long) tDiscussDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/**************************************************************************
	 * Description:实验技能-查询实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索
	 **************************************************************************/
	@Override
	public List<TDiscuss> findSkillTDiscussListByPartent(Integer partentDiscussId, 
			Integer currpage,int pageSize){
		//查询讨论
		StringBuffer sql = new StringBuffer("from TDiscuss c where c.tDiscuss.id = '"+ partentDiscussId +"'");
		sql.append(" order by c.discussTime asc");
		return tDiscussDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}

	/**************************************************************************
	 * Description:实验技能-查询实验问答列表
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	@Override
	public List<TDiscuss> findSkillTDiscussList(String tCourseSiteId,
			Integer currpage, int pageSize) {
		//查询讨论列表
		StringBuffer sql = new StringBuffer("from TDiscuss c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		sql.append("  and c.type > 99 order by c.discussTime desc");
		return tDiscussDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}

	/**************************************************************************
	 * Description:实验技能-保存实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	@Override
	public void saveSkillTDiscuss(TDiscuss tDiscuss) {
		//保存讨论
		tDiscuss.setUser(userDAO.findUserByPrimaryKey(tDiscuss.getUser().getUsername().trim())==null?shareService.getUser():userDAO.findUserByPrimaryKey(tDiscuss.getUser().getUsername().trim()));
		tDiscussDAO.store(tDiscuss);
	}

	/**************************************************************************
	 * Description:实验技能-根据主键查询实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	@Override
	public TDiscuss findSkillTDiscussByPrimaryKey(Integer id) {
		//根据主键查询讨论
		return tDiscussDAO.findTDiscussByPrimaryKey(id);
	}

	/**************************************************************************
	 * Description:实验技能-删除实验问答
	 * 
	 * @author：裴继超
	 * @date ：2016-10-10
	 **************************************************************************/
	@Override
	public void deleteSkillTDiscuss(TDiscuss tDiscuss) {
		//删除查询出的讨论
		tDiscussDAO.remove(tDiscuss);
	}
	
	/**************************************************************************
	 * Description:实验技能-保存实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	@Transactional
	@Override
	public TAssignment saveReportTAssignment(TExperimentSkill tExperimentSkill,HttpServletRequest request) 
			throws ParseException {
		//获取实验技能的chapter
		WkChapter wkChapter = wkChapterDAO.findWkChapterById(tExperimentSkill.getChapterId());
		//新建一个实验报告
		TAssignment newTAssignment = null;
		TAssignment tAssignment = this.findTAssignmentBySkillAndType(tExperimentSkill,200);
		//记录作业的控制表
		TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
		//记录作业的设置表
		TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();
		//新建一个存放实验报告的folder
		WkFolder wkFolder = tAssignment.getWkFolder();
		wkFolder.setName(tExperimentSkill.getExperimentName()+"实验报告");
		wkFolder.setUser(shareService.getUser());
		wkFolder.setCreateTime(Calendar.getInstance());
		wkFolder.setWkChapter(wkChapter);
		wkFolder.setType(200);
		wkFolder = wkFolderDAO.store(wkFolder);
		wkFolderDAO.flush();
		
		tAssignment.setTitle(tExperimentSkill.getExperimentName()+"实验报告");
		tAssignment.setCreatedTime(Calendar.getInstance());
		tAssignment.setUser(shareService.getUser());
		tAssignment.setStatus(1);
		tAssignment.setSiteId(tExperimentSkill.getSiteId());
		tAssignment.setType("assignment");
		tAssignment.setWkFolder(wkFolder);
		tAssignment.setTAssignmentControl(null);
		tAssignment.setTAssignmentAnswerAssign(null);
		tAssignment = tAssignmentDAO.store(tAssignment);
		

		

		tAssignment.setTAssignmentControl(null);
		tAssignment.setTAssignmentAnswerAssign(null);
		tAssignment.setWkFolder(wkFolder);
		
		tAssignment = tAssignmentDAO.store(tAssignment);
        //设置开始时间和结束时间
		//保存作业的控制表
		tAssignmentControl.setStartdate(tExperimentSkill.getStartdate());//开始时间
		tAssignmentControl.setDuedate(tExperimentSkill.getDuedate());//结束时间
		tAssignmentControl.setTimelimit(0);//提交次数：无限
		tAssignmentControl.setSubmitType(1);//提交方式：附件或文本
		String toGradebook = request.getParameter("toGradebook");
		if(toGradebook==null){
			toGradebook="yes";
		}
		tAssignmentControl.setToGradebook(toGradebook);//是否发布到成绩册
		tAssignmentControl.setGradeToStudent("yes");//是否公布成绩给学生
		tAssignmentControl.setGradeToTotalGrade("yes");//成绩是否计入总成绩
		tAssignmentControl.setTAssignment(tAssignment);//保存对应报告
		tAssignmentControl =tAssignmentControlDAO.store(tAssignmentControl);//保存控制表
		
		//保存作业的设置表
		tAssignmentAssign.setUser(shareService.getUser());//创建者
		tAssignmentAssign.setScore(new BigDecimal(100));//保存总分
		tAssignmentAssign.setTAssignment(tAssignment);//保存对应报告
		tAssignmentAssign =tAssignmentAnswerAssignDAO.store(tAssignmentAssign);//保存设置表
		
		
		tAssignment.setTAssignmentControl(tAssignmentControl);
		tAssignment.setTAssignmentAnswerAssign(tAssignmentAssign);
		tAssignment.setSequence(tAssignment.getId());//给作业排序，初始值和id保持一致
		
		newTAssignment = tAssignmentDAO.store(tAssignment);
		tAssignmentDAO.flush();
		return newTAssignment;
		
	}
	
	/**************************************************************************
	 * Description:实验技能-根据实验技能查找实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	@Override
	public TAssignment findReportTAssignmentBySkill(TExperimentSkill tExperimentSkill){
		String sql = "select t from TAssignment t where t.wkFolder.WkChapter.id = " + 
				tExperimentSkill.getChapterId() + " and t.wkFolder.type = 200";
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
		if(tAssignments.size()!=0){
			return tAssignments.get(0);
		}else{
			TAssignment tAssignment = new TAssignment();
			tAssignment.setWkFolder(new WkFolder());
			tAssignment.setTAssignmentAnswerAssign(new TAssignmentAnswerAssign());
			tAssignment.setTAssignmentControl(new TAssignmentControl());
			return tAssignment;
		}
	}
	
	/**************************************************************************
	 * Description:实验技能-学生获取自己的实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-12
	 **************************************************************************/
	@Override
	public List<TAssignmentGrading> findReportTAssignmentGradingBySkillAndStudent(
			TExperimentSkill tExperimentSkill){
		TAssignment tAssignment = this.findReportTAssignmentBySkill(tExperimentSkill);
		String sql = "select t from TAssignmentGrading t where t.TAssignment.id = " + 
				tAssignment.getId() + " and t.userByStudent.username like '" +
				shareService.getUser().getUsername() + "' ";
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql.toString(), 0, -1);
		return tAssignmentGradings;
	}
	
	
	/**************************************************************************
	 * Description:实验技能-学生提交实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	@Override
	public TAssignmentGrading saveReportByStudent(TAssignmentGrading tAssignmentGrade,
			Integer tCourseSiteId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		tAssignmentGrade.setUserByStudent(shareService.getUser());
		Integer islate = 0;//0表示正常提交
		//Calendar submitDate = tAssignmentGrade.getSubmitdate();
		Calendar submitDate = Calendar.getInstance();
		TAssignment tAssignment = tAssignmentDAO.findTAssignmentById(tAssignmentGrade.getTAssignment().getId());
		Calendar dueDate = tAssignment.getTAssignmentControl().getDuedate();
		if(submitDate.after(dueDate)){
			islate = 1;//1表示迟交
		}
		tAssignmentGrade.setSubmitdate(submitDate);
		tAssignmentGrade.setIslate(islate);
		TAssignmentGrading newTAssignmentGrading = tAssignmentGradingDAO.store(tAssignmentGrade);
		String sep = "/";
		String filePath="/upload"+sep+"tAssignment"+sep+tCourseSiteId+sep+tAssignmentGrade.getTAssignment().getId()+sep+"student";
		String gradeUrl = fileUpload(request, filePath,tAssignmentGrade.getUserByStudent().getUsername(),newTAssignmentGrading.getAccessmentgradingId());
		newTAssignmentGrading.setGradeUrl(gradeUrl);
		tAssignmentGradingDAO.store(newTAssignmentGrading);
		return newTAssignmentGrading;
	}
	
	
	/**************************************************************************
	 * Description:实验技能-将文件上传到指定路径并返回文件保存路径
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-13
	 **************************************************************************/
	private String fileUpload(HttpServletRequest request, String filePath,
			String username,Integer Gradingid) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    // 构建文件保存的目录
	    String PathDir = "/upload/"+ dateformat.format(new Date());*/
	    /** 得到文件保存目录的真实(绝对)路径* */
	    String RealPathDir = request.getSession().getServletContext().getRealPath(filePath);
	    //根据真实路径创建文件夹
	    File SaveFile = new File(RealPathDir);
	    if (!SaveFile.exists()){
	    	SaveFile.mkdirs();
	    }
	    /** 页面控件的文件流* */
	    MultipartFile multipartFile = multipartRequest.getFile("file");
	    /**判断文件不为空*/
	    if(multipartFile!=null&&!multipartFile.isEmpty()){
	    	//截取上传文件的名称，获取文件的后缀
	    	String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		    // 使用UUID生成文件名称,中文一般会报错(产生一个号称全球唯一的ID)
	    	//String name = UUID.randomUUID().toString() + suffix;// 构建文件名称
	    	
	    	//String CourseName=tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(Gradingid).getTAssignment().getTCourseSite().getTitle();
	    	//String name =CourseName+"-"+ username+"-"+Gradingid + suffix;// 构建文件名称
	    	String name ="-"+ username+"-"+Gradingid + suffix;// 构建文件名称
		    // 拼成完整的文件保存路径加文件
		    String fileName = RealPathDir + File.separator + name;
		    File file = new File(fileName);
		    try {
		    	//转储文件
		        multipartFile.transferTo(file);
		    } catch (IllegalStateException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    /** 上传到服务器的文件的绝对路径* */	       
		    String saveUrl=filePath+"/"+name;
		    return saveUrl;
	    }
	    return "";
	}
	/*************************************************************************************
	 * description:实验项目-设置预习测试可答题时间
	 * 
	 * @author:裴继超
	 * @date：2016-11-18
	 *************************************************************************************/
	@Override
	public void newPrepareExam(int flag,int skillId) {
		//当前登录人
		User user = shareService.getUser();
		//查看实验技能
		TExperimentSkill tExperimentSkill = this.findTExperimentSkillByPrimaryKey(skillId);
		
		//查找到预习测试
		TAssignment tAssignment = this.findTAssignmentBySkillAndType(tExperimentSkill,205);
		if (tAssignment.getId()!=null){
		  //当前登录人的答题记录
		  TAssignmentGrading grading = this.findExamGradingBySubmitTime(tAssignment.getId(), 0);
		  if(grading.getAccessmentgradingId()==null){
			  grading.setTAssignment(tAssignment);
			  grading.setUserByStudent(user);
			  grading.setSubmitTime(0);
			  grading.setSubmitdate(Calendar.getInstance());
			  grading = tAssignmentGradingDAO.store(grading);
		     }
	     }
	}

	/**************************************************************************
	 * Description:实验技能-根据实验技能查找实验报告
	 * @throws ParseException 
	 * 
	 * @author：裴继超
	 * @date ：2016-10-11
	 **************************************************************************/
	@Override
	public TAssignment findTAssignmentBySkillAndType(TExperimentSkill tExperimentSkill,Integer type){
		String sql = "select t from TAssignment t where t.wkFolder.WkChapter.id = " + 
				tExperimentSkill.getChapterId() + " and t.wkFolder.type = " + type;
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
		if(tAssignments.size()!=0){
			return tAssignments.get(0);
		}else{
			TAssignment tAssignment = new TAssignment();
			tAssignment.setWkFolder(new WkFolder());
			tAssignment.setTAssignmentAnswerAssign(new TAssignmentAnswerAssign());
			tAssignment.setTAssignmentControl(new TAssignmentControl());
			return tAssignment;
		}
	}
	/*************************************************************************************
	 * description:实验项目-预习测试-查找学生答题记录
	 * 
	 * @author:裴继超
	 * @date：2016-11-21
	 *************************************************************************************/
	@Override
	public TAssignmentGrading findExamGradingBySubmitTime(int examId,int submitTime){
		//当前登录人
		User user = shareService.getUser();
		//设置要返回的测试
		TAssignmentGrading exam = new TAssignmentGrading();
		String sql = "from TAssignmentGrading c where c.TAssignment.id = "+examId+" and c.submitTime = " + submitTime;
		sql +=" and c.userByStudent.username like '"+ user.getUsername() +"'";
		List<TAssignmentGrading> exams = tAssignmentGradingDAO.executeQuery(sql.toString(), 0, -1);
		if(exams.size()!=0){
			exam = exams.get(0);
		}
		return exam;
	}
	/*************************************************************************************
	 * description:实验项目-设置预习测试可答题时间
	 * 
	 * @author:裴继超
	 * @date：2016-11-18
	 *************************************************************************************/
	@Override
	public Integer getExamCan(int flag,int skillId) {
		//是否可以答题标志位
		Integer examCan = -1;
		//当前登录人
		User user = shareService.getUser();
		//查看实验技能
		TExperimentSkill tExperimentSkill = this.findTExperimentSkillByPrimaryKey(skillId);
		//查找到预习测试
		TAssignment exam = this.findTAssignmentBySkillAndType(tExperimentSkill,205);
		//当前登录人的答题记录
		TAssignmentGrading gtradingSave = this.findExamGradingBySubmitTime(exam.getId(),0);
		if(gtradingSave.getAccessmentgradingId()!=null){
			//当前时间
			Calendar now = Calendar.getInstance();
			now.set(Calendar.MINUTE, now.get(Calendar.MINUTE)-3); 
			//观看视频开始时间
			Calendar lookTime = gtradingSave.getSubmitdate();
			examCan = now.compareTo(lookTime);
		}
		//当前登录人的答题记录
		TAssignmentGrading gtradingSubmit = this.findExamGradingBySubmitTime(exam.getId(),1);
		if(gtradingSubmit.getAccessmentgradingId()!=null){
			examCan = 2;
		}
		if(examCan <1) {//暂时不应用“不看视频则无法做预习测试”的功能
			examCan = 1;
		}
		//1为可以答题，0和-1为已查看视频但未到时间不可以答题，2为已答过题
		return examCan;
	}
	/**************************************************************************
	 * Description:实验技能-获取实验项目的科目列表
	 * 
	 * @author：裴继超
	 * @date ：2016-11-29
	 **************************************************************************/
	@Override
	public List<TGradeObject> findTGradeObjectsByType(
			Integer cid,Integer skillId, String type) {
		TExperimentSkill tExperimentSkill = this.findTExperimentSkillByPrimaryKey(skillId);
		//根据课程和类型查看成绩科目列表
		String hql = "select c from TGradebook t join t.TGradeObjects c where t.TCourseSite.id = '"+cid+"' and c.TAssignment.status = '1'" +
				" and c.TAssignment.TAssignmentControl.toGradebook = 'yes' and c.TAssignment.TAssignmentControl.gradeToTotalGrade = 'yes'";
		if (type!=null) {
			hql += " and c.type = '"+type+"'";
		}
		hql += " and c.TAssignment.wkFolder.WkChapter.id = " + tExperimentSkill.getChapterId();
		hql += " order by c.TAssignment.id";
		List<TGradeObject> tGradeObjects = tGradeObjectDAO.executeQuery(hql, 0, -1);
		return tGradeObjects;
	}
	/**************************************************************************
	 * Description:实验技能-获取学生某个实验分数
	 * 
	 * @author：裴继超
	 * @date ：2016-11-29
	 **************************************************************************/
	@Override
	public TExperimentSkillUser findSkillUser(Integer skillId, String username) {
		TExperimentSkillUser tExperimentSkillUser = new TExperimentSkillUser();
		BigDecimal score = new BigDecimal(0);
		tExperimentSkillUser.setFinalGrade(score.intValue());
		tExperimentSkillUser.setRealGrade(score.intValue());
		//根据课程和类型查看成绩科目列表
		String hql = "select c from TExperimentSkillUser c  where 1=1"; 
		hql += " and c.skillId = " + skillId;
		hql += " and c.username like '" + username + "'";
		List<TExperimentSkillUser> tExperimentSkillUsers = tExperimentSkillUserDAO.executeQuery(hql, 0, -1);
		if(tExperimentSkillUsers.size()!=0){
			tExperimentSkillUser = tExperimentSkillUsers.get(0);
		}
		
		return tExperimentSkillUser;
	}
	/**************************************************************************
	 * Description:实验技能-查询所有学生所有实验分数
	 * 
	 * @author：裴继超
	 * @date ：2016-11-30
	 **************************************************************************/
	@Override
	public List<List<Object>> gradeAllExperimentSkill(
			List<TCourseSiteUser> tCourseSiteUsers,Integer cid) {
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = this.findTExperimentSkillListBySiteId(cid, 1,-1,"skill");
		List<List<Object>> lists = new ArrayList<List<Object>>();
		
		//循环每个学生
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			
			List<Object> objects = new ArrayList<Object>();
			//姓名
			objects.add(tCourseSiteUser.getUser().getCname());
			//学号
			objects.add(tCourseSiteUser.getUser().getUsername());
			//总成绩
			BigDecimal skillAllScore = new BigDecimal(0);
			
			//遍历所有实验，获取所有实验成绩
			for(TExperimentSkill tExperimentSkill : tExperimentSkills) {
			
				int skillId = tExperimentSkill.getId();
				//预习测试科目列表
				List<TGradeObject> prepareExamGradeObjects = this.findTGradeObjectsByType(cid,skillId,"prepareExam"); 
				//实验报告科目列表
				TAssignment tAssignmentForReport = this.findReportTAssignmentBySkill(tExperimentSkill);
				List<TGradeObject> reportGradeObjects = new ArrayList<TGradeObject>();
				reportGradeObjects.addAll(tAssignmentForReport.getTGradeObjects());
				//实验作业科目列表
				List<TAssignment> workList=new ArrayList<TAssignment>();
				if(tExperimentSkill != null && tExperimentSkill.getChapterId() != null) {
					WkChapter wkChapter = wkChapterService.findChapterByPrimaryKey(tExperimentSkill.getChapterId());
					//根据实验项目的id获取该实验项目下的所有的作业
					Set<WkFolder> wkFolders = wkChapter.getWkFolders();
					for(WkFolder folder:wkFolders){
						if(folder.getType()==4){
							//当前实验项目下的作业
							Set<TAssignment> tAssignments = folder.getTAssignments();
							workList.addAll(tAssignments);
						}
					}
				}
				List<TCourseSiteUser> tCourseSiteUserList = new ArrayList<TCourseSiteUser>();
				tCourseSiteUserList.add(tCourseSiteUser);
				//根据科目和学生查看成绩列表
				List<List<Object>> gradeLists = tGradebookService.findStudentScoreRecordsWithAll(tCourseSiteUserList, prepareExamGradeObjects, reportGradeObjects, workList, cid);
				//获取学生实验项目总分
				BigDecimal skillScore = new BigDecimal(gradeLists.get(0).get(5).toString());
				objects.add(skillScore);
				skillAllScore = skillAllScore.add(skillScore.multiply(tExperimentSkill.getWeight()));
			}
			    objects.add(skillAllScore);
			    lists.add(objects);
		 }
		return lists;
	}
	
	/**************************************************************************
	 * Description:查询实验技能下所属实验室
	 * 
	 * @author：于侃
	 * @date ：2016年10月31日 16:58:13
	 **************************************************************************/
	public List<TExperimentLabRoom> getLabRoomList(Integer id){
		String sql = "from TExperimentLabRoom t where t.experimentSkillId = " + id;
		return tExperimentLabRoomDAO.executeQuery(sql);
	}

	/**************************************************************************
	 * Description:查询实验技能下所属实验室下所属实验设备
	 * 
	 * @author：于侃
	 * @date ：2016年11月1日 10:07:29
	 **************************************************************************/
	public List<TExperimentLabDevice> getLabDeviceList(Integer id){
		String sql = "from TExperimentLabDevice t where t.experimentSkillId = " + id;
		return tExperimentLabDeviceDAO.executeQuery(sql);
	}
	/**************************************************************************
	 * Description:根据当前的courseNo来判断当前的 tcourse_site中是否生成过对应课程
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.11
	 **************************************************************************/
	@Override
	public boolean isExitTcourseSiteWithCourseNo(String courseNo,String groupNumber,String courseDetailNo) {
		String sql="select t from TCourseSite t where 1=1";
		sql+=" and t.schoolCourse.courseNo like '"+courseNo+"'";
		List<TCourseSite> list = tCourseSiteDAO.executeQuery(sql);
		if(list.size()<=0){
			return true;
		}else{
			for(TCourseSite t:list){
				Set<SchoolCourseDetail> schoolCourseDetails = t.getSchoolCourse().getSchoolCourseDetails();
				if(schoolCourseDetails.size()>0){
					for(SchoolCourseDetail d:schoolCourseDetails){
						if(d.getCourseDetailNo().equals(courseNo) || d.getCourseDetailNo().equals(groupNumber) || d.getCourseDetailNo().equals(courseDetailNo)){
							//当前课程组的站点已经存在
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	/**************************************************************************
	 * Description:实验项目-成绩管理-查寻学生总数
	 *  
	 * @author：裴继超
	 * @date ：2016-12-12
	 **************************************************************************/
	@Override
	public int findStudentRecords(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select count(tu) from TCourseSiteUser tu join tu.user u join u.timetableGroupStudentses t " +
				"join t.timetableGroup g join g.timetableBatch b where tu.TCourseSite.id = " + id + 
				" and tu.role = 0 " +
				" order by b.batchName,g.groupName desc";
		int result = ((Long)tCourseSiteUserDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	/*************************************************************************************
	 * Description:实验项目-成绩管理-查询课程下的学生(分页)
	 * 
	 * @author： 裴继超
	 * @date：2016-12-12
	 *************************************************************************************/
	@Override
	public List<TCourseSiteUser> findStudentBySiteId(Integer id, Integer currpage, 
			Integer pageSize) {
		// TODO Auto-generated method stub
		String sql = "select tu from TCourseSiteUser tu join tu.user u join u.timetableGroupStudentses t " +
				"join t.timetableGroup g join g.timetableBatch b where tu.TCourseSite.id = " + id + 
				" and tu.role = 0 " +
				" order by b.batchName,g.groupName desc";
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return tCourseSiteUsers;
	}
	/**************************************************************************
	 * Description:获取当前课程下面每个学生的实验项目的实验报告所获得的成绩
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	@Override
	public List<List<Object>> getGradingWithSkillForAll(List<TCourseSiteUser> tCourseSiteUsers,Integer cid) {
		//获取当前站点的所有学生
		//获取所有的实验
		List<TExperimentSkill> tExperimentSkills = this.findTExperimentSkillListBySiteId(cid, 1,-1,"skill");
		List<List<Object>> list=new ArrayList<List<Object>>();
		for(TCourseSiteUser u:tCourseSiteUsers){
			List<Object> objects = new ArrayList<Object>();
			//姓名
			objects.add(u.getUser().getCname());
			//学号
			String userName=u.getUser().getUsername();
			objects.add(userName);
			//总成绩
			double totalWeigthScore=0.0;
			//总权重
			Double totalWeight=0.0;
			for(TExperimentSkill t:tExperimentSkills){
				//获取当前学生的每个实验的实验报告的成绩
				TAssignment tAssignment = this.findReportTAssignmentBySkill(t);
				BigDecimal totalScore = this.getRecordByUserName(userName, tAssignment);
				objects.add(totalScore);
				//获取当前实验的权重
				Set<TGradeObject> tGradeObjects = tAssignment.getTGradeObjects();
				List<TGradeObject> list2=new ArrayList<TGradeObject>(tGradeObjects);
				BigDecimal weight = list2.get(0).getWeight();
				totalWeight+=weight.doubleValue();
				if(totalScore!=null&&totalScore.intValue()!=0){
					BigDecimal score = ((BigDecimal) totalScore).multiply(weight);
					totalWeigthScore+=score.doubleValue();
				}
			}
			BigDecimal newTotalWeight=new BigDecimal(totalWeight);
			BigDecimal newtotalWeigthScore=new BigDecimal(totalWeigthScore);
			if(newTotalWeight.intValue()<=0){
				objects.add(0);
			}else{
				objects.add(newtotalWeigthScore.divide(newTotalWeight,1,BigDecimal.ROUND_HALF_UP));
			}
			list.add(objects);
			//根据权重计算这个学生的所有实验项目的总成绩
		}
		return list;
	}
	/**************************************************************************
	 * Description:根据当前的实验报告和学生学号来获取学生的成绩
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	@Override
	public BigDecimal getRecordByUserName(String userName, TAssignment tAssigment) {
		String sql="select t from TAssignmentGrading t where 1=1";
		sql+=" and t.TAssignment.id="+tAssigment.getId();
		sql+=" and t.userByStudent.username like '"+userName+"'";
		List<TAssignmentGrading> executeQuery = tAssignmentGradingDAO.executeQuery(sql);
		if(executeQuery==null||executeQuery.size()<=0){
			//当前的学生没有成绩或者没有提交过实验报告
			return new BigDecimal(0);
		}else{
			return executeQuery.get(0).getFinalScore();
		}
	}
	/**************************************************************************
	 * Description:获取每个实验项目对应的权重
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	@Override
	public List<List<Object>> getWeightWithSkill(Integer cid) {
		List<List<Object>> list=new ArrayList<List<Object>>();
		//获取所有的实验项目
		List<TExperimentSkill> tExperimentSkills = this.findTExperimentSkillListBySiteId(cid, 1,-1,"skill");
		//在这里每个实验项目的实验报告的权重就是实验项目的权重
		for(TExperimentSkill t:tExperimentSkills){
			List<Object> newList=new ArrayList<Object>();
			newList.add(t.getId());
			newList.add(t.getExperimentName());
			newList.add(t.getWeight());
			list.add(newList);
		}
		return list;
	}
	/**************************************************************************
	 * Description:获取实验项目以及对应的名称
	 * 
	 * @author：李雪腾
	 * @date ：2017.7.13
	 **************************************************************************/
	@Override
	public List<List<Object>> getWeightAndNameWithSkill(Integer cid) {
		List<List<Object>> newList=new ArrayList<List<Object>>();
		List<TExperimentSkill> tExperimentSkills = this.findTExperimentSkillListBySiteId(cid, 1,-1,"skill");
		for(TExperimentSkill t:tExperimentSkills){
			List<Object> list=new ArrayList<Object>();
			TAssignment tAssignmentBySkill = this.findReportTAssignmentBySkill(t);
			Set<TGradeObject> tGradeObjects = tAssignmentBySkill.getTGradeObjects();
			List<TGradeObject> list2=new ArrayList<TGradeObject>(tGradeObjects);
			BigDecimal weight = list2.get(0).getWeight();
			list.add(t.getExperimentName());
			list.add(weight.doubleValue()*100+"%");
			newList.add(list);
		}
		return newList;
	}
	/**************************************************************************
	 * Description:实验技能-根据实验技能新增实验报告
	 * @throws ParseException 
	 * 
	 * @author：李雪腾
	 * @date ：2017-7-18
	 **************************************************************************/
	@Override
	public TAssignment saveExpReport(TExperimentSkill tExperimentSkill) {
		TAssignment tAssignment = new TAssignment();
		tAssignment.setWkFolder(new WkFolder());
		tAssignment.setTAssignmentAnswerAssign(new TAssignmentAnswerAssign());
		tAssignment.setTAssignmentControl(new TAssignmentControl());
		return tAssignment;
	}
	
	/**************************************************************************
	 * Description:根据当前的实验报告和学生学号来获取学生成绩（整条数据）
	 * 
	 * @author：张佳鸣
	 * @date ：2017.9.29
	 **************************************************************************/
	@Override
	public TAssignmentGrading getTAssignmentGradingByUserName(String userName, TAssignment tAssigment) {
		String sql="select t from TAssignmentGrading t where 1=1";
		sql+=" and t.TAssignment.id="+tAssigment.getId();
		sql+=" and t.userByStudent.username like '"+userName+"'";
		List<TAssignmentGrading> executeQuery = tAssignmentGradingDAO.executeQuery(sql);
		if(executeQuery==null||executeQuery.size()<=0){
			//当前的学生没有成绩或者没有提交过实验报告
			return new TAssignmentGrading();
		}else{
			return executeQuery.get(0);
		}
	}
	/**************************************************************************
	 * Description:实验技能-保存实验数据
	 * @throws ParseException 
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-11
	 **************************************************************************/
	@Override
	public TAssignment saveDataTAssignment(TExperimentSkill tExperimentSkill,
			HttpServletRequest request)throws ParseException {
		//获取实验技能的chapter
		WkChapter wkChapter = wkChapterDAO.findWkChapterById(tExperimentSkill.getChapterId());
		//新建一个实验数据
		TAssignment newTAssignment = null;
		TAssignment tAssignment = this.findTAssignmentBySkillAndType(tExperimentSkill,206);
		//记录作业的控制表
		TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
		//记录作业的设置表
		TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();
		//新建一个存放实验数据的folder
		WkFolder wkFolder = tAssignment.getWkFolder();
		wkFolder.setName(tExperimentSkill.getExperimentName()+"实验数据");
		wkFolder.setUser(shareService.getUser());
		wkFolder.setCreateTime(Calendar.getInstance());
		wkFolder.setWkChapter(wkChapter);
		wkFolder.setType(206);
		wkFolder = wkFolderDAO.store(wkFolder);
		wkFolderDAO.flush();
		
		tAssignment.setTitle(tExperimentSkill.getExperimentName()+"实验数据");
		tAssignment.setCreatedTime(Calendar.getInstance());
		tAssignment.setUser(shareService.getUser());
		tAssignment.setStatus(1);
		tAssignment.setSiteId(tExperimentSkill.getSiteId());
		tAssignment.setType("data");
		tAssignment.setWkFolder(wkFolder);
		tAssignment.setTAssignmentControl(null);
		tAssignment.setTAssignmentAnswerAssign(null);
		tAssignment = tAssignmentDAO.store(tAssignment);
		

		

		tAssignment.setTAssignmentControl(null);
		tAssignment.setTAssignmentAnswerAssign(null);
		tAssignment.setWkFolder(wkFolder);
		
		tAssignment = tAssignmentDAO.store(tAssignment);
        //设置开始时间和结束时间
		//保存作业的控制表
		tAssignmentControl.setStartdate(tExperimentSkill.getStartdate());//开始时间
		tAssignmentControl.setDuedate(tExperimentSkill.getDuedate());//结束时间
		tAssignmentControl.setTimelimit(0);//提交次数：无限
		tAssignmentControl.setSubmitType(1);//提交方式：附件或文本
		String toGradebook = request.getParameter("toGradebook");
		tAssignmentControl.setToGradebook("yes");//是否发布到成绩册
		tAssignmentControl.setGradeToStudent("yes");//是否公布成绩给学生
		tAssignmentControl.setGradeToTotalGrade("yes");//成绩是否计入总成绩
		tAssignmentControl.setTAssignment(tAssignment);//保存对应报告
		tAssignmentControl =tAssignmentControlDAO.store(tAssignmentControl);//保存控制表
		
		//保存作业的设置表
		tAssignmentAssign.setUser(shareService.getUser());//创建者
		tAssignmentAssign.setScore(new BigDecimal(100));//保存总分
		tAssignmentAssign.setTAssignment(tAssignment);//保存对应报告
		tAssignmentAssign =tAssignmentAnswerAssignDAO.store(tAssignmentAssign);//保存设置表
		
		
		tAssignment.setTAssignmentControl(tAssignmentControl);
		tAssignment.setTAssignmentAnswerAssign(tAssignmentAssign);
		tAssignment.setSequence(tAssignment.getId());//给作业排序，初始值和id保持一致
		
		newTAssignment = tAssignmentDAO.store(tAssignment);
		return newTAssignment;
		
	}
	/**************************************************************************
	 * Description:实验技能-保存预习测试
	 * @throws ParseException 
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-11
	 **************************************************************************/
	@Override
	public TAssignment savePrepareExam(TExperimentSkill tExperimentSkill,HttpServletRequest request) 
			throws ParseException {
		//获取实验技能的chapter
		WkChapter wkChapter = wkChapterDAO.findWkChapterById(tExperimentSkill.getChapterId());
		//新建一个预习测试
		TAssignment newTAssignment = null;
		TAssignment tAssignment = this.findTAssignmentBySkillAndType(tExperimentSkill,205);
		//预习测试的控制表
		TAssignmentControl tAssignmentControl = tAssignment.getTAssignmentControl();
		//预习测试的设置表
		TAssignmentAnswerAssign tAssignmentAssign = tAssignment.getTAssignmentAnswerAssign();
		//新建一个存放预习测试的folder
		WkFolder wkFolder = tAssignment.getWkFolder();
		wkFolder.setName(tExperimentSkill.getExperimentName()+"预习测试");
		wkFolder.setUser(shareService.getUser());
		wkFolder.setCreateTime(Calendar.getInstance());
		wkFolder.setWkChapter(wkChapter);
		wkFolder.setType(205);
		wkFolder = wkFolderDAO.store(wkFolder);
		wkFolderDAO.flush();
		
		tAssignment.setTitle(tExperimentSkill.getExperimentName()+"预习测试");
		tAssignment.setCreatedTime(Calendar.getInstance());
		tAssignment.setUser(shareService.getUser());
		if(tAssignment.getStatus()==null){
			tAssignment.setStatus(0);
		}
		tAssignment.setSiteId(tExperimentSkill.getSiteId());
		tAssignment.setType("prepareExam");
		tAssignment.setWkFolder(wkFolder);
		tAssignment.setTAssignmentControl(null);
		tAssignment.setTAssignmentAnswerAssign(null);
		tAssignment = tAssignmentDAO.store(tAssignment);
		

		

		tAssignment.setTAssignmentControl(null);
		tAssignment.setTAssignmentAnswerAssign(null);
		tAssignment.setWkFolder(wkFolder);
		
		tAssignment = tAssignmentDAO.store(tAssignment);
        //设置开始时间和结束时间
		//保存作业的控制表
		tAssignmentControl.setStartdate(tExperimentSkill.getStartdate());//开始时间
		tAssignmentControl.setDuedate(tExperimentSkill.getDuedate());//结束时间
		tAssignmentControl.setTimelimit(1);//提交次数：无限
		String toGradebook = request.getParameter("toGradebook");
		tAssignmentControl.setToGradebook("yes");//是否发布到成绩册
		tAssignmentControl.setGradeToStudent("no");//是否公布成绩给学生
		tAssignmentControl.setGradeToTotalGrade("yes");//成绩是否计入总成绩
		tAssignmentControl.setTAssignment(tAssignment);//保存对应报告
		tAssignmentControl =tAssignmentControlDAO.store(tAssignmentControl);//保存控制表
		
		//保存作业的设置表
		tAssignmentAssign.setUser(shareService.getUser());//创建者
		tAssignmentAssign.setScore(new BigDecimal(100));//保存总分
		tAssignmentAssign.setTAssignment(tAssignment);//保存对应报告
		tAssignmentAssign =tAssignmentAnswerAssignDAO.store(tAssignmentAssign);//保存设置表
		
		
		tAssignment.setTAssignmentControl(tAssignmentControl);
		tAssignment.setTAssignmentAnswerAssign(tAssignmentAssign);
		tAssignment.setSequence(tAssignment.getId());//给作业排序，初始值和id保持一致
		
		newTAssignment = tAssignmentDAO.store(tAssignment);
		return newTAssignment;
		
	}
	
	/**************************************************************************
	 * Description:查询实验技能（通过站点id）
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-20
	 **************************************************************************/
	@Override
	public List<TExperimentSkill> findTExperimentSkillListBySiteId(Integer tCourseSiteId, Integer currpage,
			int pageSize){
		//查询讨论
		StringBuffer sql = new StringBuffer("from TExperimentSkill c where c.siteId = "+ tCourseSiteId);
		sql.append(" order by c.sort asc");
		return tExperimentSkillDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}
	
	/**************************************************************************
	 * Description:实验技能-计算学生实验总分并保存
	 * 
	 * @author：裴继超
	 * @date ：2016-11-30
	 **************************************************************************/
	@Override
	public void saveSkillGrade(Integer tCourseSiteId,Integer skillId, String username) {
		//新建成绩记录
		TExperimentSkillUser tExperimentSkillUser = this.findSkillUser(skillId, username);
		//当前登录人
		User user = userDAO.findUserByPrimaryKey(username);
		//实验报告科目列表
		List<TGradeObject> reportGradeObjects = this.findTGradeObjectsByType(tCourseSiteId,skillId,"report");      
		//预习测试科目列表
		List<TGradeObject> prepareExamGradeObjects = this.findTGradeObjectsByType(tCourseSiteId,skillId,"prepareExam");
		//实验科目列表
		List<TGradeObject> skillGradeObjects = prepareExamGradeObjects;
		skillGradeObjects.addAll(reportGradeObjects);
		//总成绩
		BigDecimal totalScore = new BigDecimal(0);
		//总权重
		BigDecimal totalWeight = new BigDecimal(0);
		
		if(reportGradeObjects.size()!=0){
			for (TGradeObject tGradeObject : skillGradeObjects) {
				for (TGradeRecord tGradeRecord : user.getTGradeRecords()) {
					if (tGradeRecord.getTGradeObject().getId().equals(tGradeObject.getId())) {
						totalScore = totalScore.add(tGradeRecord.getPoints().multiply(tGradeObject.getWeight()));
					}
					
				}
				totalWeight = totalWeight.add(tGradeObject.getWeight());
			}
			totalScore = totalScore.divide(totalWeight,1,BigDecimal.ROUND_HALF_UP);
		}
		
		tExperimentSkillUser.setSkillId(skillId);
		tExperimentSkillUser.setUsername(username);
		tExperimentSkillUser.setRealGrade(totalScore.intValue());
		tExperimentSkillUser.setFinalGrade(totalScore.intValue());
		tExperimentSkillUser.setCreateTime(Calendar.getInstance());
		tExperimentSkillUser = tExperimentSkillUserDAO.store(tExperimentSkillUser);
		
	}
}