package net.xidlims.web.lab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.LabConstructionFundingAuditDAO;
import net.xidlims.dao.MessageDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabConstructionDevice;
import net.xidlims.domain.LabConstructionFunding;
import net.xidlims.domain.LabConstructionFundingAudit;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.LabConstructionPurchase;
import net.xidlims.domain.LabConstructionPurchaseAudit;
import net.xidlims.domain.MLabConstructionProjectUser;
import net.xidlims.domain.Message;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabConstructionFundingService;
import net.xidlims.service.lab.LabConstructionProjectService;
import net.xidlims.service.lab.LabConstructionPurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.inject.servlet.RequestParameters;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * Spring MVC controller that handles CRUD requests for LabConstructionFunding entities
 * 
 */

@Controller("LabConstructionFundingController")
public class LabConstructionFundingController {
	
	@Autowired LabConstructionFundingService labConstructionFundingService;
	@Autowired ShareService shareService;
	@Autowired LabConstructionProjectService labConstructionProjectService;
	@Autowired LabConstructionPurchaseService labConstructionPurchaseService;
	@Autowired CDictionaryDAO cDictionaryDAO;
	@Autowired LabConstructionFundingAuditDAO labConstructionFundingAuditDAO;
	@Autowired MessageDAO messageDAO;

	/********************************
	 * 功能：分页列出项目经费
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/listLabConstructionFundings")
	public ModelAndView listLabConstructionFundings(@ModelAttribute LabConstructionFunding labConstructionFunding,@RequestParam int currpage, int status){
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
			labConstructionFunding.setAuditResults(status);
		}
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//项目建设总记录数
//		int totalRecords = labConstructionFundingService.findAllLabConstructionFundingsByQueryCount(labConstructionFunding);
		//分页列出符合条件的项目建设记录
		List <LabConstructionFunding> listLabConstructionFundings=labConstructionFundingService.findAllLabConstructionFundingsByQuery(currpage, pageSize, labConstructionFunding);
		//不分页，全部
		List <LabConstructionFunding> allLabConstructionFundings=labConstructionFundingService.findAllLabConstructionFundingsByQuery(currpage, pageSize, labConstructionFunding);
		//项目建设总记录数（查询出来的listLabConstructionFundings中与当前登陆人相关的项目经费记录数）
		int totalRecords=0;
		String username="["+user.getUsername()+"]";//防止其他人的工号包含当前登陆人
		mav.addObject("username", username);//将[工号](如：[03010])传到后台，使fn:contains更精确
		for (LabConstructionFunding funding : allLabConstructionFundings) {
			LabConstructionProject project=funding.getLabConstructionProject();//找到对应的LabConstructionProject
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
			if ((projectMambers.toString().indexOf(username)!=-1)||project.getUser().getUsername()==user.getUsername()||
					project.getUserByCreatedBy().getUsername()==user.getUsername()) {
				
				totalRecords++;
			}
		}
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("excenterDirectors", excenterDirectors);
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		mav.addObject("user", user);
		mav.addObject("status", status);
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("listLabConstructionFundings", listLabConstructionFundings);		
		mav.setViewName("labconstruction/listLabConstructionFundings.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：新建项目经费
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/newLabConstructionFunding")
	public ModelAndView newLabConstructionFunding(@RequestParam int labConstructionPurchaseId,int status){
		ModelAndView mav = new ModelAndView();
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchaseService.findLabConstructionPurchaseByPrimaryKey(labConstructionPurchaseId);
		LabConstructionProject labConstructionProject = labConstructionPurchase.getLabConstructionProject();
		mav.addObject("labConstructionFunding", new LabConstructionFunding());
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("isEdit", false);
		mav.addObject("status", status);
		mav.setViewName("labconstruction/editLabConstructionFunding.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：编辑项目经费
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/editLabConstructionFunding")
	public ModelAndView editLabConstructionFunding(@RequestParam int labConstructionFundingId,int status){
		ModelAndView mav = new ModelAndView();
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		//得到该项目经费对应的项目立项
		LabConstructionProject labConstructionProject = labConstructionFunding.getLabConstructionProject();
		//获得项目立项所对应的tag为2的设备(tag为2意思是在项目经费阶段的设备)
		List<LabConstructionDevice> labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject,2);
				
		mav.addObject("labConstructionDevices", labConstructionDevices);
		mav.addObject("labConstructionDevice", new LabConstructionDevice());
		mav.addObject("labConstructionFunding", labConstructionFunding);
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("isEdit", true);
		mav.addObject("status", status);
		mav.setViewName("labconstruction/editLabConstructionFunding.jsp");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：提交项目经费
	 * 作者：贺子龙
	 * 时间：2015-10-05
	 ****************************************************************************/
	@RequestMapping("/labconstruction/submitLabConstructionFunding")
	public String submitLabConstructionFunding(@RequestParam Integer labConstructionFundingId){
		
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		labConstructionFunding.setAuditResults(2);
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		
		//提交完成后，给实验中心主任（一级审核）发送消息
		
		for (User excenterDirector : excenterDirectors) {
			if (excenterDirector.getUsername().equals(shareService.getUserDetail().getUsername())) {//如果当前登陆人是实验中心主任，则不发给自己
				
			}else {
				Message message= new Message();
				message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
				message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
				message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_TITLE);
				String content="申请成功，等待审核";
				content+="<a  href='/xidlims/labconstruction/excenterFundingAudit?labConstructionFundingId=";
				content+=labConstructionFunding.getId();
				content+="'>点击查看</a>";
				message.setContent(content);
				message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
				message.setCreateTime(Calendar.getInstance());
				message.setUsername(excenterDirector.getUsername());
				message=messageDAO.store(message);
			}
			
		}
		
		labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		return "redirect:/labconstruction/listLabConstructionFundings?currpage=1&status=0";
	}
	/****************************************************************************
	 * 功能：删除项目经费
	 * 作者：贺子龙
	 * 时间：2015-10-04
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deleteLabConstructionFunding")
	public String deleteLabConstructionFunding(@RequestParameters Integer labConstructionFundingId,int status){
		labConstructionFundingService.deleteLabConstructionFunding(labConstructionFundingId);
		return "redirect:/labconstruction/listLabConstructionFundings?currpage=1&status="+status;
	}
	
	
	
	
	/****************************************************************************
	 * 功能：查看项目经费
	 * 作者：贺子龙
	 * 时间：2015-10-04
	 ****************************************************************************/
	@RequestMapping("/labconstruction/veiwLabConstructionFunding")
	public ModelAndView veiwLabConstructionFunding(@RequestParam Integer labConstructionFundingId,int status ){
		ModelAndView mav=new ModelAndView();
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		LabConstructionProject labConstructionProject=labConstructionFunding.getLabConstructionProject();
		//找到对应的设备
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 2);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		mav.addObject("labConstructionFunding", labConstructionFunding);
		mav.addObject("labConstructionProject", labConstructionProject);
		mav.addObject("status", status);
		mav.setViewName("/labconstruction/veiwLabConstructionFunding.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：保存项目经费
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionFunding")
	public String saveLabConstructionFunding(@ModelAttribute LabConstructionFunding labConstructionFunding,
			@RequestParam int labConstructionProjectId,int status){
		
		LabConstructionProject labConstructionProject = labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		//通过labConstructionProject找到对应的labConstructionPurchase
		Set<LabConstructionPurchase> labConstructionPurchases=labConstructionProject.getLabConstructionPurchases();
		List<LabConstructionPurchase> labConstructionPurchasesList=new ArrayList<LabConstructionPurchase>(labConstructionPurchases);
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchasesList.get(0);
		//找到labConstructionPurchase之后，将其stage设置为5，用stage来限制每个项目建设只能生成一个项目经费。
		labConstructionPurchase.setStage(5);
		labConstructionPurchaseService.saveLabConstructionPurchase(labConstructionPurchase);
		labConstructionFunding.setLabConstructionProject(labConstructionProject);
		labConstructionFunding.setAuditResults(1);//草稿状态
		labConstructionFunding.setStage(0);//未审核阶段
		labConstructionFunding.setCreatedAt(Calendar.getInstance());//当前时间为创建时间
		labConstructionFunding=labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		
		return "redirect:/labconstruction/editLabConstructionFunding?labConstructionFundingId="+labConstructionFunding.getId()+"&status="+status;
	}
	/********************************
	 * 功能：保存项目经费2-跳转页面
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionFundingAll")
	public String saveLabConstructionFundingAll(@RequestParameters int status){
		
		return "redirect:/labconstruction/listLabConstructionFundings?currpage=1&status="+status;
	}
	/*********************************
	 * 根据主键获取实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping(value="/labconstruction/ajaxGetDeviceTag2", produces="application/json;charset=UTF-8")
	public @ResponseBody String ajaxGetDeviceTag2(@RequestParam int id){
		LabConstructionDevice device = labConstructionPurchaseService.findDeviceByPrimaryKey(id);
		StringBuffer result = new StringBuffer();
		result.append("{");
		result.append("\"id\":"+device.getId()+",");
		result.append("\"deviceName\":\""+device.getDeviceName()+"\",");
		result.append("\"deviceModel\":\""+device.getDeviceModel()+"\",");
		result.append("\"deviceQuantity\":"+device.getDeviceQuantity()+",");
		result.append("\"devicePriceSig\":\""+device.getDevicePriceSig()+"\"");
		result.append("}");
		return result.toString();
	}
	/*********************************
	 * 保存实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/saveDeviceTag2")
	public String saveDeviceTag2(@ModelAttribute LabConstructionDevice labConstructionDevice,@RequestParam int status){
		//将Device标志位tag设置为1(1 purchase  2 funding)
		labConstructionDevice.setTag(2);
		labConstructionDevice=labConstructionPurchaseService.saveDevice(labConstructionDevice);
		Set<LabConstructionFunding> labConstructionFundings= labConstructionDevice.getLabConstructionProject().getLabConstructionFundings();
		List<LabConstructionFunding> labConstructionFundingsList = new ArrayList<LabConstructionFunding>(labConstructionFundings);
		LabConstructionFunding labConstructionFunding=labConstructionFundingsList.get(0);
		return "redirect:/labconstruction/editLabConstructionFunding?labConstructionFundingId="+labConstructionFunding.getId()+"&status="+status;
	}
	/*********************************
	 * 删除实验项目建设所申购物品记录
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/deleteDeviceTag2")
	public String deleteDeviceTag2(@RequestParam int id,int labConstructionFundingId,int status){
		labConstructionPurchaseService.deleteDevice(id);
		return "redirect:/labconstruction/editLabConstructionFunding?labConstructionFundingId="+labConstructionFundingId+"&status="+status;
	}
	
	/****************************************************************************
	 * 功能：实验中心主任(一级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/excenterFundingAudit")
	public ModelAndView  collegePurchaseAudit(@RequestParam Integer labConstructionFundingId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		mav.addObject("labConstructionFunding", labConstructionFunding);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionFunding.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		

		//找到对应的设备
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 2);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_funding_audit_results");
		mav.addObject("results", results);
		
		//实验室中心主任审核记录
		mav.addObject("excenterFundingAudit", new LabConstructionFundingAudit());
		
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("excenterDirectors", excenterDirectors);
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=1代表是实验室中心主任的审核结果）
		Set<LabConstructionFundingAudit> audits=labConstructionFunding.getLabConstructionFundingAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
				List<Integer> auditsListId = new ArrayList<Integer>();
				for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
					if (labConstructionFundingAudit.getTag()==1) {
						auditsListId.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId中
					}
				}
				Collections.sort(auditsListId);//将auditsListId从小到大排序
				int auditId;
				if (auditsListId.size()>0) {
					auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionFundingAudit excenterFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId);
					mav.addObject("excenterFundingAuditDone", excenterFundingAuditDone);
				}
		
		/*if (audits.size()>0) {
			
			for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
				if (labConstructionFundingAudit.getTag()==1) {//确保是最后一次实验室中心主任审核（针对打回去的情况）
					LabConstructionFundingAudit excenterFundingAuditDone=labConstructionFundingAudit;
					mav.addObject("excenterFundingAuditDone", excenterFundingAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/excenterFundingAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存实验中心主任(一级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveExcenterFundingAudit")
	public ModelAndView saveExcenterFundingAudit(@ModelAttribute LabConstructionFundingAudit excenterFundingAudit,@RequestParam int labConstructionFundingId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		excenterFundingAudit.setLabConstructionFunding(labConstructionFunding);
		excenterFundingAudit.setTag(1);//实验室主任审核标志位
		//根据labConstructionFunding找到对应的labConstructionPurchase
		Set<LabConstructionPurchase> labConstructionPurchases= labConstructionFunding.getLabConstructionProject().getLabConstructionPurchases();
		List<LabConstructionPurchase> labConstructionPurchasesList=new ArrayList<LabConstructionPurchase>(labConstructionPurchases);
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchasesList.get(0);
		
		//当前登录人
		User user=shareService.getUser();
		excenterFundingAudit.setUser(user);
		labConstructionFundingAuditDAO.store(excenterFundingAudit);
		int audit_return=excenterFundingAudit.getCDictionary().getId();
		if (audit_return==612) {//审核通过
			labConstructionFunding.setAuditResults(2);//审核中状态
			labConstructionFunding.setStage(1);//实验室主任审核阶段结束
			
			
			//根据权限id查询用户
			List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
			List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
			List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
			List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
			List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
			List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
			
			//实验中心主任（一级审核）完成后，给资产管理处（二级审核）发送消息
			
			for (User assetManager : assetManagers) {
				if (assetManager.getUsername().equals(shareService.getUserDetail().getUsername())) {//如果当前登陆人是资产管理员，则不发给自己
					
				}else {
					Message message= new Message();
					message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
					message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
					message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_ONE);
					String content="实验中心主任审核通过，请资产管理处审核";
					content+="<a  href='/xidlims/labconstruction/assetFundingAudit?labConstructionFundingId=";
					content+=labConstructionFunding.getId();
					content+="'>点击查看</a>";
					message.setContent(content);
					message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
					message.setCreateTime(Calendar.getInstance());
					message.setUsername(assetManager.getUsername());
					message=messageDAO.store(message);
				}
				
			}
			
			//同时，给项目建设中的设备申购申请人发送审核成功的消息
			
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_ONE);
			String content="实验中心主任审核通过，等待资产管理处审核";
			content+="<a  href='/xidlims/labconstruction/assetFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//设备申购申请人
			message=messageDAO.store(message);
			
			
		}
		if (audit_return==613) {//审核拒绝
			labConstructionFunding.setAuditResults(4);//审核拒绝状态
			labConstructionFunding.setStage(-1);//审核拒绝后，stage值被置为-1
			
			//给项目建设中的设备申购申请人发送审核失败的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_FAIL_ONE);
			String content="未通过实验中心主任审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/excenterFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//设备申购申请人
			message=messageDAO.store(message);
		}
		labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		mav.setViewName("redirect:/labconstruction/listLabConstructionFundings?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：资产管理处审核(二级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/assetFundingAudit")
	public ModelAndView  assetFundingAudit(@RequestParam Integer labConstructionFundingId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		mav.addObject("labConstructionFunding", labConstructionFunding);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionFunding.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);

		//找到对应的设备
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 2);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_funding_audit_results");
		mav.addObject("results", results);
		
		//资产管理处审核记录
		mav.addObject("assetFundingAudit", new LabConstructionFundingAudit());
		
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("excenterDirectors", excenterDirectors);
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=2代表是资产管理处的审核结果）
		Set<LabConstructionFundingAudit> audits=labConstructionFunding.getLabConstructionFundingAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId = new ArrayList<Integer>();
		for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
			if (labConstructionFundingAudit.getTag()==2) {
				auditsListId.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		Collections.sort(auditsListId);//将auditsListId从小到大排序
		int auditId;
		if (auditsListId.size()>0) {
			auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionFundingAudit assetFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId);
			mav.addObject("assetFundingAuditDone", assetFundingAuditDone);
		}
		/*if (audits.size()>0) {
			
			for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
				if (labConstructionFundingAudit.getTag()==2) {//确保是最后一次实验资产管理处审核（针对打回去的情况）
					LabConstructionFundingAudit assetFundingAuditDone=labConstructionFundingAudit;
					mav.addObject("assetFundingAuditDone", assetFundingAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/assetFundingAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存资产管理处审核(二级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveAssetFundingAudit")
	public ModelAndView saveAssetFundingAudit(@ModelAttribute LabConstructionFundingAudit assetFundingAudit,@RequestParam int labConstructionFundingId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		assetFundingAudit.setLabConstructionFunding(labConstructionFunding);
		assetFundingAudit.setTag(2);//资产管理处审核标志位
		
		//根据labConstructionFunding找到对应的labConstructionPurchase
		Set<LabConstructionPurchase> labConstructionPurchases= labConstructionFunding.getLabConstructionProject().getLabConstructionPurchases();
		List<LabConstructionPurchase> labConstructionPurchasesList=new ArrayList<LabConstructionPurchase>(labConstructionPurchases);
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchasesList.get(0);
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		
		//当前登录人
		User user=shareService.getUser();
		assetFundingAudit.setUser(user);
		labConstructionFundingAuditDAO.store(assetFundingAudit);
		int audit_return=assetFundingAudit.getCDictionary().getId();
		if (audit_return==612) {//审核通过
			labConstructionFunding.setAuditResults(2);//审核中状态
			labConstructionFunding.setStage(2);//资产管理处审核阶段结束
			
			
			//资产管理处（二级审核）完成后，给校领导（三级审核）发送消息
			
			for (User schoolLeader : schoolLeaders) {
				if (schoolLeader.getUsername().equals(shareService.getUserDetail().getUsername())) {//如果当前登陆人是校领导，则不发给自己
					
				}else {
					Message message= new Message();
					message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
					message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
					message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_TWO);
					String content="资产管理处审核通过，请校领导审核";
					content+="<a  href='/xidlims/labconstruction/schoolFundingAudit?labConstructionFundingId=";
					content+=labConstructionFunding.getId();
					content+="'>点击查看</a>";
					message.setContent(content);
					message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
					message.setCreateTime(Calendar.getInstance());
					message.setUsername(schoolLeader.getUsername());
					message=messageDAO.store(message);
				}
				
			}
			
			//同时，给项目建设中的设备申购申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_TWO);
			String content="资产管理处审核通过，等待校领导审核";
			content+="<a  href='/xidlims/labconstruction/assetFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//项目建设设备申购的申请人
			message=messageDAO.store(message);
		}
		if (audit_return==613) {//审核拒绝
			labConstructionFunding.setAuditResults(4);//审核拒绝状态
			labConstructionFunding.setStage(-1);//审核拒绝后，stage值被置为-1
			
			//给项目建设中的设备申购申请人发送审核失败的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_FAIL_TWO);
			String content="未通过资产管理处审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/assetFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//项目建设设备申购的申请人
			message=messageDAO.store(message);
		}
		labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		mav.setViewName("redirect:/labconstruction/listLabConstructionFundings?currpage=1&status=0");
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：校领导审核(三级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/schoolFundingAudit")
	public ModelAndView  schoolFundingAudit(@RequestParam Integer labConstructionFundingId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		mav.addObject("labConstructionFunding", labConstructionFunding);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionFunding.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);

		//找到对应的设备
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 2);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_funding_audit_results");
		mav.addObject("results", results);
		
		//校领导审核记录
		mav.addObject("schoolFundingAudit", new LabConstructionFundingAudit());
		
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("excenterDirectors", excenterDirectors);
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=3代表是校领导的审核结果）
		Set<LabConstructionFundingAudit> audits=labConstructionFunding.getLabConstructionFundingAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
				List<Integer> auditsListId = new ArrayList<Integer>();
				for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
					if (labConstructionFundingAudit.getTag()==3) {
						auditsListId.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId中
					}
				}
				Collections.sort(auditsListId);//将auditsListId从小到大排序
				int auditId;
				if (auditsListId.size()>0) {
					auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
					LabConstructionFundingAudit schoolFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId);
					mav.addObject("schoolFundingAuditDone", schoolFundingAuditDone);
				}
		
		
	/*	if (audits.size()>0) {
			
			for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
				if (labConstructionFundingAudit.getTag()==3) {//确保是最后一次校领导审核（针对打回去的情况）
					LabConstructionFundingAudit schoolFundingAuditDone=labConstructionFundingAudit;
					mav.addObject("schoolFundingAuditDone", schoolFundingAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/schoolFundingAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存校领导审核(三级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveSchoolFundingAudit")
	public ModelAndView saveSchoolFundingAudit(@ModelAttribute LabConstructionFundingAudit schoolFundingAudit,@RequestParam int labConstructionFundingId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		schoolFundingAudit.setLabConstructionFunding(labConstructionFunding);
		schoolFundingAudit.setTag(3);//校领导审核标志位
		
		//根据labConstructionFunding找到对应的labConstructionPurchase
		Set<LabConstructionPurchase> labConstructionPurchases= labConstructionFunding.getLabConstructionProject().getLabConstructionPurchases();
		List<LabConstructionPurchase> labConstructionPurchasesList=new ArrayList<LabConstructionPurchase>(labConstructionPurchases);
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchasesList.get(0);
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		
		
		//当前登录人
		User user=shareService.getUser();
		schoolFundingAudit.setUser(user);
		labConstructionFundingAuditDAO.store(schoolFundingAudit);
		int audit_return=schoolFundingAudit.getCDictionary().getId();
		if (audit_return==612) {//审核通过
			labConstructionFunding.setAuditResults(2);//审核中状态
			labConstructionFunding.setStage(3);//校领导审核阶段结束
			
			//校领导（三级审核）完成后，给财务总监（四级审核）发送消息
			
			for (User cfo : cfos) {
				if (cfo.getUsername().equals(shareService.getUserDetail().getUsername())) {//如果当前登陆人是财务总监，则不发给自己
					
				}else {
					Message message= new Message();
					message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
					message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
					message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_THREE);
					String content="校领导审核通过，请财务总监审核";
					content+="<a  href='/xidlims/labconstruction/cfoFundingAudit?labConstructionFundingId=";
					content+=labConstructionFunding.getId();
					content+="'>点击查看</a>";
					message.setContent(content);
					message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
					message.setCreateTime(Calendar.getInstance());
					message.setUsername(cfo.getUsername());
					message=messageDAO.store(message);
				}
				
			}
			//同时，给项目建设中的设备申购申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_THREE);
			String content="校领导审核通过，等待财务总监审核";
			content+="<a  href='/xidlims/labconstruction/assetFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//设备申购申请人
			message=messageDAO.store(message);
		}
		if (audit_return==613) {//审核拒绝
			labConstructionFunding.setAuditResults(4);//审核拒绝状态
			labConstructionFunding.setStage(-1);//审核拒绝后，stage值被置为-1
			//同时，给项目建设中的设备申购申请人发送审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_FAIL_THREE);
			String content="未通过校领导审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/assetFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//设备申购申请人
			message=messageDAO.store(message);
			
		}
		labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		mav.setViewName("redirect:/labconstruction/listLabConstructionFundings?currpage=1&status=0");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：财务总监审核(四级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/cfoFundingAudit")
	public ModelAndView  cfoFundingAudit(@RequestParam Integer labConstructionFundingId) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		mav.addObject("labConstructionFunding", labConstructionFunding);
		//找到对应的项目立项
		LabConstructionProject labConstructionProject=labConstructionFunding.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);

		//找到对应的设备
		List<LabConstructionDevice>  labConstructionDevices=labConstructionPurchaseService.findlabConstructionDevicesByProjectAndTag(labConstructionProject, 2);
		mav.addObject("labConstructionDevices", labConstructionDevices);
		
		//审核结果选项（通过 or 拒绝）
		Set<CDictionary> results=cDictionaryDAO.findCDictionaryByCCategory("c_funding_audit_results");
		mav.addObject("results", results);
		
		//财务总监审核记录
		mav.addObject("cfoFundingAudit", new LabConstructionFundingAudit());
		
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		mav.addObject("excenterDirectors", excenterDirectors);
		mav.addObject("collegeLeaders", collegeLeaders);
		mav.addObject("assetManagers", assetManagers);
		mav.addObject("schoolLeaders", schoolLeaders);
		mav.addObject("cfos", cfos);
		mav.addObject("superAdmins", superAdmins);
		
		//找到labConstructionPurchase对应的审核结果（tag=4代表是财务总监的审核结果）
		Set<LabConstructionFundingAudit> audits=labConstructionFunding.getLabConstructionFundingAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId = new ArrayList<Integer>();
		for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
			if (labConstructionFundingAudit.getTag()==4) {
				auditsListId.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId中
			}
		}
		Collections.sort(auditsListId);//将auditsListId从小到大排序
		int auditId;
		if (auditsListId.size()>0) {
			auditId=auditsListId.get(auditsListId.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionFundingAudit cfoFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId);
			mav.addObject("cfoFundingAuditDone", cfoFundingAuditDone);
		}
	/*	if (audits.size()>0) {
			
			for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
				if (labConstructionFundingAudit.getTag()==4) {//确保是最后一次校财务总监审核（针对打回去的情况）
					LabConstructionFundingAudit cfoFundingAuditDone=labConstructionFundingAudit;
					mav.addObject("cfoFundingAuditDone", cfoFundingAuditDone);
					
				}
			}
		}*/
		
		mav.setViewName("/labconstruction/cfoFundingAudit.jsp");
		return mav;		
	}
	
	
	/****************************************************************************
	 * 功能：保存财务总监审核(四级审核)
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	@RequestMapping("/labconstruction/saveCfoFundingAudit")
	public ModelAndView saveCfoFundingAudit(@ModelAttribute LabConstructionFundingAudit cfoFundingAudit,@RequestParam int labConstructionFundingId){
		ModelAndView mav=new ModelAndView();
		//id对应的项目经费
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		cfoFundingAudit.setLabConstructionFunding(labConstructionFunding);
		cfoFundingAudit.setTag(4);//财务总监审核标志位
		
		//根据labConstructionFunding找到对应的labConstructionPurchase
		Set<LabConstructionPurchase> labConstructionPurchases= labConstructionFunding.getLabConstructionProject().getLabConstructionPurchases();
		List<LabConstructionPurchase> labConstructionPurchasesList=new ArrayList<LabConstructionPurchase>(labConstructionPurchases);
		LabConstructionPurchase labConstructionPurchase=labConstructionPurchasesList.get(0);
		
		//根据权限id查询用户
		List<User>  excenterDirectors=shareService.findUsersByAuthorityId(4);//实验中心主任
		List<User> collegeLeaders=shareService.findUsersByAuthorityId(13);//学院主管领导
		List<User> assetManagers=shareService.findUsersByAuthorityId(16);//资产管理处
		List<User> schoolLeaders=shareService.findUsersByAuthorityId(15);//校领导
		List<User> cfos=shareService.findUsersByAuthorityId(17);//财务总监
		List<User> superAdmins=shareService.findUsersByAuthorityId(11);//超级管理员
		
		//当前登录人
		User user=shareService.getUser();
		cfoFundingAudit.setUser(user);
		labConstructionFundingAuditDAO.store(cfoFundingAudit);
		int audit_return=cfoFundingAudit.getCDictionary().getId();
		if (audit_return==612) {//审核通过
			labConstructionFunding.setAuditResults(3);//审核通过状态
			labConstructionFunding.setStage(4);//财务总监审核阶段结束
			
			//财务总监审核完成后，给项目建设中的设备申购申请人审核成功的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_SUCCESS_FOUR);
			String content="财务总监审核通过，请查看审核结果";
			content+="<a  href='/xidlims/labconstruction/cfoFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//设备申购申请人
			message=messageDAO.store(message);
		}
		if (audit_return==613) {//审核拒绝
			labConstructionFunding.setAuditResults(4);//审核拒绝状态
			labConstructionFunding.setStage(-1);//审核拒绝后，stage值被置为-1
			
			//财务总监审核完成后，给项目建设中的设备申购申请人审核失败的消息
			Message message= new Message();
			message.setSendUser(shareService.getUserDetail().getCname());//当前登录人
			message.setSendCparty(shareService.getUserDetail().getSchoolAcademy().getAcademyName());//当前登录人所在学院
			message.setTitle(CommonConstantInterface.STR_LABCONSTRUCTIONFUNDING_FAIL_FOUR);
			String content="未通过财务总监审核，请修改后重新提交";
			content+="<a  href='/xidlims/labconstruction/cfoFundingAudit?labConstructionFundingId=";
			content+=labConstructionFunding.getId();
			content+="'>点击查看</a>";
			message.setContent(content);
			message.setMessageState(CommonConstantInterface.INT_Flag_ZERO);
			message.setCreateTime(Calendar.getInstance());
			message.setUsername(labConstructionPurchase.getUserByApplicant().getUsername());//设备申购申请人
			message=messageDAO.store(message);
		}
		labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		mav.setViewName("redirect:/labconstruction/listLabConstructionFundings?currpage=1&status=0");
		return mav;
	}
	
	/****************************************************************************
	 * 功能：根据项目立项id查询该项目经费的审核进度
	 * 作者：贺子龙
	 * 时间：2015-09-08
	 ****************************************************************************/
	@RequestMapping("/labconstruction/viewFundingStage")
	public @ResponseBody String viewFundingStage(@RequestParam Integer labConstructionFundingId) {
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		
		//查看项目建设对应的审核意见
		Set<LabConstructionFundingAudit> audits=labConstructionFunding.getLabConstructionFundingAudits();
		
		//新建一个整型list，用来存放所有的audits的Id
		List<Integer> auditsListId1 = new ArrayList<Integer>();
		List<Integer> auditsListId2 = new ArrayList<Integer>();
		List<Integer> auditsListId3 = new ArrayList<Integer>();
		List<Integer> auditsListId4 = new ArrayList<Integer>();
		for (LabConstructionFundingAudit labConstructionFundingAudit : audits) {
			if (labConstructionFundingAudit.getTag()==1) {
				auditsListId1.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId1中
				continue;
			}
			
			if (labConstructionFundingAudit.getTag()==2) {
				auditsListId2.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId2中
				continue;
			}
			
			if (labConstructionFundingAudit.getTag()==3) {
				auditsListId3.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId3中
				continue;
			}
			if (labConstructionFundingAudit.getTag()==4) {
				auditsListId4.add(labConstructionFundingAudit.getId());//将tag条件的id加入到auditsListId4中
			}
		}
		
		String s="";
		s+="<p>项目建设审核进度</p><br>";
		
		if (auditsListId4.size()>0 && labConstructionFunding.getStage()==4) {
			Collections.sort(auditsListId4);//将auditsListId4从小到大排序
			int auditId4=auditsListId4.get(auditsListId4.size()-1);//取最后一个，确保是最后一次财务总监领导审核（针对打回去的情况）
			LabConstructionFundingAudit cfoFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId4);
			s+=
					"<p>  财务总监审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+cfoFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+cfoFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId3);//将auditsListId3从小到大排序
			int auditId3=auditsListId3.get(auditsListId3.size()-1);//取最后一个，确保是最后一次校领导审核（针对打回去的情况）
			LabConstructionFundingAudit schoolFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId3);
			s+=
					"<p>  校领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+schoolFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+schoolFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次资产管理处审核（针对打回去的情况）
			LabConstructionFundingAudit assetFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId2);
			s+=
					"<p>  资产管理处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+assetFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+assetFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionFundingAudit deanFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId1);
			s+=
					"<p>  实验室中心主任审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+deanFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+deanFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			
		}
			
		if (auditsListId3.size()>0 && labConstructionFunding.getStage()==3) {
			s+="<p>  财务总监未审核 </p>";	
			Collections.sort(auditsListId3);//将auditsListId3从小到大排序
			int auditId3=auditsListId3.get(auditsListId3.size()-1);//取最后一个，确保是最后一次校领导审核（针对打回去的情况）
			LabConstructionFundingAudit schoolFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId3);
			s+=
					"<p>  校领导审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+schoolFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+schoolFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次资产管理处审核（针对打回去的情况）
			LabConstructionFundingAudit assetFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId2);
			s+=
					"<p>  资产管理处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+assetFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+assetFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionFundingAudit deanFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId1);
			s+=
					"<p>  实验室中心主任审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+deanFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+deanFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
		}
				
		if (auditsListId2.size()>0 && labConstructionFunding.getStage()==2) {
			s+="<p>  财务总监未审核 </p>";
			s+="<p>  校领导未审核 </p>";
			Collections.sort(auditsListId2);//将auditsListId2从小到大排序
			int auditId2=auditsListId2.get(auditsListId2.size()-1);//取最后一个，确保是最后一次资产管理处审核（针对打回去的情况）
			LabConstructionFundingAudit assetFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId2);
			s+=
					"<p>  资产管理处审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+assetFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+assetFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionFundingAudit deanFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId1);
			s+=
					"<p>  实验室中心主任审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+deanFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+deanFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
			
		}
					
		if (auditsListId1.size()>0 && labConstructionFunding.getStage()==1) {
			s+="<p>  财务总监未审核 </p>";
			s+="<p>  校领导未审核 </p>";
			s+="<p>  资产管理处未审核 </p>";
			Collections.sort(auditsListId1);//将auditsListId1从小到大排序
			int auditId1=auditsListId1.get(auditsListId1.size()-1);//取最后一个，确保是最后一次学院领导审核（针对打回去的情况）
			LabConstructionFundingAudit deanFundingAuditDone=labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(auditId1);
			s+=
					"<p>  实验室中心主任审核结果："+
					"<ul><li>&nbsp;&nbsp;&nbsp;&nbsp; 审核意见:"+deanFundingAuditDone.getComments()+"</li>"+
					"<li>&nbsp;&nbsp;&nbsp;&nbsp;审核结果:"+deanFundingAuditDone.getCDictionary().getCName()+"</li>"+
					"</ul>"
					+"</p>";
		}
		if (labConstructionFunding.getStage()==0) {
			s+="<p>  财务总监未审核 </p>";
			s+="<p>  校领导未审核 </p>";
			s+="<p>  资产管理处未审核 </p>";
			s+="<p>  实验室中心主任未审核 </p>";
			
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