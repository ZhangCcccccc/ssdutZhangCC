package net.xidlims.service.cms;

import net.xidlims.dao.CmsArticleDAO;
import net.xidlims.dao.AuthorityDAO;
import net.xidlims.dao.CmsChannelDAO;
import net.xidlims.dao.CmsLinkDAO;
import net.xidlims.dao.CmsResourceDAO;
import net.xidlims.dao.CmsSiteDAO;
import net.xidlims.dao.CmsTagDAO;
import net.xidlims.dao.CmsTemplateDAO;
import net.xidlims.dao.UserDAO;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.Authority;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.CmsTemplate;
import net.xidlims.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for User entities
 * 
 */

@Service("CmsSystemService")
@Transactional
public class CmsSystemServiceImpl implements CmsSystemService {

	
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private CmsChannelDAO channelDAO;
	@Autowired
	private CmsSiteDAO siteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CmsArticleDAO articleDAO;
	@Autowired
	private CmsTemplateDAO templateDAO;
	@Autowired
	private CmsResourceDAO resourceDAO;
	@Autowired
	private CmsLinkDAO linkDAO;
	@Autowired
	private CmsTagDAO tagDAO;

	/**
	 * 功能:显示所有网站
	 * 
	 */
	@Transactional
	public Set<CmsSite> loadSites() {
		return siteDAO.findAllCmsSites();
	}
	
	/**
	 * 功能:保存网站
	 */
	@Transactional
	public CmsSite saveSite(CmsSite site) {
		site = siteDAO.store(site);
		siteDAO.flush();
		return site;
	}
	
	/**
	 * 功能:删除网站
	 */
	@Transactional
	public void deleteSite(CmsSite site) {
		siteDAO.remove(site);
		siteDAO.flush();
	}

	@Override
	public void deleteChannel(CmsChannel channel) {
		// TODO Auto-generated method stub
		channelDAO.remove(channel);
		channelDAO.flush();		
	}

	@Override
	public List<CmsChannel> loadChannels() {
		// TODO Auto-generated method stub
		CmsSite currentSite = new CmsSite();
		String sql = "select s from CmsSite s where s.current=1";
		List<CmsSite> sites = siteDAO.executeQuery(sql);
		if(sites.size()>0){ 
			currentSite = sites.get(0);
			sql = "select ch from CmsChannel ch  where ch.cmsSite.siteurl='"+currentSite.getSiteurl()+"' ";
			List<CmsChannel>  currentSiteChannels = channelDAO.executeQuery(sql);
			return currentSiteChannels;
		}		
		return null;
	}

	@Override
	public void saveChannel(CmsChannel channel) {
		channelDAO.store(channel);
		channelDAO.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsArticle>  loadArticles(Integer page,Integer max,HttpServletRequest request) {
		//先找到当前正处于编辑状态的网站
		CmsSite currentSite = new CmsSite();
		String articleName=request.getParameter("articleName");
		String sql = "select s from CmsSite s where s.current=1";
		List<CmsSite> sites = siteDAO.executeQuery(sql);
		if(sites.size()>0){ 
			currentSite = sites.get(0);
			List<CmsArticle>  articles = new ArrayList<CmsArticle>();
			sql="select article from CmsArticle article where article.cmsChannel.cmsSite.siteurl='"+currentSite.getSiteurl()+"'";
			if(articleName!=null && !equals("")){
			sql += " and article.title like '%"+articleName+"%'";
			}
			Query q = articleDAO.createQuery(sql,(page-1)*max , max);
			articles =  new ArrayList<CmsArticle>(q.getResultList());
			return articles;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override	
	public Integer countArticlesBySite(HttpServletRequest request){
		//先找到当前正处于编辑状态的网站
		CmsSite currentSite = new CmsSite();
		String sql = "select s from CmsSite s where s.current=1";
		List<CmsSite> sites = siteDAO.executeQuery(sql);
		String articleName=request.getParameter("articleName");
		if(sites.size()>0){ 
			currentSite = sites.get(0);
			sql="select count(article) from CmsArticle article where article.cmsChannel.cmsSite.siteurl='"+currentSite.getSiteurl()+"'  ";
			if(articleName!=null&&!equals("")){
				sql +=" and article.title like '%"+articleName+"%'";}
			return ((Long) articleDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
		}else{
			return 0;
		}

	}
	

	@Override
	public void saveArticle(CmsArticle article) {
		// TODO Auto-generated method stub
		article = articleDAO.store(article);
		articleDAO.flush();
		
	}
	
	@Override	
	public  Set<User>  loadUsers(){
		return userDAO.findAllUsers();
	}
	
	
	@Override	
	public  Set<CmsTemplate>  loadTemplates(){
		return templateDAO.findAllCmsTemplates();
	}
	
	
	@Override	
	public  Set<CmsResource>  loadResources(){
		return resourceDAO.findAllCmsResources();
	}

	@Override	
	public Set<CmsResource>   findResourceByImageVideo(Integer type){
		return resourceDAO.findCmsResourceByImageVideo(type); 
	}
	
	@Override		
	public void savaResource(CmsResource resource){
		// TODO Auto-generated method stub
		resource= resourceDAO.store(resource);
		resourceDAO.flush();		
	}
	
	@Override		
	public void saveUser(User user){
		user = userDAO.store(user);
		userDAO.flush();				
	} 
	
	@Override		
	public List<CmsLink>  findCurrentSiteLinks(){
		CmsSite site = findCurrentSite();
		if(site!=null){
			String sql = "select mylink  from CmsLink  mylink where mylink.cmsSite.siteurl='"+site.getSiteurl()+"'  ";
			return linkDAO.executeQuery(sql);
		}else{
			return null;
		}
	}
	
	@Override			
	public CmsSite findCurrentSite(){
		String sql = "select s from CmsSite s where s.current=1";
		List<CmsSite> sites = siteDAO.executeQuery(sql);		
		if(sites.size()>0)
			return sites.get(0);
		else
			return null;
	}
	
	@Override		
	public CmsLink findLinkByPrimaryKeyId(Integer id){
		return linkDAO.findCmsLinkByPrimaryKey(id);
	}
	
	@Override	
	public Set<CmsTag>  findAllTags(){
		return tagDAO.findAllCmsTags();
	}
	
	@Override		
	public  void saveLink(CmsLink link){
		link = linkDAO.store(link);
		linkDAO.flush();
	}
	
	@Override
	public void deleteArticle(CmsArticle article) {
		// TODO Auto-generated method stub
		articleDAO.remove(article);
		articleDAO.flush();
	}
	
	@Override
	public void deleteResource(Integer Id){
		resourceDAO.remove(resourceDAO.findCmsResourceByPrimaryKey(Id));
		resourceDAO.flush();
	}
	
	@Override
	public void deleteResource(CmsResource resource){
		resourceDAO.remove(resource);
		resourceDAO.flush();
	}
	
	
	@Override
	public void deleteUser(String userid){
		userDAO.remove(userDAO.findUserByPrimaryKey(userid));
		userDAO.flush();
	}

	@Override
	public void deleteLink(Integer IdKey){
		linkDAO.remove(linkDAO.findCmsLinkByPrimaryKey(IdKey));
		linkDAO.flush();
	}
	
	@Override	
	public Set<CmsTag>  findTagsByTypeId(String category){
		return  tagDAO.findCmsTagByCategory(category);
	}
	
	@Override	
	public void  getOneSiteIfNotHave(){
		Set<CmsSite> sites = siteDAO.findAllCmsSites();
		List<CmsSite>  sitess = new ArrayList<CmsSite>(sites);
		int a =0 ;
		for(CmsSite s :sitess){
			if(s.getCurrent()==1)
				a=1;
		}
		if(a==0){
			sitess.get(0).setCurrent(1);
		}
	}

	@Override
	public List<CmsChannel> findTopCmsChannels() {
		// TODO Auto-generated method stub
		CmsSite currentSite = new CmsSite();
		String sql = "select s from CmsSite s where s.current=1";
		List<CmsSite> sites = siteDAO.executeQuery(sql);
		List<CmsChannel> cmsChannels = new ArrayList<CmsChannel>();
		if(sites.size()>0){ 
			currentSite = sites.get(0);
			sql = "select ch from CmsChannel ch  where ch.cmsSite.siteurl='"+currentSite.getSiteurl()+"' and ch.state = '1' and ch.cmsChannel.id is null ";
			cmsChannels = channelDAO.executeQuery(sql);
		}		
		return cmsChannels;
	}

	@Override
	public List<CmsChannel> findBrotherCmsChannelList(CmsChannel cmsChannel) {
		// TODO Auto-generated method stub
		String sql = "from CmsChannel c where state = '1' and c.cmsChannel.id = '"+cmsChannel.getCmsChannel().getId()+"'";
		List<CmsChannel> cmsChannels = channelDAO.executeQuery(sql, 0,-1);
		return cmsChannels;
	}
	
	
}
