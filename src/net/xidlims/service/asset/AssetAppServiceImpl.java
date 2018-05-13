package net.xidlims.service.asset;

import java.util.List;

import jxl.write.DateTime;

import net.xidlims.dao.AssetAppAuditDAO;
import net.xidlims.dao.AssetAppDAO;
import net.xidlims.dao.AssetAppDateDAO;
import net.xidlims.dao.AssetAppRecordDAO;
import net.xidlims.dao.AssetDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.OperationOutlineDAO;
import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppAudit;
import net.xidlims.domain.AssetAppDate;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.OperationItem;
import net.xidlims.domain.OperationOutline;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("AssetAppService")
public class AssetAppServiceImpl implements  AssetAppService {
	
	@Autowired ShareService shareService;
	@Autowired AssetDAO assetDAO;
	@Autowired AssetAppDAO assetAppDAO;
	@Autowired AssetAppRecordDAO assetAppRecordDAO;
	@Autowired AssetAppAuditDAO assetAppAuditDAO;
	@Autowired AssetAppDateDAO assetAppDateDAO;
	@Autowired OperationItemDAO operationItemDAO;
	@Autowired OperationOutlineDAO operationOutlineDAO;
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛显示所有的药品审核信息｝
	 * @author 徐文
	 * @date 2016-08-08
	 * **********************************************************************************/
	public List<AssetApp> findAssetApps(Integer currpage,Integer pageSize,Integer assetAuditStatus,AssetApp assetApp, User user,String startDate,String endDate){
		String sql="select a from AssetApp a where 1=1";//获取所有的设备申购信息
		if(user != null && user.getUsername() != null && !user.getUsername().equals("")){
			sql +=  " and user.username ='" + user.getUsername()+"'";
		}
		//筛选
		if (assetAuditStatus!=9) {
			sql+="and a.assetAuditStatus like '"+assetAuditStatus+"' ";
		}
		//查询
		if(assetApp != null && assetApp.getAppNo() != null && !assetApp.getAppNo().equals("")){
			sql+=" and appNo like '%"+assetApp.getAppNo()+"%'"; 
		}
		if(startDate != null && endDate != null && !startDate.equals("")&&!endDate.equals("")){
			sql += " and appDate between '"+ startDate + "' and '"+endDate+"'";
		}
		if(assetApp != null && assetApp.getUser() != null && assetApp.getUser().getCname() != null && !assetApp.getUser().getCname().equals("")){
			sql +=  " and user.cname ='" + assetApp.getUser().getCname()+"'";
		}
		List<AssetApp> listAssetApps = assetAppDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return listAssetApps;
	}
	
	/***********************************************************************************
     * @功能：保存申购信息
     * @author 徐文
     * @日期：2016-08-08
     * **********************************************************************************/
    public AssetApp saveAssetApp(AssetApp assetApp){
        return assetAppDAO.store(assetApp);
    }
    /***********************************************************************************
	 * @description 药品溶液管理-药品申购｛通过主键找到药品申购信息｝
	 * @author 郑昕茹
	 * @date 2016-08-08
	 * **********************************************************************************/
	public AssetApp findAssetAppByPrimaryKey(Integer id){
		return assetAppDAO.findAssetAppByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛通过主键删除药品申购信息｝
	 * @author 郑昕茹
	 * @date 2016-08-08
	 * **********************************************************************************/
	public boolean deleteAssetApp(AssetApp assetApp){
		if(assetApp != null){
			assetAppDAO.remove(assetApp);
			assetAppDAO.flush();
		} 
		return false;
	}
	/************************************************************************************
     * @功能：保存申购记录信息
     * @author 徐文
     * @日期：2016-08-09
     * **********************************************************************************/
	@Transactional
    public AssetAppRecord saveAssetAppRecord(AssetAppRecord assetAppRecord){
    	return assetAppRecordDAO.store(assetAppRecord);
    }
	/***********************************************************************************
     * @功能：保存申购审核信息
     * @author 徐文
     * @日期：2016-08-10
     * **********************************************************************************/
    public AssetAppAudit saveAssetAppAudit(AssetAppAudit assetAppAudit){
    	return assetAppAuditDAO.store(assetAppAudit);
    }
    
    /***********************************************************************************
  	 * @description 药品溶液管理-药品申购｛通过主键找到药品申购记录信息｝
  	 * @author 郑昕茹
  	 * @date 2016-08-15
  	 * **********************************************************************************/
  	public AssetAppRecord findAssetAppRecordByPrimaryKey(Integer id){
  		return assetAppRecordDAO.findAssetAppRecordByPrimaryKey(id);
  	}
  	
  	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛显示所有通过审核，需要入库的药品审核信息｝
	 * @author 郑昕茹
	 * @date 2016-08-15
	 * **********************************************************************************/
	public List<AssetApp> findAssetAppsNeedStock(Integer currpage,Integer pageSize,AssetApp assetApp, Integer stockStatus){
		String sql="select a from AssetApp a where 1=1 and assetAuditStatus = 1";//获取所有通过审核的设备申购信息
		//查询
		if(assetApp != null && assetApp.getAppNo() != null && !assetApp.getAppNo().equals("")){
			sql+=" and appNo like '%"+assetApp.getAppNo()+"%'"; 
		}
		//查询
		if(assetApp != null && assetApp.getAppNo() != null && !assetApp.getAppNo().equals("")){
				sql+=" and appNo like '%"+assetApp.getAppNo()+"%'"; 
		}
		if(stockStatus == 1 || stockStatus == 0){
			sql+=" and stockStatus like '%"+stockStatus.toString()+"%'"; 
		}
		List<AssetApp> listAssetApps = assetAppDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return listAssetApps;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛生成从0000开始到9999的数｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	public String getNumber(String lastNo){
		Integer i = Integer.parseInt(lastNo) + 1;
		int len = i.toString().length();
		String nowNo="";
		switch (len){
		case 1:
			nowNo="000"+i.toString();
		break;
		case 2:
			nowNo="00"+i.toString();
		break;
		case 3:
			nowNo="0"+i.toString();
		break;
		case 4:
			nowNo= i.toString();
		break;
		}
		return nowNo;
	}

	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛找到编号最大的申购编号的后四位｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
	public String findAppNo(){
		String sql = "select a from AssetApp a where 1=1 order by appNo";
		List<AssetApp> assetApps = assetAppDAO.executeQuery(sql);
		AssetApp assetApp=null;
		String no = "0000";
		if(assetApps.size()>0){
			assetApp = assetApps.get(assetApps.size()-1);
			no = assetApp.getAppNo().substring(assetApp.getAppNo().length()-4);
		}
		return no;
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申购｛保存设置的提交时间｝
	 * @author 郑昕茹
	 * @date 2016-08-17
	 * **********************************************************************************/
    public AssetAppDate saveAssetAppDate(AssetAppDate assetAppDate){
        return assetAppDateDAO.store(assetAppDate);
    }
    
    /***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛查找设置的提交时间｝
   	 * @author 郑昕茹
   	 * @date 2016-08-17
   	 * **********************************************************************************/
       public List<AssetAppDate> findAssetAppDate( ){
    	   String sql = "select a from AssetAppDate a where 1=1 ";
    	   return assetAppDateDAO.executeQuery(sql);
       }
       
       /***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛找到当前页面所有的申购人｝
   	 * @author 郑昕茹
   	 * @date 2016-08-20
   	 * **********************************************************************************/
   	public List<AssetApp> findAssetAppsGroupByUsers(Integer currpage,Integer pageSize,Integer assetAuditStatus,AssetApp assetApp, User user,String startDate,String endDate){
   		String sql="select a from AssetApp a where 1=1";//获取所有的设备申购信息
   		if(user != null && user.getUsername() != null && !user.getUsername().equals("")){
   			sql +=  " and user.username ='" + user.getUsername()+"'";
   		}
   		//筛选
   		if (assetAuditStatus!=9) {
   			sql+="and a.assetAuditStatus like '"+assetAuditStatus+"' ";
   		}
   		//查询
   		if(assetApp != null && assetApp.getAppNo() != null && !assetApp.getAppNo().equals("")){
   			sql+=" and appNo like '%"+assetApp.getAppNo()+"%'"; 
   		}
   		if(startDate != null && endDate != null && !startDate.equals("")&&!endDate.equals("")){
   			sql += " and appDate between '"+ startDate + "' and '"+endDate+"'";
   		}
   		if(assetApp != null && assetApp.getUser() != null && assetApp.getUser().getCname() != null && !assetApp.getUser().getCname().equals("")){
   			sql +=  " and user.cname ='" + assetApp.getUser().getCname()+"'";
   		}
   		sql += " group by user.username";
   		List<AssetApp> listAssetApps = assetAppDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
   		return listAssetApps;
   	}
    /***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛通过实验中心找到其下的实验室的所有实验大纲｝
   	 * @author 郑昕茹
   	 * @date 2016-08-30
   	 * **********************************************************************************/
   	public List<OperationItem> findOperationOutlineByLabCenter(Integer labCenterId){
   		String sql ="select oi from OperationItem oi where 1=1";
   		//sql += " and lr.labCenter.id ="+labCenterId.toString();
   		return operationItemDAO.executeQuery(sql);
   	}
   	
   	/***********************************************************************************
   	 * @description 药品溶液管理-药品申购｛通过主键找到实验大纲｝
   	 * @author 郑昕茹
   	 * @date 2016-08-30
   	 * **********************************************************************************/
   	public OperationOutline findOperationOutlineByPrimaryKey(Integer id){
   		return operationOutlineDAO.findOperationOutlineByPrimaryKey(id);
   	}
   	
    /***********************************************************************************
  	 * @description 药品溶液管理-药品申购｛删除药品申购信息｝
  	 * @author 郑昕茹
  	 * @date 2016-10-11
  	 * **********************************************************************************/
  	public boolean deleteAssetAppRecord(Integer id){
  		AssetAppRecord assetAppRecord = assetAppRecordDAO.findAssetAppRecordByPrimaryKey(id);
  		if(assetAppRecord != null){
  			assetAppRecordDAO.remove(assetAppRecord);
  			assetAppRecordDAO.flush();
  			return true;
  		}
  		return false;
  	}
}