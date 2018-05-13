package net.xidlims.service.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabWorkerDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabWorker;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.project.ProjectSummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LabRoomBasicService")
@Transactional
public class LabRoomBasicServiceImpl implements LabRoomBasicService{
	@Autowired private ShareService shareService;
	@Autowired private LabRoomService labRoomService;
	
	@Autowired private LabRoomDAO labRoomDAO;
	@Autowired private LabCenterDAO labCenterDAO;
	
	/****************************************************************
	 * @功能：获取LabRoom信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	@Override
	public int Count() {
		int RecordCount=this.getList().size();		
		return RecordCount;
	}
	
	/****************************************************************
	 * @功能：获取LabRoom信息记录数
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	public List<LabRoom> getList() {
		List<LabRoom> list = getList(0,-1);
		return list;
	}
	
	/****************************************************************
	 * @功能：查看所有的实验室信息列表
	 * @作者：陈乐为
	 * @日期：2016-01-06
	 ****************************************************************/
	public List<LabRoom> findAllLabRoomBasic(Integer currpage, Integer pageSize, LabRoom labRoom){
		StringBuffer hql = new StringBuffer("select i from LabRoom i where 1=1");
		return labRoomDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	@Override
	public List<LabRoom> getList(int startResult, int maxRows) {
		String hql="select a from LabRoom a where 1=1";                                                                   //my,新增
		List<LabRoom> list=labRoomDAO.executeQuery(hql,startResult,maxRows);           //my（-1，-1）
		return list;
	}
	
	/*************************************************************************************
	 * @內容：根据实验中心查询
	 * @作者： 陈乐为
	 * @日期：2016-01-07
	 *************************************************************************************/
	//根据实验中心查询
	@Override
	public List<LabRoom> findLabRoomByQuery(LabRoom labRoom, Integer labCenterId, Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select i from LabRoom i where 1=1");
		//String academyNumber="";
       // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        /*if (labCenterid != -1) {
    		//获取选择的实验中心
        	academyNumber = labCenterDAO.findLabCenterById(labCenterid).getSchoolAcademy().getAcademyNumber();
        }*/
        if(labCenterId!=null && labCenterId>0)
		{
			hql.append(" and i.labCenter.id="+labCenterId); 
		}
        if(labRoom.getLabCenter()!=null && !"".equals(labRoom.getLabCenter().getCenterName()));
		{
			hql.append(" and i.labCenter.centerName like '%"+labRoom.getLabCenter().getCenterName()+"%'");
		}
		System.out.println(labRoom.getLabCenter().getCenterName());
        return labRoomDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/*************************************************************************************
	 * @內容：根据实验中心查询所得数据条数
	 * @作者： 陈乐为
	 * @日期：2016-01-07
	 *************************************************************************************/
	@Override
	public Integer findAllLabRoomByQuery(LabRoom labRoom,Integer labCenterid){
		
		//SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		//int termId = s.getId();
		StringBuffer hql = new StringBuffer("select count(i) from LabRoom i where 1=1");
		
		if(labCenterid!=null && labCenterid>0)
		{
			hql.append(" and i.labRoom.labCenter.id="+labCenterid); 
		}
		return ((Long) labRoomDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
}