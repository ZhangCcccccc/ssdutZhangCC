package net.xidlims.service.teaching;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteUserDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.view.ViewTAssignmentAllGrade;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("TAssignmentGradingService")
public class TAssignmentGradingServiceImpl implements TAssignmentGradingService {

	@Autowired
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private TCourseSiteUserDAO tCourseSiteUserDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	/**
	 * @功能： 保存学生提交的作业
	 * @作者： 黄崔俊
	 * @时间： 2015-8-13 16:23:54
	 */
	@Override
	public TAssignmentGrading saveTAssignmentGrading(TAssignmentGrading tAssignmentGrade,Integer cid, HttpServletRequest request) {
		// TODO Auto-generated method stub
		if (tAssignmentGrade.getAccessmentgradingId() == null) {//直接提交或保存
			tAssignmentGrade.setUserByStudent(userDAO.findUserByPrimaryKey(tAssignmentGrade.getUserByStudent().getUsername()));
			tAssignmentGrade.setTAssignment(tAssignmentDAO.findTAssignmentById(tAssignmentGrade.getTAssignment().getId()));
			Integer islate = 0;//0表示正常提交
			//Calendar submitDate = tAssignmentGrade.getSubmitdate();
			Calendar submitDate = Calendar.getInstance();
			Calendar dueDate = tAssignmentGrade.getTAssignment().getTAssignmentControl().getDuedate();
			if(submitDate.after(dueDate)){
				islate = 1;//1表示迟交
			}
			tAssignmentGrade.setSubmitdate(submitDate);
			tAssignmentGrade.setIslate(islate);
			TAssignmentGrading newTAssignmentGrading = tAssignmentGradingDAO.store(tAssignmentGrade);
			String sep = "/";
			//String filePath="/upload"+sep+"tAssignment"+sep+cid+sep+tAssignmentGrade.getTAssignment().getId()+sep+"student";
			String filePath="/upload"+sep+"tcoursesite"+sep+"site"+cid+sep+"tAssignment"+sep+
					"folder"+tAssignmentGrade.getTAssignment().getWkFolder().getId()+sep+"student";
			
			String gradeUrl = fileUpload(request, filePath,tAssignmentGrade.getUserByStudent().getUsername(),newTAssignmentGrading.getAccessmentgradingId());
			newTAssignmentGrading.setGradeUrl(gradeUrl);
			tAssignmentGradingDAO.store(newTAssignmentGrading);
			return newTAssignmentGrading;
		}else {//提交前面保存过的内容或重复保存
			TAssignmentGrading oldTAssignmentGrading = tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(tAssignmentGrade.getAccessmentgradingId());
			oldTAssignmentGrading.setUserByStudent(userDAO.findUserByPrimaryKey(tAssignmentGrade.getUserByStudent().getUsername()));
			oldTAssignmentGrading.setSubmitTime(tAssignmentGrade.getSubmitTime());
			Integer islate = 0;//0表示正常提交
			//Calendar submitDate = tAssignmentGrade.getSubmitdate();
			Calendar submitDate = Calendar.getInstance();
			Calendar dueDate = oldTAssignmentGrading.getTAssignment().getTAssignmentControl().getDuedate();
			if(submitDate.after(dueDate)){
				islate = 1;//1表示迟交
			}
			tAssignmentGrade.setSubmitdate(submitDate);
			oldTAssignmentGrading.setIslate(islate);
			oldTAssignmentGrading.setContent(tAssignmentGrade.getContent());
			TAssignmentGrading newTAssignmentGrading = tAssignmentGradingDAO.store(oldTAssignmentGrading);
			String sep = System.getProperty("file.separator");
			//String filePath="/upload"+sep+"tAssignment"+sep+cid+sep+tAssignmentGrade.getTAssignment().getId()+sep+"student";
			String filePath="/upload"+sep+"tcoursesite"+sep+"site"+cid+sep+"tAssignment"+sep+
					"folder"+tAssignmentGrade.getTAssignment().getWkFolder().getId()+sep+"student";
			
			String gradeUrl = fileUpload(request, filePath,tAssignmentGrade.getUserByStudent().getUsername(),newTAssignmentGrading.getAccessmentgradingId());
			if(gradeUrl!=""){
				newTAssignmentGrading.setGradeUrl(gradeUrl);
			}
			tAssignmentGradingDAO.store(newTAssignmentGrading);
			return newTAssignmentGrading;
		}
		
	}

	/**
	 * @功能：根据作业id和user查询学生的作业提交情况
	 * @作者： 黄崔俊
	 * @时间： 2015-8-14 14:48:27
	 */
	@Override
	public List<TAssignmentGrading> findTAssignmentGradingList(
			Integer assignmentId, int flag, User user) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignmentGrading c where c.TAssignment.id = '"+assignmentId+"' and c.submitTime = '1'");
		if(flag==0){//学生，查看历史提交记录
			sql.append(" and c.userByStudent.username = '"+user.getUsername()+"'");
		}
		if(flag==1){//普通教师
			sql.append(" and c.TAssignment.user.username = '"+ user.getUsername() +"'");
		}
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql.toString(), 0, -1);
		Map<String,TAssignmentGrading> tAssignmentGradingsTemp = null;
		if(flag!=0){//如果不是学生则只能看到每个学生的最新提交记录
			tAssignmentGradingsTemp = new HashMap<String,TAssignmentGrading>();
			for (TAssignmentGrading tAssignmentGrading : tAssignmentGradings) {
				if (tAssignmentGradingsTemp.containsKey(tAssignmentGrading.getUserByStudent().getUsername())) {
					if (tAssignmentGrading.getAccessmentgradingId()>(tAssignmentGradingsTemp.get(tAssignmentGrading.getUserByStudent().getUsername())).getAccessmentgradingId()) {
						tAssignmentGradingsTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
					}
				}else {
					tAssignmentGradingsTemp.put(tAssignmentGrading.getUserByStudent().getUsername(), tAssignmentGrading);
				}
			}
		}
		if (tAssignmentGradingsTemp != null) {
			Set<String> set = tAssignmentGradingsTemp.keySet();
			tAssignmentGradings = new ArrayList<TAssignmentGrading>();
			for (String string : set) {
				tAssignmentGradings.add(tAssignmentGradingsTemp.get(string));
			}
			//没有成绩的在上，有成绩的在下，tAssignmentGrading1用于存储无成绩的，tAssignmentGrading2用于存储有成绩的
			List<TAssignmentGrading> tAssignmentGrading1 = new ArrayList<TAssignmentGrading>();
			List<TAssignmentGrading> tAssignmentGrading2 = new ArrayList<TAssignmentGrading>();
			for(TAssignmentGrading tAssignmentGrading : tAssignmentGradings) {
				
				if(tAssignmentGrading.getFinalScore()==null) {
					tAssignmentGrading1.add(tAssignmentGrading);
				}else {
					tAssignmentGrading2.add(tAssignmentGrading);
				}
			}
			tAssignmentGradings = new ArrayList<TAssignmentGrading>();
			tAssignmentGradings.addAll(tAssignmentGrading1);
			tAssignmentGradings.addAll(tAssignmentGrading2);
		}
		return tAssignmentGradings;
	}

	@Override
	public TAssignmentGrading findTAssignmentGradingById(Integer assignGradeId) {
		// TODO Auto-generated method stub
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(assignGradeId);
		return tAssignmentGrading;
	}

	@Override
	public TAssignmentGrading updateTAssignmentGrading(TAssignmentGrading tAssignmentGrade) {
		// TODO Auto-generated method stub
		TAssignmentGrading oldTAssignmentGrading = tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(tAssignmentGrade.getAccessmentgradingId());
		oldTAssignmentGrading.setUserByGradeBy(userDAO.findUserByPrimaryKey(tAssignmentGrade.getUserByGradeBy().getUsername()));
		oldTAssignmentGrading.setGradeTime(tAssignmentGrade.getGradeTime());
		oldTAssignmentGrading.setFinalScore(tAssignmentGrade.getFinalScore());
		oldTAssignmentGrading.setComments(tAssignmentGrade.getComments());
		return tAssignmentGradingDAO.store(oldTAssignmentGrading);
	}

	@Override
	public TAssignmentGrading updateTAssignmentGrading(Integer assignGradeId,String comments, Float finalScore,
			User nowUser,Calendar calendar) {
		// TODO Auto-generated method stub
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(assignGradeId);
		tAssignmentGrading.setUserByGradeBy(nowUser);
		tAssignmentGrading.setGradeTime(calendar);
		BigDecimal score = null;
		if (finalScore!=null) {
			if (tAssignmentGrading.getTAssignment().getTAssignmentAnswerAssign().getScore().compareTo(new BigDecimal(finalScore))==-1) {
				score = tAssignmentGrading.getTAssignment().getTAssignmentAnswerAssign().getScore();//超过分值则赋为满分
			}else {
				score = new BigDecimal(finalScore);
			}
		}
		tAssignmentGrading.setFinalScore(score);
		tAssignmentGrading.setComments(comments.trim());
		return tAssignmentGradingDAO.store(tAssignmentGrading);
	}

	/***********************************************************************************************
	 * 将文件上传到指定路径并返回文件保存路径
	 * 作者：黄崔俊
	 ***********************************************************************************************/
	private String fileUpload(HttpServletRequest request, String filePath,
			String username,Integer Gradingid) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    // 构建文件保存的目录
	    String PathDir = "/upload/"+ dateformat.format(new Date());*/
	    /** 得到文件保存目录的真实(绝对)路径* */
	    String RealPathDir = request.getSession().getServletContext().getRealPath(filePath);
	    //根据真实路径创建文件夹
	    File SaveFile = new File(RealPathDir);
	    if (!SaveFile.exists()){
	    	SaveFile.mkdirs();
	    }
	    /** 页面控件的文件流* */
	    MultipartFile multipartFile = multipartRequest.getFile("file");
	    /**判断文件不为空*/
	    if(multipartFile!=null&&!multipartFile.isEmpty()){
	    	//截取上传文件的名称，获取文件的后缀
	    	String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		    // 使用UUID生成文件名称,中文一般会报错(产生一个号称全球唯一的ID)
	    	//String name = UUID.randomUUID().toString() + suffix;// 构建文件名称
	    	
	    	//String CourseName=tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(Gradingid).getTAssignment().getTCourseSite().getTitle();
	    	//String name =CourseName+"-"+ username+"-"+Gradingid + suffix;// 构建文件名称
	    	String name ="-"+ username+"-"+Gradingid + suffix;// 构建文件名称
		    // 拼成完整的文件保存路径加文件
		    String fileName = RealPathDir + File.separator + name;
		    File file = new File(fileName);
		    try {
		    	//转储文件
		        multipartFile.transferTo(file);
		    } catch (IllegalStateException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    /** 上传到服务器的文件的绝对路径* */	       
		    String saveUrl=filePath+"/"+name;
		    return saveUrl;
	    }
	    return "";
	}
	
	/**
	 * 功能：批量下载该老师布置作业下学生提交的作业（先打包）
	 * 作者：黄崔俊
	 * 时间：2015-8-24 14:17:48
	 */
	@Override
	public String downloadAssignment(HttpServletRequest request,
			HttpServletResponse response, Integer cid, Integer tAssignmentId) {
		
		String root = System.getProperty("xidlims.root");
		String sep = System.getProperty("file.separator"); 
		
		//File.separator windows是\，unix是/
		String url=root+ "upload"+ sep+"tAssignment"+sep+cid+sep+tAssignmentId+sep+"student";
		//遍历学生文件夹下学生所提交的作业
		File rootFile = new File(url);
		File[] files = rootFile.listFiles();
		if (files==null || files.length==0) {
			return null;
		}
		//生成的ZIP文件名为Demo.zip
       String tmpFileName = root+ "upload"+ sep+"tAssignment"+sep+cid+sep+tAssignmentId+sep+"tAssignment-"+tAssignmentId+"-"+tAssignmentDAO.findTAssignmentById(tAssignmentId).getTitle()+".zip";
       byte[] buffer = new byte[1024];
       try{
    	   ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tmpFileName));
		
    	   for(File file:files){     
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
			File file = new File(tmpFileName);
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
	
	/**
	 * @功能：文件下载
	 * @作者： 黄崔俊
	 * @时间： 2015-8-24 21:15:11
	 */
	 private void downFile(HttpServletResponse response, String filePath) {  
	        try {  
	            File file = new File(filePath);  
	            if (file.exists()) {  
	                InputStream fis = new FileInputStream(filePath);  
	                /*BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面  
	                OutputStream outs = response.getOutputStream();// 获取文件输出IO流  
	                BufferedOutputStream bouts = new BufferedOutputStream(outs); */ 
	                response.setContentType("application/x-download");// 设置response内容的类型  
	                response.setHeader(  
	                        "Content-disposition",  
	                        "attachment;filename="  
	                                + URLEncoder.encode(getFileName(filePath), "UTF-8"));// 设置头部信息  
	               /* int bytesRead = 0;  
	                byte[] buffer = new byte[8192];  
	                // 开始向网络传输文件流  
	                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {  
	                    bouts.write(buffer, 0, bytesRead);  
	                }  
	                bouts.flush();// 这里一定要调用flush()方法  
	                ins.close();  
	                bins.close();  
	                outs.close();  
	                bouts.close();  */
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
		return fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
	}

	/**
	 * @功能：查询该课程下所有成绩情况
	 * @作者： 黄崔俊
	 * @时间：2015-8-25 14:05:06
	 */
	@Override
	public List<ViewTAssignmentAllGrade> searchAllGrade(User nowUser, Integer cid) {
		// TODO Auto-generated method stub
		//查询站点所属学生
		StringBuffer sql = new StringBuffer("from TCourseSiteUser c where c.TCourseSite.id = '"+cid+"'");
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql.toString(), 0, -1);
		String usernames = "(";
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			usernames += "'"+tCourseSiteUser.getUser().getUsername()+"',";
		}
		usernames = usernames.substring(0, usernames.length()-1)+")";
		sql = new StringBuffer("from User c where c.username in "+usernames+" order by c.username");
		List<User> users = userDAO.executeQuery(sql.toString(), 0, -1);
		
		//查询该站点下该老师布置的作业
		sql = new StringBuffer("from TAssignment c where c.siteId = '"+cid+"' and c.user.username = '"+nowUser.getUsername()+"' and c.type='assignment'");
		List<TAssignment> tAssignments = tAssignmentDAO.executeQuery(sql.toString(), 0, -1);
		
		//查询当前站点下该老师已经打分的成绩
		sql = new StringBuffer("from TAssignmentGrading c where c.TAssignment.siteId ='"+cid+"' and c.userByGradeBy.username ='"+nowUser.getUsername()+"' order by c.TAssignment.id");
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql.toString(), 0, -1);
		
		
		
		List<ViewTAssignmentAllGrade> viewTAssignmentAllGrades = new ArrayList<ViewTAssignmentAllGrade>();
		ViewTAssignmentAllGrade tAssignmentAllGrade = new ViewTAssignmentAllGrade();
		tAssignmentAllGrade.setCname("姓名");
		List<String> titles = new ArrayList<String>();
		for (TAssignment tAssignment : tAssignments) {
			titles.add(tAssignment.getTitle());
		}
		tAssignmentAllGrade.setScores(titles);
		tAssignmentAllGrade.setTotalGradeForOneUser("课程总分");
		viewTAssignmentAllGrades.add(tAssignmentAllGrade);
		for (User user : users) {
			ViewTAssignmentAllGrade viewTAssignmentAllGrade = new ViewTAssignmentAllGrade();
			List<String> scores = new ArrayList<String>();
			BigDecimal decimal = new BigDecimal(0);
			viewTAssignmentAllGrade.setUsername(user.getUsername());
			viewTAssignmentAllGrade.setCname(user.getCname());
				
			for (TAssignment tAssignment : tAssignments) {
				int count = 0;
				for (TAssignmentGrading tAssignmentGrading : tAssignmentGradings) {
					
					if(tAssignmentGrading.getUserByStudent().getUsername().equals(user.getUsername())&&tAssignmentGrading.getTAssignment().getId()==tAssignment.getId()){
						if (tAssignmentGrading.getFinalScore()!=null && count == 0) {
							scores.add(tAssignmentGrading.getFinalScore().toString());
							count++;
						}
						
					}
				}
				if (count==0) {
					scores.add("-");
				}
			}
			for (String string : scores) {
				if(!"-".equals(string)){
					decimal = decimal.add(new BigDecimal(string));
				}
				
			}
			viewTAssignmentAllGrade.setScores(scores);
			scores = new ArrayList<String>();
			viewTAssignmentAllGrade.setTotalGradeForOneUser(decimal.toString());
			viewTAssignmentAllGrades.add(viewTAssignmentAllGrade);
		}
		List<String> avgScores = new ArrayList<String>();
		for (TAssignment tAssignment : tAssignments) {
			int count = 0;
			BigDecimal totalScore = new BigDecimal(0);
			BigDecimal avgScore = new BigDecimal(0);
			for (TAssignmentGrading tAssignmentGrading : tAssignmentGradings) {
				if(tAssignmentGrading.getTAssignment().getId()==tAssignment.getId()){
					if (tAssignmentGrading.getFinalScore()!=null) {
						totalScore = totalScore.add(tAssignmentGrading.getFinalScore());
						count++;
					}
					
				}
			}
			if (count != 0) {
				avgScore = totalScore.divide(new BigDecimal(count),2,BigDecimal.ROUND_HALF_DOWN);
			}
			avgScores.add(avgScore.toString());
		}
		BigDecimal totalAvgScore = new BigDecimal(0);
		for (String string : avgScores) {
			totalAvgScore = totalAvgScore.add(new BigDecimal(string));
		}
		ViewTAssignmentAllGrade assignmentAllGrade = new ViewTAssignmentAllGrade();
		assignmentAllGrade.setCname("总平均分");
		assignmentAllGrade.setScores(avgScores);
		assignmentAllGrade.setTotalGradeForOneUser(totalAvgScore.toString());
		viewTAssignmentAllGrades.add(assignmentAllGrade);
		return viewTAssignmentAllGrades;
	}

	/**
	 * @功能：根据课程站点查询该站点下的学生
	 * @作者： 黄崔俊
	 * @时间：2015-8-25 15:10:28
	 */
	@Override
	public List<User> findUsersByTCourseSiteId(Integer cid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TCourseSiteUser c where c.TCourseSite.id = '"+cid+"'");
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserDAO.executeQuery(sql.toString(), 0, -1);
		String usernames = "(";
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			usernames += tCourseSiteUser.getUser().getUsername()+",";
		}
		usernames = usernames.substring(0, usernames.length()-1)+")";
		sql = new StringBuffer("from User c where c.username in "+usernames+" order by c.username");
		List<User> users = userDAO.executeQuery(sql.toString(), 0, -1);
		return users;
	}

	@Override
	public List<TAssignmentGrading> findTAssignmentGradingList(User nowUser,
			String type) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentGrading c where c.userByStudent.username like '" + nowUser.getUsername() + "' and c.TAssignment.type='exam' and c.submitTime > 0 group by c.TAssignment.id order by c.finalScore desc";
		List<TAssignmentGrading> gradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1); 
		return gradings;
	}
	@Override
	public List<TAssignmentGrading> findTAssignmentGradingList(Integer tCourseSiteId,User nowUser,String type) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentGrading c where c.userByStudent.username like '" + nowUser.getUsername() + "' and c.TAssignment.type='exam' and c.TAssignment.TCourseSite.id='"+tCourseSiteId+"' and c.submitTime > 0 group by c.TAssignment.id order by c.finalScore desc";
		List<TAssignmentGrading> gradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1); 
		return gradings;
	}
	/**********************************************************************************
	 * @功能：下载一份作业
	 * @作者： 裴继超
	 * @时间：2015-9-15
	 **********************************************************************************/
	@Override
	public String downloadFile(TAssignmentGrading tAssignmentGrade, HttpServletRequest request,
			HttpServletResponse response,Integer cid) {
		//返回值
				String result = "success";
				try{	
					String sep = "/";
					String username=tAssignmentGrade.getUserByStudent().getUsername();
					String root = System.getProperty("xidlims.root");
					String url = root+sep+tAssignmentGrade.getGradeUrl();
					String filePath =null;
					String fileName = "";
					if(url.contains(".")){
						filePath = url;
						int fileNameLength = url.lastIndexOf("/");
						fileName = url.substring(fileNameLength, url.length());//文件名
					}else{
						File rootFile = new File(url);
						File[] files = rootFile.listFiles();
						if (files!=null && files.length!=0) {
							for(File file:files){     
								if(file.getName().contains(username)){
									filePath = url+sep+file.getName();
									fileName = file.getName();
								}
									
					   		}
						}
					}
					if (filePath == null) {
						return null;
					}
					//int beginAddress = fileName.lastIndexOf("-");
					int endAddress = fileName.lastIndexOf(".");
					String ss = fileName.substring(endAddress, fileName.length());//后缀名
					fileName = tCourseSiteDAO.findTCourseSiteById(cid).getTitle()+"-"+username+ss;
					
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
					
					//fileName = fileName.replace(fileName.substring(beginAddress, endAddress), "");
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
					result = "false";
				}finally{
					try {
						response.getOutputStream().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return result;
	}
	/**********************************************************************************
	 * @功能：下载一份作业
	 * @作者： 裴继超
	 * @时间：2015-9-28
	 **********************************************************************************/
	@Override
	public String downloadFileForStudent(TAssignmentGrading tAssignmentGrade, HttpServletRequest request,
			HttpServletResponse response,Integer cid) {
		//返回值
				String result = "success";
				try{	
					String sep = "/";
					String username=tAssignmentGrade.getUserByStudent().getUsername();
					String root = System.getProperty("xidlims.root");
					String url = root+sep+tAssignmentGrade.getGradeUrl();
					String filePath =null;
					String fileName = "";
					if(url.contains(".")){
						filePath = url;
						int fileNameLength = url.lastIndexOf("/");
						fileName = url.substring(fileNameLength, url.length());//文件名
					}else{
						File rootFile = new File(url);
						File[] files = rootFile.listFiles();
						if (files!=null && files.length!=0) {
							for(File file:files){     
								if(file.getName().contains(username)){
									filePath = url+sep+file.getName();
									fileName = file.getName();
								}
									
					   		}
						}
					}
					if (filePath == null) {
						return null;
					}
					//int beginAddress = fileName.lastIndexOf("-");
					int endAddress = fileName.lastIndexOf(".");
					String ss = fileName.substring(endAddress, fileName.length());//后缀名
					fileName = tCourseSiteDAO.findTCourseSiteById(cid).getTitle()+"-"+username+ss;
					
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
					
					//fileName = fileName.replace(fileName.substring(beginAddress, endAddress), "");
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
					result = "false";
				}finally{
					try {
						response.getOutputStream().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return result;
	}
	/**********************************************************************************
	 * @功能：根据作业id和登录人获取分数及评语
	 * @作者： 裴继超
	 * @时间：2015-9-24
	 **********************************************************************************/
	@Override
	public TAssignmentGrading findGrandingByAssignmentId(Integer assignmentId){
		String userId=shareService.getUser().getUsername();
		String sql="from TAssignmentGrading c where c.TAssignment.id='"+assignmentId+"' and c.userByStudent.username='"+userId+"' order by c.submitdate desc";
		List<TAssignmentGrading> Grandings=tAssignmentGradingDAO.executeQuery(sql, 0,-1);
		if(Grandings.size()==0||Grandings.get(0).getSubmitTime()==0){
			return null;
		}
		
		TAssignmentGrading Granding=Grandings.get(0);
		
		return Granding;
	}
	/**********************************************************************************
	 * @功能：根据作业id和登录人获取分数及评语
	 * @作者： 裴继超
	 * @时间：2015-9-25
	 **********************************************************************************/
	@Override
	public int findSubmitTimeByAssignmentId(Integer assignmentId){
		String userId=shareService.getUser().getUsername();
		String sql="from TAssignmentGrading c where c.TAssignment.id='"+assignmentId+"' and c.userByStudent.username='"+userId+"' and c.submitTime=1 order by c.submitdate desc";
		List<TAssignmentGrading> Grandings=tAssignmentGradingDAO.executeQuery(sql, 0,-1);
		
		int maxtime=tAssignmentDAO.findTAssignmentById(assignmentId).getTAssignmentControl().getTimelimit();
		
		int submitTime=-1;
		if(Grandings.size()==0){
			 submitTime=0;
			
		
			
		}
		if(Grandings.size()==maxtime){
			 submitTime=maxtime;
			
		}
		return submitTime;
		
	}
	/**
	 * @功能：学生查看已提交次数
	 * @作者： 裴继超
	 * @时间： 2015-9-28
	 */
	@Override
	public int findTAssignmentGradingTimes(Integer assignmentId) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignmentGrading c where c.TAssignment.id = '"+assignmentId+"' and c.submitTime = '1'");
		User user=shareService.getUser();
			sql.append(" and c.userByStudent.username = '"+user.getUsername()+"'");
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql.toString(), 0, -1);
		
		return tAssignmentGradings.size();
	}

	@Override
	public List<TAssignmentGrading> findExamGradingList(Integer examId,
			Integer flag, User user) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TAssignmentGrading c where c.TAssignment.id = '"+examId+"' and c.submitTime > '0'");
		if(flag==0){//学生，查看历史提交记录
			sql.append(" and c.userByStudent.username = '"+user.getUsername()+"'");
		}
		if(flag==1){//普通教师
			sql.append(" and c.TAssignment.user.username = '"+ user.getUsername() +"'");
		}
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql.toString(), 0, -1);
		List<TAssignmentGrading> tAssignmentGradingTemp = new ArrayList<TAssignmentGrading>();
		/*for (TAssignmentGrading tAssignmentGrading : tAssignmentGradings) {
			if (tAssignmentGrading.getUserByStudent().getAuthorities().toString().contains("TEACHER")) {
				tAssignmentGradingTemp.add(tAssignmentGrading);
			}
		}*/
		tAssignmentGradings.removeAll(tAssignmentGradingTemp);
		return tAssignmentGradings;
	}
	
	@Override
	public TAssignmentGrading findTAssignmentGradingByExamAndUser(
			Integer examId, User nowUser) {
		// TODO Auto-generated method stub
		String sql = "from TAssignmentGrading c where c.userByStudent.username = '"+nowUser.getUsername()+"' and c.TAssignment.id = '"+examId+"'";
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGradingDAO.executeQuery(sql, 0, -1);
		return tAssignmentGradings.get(0);
	}
	
	/**
	 * @功能： 自定义成绩时保存学生提交的作业
	 * @作者： 张佳鸣
	 * @时间： 2017-9-29
	 */
	public void saveTAssignmentGrading1(TAssignmentGrading tAssignmentGrade) {
		
			tAssignmentGradingDAO.store(tAssignmentGrade);
			tAssignmentGradingDAO.flush();
			
	}
	
	/**
	 * @功能： 老师批改试验项目的作业“驳回”功能设置更新实验成绩数据
	 * @作者： 张佳鸣
	 * @时间： 2017-11-28
	 */
	public void updateTAssignmentGrading(Integer assignGradeId,User nowUser,Calendar calendar) {
		//根据assignGradeId查找对应的tAssignmentGrading
		TAssignmentGrading tAssignmentGrading = tAssignmentGradingDAO.findTAssignmentGradingByAccessmentgradingId(assignGradeId);
		//设置tAssignmentGrading的被驳回人为当前登入人，操作时间为当前时间
		tAssignmentGrading.setUserByGradeBy(nowUser);
		tAssignmentGrading.setGradeTime(calendar);
		//设置tAssignmentGrading的"是否迟交"字段数值为2，代表当前tAssignmentGrading被驳回
		tAssignmentGrading.setIslate(2);
		//保存，刷新
	    tAssignmentGradingDAO.store(tAssignmentGrading);
	    tAssignmentGradingDAO.flush();
	}
	
}
