package net.xidlims.service.newoperation;

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

public interface NewOperationService {

	/*************************************************************************************
	 * description：查找所有的实验大纲名称
	 * @authot:郑昕茹
	 * @date:2017-04-11
	 ***************************************************************************************/
	public List<OperationOutline> getOutlineNames(HttpServletRequest request);
	

	/*************************************************************************************
	 * description：根据实验大纲生成要新建的实验项目的编号
	 * @authot:郑昕茹
	 * @date:2017-04-11
	 ***************************************************************************************/
	public String getItemCodeByOutline(OperationOutline o);
	

	/***********************************************************************************
	 * @功能：根据属性orderNumber找到对于的operationItem
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public OperationItem findOperationItemByOrderNumberAndOutlineId(Integer order, Integer outlineId);
	
	/***********************************************************************************
	 * @功能：找到 实验项目中orderNumber最大的那条记录
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public OperationItem findMaxOrderNumber(Integer outlineId);
	

	/***********************************************************************************
	 * @功能：找到实验大纲下的全部实验项目并按照orderNumber排序
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public List<OperationItem> findOperationItemOutlineIdOrderByNumber( Integer outlineId);
	
	/***********************************************************************************
	 * @功能：根据课程编号找到其下全部实验大纲
	 * @author 郑昕茹
	 * @日期：2017-05-02
	 * **********************************************************************************/
	public List<OperationOutline> findOperationOutlinesByCourseCodeAndType(
			OperationOutline operationOutline, int currpage, int pagesize, String courseCode, Integer type);
	
	/***********************************************************************************
	 * @功能：找到 实验项目中编号最大的那条记录
	 * @author 郑昕茹
	 * @日期：2017-04-11
	 * **********************************************************************************/
	public OperationItem findMaxCodeCustom(Integer outlineId);
	
	
	/***********************************************************************************
	 * @功能：根据属性orderNumber找到orderNumber大于它的operationItem
	 * @author 郑昕茹
	 * @日期：2017-04-12
	 * **********************************************************************************/
	public List<OperationItem> findOperationItemsLargerOrderNumberByOutlineId(Integer order, Integer outlineId);
	
	
	/***********************************************************************************
	 * @功能：找到所有审核中的实验大纲
	 * @author 郑昕茹
	 * @日期：2017-04-12
	 * **********************************************************************************/
	public List<OperationOutline> getAuditOutlinelistpage(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize,int sid);
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲
	 * @author 郑昕茹
	 * @日期：2017-04-13
	 * **********************************************************************************/
	public List<OperationOutline> getAllOutlinelistpage(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize,int sid) ;
	
	
	/***********************************************************************************
	 * @功能：查找当前登陆人所在学院下的所有课程
	 * @author 郑昕茹
	 * @日期：2017-04-13
	 * **********************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getschoolcouresMapByAcademy(HttpServletRequest request) ;
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲（学生相关）
	 * @author 郑昕茹
	 * @日期：2017-05-02
	 * **********************************************************************************/
	public List<OperationOutline> getListStudentsOperationOutlines(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize);
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲（教师相关）
	 * @author 郑昕茹
	 * @日期：2017-05-02
	 * **********************************************************************************/
	public List<OperationOutline> getListTeachersOperationOutlines(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize);
	
	/***********************************************************************************
	 * @description：根据课程找到其下所有实验项目
	 * @author 郑昕茹
	 * @date：2017-05-14
	 * **********************************************************************************/
	public List<OperationItem> findOperationItemsByCourseNumber(String courseNumber);
	
	/***********************************************************************************
	 * @功能：找到全部实验大纲
	 * @author 郑昕茹
	 * @日期：2017-05-28
	 * **********************************************************************************/
	public List<OperationOutline> getListAllOperationOutlines(HttpServletRequest request,
			OperationOutline operationOutline, int currpage, int pagesize);
}
