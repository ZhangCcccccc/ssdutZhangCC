package net.xidlims.web.lab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.LabConstructionPurchaseAuditDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabConstructionDevice;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.LabConstructionProjectAudit;
import net.xidlims.domain.LabConstructionPurchase;
import net.xidlims.domain.LabConstructionPurchaseAudit;
import net.xidlims.domain.MLabConstructionProjectUser;
import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabConstructionProjectService;
import net.xidlims.service.lab.LabConstructionPurchaseService;

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
 * Spring MVC controller that handles CRUD requests for LabConstructionPurchase entities
 * 
 */

@Controller("LabConstructionPurchaseController")
public class LabConstructionPurchaseController {
	@Autowired ShareService shareService;
	@Autowired LabConstructionProjectService labConstructionProjectService;
	@Autowired LabConstructionPurchaseService labConstructionPurchaseService;
	@Autowired CDictionaryDAO cDictionaryDAO;
	@Autowired LabConstructionPurchaseAuditDAO labConstructionPurchaseAuditDAO;
	@Autowired MessageDAO messageDAO;
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
	
	
	/********************************
	 * 功能：新建项目申购
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/newLabConstructionPurchase")
	public ModelAndView newLabConstructionPurchase(@RequestParam int labConstructionProjectId,int status){
		ModelAndView mav = new ModelAndView();
		//当前登录用户
		User user =shareService.getUser();
		mav.addObject("user", user);
		//找到所有的学院
		List<SchoolAcademy> schoolAcademies = shareService.findAllSchoolAcademys();
		//找到所有教师的数据
		List<User> teachers = shareService.findAllTeacheres();
		LabConstructionProject labConstructionProject = labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		
		mav.addObject("labConstructionPurchase", new LabConstructionPurchase());
		mav.addObject("schoolAcademies", schoolAcademies);
		mav.addObject("teachers", teachers);
		mav.addObject("purchaseTypes", cDictionaryDAO.findCDictionaryByCCategory("c_lab_construction_purchase_type"));
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("labConstructionProjectId",labConstructionProjectId);
		mav.addObject("isEdit", false);
		mav.addObject("status", status);
		mav.setViewName("labconstruction/editLabConstructionPurchase.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：编辑项目申购
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/editLabConstructionPurchase")
	public ModelAndView editLabConstructionPurchase(@RequestParam int labConstructionPurchaseId,int status){
		ModelAndView mav = new ModelAndView();
		//找到所有的学院
		List<SchoolAcademy> schoolAcademies = shareService.findAllSchoolAcademys();
		//通过主键找到当前的labConstructionPurchase
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		//找到所有教师的数据
		List<User> teachers = shareService.findAllTeacheres();
		//获得当前项目建设对应的项目立项
		LabConstructionProject labConstructionProject = labConstructionPurchase.getLabConstructionProject();
		//获得项目立项所对应的tag为1的设备
		List<LabConstructionDevice> labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject,1);
		
		mav.addObject("labConstructionDevices", labConstructionDevices);
		mav.addObject("labConstructionDevice", new LabConstructionDevice());
		mav.addObject("labConstructionPurchase", labConstructionPurchase);
		mav.addObject("schoolAcademies", schoolAcademies);
		mav.addObject("teachers", teachers);
		mav.addObject("purchaseTypes", cDictionaryDAO.findCDictionaryByCCategory("c_lab_construction_purchase_type"));
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("isEdit", true);
		mav.addObject("status", status);
		mav.setViewName("labconstruction/editLabConstructionPurchase.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：保存项目申购
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionPurchase")
	public String saveLabConstructionPurchase(@ModelAttribute LabConstructionPurchase labConstructionPurchase,@RequestParam int labConstructionProjectId,int status){
		labConstructionPurchase.setAuditResults(1);//草稿状态
		labConstructionPurchase.setStage(0);//未审核阶段
		LabConstructionProject labConstructionProject = labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		labConstructionProject.setStage(5);//用stage来限制每个项目立项只能生成一个项目建设。
		labConstructionProjectService.saveLabConstructionProject(labConstructionProject);//保存项目立项
		labConstructionPurchase.setLabConstructionProject(labConstructionProject);
		labConstructionPurchase = labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		return "redirect:/labconstruction/editLabConstructionPurchase?labConstructionPurchaseId="+labConstructionPurchase.getId()+"&status="+status;
	}
	
	/********************************
	 * 功能：保存项目申购2-跳转页面
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionPurchaseAll")
	public String saveLabConstructionProjectAll(@RequestParameters int status){
		
		return "redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status="+status;
	}
	
	/********************************
	 * 功能：分页列出项目申购
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/listLabConstructionPurchases")
	public ModelAndView listLabConstructionPurchases(@ModelAttribute LabConstructionPurchase labConstructionPurchase,@RequestParam int currpage, int status){
		ModelAndView mav = new ModelAndView();
		//当前登录人
		User user=shareService.getUser();
		
		//判断当前登陆人是否为项目立项的审核人或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		boolean isNonCollegeAuditor;//是否为非本学院审核人或超级管理员
		if(judge.indexOf(",11,")>-1||judge.indexOf(",15,")>-1||judge.indexOf(",16,")>-1||judge.indexOf(",17,")>-1){
			isNonCollegeAuditor = true;
		}else isNonCollegeAuditor = false;
		mav.addObject("isNonCollegeAuditor", isNonCollegeAuditor);
		
		boolean isCollegeAuditor;//是否为本学院审核人
		if (judge.indexOf(",13,")>-1||judge.indexOf(",4,")>-1) {//13 学院主管领导  4 实验室中心主任
			isCollegeAuditor = true;
		}else isCollegeAuditor = false;
		mav.addObject("isCollegeAuditor", isCollegeAuditor);
		
		
		
		if (status!=0) {//status==0时，显示全部状态的项目立项
			labConstructionPurchase.setAuditResults(status);
		}
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//项目建设总记录数
//		int totalRecords = labConstructionPurchaseService.findAllLabConstructionPurchasesByQueryCount(labConstructionPurchase);
		//分页列出符合条件的项目建设记录
		List <LabConstructionPurchase> listLabConstructionPurchases=labConstructionPurchaseService.findAllLabConstructionPurchasesByQuery(currpage, pageSize, labConstructionPurchase);
		//不分页，全部
		List <LabConstructionPurchase> allLabConstructionPurchases=labConstructionPurchaseService.findAllLabConstructionPurchasesByQuery(labConstructionPurchase);
		
		//查出所有项目建设记录的保管人
		Map<String, String> keepers= labConstructionPurchaseService.findAllProjectKeepers();
		//查出所有项目建设记录的保管人
		Map<String, String> applicants= labConstructionPurchaseService.findAllProjectApplicants();
		
		//项目建设总记录数（查询出来的allLabConstructionPurchases中与当前登陆人相关的项目经费记录数）
		int totalRecords=0;
		String username="["+user.getUsername()+"]";//防止其他人的工号包含当前登陆人
		mav.addObject("username", username);//将[工号](如：[03010])传到后台，使fn:contains更精确
		for (LabConstructionPurchase purchase : allLabConstructionPurchases) {
			LabConstructionProject project=purchase.getLabConstructionProject();//找到对应的LabConstructionProject
			
			if (isNonCollegeAuditor) {//如果是非本学院审核人，可以看到所有立项
				totalRecords++;continue;
			}
			else if (isCollegeAuditor) {//如果是本学院审核人，只能看到本学院的立项
				if (project.getSchoolAcademy()!=null&&project.getSchoolAcademy().getAcademyNumber()==user.getSchoolAcademy().getAcademyNumber()) {
					totalRecords++;continue;
				}
			}
			
			
			Set<MLabConstructionProjectUser> projectMambers=project.getMLabConstructionProjectUsers();
			//如果不是审核人，则只能看到与自己相关的项目立项
			if ((projectMambers.toString().indexOf(username)!=-1)||project.getUser().getUsername()==user.getUsername()||
					project.getUserByCreatedBy().getUsername()==user.getUsername()) {
				
				totalRecords++;
			}
		}
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("superAdmins", superAdmins);
		mav.addObject("user", user);
		
		mav.addObject("status", status);
		mav.addObject("keepers", keepers);		
		mav.addObject("applicants", applicants);	
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("listLabConstructionPurchases", listLabConstructionPurchases);		
		mav.setViewName("labconstruction/listLabConstructionPurchases.jsp");
		return mav;
	}
	
	
	
	/*********************************
	 * 根据主键获取实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping(value="/labconstruction/ajaxGetDevice", produces="application/json;charset=UTF-8")
	public @ResponseBody String ajaxGetDevice(@RequestParam int id){
		LabConstructionDevice device = labConstructionPurchaseService.findDeviceByPrimaryKey(id);
		StringBuffer result = new StringBuffer();
		result.append("{");
		result.append("\"id\":"+device.getId()+",");
		result.append("\"deviceName\":\""+device.getDeviceName()+"\",");
		result.append("\"deviceModel\":\""+device.getDeviceModel()+"\",");
		result.append("\"deviceQuantity\":"+device.getDeviceQuantity()+",");
		result.append("\"devicePriceRef\":\""+device.getDevicePriceRef()+"\",");
		result.append("\"arrivalTime\":\""+device.getArrivalTime().getTime()+"\",");
		result.append("\"comments\":\""+device.getComments()+"\"");
		result.append("}");
		return result.toString();
	}
	/*********************************
	 * 保存实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/saveDevice")
	public String saveDevice(@ModelAttribute LabConstructionDevice labConstructionDevice,@RequestParam int status){
		//将Device标志位tag设置为1(1 purchase  2 funding)
		labConstructionDevice.setTag(1);
		labConstructionDevice=labConstructionPurchaseService.saveDevice(labConstructionDevice);
		Set<LabConstructionPurchase> labConstructionPurchases= labConstructionDevice.getLabConstructionProject().getLabConstructionPurchases();
		List<LabConstructionPurchase> labConstructionPurchasesList = new ArrayList<LabConstructionPurchase>(labConstructionPurchases);
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchasesList.get(0);
		return "redirect:/labconstruction/editLabConstructionPurchase?labConstructionPurchaseId="+labConstructionPurchase.getId()+"&status="+status;
	}
	/*********************************
	 * 删除实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-04
	 *********************************/
	@RequestMapping("/labconstruction/deleteDevice")
	public String deleteDevice(@RequestParam int id,int labConstructionPurchaseId,int status){
		labConstructionPurchaseService.deleteDevice(id);
		return "redirect:/labconstruction/editLabConstructionPurchase?labConstructionPurchaseId="+labConstructionPurchaseId+"&status="+status;
	}
	
	/****************************************************************************
	 * 功能：提交项目建设
	 * 作者：贺子龙
	 * 时间：2015-10-03
	 ****************************************************************************/
	@RequestMapping("/labconstruction/submitLabConstructionPurchase")
	public String submitLabConstructionPurchase(@RequestParam Integer labConstructionPurchaseId){
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		labConstructionPurchase.setAuditResults(2);
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		
		
		for (User collegeLeader : collegeLeaders) {//提交完成后向学院主管领导（一级审核）发送消息
			if (collegeLeader.getUsername().equals(labConstructionPurchase.getUserByApplicant().getUsername())) {//如果项目建设申请人是学院主管领导，则不发给自己
				
			}else {
				Message message= new Message();
				message.setSendUser(labConstructionPurchase.getUserByApplicant().getCname());//项目建设申请人
				message.setSendCparty(labConstructionPurchase.getUserByApplicant().getSchoolAcademy().getAcademyName());//项目建设申请人所在学院
				message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_TITLE);
				String content="申请成功，等待审核";
				content+="<a  href='/xidlims/labconstruction/collegePurchaseAudit?labConstructionPurchaseId=";
				content+=labConstructionPurchase.getId();
				content+="'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(Calendar.getInstance());
				message.setUsername(collegeLeader.getUsername());
				message=messageDAO.store(message);
			}
			
		}
		
		
		
		labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		return "redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status=0";
	}
	
	/****************************************************************************
	 * 功能：删除项目建设
	 * 作者：贺子龙
	 * 时间：2015-10-04
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deleteLabConstructionPurchase")
	public String deleteLabConstructionPurchase(@RequestParameters Integer labConstructionPurchaseId,int status){
		labConstructionPurchaseService.deleteLabConstructionPurchase(labConstructionPurchaseId);
		return "redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status="+status;
	}
	/****************************************************************************
	 * 功能：查看项目建设
	 * 作者：贺子龙
	 * 时间：2015-10-04
	 ****************************************************************************/
	@RequestMapping("/labconstruction/veiwLabConstructionPurchase")
	public ModelAndView veiwLabConstructionPurchase(@RequestParam Integer labConstructionPurchaseId,int status ){
		ModelAndView mav=new ModelAndView();
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		LabConstructionProject labConstructionProject=labConstructionPurchase.getLabConstructionProject();
		//找到对应的device
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 1);
		mav.addObject("labConstructionPurchase", labConstructionPurchase);
		mav.addObject("labConstructionProject", labConstructionProject);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		mav.addObject("status", status);
		mav.setViewName("/labconstruction/veiwLabConstructionPurchase.jsp");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：学院主管领导审核(一级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/collegePurchaseAudit")
	public ModelAndView  collegePurchaseAudit(@RequestParam Integer labConstructionPurchaseId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		mav.addObject("labConstructionPurchase", labConstructionPurchase);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionPurchase.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//找到对应的device
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 1);
		mav.addObject("labConstructionDevices", labConstructionDevices);
				
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_purchase_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("collegePurchaseAudit", new LabConstructionPurchaseAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=1代表是学院主管领导的审核结果）
		Set<LabConstructionPurchaseAudit> audits=labConstructionPurchase.getLabConstructionPurchaseAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
				List<Integer> auditsListId = new ArrayList<Integer>();
				for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
					if (labConstructionPurchaseAudit.getTag()==1) {
						auditsListId.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId中
					}
				}
				Collections.sort(auditsListId);//将auditsListId从小到大排序
				int auditId;
				if (auditsListId.size()>0) {
					auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionPurchaseAudit collegePurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId);
					mav.addObject("collegePurchaseAuditDone", collegePurchaseAuditDone);
				}
				
		
		/*if (audits.size()>0) {
			
			for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
				if (labConstructionPurchaseAudit.getTag()==1) {//确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionPurchaseAudit collegePurchaseAuditDone=labConstructionPurchaseAudit;
					mav.addObject("collegePurchaseAuditDone", collegePurchaseAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/collegePurchaseAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存学院主管领导审核结果(一级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveCollegePurchaseAudit")
	public ModelAndView saveCollegePurchaseAudit(@ModelAttribute LabConstructionPurchaseAudit collegePurchaseAudit,@RequestParam int labConstructionPurchaseId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		collegePurchaseAudit.setLabConstructionPurchase(labConstructionPurchase);
		collegePurchaseAudit.setTag(1);//学院主管领导审核标志位
		//当前登录人
		User user=shareService.getUser();
		collegePurchaseAudit.setUser(user);
		labConstructionPurchaseAuditDAO.store(collegePurchaseAudit);
		int audit_return=collegePurchaseAudit.getCDictionary().getId();
		if (audit_return==610) {//审核通过
			labConstructionPurchase.setAuditResults(2);//审核中状态
			labConstructionPurchase.setStage(1);//学院主管领导审核阶段结束
			//根据权限id查询用户
			List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
			List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
			List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
			List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
			List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
			
			//审核完成后向资产管理处（二级审核）发送消息
			
			for (User assetManager : assetManagers) {
				if (assetManager.getUsername().equals(shareService.getUserDetail().getUsername())) {
					
					
				}
				//如果当前登陆人为资产管理员，则不发给自己
				Message message= new Message();
				message.setSendUser(shareService.getUserDetail().getCname());//审核人
				message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
				message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_ONE);
				String content="学院主管领导审核通过，请资产管理处审核";
				content+="<a  href='/xidlims/labconstruction/assetPurchaseAudit?labConstructionPurchaseId=";
				content+=labConstructionPurchase.getId();
				content+="'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(Calendar.getInstance());
				message.setUsername(assetManager.getUsername());
				message=messageDAO.store(message);
			}
			
			//同时，要给项目建设申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_ONE);
			String content="学院主管领导审核通过，等待资产管理处审核";
			content+="<a  href='/xidlims/labconstruction/collegePurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
			
		}
		if (audit_return==611) {//审核拒绝
			labConstructionPurchase.setAuditResults(4);//审核拒绝状态
			labConstructionPurchase.setStage(-1);//审核拒绝后，stage值被置为-1
			//给项目建设申请人发送审核失败的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_FAIL_ONE);
			String content="未通过学院主管领导审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/collegePurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
			
		}
		labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		mav.setViewName("redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：资产管理处审核(二级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/assetPurchaseAudit")
	public ModelAndView  assetPurchaseAudit(@RequestParam Integer labConstructionPurchaseId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		mav.addObject("labConstructionPurchase", labConstructionPurchase);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionPurchase.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		
		
		//找到对应的device
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 1);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_purchase_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("assetPurchaseAudit", new LabConstructionPurchaseAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=2代表是资产管理处的审核结果）
		Set<LabConstructionPurchaseAudit> audits=labConstructionPurchase.getLabConstructionPurchaseAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId = new ArrayList<Integer>();
		for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
			if (labConstructionPurchaseAudit.getTag()==2) {
				auditsListId.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		Collections.sort(auditsListId);//将auditsListId从小到大排序
		int auditId;
		if (auditsListId.size()>0) {
			auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit assetPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId);
			mav.addObject("assetPurchaseAuditDone", assetPurchaseAuditDone);
		}
		/*if (audits.size()>0) {
			
			for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
				if (labConstructionPurchaseAudit.getTag()==2) {//确保是最后一次资产管理处审核（针对打回去的情况）
					LabConstructionPurchaseAudit assetPurchaseAuditDone=labConstructionPurchaseAudit;
					mav.addObject("assetPurchaseAuditDone", assetPurchaseAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/assetPurchaseAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：资产管理处审核(二级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveAssetPurchaseAudit")
	public ModelAndView saveAssetPurchaseAudit(@ModelAttribute LabConstructionPurchaseAudit assetPurchaseAudit,@RequestParam int labConstructionPurchaseId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		assetPurchaseAudit.setLabConstructionPurchase(labConstructionPurchase);
		assetPurchaseAudit.setTag(2);//资产管理处审核标志位
		//当前登录人
		User user=shareService.getUser();
		assetPurchaseAudit.setUser(user);
		labConstructionPurchaseAuditDAO.store(assetPurchaseAudit);
		int audit_return=assetPurchaseAudit.getCDictionary().getId();
		if (audit_return==610) {//审核通过
			labConstructionPurchase.setAuditResults(2);//审核中状态
			labConstructionPurchase.setStage(2);//资产管理处阶段结束
			
			//根据权限id查询用户
			List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
			List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
			List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
			List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
			List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
			
			//审核完成后向校领导（三级审核）发送消息
			
			for (User schoolLeader : schoolLeaders) {
				if (schoolLeader.getUsername().equals(shareService.getUserDetail().getUsername())) {
					
				}
				//如果当前登陆人为校领导，则不发给自己
				Message message= new Message();
				message.setSendUser(shareService.getUserDetail().getCname());//审核人
				message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
				message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_TWO);
				String content="资产管理处审核通过，请校领导审核";
				content+="<a  href='/xidlims/labconstruction/schoolPurchaseAudit?labConstructionPurchaseId=";
				content+=labConstructionPurchase.getId();
				content+="'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(Calendar.getInstance());
				message.setUsername(schoolLeader.getUsername());
				message=messageDAO.store(message);
			}
			
			//同时，给项目建设申请人发送审核成功的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_TWO);
			String content="资产管理处审核通过，等待校领导审核";
			content+="<a  href='/xidlims/labconstruction/assetPurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
		}
		if (audit_return==611) {//审核拒绝
			labConstructionPurchase.setAuditResults(4);//审核拒绝状态
			labConstructionPurchase.setStage(-1);//审核拒绝后，stage值被置为-1
			
			//给项目建设申请人发送审核失败的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_FAIL_TWO);
			String content="未通过资产管理处审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/assetPurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
		}
		labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		mav.setViewName("redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：校领导审核(三级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/schoolPurchaseAudit")
	public ModelAndView  schoolPurchaseAudit(@RequestParam Integer labConstructionPurchaseId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		mav.addObject("labConstructionPurchase", labConstructionPurchase);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionPurchase.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		
		
		//找到对应的device
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 1);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_purchase_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("schoolPurchaseAudit", new LabConstructionPurchaseAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=3代表是校领导处的审核结果）
		Set<LabConstructionPurchaseAudit> audits=labConstructionPurchase.getLabConstructionPurchaseAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
				List<Integer> auditsListId = new ArrayList<Integer>();
				for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
					if (labConstructionPurchaseAudit.getTag()==3) {
						auditsListId.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId中
					}
				}
				Collections.sort(auditsListId);//将auditsListId从小到大排序
				int auditId;
				if (auditsListId.size()>0) {
					auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionPurchaseAudit schoolPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId);
					mav.addObject("schoolPurchaseAuditDone", schoolPurchaseAuditDone);
				}
		/*if (audits.size()>0) {
			
			for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
				if (labConstructionPurchaseAudit.getTag()==3) {//确保是最后一次资产管理处审核（针对打回去的情况）
					LabConstructionPurchaseAudit schoolPurchaseAuditDone=labConstructionPurchaseAudit;
					mav.addObject("schoolPurchaseAuditDone", schoolPurchaseAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/schoolPurchaseAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：校领导审核(三级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveSchoolPurchaseAudit")
	public ModelAndView saveSchoolPurchaseAudit(@ModelAttribute LabConstructionPurchaseAudit schoolPurchaseAudit,@RequestParam int labConstructionPurchaseId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		schoolPurchaseAudit.setLabConstructionPurchase(labConstructionPurchase);
		schoolPurchaseAudit.setTag(3);//校领导审核标志位
		//当前登录人
		User user=shareService.getUser();
		schoolPurchaseAudit.setUser(user);
		labConstructionPurchaseAuditDAO.store(schoolPurchaseAudit);
		int audit_return=schoolPurchaseAudit.getCDictionary().getId();
		if (audit_return==610) {//审核通过
			labConstructionPurchase.setAuditResults(2);//审核中状态
			labConstructionPurchase.setStage(3);//校领导阶段结束
			
			
			//根据权限id查询用户
			List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
			List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
			List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
			List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
			List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
			
			//审核完成后向财务总监（四级审核）发送消息
			
			for (User cfo : cfos) {
				if (cfo.getUsername().equals(shareService.getUserDetail().getUsername())) {
					
				}
				//如果当前登陆人为财务总监，则不发给自己
				Message message= new Message();
				message.setSendUser(shareService.getUserDetail().getCname());//审核人
				message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
				message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_THREE);
				String content="校领导审核通过，请财务总监审核";
				content+="<a  href='/xidlims/labconstruction/cfoPurchaseAudit?labConstructionPurchaseId=";
				content+=labConstructionPurchase.getId();
				content+="'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(Calendar.getInstance());
				message.setUsername(cfo.getUsername());
				message=messageDAO.store(message);
			}
			
			//同时，给项目建设申请人发送审核成功的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_THREE);
			String content="校领导审核通过，等待财务总监审核";
			content+="<a  href='/xidlims/labconstruction/schoolPurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
			
		}
		if (audit_return==611) {//审核拒绝
			labConstructionPurchase.setAuditResults(4);//审核拒绝状态
			labConstructionPurchase.setStage(-1);//审核拒绝后，stage值被置为-1
			
			
			//给项目建设申请人发送审核失败的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_FAIL_THREE);
			String content="未通过校领导审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/schoolPurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
		}
		labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		mav.setViewName("redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：财务总监审核（四级审核）
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/cfoPurchaseAudit")
	public ModelAndView  cfoPurchaseAudit(@RequestParam Integer labConstructionPurchaseId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		mav.addObject("labConstructionPurchase", labConstructionPurchase);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionPurchase.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		
		//找到对应的device
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 1);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_purchase_audit_results");
		mav.addObject("results", results);
		
		//学院主管领导审核记录
		mav.addObject("cfoPurchaseAudit", new LabConstructionPurchaseAudit());
		
		
		//根据权限id查询用户
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=4代表是财务总监处的审核结果）
		Set<LabConstructionPurchaseAudit> audits=labConstructionPurchase.getLabConstructionPurchaseAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId = new ArrayList<Integer>();
		for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
			if (labConstructionPurchaseAudit.getTag()==4) {
				auditsListId.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		Collections.sort(auditsListId);//将auditsListId从小到大排序
		int auditId;
		if (auditsListId.size()>0) {
			auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit cfoPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId);
			mav.addObject("cfoPurchaseAuditDone", cfoPurchaseAuditDone);
		}
		
		/*if (audits.size()>0) {
			
			for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
				if (labConstructionPurchaseAudit.getTag()==4) {//确保是最后一次资产管理处审核（针对打回去的情况）
					LabConstructionPurchaseAudit cfoPurchaseAuditDone=labConstructionPurchaseAudit;
					mav.addObject("cfoPurchaseAuditDone", cfoPurchaseAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/cfoPurchaseAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存财务总监审核（四级审核）
	 * 作者：贺子龙
	 * 时间：2015-10-09
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveCfoPurchaseAudit")
	public ModelAndView saveCfoPurchaseAudit(@ModelAttribute LabConstructionPurchaseAudit cfoPurchaseAudit,@RequestParam int labConstructionPurchaseId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目建设
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		cfoPurchaseAudit.setLabConstructionPurchase(labConstructionPurchase);
		cfoPurchaseAudit.setTag(4);//财务总监审核标志位
		//当前登录人
		User user=shareService.getUser();
		cfoPurchaseAudit.setUser(user);
		labConstructionPurchaseAuditDAO.store(cfoPurchaseAudit);
		int audit_return=cfoPurchaseAudit.getCDictionary().getId();
		if (audit_return==610) {//审核通过
			labConstructionPurchase.setAuditResults(3);//审核通过状态
			labConstructionPurchase.setStage(4);//财务总监阶段结束
			
			//给项目建设申请人发送审核成功的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_SUCCESS_FOUR);
			String content="财务总监审核通过，请查看审核结果";
			content+="<a  href='/xidlims/labconstruction/cfoPurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
		}
		if (audit_return==611) {//审核拒绝
			labConstructionPurchase.setAuditResults(4);//审核拒绝状态
			labConstructionPurchase.setStage(-1);//审核拒绝后，stage值被置为-1
			
			
			//给项目建设申请人发送审核失败的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//审核人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//审核人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONPURCHASE_FAIL_FOUR);
			String content="未通过财务总监审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/cfoPurchaseAudit?labConstructionPurchaseId=";
			content+=labConstructionPurchase.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());
			message=messageDAO.store(message);
		}
		labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		mav.setViewName("redirect:/labconstruction/listLabConstructionPurchases?currpage=1&status=0");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：根据项目立项id查询该项目建设的审核进度
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/viewPurchaseStage")
	public @ResponseBody String viewPurchaseStage(@RequestParam Integer labConstructionPurchaseId) {
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		
		//查看项目建设对应的审核意见
		Set<LabConstructionPurchaseAudit> audits=labConstructionPurchase.getLabConstructionPurchaseAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId1 = new ArrayList<Integer>();
		List<Integer> auditsListId2 = new ArrayList<Integer>();
		List<Integer> auditsListId3 = new ArrayList<Integer>();
		List<Integer> auditsListId4 = new ArrayList<Integer>();
		for (LabConstructionPurchaseAudit labConstructionPurchaseAudit : audits) {
			if (labConstructionPurchaseAudit.getTag()==1) {
				auditsListId1.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId1中
				continue;
			}
			
			if (labConstructionPurchaseAudit.getTag()==2) {
				auditsListId2.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId2中
				continue;
			}
			
			if (labConstructionPurchaseAudit.getTag()==3) {
				auditsListId3.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId3中
				continue;
			}
			if (labConstructionPurchaseAudit.getTag()==4) {
				auditsListId4.add(labConstructionPurchaseAudit.getId());//将tag条件的id加入到auditsListId4中
			}
		}
		
		String s="";
		s+="<p>项目建设审核进度</p><br>";
		
		if (auditsListId4.size()>0 && labConstructionPurchase.getStage()==4) {
			Collections.sort(auditsListId4);//将auditsListId4从小到大排序
			int auditId4=auditsListId4.get(auditsListId4.size()-1);//取最后一个，确保是最后一次财务总监领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit cfoPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId4);
			s+=
					"<p>  财务总监审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+cfoPurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+cfoPurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId3);//将auditsListId3从小到大排序
			int auditId3=auditsListId3.get(auditsListId3.size()-1);//取最后一个，确保是最后一次校领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit schoolPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId3);
			s+=
					"<p>  校领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+schoolPurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+schoolPurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次资产管理处审核（针对打回去的情况）
			LabConstructionPurchaseAudit assetPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId2);
			s+=
					"<p>  资产管理处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+assetPurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+assetPurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit collegePurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId1);
			s+=
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegePurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegePurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			
		}
			
		if (auditsListId3.size()>0 && labConstructionPurchase.getStage()==3) {
			s+="<p>  财务总监未审核 </p>";	
			Collections.sort(auditsListId3);//将auditsListId3从小到大排序
			int auditId3=auditsListId3.get(auditsListId3.size()-1);//取最后一个，确保是最后一次校领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit schoolPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId3);
			s+=
					"<p>  校领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+schoolPurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+schoolPurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次资产管理处审核（针对打回去的情况）
			LabConstructionPurchaseAudit assetPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId2);
			s+=
					"<p>  资产管理处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+assetPurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+assetPurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit collegePurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId1);
			s+=
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegePurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegePurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
		}
				
		if (auditsListId2.size()>0 && labConstructionPurchase.getStage()==2) {
			s+="<p>  财务总监未审核 </p>";
			s+="<p>  校领导未审核 </p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次资产管理处审核（针对打回去的情况）
			LabConstructionPurchaseAudit assetPurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId2);
			s+=
					"<p>  资产管理处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+assetPurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+assetPurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit collegePurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId1);
			s+=
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegePurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegePurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			
		}
					
		if (auditsListId1.size()>0 && labConstructionPurchase.getStage()==1) {
			s+="<p>  财务总监未审核 </p>";
			s+="<p>  校领导未审核 </p>";
			s+="<p>  资产管理处未审核 </p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionPurchaseAudit collegePurchaseAuditDone=labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(auditId1);
			s+=
					"<p>  学院主管领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+collegePurchaseAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+collegePurchaseAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
		}
		if (labConstructionPurchase.getStage()==0) {
			s+="<p>  财务总监未审核 </p>";
			s+="<p>  校领导未审核 </p>";
			s+="<p>  资产管理处未审核 </p>";
			s+="<p>  学院主管领导未审核 </p>";
			
		}
						
		return htmlEncode(s);
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
}