/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/system/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemService;
import net.xidlims.service.system.UserDetailService;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolTerm;
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

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;

/**************************************************************************
 * Description:系统管理-用户管理
 * 
 * @author：于侃
 * @date ：2016-08-24
 **************************************************************************/
@Controller("TUsersController")
public class TUsersController<JsonResult>
{
	/**************************************************************************
	 * Description:系统管理-用户管理-初始化WebDataBinder，这个WebDataBinder
	 * 用于填充被@InitBinder注释的处理 方法的command和form对象
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
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

	/**************************************************************************
	 * Description:系统管理-用户管理-用户列表
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping("/tcoursesite/listUser")
	public ModelAndView listUser(@RequestParam int currpage,@ModelAttribute User formUser)
	{
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 设置用户的总记录数并赋值
		int totalRecords = userDetailService.getUserTotalRecords(formUser);
		
		Map<String, Integer> pageModel = shareService.getPage( currpage, pageSize,totalRecords);
		mav.addObject("newFlag", true);
		//将pageModel映射到pageModel
		mav.addObject("pageModel", pageModel);
		//将currpage映射到page，用来获取当前页的页码
		mav.addObject("page", currpage);
		//将totalRecords映射到totalRecords，用来获取总记录数
		mav.addObject("totalRecords", totalRecords);
		//获取用户列表
		//mav.addObject("users", userDetailService.findUsers());
		//根据页面的页码，查找出20条记录，将找到的用户映射给users
		mav.addObject("userMap", userDetailService.getUserTotalRecords(formUser,currpage,pageSize));
		//所有部门
		Set<SchoolAcademy> academys=shareService.getAllSchoolAcademy();
		mav.addObject("academys", academys);
		//左侧栏选中项
		mav.addObject("select", "user");
		if(formUser.getSchoolAcademy()==null){
			SchoolAcademy schoolAcademy = new SchoolAcademy();
			schoolAcademy.setAcademyNumber("-1");
			formUser.setSchoolAcademy(schoolAcademy);
		}
		mav.addObject("formUser", formUser);
		// 将该Model映射到listUser.jsp
		mav.setViewName("tcoursesite/user/listUser.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:系统管理-用户管理-显示用户详细信息
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping("/tcoursesite/userDetailInfo")
	public ModelAndView userDetailInfo(@RequestParam String num)
	{
		ModelAndView mav=new ModelAndView();
		//将前端获取到的Username传递给后台
		mav.addObject("num", num);
		//根据用户的Username查找出用户
		mav.addObject("users", userDetailService.findUserByNum(num));
		//导航栏
		mav.addObject("select", "user");
		//将该Model映射将该Model映射到userDetailInfo.jsp
		mav.setViewName("tcoursesite/user/userDetailInfo.jsp");
		return mav;
	}
	
	/*****************************************************************
	 * Description:系统管理-用户管理-导出列表
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/exportAll")
	public void exportUserInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam int currpage) throws Exception {
		// 设置分页变量并赋值为20
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		// 获取导出的内容
		List<User> users = userDetailService.getUserTotalRecords(null,currpage, pageSize);
		List<Map> list = new ArrayList<Map>();
		//用于显示序号
		//int i = 0;
		for (User u : users) {
			// 创建新的map接口
			Map map = new HashMap();
			// 参数值映射
			//map.put("id", ++i);
			map.put("userame", u.getUsername());
			map.put("cname", u.getCname());
			if(u.getSchoolAcademy()!=null){
				map.put("academyName", u.getSchoolAcademy().getAcademyName());
			}
			else{
				map.put("academyName", "");
			}
			map.put("userRole", u.getUserRole());
			list.add(map);
		}
		// 设置表头
		String title = "用户详细信息名单";
		// 表头数组
		String[] hearders = new String[] { "用户工号", "用户姓名", "学院", "用户身份"};
		// 表头对应map中key值
		String[] fields = new String[] {"userame", "cname", "academyName","userRole"};
		// 封装表格数据
		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(hearders), fields);
		// 导出工具类
		JsGridReportBase report = new JsGridReportBase(request, response);
		// 将TableDate对象导出到excel
		// systemService.getUser():获取当前登陆人。
		report.exportToExcel(title,shareService.getUser().getCname(), td);
	}
	
	/*****************************************************************
	 * Description:系统管理-用户管理-新建用户
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/newUser")
	public ModelAndView newUser()
	{
		ModelAndView mav = new ModelAndView();
		////当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		//标志位，代表新建
		mav.addObject("newFlag", true);
		//创建日期
		Calendar duedate = Calendar.getInstance();
		//时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//格式转换
		String nowDate =sdf.format(duedate.getTime());
		mav.addObject("duedate", nowDate);
		//新建用户
		mav.addObject("User", new User());
		//左侧栏选中项
		mav.addObject("select", "user");
		//所有部门
		Set<SchoolAcademy> academys=shareService.getAllSchoolAcademy();
		mav.addObject("academys", academys);
		mav.setViewName("tcoursesite/user/newUser.jsp");
		return mav;
	}
	
	/*****************************************************************
	 * Description:系统管理-用户管理-保存用户
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/saveNewUser")
	public String newTerm(@ModelAttribute User user)
	{
		//设置status属性为1
		user.setUserStatus("1");
//		Calendar duedate = Calendar.getInstance();
//		user.setCreatedAt(duedate);
		//保存用户
		userDetailService.saveUser(user);
		return "redirect:/tcoursesite/listUser?currpage=1";
	}
	
}