package net.xidlims.web.lab;

import java.net.BindException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.LabConstructionProjectAuditDAO;
import net.xidlims.dao.MLabConstructionProjectUserDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.LabConstructionProjectAudit;
import net.xidlims.domain.MLabConstructionProjectUser;
import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabConstructionProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.inject.servlet.RequestParameters;

import edu.emory.mathcs.backport.java.util.Collections;


/**
 * Spring MVC controller that handles CRUD requests for LabConstructionProject entities
 * 
 */

@Controller("LabConstructionProjectController")
public class LabConstructionProjectController {
	
	
	@Autowired
	private LabConstructionProjectService labConstructionProjectService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MLabConstructionProjectUserDAO mLabConstructionProjectUserDAO;
	@Autowired
	private CDictionaryDAO cDictionaryDAO;
	@Autowired 
	private LabConstructionProjectAuditDAO labConstructionProjectAuditDAO;
	@Autowired 
	private MessageDAO messageDAO;
	@Autowired 
	private CommonDocumentService commonDocumentService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register // static // property // editors.
		binder.registerCustomEditor(java.util.Calendar.class,new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	
	
	
	/***********************************
	 * 功能：分页列出满足查询条件的所有实验室建设项目立项
	 * 作者：贺子龙
	 * 日期：2015-10-01
	 ***********************************/
	
	@RequestMapping("/labconstruction/listLabConstructionProjects")
	public ModelAndView listLabConstructionProjects(@RequestParam int currpage, int status, @ModelAttribute LabConstructionProject labConstructionProject) {
		ModelAndView mav = new ModelAndView();
		//当前登录人
		User user=shareService.getUser();
		
		//判断当前登陆人是否为项目立项的审核人或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		boolean isNonCollegeAuditor;//是否为非本学院审核人或超级管理员
		if(judge.indexOf(",11,")>-1||judge.indexOf(",14,")>-1||judge.indexOf(",15,")>-1){
			isNonCollegeAuditor = true;
		}else isNonCollegeAuditor = false;
		mav.addObject("isNonCollegeAuditor", isNonCollegeAuditor);
		
		boolean isCollegeAuditor;//是否为本学院审核人
		if (judge.indexOf(",13,")>-1||judge.indexOf(",4,")>-1) {//13 学院主管领导  4 实验室中心主任
			isCollegeAuditor = true;
		}else isCollegeAuditor = false;
		mav.addObject("isCollegeAuditor", isCollegeAuditor);
		
		if (status!=0) {//status==0时，显示全部状态的项目立项
			labConstructionProject.setAuditResults(status);
		}
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//项目立项总记录数
//		int totalRecords = labConstructionProjectService.findAllLabConstructionProjectsByQueryCount(labConstructionProject);
		//分页列出符合条件的项目立项记录
		List <LabConstructionProject> listLabConstructionProjects=labConstructionProjectService.findAllLabConstructionProjectsByQuery(currpage, pageSize, labConstructionProject);
		//不分页，全部
		List <LabConstructionProject> allLabConstructionProjects=labConstructionProjectService.findAllLabConstructionProjectsByQuery(labConstructionProject);
		//项目立项总记录数（查询出来的allLabConstructionProjects中与当前登陆人相关的项目立项记录数）
		int totalRecords=0;
		String username="["+user.getUsername()+"]";//防止其他人的工号包含当前登陆人
		mav.addObject("username", username);//将[工号](如：[03010])传到后台，使fn:contains更精确
		for (LabConstructionProject project : allLabConstructionProjects) {
			Set<MLabConstructionProjectUser> projectMambers=project.getMLabConstructionProjectUsers();
			
			if (isNonCollegeAuditor) {//如果是非本学院审核人，可以看到所有立项
				totalRecords++;continue;
			}
			else if (isCollegeAuditor) {//如果是本学院审核人，只能看到本学院的立项
				if (project.getSchoolAcademy()!=null&&project.getSchoolAcademy().getAcademyNumber()==user.getSchoolAcademy().getAcademyNumber()) {
					totalRecords++;continue;
				}
			}
			//如果不是审核人，则只能看到与自己相关的项目立项
			else if (projectMambers.toString().indexOf(username)!=-1||project.getUser().getUsername()==user.getUsername()||
					project.getUserByCreatedBy().getUsername()==user.getUsername()) {
				
				totalRecords++;continue;
			}
		}
		
		
		//查出所有项目立项记录的项目负责人
		Map<String, String> users= labConstructionProjectService.findAllProjectManagers();
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("deanWorkers", deanWorkers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("superAdmins", superAdmins);
		mav.addObject("listLabConstructionProjects", listLabConstructionProjects);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("status", status);
		
		mav.addObject("users",users);
		mav.addObject("user",user);
		mav.setViewName("labconstruction/listLabConstructionProjects.jsp");
		return mav;
	}
	
	
	/********************************
	 * 功能：新建项目立项
	 * 作者：贺子龙
	 * 日期：2015-10-02
	 *********************************/
	@RequestMapping("/labconstruction/newLabConstructionProject")
	public ModelAndView newLabConstructionProject(@RequestParam int status){
		ModelAndView mav = new ModelAndView();
		//找到所有的学院
		List<SchoolAcademy> schoolAcademies = shareService.findAllSchoolAcademys();
		//找到所有教师的数据
		List<User> teachers = shareService.findAllTeacheres();
		mav.addObject("labConstructionProject", new LabConstructionProject());
		mav.addObject("schoolAcademies", schoolAcademies);
		mav.addObject("teachers", teachers);
		mav.addObject("isEdit", false);
		mav.addObject("status", status);
		
		//当前登陆人
		User user=shareService.getUser();
		mav.addObject("user", user);
		
		mav.setViewName("labconstruction/editLabConstructionProject.jsp");
		return mav;
	}
	
	
	/********************************
	 * 功能：编辑项目立项
	 * 作者：贺子龙
	 * 日期：2015-10-02
	 *********************************/
	@RequestMapping("/labconstruction/editLabConstructionProject")
	public ModelAndView editLabConstructionProject(@RequestParam int labConstructionProjectId,  int status){
		ModelAndView mav = new ModelAndView();
		//找到所有的学院
		List<SchoolAcademy> schoolAcademies = shareService.findAllSchoolAcademys();
		//找到所有教师的数据
		List<User> teachers = shareService.findAllTeacheres();
		LabConstructionProject labConstructionProject = labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		mav.addObject("schoolAcademies", schoolAcademies);
		mav.addObject("teachers", teachers);
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("projectMember",new MLabConstructionProjectUser());
		mav.addObject("projectMembers",labConstructionProject.getMLabConstructionProjectUsers());
		mav.addObject("labConstructionProjectId", labConstructionProjectId);
		mav.addObject("isEdit", true);
		mav.addObject("status", status);
		mav.setViewName("labconstruction/editLabConstructionProject.jsp");
		return mav;
	}
	

	/********************************
	 * 功能：保存项目立项
	 * 作者：贺子龙
	 * 日期：2015-10-02
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionProject")
	public String saveLabConstructionProject(@ModelAttribute LabConstructionProject labConstructionProject,@RequestParameters int status){
		labConstructionProject.setAuditResults(1);//草稿状态
		labConstructionProject.setStage(0);//未审核阶段
		User user=new User();
		//获取当前用户
		user=shareService.getUser();
		labConstructionProject.setUserByCreatedBy(user);
		labConstructionProject = labConstructionProjectService.saveLabConstructionProject(labConstructionProject);
		return "redirect:/labconstruction/editLabConstructionProject?labConstructionProjectId="+labConstructionProject.getId()+"&status="+status;
		
	}
	
	

	/********************************
	 * 功能：保存项目立项2-跳转页面
	 * 作者：贺子龙
	 * 日期：2015-10-02
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionProjectAll")
	public String saveLabConstructionProjectAll(@RequestParameters int status){
		
		return "redirect:/labconstruction/listLabConstructionProjects?currpage=1&status="+status;
	}
	
	/****************************************************************************
	 * 功能：AJAX 根据姓名、工号查询当前登录人所在学院的用户
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/findUserByCnameAndUsername")
	public @ResponseBody String findUserByCnameAndUsername(@RequestParam String cname,String username,Integer labConstructionProjectId,Integer page) {
		User user=new User();
		user.setCname(cname);
		user.setUsername(username);
		
		//分页开始
		int totalRecords=labConstructionProjectService.findUserByCnameAndUsernameCount(user,labConstructionProjectId);
		int pageSize=20;
		Map<String,Integer> pageModel =shareService.getPage(page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<User> userList=labConstructionProjectService.findUserByCnameAndUsername(user,labConstructionProjectId,page,pageSize);
	    String s="";
	    for (User d : userList) {
	    	String academy="";
	    	if(d.getSchoolAcademy()!=null){
	    		academy=d.getSchoolAcademy().getAcademyName();
	    	}
			s+="<tr>"+
			"<td><input type='checkbox' name='CK_name' value='"+d.getUsername()+"'/></td>"+
	    	"<td>"+d.getCname()+"</td>"+
			"<td>"+d.getUsername()+"</td>"+
			"<td>"+academy+"</td>"+
			
			"</tr>";			
		}
	    s+="<tr><td colspan='6'>"+
	    	    "<a href='javascript:void(0)' onclick='firstPage(1);'>"+"首页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='previousPage("+page+");'>"+"上一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='nextPage("+page+","+pageModel.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
	    	    "<a href='javascript:void(0)' onclick='lastPage("+pageModel.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
	    	    "当前第"+page+"页&nbsp; 共"+pageModel.get("totalPage")+"页  "+totalRecords+"条记录"+
	    	    		"</td></tr>";
		return htmlEncode(s);
	}
	
	/****************************************************************************
	 * 功能：保存项目成员
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveProjectMember")
	public ModelAndView saveProjectMember(@RequestParam Integer labConstructionProjectId,String[] array, int status) {
		ModelAndView mav=new ModelAndView();
		//roomId对应的实验分室
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		for (String i : array) {
	  		//username对应的用户
			User u=userDAO.findUserByPrimaryKey(i);
			MLabConstructionProjectUser projectMember=new MLabConstructionProjectUser();
			projectMember.setLabConstructionProject(labConstructionProject);
			projectMember.setUser(u);
			mLabConstructionProjectUserDAO.store(projectMember);
			userDAO.store(u);
	  	}		
		mav.setViewName("redirect:/labconstruction/editLabConstructionProject?labConstructionProjectId="+labConstructionProjectId+"&status="+status);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：删除项目成员
	 * 作者：贺子龙
	 * 时间：2015-09-15
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deleteProjectMember")
	public ModelAndView deleteProjectMember(@RequestParam Integer id,int status){
		ModelAndView mav=new ModelAndView();
		//id对应的实验室物联管理员
		MLabConstructionProjectUser projectMember=mLabConstructionProjectUserDAO.findMLabConstructionProjectUserByPrimaryKey(id);
		mLabConstructionProjectUserDAO.remove(projectMember);
		mav.setViewName("redirect:/labconstruction/editLabConstructionProject?labConstructionProjectId="+projectMember.getLabConstructionProject().getId()+"&status="+status);
		return mav;
	}
	

	/****************************************************************************
	 * 功能：提交项目立项
	 * 作者：贺子龙
	 * 时间：2015-10-03
	 ****************************************************************************/
	@RequestMapping("/labconstruction/submitLabConstructionProject")
	public String submitLabConstructionProject(@RequestParam Integer labConstructionProjectId){
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		labConstructionProject.setAuditResults(2);
		labConstructionProject.setStage(0);
		labConstructionProjectService.saveLabConstructionProject(labConstructionProject);
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		
		
		for (User collegeLeader : collegeLeaders) {//提交完成后向学院主管领导（一级审核）发送消息
			if (collegeLeader.getUsername().equals(labConstructionProject.getUserByCreatedBy().getUsername())) {//如果项目申报人是学院主管领导，则不发给自己
				
			}else {
				Message message= new Message();
				message.setSendUser(labConstructionProject.getUserByCreatedBy().getCname());//项目申报人
				message.setSendCparty(labConstructionProject.getUserByCreatedBy().getSchoolAcademy().getAcademyName());//项目申报人所在学院
				message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_TITLE);
				String content="申请成功，等待审核";
				content+="<a  href='/xidlims/labconstruction/collegeProjectAudit?labConstructionProjectId=";
				content+=labConstructionProject.getId();
				content+="'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(Calendar.getInstance());
				message.setUsername(collegeLeader.getUsername());
				message=messageDAO.store(message);
			}
			
		}
		return "redirect:/labconstruction/listLabConstructionProjects?currpage=1&status=0";
	}
	
	/****************************************************************************
	 * 功能：查看项目立项
	 * 作者：贺子龙
	 * 时间：2015-10-03
	 ****************************************************************************/
	
	@RequestMapping("/labconstruction/veiwLabConstructionProject")
	public ModelAndView veiwLabConstructionProject(@RequestParameters Integer labConstructionProjectId,int status){
		ModelAndView mav=new ModelAndView();
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		mav.addObject("projectMembers",labConstructionProject.getMLabConstructionProjectUsers());
		mav.addObject("labConstructionProject", labConstructionProject);
		mav.addObject("status", status);
		mav.setViewName("/labconstruction/veiwLabConstructionProject.jsp");
		return mav;
	}
	/****************************************************************************
	 * 功能：删除项目立项
	 * 作者：贺子龙
	 * 时间：2015-10-03
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deleteLabConstructionProject")
	public String deleteLabConstructionProject(@RequestParameters Integer labConstructionProjectId,int status){
		labConstructionProjectService.deleteLabConstructionProject(labConstructionProjectId);
		return "redirect:/labconstruction/listLabConstructionProjects?currpage=1&status="+status;
	}
	
	/****************************************************************************
	 * 功能：处理ajax中文乱码
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	public static String htmlEncode(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			if (c > 127 && c != 160) {
				sb.append("&#").append(c).append(";");
			} else {
				sb.append((char) c);
			}
		}
		return sb.toString();
	}
	/****************************************************************************
	 * 功能：学院主管领导审核(一级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/collegeProjectAudit")
	public ModelAndView  collegeProjectAudit(@RequestParam Integer labConstructionProjectId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		mav.addObject("labConstructionProject", labConstructionProject);
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_project_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("collegeProjectAudit", new LabConstructionProjectAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("deanWorkers", deanWorkers);
		mav.addObject("schoolLeaders", schoolLeaders);
		
		//找到labConstructionProject对应的审核结果（tag=1代表是学院主管领导的审核结果）
		Set<LabConstructionProjectAudit> audits=labConstructionProject.getLabConstructionProjectAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId = new ArrayList<Integer>();
		for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
			if (labConstructionProjectAudit.getTag()==1) {
				auditsListId.add(labConstructionProjectAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		Collections.sort(auditsListId);//将auditsListId从小到大排序
		int auditId;
		if (auditsListId.size()>0) {
			auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionProjectAudit collegeProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId);
			mav.addObject("collegeProjectAuditDone", collegeProjectAuditDone);
		}
		
		/*if (auditsList.size()>0) {
			for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
				if (labConstructionProjectAudit.getTag()==1) {//确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionProjectAudit collegeProjectAuditDone=labConstructionProjectAudit;
					mav.addObject("collegeProjectAuditDone", collegeProjectAuditDone);
				}
			}
		}
		*/
		mav.setViewName("/labconstruction/collegeProjectAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存学院主管领导审核结果(一级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveCollegeProjectAudit")
	public ModelAndView saveCollegeProjectAudit(@ModelAttribute LabConstructionProjectAudit collegeProjectAudit,@RequestParam int labConstructionProjectId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		collegeProjectAudit.setLabConstructionProject(labConstructionProject);
		collegeProjectAudit.setTag(1);//学院主管领导审核标志位
		//当前登录人
		User user=shareService.getUser();
		collegeProjectAudit.setUser(user);
		
		//给审核意见之后加上审核人信息
		String comments=collegeProjectAudit.getComments();
		comments+="        审核人姓名："+user.getCname()+"      审核人工号："+user.getUsername();
		collegeProjectAudit.setComments(comments);
		labConstructionProjectAuditDAO.store(collegeProjectAudit);
		int audit_return=collegeProjectAudit.getCDictionary().getId();
		if (audit_return==603) {//审核通过
			labConstructionProject.setAuditResults(2);//审核中状态
			labConstructionProject.setStage(1);//学院主管领导审核阶段结束
			//完成后向教务处（二级审核）发送消息，消息发送人为项目负责人
			
			//根据权限id查询用户
			List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
			List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
			List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
			
			
			for (User deanWorker : deanWorkers) {//提交完成后向教务处领导（二级审核）发送消息
				if (deanWorker.getUsername().equals(shareService.getUserDetail().getUsername())) {//如果当前登陆人是教务处工作人员，则不发给自己
					
				}else {
					Message message= new Message();
					message.setSendUser(shareService.getUserDetail().getCname());
					message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
					message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_SUCCESS_ONE);
					String content="学院主管领导审核通过，请教务处审核";
					content+="<a  href='/xidlims/labconstruction/deanProjectAudit?labConstructionProjectId=";
					content+=labConstructionProject.getId();
					content+="'>点击查看</a>";
					message.setContent(content);
					message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
					message.setCreateTime(Calendar.getInstance());
					message.setUsername(deanWorker.getUsername());
					message=messageDAO.store(message);
				}
				
			}
			
			
			//同时，审核人要给申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_SUCCESS_ONE);
			String content="学院主管领导审核通过，等待教务处审核";
			content+="<a  href='/xidlims/labconstruction/collegeProjectAudit?labConstructionProjectId=";
			content+=labConstructionProject.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionProject.getUserByCreatedBy().getUsername());
			message=messageDAO.store(message);
			
		}
		if (audit_return==604) {//审核拒绝
			labConstructionProject.setAuditResults(4);//审核拒绝状态
			labConstructionProject.setStage(-1);//审核拒绝后，stage值被置为-1
			
			//审核人给申请人发送审核失败的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_FAIL_ONE);
			String content="未通过学院主管领导审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/collegeProjectAudit?labConstructionProjectId=";
			content+=labConstructionProject.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionProject.getUserByCreatedBy().getUsername());
			message=messageDAO.store(message);
			
		}
		labConstructionProjectService.saveLabConstructionProject(labConstructionProject);
		mav.setViewName("redirect:/labconstruction/listLabConstructionProjects?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：教务处审核(二级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deanProjectAudit")
	public ModelAndView  deanProjectAudit(@RequestParam Integer labConstructionProjectId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		mav.addObject("labConstructionProject", labConstructionProject);
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_project_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("deanProjectAudit", new LabConstructionProjectAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("deanWorkers", deanWorkers);
		mav.addObject("schoolLeaders", schoolLeaders);
		
		//找到labConstructionProject对应的审核结果（tag=2代表是教务处的审核结果）
		Set<LabConstructionProjectAudit> audits=labConstructionProject.getLabConstructionProjectAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
				List<Integer> auditsListId = new ArrayList<Integer>();
				for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
					if (labConstructionProjectAudit.getTag()==2) {
						auditsListId.add(labConstructionProjectAudit.getId());//将tag条件的id加入到auditsListId中
					}
				}
				Collections.sort(auditsListId);//将auditsListId从小到大排序
				int auditId;
				if (auditsListId.size()>0) {
					auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionProjectAudit deanProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId);
					mav.addObject("deanProjectAuditDone", deanProjectAuditDone);
				}
				
		
		/*if (audits.size()>0) {
			
			for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
				if (labConstructionProjectAudit.getTag()==2) {//确保是最后一次教务处审核（针对打回去的情况）
					LabConstructionProjectAudit deanProjectAuditDone=labConstructionProjectAudit;
					mav.addObject("deanProjectAuditDone", deanProjectAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/deanProjectAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存教务处审核(二级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveDeanProjectAudit")
	public ModelAndView savDeanProjectAudit(@ModelAttribute LabConstructionProjectAudit deanProjectAudit,@RequestParam int labConstructionProjectId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		deanProjectAudit.setLabConstructionProject(labConstructionProject);
		deanProjectAudit.setTag(2);//教务处审核标志位
		//当前登录人
		User user=shareService.getUser();
		deanProjectAudit.setUser(user);
		labConstructionProjectAuditDAO.store(deanProjectAudit);
		int audit_return=deanProjectAudit.getCDictionary().getId();
		if (audit_return==603) {//审核通过
			labConstructionProject.setAuditResults(2);//审核中状态
			labConstructionProject.setStage(2);//教务处审核阶段结束
			
			//根据权限id查询用户
			List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
			List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
			List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
			
			
			for (User schoolLeader : schoolLeaders) {//提交完成后向校领导（三级审核）发送消息
				if (schoolLeader.getUsername().equals(shareService.getUserDetail().getUsername())) {//如果当前登陆人是校领导，则不发给自己
					
				}else {
					Message message= new Message();
					message.setSendUser(shareService.getUserDetail().getCname());
					message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
					message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_SUCCESS_TWO);
					String content="教务处审核通过，请校领导审核";
					content+="<a  href='/xidlims/labconstruction/schoolProjectAudit?labConstructionProjectId=";
					content+=labConstructionProject.getId();
					content+="'>点击查看</a>";
					message.setContent(content);
					message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
					message.setCreateTime(Calendar.getInstance());
					message.setUsername(schoolLeader.getUsername());
					message=messageDAO.store(message);
				}
				
			}
			
			
			//同时，审核人要给申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_SUCCESS_TWO);
			String content="教务处审核通过，等待校领导审核";
			content+="<a  href='/xidlims/labconstruction/deanProjectAudit?labConstructionProjectId=";
			content+=labConstructionProject.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionProject.getUserByCreatedBy().getUsername());;
			message=messageDAO.store(message);
		}
		if (audit_return==604) {//审核拒绝
			labConstructionProject.setAuditResults(4);//审核拒绝状态
			labConstructionProject.setStage(-1);//审核拒绝后，stage值被置为-1
			
			//同时，审核人要给申请人发送审核失败的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_FAIL_TWO);
			String content="未通过教务处审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/deanProjectAudit?labConstructionProjectId=";
			content+=labConstructionProject.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionProject.getUserByCreatedBy().getUsername());;
			message=messageDAO.store(message);
		}
		labConstructionProjectService.saveLabConstructionProject(labConstructionProject);
		mav.setViewName("redirect:/labconstruction/listLabConstructionProjects?currpage=1&status=0");
		return mav;
	}
	
	
	
	/****************************************************************************
	 * 功能：校领导审核(三级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/schoolProjectAudit")
	public ModelAndView  schoolProjectAudit(@RequestParam Integer labConstructionProjectId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		mav.addObject("labConstructionProject", labConstructionProject);
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_project_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("schoolProjectAudit", new LabConstructionProjectAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> deanWorkers=shareService.findUsersByAuthorityId(14);//教务处领导
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("deanWorkers", deanWorkers);
		mav.addObject("schoolLeaders", schoolLeaders);
		
		//找到labConstructionProject对应的审核结果（tag=3代表是校领导的审核结果）
		Set<LabConstructionProjectAudit> audits=labConstructionProject.getLabConstructionProjectAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId = new ArrayList<Integer>();
		for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
			if (labConstructionProjectAudit.getTag()==3) {
				auditsListId.add(labConstructionProjectAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		Collections.sort(auditsListId);//将auditsListId从小到大排序
		int auditId;
		if (auditsListId.size()>0) {
			auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionProjectAudit schoolProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId);
			mav.addObject("schoolProjectAuditDone", schoolProjectAuditDone);
		}
		
		
		
		/*if (audits.size()>0) {
			
			for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
				if (labConstructionProjectAudit.getTag()==3) {//确保是最后一次校领导审核（针对打回去的情况）
					LabConstructionProjectAudit schoolProjectAuditDone=labConstructionProjectAudit;
					mav.addObject("schoolProjectAuditDone", schoolProjectAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/schoolProjectAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：校领导审核(三级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveSchoolProjectAudit")
	public ModelAndView saveSchoolProjectAudit(@ModelAttribute LabConstructionProjectAudit schoolProjectAudit,@RequestParam int labConstructionProjectId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		schoolProjectAudit.setLabConstructionProject(labConstructionProject);
		schoolProjectAudit.setTag(3);//校领导审核标志位
		//当前登录人
		User user=shareService.getUser();
		schoolProjectAudit.setUser(user);
		labConstructionProjectAuditDAO.store(schoolProjectAudit);
		int audit_return=schoolProjectAudit.getCDictionary().getId();
		if (audit_return==603) {//审核通过
			labConstructionProject.setAuditResults(3);//审核通过状态
			labConstructionProject.setStage(3);//校领导审核阶段结束
			
			//审核人要给申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_SUCCESS_THREE);
			String content="校领导审核通过，请查看审核结果";
			content+="<a  href='/xidlims/labconstruction/schoolProjectAudit?labConstructionProjectId=";
			content+=labConstructionProject.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionProject.getUserByCreatedBy().getUsername());;
			message=messageDAO.store(message);
		
			
		}
		if (audit_return==604) {//审核拒绝
			labConstructionProject.setAuditResults(4);//审核拒绝状态
			labConstructionProject.setStage(-1);//审核拒绝后，stage值被置为-1
			
			
			//审核人要给申请人发送审核失败的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPROJECT_FAIL_THREE);
			String content="未通过校领导审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/schoolProjectAudit?labConstructionProjectId=";
			content+=labConstructionProject.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionProject.getUserByCreatedBy().getUsername());;
			message=messageDAO.store(message);
		}
		labConstructionProjectService.saveLabConstructionProject(labConstructionProject);
		mav.setViewName("redirect:/labconstruction/listLabConstructionProjects?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：给项目立项上传附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@RequestMapping("/labconstruction/projectDocumentUpload")
	public @ResponseBody String projectDocumentUpload(HttpServletRequest request, HttpServletResponse response, 
			BindException errors,Integer id) throws Exception {
		labConstructionProjectService.projectDocumentUpload(request, response,id,1);
		return "ok";
	}
	
	/****************************************************************************
	 * 功能：删除项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deleteProjectDocument")
	public ModelAndView deleteProjectDocument(@RequestParam Integer id, int status){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目附件
		CommonDocument doc=commonDocumentService.findCommonDocumentByPrimaryKey(id);
		//附件所对应的项目立项
		LabConstructionProject labConstructionProject=doc.getLabConstructionProject();
		commonDocumentService.deleteCommonDocument(doc);
		//删除服务器上的文件
		mav.setViewName("redirect:/labconstruction/editLabConstructionProject?labConstructionProjectId="+labConstructionProject.getId()+"&status="+status);
		
		return mav;
	}
	
	/****************************************************************************
	 * 功能：下载项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@RequestMapping("/labconstruction/downloadProjectDocument")
	public @ResponseBody String downloadProjectDocument(HttpServletRequest request, HttpServletResponse response,Integer id) throws Exception {
		labConstructionProjectService.downloadDocument(request, response,id);
		return "ok";
	}
	
	/****************************************************************************
	 * 功能：根据项目立项id查询该项目立项的审核进度
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/viewProjectStage")
	public @ResponseBody String viewProjectStage(@RequestParam Integer labConstructionProjectId) {
		LabConstructionProject labConstructionProject=labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		
		//查看项目立项对应的审核意见
		Set<LabConstructionProjectAudit> audits=labConstructionProject.getLabConstructionProjectAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId1 = new ArrayList<Integer>();
		List<Integer> auditsListId2 = new ArrayList<Integer>();
		List<Integer> auditsListId3 = new ArrayList<Integer>();
		for (LabConstructionProjectAudit labConstructionProjectAudit : audits) {
			if (labConstructionProjectAudit.getTag()==1) {
				auditsListId1.add(labConstructionProjectAudit.getId());//将tag条件的id加入到auditsListId中
			}
			
			if (labConstructionProjectAudit.getTag()==2) {
				auditsListId2.add(labConstructionProjectAudit.getId());//将tag条件的id加入到auditsListId中
			}
			
			if (labConstructionProjectAudit.getTag()==3) {
				auditsListId3.add(labConstructionProjectAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		
		
		String s="";
		s+="<p>项目立项审核进度</p><br>";
		
		if (auditsListId3.size()>0 && labConstructionProject.getStage()==3) {
			Collections.sort(auditsListId3);//将auditsListId3从小到大排序
			int auditId3=auditsListId3.get(auditsListId3.size()-1);//取最后一个，确保是最后一次校领导审核（针对打回去的情况）
			LabConstructionProjectAudit schoolProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId3);
			s+=
					
					"<p>  校领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp;审核意见:"+schoolProjectAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+schoolProjectAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次教务处领导审核（针对打回去的情况）
			LabConstructionProjectAudit deanProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId2);
			s+=
					
					"<p>  教务处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+deanProjectAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+deanProjectAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionProjectAudit collegeProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId1);
			s+=
					
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegeProjectAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegeProjectAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
		}
			
		if (auditsListId2.size()>0 && labConstructionProject.getStage()==2) {
			s+="<p>  校领导未审核 </p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次教务处领导审核（针对打回去的情况）
			LabConstructionProjectAudit deanProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId2);
			s+=
					
					"<p>  教务处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+deanProjectAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+deanProjectAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionProjectAudit collegeProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId1);
			s+=
					
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegeProjectAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegeProjectAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			}
				
		if (auditsListId1.size()>0 && labConstructionProject.getStage()==1) {
			s+="<p>  校领导未审核 </p>";
			s+="<p>  教务处未审核 </p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionProjectAudit collegeProjectAuditDone=labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(auditId1);
			s+=
					
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegeProjectAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegeProjectAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
		}
		if (labConstructionProject.getStage()==0) {
			s+="<p>  校领导未审核 </p>";
			s+="<p>  教务处未审核 </p>";			
			s+="<p>  学院主管领导未审核 </p>";
		}
					
					
		return htmlEncode(s);
	}
	
	
}