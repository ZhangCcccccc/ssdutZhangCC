package net.xidlims.service.tcoursesite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.TCourseSiteArticalDAO;
import net.xidlims.dao.TCourseSiteChannelDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteTagDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.TCourseSiteTag;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TCourseSiteChannelService")
public class TCourseSiteChannelServiceImpl implements TCourseSiteChannelService {

	@Autowired
	private TCourseSiteChannelDAO tCourseSiteChannelDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TCourseSiteTagDAO tCourseSiteTagDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CDictionaryDAO cDictionaryDAO;
	@Autowired
	private ShareService shareService;
	
	/**************************************************************************
	 * Description:保存课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-7-31
	 **************************************************************************/
	@Override
	public void saveCourseSiteChannel(String tCourseSiteId,
			TCourseSiteChannel tCourseSiteChannel, HttpServletRequest request) {
		TCourseSiteChannel oldTCourseSiteChannel = null;
		if (tCourseSiteChannel.getId()==null) {
			oldTCourseSiteChannel = tCourseSiteChannel;
			oldTCourseSiteChannel.setCreateDate(Calendar.getInstance());
		}else {
			oldTCourseSiteChannel = tCourseSiteChannelDAO.findTCourseSiteChannelById(tCourseSiteChannel.getId());
			oldTCourseSiteChannel.setChannelName(tCourseSiteChannel.getChannelName());
			oldTCourseSiteChannel.setChannelText(tCourseSiteChannel.getChannelText());
			oldTCourseSiteChannel.setLink(tCourseSiteChannel.getLink());
		}
		//文件上传
		String filePath="/upload/cms/"+tCourseSiteId;
		String imageUrl=shareService.fileUpload(request, filePath);
		if (!"".equals(imageUrl)) {
			oldTCourseSiteChannel.setImageUrl(imageUrl);
		}
		oldTCourseSiteChannel.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(Integer.valueOf(tCourseSiteId)));
		oldTCourseSiteChannel.setTCourseSiteTag(tCourseSiteTagDAO.findTCourseSiteTagById(tCourseSiteChannel.getTCourseSiteTag().getId()));
		oldTCourseSiteChannel.setCreateUser(userDAO.findUserByPrimaryKey(tCourseSiteChannel.getCreateUser().getUsername().trim()));
		oldTCourseSiteChannel.setCDictionary(cDictionaryDAO.findCDictionaryById(tCourseSiteChannel.getCDictionary().getId()));
		/*if (tCourseSiteChannel.getId()==null) {//如果是新增栏目，则添加创建时间
			
		}else{
			
		}*/
		oldTCourseSiteChannel.setModifyDate(Calendar.getInstance());
		TCourseSiteChannel newCourseSiteChannel=  tCourseSiteChannelDAO.store(oldTCourseSiteChannel);
		//接下来保存该栏目的父栏目
		String[] ids = request.getParameterValues("channelIds");
		if(ids!=null){
			for (String id : ids) {
				TCourseSiteChannel courseSiteChannel = tCourseSiteChannelDAO.findTCourseSiteChannelById(Integer.valueOf(id));
				Set<TCourseSiteChannel> tCourseSiteChannels = courseSiteChannel.getTCourseSiteChannelsForChannelId();
				tCourseSiteChannels.add(newCourseSiteChannel);
				courseSiteChannel.getTCourseSiteChannelsForChannelId().addAll(tCourseSiteChannels);
				tCourseSiteChannelDAO.store(courseSiteChannel);
			}
		}
		
	}

	/**************************************************************************
	 * Description:根据主键查询课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-3
	 **************************************************************************/
	@Override
	public TCourseSiteChannel findTCourseSiteChannelByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tCourseSiteChannelDAO.findTCourseSiteChannelById(id);
	}
	
	/**************************************************************************
	 * Description:删除查询出的课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-3
	 **************************************************************************/
	@Override
	public void deleteTCourseSiteChannel(TCourseSiteChannel tCourseSiteChannel) {
		// TODO Auto-generated method stub		
		tCourseSiteChannelDAO.remove(tCourseSiteChannel);
	}

	/**************************************************************************
	 * Description:根据已有课程栏目查询可成为父栏目的栏目列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-3
	 **************************************************************************/
	@Override
	public List<TCourseSiteChannel> findTCourseSiteChannelsTobeParent(
			TCourseSiteChannel tCourseSiteChannel, HttpSession httpSession) {
		// TODO Auto-generated method stub
		String condition = "";
		if (tCourseSiteChannel!=null && !tCourseSiteChannel.equals("")) {
			StringBuffer ids = new StringBuffer("(");
			ids.append(tCourseSiteChannel.getId()+",");
			if (tCourseSiteChannel.getTCourseSiteChannelsForChannelId().size() > 0) {
				Set<TCourseSiteChannel> tCourseSiteChannels = tCourseSiteChannel.getTCourseSiteChannelsForChannelId();
				for (TCourseSiteChannel tSiteChannel : tCourseSiteChannels) {
					ids.append(tSiteChannel.getId()+",");
					if (tSiteChannel.getTCourseSiteChannelsForChannelId().size() > 0) {
						Set<TCourseSiteChannel> tSiteChannels = tSiteChannel.getTCourseSiteChannelsForChannelId();
						for (TCourseSiteChannel tChannel : tSiteChannels){
							ids.append(tChannel.getId()+",");
						}
					}
				}
			}
			if (tCourseSiteChannel.getTCourseSiteChannelsForParentChannelId().size() > 0) {
				Set<TCourseSiteChannel> tCourseSiteChannels = tCourseSiteChannel.getTCourseSiteChannelsForParentChannelId();
				for (TCourseSiteChannel tSiteChannel : tCourseSiteChannels) {
					ids.append(tSiteChannel.getId()+",");
				}
			}
			condition = " and c.id NOT IN "+ids.substring(0,ids.length()-1)+")";
		}
		
		StringBuffer sql = new StringBuffer("from TCourseSiteChannel c where 1=1  ");
		if (httpSession != null) {
			sql.append(" and c.TCourseSite.id = '" + httpSession.getAttribute("selected_courseSite") +"'");
		}
		sql.append(condition);
		List<TCourseSiteChannel> courseSiteChannels = tCourseSiteChannelDAO.executeQuery(sql.toString(),0,-1);
		
		return courseSiteChannels;
	}

	/**************************************************************************
	 * Description:查询所有的课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5
	 **************************************************************************/
	@Override
	public List<TCourseSiteChannel> findTCourseSiteChannelList(
			HttpSession httpSession) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TCourseSiteChannel c ");
		if (httpSession != null) {
			sql.append("where c.TCourseSite.id = '" + httpSession.getAttribute("selected_courseSite") +"'");
		}
		return tCourseSiteChannelDAO.executeQuery(sql.toString(),0,-1);
	}

	/**************************************************************************
	 * Description:查询所有的课程标签 type：“1”表示栏目标签，“2”表示文章标签
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5
	 **************************************************************************/
	@Override
	public List<TCourseSiteTag> findTCourseSiteTagList(String type) {
		// TODO Auto-generated method stub
		return tCourseSiteTagDAO.executeQuery("from TCourseSiteTag c where c.type = '" + type + "' ",0,-1);
	}

	/*************************************************************************************
	 *  Description:判断该栏目是否为三级栏目
	 *  	
	 * @author：贺子龙
	 * @date：2015-9-12 
	 *************************************************************************************/
	@Override
	public boolean isThirdChannel(TCourseSiteChannel tCourseSiteChannel) {
				
				//判断传回的栏目是二级栏目还是三级栏目
				boolean isThirdChannle = false;
				//找到tCourseSiteChannel的所有父栏目(该项目中每个子栏目只有一个父栏目)
				Set<TCourseSiteChannel> fatherChannels= tCourseSiteChannel.getTCourseSiteChannelsForParentChannelId();
				
				if (fatherChannels.size()!=0) {
					//将set转化为list
					List<TCourseSiteChannel> fatherChannelsList = new ArrayList<TCourseSiteChannel>(fatherChannels);
					//通过get(0)找到父栏目的id
					int fatherChannelId = fatherChannelsList.get(0).getId();
					//通过主键找到父栏目
					TCourseSiteChannel fatherChannel=tCourseSiteChannelDAO.findTCourseSiteChannelById(fatherChannelId);
					//找到fatherChannel的父栏目，即tCourseSiteChannel的祖父栏目。
					Set<TCourseSiteChannel> grandfatherChannels= fatherChannel.getTCourseSiteChannelsForParentChannelId();
					if (grandfatherChannels.size()!=0) {
						isThirdChannle = true;
					}
				}
		return isThirdChannle;
	}
	/****************************************************************************
	 * Description:根据名字查询栏目
	 * 
	 * @author：裴继超
	 * @date：2015-9-30
	 ****************************************************************************/
	@Override
	public TCourseSiteChannel findTCourseSiteChannelByName(String name){
	String sql = "from TCourseSiteChannel c where c.channelName = '"+name+"'";
	TCourseSiteChannel channel = new TCourseSiteChannel();
	if(tCourseSiteChannelDAO.executeQuery(sql).size()>0){
	 channel =tCourseSiteChannelDAO.executeQuery(sql).get(0);
	}
	return channel;
	}

	/**************************************************************************
	 * Description:查询所有的课程栏目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-13
	 **************************************************************************/
	@Override
	public List<TCourseSiteChannel> findTCourseSiteChannelList() {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from TCourseSiteChannel c");
		return tCourseSiteChannelDAO.executeQuery(sql.toString(),0,-1);
	}
	
	/****************************************************************************
	 * Description:查询课程下的所有栏目
	 * 
	 * @author：裴继超
	 * @date：2016-6-16
	 ****************************************************************************/
	@Override
	public List<TCourseSiteChannel> findTCourseSiteChannelsBySiteId(Integer tCourseSiteId) {
		// TODO Auto-generated method stub
		String sql = "from TCourseSiteChannel c where c.TCourseSite.id = " + tCourseSiteId;
		return tCourseSiteChannelDAO.executeQuery(sql.toString(),0,-1);
	}
	
	/****************************************************************************
	 * Description:保存栏目
	 * 
	 * @author：裴继超
	 * @date：2015-8-3
	 ****************************************************************************/
	@Override
	public TCourseSiteChannel saveChannel(TCourseSiteChannel tCourseSiteChannel) {
		// TODO Auto-generated method stub
		return tCourseSiteChannelDAO.store(tCourseSiteChannel);
	}

}
