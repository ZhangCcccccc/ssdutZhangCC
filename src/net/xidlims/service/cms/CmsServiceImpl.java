package net.xidlims.service.cms;

import net.xidlims.dao.*;

import net.xidlims.domain.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Article entities
 * 
 */

@Service("CmsService")
@Transactional
public class CmsServiceImpl implements CmsService {

	@Autowired
	private CmsSiteDAO siteDAO;
	@Autowired
	private CmsChannelDAO channelDAO;
	@Autowired
	private CmsArticleDAO articleDAO; 
	 
	@Autowired
	private CmsArticleService articleService;
	@Autowired
	private CmsResourceDAO resourceDAO;	
	@Autowired
	private CmsLinkDAO linkDAO;		
	
	public CmsServiceImpl() {
	}
	
	/**
	 * 功能:查找网站
	 * 参数:siteUrl
	 */
	@Transactional
	public CmsSite findCmsSiteByPrimaryKey(String siteurl) {
 		return siteDAO.findCmsSiteByPrimaryKey(siteurl);
	}
	
	/**
	 * 功能:查找栏目
	 * 参数:channelId
	 */
	@Transactional
	public CmsChannel findCmsChannelByPrimaryKey(Integer channelId) {
		return channelDAO.findCmsChannelByPrimaryKey(channelId);
	}
	
	
	/**
	 * 功能:查找文章
	 * 参数:articleId
	 */
	@Transactional
	public CmsArticle findCmsArticleByPrimaryKey(Integer articleId) {
		return articleDAO.findCmsArticleByPrimaryKey(articleId);
	}
	
	@Transactional	
	public Set<CmsResource>  findAllCmsResources(){
		Set<CmsResource>  resources = resourceDAO.findAllCmsResources(-1, -1);
		return resources;
	}
	
	@Override
	public void separateChannels(List<CmsChannel> siteChannels,List<CmsChannel> homeChannels,List<CmsChannel> topChannels,List<CmsChannel> homeChannelsTwo,List<CmsChannel> linkChannels,List<CmsChannel> recommendChannels){
		int i = 0;
		for (CmsChannel channel : siteChannels) {
			if (channel.getCmsTags().isEmpty() == false) {
				i = channel.getCmsTags().iterator().next().getId().intValue();
				if (1 == i) {
					topChannels.add(channel);
				} else if (i == 3) {
					homeChannels.add(channel);
				} else if (i == 2) {
					homeChannelsTwo.add(channel);
				} else if (i == 5) {
					linkChannels.add(channel);
				} else if (i == 7) {
					recommendChannels.add(channel);
				}
			}
		}		
	}
	
	@Override
	public List<CmsChannel> getLeftChannels(CmsChannel channel){
		String sql = null;
		//如果该栏目有子栏目的话，就把它的子栏目作为文章页面的左边栏
/*		if (channel.getChannels()!=null) {		*/
		if (channel.getCmsChannels().isEmpty()==false) {
			//这里的栏目没有排序
			sql="select ch from CmsChannel ch where ch.cmsChannel.id="+channel.getId()+" order by ch.sort,createTime desc";
			return channelDAO.executeQuery(sql);
			//如果该栏目没有子栏目，则将其兄弟栏目作为左边栏。当然前提是其有兄弟栏目。如果它是一级栏目，没有父栏目，又没有子栏目，则是一个光棍栏目，那就没有左边栏
		} else {
			if (channel.getCmsChannel() != null) {
				//这里的栏目也没有排序，后期还要整理
				sql="select ch from CmsChannel ch where ch.cmsChannel.id="+channel.getCmsChannel().getId()+" order by ch.sort,ch.createTime desc";
				return channelDAO.executeQuery(sql);
			}
		}
		return null;
	}
	
	@Override
	public List<CmsArticle>  getArticlesListByChannelOrderBySortCreateTime(CmsChannel channel){
		//如果该栏目下面有文章,则获取相应的所有文章并排序
		if (channel.getCmsArticles().isEmpty() == false) {
			return articleService.findArticlesByChannelIdOrderBySortAndCreateTimeDesc(channel.getId()); 
			//如果该栏目没有文章，则去获取其第一个子栏目的文章。
		} else {
			if (channel.getCmsChannels().isEmpty() == false) {
				String sql = "select ch from CmsChannel ch where ch.cmsChannel.id="+channel.getId()+" order by ch.sort,ch.createTime desc";
				return articleService.findArticlesByChannelIdOrderBySortAndCreateTimeDesc(channelDAO.executeQuery(sql).get(0).getId());
			}
		}
		return null;
	}
	
	@Override
	public CmsChannel getFatherChannelForLeftChannels(CmsChannel channel){
		if(channel.getCmsChannels().isEmpty()==false){
			return channel;
		}else{
			if(channel.getCmsChannel()!=null){
				return channel.getCmsChannel();
			}
		}
		return channel;
	}
	
	/**
	 * 功能:查找栏目
	 * 参数:channelId
	 */
	@Override
	public List<CmsLink> getAllLinksBySite(CmsSite site){
		if(site==null){
			return null;
		}
		String sql="select l from CmsLink l where l.site.siteurl='"+site.getSiteurl()+"' ";
		List<CmsLink>  links = linkDAO.executeQuery(sql);
		return links;
	}
	
	@Override
	public CmsArticle getPreviousArticle(Integer articleId){
		CmsArticle article = findCmsArticleByPrimaryKey(articleId);		
		CmsChannel channel = article.getCmsChannel();
		List<CmsArticle>  articles = getArticlesListByChannelOrderBySortCreateTime(channel);
		int i=1;
		int n=0;
		for(CmsArticle a : articles){
			if(a.getId().intValue()==articleId.intValue()){
				n=i;
			}
			i++;
		}
		int previousArticleId = 0;
		if(n==1){
			previousArticleId=articleId.intValue();
		}else{
			i=1;
			for(CmsArticle a : articles){
				if(i==n-1){
					previousArticleId=a.getId().intValue();
				}
				i++;
			}
		}
		return  findCmsArticleByPrimaryKey(previousArticleId);
	}
	
	@Override
	public CmsArticle getNextArticle(Integer articleId){
		CmsArticle article = findCmsArticleByPrimaryKey(articleId);		
		CmsChannel channel = article.getCmsChannel();
		List<CmsArticle>  articles = getArticlesListByChannelOrderBySortCreateTime(channel);
		int i=1;
		int n=0;
		for(CmsArticle a : articles){
			if(a.getId().intValue()==articleId.intValue()){
				n=i;
			}
			i++;
		}
		int nextArticleId = 0;
		if(n==articles.size()){
			nextArticleId=articleId.intValue();
		}else{
			i=1;
			for(CmsArticle a : articles){
				if(i==n+1){
					nextArticleId=a.getId().intValue();
				}
				i++;
			}
		}
		return  findCmsArticleByPrimaryKey(nextArticleId);
	}
	
	@Override
	public List<CmsArticle>  getArticlesListByChannelOrderBySortCreateTimePagesSeparated(CmsChannel channel,Integer page,Integer max){
		//如果该栏目下面有文章,则获取相应的所有文章并排序
		if (channel.getCmsArticles().isEmpty() == false) {
			String sql = "select a from CmsArticle a where a.cmsChannel.id = "+channel.getId()+" order by a.sort ,a.createTime desc";
			Query q=articleDAO.createQuery(sql, (page-1)*max, max);
			@SuppressWarnings("unchecked")
			List<CmsArticle> as=new LinkedList<CmsArticle>(q.getResultList());
			return as; 
			//如果该栏目没有文章，则去获取其第一个子栏目的文章。
		} else {
			if (channel.getCmsChannels().isEmpty() == false) {
				String sql = "select ch from CmsChannel ch where ch.cmsChannel.id="+channel.getId()+" order by ch.sort,ch.createTime desc";
				String sql1 = "select a from CmsArticle a where a.cmsChannel.id = "+channelDAO.executeQuery(sql).get(0).getId()+" order by a.sort ,a.createTime desc";
				Query q=articleDAO.createQuery(sql1, (page-1)*15, 15);
				@SuppressWarnings("unchecked")
				List<CmsArticle> as=new LinkedList<CmsArticle>(q.getResultList());
				return as; 
			}
		}
		return null;
	}
	
	@Override
	public CmsResource findTheSiteLogo(CmsSite site){
		if(site==null){
			return null;
		}
		String sql= "select res from CmsResource res where 1=1 ";
		if(site.getImageResource()!=null&&!site.getImageResource().equals("")){
		sql += "res.id="+site.getImageResource().intValue();
		}
		
		List<CmsResource>  ress = resourceDAO.executeQuery(sql);
		if(ress.size()>0){
			return ress.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<CmsArticle> getAlumniStarByChannelId() {
		// TODO Auto-generated method stub
		String sql="select i from CmsArticle i where i.cmsChannel.id='83'";
		sql+="order by createTime desc";
		return articleDAO.executeQuery(sql, 0,-1);
	}
	
	
	@Override
	public CmsChannel gettopchannel(CmsChannel channel) {
		if(channel.getCmsChannel()==null){
			return channel;
		}
		else{
			int id=channel.getCmsChannel().getId();
		return channelDAO.findCmsChannelById(id);}
	}
	
	
	@Override
	public List<CmsChannel> getchildchannel(int id){
		String sql="select i from CmsChannel i where i.cmsChannel.id="+id;
		sql+="order by createTime desc";
		return channelDAO.executeQuery(sql, 0,-1);
	}
	@Override
	public List<CmsArticle> getCmsArticleByChannelId(int id){
		String sql="select i from CmsArticle i where i.cmsChannel.id="+id;
		return articleDAO.executeQuery(sql, 0,-1);
	}
	public List<CmsArticle> getassetpagelist(CmsArticle articles,int currpage,int pagesize){
		String sql = "select a   from CmsArticle a where 1=1 ";
		return articleDAO.executeQuery(sql, (currpage - 1) * pagesize, pagesize);
	}

	@Override
	public List<CmsArticle> getassetpagelist(int channelId,
			Integer currpage, int pagesize) {
		String sql = "select a  from CmsArticle a where a.cmsChannel.id= "+channelId;
		 
		sql+=" order by IFNULL(a.sort,1000), a.createTime desc";
		return articleDAO.executeQuery(sql, (currpage - 1) * pagesize, pagesize);
	}

	public List<CmsArticle> getArticleByreadNum(){
		String sql = "select a   from CmsArticle a where 1=1";
		sql+="order by readNum desc";
		return articleDAO.executeQuery(sql, 0,2);
	}
	public List<CmsArticle> getArticleBysearchcontent(String searchcontent){
		String sql = "select a   from CmsArticle a where a.title like '%"+searchcontent+"%'";
		return articleDAO.executeQuery(sql, 0,1);
	}
	public List<CmsChannel> getAlltopChannels(){
		String sql="select i from CmsChannel i where i.cmsChannel.id is null";
		return channelDAO.executeQuery(sql, 0,-1);
	}
	public List<CmsResource> findCmsResourceByVideo(){
		String sql="select r from CmsResource r where r.imageVideo=1";
		return resourceDAO.executeQuery(sql, 0,1);
	}
	
	/**********************************************************************************************
	 * @功能：查找需要的滚动图片
	 * @作者：操磊
	 * @时间：2016年12月1日16:18:39
	 *********************************************************************************************/
	public List<CmsLink> findCmsTag(int id){
		String sql ="select c from CmsLink c where c.cmsTag.id = " +id;
		return linkDAO.executeQuery(sql,0,-1);
	}
}
