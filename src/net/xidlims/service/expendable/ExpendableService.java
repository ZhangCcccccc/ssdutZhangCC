package net.xidlims.service.expendable;

import java.util.List;

import net.xidlims.domain.Expendable;
import net.xidlims.domain.ExpendableApply;
import net.xidlims.domain.ExpendableApplyAuditRecord;
import net.xidlims.domain.ExpendableStockRecord;
import net.xidlims.domain.User;




public interface ExpendableService {
	
	
	/***********************************************************************************
	 * @功能：获取当耗材申购信息
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findExpendableRecords(Integer currpage, Integer pageSize, Expendable expendable, User user,Integer flag);
	
	/***********************************************************************************
	 * @功能：根据消耗品订单编号找到申购信息
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findExpendableRecordsbyOrderNumber(String orderNumber);
	
	/***********************************************************************************
	 * @功能：获取当耗材申购信息
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findExpendableInUse(Integer currpage, Integer pageSize, Expendable expendable);
	
	/***********************************************************************************
	 * @功能：获取全部的不同耗材名称
	 * @author 郑昕茹
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findAllExpendableName();
	
	/***********************************************************************************
	 * @功能：获取全部的不同耗材类别的耗材记录
	 * @author 郑昕茹
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public List<Expendable> findAllExpendableSource();
	
	/***********************************************************************************
	 * @功能：根据消耗品id找到对应的消耗品
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public Expendable findExpendableByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：保存盘库信息
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public ExpendableStockRecord saveExpendableStockRecord(ExpendableStockRecord expendableStockRecord);
	
	/***********************************************************************************
	 * @功能：保存消耗品信息
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public Expendable saveExpendable(Expendable expendable);
	
	/***********************************************************************************
	 * @功能：根据消耗品id找到对应的申领记录
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public ExpendableStockRecord findExpendableStockRecordByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：保存申购信息
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public ExpendableApply saveExpendableApply(ExpendableApply expendableApply);
	
	/***********************************************************************************
	 * @功能：获取当耗材申领信息
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	public List<ExpendableApply> findExpendableApplies(Integer currpage, Integer pageSize, ExpendableApply expendableApply,Integer flag, Integer id);
	
	/***********************************************************************************
	 * @功能：根据消耗品id找到对应的消耗品审核信息
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	public ExpendableApply findExpendableApplyByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：保存消耗品审核
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	public ExpendableApplyAuditRecord saveExpendableApplyAuditRecord(ExpendableApplyAuditRecord expendableApplyAuditRecord);
	/***********************************************************************************
	 * @功能：获取当耗材盘库信息
	 * @author 郑昕茹
	 * @日期：2016-08-01
	 * **********************************************************************************/
	public List<ExpendableStockRecord> findExpendableStockRecords(Integer currpage, Integer pageSize, ExpendableStockRecord expendableStockRecord);
	
}