package net.xidlims.service.devicePurchase;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import net.xidlims.dao.DeviceStatusRecordDAO;
import net.xidlims.dao.NDeviceAuditRecordDAO;
import net.xidlims.dao.NDevicePurchaseDAO;
import net.xidlims.domain.CDeviceStatus;
import net.xidlims.domain.DeviceStatusRecord;
import net.xidlims.domain.NDeviceAuditRecord;
import net.xidlims.domain.NDevicePurchase;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DevicePurchaseService")
public class DevicePurchaseServiceImpl implements  DevicePurchaseService {
	
	@Autowired ShareService shareService;
	@Autowired NDevicePurchaseDAO nDevicePurchaseDAO;
	@Autowired DeviceStatusRecordDAO deviceStatusRecordDAO;
	@Autowired NDeviceAuditRecordDAO nDeviceAuditRecordDAO;
	@Autowired CDeviceStatusService cDeviceStatusService;
	/***********************************************************************************
	 * @功能：获取当前设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public List<NDevicePurchase> findNDevicePurchases(Integer currpage,Integer auditStatus, Integer pageSize, NDevicePurchase nDevicePurchase, User user){
		String sql="select n from NDevicePurchase n where 1=1";//获取所有的设备申购信息
		if (user.getUsername()!=null &&! user.getUsername().equals("")) {
			sql+=" and n.user.username like '%"+user.getUsername()+"%' ";
		}
		if(auditStatus == 0 || auditStatus == 1 || auditStatus == 2 || auditStatus == 3 || auditStatus == 4)
		{
			sql += " and n.auditStatus = " + auditStatus.toString();
		}
		//获取当前用户的申购信息
		if (nDevicePurchase.getPurchaseNumber()!= null&&!nDevicePurchase.getPurchaseNumber().equals("")) {
			sql+=" and n.purchaseNumber like '%"+nDevicePurchase.getPurchaseNumber()+"%' ";
		}
		if (nDevicePurchase.getUseDirection()!=null&&!nDevicePurchase.getUseDirection().equals("")) {
			sql+=" and n.useDirection like '%"+nDevicePurchase.getUseDirection()+"%' ";
		}
		List<NDevicePurchase> listNDevicePurchase = nDevicePurchaseDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return listNDevicePurchase;
	}
	/***********************************************************************************
	 * @功能：获取当前设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public List<NDevicePurchase> findNDevicePurchase(Integer currpage,Integer auditStatus, Integer pageSize, NDevicePurchase nDevicePurchase, User user){
		String sql="select n from NDevicePurchase n where 1=1";//获取所有的设备申购信息
		if (user.getUsername()!=null &&! user.getUsername().equals("")) {
			sql+=" and n.user.username like '%"+user.getUsername()+"%' ";
		}
		if(auditStatus.equals(0)||auditStatus.equals(1)||auditStatus.equals(2)||auditStatus.equals(3)||auditStatus.equals(4)){
			sql+=" and n.auditStatus like '%"+nDevicePurchase.getAuditStatus()+"%' ";
		}//获取当前用户的申购信息
		if(auditStatus.equals(9)){
			sql+=" and n.auditStatus != 0" ;
		}
		if (nDevicePurchase.getPurchaseNumber()!= null&&!nDevicePurchase.getPurchaseNumber().equals("")) {
			sql+=" and n.purchaseNumber like '%"+nDevicePurchase.getPurchaseNumber()+"%' ";
		}
		if (nDevicePurchase.getUseDirection()!=null&&!nDevicePurchase.getUseDirection().equals("")) {
			sql+=" and n.useDirection like '%"+nDevicePurchase.getUseDirection()+"%' ";
		}
		List<NDevicePurchase> listNDevicePurchase = nDevicePurchaseDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return listNDevicePurchase;
	}
	/***********************************************************************************
	 * @功能：保存设备申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchase saveDevicePurchase(NDevicePurchase nDevicePurchase){
		nDevicePurchase.setUser(shareService.getUser());//将登录人信息保存进去
		nDevicePurchase.setCreateDate(Calendar.getInstance());//将时间保存进去
		return nDevicePurchaseDAO.store(nDevicePurchase);
	}
	/***********************************************************************************
	 * @功能：根据申购主键找到申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public NDevicePurchase findDevicePurchaseByPrimaryKey(Integer id){
		return nDevicePurchaseDAO.findNDevicePurchaseByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @功能：删除-通过主键找到申购信息
	 * @author 徐文
	 * @日期：2016-07-19
	 * **********************************************************************************/
	public boolean deleteDevicePurchase(Integer id){
		NDevicePurchase nDevicePurchase = nDevicePurchaseDAO.findNDevicePurchaseByPrimaryKey(id);
		if(nDevicePurchase!=null)
		{
			nDevicePurchaseDAO.remove(nDevicePurchase);
			nDevicePurchaseDAO.flush();
			return true;
		}
		return false;
	}
	/***********************************************************************************
	 * @功能：通过deviceId找到所有的设备状态记录
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public List<DeviceStatusRecord> findDeviceStatusRecordByDeviceId(Integer id){
		String sql="select d from DeviceStatusRecord d where 1=1 and d.NDevicePurchase.id = "+ id.toString();
		return deviceStatusRecordDAO.executeQuery(sql);
	}
	/***********************************************************************************
	 * @功能：根据当前时间判断是否要改变状态即状态的结束时间
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public void changeDeviceStatusByCurrentTime(Integer currpage, Integer pageSize){
		String sql="select n from NDevicePurchase n where 1=1";//获取所有的设备申购信息
		List<NDevicePurchase> listNDevicePurchase = nDevicePurchaseDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		//遍历所有的设备申购信息，将它们当前设备状态的结束时间与系统时间进行比较并进行状态改变
		for(int i = 0; i < listNDevicePurchase.size(); i++){
			//按顺序获取每一个设备申购信息
			NDevicePurchase nDevicePurchase = listNDevicePurchase.get(i);
			//当满足审核通过并且设备状态类型为正常状态时才进行与系统时间的比较
			if(nDevicePurchase.getAuditStatus() == 3 && nDevicePurchase.getCDeviceStatus().getFlag() == 1){
				//获取当前的系统时间
				Calendar currentDate = Calendar.getInstance();
				//获取该条设备申购信息当前设备状态的结束时间与系统时间比较结果
				int flag = nDevicePurchase.getEndDate().compareTo(currentDate);
				//若当前的结束时间小于等于系统时间，则需不断进行状态更新直到结束时间大于系统时间
				while(flag != 1){
					//获取该条设备申购信息的当前设备状态在设备状态字典表中的顺序的后一个顺序
					int order = nDevicePurchase.getCDeviceStatus().getStatusOrder()+1;
					//通过顺序找到下一个设备状态
					CDeviceStatus cDeviceStatusNext = cDeviceStatusService.findCDeviceStatusByStatusOrder(order);
					//当下一个设备状态存在并且是正常状态时才继续更新，否则停止
					if(cDeviceStatusNext != null && cDeviceStatusNext.getFlag() == 1){
						//获取该条设备申购信息的当前设备状态更新前的结束时间 
						Calendar newEndDate = nDevicePurchase.getEndDate();
						//新建设备状态记录，为目前所处的设备状态
						DeviceStatusRecord deviceStatusRecord = new DeviceStatusRecord();
						 //设置其结束时间为目前所处的状态
						deviceStatusRecord.setEndDate(nDevicePurchase.getEndDate());
						//设置其对应的设备申购信息该条设备申购信息 
						deviceStatusRecord.setNDevicePurchase(nDevicePurchase);
						//设置其状态名为该设备申购信息目前的设备状态名
						deviceStatusRecord.setStatusName(cDeviceStatusNext.getStatusName());
						//保存该新建的设备状态信息
						deviceStatusRecordDAO.store(deviceStatusRecord);
						//将当前的结束时间加上该条设备申购信息当前设备状态的间隔天数得到更新后的结束时间
						newEndDate.add(Calendar.DATE, nDevicePurchase.getCDeviceStatus().getIntervalDate());
						//设置该条设备申购信息的当前设备状态的新结束时间
						nDevicePurchase.setEndDate(newEndDate);
						//设置其设备状态为期原来的下一个状态
						nDevicePurchase.setCDeviceStatus(cDeviceStatusNext);
						//保存修改结果
						nDevicePurchaseDAO.store(nDevicePurchase); 
					}
					else break;
					//将当前的设备状态的结束日期继续与系统时间进行比较，进行循环更新
					flag = nDevicePurchase.getEndDate().compareTo(currentDate);
				}
			}
		}
		
	}
	
	/***********************************************************************************
     * @功能:随机生成 位数
     * @author 徐文
     * @日期：2016-07-25
     * **********************************************************************************/
    public String getRandomNumber(Integer count){
         StringBuffer sb = new StringBuffer();
            String str = "0123456789";
            Random r = new Random();
            for(int i=0;i<count;i++){
                int num = r.nextInt(str.length());
                sb.append(str.charAt(num));
                str = str.replace((str.charAt(num)+""), "");
            }
            return sb.toString();
    }
    /***********************************************************************************
	 * @功能：保存设备审核记录
	 * @author 徐文
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public NDeviceAuditRecord saveNDeviceAuditRecord(NDeviceAuditRecord nDeviceAuditRecord){
		return nDeviceAuditRecordDAO.store(nDeviceAuditRecord);
	}
	/***********************************************************************************
	 * @功能：保存延迟后的截止日期
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public NDevicePurchase saveDevicePurchaseEndDate(NDevicePurchase nDevicePurchase){
		return nDevicePurchaseDAO.store(nDevicePurchase);
	}

	/***********************************************************************************
	 * @功能：保存设备状态记录
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public DeviceStatusRecord saveDeviceStatusRecord(DeviceStatusRecord deviceStatusRecord){
		return deviceStatusRecordDAO.store(deviceStatusRecord);
	}
	/***********************************************************************************
	 * @功能：查找所有不同申购编号的申购记录
	 * @author 郑昕茹
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public List<NDevicePurchase> findAllPurchaseNumber(){
		String sql="select n from NDevicePurchase n where 1=1 group by purchaseNumber";//获取所有的设备申购信息
		return nDevicePurchaseDAO.executeQuery(sql);
	}
}