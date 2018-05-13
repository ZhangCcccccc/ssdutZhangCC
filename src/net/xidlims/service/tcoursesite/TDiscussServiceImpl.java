package net.xidlims.service.tcoursesite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.TDiscussDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TDiscussService")
public class TDiscussServiceImpl implements TDiscussService {

	@Autowired
	private TDiscussDAO tDiscussDAO;
	@Autowired
	private SchoolClassesDAO schoolClassesDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ShareService shareService;
	
	/**************************************************************************
	 * Description:查询对应站点下的讨论数量
	 * 
	 * @author：裴继超
	 * @date ：2015-8-6 10:47:43
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 **************************************************************************/
	@Override
	public int getCountTDiscussList(String tCourseSiteId) {
		//查询对应站点下的讨论数量
		StringBuffer sql = new StringBuffer("select count(*) from TDiscuss c " +
				"where c.TCourseSite.id = '"+ tCourseSiteId +"' " +
						" and c.type < 100");
		return ((Long) tDiscussDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	
	/**************************************************************************
	 * Description:查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2016年6月27日16:27:20
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索
	 **************************************************************************/
	@Override
	public List<TDiscuss> findTDiscussListByPartent(Integer partentDiscussId, Integer currpage,
			int pageSize){
		//查询讨论
		StringBuffer sql = new StringBuffer("from TDiscuss c where c.tDiscuss.id = '"+ partentDiscussId +"'");
		sql.append(" order by c.discussTime asc");
		return tDiscussDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}

	/**************************************************************************
	 * Description:查询讨论列表
	 * 
	 * @author：裴继超
	 * @date ：2015-8-6 10:33:03
	 **************************************************************************/
	@Override
	public List<TDiscuss> findTDiscussList(String tCourseSiteId,
			Integer currpage, int pageSize) {
		//查询讨论列表
		StringBuffer sql = new StringBuffer("from TDiscuss c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		sql.append("  and c.type < 100 order by c.discussTime desc");
		return tDiscussDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}

	/**************************************************************************
	 * Description:保存讨论
	 * 
	 * @author：裴继超
	 * @date ：2015-8-6 14:33:44
	 **************************************************************************/
	@Override
	public void saveTDiscuss(TDiscuss tDiscuss) {
		//保存讨论
		tDiscuss.setUser(userDAO.findUserByPrimaryKey(tDiscuss.getUser().getUsername().trim())==null?shareService.getUser():userDAO.findUserByPrimaryKey(tDiscuss.getUser().getUsername().trim()));
		tDiscussDAO.store(tDiscuss);
	}

	/**************************************************************************
	 * Description:根据主键查询讨论
	 * 
	 * @author：裴继超
	 * @date ：2015-8-6 15:08:10
	 **************************************************************************/
	@Override
	public TDiscuss findTDiscussByPrimaryKey(Integer id) {
		//根据主键查询讨论
		return tDiscussDAO.findTDiscussByPrimaryKey(id);
	}

	/**************************************************************************
	 * Description:删除查询出的讨论
	 * 
	 * @author：裴继超
	 * @date ：2015-8-6 15:10:50
	 **************************************************************************/
	@Override
	public void deleteTDiscuss(TDiscuss tDiscuss) {
		//删除查询出的讨论
		tDiscussDAO.remove(tDiscuss);
	}

	/**************************************************************************
	 * Description:根据班级名称和学生姓名查询记录数
	 * 
	 * @author：裴继超
	 * @date ：2015-8-10 10:09:57
	 **************************************************************************/
	@Override
	public int getCountUserListByClassnameAndCname(String classname,
			String cname) {
		//根据班级名称和学生姓名查询记录数
		String sql = "select count(*) from User c where c.schoolClasses.className like '%"+ classname +"%' and c.cname like '%"+ cname +"%'";
		return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	/**************************************************************************
	 * Description:根据关键字查询班级名称记录数
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	@Override
	public int getCountClassnameBykeyword(String classname) {
		//根据关键字查询班级名称记录数
		String sql = "select count(*) from SchoolClasses c where className like '%"+ classname +"%'";
		
		return ((Long) schoolClassesDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}
	/**************************************************************************
	 * Description:根据关键字查询班级名称列表
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	@Override
	public List<SchoolClasses> findClassnameBykeyword(String classname,
			 Integer page, int pageSize) {
		//根据关键字查询班级名称列表
		String sql = "from SchoolClasses c where c.className like '%"+ classname +"%' ";
		return schoolClassesDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
	}

	/**************************************************************************
	 * Description:根据班级名称和学生姓名查询用户列表
	 * 
	 * @author：裴继超
	 * @date ：2015-8-10 10:13:05
	 **************************************************************************/
	@Override
	public List<User> findUserListByClassnameAndCname(String classname,
			String cname, Integer page, int pageSize) {
		//根据班级名称和学生姓名查询用户列表
		String sql = "from User c where c.schoolClasses.className like '%"+ classname +"%' and c.cname like '%"+ cname +"%'";
		return userDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
	}




}