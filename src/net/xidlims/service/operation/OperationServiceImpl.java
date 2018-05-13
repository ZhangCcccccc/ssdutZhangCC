package net.xidlims.service.operation;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.COperationOutlineCreditDAO;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.OperationItemDeviceDAO;
import net.xidlims.dao.OperationItemMaterialRecordDAO;
import net.xidlims.dao.OperationOutlineDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SystemMajor12DAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.COperationOutlineCredit;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationItemDevice;
import net.xidlims.domain.OperationItemMaterialRecord;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemMajor12;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.system.SystemLogService;
import net.xidlims.service.timetable.SchoolCourseService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("OperationService")
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private OperationItemMaterialRecordDAO operationItemMaterialRecordDAO;
	@Autowired
	private OperationItemDeviceDAO operationItemDeviceDAO;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private OperationOutlineDAO  operationOutlineDAO;
	@Autowired
	private SchoolCourseInfoDAO  schoolCourseInfoDAO;
	@Autowired
	private SystemMajor12DAO  systemMajor12DAO;
	@Autowired
	private COperationOutlineCreditDAO  cOperationOutlineCreditDAO;
	@Autowired
	private SchoolAcademyDAO  SchoolAcademyDAO;
	@Autowired
	private SchoolCourseService  schoolCourseService;
	@Autowired
	private CDictionaryDAO  cDictionaryDAO;
	@Autowired
	private CommonDocumentDAO  commonDocumentDAO;
	@Autowired
	private SchoolTermDAO  schoolTermDAO;
	
	
	
	/**
	 * 根据主键获取实验项目对象
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public OperationItem findOperationItemByPrimaryKey(Integer operationItemId) {
		return operationItemDAO.findOperationItemByPrimaryKey(operationItemId);
	}

	/**
	 * 根据查询条件实验项目列表
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public List<OperationItem> findAllOperationItemByQuery(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i  where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labRoom.labCenter.id="+cid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		hql.append(" order by i.schoolTerm.id desc, i.createdAt desc");
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	
	/**
	 * 根据查询条件实验项目列表
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public List<OperationItem> findAllOperationItemExceptDraft(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid, int orderBy) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i  where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labCenter.id="+cid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		hql.append(" and i.CDictionaryByLpStatusCheck.id <>543");
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getUserByLpCheckUser()!=null && operationItem.getUserByLpCheckUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCheckUser.username='"+operationItem.getUserByLpCheckUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		if (orderBy==9) {//默认，按学期排序
			hql.append(" order by i.schoolTerm.id desc, i.createdAt desc");
			
		}
		if (orderBy==0) {//实验编号降序
			hql.append(" order by i.lpCodeCustom desc, i.createdAt desc");
			
		}
		if (orderBy==10) {//实验编号升序
			hql.append(" order by i.lpCodeCustom asc, i.createdAt desc");
			
		}
		if (orderBy==1) {//实验名称降序
			hql.append(" order by i.lpName desc, i.createdAt desc");
			
		}
		if (orderBy==11) {//实验名称升序
			hql.append(" order by i.lpName asc, i.createdAt desc");
			
		}
		if (orderBy==2) {//所属实验室降序
			hql.append(" order by i.labRoom.id desc, i.createdAt desc");
			
		}
		if (orderBy==12) {//所属实验室升序
			hql.append(" order by i.labRoom.id asc, i.createdAt desc");
			
		}
		if (orderBy==3) {//所属课程降序
			hql.append(" order by i.schoolCourseInfo.courseNumber desc, i.createdAt desc");
			
		}
		if (orderBy==13) {//所属课程升序
			hql.append(" order by i.schoolCourseInfo.courseNumber asc, i.createdAt desc");
			
		}
		if (orderBy==4) {//审核状态降序
			hql.append(" order by i.CDictionaryByLpStatusCheck.id desc, i.createdAt desc");
			
		}
		if (orderBy==14) {//审核状态升序
			hql.append(" order by i.CDictionaryByLpStatusCheck.id asc, i.createdAt desc");
			
		}
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**
	 * 根据查询条件实验项目列表--增加排序
	 * @author 贺子龙
	 * 2015-11-20
	 */
	@Override
	public List<OperationItem> findAllOperationItemByQuery(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid, int orderBy) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i  where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labCenter.id="+cid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		if (orderBy==9) {//默认，按学期排序
			hql.append(" order by i.schoolTerm.id desc, i.createdAt desc");
			
		}
		if (orderBy==0) {//实验编号降序
			hql.append(" order by i.lpCodeCustom desc, i.createdAt desc");
			
		}
		if (orderBy==10) {//实验编号升序
			hql.append(" order by i.lpCodeCustom asc, i.createdAt desc");
			
		}
		if (orderBy==1) {//实验名称降序
			hql.append(" order by i.lpName desc, i.createdAt desc");
			
		}
		if (orderBy==11) {//实验名称升序
			hql.append(" order by i.lpName asc, i.createdAt desc");
			
		}
		if (orderBy==2) {//所属实验室降序
			hql.append(" order by i.labRoom.id desc, i.createdAt desc");
			
		}
		if (orderBy==12) {//所属实验室升序
			hql.append(" order by i.labRoom.id asc, i.createdAt desc");
			
		}
		if (orderBy==3) {//所属课程降序
			hql.append(" order by i.schoolCourseInfo.courseNumber desc, i.createdAt desc");
			
		}
		if (orderBy==13) {//所属课程升序
			hql.append(" order by i.schoolCourseInfo.courseNumber asc, i.createdAt desc");
			
		}
		if (orderBy==4) {//审核状态降序
			hql.append(" order by i.CDictionaryByLpStatusCheck.id desc, i.createdAt desc");
			
		}
		if (orderBy==14) {//审核状态升序
			hql.append(" order by i.CDictionaryByLpStatusCheck.id asc, i.createdAt desc");
			
		}
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 实验室导入的时候需要默认显示当前学期之前的那个学期
	 * @author 贺子龙
	 * 2015-09-24 11:15:25
	 */
	@Override
	public List<OperationItem> findAllOperationItemByQueryImport(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i  where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labCenter.id="+cid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+(termId-1));
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		hql.append(" order by i.schoolTerm.id desc");
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**
	 * 实验室导入的时候需要默认显示当前学期之前的那个学期--增加排序
	 * @author 贺子龙
	 * 2015-09-24 11:15:25
	 */
	@Override
	public List<OperationItem> findAllOperationItemByQueryImport(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid,int orderBy) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select i from OperationItem i  where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labCenter.id="+cid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+(termId-1));
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		if (orderBy==9) {//默认，按学期排序
			hql.append(" order by i.schoolTerm.id desc");
			
		}
		if (orderBy==0) {//实验编号降序
			hql.append(" order by i.lpCodeCustom desc");
			
		}
		if (orderBy==10) {//实验编号升序
			hql.append(" order by i.lpCodeCustom asc");
			
		}
		if (orderBy==1) {//实验名称降序
			hql.append(" order by i.lpName desc");
			
		}
		if (orderBy==11) {//实验名称升序
			hql.append(" order by i.lpName asc");
			
		}
		if (orderBy==2) {//所属实验室降序
			hql.append(" order by i.labRoom.id desc");
			
		}
		if (orderBy==12) {//所属实验室升序
			hql.append(" order by i.labRoom.id asc");
			
		}
		if (orderBy==3) {//所属课程降序
			hql.append(" order by i.schoolCourseInfo.courseNumber desc");
			
		}
		if (orderBy==13) {//所属课程升序
			hql.append(" order by i.schoolCourseInfo.courseNumber asc");
			
		}
		if (orderBy==4) {//审核状态降序
			hql.append(" order by i.CDictionaryByLpStatusCheck.id desc");
			
		}
		if (orderBy==14) {//审核状态升序
			hql.append(" order by i.CDictionaryByLpStatusCheck.id asc");
			
		}
		return operationItemDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**
	 * 根据查询条件实验项目记录数量
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public Integer findAllOperationItemByQueryCount(OperationItem operationItem, Integer cid) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select count(i) from OperationItem i where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labCenter.id="+cid); 
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		return ((Long) operationItemDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}
	
	
	/**
	 * 根据查询条件实验项目记录数量--除草稿外
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public Integer findAllOperationItemExceptDraft(OperationItem operationItem, Integer cid) {
		SchoolTerm s = shareService.getBelongsSchoolTerm(Calendar.getInstance());
		int termId = s.getId();
		StringBuffer hql = new StringBuffer("select count(i) from OperationItem i where 1=1");
		if(cid!=null && cid>0)
		{
			hql.append(" and i.labCenter.id="+cid);
		}
		if(operationItem.getSchoolTerm()!=null && operationItem.getSchoolTerm().getId()!=null)
		{
			hql.append(" and i.schoolTerm.id="+operationItem.getSchoolTerm().getId());
		}else {
			hql.append(" and i.schoolTerm.id="+termId);
		}
		if(operationItem.getLpName()!=null && !"".equals(operationItem.getLpName()))
		{
			hql.append(" and i.lpName like '%"+operationItem.getLpName()+"%'");
		}
		if(operationItem.getCDictionaryByLpStatusCheck()!=null && operationItem.getCDictionaryByLpStatusCheck().getId()!=null)
		{
			hql.append(" and i.CDictionaryByLpStatusCheck.id="+operationItem.getCDictionaryByLpStatusCheck().getId());
		}
		hql.append(" and i.CDictionaryByLpStatusCheck.id <>543");
		if(operationItem.getUserByLpCreateUser()!=null && operationItem.getUserByLpCreateUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCreateUser.username='"+operationItem.getUserByLpCreateUser().getUsername()+"'");
		}
		if(operationItem.getUserByLpCheckUser()!=null && operationItem.getUserByLpCheckUser().getUsername()!="")
		{
			hql.append(" and i.userByLpCheckUser.username='"+operationItem.getUserByLpCheckUser().getUsername()+"'");
		}
		if(operationItem.getSchoolCourseInfo()!=null && !"".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			hql.append(" and i.schoolCourseInfo.courseNumber='"+operationItem.getSchoolCourseInfo().getCourseNumber()+"'");
		}
		return ((Long) operationItemDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}

	/**
	 * 保存实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public OperationItem saveOperationItem(OperationItem operationItem) {
		if(operationItem.getSchoolTerm()==null || operationItem.getSchoolTerm().getId()==null)
		{
			operationItem.setSchoolTerm(null);  //学期
		}
		if(operationItem.getUserByLpTeacherSpeakerId()==null || operationItem.getUserByLpTeacherSpeakerId().getUsername()==null || "".equals(operationItem.getUserByLpTeacherSpeakerId().getUsername()))
		{
			operationItem.setUserByLpTeacherSpeakerId(null);  //主讲教师
		}
		if(operationItem.getUserByLpTeacherAssistantId()==null || operationItem.getUserByLpTeacherAssistantId().getUsername()==null || "".equals(operationItem.getUserByLpTeacherAssistantId().getUsername()))
		{
			operationItem.setUserByLpTeacherAssistantId(null);  //辅导教师
		}
		if(operationItem.getLabRoom()==null || operationItem.getLabRoom().getId()==null)
		{
			operationItem.setLabRoom(null);  //实验室
		}
		if(operationItem.getSchoolCourseInfo()==null || operationItem.getSchoolCourseInfo().getCourseNumber()==null || "".equals(operationItem.getSchoolCourseInfo().getCourseNumber()))
		{
			operationItem.setSchoolCourseInfo(null);  //课程数据
		}
		if(operationItem.getSystemSubject12()==null || operationItem.getSystemSubject12().getSNumber()==null || "".equals(operationItem.getSystemSubject12().getSNumber()))
		{
			operationItem.setSystemSubject12(null);  //学科数据
		}
		if(operationItem.getSystemMajor12()==null || operationItem.getSystemMajor12().getMNumber()==null || "".equals(operationItem.getSystemMajor12().getMNumber()))
		{
			operationItem.setSystemMajor12(null);  //专业数据
		}
		
		if(operationItem.getCDictionaryByLpCategoryMain()==null || operationItem.getCDictionaryByLpCategoryMain().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryMain(null);  //实验项目类别
		}
		if(operationItem.getCDictionaryByLpCategoryApp()==null || operationItem.getCDictionaryByLpCategoryApp().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryApp(null);  //实验项目类型
		}
		if(operationItem.getCDictionaryByLpCategoryNature()==null || operationItem.getCDictionaryByLpCategoryNature().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryNature(null);  //实验项目性质
		}
		if(operationItem.getCDictionaryByLpCategoryStudent()==null || operationItem.getCDictionaryByLpCategoryStudent().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryStudent(null);  //实验者类型
		}
		if(operationItem.getCDictionaryByLpStatusChange()==null || operationItem.getCDictionaryByLpStatusChange().getId()==null)
		{
			operationItem.setCDictionaryByLpStatusChange(null);  //变动状态
		}
		if(operationItem.getCDictionaryByLpCategoryPublic()==null || operationItem.getCDictionaryByLpCategoryPublic().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryPublic(null);  //开放实验
		}
		if(operationItem.getCDictionaryByLpCategoryRewardLevel()==null || operationItem.getCDictionaryByLpCategoryRewardLevel().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryRewardLevel(null);  //获奖等级
		}
		if(operationItem.getCDictionaryByLpCategoryRequire()==null || operationItem.getCDictionaryByLpCategoryRequire().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryRequire(null);  //实验项目要求
		}
		if(operationItem.getCDictionaryByLpCategoryGuideBook()==null || operationItem.getCDictionaryByLpCategoryGuideBook().getId()==null)
		{
			operationItem.setCDictionaryByLpCategoryGuideBook(null);  //实验项目指导书
		}
		return operationItemDAO.store(operationItem);
	}

	/**
	 * 删除实验项目
	 * @author hly
	 * 2015.07.29
	 */
	@Override
	public boolean deleteOperationItem(Integer operationItemId) {
		OperationItem operationItem = findOperationItemByPrimaryKey(operationItemId);
		if(operationItem != null)
		{
			operationItemDAO.remove(operationItem);
			operationItemDAO.flush();
			return true;
		}
		
		return false;
	}

	/**
	 * 提交实验项目
	 * @author hly
	 * 2015.08.06
	 */
	@Override
	public OperationItem submitOperationItem(OperationItem operationItem) {
		OperationItem oi = new OperationItem();
		if(operationItem.getId() != null)
		{
			oi = findOperationItemByPrimaryKey(operationItem.getId());
			if(operationItem.getUserByLpCheckUser()!=null && operationItem.getUserByLpCheckUser().getUsername()!=null)
			{
				oi.setUserByLpCheckUser(operationItem.getUserByLpCheckUser());  //指定审核人
				oi.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "2"));
				
				operationItemDAO.store(oi);
				operationItemDAO.flush();
			}
		}
		return oi;
	}

	/**
	 * 设置项目编号
	 * @author hly
	 * 2015.08.07
	 */
	@Override
	public void saveCodeCustom(OperationItem operationItem) {
		OperationItem oi = new OperationItem();
		if(operationItem.getId()!=null)
		{
			oi = findOperationItemByPrimaryKey(operationItem.getId());
			if(operationItem.getLpCodeCustom()!=null && !"".equals(operationItem.getLpCodeCustom()))
			{
				oi.setLpCodeCustom(operationItem.getLpCodeCustom());
				
				operationItemDAO.store(oi);
				operationItemDAO.flush();
			}
		}
	}

	/**
	 * 导入整个学期的实验项目
	 * @author hly
	 * 2015.08.07
	 */
	@Override
	public void importTermOperationItem(Integer sourceTermId, Integer targetTermId, Integer cid) {
		if(sourceTermId!=null && targetTermId!=null && !sourceTermId.equals(targetTermId) && cid!=null)
		{
			SchoolTerm t = new SchoolTerm();
			t.setId(targetTermId);
			
			StringBuffer hql = new StringBuffer("select i from OperationItem i where 1=1");
		    hql.append(" and i.schoolTerm.id="+sourceTermId);
		    hql.append(" and i.labRoom.labCenter.id="+cid);
		    
			//找到目标学期已经有的所有实验室项目
			List<OperationItem> operationItemOfTargetTerm=operationItemDAO.executeQuery("select i from OperationItem i  where i.schoolTerm.id="+targetTermId, 0, -1);
			String itemName=",";
			String majorName=",";
			for (OperationItem operationItem : operationItemOfTargetTerm) {
				itemName+=operationItem.getLpName()+",";
				if (operationItem.getSystemMajor12()!=null&&operationItem.getSystemMajor12().getMNumber()!=null) {
					majorName+=operationItem.getSystemMajor12().getMNumber()+",";
				}
			}
			
			List<OperationItem> operationItems = operationItemDAO.executeQuery(hql.toString(), 0, -1);
			
			for (OperationItem operationItem : operationItems) 
			{
				//如果被导入实验的名字已经在该学期的名字里面，且专业号也相同，则跳出此次循环（跳过这个项目不导入）
				if (itemName.indexOf(","+operationItem.getLpName()+",")!=-1&&(operationItem.getSystemMajor12()!=null&&majorName.indexOf(","+operationItem.getSystemMajor12().getMNumber()+",")!=-1)) {
					System.out.println("本条数据不能被导入，因为有重名，且专业号也相同");
					continue;
				}
				else {
					OperationItem oi = new OperationItem();
					oi.copy(operationItem);
					
					//草稿状态
					oi.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "1"));
					//学期
					oi.setSchoolTerm(t);
					//创建人=导入人
					oi.setUserByLpCreateUser(shareService.getUser());
					operationItemDAO.store(oi);
				}
			}
			operationItemDAO.flush();
		}
	}

	/**
	 * 导入选中的实验项目
	 * @author hly
	 * 2015.08.07
	 */
	@Override
	public void importOperationItem(HttpServletRequest request,Integer termId, String itemIds) {
		if(termId!=null && itemIds!=null && itemIds.length()>0)
		{
			SchoolTerm t = new SchoolTerm();
			t.setId(termId);  //导入的目标学期
			
			String[] ids = itemIds.split(",");
			//找到目标学期已经有的所有实验室项目
			//List<OperationItem> operationItems=operationItemDAO.executeQuery("select i from OperationItem i  where i.schoolTerm.id="+termId, 0, -1);
			//String itemName=",";
			//String majorName=",";
			/*for (OperationItem operationItem : operationItems) {
				//itemName+=operationItem.getLpName()+",";
				if (operationItem.getSystemMajor12()!=null&&operationItem.getSystemMajor12().getMNumber()!=null){
					//majorName+=operationItem.getSystemMajor12().getMNumber()+",";
				}
			}*/
			try {
				for (String string : ids) 
				{
					OperationItem item = findOperationItemByPrimaryKey(Integer.parseInt(string));
/*					//如果被导入实验的名字已经在该学期的名字里面，且专业号也相同，则跳出此次循环（跳过这个项目不导入）
					if (itemName.indexOf(","+item.getLpName()+",")!=-1&&(item.getSystemMajor12()!=null&&majorName.indexOf(","+item.getSystemMajor12().getMNumber()+",")!=-1)) {
						System.out.println("本条数据不能被导入，因为有重名，且专业号也相同");
						continue;
					}*/
					/*else {*/
						OperationItem oi = new OperationItem();
						oi.copy(item);
						oi.setCDictionaryByLpStatusCheck(shareService.getCDictionaryByCategory("status_operation_item_check", "1"));
						oi.setSchoolTerm(t);
						oi.setCreatedAt(Calendar.getInstance());
						oi.setUserByLpCreateUser(shareService.getUser());//导入人就是创建人（2015-11-12 贺子龙）
						operationItemDAO.store(oi);
						//导入日志
						String ip = shareService.getIpAddr(request);
						//保存日志信息 saveOperationItemLog(String userIp, int tag, int action, int id)
						//tag：子模块标志位  0-我的实验项目  1-实验项目管理  2-实验项目导入  id：项目卡的id 0-查看 -1--新建
						//action: 0 新建 1 编辑 2 查看 3 删除 4 提交 5 审核 6 保存 7 审核保存 8 导入
						systemLogService.saveOperationItemLog(ip, 2, 8, Integer.parseInt(string));
					/*}*/
				}
				operationItemDAO.flush();
			} catch (Exception e) {
				System.out.println("实验项目id类型转换错误");
			}
		}
	}

	/**
	 * 获取指定实验项目的材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	@Override
	public List<OperationItemMaterialRecord> getItemMaterialRecordByItem(Integer itemId, Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select mr from OperationItemMaterialRecord mr where 1=1 ");
		if(itemId!=null && itemId>0)
		{
			hql.append(" and mr.operationItem.id="+itemId);
		}
		hql.append(" order by mr.lpmrTimeCreate desc ");
		
		return operationItemMaterialRecordDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**
	 * 功能：获取指定实验项目的材料使用记录不分页
	 * 作者： 贺子龙
	 * 时间：2015-09-10
	 */
	@Override
	public List<OperationItemMaterialRecord> getItemMaterialRecordByItem(Integer itemId) {
		StringBuffer hql = new StringBuffer("select mr from OperationItemMaterialRecord mr where 1=1 ");
		if(itemId!=null && itemId>0)
		{
			hql.append(" and mr.operationItem.id="+itemId);
		}
		hql.append(" order by mr.lpmrTimeCreate desc ");
		
		return operationItemMaterialRecordDAO.executeQuery(hql.toString(),0,-1);
	}

	/**
	 * 获取指定实验项目的材料使用记录数量
	 * @author hly
	 * 2015.08.10
	 */
	@Override
	public int getItemMaterialRecordByItemCount(Integer itemId) {
		StringBuffer hql = new StringBuffer("select count(mr) from OperationItemMaterialRecord mr where 1=1 ");
		if(itemId!=null && itemId>0)
		{
			hql.append(" and mr.operationItem.id="+itemId);
		}
		
		return ((Long) operationItemMaterialRecordDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}

	/**
	 * 保存实验项目材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	@Override
	public OperationItemMaterialRecord saveItemMaterialRecord(OperationItemMaterialRecord operationItemMaterialRecord) {
		if(operationItemMaterialRecord.getLpmrTimeCreate()==null)
		{
			operationItemMaterialRecord.setLpmrTimeCreate(Calendar.getInstance());
		}
		return operationItemMaterialRecordDAO.store(operationItemMaterialRecord);
	}

	/**
	 * 删除实验项目使用材料记录
	 * @author hly
	 * 2015.08.10
	 */
	@Override
	public boolean deleteItemMaterialRecord(Integer mrId) {
		OperationItemMaterialRecord mr = findItemMaterialRecordByPrimaryKey(mrId);
		if(mr!=null)
		{
		  operationItemMaterialRecordDAO.remove(mr);
		  operationItemMaterialRecordDAO.flush();
		  
		  return true;
		}
		
		return false;
	}

	/**
	 * 根据主键获取实验项目使用材料记录
	 * @author hly
	 * 2015.08.10
	 */
	@Override
	public OperationItemMaterialRecord findItemMaterialRecordByPrimaryKey(Integer lpmrId) {
		return operationItemMaterialRecordDAO.findOperationItemMaterialRecordByPrimaryKey(lpmrId);
	}
	
	/**
	 * 获取指定实验项目的设备
	 * @author hly
	 * 2015.08.19
	 */
	@Override
	public List<OperationItemDevice> getItemDeviceByItem(Integer itemId, Integer category, Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select oid from OperationItemDevice oid where 1=1 ");
		if(category!=null && !"".equals(category))
		{
		    hql.append(" and oid.CDictionary.id="+category);	
		}
		if(itemId!=null && itemId>0)
		{
			hql.append(" and oid.operationItem.id="+itemId);
		}
		
		return operationItemDeviceDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**
	 * 获取指定实验项目的设备数量
	 * @author hly
	 * 2015.08.19
	 */
	@Override
	public int getItemDeviceByItemCount(Integer itemId, Integer category){
		StringBuffer hql = new StringBuffer("select count(oid) from OperationItemDevice oid where 1=1 ");
		if(category!=null && !"".equals(category))
		{
		    hql.append(" and oid.CDictionary.id="+category);	
		}
		if(itemId!=null && itemId>0)
		{
			hql.append(" and oid.operationItem.id="+itemId);
		}
		
		return ((Long) operationItemDeviceDAO.createQuerySingleResult(hql.toString()).getSingleResult()).intValue();
	}

	/**
	 * 批量保存实验项目设备
	 * @author hly
	 * 2015.08.19
	 */
	@Override
	public void saveItemDevice(Integer itemId, String category, String ids) {
		if(ids!=null && !"".equals(ids) && ids.length()>0)
		{
		  	String[] labRoomDeviceIdArr = ids.split(","); 
            
		  	for (String s : labRoomDeviceIdArr) 
		  	{
		  		OperationItemDevice operationItemDevice = new OperationItemDevice();
		  		try {
					LabRoomDevice labRoomDevice = labRoomDeviceService.findLabRoomDeviceByPrimaryKey(Integer.parseInt(s));
					operationItemDevice.setOperationItem(findOperationItemByPrimaryKey(itemId));
					operationItemDevice.setLabRoomDevice(labRoomDevice);
					operationItemDevice.setCDictionary(shareService.getCDictionaryByCategory("category_operation_item_device_main", category));  //类型：公用，专用
					operationItemDeviceDAO.store(operationItemDevice);
				} catch (Exception e) {
					System.out.println("operationItemDevice保存失败");
				}
			}
		  	
		  	operationItemDeviceDAO.flush();
		}
	}

	/**
	 * 删除实验项目设备
	 * @author hly
	 * 2015.08.19
	 */
	@Override
	public boolean deleteItemDevice(Integer itemDeviceId) {
		OperationItemDevice operationItemDevice = operationItemDeviceDAO.findOperationItemDeviceByPrimaryKey(itemDeviceId);
		if(operationItemDevice!=null)
		{
			operationItemDeviceDAO.remove(operationItemDevice);
			operationItemDeviceDAO.flush();
		}
		return false;
	}

	/**
	 * 根据学院和角色获取用户数据
	 * @author hly
	 * 2015.08.28
	 */
	@Override
	public List<Map<String, String>> getUserByAcademyRole(String academyNumber, String role) {
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		StringBuffer hql = new StringBuffer("select u from User u where 1=1 ");
		if(academyNumber!=null && !"".equals(academyNumber))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		if(role!=null && !"".equals(role))
		{
			hql.append(" and u.userRole='"+role+"'");
		}
		List<User> users = userDAO.executeQuery(hql.toString(), 0, -1);
		for (User user : users) 
		{
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("username", user.getUsername());
			map.put("cname", user.getCname());
			
			result.add(map);
		}
		
		return result;
	}
	
	
	/**
	 * 根据学院和角色获取用户数据
	 * @author hly
	 * 2015.08.28
	 */
	@Override
	public List<Map<String, String>> getUserByAcademyRole(String academyNumber, String role, int authorityId) {
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		StringBuffer hql = new StringBuffer("select u from User u where 1=1 ");
		if(academyNumber!=null && !"".equals(academyNumber))
		{
			hql.append(" and u.schoolAcademy.academyNumber='"+academyNumber+"' ");
		}
		if(role!=null && !"".equals(role))
		{
			hql.append(" and u.userRole='"+role+"'");
		}
		List<User> users = userDAO.executeQuery(hql.toString(), 0, -1);
		//判断当前登陆人是否为指定权限
		List<User> departmentHeaders=new ArrayList<User>();
		for (User user2 : users) {
			String judge=",";
			for(Authority authority:user2.getAuthorities()){
				judge = judge + "," + authority.getId() + "," ;
			}
			if(judge.indexOf(","+authorityId+",")>-1){//18--系主任
				departmentHeaders.add(user2);
			}
		}
		for (User user : departmentHeaders) 
		{
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("username", user.getUsername());
			map.put("cname", user.getCname());
			
			result.add(map);
		}
		
		return result;
	}

	/**********************************
	 * 功能：找出operationitem表中的创建者字段
	 * 作者：贺子龙
	 * 时间：2015-09-02
	 *********************************/
	
	

	@Override
	public Map<String, String> getsome() {
		Map<String, String> map=new HashMap<String, String>();
		
		String s="select u from OperationItem u where u.userByLpCreateUser.username is not null group by u.userByLpCreateUser.username";
		List<OperationItem>   list=operationItemDAO.executeQuery(s);
		if(list.size()>0){
		for(OperationItem op:list){
			map.put(op.getUserByLpCreateUser().getUsername(), op.getUserByLpCreateUser().getCname());
		}
		}
		return map;
		
	}

	/**********************************
	 * 功能：找出operationitem表中的课程段
	 * 作者：贺子龙
	 * 时间：2015-09-02
	 *********************************/
	public Map<String, String>getCourse(int cid){
		Map<String, String> map=new HashMap<String, String>();
		String s="select u from OperationItem u where u.schoolCourseInfo.courseNumber is not null";
		s+=" and u.labRoom.labCenter.id="+cid; 
		s+=" group by u.schoolCourseInfo.courseNumber";
		// 去掉and (u.labRoom.id between 42 and 50)  贺子龙  2015-11-12
		List<OperationItem>   list=operationItemDAO.executeQuery(s,0,-1);
		if(list.size()>0){
		for(OperationItem op:list){
			map.put(op.getSchoolCourseInfo().getCourseNumber(), op.getSchoolCourseInfo().getCourseName());
		}
		}
		return map;
		
	}
	
	
	/**********************************
	 * 功能：找出operationitem表中的课程段(属于当前登录用户的)
	 * 作者：贺子龙
	 * 时间：2015-09-02
	 *********************************/
	public Map<String, String>getCourse(int cid,String username){
		Map<String, String> map=new HashMap<String, String>();
		String s="select u from OperationItem u where u.schoolCourseInfo.courseNumber is not null";
		s+=" and u.labRoom.labCenter.id="+cid; 
		s+=" and u.userByLpCreateUser.username='"+username+"'";
		s+=" group by u.schoolCourseInfo.courseNumber";
		// 去掉and (u.labRoom.id between 42 and 50)  贺子龙  2015-11-12
		List<OperationItem>   list=operationItemDAO.executeQuery(s,0,-1);
		if(list.size()>0){
		for(OperationItem op:list){
			map.put(op.getSchoolCourseInfo().getCourseNumber(), op.getSchoolCourseInfo().getCourseName());
		}
		}
		return map;
		
	}
	/*************************************************************************************
     * 功能：查找所有的运行记录
     * 作者 ：徐文
     * 日期：2016-05-27
     ***************************************************************************************/
	@Override
	public int getOutlinelist(OperationOutline operationOutline,int currpage,int pagesize,int sid) {
		String sql="select count(o) from OperationOutline o where 1=1";
		
	 if(operationOutline.getLabOutlineName()!=null){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		  }
	 

//		 LabCenter labCenter=labCenterDAO.findLabCenterById(sid);
//		   if(labCenter.getSchoolAcademy()!=null){ 
//		  sql+=" and  (o.schoolAcademy.academyNumber='"+labCenter.getSchoolAcademy().getAcademyNumber()+"' or o.user.username='"+shareService.getUser().getUsername()+"')";
//		   }
		   
//         try{
//        		return ((Long) operationOutlineDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
//     	}catch (Exception e) {
//     		
//     	return 0;
//     	} 
	 return ((Long) operationOutlineDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	/*************************************************************************************
     * 功能：查找所有的运行记录分页
     * 作者 ：徐文
     * 日期：2016-05-27
     ***************************************************************************************/
	@Override
	public List<OperationOutline> getOutlinelistpage(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize,int sid) {
		Integer dictionaryTermId = null;
		if(request.getParameter("searchterm")!=null&&!request.getParameter("searchterm").equals("")){
			dictionaryTermId = Integer.valueOf(request.getParameter("searchterm"));
		}
		String sql="select o from OperationOutline o left join o.studyStages oc where 1=1 ";
		if(dictionaryTermId!=null){
			sql += " and oc.id = " + dictionaryTermId;
		}
		
		//sql+="and o.schoolAcademy.academyNumber like  '%"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"%'";
		 if(operationOutline.getLabOutlineName()!=null&&!operationOutline.getLabOutlineName().equals("")){
		   sql+=" and (o.labOutlineName like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseName  like '%"+operationOutline.getLabOutlineName().trim()+"%'";
		   sql+=" or o.schoolCourseInfoByClassId.courseNumber  like '%"+operationOutline.getLabOutlineName().trim()+"%')";
		   
		  }
		 if(operationOutline.getStatus() != null && !operationOutline.getStatus().equals("")){
			 sql += " and o.status = "+operationOutline.getStatus();
		 }
		 if(operationOutline.getSchoolCourseInfoByClassId() != null && operationOutline.getSchoolCourseInfoByClassId().getCourseNumber() != null && !operationOutline.getSchoolCourseInfoByClassId().getCourseNumber().equals("")){
			 sql += " and o.schoolCourseInfoByClassId.courseNumber = '"+operationOutline.getSchoolCourseInfoByClassId().getCourseNumber()+"'";
		 }
		 if(!request.getSession().getAttribute("authorityName").equals("SUPERADMIN") && !request.getSession().getAttribute("authorityName").equals("LABCENTERMANAGER")){
			 sql+=" and o.schoolCourseInfoByClassId.academyNumber like '%"+shareService.getUser().getSchoolAcademy().getAcademyNumber()+"%'" ; 
		 }
		 sql += " group by o.id";
		return  operationOutlineDAO.executeQuery(sql,(currpage-1)*pagesize,pagesize);
	}
	/***************************************************************************************
     * 功能 ：查找大纲
     * 作者：徐文
     * 日期：2016-05-27
     **************************************************************************************/
	@Override
	public OperationOutline getoperationoutlineinfor(int idkey) {
		
		return operationOutlineDAO.findOperationOutlineById(idkey);
	}
	/***********************************************************************************
     * 功能 ： 查找未被大纲使用的项目卡项目卡数
     * 作者：徐文
     * 日期：2016-05-27
     ***********************************************************************************/
	@Override
	public List<OperationItem> getoperationItemlist() {
		String sql="select o from OperationItem o where 1=1 and o.id!=0 ";
		 
		return  operationItemDAO.executeQuery(sql);
	}
	/***********************************************************************************
     * 功能 ：查找所有课程info
     * 作者：徐文
     * 日期：2016-05-27
     ***********************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getschoolcouresMap(Integer sid) {
		Map attributesMap=new HashMap();
		 String sql="select s from SchoolCourseInfo s left join s.schoolCourses sc where 1=1 ";
		 //sql +="and sc.schoolCourseInfo.courseNumber=s.courseNumber ";
		 //sql +="and sc.schoolAcademy.academyNumber like  '%"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"%'";
		 
		 
		
	List<SchoolCourseInfo> d=	schoolCourseInfoDAO.executeQuery(sql,1,-1);
		for(SchoolCourseInfo schoolCourseInfo: d)
			attributesMap.put(schoolCourseInfo.getCourseNumber(), schoolCourseInfo.getCourseName()+" "+schoolCourseInfo.getCourseNumber());
		return attributesMap;	
	}
	/***********************************************************************************
     * 功能 ： 查找所在专业
     * 作者：徐文
     * 日期：2016-05-27
     ***********************************************************************************/
	@Override
	public List<SystemMajor12> getschoolmajerSet(Integer sid) {
		 String sql="select s from  SystemMajor12 s  where 1=1 ";
		 List<SystemMajor12> systemMajors= systemMajor12DAO.executeQuery(sql,0,-1);
		 return systemMajors;
	}
	/***********************************************************************************
     * 功能 ：查学分
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getcoperationscareMap() {
		Map coperationOutlineCredit=new HashMap();
		for (COperationOutlineCredit it : cOperationOutlineCreditDAO.findAllCOperationOutlineCredits()) {
			coperationOutlineCredit.put(it.getId(), it.getCredit());
		}
		return coperationOutlineCredit;
	}
	/***********************************************************************************
     * 功能 ：查开课学院
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	@Override
	public Map getoperationstartschooleMap(Integer sid) {
		 Map academy=new HashMap();
		 String sql ="select s from SchoolAcademy s  where 1=1 ";
		 //sql +="and s.academyNumber =  '"+labCenterDAO.findLabCenterById(sid).getSchoolAcademy().getAcademyNumber()+"'";
		 List<SchoolAcademy> d=	SchoolAcademyDAO.executeQuery(sql,0,-1);
		 for (SchoolAcademy schoolAcademy : d) {
			 academy.put(schoolAcademy.getAcademyNumber(), schoolAcademy.getAcademyName());
		}
		return academy;
	}
	/***********************************************************************************
     * 功能 ：查找课程性质
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	@Override
	public List<CDictionary> getcommencementnatureSet() {
		String sql="select s from  CDictionary s where s.CCategory ='c_operation_outline_property'";
		List<CDictionary> commencementnaturemap=cDictionaryDAO.executeQuery(sql);
		return commencementnaturemap;
	}
	
	/***********************************************************************************
     * 功能 ：查找课程类型
     * 作者：张凯
     * 日期：2017-03-8
     ***********************************************************************************/
	@Override
	public List<CDictionary> getCourseType() {
		String sql="select s from  CDictionary s where s.CCategory ='c_operation_outline_type'";
		List<CDictionary> coursetypemap=cDictionaryDAO.executeQuery(sql);
		return coursetypemap;
	}
	
	/*********************************************************************************
	 * 功能:保存大纲内容
	 * 作者：徐文
	 * 日期：2016-05-30
	 ********************************************************************************/
	@Override
	public OperationOutline saveoperationoutline(OperationOutline operationOutline,//commencementnaturemap是课程性质，projectitrms是item，schoolMajors是面向专业
			HttpServletRequest request) {
		//operationOutline.setSchoolCourseInfoByFollowUpCourses(schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(operationOutline.getSchoolCourseInfoByFollowUpCourses().getCourseNumber()));
		//operationOutline.setSchoolCourseInfoByFirstCourses(schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(operationOutline.getSchoolCourseInfoByFirstCourses().getCourseNumber()));
		if(operationOutline.getSchoolCourseInfoByClassId()!=null&&!operationOutline.getSchoolCourseInfoByClassId().equals("")){
			operationOutline.setSchoolCourseInfoByClassId(schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(operationOutline.getSchoolCourseInfoByClassId().getCourseNumber()));
			SchoolCourseInfo si= operationOutline.getSchoolCourseInfoByClassId();
			operationOutline.setLabOutlineName(si.getCourseName()+" "+si.getCourseNumber());
		}else{
			operationOutline.setSchoolCourseInfoByClassId(schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(request.getParameter("coursenumber")));
		}
		//保存实验大纲名称
		if(request.getParameter("coursenumber")!=null&&!request.getParameter("coursenumber").equals("")){
			SchoolCourseInfo si= schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(request.getParameter("coursenumber"));
			operationOutline.setLabOutlineName(si.getCourseName()+" "+si.getCourseNumber());
		}
		//保存学分
		if(operationOutline.getCOperationOutlineCredit()!=null&&!operationOutline.getCOperationOutlineCredit().equals("")){
			operationOutline.setCOperationOutlineCredit(cOperationOutlineCreditDAO.findCOperationOutlineCreditById(operationOutline.getCOperationOutlineCredit().getId()));
		}else{
			operationOutline.setCOperationOutlineCredit(cOperationOutlineCreditDAO.findCOperationOutlineCreditById(Integer.parseInt(request.getParameter("credit"))));
		}
		if(operationOutline.getCourseDescription()==null||operationOutline.getCourseDescription()==""){
			if(request.getParameter("coursetype") != null){
				CDictionary cd = cDictionaryDAO.findCDictionaryById(Integer.parseInt(request.getParameter("coursetype")));
				operationOutline.setCourseDescription(cd.getCName());
			}
		}
		operationOutline = operationOutlineDAO.store(operationOutline);
		//获取课程性质多对多
		String[] commencementnaturemaps = request.getParameterValues("commencementnaturemap");
		//获取面向专业多对多
		String[] schoolMajors = request.getParameterValues("schoolMajorsa");
		if( request.getParameter("projectitrms") != null)
		{
			String[] projectitrms = request.getParameter("projectitrms").split(",");
			 if(projectitrms!=null && !projectitrms.equals("") && projectitrms.length>0  ){
			      this.saveoperationoutlineitems(operationOutline.getId(),projectitrms);
			      }
		}
		//获取开课学院多对多
		String[] academynumbers = request.getParameterValues("academynumbers");
		//获取先修课程多对多
		String[] firstCourses = request.getParameterValues("firstcourses");
		//获取开课学期
		String[] termIdes = request.getParameterValues("termIdes");
	    operationOutline.setUser(shareService.getUser());
	    operationOutline =  operationOutlineDAO.store(operationOutline);
		     if(schoolMajors!=null ){
		       this.saveSystemMajor(operationOutline.getId(),schoolMajors);
		     }
		     if(commencementnaturemaps!=null ){
	         	this.saveoperationoutlineproperty(operationOutline.getId(),commencementnaturemaps);
		     }
		    
		     if(academynumbers!=null){
		    	 this.saveoperationoutlineacademies(operationOutline.getId(), academynumbers);
		     }
		     if(firstCourses!=null){
		    	 this.saveoperationoutlinefirstcourse(operationOutline.getId(), firstCourses);
		     }
		     if(termIdes!=null){
		    	 this.saveoperationoutlineterms(operationOutline.getId(), termIdes);
		     }
		     return operationOutline;
	}
	
	   /***********************************************************************************
	    * 功能 ：保存major
	    * 作者：徐文
	    * 日期：2016-05-31
	    ***********************************************************************************/
		private  void   saveSystemMajor(int outlineId,String[] systemmajor ){
			OperationOutline outline=operationOutlineDAO.findOperationOutlineById(outlineId);
			Set<SystemMajor12> historymajor=outline.getSystemMajors();
			if (historymajor!=null && historymajor.size()>0) {
				for (SystemMajor12 operationMajor : historymajor) {
					historymajor.remove(operationMajor);
				}
			}
			 if (systemmajor!=null ) {
				if (systemmajor!=null && systemmajor.length>0) {
					for (String majorString : systemmajor) {
						SystemMajor12 major=systemMajor12DAO.findSystemMajor12ByPrimaryKey(majorString);
						historymajor.add(major);
					}
				}
			}
			outline.setSystemMajors(historymajor);
			operationOutlineDAO.store(outline);
			operationOutlineDAO.flush();	
		   }
   /***********************************************************************************
     * 功能 ：保存课程性质
     * 作者：贺子龙
     * 日期：2016-05-31
     ***********************************************************************************/
	private  void   saveoperationoutlineproperty(int outlineId,String[] courseproperty ){
		/**
		* 1、把jsp传来的字符串转化成字符数组；
		* 2、轮询字符数组，找到对应的课程性质
		* 3、将所得到的课程性质集合赋值给OperationOutline对象
		*/
		OperationOutline outline=operationOutlineDAO.findOperationOutlineById(outlineId);
		Set<CDictionary> historyproperty=outline.getCDictionarys();
		if (historyproperty!=null && historyproperty.size()>0) {
			for (CDictionary cDictionary : historyproperty) {
				historyproperty.remove(cDictionary);
			}
		}
		if (courseproperty!=null) {
			if (courseproperty!=null && courseproperty.length>0) {
				for (String creditString : courseproperty) {
					//int creditId=Integer.parseInt(creditString);
					CDictionary property=cDictionaryDAO.findCDictionaryByPrimaryKey(Integer.parseInt(creditString));
					historyproperty.add(property);
				}
			}
		}
		outline.setCDictionarys(historyproperty);
		operationOutlineDAO.store(outline);
		operationOutlineDAO.flush();	
	   }
	/***********************************************************************************
     * 功能 ：保存item
     * 作者：徐文
     * 日期：2016-05-31
     ***********************************************************************************/
	private  void   saveoperationoutlineitems(int outlineId,String[] courseitems ){
		OperationOutline outline=operationOutlineDAO.findOperationOutlineById(outlineId);
			Set<OperationItem> historyitem=outline.getOperationItems();
			System.out.println(courseitems);
		
		if (historyitem!=null && historyitem.size()>0) {
			for (OperationItem operationItem : historyitem) {
				historyitem.remove(operationItem);
			}
		}
			if (courseitems!=null && courseitems.length>0 ) {
				for (String creditString : courseitems) {
					//int itemid=Integer.parseInt(creditString);
					if(creditString != null&& creditString!=""){
					OperationItem item=operationItemDAO.findOperationItemByPrimaryKey(Integer.parseInt(creditString));
					item.setOperationOutline(outline);
					operationItemDAO.store(item);
				}
				}
			}
		
		operationOutlineDAO.store(outline);
		operationOutlineDAO.flush();	
	   }
	/*********************************************************************************
	 * 功能:实验室大纲多文件上传
	 * 作者：徐文
	 * 日期：2016-06-01
	 ********************************************************************************/
	@SuppressWarnings({ "rawtypes" })
	@Override
	 public String   uploaddnolinedocment(HttpServletRequest request, HttpServletResponse response, Integer id){
		String  listid="";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
		 String sep = System.getProperty("file.separator"); 
		 Map files = multipartRequest.getFileMap(); 
		 Iterator fileNames = multipartRequest.getFileNames();
		 boolean flag =false; 
		 String suiji = UUID.randomUUID().toString();
		 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"operation"+sep+suiji;
		//存放文件文件夹名称
		for(; fileNames.hasNext();){
			
		  String filename = (String) fileNames.next(); 
		  CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename); 
		  byte[] bytes = file.getBytes(); 
		  if(bytes.length != 0) {
			  // 说明申请有附件
			  if(!flag) { 
				  File dirPath = new File(fileDir); 
				  if(!dirPath.exists()) { 
					  flag = dirPath.mkdirs();
		              } 
		      } 
			  String fileTrueName = file.getOriginalFilename(); 
			  //System.out.println("文件名称："+fileTrueName);
			  File uploadedFile = new File(fileDir + sep + fileTrueName); 
			  //System.out.println("文件存放路径为："+fileDir + sep + fileTrueName);
			  try {
				FileCopyUtils.copy(bytes,uploadedFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  CommonDocument conn=new CommonDocument();
			  conn.setDocumentName(fileTrueName);
			  conn.setDocumentUrl(fileDir + sep + fileTrueName);
			  if(id!=null){
			  conn.setOperationOutline(operationOutlineDAO.findOperationOutlineById(id));
			  }
			 CommonDocument ids= commonDocumentDAO.store(conn);
			 listid="<tr id='s_"+ids.getId()+"'><td>"+ids.getDocumentName()+"</td><td><input type='button' onclick='delectuploaddocment("+ids.getId()+")' value='删除'/> </td></tr>"; 
		     
		  } 
		  
		}
		
		return listid;	 
	 }
	/*********************************************************************************
	  * 功能:实验室item搜索
	  * 作者：徐文
	  * 日期：2016-06-01
	  ********************************************************************************/
	 public  List<OperationItem> getitem(String a ){
		 String sql="select s from OperationItem s where 1=1  and s.id!=0 ";
			if(a!=null && a!=""){
				sql+=" and (s.lpName  like '%"+a+"%'";
			}
			if(a!=null && a!=""){
				sql+=" or s.lpCodeCustom like '%"+a+"%')";
			}  
		
		return operationItemDAO.executeQuery(sql);
	 }
	 /****************************************************************************
	  * 功能：删除实验室大纲
	  * 作者：徐文
	  * 日期：2016-06-01
	  ****************************************************************************/
	 public void  delectloutline(int idkey){
		 OperationOutline s=operationOutlineDAO.findOperationOutlineById(idkey);
			
			operationOutlineDAO.remove(s);
	 }
	 
	 /*********************************************************************************
		 *@description:实验室项目多文件上传
		 *@author: 郑昕茹
		 *@date：2016-11-09
		 ********************************************************************************/
		@SuppressWarnings({ "rawtypes" })
		@Override
		 public String   uploadItemdocument(HttpServletRequest request, HttpServletResponse response, Integer id){
			String  listid="";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
			 String sep = System.getProperty("file.separator"); 
			 Map files = multipartRequest.getFileMap(); 
			 Iterator fileNames = multipartRequest.getFileNames();
			 boolean flag =false; 
			 String suiji = UUID.randomUUID().toString();
			 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"operation"+sep+suiji;
			//存放文件文件夹名称
			for(; fileNames.hasNext();){
				
			  String filename = (String) fileNames.next(); 
			  CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename); 
			  byte[] bytes = file.getBytes(); 
			  if(bytes.length != 0) {
				  // 说明申请有附件
				  if(!flag) { 
					  File dirPath = new File(fileDir); 
					  if(!dirPath.exists()) { 
						  flag = dirPath.mkdirs();
			              } 
			      } 
				  String fileTrueName = file.getOriginalFilename(); 
				  //System.out.println("文件名称："+fileTrueName);
				  File uploadedFile = new File(fileDir + sep + fileTrueName); 
				  //System.out.println("文件存放路径为："+fileDir + sep + fileTrueName);
				  try {
					FileCopyUtils.copy(bytes,uploadedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				  CommonDocument conn=new CommonDocument();
				  conn.setDocumentName(fileTrueName);
				  conn.setDocumentUrl(fileDir + sep + fileTrueName);
				  if(id!=null){
				  conn.setOperationItem(operationItemDAO.findOperationItemById(id));
				  }
				 CommonDocument ids= commonDocumentDAO.store(conn);
				 listid="<tr id='s_"+ids.getId()+"'><td>"+ids.getDocumentName()+"</td><td><input type='button' onclick='delectuploaddocment("+ids.getId()+")' value='删除'/> </td></tr>"; 
			     
			  } 
			  
			}
			
			return listid;	 
		 }
		
		/**************************************************************************************
	     * 功能：实验大纲管理-导入实验大纲
	     * 作者：罗璇
	     * 日期：2017年3月5日
	     **************************************************************************************/
		@Transactional
		public void importOperationOutlines(String File,Integer sid){
			Boolean isE2007=false;
			if(File.endsWith("xlsx")){
				isE2007=true;
			}
			//建立输入流
			try {
				//建立输入流
				InputStream input = new FileInputStream(File);
				Workbook wb =null;
				if(isE2007){
					wb=new XSSFWorkbook(input);
				}else{
					wb=new HSSFWorkbook(input);
				}
				//获取第一个表单数据
				Sheet sheet= wb.getSheetAt(0);
				//获取第一个表单迭代器
				Iterator<Row>rows=sheet.rowIterator();
				Row rowContent=null;// 表头
				//以下变量名全部使用domain对应字段名
				String schoolCourseInfoByClassId ="";//课程代码
				String enName = "";//英文名称
				String systemMajors = "";//面向专业
				String COperationOutlineCredit = "";//课程学分
				String schoolAcademy = "";//开课学院
				String CDictionarys = "";//课程性质
				//String schoolCourseInfoByFollowUpCourses = "";//后续课程
				String schoolCourseInfoByFirstCourses = "";//先修课程
				String labOutlineName = "";//大纲名称
				String period="";
				String schoolTerm = "";//所属学期
				String useMaterials = "";//使用教材
				String courseDescription = "";//课程类型
				//String coursesAdvice = "";//选课建议
				String outlineCourseTeachingTarget = "";//任务和教学目标
				String basicContentCourse = "";//本课程与其它课程的联系和分工
				//String basicRequirementsCourse = "";//课程基本要求
				String assResultsPerEvaluation = "";//作业、考核成绩及成绩评定
				//String outlineCourseTeachingTargetOver = "";//课程任务和教学目标
				
				int a=0;
				while(rows.hasNext()){
					
					if(a==0){
						rowContent=rows.next();
						a=1;
					}
					Row row =rows.next();
					int column=sheet.getRow(0).getPhysicalNumberOfCells();
					for(int k=0;k<column;k++){
						if(row.getCell(k)!=null){
							row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
							String columnName = rowContent.getCell(k).getStringCellValue();
							String content = row.getCell(k).getStringCellValue();
							if(columnName.equals("课程代码")){
								schoolCourseInfoByClassId = content;
							}
							if(columnName.equals("英文名称")){
								enName = content;
							}
							if(columnName.equals("面向专业")){
								systemMajors = content;
							}
							if(columnName.equals("课程学分")){
								COperationOutlineCredit = content;
							}
							if(columnName.equals("开课学院")){
								schoolAcademy = content;
							}
							if(columnName.equals("课程性质")){
								CDictionarys = content;
							}
							/*if(columnName.equals("后续课程")){
								schoolCourseInfoByFollowUpCourses = content;
							}*/
							if(columnName.equals("先修课程")){
								schoolCourseInfoByFirstCourses = content;
							}
							/*if(columnName.equals("学时")){
								labOutlineName = content;
							}*/
							if(columnName.equals("学时")){
								period = content;
							}
							if(columnName.equals("所属学期")){
								schoolTerm = content;
							}
							if(columnName.equals("使用教材")){
								useMaterials = content;
							}
							if(columnName.equals("课程类型")){
								courseDescription = content;
							}
							/*if(columnName.equals("选课建议")){
								coursesAdvice = content;
							}*/
							
							if(columnName.equals("本课程与其它课程的联系和分工")){
								basicContentCourse = content;
							}
							if(columnName.equals("任务和教学目标")){
								outlineCourseTeachingTarget = content;
							}
							
							/*
							if(columnName.equals("课程基本要求")){
								basicRequirementsCourse = content;
							}*/
							if(columnName.equals("作业、考核成绩及成绩评定")){
								assResultsPerEvaluation = content;
							}
							/*if(columnName.equals("课程任务和教学目标")){
								outlineCourseTeachingTargetOver = content;
							}*/
						}
					}
					//开始处理从excel读取的数据并存入表
					//实例化一个空的实验大纲对象
					OperationOutline operationOutline = new OperationOutline();
					//课程代码
					if(!schoolCourseInfoByClassId.equals("")){
						SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
						schoolCourseInfo.setCourseNumber(schoolCourseInfoByClassId.trim());
						operationOutline.setSchoolCourseInfoByClassId(schoolCourseInfo);
					}
					//实验大纲名称
					if(operationOutline.getSchoolCourseInfoByClassId()!=null){
						SchoolCourseInfo schoolCourseInfo = schoolCourseInfoDAO.findSchoolCourseInfoByPrimaryKey(operationOutline.getSchoolCourseInfoByClassId().getCourseNumber());
						operationOutline.setLabOutlineName(schoolCourseInfo.getCourseName()+" "+schoolCourseInfo.getCourseNumber());
					}
					//英文名称
					if(!enName.equals("")){
						operationOutline.setEnName(enName);
					}
					//面向专业
					if(!systemMajors.equals("")){
						//将字符串以逗号隔开转成major12集合
						String[] majorArray = systemMajors.split(",");
						//转为对象set集合
						Set<SystemMajor12> majorSet = new HashSet<SystemMajor12>();;
						for(String majorNum : majorArray){
							SystemMajor12 systemMajor12 = new SystemMajor12();
							systemMajor12.setMNumber(majorNum);
							majorSet.add(systemMajor12);
						}
						//注入set到持久层
						operationOutline.setSystemMajors(majorSet);
					}
					//课程学分
					if(!COperationOutlineCredit.equals("")){
						Integer creditId = Integer.parseInt(COperationOutlineCredit.trim());
						COperationOutlineCredit credit = new COperationOutlineCredit();
						credit.setId(creditId);
						operationOutline.setCOperationOutlineCredit(credit);
					}
					//开课学院
					if(!schoolAcademy.equals("")){
						/*SchoolAcademy academy = new SchoolAcademy();
						academy.setAcademyNumber(schoolAcademy.trim());
						operationOutline.setSchoolAcademy(academy);*/
						String[] schoolAcademyArray = schoolAcademy.split(",");
						//转为对象set集合
						Set<SchoolAcademy> schoolAcademySet = new HashSet<SchoolAcademy>();;
						for(String schoolacademy : schoolAcademyArray){
							SchoolAcademy temp = new SchoolAcademy();
							temp.setAcademyNumber(schoolacademy);
							schoolAcademySet.add(temp);
						}
						operationOutline.setSchoolAcademies(schoolAcademySet);
					}
					//课程性质
					if(!CDictionarys.equals("")){
						//将字符串以逗号隔开转成major12集合
						String[] cDictionaryArray = CDictionarys.split(",");
						//转为对象set集合
						Set<CDictionary> cDictionarySet = new HashSet<CDictionary>();;
						for(String cDictionary : cDictionaryArray){
							CDictionary temp = new CDictionary();
							temp.setId(Integer.parseInt(cDictionary.trim()));
							cDictionarySet.add(temp);
						}
						operationOutline.setCDictionarys(cDictionarySet);
					}
					//后续课程
					/*if(!schoolCourseInfoByFollowUpCourses.equals("")){
						SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
						schoolCourseInfo.setCourseNumber(schoolCourseInfoByFollowUpCourses.trim());
						operationOutline.setSchoolCourseInfoByFollowUpCourses(schoolCourseInfo);
					}*/
					//先修课程
					if(!schoolCourseInfoByFirstCourses.equals("")){
						//将字符串以逗号隔开转成major12集合
						String[] firstCoursesArray = schoolCourseInfoByFirstCourses.split(",");
						//转为对象set集合
						Set<SchoolCourseInfo> firstCourseSet = new HashSet<SchoolCourseInfo>();;
						for(String firstCourse : firstCoursesArray){
							SchoolCourseInfo temp = new SchoolCourseInfo();
							temp.setCourseNumber(firstCourse);
							firstCourseSet.add(temp);
						}
						operationOutline.setSchoolCourseInfoes(firstCourseSet);
						
						/*SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
						schoolCourseInfo.setCourseNumber(schoolCourseInfoByFirstCourses.trim());
						operationOutline.setSchoolCourseInfoByFirstCourses(schoolCourseInfo);*/
					}
					//大纲名称
					/*if(!labOutlineName.equals("")){
						operationOutline.setLabOutlineName(labOutlineName);
					}*/
					if(!period.equals("")){
						operationOutline.setPeriod(Double.parseDouble(period.trim()));
					}
					//所属学期
					if(!schoolTerm.equals("")){
						/*SchoolTerm term = new SchoolTerm();
						term.setId(Integer.parseInt(schoolTerm.trim()));
						operationOutline.setSchoolTerm(term);*/
						
						//将字符串以逗号隔开转成major12集合
						String[] schoolTermArray = schoolTerm.split(",");
						//转为对象set集合
						Set<CDictionary> schoolTermSet = new HashSet<CDictionary>();;
						for(String termId : schoolTermArray){
							CDictionary temp = new CDictionary();
							temp.setId(Integer.parseInt(termId.trim()));
							schoolTermSet.add(temp);
						}
						operationOutline.setStudyStages(schoolTermSet);
					}
					//使用教材
					if(!useMaterials.equals("")){
						operationOutline.setUseMaterials(useMaterials);
					}
					//课程简介
					if(!courseDescription.equals("")){
						operationOutline.setCourseDescription(courseDescription);
					}
					/*
					//选课建议
					if(!coursesAdvice.equals("")){
						operationOutline.setCoursesAdvice(coursesAdvice);
					}*/
					//任务和教学目标
					if(!outlineCourseTeachingTarget.equals("")){
						operationOutline.setOutlineCourseTeachingTarget(outlineCourseTeachingTarget);
					}
					//课程基本内容
					if(!basicContentCourse.equals("")){
						operationOutline.setBasicContentCourse(basicContentCourse);
					}
					/*
					//课程基本要求
					if(!basicRequirementsCourse.equals("")){
						operationOutline.setBasicRequirementsCourse(basicRequirementsCourse);
					}*/
					//作业、考核成绩及成绩评定
					if(!assResultsPerEvaluation.equals("")){
						operationOutline.setAssResultsPerEvaluation(assResultsPerEvaluation);
					}
					//课程任务和教学目标
					/*if(!outlineCourseTeachingTargetOver.equals("")){
						operationOutline.setOutlineCourseTeachingTargetOver(outlineCourseTeachingTargetOver);
					}*/
					//创建人
					operationOutline.setUser(shareService.getUser());
					//lab_center
					LabCenter labCenter = new LabCenter();
					labCenter.setId(sid);
					operationOutline.setLabCenter(labCenter);
					//持久化保存
					operationOutlineDAO.store(operationOutline);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		
		
		}
		
		 /***********************************************************************************
	     * 功能 ：保存开课学院
	     * 作者：张凯
	     * 日期：2017-03-8
	     ***********************************************************************************/
		private  void   saveoperationoutlineacademies(int outlineId,String[] academies ){
			/**
			* 1、把jsp传来的字符串转化成字符数组；
			* 2、轮询字符数组，找到对应的课程性质
			* 3、将所得到的课程性质集合赋值给OperationOutline对象
			*/
			OperationOutline outline=operationOutlineDAO.findOperationOutlineById(outlineId);
			Set<SchoolAcademy> historyacademy=outline.getSchoolAcademies();
			if (historyacademy!=null && historyacademy.size()>0) {
				for (SchoolAcademy schoolAcademy : historyacademy) {
					historyacademy.remove(schoolAcademy);
				}
			}
			if (academies!=null) {
				if (academies!=null && academies.length>0) {
					for (String creditString : academies) {
						//int creditId=Integer.parseInt(creditString);
						SchoolAcademy academy=SchoolAcademyDAO.findSchoolAcademyByAcademyNumber(creditString);
						historyacademy.add(academy);
					}
				}
			}
			outline.setSchoolAcademies(historyacademy);
			operationOutlineDAO.store(outline);
			operationOutlineDAO.flush();	
		   }
		

		 /***********************************************************************************
	     * 功能 ：保存先修课程
	     * 作者：张凯
	     * 日期：2017-03-8
	     ***********************************************************************************/
		private  void   saveoperationoutlinefirstcourse(int outlineId,String[]  firstCourses ){
			/**
			* 1、把jsp传来的字符串转化成字符数组；
			* 2、轮询字符数组，找到对应的课程性质
			* 3、将所得到的课程性质集合赋值给OperationOutline对象
			*/
			OperationOutline outline=operationOutlineDAO.findOperationOutlineById(outlineId);
			Set<SchoolCourseInfo> historyacademy=outline.getSchoolCourseInfoes();
			if (historyacademy!=null && historyacademy.size()>0) {
				for (SchoolCourseInfo schoolCourseInfo : historyacademy) {
					historyacademy.remove(schoolCourseInfo);
				}
			}
			if ( firstCourses!=null) {
				if ( firstCourses!=null &&  firstCourses.length>0) {
					for (String creditString :  firstCourses) {
						//int creditId=Integer.parseInt(creditString);
						SchoolCourseInfo schoolCourseInfo=schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(creditString);
						historyacademy.add(schoolCourseInfo);
					}
				}
			}
			outline.setSchoolCourseInfoes(historyacademy);
			operationOutlineDAO.store(outline);
			operationOutlineDAO.flush();	
		   }
		
		/***********************************************************************************
	     * 功能 ：保存开课学期
	     * 作者：张凯
	     * 日期：2017-03-8
	     ***********************************************************************************/
		private  void   saveoperationoutlineterms(int outlineId,String[] termIdes ){
			/**
			* 1、把jsp传来的字符串转化成字符数组；
			* 2、轮询字符数组，找到对应的课程性质
			* 3、将所得到的课程性质集合赋值给OperationOutline对象
			*/
			OperationOutline outline=operationOutlineDAO.findOperationOutlineById(outlineId);
			Set<CDictionary> historyacademy=outline.getStudyStages();
			if (historyacademy!=null && historyacademy.size()>0) {
				for (CDictionary cd : historyacademy) {
					historyacademy.remove(cd);
				}
			}
			if (termIdes!=null) {
				if (termIdes!=null && termIdes.length>0) {
					for (String creditString : termIdes) {
						//int creditId=Integer.parseInt(creditString);
						CDictionary term = cDictionaryDAO.findCDictionaryById(Integer.parseInt(creditString));
						historyacademy.add(term);
					}
				}
			}
			outline.setStudyStages(historyacademy);
			operationOutlineDAO.store(outline);
			operationOutlineDAO.flush();	
		   }
		/*********************************************************************************
		 * 功能:个人管理上传头像
		 * 作者：戴昊宇
		 * 日期：2017-08-30
		 ********************************************************************************/
		@SuppressWarnings({ "rawtypes" })
		@Override
		 public String   uploadphoto(HttpServletRequest request, HttpServletResponse response, Integer id){
			String  listid="";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
			 String sep = System.getProperty("file.separator"); 
			 Map files = multipartRequest.getFileMap(); 
			 Iterator fileNames = multipartRequest.getFileNames();
			 boolean flag =false; 
			 String suiji = UUID.randomUUID().toString();
			 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"operation"+sep+suiji;
			//存放文件文件夹名称
			for(; fileNames.hasNext();){
				
			  String filename = (String) fileNames.next(); 
			  CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename); 
			  byte[] bytes = file.getBytes(); 
			  if(bytes.length != 0) {
				  // 说明申请有附件
				  if(!flag) { 
					  File dirPath = new File(fileDir); 
					  if(!dirPath.exists()) { 
						  flag = dirPath.mkdirs();
			              } 
			      } 
				  String fileTrueName = file.getOriginalFilename(); 
				  //System.out.println("文件名称："+fileTrueName);
				  File uploadedFile = new File(fileDir + sep + fileTrueName); 
				  //System.out.println("文件存放路径为："+fileDir + sep + fileTrueName);
				  try {
					FileCopyUtils.copy(bytes,uploadedFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				   String username = shareService.getUser().getUsername();
				   User findUserByUsername = shareService.findUserByUsername(username);
				   findUserByUsername.setPhoto("upload" + sep+"operation"+sep+suiji+sep + fileTrueName);
				
				userDAO.store(findUserByUsername);
		     
			  } 
			  
			}
			
			return listid;	 
		 }

}