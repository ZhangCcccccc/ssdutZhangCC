package net.xidlims.service.report;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.MonthReport;
import net.xidlims.domain.LabWorker;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableLabRelated;

import org.springframework.transaction.annotation.Transactional;

public interface LabWorkerService{
	/****************************************************************
	 * @功能：获取LabWorker列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	public List<LabWorker> getList();
	
	/****************************************************************
	 * @功能：获取LabWorker列表记录数
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	public int Count();
	
	/****************************************************************
	 * @功能：查看所有的人员信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	public List<LabWorker> findAllLabWorker(Integer currpage, Integer pageSize, LabWorker labWorker);
	
	
	public List<LabWorker> getList(int startResult, int maxRows);
	
}