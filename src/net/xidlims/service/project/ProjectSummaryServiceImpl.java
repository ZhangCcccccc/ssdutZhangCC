package net.xidlims.service.project;

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
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.LabRoom;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ProjectSummaryServce")
@Transactional
public class ProjectSummaryServiceImpl implements ProjectSummaryService{
	
	@Autowired private OperationItemDAO operationItemDAO;
	@Autowired private ShareService shareService;
	@Autowired private LabRoomService labRoomService;
	//返回记录数
	public int Count(Integer sid){
		int RecordCount=this.getList(sid).size();		
		return RecordCount;
	}

	/*********************************************************************
	 * @内容：获取所有的项目信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-05
	 *********************************************************************/
	public List<OperationItem> getList(Integer sid) {
		//StringBuffer sql=new StringBuffer("select c from OperationItem c where 1=1");
		List<OperationItem> list=getList(0,-1,sid);
		return list;
	}
	
	/*********************************************************************
	 * @功能：查看所有的项目信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-05
	 *********************************************************************/
	@Override
	public List<OperationItem> findAllOperationItem(Integer currpage, Integer pageSize, OperationItem operationItem, Integer sid) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i where 1=1");
		hql.append(" and i.labCenter.id ="+sid);
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/*********************************************************************
	 * @功能：根据条件查询结果
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 *********************************************************************/
	@Override
	public List<OperationItem> findOperationItemsByQuery(Integer currpage, Integer pageSize, Integer roomid, OperationItem operationItem, Integer sid){
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i where 1=1");
		if(roomid!=null && roomid>0)
		{
			hql.append(" and i.labRoom.id="+roomid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLabRoom()!=null && !"".equals(operationItem.getLabRoom().getLabRoomName()))
		{
			hql.append(" and i.labRoom.labRoomName like '%"+operationItem.getLabRoom().getLabRoomName()+"%'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		hql.append(" and i.labCenter.id ="+sid);
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/*********************************************************************
	 * @功能：根据条件查询所得数据条数
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 *********************************************************************/
	@Override
	public Integer findAllOperationItemByQueryCount(OperationItem operationItem, Integer roomid, Integer sid){
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select count(i) from OperationItem i where 1=1");
		
		if(roomid!=null && roomid>0)
		{
			hql.append(" and i.labRoom.labCenter.id="+roomid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLabRoom()!=null && !"".equals(operationItem.getLabRoom().getLabRoomName()))
		{
			hql.append(" and i.labRoom.labRoomName like '%"+operationItem.getLabRoom().getLabRoomName()+"%'");
		}
		hql.append(" and i.labCenter.id ="+sid);
		return ((Long) operationItemDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	//返回指定的表
	public OperationItem getOperationItemByPrimaryKey(Integer id){
		return operationItemDAO.findOperationItemByPrimaryKey(id);                                
	}
	
	//返回指定分页的记录列表
	public List<OperationItem> getList(int startResult, int maxRows,Integer sid){
		String hql="select a from OperationItem a where 1=1";                                                                   //my,新增
		hql += " and a.labCenter.id ="+sid;
		List<OperationItem> list=operationItemDAO.executeQuery(hql,startResult,maxRows);           //my（-1，-1）
		return list;
	}
	
}