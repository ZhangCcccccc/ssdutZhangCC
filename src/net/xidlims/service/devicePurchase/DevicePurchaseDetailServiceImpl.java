package net.xidlims.service.devicePurchase;

import java.util.List;

import net.xidlims.dao.CDeviceSupplierDAO;
import net.xidlims.dao.NDevicePurchaseDAO;
import net.xidlims.dao.NDevicePurchaseDetailDAO;
import net.xidlims.domain.CDeviceSupplier;
import net.xidlims.domain.NDevicePurchaseDetail;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DevicePurchaseDetailService")
public class DevicePurchaseDetailServiceImpl implements  DevicePurchaseDetailService {
	
	@Autowired ShareService shareService;
	@Autowired NDevicePurchaseDAO nDevicePurchaseDAO;
	@Autowired NDevicePurchaseDetailDAO nDevicePurchaseDetailDAO;
	@Autowired CDeviceSupplierDAO cDeviceSupplierDAO;
	
	/***********************************************************************************
	 * @功能：保存设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchaseDetail saveNDevicePurchaseDetail(NDevicePurchaseDetail nDevicePurchaseDetail){
		return nDevicePurchaseDetailDAO.store(nDevicePurchaseDetail);
	}
	
	/***********************************************************************************
	 * @功能：根据申购主键找到申购信息detail
	 * @author 徐文
	 * @日期：2016-07-22
	 * **********************************************************************************/
	public NDevicePurchaseDetail findDevicePurchaseDetailByPrimaryKey(Integer id){
		return nDevicePurchaseDetailDAO.findNDevicePurchaseDetailByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @功能：找到所有的供应商
	 * @author 徐文
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public List<CDeviceSupplier> getCDeviceSuppliers(){
		String sql="select c from  CDeviceSupplier c where 1=1 ";
		 List<CDeviceSupplier> cDeviceSuppliers= cDeviceSupplierDAO.executeQuery(sql,0,-1);
		 return cDeviceSuppliers;
	}
}