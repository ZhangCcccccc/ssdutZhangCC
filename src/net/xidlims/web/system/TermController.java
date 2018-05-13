/****************************************************************************
 * 命名规范：
 * 本控制层调整必须是按照如下的路径调整：@RequestMapping("/device/system/sample")，全部小写。
 * 列表信息listxxxx，獲取信息：getxxxx 编辑信息：editxxxx 删除信息：detelexxxx 新增信息 newxxxx
 * 导出信息exportxxxx 保存信息：savexxxx 
 ****************************************************************************/

package net.xidlims.web.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.xidlims.domain.CDictionary;
import javax.servlet.http.HttpServletRequest;
import net.xidlims.dao.SchoolTermActiveDAO;
import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.system.TermDetailService;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;

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

/****************************************************************************
 * 功能：系统后台管理模块
 * 作者：魏诚
 * 时间：2014-07-14
 ****************************************************************************/
@Controller("TermController")
@SessionAttributes("selected_labCenter")
public class TermController<JsonResult>{
	/************************************************************ 
	 * @初始化WebDataBinder，这个WebDataBinder用于填充被@InitBinder注释的处理
	 * 方法的command和form对象
	 *
	 ************************************************************/
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
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	@Autowired
	private SchoolTermActiveDAO schoolTermActiveDAO;
	/************************************************************ 
	 * @内容：学期列表
	 * @作者：叶明盾
	 * @日期：2014-07-28
	 ************************************************************/
	@RequestMapping("/system/listTerm")
	public ModelAndView listTerm(@RequestParam int currpage,@ModelAttribute("selected_labCenter") Integer cid) {
		ModelAndView mav = new ModelAndView();		
		// 设置分页变量并赋值为20；
		int pageSize = CommonConstantInterface.INT_PAGESIZE;
		//设置学期表的总记录数并赋值
		int totalRecords = termDetailService.getTermTotalRecords();
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("newFlag", true);
		mav.addObject("pageModel", pageModel);
		mav.addObject("page", currpage);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("cid", cid);
		mav.addObject("terms", termDetailService.findAllTerms(currpage - 1, pageSize));
		// 将该Model映射到listTerm.jsp;
		mav.setViewName("system/term/listTerm.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：新增学期
	 * @作者：叶明盾
	 * @日期：2014-07-28
	 ************************************************************/
	@RequestMapping("/system/newTerm")
	public ModelAndView newTerm(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("newFlag", true);
		mav.addObject("schoolTerm", new SchoolTerm());
//		mav.addObject("departments", shareService.getDepartmentsMap());
//		mav.addObject("educations", shareService.getEducationsMap());
//		mav.addObject("degrees", shareService.getDegreesMap());
//		mav.addObject("genders", shareService.getGendersMap());
		mav.addObject("page", shareService.getCurrpage(request));
		mav.addObject("cDictionarys", termDetailService.findCTeachingDateType());
		mav.setViewName("system/term/newTerm.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：修改学期
	 * @作者: 李鹏翔
	 ************************************************************/
	@RequestMapping("/system/editTerm")
	public ModelAndView editTerm(@RequestParam Integer idKey)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("newFlag", true);
		mav.addObject("idKey", idKey);
		SchoolTerm schoolTerm=schoolTermDAO.findSchoolTermByPrimaryKey(idKey);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		mav.addObject("termStart",sdf.format(schoolTerm.getTermStart().getTime()));
		mav.addObject("termEnd",sdf.format(schoolTerm.getTermEnd().getTime()));
		mav.addObject("schoolTerm",schoolTerm);
		List<CDictionary> cDictionarys=termDetailService.findCTeachingDateType();
		mav.addObject("cDictionarys", cDictionarys);
		//List<Object[]> weeks = termDetailService.findSchoolWeeksStartAndEnd(idKey);
		mav.addObject("cDictionarys", termDetailService.findCTeachingDateType());
		List<Object[]> weeks = termDetailService.findSchoolWeeksStartAndEnd(idKey);
		mav.addObject("weeks", weeks);
		mav.setViewName("system/term/editTerm.jsp");
		return mav;
	}
	
	/************************************************************ 
	 * @内容：删除学期
	 * @作者: 李鹏翔
	 ************************************************************/
	@RequestMapping ("/system/deleteTerm")
	public String deleteTerm(@RequestParam Integer idKey){
		SchoolTerm schoolTerm = termDetailService.findTermById(idKey);
		termDetailService.deleteTerm(schoolTerm);
		return "redirect:/system/listTerm?currpage=1";
	}
	/************************************************************ 
	 * @内容：根据学期生成周次
	 * @param：id为学期的主键id
	 * @作者：叶明盾
	 * @日期：2014-07-28
	 ************************************************************/
	@RequestMapping("/system/createWeek")
	public String createWeek(@RequestParam int id)
	{
		SchoolTerm schoolTerm = termDetailService.findTermById(id);	
		termDetailService.createWeek(schoolTerm);
		return "redirect:/system/listTerm?currpage=1";		
	}
	
	/************************************************************ 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * @内容：保存新建的学期
	 * @作者：李鹏翔
	 ************************************************************/
	@RequestMapping("/system/saveNewTerm")
	public String saveTerm(@ModelAttribute SchoolTerm schoolTerm, HttpServletRequest request) throws NumberFormatException, ParseException{
		String[] types = request.getParameterValues("type");
		schoolTerm = termDetailService.saveTerm(schoolTerm);
		
		Integer count = 0;
		if(types != null && types.length != 0){
			for(String type:types){
				count ++;
				String startDate = request.getParameter("startDate"+count);
				String endDate = request.getParameter("endDate"+count);
				if(startDate != null && endDate != null){
					termDetailService.createSpecialWeekByDateAndTypeAndTerm1(startDate, endDate, Integer.parseInt(type), schoolTerm);
				}
			}
			
		}
		return "redirect:/system/listTerm?currpage=1";
	}
	/*************************************************************
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * @内容：修改学期的活动
	 * @作者：郭明杰
	 ***********************************************************/
	@RequestMapping("/system/saveeditor")
	public String saveeditor(@ModelAttribute SchoolTerm schoolTerm,HttpServletRequest request) throws NumberFormatException, ParseException{
		String[] types = request.getParameterValues("type");		
		Integer count = 0;
		//保存学期
		schoolTerm = termDetailService.saveTerm(schoolTerm);
		
		termDetailService.findSchoolWeekByTermAndDelete(schoolTerm.getId());
		//查找所有SchoolTermActives
		termDetailService.deleteAllSchoolTermActives(schoolTerm.getId());
		if(types != null && types.length != 0){
			for(String type:types){
				count ++;
				String startDate = request.getParameter("startDate"+count);
				String endDate = request.getParameter("endDate"+count);
				if(startDate != null && endDate != null){
					termDetailService.createSpecialWeekByDateAndTypeAndTerm(startDate, endDate, Integer.parseInt(type), schoolTerm);
				}
			}
			
		}
		return "redirect:/system/listTerm?currpage=1";
	}
	/************************************************************ 
	 * @内容：判断学期名称是否重复
	 * @作者：叶明盾
	 * @日期：2014-07-29
	 ************************************************************/
	@RequestMapping("/system/checkTerm")
	public @ResponseBody	String checkTerm(@RequestParam String termName)
	{
		Set<SchoolTerm> terms = termDetailService.findTermsByTermName(termName);
		if (terms.size() == 0)
		{
			return "no";
		}
		else
		{
			return "ok";
		}
	}
	
	
	/************************************************************ 
	 * @description：查看学期日历
	 * @author：郑昕茹
	 * @date：2017-04-07
	 ************************************************************/
	@RequestMapping("/system/viewSchoolTerm")
	public ModelAndView viewSchoolTerm(@RequestParam Integer idKey)
	{
		ModelAndView mav = new ModelAndView();
		SchoolTerm schoolTerm = termDetailService.findTermById(idKey);	
		List<Object[]> schoolTermWeeks = termDetailService.findViewSchoolTermWeek(idKey);
		mav.addObject("schoolTerm", schoolTerm);
		mav.addObject("schoolTermWeeks", schoolTermWeeks);
		mav.addObject("specialSchoolWeeks", termDetailService.findSpecialSchoolWeekByTerm(idKey));
		
		mav.setViewName("system/term/viewSchoolTerm.jsp");
		return mav;
	}
}