package net.xidlims.service.project;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.MonthReport;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableLabRelated;

import org.springframework.transaction.annotation.Transactional;

public interface ProjectSummaryService{
	/*********************************************************************
	 * @内容：查看所有的项目信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-05
	 *********************************************************************/
	public List<OperationItem> getList(Integer sid);
	
	//返回记录数
	public int Count(Integer sid);
	
	//返回指定的表
	public OperationItem getOperationItemByPrimaryKey(Integer id);
	
	//返回指定分页的记录列表
	public List<OperationItem> getList(int startResult, int maxRows, Integer sid);
	
	//
	public List<OperationItem> findAllOperationItem(Integer currpage, Integer pageSize, OperationItem operationItem,Integer sid);
	
	//
	public List<OperationItem> findOperationItemsByQuery(Integer currpage, Integer pageSize, Integer roomid, OperationItem operationItem, Integer sid);
	
	//
	public Integer findAllOperationItemByQueryCount(OperationItem operationItem, Integer roomid, Integer sid);
}