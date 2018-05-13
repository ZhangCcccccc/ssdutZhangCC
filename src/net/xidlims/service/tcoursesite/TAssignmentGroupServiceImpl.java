package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//import for 时间格式 文件大小
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TAssignmentGradingAttachmentDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentGradingAttachment;
import net.xidlims.service.common.ShareService;

import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("TAssignmentGroupService")
public class TAssignmentGroupServiceImpl implements TAssignmentGroupService {

	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentGradingAttachmentDAO tAssignmentGradingAttachmentDAO;
	@Autowired
	private TAssignmentService tAssignmentService;

	/**************************************************************************
	 * Description:查询小组作业附件
	 * 
	 * @author：于侃
	 * @date ：2016年10月18日 10:15:19
	 **************************************************************************/
	public List<TAssignmentGradingAttachment> findGradeUrls(Integer id){
		String sql = "from TAssignmentGradingAttachment c where c.TAssignmentGradingId = '" + id + "'";
		List<TAssignmentGradingAttachment> gradeUrls = tAssignmentGradingAttachmentDAO.executeQuery(sql, 0, -1);
		return gradeUrls;
	}
	
	/**************************************************************************
	 * Description:保存学生提交的小组作业
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 09:59:00
	 **************************************************************************/
	public TAssignmentGrading saveGroupTAssignmentGrading(TAssignmentGrading tAssignmentGrade,Integer cid,Integer groupId, HttpServletRequest request) {
		TAssignmentGrading tAssignmentGrading = new TAssignmentGrading();
		//设置关联的提交小组作业的学生
		tAssignmentGrading.setUserByStudent(userDAO.findUserByPrimaryKey(tAssignmentGrade.getUserByStudent().getUsername()));
		//设置关联的作业
		tAssignmentGrading.setTAssignment(tAssignmentDAO.findTAssignmentById(tAssignmentGrade.getTAssignment().getId()));
		//设置作业描述
		tAssignmentGrading.setContent(tAssignmentGrade.getContent());
		//设置小组分工
		tAssignmentGrading.setDistribution(tAssignmentGrade.getDistribution());
		//设置SubmitTime
		tAssignmentGrading.setSubmitTime(tAssignmentGrade.getSubmitTime());
		//设置groupId
		tAssignmentGrading.setGroupId(tAssignmentGrade.getGroupId());
		//0表示正常提交
		Integer islate = 0;
		//提交日期 即当前时间
		Calendar submitDate = Calendar.getInstance();
		//规定提交日期
		Calendar dueDate = tAssignmentGrading.getTAssignment().getTAssignmentControl().getDuedate();
		if(submitDate.after(dueDate)){
			//1表示迟交
			islate = 1;
		}
		//设置提交日期
		tAssignmentGrading.setSubmitdate(submitDate);
		//设置是否迟交
		tAssignmentGrading.setIslate(islate);
		//保存提交小组作业
		tAssignmentGrading = tAssignmentGradingDAO.store(tAssignmentGrading);
		//获取附件id列表
		String uploadIdStringList = request.getParameter("groupAssignmentsList");
		String[] uploadIdList = uploadIdStringList.split(",");
		if(uploadIdStringList.indexOf(",")==-1){
			uploadIdList = null;
		}else{
			for (String attach : uploadIdList) {
				TAssignmentGradingAttachment tempAttachment = tAssignmentGradingAttachmentDAO.findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(Integer.parseInt(attach));
				TAssignmentGradingAttachment attachment = new TAssignmentGradingAttachment();
				attachment.setCreatedBy(tempAttachment.getCreatedBy());
				attachment.setGradeUrl(tempAttachment.getGradeUrl());
				attachment.setType(1);
				attachment.setCreatedTime(tempAttachment.getCreatedTime());
				//设置关联的grading记录
				attachment.setTAssignmentGradingId(tAssignmentGrading.getAccessmentgradingId());
				//设置groupId
				attachment.setGroupId(groupId);
				tAssignmentGradingAttachmentDAO.store(attachment);
			}
		}
		return tAssignmentGrading;
		
	}
	
	/**************************************************************************
	 * Description:根据groupId查询小组作业(按照提交日期正序排序)
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 14:15:09
	 **************************************************************************/
	public List<TAssignmentGrading> findTAssignmentGradingList(Integer tAssignmentId,Integer groupId) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentGrading c where c.TAssignment.id = " + tAssignmentId + " and c.groupId= "+groupId+" and c.finalScore is null order by c.submitdate";
		List<TAssignmentGrading> gradings = tAssignmentGradingDAO.executeQuery(sql); 
		return gradings;
	}
	
	/**************************************************************************
	 * Description:根据tAssignmentId,username,groupId查询该学生小组作业成绩
	 * 
	 * @author：于侃
	 * @date ：2016年10月20日 09:33:49
	 **************************************************************************/
	public TAssignmentGrading findGroupTAssignmentGrading(Integer tAssignmentId,String username,Integer groupId) {
		String sql = "from TAssignmentGrading c where c.TAssignment.id = " + tAssignmentId + " and c.groupId= " + groupId + " and c.userByStudent.username= '" + username +
				"' and c.finalScore is not null";
		List<TAssignmentGrading> gradings = tAssignmentGradingDAO.executeQuery(sql); 
		if(gradings.size()>0){
			return gradings.get(0);
		}
		return new TAssignmentGrading();
	}
	
	/**************************************************************************
	 * Description:处理ajax上传过来文件，支持多文件 
	 * 
	 * @author：于侃
	 * @date ：2016年10月20日 13:57:07
	 **************************************************************************/
	public int processUpload(Integer tAssignmentId,Integer tcoursesiteId,HttpServletRequest request) {
		//用于多文件上传的request
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//分隔符/
		String sep = System.getProperty("file.separator");
		//获取所有上传的文件
		Map<?, ?> files = multipartRequest.getFileMap();
		//多文件所有文件名称集合
		Iterator<?> fileNames = multipartRequest.getFileNames();
		//文件id列表，通过","分隔
		String resourceLis = "";
		//文件夹是否创建成功的标志位
		boolean flag = false;
		//拼接文件夹地址   "tcoursesite\site"+tcoursesiteId+"\tAssignment"+tAssignmentId+"\"+文件名
		//String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
		//		+ "tcoursesite" + sep + "site"+ tcoursesiteId + sep + "tAssignment" + tAssignmentId + sep;
		String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
				+ "tcoursesite" + sep + "site"+ tcoursesiteId + sep + "tAssignment"+ sep
				+ "folder" + tAssignmentService.findTAssignmentById(tAssignmentId).getWkFolder().getId()
				+ sep + "student" + sep;
		
		
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			//将文件转换成用于上传
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename);
			//获得文件数据
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					//判断该文件夹是否存在
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						//该文件夹不存在，创建该文件夹
						flag = dirPath.mkdirs();
					}
				}
				//取得上传时的文件名称
				String fileTrueName = file.getOriginalFilename();
//				//取得上传文件后缀
//				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
//				// 使用UUID生成文件名称
//				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
				//拼成完整的文件保存路径加文件
				String fileName = fileDir + fileTrueName;
				//通过文件保存路径新建上传文件
				File uploadFile = new File(fileName);
				try {
					//拷贝文件内容，在相应路径创建该文件
					FileCopyUtils.copy(bytes, uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//新建上传文件
				TAssignmentGradingAttachment attachment = new TAssignmentGradingAttachment();
//				//保存图片大小
//				long a=uploadFile.length()/1024/1024;
//				//文件大小的格式
//				DecimalFormat fmt=new DecimalFormat("#.##");
//				//文件大小
//				String size="";
//				//根据文件大小来添加单位M/KB
//				if (a!=0) {
//					size=fmt.format(a)+"M";
//				}else{
//					a = uploadFile.length()/1024;
//					size = fmt.format(a)+"KB";
//				}
				//设置上传文件路径
				attachment.setGradeUrl(sep + "upload" + sep + "tcoursesite" + sep + "site"+ tcoursesiteId + 
						sep + "tAssignment" + tAssignmentId + sep + fileTrueName);
				//设置上传人
				attachment.setCreatedBy(shareService.getUserDetail().getUsername());
				//设置上传时间  current time
				attachment.setCreatedTime(Calendar.getInstance());
				//保存上传文件 返回生成的文件id
				int id = tAssignmentGradingAttachmentDAO.store(attachment).getTAssignmentGradingAttachmentId();
				//资源列表
				resourceLis = resourceLis + String.valueOf(id) + ",";
				//返回生成的文件id
				return id;
			}
		}
		return 0;
	}
	
	/**************************************************************************
	 * Description:批量下载附件（先打包）
	 * 
	 * @author：于侃
	 * @date ：2016年10月21日 10:23:46
	 **************************************************************************/
	public String downloadAttachments(Integer tAssignmentId,Integer tcoursesiteId,String attachList,HttpServletRequest request,HttpServletResponse response) {
		String root = System.getProperty("xidlims.root");
		//File.separator windows是\，unix是/
		String sep = System.getProperty("file.separator");
		//获取待下载附件列表
		String[] attachs = attachList.split(",");
		//附件文件列表
		File file;
		//生成的ZIP文件名为Demo.zip
		String tmpFileName = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
		+ "tcoursesite" + sep + "site"+ tcoursesiteId + sep + "tAssignment" + tAssignmentId + sep + "Demo.zip";
       byte[] buffer = new byte[1024];
       try{
    	   ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tmpFileName));
    	   for(String attach : attachs){   
    		   TAssignmentGradingAttachment tempAttachment = tAssignmentGradingAttachmentDAO.findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(Integer.parseInt(attach));
    		   file = new File(root + tempAttachment.getGradeUrl());
    		   FileInputStream fis = new FileInputStream(file);
    		   out.putNextEntry(new ZipEntry(file.getName()));
    		   //设置压缩文件内的字符编码，不然会变成乱码  
    		   out.setEncoding("GBK");
    		   int len;
    		   //读入需要下载的文件的内容，打包到zip文件
    		   while((len = fis.read(buffer))>0) {
    			   out.write(buffer,0,len);
    		   }
    		   out.closeEntry();
    		   fis.close();
	   		}
			out.close();
			downFile(response, tmpFileName);
			file = new File(tmpFileName);
			file.delete();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
      return "success";
	}
	
	/**************************************************************************
	 * Description:文件下载
	 * 
	 * @author：于侃
	 * @date ：2016年10月21日 10:23:46
	 **************************************************************************/
	 private void downFile(HttpServletResponse response, String filePath) { 
        try {  
            File file = new File(filePath);  
            if (file.exists()) {  
                InputStream fis = new FileInputStream(filePath);  
                response.reset();   
                response.setContentType("multipart/form-data;charset=UTF-8");// 设置response内容的类型  
                response.setHeader(  
                        "Content-disposition",  
                        "attachment;filename="  
                                + URLEncoder.encode(getFileName(filePath), "UTF-8"));// 设置头部信息  
				OutputStream fos = response.getOutputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = fis.read(buffer))>0){
					fos.write(buffer,0,count);   
				}
				fos.close();
				fis.close();
            } else {  
                System.out.println("该文件不存在！"); 
            }  
        } catch (IOException e) {  
            
        	System.out.println("文件下载出错");
        	e.printStackTrace();
        }  
	}  
	
	private String getFileName(String fileName) {
		//File.separator windows是\，unix是/
		String sep = System.getProperty("file.separator");
		return fileName.substring(fileName.lastIndexOf(sep)+1, fileName.length());
	}
}
