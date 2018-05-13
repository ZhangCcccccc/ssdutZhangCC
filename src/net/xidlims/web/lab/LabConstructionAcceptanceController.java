package net.xidlims.web.lab;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabConstructionAcceptance;
import net.xidlims.domain.LabConstructionFunding;
import net.xidlims.domain.LabConstructionProject;
import net.xidlims.domain.MLabConstructionProjectUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.CommonDocumentService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabConstructionAcceptanceService;
import net.xidlims.service.lab.LabConstructionFundingService;
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

/**
 * Spring MVC controller that handles CRUD requests for LabConstructionAcceptance entities
 * 
 */

@Controller("LabConstructionAcceptanceController")
public class LabConstructionAcceptanceController {
	
	@Autowired private ShareService shareService;
	@Autowired private CommonDocumentService commonDocumentService;
	@Autowired private LabConstructionAcceptanceService labConstructionAcceptanceService;
	@Autowired private LabConstructionFundingService labConstructionFundingService;
	@Autowired private LabConstructionProjectService labConstructionProjectService;

	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
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
	 * 功能：分页列出满足查询条件的所有实验室建设项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 ***********************************/
	@RequestMapping("/labconstruction/listLabConstructionAcceptances")
	public ModelAndView listLabConstructionAcceptances(@RequestParam int currpage, @ModelAttribute LabConstructionAcceptance labConstructionAcceptance) {
		ModelAndView mav = new ModelAndView();
		//当前登录人
		User user=shareService.getUser();
		
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//项目立项总记录数
//		int totalRecords = labConstructionAcceptanceService.findAllLabConstructionAcceptancesByQueryCount(labConstructionAcceptance);
		//分页列出符合条件的项目立项记录
		List <LabConstructionAcceptance> listLabConstructionAcceptances=labConstructionAcceptanceService.findAllLabConstructionAcceptancesByQuery(currpage, pageSize, labConstructionAcceptance);
		//项目建设总记录数（查询出来的listLabConstructionAcceptances中与当前登陆人相关的项目经费记录数）
		int totalRecords=0;
		String username="["+user.getUsername()+"]";//防止其他人的工号包含当前登陆人
		mav.addObject("username", username);//将[工号](如：[03010])传到后台，使fn:contains更精确
		for (LabConstructionAcceptance acceptance : listLabConstructionAcceptances) {
			LabConstructionProject project=acceptance.getLabConstructionProject();//找到对应的LabConstructionProject
			Set<MLabConstructionProjectUser> projectMambers=project.getMLabConstructionProjectUsers();
			if ((projectMambers.toString().indexOf(username)!=-1)||project.getUser().getUsername()==user.getUsername()||
					project.getUserByCreatedBy().getUsername()==user.getUsername()) {
				
				totalRecords++;
			}
		}
		
		
		mav.addObject("pageModel", shareService.getPage(currpage, pageSize, totalRecords));
		mav.addObject("user",user);
		mav.addObject("listLabConstructionAcceptances",listLabConstructionAcceptances);
		mav.setViewName("labconstruction/listLabConstructionAcceptances.jsp");
		return mav;
	}
	
	/***********************************
	 * 功能：新建项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 ***********************************/
	
	@RequestMapping("/labconstruction/newLabConstructionAcceptance")
	public ModelAndView newLabConstructionAcceptance(@RequestParam int labConstructionFundingId){
		ModelAndView mav = new ModelAndView();
		LabConstructionFunding labConstructionFunding=labConstructionFundingService.findLabConstructionFundingByPrimaryKey(labConstructionFundingId);
		LabConstructionProject labConstructionProject = labConstructionFunding.getLabConstructionProject();
		mav.addObject("labConstructionAcceptance", new LabConstructionAcceptance());
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("isEdit", false);
		mav.setViewName("labconstruction/editLabConstructionAcceptance.jsp");
		return mav;
	}
	
	
	/***********************************
	 * 功能：编辑项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 ***********************************/
	
	@RequestMapping("/labconstruction/editLabConstructionAcceptance")
	public ModelAndView editLabConstructionAcceptance(@RequestParam int labConstructionAcceptanceId){
		ModelAndView mav = new ModelAndView();
		LabConstructionAcceptance labConstructionAcceptance=labConstructionAcceptanceService.findLabConstructionAcceptanceByPrimaryKey(labConstructionAcceptanceId);
		//得到该项目经费对应的项目立项
		LabConstructionProject labConstructionProject = labConstructionAcceptance.getLabConstructionProject();
		//得到项目经费所有的附件
		Set<CommonDocument> commonDocuments=labConstructionAcceptance.getCommonDocuments();
		//附件分为四类（ 1 项目建设阶段资料  2项目教学阶段资料  3仪器设备资料   4 综合效益资料）
		List<CommonDocument> commonDocuments1=new ArrayList<CommonDocument>();
		List<CommonDocument> commonDocuments2=new ArrayList<CommonDocument>();
		List<CommonDocument> commonDocuments3=new ArrayList<CommonDocument>();
		List<CommonDocument> commonDocuments4=new ArrayList<CommonDocument>();
		for (CommonDocument commonDocument : commonDocuments) {
			if (commonDocument.getFlag()==1) {
				commonDocuments1.add(commonDocument);
			}
			if (commonDocument.getFlag()==2) {
				commonDocuments2.add(commonDocument);
			}
			if (commonDocument.getFlag()==3) {
				commonDocuments3.add(commonDocument);
			}
			if (commonDocument.getFlag()==4) {
				commonDocuments4.add(commonDocument);
			}
		}
		
		mav.addObject("commonDocuments1", commonDocuments1);
		mav.addObject("commonDocuments2", commonDocuments2);
		mav.addObject("commonDocuments3", commonDocuments3);
		mav.addObject("commonDocuments4", commonDocuments4);
		
		mav.addObject("labConstructionAcceptance", labConstructionAcceptance);
		mav.addObject("labConstructionProject",labConstructionProject);
		mav.addObject("isEdit", true);
		mav.setViewName("labconstruction/editLabConstructionAcceptance.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：保存项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionAcceptance")
	public String saveLabConstructionAcceptance(@ModelAttribute LabConstructionAcceptance labConstructionAcceptance,
			@RequestParam int labConstructionProjectId){
		
		LabConstructionProject labConstructionProject = labConstructionProjectService.findLabConstructionProjectByPrimaryKey(labConstructionProjectId);
		//通过labConstructionProject找到对应的labConstructionFunding
		Set<LabConstructionFunding> labConstructionFundings=labConstructionProject.getLabConstructionFundings();
		List<LabConstructionFunding> labConstructionFundingsList=new ArrayList<LabConstructionFunding>(labConstructionFundings);
		LabConstructionFunding labConstructionFunding=labConstructionFundingsList.get(0);
		//找到labConstructionFunding之后，将其stage设置为5，用stage来限制每个项目建设只能生成一个项目经费。
		labConstructionFunding.setStage(5);
		labConstructionFundingService.saveLabConstructionFunding(labConstructionFunding);
		
		
		labConstructionAcceptance.setLabConstructionProject(labConstructionProject);
		labConstructionAcceptance.setStage(0);//未审核阶段
		labConstructionAcceptance=labConstructionAcceptanceService.saveLabConstructionAcceptance(labConstructionAcceptance);
		
		return "redirect:/labconstruction/editLabConstructionAcceptance?labConstructionAcceptanceId="+labConstructionAcceptance.getId();
	}
	/********************************
	 * 功能：保存项目经费2-跳转页面
	 * 作者：贺子龙
	 * 日期：2015-10-05
	 *********************************/
	@RequestMapping("/labconstruction/saveLabConstructionAcceptanceAll")
	public String saveLabConstructionAcceptanceAll(){
		
		return "redirect:/labconstruction/listLabConstructionAcceptances?currpage=1";
	}
	
	/********************************
	 * 功能：查看项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@RequestMapping("/labconstruction/veiwLabConstructionAcceptance")
	public ModelAndView veiwLabConstructionAcceptance(@RequestParameters Integer labConstructionAcceptanceId){
		ModelAndView mav=new ModelAndView();
		LabConstructionAcceptance labConstructionAcceptance=labConstructionAcceptanceService.findLabConstructionAcceptanceByPrimaryKey(labConstructionAcceptanceId);
		LabConstructionProject labConstructionProject=labConstructionAcceptance.getLabConstructionProject();
		mav.addObject("labConstructionProject", labConstructionProject);
		//得到项目经费所有的附件
		Set<CommonDocument> commonDocuments=labConstructionAcceptance.getCommonDocuments();
		//附件分为四类（ 1 项目建设阶段资料  2项目教学阶段资料  3仪器设备资料   4 综合效益资料）
		List<CommonDocument> commonDocuments1=new ArrayList<CommonDocument>();
		List<CommonDocument> commonDocuments2=new ArrayList<CommonDocument>();
		List<CommonDocument> commonDocuments3=new ArrayList<CommonDocument>();
		List<CommonDocument> commonDocuments4=new ArrayList<CommonDocument>();
		for (CommonDocument commonDocument : commonDocuments) {
			if (commonDocument.getFlag()==1) {
				commonDocuments1.add(commonDocument);
			}
			if (commonDocument.getFlag()==2) {
				commonDocuments2.add(commonDocument);
			}
			if (commonDocument.getFlag()==3) {
				commonDocuments3.add(commonDocument);
			}
			if (commonDocument.getFlag()==4) {
				commonDocuments4.add(commonDocument);
			}
		}
		
		mav.addObject("commonDocuments1", commonDocuments1);
		mav.addObject("commonDocuments2", commonDocuments2);
		mav.addObject("commonDocuments3", commonDocuments3);
		mav.addObject("commonDocuments4", commonDocuments4);
		
		mav.addObject("labConstructionAcceptance", labConstructionAcceptance);
		mav.setViewName("/labconstruction/veiwLabConstructionAcceptance.jsp");
		return mav;
	}
	
	/********************************
	 * 功能：提交项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@RequestMapping("/labconstruction/submitLabConstructionAcceptance")
	public String submitLabConstructionAcceptance(@RequestParam Integer labConstructionAcceptanceId){
		LabConstructionAcceptance labConstructionAcceptance=labConstructionAcceptanceService.findLabConstructionAcceptanceByPrimaryKey(labConstructionAcceptanceId);
		labConstructionAcceptance.setStage(1);
		labConstructionAcceptanceService.saveLabConstructionAcceptance(labConstructionAcceptance);
		return "redirect:/labconstruction/listLabConstructionAcceptances?currpage=1&status=0";
	}
	
	/********************************
	 * 功能：删除项目验收
	 * 作者：贺子龙
	 * 日期：2015-10-14
	 *********************************/
	@RequestMapping("/labconstruction/deleteLabConstructionAcceptance")
	public String deleteLabConstructionAcceptance(@RequestParameters Integer labConstructionAcceptanceId){
		labConstructionAcceptanceService.deleteLabConstructionAcceptance(labConstructionAcceptanceId);
		return "redirect:/labconstruction/listLabConstructionAcceptances?currpage=1&status=0";
	}
	
	/****************************************************************************
	 * 功能：给项目验收上传附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@RequestMapping("/labconstruction/acceptanceDocumentUpload")
	public @ResponseBody String acceptanceDocumentUpload(HttpServletRequest request, HttpServletResponse response, 
			BindException errors,Integer id,int flag) throws Exception {
		labConstructionAcceptanceService.acceptanceDocumentUpload(request, response,id,1,flag);
		return "ok";
	}
	/****************************************************************************
	 * 功能：删除项目验收附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@RequestMapping("/labconstruction/deleteAcceptanceDocument")
	public ModelAndView deleteAcceptanceDocument(@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的项目附件
		CommonDocument doc=commonDocumentService.findCommonDocumentByPrimaryKey(id);
		//附件所对应的项目立项
		LabConstructionAcceptance labConstructionAcceptance=doc.getLabConstructionAcceptance();
		commonDocumentService.deleteCommonDocument(doc);
		//删除服务器上的文件
		mav.setViewName("redirect:/labconstruction/editLabConstructionAcceptance?labConstructionAcceptanceId="+labConstructionAcceptance.getId());
		
		return mav;
	}
	
	/****************************************************************************
	 * 功能：下载项目立项附件
	 * 作者：贺子龙
	 * 时间：2015-10-14
	 ****************************************************************************/
	@RequestMapping("/labconstruction/downloadAcceptanceDocument")
	public @ResponseBody String downloadAcceptanceDocument(HttpServletRequest request, HttpServletResponse response,Integer id) throws Exception {
		labConstructionAcceptanceService.downloadAcceptanceDocument(request, response,id);
		return "ok";
	}
}