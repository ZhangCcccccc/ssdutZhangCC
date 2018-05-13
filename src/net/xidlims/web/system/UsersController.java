/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/system/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
//import net.xidlims.service.system.TimeDetailService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/****************************************************************************
 * 功能：系统后台管理模块 作者：魏诚 时间：2014-07-14
 ****************************************************************************/
@Controller("UsersController")
@SessionAttributes("selected_labCenter")
public class UsersController<JsonResult>
{
	/************************************************************
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 ************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request)
	{ // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	ShareService shareService;
	@Autowired
	LabCenterService labCenterService;

	/************************************************************
	 * @内容：用户列表
	 * @作者：叶明盾
	 * @日期：2014-07-30
	 ************************************************************/
	@RequestMapping("/system/listUser")
	public ModelAndView listUser(HttpServletRequest request, @ModelAttribute User user,@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer cid)
	{
		ModelAndView mav = new ModelAndView();
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		String number="";
		if(cid != null & cid != -1)
		{
			// 中心所属学院
			number = labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		}
		else{
			number=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
		// 设置用户的总记录数并赋值
		int totalRecords = userDetailService.getUserTotalRecords(user,number);
		int curr=0;
		List<User> user1=userDetailService.findAllUsers(curr, pageSize);
		mav.addObject("user1", user1);
		Map<String, Integer> pageModel = shareService.getPage( currpage, pageSize,totalRecords);
		//
		mav.addObject("newFlag", true);
		//将pageModel映射到pageModel
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//将totalRecords映射到totalRecords，用来获取总记录数
		mav.addObject("totalRecords", totalRecords);
		
		//根据页面的页码，查找出20条记录，将找到的用户映射给users
		mav.addObject("users", userDetailService.findUsersByAcademy(number));
		//获取用户列表
		mav.addObject("userMap", userDetailService.getUserTotalRecords(user,number,currpage,pageSize));
		// 将该Model映射到listUser.jsp
		mav.setViewName("system/user/listUser.jsp");
		return mav;
	}
	
	/************************************************************
	 * @内容：显示用户详细信息
	 * @作者：叶明盾
	 * @日期：2014-09-02
	 ************************************************************/
	@RequestMapping("/system/userDetailInfo")
	public ModelAndView userDetailInfo(@RequestParam String num)
	{
		ModelAndView mav=new ModelAndView();
		//将前端获取到的Username传递给后台
		mav.addObject("num", num);
		//根据用户的Username查找出用户
		mav.addObject("users", userDetailService.findUserByNum(num));
		//将该Model映射将该Model映射到userDetailInfo.jsp
		mav.setViewName("system/user/userDetailInfo.jsp");
		return mav;
	}
}