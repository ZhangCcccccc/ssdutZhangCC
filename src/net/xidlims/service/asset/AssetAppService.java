package net.xidlims.service.asset;

import java.util.List;

import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppAudit;
import net.xidlims.domain.AssetAppDate;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.User;





public interface AssetAppService {
	
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛显示所有的药品审核信息｝
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	public List<AssetApp> findAssetApps(Integer currpage,Integer pageSize,Integer assetAuditStatus,AssetApp assetApp, User user,String startDate, String endDate);
	
	 /***********************************************************************************
     * @功能：保存申购信息
     * @author 徐文
     * @日期：2016-08-08
     * **********************************************************************************/
    public AssetApp saveAssetApp(AssetApp assetApp);
   
    /***********************************************************************************
	 * @description 药品溶液管理-药品申购｛通过主键找到药品申购信息｝
	 * @author 郑昕茹
	 * @date 2016-08-08
	 * **********************************************************************************/
	public AssetApp findAssetAppByPrimaryKey(Integer id);
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛删除药品申购信息｝
	 * @author 郑昕茹
	 * @date 2016-08-08
	 * **********************************************************************************/
	public boolean deleteAssetApp(AssetApp assetApp);
	
	/************************************************************************************
     * @功能：保存申购记录信息
     * @author 徐文
     * @日期：2016-08-09
     * **********************************************************************************/
    public AssetAppRecord saveAssetAppRecord(AssetAppRecord assetAppRecord);
    
    /***********************************************************************************
     * @功能：保存申购审核信息
     * @author 徐文
     * @日期：2016-08-10
     * **********************************************************************************/
    public AssetAppAudit saveAssetAppAudit(AssetAppAudit assetAppAudit);
    
    /***********************************************************************************
  	 * @description 药品溶液管理-药品申购｛通过主键找到药品申购记录信息｝
  	 * @author 郑昕茹
  	 * @date 2016-08-15
  	 * **********************************************************************************/
  	public AssetAppRecord findAssetAppRecordByPrimaryKey(Integer id);
  	
  	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛显示所有通过审核，需要入库的药品审核信息｝
	 * @author 郑昕茹
	 * @date 2016-08-15
	 * **********************************************************************************/
	public List<AssetApp> findAssetAppsNeedStock(Integer currpage,Integer pageSize,AssetApp assetApp, Integer stockStatus);
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛生成从0000开始到9999的数｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	public String getNumber(String lastNo);
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛找到编号最大的申购编号的后四位｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	public String findAppNo();
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛保存设置的提交时间｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
    public AssetAppDate saveAssetAppDate(AssetAppDate assetAppDate);
    
    /***********************************************************************************
	 * @description 药品溶液管理-药品申购｛查找设置的提交时间｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
    public List<AssetAppDate> findAssetAppDate( );
    
    /***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛找到当前页面所有的申购人｝
   	 * @author 郑昕茹
   	 * @date 2016-08-20
   	 * **********************************************************************************/
   	public List<AssetApp> findAssetAppsGroupByUsers(Integer currpage,Integer pageSize,Integer assetAuditStatus,AssetApp assetApp, User user,String startDate,String endDate);
   	/***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛通过实验中心找到其下的实验室的所有实验大纲｝
   	 * @author 郑昕茹
   	 * @date 2016-08-30
   	 * **********************************************************************************/
   	public List<OperationItem> findOperationOutlineByLabCenter(Integer labCenterId);
   	
   	/***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛通过主键找到实验大纲｝
   	 * @author 郑昕茹
   	 * @date 2016-08-30
   	 * **********************************************************************************/
   	public OperationOutline findOperationOutlineByPrimaryKey(Integer id);
   	
   	/***********************************************************************************
  	 * @description 药品溶液管理-药品申购｛删除药品申购信息｝
  	 * @author 郑昕茹
  	 * @date 2016-10-11
  	 * **********************************************************************************/
  	public boolean deleteAssetAppRecord(Integer id);
}