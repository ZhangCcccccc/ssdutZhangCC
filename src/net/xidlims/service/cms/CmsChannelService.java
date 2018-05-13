package net.xidlims.service.cms;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.User;

import java.util.List;
import java.util.Set;

/**
 * Spring service that handles CRUD requests for CmsChannel entities
 * 
 */
public interface CmsChannelService {

	/**
	 * Save an existing User entity
	 * 
	 */
	public CmsChannel saveChannelUser(Integer id, User related_user);

	/**
	 * Return all CmsChannel entity
	 * 
	 */
	public List<CmsChannel> findAllChannels(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing Tag entity
	 * 
	 */
	public CmsChannel deleteChannelTags(Integer CmsChannel_id, Integer related_tags_id);

	/**
	 * Delete an existing Site entity
	 * 
	 */
	public CmsChannel deleteChannelSite(Integer CmsChannel_id_1, String related_site_siteurl);

	/**
	 * Delete an existing CmsChannel entity
	 * 
	 */
	public CmsChannel deleteChannelChannel(Integer CmsChannel_id_2, Integer related_channel_id);

	/**
	 * Delete an existing CmsChannel entity
	 * 
	 */
	public CmsChannel deleteChannelChannels(Integer CmsChannel_id_3, Integer related_channels_id);

	/**
	 * Return a count of all CmsChannel entity
	 * 
	 */
	public Integer countChannels();

	/**
	 * Load an existing CmsChannel entity
	 * 
	 */
	public Set<CmsChannel> loadChannels();

	/**
	 * Save an existing CmsChannel entity
	 * 
	 */
	public CmsChannel saveChannelChannels(Integer id_1, CmsChannel related_channels);

	/**
	 * Save an existing Resource entity
	 * 
	 */
	public CmsChannel saveChannelResource(Integer id_2, CmsResource related_resource);

	/**
	 * Save an existing Tag entity
	 * 
	 */
	public CmsChannel saveChannelTags(Integer id_3, CmsTag related_tags);

	/**
	 * Save an existing Site entity
	 * 
	 */
	public CmsChannel saveChannelSite(Integer id_4, CmsSite related_site);

	/**
	 */
	public CmsChannel findChannelByPrimaryKey(Integer id_5);

	/**
	 * Delete an existing User entity
	 * 
	 */
	public CmsChannel deleteChannelUser(Integer CmsChannel_id_4, String related_user_userid);

	/**
	 * Save an existing CmsChannel entity
	 * 
	 */
	public CmsChannel saveChannelChannel(Integer id_6, CmsChannel related_channel);

	/**
	 * Save an existing Article entity
	 * 
	 */
	public CmsChannel saveChannelArticles(Integer id_7, CmsArticle related_articles);

	/**
	 * Delete an existing Article entity
	 * 
	 */
	public CmsChannel deleteChannelArticles(Integer CmsChannel_id_5, Integer related_articles_id);

	/**
	 * Save an existing CmsChannel entity
	 * 
	 */
	public void saveChannel(CmsChannel CmsChannel);

	/**
	 * Delete an existing Resource entity
	 * 
	 */
	public CmsChannel deleteChannelResource(Integer CmsChannel_id_6, Integer related_resource_id);

	/**
	 * Delete an existing CmsChannel entity
	 * 
	 */
	public void deleteChannel(CmsChannel CmsChannel_1);

	/**
	 * Delete an existing CmsChannel entity
	 * 
	 */
	public List<CmsChannel>  findAllChildrenChannelsOrderBySort(CmsChannel CmsChannel);
	
	/**
	 * Delete an existing CmsChannel entity
	 * 
	 */
	public List<CmsChannel> findAllChannelsBySite(CmsSite site);
	
	/**
	 * 找到栏目的子栏目
	 * 
	 */	
	public List<CmsChannel>  findChildrenChannelsOrderBySort(Integer id);
	/**
	 * 找到
	 * 
	 */	
	public List<CmsLink>  findCmstagId(int id);

}