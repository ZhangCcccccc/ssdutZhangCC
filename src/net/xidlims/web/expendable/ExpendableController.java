package net.xidlims.web.expendable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.Expendable;
import net.xidlims.domain.ExpendableApply;
import net.xidlims.domain.ExpendableApplyAuditRecord;
import net.xidlims.domain.ExpendableStockRecord;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.expendable.ExpendableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller("ExpendableController")
@SessionAttributes("selected_labCenter")
@RequestMapping("/expendable")
public class ExpendableController<JsonResult> {

	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register // static // property // editors.
		binder.registerCustomEditor(java.util.Calendar.class,new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class,new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class,new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	@Autowired
	private ShareService shareService;
	@Autowired
	private ExpendableService expendableService;
	
	/***********************************************************************************
	 * @功能：耗材管理--耗材申购
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	@RequestMapping("/listExpendableRecords")
	public ModelAndView listExpendableRecords(@RequestParam int currpage,int flag,@ModelAttribute Expendable expendable){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		User user = shareService.getUser();//获得当前登录人
		List<Expendable> listExpendableRecords=expendableService.findExpendableRecords(currpage, pageSize, expendable,user,flag);
		//显示导入的耗材，新建的耗材不显示，用flag=0区分
		List<Expendable> listExpendables = expendableService.findExpendableRecords(1, -1, null, user, 0);
		Map users = shareService.getUsersMap();
		//获取总条数
		int totalRecords = expendableService.findExpendableRecords(1, -1, expendable,user,flag).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listExpendableRecords", listExpendableRecords);
		mav.addObject("listExpendables",listExpendables);
		mav.addObject("users",users);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("expendable/listExpendableRecords.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	@RequestMapping("/showExpendableRecordsbyOrderNumber")
	public ModelAndView showExpendableRecordsbyOrderNumber(@RequestParam String orderNumber){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的设备申购
		List<Expendable> expendable = expendableService.findExpendableRecordsbyOrderNumber(orderNumber);
		mav.addObject("expendable", expendable);
		mav.setViewName("expendable/showExpendableRecordsbyOrderNumber.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材记录
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	@RequestMapping("/listExpendableInUse")
	public ModelAndView listExpendableInUse(@RequestParam int currpage,@ModelAttribute Expendable expendable){
		ModelAndView mav = new ModelAndView();
		int pageSize = 15;//设置分页
		User user = shareService.getUser();//获得当前登录人
		//获取当前登录人设备申购的所有信息
		List<Expendable> listExpendableInUse=expendableService.findExpendableInUse(currpage, pageSize, expendable);
		//获取所有耗材名称
		List<Expendable> listExpendableNames=expendableService.findAllExpendableName();
		//获取所有类别
		List<Expendable> listExpendableSources=expendableService.findAllExpendableSource();
		//获取总条数
		int totalRecords = expendableService.findExpendableInUse(1,-1, expendable).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listExpendableInUse", listExpendableInUse);
		mav.addObject("listExpendableNames", listExpendableNames);
		mav.addObject("listExpendableSources", listExpendableSources);
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("userName", user.getUsername());
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("expendable/listExpendableInUse.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材-盘库
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	@RequestMapping("/newExpendablestock")
	public ModelAndView newExpendablestock(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		ExpendableStockRecord expendableStockRecord = new ExpendableStockRecord();
		mav.addObject("id", id);//把expendable的id传过来
		mav.addObject("expendableStockRecord", expendableStockRecord);
		mav.setViewName("expendable/newExpendablestock.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材-盘库保存
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/saveExpendablestock")
	public ModelAndView saveExpendablestock(@RequestParam Integer id,@ModelAttribute ExpendableStockRecord expendableStockRecord){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的设备申购
		Expendable expendable = expendableService.findExpendableByPrimaryKey(id);
		//将消耗品信息保存进去
		expendableStockRecord.setExpendable(expendable);
		expendableStockRecord=expendableService.saveExpendableStockRecord(expendableStockRecord);
		//消耗品来源设置为盘库
		expendable.setExpendableSource("盘库");
		//消耗品数量等于现有数量减去盘库数量
		expendable.setQuantity(expendable.getQuantity()-expendableStockRecord.getStockNumber());
		//保存
		expendableService.saveExpendable(expendable);
		mav.setViewName("redirect:/expendable/listExpendableInUse?currpage=1");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理-查看盘库记录
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/listExpendablestockRecords")
	public ModelAndView listExpendablestockRecords(@RequestParam int currpage,Integer id,@ModelAttribute ExpendableStockRecord expendableStockRecord){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		//通过主键找到消耗品
		Expendable expendable=expendableService.findExpendableByPrimaryKey(id);
		Set<ExpendableStockRecord> listExpendablestockRecords=expendable.getExpendableStockRecords();
		//List<ExpendableStockRecord> listExpendablestockRecords=expendableService.findExpendableStockRecords(currpage, pageSize, expendableStockRecord);
		//获取所有耗材名称
		List<Expendable> listExpendableNames=expendableService.findAllExpendableName();
		//获取总条数
		//int totalRecords=expendable.getExpendableStockRecords().size();
		int totalRecords = listExpendablestockRecords.size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listExpendablestockRecords", listExpendablestockRecords);//传值
		mav.addObject("listExpendableNames",listExpendableNames);
		mav.addObject("expendableStockRecord",expendableStockRecord);
		mav.addObject("pageModel", pageModel);
		mav.addObject("id", id);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("expendable/listExpendablestockRecords.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材-新建耗材记录
	 * @author 郑昕茹
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/newExpendableRecord")
	public ModelAndView newExpendableRecord(){
		ModelAndView mav = new ModelAndView();
		//找到所有申购人（除学生）工号和名称的map
		Map users = shareService.getUsersMap();
		Expendable expendable = new Expendable();
		mav.addObject("expendable", expendable);
		mav.addObject("users", users);
		mav.setViewName("expendable/newExpendableRecord.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材-耗材记录保存 
	 * @author 郑昕茹
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/saveExpendableRecord")
	public ModelAndView saveExpendableRecord(@ModelAttribute Expendable expendable){
		ModelAndView mav = new ModelAndView();
		//标志位设为1，表示新建
		expendable.setFlag(1);
		expendableService.saveExpendable(expendable);
		mav.setViewName("redirect:/expendable/listExpendableInUse?currpage=1");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材-申领
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/newExpendableApply")
	public ModelAndView newExpendableApply(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
		ExpendableApply expendableApply = new ExpendableApply();
		mav.addObject("id", id);//消耗品id
		mav.addObject("expendableApply", expendableApply);
		mav.setViewName("expendable/newExpendableApply.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材-申领保存
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/saveExpendableApply")
	public ModelAndView saveExpendablestock(@RequestParam Integer id,@ModelAttribute ExpendableApply expendableApply){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的设备申购
		Expendable expendable = expendableService.findExpendableByPrimaryKey(id);
		//将消耗品信息保存进去
		expendableApply.setFlag(0);
		//找到当前用户
		expendableApply.setUser(shareService.getUser());
		expendableApply.setBorrowTime(Calendar.getInstance());
		expendableApply.setExpendable(expendable);
		expendableApply=expendableService.saveExpendableApply(expendableApply);
		expendable.setExpendableSource("申领");
		expendableService.saveExpendable(expendable);
		mav.setViewName("redirect:/expendable/listExpendableInUse?currpage=1");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理-查看申领记录
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	@RequestMapping("/listExpendableApplyRecords")
	public ModelAndView listExpendableApplyRecords(@RequestParam int currpage,Integer id,@ModelAttribute ExpendableApply expendableApply){
		ModelAndView mav = new ModelAndView();
		int pageSize = 30;//设置分页
		//通过主键找到消耗品
		//Expendable expendable=expendableService.findExpendableByPrimaryKey(id);
		List<ExpendableApply> listExpendableApplyRecords=expendableService.findExpendableApplies(1,-1,expendableApply,2,id);
		//获取所有耗材名称
		List<Expendable> listExpendableNames=expendableService.findAllExpendableName();
		//获取总条数
		int totalRecords=listExpendableApplyRecords.size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("expendableApply",expendableApply);
		mav.addObject("listExpendableApplyRecords", listExpendableApplyRecords);//传值
		mav.addObject("listExpendableNames",listExpendableNames);
		mav.addObject("pageModel", pageModel);
		mav.addObject("id", id);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("expendable/listExpendableApplyRecords.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--耗材申领审核
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	@RequestMapping("/listExpendableApplies")
	public ModelAndView listExpendableApplies(@RequestParam int currpage,int flag,@ModelAttribute ExpendableApply expendableApply){
		ModelAndView mav = new ModelAndView();
		int pageSize = 20;//设置分页
		User user = shareService.getUser();//获得当前登录人
		//获取当前登录人设备申购的所有信息
		List<ExpendableApply> listExpendableApplies=expendableService.findExpendableApplies(currpage, pageSize, expendableApply, flag,null);
		List<Expendable> listExpendables = expendableService.findExpendableRecords(1, -1, null, user, 0);
		Map users = shareService.getUsersMap();
		//获取总条数
		int totalRecords = expendableService.findExpendableApplies(1, -1, expendableApply, flag,null).size();
		//翻页相关
		Map<String, Integer> pageModel = shareService.getPage(currpage, pageSize, totalRecords);
		mav.addObject("listExpendableApplies", listExpendableApplies);
		mav.addObject("listExpendables",listExpendables);
		mav.addObject("users",users);
		mav.addObject("userName", user.getUsername());
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("currpage", currpage);
		mav.addObject("flag", flag);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("expendable/listExpendableApplies.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--审核(消耗品拥有者)
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	@RequestMapping("/auditExpendableApplyByOwner")
	public ModelAndView auditExpendableApplyByOwner(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的耗材申购
		ExpendableApply expendableApply = expendableService.findExpendableApplyByPrimaryKey(id);
		mav.addObject("expendableApply", expendableApply);
		mav.addObject("id",id);
		mav.setViewName("expendable/auditExpendableApplyByOwner.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--审核（中心主任）
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	@RequestMapping("/auditExpendableApply")
	public ModelAndView auditExpendableApply(@RequestParam Integer id){
		ModelAndView mav = new ModelAndView();
        //通过主键找到所有的耗材申购
		ExpendableApply expendableApply = expendableService.findExpendableApplyByPrimaryKey(id);
		mav.addObject("expendableApply", expendableApply);
		mav.addObject("id",id);
		mav.setViewName("expendable/auditExpendableApply.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：设备申购管理--保存设备申购结果（fen中心主任）
	 * @author 徐文
	 * @日期：2016-07-23
	 * **********************************************************************************/
	@RequestMapping("/saveExpendableApplyByOwner")
	public ModelAndView saveExpendableApplyByOwner(HttpServletRequest request, @RequestParam int id){
		ModelAndView mav = new ModelAndView();
		//通过主键找到所有的设备申购
		ExpendableApply expendableApply = expendableService.findExpendableApplyByPrimaryKey(id);
		//将flag值保存
		expendableApply.setFlag(Integer.valueOf(request.getParameter("flag")));
		if (request.getParameter("remarks")!=null) {
			expendableApply.setRemarks(request.getParameter("remarks"));
		}
		expendableService.saveExpendableApply(expendableApply);
		//将审核结果逐条保存到record表里
		ExpendableApplyAuditRecord expendableApplyAuditRecord = new ExpendableApplyAuditRecord();
		//获取的登录人
        User user = shareService.getUser();
        expendableApplyAuditRecord.setUser(user);
        expendableApplyAuditRecord.setAuditDate(Calendar.getInstance());
        //审核结果是3（拒绝）的时候保存为0，是1，2通过保存为1
        if (Integer.valueOf(request.getParameter("flag"))==3) {
        	expendableApplyAuditRecord.setAuditResult(0);
        }
        if (Integer.valueOf(request.getParameter("flag"))==1) {
        	expendableApplyAuditRecord.setAuditResult(1);
        }
        
        if (Integer.valueOf(request.getParameter("flag"))==2) {
        	expendableApplyAuditRecord.setAuditResult(1);
        	/**
        	 * 将审核通过的耗材新增一条到expendable表里
        	 * 1.通过主键找到申领时起初的expendableori
        	 * 2.new一个新的expendable，将expendableori的信息copy进去
        	 * 3.得到新的expendable，同时将拥有人，时间，数量，来源等重新set进去
        	 * 4.保存
        	 * **/
        	Expendable expendableOri=expendableService.findExpendableByPrimaryKey(expendableApply.getExpendable().getId());
        	expendableOri.setQuantity(expendableOri.getQuantity()-expendableApply.getExpendableNumber());
        	expendableService.saveExpendable(expendableOri);
        	Expendable expendable = new Expendable();
        	expendable.copy(expendableOri);
        	expendable.setArriveQuantity(expendableApply.getExpendableNumber());
        	expendable.setQuantity(expendableApply.getExpendableNumber());
        	expendable.setUser(expendableApply.getUser());
        	expendable.setExpendableSource("申领");
        	BigDecimal expendableNumber = new BigDecimal(expendableApply.getExpendableNumber());
        	expendable.setArriveTotalPrice(expendableApply.getExpendable().getUnitPrice().multiply(expendableNumber));
        	expendable.setFlag(1);
        	expendable.setPurchaseDate(expendableApply.getBorrowTime());
        	expendableService.saveExpendable(expendable);
        }
        expendableApplyAuditRecord.setRemark(expendableApply.getRemarks());
        //关联表保存
        expendableApplyAuditRecord.setExpendableApply(expendableApply);
        expendableService.saveExpendableApplyAuditRecord(expendableApplyAuditRecord);
		mav.setViewName("redirect:/expendable/listExpendableApplies?currpage=1&flag=0");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材记录（修改存放位置）
	 * @author 郑昕茹
	 * @日期：2016-07-29
	 * **********************************************************************************/
	@RequestMapping("/modifyPlace")
	public ModelAndView modifyPlace(@RequestParam int id){
		ModelAndView mav = new ModelAndView();
		Expendable expendable = expendableService.findExpendableByPrimaryKey(id);
		String place = expendable.getPlace();
		mav.addObject("place",place);
		mav.addObject("id", id);
		
		mav.setViewName("expendable/modifyPlace.jsp");
		return mav;
	}
	/***********************************************************************************
	 * @功能：耗材管理--在用耗材记录（保存存放位置）
	 * @author 郑昕茹
	 * @日期：2016-07-29
	 * **********************************************************************************/
	@RequestMapping("/savePlace")
	public ModelAndView savePlace(@RequestParam int id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String place = request.getParameter("place");
		Expendable expendable = expendableService.findExpendableByPrimaryKey(id);
		expendable.setPlace(place);
		expendableService.saveExpendable(expendable);
		mav.setViewName("redirect:/expendable/listExpendableInUse?currpage=1");
		return mav;
	}
}