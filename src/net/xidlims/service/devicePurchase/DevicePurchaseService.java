package net.xidlims.service.devicePurchase;

import java.util.List;

import net.xidlims.domain.DeviceStatusRecord;
import net.xidlims.domain.NDeviceAuditRecord;
import net.xidlims.domain.NDevicePurchase;
import net.xidlims.domain.User;




public interface DevicePurchaseService {
	/***********************************************************************************
	 * @功能：获取当前设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public List<NDevicePurchase> findNDevicePurchase(Integer currpage,Integer auditStatus, Integer pageSize, NDevicePurchase nDevicePurchase, User user);
	
	/***********************************************************************************
	 * @功能：获取当前设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public List<NDevicePurchase> findNDevicePurchases(Integer currpage,Integer auditStatus, Integer pageSize, NDevicePurchase nDevicePurchase, User user);
	
	/***********************************************************************************
	 * @功能：保存设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchase saveDevicePurchase(NDevicePurchase nDevicePurchase);
	
	/***********************************************************************************
	 * @功能：根据申购主键找到申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchase findDevicePurchaseByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：删除-通过主键找到申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public boolean deleteDevicePurchase(Integer id);
	/***********************************************************************************
	 * @功能：通过deviceId找到所有的设备状态记录
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public List<DeviceStatusRecord> findDeviceStatusRecordByDeviceId(Integer id);
	/***********************************************************************************
	 * @功能：根据当前时间判断是否要改变状态
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public void changeDeviceStatusByCurrentTime(Integer currpage, Integer pageSize);
	/***********************************************************************************
     * @功能:随机生成 位数
     * @author 徐文
     * @日期：2016-07-25
     * **********************************************************************************/
    public String getRandomNumber(Integer count);
	
    /***********************************************************************************
	 * @功能：保存设备审核记录
	 * @author 徐文
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public NDeviceAuditRecord saveNDeviceAuditRecord(NDeviceAuditRecord nDeviceAuditRecord);
	/***********************************************************************************
	 * @功能：保存延迟后的截止日期
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public NDevicePurchase saveDevicePurchaseEndDate(NDevicePurchase nDevicePurchase);
	/***********************************************************************************
	 * @功能：保存设备状态记录
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public DeviceStatusRecord saveDeviceStatusRecord(DeviceStatusRecord deviceStatusRecord);
	/***********************************************************************************
	 * @功能：查找所有不同申购编号的申购记录
	 * @author 郑昕茹
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public List<NDevicePurchase> findAllPurchaseNumber();
}