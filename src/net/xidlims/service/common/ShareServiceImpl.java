package net.xidlims.service.common;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luxunsh.util.DateUtil;
import net.xidlims.service.EmptyUtil;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.AuthorityMenuDAO;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.CStaticValueDAO;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.IotSharePowerOpentimeDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.OperationOutlineDAO;
import net.xidlims.dao.SchoolAcademyDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.SchoolCourseDAO;
import net.xidlims.dao.SchoolCourseStudentDAO;
import net.xidlims.dao.SchoolMajorDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.dao.SystemBuildDAO;
import net.xidlims.dao.SystemTimeDAO;
import net.xidlims.dao.TimetableAppointmentDAO;
import net.xidlims.dao.TimetableAttendanceDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.CStaticValue;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.IotSharePowerOpentime;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomLimitTime;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.SchoolCourse;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseStudent;
import net.xidlims.domain.SchoolMajor;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SystemBuild;
import net.xidlims.domain.SystemTime;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableAttendance;
import net.xidlims.domain.User;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.device.LabRoomDeviceService;
import net.xidlims.service.lab.LabAnnexService;
import net.xidlims.service.lab.LabRoomService;
import net.xidlims.service.tcoursesite.WkUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import excelTools.DBConnect;
import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.MatrixToImageWriter;
import excelTools.People;
import excelTools.TableData;

@Service("ShareService")
public class ShareServiceImpl implements ShareService {
	//读取属性文件中specialAcademy对应的值（此方法需要在web-content.xml中增加配置）
	@Value("${showDevice}")
	private String showDeviceURL;
	
	/*
	 * 将UserDAO引入，并重命名为userDAO;
	 */
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SchoolTermDAO schoolTermDAO;
		
	/*
	 * 自动引入SchoolWeekDAO,并重命名为schoolWeekDAO;
	 */
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	
	
	
	/*
	 * 自动引入AuthorityDAO,并重命名为authorityDAO; 鲁静 2013.08.26
	 */
	@Autowired
	private AuthorityDAO authorityDAO;
	/*
	 * 自动引入SysClassesDAO,并重命名为sysClassesDAO; 鲁静 2013.08.26
	 */
	@Autowired
	private SchoolClassesDAO schoolClassesDAO;
	@Autowired
	private SchoolAcademyDAO schoolAcademyDAO;
	@Autowired
	private SchoolCourseDAO schoolCourseDAO;
	@Autowired
	private SystemBuildDAO systemBuildDAO;
	@Autowired
	private TimetableAppointmentDAO timetableAppointmentDAO;
	@Autowired
	private SchoolCourseStudentDAO schoolCourseStudentDAO;
	@Autowired
	private TimetableAttendanceDAO timetableAttendanceDAO;
	@Autowired
	private CDictionaryDAO cDictionaryDAO;
	@Autowired
	private AuthorityMenuDAO authorityMenuDAO;
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private CommonDocumentDAO documentDAO;
	@Autowired
	private LabRoomService labRoomService;
	@Autowired
	private LabAnnexService labAnnexService;
	@Autowired
	private LabRoomDeviceService labRoomDeviceService;
	@Autowired
	private SystemTimeDAO systemTimeDAO;
	@Autowired
	private
	LabRoomDAO labRoomDAO;
	@Autowired
	private
	IotSharePowerOpentimeDAO iotSharePowerOpentimeDAO;
	
	@Autowired
	private SchoolMajorDAO schoolMajorDAO;
	
	@Autowired
	private CStaticValueDAO cStaticValueDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private OperationOutlineDAO operationOutlineDAO;
	@Autowired
	private CommonDocumentDAO  commonDocumentDAO;
	/*
	 * Instantiates a new ShareServiceImpl.
	 */
	public ShareServiceImpl() {
	}
	
	/**
	 * 获取字典数据
	 * @author hly
	 *2015.08.03
	 */
	@Override
	public List<CDictionary> getCDictionaryData(String category) {
		StringBuffer hql = new StringBuffer("select c from CDictionary c where 1=1 ");
		if(category!=null && !"".equals(category))
		{
			hql.append(" and c.CCategory = '"+category+"' order by c.id");
			
			return cDictionaryDAO.executeQuery(hql.toString(), 0, -1);
		}
		
		return null;
	}

	/**
	 * 根据类别和编号获取字典数据
	 * @author hly
	 * 2015.08.04
	 */
	@Override
	public CDictionary getCDictionaryByCategory(String category, String number) {
		StringBuffer hql = new StringBuffer("select c from CDictionary c where 1=1 ");
		if(category!=null && !"".equals(category) && number!=null && !"".equals(number))
		{
			hql.append(" and c.CCategory='"+category+"' and c.CNumber='"+number+"'");
			
			List<CDictionary> cDictionaries = cDictionaryDAO.executeQuery(hql.toString(), 0, -1);
			
			if(cDictionaries.size() > 0)
			{
		        return cDictionaries.get(0);
			}
		}
		
		return null;
	}
	
	/**
	 * 获取所有的学期数据
	 * @author hly
	 * 2015.08.03
	 */
	@Override
	public List<SchoolTerm> findAllSchoolTerm() {
		StringBuffer hql = new StringBuffer("select t from SchoolTerm t ");
		hql.append(" order by t.termStart desc");
		
		return schoolTermDAO.executeQuery(hql.toString(), 0, -1);
	}

	/*
	 * 分页显示
	 */
	
	@Override
	@Transactional
	public Map<String, Integer> getPage(int currpage, int pageSize, int totalRecords) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		PageModel pageModel = new PageModel();
		pageModel.setTotalRecords(totalRecords);
		pageModel.setPageSize(pageSize);
		pageModel.setCurrpage(currpage);
		map.put("totalPage", pageModel.getTotalPage());
		map.put("nextPage", pageModel.getNextPage());
		map.put("previousPage", pageModel.getPreiviousPage());
		map.put("firstPage", pageModel.getFisrtPage());
		map.put("lastPage", pageModel.getLastPage());
		map.put("currpage", pageModel.getCurrpage());
		map.put("totalRecords", totalRecords);
		map.put("pageSize", pageSize);
		return map;
	}

	/*
	 * 获取登录人的所有信息
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getUser()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getUser()
	 */
	@Override
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null
				&& (!AnonymousAuthenticationToken.class.isAssignableFrom(auth
						.getClass()))) {
			User user = userDAO.findUserByUsername(SecurityContextHolder
					.getContext().getAuthentication().getName());
			return user;
		} else {
			return null;
		}
	}

	/*
	 * 处理中文乱码 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#htmlEncode(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#htmlEncode(java.lang.String)
	 */
	@Override
	public String htmlEncode(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			if (c > 127 && c != 160) {
				sb.append("&#").append(c).append(";");
			} else {
				sb.append((char) c);
			}
		}
		return sb.toString();
	}

	/*
	 * 获得当前页数
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getCurrpage(javax.servlet.http.HttpServletRequest)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getCurrpage(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public int getCurrpage(HttpServletRequest request) {
		String url = request.getHeader("Referer");
		int page = 0;
		String ul = url.substring(url.length() - 1);
		String equalSign = url.substring(url.length() - 2, url.length() - 1);
		if (equalSign.equals("=")) {

			page = Integer.parseInt(ul);
		} else {
			page = 1;
		}
		return page;
	}

	
	/*
	 * 查找到最近的学期； 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#findNewTerm()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#findNewTerm()
	 */
	@Override
	public SchoolTerm findNewTerm() {
		/*
		 * //获取当前时间 Calendar now=Calendar.getInstance();
		 */
		// 新建一个schoolTerm的对象
		SchoolTerm schoolTerm = new SchoolTerm();
		// 创建一条按创建时间倒序排序的语句；
		StringBuffer sb = new StringBuffer(
				"select t from SchoolTerm t where 1=1 and now() between t.termStart and t.termEnd or now() < t.termStart order by t.termStart asc");
		// 给语句添加分页机制；
		List<SchoolTerm> terms = schoolTermDAO.executeQuery(sb.toString());
		// 如果terms大于0；
		if (terms.size() > 0) {
			// 获取schoolWeek的第一个
			schoolTerm = terms.get(0);
		}
		return schoolTerm;
	}

	
	/*
	 * 获取当前日期的字符串 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getDate1()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getDate1()
	 */
	@Override
	public String getDate1() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		// 生成1到100之间的随机整数
		int i = 1 + (int) (Math.random() * 100);
		// 转换i成字符串格式然后再和date拼接赋值给变量randomString；
		String randomString = date + String.valueOf(i);
		return randomString;
	}

	
	/*
	 * 获取所有的周次；
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getWeeksMap(int)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getWeeksMap(int)
	 */
	@Override
	public Map getWeeksMap(int termId) {
		// 新建一个HashMap对象；
		Map weeksMap = new HashMap();
		// 创建一条通过周次和星期分组的语句；
		StringBuffer sb1 = new StringBuffer(
				"select s from SchoolWeek s where 1=1 and s.schoolTerm.id='"
						+ termId + "'group by s.week");
		// 执行sb1得到需要的周次；
		List<SchoolWeek> weeks = schoolWeekDAO.executeQuery(sb1.toString());
		// 循环weeks；
		for (SchoolWeek schoolWeek : weeks) {
			// 将schoolWeek的week映射给week；
			weeksMap.put(schoolWeek.getWeek(), schoolWeek.getWeek());
		}
		return weeksMap;
	}

	/*
	 * 获取所有的星期； 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getWeekdays()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getWeekdays()
	 */
	@Override
	public Map getWeekdays() {
		// 新建一个HashMap对象；
		Map weekdayMap = new HashMap();
		// 循环7map星期几；
		for (int i = 1; i < 8; i++) {
			switch (i) {
			// 如果i等于1，则map1为星期一；
			case 1:
				weekdayMap.put(1, "星期一");
				break;
			// 如果i等于2，则map2为星期二；
			case 2:
				weekdayMap.put(2, "星期二");
				break;
			// 如果i等于3，则map3为星期二；
			case 3:
				weekdayMap.put(3, "星期三");
				break;
			// 如果i等于2，则map2为星期二；
			case 4:
				weekdayMap.put(4, "星期四");
				break;
			// 如果i等于5，则map5为星期二；
			case 5:
				weekdayMap.put(5, "星期五");
				break;
			// 如果i等于6，则map6为星期六；
			case 6:
				weekdayMap.put(6, "星期六");
				break;
			// 如果i等于7，则map7为星期天；
			case 7:
				weekdayMap.put(7, "星期日");
				break;
			}
		}
		return weekdayMap;
	}

	/*
	 * 获取所有的星期List； 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getWeekdaysList()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getWeekdaysList()
	 */
	@Override
	public List<SchoolTerm> getWeekdaysList() {
		// 新建一个SchoolTerm的list对象；
		List<SchoolTerm> terms = new ArrayList<SchoolTerm>();
		// 循环7map星期几；
		for (int i = 1; i < 8; i++) {
			// 新建一个SchoolTerm的对象；
			SchoolTerm schoolTerm = new SchoolTerm();
			
			schoolTerm.setTermCode(i);
			switch (i) {
			// 如果i等于1，则设置schoolTerm的termName为星期一；
			case 1:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期一");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			// 如果i等于2，则设置schoolTerm的termName为星期二；
			case 2:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期二");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			// 如果i等于3，则设置schoolTerm的termName为星期二；
			case 3:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期三");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			// 如果i等于4，则设置schoolTerm的termName为星期二；
			case 4:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期四");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			// 如果i等于5，则设置schoolTerm的termName为星期二；
			case 5:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期五");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			// 如果i等于6，则设置schoolTerm的termName为星期六；
			case 6:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期六");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			// 如果i等于7，则设置schoolTerm的termName为星期日；
			case 7:
				// 设置schoolTerm的termName;
				schoolTerm.setTermName("星期日");
				// 将schoolTerm加入到terms集合；
				terms.add(schoolTerm);
				break;
			}
		}
		return terms;
	}

	/*
	 * 获取所有的用户列表； 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getUsersMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getUsersMap()
	 */
	@Override
	public Map getUsersMap() {
		// 获取所有的用户；
		Set<User> users = userDAO.findUserByUserRole("1");
		// 新建一个HashMap对象；
		Map userMap = new HashMap();
		// 循环users；
		for (User user : users) {
			// 将user的Cname映射成id；
			userMap.put(user.getUsername(), user.getCname());
		}
		return userMap;
	}

	/*
	 * 获取所有用户的username 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getAllUsernameMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getAllUsernameMap()
	 */
	@Override
	public Map getAllUsernameMap() {
		// 获取所有的用户；
		Set<User> users = userDAO.findUserByUserRole("1");
		// 新建一个HashMap对象；
		Map userMap = new HashMap();
		// 循环users；
		for (User user : users) {
			// 将user的Cname映射成id；
			userMap.put(user.getUsername(), user.getCname());
		}
		return userMap;
	}

	/*
	 * 选择的周次的方式 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getWeekStatus()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getWeekStatus()
	 */
	@Override
	public Map getWeekStatus() {
		// 新建一个HashMap对象；
		Map weekMap = new HashMap();
		// 循环3次，map是否连续周、单周、双周
		for (int i = 1; i < 4; i++) {
			switch (i) {
			// 如果i等于1，则map1为连续周；
			case 1:
				weekMap.put(i, "连续周");
				break;
			// 如果i等于2，则map2为单周；；
			case 2:
				weekMap.put(i, "单周");
				break;
			// 如果i等于3，则map3为双周；
			case 3:
				weekMap.put(i, "双周");
				break;
			}
		}
		return weekMap;
	}

	/*
	 * 跳页 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getChoosedPage(int)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getChoosedPage(int)
	 */
	@Override
	public Map getChoosedPage(int page) {
		// 新建一个HashMap对象；
		Map weekMap = new HashMap();
		// 循环页数
		for (int i = 1; i < page + 1; i++) {
			// 映射1为1；
			weekMap.put(i, i);
		}
		return weekMap;
	}

	/*
	 * 教师需求状态Map 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getNeedStatusMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getNeedStatusMap()
	 */
	@Override
	public Map getNeedStatusMap() {
		// 新建一个HashMap对象；
		Map weekMap = new HashMap();
		// 循环2次，map是否通过或拒绝
		for (int i = 3; i < 5; i++) {
			switch (i) {
			// 如果i等于3，则map3为通过；
			case 3:
				weekMap.put(i, "通过");
				break;
			// 如果i等于4，则map4为拒绝；
			case 4:
				weekMap.put(i, "拒绝");
				break;
			}
		}
		return weekMap;
	}

	/*
	 * 获取可选的周次 作者：彭文玉 2013-10-21
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getChoosedWeeks(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getChoosedWeeks(java.lang.String)
	 */
	@Override
	public Map getChoosedWeeks(String week) {
		// 新建一个HashMap对象
		Map weekMap = new HashMap();
		// 以逗号分隔
		String[] strWeek = week.split(",");
		// 循环strWeek
		for (int i = 0; i < strWeek.length; i++) {
			// 获取strWeek的第i个
			String weekStr = strWeek[i];
			// 将weekStr从字符串转成int
			int weekId = Integer.parseInt(weekStr);
			// 将weekId映射成weekStr
			weekMap.put(weekId, weekStr);

		}
		return weekMap;
	}

	/*
	 * 获取可选的节次 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getChoosedClasses(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getChoosedClasses(java.lang.String)
	 */
	@Override
	public Map getChoosedClasses(String allClass) {
		// 新建一个HashMap对象
		Map allClassMap = new HashMap();
		// 以逗号分隔
		String[] strWeek = allClass.split(",");
		// 循环strWeek
		for (int i = 0; i < strWeek.length; i++) {
			// 获取strWeek的第i个
			String allClassStr = strWeek[i];
			// 将allClassStr从字符串转成int
			int allClassId = Integer.parseInt(allClassStr);
			// 将allClassId映射成allClassStr
			allClassMap.put(allClassId, allClassStr);

		}
		return allClassMap;
	}

	/*
	 * 得到所有的学期 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getTermsMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getTermsMap()
	 */
	@Override
	public Map getTermsMap() {
		// 获取所有的学期；
		Set<SchoolTerm> terms = schoolTermDAO.findAllSchoolTerms();
		// 新建一个Map集合；
		Map termsMap = new HashMap();
		// 循环map terms对象；
		for (SchoolTerm term : terms) {
			termsMap.put(term.getId(), term.getTermName());
		}
		return termsMap;
	}

	/*
	 * 获取所有的周次 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getWeekMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getWeekMap()
	 */
	@Override
	public Map getWeekMap() {
		// 新建一个week的map集合；
		Map weeksMap = new HashMap();
		for (int i = 1; i < 26; i++) {
			// 声明并赋值weekName；
			String weekName = "第" + String.valueOf(i) + "周";
			weeksMap.put(i, weekName);
		}
		return weeksMap;
	}

	/*
	 * 获取需要添加排课调整时间的学期Map 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getTermsByAdjust(java.util.List)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getTermsByAdjust(java.util.List)
	 */
	@Override
	public Map getTermsByAdjust(List<SchoolTerm> terms) {
		// 新建一个terms的map对象
		Map termsMap = new HashMap();
		// 循环terms
		for (SchoolTerm schoolTerm : terms) {
			// 将term的名称映射成id
			termsMap.put(schoolTerm.getId(), schoolTerm.getTermName());
		}
		return termsMap;
	}

	/*
	 * 获取Map的term 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getTermsByTerm(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getTermsByTerm(java.lang.String)
	 */
	@Override
	public List<SchoolTerm> getTermsByTerm(String term) {
		// 新建一个terms的List对象
		List<SchoolTerm> terms = new ArrayList<SchoolTerm>();
		// 以逗号分隔term
		String[] termString = term.split(",");
		// 循环termString
		for (int i = 0; i < termString.length; i++) {
			// 获取termString的第i个
			String termStr = termString[i];
			// 将termStr转成数字类型
			int termId = Integer.parseInt(termStr);
			// 根据id查找termId的schoolTerm
			SchoolTerm schoolTerm = schoolTermDAO.findSchoolTermById(termId);
			// 将schoolTerm添加到terms
			terms.add(schoolTerm);
		}
		return terms;
	}

	/*
	 * 根据周次获取term 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getTermsByWeek(int, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getTermsByWeek(int, java.lang.String)
	 */
	@Override
	public List<SchoolTerm> getTermsByWeek(int termId, String week) {
		// 新建一个terms的List对象
		List<SchoolTerm> terms = new ArrayList<SchoolTerm>();
		// 根据id查找termId的schoolTerm
		SchoolTerm schoolTerm = schoolTermDAO.findSchoolTermById(termId);
		// 以逗号分隔week
		String[] weekString = week.split(",");
		// 循环termString
		for (int i = 0; i < weekString.length; i++) {
			// 新建一个schoolTerm的对象
			SchoolTerm term = new SchoolTerm();
			// 获取weekString的第i个
			String weekStr = weekString[i];
			// 将schoolTerm的termName和weekStr进行拼接
			String termName = schoolTerm.getTermName() + "第" + weekStr + "周";
			// 设置term的termName
			term.setTermName(termName);
			// 将schoolTerm添加到terms
			terms.add(term);
		}
		return terms;
	}

	/*
	 * 获取所有的周次 作者：彭文玉
	 */

	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getAllWeek()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getAllWeek()
	 */
	@Override
	public Set<SchoolWeek> getAllWeek() {
		// 新建一个week的Set集合；
		Set<SchoolWeek> weeksSet = new HashSet<SchoolWeek>();
		for (int i = 1; i < 26; i++) {
			// 新建一个schoolWeek的set集合
			SchoolWeek schoolWeek = new SchoolWeek();
			// 设置schoolWeek的id
			schoolWeek.setId(i);
			weeksSet.add(schoolWeek);
		}
		return weeksSet;
	}

	/*
	 * 获取选中的周次 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getChoosedWeeks(java.util.Set)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getChoosedWeeks(java.util.Set)
	 */
	@Override
	public Map getChoosedWeeks(Set<SchoolWeek> sets) {
		// 新建一个week的map集合；
		Map weeksMap = new HashMap();
		for (SchoolWeek schoolWeek : sets) {
			// 声明并赋值weekName；
			String weekName = "第" + String.valueOf(schoolWeek.getId()) + "周";
			weeksMap.put(schoolWeek.getId(), weekName);
		}
		return weeksMap;
	}

	/*
	 * 获取被选中的周次 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getChooseWeek(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getChooseWeek(java.lang.String)
	 */
	@Override
	public Set<SchoolWeek> getChooseWeek(String weekStr) {
		// 以逗号分隔labStr
		String[] weekString = weekStr.split(",");
		// 新建一个week的Set集合；
		Set<SchoolWeek> weeksSet = new HashSet<SchoolWeek>();
		// 循环weekString
		for (int i = 0; i < weekString.length; i++) {
			// 获取weekString的第i个
			weekStr = weekString[i];
			// 将weekStr转成int型
			int weekId = Integer.parseInt(weekStr);
			// 新建一个schoolWeek的set集合
			SchoolWeek schoolWeek = new SchoolWeek();
			// 设置schoolWeek的id
			schoolWeek.setId(weekId);
			weeksSet.add(schoolWeek);
		}
		return weeksSet;
	}

	
	/*
	 * 导出excel 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#exportExcel(java.util.List, java.lang.String, java.lang.String[], java.lang.String[], javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#exportExcel(java.util.List, java.lang.String, java.lang.String[], java.lang.String[], javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void exportExcel(List<Map> list, String title, String[] hearders,
			String[] fields, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 封装表格数据
		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(hearders), fields);
		// 导出工具类
		JsGridReportBase report = new JsGridReportBase(request, response);
		// 将TableData数据对象导出到excel
		report.exportToExcel(title, this.getUser().getCname(), td);
	}
	
	/**
	 * @内容：导出Excel(复杂表头)
	 * @作者： 何立友
	 * @日期：2014-09-14
	 */
	@Override
	public void exportExcel(List<Map> list, String title, String[] parentHeaders, String[][] childrenHeaders,
			String[] fields, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 封装表格数据
		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(parentHeaders, childrenHeaders), fields);
		// 导出工具类
		JsGridReportBase report = new JsGridReportBase(request, response);
		// 将TableData数据对象导出到excel
		report.exportToExcel(title, this.getUser().getCname(), td);
	}

	
	/*
	 * 得到本周的周次 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#findNewWeek()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#findNewWeek()
	 */
	@Override
	public int findNewWeek() {
		int week;
		// 声明变量now并给它赋值成当前时间；
		Calendar now = Calendar.getInstance();
		// 查找今天所属的周次
		Set<SchoolWeek> schoolWeeks = schoolWeekDAO.findSchoolWeekByDate(now);
		// 新建SchoolWeek的list集合
		List<SchoolWeek> schoolWeeks1 = new ArrayList<SchoolWeek>();
		// 将schoolWeeks添加到schoolWeeks1
		schoolWeeks1.addAll(schoolWeeks);
		// 如果schoolWeeks大于0；
		if (schoolWeeks1.size() > 0) {
			// 获取schoolWeek的第一个
			SchoolWeek schoolWeek = schoolWeeks1.get(0);
			week = schoolWeek.getWeek();
		}// 否则为0
		else {
			week = 0;
		}
		return week;
	}

	/*
	 * 需要排课的学期 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getTermsByNow()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getTermsByNow()
	 */
	@Override
	public Map getTermsByNow() {
		// 创建一条按创建时间倒序排序的语句；
		StringBuffer sb = new StringBuffer(
				"select s from SchoolTerm s where 1=1 and (now()< s.termEnd or now() between s.termStart and s.termEnd) order by s.termEnd");
		// 给语句添加分页机制；
		List<SchoolTerm> terms = schoolTermDAO.executeQuery(sb.toString());
		// 新建一个termMaps
		Map termsMap = new HashMap();
		// 循环terms
		for (SchoolTerm schoolTerm : terms) {
			// mapschoolTerm的id映射成schoolTerm的Name
			termsMap.put(schoolTerm.getId(), schoolTerm.getTermName());
		}
		return termsMap;
	}

	/*
	 * 获取到目前为止的学期 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getCurrentTerms()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getCurrentTerms()
	 */
	@Override
	public List<SchoolTerm> getCurrentTerms() {
		// 创建一条按创建时间倒序排序的语句；
		StringBuffer sb = new StringBuffer(
				"select s from SchoolTerm s where now()> s.termEnd or now() between s.termStart and s.termEnd order by s.termEnd desc");
		// 给语句添加分页机制；
		List<SchoolTerm> terms = schoolTermDAO.executeQuery(sb.toString());
		/*
		 * System.out.println(terms); //新建一个termMaps Map termsMap=new HashMap();
		 * //循环terms for(SchoolTerm schoolTerm:terms){
		 * //mapschoolTerm的id映射成schoolTerm的Name termsMap.put(schoolTerm.getId(),
		 * schoolTerm.getTermName()); } return termsMap;
		 */
		return terms;
	}

	/*
	 * 打印功能； 作者：彭文玉
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#print()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#print()
	 */
	@Override
	public void print() {
		File file = new File("d://xh.txt");
		// 构建打印请求属性集
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		// 设置打印格式
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		// 查找所有的可用的打印服务
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		// 定义默认的打印服务(PrintService)
		PrintService defaultService = PrintServiceLookup
				.lookupDefaultPrintService();
		// 显示打印对话框
		PrintService service = ServiceUI.printDialog(null, 200, 200,
				printService, defaultService, flavor, pras);
		if (service != null) {
			try {
				// 创建打印作业
				DocPrintJob job = service.createPrintJob();
				// 构建文件打印流
				FileInputStream fis = new FileInputStream(file);
				// 构建文件属性
				DocAttributeSet das = new HashDocAttributeSet();

				Doc doc = new SimpleDoc(fis, flavor, das);

				job.print(doc, pras);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (PrintException e) {
				e.printStackTrace();
			}// try结束
		}// if结束
	}// 类结束

	/*
	 * 密码加密md5； 鲁静 2013.08.26
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#createMD5(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#createMD5(java.lang.String)
	 */
	@Override
	public String createMD5(String s) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = s.getBytes("ISO-8859-1");

			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 查找所有的学院信息 鲁静 2013.08.26
	 */
	// public Map getAllAcademy() {
	// //查找到所有的学院信息，存储在set集合中
	// Set<Academy> academys=academyDAO.findAllAcademys();
	// //new一个map集合
	// Map academyMap=new HashMap();
	// //循环academys
	// for (Academy academy : academys) {
	// //将学院以id,name的形式存储在map中展示
	// academyMap.put(academy.getId(),academy.getAcademyName());
	// }
	// return academyMap;
	// }
	/*
	 * 查找所有的班级信息 鲁静 2013.08.26
	 */
	// public Map getAllClasses() {
	// //查找到所有的班级信息，存储在set集合中
	// Set<SysClasses> sysClasses=sysClassesDAO.findAllSysClassess();
	// //new一个map集合
	// Map sysClassesMap=new HashMap();
	// //循环sysClasses
	// for (SysClasses sysClass : sysClasses) {
	// //将班级以id,name的形式存储在map中进行展示
	// sysClassesMap.put(sysClass.getId(), sysClass.getClassName());
	// }
	// return sysClassesMap;
	// }
	/*
	 * 查找所有的部门信息 鲁静 2013.08.26
	 */
	// public Map getAllDepartment() {
	// //查找到所有的部门信息，存储在set集合中
	// Set<Department> departments=departmentDAO.findAllDepartments();
	// //new一个map集合
	// Map departmentMap=new HashMap();
	// //循环departments
	// for (Department department : departments) {
	// //将部门以id，name的形式存储在map中进行展示
	// departmentMap.put(department.getId(), department.getDepartmentName());
	// }
	// return departmentMap;
	// }
	/*
	 * 查找所有的专业信息 鲁静 2013.08.26
	 */
	// public Map getAllMajor() {
	// //查找到所有的专业信息，存储在set集合中
	// Set<Major> majors=majorDAO.findAllMajors();
	// //new一个map集合
	// Map majorMap=new HashMap();
	// //循环majors
	// for (Major major : majors) {
	// //将专业以id,name的形式存储在map中进行展示
	// majorMap.put(major.getId(), major.getMajorName());
	// }
	// return majorMap;
	// }
	/*
	 * 查找所有的职称 鲁静 2013.08.27
	 */
	// public Map getAllPosition(){
	// //查找到所有的专业信息，存储在set集合中
	// Set<CPosition> cPositions=cPositionDAO.findAllCPositions();
	// //new一个map集合
	// Map cPositionMap=new HashMap();
	// //循环cPositions
	// for (CPosition cPosition : cPositions) {
	// //将职称以id,name的形式存储在map中进行展示
	// cPositionMap.put(cPosition.getId(), cPosition.getName());
	// }
	// return cPositionMap;
	//
	// }

	/*
	 * 查找id等于idKey的权限；鲁静2013.08.27
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#findAutnorityById(int)
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#findAutnorityById(int)
	 */
	@Override
	public Authority findAutnorityById(int idKey) {
		return authorityDAO.findAuthorityById(idKey);
	}

	/*
	 * 获取当前日期 鲁静 2013.09.04
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getDate()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getDate()
	 */
	@Override
	public String getDate() {
		String m, d, getDate;
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		if (month < 10) {
			m = "0" + String.valueOf(month);
		} else {
			m = String.valueOf(month);
		}
		int day = date.get(Calendar.DATE);
		if (day < 10) {
			d = "0" + String.valueOf(day);
		} else {
			d = String.valueOf(day);
		}
		getDate = String.valueOf(year) + "-" + m + "-" + d;

		return getDate;
	}

	/*
	 * 用户列表 鲁静 2013.09.04
	 */
	// public Map getUsersMap()
	// {
	// Set<User> users = userDAO.findUserByEnabled(true);
	// Map usersMap = new HashMap();
	// for(User user:users)
	// usersMap.put(user.getId(), user.getCname());
	// return usersMap;
	// }
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getAppStatusMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getAppStatusMap()
	 */
	@Override
	public Map getAppStatusMap() {
		Map referenceData = new HashMap();
		for (int i = 0; i < 2; i++) {
			if (i == 1) {
				referenceData.put("2", "同意");
			} else {
				referenceData.put("3", "拒绝");
			}
		}
		return referenceData;
	}

	/*
	 * 维修结果 鲁静 2013.10.09
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getEquipStatusMap()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getEquipStatusMap()
	 */
	@Override
	public Map getEquipStatusMap() {
		Map referenceData = new HashMap();
		for (int i = 0; i < 5; i++) {
			if (i == 4) {
				referenceData.put("1", "恢复正常");
			} else {
				referenceData.put("4", "深度维修");
			}
		}
		return referenceData;
	}

	@Override
	public Map getAllAcademy() {
		Set<SchoolAcademy> academys = schoolAcademyDAO.findAllSchoolAcademys();
		Map academyMap = new HashMap();
		for (SchoolAcademy schoolAcademy : academys) {
			academyMap.put(schoolAcademy.getAcademyNumber(), schoolAcademy.getAcademyName());
		}
		return academyMap;
	}

	@Override
	public Map getAllClasses() {
		Set<SchoolClasses> classes = schoolClassesDAO.findAllSchoolClassess();
		Map classesMap = new HashMap();
		for (SchoolClasses schoolClasses : classes) {
			classesMap.put(schoolClasses.getId(), schoolClasses.getClassName());
		}
		return classesMap;
	}

	@Override
	public Map getAllDepartment() {
		// 获取所有的实验中心；
		Set<SchoolAcademy> schoolAcademys = schoolAcademyDAO
				.findAllSchoolAcademys();
		// 创建一个HashMap对象；
		Map map = new HashMap();
		// 循环center；
		for (SchoolAcademy schoolAcademy : schoolAcademys) {
			map.put(schoolAcademy.getAcademyName(),
					schoolAcademy.getAcademyName());
		}
		return map;
	}

	/*
	 * 自动增加的编号 鲁静 2013.09.23
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#number()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#number()
	 */
	@Override
	public ArrayList number() {
		ArrayList numbers = new ArrayList();
		for (int i = 0; i < 10000; i++) {
			numbers.add(i + 1);
		}
		return numbers;
	}

	/*
	 * 获取时间 鲁静
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getDate2()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getDate2()
	 */
	@Override
	public String getDate2() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		// 生成1到100之间的随机整数
		int i = 1 + (int) (Math.random() * 100);
		// 转换i成字符串格式然后再和date拼接赋值给变量randomString；
		String randomString = date + String.valueOf(i);
		return randomString;
	}
	
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getUserDetail()
	 */
	public User getUserDetail() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDAO.findUserByUsername(userDetails.getUsername());
		
	}
	/***********************************************************************************************
	 * 获取上传文件的保存路径
	 * 作者：李小龙 
	 * 日期：2014-07-27
	 ***********************************************************************************************/
	@Override
	public String getUpdateFilePath(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    /**日期格式**/    
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    /** 构建文件保存的目录* */
	    String PathDir = "/upload/"+ dateformat.format(new Date());
	    /** 得到文件保存目录的真实路径* */
	    String RealPathDir = request.getSession().getServletContext().getRealPath(PathDir);
	    //System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
	    /** 根据真实路径创建目录* */
	    File SaveFile = new File(RealPathDir);
	    if (!SaveFile.exists()){
	    	SaveFile.mkdirs();
	    }
	    /** 页面控件的文件流* */
	    System.out.println("准备获取文件---");
	    MultipartFile multipartFile = multipartRequest.getFile("file");
	    /** 获取文件的后缀* */
	    System.out.println("上传的文件名称"+multipartFile.getOriginalFilename());
	    /**判断文件不为空*/
	    if(!multipartFile.isEmpty()){
	    	String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		    /** 使用UUID生成文件名称* */
	    	String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
		    /** 拼成完整的文件保存路径加文件* */
		    String fileName = RealPathDir + File.separator + logImageName;
		    File file = new File(fileName);
		    try {
		        multipartFile.transferTo(file);
		    } catch (IllegalStateException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    /** 上传到服务器的文件的绝对路径* */	       
		    String saveUrl=PathDir+"/"+logImageName;
		    return saveUrl;
	    }
	    return "";
	}
	/***********************************************************************************************
	 * 查询和当前登录人同一学院的用户
	 * 作者：李小龙 
	 ***********************************************************************************************/
	@Override
	public List<User> findTheSameCollegeUser(String academyNumber) {
		// TODO Auto-generated method stub
		String sql="select u from User u where u.schoolAcademy.academyNumber like'"+academyNumber+"%'";
		return userDAO.executeQuery(sql,0,-1);
	}
	/***********************************************************************************************
	 * 判断时间所属的学期
	 * 作者：李小龙 
	 ***********************************************************************************************/
	@Override
	public SchoolTerm getBelongsSchoolTerm(Calendar time) {
		// TODO Auto-generated method stub
		//遍历所有的学期
		Set<SchoolTerm> terms=schoolTermDAO.findAllSchoolTerms();
		SchoolTerm term=new SchoolTerm();
		for (SchoolTerm schoolTerm : terms) {
			// 获得学期开始时间
			Calendar start=schoolTerm.getTermStart();
			// 获取学期结束时间
			Calendar end=schoolTerm.getTermEnd();
			
			if(time.after(start)&&time.before(end)){//当前时间正好属于某一个学期
				term=schoolTerm;
				break;
			}else if(time.before(start)){//寒暑假，没有已经建立新学期时，按新学期
				term=schoolTerm;
				break;
			}
			else if(time.after(end)){//寒暑假，没有建新学期时，按新学期
				term=schoolTerm;
			}
		}
		return term;
	}
	/***********************************************************************************************
	 * 获取指定学期和学院的可用课程
	 * 作者：魏晨 
	 ***********************************************************************************************/
	@Override
	public List<SchoolCourse> getSchoolCourseList(int termId) {
		String sql = "select c from SchoolCourse c where c.schoolTerm.id =" + termId + " and schoolAcademy.academyNumber like '" + this.getUserDetail().getSchoolAcademy().getAcademyNumber() + "' group by c.courseNo";
		List <SchoolCourse> schoolCourses = schoolCourseDAO.executeQuery(sql, 0,-1);
		return schoolCourses;
	}

	/***********************************************************************************************
	* 查询和当前登录人同一学院的老师
	* 作者：李小龙 
	***********************************************************************************************/
	@Override
	public List<User> findTheSameCollegeTeacher(String academyNumber) {
		// TODO Auto-generated method stub
		String sql="select u from User u where u.schoolAcademy.academyNumber='"+academyNumber+"' and u.userRole=1";
		return userDAO.executeQuery(sql,0,-1);
	}
	/***********************************************************************************************
	 * 根据权限id查询用户
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<User> findUsersByAuthorityId(int i) {
		// TODO Auto-generated method stub
		String sql="select u from User u join u.authorities a where a.id= "+i;
		return userDAO.executeQuery(sql,0,-1);
	}
	/***********************************************************************************************
	 * 查询所有的学期并倒序排列
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<SchoolTerm> findAllSchoolTerms() {
		// TODO Auto-generated method stub
		String sql="select s from SchoolTerm s where 1=1 order by s.id desc";
		return schoolTermDAO.executeQuery(sql, 0, -1);
	}
	/***********************************************************************************************
	 * 查询所有的十二个学院
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<SchoolAcademy> findAllSchoolAcademys() {
		// TODO Auto-generated method stub
		String sql="select s from SchoolAcademy s where 1=1";//where s.academyNumber like '02__'  2015.10.02贺子龙
		List<SchoolAcademy> list=new ArrayList<SchoolAcademy>();
		List<SchoolAcademy> s=schoolAcademyDAO.executeQuery(sql, 0,-1);
		for (SchoolAcademy a:s) {
			if(list.size()>100){//11
				break;
			}
			list.add(a);
		}
		return list;
	}
	/***********************************************************************************************
	 * 根据实验室判断实验室所属一期还是二期
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public int getLabRoomBelongsTime(LabRoom labRoom) {
		// TODO Auto-generated method stub
		SchoolAcademy a=labRoom.getLabCenter().getSchoolAcademy();
		int time=2;
		//属于一期的学院
		String sql="select s from SchoolAcademy s where s.academyNumber in('0201','0204','0206')";
		List<SchoolAcademy> Academys=schoolAcademyDAO.executeQuery(sql, 0,-1);
		for (SchoolAcademy sa : Academys) {
			if(sa.getAcademyNumber()==a.getAcademyNumber()){
				time=1;
			}
		}
		return time;
	}
	/***********************************************************************************************
	 * 查询出实验室不为空的楼栋
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<SystemBuild> findNotEmptyBuild() {
		// TODO Auto-generated method stub
		String sql="select b from SystemBuild b where b.buildNumber in(select distinct m.systemRoom.systemBuild.buildNumber from LabRoom m)";
		return systemBuildDAO.executeQuery(sql, 0,-1);
	}

	/***********************************************************************************************
	 * 根据排课记录登录用户判断是否用户仅为老师权限，且是否为当前用户，或者为超级管理员或实验中心主任
	 * 作者：魏诚
	 ***********************************************************************************************/
	@Override
	public boolean isOrNotAuthority(TimetableAppointment timetableAppointment) {
		/**
		 * 判断是否只是老师角色
	    **/		
		User user = this.getUserDetail();
		String judge = ",";
		for(Authority authority:user.getAuthorities()){
			judge = judge + "," + authority.getId() + "," ;
		}
		//如果权限仅为教师，不为实验中心主任或超级管理员，则增加过滤
		if(judge.indexOf(",11,")>-1||judge.indexOf(",4,")>-1||judge.indexOf(",7,")>-1){
			return true;
		}else if(judge.indexOf(",2,")!=-1){
			/*for(TimetableTeacherRelated timetableTeacherRelated:timetableAppointment.getTimetableTeacherRelateds()){
				if(user.getUsername().equals(timetableTeacherRelated.getUser().getUsername())){
					return true;
				}
			}*/
			if(timetableAppointment.getTimetableStyle()==5||timetableAppointment.getTimetableStyle()==6){
				if(user.getUsername().equals(timetableAppointment.getTimetableSelfCourse().getUser().getUsername())){
					return true;
				}
			}else if(timetableAppointment.getTimetableStyle()==1||timetableAppointment.getTimetableStyle()==2||timetableAppointment.getTimetableStyle()==3||timetableAppointment.getTimetableStyle()==4){
				if(user.getUsername().equals(timetableAppointment.getSchoolCourse().getUserByTeacher().getUsername())){
					return true;
				}
			}
		}
		return false;
	}
	/***********************************************************************************************
	 * 获取所有的部门
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public Set<SchoolAcademy> getAllSchoolAcademy() {
		
		return schoolAcademyDAO.findAllSchoolAcademys();
	}
	/***********************************************************************************************
	 * 构建文件夹
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public File createDirdown() {
		//上传之后注意修改服务器文件夹权限
		String root = System.getProperty("xidlims.root");
		//操作手册的保存路径
		//File.separator windows是\，unix是/
		String url=root+"pages"+File.separator+"instructions";
		File sendPath = new File(url);
		//构建文件夹
		if(!sendPath.exists()){
			sendPath.mkdirs();
		}
		return sendPath;
	}
	/***********************************************************************************************
	 * 下载文件
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public void downloadFile(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		try{			
				File sendPath = createDirdown();
				FileInputStream fis = new FileInputStream(sendPath+File.separator+fileName);
				response.setCharacterEncoding("utf-8");
				//解决上传中文文件时不能下载的问题
				response.setContentType("multipart/form-data;charset=UTF-8");
				if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
					fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");// firefox浏览器
				} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
					fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
				} else {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
				response.setHeader("Content-Disposition", "attachment;fileName="+fileName.replaceAll(" ", ""));
				
				OutputStream fos = response.getOutputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = fis.read(buffer))>0){
					fos.write(buffer,0,count);   
				}
				fis.close();
				fos.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					response.getOutputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	}
	/***********************************************************************************************
	 * 查询排课所属学期的周次数量
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public int getTermNumber(TimetableAppointment t) {
		int weeks=0;
		if(t!=null){
			int style=t.getTimetableStyle();
			if(style==5||style==6){
				weeks=t.getTimetableSelfCourse().getSchoolTerm().getSchoolWeeks().size()/7;
			}else{
				weeks=t.getSchoolCourse().getSchoolTerm().getSchoolWeeks().size()/7;
			}
		}
		return weeks;
	}
	/***********************************************************************************************
	 * 判断当前时间是否在指定时间（周次、星期）之后
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public boolean getTimeMark(Integer week, Integer weekday) {
		boolean flag=false;
		Calendar now=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(now.getTime());
		
		String sql="select w from SchoolWeek w where w.date like '"+time+"' ";
		List<SchoolWeek> weeks=schoolWeekDAO.executeQuery(sql, 0,-1);
		if(weeks.size()>0){
			
			int currentWeek=weeks.get(0).getWeek();
			int currentWeekDay=weeks.get(0).getWeekday();
			if(currentWeek>week){
				flag=true;
			}
			if(currentWeek==week){
				if(currentWeekDay>=weekday){
					flag=true;
				}
			}
		}
		return flag;
	}
	/***********************************************************************************************
	 * 根据对象生成二维码，并返回二维码保存路径
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public String getDimensionalCode(LabRoomDevice d) throws Exception {
		
		String url="";
		//获取系统路径
		String root = System.getProperty("xidlims.root");
		//二维码的保存路径
		//File.separator windows是\，unix是/
		String path="upload"+"/"+"dimensionalCode";
		String text =showDeviceURL+d.getId();
		int width = 300;
		int height = 300;
		//二维码的图片格式
		String format = "gif";
		Hashtable hints = new Hashtable();
		//内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
		File sendPath = new File(root+path);
		//构建文件夹
		if(!sendPath.exists()){
			sendPath.mkdirs();
		}
		//生成二维码
		File outputFile = new File(root+path+File.separator+d.getSchoolDevice().getDeviceNumber()+".gif");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		url=path+"/"+d.getSchoolDevice().getDeviceNumber()+".gif";
		
System.out.println("二维码路径："+url);
		return url;
	}
	/***********************************************************************************************
	 * 获取已经排过课的课程
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public Set<SchoolCourse> findSchoolCourse() {
		Set<SchoolCourse> courses=new HashSet<SchoolCourse>();
		String sql="select t from TimetableAppointment t where t.status=1";
		List<TimetableAppointment> timetableList=timetableAppointmentDAO.executeQuery(sql, 0,-1);
		for (TimetableAppointment t : timetableList) {
			if (t.getSchoolCourse()!=null) {
				courses.add(t.getSchoolCourse());
			}
			
		}
		return courses;
	}
	/***********************************************************************************************
	 * 获取当前时间所属的周次星期信息
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public int getBelongsSchoolWeek(Calendar time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(time.getTime());
		String sql="select w from SchoolWeek w where w.date='"+date+"' ";
		List<SchoolWeek> weeks=schoolWeekDAO.executeQuery(sql, 0,-1);
		if(weeks.size()>0){
			return weeks.get(0).getWeek();
		}else{//如果是暑假和寒假期间则显示第一周的数据
			return 1;
		}
		
	}
	/***********************************************************************************************
	 * 根据课程获取教师
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public Set<User> findSchoolCourseTeachers(Set<SchoolCourse> schoolCourses) {
		Set<User> users=new HashSet<User>();
		for (SchoolCourse course : schoolCourses) {
			if(course!=null&&course.getUserByTeacher()!=null){
				users.add(course.getUserByTeacher());
			}
		}
		return users;
	}
	/***********************************************************************************************
	 * 根据选课组编号获取学生名单
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<SchoolCourseStudent> findStudentByCourseNo(String courseNo) {
		SchoolCourse schoolCourse=schoolCourseDAO.findSchoolCourseByPrimaryKey(courseNo);
		Set<SchoolCourseDetail> courseDetails=schoolCourse.getSchoolCourseDetails();
		String detailNo="";
		if(courseDetails.size()>0){
			SchoolCourseDetail detail=courseDetails.iterator().next();
			detailNo=detail.getCourseDetailNo();
		}
		if(detailNo!=null&&!detailNo.equals("")){
			String sql="select  s from SchoolCourseStudent s where s.schoolCourseDetail.courseDetailNo='"+detailNo+"' ";
			return schoolCourseStudentDAO.executeQuery(sql, 0,-1);
		}else{
			return null;
		}
		
	}
	/***********************************************************************************************
	 * 根据学生名单和周次统计学生实到次数
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<People> findPeopleByStudentsAndWeek(List<SchoolCourseStudent> studentList, Integer week,String courseNo) {
		List<People> people=new ArrayList<People>();
		for (SchoolCourseStudent student : studentList) {
			String username=student.getUserByStudentNumber().getUsername();
			String cname=student.getUserByStudentNumber().getCname();
			
			//统计实到人数
			String peopleSql="select t from TimetableAttendance t where (t.attendanceMachine=1 or t.actualAttendance=1)";
			if(!week.equals(0)){//不等于0即查询的周次不是全部
				peopleSql+=" and t.week="+week;
			}
			peopleSql+=" and t.courseNo='"+courseNo+"' and t.userByUserNumber.username='"+username+"' ";
//	System.out.println("-------peopleSql-------------"+peopleSql);
			List<TimetableAttendance> atds=timetableAttendanceDAO.executeQuery(peopleSql, 0,-1);
			int time=atds.size();//实到人数
			if(time!=0){
				People p=new People();
				p.setUsername(username);
				p.setCname(cname);
				p.setTime(time);
				
				people.add(p);
			}
			
			
			
		}
		return people;
	}
	/***********************************************************************************************
	 * 根据选课组编号和周次统计缺勤次数
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public List<People> findAbsenceByStudentsAndWeek(Integer week,String courseNo) {
		List<People> people=new ArrayList<People>();
		String peopleSql="select t from TimetableAttendance t where t.attendanceMachine=0 and t.actualAttendance=0";
		if(!week.equals(0)){//不等于0即查询的周次不是全部
			peopleSql+=" and t.week="+week;
		}
		peopleSql+=" and t.courseNo='"+courseNo+"' ";
		List<TimetableAttendance> atds=timetableAttendanceDAO.executeQuery(peopleSql, 0,-1);
		Set<User> students=new HashSet<User>();
		for (TimetableAttendance t : atds) {
			students.add(t.getUserByUserNumber());
		}
		for (User user : students) {
			String sql="select t from TimetableAttendance t where t.attendanceMachine=0 and t.actualAttendance=0";
			if(!week.equals(0)){//不等于0即查询的周次不是全部
				sql+=" and t.week="+week;
			}
			sql+=" and t.courseNo='"+courseNo+"' and t.userByUserNumber.username='"+user.getUsername()+"' ";
			List<TimetableAttendance> atts=timetableAttendanceDAO.executeQuery(sql, 0,-1);
			int time=atts.size();//缺勤次数
			if(time!=0){
				People p=new People();
				p.setUsername(user.getUsername());
				p.setCname(user.getCname());
				p.setTime(time);
				
				people.add(p);
			}
		}
		return people;
	}

	/**
	 * 判断指定用户是否具有指定权限
	 * @param username 用户工号
	 * @param authStr 权限标识
	 * @return boolean
	 * @author hely
	 * 2015.08.25
	 */
	@Override
	public boolean checkAuthority(String username, String authStr) {
		User user = userDAO.findUserByPrimaryKey(username);
		if(user != null && !"".equals(authStr))
		{
			for(Authority a : user.getAuthorities())
			{
				if(authStr.equals(a.getAuthorityName()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/***********************************************************************************************
	 * 获取所有教师数据
	 * 作者：贺子龙
	 ***********************************************************************************************/
	@Override
	public List<User> findAllTeacheres(){
		String s = "select u from User u where u.userRole=1";
		List<User> users = userDAO.executeQuery(s, 0, -1);
		return users;
	}
	
	/***********************************************************************************************
	 * @功能：获取ip地址
	 * @作者：魏诚
	 ***********************************************************************************************/
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/***********************************************************************************************
	 * @功能：根据学号判断是否为本科生
	 * @作者：贺子龙
	 ***********************************************************************************************/
	public boolean isUndergraduate(String username){
		boolean isUndergraduate=true;
		if (!username.equals("")&&username.length()>3) {
			int grade=Integer.parseInt(username.substring(0, 2));//用户年级
			String flag=username.substring(2, 3);//学号第三位为标志位
			if (grade>=11) {//11级以后的学生
				if (flag.equals("0")) {
					isUndergraduate=false;
				}
			}else {//11级以前的学生
				if (!flag.equals("B")) {
					isUndergraduate=false;
				}
			}
			
		}
		
		return isUndergraduate;
	}
	/***********************************************************************************************
	 * @throws ParseException 
	 * @功能：根据年、月数字获取一个时间段
	 * @作者：贺子龙
	 ***********************************************************************************************/
	public Calendar[] getTimePeriod(int year, int month) throws ParseException{
		Calendar[] timePeriod={Calendar.getInstance(),Calendar.getInstance()};
		int day=31;
		if (month==2) {
			if(year%4==0&&year%100!=0||year%400==0) {//是闰年
				day=29;
			}else {
				day=28;
			}
		}else {
			if (month==4||month==6||month==9||month==11) {
				day=30;
			}
		}
		String str1=year+"-"+month+"-"+"01";
		String str2=year+"-"+month+"-"+day;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date1 =sdf.parse(str1);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Date date2 =sdf.parse(str2);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		timePeriod[0]=calendar1;
		timePeriod[1]=calendar2;
//		System.out.println(getBelongsSchoolTerm(timePeriod[0]).getId()+"[[");
		return timePeriod;

	}
	/***********************************************************************************************
	 * 判断月份所属的学期
	 * 作者：贺子龙
	 ***********************************************************************************************/
	public SchoolTerm getSchoolTermByMonth(int year, int month) throws ParseException{
		SchoolTerm term=getBelongsSchoolTerm(Calendar.getInstance());//默认取当前学期
		int day=31;
		if (month==2) {
			if(year%4==0&&year%100!=0||year%400==0) {//是闰年
				day=29;
			}else {
				day=28;
			}
		}else {
			if (month==4||month==6||month==9||month==11) {
				day=30;
			}
		}
		for (int i = 1; i <= day; i++) {
			
			String str=year+"-"+month+"-"+i;
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date =sdf.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			//遍历所有的学期
			Set<SchoolTerm> terms=schoolTermDAO.findAllSchoolTerms();
			int count=0;
			for (SchoolTerm schoolTerm : terms) {
				Calendar start=schoolTerm.getTermStart();
				Calendar end=schoolTerm.getTermEnd();
				if(calendar.after(start)&&calendar.before(end)){//输入月份的任意一天落在一个学期，那这个月就属于它所落入的学期。
					count++;
					term=schoolTerm;break;//减少循环次数
				}
			}
			if (count!=0) {
				break;//减少循环次数
			}
		}
//		System.out.println(term.getId()+"----");
		return term;
	}
	/***********************************************************************************************
	 * 判断该月份的起始和终止周次
	 * 作者：贺子龙
	 ***********************************************************************************************/
	public int[] getSchoolWeekByMonth(int year, int month) throws ParseException{
		int[] weekId={0,0};
		if (year!=0&&month!=0) {
			int day=31;
			if (month==2) {
				if(year%4==0&&year%100!=0||year%400==0) {//是闰年
					day=29;
				}else {
					day=28;
				}
			}else {
				if (month==4||month==6||month==9||month==11) {
					day=30;
				}
			}
			String str1=year+"-"+month+"-"+"01";
			String str2=year+"-"+month+"-"+day;
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date1 =sdf.parse(str1);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			SchoolWeek startWeek=getSchoolWeekByDate(calendar1);
			if (startWeek!=null&&startWeek.getWeek()!=0) {
				weekId[0]=startWeek.getWeek();
			}
			Date date2 =sdf.parse(str2);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date2);
			SchoolWeek endWeek=getSchoolWeekByDate(calendar2);
			if (endWeek!=null&&endWeek.getWeek()!=0) {
				weekId[1]=endWeek.getWeek();
			}
		}
		return weekId;
	}
	/***********************************************************************************************
	 * 判断该月份的起始和终止周次
	 * 作者：贺子龙
	 ***********************************************************************************************/
	public SchoolWeek getSchoolWeekByDate(Calendar time) throws ParseException{
		String sql="select w from SchoolWeek w where 1=1";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(time.getTime());
		
		sql+=" and w.date between '"+dateStr +"' and '"+dateStr+"' "; 
		List<SchoolWeek> weeks=schoolWeekDAO.executeQuery(sql, 0,-1);
		if (weeks.size()>0) {
			return weeks.get(0);
		}
		return null;
	}
	
	/***********************************************************************************************
	 * @功能：替换特殊字符
	 * @作者：贺子龙
	 * @日期：2016-03-07
	 ***********************************************************************************************/
	public String replaceString(String oldString){
		//将传来的特殊字符还原
		//1. +  URL 中+号表示空格 %2B
		//2. 空格 URL中的空格可以用+号或者编码 %20
		//3. /  分隔目录和子目录 %2F 
		//4. ?  分隔实际的 URL 和参数 %3F 
		//5. % 指定特殊字符 %25 
		//6. # 表示书签 %23 
		//7. & URL 中指定的参数间的分隔符 %26 
		//8. = URL 中指定参数的值 %3D
		oldString=oldString.replace("[Geng1Shang]", "+");
		oldString=oldString.replace("[Geng2Shang]", " ");
		oldString=oldString.replace("[Geng3Shang]", "/");
		oldString=oldString.replace("[Geng4Shang]", "?");
		oldString=oldString.replace("[Geng5Shang]", "%");
		oldString=oldString.replace("[Geng6Shang]", "#");
		oldString=oldString.replace("[Geng7Shang]", "&");
		oldString=oldString.replace("[Geng8Shang]", "=");
		oldString=oldString.replace("[Geng9Shang]", ".");
		String newString=oldString;
		return newString;
	}
	
	/***********************************************************************************************
	 * @功能：通用模塊service層定義-根据对象生成二维码，并返回二维码保存路径
	 * @作者：李小龙
	 ***********************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getDimensionalCode(LabRoomDevice d, String serverName) throws Exception {

		String url = "";
		// 获取系统路径
		String root = System.getProperty("xidlims.root");
		// 二维码的保存路径
		// File.separator windows是\，unix是/
		String path = "upload" + "/" + "dimensionalCode";
		String text = "http://"+serverName+showDeviceURL + d.getId();
		int width = 300;
		int height = 300;
		// 二维码的图片格式
		String format = "gif";
		Hashtable hints = new Hashtable();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		File sendPath = new File(root + path);
		// 构建文件夹
		if (!sendPath.exists()) {
			sendPath.mkdirs();
		}
		// 生成二维码
		File outputFile = new File(root + path + File.separator + d.getSchoolDevice().getDeviceNumber() + ".gif");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		url = path + "/" + d.getSchoolDevice().getDeviceNumber() + ".gif";

		System.out.println("二维码路径：" + url);
		return url;
	}
	
	
	/* (non-Javadoc)
	 * @see net.xidlims.service.ShareService#getAllPosition()
	 */
	/* (non-Javadoc)
	 * @see net.xidlims.service.common.ShareService#getAllPosition()
	 */
	@Override
	public Map getAllPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	/***********************************************************************************************
	 * 根据模块名称和操作类型判断当前登录人是否具有操作权限
	 * 
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public boolean isAuthentificated(User user,String menu, String realm) {
		boolean result=false;
		Set<Authority> authorities=user.getAuthorities();
		String ids="";
		for (Authority authority : authorities) {
			ids+=authority.getId()+",";
		}
		if(!ids.equals("")){
			ids=ids.substring(0, ids.length()-1);
		}
		DBConnect db=new DBConnect();
		String sql="SELECT count(*) FROM authority_menu "+
		"INNER JOIN authority ON authority_menu.authority_id = authority.id "+
		"INNER JOIN menu ON authority_menu.menu_id = menu.id "+
		"INNER JOIN realm ON authority_menu.realm_id = realm.id "+
		"WHERE menu.memo = '"+menu+"' AND realm.realm_type = '"+realm+"' AND authority_id IN("+ids+")";
		//System.out.println(sql);
		ResultSet resultSet=db.executeSql(sql, "localhost", "xidlims");
		try {
			if (resultSet.next()) {
				if(resultSet.getInt(1)>0){
					result=true;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("自定义标签类执行sql语句异常——————————");
			e.printStackTrace();
		}
		return result;
	}
	
	/***********************************************************************************************
	 * 根据模块名称和操作类型判断当前登录人是否具有操作权限
	 * 作者：黄崔俊
	 * 时间：2015-10-26 16:50:20
	 ***********************************************************************************************/
	@Override
	public boolean isAuthentificated(String menu, String realm,User user) {
		boolean result=false;
		Set<Authority> authorities=user.getAuthorities();
		String ids="";
		for (Authority authority : authorities) {
			ids+=authority.getId()+",";
		}
		if(!ids.equals("")){
			ids=ids.substring(0, ids.length()-1);
		}
		DBConnect db=new DBConnect();
		String sql="SELECT count(*) FROM AuthorityMenu c where c.menu.memo = '"+menu+"' and c.realm.realmType = '"+realm+"' and c.authority.id in("+ids+")";
		//System.out.println(sql);
		int count = ((Long)authorityMenuDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		if (count>0) {
			result = true;
		}
		return result;
	}


	/***********************************************************************************************
	 * 将文件上传到指定路径并返回文件名字
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public String fileUpload(HttpServletRequest request, String filePath) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    // 构建文件保存的目录
	    String PathDir = "/upload/"+ dateformat.format(new Date());*/
	    /** 得到文件保存目录的真实(绝对)路径* */
	    String RealPathDir = request.getSession().getServletContext().getRealPath(filePath);
	    //根据真实路径创建文件夹
	    File SaveFile = new File(RealPathDir);
	    if (!SaveFile.exists()){
	    	SaveFile.mkdirs();
	    }
	    /** 页面控件的文件流* */
	    MultipartFile multipartFile = multipartRequest.getFile("file");
	    /**判断文件不为空*/
	    if(!multipartFile.isEmpty()){
	    	//截取上传文件的名称，获取文件的后缀
	    	String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		    // 使用UUID生成文件名称,中文一般会报错(产生一个号称全球唯一的ID)
	    	String name = UUID.randomUUID().toString() + suffix;// 构建文件名称
		    // 拼成完整的文件保存路径加文件
		    String fileName = RealPathDir + File.separator + name;
		    File file = new File(fileName);
		    try {
		    	//转储文件
		        multipartFile.transferTo(file);
		    } catch (IllegalStateException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    /** 上传到服务器的文件的绝对路径* */	       
		    String saveUrl=filePath+File.separator+name;
		    return saveUrl;
	    }
	    return "";
	}
	/***********************************************************************************************
	 * 功能：将文件上传到指定路径并返回文件保存路径
	 * 作者：黄崔俊
	 * 时间：2015-12-14 14:02:30
	 ***********************************************************************************************/
	@Override
	public String fileUpload(HttpServletRequest request,String folderName, String filePath) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    // 构建文件保存的目录
	    String PathDir = "/upload/"+ dateformat.format(new Date());*/
		/** 得到文件保存目录的真实(绝对)路径* */
		String RealPathDir = request.getSession().getServletContext().getRealPath(filePath);
		//根据真实路径创建文件夹
		File SaveFile = new File(RealPathDir);
		if (!SaveFile.exists()){
			SaveFile.mkdirs();
		}
		/** 页面控件的文件流* */
		MultipartFile multipartFile = multipartRequest.getFile(folderName);
		/**判断文件不为空*/
		if(!multipartFile.isEmpty()){
			//截取上传文件的名称，获取文件的后缀
			String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			// 使用UUID生成文件名称,中文一般会报错(产生一个号称全球唯一的ID)
			String name = UUID.randomUUID().toString() + suffix;// 构建文件名称
			// 拼成完整的文件保存路径加文件
			String fileName = RealPathDir + File.separator + name;
			File file = new File(fileName);
			try {
				//转储文件
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/** 上传到服务器的文件的绝对路径* */	       
			String saveUrl=filePath+File.separator+name;
			return saveUrl;
		}
		return "";
	}
	/***********************************************************************************************
	 * 删除指定路径的文件
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public boolean deleteFile(String documentUrl) {
		boolean flag=false;
		File file=new File(documentUrl);
		if(file.exists()&&file.isFile()){
			file.delete();
			flag=true;
		}
		return flag;
	}
	/***********************************************************************************************
	 * 将指定文件上传到指定路径并返回文件保存路径
	 * 作者：李小龙
	 ***********************************************************************************************/
	@Override
	public String fileUpload(String pageFile,HttpServletRequest request, String filePath) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    
		/*SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    // 构建文件保存的目录
	    String PathDir = "/upload/"+ dateformat.format(new Date());*/
	    /** 得到文件保存目录的真实(绝对)路径* */
	    String RealPathDir = request.getSession().getServletContext().getRealPath(filePath);
	    //根据真实路径创建文件夹
	    File SaveFile = new File(RealPathDir);
	    if (!SaveFile.exists()){
	    	SaveFile.mkdirs();
	    }
	    /** 页面控件的文件流* */
	    System.out.println("=========="+pageFile);
	    MultipartFile multipartFile = multipartRequest.getFile(pageFile);
	    /**判断文件不为空*/
	    if(!multipartFile.isEmpty()){
	    	//截取上传文件的名称，获取文件的后缀
	    	String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		    // 使用UUID生成文件名称,中文一般会报错(产生一个号称全球唯一的ID)
	    	String name = UUID.randomUUID().toString() + suffix;// 构建文件名称
		    // 拼成完整的文件保存路径加文件
		    String fileName = RealPathDir + File.separator + name;
		    File file = new File(fileName);
		    try {
		    	//转储文件
		        multipartFile.transferTo(file);
		    } catch (IllegalStateException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    /** 上传到服务器的文件的绝对路径* */	       
		    String saveUrl=filePath+File.separator+name;
		    return saveUrl;
	    }
	    return "";
	}
	
	/***********************************************************************************************
	 * @功能： 处理资源列表的字符串 输入：包含资源id字符串，比如：,1632,1633,1634,1635,输入：资源id字符串,return upload集合
	 * @作者：魏诚
	 ***********************************************************************************************/
	public List<WkUpload> getUpload(String list) {
		// 要返回的集合
		List<WkUpload> upload = new ArrayList<WkUpload>();
		if (list != null && list.length() > 0 && list.indexOf(",") != -1) {// 字符串列表判空
			String[] s = list.split(",");
			for (String id : s) {
				// System.out.println(string);
				if (id != null && id.length() > 0&&!id.equals("undefined")) {// 资源id判空
					WkUpload u = wkUploadService.findUploadByPrimaryKey(Integer.valueOf(id));
					if (u != null) {
						upload.add(u);// 添加资源
					}

				}
			}
		}
		return upload;
	}

	@Override
	public User changeUserPassword(User user, String password) {
		// TODO Auto-generated method stub
		user.setPassword(password);
		return userDAO.store(user);
	}
	
	/**
	  *@comment：获取form上传的文件
	  *@param request
	  *@return：
	  *@author：叶明盾
	  *@date：2015-10-14 下午3:52:37
	 */
	public String getUpdateFileSavePath(HttpServletRequest request) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 日期格式 **/
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		/** 构建文件保存的目录* */
		String logoPathDir = "/upload/" + dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
		/** 根据真实路径创建目录* */
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		/** 页面控件的文件流* */

		MultipartFile multipartFile = multipartRequest.getFile("file");
		if (multipartFile == null) {// 如果没有文件，则返回
			return null;
		}
		/** 获取文件的后缀* */
		// System.out.println(multipartFile.getOriginalFilename());
		/** 判断文件不为空 */
		if (!multipartFile.isEmpty()) {
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			/** 使用UUID生成文件名称* */
			String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件* */
			String fileName = logoRealPathDir + File.separator + logImageName;
			File file = new File(fileName);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/** 上传到服务器的文件的绝对路径* */
			String saveUrl = logoPathDir + "/" + logImageName;
			return saveUrl;
		}
		return null;
	}
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		User newUser = userDAO.store(user);
		return newUser;
	}

	/***********************************************************************************************
	 * @功能：上传文件
	 * @作者：魏诚
	 * @日期：2014-07-27
	 ***********************************************************************************************/
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void uploadFiles(HttpServletRequest request, String path, String type, int id) {
		// TODO Auto-generated method stub
		String sep = System.getProperty("file.separator");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 构建文件保存的目录* */
		// String PathDir = "/upload/"+ dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String RealPathDir = request.getSession().getServletContext().getRealPath("/") + sep + path;
		// System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
		/** 根据真实路径创建目录* */
		File SaveFile = new File(RealPathDir);
		if (!SaveFile.exists()) {
			SaveFile.mkdirs();
		}
		Iterator fileNames = multipartRequest.getFileNames();
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			MultipartFile file = multipartRequest.getFile(filename);
			String fileTrueName = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					file.transferTo(new File(RealPathDir + sep + fileTrueName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (type.equals("saveLabRoomVideo")) {
					labRoomService.saveLabRoomVideo(fileTrueName, id);
				} else if (type.equals("saveLabRoomDocument")) {
					labRoomService.saveLabRoomDocument(fileTrueName, id, 1);
				} else if (type.equals("saveDocument")) {
					labAnnexService.saveDocument(fileTrueName, id);
				} else if (type.equals("saveVideo")) {
					labAnnexService.saveVideo(fileTrueName, id);
				} else if (type.equals("saveDeviceDocument")) {
					labRoomDeviceService.saveDeviceDocument(fileTrueName, id, 2);
				} else if (type.equals("saveDeviceImage")) {
					labRoomDeviceService.saveDeviceDocument(fileTrueName, id, 1);
				}

			}
		}
		MultipartFile multipartFile = multipartRequest.getFile("file");

		/** 判断文件不为空 */
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			MultipartFile file = multipartRequest.getFile(filename);
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			/** 使用UUID生成文件名称* */
			String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件* */
			String fileName = RealPathDir + File.separator + logImageName;
			File thisFile = new File(RealPathDir);
			if (!thisFile.exists()) {
				thisFile.mkdirs();
			} else {
				try {
					file.transferTo(new File(fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	/***********************************************************************************************
	 * @功能：通用模塊service層定義-判断指定实验室的禁用时间段内，根据日历表的起始时间和结束时间判断是否可用
	 * @作者：魏诚
	 * @日期：2016-03-08
	 ***********************************************************************************************/
	@Override
	public boolean isLimitedByTime(List<LabRoomLimitTime> labRoomLimitTimes,Calendar startDate,Calendar endDate) {

		boolean flag = true;
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//如果是禁用时间则返回limit
		for(LabRoomLimitTime labRoomLimitTime:labRoomLimitTimes ){
			//设置日期起始变量
			Calendar currCalendar = startDate;
			//遍历日历插件上的起始时间结束时间，计量单位按日
			//for (; currCalendar.compareTo(endDate) <= 0;) {  
				int cCurrentWeek = this.getBelongsSchoolWeek(startDate);

				
				//当前遍历禁用日期星期，匹配当前的日历时间的星期
				int weekday =currCalendar.get(Calendar.DAY_OF_WEEK)-1;
				
				if(weekday==0){  
					weekday = 7;;  
				}
				
				if(weekday==labRoomLimitTime.getWeekday()){
					//当前遍历的周次匹配禁用日期的周次
					if(labRoomLimitTime.getStartweek()<=cCurrentWeek&&labRoomLimitTime.getEndweek()>=cCurrentWeek){
						//当前选取时间对于当前节次
						String sql = "select c from SystemTime c where c.systemCampus.id=2 and (c.startDate>'"+ sdfTime.format(currCalendar.getTime()).split(" ")[1] +
								"' and c.startDate<'"+ sdfTime.format(endDate.getTime()).split(" ")[1] + 
								"' or c.endDate>'"+ sdfTime.format(currCalendar.getTime()).split(" ")[1] +
								"' and c.endDate<'"+ sdfTime.format(endDate.getTime()).split(" ")[1] + "')";
						System.out.println("sql:" + sql);
						List<SystemTime> systemTimes = systemTimeDAO.executeQuery(sql, 0,-1);
						//对在日历所选的日期所获取的周次进行遍历
			            //如果禁止日期对应在周次内，同时星期对应的日期相同，节次相同则判断为limit
						for(SystemTime systemTime:systemTimes){
							if(labRoomLimitTime.getStartclass()<=systemTime.getId()&&labRoomLimitTime.getEndclass()>=systemTime.getId()){
								return false;
							}
						}
						
					}
				//}
				//累计加一天
				//currCalendar.add(Calendar.DAY_OF_YEAR, 1);  
			}
		}
		
		return flag;
	}
	
	/***********************************************************************************************
	 * @功能：通用模塊service層定義-根据周次星期信息获取当前时间
	 * @作者：魏诚
	 * @日期：2016-03-05
	 ***********************************************************************************************/
	@Override
	public String getDateByWeekdayClassWeek(int weekday,int classes,int week,int term,int flag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "select w from SchoolWeek w where w.weekday=" + weekday + " and w.week=" +week + " and w.schoolTerm.id=" +term;
		List<SchoolWeek> weeks = schoolWeekDAO.executeQuery(sql, 0, -1);
		  
		SystemTime systemTime = systemTimeDAO.findSystemTimeById(classes);
		
		if (weeks.size() > 0&&systemTime!=null) {
			//如果为起始时间flag=0，如果为结束时间flag=1
			if(flag==0){
				return sdf.format(weeks.get(0).getDate().getTime())+" "+ sdfTime.format(systemTime.getStartDate().getTime()).split(" ")[1]  ;
			}else{
				return sdf.format(weeks.get(0).getDate().getTime())+" "+ sdfTime.format(systemTime.getEndDate().getTime()).split(" ")[1]  ;
			}
		} else {
			return "";
		}

	}
	/***********************************************************************************************
	 * @功能：通用username找到user
	 * @作者：郑昕茹
	 * @日期：2016-08-07
	 ***********************************************************************************************/
	public User findUserByUsername(String username){
		return userDAO.findUserByUsername(username);
	}
	
	/*************************************************************************************
	 * Description：排课模块-ajax获取可用周次{判断当前的实验室是否属于不需要判冲的特殊实验室}
	 * 
	 * @author： 贺子龙
	 * @date：2016-07-27
	 *************************************************************************************/
	public boolean isSpecialLabRoom(int labRoomId){
		boolean isSpecialLabRoom = false;
		// 通过主键查找对应的实验室记录
		LabRoom labRoom = labRoomDAO.findLabRoomByPrimaryKey(labRoomId);
		// 判断labRoom的isSpecial属性
		if (!EmptyUtil.isIntegerEmpty(labRoom.getIsSpecial()) 
				&& labRoom.getIsSpecial().equals(1)) {// 是特殊实验室
			isSpecialLabRoom = true;
		}
		
		return isSpecialLabRoom;
	}
	
	/*************************************************************************************
	 * Description：设备预约模块-根据日期获取其所在天的电源控制器开放时间
	 * 
	 * @author： 郑昕茹
	 * @date：2016-10-18
	 *************************************************************************************/
	public IotSharePowerOpentime getBelongNowdayIotSharePowerOpentime(Calendar c){
		int weekday =c.get(Calendar.DAY_OF_WEEK);
		SchoolTerm schoolTerm = this.getBelongsSchoolTerm(c);
		String sql ="select i from IotSharePowerOpentime i where 1=1";
		sql +=" and schoolWeekday.id="+weekday;
		if(schoolTerm != null){
			sql += " and schooTerm.id ="+schoolTerm.getId();
		}
		List<IotSharePowerOpentime> times = iotSharePowerOpentimeDAO.executeQuery(sql, 0, -1, null);
		if(times != null && times.size() != 0){
			return times.get(0);
		}
		return null;
	}
	
	/***********************************************************************************************
	 * @功能：获取专业列表map
	 * @作者：裴继超
	 * @日期：2016年7月13日9:18:37
	 ***********************************************************************************************/

	@Override
	public Map getMajorsMap() {
		// 获取所有的专业；
		Set<SchoolMajor> majors = schoolMajorDAO.findAllSchoolMajors();
		// 新建一个HashMap对象；
		Map majorMap = new HashMap();
		// 循环majors；
		for (SchoolMajor major : majors) {
			// 将major的majorName映射成majorNumber；
			majorMap.put(major.getMajorNumber(), major.getMajorName());
		}
		return majorMap;
	}
	
	/***********************************************************************************************
	 * 学期-查找当前学期及以后的学期
	 * 作者：裴继超
	 * 时间：2016年8月11日11:38:44
	 ***********************************************************************************************/
	@Override
	public List<SchoolTerm> getNowAndAfterSchoolTerm(Calendar time) {
		// TODO Auto-generated method stub
		List<SchoolTerm> terms = new ArrayList();
		//遍历所有的学期
		Set<SchoolTerm> allTerms=schoolTermDAO.findAllSchoolTerms();
		for (SchoolTerm schoolTerm : allTerms) {
			Calendar start=schoolTerm.getTermStart();
			Calendar end=schoolTerm.getTermEnd();
			if(time.after(start)&&time.before(end)){
				terms.add(schoolTerm);
			}
			if(time.before(start)){
				terms.add(schoolTerm);
			}
		}
		return terms;
	}
	
	
	
	/***********************************************************************************************
 * @功能：通用模塊service層定義-修改登录的角色
 * @作者：魏诚 
 * @日期：2016-11-17
 ***********************************************************************************************/
public void changeRole(final String authorityName) {

	final SecurityContext context = SecurityContextHolder.getContext();

	final Object credentials = context.getAuthentication().getCredentials();
	final Object details = context.getAuthentication().getDetails();
	final Object principal = context.getAuthentication().getPrincipal();
	final String name = context.getAuthentication().getName();

	Authentication authentication = new Authentication() {
		private static final long serialVersionUID = 1L;

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		}

		@Override
		public boolean isAuthenticated() {
			return true;
		}

		@Override
		public Object getPrincipal() {
			return principal;
		}

		@Override
		public Object getDetails() {
			return details;
		}

		@Override
		public Object getCredentials() {
			return credentials;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_"+authorityName));
			return authorities;
		}
	};

	context.setAuthentication(authentication);
	}


		/***********************************************************************************************
		 * description:根据学院编号获取所有教师
		 * @author：郑昕茹
		 * @date:2017-04-17
		 ***********************************************************************************************/
		@Override
		public List<User> findAllTeacheresByAcademyNumber(String academyNumber){
			String s = "select u from User u where u.userRole=1";
			if(!academyNumber.equals("-1"))
			{
				s += " and u.schoolAcademy.academyNumber ='"+academyNumber+"'";
			}
			List<User> users = userDAO.executeQuery(s, 0, -1);
			return users;
		}
		
		/***********************************************************************************************
		+	 * @功能：判断时间字符串所属的周次
		+	 * @作者：贺子龙
		+	 * @日期：2016-05-18
		+	 ***********************************************************************************************/
		public SchoolWeek getSchoolWeekByString(String timeString){
				String sql = "select s from SchoolWeek s where 1=1";
				if (timeString!=null && !timeString.equals("")) {
					sql+=" and s.date between '"+timeString+"' and '"+timeString+"'";
				}
				List<SchoolWeek> weekList = schoolWeekDAO.executeQuery(sql);
				if(weekList!=null && weekList.size()>0) {
					return weekList.get(0);
				}else {
					return null;
				}
			}
			
			/***********************************************************************************************
			 * @功能：判断时间字符串所属的周次
			 * @作者：贺子龙
			 * @日期：2016-05-18
			 ***********************************************************************************************/
			public CStaticValue findCStaticValueByCodeAndAcademy(String code, String academy){
				String sql = "select c from CStaticValue c where 1=1";
				sql+=" and c.code like '"+code+"'";
				sql+=" and c.academyNumber like '"+academy+"'";
				List<CStaticValue> values = cStaticValueDAO.executeQuery(sql);
				if (values!=null && values.size()>0) {
					return values.get(0);
				}else {
					return null;
				}
			}
			
		
			/*************************************************************************************
			 * @Description: 实验室预约-实验室预约{通过week,weekday,termId查找一条SchoolWeek}
			 * @author： 郭宇翀
			 * @date：2016-09-14
			 *************************************************************************************/
			public SchoolWeek findSchoolWeekByWeekAndWeekDay(int week, int weekday,int termId) {
				String sql = "select s from SchoolWeek s where 1=1 and s.week="+week+"and s.weekday="+weekday+" and s.schoolTerm.id="+termId;
				List<SchoolWeek> schoolWeeks = schoolWeekDAO.executeQuery(sql);
				return schoolWeeks.get(0);
			}
			
			
			/***********************************************************************************************
			 * @功能：判断时间字符串所属的节次
			 * @作者：贺子龙
			 * @日期：2016-05-18
			 ***********************************************************************************************/
			public int getSystemTimeByString(String timeString, int cid){
				int systemTimeId = 0;
				LabCenter center = labCenterDAO.findLabCenterByPrimaryKey(cid);
				String campusNumber = "S";
				
				Calendar time = DateUtil.strToCal(timeString, "HH:mm");
				// 建立查询
				String sql = "select s from SystemTime s where 1=1 ";
				sql+=" and s.systemCampus.campusNumber like '"+campusNumber+"'";// 确定校区
				sql+=" order by s.startDate asc";
				// 遍历所有的学期，并按学期开始时间，正序排列
				List<SystemTime> timeList = systemTimeDAO.executeQuery(sql);
				SystemTime theSystemTime=new SystemTime();
				for (SystemTime systemTime : timeList) {
					// 获取学期起始时间
					Calendar start=systemTime.getStartDate();
					// 获取学期结束时间
					Calendar end=systemTime.getEndDate();
					
					if(time.after(start)&&time.before(end)){// 当前日期正好属于某一个学期
					theSystemTime = systemTime;
						break;
					}else if (time.before(start)) {// 寒暑假，没有已经建立新学期时，按新学期
						theSystemTime = systemTime;
						break;
					}else if(time.after(end)){// 寒暑假，没有建新学期时，按老学期
						theSystemTime = systemTime;
					}
				}
				
				if (campusNumber.equals("1")) {
					systemTimeId = theSystemTime.getId()-13;
				}else {
					systemTimeId = theSystemTime.getId();
				}
				
				return systemTimeId;
			}

		/***********************************************************************************************
		 * @description：根据权限名找到权限
		 * @author:郑昕茹
		 * @date: 2017-05-28
		***********************************************************************************************/
		public Authority findAuthorityByAuthorityName(String authorityName){
			String sql = "select a from Authority a where 1=1 and a.authorityName ='"+authorityName+"'";
			if(authorityDAO.executeQuery(sql, 0,-1).size() != 0){
				return authorityDAO.executeQuery(sql, 0,-1).get(0);
			}
			else return null;
		}
		
		/***********************************************************************************************
		 * @description：根据权限找到拥有该权限的用户
		 * @author:郑昕茹
		 * @date: 2017-06-04
		***********************************************************************************************/
		public List<User> findUsersByAuthority(Integer authorityId){
			String sql = "select u from User u left join u.authorities a where 1=1";
			sql += " and a.id ="+authorityId;
			sql += " group by u.username";
			return userDAO.executeQuery(sql, 0, -1);
		}
		
		/***********************************************************************************************
		 * 判断时间所属的学期
		 * 作者：王昊 
		 ***********************************************************************************************/
		@Override
		public SchoolTerm getBelongsSchoolTerm1(Calendar time) {
			//遍历所有的学期
			Set<SchoolTerm> terms=schoolTermDAO.findAllSchoolTerms();
			SchoolTerm term=new SchoolTerm();
			for (SchoolTerm schoolTerm : terms) {
				// 获得学期开始时间
				Calendar start=schoolTerm.getTermStart();
				// 获取学期结束时间
				Calendar end=schoolTerm.getTermEnd();
				
				if(time.after(start)&&time.before(end)){//当前时间正好属于某一个学期
					term=schoolTerm;
					break;
				}else if(time.before(start)){//寒暑假，没有已经建立新学期时，按新学期
					term=schoolTerm;
					break;
				}
				else if(time.after(end)){//寒暑假，没有建新学期时，按新学期
					term=schoolTerm;
				}
			}
			return term;
		}
		/***********************************************************************************************
		 * 查询所有的学期并倒序排列
		 * 作者：王昊
		 ***********************************************************************************************/
		@Override
		public List<SchoolTerm> findAllSchoolTerms1() {
			String sql="select s from SchoolTerm s where 1=1 order by s.id desc";
			return schoolTermDAO.executeQuery(sql, 0, -1);
		}
		
}