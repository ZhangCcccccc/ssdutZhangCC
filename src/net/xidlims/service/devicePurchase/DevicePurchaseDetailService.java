package net.xidlims.service.devicePurchase;

import java.util.List;

import net.xidlims.domain.CDeviceSupplier;
import net.xidlims.domain.NDevicePurchaseDetail;




public interface DevicePurchaseDetailService {
	
	/***********************************************************************************
	 * @功能：保存设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchaseDetail saveNDevicePurchaseDetail(NDevicePurchaseDetail nDevicePurchaseDetail);
	
	/***********************************************************************************
	 * @功能：根据申购主键找到申购信息detail
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchaseDetail findDevicePurchaseDetailByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：找到所有的供应商
	 * @author 徐文
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public List<CDeviceSupplier> getCDeviceSuppliers();
}