package net.xidlims.web.tcoursesite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.TAssignmentGradingDAO;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentGrading;
import net.xidlims.domain.TAssignmentGradingAttachment;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteGroup;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.service.teaching.TAssignmentGradingService;
import net.xidlims.service.tcoursesite.TAssignmentGroupService;
import net.xidlims.service.tcoursesite.TAssignmentService;
import net.xidlims.service.tcoursesite.TCourseSiteService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;
import net.xidlims.service.tcoursesite.TGradebookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
 * Description:小组作业模块
 * 
 * @author：于侃
 * @date ：2016年10月21日 09:39:34
 **************************************************************************/
@Controller("TAssignmentGroupController")
@SessionAttributes("selected_courseSite")
public class TAssignmentGroupController<JsonResult> {
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
	private TAssignmentGradingDAO tAssignmentGradingDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentService tAssignmentService;
	@Autowired
	private TAssignmentGradingService tAssignmentGradingService;
	@Autowired
	private TCourseSiteService tCourseSiteService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private TAssignmentGroupService tAssignmentGroupService;
	@Autowired
	private TGradebookService tGradebookService;
	
	/**************************************************************************
	 * Description:教师权限查看小组作业提交情况
	 * 
	 * @author：于侃
	 * @date ：2016年10月17日 15:31:44
	 **************************************************************************/
	@RequestMapping("/tcoursesite/assignment/groupAssignmentGradingList")
	public ModelAndView groupAssignmentGradingList(@RequestParam Integer tCourseSiteId,
			Integer assignmentId, int flag,Integer moduleType,Integer selectId,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		//flag用于判断是学生和教师
		mav.addObject("flag", flag);
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//查看当前登录用户
		User user = shareService.getUserDetail();
		mav.addObject("user", user);
		//当前作业
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignmentId);
		mav.addObject("tAssignment", tAssignment);
		//小组列表
		List<TCourseSiteGroup> groups = new ArrayList<TCourseSiteGroup>(tCourseSite.getTCourseSiteGroups());
		mav.addObject("groups", groups);
		//如果该课程有小组
		if(groups.size() > 0){
			//获取第一个小组（刚进入页面时显示）
			TCourseSiteGroup group = groups.get(0);
			mav.addObject("groupId", group.getId());
			mav.addObject("groupTitle", group.getGroupTitle());
			//获取小组成员信息
			List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserByGroupId(group.getId());
			String groupMemberInfo = "";
			//拼接前台评分
			String scoreInfo = "";
			String tempScore = "";
			for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
				if(tAssignmentGroupService.findGroupTAssignmentGrading(tAssignment.getId(), tCourseSiteUser.getUser().getUsername(), group.getId()).getAccessmentgradingId() != null){
					tempScore = tAssignmentGroupService.findGroupTAssignmentGrading(tAssignment.getId(), tCourseSiteUser.getUser().getUsername(), group.getId()).getFinalScore().toString();
				}
				if(tCourseSiteUser.getRole() == 2){
					groupMemberInfo += tCourseSiteUser.getUser().getCname() + "(组长) ";
					scoreInfo = "<span class=\"l h40 lh40 mb15\">导师打分：</span><table class=\"cl w50p stu_score ml70 mt10\">" + 
							"<tr><td>" + tCourseSiteUser.getUser().getCname() + "(组长)</td><td><input id='"+
							tCourseSiteUser.getUser().getUsername()+"' style='width:100%' onchange=\"saveScore("+
							tAssignment.getId()+",'"+
							tCourseSiteUser.getUser().getUsername()+"',"+group.getId()+",'2');\" value='" + tempScore +
							"' oninput=\"changeNumber(this,100 )\" /></td>" + scoreInfo;
				}else{
					groupMemberInfo += tCourseSiteUser.getUser().getCname() + " ";
					scoreInfo += "<tr><td>" + tCourseSiteUser.getUser().getCname() + "</td><td><input id='"+
							tCourseSiteUser.getUser().getUsername()+"' style='width:100%' onchange=\"saveScore("+
							tAssignment.getId()+",'"+
							tCourseSiteUser.getUser().getUsername()+"',"+group.getId()+",'0');\" value='" + tempScore +
							"' oninput=\"changeNumber(this,100 )\" /></td>";
				}
			}
			mav.addObject("groupMemberInfo", groupMemberInfo);
			mav.addObject("scoreInfo", scoreInfo);
			//获取小组作业信息
			List<TAssignmentGrading> tAssignmentGradings = tAssignmentGroupService.findTAssignmentGradingList(assignmentId, group.getId());
			mav.addObject("tAssignmentGradings", tAssignmentGradings);
			//如果该小组作业有提交信息
			if(tAssignmentGradings.size() > 0){
				TAssignmentGrading tAssignmentGrading = tAssignmentGradings.get(tAssignmentGradings.size()-1);
				mav.addObject("tAssignmentGrading", tAssignmentGrading);
				//获取附件列表
				List<TAssignmentGradingAttachment> gradeUrls = tAssignmentGroupService.findGradeUrls(tAssignmentGrading.getAccessmentgradingId());
				//附件名称列表
				String groupAssignmentsList = "";
				//附件列表
				String groupAssignmentsNameList = "";
				//分隔符
				String sep = System.getProperty("file.separator");
				//图标名称类型部分
				String iconName;
				//后缀集合
	    		String suffixList = ".chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls";
	    		//文件名称
	    		String fileName = "";
	    		//后缀
	    		String suffix = "";
				for (TAssignmentGradingAttachment attachement : gradeUrls) {
					Integer id = attachement.getTAssignmentGradingAttachmentId();
					String url = attachement.getGradeUrl();
					groupAssignmentsList += id + ",";
					fileName = url.substring(url.lastIndexOf(sep)+1);
					suffix = fileName.substring(fileName.indexOf(".")+1);
		    		if(suffixList.indexOf(suffix) == -1){
		    			iconName = "default.png";
		    		}else{
		    			iconName = suffix+".gif";
		    		}
					groupAssignmentsNameList += "<li class=\"l w25p\" id=\"attach" + id + "\">" + 
		    				"<input type='checkbox' id=\"attachment" + id + 
		    				"\" name='checknames' value=\"attachment" + id + 
		    				"\" /><label for=\"attachment" + id + 
		    				"\" ></label><img src=\""+request.getContextPath()+"/images/fileTypeImages/icon_"+
		    				iconName+"\"  alt=\"\" /><span>" + url.substring(url.lastIndexOf(sep)+1) + "</span></li>";
				}
				mav.addObject("groupAssignmentsNameList", groupAssignmentsNameList);
				mav.addObject("groupAssignmentsList", groupAssignmentsList);
			}
		}
		mav.setViewName("tcoursesite/groupassignment/assignmentGradingList.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:切换小组作业提交情况
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 13:51:45
	 **************************************************************************/
	@RequestMapping("/tcoursesite/assignment/changeGroup")
	public @ResponseBody Map<String, String> changeGroup(Integer assignmentId, int groupId,HttpServletRequest request){
		//map
		Map<String, String> map = new HashMap<String, String>();
		//当前作业
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignmentId);
		//获取小组成员信息
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserByGroupId(groupId);
		String groupMemberInfo = "该组成员：";
		//拼接前台评分
		String scoreInfo = "";
		String tempScore = "";
		for (TCourseSiteUser user : tCourseSiteUsers) {
			if(tAssignmentGroupService.findGroupTAssignmentGrading(tAssignment.getId(), user.getUser().getUsername(), groupId).getAccessmentgradingId() != null){
				tempScore = tAssignmentGroupService.findGroupTAssignmentGrading(tAssignment.getId(), user.getUser().getUsername(), groupId).getFinalScore().toString();
			}
			if(user.getRole() == 2){
				groupMemberInfo += user.getUser().getCname() + "(组长) ";
				scoreInfo = "<span class=\"l h40 lh40 mb15\">导师打分：</span><table class=\"cl w50p stu_score ml70 mt10\">" + 
						"<tr><td>" + user.getUser().getCname() + "(组长)</td><td><input id='"+
						user.getUser().getUsername()+"' style='width:100%' onchange=\"saveScore("+
						tAssignment.getId()+",'"+
						user.getUser().getUsername()+"',"+groupId+",'2');\" value='" + tempScore +
						"' oninput=\"changeNumber(this,100 )\" /></td>" + scoreInfo;
			}else{
				groupMemberInfo += user.getUser().getCname() + " ";
				scoreInfo += "<tr><td>" + user.getUser().getCname() + "</td><td><input id='"+
						user.getUser().getUsername()+"' style='width:100%' onchange=\"saveScore("+
						tAssignment.getId()+",'"+
						user.getUser().getUsername()+"',"+groupId+",'0');\" value='" + tempScore +
						"' oninput=\"changeNumber(this,100 )\" /></td>";
			}
		}
		map.put("groupMemberInfo", groupMemberInfo);
		map.put("scoreInfo", scoreInfo);
		//获取小组作业信息
		List<TAssignmentGrading> tAssignmentGradings = tAssignmentGroupService.findTAssignmentGradingList(assignmentId, groupId);
		TAssignmentGrading tAssignmentGrading = new TAssignmentGrading();
		//如果该小组作业有提交信息
		if(tAssignmentGradings.size() > 0){
			//该小组最新一次提交记录
			tAssignmentGrading = tAssignmentGradings.get(tAssignmentGradings.size()-1);
			//作业描述
			map.put("content", tAssignmentGrading.getContent());
			//小组分工
			map.put("distribution", tAssignmentGrading.getDistribution());
			//获取附件列表
			List<TAssignmentGradingAttachment> gradeUrls = tAssignmentGroupService.findGradeUrls(tAssignmentGrading.getAccessmentgradingId());
			//附件名称列表
			String groupAssignmentsList = "";
			//附件列表
			String groupAssignmentsNameList = "";
			//分隔符
			String sep = System.getProperty("file.separator");
			//图标名称类型部分
			String iconName;
			//后缀集合
    		String suffixList = ".chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls";
    		//文件名称
    		String fileName = "";
    		//后缀
    		String suffix = "";
			for (TAssignmentGradingAttachment attachement : gradeUrls) {
				Integer id = attachement.getTAssignmentGradingAttachmentId();
				String url = attachement.getGradeUrl();
				groupAssignmentsList += id + ",";
				fileName = url.substring(url.lastIndexOf(sep)+1);
				suffix = fileName.substring(fileName.indexOf(".")+1);
	    		if(suffixList.indexOf(suffix) == -1){
	    			iconName = "default.png";
	    		}else{
	    			iconName = suffix+".gif";
	    		}
				groupAssignmentsNameList += "<li class=\"l w25p\" id=\"attach" + id + "\">" + 
	    				"<input type='checkbox' id=\"attachment" + id + 
	    				"\" name='checknames' value=\"attachment" + id + 
	    				"\" /><label for=\"attachment" + id + 
	    				"\" ></label><img src=\""+request.getContextPath()+"/images/fileTypeImages/icon_"+
	    				iconName+"\"  alt=\"\" /><span>" + url.substring(url.lastIndexOf(sep)+1) + "</span></li>";
			}
			map.put("groupAssignmentsNameList", groupAssignmentsNameList);
			map.put("groupAssignmentsList", groupAssignmentsList);
		}else{
			//作业描述
			map.put("content", "");
			//小组分工
			map.put("distribution","");
			map.put("groupAssignmentsNameList", "");
			map.put("groupAssignmentsList", "");
		}
		
		return map;
	}
	
	/**************************************************************************
	 * Description:进入学生提交小组作业页面
	 * 
	 * @author：于侃
	 * @date ：2016年10月17日 15:31:44
	 **************************************************************************/
	@RequestMapping("/tcoursesite/assignment/newGroupAssignmentGrade")
	public ModelAndView newGroupAssignmentGrade(@ModelAttribute("selected_courseSite")Integer cid,
			Integer moduleType,@RequestParam Integer tCourseSiteId,int assignId, int flag,Integer selectId,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		//新建ModelAndView对象；
		mav.addObject("flag", flag);
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(tCourseSiteId);
		mav.addObject("tCourseSite", tCourseSite);
		//当前作业
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignId);
		mav.addObject("tAssignment", tAssignment);
		//该作业下所有提交记录
		Set<TAssignmentGrading> tAssignmentGradings = tAssignment.getTAssignmentGradings();
		//查询当前登陆账户
		User student = shareService.getUserDetail();
		TCourseSiteGroup group = tCourseSiteUserService.findGroupByUser(student.getUsername(),cid);
		mav.addObject("group", group);
		//获取小组成员信息
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserByGroupId(group.getId());
		String groupMemberInfo = "";
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			if(tCourseSiteUser.getRole() == 2){
				if(student.getUsername() == tCourseSiteUser.getUser().getUsername()){
					mav.addObject("groupRole", 2);
				}
				groupMemberInfo += tCourseSiteUser.getUser().getCname() + "(组长) ";
			}else{
				groupMemberInfo += tCourseSiteUser.getUser().getCname() + " ";
			}
		}
		mav.addObject("groupMemberInfo", groupMemberInfo);
		//存放非当前登陆学生所提交的作业
		Set<TAssignmentGrading> tAssignmentGradingsTemp = new HashSet<TAssignmentGrading>();
		//是否被批改
		String isGraded = null;
		for (TAssignmentGrading assignmentGrading : tAssignmentGradings) {
			if (!student.getUsername().equals(assignmentGrading.getUserByStudent().getUsername())) {
				//移除非当前登陆学生所提交的作业
				tAssignmentGradingsTemp.add(assignmentGrading);
			}else if(assignmentGrading.getFinalScore()!=null) {
				//已被批改
				isGraded = "isGraded";
			}
		}
		mav.addObject("isGraded", isGraded);
		//当前登录人的历史提交记录
		tAssignmentGradings.removeAll(tAssignmentGradingsTemp);
		//获取小组作业信息
		List<TAssignmentGrading> tAssignmentGradingList = tAssignmentGroupService.findTAssignmentGradingList(tAssignment.getId(), group.getId());
		TAssignmentGrading tAssignmentGrading = new TAssignmentGrading();
		//如果该小组作业有提交信息
		if(tAssignmentGradingList.size() > 0){
			//该小组最新一次提交记录
			tAssignmentGrading = tAssignmentGradingList.get(tAssignmentGradingList.size()-1);
			//获取附件列表
			List<TAssignmentGradingAttachment> gradeUrls = tAssignmentGroupService.findGradeUrls(tAssignmentGrading.getAccessmentgradingId());
			//附件名称列表
			String groupAssignmentsList = "";
			//附件列表
			String groupAssignmentsNameList = "";
			//分隔符
			String sep = System.getProperty("file.separator");
			//图标名称类型部分
			String iconName;
			//后缀集合
			String suffixList = ".chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls";
			//文件名称
			String fileName = "";
			//后缀
			String suffix = "";
			for (TAssignmentGradingAttachment attachement : gradeUrls) {
				Integer id = attachement.getTAssignmentGradingAttachmentId();
				String url = attachement.getGradeUrl();
				groupAssignmentsList += id + ",";
				fileName = url.substring(url.lastIndexOf(sep)+1);
				suffix = fileName.substring(fileName.indexOf(".")+1);
	    		if(suffixList.indexOf(suffix) == -1){
	    			iconName = "default.png";
	    		}else{
	    			iconName = suffix+".gif";
	    		}
				groupAssignmentsNameList += "<li class=\"l w25p\" id=\"attach" + id + "\">" + 
	    				"<input type='checkbox' id=\"attachment" + id + 
	    				"\" name='checknames' value=\"attachment" + id + 
	    				"\" /><label for=\"attachment" + id + 
	    				"\" ></label><img src=\""+request.getContextPath()+"/images/fileTypeImages/icon_"+
	    				iconName+"\"  alt=\"\" /><span>" + url.substring(url.lastIndexOf(sep)+1) + "</span></li>";
			}
			mav.addObject("groupAssignmentsNameList", groupAssignmentsNameList);
			mav.addObject("groupAssignmentsList", groupAssignmentsList);
		}else{
			tAssignmentGrading.setSubmitTime(1);
			tAssignmentGrading.setSubmitdate(Calendar.getInstance());
			tAssignmentGrading.setUserByStudent(student);
			tAssignmentGrading.setTAssignment(tAssignment);
			tAssignmentGrading.setGroupId(group.getId());
		}
		mav.addObject("tAssignmentGrade", tAssignmentGrading);
		mav.setViewName("tcoursesite/groupassignment/editAssignmentGrade.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:学生提交小组作业
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 09:53:15
	 **************************************************************************/
	@RequestMapping("/tcoursesite/assignment/saveGroupTAssignmentGrade")
	public ModelAndView saveGroupTAssignmentGrade(@ModelAttribute TAssignmentGrading tAssignmentGrade,
			@ModelAttribute("selected_courseSite")Integer cid,Integer moduleType,Integer selectId,Integer groupId,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		TAssignmentGrading tAssignmentGrading = tAssignmentGroupService.saveGroupTAssignmentGrading(tAssignmentGrade,cid,groupId,request);
		mav.setViewName("redirect:/tcoursesite/chaptersList?tCourseSiteId="+cid+"&moduleType="+moduleType +"&selectId="+selectId);
		return mav;
	}
	
	/**************************************************************************
	 * Description:学生查看小组作业
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 10:34:06
	 **************************************************************************/
	@RequestMapping("/tcoursesite/assignment/lookGroupAssignmentGrade")
	public ModelAndView lookGroupAssignmentGrade(@ModelAttribute("selected_courseSite")Integer cid,@RequestParam int assignId, int flag,Integer moduleType,Integer selectId,HttpServletRequest request){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		mav.addObject("flag", flag);
		//全部:-1  视频:1  图片:2  参考文件:3  作业:4  测试:5 考试:6
		mav.addObject("selectId", selectId);
		mav.addObject("moduleType", moduleType);
		// 选择的课程中心
		TCourseSite tCourseSite = tCourseSiteService.findCourseSiteById(cid);
		mav.addObject("tCourseSite", tCourseSite);
		//查询当前登陆账户
		User student = shareService.getUserDetail();
		TCourseSiteGroup group = tCourseSiteUserService.findGroupByUser(student.getUsername(),cid);
		mav.addObject("group", group);
		//获取小组成员信息
		List<TCourseSiteUser> tCourseSiteUsers = tCourseSiteUserService.findTCourseSiteUserByGroupId(group.getId());
		String groupMemberInfo = "";
		String scoreInfo = "";
		String tempScore = "";
		for (TCourseSiteUser tCourseSiteUser : tCourseSiteUsers) {
			if(tAssignmentGroupService.findGroupTAssignmentGrading(assignId, tCourseSiteUser.getUser().getUsername(), group.getId()).getAccessmentgradingId() != null){
				tempScore = tAssignmentGroupService.findGroupTAssignmentGrading(assignId, tCourseSiteUser.getUser().getUsername(), group.getId()).getFinalScore().toString();
			}
			if(tCourseSiteUser.getRole() == 2){
				groupMemberInfo += tCourseSiteUser.getUser().getCname() + "(组长) ";
				scoreInfo = "<span class=\"l h40 lh40 mb15\">导师打分：</span><table class=\"cl w50p stu_score ml70 mt10\">" + 
						"<tr><td>" + tCourseSiteUser.getUser().getCname() + "(组长)</td><td><input id='" +
						tCourseSiteUser.getUser().getUsername()+"' style='width:100%' value='" + tempScore +
						"' disabled=\"disabled\" /></td> " + scoreInfo;
			}else{
				groupMemberInfo += tCourseSiteUser.getUser().getCname() + " ";
				scoreInfo += "<tr><td>" + tCourseSiteUser.getUser().getCname() + "</td><td><input id='"+
						tCourseSiteUser.getUser().getUsername()+"' style='width:100%' value='" + tempScore +
						"' disabled=\"disabled\" /></td>";
			}
		}
		mav.addObject("groupMemberInfo", groupMemberInfo);
		//教师评分
		mav.addObject("scoreInfo", scoreInfo);
		//查看当前登录用户
		User user = shareService.getUserDetail();
		mav.addObject("user", user);
		//通过作业id查询作业
		TAssignment tAssignment = tAssignmentService.findTAssignmentById(assignId);
		mav.addObject("tAssignment", tAssignment);
		//获取该作业所有提交记录
		Set<TAssignmentGrading> tAssignmentGradings = tAssignment.getTAssignmentGradings();
		//存放非登录账户所提交的作业
		Set<TAssignmentGrading> tAssignmentGradingsTemp = new HashSet<TAssignmentGrading>();
		TAssignmentGrading tAssignmentGrading = null;
		//标记是否被批改
		String isGraded = null;
		//查找所有非当前登陆人提交记录
		for (TAssignmentGrading assignmentGrading : tAssignmentGradings) {
			if (!student.getUsername().equals(assignmentGrading.getUserByStudent().getUsername())) {
				//非当前登陆学生所提交的作业
				tAssignmentGradingsTemp.add(assignmentGrading);
			}else if (assignmentGrading.getFinalScore()!=null) {
				//已被批改
				isGraded = "isGraded";
			}
		}
		mav.addObject("isGraded", isGraded);
		//当前统计登录人的历史提交记录
		tAssignmentGradings.removeAll(tAssignmentGradingsTemp);
//		//最新一次提交记录
//		tAssignmentGrading = new ArrayList<TAssignmentGrading>(tAssignmentGradings).get(tAssignmentGradings.size()-1);
//		mav.addObject("tAssignmentGrade", tAssignmentGrading);
		//获取该小组所有提交记录
		List<TAssignmentGrading> list = tAssignmentGroupService.findTAssignmentGradingList(assignId, group.getId());
		mav.addObject("tAssignmentGradings", list);
		//最新一次提交记录
		tAssignmentGrading = list.get(list.size()-1);
		mav.addObject("tAssignmentGrading", tAssignmentGrading);
		//获取附件列表
		List<TAssignmentGradingAttachment> gradeUrls = tAssignmentGroupService.findGradeUrls(tAssignmentGrading.getAccessmentgradingId());
		//附件名称列表
		String groupAssignmentsList = "";
		//附件列表
		String groupAssignmentsNameList = "";
		//分隔符
		String sep = System.getProperty("file.separator");
		//图标名称类型部分
		String iconName;
		//后缀集合
		String suffixList = ".chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls";
		//文件名称
		String fileName = "";
		//后缀
		String suffix = "";
		for (TAssignmentGradingAttachment attachement : gradeUrls) {
			Integer id = attachement.getTAssignmentGradingAttachmentId();
			String url = attachement.getGradeUrl();
			groupAssignmentsList += id + ",";
			fileName = url.substring(url.lastIndexOf(sep)+1);
			suffix = fileName.substring(fileName.indexOf(".")+1);
    		if(suffixList.indexOf(suffix) == -1){
    			iconName = "default.png";
    		}else{
    			iconName = suffix+".gif";
    		}
			groupAssignmentsNameList += "<li class=\"l w25p\" id=\"attach" + id + "\">" + 
    				"<input type='checkbox' id=\"attachment" + id + 
    				"\" name='checknames' value=\"attachment" + id + 
    				"\" /><label for=\"attachment" + id + 
    				"\" ></label><img src=\""+request.getContextPath()+"/images/fileTypeImages/icon_"+
    				iconName+"\"  alt=\"\" /><span>" + url.substring(url.lastIndexOf(sep)+1) + "</span></li>";
		}
		mav.addObject("groupAssignmentsNameList", groupAssignmentsNameList);
		mav.addObject("groupAssignmentsList", groupAssignmentsList);
		mav.setViewName("tcoursesite/groupassignment/lookAssignmentGrade.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:教师评分自动保存
	 * 
	 * @author：于侃
	 * @date ：2016年10月19日 09:53:15
	 **************************************************************************/
	@ResponseBody
	@RequestMapping("/tcoursesite/assignment/saveScore")
	public void saveGroupTAssignmentGrade(Integer assignmentId,String username,Integer score,Integer groupId){
		
		TAssignmentGrading tAssignmentGrade = tAssignmentGroupService.findGroupTAssignmentGrading(assignmentId, username, groupId);
		tAssignmentGrade.setTAssignment(tAssignmentService.findTAssignmentById(assignmentId));
		tAssignmentGrade.setUserByStudent(userDetailService.findUserByNum(username));
		tAssignmentGrade.setFinalScore(new BigDecimal(score));
		tAssignmentGrade.setGroupId(groupId);
		tAssignmentGrade.setSubmitTime(1);
		//查看当前登录用户
		User user = shareService.getUserDetail();
		tAssignmentGrade.setUserByGradeBy(user);
		tAssignmentGrade.setGradeTime(Calendar.getInstance());
		tAssignmentGrade = tAssignmentGradingDAO.store(tAssignmentGrade);
		tGradebookService.saveGradebook(tAssignmentService.findTAssignmentById(assignmentId).getSiteId(),assignmentId,tAssignmentGrade);
	}
	
	/****************************************************************************
	 * description:处理小组作业编辑页面ajax发来的附件
	 * 
	 * author:于侃
	 * date:2016年10月20日 13:55:42
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/assignment/fileUpload")
	public @ResponseBody String fileUpload(@RequestParam Integer tAssignmentId,@RequestParam Integer tcoursesiteId,HttpServletRequest request) throws Exception {
		int id =  tAssignmentGroupService.processUpload(tAssignmentId,tcoursesiteId,request);
		return String.valueOf(id);
	}
	
	/****************************************************************************
	 * description:教师批量下载附件
	 * 
	 * author:于侃
	 * date:2016年10月21日 10:21:16
	 ****************************************************************************/
	@RequestMapping("/tcoursesite/assignment/downloadAttachments")
	public String downloadAttachments(Integer tAssignmentId,Integer tcoursesiteId,String attachList,HttpServletRequest request,HttpServletResponse response){
		String result = tAssignmentGroupService.downloadAttachments(tAssignmentId,tcoursesiteId,attachList,request, response);
//		return tAssignmentGroupService.downloadAttachments(tAssignmentId,tcoursesiteId,attachList,request, response);
//		if (result == null) {
//			//return "redirect:/tcoursesite/assignment/assignmentGradingList?tCourseSiteId="+tCourseSiteId+"&assignmentId="+assignId+"&flag=1";
//		}
		return result;
	}
	
}