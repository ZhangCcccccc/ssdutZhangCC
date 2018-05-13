package net.xidlims.service.newtimetable;

import java.util.Arrays;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolCourseDetailDAO;
import net.xidlims.dao.SchoolCourseMergeDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAppointmentSameNumberDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableItemRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseMerge;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.service.ConvertUtil;
import net.xidlims.service.EmptyUtil;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.timetable.OuterApplicationService;
import net.xidlims.service.timetable.SchoolCourseService;
import net.xidlims.service.timetable.TimetableAppointmentService;
import net.xidlims.service.timetable.TimetableSelfCourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**********************************************
 * Description: 排课模块{各种形式排课的保存实现}
 * 
 * @author 贺子龙
 * @date 2016-08-31
 ***********************************************/
@Service
public class TimetableAppointmentSaveServiceImpl implements TimetableAppointmentSaveService{
	
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableItemRelatedDAO timetableItemRelatedDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private TimetableAppointmentSameNumberDAO timetableAppointmentSameNumberDAO;
	@Autowired
	private OuterApplicationService outerApplicationServiceImpl;
	@Autowired
	private SchoolCourseService schoolCourseService;
	@Autowired
	private TimetableSelfCourseService timetableSelfCourseService;
	@Autowired
	private TimetableAppointmentService timetableAppointmentService;
	@Autowired
	private SchoolCourseDetailDAO schoolCourseDetailDAO;
	@Autowired
	private
	NewTimetableCourseSchedulingService newTimetableCourseSchedulingService;
	@Autowired
	private SchoolCourseMergeDAO schoolCourseMergeDAO;
	
	@Autowired
	private
	ShareService shareService;
	/***********************************************************************************************
	 * Description：保存公选课排课结果
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-20
	 ***********************************************************************************************/
	public TimetableAppointment savePublicElectiveCourseTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, String courseNo, int groupId){		
		// 定义布尔变量，表征是否满足判冲条件
		boolean timeOK = true;
		// 把已选的周次和可用周次比对
		// 通过接口来查出可用的周次
		Integer[] validWeeks = outerApplicationServiceImpl.getValidWeeks(term, classes, labrooms, weekday, 0);
		for (int chosenWeek : weekArray) {
			if (!EmptyUtil.isIntegerArray(validWeeks, chosenWeek)) {// 所选的周次有不包含在可用周次范围内的
				timeOK = false;
			}
		}
		SchoolCourseDetail schoolCourseDetail = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseNo);
		if (timeOK) {// 时间、地点合法，允许排课
			// 周次进行排序
			String[] sWeek = this.getTimetableWeekClass(weekArray);
			
			// 节次进行排序
			String[] sClasses = this.getTimetableWeekClass(classes);
			// 判断有没有跳节、跳周
			boolean jumpTime = false; // 默认没有
			if (sWeek.length > 1 || sClasses.length > 1) {
				jumpTime = true;
			}
			// 根据编号找到对应教务课程
			SchoolCourseDetail course = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseNo);
			// 保存排课主表
			TimetableAppointment timetableAppointment = new TimetableAppointment();
			// 保存操作时间
			timetableAppointment.setCreatedDate(Calendar.getInstance());
			timetableAppointment.setUpdatedDate(Calendar.getInstance());
			// 设置排课的选课组编号
			timetableAppointment.setCourseCode(course.getSchoolCourse().getCourseCode());
			timetableAppointment.setSchoolCourse(course.getSchoolCourse());
			timetableAppointment.setSchoolCourseInfo(course.getSchoolCourse().getSchoolCourseInfo());
			timetableAppointment.setSchoolCourseDetail(course);
			// 生成主表公用数据
			timetableAppointment = this.saveTimetableMain(timetableAppointment, sWeek, sClasses, weekday);
		
				timetableAppointment.setTimetableStyle(23);
			// 保存主表
			timetableAppointment = timetableAppointmentDAO.store(timetableAppointment);
			// 保存排课关联表
			// 1 保存排课跳周表（如果有的话）
			if (jumpTime) {
				this.saveTimetableAppointmentSameNumber(timetableAppointment, sWeek, sClasses);
			}
			// 2 保存教师
			//this.saveTimetableTeacherRelated(timetableAppointment, teachers);
			if(timetableAppointment.getTimetableTeacherRelateds() == null || timetableAppointment.getTimetableTeacherRelateds() != null && timetableAppointment.getTimetableTeacherRelateds().size() == 0){
				if(schoolCourseDetail.getUsers() != null){
					for(User u:schoolCourseDetail.getUsers()){
						TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
						timetableTeacherRelated.setUser(u);
						timetableTeacherRelated.setTimetableAppointment(timetableAppointment);
						timetableTeacherRelatedDAO.store(timetableTeacherRelated);
						break;
					}
					
				}
			}
			// 3 保存项目
			//this.saveTimetableItemRelated(timetableAppointment, items);
			// 4 保存实验室
			this.saveTimetableLabRelated(timetableAppointment, labrooms);
			
			// 接着保存分组信息
			Set<TimetableGroup> timetableGroupSet = new HashSet<TimetableGroup>();
			timetableGroupSet.add(timetableGroupDAO.findTimetableGroupById(groupId));
			timetableAppointment.setTimetableGroups(timetableGroupSet);
			// 保存主表
			timetableAppointment = timetableAppointmentDAO.store(timetableAppointment);
			// 返回成功
			return timetableAppointment;
		}else {// 该时间、该实验室已经被占用，不能排课
			// 返回失败
			return new TimetableAppointment();
		}
		
	}
	
	
	/***********************************************************************************************
	 * Description：保存公选课排课结果（合班）
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-20
	 ***********************************************************************************************/
	public TimetableAppointment savePublicElectiveCourseTimetableInMerge(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, Integer mergeId, int groupId){		
		// 定义布尔变量，表征是否满足判冲条件
		boolean timeOK = true;
		SchoolCourseMerge schoolCourseMerge = schoolCourseMergeDAO.findSchoolCourseMergeById(mergeId);
		// 把已选的周次和可用周次比对
		// 通过接口来查出可用的周次
		Integer[] validWeeks = outerApplicationServiceImpl.getValidWeeks(term, classes, labrooms, weekday, 0);
		for (int chosenWeek : weekArray) {
			if (!EmptyUtil.isIntegerArray(validWeeks, chosenWeek)) {// 所选的周次有不包含在可用周次范围内的
				timeOK = false;
			}
		}
		
		if (timeOK) {// 时间、地点合法，允许排课
			// 周次进行排序
			String[] sWeek = this.getTimetableWeekClass(weekArray);
			
			// 节次进行排序
			String[] sClasses = this.getTimetableWeekClass(classes);
			// 判断有没有跳节、跳周
			boolean jumpTime = false; // 默认没有
			if (sWeek.length > 1 || sClasses.length > 1) {
				jumpTime = true;
			}
			// 根据编号找到对应教务课程
			SchoolCourseMerge merge = schoolCourseMergeDAO.findSchoolCourseMergeById(mergeId);
			// 保存排课主表
			TimetableAppointment timetableAppointment = new TimetableAppointment();
			// 保存操作时间
			timetableAppointment.setCreatedDate(Calendar.getInstance());
			timetableAppointment.setUpdatedDate(Calendar.getInstance());
			// 设置排课的选课组编号
			//timetableAppointment.setCourseCode(course.getSchoolCourse().getCourseCode());
			//timetableAppointment.setSchoolCourse(course.getSchoolCourse());
			//timetableAppointment.setSchoolCourseInfo(course.getSchoolCourse().getSchoolCourseInfo());
			//timetableAppointment.setSchoolCourseDetail(course);
			timetableAppointment.setSchoolCourseMerge(merge);
			// 生成主表公用数据
			timetableAppointment = this.saveTimetableMain(timetableAppointment, sWeek, sClasses, weekday);
		
				timetableAppointment.setTimetableStyle(23);
			// 保存主表
			timetableAppointment = timetableAppointmentDAO.store(timetableAppointment);
			// 保存排课关联表
			// 1 保存排课跳周表（如果有的话）
			if (jumpTime) {
				this.saveTimetableAppointmentSameNumber(timetableAppointment, sWeek, sClasses);
			}
			// 2 保存教师
			//this.saveTimetableTeacherRelated(timetableAppointment, teachers);
			// 3 保存项目
			//this.saveTimetableItemRelated(timetableAppointment, items);
			if(timetableAppointment.getTimetableTeacherRelateds() == null || timetableAppointment.getTimetableTeacherRelateds() != null && timetableAppointment.getTimetableTeacherRelateds().size() == 0){
				if(schoolCourseMerge.getSchoolCourseDetails() != null){
					for(SchoolCourseDetail scd:schoolCourseMerge.getSchoolCourseDetails()){
						if(scd.getUsers() != null){
							for(User u:scd.getUsers()){
								TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
								timetableTeacherRelated.setUser(u);
								timetableTeacherRelated.setTimetableAppointment(timetableAppointment);
								timetableTeacherRelatedDAO.store(timetableTeacherRelated);
								break;
							}
						}
						break;
					}
				}
			}
			// 4 保存实验室
			this.saveTimetableLabRelated(timetableAppointment, labrooms);
			
			// 接着保存分组信息
			Set<TimetableGroup> timetableGroupSet = new HashSet<TimetableGroup>();
			timetableGroupSet.add(timetableGroupDAO.findTimetableGroupById(groupId));
			timetableAppointment.setTimetableGroups(timetableGroupSet);
			// 保存主表
			timetableAppointment = timetableAppointmentDAO.store(timetableAppointment);
			// 返回成功
			return timetableAppointment;
		}else {// 该时间、该实验室已经被占用，不能排课
			// 返回失败
			return new TimetableAppointment();
		}
		
	}
	
	/***********************************************************************************************
	 * Description：排课模块{保存分组排课}
	 * 
	 * @author：贺子龙
	 * @Date：2016-08-31
	 ***********************************************************************************************/
	public TimetableAppointment saveGroupTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, int[] items, String teachers, String courseNo, int groupId, Integer isAdmin){
		// 创建对象
		TimetableAppointment timetableAppointment = new TimetableAppointment();
		// 分组与不分组之间的差别就在于分组信息的保存
		// 所以，首先利用已经写好的方法，将除分组信息之外的其他信息保存
		// 按isAdmin分两种情况：
		if (!EmptyUtil.isIntegerEmpty(isAdmin) && isAdmin.equals(1)) {// 教务分组
			timetableAppointment = this.savePublicElectiveCourseTimetable(term, classes, labrooms, weekArray, weekday,  courseNo,groupId);
		}
		// 判断是否保存成功
		if (timetableAppointment.getId()!=null) {// 保存成功
			if (!EmptyUtil.isIntegerEmpty(isAdmin) && isAdmin.equals(1)) {// 教务分组
				timetableAppointment.setTimetableStyle(24);
			}else {
				timetableAppointment.setTimetableStyle(26);
			}
			// 接着保存分组信息
			Set<TimetableGroup> timetableGroupSet = new HashSet<TimetableGroup>();
	        timetableGroupSet.add(timetableGroupDAO.findTimetableGroupById(groupId));
	        timetableAppointment.setTimetableGroups(timetableGroupSet);
	        timetableAppointmentDAO.store(timetableAppointment);
	        timetableAppointmentDAO.flush();
		}else {// 保存不成功
			// do nothing
		}
		return timetableAppointment;
	}
	
	/*************************************************************************************
	 * Description：排课保存公用方法{生成主表字段，除去选课组关联（自主排课选课组和教务排课选课组不同）}
	 * @author： 贺子龙
	 * @Date：2016-09-01
	 *************************************************************************************/
	public TimetableAppointment saveTimetableMain(TimetableAppointment timetableAppointment, 
			String[] sWeek, String[] sClasses, int weekday){
		// 保存操作时间
		timetableAppointment.setCreatedDate(Calendar.getInstance());
		timetableAppointment.setUpdatedDate(Calendar.getInstance());
		// 设置时间
		// 设置星期
		timetableAppointment.setWeekday(weekday);
		// 没有跳周、跳节
		// 设置周次
		int startWeek = 0;
		int endWeek = 0;
		if (sWeek[0].indexOf(("-")) == -1) {// 判断是否只有一周
			startWeek = Integer.parseInt(sWeek[0]);
			endWeek = Integer.parseInt(sWeek[0]);
		} else {// 如果是一个区间
			startWeek = Integer.parseInt(sWeek[0].split("-")[0]);
			endWeek = Integer.parseInt(sWeek[0].split("-")[1]);
		}
		timetableAppointment.setTotalWeeks(String.valueOf(( endWeek - startWeek + 1)));
		timetableAppointment.setStartWeek(startWeek);
		timetableAppointment.setEndWeek(endWeek);
		// 设置节次
		int startClass = 0;
		int endClass = 0;
		if (sClasses[0].indexOf(("-")) == -1) {// 判断是否只有一节
			startClass = Integer.parseInt(sClasses[0]);
			endClass = Integer.parseInt(sClasses[0]);
		} else {// 如果是一个区间
			startClass = Integer.parseInt(sClasses[0].split("-")[0]);
			endClass = Integer.parseInt(sClasses[0].split("-")[1]);
		}
		timetableAppointment.setTotalClasses(endClass - startClass + 1);
		timetableAppointment.setStartClass(startClass);
		timetableAppointment.setEndClass(endClass);
		// 保存排课状态为待发布
		timetableAppointment.setStatus(10);
		return timetableAppointment;
	}
	
	
	/*************************************************************************************
	 * Description：排课保存公用方法{保存跳周}
	 * @author： 贺子龙
	 * @Date：2016-09-01
	 *************************************************************************************/
	public void saveTimetableAppointmentSameNumber(TimetableAppointment timetableAppointment, 
			String[] sWeek, String[] sClasses){
		for (int i = 0; i < sWeek.length; i++) {
			for (int j = 0; j < sClasses.length; j++) {
				TimetableAppointmentSameNumber sameNumber = new TimetableAppointmentSameNumber();
				sameNumber.setCreatedDate(Calendar.getInstance());
				sameNumber.setUpdatedDate(Calendar.getInstance());
				// 设置跳周
				int startWeekSame = 0;
				int endWeekSame = 0;
				if (sWeek[i].indexOf(("-")) == -1) {// 判断是否只有一周
					startWeekSame = Integer.parseInt(sWeek[i]);
					endWeekSame = Integer.parseInt(sWeek[i]);
				} else {// 如果是一个区间
					startWeekSame = Integer.parseInt(sWeek[i].split("-")[0]);
					endWeekSame = Integer.parseInt(sWeek[i].split("-")[1]);
				}
				sameNumber.setTotalWeeks(String.valueOf(endWeekSame - startWeekSame + 1));
				sameNumber.setStartWeek(startWeekSame);
				sameNumber.setEndWeek(endWeekSame);
				// 设置跳节
				int startClassSame = 0;
				int endClassSame = 0;
				if (sClasses[j].indexOf(("-")) == -1) {// 判断是否只有一节
					startClassSame = Integer.parseInt(sClasses[j]);
					endClassSame = Integer.parseInt(sClasses[j]);
				} else {// 如果是一个区间
					startClassSame = Integer.parseInt(sClasses[j].split("-")[0]);
					endClassSame = Integer.parseInt(sClasses[j].split("-")[1]);
				}
				sameNumber.setTotalClasses(endClassSame - startClassSame + 1);
				sameNumber.setStartClass(startClassSame);
				sameNumber.setEndClass(endClassSame);
				sameNumber.setTimetableAppointment(timetableAppointment);
				timetableAppointmentSameNumberDAO.store(sameNumber);
				timetableAppointmentSameNumberDAO.flush();
			}
		}
	}
	
	/*************************************************************************************
	 * Description：排课保存公用方法{保存教师}
	 * @author： 贺子龙
	 * @Date：2016-09-01
	 *************************************************************************************/
	public void saveTimetableTeacherRelated(TimetableAppointment timetableAppointment, String teacherString){
		// 转化为字符数组
		String[] teachers = teacherString.split(",");
		// 新建关联表
		TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
		// 保存关联表
		if (teachers!=null&&teachers.length > 0) {
			for (int i = 0; i < teachers.length; i++) {
				timetableTeacherRelated.setUser(userDAO.findUserByUsername(teachers[i]));
				timetableTeacherRelated.setTimetableAppointment(timetableAppointment);
				timetableTeacherRelatedDAO.store(timetableTeacherRelated);
				timetableTeacherRelatedDAO.flush();
			}
		}
	}
	
	/*************************************************************************************
	 * Description：排课保存公用方法{保存项目}
	 * @author： 贺子龙
	 * @Date：2016-09-01
	 *************************************************************************************/
	public void saveTimetableItemRelated(TimetableAppointment timetableAppointment, int[] items){
		// 新建关联表
		TimetableItemRelated timetableItemRelated = new TimetableItemRelated();
		// 保存关联表
		if (items!=null&&items.length > 0) {
			for (int i = 0; i < items.length; i++) {
				timetableItemRelated.setOperationItem(operationItemDAO.findOperationItemById(items[i]));
				timetableItemRelated.setTimetableAppointment(timetableAppointment);
				timetableItemRelatedDAO.store(timetableItemRelated);
				timetableItemRelatedDAO.flush();
			}
		}
	}
	
	/*************************************************************************************
	 * Description：排课保存公用方法{保存实验室}
	 * @author： 贺子龙
	 * @Date：2016-09-01
	 *************************************************************************************/
	public void saveTimetableLabRelated(TimetableAppointment timetableAppointment, int[] labRooms){
		// 新建关联表
		TimetableLabRelated timetableLabRelated = new TimetableLabRelated();
		if (labRooms != null && labRooms.length > 0) {
			for (int labroomId : labRooms) {
				// 获取实验室
				LabRoom labRoom = labRoomDAO.findLabRoomById(labroomId);
				// 设置实验室
				timetableLabRelated.setLabRoom(labRoom);
				timetableLabRelated.setTimetableAppointment(timetableAppointment);
				timetableLabRelatedDAO.store(timetableLabRelated);
				timetableLabRelatedDAO.flush();
			}
		}
	}
	
	/*************************************************************************************
	 * Description：排课保存公用方法{返回排课节次或者周次}
	 * eg：[234,235,236]--->[234-236] [234,235,237,238]--->[234-235,237-238]
	 * @author： 魏誠
	 * @Date：2014-08-5
	 *************************************************************************************/
	public String[] getTimetableWeekClass(int[] intWeeks) {
		String startWeek = "1";
		String endWeek = "1";
		String sWeek = "";
		Arrays.sort(intWeeks);
		// 创建根据学期来查询课程；
		for (int i = 0; i < intWeeks.length; i++) {

			if (i == 0) {
				startWeek = String.valueOf(intWeeks[i]);
				if (intWeeks.length == 1) {
					sWeek = startWeek + ";";
				}
			} else {
				if (intWeeks[i] - intWeeks[i - 1] == 1) {
					if (i == intWeeks.length - 1) {
						endWeek = String.valueOf(intWeeks[i]);
						sWeek = sWeek + startWeek + "-" + endWeek + ";";
					} else {
						continue;
					}
				} else if (intWeeks[i] - intWeeks[i - 1] > 1 && intWeeks.length > i + 1) {
					endWeek = String.valueOf(intWeeks[i - 1]);
					sWeek = sWeek + startWeek + "-" + endWeek + ";";
					startWeek = String.valueOf(intWeeks[i]);

				} else if (intWeeks[i] - intWeeks[i - 1] > 1 && intWeeks.length == i + 1) {
					endWeek = String.valueOf(intWeeks[i - 1]);
					sWeek = sWeek + startWeek + "-" + endWeek + ";";
					sWeek = sWeek + String.valueOf(intWeeks[i]) + "-" + String.valueOf(intWeeks[i]);
				}
			}
		}
		return sWeek.split(";");
	}

	/***********************************************************************************************
	 * Description：保存基础课排课结果
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-20
	 ***********************************************************************************************/
	public Map<String, Object> saveSpecializedBasicCourseTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, String courseNo ,int[] item, String teacher, Integer groupId){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer oldAppointmentId = -1;
		// 定义布尔变量，表征是否满足判冲条件
		boolean timeOK = true;
		// 把已选的周次和可用周次比对
		// 通过接口来查出可用的周次
		Integer[] validWeeks = outerApplicationServiceImpl.getValidWeeks(term, classes, labrooms, weekday, 0);
		for (int chosenWeek : weekArray) {
			if (!EmptyUtil.isIntegerArray(validWeeks, chosenWeek)) {// 所选的周次有不包含在可用周次范围内的
				timeOK = false;
			}
		}
		
		if (timeOK) {// 时间、地点合法，允许排课
			String addClass = "";
			//找到同一组下针对同一周同一天的排课
			TimetableAppointment t = newTimetableCourseSchedulingService.findTimetableAppointmentByGroupIdAndSameWeekAndWeekday(groupId, weekArray[0], weekday);
			if(timetableGroupDAO.findTimetableGroupById(groupId).getTimetableStyle() == 28){
				t = newTimetableCourseSchedulingService.findTimetableAppointmentByGroupIdAndSameWeekAndWeekdayAndItem(groupId, weekArray[0], weekday, item[0]);
			}
			if(t != null){
				
				if(t.getTimetableAppointmentSameNumbers() == null || t.getTimetableAppointmentSameNumbers() != null && t.getTimetableAppointmentSameNumbers().size() == 0){
					for(Integer i=t.getStartClass(); i <= t.getEndClass(); i++){
							addClass += "," + i;
					}
				}else{
					for(TimetableAppointmentSameNumber ts:t.getTimetableAppointmentSameNumbers()){
						for(Integer i=ts.getStartClass(); i <= ts.getEndClass(); i++){
							addClass += "," + i;
						}
					}
				}
				oldAppointmentId = t.getId();
				timetableAppointmentDAO.remove(t);
			}
			
			
			// 周次进行排序
			String[] sWeek = this.getTimetableWeekClass(weekArray);
			//合并排课
			String s = "";
			for(int i = 0; i < classes.length; i++){
				if(i == 0){
					s += classes[i];
				}else{
					s += ","+classes[i];
				}
			}
			if(("," + s).equals(addClass)){
				classes = ConvertUtil.stringToIntArray(s);
			}
			else{
				classes = ConvertUtil.stringToIntArray(s+addClass);
			}
			// 节次进行排序
			String[] sClasses = this.getTimetableWeekClass(classes);
			// 判断有没有跳节、跳周
			boolean jumpTime = false; // 默认没有
			if (sWeek.length > 1 || sClasses.length > 1) {
				jumpTime = true;
			}
			// 根据编号找到对应教务课程
			SchoolCourseDetail course = schoolCourseDetailDAO.findSchoolCourseDetailByCourseDetailNo(courseNo);
			// 保存排课主表
			TimetableAppointment timetableAppointment = new TimetableAppointment();
			//if(t != null )timetableAppointment = t;
			// 保存操作时间
			timetableAppointment.setCreatedDate(Calendar.getInstance());
			timetableAppointment.setUpdatedDate(Calendar.getInstance());
			// 设置排课的选课组编号
			timetableAppointment.setCourseCode(course.getSchoolCourse().getCourseCode());
			timetableAppointment.setSchoolCourse(course.getSchoolCourse());
			timetableAppointment.setSchoolCourseInfo(course.getSchoolCourse().getSchoolCourseInfo());
			timetableAppointment.setSchoolCourseDetail(course);
			// 生成主表公用数据
			timetableAppointment = this.saveTimetableMain(timetableAppointment, sWeek, sClasses, weekday);
		
			timetableAppointment.setTimetableStyle(timetableGroupDAO.findTimetableGroupById(groupId).getTimetableStyle());

			// 接着保存分组信息
			Set<TimetableGroup> timetableGroupSet = new HashSet<TimetableGroup>();
			timetableGroupSet.add(timetableGroupDAO.findTimetableGroupById(groupId));
			timetableAppointment.setTimetableGroups(timetableGroupSet);
			// 保存主表
			timetableAppointment = timetableAppointmentDAO.store(timetableAppointment);
			
			// 保存排课关联表
			// 1 保存排课跳周表（如果有的话）
			if (jumpTime) {
				this.saveTimetableAppointmentSameNumber(timetableAppointment, sWeek, sClasses);
			}
			if(timetableAppointment.getTimetableTeacherRelateds() == null || timetableAppointment.getTimetableTeacherRelateds() != null && timetableAppointment.getTimetableTeacherRelateds().size() == 0){
				TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
				timetableTeacherRelated.setUser(shareService.getUser());
				timetableTeacherRelated.setTimetableAppointment(timetableAppointment);
				timetableTeacherRelatedDAO.store(timetableTeacherRelated);
			}
			// 2 保存教师
			//this.saveTimetableTeacherRelated(timetableAppointment, teacher);
			// 3 保存项目
			this.saveTimetableItemRelated(timetableAppointment, item);
			// 4 保存实验室
			this.saveTimetableLabRelated(timetableAppointment, labrooms);
			map.put("appointment", timetableAppointment);
			map.put("oldAppointmentId", oldAppointmentId);
			// 返回成功
			return map;
		}else {// 该时间、该实验室已经被占用，不能排课
			// 返回失败
			map.put("appointment", new TimetableAppointment());
			map.put("oldAppointmentId", oldAppointmentId);
			return map;
		}
		
	}
	
	
	/***********************************************************************************************
	 * Description：保存合班排课结果
	 * 
	 * @author：郑昕茹
	 * @Date：2017-04-26
	 ***********************************************************************************************/
	public Map<String,Object> saveMergeCourseTimetableCourseTimetable(int term, int[] classes, int[] labrooms, int[] weekArray, 
			int weekday, Integer mergeId ,int[] item, String teacher, Integer groupId){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer oldAppointmentId = -1;
		// 定义布尔变量，表征是否满足判冲条件
		boolean timeOK = true;
		// 把已选的周次和可用周次比对
		// 通过接口来查出可用的周次
		Integer[] validWeeks = outerApplicationServiceImpl.getValidWeeks(term, classes, labrooms, weekday, 0);
		for (int chosenWeek : weekArray) {
			if (!EmptyUtil.isIntegerArray(validWeeks, chosenWeek)) {// 所选的周次有不包含在可用周次范围内的
				timeOK = false;
			}
		}
		TimetableGroup timetableGroup = timetableGroupDAO.findTimetableGroupById(groupId);
		if (timeOK) {// 时间、地点合法，允许排课
			String addClass = "";
			//找到同一组下针对同一周同一天的排课
			TimetableAppointment t = newTimetableCourseSchedulingService.findTimetableAppointmentByGroupIdAndSameWeekAndWeekday(groupId, weekArray[0], weekday);
			if(timetableGroup.getTimetableStyle() == 26){
				t = newTimetableCourseSchedulingService.findTimetableAppointmentByGroupIdAndSameWeekAndWeekdayAndItem(groupId, weekArray[0], weekday, item[0]);
			}
			if(t != null){
				
				if(t.getTimetableAppointmentSameNumbers() == null || t.getTimetableAppointmentSameNumbers() != null && t.getTimetableAppointmentSameNumbers().size() == 0){
					for(Integer i=t.getStartClass(); i <= t.getEndClass(); i++){
							addClass += "," + i;
					}
				}else{
					for(TimetableAppointmentSameNumber ts:t.getTimetableAppointmentSameNumbers()){
						for(Integer i=ts.getStartClass(); i <= ts.getEndClass(); i++){
							addClass += "," + i;
						}
					}
				}
				oldAppointmentId = t.getId();
				timetableAppointmentDAO.remove(t);
			}
			// 周次进行排序
			String[] sWeek = this.getTimetableWeekClass(weekArray);
			//合并排课
			String s = "";
			for(int i = 0; i < classes.length; i++){
				if(i == 0){
					s += classes[i];
				}else{
					s += ","+classes[i];
				}
			}
			if(("," + s).equals(addClass)){
				classes = ConvertUtil.stringToIntArray(s);
			}
			else{
				classes = ConvertUtil.stringToIntArray(s+addClass);
			}
			// 节次进行排序
			String[] sClasses = this.getTimetableWeekClass(classes);
			// 判断有没有跳节、跳周
			boolean jumpTime = false; // 默认没有
			if (sWeek.length > 1 || sClasses.length > 1) {
				jumpTime = true;
			}
			// 保存排课主表
			TimetableAppointment timetableAppointment = new TimetableAppointment();
			//if(t != null )timetableAppointment = t;
			// 保存操作时间
			timetableAppointment.setCreatedDate(Calendar.getInstance());
			timetableAppointment.setUpdatedDate(Calendar.getInstance());
			// 设置排课的选课组编号
			timetableAppointment.setSchoolCourseMerge(schoolCourseMergeDAO.findSchoolCourseMergeById(mergeId));
			// 生成主表公用数据
			timetableAppointment = this.saveTimetableMain(timetableAppointment, sWeek, sClasses, weekday);
		
			timetableAppointment.setTimetableStyle(timetableGroupDAO.findTimetableGroupById(groupId).getTimetableStyle());

			// 接着保存分组信息
			Set<TimetableGroup> timetableGroupSet = new HashSet<TimetableGroup>();
			timetableGroupSet.add(timetableGroupDAO.findTimetableGroupById(groupId));
			timetableAppointment.setTimetableGroups(timetableGroupSet);
			// 保存主表
			timetableAppointment = timetableAppointmentDAO.store(timetableAppointment);
			
			// 保存排课关联表
			// 1 保存排课跳周表（如果有的话）
			if (jumpTime) {
				this.saveTimetableAppointmentSameNumber(timetableAppointment, sWeek, sClasses);
			}
			// 2 保存教师
			//this.saveTimetableTeacherRelated(timetableAppointment, teacher);
			if(timetableAppointment.getTimetableTeacherRelateds() == null || timetableAppointment.getTimetableTeacherRelateds() != null && timetableAppointment.getTimetableTeacherRelateds().size() == 0){
				TimetableTeacherRelated timetableTeacherRelated = new TimetableTeacherRelated();
				timetableTeacherRelated.setUser(shareService.getUser());
				timetableTeacherRelated.setTimetableAppointment(timetableAppointment);
				timetableTeacherRelatedDAO.store(timetableTeacherRelated);
			}
			// 3 保存项目
			this.saveTimetableItemRelated(timetableAppointment, item);
			// 4 保存实验室
			this.saveTimetableLabRelated(timetableAppointment, labrooms);
			
			// 返回成功
			map.put("appointment", timetableAppointment);
			map.put("oldAppointmentId", oldAppointmentId);
			return map;
		}else {// 该时间、该实验室已经被占用，不能排课
			// 返回失败
			map.put("appointment", new TimetableAppointment());
			map.put("oldAppointmentId", oldAppointmentId);
			return map;
		}
		
	}
	
	
	/*
	 * description:根据appointId找到其下所有的跨节排课
	 * @author:郑昕茹
	 * 
	 */
	public List<TimetableAppointmentSameNumber> findSameNumbersByAppointmentId(Integer appointmentId){
		String sql = "select t from TimetableAppointmentSameNumber t where 1=1";
		sql += " and t.timetableAppointment.id ="+appointmentId;
		return timetableAppointmentSameNumberDAO.executeQuery(sql, 0, -1);
	}
}
