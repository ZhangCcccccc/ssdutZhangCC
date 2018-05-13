package net.xidlims.web.teaching;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TExperimentSkill;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TExperimentSkillService;
import net.xidlims.service.teaching.TAssignmentSectionService;

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


/******************************************************************************************
 * @功能：题库模块
 * @作者：黄崔俊
 * @时间：2015-10-23 10:12:05
 *****************************************************************************************/
@Controller("TeachingQuestionController")
@SessionAttributes("selected_courseSite")
public class TeachingQuestionController<JsonResult> {
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
	private TAssignmentSectionService tAssignmentSectionService;
	@Autowired 
	private ShareService shareService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TExperimentSkillService tExperimentSkillService;

	/**************************************************************************************
	 * @功能：查看题库类别列表
	 * @作者：黄崔俊
	 * @日期：2015-10-23 10:13:40
	 *************************************************************************************/
	@RequestMapping("/teaching/question/findQuestionList")
	public ModelAndView findQuestionList(@ModelAttribute("selected_courseSite")Integer cid) {
		ModelAndView mav = new ModelAndView();
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(cid);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		
		TAssignmentQuestionpool tAssignmentQuestionpool = new TAssignmentQuestionpool();
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		
		/*//根据题库类型查找题库列表
		Integer type = 1;//查询公共题库
		Map<Integer, String> map = tAssignmentQuestionPoolService.findQuestionListByTypeAndUser(type,cid,null);
		mav.addObject("map", map);*/
		mav.setViewName("teaching/question/questionPoolList.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：查看题库类别列表
	 * @作者：黄崔俊
	 * @日期：2015-10-23 10:13:40
	 *************************************************************************************/
	@RequestMapping("/teaching/question/findQusetionListByTypeAndId")
	public @ResponseBody String findQusetionListByTypeAndId(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam Integer type,Integer id) {
		
		//根据题库类型查找题库列表
		Map<Integer, String> map = tAssignmentQuestionPoolService.findQuestionListByTypeAndUser(type,cid,null);
		Set<Integer> set = map.keySet();
		String s = "<option value=''>请选择</option>";
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
	
	
	/**************************************************************************************
	 * @功能：保存题库类别
	 * @作者：黄崔俊
	 * @日期：2015-10-23 16:37:10
	 *************************************************************************************/
	@RequestMapping("/teaching/question/saveQuestionPool")
	public ModelAndView saveQuestionPool(@ModelAttribute("selected_courseSite")Integer cid,@ModelAttribute TAssignmentQuestionpool tAssignmentQuestionpool) {
		
		ModelAndView mav = new ModelAndView();
		
		//保存题库类别
		tAssignmentQuestionPoolService.saveTAssignmentQuestionPool(cid,tAssignmentQuestionpool);
		mav.setViewName("redirect:/teaching/question/findQuestionList");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：保存题库类别
	 * @作者：黄崔俊
	 * @日期：2015-10-23 16:37:10
	 *************************************************************************************/
	@RequestMapping("/teaching/question/saveCheckQuestionPool")
	public ModelAndView saveCheckQuestionPool(@ModelAttribute("selected_courseSite")Integer cid,@ModelAttribute TAssignmentQuestionpool tAssignmentQuestionpool,@RequestParam Integer examId) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("examId", examId);
		//保存题库类别
		tAssignmentQuestionPoolService.saveTAssignmentQuestionPool(cid,tAssignmentQuestionpool);
		mav.setViewName("redirect:/teaching/question/checkQuestionList");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：查询题库类别信息
	 * @作者：黄崔俊
	 * @日期：2015-10-26 10:46:14
	 *************************************************************************************/
	@RequestMapping("/teaching/question/findQusetionStringById")
	public @ResponseBody String[] findQusetionStringById(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam Integer id) {
		String[] ss = new String[4];
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(id);
		ss[0] = tAssignmentQuestionpool.getTitle();
		ss[1] = tAssignmentQuestionpool.getType().toString();
		ss[2] = tAssignmentQuestionpool.getDescription();
		ss[3] = tAssignmentQuestionpool.getTAssignmentQuestionpool()==null?"":tAssignmentQuestionpool.getTAssignmentQuestionpool().getQuestionpoolId().toString();
		return ss;
	}
	
	/**************************************************************************************
	 * @功能：根据id查询题库中的试题信息
	 * @作者：黄崔俊
	 * @日期：2015-10-27 13:55:15
	 *************************************************************************************/
	@RequestMapping("/teaching/question/findTAssignmentItemsByQuestionId")
	public ModelAndView findTAssignmentItemsByQuestionId(@RequestParam Integer id,Integer currpage) {
		ModelAndView mav = new ModelAndView();
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(id);
		
		//获取该题库下总的题目数
		int totalRecords = tAssignmentQuestionpool.getTAssignmentItems().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TAssignmentItem> tAssignmentItems = tAssignmentQuestionPoolService.findTAssignmentItemsByQuestionId(id,currpage,pageSize);
		//将所属题库传回去，分页查询需要用到题库id
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		//该题库下题目列表
		mav.addObject("tAssignmentItems", tAssignmentItems);
		mav.setViewName("teaching/question/tAssignmentItemList.jsp");
		return mav;
	}
	/**************************************************************************************
	 * @功能：根据题库id查询试题信息
	 * @作者：黄崔俊
	 * @日期：2015-11-4 14:27:38
	 *************************************************************************************/
	@RequestMapping("/teaching/question/findItemListByQuestionId")
	public ModelAndView findItemListByQuestionId(@RequestParam Integer questionId,Integer currpage,Integer sectionId) {
		ModelAndView mav = new ModelAndView();
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(questionId);
		
		//获取该题库下总的题目数
		int totalRecords = tAssignmentQuestionpool.getTAssignmentItems().size();
		mav.addObject("totalRecords", totalRecords);
		//设置分页大小
		int pageSize = 20;
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		List<TAssignmentItem> tAssignmentItems = tAssignmentQuestionPoolService.findTAssignmentItemsByQuestionId(questionId,currpage,pageSize);
		//将所属题库传回去，分页查询需要用到题库id
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		//该题库下题目列表
		mav.addObject("tAssignmentItems", tAssignmentItems);
		mav.addObject("sectionId", sectionId);
		mav.addObject("questionId", questionId);
		mav.addObject("examInfo",tAssignmentSectionService.findTAssignmentSectionById(sectionId).getTAssignment());
		mav.setViewName("teaching/question/checkTAssignmentItemList.jsp");
		return mav;
	}
	/**************************************************************************************
	 * @功能：根据题库id导入题目
	 * @作者：黄崔俊
	 * @日期：2015-10-27 21:31:05
	 *************************************************************************************/
	@RequestMapping("/teaching/question/importItemsByQuestionId")
	public ModelAndView importItemsByQuestionId(HttpServletRequest request,@RequestParam Integer questionId) {
		ModelAndView mav = new ModelAndView();
		// 文件名称
		String fileName = shareService.getUpdateFileSavePath(request);
		// 服务器地址
		String logoRealPathDir = request.getSession().getServletContext().getRealPath("/");
		// 文件的全部地址
		String filePath = logoRealPathDir + fileName;

		if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
			try{
				tAssignmentQuestionPoolService.importTAssignmentItemsXls(filePath, questionId);
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
		
		mav.setViewName("redirect:/teaching/question/findTAssignmentItemsByQuestionId?currpage=1&id="+questionId);
		return mav;
	}
	
	
	/**************************************************************************************
	 * @功能：根据题库id删除题库
	 * @作者：黄崔俊
	 * @日期：2015-10-27 21:31:05
	 *************************************************************************************/
	@RequestMapping("/teaching/question/deleteQuestionPoolById")
	public ModelAndView deleteQuestionPoolById(@RequestParam Integer questionId) {
		ModelAndView mav = new ModelAndView();
		
		tAssignmentQuestionPoolService.deleteTAssignmentQuestionPoolById(questionId);
		mav.setViewName("redirect:/teaching/question/findQuestionList");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：根据试题id删除试题
	 * @作者：黄崔俊
	 * @日期：2015-10-27 21:31:05
	 *************************************************************************************/
	@RequestMapping("/teaching/question/deleteTAssignmentItemById")
	public ModelAndView deleteTAssignmentItemById(@RequestParam Integer questionId,Integer itemId) {
		ModelAndView mav = new ModelAndView();
		
		tAssignmentQuestionPoolService.deleteTAssignmentItemById(itemId,questionId);
		
		mav.addObject("id", questionId);
		mav.addObject("currpage", 1);
		mav.setViewName("redirect:/teaching/question/findTAssignmentItemsByQuestionId");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：查看可供导入的题库
	 * @作者：黄崔俊
	 * @日期：2015-11-2 15:09:21
	 *************************************************************************************/
	@RequestMapping("/teaching/question/checkQuestionList")
	public ModelAndView checkQuestionList(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam Integer examId) {
		ModelAndView mav = new ModelAndView();
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(cid);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		
		TAssignmentQuestionpool tAssignmentQuestionpool = new TAssignmentQuestionpool();
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		
		mav.addObject("examId", examId);
		mav.setViewName("teaching/question/checkQuestionList.jsp");
		return mav;
	}
	/**************************************************************************************
	 * @功能：查看可导入题目的题库
	 * @作者：黄崔俊
	 * @日期：2015-11-4 14:55:50
	 *************************************************************************************/
	@RequestMapping("/teaching/question/checkQuestionListForSection")
	public ModelAndView checkQuestionListForSection(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam Integer sectionId) {
		ModelAndView mav = new ModelAndView();
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionList(cid);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		
		TAssignmentQuestionpool tAssignmentQuestionpool = new TAssignmentQuestionpool();
		mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionpool);
		mav.addObject("sectionId", sectionId);
		mav.setViewName("teaching/question/checkQuestionListForSection.jsp");
		return mav;
	}
	
	/**************************************************************************************
	 * @功能：将测试中的题目导入题库
	 * @作者：黄崔俊
	 * @日期：2015-11-2 15:23:30
	 *************************************************************************************/
	@RequestMapping("/teaching/question/checkQuestion")
	public ModelAndView checkQuestion(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam Integer examId,Integer questionId) {
		ModelAndView mav = new ModelAndView();
		tAssignmentQuestionPoolService.importTAssignmentItemsByQuestionId(questionId,examId);
		mav.setViewName("redirect:/teaching/question/findTAssignmentItemsByQuestionId?currpage=1&id="+questionId);
		return mav;
	}

	
	/**************************************************************************
	 * Description:实验技能-预习测试-新建测验题目
	 * 
	 * @author：裴继超
	 * @date ：2016-11-18
	 **************************************************************************/
	@RequestMapping("/tcoursesite/skill/prepareExamNewItem")
	public ModelAndView prepareExamNewItem(@RequestParam int tCourseSiteId,Integer skillId,
			@RequestParam Integer sectionId,HttpSession httpSession) {
		//新建ModelAndView对象；
		ModelAndView mav = new ModelAndView();
		//选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//角色判断：如果具有老师权限且为该课程的创建者或者助教则默认为老师，如果没有教师权限则默认为学生
		Integer flag = tCourseSiteService.getFlagByUserAndSite(user, tCourseSite, httpSession);
		mav.addObject("flag", flag);
		//当前进入的url
		mav.addObject("url", "prepareExam");
		//查看站点下的所有实验技能
		List<TExperimentSkill> tExperimentSkills = tExperimentSkillService.findTExperimentSkillListBySiteId(tCourseSiteId, 1,-1);
		mav.addObject("tExperimentSkills",tExperimentSkills);
		//查看实验技能
		TExperimentSkill tExperimentSkill = tExperimentSkillService.findTExperimentSkillByPrimaryKey(skillId);
		mav.addObject("tExperimentSkill",tExperimentSkill);
		//实验技能完成情况列表
		List<Integer> skillDoList = tExperimentSkillService.findTExperimentSkillStringList(tCourseSiteId);
		mav.addObject("skillDoList",skillDoList);
		
		//实验技能下的预习测试
		TAssignment exam = tExperimentSkillService.findTAssignmentBySkillAndType(tExperimentSkill,205);
		mav.addObject("examId",exam.getId());
		TAssignmentItem tAssignmentItem = new TAssignmentItem();
		//初始化默认时间
		Calendar createdTime = Calendar.getInstance();
		tAssignmentItem.setCreatedTime(createdTime);
		tAssignmentItem.setUser(shareService.getUserDetail());
		TAssignmentSection tAssignmentSection = tAssignmentSectionService.findTAssignmentSectionById(sectionId);
		tAssignmentItem.setTAssignmentSection(tAssignmentSection);
		tAssignmentItem.setStatus(0);
		mav.addObject("tAssignmentItem", tAssignmentItem);
		mav.addObject("examInfo", exam);
		mav.setViewName("/tcoursesite/skill/prepareExamNewItem.jsp");
			
		return mav;
	}
	
}