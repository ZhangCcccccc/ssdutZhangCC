package net.xidlims.service.expendable;

import java.util.Calendar;
import java.util.List;

import net.xidlims.dao.ExpendableApplyAuditRecordDAO;
import net.xidlims.dao.ExpendableApplyDAO;
import net.xidlims.dao.ExpendableDAO;
import net.xidlims.dao.ExpendableStockRecordDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.Expendable;
import net.xidlims.domain.ExpendableApply;
import net.xidlims.domain.ExpendableApplyAuditRecord;
import net.xidlims.domain.ExpendableStockRecord;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("ExpendableService")
public class ExpendableServiceImpl implements  ExpendableService {
	
	@Autowired ShareService shareService;
	@Autowired ExpendableDAO expendableDAO;
	@Autowired ExpendableStockRecordDAO expendableStockRecordDAO;
	@Autowired ExpendableApplyDAO expendableApplyDAO;
	@Autowired ExpendableApplyAuditRecordDAO expendableApplyAuditRecordDAO;
	
	/***********************************************************************************
	 * @功能：获取当耗材申购信息
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findExpendableRecords(Integer currpage, Integer pageSize, Expendable expendable, User user,Integer flag){
		String sql="select e from Expendable e where 1=1";//获取所有的设备申购信息
		//现将是否为中心主任设为否
		boolean isExcenterdirector = false;
		/**
		 *1.遍历一遍当前登录人的所有权限
		 *2.判断权限里面是否包含4（中心主任）
		 *3.只要包含，将 isExcenterdirector 设为true
		 *4.在isExcenterdirector是false的条件下执行只显示当前登录人的申购记录
		 *5.中心中心可以看到所有人的申领记录
		 * */
		for (Authority authority : user.getAuthorities()) {
			if (authority.getId()==4) {
				isExcenterdirector = true;
			}
		}
		if (!isExcenterdirector) {
			if (user.getUsername()!=null &&! user.getUsername().equals("")) {
				sql+=" and e.user.username like '%"+user.getUsername()+"%' ";
			}
		}
		if (flag!=null) {
			sql+=" and e.flag like '"+flag+"' ";
		}
		if(expendable!=null &&expendable.getOrderNumber()!=null && !expendable.getOrderNumber().equals("")){
			sql+=" and e.orderNumber like '%"+expendable.getOrderNumber()+"%'";
		}
		if(expendable!=null&&expendable.getUser()!=null&&expendable.getUser().getUsername()!=null&&!expendable.getUser().getUsername().equals("")){
			sql+=" and e.user.username like '%"+expendable.getUser().getUsername()+"%'";
		}
		sql+=" group by orderNumber";
		List<Expendable> listExpendableRecords = expendableDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return listExpendableRecords;
	}
	
	/***********************************************************************************
	 * @功能：根据消耗品订单编号找到申购信息
	 * @author 徐文
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findExpendableRecordsbyOrderNumber(String orderNumber){
		String sql="select e from Expendable e where 1=1";//获取所有的设备申购信息
		sql+=" and e.flag like '"+0+"' ";
		sql+=" and e.orderNumber like '%"+orderNumber+"%' ";
		List<Expendable> expendable = expendableDAO.executeQuery(sql, 0, -1);
		return expendable;
	}
	/***********************************************************************************
     * @功能：获取当耗材申购信息
     * @author 徐文
     * @日期：2016-07-27
     * **********************************************************************************/
    public List<Expendable> findExpendableInUse(Integer currpage, Integer pageSize, Expendable expendable){
        String sql="select e from Expendable e where 1=1";//获取所有的设备申购信息
        if(expendable != null && expendable.getExpendableName() != null && !expendable.getExpendableName().equals("")){
            sql+=" and e.expendableName like '%"+ expendable.getExpendableName()+"%'";
        }
        if(expendable != null && expendable.getExpendableSource() != null && !expendable.getExpendableSource().equals("")){
            sql+=" and e.expendableSource like '%"+ expendable.getExpendableSource()+"%'";
        }
        if(expendable != null && expendable.getIfDangerous() != null && !expendable.getIfDangerous().equals("")){
            sql+=" and e.ifDangerous ="+ expendable.getIfDangerous().toString();
        }
        List<Expendable> listExpendableInUse = expendableDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
        
        return listExpendableInUse;
    }
	/***********************************************************************************
	 * @功能：获取全部的不同耗材名称
	 * @author 郑昕茹
	 * @日期：2016-07-27
	 * **********************************************************************************/
	public List<Expendable> findAllExpendableName(){
		String sql="select e from Expendable e where 1=1 group by expendableName";
		List<Expendable> listExpendables = expendableDAO.executeQuery(sql);
		return listExpendables;
	}
	/***********************************************************************************
	 * @功能：获取全部的不同耗材类别的耗材记录
	 * @author 郑昕茹
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public List<Expendable> findAllExpendableSource(){
		String sql="select e from Expendable e where 1=1 group by expendableSource";
		List<Expendable> listExpendables = expendableDAO.executeQuery(sql);
		return listExpendables;
	}
	
	/***********************************************************************************
	 * @功能：根据消耗品id找到对应的消耗品
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public Expendable findExpendableByPrimaryKey(Integer id){
		Expendable expendable = expendableDAO.findExpendableByPrimaryKey(id);
		return expendable;
	}
	
	/***********************************************************************************
	 * @功能：保存盘库信息
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public ExpendableStockRecord saveExpendableStockRecord(ExpendableStockRecord expendableStockRecord){
		expendableStockRecord.setUser(shareService.getUser());
		expendableStockRecord.setStockDate(Calendar.getInstance());
		return expendableStockRecordDAO.store(expendableStockRecord);
	}
	
	/***********************************************************************************
	 * @功能：保存消耗品信息
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public Expendable saveExpendable(Expendable expendable){
		return expendableDAO.store(expendable);
	}
	/***********************************************************************************
	 * @功能：根据消耗品id找到对应的申领记录
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public ExpendableStockRecord findExpendableStockRecordByPrimaryKey(Integer id){
		ExpendableStockRecord expendableStockRecord=expendableStockRecordDAO.findExpendableStockRecordByPrimaryKey(id);
	    return expendableStockRecord;
	}
	
	/***********************************************************************************
	 * @功能：保存申购信息
	 * @author 徐文
	 * @日期：2016-07-28
	 * **********************************************************************************/
	public ExpendableApply saveExpendableApply(ExpendableApply expendableApply){
		return expendableApplyDAO.store(expendableApply);
	}
	/***********************************************************************************
	 * @功能：获取当耗材申领信息
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	public List<ExpendableApply> findExpendableApplies(Integer currpage, Integer pageSize, ExpendableApply expendableApply,Integer flag, Integer id){
		String sql="select e from ExpendableApply e where 1=1";//获取所有的设备申购信息
		//查看所以审核记录试flag是9，（9无意义，为了方法通用），所以当不为9的时候才按照flag筛选
		if (flag!=9) {
			sql+=" and e.flag like '"+flag+"' ";
		}
		if(id != null){
			sql+=" and e.expendable.id = "+id.toString();
		}
		if(expendableApply != null && expendableApply.getExpendable() != null && expendableApply.getExpendable().getExpendableName() != null && !expendableApply.getExpendable().getExpendableName().equals("")){
			sql+="and e.expendable.expendableName like '%"+expendableApply.getExpendable().getExpendableName()+"%'";
		}
		if(expendableApply != null && expendableApply.getExpendable() != null && expendableApply.getExpendable().getIfDangerous()!= null && !expendableApply.getExpendable().getIfDangerous().equals("")){
			sql+="and e.expendable.ifDangerous like '%"+expendableApply.getExpendable().getIfDangerous()+"%'";
		}
		List<ExpendableApply> expendableApplies = expendableApplyDAO.executeQuery(sql,(currpage-1)*pageSize, pageSize);
		return expendableApplies;
	}
	/***********************************************************************************
	 * @功能：根据消耗品id找到对应的消耗品审核信息
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	public ExpendableApply findExpendableApplyByPrimaryKey(Integer id){
		return expendableApplyDAO.findExpendableApplyByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @功能：保存消耗品审核
	 * @author 徐文
	 * @日期：2016-07-29
	 * **********************************************************************************/
	public ExpendableApplyAuditRecord saveExpendableApplyAuditRecord(ExpendableApplyAuditRecord expendableApplyAuditRecord){
		return expendableApplyAuditRecordDAO.store(expendableApplyAuditRecord);
	}
	/***********************************************************************************
	 * @功能：获取当耗材盘库信息
	 * @author 郑昕茹
	 * @日期：2016-08-01
	 * **********************************************************************************/
	public List<ExpendableStockRecord> findExpendableStockRecords(Integer currpage, Integer pageSize, ExpendableStockRecord expendableStockRecord){
		String sql="select e from ExpendableStockRecord e where 1=1";//获取所有的设备申购信息
		
		if(expendableStockRecord != null && expendableStockRecord.getExpendable() != null && expendableStockRecord.getExpendable().getExpendableName() != null && !expendableStockRecord.getExpendable().getExpendableName().equals("")){
			sql+="and e.expendable.expendableName like '%"+expendableStockRecord.getExpendable().getExpendableName()+"%'";
		}
		if(expendableStockRecord != null && expendableStockRecord.getExpendable() != null && expendableStockRecord.getExpendable().getIfDangerous()!= null && !expendableStockRecord.getExpendable().getIfDangerous().equals("")){
			sql+="and e.expendable.ifDangerous like '%"+expendableStockRecord.getExpendable().getIfDangerous()+"%'";
		}
		List<ExpendableStockRecord> expendableStockRecords= expendableStockRecordDAO.executeQuery(sql,(currpage-1)*pageSize, pageSize);
		return expendableStockRecords;
	}
	
	
}