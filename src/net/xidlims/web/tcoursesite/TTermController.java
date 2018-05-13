/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/system/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.tcoursesite;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.TermDetailService;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.User;

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
 * Description:系统管理-学期管理
 * 
 * @author：于侃
 * @date ：2016-08-24
 **************************************************************************/

@Controller("TTermController")
public class TTermController<JsonResult>{
	/**************************************************************************
	 * Description:系统管理-学期管理-初始化WebDataBinder，这个WebDataBinder
	 * 用于填充被@InitBinder注释的处理方法的command和form对象
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
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
	TermDetailService termDetailService;
	@Autowired
	ShareService shareService;
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	
	/**************************************************************************
	 * Description:系统管理-学期管理-学期列表
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping("/tcoursesite/listTerm")
	public ModelAndView listTerm(@RequestParam int currpage) {
		ModelAndView mav = new ModelAndView();	
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//设置学期表的总记录数并赋值
		int totalRecords = termDetailService.getTermTotalRecords();
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("newFlag", true);
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("terms", termDetailService.findAllTerms(currpage - 1, pageSize));
		//左侧栏选中项
		mav.addObject("select", "term");
		// 将该Model映射到listTerm.jsp;
		mav.setViewName("tcoursesite/term/listTerm.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:系统管理-学期管理-新增学期
	 * 
	 * @author：于侃
	 * @date ：2016-08-29
	 **************************************************************************/
	@RequestMapping("/tcoursesite/newTerm")
	public ModelAndView newTerm(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.addObject("newFlag", true);
		mav.addObject("schoolTerm", new SchoolTerm());
		mav.addObject("page", shareService.getCurrpage(request));
		
		//左侧栏选中项
		mav.addObject("select", "term");
		mav.setViewName("tcoursesite/term/newTerm.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:系统管理-学期管理-修改学期
	 * 
	 * @author：于侃
	 * @date ：2016-08-29
	 **************************************************************************/
	@RequestMapping("/tcoursesite/editTerm")
	public ModelAndView editTerm(@RequestParam Integer idKey)
	{
		ModelAndView mav = new ModelAndView();
		//当前登陆人
		User user = shareService.getUser();
		mav.addObject("user", user);
		mav.addObject("newFlag", true);
		mav.addObject("idKey", idKey);
		//通过id查询学期
		SchoolTerm schoolTerm=schoolTermDAO.findSchoolTermByPrimaryKey(idKey);
		//时间格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		mav.addObject("termStart",sdf.format(schoolTerm.getTermStart().getTime()));
		mav.addObject("termEnd",sdf.format(schoolTerm.getTermEnd().getTime()));
		mav.addObject("schoolTerm",schoolTerm);
		
		//左侧栏选中项
		mav.addObject("select", "term");
		mav.setViewName("tcoursesite/term/editTerm.jsp");
		return mav;
	}
	
	/**************************************************************************
	 * Description:系统管理-学期管理-删除学期
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping ("/tcoursesite/deleteTerm")
	public String deleteTerm(@RequestParam Integer idKey){
		//通过id查询学期
		SchoolTerm schoolTerm = termDetailService.findTermById(idKey);
		//删除学期
		termDetailService.deleteTerm(schoolTerm);
		return "redirect:/tcoursesite/listTerm?currpage=1";
	}
	/**************************************************************************
	 * Description:系统管理-学期管理-根据学期生成周次
	 * @param：id为学期的主键id
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping("/tcoursesite/createWeek")
	public String createWeek(@RequestParam int id)
	{
		//通过id查找学期
		SchoolTerm schoolTerm = termDetailService.findTermById(id);
		//生成周次
		termDetailService.createWeek(schoolTerm);
		return "redirect:/tcoursesite/listTerm?currpage=1";		
	}
	
	/**************************************************************************
	 * Description:系统管理-学期管理-保存新建的学期
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping("/tcoursesite/saveNewTerm")
	public String saveTerm(@ModelAttribute SchoolTerm schoolTerm){
		//保存学期
		termDetailService.saveTerm(schoolTerm);
		return "redirect:/tcoursesite/listTerm?currpage=1";
	}
	/**************************************************************************
	 * Description:系统管理-学期管理-判断学期名称是否重复
	 * 
	 * @author：于侃
	 * @date ：2016-08-24
	 **************************************************************************/
	@RequestMapping("/tcoursesite/checkTerm")
	public @ResponseBody	String checkTerm(@RequestParam String termName)
	{
		//根据学期名称获取学期
		Set<SchoolTerm> terms = termDetailService.findTermsByTermName(termName);
		if (terms.size() == 0)
		{
			//没用重复
			return "no";
		}
		else
		{
			//有重复
			return "ok";
		}
	}
}