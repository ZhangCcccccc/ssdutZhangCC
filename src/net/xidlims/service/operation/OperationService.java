package net.xidlims.service.operation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import net.xidlims.domain.CDictionary;
import net.xidlims.domain.COperationOutlineCredit;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationItemDevice;
import net.xidlims.domain.OperationItemMaterialRecord;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.SchoolAcademy;
import net.xidlims.domain.SystemMajor12;

public interface OperationService {

	/**
	 * 根据主键获取实验项目对象
	 * @author hly
	 * 2015.07.29
	 */
	public OperationItem findOperationItemByPrimaryKey(Integer operationItemId);
	
	/**
	 * 根据查询条件实验项目列表
	 * @author hly
	 * 2015.07.29
	 */
	public List<OperationItem> findAllOperationItemByQuery(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid);
	
	/**
	 * 根据查询条件实验项目列表--除草稿
	 * @author hly
	 * 2015.07.29
	 */
	public List<OperationItem> findAllOperationItemExceptDraft(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid, int orderBy);
	
	
	/**
	 * 根据查询条件实验项目列表--增加排序
	 * @author 贺子龙
	 * 2015-11-20
	 */
	public List<OperationItem> findAllOperationItemByQuery(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid, int orderBy);
	
	/**
	 * 实验室导入的时候需要默认显示当前学期之前的那个学期
	 * @author 贺子龙
	 * 2015-09-24 11:15:25
	 */
	public List<OperationItem> findAllOperationItemByQueryImport(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid);
	/**
	 * 实验室导入的时候需要默认显示当前学期之前的那个学期--增加排序
	 * @author 贺子龙
	 * 2015-09-24 11:15:25
	 */
	public List<OperationItem> findAllOperationItemByQueryImport(Integer currpage, Integer pageSize, OperationItem operationItem, Integer cid, int orderBy);
	
	
	/**
	 * 根据查询条件实验项目记录数量
	 * @author hly
	 * 2015.07.29
	 */
	public Integer findAllOperationItemByQueryCount(OperationItem operationItem, Integer cid);
	
	/**
	 * 根据查询条件实验项目记录数量--除草稿外
	 * @author hly
	 * 2015.07.29
	 */
	public Integer findAllOperationItemExceptDraft(OperationItem operationItem, Integer cid);
	
	/**
	 * 保存实验项目
	 * @author hly
	 * 2015.07.29
	 */
	public OperationItem saveOperationItem(OperationItem operationItem);
	
	/**
	 * 删除实验项目
	 * @author hly
	 * 2015.07.29
	 */
	public boolean deleteOperationItem(Integer operationItemId);
	
	/**
	 * 提交实验项目
	 * @author hly
	 * 2015.08.06
	 */
	public OperationItem submitOperationItem(OperationItem operationItem);
	
	/**
	 * 设置项目编号
	 * @author hly
	 * 2015.08.07
	 */
	public void saveCodeCustom(OperationItem operationItem);
	
	/**
	 * 导入整个学期的实验项目
	 * @author hly
	 * 2015.08.07
	 */
	public void importTermOperationItem(Integer sourceTermId, Integer targetTermId, Integer cid);
	
	/**
	 * 导入选中的实验项目
	 * @author hly
	 * 2015.08.07
	 */
	public void importOperationItem(HttpServletRequest request,Integer termId, String itemIds);
	
	/**
	 * 获取指定实验项目的材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	public List<OperationItemMaterialRecord> getItemMaterialRecordByItem(Integer itemId, Integer currpage, Integer pageSize);
	/**
	 * 获取指定实验项目的材料使用记录不分页
	 * @author 贺子龙
	 * 2015.09.10
	 */
	public List<OperationItemMaterialRecord> getItemMaterialRecordByItem(Integer itemId);
	/**
	 * 获取指定实验项目的材料使用记录数量
	 * @author hly
	 * 2015.08.10
	 */
	public int getItemMaterialRecordByItemCount(Integer itemId);
	
	/**
	 * 保存实验项目材料使用记录
	 * @author hly
	 * 2015.08.10
	 */
	public OperationItemMaterialRecord saveItemMaterialRecord(OperationItemMaterialRecord operationItemMaterialRecord);
	
	/**
	 * 删除实验项目使用材料记录
	 * @author hly
	 * 2015.08.10
	 */
	public boolean deleteItemMaterialRecord(Integer mrId);
	
	/**
	 * 根据主键获取实验项目使用材料记录
	 * @author hly
	 * 2015.08.10
	 */
	public OperationItemMaterialRecord findItemMaterialRecordByPrimaryKey(Integer lpmrId);
	
	/**
	 * 获取指定实验项目的设备
	 * @author hly
	 * 2015.08.19
	 */
	public List<OperationItemDevice> getItemDeviceByItem(Integer itemId, Integer category, Integer currpage, Integer pageSize);
	
	/**
	 * 获取指定实验项目的设备数量
	 * @author hly
	 * 2015.08.19
	 */
	public int getItemDeviceByItemCount(Integer itemId, Integer category);
	
	/**
	 * 批量保存实验项目设备
	 * @author hly
	 * 2015.08.19
	 */
	public void saveItemDevice(Integer itemId, String category, String ids);
	
	/**
	 * 删除实验项目设备
	 * @author hly
	 * 2015.08.19
	 */
	public boolean deleteItemDevice(Integer itemDeviceId);
	
	/**
	 * 根据学院和角色获取用户数据
	 * @author hly
	 * 2015.08.28
	 */
	public List<Map<String, String>> getUserByAcademyRole(String academyNumber, String role);
	
	/**
	 * 根据学院和角色获取用户数据--加权限
	 * @author hly
	 * 2015.08.28
	 */
	public List<Map<String, String>> getUserByAcademyRole(String academyNumber, String role, int authorityId);
	
	/**********************************
	 * 功能：找出operationitem表中的创建者字段
	 * 作者：贺子龙
	 * 时间：2015-09-02
	 *********************************/
	
	public Map<String, String>getsome();
	

	/**********************************
	 * 功能：找出operationitem表中的课程段
	 * 作者：贺子龙
	 * 时间：2015-09-02
	 *********************************/
	public Map<String, String>getCourse(int cid);
	
	/**********************************
	 * 功能：找出operationitem表中的课程段(属于当前登录用户的)
	 * 作者：贺子龙
	 * 时间：2015-11-12
	 *********************************/
	public Map<String, String>getCourse(int cid,String username);
	/*************************************************************************************
     * 功能：查找所有的运行记录
     * 作者 ：徐文
     * 日期：2016-05-27
     ***************************************************************************************/
	public int  getOutlinelist(OperationOutline operationOutline,int currpage,int pagesize,int sid);
	/*************************************************************************************
     * 功能：查找所有的运行记录分页
     * 作者 ：徐文
     * 日期：2016-05-27
     ***************************************************************************************/
	public List<OperationOutline>  getOutlinelistpage(HttpServletRequest request,OperationOutline operationOutline,int currpage,int pagesize,int sid);
	/***************************************************************************************
     * 功能 ：查找大纲
     * 作者：徐文
     * 日期：2016-05-27
     **************************************************************************************/
	public  OperationOutline   getoperationoutlineinfor(int idkey);
	/***********************************************************************************
     * 功能 ： 查找未被大纲使用的项目卡项目卡数
     * 作者：徐文
     * 日期：2016-05-27
     ***********************************************************************************/
	public List<OperationItem>  getoperationItemlist();
	/***********************************************************************************
     * 功能 ： 查找所以课程info
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	@SuppressWarnings("rawtypes")
	public   Map  getschoolcouresMap(Integer sid);
	/***********************************************************************************
     * 功能 ： 查找所在专业
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	public List<SystemMajor12> getschoolmajerSet(Integer sid);
	/***********************************************************************************
     * 功能 ：查学分
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	@SuppressWarnings("rawtypes")
	public Map  getcoperationscareMap();
	/***********************************************************************************
     * 功能 ：查开课学院
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	@SuppressWarnings("rawtypes")
	public Map   getoperationstartschooleMap(Integer sid);
	/***********************************************************************************
     * 功能 ：查找课程性质
     * 作者：徐文
     * 日期：2016-05-30
     ***********************************************************************************/
	public List<CDictionary>  getcommencementnatureSet();
	
	/***********************************************************************************
     * 功能 ：查找课程类型
     * 作者：张凯
     * 日期：2017-03-8
     ***********************************************************************************/
	public List<CDictionary> getCourseType();
	/*********************************************************************************
	 * 功能:保存大纲内容
	 * 作者：徐文
	 * 日期：2016-05-30
	 ********************************************************************************/
	public  OperationOutline  saveoperationoutline(OperationOutline operationOutline,HttpServletRequest request);
	/*********************************************************************************
	 * 功能:实验室大纲多文件上传
	 * 作者：徐文
	 * 日期：2016-06-01
	 ********************************************************************************/
	 public String   uploaddnolinedocment(HttpServletRequest request, HttpServletResponse response, Integer id);
	 /*********************************************************************************
	  * 功能:item搜索
	  * 作者：徐文
	  * 日期：2016-06-01
	  ********************************************************************************/
	 public  List<OperationItem> getitem(String a );
	 /****************************************************************************
	  * 功能：删除实验室大纲
	  * 作者：徐文
	  * 日期：2016-06-01
	  ****************************************************************************/
	 public void  delectloutline(int idkey);
	 
	 /*********************************************************************************
		 *@description:实验室项目多文件上传
		 *@author: 郑昕茹
		 *@date：2016-11-09
		 ********************************************************************************/
	public String uploadItemdocument(HttpServletRequest request, HttpServletResponse response, Integer id);
	
	/**************************************************************************************
     * 功能：实验大纲管理-导入实验大纲
     * 作者：罗璇
     * 日期：2017年3月5日
     **************************************************************************************/
	@Transactional
	public void importOperationOutlines(String File,Integer sid);
	/*********************************************************************************
	 * 功能:个人管理上传头像
	 * 作者：戴昊宇
	 * 日期：2017-08-30
	 ********************************************************************************/
	 public String uploadphoto(HttpServletRequest request, HttpServletResponse response, Integer id);
}
