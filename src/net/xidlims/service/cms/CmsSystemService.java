package net.xidlims.service.cms;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.CmsTemplate;
import net.xidlims.domain.User;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for User entities
 * 
 */
public interface CmsSystemService {

	/**
	 * 功能:显示所有网站
	 * 
	 */
	public Set<CmsSite> loadSites();
	
	/**
	 * 功能:保存网站
	 */
	public CmsSite saveSite(CmsSite site);
	
	/**
	 * 功能:删除网站
	 */
	public void deleteSite(CmsSite site);

	/**
	 * 功能:删除栏目
	 */
	public void deleteChannel(CmsChannel channel);

	/**
	 * 功能:查找栏目
	 */
	public List<CmsChannel> loadChannels();

	/**
	 * 功能:保存栏目
	 */
	public void saveChannel(CmsChannel channel);

	/**
	 * 功能:查找所有文章
	 */
	public List<CmsArticle>  loadArticles(Integer page,Integer max,HttpServletRequest request);

	/**
	 * 功能:删除文章
	 */
	public void deleteArticle(CmsArticle article);

	/**
	 * 功能:保存文章
	 */
	public void saveArticle(CmsArticle article);
	
	/**
	 * 功能:找到所有的用户
	 */	
	public  Set<User>  loadUsers();
	
	/**
	 * 功能:找到所有的模板
	 */		
	public  Set<CmsTemplate>  loadTemplates();
	
	/**
	 * 功能:找到所有的资源
	 */			
	public  Set<CmsResource>  loadResources();
	
	/**
	 * 功能:找到所有的资源
	 */		
	public Set<CmsResource>   findResourceByImageVideo(Integer type);
	
	/**
	 * 功能:保存资源
	 */	
	public void savaResource(CmsResource resource);
	
	/**
	 * 功能:保存用户
	 */	
	public void saveUser(User user);
	
	/**
	 * 功能:找到某一个网站对应的所有的链接
	 */	
	public List<CmsLink>  findCurrentSiteLinks();
	
	/**
	 * 功能:找到目前在编辑状态的网站。
	 */		
	public CmsSite findCurrentSite();
	
	/**
	 * 功能:找到主键对应的链接。
	 */		
	public CmsLink findLinkByPrimaryKeyId(Integer id);
	
	/**
	 * 功能:找到所有的标签。
	 */			
	public Set<CmsTag>  findAllTags();
	
	/**
	 * 功能:保存链接。
	 */	
	public  void saveLink(CmsLink link);
	
	/**
	 * 功能:删除一条资源记录。
	 */		
	public void deleteResource(Integer Id);
	
	/**
	 * 功能:删除一条资源记录。
	 */		
	public void deleteResource(CmsResource resource);
	
	/**
	 * 功能:删除一个用户。
	 */		
	public void deleteUser(String userid);
	
	/**
	 * 功能:删除一个链接。
	 */			
	public void deleteLink(Integer IdKey);
	
	/**
	 * 功能:根据category查找tags。
	 */			
	public Set<CmsTag>  findTagsByTypeId(String category);
	
	/**
	 * 功能:在没有网站设置为当前网站的时候，挑选一个做为当前编辑网站，要加在所有的后台方法前面。。
	 */	
	public void  getOneSiteIfNotHave();
	
	/**
	 * 功能:查找得到当前网站有多少条article。
	 */		
	public Integer countArticlesBySite(HttpServletRequest request);

	/**
	 * 获取当前活动网站的可显示的顶级栏目
	 * @return
	 */
	public List<CmsChannel> findTopCmsChannels();

	/**
	 * 根据当前栏目查询平级栏目列表
	 * @return
	 */
	public List<CmsChannel> findBrotherCmsChannelList(CmsChannel cmsChannel);
	

}