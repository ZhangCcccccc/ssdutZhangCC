package net.xidlims.web.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabCenterService;
import net.xidlims.service.system.SystemAuthorityService;
import net.xidlims.service.system.showAcademyAuthority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller("SystemAuthorityController")
@SessionAttributes("selected_labCenter")
public class SystemAuthorityController<JsonResult>{

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
	@Autowired
	private LabCenterDAO labCenterDAO;
	/************************************************************
	 * @权限管理模块入口
	 * @作者：喻泉声
	 * @日期：2014-09-4
	 ************************************************************/
	@RequestMapping("/userAuthorityMange/listUserAuthority")
	public ModelAndView listUserAuthority(@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();
		String academyNumber="";
		if(cid != null & cid != -1)
		{
			// 中心所属学院
			academyNumber = labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		}
		else{
			academyNumber=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		}
		//当前登录人
		User user=shareService.getUser();
		
		//判断当前登陆人是否为项目立项的审核人或者超级管理员
		String judge=",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		boolean isEXCENTERDIRECTOR;//中心主任
		boolean isSUPERADMIN;//超级管理员
		if(judge.indexOf(",11,")>-1){
			isSUPERADMIN = true;
		}else isSUPERADMIN = false;
		if(judge.indexOf(",4,")>-1){
			isEXCENTERDIRECTOR = true;
		}else isEXCENTERDIRECTOR = false;
		mav.addObject("isEXCENTERDIRECTOR", isEXCENTERDIRECTOR);
		mav.addObject("isSUPERADMIN", isSUPERADMIN);
		
		List<showAcademyAuthority>  AllUserAuthority=systemAuthorityService.getUserTotalRecords();
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
		mav.setViewName("/system/userAuthorityMange/listUserAuthority.jsp");
		return mav;
	}
	
	/************************************************************
	 * @根据权限id查看该权限下的人数
	 * @作者：喻泉声
	 * @日期：2014-09-4
	 ************************************************************/
	@RequestMapping("/userAuthorityMange/listUserAuthorityDetail")
	public ModelAndView showAcademyAuthority(@ModelAttribute User user,@RequestParam int page,int Id,@ModelAttribute("selected_labCenter") Integer cid) {
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		
		//查询表单的对象
		mav.addObject("user",user);
		//中心所属学院
		//String academyNumber=labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		//String academyNumber=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		//mav.addObject("academyNumber", academyNumber);
		int pageSize=10;//每页20条记录
		//查询出来的总记录条数
		//int totalRecords=systemAuthorityService.findUserByUser(user,Id,academyNumber);
		int totalRecords=systemAuthorityService.findUserByUser(user,Id);
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		//List<User> listUser=systemAuthorityService.findUserByUser(user,page,pageSize,Id,academyNumber);
		List<User> listUser=systemAuthorityService.findUserByUser(user,page,pageSize,Id);
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
		mav.setViewName("/system/userAuthorityMange/listUserAuthorityDetail.jsp");
		List<LabCenter> centers = labCenterService.findAllLabCenterByQuery(new LabCenter(), 1, -1);
		mav.addObject("listLabCenter", centers);
	   	return mav;
		
	}
	
	/************************************************************
	 * @删除权限管理模块信息
	 * @作者：喻泉声
	 * @日期：2014-09-4
	 ************************************************************/
	@RequestMapping("/userAuthorityMange/deleteUserAuthority")
	public String deleteUserAuthority(String username,int Id,int page){
		User users=userDAO.findUserByPrimaryKey(username);
		Authority authoritys=authorityDAO.findAuthorityById(Id);
		
		systemAuthorityService.deleteUserAuthority(users,authoritys);
		
		return "redirect:/userAuthorityMange/listUserAuthorityDetail?page="+page+"&Id="+Id;
	}
	
	/************************************************************
	 * @新建权限管理模块信息
	 * @作者：喻泉声
	 * @日期：2014-09-4
	 ************************************************************/
	@RequestMapping("/userAuthorityMange/newUserAuthority")
	public ModelAndView newUserAuthority(int Id,int page){
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("listUser", new User());
		
		mav.addObject("Id", Id);
		mav.addObject("page", page);
		mav.addObject("authority",authorityDAO.findAuthorityById(Id));
		
		mav.addObject("userMap",systemAuthorityService.findAllAcademyMap(Id));
		
		mav.setViewName("/system/userAuthorityMange/newUserAuthority.jsp");

	   	return mav;
	}
	
	/****************************************************************************
	 * 功能：AJAX 根据姓名、工号查询当前所在学院的用户以及不属于12个学院的用户
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/userAuthorityMange/findUserByCnameAndUsername")
	public @ResponseBody String findUserByCnameAndUsername(@RequestParam String cname,String username,String academy,Integer authorityId,Integer page,@ModelAttribute("selected_labCenter") Integer cid) {
		//当前所在中心的学院
		//String academyNumber=labCenterService.findLabCenterByPrimaryKey(cid).getSchoolAcademy().getAcademyNumber();
		/*String academyNumber=shareService.getUser().getSchoolAcademy().getAcademyNumber();
		String academyForSearch="";
		if (academy!=null&&!academy.equals("")) {
			academyForSearch=academy;
		}else {
			academyForSearch=academyNumber;
		}*/
		//分页开始
		//int totalRecords=systemAuthorityService.findUserByCnameAndUsername(cname,username,authorityId,academyForSearch);
		int totalRecords=systemAuthorityService.findUserByCnameAndUsername(cname,username,authorityId);
		int pageSize=20;
		Map<String,Integer> pageModel =shareService.getPage( page,pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<User> userList=systemAuthorityService.findUserByUserAndSchoolAcademy(cname,username,authorityId,page,pageSize);
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
	/****************************************************************************
	 * 功能：权限下的用户
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/userAuthorityMange/saveUserAuthority")
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
			userDAO.store(u);
	  	}		
		
		mav.setViewName("redirect:/userAuthorityMange/listUserAuthorityDetail?page=1&Id="+authorityId);
		return mav;
	}
	
	
	/****************************************************************************
	 * 功能：AJAX 保存用户所属实验中学
	 * 作者：李小龙
	 ****************************************************************************/
	@RequestMapping("/userAuthorityMange/setUserLabCenter")
	public @ResponseBody String setUserLabCenter(@RequestParam Integer centerId,String username){
		User user = userDAO.findUserByPrimaryKey(username);
		LabCenter labCenter = labCenterDAO.findLabCenterById(centerId);
		user.setLabCenter(labCenter);
		userDAO.store(user);
		return "success";
	}
}
