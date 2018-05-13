package net.xidlims.web.tcoursesite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemAuthorityService;
import net.xidlims.service.system.showAcademyAuthority;

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

/*****************************************************************
 * Description:系统管理-权限管理
 * 
 * @author:于侃
 * @date:2016-08-24
 *****************************************************************/

@Controller("TSystemAuthorityController")
public class TSystemAuthorityController<JsonResult>{
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register
																				// static
																				// property
																				// editors.
		binder.registerCustomEditor(java.util.Calendar.class,
				new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
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
	}
	
	

	@Autowired
	private SystemAuthorityService systemAuthorityService;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LabCenterService labCenterService;
	
	/*****************************************************************
	 * Description:系统管理-权限管理-权限列表
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/listUserAuthority")
	public ModelAndView listUserAuthority() {
		ModelAndView mav = new ModelAndView();
		//当前登录人
		User user=shareService.getUser();
		mav.addObject("user", user);
		
		//判断当前登陆人是否为项目立项的审核人或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		//中心主任
		boolean isEXCENTERDIRECTOR;
		//超级管理员
		boolean isSUPERADMIN;
		if(judge.indexOf(",11,")>-1){
			isSUPERADMIN = true;
		}else isSUPERADMIN = false;
		if(judge.indexOf(",4,")>-1){
			isEXCENTERDIRECTOR = true;
		}else isEXCENTERDIRECTOR = false;
		mav.addObject("isEXCENTERDIRECTOR", isEXCENTERDIRECTOR);
		mav.addObject("isSUPERADMIN", isSUPERADMIN);
		//读取对应权限人员列表
		List<showAcademyAuthority>  AllUserAuthority=systemAuthorityService.getUserRecords();
		List<showAcademyAuthority>  userAuthorityForEXCENTERDIRECTOR=new ArrayList<showAcademyAuthority>();
		for (showAcademyAuthority showAcademyAuthority : AllUserAuthority) {
			if (showAcademyAuthority.getAuthorityId()<=7
					||showAcademyAuthority.getAuthorityId()==13
					||showAcademyAuthority.getAuthorityId()==18) {
				userAuthorityForEXCENTERDIRECTOR.add(showAcademyAuthority);
			}else {
				
			}
		}
		mav.addObject("AllUserAuthority",AllUserAuthority);
		mav.addObject("userAuthorityForEXCENTERDIRECTOR",userAuthorityForEXCENTERDIRECTOR);
		//左侧栏选中项
		mav.addObject("select", "authority");
		mav.setViewName("/tcoursesite/userAuthorityMange/listUserAuthority.jsp");
		return mav;
	}
	
	/*****************************************************************
	 * Description:系统管理-权限管理-根据权限id查看该权限下的人数
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/listUserAuthorityDetail")
	public ModelAndView showAcademyAuthority(@ModelAttribute User formUser,@RequestParam int page,int Id) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		User user=shareService.getUser();
		mav.addObject("user", user);
		//查询表单的对象
		mav.addObject("formUser",formUser);
		int pageSize=10;//每页20条记录
		//查询出来的总记录条数
		int totalRecords=systemAuthorityService.findUserByIdAndUser(formUser,Id);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<User> listUser=systemAuthorityService.findUserByIdAndUser(formUser,page,pageSize,Id);
		mav.addObject("listUser",listUser);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		mav.addObject("authority",authorityDAO.findAuthorityById(Id));
		
		mav.addObject("Id",Id);
		//所有部门
		Set<SchoolAcademy> academys=shareService.getAllSchoolAcademy();
		mav.addObject("academys", academys);
		//左侧栏选中项
		mav.addObject("select", "authority");
		mav.setViewName("/tcoursesite/userAuthorityMange/listUserAuthorityDetail.jsp");

	   	return mav;
		
	}
	
	/*****************************************************************
	 * Description:系统管理-权限管理-删除权限下员工
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/deleteUserAuthority")
	public String deleteUserAuthority(String username,int Id,int page){
		User users=userDAO.findUserByPrimaryKey(username);
		//通过id获取权限
		Authority authoritys=authorityDAO.findAuthorityById(Id);
		//删除权限下员工
		systemAuthorityService.deleteUserAuthority(users,authoritys);
		
		return "redirect:/tcoursesite/listUserAuthorityDetail?page="+page+"&Id="+Id;
	}
	
	/*****************************************************************
	 * Description:系统管理-权限管理-新建权限下人员
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/newUserAuthority")
	public ModelAndView newUserAuthority(int Id,int page){
		ModelAndView mav=new ModelAndView();
		//新建用户
		mav.addObject("listUser", new User());
		
		mav.addObject("Id", Id);
		mav.addObject("page", page);
		//通过id获取权限
		mav.addObject("authority",authorityDAO.findAuthorityById(Id));
		//通过id获取学院
		mav.addObject("userMap",systemAuthorityService.findAllAcademyMap(Id));
		
		mav.setViewName("/tcoursesite/userAuthorityMange/newUserAuthority.jsp");

	   	return mav;
	}
	
	/*****************************************************************
	 * Description:系统管理-权限管理-AJAX 根据姓名、工号查询当前所在学院的用户以及不属于12个学院的用户
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/findUserByCnameAndUsername")
	public @ResponseBody String findUserByCnameAndUsername(@RequestParam String cname,String username,String academy,Integer authorityId,Integer page) {
		//分页开始
		int totalRecords=systemAuthorityService.findUserByCnameAndUname(cname,username,authorityId,academy);
		//每页显示记录数
		int pageSize=20;
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<User> userList=systemAuthorityService.findUserByUserAndId(cname,username,authorityId,academy,page,pageSize);
	    String s="";
	    for (User d : userList) {
	    	String acd="";
	    	if(d.getSchoolAcademy()!=null){
	    		acd=d.getSchoolAcademy().getAcademyName();
	    	}
			s+="<tr>"+
	    	"<td>"+d.getCname()+"</td>"+
			"<td>"+d.getUsername()+"</td>"+
			"<td>"+acd+"</td>"+
			"<td><input type='checkbox' name='CK_name' value='"+d.getUsername()+"'/></td>"+
			"</tr>";			
		}
	    s+="<tr><td colspan='6'>"+
	    	    "<a href='#' onclick='firstPage(1);'>"+"首页"+"</a>&nbsp;"+
	    	    "<a href='#' onclick='previousPage("+page+");'>"+"上一页"+"</a>&nbsp;"+
	    	    "<a href='#' onclick='nextPage("+page+","+pageModel.get("totalPage")+");'>"+"下一页"+"</a>&nbsp;"+
	    	    "<a href='#' onclick='lastPage("+pageModel.get("totalPage")+");'>"+"末页"+"</a>&nbsp;"+
	    	    "当前第"+page+"页&nbsp; 共"+pageModel.get("totalPage")+"页  "+totalRecords+"条记录"+
	    	    		"</td></tr>";
		return shareService.htmlEncode(s);
	}

	/*****************************************************************
	 * Description:系统管理-权限管理-保存权限下人员
	 * 
	 * @author:于侃
	 * @date:2016-08-24
	 *****************************************************************/
	@RequestMapping("/tcoursesite/saveUserAuthority")
	public ModelAndView saveUserAuthority(@RequestParam Integer authorityId,String[] array) {
		ModelAndView mav=new ModelAndView();
		//authorityId对应的权限
		Authority a=authorityDAO.findAuthorityByPrimaryKey(authorityId);
		for (String i : array) {
	  		//username对应的用户
			User u=userDAO.findUserByPrimaryKey(i);
			Set<Authority> authoritys=u.getAuthorities();
			authoritys.add(a);
			u.getAuthorities().addAll(authoritys);
			//保存员工
			userDAO.store(u);
	  	}		
		
		mav.setViewName("redirect:/tcoursesite/listUserAuthorityDetail?page=1&Id="+authorityId);
		return mav;
	}
	
}
