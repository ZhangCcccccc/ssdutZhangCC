package net.xidlims.service.visualization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.ShareService;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.LabReservationDAO;
import net.xidlims.dao.LabRoomAdminDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.LabRoomDeviceDAO;
import net.xidlims.dao.LabRoomDeviceReservationDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolDeviceDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SystemRoomDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableCourseStudentDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabReservation;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SystemRoom;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.User;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

@Service("VisualizationService")
public class VisualizationServiceImpl implements VisualizationService 
{
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private SchoolDeviceDAO schoolDeviceDAO;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	@Autowired
	private LabRoomDeviceReservationDAO labRoomDeviceReservationDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TimetableCourseStudentDAO timetableCourseStudentDAO;
	@Autowired 
	private LabRoomAdminDAO labRoomAdminDAO;
	@Autowired
	private LabReservationDAO labReservationDAO;
	@Autowired
	private LabRoomDeviceDAO labRoomDeviceDAO;
	@Autowired
	private SystemRoomDAO systemRoomDAO;
	@Autowired
	private CommonDocumentService commonDocumentService;
	@Autowired
	private CommonDocumentDAO documentDAO;
	
	public VisualizationServiceImpl(){
		
	}
	

	/**
	 * 根据楼宇和楼层查找楼层房间列表
	 * 裴继超
	 * 2016年1月22日
	 */
	@Override
	public List<LabRoom> getLabRoomsByBuildAndFloor(String buildNumber,String floor,
			Integer page,int pageSize) 
	{
		String sql = "select r from LabRoom r where 1=1 ";
		if(buildNumber!=null&&buildNumber!=""){
			sql = sql + " and r.systemRoom.systemBuild.buildNumber like '"+buildNumber+"'";
			if(floor!=null&floor!=""){
				sql = sql + " and r.systemRoom.roomNo like '"+buildNumber+floor+"%'";
			}
		}
		
		sql = sql + " and r.isUsed = 1 order by r.labRoomNumber";
		//sql = sql + " and r.labRoomNumber like '"+buildNumber+"%'";
		List<LabRoom> labRooms = labRoomDAO.executeQuery(sql.toString(), (page-1)*pageSize, pageSize);
		return labRooms;
	}
	
	/**
	 * 根据id查找实验室
	 * 裴继超
	 * 2016年1月23日
	 */
	@Override
	public LabRoom findLabRoomByPrimaryKey(int id){
		LabRoom labRoom = labRoomDAO.findLabRoomByPrimaryKey(id);
		return labRoom;
	}
	
	/**
	 * 保存实验室设备
	 * 裴继超
	 * 2016年1月27日
	 */
	@Override
	public void saveLabRoomDevice(LabRoomDevice labRoomDevice){
		labRoomDeviceDAO.store(labRoomDevice);
		labRoomDeviceDAO.flush();
	}
	
	/**
	 * 保存设备字典
	 * 裴继超
	 * 2016年1月27日
	 */
	@Override
	public SchoolDevice saveSchoolDevice(LabRoomDevice labRoomDevice){
		SchoolDevice schoolDevice = labRoomDevice.getSchoolDevice();
		schoolDevice.setId(0);
		schoolDevice = schoolDeviceDAO.store(schoolDevice);
		schoolDeviceDAO.flush();
		return schoolDevice;
	}
	
	/**
	 * 根据id查找实验室设备
	 * 裴继超
	 * 2016年1月27日
	 */
	@Override
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(int id){
		LabRoomDevice labRoomDevice = labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(id);
		return labRoomDevice;
	}
	
	/**
	 * 删除实验室设备位置标记
	 * 裴继超
	 * 2016年1月28日
	 */
	@Override
	public void deletLabRoomDeviceXY(int id){
		LabRoomDevice labRoomDevice = labRoomDeviceDAO.findLabRoomDeviceByPrimaryKey(id);
		labRoomDevice.setxCoordinate(null);
		labRoomDevice.setyCoordinate(null);
		labRoomDeviceDAO.remove(labRoomDevice);
		labRoomDeviceDAO.flush();
	}
	
	/**
	 * 删除设备字典
	 * 裴继超
	 * 2016年1月28日
	 */
	@Override
	public void deletSchoolDevice(String schoolDeviceNumber){
		SchoolDevice schoolDevice = schoolDeviceDAO.findSchoolDeviceByPrimaryKey(schoolDeviceNumber);
		schoolDeviceDAO.remove(schoolDevice);
		schoolDeviceDAO.flush();
	}
	
	/**
	 * 根据是否存在坐标查找实验室设备
	 * 裴继超
	 * 2016年3月24日11:09:34
	 */
	@Override
	public List<LabRoomDevice> findLabRoomDevicesByLabRoomIdAndXY(int labRoomId){
		String sql = "select d from LabRoomDevice d where d.labRoom.id = "+labRoomId+" and (d.xCoordinate != null or d.yCoordinate != null) ";
		List<LabRoomDevice> labRoomDevices = labRoomDeviceDAO.executeQuery(sql.toString(), 0, -1);;
		return labRoomDevices;
	}
	
	/**
	 * floor页面替换实验室详细信息map
	 * 裴继超
	 * 2016年3月25日
	 */
	@Override
	public Map findLabRoomMap(LabRoom labRoom){
		Map<String, String> map = new HashMap<String, String>();
		if(labRoom!=null){
			map.put("labRoomName", labRoom.getLabRoomNumber()+"  "+labRoom.getLabRoomName());
			//map.put("adress", (labRoom.getLabRoomAddress()==null)?"":labRoom.getLabRoomAddress());
			map.put("adress", labRoom.getSystemRoom().getSystemBuild().getSystemCampus().getCampusName()
					+labRoom.getSystemRoom().getSystemBuild().getBuildName()
					+labRoom.getSystemRoom().getRoomName()
					+"("+labRoom.getSystemRoom().getRoomNo()+")");
			map.put("departmentNumber", (labRoom.getSystemRoom().getDepartmentNumber()==null)?"":labRoom.getSystemRoom().getDepartmentNumber());
			map.put("labRoomNumber", (labRoom.getLabRoomNumber()==null)?"":labRoom.getLabRoomNumber());
			map.put("CLabRoomType", (labRoom.getCDictionaryByLabRoom()==null)?"":labRoom.getCDictionaryByLabRoom().getCName());
			map.put("labRoomCapacity", (labRoom.getLabRoomCapacity()==null)?"":labRoom.getLabRoomCapacity().toString());
			map.put("isUsed", labRoom.getLabRoomActive().toString());
			map.put("appointment", labRoom.getLabRoomReservation().toString());
			map.put("labRoomArea", (labRoom.getLabRoomArea()==null)?"":labRoom.getLabRoomArea().toString());
			map.put("labRoomIntroduction", (labRoom.getLabRoomIntroduction()==null)?"":labRoom.getLabRoomIntroduction());
		}else{
			map.put("labRoomName","");
			map.put("adress","");
			map.put("departmentNumber","");
			map.put("labRoomNumber","");
			map.put("CLabRoomType","");
			map.put("labRoomCapacity","");
			map.put("isUsed","");
			map.put("appointment","");
			map.put("labRoomArea","");
			map.put("labRoomIntroduction","");
		}
		return map;
	}
	
	/**
	 * 根据tag查找channel
	 * 裴继超
	 * 2016年3月25日
	 */
	/*@Override
	public List<Channel> findChannelsByTag(int tag){
		String sql = "select c from Channel c join c.tags t where t.id = "+tag;
		List<Channel> channels = channelDAO.executeQuery(sql.toString(), 0, -1);;
		return channels;
	}*/
	
	/****************************************************************************
	 * 功能：保存实验分室
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	@Override
	public LabRoom save(LabRoom labRoom) {
		// TODO Auto-generated method stub
		return labRoomDAO.store(labRoom);
	}
	
	/**
	 * 根据主键查找字典实验室
	 * 裴继超
	 * 2016年5月27日
	 */
	@Override
	public SystemRoom findSystemRoomByPrimaryKey(String nummber){
		SystemRoom systemRoom = systemRoomDAO.findSystemRoomByPrimaryKey(nummber);
		return systemRoom;
	}
	
	/****************************************************************************
	 * 功能：给实验室上传图片
	 * 作者：裴继超
	 * 时间：2016年5月27日
	 ****************************************************************************/
	@Override
	public void uploadImageForLabRoom(HttpServletRequest request,
			HttpServletResponse response, Integer id,Integer type) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
		 String sep = System.getProperty("file.separator"); 
		 Map files = multipartRequest.getFileMap(); 
		 Iterator fileNames = multipartRequest.getFileNames();
		 boolean flag =false; 
		 String fileDir = request.getSession().getServletContext().getRealPath( "/") +  "upload"+ sep+"labroom"+sep+id;
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
			  //文件重命名
			  int endAddress = fileTrueName.lastIndexOf(".");
			  String ss = fileTrueName.substring(endAddress, fileTrueName.length());//后缀名
			  String fileNewName = "roomImageType"+type+ss; 
			  //System.out.println("文件名称："+fileTrueName);
			  File uploadedFile = new File(fileDir + sep + fileNewName); 
			  //System.out.println("文件存放路径为："+fileDir + sep + fileNewName);
			  try {
				FileCopyUtils.copy(bytes,uploadedFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  saveLabRoomDocument(fileTrueName,fileNewName,id,type);
		  } 
		}
		
	}
	
	/****************************************************************************
	 * 功能：保存实验分室的文档
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	public void saveLabRoomDocument(String fileTrueName,String fileNewName, Integer labRoomid,Integer type) {
		// TODO Auto-generated method stub
		//id对应的实验分室
		LabRoom room=labRoomDAO.findLabRoomByPrimaryKey(labRoomid);
		CommonDocument doc=new CommonDocument();
		if(type==4){
			CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(room,4);
			if(commonDocument != null){
				doc = commonDocument;
			}
		}
		if(type==3){
			CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(room,3);
			if(commonDocument != null){
				doc = commonDocument;
			}
		}
		if(type==1){
			CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(room,1);
			if(commonDocument != null){
				doc = commonDocument;
			}
		}
		doc.setType(type);
		doc.setDocumentName(fileTrueName);
		String imageUrl="upload/labroom/"+labRoomid+"/"+fileNewName;
		doc.setDocumentUrl(imageUrl);
		doc.setLabRoom(room);
		documentDAO.store(doc);
	}

	/****************************************************************************
	 * description:删除图片
	 * 
	 * author:于侃
	 * date:2016年9月21日 14:45:13
	 ****************************************************************************/
	public void deleteImageForLabRoom(Integer labRoomid,Integer type,HttpServletRequest request) {
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String path;
		if(type ==2){
			//id对应的实验分室
			LabRoom room=labRoomDAO.findLabRoomByPrimaryKey(labRoomid);
			List<CommonDocument> commonDocuments = commonDocumentService.findCommonDocumentsByLabRoom(room,type);
			for (CommonDocument c : commonDocuments) {
				path = rootPath+c.getDocumentUrl();
				File file = new File(path);
				file.delete();
				documentDAO.remove(c);
			}
		}else{
			//id对应的实验分室
			LabRoom room=labRoomDAO.findLabRoomByPrimaryKey(labRoomid);
			CommonDocument commonDocument = commonDocumentService.findCommonDocumentByLabRoom(room,type);
			path = rootPath+commonDocument.getDocumentUrl();
			File file = new File(path);
			file.delete();
			documentDAO.remove(commonDocument);
		}
	}
	
	/****************************************************************************
	 * description:下载图片
	 * 
	 * author:于侃
	 * date:2016年9月22日 14:21:28
	 ****************************************************************************/
	public void downloadImageForLabRoom(Integer id,HttpServletRequest request,
			HttpServletResponse response) {
		try{
			//String sep = System.getProperty("file.separator");
			//根路径
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			//当前登陆人
			String username=shareService.getUser().getUsername();
			//String root = System.getProperty("gvsuntms.root");
			//获取下载的文件
			CommonDocument commonDocument = commonDocumentService.findCommonDocumentByPrimaryKey(id);
			//路径
			String filePath = rootPath+commonDocument.getDocumentUrl();
			//名称
			String fileName = commonDocument.getDocumentName();
			FileInputStream fis = new FileInputStream(filePath);
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
			
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName.replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&"));
			
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
}
