package net.xidlims.service.timetable;

import java.util.List;

import net.xidlims.service.common.ShareService;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.TimetableBatchDAO;
import net.xidlims.dao.TimetableSelfCourseDAO;
import net.xidlims.domain.TimetableBatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TimetableBatchService")
public class TimetableBatchServiceImpl implements TimetableBatchService {

	@Autowired
	private ShareService shareService;
	@Autowired
	private TimetableBatchDAO timetableBatchDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private TimetableSelfCourseDAO timetableSelfCourseDAO;

	/*************************************************************************************
	 * @內容：查看所有的预约的列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	@SuppressWarnings("unused")
	public List<TimetableBatch> getTimetableBatchByQuery(int termId, int status, int curr, int size, int iLabCenter) {
		String academyNumber = "";
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		if (iLabCenter != -1) {
			// 获取选择的实验中心
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		} else {
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		}
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer("select c from TimetableBatch  c where 1=1 ");

		List<TimetableBatch> timetableBatchList = timetableBatchDAO.executeQuery(sql.toString(), curr * size, size);
		
		//返回值
		List<TimetableBatch> returnTimetableBatchList = timetableBatchDAO.executeQuery(sql.toString(),0, -1);
		
		return returnTimetableBatchList;
	}

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableBatchByQuery(int termId,int status, int iLabCenter) {
		String academyNumber = "";
		// 如果没有获取有效的实验分室列表-根据登录用户的所属学院
		if (iLabCenter != -1) {
			// 获取选择的实验中心
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		} else {
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		}
		// 创建根据学期来查询课程；
		StringBuffer sql = new StringBuffer("select c from TimetableBatch  c where 1=1 ");

		List<TimetableBatch> timetableBatchList = timetableBatchDAO.executeQuery(sql.toString(), 0, -1);
		//返回值
		List<TimetableBatch> returnTimetableBatchList = timetableBatchDAO.executeQuery(sql.toString(), 0, -1);
		// 判断批次所在的部门
		for (TimetableBatch timetableBatch : timetableBatchList) {
			// 如果为教务类排课
			if (!schoolCourseDAO.findSchoolCourseByCourseCode(timetableBatch.getCourseCode()).isEmpty()) {
				if (!schoolCourseDAO.findSchoolCourseByCourseCode(timetableBatch.getCourseCode()).iterator().next()
						.getSchoolAcademy().getAcademyNumber().equals(academyNumber)) {
					returnTimetableBatchList.remove(timetableBatch);
				}
			}
			// 如果为自主类排课
			if (!timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(timetableBatch.getCourseCode()).isEmpty()) {
				if (!timetableSelfCourseDAO.findTimetableSelfCourseByCourseCode(timetableBatch.getCourseCode())
						.iterator().next().getSchoolAcademy().getAcademyNumber().equals(academyNumber)) {
					returnTimetableBatchList.remove(timetableBatch);
				}
			}
		}

		return returnTimetableBatchList.size();
	}

}