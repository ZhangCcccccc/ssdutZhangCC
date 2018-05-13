package net.xidlims.service.cms;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;

import java.util.List;
import java.util.Set;

/**
 * Spring service that handles CRUD requests for Article entities
 * 
 */
public interface CmsService {

	public CmsChannel gettopchannel(CmsChannel channel);

	/**
	 * 功能:查找网站
	 * 参数:siteUrl
	 */
	public CmsSite findCmsSiteByPrimaryKey(String siteurl);
	
	
	/**
	 * 功能:查找栏目
	 * 参数:channelId
	 */
	public CmsChannel findCmsChannelByPrimaryKey(Integer channelId);
	
	/**
	 * 功能:查找文章
	 * 参数:articleId
	 */
	public CmsArticle findCmsArticleByPrimaryKey(Integer articleId);
	
	/**
	 * 功能:查找所有的资源
	 * 
	 */	
	public Set<CmsResource>  findAllCmsResources();
	
	/**
	 * 功能:将网站的所有栏目分类
	 * 
	 */	
	public void separateChannels(List<CmsChannel> siteChannels,List<CmsChannel> homeChannels,List<CmsChannel> topChannels,List<CmsChannel> homeChannelsTwo,List<CmsChannel> linkChannels,List<CmsChannel> recommendChannels);

	/**
	 * 功能:得到作为左边栏的栏目们
	 * 
	 */
	public List<CmsChannel> getLeftChannels(CmsChannel channel);

	/**
	 * 功能:得到栏目对应的文章，排序是按照sort和createTime。
	 * 
	 */	
	public List<CmsArticle>  getArticlesListByChannelOrderBySortCreateTime(CmsChannel channel);
	
	/**
	 * 功能:得到页面的左边栏的父栏目。
	 * 
	 */	
	public CmsChannel getFatherChannelForLeftChannels(CmsChannel channel);
	
	/**
	 * 功能:得到本网站所有的链接。
	 * 
	 */	
	public List<CmsLink> getAllLinksBySite(CmsSite site);
	
	/**
	 * 功能:得到本栏目的上一篇文章。
	 * 
	 */	
	public CmsArticle getPreviousArticle(Integer articleId);
	
	/**
	 * 功能:得到本栏目的下一篇文章。
	 * 
	 */		
	public CmsArticle getNextArticle(Integer articleId);
	
	/**
	 * 功能:栏目的分页内容。
	 * 
	 */			
	public List<CmsArticle>  getArticlesListByChannelOrderBySortCreateTimePagesSeparated(CmsChannel channel,Integer page,Integer max);

	/**
	 * 功能:获取网站的logo。
	 * 
	 */	
	public CmsResource findTheSiteLogo(CmsSite site);
	
 /***
  * 获得群星璀璨栏目下面的所有文章；
  *   id写死了，求放过！！！
  *   王建羽
  *   2015-9-25
  *   
  * 
  */
	public List<CmsArticle>getAlumniStarByChannelId();
	
	
	/**
	 * 功能:获取子栏目
	 * 
	 */	
	
	
	public List<CmsChannel> getchildchannel(int id);
	/**
	 * 功能:根据栏目获取文章
	 * 
	 */	
	public List<CmsArticle> getCmsArticleByChannelId(int id);

	public List<CmsArticle> getassetpagelist(int channelId,
			Integer currpage, int pagesize);
	
	
	public List<CmsArticle> getArticleByreadNum();
	
	
	
	public List<CmsArticle> getArticleBysearchcontent(String searchcontent);
	
	
	public List<CmsChannel> getAlltopChannels();
	
	
	
	public List<CmsResource> findCmsResourceByVideo();
	/**********************************************************************************************
	 * @功能：查找需要的滚动图片
	 * @作者：操磊
	 * @时间：2016年12月1日16:18:39
	 *********************************************************************************************/
	public List<CmsLink> findCmsTag(int id);
}