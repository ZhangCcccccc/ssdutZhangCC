package net.xidlims.service.devicePurchase;

import java.util.List;
import java.util.Set;

import net.xidlims.dao.CDeviceStatusDAO;
import net.xidlims.dao.CDeviceSupplierDAO;
import net.xidlims.dao.NDevicePurchaseDAO;

import net.xidlims.domain.CDeviceStatus;
import net.xidlims.domain.CDeviceSupplier;
import net.xidlims.domain.NDevicePurchase;
import net.xidlims.domain.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for CDeviceStatus entities
 * 
 */

@Service("CDeviceStatusService")
@Transactional
public class CDeviceStatusServiceImpl implements CDeviceStatusService {

	/**
	 * DAO injected by Spring that manages CDeviceStatus entities
	 * 
	 */
	@Autowired
	private CDeviceStatusDAO cDeviceStatusDAO;

	/**
	 * DAO injected by Spring that manages NDevicePurchase entities
	 * 
	 */
	@Autowired
	private NDevicePurchaseDAO nDevicePurchaseDAO;
	@Autowired
	private CDeviceSupplierDAO cDeviceSupplierDAO;
	

	/**
	 * Instantiates a new CDeviceStatusServiceImpl.
	 *
	 */
	/***********************************************************************************
	 * @功能：获取所有设备状态信息
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	public List<CDeviceStatus> findCDeviceStatus(Integer currpage, Integer pageSize){
		String hql = "select c from CDeviceStatus c where 1=1 order by c.statusOrder";//获取所有设备状态信息
		List<CDeviceStatus> listCDeviceStatus = cDeviceStatusDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
		return listCDeviceStatus;
	}
	/***********************************************************************************
	 * @功能：保存设备状态信息
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	public CDeviceStatus saveCDeviceStatus(CDeviceStatus cDeviceStatus){
		return cDeviceStatusDAO.store(cDeviceStatus); 	 
	}
	
	/***********************************************************************************
	 * @功能：根据设备状态主键找到设备状态信息
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	public CDeviceStatus findCDeviceStatusByPrimaryKey(Integer id){
		return cDeviceStatusDAO.findCDeviceStatusByPrimaryKey(id); 
	}
	
	/***********************************************************************************
	 * @功能：删除-通过主键找到设备状态信息
	 * @author 郑昕茹
	 * @日期：2016-07-20
	 * **********************************************************************************/
	public boolean deleteCDeviceStatus(Integer id){
		//根据主键找到该条设备状态信息
		CDeviceStatus cDeviceStatus = cDeviceStatusDAO.findCDeviceStatusByPrimaryKey(id);
		//合法性判断
		if(cDeviceStatus != null){
			Integer order = cDeviceStatus.getStatusOrder();//获得要删除的设备状态的顺序
			cDeviceStatusDAO.remove(cDeviceStatus);
			cDeviceStatusDAO.flush();
			//找到顺序位于删除的设备状态之后的所有设备状态信息
			String hql = "select c from CDeviceStatus c where 1=1 and c.statusOrder>"+ order.toString();
			List<CDeviceStatus> listCDeviceStatus = cDeviceStatusDAO.executeQuery(hql, null);
			//将这些记录的顺序都前移一位并保存
			for(CDeviceStatus l:listCDeviceStatus){       
				l.setStatusOrder(l.getStatusOrder()-1);
				cDeviceStatusDAO.store(l); 
			} 
			return true;
		}
		return false;
	}
	/***********************************************************************************
	 * @功能：根据属性statusOrder找到设备状态信息
	 * @author 郑昕茹
	 * @日期：2016-07-22
	 * **********************************************************************************/
	public CDeviceStatus findCDeviceStatusByStatusOrder(Integer order){
		//查询表中statusOrder为order的记录
		String hql = "select c from CDeviceStatus c where 1=1";
		hql += "and c.statusOrder =" + order.toString();
		CDeviceStatus listCDeviceStatus = cDeviceStatusDAO.executeQuerySingleResult(hql, null);
		return listCDeviceStatus;	
	}
	/***********************************************************************************
	 * @功能：找到 设备状态中orderStatus最大的那条记录
	 * @author 郑昕茹
	 * @日期：2016-07-22
	 * **********************************************************************************/
	public CDeviceStatus findMaxStatusOrder(){
		String hql = "select c from CDeviceStatus c where 1=1";
		hql += " and c.statusOrder = (select max(s.statusOrder) from CDeviceStatus s where 1=1)";
		CDeviceStatus listCDeviceStatus = cDeviceStatusDAO.executeQuerySingleResult(hql, null);
		return listCDeviceStatus;	
	}
	/***********************************************************************************
	 * @功能：找到 设备状态中orderStatus最小的那条记录
	 * @author 郑昕茹
	 * @日期：2016-07-25
	 * **********************************************************************************/
	public CDeviceStatus findMinStatusOrder(){
		String hql = "select c from CDeviceStatus c where 1=1";
		hql += " and c.statusOrder = (select min(s.statusOrder) from CDeviceStatus s where 1=1 and s.flag = 1)";
		CDeviceStatus listCDeviceStatus = cDeviceStatusDAO.executeQuerySingleResult(hql, null);
		return listCDeviceStatus;	
	}
	/***********************************************************************************
	 * @功能：获取正常状态的设备记录
	 * @author 郑昕茹
	 * @日期：2016-07-23
	 * **********************************************************************************/
	public List<CDeviceStatus> findCDeviceStatusByFlag(Integer currpage, Integer pageSize){
		String hql = "select c from CDeviceStatus c where 1=1 and c.flag = 1";//获取所有flag为1的设备状态信息
		List<CDeviceStatus> listCDeviceStatus = cDeviceStatusDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
		return listCDeviceStatus;
	}
	/***********************************************************************************
	 * @功能：将所有的特殊状态记录下移一位order
	 * @author 郑昕茹
	 * @日期：2016-07-23
	 * **********************************************************************************/
	public boolean MoveOrder(){
		//找到正常状态的记录中statusOrder最大的设备状态记录
		CDeviceStatus cDeviceStatus = this.findMaxStatusOrder();
		//获取该记录的statusOrder
		Integer order = cDeviceStatus.getStatusOrder();
		//获取所有特殊状态的设备状态记录
		String hql = "select c from CDeviceStatus c where 1=1 and c.flag = 0 and c.statusOrder >="+order.toString();
		List<CDeviceStatus> listCDeviceStatus = cDeviceStatusDAO.executeQuery(hql.toString());
		//将这些设备状态记录的statusOrder都加1并保存
		for(CDeviceStatus l:listCDeviceStatus){       
			l.setStatusOrder(l.getStatusOrder()+1);
			cDeviceStatusDAO.store(l); 
		} 
		return true;
	}
	/***********************************************************************************
	 * @功能：类型改变后的所有设备状态信息的statusOrder都进行改变，使正常状态的信息的statusOrder始终小于特殊状态的信息的statusOrder
	 * @author 郑昕茹
	 * @日期：2016-07-23
	 * **********************************************************************************/
	public boolean reOrderAfterChangeFlag(Integer flag,  CDeviceStatus cDeviceStatus){
		//获取发生状态改变的设备状态记录的statusOrder
		Integer order = cDeviceStatus.getStatusOrder();
		//若从特殊状态变为正常状态，需将该记录前的所有特殊状态记录都下移一位，将该条记录的statusOrder设为最后一条正常状态的statusOrder加1
		if(flag == 1){
			//找到所有statusOrder小于order的所有特殊状态的设备状态记录
			String hql1 = "select c from CDeviceStatus c where 1=1 and c.flag = 0 and c.statusOrder <"+order.toString();
			List<CDeviceStatus> listCDeviceStatus1 = cDeviceStatusDAO.executeQuery(hql1.toString());
			//将这些记录的statusOrder都进行加1并保存
			for(CDeviceStatus l:listCDeviceStatus1){       
				l.setStatusOrder(l.getStatusOrder()+1);
				cDeviceStatusDAO.store(l); 
			} 
			//将该条设备状态的statusOrder设为正常状态中最大的statusOrder+1
			cDeviceStatus.setStatusOrder(this.findCDeviceStatusByFlag(1, -1).size()+1);
		}//若从正常状态变为特殊状态，需将该记录后的所有记录都前移一位，将该条记录的statusOrder为总记录数
		else{
			//找到statusOrder大于当前设备状态记录的所有设备状态信息
			String hql2 = "select c from CDeviceStatus c where 1=1 and c.statusOrder >"+order.toString();
			List<CDeviceStatus> listCDeviceStatus2 = cDeviceStatusDAO.executeQuery(hql2.toString());
			//将这些设备状态信息的statusOrder整体减1并保存
			for(CDeviceStatus l:listCDeviceStatus2){       
				l.setStatusOrder(l.getStatusOrder()-1);
				cDeviceStatusDAO.store(l); 
			} 
			//将该条设备状态的statusOrder设为总记录数
			cDeviceStatus.setStatusOrder(this.findCDeviceStatus(1, -1).size());
		}
		
		cDeviceStatusDAO.store(cDeviceStatus); 
		return true;
	}
	/***********************************************************************************
	 * @功能：获取所有设备供应商信息
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public List<CDeviceSupplier> findCDeviceSuppliers(Integer currpage, Integer pageSize, CDeviceSupplier cDeviceSupplier){
		String sql="select c from CDeviceSupplier c where 1=1";//获取所有的设备申购信息
		//对供应商名称进行查询操作 
		if (cDeviceSupplier!=null && cDeviceSupplier.getDevicName()!=null &&!cDeviceSupplier.getDevicName().equals("")) {
			sql+=" and c.devicName like '%"+cDeviceSupplier.getDevicName()+"%' ";
		} 
		//获取设备申购信息
		List<CDeviceSupplier> listCDeviceSupplier = cDeviceSupplierDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return listCDeviceSupplier;
	}
	/***********************************************************************************
	 * @功能：根据主键找到设备供应商信息
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public CDeviceSupplier findCDeviceSupplierByPrimaryKey(Integer id){
		return cDeviceSupplierDAO.findCDeviceSupplierByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @功能：保存设备供应商信息
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public CDeviceSupplier  saveCDeviceSupplier(CDeviceSupplier cDeviceSupplier ){
		return cDeviceSupplierDAO.store(cDeviceSupplier); 	 
	}
	/***********************************************************************************
	 * @功能：删除-通过主键找到设备供应商信息然后删除
	 * @author 郑昕茹
	 * @日期：2016-07-26
	 * **********************************************************************************/
	public boolean deleteCDeviceSupplier(Integer id){
		CDeviceSupplier cDeviceSupplier = cDeviceSupplierDAO.findCDeviceSupplierByPrimaryKey(id);
		if(cDeviceSupplier != null){
			cDeviceSupplierDAO.remove(cDeviceSupplier);
			cDeviceSupplierDAO.flush();
			return true;
		}
		else return false;
	}
}
