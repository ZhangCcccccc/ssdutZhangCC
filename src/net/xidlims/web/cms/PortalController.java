package net.xidlims.web.cms;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.CmsArticleDAO;
import net.xidlims.dao.CmsChannelDAO;
import net.xidlims.dao.CmsDocumentDAO;
import net.xidlims.dao.CmsLinkDAO;
import net.xidlims.dao.CmsResourceDAO;
import net.xidlims.dao.CmsTagDAO;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsDocument;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.CmsTemplate;

import net.xidlims.service.cms.CmsArticleService;
import net.xidlims.service.cms.CmsChannelService;
import net.xidlims.service.cms.CmsService;
import net.xidlims.service.cms.CmsDocumentService;
import net.xidlims.service.common.ShareService;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * cms的控制器，包括生成网站的主页，栏目列表页，新闻页
 * 
 */
@Controller("PortalController")
public class PortalController {

	@Autowired
	private CmsService cmsService;
	@Autowired
	private CmsChannelDAO channelDAO;
	@Autowired
	private CmsDocumentDAO documentDAO;
	@Autowired
	private CmsTagDAO tagDAO;
	@Autowired
	private CmsChannelService channelService;
	@Autowired
	private CmsArticleService articleService;	
	@Autowired
	private CmsArticleDAO articleDAO;	
	@Autowired
	private ShareService shareService;
	@Autowired
	private CmsDocumentService documentService;
	@Autowired
	private CmsLinkDAO linkDAO;
	
	/**
	 * 功能:网站首页 参数:siteUrl;
	 * @author 胡科；
	 * @param 网站的字符标号 siteUrl
	 * @return 提供首页需要的数据
	 */
	@RequestMapping(value = "//{siteUrl}")
	public ModelAndView index(@PathVariable String siteUrl) {
		ModelAndView mav = new ModelAndView();
		// 获取网站
		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("site", site); 
		//获取网站logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		// 获取栏目。这里还要改，是获取本网站的栏目
		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		Set<CmsLink> link = linkDAO.findAllCmsLinks();//查找所有的链接信息
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels, homeChannelsTwo, linkChannels, recommendChannels);
		
		mav.addObject("siteChannels", siteChannels); 
		mav.addObject("topChannels", topChannels);
		mav.addObject("homeChannels", homeChannels);
		mav.addObject("homeChannelsTwo", homeChannelsTwo);
		//原有的链接栏目
		mav.addObject("linkChannels", linkChannels);
		//显示链接资源
		mav.addObject("links", link);
		mav.addObject("recommendChannels", recommendChannels);
		mav.addObject("siteUrl", siteUrl);
		// 获取模板，并决定跳转到对应的哪个文件夹下的页面
		CmsTemplate template = site.getCmsTemplate();
		mav.setViewName(template.getName() + "/index.jsp");
		//得到本网站所有的链接
		List<CmsLink>  allLinks = cmsService.getAllLinksBySite(site);
		mav.addObject("allLinks", allLinks);
		//得到推荐新闻
		String  str = "0";//新建字符串用于存放得到的推荐新闻的文章的Id
		for(CmsArticle a : articleDAO.executeQuery("select t.articles from CmsTag t where t.id=15")){
			str+=","+(a.getId().toString());
		}
		//根据推荐新闻的文章id的时间排序得到最新的推荐新闻
		String sql = "select a from CmsArticle a where a.id in ("+str+") order by a.createTime desc";
		if(articleDAO.executeQuery(sql).size()!=0){//判断文章的列表是否为空，不为空则映射到前端
			CmsArticle recomNew = articleDAO.executeQuery(sql).get(0);
			mav.addObject("recomNew", recomNew);
		} else {
			mav.addObject("recomNew", new CmsArticle()); //判断文章的列表是否为空，为空则新建new一个文章信息映射到前端
		}
		return mav;
	}

//	/**
//	 * 功能:查看网站栏目 
//	 * @author 胡科
//	 * @param  siteUrl,channelId,page
//	 * @return mav
//	 */
//	@RequestMapping(value = "//{siteUrl}/channel/{channelId}/{page}")
//	public ModelAndView channel(@PathVariable String siteUrl, @PathVariable Integer channelId,@PathVariable Integer page) {
//		ModelAndView mav = new ModelAndView();
//		// 获取网站
//		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
//		mav.addObject("siteUrl", siteUrl);
//		mav.addObject("site", site); 
//		//查找网站的logo
//		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
//		//flash幻灯图片
//		mav.addObject("allLinks", cmsService.getAllLinksBySite(site));
//		// 获取模板
//		CmsTemplate template = site.getCmsTemplate();
//		mav.setViewName(template.getName() + "/channel.jsp");
//
//		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
//		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
//		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
//		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
//		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
//		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
//		//将查到的栏目按照标签进行分类。
//		cmsService.separateChannels(siteChannels, homeChannels, topChannels, homeChannelsTwo, linkChannels, recommendChannels);
//		mav.addObject("topChannels", topChannels);
//		mav.addObject("homeChannels", homeChannels);
//		mav.addObject("homeChannelsTwo", homeChannelsTwo);
//		mav.addObject("linkChannels", linkChannels);
//		mav.addObject("recommendChannels", recommendChannels);
//		CmsChannel channel = cmsService.findCmsChannelByPrimaryKey(channelId);
//		//如果该栏目有子拉姆，获取该栏目的子栏目；若没有，则获取该栏目的兄弟栏目 
//		mav.addObject("channels", cmsService.getLeftChannels(channel)); 
//		
//		//以下这个功能，并不是所有的模板都要使用。这个是提供给左边栏的上面那个父栏目的。 
//		mav.addObject("fatherChannel", cmsService.getFatherChannelForLeftChannels(channel));
//		mav.addObject("channel", channel);
//		//做分页
//		int max=15;
//		int maxpage=1;
//		//获得要分页的那个栏目
//		CmsChannel articleListChannel = new CmsChannel();
//		if (channel.getCmsArticles().isEmpty() == false) {
//			articleListChannel=channel;
//		}else{
//			if (channel.getCmsChannels().isEmpty() == false) {
//				String sql = "select ch from CmsChannel ch where ch.cmsChannel.id="+channel.getId()+" order by ch.sort,ch.createTime desc";
//				articleListChannel=channelDAO.executeQuery(sql).get(0);
//			}else{
//				articleListChannel=channel;				
//			}			
//		}		
//		mav.addObject("articleListChannel", articleListChannel); 
//		int count = articleListChannel.getCmsArticles().size();
//		mav.addObject("count", count);
//		if (page <= 1) {
//			page = 1;
//		}
//		if (count % max == 0) {
//			maxpage = count / max;
//		}
//		if (count % max > 0) {
//			maxpage = count/ max + 1;
//		}
//		if (page >= maxpage) {
//			page = maxpage;
//		}		
//		mav.addObject("articles", cmsService.getArticlesListByChannelOrderBySortCreateTimePagesSeparated(channel,page,max)); 
//		mav.addObject("max", max);
//		mav.addObject("page", page); 
//		mav.addObject("maxpage", maxpage); 
//		if(channel.getCmsChannel()!=null){
//			mav.addObject("parentChannel", channel.getCmsChannel()); 
//		}
//		if (channel.getCmsArticles().isEmpty() == true) {
//			if (channel.getCmsChannels().isEmpty() == false) {
//				mav.addObject("firstChildChannel", channelService.findAllChildrenChannelsOrderBySort(channel).get(0));
//			}
//		}
//		return mav;
//	}
//	
	/**
	 * 功能:查看网站栏目 ,每页显示记录数自适应
	 * @author 张星
	 * @param  siteUrl,channelId,page,max
	 * @return mav
	 */
	@RequestMapping(value = "/{siteUrl}/channel/{channelId}/{page}/{max}")
	public ModelAndView channel(@PathVariable String siteUrl, @PathVariable Integer channelId,@PathVariable Integer page,@PathVariable Integer max) {
		ModelAndView mav = new ModelAndView();
		// 获取网站
		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("siteUrl", siteUrl);
		mav.addObject("site", site); 
		//查找网站的logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		//flash幻灯图片
		mav.addObject("allLinks", cmsService.getAllLinksBySite(site));
		// 获取模板
		CmsTemplate template = site.getCmsTemplate();
		mav.setViewName(template.getName() + "/channel.jsp");

		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels,homeChannelsTwo,linkChannels, recommendChannels);
		mav.addObject("topChannels", topChannels);
		mav.addObject("homeChannels", homeChannels);
		mav.addObject("homeChannelsTwo", homeChannelsTwo);
		mav.addObject("linkChannels", linkChannels);
		mav.addObject("recommendChannels", recommendChannels);
		CmsChannel channel = cmsService.findCmsChannelByPrimaryKey(channelId);
		//如果该栏目有子拉姆，获取该栏目的子栏目；若没有，则获取该栏目的兄弟栏目 
		mav.addObject("channels", cmsService.getLeftChannels(channel)); 
		
		//以下这个功能，并不是所有的模板都要使用。这个是提供给左边栏的上面那个父栏目的。 
		mav.addObject("fatherChannel", cmsService.getFatherChannelForLeftChannels(channel));
		mav.addObject("channel", channel);
		//做分页
		int maxpage=1;
		//获得要分页的那个栏目
		CmsChannel articleListChannel = new CmsChannel();
		if (channel.getCmsArticles().isEmpty() == false) {
			articleListChannel=channel;
		}else{
			if (channel.getCmsChannels().isEmpty() == false) {
				String sql = "select ch from CmsChannel ch where ch.cmsChannel.id="+channel.getId()+" order by ch.sort,ch.createTime desc";
				articleListChannel=channelDAO.executeQuery(sql).get(0);
			}else{
				articleListChannel=channel;				
			}			
		}		
		mav.addObject("articleListChannel", articleListChannel); 
		int count = articleListChannel.getCmsArticles().size();
		mav.addObject("count", count);
		if (page <= 1) {
			page = 1;
		}
		if (count % max == 0) {
			maxpage = count / max;
		}
		if (count % max > 0) {
			maxpage = count/ max + 1;
		}
		if (page >= maxpage) {
			page = maxpage;
		}		
		mav.addObject("articles", cmsService.getArticlesListByChannelOrderBySortCreateTimePagesSeparated(channel,page,max)); 
		mav.addObject("max", max);
		mav.addObject("page", page); 
		mav.addObject("maxpage", maxpage); 
		if(channel.getCmsChannel()!=null){
			mav.addObject("parentChannel", channel.getCmsChannel()); 
		}
		if (channel.getCmsArticles().isEmpty() == true) {
			if (channel.getCmsChannels().isEmpty() == false) {
				mav.addObject("firstChildChannel", channelService.findAllChildrenChannelsOrderBySort(channel).get(0));
			}
		}
		return mav;
	}
	
	/**
	 * 查看网站新闻
	 * @author 胡科
	 * @param siteUrl ，articleId
	 * @return mav
	 */
//	@RequestMapping(value = "//{siteUrl}/article/{articleId}")
//	public ModelAndView content(@PathVariable String siteUrl, @PathVariable Integer articleId) {
//		ModelAndView mav = new ModelAndView();
//		// 获取网站
//		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
//		mav.addObject("siteUrl", siteUrl);
//		mav.addObject("site", site); 
//		//获取网站logo
//		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
//		//flash幻灯图片
//		mav.addObject("allLinks", cmsService.getAllLinksBySite(site));
//		// 获取模板
//		CmsTemplate template = site.getCmsTemplate();
//		mav.setViewName(template.getName() + "/article.jsp");
//
//		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
//		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
//		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
//		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
//		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
//		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
//		//将查到的栏目按照标签进行分类。
//		cmsService.separateChannels(siteChannels, homeChannels, topChannels,homeChannelsTwo,linkChannels, recommendChannels);
//		mav.addObject("topChannels", topChannels);
//		mav.addObject("homeChannels", homeChannels);		
//		mav.addObject("homeChannelsTwo", homeChannelsTwo);
//		mav.addObject("linkChannels", linkChannels);
//		//获取该Id对应的文章
//		CmsArticle article = cmsService.findCmsArticleByPrimaryKey(articleId);
//		mav.addObject("article", article);
//		int readNum = 0;
//		if(article.getReadNum()!=null){
//			readNum = article.getReadNum().intValue();
//		}
//		article.setReadNum(readNum+1);
//		articleService.saveArticle(article);
//		
//		//文章页面的左边栏的栏目切换按钮
//		CmsChannel channel = article.getCmsChannel();
//		mav.addObject("fatherChannel", cmsService.getFatherChannelForLeftChannels(channel));
//		mav.addObject("channels", cmsService.getLeftChannels(channel)); 
//		mav.addObject("channel", channel); 
//		if (channel.getCmsChannel() != null) {
//			mav.addObject("parentChannel", channel.getCmsChannel());
//		}
//		return mav;
//	}

	/**
	 * 查看网站新闻
	 * @author 胡科
	 * @param siteUrl ，articleId
	 * @return mav
	 */
	@RequestMapping(value="/{siteUrl}/previousArticle/{articleId}")
	public ModelAndView previousArticle(@PathVariable String siteUrl, @PathVariable Integer articleId){
		ModelAndView mav = new ModelAndView();
		// 获取网站
		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("siteUrl", siteUrl);
		mav.addObject("site", site); 
		//获取网站logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		//flash幻灯图片
		mav.addObject("allLinks", cmsService.getAllLinksBySite(site));
		// 获取模板
		CmsTemplate template = site.getCmsTemplate();
		mav.setViewName(template.getName() + "/article.jsp");

		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels,homeChannelsTwo,linkChannels, recommendChannels);
		mav.addObject("topChannels", topChannels);
		//寻找上一篇文章
		CmsArticle article = cmsService.getPreviousArticle(articleId);
		article.setReadNum(article.getReadNum().intValue()+1);
		articleService.saveArticle(article);
		mav.addObject("article", article);		 
		return mav;
	}
	
	
	/**
	 * 查看网站新闻
	 * @author 胡科
	 * @param siteUrl ，articleId
	 * @return mav
	 */
	@RequestMapping(value="/{siteUrl}/nextArticle/{articleId}")
	public ModelAndView nextArticle(@PathVariable String siteUrl, @PathVariable Integer articleId){
		ModelAndView mav = new ModelAndView();
		// 获取网站
		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("siteUrl", siteUrl);
		mav.addObject("site", site); 
		//获取网站logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		//flash幻灯图片
		mav.addObject("allLinks", cmsService.getAllLinksBySite(site));
		// 获取模板
		CmsTemplate template = site.getCmsTemplate();
		mav.setViewName(template.getName() + "/article.jsp");

		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		//将查到的栏目按照标签进行分类。
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		
		cmsService.separateChannels(siteChannels, homeChannels, topChannels,homeChannelsTwo,linkChannels, recommendChannels);
		mav.addObject("topChannels", topChannels);
		//寻找上一篇文章
		CmsArticle article = cmsService.getNextArticle(articleId);
		article.setReadNum(article.getReadNum().intValue()+1);
		articleService.saveArticle(article);
		mav.addObject("article", article);		 
		return mav;
	}
	
	/*
	 * 搜索功能 
	 * 
	 * */
	@RequestMapping("/alexSearch")
	public ModelAndView   alexSearch(HttpServletRequest request,@RequestParam String siteUrl){
		ModelAndView mav = new ModelAndView();
		// 获取网站
		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("site", site); 
		//获取网站logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		// 获取栏目。这里还要改，是获取本网站的栏目
		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels,homeChannelsTwo,linkChannels, recommendChannels);
		mav.addObject("siteChannels", siteChannels); 
		mav.addObject("topChannels", topChannels);
		mav.addObject("homeChannels", homeChannels);
		mav.addObject("linkChannels", linkChannels);
		mav.addObject("recommendChannels", recommendChannels);
		mav.addObject("siteUrl", siteUrl);
		 try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = new String();
		str=request.getParameter("seachValue"); 
		String sql = "select c from CmsArticle c where c.news like  '%"+str+"%'  or  c.title like '%"+str+"%' order by c.createTime desc";
		List<CmsArticle>   articles = articleDAO.executeQuery(sql);
		mav.addObject("str", str); //str是搜索时输入的内容
		mav.addObject("articles", articles); 
		mav.addObject("count", articles.size()); //输出找到的条数
		// 获取模板
		CmsTemplate template = site.getCmsTemplate();
		mav.setViewName(template.getName() + "/search.jsp");
		return mav;
	}
	/**
	 * 群星璀璨页面
	 * 王建羽 
	 * 2015-09-24 
	 * @param siteUrl
	 * @param channelId
	 * @param page
	 * @param max
	 * @return
	 */
	@RequestMapping(value = "/{siteUrl}/alumniStar/{channelId}/{page}")
	public ModelAndView alumniStar(@PathVariable String siteUrl, @PathVariable Integer channelId,@PathVariable Integer page){
		ModelAndView mav=new ModelAndView();
		// 获取网站
		CmsSite site = cmsService.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("site", site); 
		mav.addObject("allLinks", cmsService.getAllLinksBySite(site));
		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels, homeChannelsTwo, linkChannels, recommendChannels);
		mav.addObject("topChannels", topChannels);
		mav.addObject("homeChannels", homeChannels);
		mav.addObject("homeChannelsTwo", homeChannelsTwo);
		mav.addObject("linkChannels", linkChannels);
		mav.addObject("recommendChannels", recommendChannels);
		CmsChannel channel = cmsService.findCmsChannelByPrimaryKey(channelId);
		//如果该栏目有子拉姆，获取该栏目的子栏目；若没有，则获取该栏目的兄弟栏目 
		mav.addObject("channels", cmsService.getLeftChannels(channel)); 
		
		//以下这个功能，并不是所有的模板都要使用。这个是提供给左边栏的上面那个父栏目的。 
		mav.addObject("fatherChannel", cmsService.getFatherChannelForLeftChannels(channel));
		mav.addObject("AlumniStar",cmsService.getAlumniStarByChannelId());
		if(channel.getCmsChannel()!=null){
			mav.addObject("parentChannel", channel.getCmsChannel()); 
		}
		if (channel.getCmsArticles().isEmpty() == true) {
			if (channel.getCmsChannels().isEmpty() == false) {
				mav.addObject("firstChildChannel", channelService.findAllChildrenChannelsOrderBySort(channel).get(0));
			}
		}
		// 获取模板，并决定跳转到对应的哪个文件夹下的页面
		CmsTemplate template = site.getCmsTemplate();
		mav.setViewName(template.getName() + "/alumniStar.jsp");		
		return mav;
	}
	
	/**
	 * @module 校友列表
	 * @param alumniInfo
	 * @param page
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:52:15
	 */
	/*@RequestMapping("/{siteUrl}/alumniShow")	
	public ModelAndView alumniShow(@PathVariable String siteUrl,@ModelAttribute AlumniInfo alumniInfo,@RequestParam int page){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//查询表单的对象
		mav.addObject("alumniInfo", alumniInfo);
		//每页显示20条记录
		int pageSize=15;
		//查询出来的总记录条数
		int totalRecords=alumniService.findAllAlumniInfoByAlumniInfo(alumniInfo).size();
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<AlumniInfo> listAlumni=alumniService.findAllAlumniInfoByAlumniInfo(alumniInfo, page, pageSize);
		//查询出来的总记录条数
		mav.addObject("alumni",listAlumni);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);	
		// 获取网站
		Site site = cmsService.findSiteByPrimaryKey(siteUrl);
		mav.addObject("site", site); 
		//获取网站logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		// 获取栏目。这里还要改，是获取本网站的栏目
		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		Set<Link> link = linkDAO.findAllLinks();//查找所有的链接信息
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels, homeChannelsTwo, linkChannels, recommendChannels);
		
		mav.addObject("siteChannels", siteChannels); 
		mav.addObject("topChannels", topChannels);
		mav.addObject("homeChannels", homeChannels);
		mav.addObject("homeChannelsTwo", homeChannelsTwo);
		//原有的链接栏目
		mav.addObject("linkChannels", linkChannels);
		//显示链接资源
		mav.addObject("links", link);
		mav.addObject("recommendChannels", recommendChannels);
		mav.addObject("siteUrl", siteUrl);
		// 获取模板，并决定跳转到对应的哪个文件夹下的页面
		Template template = site.getTemplate();
		mav.setViewName(template.getName() + "/alumniShow.jsp");
	
		//得到本网站所有的链接
		List<Link>  allLinks = cmsService.getAllLinksBySite(site);
		mav.addObject("allLinks", allLinks);		
		return mav;
	}
	*/
	/**
	 * @module 募集信息列表
	 * @param info
	 * @param page
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:52:15
	 */
	/*@RequestMapping("/{siteUrl}/donationShow")
	public ModelAndView donationShow(@PathVariable String siteUrl,@ModelAttribute DonationInfo info,@RequestParam int page){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//查询表单的对象
		mav.addObject("donationInfo", info);
		//每页显示20条记录
		int pageSize=15;
		//查询出来的总记录条数
		int totalRecords=donationService.findAllInfoByInfo(info).size();
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<DonationInfo> listInfo=donationService.findAllInfoByInfo(info, page, pageSize);
		//用于计算捐赠的金额
		BigDecimal count = new BigDecimal(0);
		//计算总的捐款金额
		for(DonationInfo d:donationService.findAllInfoByInfo(info)){
			if(d.getDonationMoney()!=null&&!d.getDonationMoney().equals("")){
				count=count.add(d.getDonationMoney());
			}			
		}
		mav.addObject("count",count);
		mav.addObject("info",listInfo);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		// 获取网站
		Site site = cmsService.findSiteByPrimaryKey(siteUrl);
		mav.addObject("site", site); 
		//获取网站logo
		mav.addObject("logo", cmsService.findTheSiteLogo(site)); 
		// 获取栏目。这里还要改，是获取本网站的栏目
		List<CmsChannel> siteChannels = channelService.findAllChannelsBySite(site);
		List<CmsChannel> topChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> homeChannelsTwo = new ArrayList<CmsChannel>();
		List<CmsChannel> linkChannels = new ArrayList<CmsChannel>();
		List<CmsChannel> recommendChannels = new ArrayList<CmsChannel>();
		Set<Link> link = linkDAO.findAllLinks();//查找所有的链接信息
		//将查到的栏目按照标签进行分类。
		cmsService.separateChannels(siteChannels, homeChannels, topChannels, homeChannelsTwo, linkChannels, recommendChannels);		
		mav.addObject("siteChannels", siteChannels); 
		mav.addObject("topChannels", topChannels);
		mav.addObject("homeChannels", homeChannels);
		mav.addObject("homeChannelsTwo", homeChannelsTwo);
		//原有的链接栏目
		mav.addObject("linkChannels", linkChannels);
		//显示链接资源
		mav.addObject("links", link);
		mav.addObject("recommendChannels", recommendChannels);
		mav.addObject("siteUrl", siteUrl);
		// 获取模板，并决定跳转到对应的哪个文件夹下的页面
		Template template = site.getTemplate();
		mav.setViewName(template.getName() + "/donationShow.jsp");	
		//得到本网站所有的链接
		List<Link>  allLinks = cmsService.getAllLinksBySite(site);
		mav.addObject("allLinks", allLinks);			
		return mav;
	}*/
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
	/**
	 * 下载附件
	 */
	@RequestMapping("/cc/download")
	public void download(@RequestParam Integer idKey, HttpServletRequest request,HttpServletResponse response) throws Exception {
		CmsDocument document = documentDAO.findCmsDocumentByPrimaryKey(idKey);
		documentService.downloadFile(document, request, response);
	}


}