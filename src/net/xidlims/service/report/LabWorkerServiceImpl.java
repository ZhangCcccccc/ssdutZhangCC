package net.xidlims.service.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabWorkerDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.domain.LabWorker;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.LabRoom;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.project.ProjectSummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LabWorkerService")
@Transactional
public class LabWorkerServiceImpl implements LabWorkerService{
	@Autowired private ShareService shareService;
	@Autowired private LabRoomService labRoomService;
	@Autowired private LabWorkerDAO labWorkerDAO;
	
	/****************************************************************
	 * @功能：获取LabWorker列表记录数
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	@Override
	public int Count() {
		int RecordCount=this.getList().size();		
		return RecordCount;
	}
	
	/****************************************************************
	 * @功能：获取LabWorker列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	public List<LabWorker> getList() {
		List<LabWorker> list = getList(0,-1);
		return list;
	}
	
	/*********************************************************************
	 * @功能：查看所有的人员信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 *********************************************************************/
	@Override
	public List<LabWorker> findAllLabWorker(Integer currpage, Integer pageSize, LabWorker labWorker) {
		StringBuffer hql = new StringBuffer("select i from LabWorker i where 1=1");
		return labWorkerDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	//返回指定分页的记录列表
	public List<LabWorker> getList(int startResult, int maxRows){
		String hql="select a from LabWorker a where 1=1";                                                                   //my,新增
		List<LabWorker> list=labWorkerDAO.executeQuery(hql,startResult,maxRows);           //my（-1，-1）
		return list;
	}
	
	
}
