package net.xidlims.service.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.xidlims.constant.SchoolConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.TimetableAppointmentSameNumber;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OuterApplicationService")
public class OuterApplicationServiceImpl implements OuterApplicationService {

	@Autowired
	private ShareService shareService;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private SchoolCourseDetailService schoolCourseDetailService;

	/************************************************************
	 * @ 获取可排的实验分室列表 @ 作者：魏诚 @ 日期：2014-07-24
	 ************************************************************/
	public Map<Integer, String> getLabRoomMap(int iLabCenter) {
		Map<Integer, String> labRoomMap = new LinkedHashMap<Integer, String>(0);
		String academyNumber = "";
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		if (iLabCenter != -1) {
			// 获取选择的实验中心
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		} else {
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		}

		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer sql = new StringBuffer("select c from LabRoom c "
				//+ "where c.CDictionaryByLabRoom.CCategory='c_lab_room_type' and c.CDictionaryByLabRoom.CNumber = '1' and c.labAnnex.labCenter.schoolAcademy.academyNumber like '"
				+ "where c.CDictionaryByLabRoom.CCategory='c_lab_room_type' and c.CDictionaryByLabRoom.CNumber = '1'"
				+ " and c.isUsed=1 ");

		List<LabRoom> list = labRoomDAO.executeQuery(sql.toString());
		// 遍历实验分室
		for (LabRoom labRoom : list) {
			labRoomMap.put(labRoom.getId(),labRoom.getLabRoomName());
		}
		return labRoomMap;
	}

	/************************************************************
	 * @ 获取可排的实验项目列表 @ 作者：魏诚 @ 日期：2014-07-24
	 ************************************************************/
	public Map<Integer, String> getTimetableItemMap(String courseNo) {
		Map<Integer, String> timetableItemMap = new HashMap<Integer, String>();
		// 获取有效的实验分室列表-根据登录用户的所属学院
		String sql = "select c from OperationItem c "
				+ "where c.schoolCourseInfo.courseNumber like '" + courseNo + "'";
		
		sql += " and c.CDictionaryByLpStatusCheck.id = 545";
		List<OperationItem> list = new ArrayList<OperationItem>(operationItemDAO.executeQuery(sql));
		// 遍历实验分室
		for (OperationItem operationItem : list) {
			/*timetableItemMap.put(operationItem.getId(), operationItem.getItemName()*/
					timetableItemMap.put(operationItem.getId(), operationItem.getLpName()
			/*
			 * + ";" + operationItem.getItemNumber() + ";" +
			 * operationItem.getSchoolAcademyByCollege() .getAcademyName()
			 */);
		}
		return timetableItemMap;
	}

	/************************************************************
	 * @ 根据选课组编号获取可排的实验项目列表 @ 作者：魏诚 @ 日期：2014-07-24
	 ************************************************************/
	public List<OperationItem> getCourseCodeItemList(String courseCode) {
		// 获取有效的实验分室列表-根据登录用户的所属学院
		String sql = "select c from OperationItem c "
				+ "where c.labRoom.labCenter.schoolAcademy.academyNumber like '"
				+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber()
						.substring(0, SchoolConstantInterface.INT_SCHOOLACADEMY_NUMBER) + "'";
//				+ " and c.schoolCourseByClassNo.courseCode like '" + courseCode + "'";
		List<OperationItem> list = new ArrayList<OperationItem>(operationItemDAO.executeQuery(sql));

		return list;
	}

	/************************************************************
	 * @根据选课组，获取可选的实验 列表
	 * @作者：魏诚
	 * @日期：2014-07-24
	 ************************************************************/
	public Map<String, String> getTimetableTearcherMap() {
		Map<String, String> timetableTearcherMap = new HashMap<String, String>();
		// 获取有效的实验分室列表-根据登录用户的所属学院
		String sql = "select c from User c " + "where c.userRole=1 and c.schoolAcademy.academyNumber like '"
				+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber() + "'";
		List<User> list = new ArrayList<User>(userDAO.executeQuery(sql, 0, -1));
		// 遍历实验分室
		for (User user : list) {
			timetableTearcherMap.put(user.getUsername(), user.getCname() + ";" + user.getUsername());
		}
		return timetableTearcherMap;
	}
	
	/************************************************************ 
	 * 获取可选的教师列表(以当前选课组所在学院查询该学院下所有老师)
	 * 作者：罗璇
	 * 日期：2017年3月22日
	 ************************************************************/
	public Map<String, String> getTimetableTearcherMapByCourseAcademy(String courseCode) {
		Map<String, String> timetableTearcherMap = new HashMap<String, String>();
		//根据courseCode找到选课组
		String academyNumber = schoolCourseDetailService.getSchoolCourseDetailByCourseCode(courseCode).get(0).getSchoolAcademy().getAcademyNumber();
		String sql = "select c from User c " + "where c.userRole=1 and c.schoolAcademy.academyNumber like '"
				+ academyNumber + "'";
		List<User> list = new ArrayList<User>(userDAO.executeQuery(sql, 0, -1));
		// 遍历实验分室
		for (User user : list) {
			timetableTearcherMap.put(user.getUsername(), user.getCname() + ";" + user.getUsername());
		}
		return timetableTearcherMap;
	}

	/************************************************************
	 * @获取可选的教学周
	 * @作者：魏诚
	 * @日期：2014-07-24
	 ************************************************************/
	public Integer[] getValidWeeks(int term, int classids, int labroom, int weekday) {

		// 获取当前学期的可用周次数组
		List<SchoolWeek> weeklist = schoolWeekDAO.executeQuery("select c from SchoolWeek c where schoolTerm.id = "
				+ term + " group by c.week order by c.week asc");
		// 相关list转换的数组
		Integer[] allWeeks = new Integer[weeklist.size()];
		int iWeek = 0;
		for (SchoolWeek schoolWeek : weeklist) {
			allWeeks[iWeek] = schoolWeek.getWeek();
			iWeek++;
		}
		// 获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer sql = new StringBuffer(
				"select c from TimetableLabRelated c where 1=1 and c.timetableAppointment.schoolCourse.schoolTerm.id ="
						+ term + " and c.timetableAppointment.weekday =" + weekday);
		if (classids <= 4) {
			sql.append(" and c.labRoom.id =" + String.valueOf(labroom));
		} else {

		}
		List<TimetableLabRelated> list = timetableLabRelatedDAO.executeQuery(sql.toString());
		// String weeks ="";
		String weeks = "";
		for (TimetableLabRelated timetableLabRelated : list) {
			if (timetableLabRelated.getTimetableAppointment().getStartClass() >= classids * 2 - 1
					&& timetableLabRelated.getTimetableAppointment().getStartClass() <= classids * 2
					&& timetableLabRelated.getTimetableAppointment().getWeekday() >= weekday
					|| timetableLabRelated.getTimetableAppointment().getEndClass() >= classids * 2 - 1
					&& timetableLabRelated.getTimetableAppointment().getEndClass() <= classids * 2
					&& timetableLabRelated.getTimetableAppointment().getWeekday() >= weekday) {
				for (int i = timetableLabRelated.getTimetableAppointment().getStartWeek(); i <= timetableLabRelated
						.getTimetableAppointment().getEndWeek();) {
					weeks = weeks + i + ";";
					i++;
				}
			}
		}
		if (!weeks.equals("")) {
			String[] sWeeks = weeks.split(";");

			int[] intWeeks = new int[sWeeks.length];
			// 获取可用的教学
			for (int i = 0; i < intWeeks.length; i++) {
				intWeeks[i] = Integer.parseInt(sWeeks[i]);
			}

			// 数据排序去重
			Arrays.sort(intWeeks);

			String strWeek = "";
			Integer[] newIntWeeks1 = this.getDistinct(intWeeks);
			// int j = allWeeks.length;
			for (int i = 0; i < allWeeks.length; i++) {
				int a = Arrays.binarySearch(newIntWeeks1, allWeeks[i]);
				if (a < 0) {
					strWeek = strWeek + allWeeks[i] + ";";
				}
			}
			Integer[] newIntWeeks = new Integer[strWeek.split(";").length];
			if (newIntWeeks.length != 0 && newIntWeeks[0] != null) {

				for (int i = 0; i < strWeek.split(";").length; i++) {
					newIntWeeks[i] = Integer.parseInt(strWeek.split(";")[i]);

				}
			}
			return newIntWeeks;
		} else {
			return allWeeks;
		}

	}

	/************************************************************
	 * @获取可选的教学周
	 * @作者：魏诚
	 * @日期：2014-07-24
	 ************************************************************/
	public Integer[] getValidWeeks(int term, int[] classes, int[] labrooms, int weekday) {
		List<SchoolWeek> weeklist = schoolWeekDAO.executeQuery("select c from SchoolWeek c where schoolTerm.id = "
				+ term + " group by c.week order by c.week asc");
		// 相关list转换的数组
		Integer[] weeklists = new Integer[weeklist.size()];

		// 获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer selfSql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		StringBuffer sql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		if (labrooms!=null&&labrooms.length>0) {
			for (int labroom : labrooms) {
				if (shareService.isSpecialLabRoom(labroom)) {
					// 特殊实验室不参与判冲
				}else {
					sql.append("or ( c.timetableAppointment.schoolCourse.schoolTerm.id =" + term
							+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
					
					selfSql.append("or ( c.timetableAppointment.timetableSelfCourse.schoolTerm.id =" + term
							+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
				}
			}
		}
		// 教务课程排课数据
		List<TimetableLabRelated> list = timetableLabRelatedDAO.executeQuery(sql.toString());

		List<TimetableLabRelated> selfList = timetableLabRelatedDAO.executeQuery(selfSql.toString());
		list.addAll(selfList);
		// 自主排课数据
		// String weeks ="";
		String weeks = "";
		for (TimetableLabRelated timetableLabRelated : list) {
			boolean isIn = true;
			for (int intClass : classes) {
				if (timetableLabRelated.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() == 0) {
					isIn = timetableLabRelated.getTimetableAppointment().getEndClass() >= intClass
							&& timetableLabRelated.getTimetableAppointment().getStartClass() <= intClass;

					if (!isIn) {
						break;
					} else {
						for (int i = timetableLabRelated.getTimetableAppointment().getStartWeek(); i <= timetableLabRelated
								.getTimetableAppointment().getEndWeek();) {
							weeks = weeks + i + ";";
							i++;
						}
					}
				} else {
					for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : timetableLabRelated
							.getTimetableAppointment().getTimetableAppointmentSameNumbers()) {
						isIn = timetableAppointmentSameNumber.getEndClass() >= intClass
								&& timetableAppointmentSameNumber.getStartClass() <= intClass;

						if (!isIn) {
							break;
						} else {
							for (int i = timetableAppointmentSameNumber.getStartWeek(); i <= timetableAppointmentSameNumber
									.getEndWeek();) {
								weeks = weeks + i + ";";
								i++;
							}
						}

					}
				}

			}

		}
		if (!weeks.equals("")) {
			String[] sWeeks = weeks.split(";");

			int[] intWeeks = new int[sWeeks.length];
			// 获取可用的教学
			for (int i = 0; i < intWeeks.length; i++) {
				intWeeks[i] = Integer.parseInt(sWeeks[i]);
			}

			// 数据排序去重
			Arrays.sort(intWeeks);

			String strWeek = "";
			Integer[] newIntWeeks1 = this.getDistinct(intWeeks);

			for (int i = 0; i < weeklist.size(); i++) {
				int a = Arrays.binarySearch(newIntWeeks1, weeklist.get(i).getWeek());
				if (a < 0) {
					strWeek = strWeek + weeklist.get(i).getWeek() + ";";
				}
			}

			Integer[] newIntWeeks = new Integer[strWeek.split(";").length];
			if (strWeek.equals("")) {
				return newIntWeeks;
			}
			if (strWeek.split(";").length != 0 && strWeek.split(";")[0] != null) {

				for (int i = 0; i < strWeek.split(";").length; i++) {
					newIntWeeks[i] = Integer.parseInt(strWeek.split(";")[i]);

				}
			}
			return newIntWeeks;
		} else {
			// list转换为数组
			for (int i = 0; i < weeklist.size(); i++) {
				weeklists[i] = weeklist.get(i).getWeek();
			}
			return weeklists;
		}

	}
	/************************************************************
	 * @获取可选的教学周--编辑排课记录
	 * @作者：贺子龙
	 * @日期：2016-01-06
	 ************************************************************/
	public Integer[] getValidWeeks(int term, int[] classes, int[] labrooms, int weekday, int tableAppId) {
		List<SchoolWeek> weeklist = schoolWeekDAO.executeQuery("select c from SchoolWeek c where schoolTerm.id = "
				+ term + " group by c.week order by c.week asc");
		// 相关list转换的数组
		Integer[] weeklists = new Integer[weeklist.size()];
		
		// 获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer selfSql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		StringBuffer sql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		StringBuffer mergeSql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		if (labrooms!=null&&labrooms.length>0) {
			for (int labroom : labrooms) {
				if (shareService.isSpecialLabRoom(labroom)) {
					// 特殊实验室不参与判冲
				}else {
					sql.append("or ( c.timetableAppointment.schoolCourse.schoolTerm.id =" + term
							+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
					selfSql.append("or ( c.timetableAppointment.timetableSelfCourse.schoolTerm.id =" + term
							+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
					mergeSql.append("or ( c.timetableAppointment.schoolCourseMerge.termId =" + term
							+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
				}
			}
		}
		// 教务课程排课数据
		List<TimetableLabRelated> list = timetableLabRelatedDAO.executeQuery(sql.toString());
		
		List<TimetableLabRelated> selfList = timetableLabRelatedDAO.executeQuery(selfSql.toString());
		
		List<TimetableLabRelated> mergeList = timetableLabRelatedDAO.executeQuery(mergeSql.toString());
		list.addAll(selfList);
		list.addAll(mergeList);
		// 自主排课数据
		// String weeks ="";
		String weeks = "";
		for (TimetableLabRelated timetableLabRelated : list) {
			
			if (timetableLabRelated.getTimetableAppointment().getId()==tableAppId) {
				continue;//不与自身判冲
			}
			boolean isIn = true;
			for (int intClass : classes) {
				if (timetableLabRelated.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() == 0) {
					isIn = timetableLabRelated.getTimetableAppointment().getEndClass() >= intClass
							&& timetableLabRelated.getTimetableAppointment().getStartClass() <= intClass;
					
					if (!isIn) {
						break;
					} else {
						for (int i = timetableLabRelated.getTimetableAppointment().getStartWeek(); i <= timetableLabRelated
								.getTimetableAppointment().getEndWeek();) {
							weeks = weeks + i + ";";
							i++;
						}
					}
				} else {
					for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : timetableLabRelated
							.getTimetableAppointment().getTimetableAppointmentSameNumbers()) {
						isIn = timetableAppointmentSameNumber.getEndClass() >= intClass
								&& timetableAppointmentSameNumber.getStartClass() <= intClass;
						
						if (!isIn) {
							break;
						} else {
							for (int i = timetableAppointmentSameNumber.getStartWeek(); i <= timetableAppointmentSameNumber
									.getEndWeek();) {
								weeks = weeks + i + ";";
								i++;
							}
						}
						
					}
				}
				
			}
			
		}
		if (!weeks.equals("")) {
			String[] sWeeks = weeks.split(";");
			
			int[] intWeeks = new int[sWeeks.length];
			// 获取可用的教学
			for (int i = 0; i < intWeeks.length; i++) {
				intWeeks[i] = Integer.parseInt(sWeeks[i]);
			}
			
			// 数据排序去重
			Arrays.sort(intWeeks);
			
			String strWeek = "";
			Integer[] newIntWeeks1 = this.getDistinct(intWeeks);
			
			for (int i = 0; i < weeklist.size(); i++) {
				int a = Arrays.binarySearch(newIntWeeks1, weeklist.get(i).getWeek());
				if (a < 0) {
					strWeek = strWeek + weeklist.get(i).getWeek() + ";";
				}
			}
			
			Integer[] newIntWeeks = new Integer[strWeek.split(";").length];
			if (strWeek.equals("")) {
				return newIntWeeks;
			}
			if (strWeek.split(";").length != 0 && strWeek.split(";")[0] != null) {
				
				for (int i = 0; i < strWeek.split(";").length; i++) {
					newIntWeeks[i] = Integer.parseInt(strWeek.split(";")[i]);
					
				}
			}
			return newIntWeeks;
		} else {
			// list转换为数组
			for (int i = 0; i < weeklist.size(); i++) {
				weeklists[i] = weeklist.get(i).getWeek();
			}
			return weeklists;
		}
		
	}

	/************************************************************
	 * @获取可选的教学周-针对实验室预约
	 * @作者：魏诚
	 * @日期：2015-07-09
	 ************************************************************/
	public Integer[] getValidLabWeeks(int term, int[] classes, int[] labrooms, int weekday) {
		List<SchoolWeek> weeklist = schoolWeekDAO.executeQuery("select c from SchoolWeek c where schoolTerm.id = "
				+ term + " group by c.week order by c.week asc");
		// 相关list转换的数组
		Integer[] weeklists = new Integer[weeklist.size()];
		String invalidtemp = "";
		// 获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer selfSql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		StringBuffer sql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		for (int labroom : labrooms) {
			sql.append("or ( c.timetableAppointment.schoolCourse.schoolTerm.id =" + term
					+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");

			selfSql.append("or ( c.timetableAppointment.timetableSelfCourse.schoolTerm.id =" + term
					+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
		}
		// 教务课程排课数据
		List<TimetableLabRelated> list = timetableLabRelatedDAO.executeQuery(sql.toString(), 0, -1);

		List<TimetableLabRelated> selfList = timetableLabRelatedDAO.executeQuery(selfSql.toString(), 0, -1);
		list.addAll(selfList);
		// 自主排课数据
		// String weeks ="";
		String weeks = "";
		String invalidInt = "";
		if (list != null && list.size() > 0) {
			for (int intClass : classes) {
				for (TimetableLabRelated timetableLabRelated : list) {
					/* for (int intClass : classes) { */
					if (timetableLabRelated.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() == 0) {
						for (int i = timetableLabRelated.getTimetableAppointment().getStartClass(); i <= timetableLabRelated
								.getTimetableAppointment().getEndClass();) {
							if (i == intClass) {
								for (int j = timetableLabRelated.getTimetableAppointment().getStartWeek(); j <= timetableLabRelated
										.getTimetableAppointment().getEndWeek();) {
									weeks = weeks + j + ";";
									j++;
								}
							}
							i++;
						}
					} else {
						for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : timetableLabRelated
								.getTimetableAppointment().getTimetableAppointmentSameNumbers()) {

							for (int i = timetableAppointmentSameNumber.getStartWeek(); i <= timetableAppointmentSameNumber
									.getEndWeek();) {
								// 所有实验室已占用weeks字符串列表
								weeks = weeks + i + ";";
								i++;
							}
						}
					}
				}
				// 字符串转换为数字数组并排序
				String[] strWeeks = weeks.split(";");
				int[] iWeeks = new int[strWeeks.length];
				// 获取可用的教学
				if (iWeeks.length > 0) {
					for (int i = 0; i < iWeeks.length; i++) {
						if (strWeeks[i] != null && !strWeeks[i].equals("")) {
							iWeeks[i] = Integer.parseInt(strWeeks[i]);
						}
					}
				}
				// 数据排序去重
				Arrays.sort(iWeeks);

				// 判断冲突的周次是否在并发labreservation_number之内，如果是则排除，如果不是则确定冲突
				int temp = -1;
				int count = 1;
                //设置循环步进值
				int step = 1;
                for(int invalidNum:iWeeks){
                	if(temp==-1){
                		count = count +1;
                	}
                	if(temp == invalidNum){
                		count = count +1;
                	}else{
                		for (int labroom : labrooms) {
	                		if(count < labRoomDAO.findLabRoomById(labroom).getReservationNumber()){
	                			
	                		}else{
	                			invalidtemp = invalidtemp + temp +";";
	                		}
                		}
                		count=1;
                	}
                	//如果是最后一条记录
                	if(step==iWeeks.length){
                		for (int labroom : labrooms) {
	                		if(count < labRoomDAO.findLabRoomById(labroom).getReservationNumber()){
	                			
	                		}else{
	                			invalidtemp = invalidtemp + temp +";";
	                		}
                		}
                	}
                	temp = invalidNum;
                	step++;
                }
				System.out.println("不可用周次：" + invalidtemp);
				invalidInt = invalidInt + invalidtemp;
                weeks ="";
			}
		}
		// 字符串转换为数字数组并排序
		String[] strWeeks = invalidInt.split(";");
		int[] iWeeks = new int[strWeeks.length];
		// 获取可用的教学
		if (iWeeks.length > 0) {
			for (int i = 0; i < iWeeks.length; i++) {
				if (strWeeks[i] != null && !strWeeks[i].equals("")) {
					iWeeks[i] = Integer.parseInt(strWeeks[i]);
				}
			}
		}
		// 数据排序去重
		Arrays.sort(iWeeks);

		// 判断冲突的周次是否在并发labreservation_number之内，如果是则排除，如果不是则确定冲突
		for (Integer weektmp = 0; weektmp < weeklist.size(); weektmp++) {

			int count = 0;
			for (int a : iWeeks) {
				if (a == weektmp) {
					count = count + 1;
				}
			}

			for (int labroom : labrooms) {
				LabRoom lab = labRoomDAO.findLabRoomById(1316);
				if (count < labRoomDAO.findLabRoomById(labroom).getReservationNumber()) {
					String sWeektmp = weektmp + ";";
					weeks = weeks.replaceAll(sWeektmp, "");
				}
			}

			// weektmp = weektmp + 1;
		}

		//System.out.println("周次：" + weeks);
		if (!invalidInt.equals("")) {
			String[] sWeeks = invalidInt.split(";");

			int[] intWeeks = new int[sWeeks.length];
			// 获取可用的教学
			for (int i = 0; i < intWeeks.length; i++) {
				intWeeks[i] = Integer.parseInt(sWeeks[i]);
			}

			// 数据排序去重
			Arrays.sort(intWeeks);

			String strWeek = "";
			Integer[] newIntWeeks1 = this.getDistinct(intWeeks);

			for (int i = 0; i < weeklist.size(); i++) {
				int a = Arrays.binarySearch(newIntWeeks1, weeklist.get(i).getWeek());
				if (a < 0) {
					strWeek = strWeek + weeklist.get(i).getWeek() + ";";
				}
			}

			Integer[] newIntWeeks = new Integer[strWeek.split(";").length];
			if (strWeek.equals("")) {
				return newIntWeeks;
			}
			if (strWeek.split(";").length != 0 && strWeek.split(";")[0] != null) {

				for (int i = 0; i < strWeek.split(";").length; i++) {
					newIntWeeks[i] = Integer.parseInt(strWeek.split(";")[i]);

				}
			}
			return newIntWeeks;
		} else {
			// list转换为数组
			for (int i = 0; i < weeklist.size(); i++) {
				weeklists[i] = weeklist.get(i).getWeek();
			}
			return weeklists;
		}

	}

	/*************************************************************************************
	 * @內容：判断选课资源是否冲突
	 * @作者： 魏誠
	 * @日期：2015-03-10
	 *************************************************************************************/
	@SuppressWarnings("unused")
	public boolean isTimetableConflict(int term, int weekday, int[] labrooms, int[] classes,int[] weeks) {
		List<SchoolWeek> weeklist = schoolWeekDAO.executeQuery("select c from SchoolWeek c where schoolTerm.id = "
				+ term + " group by c.week order by c.week asc");
		// 相关list转换的数组
		Integer[] weeklists = new Integer[weeklist.size()];

		// 获取有效的实验分室列表-根据登录用户的所属学院
		StringBuffer selfSql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		StringBuffer sql = new StringBuffer("select c from TimetableLabRelated c where 1=0 ");
		for (int labroom : labrooms) {
			
				sql.append("or ( c.timetableAppointment.schoolCourse.schoolTerm.id =" + term
						+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
				
				selfSql.append("or ( c.timetableAppointment.timetableSelfCourse.schoolTerm.id =" + term
						+ " and c.timetableAppointment.weekday =" + weekday + " and c.labRoom.id =" + labroom + ") ");
			
		}
		// 教务课程排课数据
		List<TimetableLabRelated> list = timetableLabRelatedDAO.executeQuery(sql.toString());

		List<TimetableLabRelated> selfList = timetableLabRelatedDAO.executeQuery(selfSql.toString());
		list.addAll(selfList);
		// 自主排课数据
		// String weeks ="";
		String sweek = "";
		for (TimetableLabRelated timetableLabRelated : list) {
			boolean isIn = true;
			for (int intClass : classes) {
				if (timetableLabRelated.getTimetableAppointment().getTimetableAppointmentSameNumbers().size() == 0) {
					isIn = timetableLabRelated.getTimetableAppointment().getEndClass() >= intClass
							&& timetableLabRelated.getTimetableAppointment().getStartClass() <= intClass
								&& (timetableLabRelated.getTimetableAppointment().getEndWeek() >= weeks[0]
									&& timetableLabRelated.getTimetableAppointment().getStartWeek() <= weeks[0]
										|| timetableLabRelated.getTimetableAppointment().getEndWeek() >= weeks[weeks.length-1]
									       && timetableLabRelated.getTimetableAppointment().getStartWeek() <= weeks[weeks.length-1]);

					if (isIn) {
						return false;
						//break;
					} else {
						for (int i = timetableLabRelated.getTimetableAppointment().getStartWeek(); i <= timetableLabRelated
								.getTimetableAppointment().getEndWeek();) {
							sweek = sweek + i + ";";
							i++;
						}
						//return true;
					}
				} else {
					for (TimetableAppointmentSameNumber timetableAppointmentSameNumber : timetableLabRelated
							.getTimetableAppointment().getTimetableAppointmentSameNumbers()) {
						isIn = timetableAppointmentSameNumber.getEndClass() >= intClass
								&& timetableAppointmentSameNumber.getStartClass() <= intClass;
						isIn = timetableAppointmentSameNumber.getEndClass() >= intClass
								&& timetableAppointmentSameNumber.getStartClass() <= intClass
									&& (timetableAppointmentSameNumber.getEndWeek() >= weeks[0]
										&& timetableAppointmentSameNumber.getStartWeek() <= weeks[0]
											|| timetableAppointmentSameNumber.getEndWeek() >= weeks[weeks.length-1]
										       && timetableAppointmentSameNumber.getStartWeek() <= weeks[weeks.length-1]);

						if (isIn) {
							//break;
							return false;
						} else {
							for (int i = timetableAppointmentSameNumber.getStartWeek(); i <= timetableAppointmentSameNumber
									.getEndWeek();) {
								sweek = sweek + i + ";";
								i++;
							}
						}

					}
				}

			}

		}
		return true;
	}

	/************************************************************
	 * @获取数组中去重的结果
	 * @作者：魏诚
	 * @日期：2014-07-24
	 ************************************************************/
	public Integer[] getDistinct(int num[]) {
		List<Integer> list = new java.util.ArrayList<Integer>();
		for (int i = 0; i < num.length; i++) {
			if (!list.contains(num[i])) {// 如果list数组不包括num[i]中的值的话，就返回true。
				list.add(num[i]); // 在list数组中加入num[i]的值。已经过滤过。
			}
		}
		return list.toArray(new Integer[0]);
	}

	/************************************************************
	 * @获取所有学期数据，按id倒序
	 * @作者：魏诚
	 * @日期：2014-08-17
	 ************************************************************/
	public List<SchoolTerm> getSchoolTermList() {
		String sql = "select c from SchoolTerm c order by id desc";
		List<SchoolTerm> schoolTerms = schoolTermDAO.executeQuery(sql);
		return schoolTerms;
	}
}