package net.xidlims.service.tcoursesite;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteTag;
import net.xidlims.domain.User;

public interface TCourseSiteChannelService {

	/**************************************************************************
	 * Description:保存课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-7-31
	 **************************************************************************/
	public void saveCourseSiteChannel(String tCourseSiteId,
			TCourseSiteChannel tCourseSiteChannel, HttpServletRequest request);

	/**************************************************************************
	 * Description:根据主键查询课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-3
	 **************************************************************************/
	public TCourseSiteChannel findTCourseSiteChannelByPrimaryKey(Integer id);

	/**************************************************************************
	 * Description:删除查询出的课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-3
	 **************************************************************************/
	public void deleteTCourseSiteChannel(TCourseSiteChannel tCourseSiteChannel);

	/**************************************************************************
	 * Description:根据已有课程栏目查询可成为父栏目的栏目列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-3
	 **************************************************************************/
	public List<TCourseSiteChannel> findTCourseSiteChannelsTobeParent(TCourseSiteChannel tCourseSiteChannel, HttpSession httpSession);

	/**************************************************************************
	 * Description:查询所有的课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5
	 **************************************************************************/
	public List<TCourseSiteChannel> findTCourseSiteChannelList(HttpSession httpSession);

	/**************************************************************************
	 * Description:查询所有的课程标签 type：“1”表示栏目标签，“2”表示文章标签
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5
	 **************************************************************************/
	public List<TCourseSiteTag> findTCourseSiteTagList(String type);
	
	/*************************************************************************************
	 * Description:判断该栏目是否为三级栏目
	 *  	
	 * @author：贺子龙
	 * @date：2015-9-12 
	 *************************************************************************************/
	public boolean isThirdChannel(TCourseSiteChannel tCourseSiteChannel);
	/****************************************************************************
	 * Description:根据名字查询栏目
	 * 
	 * @author：裴继超
	 * @date：2015-9-30
	 ****************************************************************************/
	public TCourseSiteChannel findTCourseSiteChannelByName(String name);

	/**************************************************************************
	 * Description:查询所有的课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-13
	 **************************************************************************/
	public List<TCourseSiteChannel> findTCourseSiteChannelList();
	
	/****************************************************************************
	 * Description:查询课程下的所有栏目
	 * 
	 * @author：裴继超
	 * @date：2016-6-16
	 ****************************************************************************/
	public List<TCourseSiteChannel> findTCourseSiteChannelsBySiteId(Integer tCourseSiteId); 

	/****************************************************************************
	 * Description:保存栏目
	 * 
	 * @author：裴继超
	 * @date：2015-8-3
	 ****************************************************************************/
	public TCourseSiteChannel saveChannel(TCourseSiteChannel tCourseSiteChannel);
}

