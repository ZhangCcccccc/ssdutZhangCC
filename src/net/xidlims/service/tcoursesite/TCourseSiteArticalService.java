package net.xidlims.service.tcoursesite;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;

public interface TCourseSiteArticalService {

	/**************************************************************************
	 * Description:根据已有课程栏目查询该栏目所包含的文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-4 
	 **************************************************************************/
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannel(TCourseSiteChannel tCourseSiteChannel, Integer currpage, int pageSize);

	/**************************************************************************
	 * Description:保存课程文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-4 
	 **************************************************************************/
	public void saveCourseSiteArtical(TCourseSiteArtical tCourseSiteArtical,HttpServletRequest request);

	/**************************************************************************
	 * Description:根据文章id查询文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5 
	 **************************************************************************/
	public TCourseSiteArtical findTCourseSiteArticalByPrimaryKey(Integer id);

	/**************************************************************************
	 * Description:删除课程文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5 
	 **************************************************************************/
	public void deleteTCourseSiteArtical(TCourseSiteArtical tCourseSiteArtical);

	/**************************************************************************
	 * Description:删除选中的课程文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 
	 **************************************************************************/
	public void deleteCourseSiteArticalByIds(String ids);

	/****************************************************************************
	 *Description:根据ChannelId和State查出Channel下的文章列表
	 *
	 *@author：贺子龙
	 *@date ：2015-8-26 
	 ****************************************************************************/
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelIdAndState(Integer id,int i);

	/**************************************************************************
	 * Description:保存文章
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-8
	 **************************************************************************/
	public void saveDonationArtical(TCourseSiteArtical artical,String channelName); 

	/**************************************************************************
	 * Description::根据当前登录人和ChannelId查出Channel下的文章列表
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-9
	 **************************************************************************/
	public List<TCourseSiteArtical> findArticalByUser(String channelName) ;

	/**************************************************************************
	 * Description:根据ChannelId查出Channel下的文章列表(倒序)
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-10
	 **************************************************************************/
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelId(Integer id);

	/**************************************************************************
	 * Description:分页根据ChannelId查出Channel下的文章列表(倒序)
	 * 
	 * @author： 姜新剑
	 * @date ：2016-04-12
	 **************************************************************************/
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelId(Integer id,int page,int pagesize);
	
	/**************************************************************************
	 * Description:获取文章，分页，倒序
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-14
	 **************************************************************************/
	public List<TCourseSiteArtical> findTCourseSiteArticals(
			TCourseSiteChannel tCourseSiteChannel, Integer currpage,
			int pageSize);

	/**************************************************************************
	 * Description:根据栏目id及文章状态查询该栏目下的文章数
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-13 
	 **************************************************************************/
	public int countTCourseSiteArticalsByChannelIdAndState(Integer id, int state);

	/**************************************************************************
	 * Description:根据栏目id及文章状态查询该栏目下的文章(分页查询)
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-13 
	 **************************************************************************/
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelIdAndState(Integer id, 
			int state,Integer currpage, int pageSize);

	/**************************************************************************
	 * Description:保存文章
	 * 
	 * @author： 裴继超
	 * @date ：2015-8-3
	 **************************************************************************/
	public TCourseSiteArtical saveArtical(TCourseSiteArtical tCourseSiteArtical) ;
}

