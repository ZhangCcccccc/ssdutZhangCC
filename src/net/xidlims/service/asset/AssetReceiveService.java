package net.xidlims.service.asset;

import java.util.List;
import java.util.Set;

import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveAllocation;
import net.xidlims.domain.AssetReceiveAudit;
import net.xidlims.domain.AssetReceiveRecord;




public interface AssetReceiveService {
	
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取全部药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public List<AssetReceive> findAllAssetReceives(int currpage,int pageSize, AssetReceive assetReceive,Integer status);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（通过主键找到药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public AssetReceive findAssetReceiveByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public AssetReceive saveAssetReceive(AssetReceive assetReceive);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（根据主键删除药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public boolean deleteAssetReceive(Integer id);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-申领审核（获取全部申领审核记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public List<AssetReceiveAudit> findAllAssetReceiveAudits(int currpage,int pageSize, AssetReceiveAudit assetReceiveAudit,Integer status);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存药品申领记录）
	 * @author 徐文
	 * @日期：2016-08-12
	 * **********************************************************************************/
	public AssetReceiveRecord saveAssetReceiveRecord(AssetReceiveRecord assetReceiveRecord);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存药品申领审核记录）
	 * @author 徐文
	 * @日期：2016-08-12
	 * **********************************************************************************/
	public AssetReceiveAudit saveAuditAssetReceive(AssetReceiveAudit assetReceiveAudit);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品申领（通过主键找到药品申领记录信息）
	 * @author 郑昕茹
	 * @date：2016-08-16
	 * **********************************************************************************/
	public AssetReceiveRecord findAssetReceiveRecordByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存申领物品仓库分配信息）
	 * @author 郑昕茹
	 * @日期：2016-08-16
	 * **********************************************************************************/
	public AssetReceiveAllocation saveAssetReceiveAllocation(AssetReceiveAllocation assetReceiveAllocation);
	
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（判断药品申领单是否全部分配仓库）
	 * @author 郑昕茹
	 * @日期：2016-08-16
	 * **********************************************************************************/
	public void judgeAllocationStatus(AssetReceive assetReceive);
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛生成从0000开始到9999的数｝
	 * @author 郑昕茹
	 * @date 2016-08-18
	 * **********************************************************************************/
	public String getNumber(String lastNo);
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛找到编号最大的申领编号的后四位｝
	 * @author 郑昕茹
	 * @date 2016-08-18
	 * **********************************************************************************/
	public String findReceiveNo();
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取当前登录人能申领的私用物资的数量）
	 * @author 郑昕茹
	 * @日期：2016-08-18
	 * **********************************************************************************/
	public Double findPrivateQuantityByAssetWithUser(Asset asset);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取公用的物资数量）
	 * @author 郑昕茹
	 * @日期：2016-08-18
	 * **********************************************************************************/
	public Double findpublicQuantityByAsset(Asset asset);
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品申领（判断申领成功的物品是否已领取，领取则减少物资数量）
	 * @author 郑昕茹
	 * @date：2016-08-26
	 * **********************************************************************************/
	public void judgeAllocationWarehouseOpenAndReduce();
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取可以申领的物资）
	 * @author 郑昕茹
	 * @日期：2016-08-30
	 * **********************************************************************************/
	public Set<Asset> findAssetsCanReceive(AssetReceive assetReceive);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（根据物资查找其可被申领的数量）
	 * @author 郑昕茹
	 * @日期：2016-08-30
	 * **********************************************************************************/
	public Double findReceiveNumByAsset(Asset asset,AssetReceive assetReceive);
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（根据物资查找其可被申领物资记录）
	 * @author 郑昕茹
	 * @日期：2016-08-30
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccessRecord> findAssetCabinetWarehouseAccessRecordsByAsset(Asset asset,AssetReceive assetReceive);
}