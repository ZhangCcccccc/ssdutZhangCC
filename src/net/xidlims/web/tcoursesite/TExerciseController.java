package net.xidlims.web.tcoursesite;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TExerciseInfo;
import net.xidlims.domain.TMistakeItem;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TAssignmentItemService;
import net.xidlims.service.tcoursesite.TExerciseItemRecordService;
import net.xidlims.service.tcoursesite.TAssignmentQuestionPoolService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
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


/**************************************************************************
 * Description:题库模块
 * 
 * @author：黄崔俊
 * @date ：2015-10-23 10:12:05
 **************************************************************************/
@Controller("TExerciseController")
public class TExerciseController<JsonResult> {
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
	private TAssignmentItemService tAssignmentItemService;
	@Autowired 
	private TExerciseItemRecordService tExerciseItemRecordService;

	/**************************************************************************
	 * Description:学生根据课程查询当前课程下题库中的题目(顺序练习)
	 * 
	 * @author：裴继超
	 * @date ：2016年6月12日13:23:15
	 **************************************************************************/
	@SystemServiceLog("顺序练习")
	@ResponseBody
	@RequestMapping("/tcoursesite/exercise/findOrderItemListBySiteIdAndQuestionId")
	public ModelAndView findOrderItemListBySiteIdAndQuestionId(@RequestParam Integer tCourseSiteId,Integer currpage,Integer questionId,Integer itemType){
		ModelAndView mav = new ModelAndView();
		//根据课程id获得课程站点
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		if (currpage<1) {
			currpage = 1;
		}
		//各题库错题总数
		int totalRecords = tAssignmentItemService.countOrderItemListBySiteId(tCourseSiteId,questionId,itemType);
		//分页每页显示个数
		int pageSize = 1;
		//根据分页信息获取记录列表
		List<TAssignmentItem> items = tAssignmentItemService.findOrderItemListBySiteId(tCourseSiteId,currpage,pageSize,questionId,itemType);
		mav.addObject("items", items);
		//分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize,  totalRecords);
		mav.addObject("pageModel", pageModel);
		//顺序学习
		String type = "order";
		Integer iscorrect = 1;
		//正确记录个数
		Integer rightCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,itemType);
		iscorrect = 0;
		//错误记录个数
		Integer errorCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,itemType);
		//正确率
		String accuracy = "0.00%";
		//根据正确和错误记录个数计算正确率
		if (rightCount!=0||errorCount!=0) {
			accuracy = new BigDecimal(rightCount).divide(new BigDecimal(errorCount+rightCount),4,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).toString();
			accuracy = accuracy.substring(0,accuracy.length()-2)+"%";
		}
		mav.addObject("rightCount", rightCount);
		mav.addObject("errorCount", errorCount);
		mav.addObject("accuracy", accuracy);
		mav.addObject("questionId", questionId);
		mav.addObject("itemType", itemType);
		if (questionId>0) {
			//根据id查询对应题库
			mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(questionId));
		}
		mav.setViewName("tcoursesite/exercise/orderExercise.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:学生根据课程查询当前课程下题库中的题目(随机练习)
	 * 
	 * @author：裴继超
	 * @date ：2015-12-29 15:51:5
	 **************************************************************************/
	@SystemServiceLog("随机练习")
	@ResponseBody
	@RequestMapping("/tcoursesite/exercise/findStochasticItemListBySiteIdAndQuestionId")
	public ModelAndView findStochasticItemListBySiteIdAndQuestionId(@RequestParam Integer tCourseSiteId,Integer currpage,Integer questionId,Integer itemType){
		ModelAndView mav = new ModelAndView();
		//根据课程id获得课程站点
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		if (currpage<0) {
			currpage = 1;
		}
		//各题库错题总数
		int totalRecords = tAssignmentItemService.countOrderItemListBySiteId(tCourseSiteId,questionId,itemType);
		//分页每页显示个数
		int pageSize = 1;
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//获取学生练习记录
		TExerciseInfo tExerciseInfo = tExerciseItemRecordService.findStochasticStringByQuestionAndUser(tCourseSiteId,questionId,user,totalRecords,itemType);
		if(totalRecords!=0){
			//分页查询课程下题库中试题
			List<TAssignmentItem> items = tAssignmentItemService.findStochasticItemListBySiteIdAndItemType(tExerciseInfo,currpage,pageSize,itemType);
			mav.addObject("items", items);
		}
		//随机学习
		String type = "stochastic";
		Integer iscorrect = 1;
		//正确记录个数
		Integer rightCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,itemType);
		iscorrect = 0;
		//错误记录个数
		Integer errorCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,itemType);
		//正确率
		String accuracy = "0.00%";
		//根据正确和错误记录个数计算正确率
		if (rightCount!=0||errorCount!=0) {
			accuracy = new BigDecimal(rightCount).divide(new BigDecimal(errorCount+rightCount),4,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).toString();
			accuracy = accuracy.substring(0,accuracy.length()-2)+"%";
		}
		mav.addObject("rightCount", rightCount);
		mav.addObject("errorCount", errorCount);
		mav.addObject("accuracy", accuracy);
		mav.addObject("questionId", questionId);
		mav.addObject("itemType", itemType);
		if (questionId>0) {
			//根据id查询对应题库
			mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(questionId));
		}
		mav.setViewName("tcoursesite/exercise/stochasticExercise.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:学生根据课程查询当前课程下题库中的题目(错题练习)
	 * 
	 * @author：裴继超
	 * @date ：2015-12-29 16:21:25
	 **************************************************************************/
	@SystemServiceLog("错题练习")
	@ResponseBody
	@RequestMapping("/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId")
	public ModelAndView findMistakeItemListBySiteIdAndQuestionId(@RequestParam Integer tCourseSiteId,Integer currpage,Integer questionId,String orderType,Integer itemType){
		ModelAndView mav = new ModelAndView();
		//根据课程id获得课程站点
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		if (currpage<0) {
			currpage = 1;
		}
		//各题库错题总数
		int totalRecords = tAssignmentItemService.countMistakeItemListBySiteId(tCourseSiteId,questionId,itemType);
		//分页每页显示个数
		int pageSize = 1;
		//根据分页信息获取记录列表
		Map<String, Integer> pageModel = shareService.getPage(currpage,pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//根据分页信息获取错题记录列表
		List<TMistakeItem> tMistakeItems = tAssignmentItemService.findMistakeItemListBySiteId(tCourseSiteId,currpage,pageSize,questionId,orderType,itemType);
		//记录第一个错题试题记录
		TMistakeItem tMistakeItem = null;
		if (tMistakeItems.size()>0) {
			tMistakeItem = tMistakeItems.get(0);
		}
		mav.addObject("tMistakeItem", tMistakeItem);
		//错题学习
		String type = "mistake";
		Integer iscorrect = 1;
		//正确记录个数
		Integer rightCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,itemType);
		iscorrect = 0;
		//错误记录个数
		Integer errorCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,itemType);
		//正确率
		String accuracy = "0.00%";
		if (rightCount!=0||errorCount!=0) {
			//根据正确和错误记录个数计算正确率
			accuracy = new BigDecimal(rightCount).divide(new BigDecimal(errorCount+rightCount),4,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).toString();
			accuracy = accuracy.substring(0,accuracy.length()-2)+"%";
		}
		mav.addObject("rightCount", rightCount);
		mav.addObject("errorCount", errorCount);
		mav.addObject("accuracy", accuracy);
		mav.addObject("questionId", questionId);
		mav.addObject("itemType", itemType);
		if (questionId>0) {
			//根据id查询对应题库
			mav.addObject("tAssignmentQuestionpool", tAssignmentQuestionPoolService.findTAssignmentQuestionpoolById(questionId));
		}
		mav.setViewName("tcoursesite/exercise/mistakeExercise.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:学生根据课程查询当前课程下所有的题库
	 * 
	 * @author：裴继超
	 * @date ：2015-12-22 14:31:31
	 **************************************************************************/
	@SystemServiceLog("题库列表")
	@ResponseBody
	@RequestMapping("/tcoursesite/exercise/findQuestionListBySiteId")
	public ModelAndView findQuestionListBySiteId(@RequestParam Integer tCourseSiteId,Integer currpage){
		ModelAndView mav = new ModelAndView();
		//根据课程id获得课程站点
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前登录人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//课程的题库个数
		int totalRecords = tAssignmentQuestionPoolService.countQuestionBySiteId(tCourseSiteId);
		//分页每页显示个数
		int pageSize = 1000;
		//分页信息
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("pageModel", pageModel);
		//分页查询课程下的题库列表
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionPoolService.findQuestionListBySiteId(tCourseSiteId,currpage,pageSize);
		mav.addObject("tAssignmentQuestionpools", tAssignmentQuestionpools);
		mav.setViewName("tcoursesite/exercise/questionPoolList.jsp");
		return mav;
		
	}
	
	/**************************************************************************
	 * Description:保存学生的答题记录
	 * 
	 * @author：裴继超
	 * @date ：2015-12-22 14:31:31
	 **************************************************************************/
	@RequestMapping("/tcoursesite/exercise/saveItemRecord")
	@ResponseBody
	public Map<String, String> saveItemRecord(HttpServletRequest request,@RequestParam Integer tCourseSiteId,Integer questionId,Integer itemId,Integer answerId,String type){
		Map<String, String> map = new HashMap<String, String>();
		//当前登录人
		User user = shareService.getUser();
		//保存学生的答题记录
		map = tExerciseItemRecordService.saveItemRecord(user,tCourseSiteId,questionId,itemId,answerId,type);
		//根据测验小题id查询测验小题
		TAssignmentItem tAssignmentItem = tAssignmentItemService.findTAssignmentItemById(itemId);
		Integer iscorrect = 1;
		//正确记录个数
		Integer rightCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,tAssignmentItem.getType());
		iscorrect = 0;
		//错误记录个数
		Integer errorCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,tAssignmentItem.getType());
		//正确率
		String accuracy = "0.00%";
		if (rightCount!=0||errorCount!=0) {
			//根据正确和错误记录个数计算正确率
			accuracy = new BigDecimal(rightCount).divide(new BigDecimal(errorCount+rightCount),4,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).toString();
			accuracy = accuracy.substring(0,accuracy.length()-2)+"%";
		}
		map.put("rightCount", rightCount.toString());
		map.put("errorCount", errorCount.toString());
		map.put("accuracy", accuracy);
		return map;
	}
	
	/****************************************************************************
	 * Description:课程-练习-多选题保存答案 
	 * 
	 * @author：李军凯
	 * @date ：2016-08-25
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/exercise/saveMultiItemRecord")
	@ResponseBody
	public Map<String, String> saveMultiItemRecord(HttpServletRequest request,@RequestParam Integer tCourseSiteId,Integer questionId,Integer itemId,String answer,String type){
		Map<String, String> map = new HashMap<String, String>();
		//当前登录人
		User user = shareService.getUser();
		//多选题保存答案 
		map = tExerciseItemRecordService.saveMultiItemRecord(user,tCourseSiteId, questionId, itemId, answer, type);
		//根据测验小题id查询测验小题
		TAssignmentItem tAssignmentItem = tAssignmentItemService.findTAssignmentItemById(itemId);
		Integer iscorrect = 1;
		//正确记录个数
		Integer rightCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,tAssignmentItem.getType());
		iscorrect = 0;
		//错误记录个数
		Integer errorCount = tAssignmentItemService.countItemByQuestionAndUser(tCourseSiteId,questionId,user,type,iscorrect,tAssignmentItem.getType());
		//正确率
		String accuracy = "0.00%";
		if (rightCount!=0||errorCount!=0) {
			//根据正确和错误记录个数计算正确率
			accuracy = new BigDecimal(rightCount).divide(new BigDecimal(errorCount+rightCount),4,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).toString();
			accuracy = accuracy.substring(0,accuracy.length()-2)+"%";
		}
		map.put("rightCount", rightCount.toString());
		map.put("errorCount", errorCount.toString());
		map.put("accuracy", accuracy);
		return map;
	}
}