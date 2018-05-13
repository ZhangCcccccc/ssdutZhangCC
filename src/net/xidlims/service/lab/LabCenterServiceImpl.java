package net.xidlims.service.lab;

import java.util.List;

import net.xidlims.dao.LabCenterDAO;
import net.xidlims.domain.LabCenter;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("LabCenterService")
public class LabCenterServiceImpl implements LabCenterService {
	
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private ShareService shareService;

	/**
	 * 根据实验中心主键查找实验中心对象
	 * @author hly
	 * 2015.07.24
	 */
	@Override
	public LabCenter findLabCenterByPrimaryKey(Integer labCenterId) {
		return labCenterDAO.findLabCenterByPrimaryKey(labCenterId);
	}

	/**
	 * 获取所有的实验中心
	 * @author hly
	 * 2015.07.24
	 */
	@Override
	public List<LabCenter> findAllLabCenterByQuery(LabCenter labCenter, Integer currpage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("select l from LabCenter l where 1=1 ");
		if(labCenter.getId()!=null)
		{
			hql.append(" and l.id="+labCenter.getId());
		}
		if(labCenter.getCenterName()!=null && !"".equals(labCenter.getCenterName()))
		{
			hql.append(" and l.centerName like '%"+labCenter.getCenterName()+"%'");
		}
		
		return labCenterDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
	}

	/**
	 * 保存实验中心数据
	 * @author hly
	 * 2015.07.27
	 */
	@Override
	public LabCenter saveLabCenter(LabCenter labCenter) {
		if(labCenter.getUserByCenterManager()==null || labCenter.getUserByCenterManager().getUsername()==null)
		{
			labCenter.setUserByCenterManager(null);  //实验中心主任
		}
		if(labCenter.getSystemCampus()==null || labCenter.getSystemCampus().getCampusNumber()==null)
		{
			labCenter.setSystemCampus(null);  //所属校区
		}
		if(labCenter.getSchoolAcademy()==null || labCenter.getSchoolAcademy().getAcademyNumber()==null)
		{
			labCenter.setSchoolAcademy(null);  //所属学院
		}
		if(labCenter.getSystemBuild()==null || labCenter.getSystemBuild().getBuildNumber()==null)
		{
			labCenter.setSystemBuild(null);  //所属楼宇
		}
		return labCenterDAO.store(labCenter);
	}

	/**
	 * 删除实验中心
	 * @author hly
	 * 2015.07.27
	 */
	@Override
	public boolean deleteLabCenter(Integer labCenterId) {
		LabCenter labCenter = labCenterDAO.findLabCenterByPrimaryKey(labCenterId);
		if(labCenter!=null)
		{
			labCenterDAO.remove(labCenter);
			labCenterDAO.flush();
			return true;
		}
		
		return false;
	}

	/****************************************************************************
	 * 功能：根据实验中心信息查询实验中心
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabCenter> findAllLabCenterByLabCenter(LabCenter labCenter) {
		// TODO Auto-generated method stub
		String sql="select c from LabCenter c where 1=1";
		if(labCenter.getCenterName()!=null&&!labCenter.getCenterName().equals("")){
			sql+=" and c.centerName like '%"+labCenter.getCenterName()+"%'";
		}
		//超级管理员和教务
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1){
			
		}else if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_EXPERIMENTALTEACHING") != -1){
			
		}else{
			User user=shareService.getUser();
			if(user.getSchoolAcademy()!=null){
				if(user.getSchoolAcademy().getAcademyNumber()!=null&&!user.getSchoolAcademy().getAcademyNumber().equals("")){
					sql+=" and c.schoolAcademy.academyNumber='"+user.getSchoolAcademy().getAcademyNumber()+"' ";
				}
			}
			
		}				
		sql+=" order by c.updatedAt desc";
		return labCenterDAO.executeQuery(sql,0,-1);
	}
	
	/****************************************************************************
	 * 功能：根据实验中心信息查询实验中心并分页
	 * 作者：李小龙
	 ****************************************************************************/
	@Override
	public List<LabCenter> findAllLabCenterByLabCenter(LabCenter labCenter,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select c from LabCenter c where 1=1";
		if(labCenter.getCenterName()!=null&&!labCenter.getCenterName().equals("")){
			sql+=" and c.centerName like '%"+labCenter.getCenterName()+"%'";
		}
		//超级管理员和教务
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_SUPERADMIN") != -1){
			
		}else if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().indexOf("ROLE_EXPERIMENTALTEACHING") != -1){
			
		}else{
			User user=shareService.getUser();
			if(user.getSchoolAcademy()!=null){
				if(user.getSchoolAcademy().getAcademyNumber()!=null&&!user.getSchoolAcademy().getAcademyNumber().equals("")){
					sql+=" and c.schoolAcademy.academyNumber='"+user.getSchoolAcademy().getAcademyNumber()+"' ";
				}
			}
			
		}		
		sql+=" order by c.updatedAt desc";
		return labCenterDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	
	/****************************************************************************
	 * 功能：根据学院查找中心
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabCenter> findLabCenterByAcademy(String academyNumber){
		String sql="select c.labCenter from InnerAcademyCenter c where 1=1" +
				" and c.schoolAcademy.academyNumber like '%"+academyNumber+"%'";
		return labCenterDAO.executeQuery(sql, 0, -1);
	}
	
}
