package net.xidlims.service.cms;

import net.xidlims.dao.CmsArticleDAO;
import net.xidlims.dao.CmsChannelDAO;
import net.xidlims.dao.CmsResourceDAO;
import net.xidlims.dao.CmsSiteDAO;
import net.xidlims.dao.CmsTagDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.dao.CmsLinkDAO;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Channel entities
 * 
 */

@Service("CmsChannelService")
@Transactional
public class CmsChannelServiceImpl implements CmsChannelService {

	/**
	 * DAO injected by Spring that manages Article entities
	 * 
	 */
	@Autowired
	private CmsArticleDAO articleDAO;

	/**
	 * DAO injected by Spring that manages Channel entities
	 * 
	 */
	@Autowired
	private CmsChannelDAO channelDAO;

	/**
	 * DAO injected by Spring that manages Resource entities
	 * 
	 */
	@Autowired
	private CmsResourceDAO resourceDAO;

	/**
	 * DAO injected by Spring that manages Site entities
	 * 
	 */
	@Autowired
	private CmsSiteDAO siteDAO;

	/**
	 * DAO injected by Spring that manages Tag entities
	 * 
	 */
	@Autowired
	private CmsTagDAO tagDAO;
	/**
	 * DAO injected by Spring that manages Tag entities
	 * 
	 */
	@Autowired
	private CmsLinkDAO cmsLinkDAO;

	/**
	 * DAO injected by Spring that manages User entities
	 * 
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * Instantiates a new ChannelServiceImpl.
	 *
	 */
	public CmsChannelServiceImpl() {
	}

	/**
	 * Save an existing User entity
	 * 
	 */
	@Transactional
	public CmsChannel saveChannelUser(Integer id, User related_user) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		User existinguser = userDAO.findUserByPrimaryKey(related_user.getUsername());

		// copy into the existing record to preserve existing relationships
		if (existinguser != null) {
			existinguser.setUsername(related_user.getUsername());
			existinguser.setPassword(related_user.getPassword());
			existinguser.setEnabled(related_user.getEnabled());
			//existinguser.setRemarks(related_user.getRemarks());
			related_user = existinguser;
		} else {
			related_user = userDAO.store(related_user);
			userDAO.flush();
		}

		channel.setUser(related_user);
		related_user.getCmsChannels().add(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_user = userDAO.store(related_user);
		userDAO.flush();

		return channel;
	}

	/**
	 * Return all Channel entity
	 * 
	 */
	@Transactional
	public List<CmsChannel> findAllChannels(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<CmsChannel>(channelDAO.findAllCmsChannels(startResult, maxRows));
	}

	/**
	 * Delete an existing Tag entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelTags(Integer channel_id, Integer related_tags_id) {
		CmsTag related_tags = tagDAO.findCmsTagByPrimaryKey(related_tags_id, -1, -1);

		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);

		related_tags.getCmsChannels().remove(channel);
		channel.getCmsTags().remove(related_tags);

		tagDAO.remove(related_tags);
		tagDAO.flush();

		return channel;
	}

	/**
	 * Delete an existing Site entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelSite(Integer channel_id, String related_site_siteurl) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);
		CmsSite related_site = siteDAO.findCmsSiteByPrimaryKey(related_site_siteurl, -1, -1);

		channel.setCmsSite(null);
		related_site.getCmsChannels().remove(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_site = siteDAO.store(related_site);
		siteDAO.flush();

		siteDAO.remove(related_site);
		siteDAO.flush();

		return channel;
	}

	/**
	 * Delete an existing Channel entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelChannel(Integer channel_id, Integer related_channel_id) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);
		CmsChannel related_channel = channelDAO.findCmsChannelByPrimaryKey(related_channel_id, -1, -1);

		channel.setCmsChannel(null);
		related_channel.getCmsChannels().remove(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_channel = channelDAO.store(related_channel);
		channelDAO.flush();

		channelDAO.remove(related_channel);
		channelDAO.flush();

		return channel;
	}

	/**
	 * Delete an existing Channel entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelChannels(Integer channel_id, Integer related_channels_id) {
		CmsChannel related_channels = channelDAO.findCmsChannelByPrimaryKey(related_channels_id, -1, -1);

		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);

		channel.setCmsChannel(null);
		related_channels.getCmsChannels().remove(channel);

		channelDAO.remove(related_channels);
		channelDAO.flush();

		return channel;
	}

	/**
	 * Return a count of all Channel entity
	 * 
	 */
	@Transactional
	public Integer countChannels() {
		return ((Long) channelDAO.createQuerySingleResult("select count(o) from CmsChannel o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing Channel entity
	 * 
	 */
	@Transactional
	public Set<CmsChannel> loadChannels() {
		return channelDAO.findAllCmsChannels();
	}

	/**
	 * Save an existing Channel entity
	 * 
	 */
	@Transactional
	public CmsChannel saveChannelChannels(Integer id, CmsChannel related_channels) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		CmsChannel existingchannels = channelDAO.findCmsChannelByPrimaryKey(related_channels.getId());

		// copy into the existing record to preserve existing relationships
		if (existingchannels != null) {
			existingchannels.setId(related_channels.getId());
			existingchannels.setTitle(related_channels.getTitle());
			existingchannels.setSort(related_channels.getSort());
			existingchannels.setHyperlink(related_channels.getHyperlink());
			existingchannels.setProfile(related_channels.getProfile());
			existingchannels.setState(related_channels.getState());
			existingchannels.setReadNum(related_channels.getReadNum());
			existingchannels.setCreateTime(related_channels.getCreateTime());
			related_channels = existingchannels;
		} else {
			related_channels = channelDAO.store(related_channels);
			channelDAO.flush();
		}

		channel.setCmsChannel(related_channels);
		related_channels.getCmsChannels().add(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_channels = channelDAO.store(related_channels);
		channelDAO.flush();

		return channel;
	}

	/**
	 * Save an existing Resource entity
	 * 
	 */
	@Transactional
	public CmsChannel saveChannelResource(Integer id, CmsResource related_resource) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		CmsResource existingresource = resourceDAO.findCmsResourceByPrimaryKey(related_resource.getId());

		// copy into the existing record to preserve existing relationships
		if (existingresource != null) {
			existingresource.setId(related_resource.getId());
			existingresource.setName(related_resource.getName());
			existingresource.setUrl(related_resource.getUrl());
			existingresource.setProfile(related_resource.getProfile());
			existingresource.setCreateTime(related_resource.getCreateTime());
			related_resource = existingresource;
		} else {
			related_resource = resourceDAO.store(related_resource);
			resourceDAO.flush();
		}

		channel.setCmsResource(related_resource);
		related_resource.getCmsChannels().add(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_resource = resourceDAO.store(related_resource);
		resourceDAO.flush();

		return channel;
	}

	/**
	 * Save an existing Tag entity
	 * 
	 */
	@Transactional
	public CmsChannel saveChannelTags(Integer id, CmsTag related_tags) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		CmsTag existingtags = tagDAO.findCmsTagByPrimaryKey(related_tags.getId());

		// copy into the existing record to preserve existing relationships
		if (existingtags != null) {
			existingtags.setId(related_tags.getId());
			existingtags.setName(related_tags.getName());
			existingtags.setDescription(related_tags.getDescription());
			existingtags.setCategory(related_tags.getCategory());
			related_tags = existingtags;
		} else {
			related_tags = tagDAO.store(related_tags);
			tagDAO.flush();
		}

		related_tags.getCmsChannels().add(channel);
		channel.getCmsTags().add(related_tags);
		related_tags = tagDAO.store(related_tags);
		tagDAO.flush();

		channel = channelDAO.store(channel);
		channelDAO.flush();

		return channel;
	}

	/**
	 * Save an existing Site entity
	 * 
	 */
	@Transactional
	public CmsChannel saveChannelSite(Integer id, CmsSite related_site) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		CmsSite existingsite = siteDAO.findCmsSiteByPrimaryKey(related_site.getSiteurl());

		// copy into the existing record to preserve existing relationships
		if (existingsite != null) {
			existingsite.setSiteurl(related_site.getSiteurl());
			existingsite.setName(related_site.getName());
			existingsite.setImageResource(related_site.getImageResource());
			existingsite.setVideoResource(related_site.getVideoResource());
			existingsite.setBottomContent(related_site.getBottomContent());
			existingsite.setState(related_site.getState());
			existingsite.setCreateTime(related_site.getCreateTime());
			related_site = existingsite;
		}

		channel.setCmsSite(related_site);
		related_site.getCmsChannels().add(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_site = siteDAO.store(related_site);
		siteDAO.flush();

		return channel;
	}

	/**
	 */
	@Transactional
	public CmsChannel findChannelByPrimaryKey(Integer id) {
		return channelDAO.findCmsChannelByPrimaryKey(id);
	}

	/**
	 * Delete an existing User entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelUser(Integer channel_id, String related_user_userid) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);
		User related_user = userDAO.findUserByPrimaryKey(related_user_userid, -1, -1);

		channel.setUser(null);
		related_user.getCmsChannels().remove(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_user = userDAO.store(related_user);
		userDAO.flush();

		userDAO.remove(related_user);
		userDAO.flush();

		return channel;
	}

	/**
	 * Save an existing Channel entity
	 * 
	 */
	@Transactional
	public CmsChannel saveChannelChannel(Integer id, CmsChannel related_channel) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		CmsChannel existingchannel = channelDAO.findCmsChannelByPrimaryKey(related_channel.getId());

		// copy into the existing record to preserve existing relationships
		if (existingchannel != null) {
			existingchannel.setId(related_channel.getId());
			existingchannel.setTitle(related_channel.getTitle());
			existingchannel.setSort(related_channel.getSort());
			existingchannel.setHyperlink(related_channel.getHyperlink());
			existingchannel.setProfile(related_channel.getProfile());
			existingchannel.setState(related_channel.getState());
			existingchannel.setReadNum(related_channel.getReadNum());
			existingchannel.setCreateTime(related_channel.getCreateTime());
			related_channel = existingchannel;
		} else {
			related_channel = channelDAO.store(related_channel);
			channelDAO.flush();
		}

		channel.setCmsChannel(related_channel);
		related_channel.getCmsChannels().add(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_channel = channelDAO.store(related_channel);
		channelDAO.flush();

		return channel;
	}

	/**
	 * Save an existing Article entity
	 * 
	 */
/*	@Transactional
	public Channel saveChannelArticles(Integer id, Article related_articles) {
		Channel channel = channelDAO.findChannelByPrimaryKey(id, -1, -1);
		Article existingarticles = articleDAO.findArticleByPrimaryKey(related_articles.getId());

		// copy into the existing record to preserve existing relationships
		if (existingarticles != null) {
			existingarticles.setId(related_articles.getId());
			existingarticles.setTitle(related_articles.getTitle());
			existingarticles.setNews(related_articles.getNews());
			existingarticles.setProfile(related_articles.getProfile());
			existingarticles.setState(related_articles.getState());
			existingarticles.setSort(related_articles.getSort());
			existingarticles.setReadNum(related_articles.getReadNum());
			existingarticles.setCreateUser(related_articles.getCreateUser());
			existingarticles.setCreateTime(related_articles.getCreateTime());
			related_articles = existingarticles;
		}

		related_articles.setChannel(channel);
		channel.getArticles().add(related_articles);
		related_articles = articleDAO.store(related_articles);
		articleDAO.flush();

		channel = channelDAO.store(channel);
		channelDAO.flush();

		return channel;
	}*/
	@Transactional
	public CmsChannel saveChannelArticles(Integer id, CmsArticle related_articles) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(id, -1, -1);
		CmsArticle existingarticles = articleDAO.findCmsArticleByPrimaryKey(related_articles.getId());

		// copy into the existing record to preserve existing relationships
		if (existingarticles != null) {
			existingarticles.setId(related_articles.getId());
			existingarticles.setTitle(related_articles.getTitle());
			existingarticles.setNews(related_articles.getNews());
			existingarticles.setProfile(related_articles.getProfile());
			existingarticles.setState(related_articles.getState());
			existingarticles.setSort(related_articles.getSort());
			existingarticles.setReadNum(related_articles.getReadNum());
			existingarticles.setCreateTime(related_articles.getCreateTime());
			related_articles = existingarticles;
		}

		related_articles.setCmsChannel(channel);
		channel.getCmsArticles().add(related_articles);
		related_articles = articleDAO.store(related_articles);
		articleDAO.flush();

		channel = channelDAO.store(channel);
		channelDAO.flush();

		return channel;
	}
	/**
	 * Delete an existing Article entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelArticles(Integer channel_id, Integer related_articles_id) {
		CmsArticle related_articles = articleDAO.findCmsArticleByPrimaryKey(related_articles_id, -1, -1);

		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);

		related_articles.setCmsChannel(null);
		channel.getCmsArticles().remove(related_articles);

		articleDAO.remove(related_articles);
		articleDAO.flush();

		return channel;
	}

	/**
	 * Save an existing Channel entity
	 * 
	 */
	@Transactional
	public void saveChannel(CmsChannel channel) {
		CmsChannel existingChannel = channelDAO.findCmsChannelByPrimaryKey(channel.getId());

		if (existingChannel != null) {
			if (existingChannel != channel) {
				existingChannel.setId(channel.getId());
				existingChannel.setTitle(channel.getTitle());
				existingChannel.setSort(channel.getSort());
				existingChannel.setHyperlink(channel.getHyperlink());
				existingChannel.setProfile(channel.getProfile());
				existingChannel.setState(channel.getState());
				existingChannel.setReadNum(channel.getReadNum());
				existingChannel.setCreateTime(channel.getCreateTime());
			}
			channel = channelDAO.store(existingChannel);
		} else {
			channel = channelDAO.store(channel);
		}
		channelDAO.flush();
	}

	/**
	 * Delete an existing Resource entity
	 * 
	 */
	@Transactional
	public CmsChannel deleteChannelResource(Integer channel_id, Integer related_resource_id) {
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(channel_id, -1, -1);
		CmsResource related_resource = resourceDAO.findCmsResourceByPrimaryKey(related_resource_id, -1, -1);

		channel.setCmsResource(null);
		related_resource.getCmsChannels().remove(channel);
		channel = channelDAO.store(channel);
		channelDAO.flush();

		related_resource = resourceDAO.store(related_resource);
		resourceDAO.flush();

		resourceDAO.remove(related_resource);
		resourceDAO.flush();

		return channel;
	}

	/**
	 * Delete an existing Channel entity
	 * 
	 */
	@Transactional
	public void deleteChannel(CmsChannel channel) {
		channelDAO.remove(channel);
		channelDAO.flush();
	}
	
	@Override
	public List<CmsChannel>  findAllChildrenChannelsOrderBySort(CmsChannel channel){
		List<CmsChannel>  childrenChannels = new ArrayList<CmsChannel>();
		String sql = "select c from CmsChannel c where c.cmsChannel.id = "+channel.getId()+" order by c.sort,c.createTime desc";
		childrenChannels = channelDAO.executeQuery(sql);
		return childrenChannels;
	}
	
	@Transactional
	public List<CmsChannel> findAllChannelsBySite(CmsSite site) {
		String sql = "select c from CmsChannel c where c.site.siteurl='"+site.getSiteurl()+"' ";
		List<CmsChannel>  cs = channelDAO.executeQuery(sql);
		return cs;
	}
	
	@Override
	public List<CmsChannel>  findChildrenChannelsOrderBySort(Integer id){
		List<CmsChannel>  childrenChannels = new ArrayList<CmsChannel>();
		String sql = "select c from CmsChannel c where c.cmsChannel.id = "+id+" order by c.sort,c.createTime desc";
		childrenChannels = channelDAO.executeQuery(sql);
		return childrenChannels;		
	}
	
	/**
	 * 找到
	 * 
	 */	
	public List<CmsLink>  findCmstagId(int id)
	{
		String sql="select c from CmsLink c where 1=1";
		sql += " and c.cmsTag.id = " +id;
		List<CmsLink> cmsLinks=cmsLinkDAO.executeQuery(sql);
		return cmsLinks;
		
	}
	
}
