package net.xidlims.service.device;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.service.EmptyUtil;
import net.xidlims.domain.SchoolDeviceUse;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoomDeviceLending;
import net.xidlims.domain.LabRoomDeviceLendingResult;
import net.xidlims.domain.LabRoomDeviceRepair;
import net.xidlims.domain.LabRoomDeviceReservationResult;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.CommonVideoDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceLendingDAO;
import net.xidlims.dao.LabRoomDeviceLendingResultDAO;
import net.xidlims.dao.LabRoomDevicePermitUsersDAO;
import net.xidlims.dao.LabRoomDeviceRepairDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.LabRoomDeviceReservationResultDAO;
import net.xidlims.dao.LabRoomDeviceTrainingDAO;
import net.xidlims.dao.LabRoomDeviceTrainingPeopleDAO;
import net.xidlims.dao.ResearchProjectDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SchoolDeviceUseDAO;
import net.xidlims.dao.TimetableItemRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDAO;
import net.xidlims.dao.TimetableLabRelatedDeviceDAO;
import net.xidlims.dao.TimetableTeacherRelatedDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonVideo;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDevicePermitUsers;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.LabRoomDeviceTrainingPeople;
import net.xidlims.domain.ResearchProject;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableLabRelatedDevice;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import edu.emory.mathcs.backport.java.util.LinkedList;
import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

@Service("LabRoomDeviceService")
public class LabRoomDeviceServiceImpl implements  LabRoomDeviceService {

	@PersistenceContext private EntityManager entityManager;
	@Autowired  LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired  UserDAO userDAO;
	@Autowired   CommonDocumentDAO documentDAO;
	@Autowired   CommonVideoDAO videoDAO;
	@Autowired   LabRoomAdminDAO labRoomAdminDAO;
	@Autowired   TimetableLabRelatedDAO timetableLabRelatedDAO;
	@Autowired   ShareService shareService;
	@Autowired   SchoolDeviceDAO schoolDeviceDAO;
	@Autowired   LabRoomDAO labRoomDAO;
	@Autowired	LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired	LabRoomDeviceTrainingDAO labRoomDeviceTrainingDAO;
	@Autowired	LabRoomDeviceTrainingPeopleDAO labRoomDeviceTrainingPeopleDAO;
	@Autowired	LabRoomDevicePermitUsersDAO labRoomDevicePermitUsersDAO;
	@Autowired	LabCenterDAO labCenterDAO;
	@Autowired	LabRoomDeviceLendingDAO labRoomDeviceLendingDAO;
	@Autowired	LabRoomDeviceLendingResultDAO labRoomDeviceLendingResultDAO;
	@Autowired	LabRoomDeviceRepairDAO labRoomDeviceRepairDAO;
	@Autowired	LabRoomDeviceReservationResultDAO labRoomDeviceReservationResultDAO;
	@Autowired  TimetableLabRelatedDeviceDAO timetableLabRelatedDeviceDAO;
	@Autowired TimetableTeacherRelatedDAO timetableTeacherRelatedDAO;
	@Autowired TimetableItemRelatedDAO timetableItemRelatedDAO;
	@Autowired ResearchProjectDAO researchProjectDAO;
	@Autowired SchoolDeviceUseDAO schoolDeviceUseDAO;
	@Autowired SchoolDeviceService schoolDeviceService;
	/****************************************************************************
	 * 功能：保存实验分室设备
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public LabRoomDevice save(LabRoomDevice device) {
		
		return labRoomDeviceDAO.store(device);
	}
	/****************************************************************************
	 * 功能：根据实验分室id查询实验分室的设备
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDevice> findLabRoomDeviceByRoomId(Integer id) {
		
		String sql="select d from LabRoomDevice d where d.id not in (select ld.labRoomDevice.id from OperationItemDevice ld) and d.labRoom.id="+id;
		return labRoomDeviceDAO.executeQuery(sql);
	}
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public int findAllLabRoomDeviceNew(LabRoomDevice device,Integer cid,Integer isReservation) {
		String sql="select count(d) from LabRoomDevice d where 1=1 ";
		//实验室
		if(device.getLabRoom()!=null){
			if(device.getLabRoom().getId()!=null&&!device.getLabRoom().getId().equals("")){
				sql+=" and d.labRoom.id="+device.getLabRoom().getId();
			}
		}
		//设备
		if(device.getSchoolDevice()!=null){
			//设备编号
			if(device.getSchoolDevice().getDeviceNumber()!=null&&!device.getSchoolDevice().getDeviceNumber().equals("")){
				sql+=" and d.schoolDevice.deviceNumber like '%"+device.getSchoolDevice().getDeviceNumber()+"%'";
			}
			//设备名称
			if(device.getSchoolDevice().getDeviceName()!=null&&!device.getSchoolDevice().getDeviceName().equals("")){
				sql+=" and d.schoolDevice.deviceName like '%"+device.getSchoolDevice().getDeviceName()+"%'";
			}
			if(device.getUser()!=null){
				//设备管理员
				if(device.getUser().getUsername()!=null&&!device.getUser().getUsername().equals("")){
					sql+="and d.user.username  like '%"+device.getUser().getUsername()+"%'";
				}
			}
		}
		if(device != null && device.getCDictionaryByAllowAppointment() != null 
				&& device.getCDictionaryByAllowAppointment().getCNumber() != null
				&& !device.getCDictionaryByAllowAppointment().getCNumber().equals("")){
			if(device.getCDictionaryByAllowAppointment().getCNumber().equals("1"))
			{
				sql += " and d.CDictionaryByAllowAppointment.CNumber ='"+device.getCDictionaryByAllowAppointment().getCNumber()+"'";
			}
			else{
				sql += " and (d.CDictionaryByAllowAppointment.CNumber !='1' or d.CDictionaryByAllowAppointment is null)";
			}
		}
		if(isReservation != null && isReservation == 1){
			sql += " and d.labRoom.labRoomActive=1";
		}
		/*if(cid!=null){
			sql+=" and d.labRoom.labCenter.id="+cid;
		}*/
		
		
		return ((Long) labRoomDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备并分页
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDevice> findAllLabRoomDeviceNew(LabRoomDevice device,Integer cid,
			Integer page, int pageSize,Integer isReservation) {
		String sql="select d from LabRoomDevice d where 1=1 ";
		if(device.getLabRoom()!=null){
			if(device.getLabRoom().getId()!=null&&!device.getLabRoom().getId().equals("")){
				sql+=" and d.labRoom.id="+device.getLabRoom().getId();
			}
		}
		//设备
		if(device.getSchoolDevice()!=null){
			//设备编号
			if(device.getSchoolDevice().getDeviceNumber()!=null&&!device.getSchoolDevice().getDeviceNumber().equals("")){
				sql+="and d.schoolDevice.deviceNumber like '%"+device.getSchoolDevice().getDeviceNumber()+"%'";
			}
			//设备名称
			if(device.getSchoolDevice().getDeviceName()!=null&&!device.getSchoolDevice().getDeviceName().equals("")){
				sql+="and d.schoolDevice.deviceName like '%"+device.getSchoolDevice().getDeviceName()+"%'";
			}
			if(device.getUser()!=null){
				//设备管理员
				if(device.getUser().getUsername()!=null&&!device.getUser().getUsername().equals("")){
					sql+="and d.user.username  like '%"+device.getUser().getUsername()+"%'";
				}
			}
		}
		if(device != null && device.getCDictionaryByAllowAppointment() != null 
				&& device.getCDictionaryByAllowAppointment().getCNumber() != null
				&& !device.getCDictionaryByAllowAppointment().getCNumber().equals("")){
			if(device.getCDictionaryByAllowAppointment().getCNumber().equals("1"))
			{
				sql += " and d.CDictionaryByAllowAppointment.CNumber ='"+device.getCDictionaryByAllowAppointment().getCNumber()+"'";
			}
			else{
				sql += " and (d.CDictionaryByAllowAppointment is null or (d.CDictionaryByAllowAppointment is not null and d.CDictionaryByAllowAppointment.CNumber = '2'))";
			}
		}
		/*if(cid!=null){
			sql+=" and d.labRoom.labCenter.id="+cid;
		}*/
		
		if(isReservation != null && isReservation == 1){
			sql += " and d.labRoom.labRoomActive = 1";
		}
		sql+=" order by d.schoolDevice.deviceName";
		return labRoomDeviceDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	
	/****************************************************************************
	 * 功能：删除实验室设备
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public void deleteLabRoomDevice(LabRoomDevice d) {
		
		labRoomDeviceDAO.remove(d);
	}
	/****************************************************************************
	 * 功能：根据实验分室设备主键查询对象
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(Integer id) {
		
		return labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(id);
	}
	
	/****************************************************************************
	 * 功能：根据实验室id查询管理员
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomAdmin> findLabRoomAdminByRoomId(Integer id,Integer typeId) {
		
		String sql="select a from LabRoomAdmin a where a.labRoom.id="+id+" and a.typeId="+typeId;
		
		return labRoomAdminDAO.executeQuery(sql,0,-1);
	}
	
	/****************************************************************************
	 * 功能：根据实验室查询实验室的排课
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<TimetableLabRelated> findTimetableLabRelatedByRoomId(Integer id) {
		
		String sql="select t from TimetableLabRelated t where t.labRoom.id="+id;
		return timetableLabRelatedDAO.executeQuery(sql);
	}
	
	/****************************************************************************
	 * 功能：查找实验室管理员
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<User> findAdminByLrid(Integer idKey){
		List<User> us = new java.util.ArrayList<User>();
		String sql = "select l from LabRoomAdmin l where l.labRoom.id="+idKey;
		List<LabRoomAdmin> lras =  labRoomAdminDAO.executeQuery(sql);
		if(lras.size()>0){
			for(LabRoomAdmin lra : lras){
				us.add(lra.getUser());
			}
		}
		return us;
	}
	
	
	/****************************************************************************
	 * 功能：判断当前登录用户是否为实验室管理员
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public Boolean getLabRoomAdmin(Integer roomId, String username) {
		String sql="select a from LabRoomAdmin a where labRoom.id="+roomId;
		List<LabRoomAdmin> list=labRoomAdminDAO.executeQuery(sql, 0,-1);
		Boolean flag=false;
		for (LabRoomAdmin a : list) {
			if(a.getUser().getUsername().equals(username)){
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	
	
	/****************************************************************************
	 * 功能：上传设备图片
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:50:23
	 ****************************************************************************/
	@Override
	public void deviceImageUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id,int type) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
		 String sep = System.getProperty("file.separator"); 
		 Map files = multipartRequest.getFileMap(); 
		 Iterator fileNames = multipartRequest.getFileNames();
		 boolean flag =false; 
		 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"device"+sep+id;
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
			  saveDeviceDocument(fileTrueName,id,type);
		  } 
		}
	}
	/****************************************************************************
	 * 功能：保存设备的文档
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:50:33
	 ****************************************************************************/
	public void saveDeviceDocument(String fileTrueName, Integer id,int type) {
		// TODO Auto-generated method stub
		//id对应的设备
		LabRoomDevice device=labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(id);
		CommonDocument doc = new CommonDocument( );
		doc.setDocumentName(fileTrueName);
		String imageUrl="upload/device/"+id+"/"+fileTrueName;
		doc.setDocumentUrl(imageUrl);
		doc.setLabRoomDevice(device);
		doc.setType(type);//图片和文档
		
		documentDAO.store(doc);
	}
	
	/****************************************************************************
	 * 功能：上传设备视频
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:50:33
	 ****************************************************************************/
	@Override
	public void deviceVideoUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
		 String sep = System.getProperty("file.separator"); 
		 Map files = multipartRequest.getFileMap(); 
		 Iterator fileNames = multipartRequest.getFileNames();
		 boolean flag =false; 
		 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"device"+sep+id;
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
			  saveDeviceVideo(fileTrueName,id);
		  } 
		}
	}
	/****************************************************************************
	 * 功能：保存设备的视频
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:50:33
	 ****************************************************************************/
	public void saveDeviceVideo(String fileTrueName, Integer id) {
		// TODO Auto-generated method stub
		//id对应的设备
		LabRoomDevice device=labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(id);
		CommonVideo video = new CommonVideo( );
		video.setVideoName(fileTrueName);
		String imageUrl="upload/device/"+id+"/"+fileTrueName;
		video.setVideoUrl(imageUrl);
		video.setLabRoomDevice(device);
		
		videoDAO.store(video);
	}
	
	/****************************************************************************
	 * 功能：根据设备id查询设备的预约记录
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findReservationListByDeviceId(
			Integer equinemtid) {
		// TODO Auto-generated method stub
		String sql="select r from LabRoomDeviceReservation r where  r.labRoomDevice.id="+equinemtid;
		return labRoomDeviceReservationDAO.executeQuery(sql);
	}
	

	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约并分页
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservation(
			LabRoomDeviceReservation reservation, int cId,Integer centerId, Integer page,
			int pageSize) {
		//获取当前登录人
		User user=shareService.getUser();
		//判断当前登陆人是否为实验室中心主任或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		boolean isEXCENTERDIRECTOR;
		if(judge.indexOf(",11,")>-1||judge.indexOf(",4,")>-1){
			isEXCENTERDIRECTOR = true;
		}else isEXCENTERDIRECTOR = false;
				
		//String sql="select l from LabRoomDeviceReservation l left join l.labRoomDevice.labRoom.labRoomAdmins la where 1=1";
		String sql="select distinct l from LabRoomDeviceReservation l left join l.labRoomDevice.labRoom.labRoomAdmins la where 1=1";
		if(cId!=0){
			//sql+=" and l.CAuditResult.id="+cId;
			//sql+=" and l.CDictionaryByCAuditResult.id="+cId;
			sql+=" and l.CDictionaryByCAuditResult.CCategory='c_audit_result' and l.CDictionaryByCAuditResult.CNumber ='"+cId+"'";
			
		}
		if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getSchoolDevice()!=null){
			if(reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()!=null&&!reservation.getLabRoomDevice().getSchoolDevice().getDeviceName().equals("")){
				sql+=" and l.labRoomDevice.schoolDevice.deviceName like '%"+reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()+"%'";
			}
			if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getLabRoom()!=null&&reservation.getLabRoomDevice().getLabRoom().getId()!=null&&!reservation.getLabRoomDevice().getLabRoom().getId().equals("")){
				sql+=" and l.labRoomDevice.labRoom.id="+reservation.getLabRoomDevice().getLabRoom().getId();
			}
		}
		if(reservation.getSchoolTerm()!=null){
			if(reservation.getSchoolTerm().getId()!=null){
				sql+=" and l.schoolTerm.id="+reservation.getSchoolTerm().getId();
			}
		}
		if(reservation.getUserByReserveUser() != null && reservation.getUserByReserveUser().getUsername() != null && !reservation.getUserByReserveUser().getUsername().equals("")){
			sql += " and l.userByReserveUser.username ='" + reservation.getUserByReserveUser().getUsername()+"'";
		}
		/*if(centerId!=null){
			sql+=" and l.labRoomDevice.labRoom.labCenter.id="+centerId;
		}*/
		//身份限制
		if (isEXCENTERDIRECTOR) {//是实验室中心主任或者超级管理员
			//do nothing
		}else{//不是上述两种身份
			sql+=" and (l.userByReserveUser.username='"+shareService.getUser().getUsername()+"'";//申请者
			sql+=" or l.userByTeacher.username='"+shareService.getUser().getUsername()+"'";//导师
			sql+=" or l.labRoomDevice.user.username='"+shareService.getUser().getUsername()+"'";//设备管理员
			sql+=" or (la.user.username='"+shareService.getUser().getUsername()+"' and la.typeId=1))";//实验室管理员
		}
			
		sql+=" group by l.innerSame";
		sql+=" order by l.time desc";
		return labRoomDeviceReservationDAO.executeQuery(sql,(page-1)*pageSize,pageSize);	
	}
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约并分页
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservation(
			LabRoomDeviceReservation reservation, Integer cId,Integer centerId, Integer page,
			int pageSize,int tag) {
		// TODO Auto-generated method stub
		//String sql="select l from LabRoomDeviceReservation l where l.CAuditResult.id="+cId;
		String sql="select l from LabRoomDeviceReservation l where 1=1";
		if (cId!=null) {
			//sql+=" and l.CDictionaryByCAuditResult.id="+cId;
			sql+=" and l.CDictionaryByCAuditResult.CCategory='c_audit_result' and l.CDictionaryByCAuditResult.CNumber ='"+cId+"'";
		}

		if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getSchoolDevice()!=null){
			if(reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()!=null&&!reservation.getLabRoomDevice().getSchoolDevice().getDeviceName().equals("")){
				sql+=" and l.labRoomDevice.schoolDevice.deviceName like '%"+reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()+"%'";
			}
			if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getLabRoom()!=null&&reservation.getLabRoomDevice().getLabRoom().getId()!=null&&!reservation.getLabRoomDevice().getLabRoom().getId().equals("")){
				sql+=" and l.labRoomDevice.labRoom.id="+reservation.getLabRoomDevice().getLabRoom().getId();
			}
		}
		if(reservation.getSchoolTerm()!=null){
			if(reservation.getSchoolTerm().getId()!=null){
				sql+=" and l.schoolTerm.id="+reservation.getSchoolTerm().getId();
			}
		}
		/*if(centerId!=null){
			sql+=" and l.labRoomDevice.labRoom.labCenter.id="+centerId;
		}*/
		sql+=" and (l.tag=1 or l.tag=2 or l.tag=3)";
		//sql+=" group by l.innerSame";
		sql+=" order by l.time desc";
		return labRoomDeviceReservationDAO.executeQuery(sql,(page-1)*pageSize,pageSize);	
	}
	
	/****************************************************************************
	 * 功能：找出所有设备预约的实验室
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@Override
	public Map<Integer, String> findAllLabrooms(Integer centerId){
		
		Map<Integer, String> map=new HashMap<Integer, String>();
		String s="select u from LabRoomDeviceReservation u where 1=1 ";
		List<LabRoomDeviceReservation>   list=labRoomDeviceReservationDAO.executeQuery(s);
		if(list.size()>0){
		for(LabRoomDeviceReservation re:list){
			map.put(re.getLabRoomDevice().getLabRoom().getId(), re.getLabRoomDevice().getLabRoom().getLabRoomName());
		}
		}
		return map;
	}
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservation(
			LabRoomDeviceReservation reservation, Integer cId,Integer centerId) {
		// TODO Auto-generated method stub
		//String sql="select l from LabRoomDeviceReservation l where l.CAuditResult.id="+cId;
		String sql="select l from LabRoomDeviceReservation l where 1=1";
		if (cId!=null) {
			//sql +=" and l.CDictionaryByCAuditResult.id="+cId;
			sql+=" and l.CDictionaryByCAuditResult.CCategory='c_audit_result' and l.CDictionaryByCAuditResult.CNumber ='"+cId+"'";
		}
		if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getSchoolDevice()!=null){
			if(reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()!=null&&!reservation.getLabRoomDevice().getSchoolDevice().getDeviceName().equals("")){
				sql+=" and l.labRoomDevice.schoolDevice.deviceName like '%"+reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()+"%'";
			}
			if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getLabRoom()!=null&&reservation.getLabRoomDevice().getLabRoom().getId()!=null&&!reservation.getLabRoomDevice().getLabRoom().getId().equals("")){
				sql+=" and l.labRoomDevice.labRoom.id="+reservation.getLabRoomDevice().getLabRoom().getId();
			}
		}
		if(reservation.getSchoolTerm()!=null){
			if(reservation.getSchoolTerm().getId()!=null){
				sql+=" and l.schoolTerm.id="+reservation.getSchoolTerm().getId();
			}
		}
		/*if(centerId!=null){
			sql+=" and l.labRoomDevice.labRoom.labCenter.id="+centerId;
		}*/
		sql+="order by l.time desc";
		return labRoomDeviceReservationDAO.executeQuery(sql,0,-1);	
	}
	
	/****************************************************************************
	 * 功能：找出所有设备预约的申请人
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@Override
	public Map<String, String> findAllreserveUsers(Integer centerId){
		
		Map<String, String> map=new HashMap<String, String>();
		String s="select u from LabRoomDeviceReservation u where u.userByReserveUser.username is not null ";
		s+="group by u.userByReserveUser.username";
		List<LabRoomDeviceReservation>   list=labRoomDeviceReservationDAO.executeQuery(s);
		if(list.size()>0){
		for(LabRoomDeviceReservation re:list){
			map.put(re.getUserByReserveUser().getUsername(), re.getUserByReserveUser().getCname());
		}
		}
		return map;
	}
	
	/****************************************************************************
	 * 功能：找出所有设备预约的指导老师
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@Override
	public Map<String, String> findAllTeachers(Integer centerId){
		
		Map<String, String> map=new HashMap<String, String>();
		String s="select u from LabRoomDeviceReservation u where u.userByTeacher.username is not null";
		s+="group by u.userByTeacher.username";
		List<LabRoomDeviceReservation>   list=labRoomDeviceReservationDAO.executeQuery(s);
		if(list.size()>0){
		for(LabRoomDeviceReservation re:list){
			map.put(re.getUserByTeacher().getUsername(), re.getUserByTeacher().getCname());
		}
		}
		return map;
	}
	
	
	/****************************************************************************
	 * 功能：找出所有设备预约的设备管理员
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@Override
	public Map<String, String> findAllManageUsers(Integer centerId){
		
		Map<String, String> map=new HashMap<String, String>();
		String s="select u from LabRoomDeviceReservation u where u.labRoomDevice.user.username is not null";
		s+="group by u.labRoomDevice.user.username";
		List<LabRoomDeviceReservation>   list=labRoomDeviceReservationDAO.executeQuery(s);
		if(list.size()>0){
		for(LabRoomDeviceReservation re:list){
			map.put(re.getLabRoomDevice().getUser().getUsername(), re.getLabRoomDevice().getUser().getCname());
		}
		}
		return map;
	}
	
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备
	 * 作者：贺子龙
	 * 时间：2015-09-22 10:24:59
	 ****************************************************************************/
	@Override
	public List<LabRoomDevice> findAllLabRoomDeviceNew(LabRoomDevice device,Integer cid) {
		String sql="select d from LabRoomDevice d where 1=1";
		if(device.getLabRoom()!=null){
			if(device.getLabRoom().getId()!=null&&!device.getLabRoom().getId().equals("")){
				sql+=" and d.labRoom.id="+device.getLabRoom().getId();
			}
		}
		//设备
		if(device.getSchoolDevice()!=null){
			//设备编号
			if(device.getSchoolDevice().getDeviceNumber()!=null&&!device.getSchoolDevice().getDeviceNumber().equals("")){
				sql+="and d.schoolDevice.deviceNumber like '%"+device.getSchoolDevice().getDeviceNumber()+"%'";
			}
			//设备名称
			if(device.getSchoolDevice().getDeviceName()!=null&&!device.getSchoolDevice().getDeviceName().equals("")){
				sql+="and d.schoolDevice.deviceName like '%"+device.getSchoolDevice().getDeviceName()+"%'";
			}
			if(device.getSchoolDevice().getUserByKeepUser()!=null){
				//设备管理员
				if(device.getSchoolDevice().getUserByKeepUser().getUsername()!=null&&!device.getSchoolDevice().getUserByKeepUser().getUsername().equals("")){
					sql+="and d.schoolDevice.userByKeepUser.username  like '%"+device.getSchoolDevice().getUserByKeepUser().getUsername()+"%'";
				}
			}
		}
		/*if(cid!=null){
			sql+=" and d.labRoom.labCenter.id="+cid;
		}*/
		return labRoomDeviceDAO.executeQuery(sql,0,-1);
	}
	
	
	@Override
	public List<LabRoomDevice> findAllLabRoomDeviceNumbers(LabRoomDevice device,Integer cid) {
		String sql="select d from LabRoomDevice d where 1=1";
		/*if(cid!=null){
			sql+=" and d.labRoom.labCenter.id="+cid;
		}*/
		if(device.getLabRoom()!=null){
			if(device.getLabRoom().getId()!=null&&!device.getLabRoom().getId().equals("")){
				sql+=" and d.labRoom.id="+device.getLabRoom().getId();
			}
		}
		sql+=" group by d.schoolDevice.deviceNumber ";
		return labRoomDeviceDAO.executeQuery(sql,0,-1);
	}
	
	/****************************************************************************
	 * 功能：根据设备id查询培训
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingByDeviceId(LabRoomDeviceTraining train,
			Integer deviceId) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceTraining l where l.labRoomDevice.id="+deviceId;
		if(train!=null){
			if(train.getSchoolTerm()!=null){
				sql+=" and l.schoolTerm.id="+train.getSchoolTerm().getId();
			}
		}
		sql+=" order by l.time desc";
		return labRoomDeviceTrainingDAO.executeQuery(sql);
	}
	
	/****************************************************************************
	 * 功能：根据培训id查询培训通过的人
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceTrainingPeople> findPassLabRoomDeviceTrainingPeopleByTrainId(
			Integer id) {
		// TODO Auto-generated method stub
		//String sql="select p from LabRoomDeviceTrainingPeople p where p.CDictionary.id=619 and p.labRoomDeviceTraining.id="+id;
		String sql="select p from LabRoomDeviceTrainingPeople p where 1=1 and p.CDictionary.CCategory='c_training_result' and p.CDictionary.CNumber ='1' and p.labRoomDeviceTraining.id="+id;
		return labRoomDeviceTrainingPeopleDAO.executeQuery(sql);
	}
	
	/****************************************************************************
	 * 功能：根据培训查询培训名单
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceTrainingPeople> findTrainingPeoplesByTrainingId(
			int id) {
		// TODO Auto-generated method stub
		String sql="select p from LabRoomDeviceTrainingPeople p where p.labRoomDeviceTraining.id="+id;
		return labRoomDeviceTrainingPeopleDAO.executeQuery(sql);
	}
	
	/****************************************************************************
	 * 功能：保存设备培训
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public LabRoomDeviceTraining saveLabRoomDeviceTraining(
			LabRoomDeviceTraining train) {
		// TODO Auto-generated method stub
		return labRoomDeviceTrainingDAO.store(train);
	}
	
	/****************************************************************************
	 * 功能：根据培训查询培训名单--已通过的学生
	 * 作者：贺子龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceTrainingPeople> findTrainingPassPeoplesByTrainingId(
			int id) {
		// TODO Auto-generated method stub
		String sql="select p from LabRoomDeviceTrainingPeople p where p.labRoomDeviceTraining.id="+id;
		//sql+=" and p.CDictionary.id=619";
		sql+=" and p.CDictionary.CCategory='c_training_result' and p.CDictionary.CNumber ='1'";
		return labRoomDeviceTrainingPeopleDAO.executeQuery(sql);
	}
	
	/****************************************************************************
	 * 功能：当前用户取消已经预约的培训
	 * 作者：贺子龙
	 ****************************************************************************/
	public void cancleTraining(int trainingId){
		LabRoomDeviceTraining train = labRoomDeviceTrainingDAO.findLabRoomDeviceTrainingByPrimaryKey(trainingId);
		User user = shareService.getUser();
		// 找到对应id
		int labRoomDeviceTrainingPeopleId=0;
		Set<LabRoomDeviceTrainingPeople> students=train.getLabRoomDeviceTrainingPeoples();
		for (LabRoomDeviceTrainingPeople labRoomDeviceTrainingPeople : students) {
			if (labRoomDeviceTrainingPeople.getUser().getUsername()==user.getUsername()) {
				students.remove(labRoomDeviceTrainingPeople);
				labRoomDeviceTrainingPeopleId=labRoomDeviceTrainingPeople.getId();
			}
		}
		train.setLabRoomDeviceTrainingPeoples(students);
		labRoomDeviceTrainingDAO.store(train);
		LabRoomDeviceTrainingPeople student=labRoomDeviceTrainingPeopleDAO.findLabRoomDeviceTrainingPeopleByPrimaryKey(labRoomDeviceTrainingPeopleId);
		if (student!=null) {
			student.setLabRoomDeviceTraining(null);
			labRoomDeviceTrainingPeopleDAO.remove(student);
			labRoomDeviceTrainingPeopleDAO.flush();
		}
	}
	
	/****************************************************************************
	 * 功能：判断用户是否通过安全准入
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public String securityAccess(String username, Integer id) {
		// TODO Auto-generated method stub
		String str="success";
		//id对应的设备
		LabRoomDevice device=labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(id);

		
		//是否需要安全准入
	    CDictionary a=device.getCDictionaryByAllowSecurityAccess();
	    
	    /*if(a!=null&&a.getId()==621){//需要安全准入
	    	//培训形式
	    	CDictionary c=device.getCDictionaryByTrainType();
	    	if (c!=null&&c.getId()==627){//集中培训
	    		
	    		System.out.println("进入现场培训验证流程");
	    		//String sql="select p from LabRoomDeviceTrainingPeople p where p.labRoomDeviceTraining.labRoomDevice.id="
	    				//+id+" and p.user.username like '%"+username+"%'"+" and p.CDictionary.id=619";
	    		String sql="select p from LabRoomDeviceTrainingPeople p where p.labRoomDeviceTraining.labRoomDevice.id="
	    				+id+" and p.user.username like '%"+username+"%' ";
	    		sql+=" and p.CDictionary.CCategory='c_training_result' and p.CDictionary.CNumber ='1'";
	    		List<LabRoomDeviceTrainingPeople> peoples=labRoomDeviceTrainingPeopleDAO.executeQuery(sql,0,-1);
	    		if(peoples.size()==0){
	    			str="error";
	    		}
	    	}
	    	if (c!=null&&c.getId()==628) {//单独培训
	    		boolean pass=false;
	    		Set<LabRoomDevicePermitUsers> students =device.getLabRoomDevicePermitUserses();
	    		for (LabRoomDevicePermitUsers ss : students) {
	    			System.out.println(ss);
	    			if (ss.getUser().getUsername()==username) {
	    				pass=true;break;
	    			}
	    		}
	    		if (!pass) {
	    			str="errorType2";
	    		}
	    	}
	    }*/
	 // 获取当前用户
	 User currUser = shareService.getUser();
	 		// 当前用户是否为实验室中心主任
	 String judge = ",";
	 for (Authority authority : currUser.getAuthorities()) {
	 	judge = judge + "," + authority.getId() + ",";
	 }
	  if (device.getUser()==null
	    ||device.getUser().getUsername()==null
	    ||device.getUser().getUsername().equals("")) {
		str = "noManager";
	}else {
			if(a!=null&&a.getId()==621){//需要安全准入
				// 中心主任或设备管理员不需要进行培训
				if (judge.indexOf(",4,") > -1 || 
						(device.getUser()!=null&&device.getUser().getUsername().equals(username))) {
					// do nothing
				}else {
					if (findPermitUserByUsernameAndDeivce(username, id)==null) {
						str = "error";
					}
				}
			}
		}
	    
	    return str;
	}
	
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备，添加所在实验室条件
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public int findAllLabRoomDevice(LabRoomDevice device,Integer cid, int roomId) {
		String sql="select count(*) from LabRoomDevice d where 1=1";
		//实验室
		if(device.getLabRoom()!=null){
			if(device.getLabRoom().getId()!=null&&!device.getLabRoom().getId().equals("")){
				sql+="and d.labRoom.id="+device.getLabRoom().getId();
			}
		}else {
			if(roomId != -1)
			{
				sql+=" and d.labRoom.id="+roomId;
			}
		}
		//设备
		if(device.getSchoolDevice()!=null){
			//设备编号
			if(device.getSchoolDevice().getDeviceNumber()!=null&&!device.getSchoolDevice().getDeviceNumber().equals("")){
				sql+=" and d.schoolDevice.deviceNumber like '%"+device.getSchoolDevice().getDeviceNumber()+"%'";
			}
			//设备名称
			if(device.getSchoolDevice().getDeviceName()!=null&&!device.getSchoolDevice().getDeviceName().equals("")){
				sql+=" and d.schoolDevice.deviceName like '%"+device.getSchoolDevice().getDeviceName()+"%'";
			}
			if(device.getUser()!=null){
				//设备管理员
				if(device.getUser().getUsername()!=null&&!device.getUser().getUsername().equals("")){
					sql+="and d.user.username  like '%"+device.getUser().getUsername()+"%'";
				}
			}
		}
		if(device != null && device.getCDictionaryByAllowAppointment() != null 
				&& device.getCDictionaryByAllowAppointment().getCNumber() != null
				&& !device.getCDictionaryByAllowAppointment().getCNumber().equals("")){
			if(device.getCDictionaryByAllowAppointment().getCNumber().equals("1"))
			{
				sql += " and d.CDictionaryByAllowAppointment.CNumber ='"+device.getCDictionaryByAllowAppointment().getCNumber()+"'";
			}
			else{
				sql += " and (d.CDictionaryByAllowAppointment.CNumber !='1' or d.CDictionaryByAllowAppointment is null)";
			}
		}
		/*if(cid!=null){
			sql+=" and d.labRoom.labCenter.id="+cid;
		}*/
		//sql+=" and d.labRoom.id="+roomId;
		
		return ((Long) labRoomDeviceDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	@Override
	public int findStudentByCnameAndUsername(User user, String academyNumber){
		String sql="select count(*) from User u where 1=1";
		/*if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and u.schoolAcademy.academyNumber like '"+academyNumber+"%'";
		}*/
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equals("")){
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql+=" and u.username like '%"+user.getUsername()+"%'";
			}
		}
		sql+=" and u.userRole<>1";
		return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	@Override
	public List<User> findStudentByCnameAndUsername(User user, String academyNumber, Integer page, int pageSize){
		String sql="select u from User u where 1=1";
		/*if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and u.schoolAcademy.academyNumber like '"+academyNumber+"%'";
		}*/
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equals("")){
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql+=" and u.username like '%"+user.getUsername()+"%'";
			}
		}
		sql+=" and u.userRole<>1";
		sql+="ORDER BY CASE WHEN u.schoolAcademy.academyNumber like '"+academyNumber+"%' THEN 0 ELSE 1 END";
		sql+=" ,u.username desc";
		return userDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	@Override
	public List<LabRoomDeviceReservation> findLabRoomDeviceReservationInfoByDeviceId(
			Integer labRoomDeviceId, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where l.labRoomDevice.id = '"+labRoomDeviceId+"'";
			
		sql+=" order by l.time desc";
		return labRoomDeviceReservationDAO.executeQuery(sql,(currpage-1)*pageSize,pageSize);	
	}
	@Override
	public int countLabRoomDeviceReservationInfoByDeviceId(
			Integer labRoomDeviceId) {
		// TODO Auto-generated method stub
		String sql="select count(l) from LabRoomDeviceReservation l where l.labRoomDevice.id = '"+labRoomDeviceId+"'";
		int result =((Long)labRoomDeviceReservationDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	@Override
	public List<LabRoomDevicePermitUsers> findPermitUserByDeivceId(
			Integer labRoomDeviceId, int currpage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select u from LabRoomDevicePermitUsers u where 1=1";
		sql+=" and u.labRoomDevice.id ="+labRoomDeviceId;
		List<LabRoomDevicePermitUsers> users = labRoomDevicePermitUsersDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return users;
	}
	@Override
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingListByDeviceId(
			Integer labRoomDeviceId, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceTraining l where l.labRoomDevice.id="+labRoomDeviceId;
		sql+=" order by l.time desc";
		return labRoomDeviceTrainingDAO.executeQuery(sql,(currpage-1)*pageSize,pageSize);
	}
	@Override
	public int countLabRoomDeviceTrainingListByDeviceId(Integer labRoomDeviceId) {
		// TODO Auto-generated method stub
		String sql="select count(l) from LabRoomDeviceTraining l where l.labRoomDevice.id="+labRoomDeviceId;
		int result = ((Long)labRoomDeviceTrainingDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return result;
	}
	
	
	
	
	
	
	
	
	
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备并分页
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDevice> findAllLabRoomDevice(LabRoomDevice device,Integer cid,
			Integer page, int pageSize,int roomId) {
		String sql="select d from LabRoomDevice d where 1=1";
		if(device.getLabRoom()!=null){
			if(device.getLabRoom().getId()!=null&&!device.getLabRoom().getId().equals("")){
				sql+=" and d.labRoom.id="+device.getLabRoom().getId();
			}
		}else{
			if(roomId != -1)
			{
				sql+=" and d.labRoom.id="+roomId;
			}
		}
		//设备
		if(device.getSchoolDevice()!=null){
			//设备编号
			if(device.getSchoolDevice().getDeviceNumber()!=null&&!device.getSchoolDevice().getDeviceNumber().equals("")){
				sql+="and d.schoolDevice.deviceNumber like '%"+device.getSchoolDevice().getDeviceNumber()+"%'";
			}
			//设备名称
			if(device.getSchoolDevice().getDeviceName()!=null&&!device.getSchoolDevice().getDeviceName().equals("")){
				sql+="and d.schoolDevice.deviceName like '%"+device.getSchoolDevice().getDeviceName()+"%'";
			}
			if(device.getUser()!=null){
				//设备管理员
				if(device.getUser().getUsername()!=null&&!device.getUser().getUsername().equals("")){
					sql+="and d.user.username  like '%"+device.getUser().getUsername()+"%'";
				}
			}
		}
		if(device != null && device.getCDictionaryByAllowAppointment() != null 
				&& device.getCDictionaryByAllowAppointment().getCNumber() != null
				&& !device.getCDictionaryByAllowAppointment().getCNumber().equals("")){
			if(device.getCDictionaryByAllowAppointment().getCNumber().equals("1"))
			{
				sql += " and d.CDictionaryByAllowAppointment.CNumber ='"+device.getCDictionaryByAllowAppointment().getCNumber()+"'";
			}
			else{
				sql += " and (d.CDictionaryByAllowAppointment.CNumber !='1' or d.CDictionaryByAllowAppointment is null)";
			}
		}
		/*if(cid!=null){
			sql+=" and d.labRoom.labAnnex.labCenter.id="+cid;
		}*/
		sql+=" order by d.schoolDevice.deviceName";
		//sql+=" and d.labRoom.id="+roomId;
		return labRoomDeviceDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	
	
	/****************************************************************************
	 * 功能：根据设备id查询设备的预约记录
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findLabRoomDeviceReservationByDeviceNumber(
			String deviceNumber) {
		// TODO Auto-generated method stub
		String sql="select l from LabRoomDeviceReservation l where l.schoolDevice.deviceNumber="+deviceNumber;
		return labRoomDeviceReservationDAO.executeQuery(sql);
	}
	
	
	/****************************************************************************
	 * 功能：上传设备图片
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public void deviceDocumentUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id,int type) {
		String sep = System.getProperty("file.separator"); 
		String path = sep+ "upload"+ sep+"device"+sep+id;
		shareService.uploadFiles(request, path,"saveDeviceDocument",id);
	}
	/****************************************************************************
	 * 功能：根据学期查询培训
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingByTermId(
			Integer termId,Integer id) {
		// TODO Auto-generated method stub
		String sql="select t from LabRoomDeviceTraining t where t.schoolTerm.id="+termId+" and t.labRoomDevice.id="+id;
		sql+=" order by t.time desc";
		return labRoomDeviceTrainingDAO.executeQuery(sql);
	}
	/****************************************************************************
	 * 功能：根据实验室设备查询设备预约
	 * 作者：贺子龙
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceReservation> findLabRoomDeviceReservationByDeviceNumberAndCid(LabRoomDeviceReservation reservation, HttpServletRequest request,
			String deviceNumber, int page, int pageSize, int status, Integer cid) {
		String sql="select l from LabRoomDeviceReservation l where 1=1";
		if (deviceNumber!=null && !deviceNumber.equals("")) {
			sql+=" and l.labRoomDevice.schoolDevice.deviceNumber like '"+deviceNumber +"'";
		}
		/*if (cid!=null && cid!=0) {
			sql+=" and l.labRoomDevice.labRoom.labAnnex.labCenter.id ="+cid;
		}*/
		// 学期
        if(reservation.getSchoolTerm()!=null){
            if(reservation.getSchoolTerm().getId()!=null){
                sql+=" and l.schoolTerm.id="+reservation.getSchoolTerm().getId();
            }
        }
        //申请人
        if (reservation.getUserByReserveUser()!=null&&reservation.getUserByReserveUser().getUsername()!=null&&
                !reservation.getUserByReserveUser().getUsername().equals("")) {
            sql+=" and l.userByReserveUser.username="+reservation.getUserByReserveUser().getUsername();
        }
        
        //指导老师
        if (reservation.getUserByTeacher()!=null&&reservation.getUserByTeacher().getUsername()!=null&&
                !reservation.getUserByTeacher().getUsername().equals("")) {
            sql+=" and l.userByTeacher.username="+reservation.getUserByTeacher().getUsername();
        }
        
        //设备管理员
        if (reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getUser()!=null
                &&reservation.getLabRoomDevice().getUser().getUsername()!=null&&
                !reservation.getLabRoomDevice().getUser().getUsername().equals("")) {
            sql+=" and l.labRoomDevice.user.username="+reservation.getLabRoomDevice().getUser().getUsername();
        }
        
        // 设备名称  实验室
        if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getSchoolDevice()!=null){
            if(reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()!=null&&!reservation.getLabRoomDevice().getSchoolDevice().getDeviceName().equals("")){
                sql+=" and l.labRoomDevice.schoolDevice.deviceName like '%"+reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()+"%'";
            }
            if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getLabRoom()!=null&&reservation.getLabRoomDevice().getLabRoom().getId()!=null&&!reservation.getLabRoomDevice().getLabRoom().getId().equals("")){
                sql+=" and l.labRoomDevice.labRoom.id="+reservation.getLabRoomDevice().getLabRoom().getId();
            }
        }
        
        // 使用机时数
        if (reservation.getReserveHours()!=null && !reservation.getReserveHours().equals(0)) {
        	sql+=" and l.reserveHours = "+reservation.getReserveHours();
		}
        
		// 起止时间
        if (request!=null) {
        	String begintime= request.getParameter("begintime");
        	String endtime=	request.getParameter("endtime");
        	if(begintime!=null && begintime.length()>0 && endtime!=null&& endtime.length()>0){
        		sql += " and l.begintime between '"+begintime +"' and '"+endtime+"' "; 	
        	}
		}
        
        // 审核状态
		if (status!=0) {
			//sql+=" and l.CAuditResult.id = "+status;
			sql+=" and l.CDictionaryByCAuditResult.CCategory = 'c_audit_result' and l.CDictionaryByCAuditResult.CNumber = '"+status+"'";
		}
		if(reservation.getResearchProject() != null && reservation.getResearchProject().getId() != null && !reservation.getResearchProject().getId().equals("")){
			sql +=" and l.researchProject.id="+reservation.getResearchProject().getId();
		}
		
		// 非排课占用
		sql+=" and (l.timetableLabDevice is null or l.appointmentId is null)";
		sql+=" group by l.innerSame";
		sql+=" order by l.time desc";
		List<LabRoomDeviceReservation> reservationList = labRoomDeviceReservationDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
		// 设置使用机时数
		if (reservationList!=null && reservationList.size()>0) {
			for (LabRoomDeviceReservation labRoomDeviceReservation : reservationList) {
				BigDecimal reserveHours = this.getReserveHoursOfReservation(labRoomDeviceReservation);
				labRoomDeviceReservation.setReserveHours(reserveHours);
				labRoomDeviceReservationDAO.store(labRoomDeviceReservation);
			}
		}
		return reservationList;
	}
	
	/****************************************************************************
	 * 功能：根据实验室设备查询设备预约--数量
	 * 作者：贺子龙
	 ****************************************************************************/
	public int countLabRoomDeviceReservationByDeviceNumberAndCid(LabRoomDeviceReservation reservation, HttpServletRequest request,
			String deviceNumber, int status, Integer cid){
		String sql="select count(l) from LabRoomDeviceReservation l where 1=1";
		if (deviceNumber!=null && !deviceNumber.equals("")) {
			sql+=" and l.labRoomDevice.schoolDevice.deviceNumber like '"+deviceNumber +"'";
		}
		if (cid!=null && cid!=0) {
			sql+=" and l.labRoomDevice.labRoom.labAnnex.labCenter.id ="+cid;
		}
		// 学期
        if(reservation.getSchoolTerm()!=null){
            if(reservation.getSchoolTerm().getId()!=null){
                sql+=" and l.schoolTerm.id="+reservation.getSchoolTerm().getId();
            }
        }
        //申请人
        if (reservation.getUserByReserveUser()!=null&&reservation.getUserByReserveUser().getUsername()!=null&&
                !reservation.getUserByReserveUser().getUsername().equals("")) {
            sql+=" and l.userByReserveUser.username="+reservation.getUserByReserveUser().getUsername();
        }
        
        //指导老师
        if (reservation.getUserByReserveUser()!=null&&reservation.getUserByReserveUser().getUsername()!=null&&
                !reservation.getUserByTeacher().getUsername().equals("")) {
            sql+=" and l.userByTeacher.username="+reservation.getUserByTeacher().getUsername();
        }
        
        //设备管理员
        if (reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getUser()!=null
                &&reservation.getLabRoomDevice().getUser().getUsername()!=null&&
                !reservation.getLabRoomDevice().getUser().getUsername().equals("")) {
            sql+=" and l.labRoomDevice.user.username="+reservation.getLabRoomDevice().getUser().getUsername();
        }
        
        // 设备名称  实验室
        if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getSchoolDevice()!=null){
            if(reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()!=null&&!reservation.getLabRoomDevice().getSchoolDevice().getDeviceName().equals("")){
                sql+=" and l.labRoomDevice.schoolDevice.deviceName like '%"+reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()+"%'";
            }
            if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getLabRoom()!=null&&reservation.getLabRoomDevice().getLabRoom().getId()!=null&&!reservation.getLabRoomDevice().getLabRoom().getId().equals("")){
                sql+=" and l.labRoomDevice.labRoom.id="+reservation.getLabRoomDevice().getLabRoom().getId();
            }
        }
        
        // 使用机时数
        if (reservation.getReserveHours()!=null && !reservation.getReserveHours().equals(0)) {
        	sql+=" and l.reserveHours = "+reservation.getReserveHours();
		}
        
		// 起止时间
        if (request!=null) {
        	String begintime= request.getParameter("begintime");
        	String endtime=	request.getParameter("endtime");
        	if(begintime!=null && begintime.length()>0 && endtime!=null&& endtime.length()>0){
        		sql += " and l.begintime between '"+begintime +"' and '"+endtime+"' "; 	
        	}
		}
        
        // 审核状态
		if (status!=0) {
			//sql+=" and l.CAuditResult.id = "+status;
			sql+=" and l.CDictionaryByCAuditResult.CCategory = 'c_audit_result' and l.CDictionaryByCAuditResult.CNumber = '"+status+"'";
		}
		
		// 非排课占用
		sql+=" and (l.timetableLabDevice is null or l.appointmentId is null)";
		
		int count = ((Long)labRoomDeviceReservationDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return count;
	}
	
	/****************************************************************************
	 * 功能：获取一个设备预约的使用机时数
	 * 作者：贺子龙
	 ****************************************************************************/
	public BigDecimal getReserveHoursOfReservation(LabRoomDeviceReservation labRoomDeviceReservation){
		// 通过设备预约找到设备对应的实验中心id
		int centerId = labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabAnnex().getLabCenter().getId();
		int reserveHours = 0;
		int reserveMinutes = 0;
		int startHour = labRoomDeviceReservation.getBegintime().get(Calendar.HOUR_OF_DAY);
		int endHour = labRoomDeviceReservation.getEndtime().get(Calendar.HOUR_OF_DAY);
		int startDay = labRoomDeviceReservation.getBegintime().get(Calendar.DAY_OF_YEAR);
		int endDay = labRoomDeviceReservation.getEndtime().get(Calendar.DAY_OF_YEAR);
		int startMinute = labRoomDeviceReservation.getBegintime().get(Calendar.MINUTE);
		int endMinute = labRoomDeviceReservation.getEndtime().get(Calendar.MINUTE);
		if (startDay == endDay) {// 不跨天
			reserveHours = endHour - startHour;
		}else {// 跨天
			if (centerId == 12) { // 纺织中心
				if (endHour>=17) {
					endHour=17;
				}
				if (startHour<=8) {
					startHour=8;
				}
				reserveHours += 17 - startHour;// 第一天
				reserveHours += (endDay-startDay-1)*(17-8);// 中间天
				reserveHours += endHour-8;// 最后一天
				
			}else { // 非纺织中心
				if (endHour>=20) {
					endHour=20;
				}
				if (startHour<=8) {
					startHour=8;
				}
				reserveHours += 20 - startHour;// 第一天
				reserveHours += (endDay-startDay-1)*(20-8);// 中间天
				reserveHours += startHour-8;// 最后一天
			}
		}
		reserveMinutes = endMinute - startMinute;
		double hourInMinute = reserveMinutes/60.0;
		double hour = hourInMinute + reserveHours;
		BigDecimal bg = new BigDecimal(hour);
		return bg;
	}
	
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约--数量
	 * 作者：贺子龙
	 ****************************************************************************/
	public int countAllLabRoomDeviceReservation(LabRoomDeviceReservation reservation, int cId,Integer centerId){
		//获取当前登录人
		User user=shareService.getUser();
		//判断当前登陆人是否为实验室中心主任或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
				
		String sql="select count(distinct l) from LabRoomDeviceReservation l left join l.labRoomDevice.labRoom.labRoomAdmins la where 1=1";
		if(cId!=0){
			//sql+=" and l.CAuditResult.id="+cId;
			sql+=" and l.CDictionaryByCAuditResult.CCategory='c_audit_result' and l.CDictionaryByCAuditResult.CNumber ='"+cId+"'";
			
		}
		// 设备名称  实验室
		if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getSchoolDevice()!=null){
			if(reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()!=null&&!reservation.getLabRoomDevice().getSchoolDevice().getDeviceName().equals("")){
				sql+=" and l.labRoomDevice.schoolDevice.deviceName like '%"+reservation.getLabRoomDevice().getSchoolDevice().getDeviceName()+"%'";
			}
			if(reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getLabRoom()!=null&&reservation.getLabRoomDevice().getLabRoom().getId()!=null&&!reservation.getLabRoomDevice().getLabRoom().getId().equals("")){
				sql+=" and l.labRoomDevice.labRoom.id="+reservation.getLabRoomDevice().getLabRoom().getId();
			}
		}
		// 学期
		if(reservation.getSchoolTerm()!=null){
			if(reservation.getSchoolTerm().getId()!=null){
				sql+=" and l.schoolTerm.id="+reservation.getSchoolTerm().getId();
			}
		}
		//申请人
		if (reservation.getUserByReserveUser()!=null&&reservation.getUserByReserveUser().getUsername()!=null&&
				!reservation.getUserByReserveUser().getUsername().equals("")) {
			sql+=" and l.userByReserveUser.username="+reservation.getUserByReserveUser().getUsername();
		}
		//指导老师
		if (reservation.getUserByReserveUser()!=null&&reservation.getUserByReserveUser().getUsername()!=null&&
				!reservation.getUserByTeacher().getUsername().equals("")) {
			sql+=" and l.userByTeacher.username="+reservation.getUserByTeacher().getUsername();
		}
		//设备管理员
		if (reservation.getLabRoomDevice()!=null&&reservation.getLabRoomDevice().getUser()!=null
				&&reservation.getLabRoomDevice().getUser().getUsername()!=null&&
				!reservation.getLabRoomDevice().getUser().getUsername().equals("")) {
			sql+=" and l.labRoomDevice.user.username="+reservation.getLabRoomDevice().getUser().getUsername();
		}
		//中心限制
		if(centerId!=null){
			sql+=" and l.labRoomDevice.labRoom.labAnnex.labCenter.id="+centerId;
		}
		LabCenter center = labCenterDAO.findLabCenterByPrimaryKey(centerId);
		String academyNumber = center.getSchoolAcademy().getAcademyNumber();
		boolean isEXCENTERDIRECTOR;
		if(judge.indexOf(",11,")>-1||judge.indexOf(",4,")>-1){
			isEXCENTERDIRECTOR = true;
		}else isEXCENTERDIRECTOR = false;
		//身份限制
		if (isEXCENTERDIRECTOR) {//是实验室中心主任或者超级管理员
			//do nothing
		}else{//不是上述两种身份
			sql+="and (";
			sql+=" l.userByReserveUser.username="+shareService.getUser().getUsername();//申请者
			sql+=" or l.labRoomDevice.user.username="+shareService.getUser().getUsername();//设备管理员
			if (!academyNumber.equals("0207")) {// 化工学院
				sql+=" or l.userByTeacher.username="+shareService.getUser().getUsername();//导师
				sql+=" or (la.user.username="+shareService.getUser().getUsername()+" and la.typeId=1)";//实验室管理员
			}
			sql+=" )";
		}
		// 不显示排课生成的设备预约记录
		sql+=" and (l.timetableLabDevice is null or l.appointmentId is null)";
		sql+=" group by l.innerSame";
		int count = ((Long)labRoomDeviceReservationDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		return count;
	}
	
	/****************************************************************************
	 * 功能：保存新建的出借申请单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void saveDeviceLendApply(LabRoomDeviceLending lrdl){
		if(lrdl.getBackTime() == null){
			lrdl.setBackTime(Calendar.getInstance());
		}
		labRoomDeviceLendingDAO.store(lrdl);
		labRoomDeviceLendingDAO.flush();
	}
	/****************************************************************************
	 * 功能：查找所有设备借用单的记录总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getLabRoomLendsTotals(
			LabRoomDeviceLending lrdl) {
//		String sql="select count(l) from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='1'";
		String sql="select count(l) from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='1'";
		return ((Long)labRoomDeviceLendingDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有设备领用单的记录总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getLabRoomKeepsTotals(
			LabRoomDeviceLending lrdl) {
//		String sql="select count(l) from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='2'";
		String sql="select count(l) from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='2'";
		return ((Long)labRoomDeviceLendingDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有设备借用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	@Override
	public List<LabRoomDeviceLending> findAllLabRoomLends(
			LabRoomDeviceLending lrdl, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='1'";
		String sql="select l from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='1'";
		return labRoomDeviceLendingDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有设备领用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLending> findAllLabRoomKeeps(
			LabRoomDeviceLending lrdl, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='2'";
		String sql="select l from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='2'";
		return labRoomDeviceLendingDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：根据id查找设备借用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public LabRoomDeviceLending findDeviceLendingById(Integer idKey){
		return labRoomDeviceLendingDAO.findLabRoomDeviceLendingByPrimaryKey(idKey);
	}
	/****************************************************************************
	 * 功能：查找审核结果map
	 * 作者：李鹏翔
	 ****************************************************************************/
	public Map<Integer,String> getAuditResultMap(){
		//Set<CAuditResult> cars = cAuditResultDAO.findAllCAuditResults();
		List<CDictionary> cars = shareService.getCDictionaryData("c_audit_result");
		Map<Integer,String> map = new HashMap<Integer, String>();
		/*for(CAuditResult car : cars){
			map.put(car.getId(), car.getName());
		}*/
		for(CDictionary car : cars){
			map.put(car.getId(), car.getCName());
		}
		return map;
	}
	/****************************************************************************
	 * 功能：保存审核之后的借用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void saveAuditDeviceLending(LabRoomDeviceLendingResult lrdlr){
		labRoomDeviceLendingResultDAO.store(lrdlr);
		labRoomDeviceLendingResultDAO.flush();
	}
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getPassLendingTotals(LabRoomDeviceLendingResult lrdlr) {
//		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='1'";
		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='1'";
		return ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getPassKeepingTotals(LabRoomDeviceLendingResult lrdlr) {
//		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='2'";
		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='2'";
		return ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllPassLending(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='1'";
		String sql="select l from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='1'";
		return labRoomDeviceLendingResultDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllPassKeeping(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='2'";
		String sql="select l from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='2'";
		return labRoomDeviceLendingResultDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有审核被拒绝的设备借用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getRejectedLendingTotals(LabRoomDeviceLendingResult lrdlr) {
//		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CAuditResult.id=3 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='1'";
		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='3' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='1'";
		return ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有审核被拒绝的设备领用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getRejectedKeepingTotals(LabRoomDeviceLendingResult lrdlr) {
//		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CAuditResult.id=3 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='2'";
		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='3' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='2'";
		return ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllRejectedLending(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLendingResult l where l.CAuditResult.id=3 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='1'";
		String sql="select l from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='3' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='1'";
		return labRoomDeviceLendingResultDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllRejectedKeeping(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLendingResult l where l.CAuditResult.id=3 and l.labRoomDeviceLending.CLendingStatus.id = 1 and l.labRoomDeviceLending.lendType='2'";
		String sql="select l from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='3' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '1' and l.labRoomDeviceLending.lendType='2'";
		return labRoomDeviceLendingResultDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有已归还设备借用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getReturnedLendingTotals(LabRoomDeviceLendingResult lrdlr) {
//		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 2 and l.labRoomDeviceLending.lendType='1'";
		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '2' and l.labRoomDeviceLending.lendType='1'";
		return ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有已归还设备领用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getReturnedKeepingTotals(LabRoomDeviceLendingResult lrdlr) {
//		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 2 and l.labRoomDeviceLending.lendType='2'";
		String sql="select count(l) from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '2' and l.labRoomDeviceLending.lendType='2'";
		return ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有已归还的设备借用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllReturnedLending(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 2 and l.labRoomDeviceLending.lendType='1'";
		String sql="select l from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '2' and l.labRoomDeviceLending.lendType='1'";
		return labRoomDeviceLendingResultDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有已归还的设备领用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllReturnedKeeping(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceLendingResult l where l.CAuditResult.id=2 and l.labRoomDeviceLending.CLendingStatus.id = 2 and l.labRoomDeviceLending.lendType='2'";
		String sql="select l from LabRoomDeviceLendingResult l where l.CDictionary.CCategory='c_audit_result' and l.CDictionary.CNumber ='2' and l.labRoomDeviceLending.CDictionary.CCategory='c_lending_status' and l.labRoomDeviceLending.CDictionary.CNumber= '2' and l.labRoomDeviceLending.lendType='2'";
		return labRoomDeviceLendingResultDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：归还借出的设备
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void returnDeviceLending(Integer idKey){
		LabRoomDeviceLendingResult lrdlr = labRoomDeviceLendingResultDAO.findLabRoomDeviceLendingResultByPrimaryKey(idKey);
		LabRoomDeviceLending lrdl = lrdlr.getLabRoomDeviceLending();
		LabRoomDevice lrd = lrdl.getLabRoomDevice();
		/*CDeviceStatus cds = new CDeviceStatus();
		cds.setId(1);
		lrd.setCDeviceStatus(cds);*/
		CDictionary cds = shareService.getCDictionaryByCategory("c_lab_room_device_status", "1");
		lrd.setCDictionaryByDeviceStatus(cds);
		labRoomDeviceDAO.store(lrd);
		
		/*CLendingStatus cls = new CLendingStatus();
		cls.setId(2);
		lrdl.setCLendingStatus(cls);*/
		CDictionary cls = shareService.getCDictionaryByCategory("c_lending_status", "2");
		lrdl.setCDictionary(cls);
		labRoomDeviceLendingDAO.store(lrdl);
	}
	/****************************************************************************
	 * 功能：查找所有维修列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getAllRepairTotals(LabRoomDeviceRepair lrdr) {
		String sql="select count(l) from LabRoomDeviceRepair l  where 1=1";
		return ((Long)labRoomDeviceRepairDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有维修列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findAllRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize) {
		String sql="select l from LabRoomDeviceRepair l  where 1=1";
		return labRoomDeviceRepairDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找当前用户的设备报修或审核
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findAllRepairsByUser(String username) {
		String sql="select l from LabRoomDeviceRepair l  where l.user.username = '"+username+"' or l.labRoomDevice.user.username = '"+username+"'";
		return labRoomDeviceRepairDAO.executeQuery(sql);		
	}
	/****************************************************************************
	 * 功能：保存设备报修
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void saveNewDeviceRepair(LabRoomDeviceRepair lrdr){
		if(lrdr.getCreateTime() == null){
			lrdr.setCreateTime(Calendar.getInstance());
		}
		labRoomDeviceRepairDAO.store(lrdr);
		labRoomDeviceRepairDAO.flush();
	}
	/****************************************************************************
	 * 功能：查找所有报修列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getApplyRepairTotals(LabRoomDeviceRepair lrdr) {
//		String sql="select count(l) from LabRoomDeviceRepair l  where l.CLabRoomDeviceRepairStatus = 1";
		String sql="select count(l) from LabRoomDeviceRepair l  where l.CDictionaryByStatusId.CCategory='c_lab_room_device_repair_status' and l.CDictionaryByStatusId.CNumber ='1'";
		
		return ((Long)labRoomDeviceRepairDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有报修列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findApplyRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceRepair l  where l.CLabRoomDeviceRepairStatus = 1";
		String sql="select l from LabRoomDeviceRepair l  where l.CDictionaryByStatusId.CCategory='c_lab_room_device_repair_status' and l.CDictionaryByStatusId.CNumber ='1'";
		return labRoomDeviceRepairDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有已修复列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getPassRepairTotals(LabRoomDeviceRepair lrdr) {
//		String sql="select count(l) from LabRoomDeviceRepair l  where l.CLabRoomDeviceRepairStatus = 3";
		String sql="select count(l) from LabRoomDeviceRepair l  where l.CDictionaryByStatusId.CCategory='c_lab_room_device_repair_status' and l.CDictionaryByStatusId.CNumber ='3'";
		return ((Long)labRoomDeviceRepairDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有已修复列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findPassRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceRepair l  where l.CLabRoomDeviceRepairStatus = 3";
		String sql="select l from LabRoomDeviceRepair l  where l.CDictionaryByStatusId.CCategory='c_lab_room_device_repair_status' and l.CDictionaryByStatusId.CNumber ='3'";
		return labRoomDeviceRepairDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	/****************************************************************************
	 * 功能：查找所有报修列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getRejectedRepairTotals(LabRoomDeviceRepair lrdr) {
//		String sql="select count(l) from LabRoomDeviceRepair l  where l.CLabRoomDeviceRepairStatus = 2";
		String sql="select count(l) from LabRoomDeviceRepair l  where l.CDictionaryByStatusId.CCategory='c_lab_room_device_repair_status' and l.CDictionaryByStatusId.CNumber ='2'";
		return ((Long)labRoomDeviceRepairDAO.createQuerySingleResult(sql).getSingleResult()).intValue();		
	}
	/****************************************************************************
	 * 功能：查找所有报修列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findRejectedRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize) {
//		String sql="select l from LabRoomDeviceRepair l  where l.CLabRoomDeviceRepairStatus = 2";
		String sql="select l from LabRoomDeviceRepair l  where l.CDictionaryByStatusId.CCategory='c_lab_room_device_repair_status' and l.CDictionaryByStatusId.CNumber ='2'";
		return labRoomDeviceRepairDAO.executeQuery(sql,(page-1)*pageSize,pageSize);		
	}
	
	/****************************************************************************
	 * 功能：查找所有借用记录条数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getAllLendTotals(){
		//String sql1 = "select count(l) from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='1'";
		String sql1 = "select count(l) from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='1'";
		String sql2 = "select count(l) from LabRoomDeviceLendingResult l where l.labRoomDeviceLending.lendType='1'";
		//申请单数目
		int t1 =  ((Long)labRoomDeviceLendingDAO.createQuerySingleResult(sql1).getSingleResult()).intValue();
		//审核数目
		int t2 = ((Long)labRoomDeviceLendingResultDAO.createQuerySingleResult(sql2).getSingleResult()).intValue();
		return t1 + t2;
	}

	/****************************************************************************
	 * 功能：查找所有借用申请和审核
	 * 作者：李鹏翔
	 ****************************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SchoolDevice> findAllLendingList(int curr , int size){
		//String sql1 = "select l from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='1' ";
		String sql1 = "select l from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='1' ";
		String sql2 = "select l from LabRoomDeviceLendingResult l where l.labRoomDeviceLending.lendType='1'";
		List<LabRoomDeviceLending> l1 = new LinkedList(labRoomDeviceLendingDAO.executeQuery(sql1, (curr-1)*size, size));
		List<LabRoomDeviceLendingResult> l2 = new LinkedList(labRoomDeviceLendingResultDAO.executeQuery(sql2, (curr-1)*size, size));
		//创建新集合存放两个集合的内同
		List<SchoolDevice> sds = new ArrayList();
		//将两个集合合并
		if(l2.size()>0){
			for(LabRoomDeviceLendingResult l : l2){
				if(l.getLabRoomDeviceLending()!=null){
					SchoolDevice sd = new SchoolDevice();
					if(l.getLabRoomDeviceLending()!=null){
						sd.setDevicePattern(l.getLabRoomDeviceLending()==null?null:l.getLabRoomDeviceLending().getUserByTeacher().getCname());
						sd.setDeviceEnName(l.getLabRoomDeviceLending()==null?null:l.getLabRoomDeviceLending().getUserByLendingUser().getCname());
						sd.setDeviceFormat(l.getLabRoomDeviceLending().getContent());
						sd.setCreatedAt(l.getLabRoomDeviceLending().getLendingTime());
						sd.setDeviceCountry(l.getLabRoomDeviceLending()==null?null:l.getLabRoomDeviceLending().getUserByLendingUser().getUsername());
						/*if(l.getLabRoomDeviceLending()!=null&&l.getLabRoomDeviceLending().getCLendingStatus()!=null){
						    sd.setAcademyMemo(l.getLabRoomDeviceLending().getCLendingStatus().getName());
						}*/
						if(l.getLabRoomDeviceLending()!=null&&l.getLabRoomDeviceLending().getCDictionary()!=null){
						    sd.setAcademyMemo(l.getLabRoomDeviceLending().getCDictionary().getCName());
						}
						if(l.getLabRoomDeviceLending().getLabRoomDevice()!=null){
							if(l.getLabRoomDeviceLending().getLabRoomDevice().getSchoolDevice()!=null){
								sd.setDeviceName(l.getLabRoomDeviceLending().getLabRoomDevice().getSchoolDevice().getDeviceName());
								sd.setDeviceNumber(l.getLabRoomDeviceLending().getLabRoomDevice().getSchoolDevice().getDeviceNumber());
							}
							if(l.getLabRoomDeviceLending().getLabRoomDevice().getUser()!=null){
								sd.setProjectSource(l.getLabRoomDeviceLending().getLabRoomDevice().getUser().getUsername());
							}
						}
					}
					if(l.getUser()!=null){
						sd.setDeviceUseDirection(l.getUser().getCname());
					}
					//sd.setDeviceAddress(l.getCAuditResult().getName());
					sd.setDeviceAddress(l.getCDictionary().getCName());
					sd.setDeviceSupplier(l.getRemark());
					sd.setManufacturer(String.valueOf(l.getLabRoomDeviceLending().getLabRoomDevice().getLabRoom().getLabAnnex().getLabCenter().getId()));
					sds.add(sd);
				}
			}
		}
		if(l1.size()>0){
			for(LabRoomDeviceLending ld : l1){
				SchoolDevice sd = new SchoolDevice();
				sd.setDeviceNumber(ld.getLabRoomDevice().getSchoolDevice().getDeviceNumber());
				sd.setDeviceName(ld.getLabRoomDevice().getSchoolDevice().getDeviceName());
				sd.setDeviceEnName(ld.getUserByLendingUser().getCname());
				sd.setDevicePattern(ld.getUserByTeacher().getCname());
				sd.setDeviceFormat(ld.getContent());
				sd.setCreatedAt(ld.getLendingTime());
				sd.setDeviceUseDirection("未审核");
				sd.setDeviceAddress("未审核");
				sd.setDeviceSupplier("未审核");
				sd.setAcademyMemo("未归还");
				sd.setDeviceCountry(ld.getUserByLendingUser().getUsername());
				if(ld.getLabRoomDevice().getUser()!=null){
					sd.setProjectSource(ld.getLabRoomDevice().getUser().getUsername());
				}
				sd.setManufacturer(String.valueOf(ld.getLabRoomDevice().getLabRoom().getLabAnnex().getLabCenter().getId()));
				sds.add(sd);
			}
		}
		return sds;
	}
	
	/****************************************************************************
	 * 功能：查找当前用户的借用申请或审核
	 * 作者：李鹏翔
	 ****************************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SchoolDevice> findLendingListByUser(String username){
//		String sql1 = "select l from LabRoomDeviceLending l where l.CLendingStatus.id is null and l.lendType='1' and ( l.userByLendingUser.username = '"+username+"' or l.labRoomDevice.user.username = '"+username+"' ) ";
		String sql1 = "select l from LabRoomDeviceLending l where l.CDictionary.id is null and l.lendType='1' and ( l.userByLendingUser.username = '"+username+"' or l.labRoomDevice.user.username = '"+username+"' ) ";
		String sql2 = "select l from LabRoomDeviceLendingResult l where l.labRoomDeviceLending.lendType='1' and ( l.labRoomDeviceLending.userByLendingUser.username = '"+username+"' or l.labRoomDeviceLending.labRoomDevice.user.username = '"+username+"' ) ";
		List<LabRoomDeviceLending> l1 = labRoomDeviceLendingDAO.executeQuery(sql1);
		List<LabRoomDeviceLendingResult> l2 = labRoomDeviceLendingResultDAO.executeQuery(sql2);
		//创建新集合存放两个集合的内同
		List<SchoolDevice> sds = new ArrayList();
		//将两个集合合并
		if(l2.size()>0){
			for(LabRoomDeviceLendingResult l : l2){
				if(l.getLabRoomDeviceLending()!=null){
					SchoolDevice sd = new SchoolDevice();
					sd.setId(l.getId());
					if(l.getLabRoomDeviceLending()!=null){
						sd.setDeviceEnName(l.getLabRoomDeviceLending().getUserByLendingUser().getCname());
						sd.setDevicePattern(l.getLabRoomDeviceLending().getUserByTeacher().getCname());
						sd.setDeviceFormat(l.getLabRoomDeviceLending().getContent());
						sd.setCreatedAt(l.getLabRoomDeviceLending().getLendingTime());
						//sd.setDeviceAddress(l.getCAuditResult().getName());
						sd.setDeviceAddress(l.getCDictionary().getCName());
						sd.setDeviceSupplier(l.getRemark());
						sd.setDeviceCountry(l.getLabRoomDeviceLending().getUserByLendingUser().getUsername());
						//sd.setAcademyMemo(l.getLabRoomDeviceLending().getCLendingStatus().getName());
						sd.setAcademyMemo(l.getLabRoomDeviceLending().getCDictionary().getCName());
						if(l.getLabRoomDeviceLending().getLabRoomDevice()!=null){
							if(l.getLabRoomDeviceLending().getLabRoomDevice().getSchoolDevice()!=null){
								sd.setDeviceName(l.getLabRoomDeviceLending().getLabRoomDevice().getSchoolDevice().getDeviceName());
							}
							if(l.getLabRoomDeviceLending().getLabRoomDevice().getUser()!=null){
								sd.setDeviceNumber(l.getLabRoomDeviceLending().getLabRoomDevice().getUser().getCname());
								sd.setProjectSource(l.getLabRoomDeviceLending().getLabRoomDevice().getUser().getUsername());
							}
							if(l.getUser()!=null){
								sd.setDeviceUseDirection(l.getUser().getCname());
							}
						}
					}
					sd.setManufacturer(String.valueOf(l.getLabRoomDeviceLending().getLabRoomDevice().getLabRoom().getLabAnnex().getLabCenter().getId()));
					sds.add(sd);
				}
			}
		}
		if(l1.size()>0){
			for(LabRoomDeviceLending ld : l1){
				SchoolDevice sd = new SchoolDevice();
				sd.setId(ld.getId());
				sd.setDeviceEnName(ld.getUserByLendingUser().getCname());
				sd.setDevicePattern(ld.getUserByTeacher().getCname());
				if(ld.getLabRoomDevice()!=null){
					if(ld.getLabRoomDevice().getSchoolDevice()!=null){
						sd.setDeviceNumber(ld.getLabRoomDevice().getSchoolDevice().getDeviceNumber());
						sd.setDeviceName(ld.getLabRoomDevice().getSchoolDevice().getDeviceName());
					}
					if(ld.getLabRoomDevice().getUser()!=null){
						sd.setProjectSource(ld.getLabRoomDevice().getUser().getUsername()); //这一行在报错，暂时先注释掉
					}
				}
				sd.setDeviceFormat(ld.getContent());
				sd.setCreatedAt(ld.getLendingTime());
				sd.setDeviceUseDirection("未审核");
				sd.setDeviceAddress("未审核");
				sd.setDeviceSupplier("未审核");
				sd.setAcademyMemo("未归还");
				sd.setDeviceCountry(ld.getUserByLendingUser().getUsername());
				if(ld.getLabRoomDevice().getUser()!=null){
				}
				sd.setManufacturer(String.valueOf(ld.getLabRoomDevice().getLabRoom().getLabAnnex().getLabCenter().getId()));
				sds.add(sd);
			}
		}
		return sds;
	}
	/****************************************************************************
	 * 功能：根据主键查询实验室设备预约
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public LabRoomDeviceReservation findlabRoomDeviceReservationByPrimaryKey(int id) {
		return labRoomDeviceReservationDAO.findLabRoomDeviceReservationByPrimaryKey(id);
	}
	/****************************************************************************
	 * 功能：根据设备预约id和username查询审核结果
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResult(
			int id, String username) {
		String sql="select t from LabRoomDeviceReservationResult t where t.labRoomDeviceReservation.id="+id+" and t.user.username="+username;
		List<LabRoomDeviceReservationResult> list=labRoomDeviceReservationResultDAO.executeQuery(sql, 0,-1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return new LabRoomDeviceReservationResult();
		}
		
	}
	/****************************************************************************
	 * 功能：根据预约id和导师查询非导师的审核结果
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public LabRoomDeviceReservationResult findManageAudit(int id,
			String username) {
		String sql="select t from LabRoomDeviceReservationResult t where t.labRoomDeviceReservation.id="+id+" and t.user.username <>"+username;
		List<LabRoomDeviceReservationResult> list=labRoomDeviceReservationResultDAO.executeQuery(sql, 0,-1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return new LabRoomDeviceReservationResult();
		}
	}
	/****************************************************************************
	 * 功能：下载设备文档（化工学院）
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public void downloadFile(CommonDocument doc, HttpServletRequest request,
			HttpServletResponse response) {
		try{			
			String fileName=doc.getDocumentName();
			String root = System.getProperty("xidlims.root");
			FileInputStream fis = new FileInputStream(root+doc.getDocumentUrl());
			response.setCharacterEncoding("utf-8");
			//解决上传中文文件时不能下载的问题
			response.setContentType("multipart/form-data;charset=UTF-8");
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");// firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName.replaceAll(" ", ""));
			
			OutputStream fos = response.getOutputStream();
			byte[] buffer = new byte[8192];
			int count = 0;
			while((count = fis.read(buffer))>0){
				fos.write(buffer,0,count);   
			}
			fis.close();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	@Override
	public int findStudentByCnameAndUsername(User user, String academyNumber, Integer deviceId){
		String sql="select count(distinct u) from User u join u.authorities a where (a.id <> 4 and a.id <> 10)";
		sql+=" and u.username not in (select p.user.username from LabRoomDevicePermitUsers p where p.labRoomDevice.id = "+deviceId+" )";
		LabRoomDevice device = findLabRoomDeviceByPrimaryKey(deviceId);
		if (device.getUser()!=null&&device.getUser().getUsername()!=null) {
			sql+=" and u.username <> '" + device.getUser().getUsername()+"'";
		}
		// 找到所有的实验室中心主任
		List<User> userList = shareService.findUsersByAuthorityId(4);
		for (User director : userList) {
			sql+=" and u.username <> '"+director.getUsername()+"'";
		}
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and u.schoolAcademy.academyNumber like '"+academyNumber+"%'";
		}
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equals("")){
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql+=" and u.username like '%"+user.getUsername()+"%'";
			}
		}
		// sql+=" and u.userRole<>1"; //可以有教师
		return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	@Override
	public List<User> findStudentByCnameAndUsername(User user, String academyNumber, Integer deviceId, Integer page, int pageSize){
		String sql="select distinct u from User u join u.authorities a where (a.id <> 4 and a.id <> 10)";
		sql+=" and u.username not in (select p.user.username from LabRoomDevicePermitUsers p where p.labRoomDevice.id = "+deviceId+" )";
		LabRoomDevice device = findLabRoomDeviceByPrimaryKey(deviceId);
		if (device.getUser()!=null&&device.getUser().getUsername()!=null) {
			sql+=" and u.username <> '" + device.getUser().getUsername()+"'";
		}
		// 找到所有的实验室中心主任
		List<User> userList = shareService.findUsersByAuthorityId(4);
		for (User director : userList) {
			sql+=" and u.username <> '"+director.getUsername()+"'";
		}
		
		if(academyNumber!=null&&!academyNumber.equals("")){
			sql+=" and u.schoolAcademy.academyNumber like '"+academyNumber+"%'";
		}
		if(user!=null){
			if(user.getCname()!=null&&!user.getCname().equals("")){
				sql+=" and u.cname like '%"+user.getCname()+"%'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				sql+=" and u.username like '%"+user.getUsername()+"%'";
			}
		}
		//sql+=" and u.userRole<>1";  //可以有教师
		sql+="ORDER BY CASE WHEN u.schoolAcademy.academyNumber like '"+academyNumber+"%' THEN 0 ELSE 1 END";
		sql+=" ,u.username desc";
		return userDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	
	
	/****************************************************************************
	 * 功能：通过username和deviceId查询labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public LabRoomDevicePermitUsers findPermitUserByUsernameAndDeivce(String username, int deviceId){
		String sql = "select u from LabRoomDevicePermitUsers u where 1=1";
		if (username!=null&&!username.equals("")
				&&deviceId!=0) {
			sql+=" and u.user.username like '"+ username +"'";
			sql+=" and u.labRoomDevice.id ="+deviceId;
			List<LabRoomDevicePermitUsers> users = labRoomDevicePermitUsersDAO.executeQuery(sql, 0, -1);
			if (users.size()>0) {
				return users.get(0);
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	/****************************************************************************
	 * 功能：通过deviceId查询labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDevicePermitUsers> findPermitUserByDeivceId(int deviceId, int page, int pageSize){
		String sql = "select u from LabRoomDevicePermitUsers u where 1=1";
		if (deviceId!=0) {
			sql+=" and u.labRoomDevice.id ="+deviceId;
			sql+=" and u.flag <>1";//1--单独培训
			List<LabRoomDevicePermitUsers> users = labRoomDevicePermitUsersDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
			if (users.size()>0) {
				return users;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	/****************************************************************************
	 * 功能：删除labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public void deletePermitUser(LabRoomDevicePermitUsers user){
		labRoomDevicePermitUsersDAO.remove(user);
		labRoomDevicePermitUsersDAO.flush();
	}
	
	/****************************************************************************
	 * 功能：通过主键查询labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersByPrimaryKey(int id){
		LabRoomDevicePermitUsers user = labRoomDevicePermitUsersDAO.findLabRoomDevicePermitUsersByPrimaryKey(id);
		return user;
	}
	
	/****************************************************************************
	 * 功能：更新某一设备下所有预约的审核结果
	 * 作者：贺子龙
	 * @throws ParseException 
	 ****************************************************************************/
	@SuppressWarnings("deprecation")
	public void updateReservationResult(int deviceId) throws ParseException{
		//id对应的设备
        //针对设置过预约审核时间的设备，删除已经过时了的预约
        LabRoomDevice device=labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(deviceId);
        
        //针对使用时间已过期的设备，予以审核取消
        if (device.getCDictionaryByAllowAppointment()!=null&&"1".equals(device.getCDictionaryByAllowAppointment().getCNumber())&&
            device.getCDictionaryByIsAudit()!=null&&"1".equals(device.getCDictionaryByIsAudit().getCNumber())){//需要审核
        	Set<LabRoomDeviceReservation> reservations = device.getLabRoomDeviceReservations();
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
            	if (labRoomDeviceReservation.getCAuditResult()!=null&&
            			labRoomDeviceReservation.getCAuditResult().getId()==1) {//还没有审核结果
            		//获取当前时间
                    Calendar currTime = Calendar.getInstance();
                    //获取使用时间
                    Calendar beginTime = labRoomDeviceReservation.getBegintime();
            		if (currTime.after(beginTime)) {//当前时间已经超过使用时间
            			//设备预约拒绝
                    	alterTimeAfterRefused(labRoomDeviceReservation,2);
					}
            	}
            }
        }
        
        
        //判断设备允许预约、需要审核并且有预约审核时间限制
        if (device.getCDictionaryByAllowAppointment()!=null&&"1".equals(device.getCDictionaryByAllowAppointment().getCNumber())&&
                device.getCDictionaryByIsAudit()!=null&&"1".equals(device.getCDictionaryByIsAudit().getCNumber())&&
        		device.getCDictionaryByTeacherAudit()!=null&&"1".equals(device.getCDictionaryByTeacherAudit().getCNumber())&&//需要导师审核
        		device.getIsAuditTimeLimit()!=null&&device.getIsAuditTimeLimit()==1) {
        	//获取审核限制时间
        	int addHours = 48;//默认为48小时
        	if (device.getAuditTimeLimit()!=null&&device.getAuditTimeLimit()!=0) {
        		addHours = device.getAuditTimeLimit();
			}
            //获取当前时间
            Calendar currTime = Calendar.getInstance();
            
            Set<LabRoomDeviceReservation> reservations = device.getLabRoomDeviceReservations();
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
            	if (labRoomDeviceReservation.getCAuditResult()!=null&&
            			labRoomDeviceReservation.getCAuditResult().getId()==1) {//设备预约处于未审核状态
                //获取该预约的创建时间
//            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Calendar reservationTime = labRoomDeviceReservation.getTime();
//                String dateStr = sdf.format(reservationTime.getTime());
//                System.out.println("before:"+dateStr);
                Date date = reservationTime.getTime();
                //加上审核限制时间
                date.setHours(date.getHours()+addHours);
                Calendar judgeTime = Calendar.getInstance();
                judgeTime.setTime(date);
//                dateStr = sdf.format(reservationTime.getTime());
//                System.out.println("after:"+dateStr);
                if (currTime.after(judgeTime)) {//当前时间已经超过限制时间
                	//设备预约拒绝
                	alterTimeAfterRefused(labRoomDeviceReservation,1);
                	
                	/*//创建一条审核意见
                	LabRoomDeviceReservationResult audit = new LabRoomDeviceReservationResult();
                	audit.setLabRoomDeviceReservation(labRoomDeviceReservation);
                	User user = new User();
                	int tag=0;
                	//根据对应的设备的审核流程情况，设置audit对应的用户
                	if (device.getCActiveByTeacherAudit()!=null&&device.getCActiveByTeacherAudit().getId()==1) {
                		//导师审核
						user = labRoomDeviceReservation.getUserByTeacher();
						tag=1;
					}else if (device.getCActiveByLabManagerAudit()!=null&&device.getCActiveByLabManagerAudit().getId()==1) {
						tag=2;
						//实验室管理员审核
						Set<LabRoomAdmin> admins=labRoomDeviceReservation.getLabRoomDevice().getLabRoom().getLabRoomAdmins();
						if (admins.size()>0) {
							for (LabRoomAdmin labRoomAdmin : admins) {
								if (labRoomAdmin.getUser().getUsername()!=null) {
									user=labRoomAdmin.getUser();break;
								}
							}
						}
					}else {
						//设备管理员审核
						tag=3;
						user = labRoomDeviceReservation.getLabRoomDevice().getUser();
					}
                	audit.setUser(user);
                	audit.setTag(tag);
                	//审核意见
            		String remark="";
            		remark+="未在规定时间内进行审核  审核人姓名："+user.getCname()+"      审核人工号："+user.getUsername();
            		audit.setRemark(remark);
            		CTrainingResult result=cTrainingResultDAO.findCTrainingResultByPrimaryKey(2);
            		audit.setCTrainingResult(result);
            		labRoomDeviceReservationResultDAO.store(audit);*/
                }
				}
            }
        }
	}
	
	/****************************************************************************
	 * 功能：设备预约审核拒绝后放开被预约掉的时间段，使其设备可以重新预约该时间段
	 * 作者：贺子龙
	 ****************************************************************************/
	public void alterTimeAfterRefused(LabRoomDeviceReservation reservation, int flag) throws ParseException{
		int resultId = -1;
		if (flag==0) {//审核拒绝
			resultId = 3;
		}else if(flag==1){//审核取消--未在48小时内审核
			resultId = 4;
		}else if(flag==3){//排课冲突
			resultId = 6;
		}else {//审核取消--使用时间过期
			resultId = 5;
		}
		//CAuditResult r=cAuditResultDAO.findCAuditResultByPrimaryKey(resultId);
		CDictionary r = shareService.getCDictionaryByCategory("c_audit_result", String.valueOf(resultId));
		reservation.setCAuditResult(r);
		reservation.setStage(-1);
		reservation.setOriginalBegin(reservation.getBegintime());//在被赋值为1900-01-01之前，把本身的值保存在original中
		reservation.setOriginalEnd(reservation.getEndtime());
		String str="1900-01-01";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date =sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		reservation.setBegintime(calendar);
		reservation.setEndtime(calendar);
		labRoomDeviceReservationDAO.store(reservation);
	}
	
	/****************************************************************************
	 * 功能：查出某一中心下的所有设备管理员
	 * 作者：贺子龙
	 ****************************************************************************/
	public Map<String, String> findDeviceManagerByCid(int cid){
		Map<String, String> userMap = new HashMap<String, String>();
		//创造一个空的labRoomDevice对象
		LabRoomDevice device = new LabRoomDevice();
		List<LabRoomDevice> devices = findAllLabRoomDeviceNew(device,cid,1,-1,1);
		for (LabRoomDevice labRoomDevice : devices) {
			if (labRoomDevice.getUser()!=null&&labRoomDevice.getUser().getUsername()!=null
					&&!labRoomDevice.getUser().getUsername().equals("")) {
				userMap.put(labRoomDevice.getUser().getUsername(), 
						labRoomDevice.getUser().getCname()+"["+labRoomDevice.getUser().getUsername()+"]");
			}
		}
		return userMap;
	}
	
	/****************************************************************************
	 * 功能：根据设备编号查询实验室设备
	 * 作者：贺子龙
	 ****************************************************************************/
	public LabRoomDevice findLabRoomDeviceByDeviceNumber(String deviceNumber){
		if (deviceNumber!=null&&!deviceNumber.equals("")) {
			String sql = "select d from LabRoomDevice d where 1=1";
			sql+=" and d.schoolDevice.deviceNumber like '%"+deviceNumber+"%'";
			List<LabRoomDevice> devices = labRoomDeviceDAO.executeQuery(sql, 0, -1);
			if (devices.size()>0) {
				return devices.get(0);
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	/****************************************************************************
	 * 功能：找到当前用户的所有培训预约
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingByUser(Integer deviceId, Integer termId, int page, int pageSize){
		// 获取当前用户
		User user = shareService.getUser();
		String sql = "";
		boolean isTeacher = true;
		if (shareService.getUserDetail().getUserRole().trim().equals("1")) {// 老师权限
			sql="select t from LabRoomDeviceTraining t where 1=1 ";
		}else {
			sql="select distinct t from LabRoomDeviceTraining t, LabRoomDeviceTrainingPeople lp where 1=1 ";
			sql+=" and lp.labRoomDeviceTraining.id=t.id";
			isTeacher = false;
		}
		// 学期限制
		if (termId!=null) {
			// do nothing
		}else {
			termId = shareService.findNewTerm().getId();
		}
		sql+=" and t.schoolTerm.id="+termId;
		// 设备限制
		if (deviceId!=null) {
			sql+=" and t.labRoomDevice.id="+deviceId;
		}
		// 用户限制
		if (isTeacher) {
			sql+=" and t.user.username = '"+ user.getUsername()+"'";
		}else {
			sql+=" and lp.user.username = '"+ user.getUsername()+"')";
		}
		sql+=" order by t.time desc";
		return labRoomDeviceTrainingDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
	}
	
	/****************************************************************************
	 * description：设备教学使用报表
	 * author：郑昕茹
	 ****************************************************************************/
	@SuppressWarnings("rawtypes")
		public List getListLabRoomDeviceUsageInAppointment(HttpServletRequest request, int page, int pageSize){
			String sql = "select * from view_device_related_appointment v where 1=1";
			if(request.getParameter("deviceName") != null && !request.getParameter("deviceName").equals("")){
				sql += " and device_name like '%"+ request.getParameter("deviceName") +"%'";
			}
			if(request.getParameter("deviceNumber") != null && !request.getParameter("deviceNumber").equals("")){
				sql += " and device_number like '%"+ request.getParameter("deviceNumber") +"%'";
			}
			if(request.getParameter("courseName") != null && !request.getParameter("courseName").equals("")){
				sql += " and courseName like '%"+ request.getParameter("courseName") +"%'";
			}
			if(request.getParameter("itemName") != null && !request.getParameter("itemName").equals("")){
				sql += " and itemName like '%"+ request.getParameter("itemName") +"%'";
			}
			if(request.getParameter("teacherName") != null && !request.getParameter("teacherName").equals("")){
				sql += " and cname like '%"+ request.getParameter("teacherName") +"%'";
			}
			Query query= entityManager.createNativeQuery(sql);
			if(pageSize != -1)
			query.setMaxResults(pageSize);
			query.setFirstResult(pageSize*(page-1));
	       // 获取list对象
	        List<Object[]> list= query.getResultList();
			return list;
		}
	
	/****************************************************************************
	 * @description：设备教学使用报表-找到所有被排课用到的设备
	 * @author：郑昕茹
	 ****************************************************************************/ 
		public List<TimetableLabRelatedDevice> getAllLabRoomDeviceUsageInAppointment( ){
			String sql ="select l from TimetableLabRelatedDevice l where 1=1 " ;
			sql += " group by labRoomDevice.id";
			return timetableLabRelatedDeviceDAO.executeQuery(sql, 0, -1);
		}
		
	/****************************************************************************
	 * @description：设备教学使用报表-找到所有上课教师
	 * @author：郑昕茹
	****************************************************************************/ 
	public List<TimetableTeacherRelated> getAllTimetableRelatedTeachers( ){
		String sql ="select l from TimetableTeacherRelated l where 1=1 " ;
		sql += " group by user.username";
		return timetableTeacherRelatedDAO.executeQuery(sql, 0, -1);
	}
			
	/****************************************************************************
	 * @description：设备教学使用报表-找到所有排课相关的实验项目
	 * @author：郑昕茹
	****************************************************************************/ 
	public List<TimetableItemRelated> getAllTimetableRelatedItems( ){
		String sql ="select l from TimetableItemRelated l where 1=1 " ;
		sql += " group by operationItem.id";
		return timetableItemRelatedDAO.executeQuery(sql, 0, -1);
	}		
	
	/****************************************************************************
	 * description：设备教学使用报表-找到所有的课程名称
	 * author：郑昕茹
	 ****************************************************************************/
	@SuppressWarnings("rawtypes")
		public List getAllCoursesInAppointment(HttpServletRequest request){
			String sql = "select * from view_device_related_appointment v where 1=1";
			sql += " group by courseName";
			Query query= entityManager.createNativeQuery(sql); 
	       // 获取list对象
	        List<Object[]> list= query.getResultList();
			return list;
		}
	/*************************************************************************************
	 * @description：设备教学使用报表
	 * @author：郑昕茹
	 * @date：2016-10-25
	 *************************************************************************************/
	@Override
	public void exportLabRoomDeviceUsageInAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		List<Map> list = new ArrayList<Map>();
		List<Object[]> listLabRoomDeviceUsageInAppointments = this.getListLabRoomDeviceUsageInAppointment(request,1,-1);
		//实验中心所在学院的实验室项目
		int i=1;
			for (Object[] o : listLabRoomDeviceUsageInAppointments) 
			{
				Map map = new HashMap();
				
				map.put("serial number",i);//序号
				i++;
				map.put("deviceName",o[2]);//设备名称
				map.put("courseName",o[12]);//课程名称
				map.put("itemName",o[10]);//项目
				map.put("weekday", o[4]);//星期
				map.put("classes",o[6]);//节次
				map.put("weeks",o[5]);//周次
				map.put("teachers",o[8]);//上课教师
				map.put("rooms",o[9]);//实验室
				list.add(map);
			}  
		String title = "设备教学使用表";
        String[] hearders = new String[] {"序号","设备名称","课程名称","实验项目","星期",
        		"节次","周次","上课教师","实验室"};//表头数组
        String[] fields = new String[] {"serial number","deviceName", "courseName", "itemName",    "weekday",
        		"classes", "weeks", "teachers", "rooms"};
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportExcel(title,  shareService.getUserDetail().getCname(), "",td);	
	}
	
	/*************************************************************************************
	 * @description：设备使用报表
	 * @author：郑昕茹
	 * @date：2016-10-25
	 *************************************************************************************/
	@Override
	public void exportLabRoomDeviceUsage(HttpServletRequest request, HttpServletResponse response,LabRoomDeviceReservation reservation, Integer cid) throws Exception 
	{
		List<Map> list = new ArrayList<Map>();
		List<LabRoomDeviceReservation> reservationList = this.findLabRoomDeviceReservationByDeviceNumberAndCid(reservation, request, null, 1, -1, 2, cid);
		//实验中心所在学院的实验室项目
		int i=1;
			for (LabRoomDeviceReservation l : reservationList) 
			{
				Map map = new HashMap();
				
				map.put("serial number",i);//序号
				i++;
				//预约设备
				if(l.getInnerDeviceName() == null){
					map.put("deviceName",l.getLabRoomDevice().getSchoolDevice().getDeviceName()+"["
							+l.getLabRoomDevice().getSchoolDevice().getDeviceNumber()+"]");
				}
				else{
					map.put("deviceName", l.getInnerDeviceName()+" 关联设备");
				}
				//申请人
				if(l.getUserByReserveUser() != null)
				{
					map.put("reserveUser",l.getUserByReserveUser().getCname()+"["+l.getUserByReserveUser().getUsername()+"]");
				}
				else{
					map.put("reserveUser","");
				}
				//指导教师
				if(l.getUserByTeacher() != null)
				{
					map.put("teacher",l.getUserByTeacher().getCname()+"["+l.getUserByTeacher().getUsername()+"]");
				}
				else{
					map.put("teacher","");
				}
				//使用内容
				map.put("content", l.getContent());
				//使用机时 
				map.put("reserveHours", l.getReserveHours());
				
				//日期
				if(l.getBegintime() != null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					map.put("date",sdf.format(l.getBegintime().getTime()));
				}
				else{
					map.put("date", "");
				}
				//使用时间
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
				String start = "", end = "";
				if(l.getBegintime() != null)
				start = sdf.format(l.getBegintime().getTime());
				if(l.getEndtime() != null){
					end = sdf.format(l.getEndtime().getTime());
				}
				map.put("useTime", start+"-"+end);
				//设备管理员
				if(l.getLabRoomDevice().getUser() != null){
					map.put("manager", l.getLabRoomDevice().getUser().getCname()+"["+l.getLabRoomDevice().getUser().getUsername());
				}
				else{
					map.put("manager", "");
				}
				
				if(l.getLabRoomDevice() != null && l.getLabRoomDevice().getLabRoom() != null && l.getLabRoomDevice().getLabRoom().getLabRoomName() != null){
					map.put("labRoomName", l.getLabRoomDevice().getLabRoom().getLabRoomName());
				}
				else{
					map.put("labRoomName", "");
				}
				//收费情况
				if(l.getLabRoomDevice() != null && l.getLabRoomDevice().getCDictionaryByDeviceCharge() != null){
					String s = l.getLabRoomDevice().getCDictionaryByDeviceCharge().getCName()+"\n";
					if(l.getLabRoomDevice().getPrice() != null && l.getReserveHours() != null){
						s += l.getLabRoomDevice().getPrice().multiply(l.getReserveHours()).toString();
					}
					else{
						s += "0元";
					}
					map.put("fees", s);
				}
				else{
					map.put("fees", "");
				}
				list.add(map);
			}  
		String title = "设备使用报表";
        String[] hearders = new String[] {"序号","预约设备","申请人","指导教师","使用内容",
        		"使用机时","日期","使用时间","设备管理员","实验室","收费情况"};//表头数组
        String[] fields = new String[] {"serial number","deviceName", "reserveUser", "teacher",    "content",
        		"reserveHours", "date", "useTime", "manager","labRoomName","fees"};
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportExcel(title,  shareService.getUserDetail().getCname(), "",td);	
	}
	
	/****************************************************************************
	 * @description：课题组管理-查询到所有的课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	public List<ResearchProject> findAllResearchProjects(ResearchProject researchProject, int currpage, int pageSize){
		String sql = "select r from ResearchProject r where 1=1";
		if(researchProject != null && researchProject.getCode() != null && !researchProject.getCode().equals("")){
			sql += " and code like '%"+researchProject.getCode()+"%'";
		}
		if(researchProject != null && researchProject.getName() != null && !researchProject.getName().equals("")){
			sql += " and name like '%"+researchProject.getName()+"%'";
		}
		return researchProjectDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	
	/****************************************************************************
	 * @description：课题组管理-保存新建的课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	public ResearchProject saveResearchProject(ResearchProject researchProject){
		return researchProjectDAO.store(researchProject);
	}
	
	/****************************************************************************
	 * @description：课题组管理-删除课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	public boolean deleteResearchProject(int id){
		ResearchProject researchProject = researchProjectDAO.findResearchProjectByPrimaryKey(id);
		if(researchProject != null){
			researchProjectDAO.remove(researchProject);
			researchProjectDAO.flush();
			return true;
		}
		return false;
	}
	
	/****************************************************************************
	 * @description：课题组管理-根据主键找到课题组
	 * @author：郑昕茹
	 * @date:2016-11-06
	 ****************************************************************************/
	public ResearchProject findResearchProjectByPrimaryKey(int id){
		return researchProjectDAO.findResearchProjectByPrimaryKey(id); 
	}
	
	/****************************************************************************
	 * 功能：查出某一中心下的所有设备管理员
	 * 作者：贺子龙
	 ****************************************************************************/
	public Map<String, String> findDeviceManageCnamerByCid(int cid){
		Map<String, String> userMap = new HashMap<String, String>();
		//创造一个空的labRoomDevice对象
		LabRoomDevice device = new LabRoomDevice();
		List<LabRoomDevice> devices = findAllLabRoomDeviceNew(device,cid,1,-1,1);
		for (LabRoomDevice labRoomDevice : devices) {
			if (labRoomDevice.getUser()!=null&&labRoomDevice.getUser().getUsername()!=null
					&&!labRoomDevice.getUser().getUsername().equals("")) {
				userMap.put(labRoomDevice.getUser().getUsername(), 
						labRoomDevice.getUser().getCname());
			}
		}
		return userMap;
	}

	/****************************************************************************
     * 得到设备的使用时间、使用次数、收取费用
     * @author 贺子龙
     * 2016-07-18
     ***************************************************************************/
    public void calculateHoursForSchoolDevice (String deviceNumber, String terms){
        
    	// LabRoomDeviceReservation和labRoomDevice联合查询，条件是设备编号和学期
        String sql = "select l from LabRoomDeviceReservation l where 1=1";		
        sql+=" and l.labRoomDevice.schoolDevice.deviceNumber like '"+deviceNumber+"'";
        sql+=" and l.CDictionaryByCAuditResult.id = 2";
        
        
        if (!EmptyUtil.isStringEmpty(terms)) {
			sql+=" and l.schoolTerm.id in "+terms;
		}
        
        List<LabRoomDeviceReservation> reservations = labRoomDeviceReservationDAO.executeQuery(sql, 0 , -1);
        SchoolDevice schoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(deviceNumber);
        
        double useHours = 0.0;// 设备使用时长
        double price = 0.0;// 设备收取费用
        int useCount = 0;// 设备使用次数（预约成功的次数）
        if (reservations!=null && reservations.size()>0) {
            for (LabRoomDeviceReservation labRoomDeviceReservation : reservations) {
            	
            	if (labRoomDeviceReservation.getBegintime().after(Calendar.getInstance())) {
					continue;
				}
            	
            	// 累计使用时长
            	double useHour = this.getReserveHoursOfReservation(labRoomDeviceReservation).doubleValue();
                useHours += useHour;
                
                // 累计收取的费用
                // 判断收费类型(1计时 2计件 3计次 4记天)
                int chargeType = 0;
                if (!EmptyUtil.isObjectEmpty(labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByDeviceCharge())) {
                	chargeType = labRoomDeviceReservation.getLabRoomDevice().getCDictionaryByDeviceCharge().getId();
				}
                // 如果没有计费类型，则无法统计收费情况
                
                if (chargeType!=0 && !EmptyUtil.isObjectEmpty(labRoomDeviceReservation.getLabRoomDevice().getPrice())) {
                	switch (chargeType) {
					case 1:// 计时收费
						price += useHour * labRoomDeviceReservation.getLabRoomDevice().getPrice().doubleValue();
						break;
					case 4:// 按天收费
						price += (useHour/24) * labRoomDeviceReservation.getLabRoomDevice().getPrice().doubleValue();
					default:
						price += labRoomDeviceReservation.getLabRoomDevice().getPrice().doubleValue();
						break;
					}
				}
                
                useCount++;
            }
        }
        
        // 使用时长
        BigDecimal userHourBD = new BigDecimal(useHours);
        // 收取费用
        BigDecimal priceBD = new BigDecimal(price);
        if (!EmptyUtil.isStringEmpty(terms)) {// 判空。
        	int term = Integer.parseInt(terms.substring(1, terms.length()-1));
        	SchoolDeviceUse deviceUse = schoolDeviceService.findSchoolDeviceUseByNumberAndTerm(deviceNumber, term);
        	if (EmptyUtil.isObjectEmpty(deviceUse)) {// 未有历史记录，则新建
        		deviceUse = new SchoolDeviceUse();
        		deviceUse.setSchoolDevice(schoolDevice);
        		deviceUse.setTerm(term);
        		deviceUse.setUseCount(useCount);
        		deviceUse.setUseHours(userHourBD);
        		deviceUse.setPrice(priceBD);
        		schoolDeviceUseDAO.store(deviceUse);
        	}else {// 有历史记录，则更新
        		deviceUse.setUseCount(useCount);
        		deviceUse.setUseHours(userHourBD);
        		deviceUse.setPrice(priceBD);
        		schoolDeviceUseDAO.store(deviceUse);
        	}
		}
    }
    
}