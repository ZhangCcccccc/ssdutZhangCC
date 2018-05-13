package net.xidlims.service.asset;

import java.util.List;

import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetAdjustRecord;
import net.xidlims.domain.AssetCabinet;
import net.xidlims.domain.IotSharePowerOpentime;
import net.xidlims.domain.AssetCabinetWarehouse;
import net.xidlims.domain.AssetCabinetWarehouseAccess;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveRecord;




public interface AssetCabinetWarehouseAccessService {
	
	
	/***********************************************************************************
	 * @功能：获取全部在用物资
	 * @author 郑昕茹
	 * @日期：2016-08-05
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccess> findAllAssetCabinetWarehouseAccesss(int currpage,int pageSize, AssetCabinetWarehouseAccess assetCabinetWarehouseAccess);
	
	/***********************************************************************************
	 * @功能：根据主键找到在用物资表中的物资记录
	 * @author 郑昕茹
	 * @日期：2016-08-07
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：保存在用物资记录
	 * @author 郑昕茹
	 * @日期：2016-08-08
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccess saveAssetCabinetWarehouseAccess(AssetCabinetWarehouseAccess assetCabinetWarehouseAccess);
	/***********************************************************************************
	 * @description：保存在用物资记录
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	public String findAssetCabinetByLabRoomId(String labRoomId);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(联动查询-根据cabinetId找到所有相关的格子)
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	public String findWarehouseByCabinetId(String cabinetId);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(根据主键找到药品柜)
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	public AssetCabinet findAssetCabinetByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(保存物资位置信息)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccessRecord saveLocationMessage(AssetCabinetWarehouseAccessRecord assetRecord);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(删除物资位置信息)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public boolean deleteLocationMessage(Integer id);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(判断要入库的在用物资与其位置信息的总数量是否匹配)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public boolean judgeQuantitiesMatch(AssetCabinetWarehouseAccess assetCabinetWarehouseAccess);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(根据主键找到位置信息)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(查询到所有新建的在用物资记录)
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccess> findAllNewAccessStock(int currpage,int pageSize);
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录(根据物资的id查询到所有库存调整记录)
	 * @author 郑昕茹
	 * @date：2016-08-17
	 * **********************************************************************************/
	public List<AssetAdjustRecord> findAllAdjustRecordsByAsset(int currpage,int pageSize, Asset asset);
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录(保存库存调整记录)
	 * @author 郑昕茹
	 * @date：2016-08-17
	 * **********************************************************************************/
	public AssetAdjustRecord saveAssetAdjustRecord(AssetAdjustRecord assetAdjustRecord);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-物资记录（根据物资查找药品品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-17
	 * **********************************************************************************/
	public List<AssetReceiveRecord> findAllAssetReceiveRecordsByAsset(int currpage,int pageSize, Asset asset);
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到所有入库的药品）
	 * @author 郑昕茹
	 * @date：2016-08-30
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccess> findAllAccesss(int currpage,int pageSize, AssetReceive assetReceive);
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资入库记录）
	 * @author 郑昕茹
	 * @date：2016-08-31
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccessRecord> findAllAccesssInSameAssetAndType(AssetCabinetWarehouseAccess access);
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资调整记录）
	 * @author 郑昕茹
	 * @date：2016-08-31
	 * **********************************************************************************/
	public List<AssetAdjustRecord> findAllAdjustRecordsInSameAssetAndType(AssetCabinetWarehouseAccess access,int currpage, int pageSize);

	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资调整记录）
	 * @author 郑昕茹
	 * @date：2016-08-31
	 * **********************************************************************************/
	public List<AssetReceiveRecord> findAllReceiveRecordsInSameAssetAndType(AssetCabinetWarehouseAccess access,int currpage, int pageSize);
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资调整记录）
	 * @author 郑昕茹
	 * @date：2016-08-31
	 * **********************************************************************************/
	public List<AssetCabinet> findAllAssetCabinets();
	
	/***********************************************************************************
	 * @description：药品溶液管理-显示自动开箱时间（找到所有）
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public List<IotSharePowerOpentime> findAllOpenTimes(IotSharePowerOpentime IotSharePowerOpentime);
	
	/***********************************************************************************
	 * @description：药品溶液管理-设置开箱时间 
	 * @author 郑昕茹
	 * @date：2016-09-066
	 * **********************************************************************************/
	public IotSharePowerOpentime saveIotSharePowerOpentime(IotSharePowerOpentime IotSharePowerOpentime);
	
	/***********************************************************************************
	 * @description：药品溶液管理-根据主键找到开箱时间就
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public IotSharePowerOpentime findIotSharePowerOpentimeByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @description：药品溶液管理-删除开箱记录
	 * @author 郑昕茹
	 * @date：2016-09-07
	 * **********************************************************************************/
	public boolean deleteIotSharePowerOpentime(Integer id);
	
	/***********************************************************************************
	 * @description：药品溶液管理-根据学期找到还需要增加的周几的记录
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public List<IotSharePowerOpentime> findOpenTimesByTermId(Integer termId);
}