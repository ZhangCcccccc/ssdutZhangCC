package net.xidlims.service.asset;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.AssetAppRecordDAO;
import net.xidlims.dao.AssetCabinetWarehouseAccessDAO;
import net.xidlims.dao.AssetCabinetWarehouseAccessRecordDAO;
import net.xidlims.dao.AssetDAO;
import net.xidlims.dao.AssetReceiveAllocationDAO;
import net.xidlims.dao.AssetReceiveAuditDAO;
import net.xidlims.dao.AssetReceiveDAO;
import net.xidlims.dao.AssetReceiveRecordDAO;
import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetApp;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinetWarehouseAccess;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
import net.xidlims.domain.AssetOpenLog;
import net.xidlims.domain.AssetReceive;
import net.xidlims.domain.AssetReceiveAllocation;
import net.xidlims.domain.AssetReceiveAudit;
import net.xidlims.domain.AssetReceiveRecord;
import net.xidlims.domain.Authority;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("AssetReceiveService")
public class AssetReceiveServiceImpl implements  AssetReceiveService {
	
	@Autowired ShareService shareService;
	@Autowired AssetDAO assetDAO;
	@Autowired AssetReceiveDAO assetReceiveDAO;
	@Autowired AssetReceiveAuditDAO assetReceiveAuditDAO;
	@Autowired AssetReceiveRecordDAO assetReceiveRecordDAO;
	@Autowired AssetReceiveAllocationDAO assetReceiveAllocationDAO;
	@Autowired AssetAppRecordDAO assetAppRecordDAO;
	@Autowired AssetCabinetWarehouseAccessDAO assetCabinetWarehouseAccessDAO;
	@Autowired AssetCabinetWarehouseAccessRecordDAO assetCabinetWarehouseAccessRecordDAO;
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取全部药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public List<AssetReceive> findAllAssetReceives(int currpage,int pageSize, AssetReceive assetReceive,Integer status){
		String sql = "select a from AssetReceive a where 1=1";
		User user = shareService.getUser();
		Set<Authority> authorities = user.getAuthorities();
		boolean isAdmin = false;
		for(Authority a:authorities){
			if(a.getId() == 11){
				isAdmin = true;
				break;
			}
		}
		if(isAdmin == false){
			sql += " and user.username like '%"+ user.getUsername()+"%'";
		}
		if(assetReceive != null && assetReceive.getReceiveNo() != null && !assetReceive.getReceiveNo().equals("")){
			sql += " and receiveNo like '%" + assetReceive.getReceiveNo()+"%'";
		}
		if(assetReceive != null && assetReceive.getUser() != null && assetReceive.getUser().getUsername() != null && !assetReceive.getUser().getUsername().equals("")){
			sql += " and user.username like '%"+ assetReceive.getUser().getUsername()+"%'";
		}
		//审核通过/审核拒绝
		if(status.equals(1)|| status.equals(0)|| status.equals(2)|| status.equals(3)){
			sql += " and status ="+status.toString();
		}
		List<AssetReceive> listAssetReceives = assetReceiveDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
		return listAssetReceives;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（通过主键找到药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public AssetReceive findAssetReceiveByPrimaryKey(Integer id){
		return assetReceiveDAO.findAssetReceiveByPrimaryKey(id);
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public AssetReceive saveAssetReceive(AssetReceive assetReceive){
		return assetReceiveDAO.store(assetReceive);
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（根据主键删除药品申领记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public boolean deleteAssetReceive(Integer id){
		AssetReceive assetReceive = assetReceiveDAO.findAssetReceiveByPrimaryKey(id);
		if(assetReceive != null){
			assetReceiveDAO.remove(assetReceive);
			assetReceiveDAO.flush();
			return true;
		}
		return false;
	}
	/***********************************************************************************
	 * @功能：药品溶液管理-申领审核（获取全部申领审核记录）
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public List<AssetReceiveAudit> findAllAssetReceiveAudits(int currpage,int pageSize, AssetReceiveAudit assetReceiveAudit,Integer status){
		/*String sql = "select a from AssetReceiveAudit a where 1=1";
		if(assetReceiveAudit != null && assetReceiveAudit.getAssetReceive() != null && assetReceiveAudit.getAssetReceive().getAsset() != null && assetReceiveAudit.getAssetReceive().getAsset().getChName() != null && !assetReceiveAudit.getAssetReceive().getAsset().getChName().equals("")){
			sql += " and asset.chName like '%" + assetReceiveAudit.getAssetReceive().getAsset().getChName() +"%'";
		}
		//审核通过/审核拒绝
		if(status.equals(1)|| status.equals(0)){
			sql += " and status ="+status.toString();
		}	
		return assetReceiveAuditDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);*/
		return null;
		
	}
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存药品申领记录）
	 * @author 徐文
	 * @日期：2016-08-12
	 * **********************************************************************************/
	public AssetReceiveRecord saveAssetReceiveRecord(AssetReceiveRecord assetReceiveRecord){
		return assetReceiveRecordDAO.store(assetReceiveRecord);
	}
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存药品申领审核记录）
	 * @author 徐文
	 * @日期：2016-08-12
	 * **********************************************************************************/
	public AssetReceiveAudit saveAuditAssetReceive(AssetReceiveAudit assetReceiveAudit){
		return assetReceiveAuditDAO.store(assetReceiveAudit);
	}
	
	/***********************************************************************************
	 * @description：药品溶液管理-药品申领（通过主键找到药品申领记录信息）
	 * @author 郑昕茹
	 * @date：2016-08-16
	 * **********************************************************************************/
	public AssetReceiveRecord findAssetReceiveRecordByPrimaryKey(Integer id){
		return assetReceiveRecordDAO.findAssetReceiveRecordByPrimaryKey(id);
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（保存申领物品仓库分配信息）
	 * @author 郑昕茹
	 * @日期：2016-08-16
	 * **********************************************************************************/
	public AssetReceiveAllocation saveAssetReceiveAllocation(AssetReceiveAllocation assetReceiveAllocation){
		return assetReceiveAllocationDAO.store(assetReceiveAllocation);
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（判断药品申领单是否全部分配仓库）
	 * @author 郑昕茹
	 * @日期：2016-08-16
	 * **********************************************************************************/
	public void judgeAllocationStatus(AssetReceive assetReceive){
		//找到该申购对应的所有申领记录
		Set<AssetReceiveRecord> listAssetReceiveRecords=assetReceive.getAssetReceiveRecords();
		boolean flag = true;
		Iterator<AssetReceiveRecord> it = listAssetReceiveRecords.iterator();
		//遍历所有的申领物资记录，获取它们的单位,规格，判断它们对应的在用物资的条数
		while(it.hasNext()){
			AssetReceiveRecord assetReceiveRecord = it.next();
			//存在没有分配的记录
			if(assetReceiveRecord.getResult() == 1 && assetReceiveRecord.getAllocationStatus() == 0){
				flag = false;
				break;
			}
		}
		if(flag == true)assetReceive.setAllocationStatus(1);
		else assetReceive.setAllocationStatus(0);
		assetReceiveDAO.store(assetReceive);
	}
	
	/***********************************************************************************
	 * @description 药品溶液管理-药品申领｛生成从0000开始到9999的数｝
	 * @author 郑昕茹
	 * @date 2016-08-18
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
	 * @description 药品溶液管理-药品申领｛找到编号最大的申领编号的后四位｝
	 * @author 郑昕茹
	 * @date 2016-08-18
	 * **********************************************************************************/
	public String findReceiveNo(){
		String sql = "select a from AssetReceive a where 1=1 order by receiveNo";
		List<AssetReceive> assetReceives = assetReceiveDAO.executeQuery(sql);
		AssetReceive assetReceive=null;
		String no = "0000";
		if(assetReceives.size()>0){
			assetReceive = assetReceives.get(assetReceives.size()-1);
			no = assetReceive.getReceiveNo().substring(assetReceive.getReceiveNo().length()-4);
		}
		return no;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取当前登录人当前物资对应的通过审核的申购记录,私用）
	 * @author 郑昕茹
	 * @日期：2016-08-18
	 * **********************************************************************************/
	public Double findPrivateQuantityByAssetWithUser(Asset asset){
		User user = shareService.getUser();
		//通过审核，物资，用户,已入库
		Double num = new Double(0);
		/*
		 * 找到申购中符合条件的在用物资
		 */
		String sql = "select a from AssetAppRecord a where 1=1 and a.result = 1 and a.assetApp.user.username = '"+user.getUsername()
				+"' and a.asset.id = "+asset.getId().toString()
				+" and stockStatus = 1";	
		//找到符合条件的申购记录
		List<AssetAppRecord> listAssetAppRecords = assetAppRecordDAO.executeQuery(sql);
		//找到申购记录对应的入库记录
		for(AssetAppRecord assetAppRecord:listAssetAppRecords){
			Set<AssetCabinetWarehouseAccess> accesss = assetAppRecord.getAssetCabinetWarehouseAccesses();
			AssetCabinetWarehouseAccess access = null;
			Iterator<AssetCabinetWarehouseAccess> it = accesss.iterator();
			//只获得第一条，因为最多只有一条
			while(it.hasNext()){
				access = it.next();
				break;
			}
			num += access.getCabinetQuantity().doubleValue();
		}
		/*
		 * 找到直接入库的符合条件的在用物资数量，使用人为当前登录人，申购无关，私用,已入库
		 */
		String hql = "select a from AssetCabinetWarehouseAccess a where 1=1 and flag = 0 and ifPublic = 0 and status = 1 and a.user.username = '"+user.getUsername()+"'"
				+ " and asset.id = "+asset.getId().toString();
		List<AssetCabinetWarehouseAccess> assetAccesss = assetCabinetWarehouseAccessDAO.executeQuery(hql);
		for(AssetCabinetWarehouseAccess a:assetAccesss){
			num += a.getCabinetQuantity().doubleValue();
		}
		return num;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取公用的物资数量）
	 * @author 郑昕茹
	 * @日期：2016-08-18
	 * **********************************************************************************/
	public Double findpublicQuantityByAsset(Asset asset){
		Double num = new Double(0);
		/*
		 * 找到直接入库的符合条件的在用物资数量，公用,已入库
		 */
		String hql = "select a from AssetCabinetWarehouseAccess a where 1=1  and ifPublic = 1 and status = 1 and asset.id = "+asset.getId().toString();
		List<AssetCabinetWarehouseAccess> assetAccesss = assetCabinetWarehouseAccessDAO.executeQuery(hql);
		for(AssetCabinetWarehouseAccess a:assetAccesss){
			num += a.getCabinetQuantity().doubleValue();
		}
		return num;
	}
	/***********************************************************************************
	 * @description：药品溶液管理-药品申领（判断申领成功的物品是否已领取，领取则减少物资数量）
	 * @author 郑昕茹
	 * @date：2016-08-26
	 * **********************************************************************************/
	public void judgeAllocationWarehouseOpenAndReduce(){
		Set<AssetReceiveAllocation>  allocations = assetReceiveAllocationDAO.findAllAssetReceiveAllocations();
		//遍历物资分配表，判断是否已开箱领取
		if(allocations != null){
			for(AssetReceiveAllocation allocation:allocations){
				//未开箱
				if(allocation.getMem().equals("0")&&allocation.getAssetCabinetWarehouse()!=null && allocation.getAssetCabinetWarehouse().getAssetCabinet().getFlag() != null && allocation.getAssetCabinetWarehouse().getAssetCabinet().getFlag() == 1){
					//获得该药品柜的所有打开记录
					Set<AssetOpenLog> openLogs = allocation.getAssetCabinetWarehouse().getAssetOpenLogs();
					boolean flag = false;//表示箱子未打开
					if(openLogs != null){
						for(AssetOpenLog openLog:openLogs){
							openLog.getId();
							if(openLog.getCreateUser().equals(allocation.getAssetReceiveRecord().getAssetReceive().getUser().getUsername())
									&& openLog.getOpenDate().after(allocation.getAssetReceiveRecord().getAssetReceive().getStartData())
									&& openLog.getOpenDate().before(allocation.getAssetReceiveRecord().getAssetReceive().getEndDate())
									&& openLog.getStatus() == 1){
								flag =true;
								break;
							}
						}
					}
					//若开箱成功，减少药品柜对应物品的数量
					if(flag == true){
						//找到该药品柜中的所有物资记录
						AssetCabinetWarehouseAccessRecord assetRecord = allocation.getAssetCabinetWarehouseAccessRecord();
						int spec=1;
						if(allocation.getAsset().getSpecifications() != null){
							spec = Integer.parseInt(allocation.getAsset().getSpecifications().replaceAll("[^0-9]", ""));
						}
						Double reduceQuantity = allocation.getQuantity().doubleValue()/spec;
						assetRecord.setCabinetQuantity(new BigDecimal(assetRecord.getCabinetQuantity().doubleValue()-reduceQuantity));
						assetCabinetWarehouseAccessRecordDAO.store(assetRecord);
						allocation.setMem("1");
						assetReceiveAllocationDAO.store(allocation);
					}
				}
				//没有药品柜的物资超过申领时间即减少
				if(allocation.getMem().equals("0")&&allocation.getAssetCabinetWarehouse()==null){
					if(Calendar.getInstance().after(allocation.getAssetReceiveRecord().getAssetReceive().getEndDate())){
						int spec=1;
						if(allocation.getAsset().getSpecifications() != null){
							spec = Integer.parseInt(allocation.getAsset().getSpecifications().replaceAll("[^0-9]", ""));
						}
						AssetCabinetWarehouseAccessRecord assetRecord = allocation.getAssetCabinetWarehouseAccessRecord();
						Double reduceQuantity = allocation.getQuantity().doubleValue()/spec;
						assetRecord.setCabinetQuantity(new BigDecimal(assetRecord.getCabinetQuantity().doubleValue()-reduceQuantity));
						assetCabinetWarehouseAccessRecordDAO.store(assetRecord);
						allocation.setMem("1");
						assetReceiveAllocationDAO.store(allocation);
					}
				}
				if(allocation.getMem().equals("0")&&allocation.getAssetCabinetWarehouse()!=null && allocation.getAssetCabinetWarehouse().getAssetCabinet().getFlag() != null && allocation.getAssetCabinetWarehouse().getAssetCabinet().getFlag() == 0){
					if(Calendar.getInstance().after(allocation.getAssetReceiveRecord().getAssetReceive().getEndDate())){
						int spec=1;
						if(allocation.getAsset().getSpecifications() != null){
							spec = Integer.parseInt(allocation.getAsset().getSpecifications().replaceAll("[^0-9]", ""));
						}
						AssetCabinetWarehouseAccessRecord assetRecord = allocation.getAssetCabinetWarehouseAccessRecord();
						Double reduceQuantity = allocation.getQuantity().doubleValue()/spec;
						assetRecord.setCabinetQuantity(new BigDecimal(assetRecord.getCabinetQuantity().doubleValue()-reduceQuantity));
						assetCabinetWarehouseAccessRecordDAO.store(assetRecord);
						allocation.setMem("1");
						assetReceiveAllocationDAO.store(allocation);
					}
				}
			}
		}
	}
	
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（获取可以申领的物资）
	 * @author 郑昕茹
	 * @日期：2016-08-30
	 * **********************************************************************************/
	public Set<Asset> findAssetsCanReceive(AssetReceive assetReceive){
		Set<Asset> assets = new HashSet<Asset>();
		//非实验项目申领(申领公用物品）
		if(assetReceive.getType() == 0){
			//申购无关,已入库
			String hql = "select a from AssetCabinetWarehouseAccess a where 1=1  and type=0 and status = 1";
			List<AssetCabinetWarehouseAccess> assetAccesss = assetCabinetWarehouseAccessDAO.executeQuery(hql);
			if(assetAccesss != null){
				for(AssetCabinetWarehouseAccess a:assetAccesss){
					if(a.getAsset().getCategory() != null && a.getAsset().getCategory() == 0)
					{
						assets.add(a.getAsset());
					}
				}
			}
		}
		else{
			//申购相关，属于该项目，已入库
			String hql = "select a from AssetCabinetWarehouseAccess a where 1=1  and type != 0 and status = 1 and a.operationItem.id="+assetReceive.getOperationItem().getId();
			List<AssetCabinetWarehouseAccess> assetAccesss = assetCabinetWarehouseAccessDAO.executeQuery(hql);
			if(assetAccesss != null){
				for(AssetCabinetWarehouseAccess a:assetAccesss){
					if(a.getAsset().getCategory() != null && a.getAsset().getCategory() == 0)
					{
						assets.add(a.getAsset());
					}
				}
			}
		}
		return assets;
	}
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（根据物资查找其可被申领的数量）
	 * @author 郑昕茹
	 * @日期：2016-08-30
	 * **********************************************************************************/
	public Double findReceiveNumByAsset(Asset asset,AssetReceive assetReceive){
		Double num = new Double(0);
		//非实验项目申领(申领公用物品）
		if(assetReceive.getType() == 0){
			//申购无关,已入库,限定物资
			String hql = "select a from AssetCabinetWarehouseAccess a where 1=1  and type=0 and status = 1 and asset.id="+asset.getId();
			List<AssetCabinetWarehouseAccess> assetAccesss = assetCabinetWarehouseAccessDAO.executeQuery(hql);
			if(assetAccesss != null){
				for(AssetCabinetWarehouseAccess a:assetAccesss){
					num += a.getCabinetQuantity().doubleValue();
				}
			}
		}
		else{
			//申购相关，属于该项目，已入库
			String hql = "select a from AssetCabinetWarehouseAccess a where 1=1  and type!=0 and status = 1 and a.operationItem.id="
			+assetReceive.getOperationItem().getId()+" and asset.id="+asset.getId();
			List<AssetCabinetWarehouseAccess> assetAccesss = assetCabinetWarehouseAccessDAO.executeQuery(hql);
			if(assetAccesss != null){
				for(AssetCabinetWarehouseAccess a:assetAccesss){
					num += a.getCabinetQuantity().doubleValue();
				}
			}
		}
		return num;
	}
	
	
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申领（根据物资查找其可被申领物资记录）
	 * @author 郑昕茹
	 * @日期：2016-08-30
	 * **********************************************************************************/
	public List<AssetCabinetWarehouseAccessRecord> findAssetCabinetWarehouseAccessRecordsByAsset(Asset asset,AssetReceive assetReceive){
		List<AssetCabinetWarehouseAccessRecord> assetAccessRecords = null;
		//非实验项目申领(申领公用物品）
		if(assetReceive.getType() == 0){
			//申购无关,已入库,限定物资
			String hql = "select a from AssetCabinetWarehouseAccessRecord a where 1=1  and assetCabinetWarehouseAccess.type=0 and status = 1 and asset.id="+asset.getId();
			assetAccessRecords = assetCabinetWarehouseAccessRecordDAO.executeQuery(hql);
		}
		else{
			//申购相关，属于该项目，已入库
			String hql = "select a from AssetCabinetWarehouseAccessRecord a where 1=1 and assetCabinetWarehouseAccess.type!=0 and status = 1 and assetCabinetWarehouseAccess.operationItem.id="
			+assetReceive.getOperationItem().getId()+" and asset.id="+asset.getId();
			assetAccessRecords = assetCabinetWarehouseAccessRecordDAO.executeQuery(hql);
		}
		return assetAccessRecords;
	}
}