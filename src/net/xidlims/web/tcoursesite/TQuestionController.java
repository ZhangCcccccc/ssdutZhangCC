package net.xidlims.web.tcoursesite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.web.aop.SystemServiceLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.ws.message.StringHeader;

import net.xidlims.dao.TCourseSiteDAO;


/**************************************************************************
 * Description:题库模块
 * 
 * @author：黄崔俊
 * @date ：2015-10-23 10:12:05
 **************************************************************************/
@Controller("TQuestionController")
public class TQuestionController<JsonResult> {
	/**************************************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 *************************************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,
				new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Double.class, true));
		binder.registerCustomEditor(Float.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(
				Float.class, true));
	}
	
	@Autowired
	private TAssignmentQuestionPoolService tAssignmentQuestionPoolService;
	@Autowired 
	private ShareService shareService;
	@Autowired 
	private TCourseSiteService tCourseSiteService;
	@Autowired 
	private TCourseSiteUserService tCourseSiteUserService;

	/**************************************************************************
	 * Description:查看题库列表
	 * @author：裴继超
	 * @date ：2016年7月8日16:11:44
	 **************************************************************************/
	@SystemServiceLog("查看题库列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/findQuestionList")
	public ModelAndView findQuestionList(@RequestParam Integer tCourseSiteId,Integer moduleType,Integer selectId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长    或者教务管理员   或者实验中心管理员   为教师权限
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//课程对应题库
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(tCourseSiteId);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		//新建题库
		TAssignmentQuestionpool tAssignmentQuestionpool = new TAssignmentQuestionpool();
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		
		/*//根据题库类型查找题库列表
		Integer type = 1;//查询公共题库
		Map<Integer, String> map = tAssignmentQuestionPoolService.findQuestionListByTypeAndUser(type,cid,null);
		mav.addObject("map", map);*/
		if(flag > 0){
			mav.setViewName("tcoursesite/question/questionPoolList.jsp");
		}else{
			mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId);
		}
		return mav;
	}
	
	/**************************************************************************
	 * Description:查看题库类别列表
	 * 
	 * @author：裴继超
	 * @date ：2016年7月8日16:12:09
	 **************************************************************************/
	@SystemServiceLog("查看题库类别列表")
	@RequestMapping("/tcoursesite/question/findQusetionListByTypeAndId")
	public @ResponseBody String findQusetionListByTypeAndId(@RequestParam Integer tCourseSiteId,@RequestParam Integer type,Integer id) {
		
		//根据题库类型查找题库列表
		Map<Integer, String> map = tAssignmentQuestionPoolService.findQuestionListByTypeAndUser(type,tCourseSiteId,null);
		//所有key值
		Set<Integer> set = map.keySet();
		String s = "<option value=''>请选择</option>";
		//题库列表
		for (Integer questionId : set) {
			if (id==null||!questionId.equals(id)) {
				s += "<option value='"+questionId+"'";
				if (id!=null) {
					 
					TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(id);
					if (tAssignmentQuestionpool.getTAssignmentQuestionpool()!=null&&tAssignmentQuestionpool.getTAssignmentQuestionpool().getQuestionpoolId().equals(questionId)) {
						s += " selected='selected' ";
					}
				}
				s += ">"+map.get(questionId)+"</option>";
			}
		}
		
		return shareService.htmlEncode(s);
	}
	
	
	/**************************************************************************
	 * Description:保存题库
	 * 
	 * @author：裴继超
	 * @date ：2016年7月8日16:12:37
	 **************************************************************************/
	@SystemServiceLog("保存题库")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/saveQuestionPool")
	public ModelAndView saveQuestionPool(@RequestParam Integer tCourseSiteId,@ModelAttribute TAssignmentQuestionpool tAssignmentQuestionpool) {
		
		ModelAndView mav = new ModelAndView();
		
		//保存题库类别
		tAssignmentQuestionPoolService.saveTAssignmentQuestionPool(tCourseSiteId,tAssignmentQuestionpool);
		mav.setViewName("redirect:/tcoursesite/question/findQuestionList?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:保存题库类别
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23 16:37:10
	 **************************************************************************/
	@SystemServiceLog("保存题库类别")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/saveCheckQuestionPool")
	public ModelAndView saveCheckQuestionPool(@RequestParam Integer tCourseSiteId,@ModelAttribute TAssignmentQuestionpool tAssignmentQuestionpool,@RequestParam Integer examId) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("examId", examId);
		//保存题库类别
		tAssignmentQuestionPoolService.saveTAssignmentQuestionPool(tCourseSiteId,tAssignmentQuestionpool);
		mav.setViewName("redirect:/tcoursesite/question/checkQuestionList");
		return mav;
	}
	
	/**************************************************************************
	 * Description:查询题库类别信息
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-26 10:46:14
	 **************************************************************************/
	@SystemServiceLog("查询题库类别信息")
	@RequestMapping("/tcoursesite/question/findQusetionStringById")
	public @ResponseBody String[] findQusetionStringById(@RequestParam Integer tCourseSiteId,@RequestParam Integer id) {
		String[] ss = new String[4];
		//根据id查询题库类别
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(id);
		ss[0] = tAssignmentQuestionpool.getTitle();
		ss[1] = tAssignmentQuestionpool.getType().toString();
		ss[2] = tAssignmentQuestionpool.getDescription();
		ss[3] = tAssignmentQuestionpool.getTAssignmentQuestionpool()==null?"":tAssignmentQuestionpool.getTAssignmentQuestionpool().getQuestionpoolId().toString();
		return ss;
	}
	
	/**************************************************************************
	 * Description:根据id查询题库中的试题信息
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27 13:55:15
	 **************************************************************************/
	@SystemServiceLog("试题列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/findTAssignmentItemsByQuestionId")
	public ModelAndView findTAssignmentItemsByQuestionId(@RequestParam Integer tCourseSiteId,@RequestParam Integer id,Integer currpage) {
		ModelAndView mav = new ModelAndView();
		
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		mav.addObject("tCourseSiteId",tCourseSiteId);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = 2;
		if(user!=null&&(user.getAuthorities().toString().contains("EXCENTERDIRECTOR")
		        ||user.getAuthorities().toString().contains("LABMANAGER")
		        ||user.getAuthorities().toString().contains("SUPERADMIN")
		        ||user.getAuthorities().toString().contains("DEAN")
		        ||user.getAuthorities().toString().contains("SCHOOLLEADER")
		        ||user.getAuthorities().toString().contains("ASSOCIATEDEAN")
		        ||user.getAuthorities().toString().contains("EDUCATIONADMIN")
		        ||user.getAuthorities().toString().contains("LABCENTERMANAGER"))){
			flag = 1;//实验中心主任   或者实验室管理员   或者超级管理员   或者教务处   或者校领导   或者教学副院长     或者教务管理员   或者实验中心管理员   为教师权限
		}else if(user!=null&&user.getAuthorities().toString().contains("TEACHER")){
			if (tCourseSite.getUserByCreatedBy().getUsername().equals(user.getUsername())) {
				flag = 1;//如果当前登陆人是本人开的课程，则是老师身份
			}else if (tCourseSiteUserService.isSTeacherBySiteId(tCourseSiteId,user.getUsername())){
				flag = 1;//如果当前登陆人是本课程的助教，则是老师身份
			}else if (tCourseSiteUserService.isUserBelongToTeacher(tCourseSiteId)){
				flag=1; //如果当前登陆人参与这门课，则是老师身份
			}else {
				flag = 0;//如果当前登陆人不是本人开的课程，则是学生身份
			}
			
		}else if(user!=null&&user.getAuthorities().toString().contains("STUDENT")){
			flag = 0;
		}
		mav.addObject("flag", flag);
		//根据id查询题库
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(id);
		
		//获取该题库下总的题目数
		int totalRecords = tAssignmentQuestionpool.getTAssignmentItems().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		//分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize,  totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TAssignmentItem> tAssignmentItems = tAssignmentQuestionPoolService.findTAssignmentItemsByQuestionId(id,currpage,pageSize);
		//将所属题库传回去，分页查询需要用到题库id
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		//该题库下题目列表
		mav.addObject("tAssignmentItems", tAssignmentItems);
		mav.setViewName("tcoursesite/question/tAssignmentItemList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:根据题库id查询试题信息
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-4 14:27:38
	 **************************************************************************/
	@SystemServiceLog("试题信息")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/findItemListByQuestionId")
	public ModelAndView findItemListByQuestionId(@RequestParam Integer questionId,Integer currpage,Integer sectionId) {
		ModelAndView mav = new ModelAndView();
		//根据id获取题库
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(questionId);
		
		//获取该题库下总的题目数
		int totalRecords = tAssignmentQuestionpool.getTAssignmentItems().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		//分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//根据题库id获取题目
		List<TAssignmentItem> tAssignmentItems = tAssignmentQuestionPoolService.findTAssignmentItemsByQuestionId(questionId,currpage,pageSize);
		//将所属题库传回去，分页查询需要用到题库id
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		//该题库下题目列表
		mav.addObject("tAssignmentItems", tAssignmentItems);
		mav.addObject("sectionId", sectionId);
		mav.setViewName("tcoursesite/question/checkTAssignmentItemList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:根据题库id导入题目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27 21:31:05
	 **************************************************************************/
	@SystemServiceLog("导入题目")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/importItemsByQuestionId")
	public ModelAndView importItemsByQuestionId(@RequestParam Integer tCourseSiteId,HttpServletRequest request,@RequestParam Integer questionId) {
		ModelAndView mav = new ModelAndView();
		// 文件名称
		String fileName = shareService.getUpdateFileSavePath(request);
		// 服务器地址
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		// 文件的全部地址
		String filePath = logoRealPathDir + fileName;
		//表格文件导入
		if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
			try{
				int result = tAssignmentQuestionPoolService.importTAssignmentItemsXls(filePath, questionId);
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		//txt文件导入
		if (fileName.endsWith("txt")) {
			
			String result = tAssignmentQuestionPoolService.importTAssignmentItemsTxt(filePath, questionId);
			if (result!=null) {
				mav.addObject("result", result);
			}			
		}
		mav.setViewName("redirect:/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId="+tCourseSiteId+"&currpage=1&id="+questionId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:根据题库id删除题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27 21:31:05
	 **************************************************************************/
	@SystemServiceLog("删除题库")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/deleteQuestionPoolById")
	public ModelAndView deleteQuestionPoolById(@RequestParam Integer tCourseSiteId,@RequestParam Integer questionId) {
		ModelAndView mav = new ModelAndView();
		//根据题库id删除题库
		tAssignmentQuestionPoolService.deleteTAssignmentQuestionPoolById(questionId);
		mav.setViewName("redirect:/tcoursesite/question/findQuestionList?tCourseSiteId="+tCourseSiteId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:根据试题id删除试题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27 21:31:05
	 **************************************************************************/
	@SystemServiceLog("删除试题")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/deleteTAssignmentItemById")
	public ModelAndView deleteTAssignmentItemById(@RequestParam Integer tCourseSiteId,@RequestParam Integer questionId,Integer itemId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//根据试题id删除试题
		tAssignmentQuestionPoolService.deleteTAssignmentItemById(itemId,questionId);
		
		mav.addObject("tCourseSiteId", tCourseSiteId);
		mav.addObject("id", questionId);
		mav.addObject("currpage", 1);
		mav.setViewName("redirect:/tcoursesite/question/findTAssignmentItemsByQuestionId");
		return mav;
	}
	
	/**************************************************************************
	 * Description:查看可供导入的题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-2 15:09:21
	 **************************************************************************/
	@SystemServiceLog("查看可供导入的题库")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/checkQuestionList")
	public ModelAndView checkQuestionList(@RequestParam Integer tCourseSiteId,@RequestParam Integer examId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(tCourseSiteId);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		//新建题库
		TAssignmentQuestionpool tAssignmentQuestionpool = new TAssignmentQuestionpool();
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		//examId
		mav.addObject("examId", examId);
		mav.setViewName("tcoursesite/question/checkQuestionList.jsp");
		return mav;
	}
	/**************************************************************************
	 * Description:查看可导入题目的题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-4 14:55:50
	 **************************************************************************/
	@SystemServiceLog("查看可导入题目的题库")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/checkQuestionListForSection")
	public ModelAndView checkQuestionListForSection(@RequestParam Integer tCourseSiteId,@RequestParam Integer sectionId) {
		ModelAndView mav = new ModelAndView();
		// 选择的课程中心
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(tCourseSiteId);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		//新建题库
		TAssignmentQuestionpool tAssignmentQuestionpool = new TAssignmentQuestionpool();
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		//sectionId大题id
		mav.addObject("sectionId", sectionId);
		mav.setViewName("tcoursesite/question/checkQuestionListForSection.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:将测试中的题目导入题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-2 15:23:30
	 **************************************************************************/
	@SystemServiceLog("将测试中的题目导入题库")
	@ResponseBody
	@RequestMapping("/tcoursesite/question/checkQuestion")
	public ModelAndView checkQuestion(@RequestParam Integer tCourseSiteId,@RequestParam Integer examId,Integer questionId) {
		ModelAndView mav = new ModelAndView();
		//根据题库id将试题导入题库
		tAssignmentQuestionPoolService.importTAssignmentItemsByQuestionId(questionId,examId);
		mav.setViewName("redirect:/tcoursesite/question/findTAssignmentItemsByQuestionId?currpage=1&id="+questionId);
		return mav;
	}
	/**************************************************************************
	 * Description:课程-题库-题库复制
	 * @author：李军凯
	 * @date ：2016-09-02
	 **************************************************************************/
	@RequestMapping("/tcoursesite/question/findQusetionStringByUsername")
	public @ResponseBody Map<Integer,String> findQusetionStringByUsername(@RequestParam String username,@RequestParam Integer tCourseSiteId) {		
		Map<Integer,String> map = new HashMap<Integer,String>();
		//新建题库List
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = new ArrayList<TAssignmentQuestionpool>();
		//用户和课程对应题库List
		tAssignmentQuestionpools=tAssignmentQuestionPoolService.findQuestionListByUser(username,tCourseSiteId);	
		//题库id  题库名称
		for(TAssignmentQuestionpool t:tAssignmentQuestionpools){
		map.put(t.getQuestionpoolId(),t.getTitle());
		}		
		return map;
	}	
	/**************************************************************************
	 * Description:课程-题库-题库复制
	 * @author：李军凯
	 * @date ：2016-09-03
	 **************************************************************************/
	@SystemServiceLog("复制题库")	
	@RequestMapping("/tcoursesite/question/saveCopyQuestionPool")
	public @ResponseBody ModelAndView saveCopyQuestionPool(@RequestParam Integer tCourseSiteId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();	
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		//获取选中的题库id
		String[] poolIds= request.getParameterValues("checkname");		
		Set<TAssignmentQuestionpool> tAssignmentQuestionpools = new HashSet<TAssignmentQuestionpool>();
		Set<TCourseSite> tCourseSites = new HashSet<TCourseSite>();
		tCourseSites.add(tCourseSite);
		//获取题库列表
		tAssignmentQuestionpools=tAssignmentQuestionPoolService.findCopyQuestionListByUser(poolIds);
		for(TAssignmentQuestionpool tAssignmentQuestionpool:tAssignmentQuestionpools){
		//导入题库
		tAssignmentQuestionpool.setTCourseSites(tCourseSites);
		tAssignmentQuestionPoolService.saveCopyQuestionList(tAssignmentQuestionpool);
		//tCourseSite.setTAssignmentQuestionpools(tAssignmentQuestionpools);
		}
		//tAssignmentQuestionPoolService.saveCopyQuestionListByUser(tCourseSite);
		mav.setViewName("redirect:/tcoursesite/question/findQuestionList?tCourseSiteId="+tCourseSiteId);
		return mav;
	}

	/**************************************************************************************
	 * Description:课程-题库-题库导出
	 * @author：李军凯
	 * @date ：2016-09-07
	 *************************************************************************************/
	@RequestMapping("/tcoursesite/question/exportQuestionPoolById")
	public void exportQuestionPoolById(@RequestParam Integer questionId,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//题库导出
		tAssignmentQuestionPoolService.exportTAssignmentItemById(questionId,request,response);

	}
	/**************************************************************************************
	 * Description:课程-题库-题库导出
	 * @author：李军凯
	 * @date ：2016-09-08
	 *************************************************************************************/
	@RequestMapping("/tcoursesite/question/exportExcelQuestionPoolById")
	public void exportExcelQuestionPoolById(@RequestParam Integer questionId,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//题库导出
		tAssignmentQuestionPoolService.exportExcelQuestionPoolById(questionId,request,response);

	}
	
	/**************************************************************************
	 * Description:检查指定类型题目数量是否超出题库该类型题目数量
	 * 
	 * @author：于侃
	 * @date ：2016年10月27日 14:18:05
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/question/checkTestItemCount")
	public String checkTestItemCount(Integer questionpoolId,Integer quantity,String type){
//		//题目类型：1多选，2对错，4单选，5简答题，8填空，9匹配
//		Integer itemType = -1;
//		if(type.indexOf("多选") != -1){
//			itemType = 1;
//		}else if(type.indexOf("判断") != -1){
//			itemType = 2;
//		}else if(type.indexOf("单选") != -1){
//			itemType = 4;
//		}else if(type.indexOf("填空") != -1){
//			itemType = 8;
//		}
		return tAssignmentQuestionPoolService.checkTestItemCount(questionpoolId, quantity, Integer.parseInt(type));
	}
	
}