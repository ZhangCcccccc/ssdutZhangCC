package net.xidlims.service.asset;

import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.AssetAdjustRecordDAO;
import net.xidlims.dao.AssetCabinetDAO;
import net.xidlims.dao.IotSharePowerOpentimeDAO;
import net.xidlims.dao.IotSharePowerOpentimeDAO;
import net.xidlims.dao.AssetCabinetWarehouseAccessDAO;
import net.xidlims.dao.AssetCabinetWarehouseAccessRecordDAO;
import net.xidlims.dao.AssetCabinetWarehouseDAO;
import net.xidlims.dao.AssetDAO;
import net.xidlims.dao.AssetReceiveRecordDAO;
import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetAdjustRecord;
import net.xidlims.domain.AssetCabinet;
import net.xidlims.domain.IotSharePowerOpentime;
import net.xidlims.domain.AssetCabinetWarehouse;
import net.xidlims.domain.AssetCabinetWarehouseAccess;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveRecord;
import net.xidlims.domain.Authority;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.lab.LabRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("AssetCabinetWarehouseAccessService")
public class AssetCabinetWarehouseAccessServiceImpl implements  AssetCabinetWarehouseAccessService {
	
	@Autowired ShareService shareService;
	@Autowired AssetDAO assetDAO;
	@Autowired AssetCabinetWarehouseAccessDAO assetCabinetWarehouseAccessDAO;
	@Autowired LabRoomService labRoomService;
	@Autowired AssetCabinetDAO assetCabinetDAO;
	@Autowired AssetCabinetWarehouseAccessRecordDAO assetCabinetWarehouseAccessRecordDAO;
	@Autowired AssetAdjustRecordDAO assetAdjustRecordDAO;
	@Autowired AssetReceiveRecordDAO assetReceiveRecordDAO;
	@Autowired AssetCabinetWarehouseDAO assetCabinetWarehouseDAO;
	@Autowired IotSharePowerOpentimeDAO iotSharePowerOpentimeDAO; 
	/***********************************************************************************
	 * @功能：药品溶液管理-药品入库(获取全部在用物资)
	 * @author 郑昕茹
	 * @日期：2016-08-05
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccess> findAllAssetCabinetWarehouseAccesss(int currpage,int pageSize, AssetCabinetWarehouseAccess assetCabinetWarehouseAccess){
		String sql = "select a from AssetCabinetWarehouseAccess a where 1=1";
//String sql = "select a, count(cabinetQuantity) from AssetCabinetWarehouseAccess where 1=1 and asset.ifPublic = 1 group by asset.chName, asset.specifications";
		
		/*if(assetCabinetWarehouseAccess != null &&assetCabinetWarehouseAccess.getAsset() != null && assetCabinetWarehouseAccess.getAsset().getId()!=null&& assetCabinetWarehouseAccess.getAsset().getId().equals("")){
			sql+=" and asset.id like '%" + assetCabinetWarehouseAccess.getAsset().getId().toString()+"%'";
		}
		if(assetCabinetWarehouseAccess != null && assetCabinetWarehouseAccess.getLabRoom()!=null && assetCabinetWarehouseAccess.getLabRoom().getLabRoomName()!=null&& assetCabinetWarehouseAccess.getLabRoom().getLabRoomName().equals("")){
			sql+=" and labRoom.labRoomName like '%" + assetCabinetWarehouseAccess.getLabRoom().getLabRoomName()+"%'";
		}*/
		/*if(assetCabinetWarehouseAccess != null &&assetCabinetWarehouseAccess.getAsset() != null && assetCabinetWarehouseAccess.getAsset().getId()!=null&& assetCabinetWarehouseAccess.getAsset().getId().equals("")){
			sql+=" and asset.id like '%" + assetCabinetWarehouseAccess.getAsset().getId().toString()+"%'";
		}
		if(assetCabinetWarehouseAccess != null && assetCabinetWarehouseAccess.getLabRoom()!=null && assetCabinetWarehouseAccess.getLabRoom().getLabRoomName()!=null&& assetCabinetWarehouseAccess.getLabRoom().getLabRoomName().equals("")){
			sql+=" and labRoom.labRoomName like '%" + assetCabinetWarehouseAccess.getLabRoom().getLabRoomName()+"%'";
		}*/
		return assetCabinetWarehouseAccessDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	/***********************************************************************************
	 * @功能：药品溶液管理-药品入库(根据主键找到在用物资表中的物资记录)
	 * @author 郑昕茹
	 * @日期：2016-08-07
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessByPrimaryKey(Integer id){
		return assetCabinetWarehouseAccessDAO.findAssetCabinetWarehouseAccessByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @功能：药品溶液管理-药品入库(保存在用物资记录)
	 * @author 郑昕茹
	 * @日期：2016-08-08
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccess saveAssetCabinetWarehouseAccess(AssetCabinetWarehouseAccess assetCabinetWarehouseAccess){
		return assetCabinetWarehouseAccessDAO.store(assetCabinetWarehouseAccess);
	}
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(联动查询-根据labRoomId找到所有相关的药品柜)
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	public String findAssetCabinetByLabRoomId(String labRoomId){
		//通过主键找到实验室
		LabRoom labRoom = labRoomService.findLabRoomByPrimaryKey(Integer.parseInt(labRoomId));
		//找到该实验室下的所有柜子
		Set<AssetCabinet> assetCabinets = labRoom.getAssetCabinets();
		String assetCabinet = "<option value=\"\">请选择</option>";
		//遍历所有的柜子
		Iterator<AssetCabinet> it = assetCabinets.iterator();
		while(it.hasNext()){
			AssetCabinet a = it.next();
			assetCabinet+="<option value=\""+a.getId()+"\">"+a.getCabinetName()+"</option>";
		}
		String assetCabinetValue = shareService.htmlEncode(assetCabinet);
		return assetCabinetValue;
		
	}
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(联动查询-根据cabinetId找到所有相关的格子)
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	public String findWarehouseByCabinetId(String cabinetId){
		//通过主键找到药品柜
		AssetCabinet assetCabinet = assetCabinetDAO.findAssetCabinetByPrimaryKey(Integer.parseInt(cabinetId));
		//找到该药品柜的所有的格子
		Set<AssetCabinetWarehouse> assetCabinetWarehouses = assetCabinet.getAssetCabinetWarehouses();
		String warehouse = "<option value=\"\">请选择</option>";
		//遍历所有的柜子
		Iterator<AssetCabinetWarehouse> it = assetCabinetWarehouses.iterator();
		while(it.hasNext()){
			AssetCabinetWarehouse a = it.next();
			warehouse+="<option value=\""+a.getId()+"\">"+a.getWarehouseName()+"</option>";
		}
		String warehouseValue = shareService.htmlEncode(warehouse);
		return warehouseValue;
		
	}
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(根据主键找到药品柜)
	 * @author 郑昕茹
	 * @date：2016-08-12
	 * **********************************************************************************/
	public AssetCabinet findAssetCabinetByPrimaryKey(Integer id){
		return assetCabinetDAO.findAssetCabinetByPrimaryKey(id);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(保存物资位置信息)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccessRecord saveLocationMessage(AssetCabinetWarehouseAccessRecord assetRecord){
		return assetCabinetWarehouseAccessRecordDAO.store(assetRecord);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(删除物资位置信息)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public boolean deleteLocationMessage(Integer id){
		AssetCabinetWarehouseAccessRecord assetCabinetWarehouseAccessRecord = assetCabinetWarehouseAccessRecordDAO.findAssetCabinetWarehouseAccessRecordByPrimaryKey(id);
		if(assetCabinetWarehouseAccessRecord != null){
			assetCabinetWarehouseAccessRecordDAO.remove(assetCabinetWarehouseAccessRecord);
			assetCabinetWarehouseAccessRecordDAO.flush();
			return true;
		}
		else return false;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(判断要入库的在用物资与其位置信息的总数量是否匹配)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public boolean judgeQuantitiesMatch(AssetCabinetWarehouseAccess assetCabinetWarehouseAccess){
		//找到该要入库的在用物资对应的所有位置信息
		Set<AssetCabinetWarehouseAccessRecord> assetRecords = assetCabinetWarehouseAccess.getAssetCabinetWarehouseAccessRecords();
		//存储位置信中的总数量
		Float totalQuantity = new Float(0); 
		//迭代器遍历set
		Iterator<AssetCabinetWarehouseAccessRecord> it = assetRecords.iterator();
		while(it.hasNext()){
			AssetCabinetWarehouseAccessRecord assetRecord = it.next();
			totalQuantity += assetRecord.getCabinetQuantity().floatValue();
		}
		//判断要入库的在用物资与其位置信息的总数量是否匹配
		if(totalQuantity.equals(assetCabinetWarehouseAccess.getCabinetQuantity().floatValue()))return true;
		else return false;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(根据主键找到位置信息)
	 * @author 郑昕茹
	 * @date：2016-08-13
	 * **********************************************************************************/
	public AssetCabinetWarehouseAccessRecord findAssetCabinetWarehouseAccessRecordByPrimaryKey(Integer id){
		return assetCabinetWarehouseAccessRecordDAO.findAssetCabinetWarehouseAccessRecordByPrimaryKey(id);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品入库(查询到所有新建的在用物资记录)
	 * @author 郑昕茹
	 * @date：2016-08-15
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccess> findAllNewAccessStock(int currpage,int pageSize){ 
		String sql = "select a from AssetCabinetWarehouseAccess a where 1=1 and flag = 0"; 
		return assetCabinetWarehouseAccessDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录(根据物资的id查询到所有库存调整记录)
	 * @author 郑昕茹
	 * @date：2016-08-17
	 * **********************************************************************************/
	public List<AssetAdjustRecord> findAllAdjustRecordsByAsset(int currpage,int pageSize, Asset asset){ 
		String sql ="select a from AssetAdjustRecord a where 1=1 and asset.id = "+asset.getId().toString();
		return assetAdjustRecordDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录(保存库存调整记录)
	 * @author 郑昕茹
	 * @date：2016-08-17
	 * **********************************************************************************/
	public AssetAdjustRecord saveAssetAdjustRecord(AssetAdjustRecord assetAdjustRecord){
		return assetAdjustRecordDAO.store(assetAdjustRecord);
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-物资记录（根据物资查找药品品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-17
	 * **********************************************************************************/
	public List<AssetReceiveRecord> findAllAssetReceiveRecordsByAsset(int currpage,int pageSize, Asset asset){
		User user = shareService.getUser();
		Set<Authority> authorities = user.getAuthorities();
		boolean isAdmin=false;//是否为管理员
		if(authorities != null){
			for(Authority authority:authorities){
				if(authority.getId()==11){
					isAdmin =true; 
					break;
				}
			}
		} 
		String sql = "select a from AssetReceiveRecord a where 1=1 and asset.id = "+asset.getId().toString(); 
		if(isAdmin != true){
			sql += " and assetReceive.user.username = '"+user.getUsername()+"'";
		}
		List<AssetReceiveRecord> listAssetReceiveReords = assetReceiveRecordDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
		return listAssetReceiveReords;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到所有入库的药品）
	 * @author 郑昕茹
	 * @date：2016-08-30
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccess> findAllAccesss(int currpage,int pageSize, AssetReceive assetReceive){
		String sql = "select a from AssetCabinetWarehouseAccess a where 1=1 and status=1";
		if(assetReceive != null && assetReceive.getType()!= null && !assetReceive.getType().equals("")){
			sql +=" and type ="+assetReceive.getType(); 
		}
		if(assetReceive != null && assetReceive.getOperationItem() != null && assetReceive.getOperationItem().getId()!=null && !assetReceive.getOperationItem().getId().equals("")){
			sql += " and a.operationItem.id="+assetReceive.getOperationItem().getId();
		}
		if(assetReceive != null && assetReceive.getId() != null && !assetReceive.getId().equals("")){
			sql += " and a.asset.id="+assetReceive.getId();
		}
		sql += " group by asset.id,operationItem";
		return assetCabinetWarehouseAccessDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资入库记录Record）
	 * @author 郑昕茹
	 * @date：2016-08-30
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccessRecord> findAllAccesssInSameAssetAndType(AssetCabinetWarehouseAccess access){
		String sql = "";
		//与实验项目无关的物资
		if(access.getType()  == 0){
			sql = "select a from AssetCabinetWarehouseAccessRecord a where 1=1 and status=1 and asset.id ="+access.getAsset().getId()+" and assetCabinetWarehouseAccess.type = 0";
		}
		else{ 
			sql = "select a from AssetCabinetWarehouseAccessRecord a where 1=1 and status = 1 and asset.id="+access.getAsset().getId().toString()
					+" and assetCabinetWarehouseAccess.type != 0 and assetCabinetWarehouseAccess.operationItem.id="+access.getOperationItem().getId().toString(); 
		}
		return assetCabinetWarehouseAccessRecordDAO.executeQuery(sql);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资调整记录）
	 * @author 郑昕茹
	 * @date：2016-08-31
	 * **********************************************************************************/
	public List<AssetAdjustRecord> findAllAdjustRecordsInSameAssetAndType(AssetCabinetWarehouseAccess access,int currpage, int pageSize){
		String sql = "";
		//与实验项目无关的物资
		if(access.getType() == 0){
			sql = "select a from AssetAdjustRecord a where 1=1 and assetCabinetWarehouseAccessRecord.status=1 and asset.id ="+access.getAsset().getId()+" and assetCabinetWarehouseAccessRecord.assetCabinetWarehouseAccess.type = 0";
		}
		else{ 
			sql = "select a from AssetAdjustRecord a where 1=1 and assetCabinetWarehouseAccessRecord.status = 1 and asset.id="+access.getAsset().getId().toString()
					+" and assetCabinetWarehouseAccessRecord.assetCabinetWarehouseAccess.type != 0 and assetCabinetWarehouseAccessRecord.assetCabinetWarehouseAccess.operationItem.id="+access.getOperationItem().getId().toString(); 
		}
		return assetAdjustRecordDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到属于相同类型，相同物资的物资调整记录）
	 * @author 郑昕茹
	 * @date：2016-08-31
	 * **********************************************************************************/
	public List<AssetReceiveRecord> findAllReceiveRecordsInSameAssetAndType(AssetCabinetWarehouseAccess access,int currpage, int pageSize){
		String sql = "";
		//与实验项目无关的物资
		if(access.getType()  == 0){
			sql = "select a from AssetReceiveRecord a where 1=1 and result=1 and asset.id ="+access.getAsset().getId()+" and assetReceive.type = 0";
		}
		else{ 
			sql = "select a from AssetReceiveRecord a where 1=1 and result=1 and asset.id="+access.getAsset().getId().toString()
					+" and assetReceive.type != 0 and assetReceive.operationItem.id="+access.getOperationItem().getId().toString(); 
		}
		User user = shareService.getUser();
		Set<Authority> authorities = user.getAuthorities();
		boolean isAdmin=false;//是否为管理员
		if(authorities != null){
			for(Authority authority:authorities){
				if(authority.getId()==11){
					isAdmin =true; 
					break;
				}
			}
		} 
		if(isAdmin != true){
			sql += " and assetReceive.user.username = '"+user.getUsername()+"'";
		}
		List<AssetReceiveRecord> listAssetReceiveReords = assetReceiveRecordDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
		return listAssetReceiveReords;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-物资记录（找到所有）
	 * @author 郑昕茹
	 * @date：2016-09-04
	 * **********************************************************************************/
	public List<AssetCabinet> findAllAssetCabinets(){
		String sql = "select a from AssetCabinet a where 1=1 ";
		return assetCabinetDAO.executeQuery(sql, 0, -1);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-显示自动开箱时间（找到所有）
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public List<IotSharePowerOpentime> findAllOpenTimes(IotSharePowerOpentime iotSharePowerOpentime){
		String sql = "select a from IotSharePowerOpentime a where 1=1 ";
		if(iotSharePowerOpentime != null && iotSharePowerOpentime.getSchoolTerm() != null && !iotSharePowerOpentime.getSchoolTerm().equals("")){
			sql+=" and schoolTerm.id ="+iotSharePowerOpentime.getSchoolTerm().getId();
		}
		return iotSharePowerOpentimeDAO.executeQuery(sql, 0, -1);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-设置开箱时间 
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public IotSharePowerOpentime saveIotSharePowerOpentime(IotSharePowerOpentime iotSharePowerOpentime){
		return iotSharePowerOpentimeDAO.store(iotSharePowerOpentime);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-根据主键找到开箱时间就
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public IotSharePowerOpentime findIotSharePowerOpentimeByPrimaryKey(Integer id){
		return iotSharePowerOpentimeDAO.findIotSharePowerOpentimeByPrimaryKey(id);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-删除开箱记录
	 * @author 郑昕茹
	 * @date：2016-09-07
	 * **********************************************************************************/
	public boolean deleteIotSharePowerOpentime(Integer id){
		IotSharePowerOpentime IotSharePowerOpentime = iotSharePowerOpentimeDAO.findIotSharePowerOpentimeByPrimaryKey(id);
		if(IotSharePowerOpentime != null){
			iotSharePowerOpentimeDAO.remove(IotSharePowerOpentime);
			return true;
		}
		return false;
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-根据学期zha
	 * @author 郑昕茹
	 * @date：2016-09-06
	 * **********************************************************************************/
	public List<IotSharePowerOpentime> findOpenTimesByTermId(Integer termId){
		String sql = "select a from IotSharePowerOpentime a where 1=1 ";
		if(termId != null){
			sql += " and schoolTerm.id = "+termId.toString();
		}
		return iotSharePowerOpentimeDAO.executeQuery(sql, 0, -1);
	}
}