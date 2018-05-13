package net.xidlims.web.cms;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.BindException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.inject.servlet.RequestParameters;
import net.xidlims.dao.CmsArticleDAO;
import net.xidlims.dao.CmsChannelDAO;
import net.xidlims.dao.CmsDocumentDAO;
import net.xidlims.dao.CmsResourceDAO;
import net.xidlims.dao.CmsSiteDAO;
import net.xidlims.dao.CmsTagDAO;
import net.xidlims.dao.UserDAO;

import net.xidlims.domain.CmsArticle;
import net.xidlims.domain.CmsChannel;
import net.xidlims.domain.CmsDocument;
import net.xidlims.domain.CmsLink;
import net.xidlims.domain.CmsResource;
import net.xidlims.domain.CmsSite;
import net.xidlims.domain.CmsTag;
import net.xidlims.domain.User;

import net.xidlims.service.cms.CmsArticleService;
import net.xidlims.service.cms.CmsChannelService;
import net.xidlims.service.cms.CmsDocumentService;
import net.xidlims.service.cms.ImageService;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.cms.CmsSystemService;
import net.xidlims.service.system.UserDetailService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

/**
 *	系统的控制器，包括栏目文章的操作
 */
@RequestMapping("admin")
@Controller("CmsSystemController")
public class CmsSystemController {

	
	@Autowired
	private CmsSystemService systemService;
	@Autowired
	private CmsChannelDAO channelDAO;
	@Autowired
	private CmsSiteDAO siteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CmsArticleDAO articleDAO;
	@Autowired
	private CmsChannelService channelService;
	@Autowired
	private CmsDocumentDAO documentDAO;
	@Autowired
	private CmsDocumentService documentService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private CmsArticleService articleService;
	@Autowired
	private CmsResourceDAO resourceDAO;	
	@Autowired
	private ImageService imageService;	
	@Autowired
	private CmsTagDAO tagDAO;	
	@Autowired
	private UserDetailService userDetailService;	

	/**
	 * 功能:登陆后的默认页面
	 * 参数:
	 */
	@RequestMapping("/")
	public ModelAndView admin(HttpSession session) { 
		ModelAndView mav = new ModelAndView();
		//如果没有网站处于当前编辑状态，那么就找一个，设为当前编辑状态。
		systemService.getOneSiteIfNotHave();
		User user = shareService.getUser();
		if (user==null) {
			mav.setViewName("/cms/cindex");
		}else {
			mav.addObject("user", user);
			mav.setViewName("system/admin.jsp");
		}
		return mav;
	}
	
	/**
	 * 功能:登陆后的默认页面
	 * 参数:
	 */
	@RequestMapping("/log")
	public ModelAndView log() {

		ModelAndView mav = new ModelAndView();
		//如果没有网站处于当前编辑状态，那么就找一个，设为当前编辑状态。
		systemService.getOneSiteIfNotHave();
		User user = shareService.getUser();
		if (user==null) {
			mav.setViewName("/login.jsp");
		}else {
			mav.addObject("user", user);
			mav.setViewName("system/admin.jsp");
		}
		return mav;
	}
	
	/*=================================网站=====================================================*/
	/**
	 * 功能:显示所有网站
	 * 
	 */
	@RequestMapping("/site")
	public ModelAndView listSites() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("sites", systemService.loadSites());
		mav.setViewName("system/site/sitesList.jsp");
		return mav;
	}
	
	/**
	 * 功能:创建或者编辑网站
	 * 
	 */
	@RequestMapping("/newSite")
	public ModelAndView newSite() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("site", new CmsSite());
		mav.addObject("newFlag", true);
		mav.addObject("templates", systemService.loadTemplates());
		mav.addObject("resources", systemService.loadResources());
		mav.addObject("users",userDetailService.findAllUsers("1"));
		mav.setViewName("system/site/editSite.jsp");
		return mav;
	}	
	
	/**
	 * 功能:创建或者编辑网站
	 * 
	 */
	@RequestMapping("/editSite")
	public ModelAndView editSite(@RequestParam String siteUrl) {
		ModelAndView mav = new ModelAndView();
		CmsSite site = siteDAO.findCmsSiteByPrimaryKey(siteUrl);
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("site", site); 
		mav.addObject("newFlag", true);
		mav.addObject("templates", systemService.loadTemplates());
		mav.addObject("resources", systemService.loadResources());
		mav.addObject("users",userDetailService.findAllUsers("1"));
		mav.setViewName("system/site/editSite.jsp");
		return mav;
	}
	
	/**
	 * 功能:保存网站
	 */
	@RequestMapping("/saveSite")
	public String saveSite(@ModelAttribute CmsSite site) {
		if(site.getCurrent()==null){
			site.setCurrent(0);
		}
		systemService.saveSite(site);
		return "redirect:/admin/site";
	}
	
	/**
	 * 功能:保存网站
	 */
	@RequestMapping("/setCurrentSite")
	public String setCurrentSite(@RequestParam String siteurl) {
		Set<CmsSite>  allSites = siteDAO.findAllCmsSites();
		for (CmsSite s : allSites) {
			if(s.getCurrent().intValue()==1)
			s.setCurrent(0);
			systemService.saveSite(s);
		}
		CmsSite currentSite = siteDAO.findCmsSiteByPrimaryKey(siteurl);
		currentSite.setCurrent(1);
		systemService.saveSite(currentSite);		
		return "redirect:/admin/site";
	}	
	
	/**
	 * 功能:设计网站为正常活动
	 */
	@RequestMapping("/setNormalSite")
	public String setNormalSite(@RequestParam String siteurl) {
		CmsSite currentSite = siteDAO.findCmsSiteByPrimaryKey(siteurl);
		currentSite.setState(1);
		systemService.saveSite(currentSite);		
		return "redirect:/admin/site";
	}	
		
	
	
	/**
	 * 功能:删除网站
	 */
	@RequestMapping("/deleteSite")
	public String deleteSite(@RequestParam String siteurlKey) {
		CmsSite site = siteDAO.findCmsSiteByPrimaryKey(siteurlKey);
		systemService.deleteSite(site);
		return "redirect:/admin/site";
	}
/*=================================栏目==================================================*/	
	/**
	 * 功能:新建或者编辑网站
	 */
	@RequestMapping("/newChannel")
	public ModelAndView newChannel() {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		CmsChannel channel = new CmsChannel();
		CmsSite site = systemService.findCurrentSite();
		if(site!=null){
			channel.setCmsSite(site);
		}
		mav.addObject("channel", channel);
		mav.addObject("newFlag", true);
		mav.addObject("resources", systemService.loadResources());		
		List<CmsChannel>  channels = new ArrayList<CmsChannel>();
		List<CmsChannel>  channelss = systemService.loadChannels();
		for(CmsChannel  ch :channelss){
			if(ch.getCmsChannel()==null){
				channels.add(ch);
			}
		}
		mav.addObject("channels", channels); 
		mav.addObject("tags", systemService.findTagsByTypeId("channel"));
		mav.addObject("sites", systemService.loadSites()); 
		mav.addObject("users", userDetailService.findAllUsers("1"));
		mav.setViewName("system/channel/editChannel.jsp");
		return mav;
	}	
	/**
	 * 功能:新建或者编辑网站
	 */
	@RequestMapping("/editChannel")
	public ModelAndView editChannel(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("channel", channelDAO.findCmsChannelByPrimaryKey(id)); 
		mav.addObject("newFlag", true);
		mav.addObject("resources", systemService.loadResources());		
		List<CmsChannel>  channels = new ArrayList<CmsChannel>();
		List<CmsChannel>  channelss = systemService.loadChannels();
		for(CmsChannel  ch :channelss){
			if(ch.getCmsChannel()==null){
				channels.add(ch);
			}
		}
		mav.addObject("channels", channels); 		
		mav.addObject("tags", systemService.findTagsByTypeId("channel"));		
		mav.addObject("sites", systemService.loadSites()); 
		mav.addObject("users", userDetailService.findAllUsers("1"));
		mav.setViewName("system/channel/editChannel.jsp");
		return mav;
	}
	
	/**
	 * 功能：删除栏目
	 */
	@RequestMapping("/deleteChannel")
	public String deleteChannel(@RequestParam Integer idKey) {
		systemService.getOneSiteIfNotHave();
		CmsChannel channel = channelDAO.findCmsChannelByPrimaryKey(idKey);
		systemService.deleteChannel(channel);
		return "redirect:/admin/channel";
	}
	
	/**
	 * 功能：显示所有栏目
	 */
	@RequestMapping("/channel")
	public ModelAndView listChannels() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		systemService.getOneSiteIfNotHave();
		mav.addObject("channels", systemService.loadChannels());
		mav.setViewName("system/channel/channelsList.jsp");
		return mav;
	}
	
	/**
	 * 功能：保存栏目
	 */
	@RequestMapping("/saveChannel")
	public String saveChannel(@ModelAttribute CmsChannel channel,HttpServletRequest request) {
		systemService.getOneSiteIfNotHave();
		if(channel.getCmsChannel().getId().intValue()==0){
			channel.setCmsChannel(null);
		}
		
		String[] strs = new String[10];
		strs = request.getParameterValues("tagsss");
		if(strs==null){
			System.out.println("没有选中任何标签！");
		}else{
			int count = strs.length; 
			String sql = "select myTag  from CmsTag myTag where myTag.id=";
			int i=1;
			for(String s :strs){
				if(i<count){
					int tagId = Integer.parseInt(s);
					sql+=tagId;
					sql+="  or myTag.id=";
				}else{
					int tagId = Integer.parseInt(s);
					sql+=tagId;					
				}
				i++;
			}
			List<CmsTag>  tagAlex = tagDAO.executeQuery(sql);
			Set<CmsTag>  tagAlexSet = new HashSet<CmsTag>(tagAlex);
			channel.setCmsTags(tagAlexSet);
		}
		systemService.saveChannel(channel);		
		return "redirect:/admin/channel";
	}
	
	@RequestMapping("/deleteMultipleChannels")
	public String  deleteMultipleChannels(@RequestParam  int[]  array){
		systemService.getOneSiteIfNotHave();
		for(int i :array){
			channelService.deleteChannel(channelDAO.findCmsChannelByPrimaryKey(i)); 
		}
		return "redirect:/admin/channel";
	}
	
	/**
	 * 功能：新建或者编辑文章
	 */
	@RequestMapping("/getChildChannelsByChannelId")
	public ModelAndView getChildChannelsByChannelId(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("channels", channelService.findChildrenChannelsOrderBySort(id));
		mav.addObject("article", new CmsArticle());
		mav.addObject("newFlag", true);
		mav.setViewName("system/channel/channelsList.jsp");
		return mav;
	}	
	
/*===================================文章====================================*/
	
	/**
	 * 功能：显示当前编辑网站所有文章
	 */
	@RequestMapping("/article")
	public ModelAndView listArticles(HttpServletRequest request,@RequestParameters  Integer page) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		systemService.getOneSiteIfNotHave();
		int count = systemService.countArticlesBySite(request);
		int max = 20;
		List<CmsArticle>  articles = systemService.loadArticles(page,max,request);
		Map<String, Integer> pageModel = shareService.getPage(
				page, max, count);
		mav.addObject("articleName",request.getParameter("articleName"));
		mav.addObject("pageModel", pageModel);
		mav.addObject("totalRecords", count);
		mav.addObject("page", page);
		mav.addObject("articles", articles);
		mav.setViewName("system/article/articlesList.jsp");
		return mav;
	}
	
	/**
	 * 功能：显示所有文章
	 */
	@RequestMapping("/channelListArticles")
	public ModelAndView channelListArticles(@RequestParam Integer idKey,@RequestParam Integer page) {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		int count = articleService.countChannelArticles(idKey);
		mav.addObject("idKey", idKey); 
		int maxpage = 1;// 最大页码 
		int max = 20;
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
		List<CmsArticle>  articles = articleService.findArticlesByChannelIdOrderBySortAndCreateTimeDesc(idKey,page,20);
		mav.addObject("count", count); 
		mav.addObject("max", max);
		mav.addObject("maxpage", maxpage);
		mav.addObject("page", page);
		mav.addObject("articles", articles);		
		mav.setViewName("system/article/channelArticlesList.jsp");
		return mav;
	}
	
	
	/**
	 * 功能：删除文章
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(@RequestParam Integer idKey) {
		systemService.getOneSiteIfNotHave();
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(idKey);
		systemService.deleteArticle(article);
		return "redirect:/admin/article?page=1"; 	
	}
	
	 
	
	
	//删除多个新闻
	@RequestMapping("/deleteMultipleArticles")
	public String deleteMultipleArticles(@RequestParam  int[]  array){
		systemService.getOneSiteIfNotHave();
	    //循环删除id对应的新闻
	    for (int i : array) {
	  		articleService.deleteArticle(articleService.findArticleByPrimaryKey(i)); 
	  	}
		return "redirect:/admin/article?page=1"; 	
	}

	
	/**
	 * 功能：保存文章
	 */
	@RequestMapping("saveArticle")
	public String saveArticle(@ModelAttribute CmsArticle article,HttpServletRequest request) {
		systemService.getOneSiteIfNotHave();
		if(article.getCmsResource().getId().intValue()==0){
			article.setCmsResource(null);
		}
		if(article.getCmsDocument().getId().intValue()==0){
			article.setCmsDocument(null);
		}
		if(article.getId()==null){
			article.setReadNum(0);
		}

		String[] strs = new String[10];
		strs = request.getParameterValues("tagsss");
		if(strs==null){
			System.out.println("没有选中任何标签！");
		}else{
			int count = strs.length; 
			String sql = "select myTag  from CmsTag myTag where myTag.id=";
			int i=1;
			for(String s :strs){
				if(i<count){
					int tagId = Integer.parseInt(s);
					sql+=tagId;
					sql+="  or myTag.id=";
				}else{
					int tagId = Integer.parseInt(s);
					sql+=tagId;					
				}
				i++;
			}
			List<CmsTag>  tagAlex = tagDAO.executeQuery(sql);
			Set<CmsTag>  tagAlexSet = new HashSet<CmsTag>(tagAlex);
			article.setCmsTags(tagAlexSet);
		}
		
		String news = article.getNews();
		news = news.replaceAll("src=\"/ueditor/jsp/upload", "src=\"/xidlims/ueditor/jsp/upload");
		article.setNews(news);
		
		systemService.saveArticle(article);
		return "redirect:/admin/article?page=1"; 	
	}

	
	/**
	 * 功能：新建或者编辑文章
	 */
	@RequestMapping("/newArticle")
	public ModelAndView newArticle() {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("article", new CmsArticle());
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("resources", systemService.loadResources());		
		List<CmsChannel>  channels = new ArrayList<CmsChannel>();
		List<CmsChannel>  channelss = systemService.loadChannels();
		for(CmsChannel  ch :channelss){
			if(ch.getCmsChannel()==null){
				channels.add(ch);
			}
		}
		mav.addObject("channels", channels); 		
		mav.addObject("sites", systemService.loadSites()); 
		mav.addObject("users", userDetailService.findAllUsers("1"));
		mav.addObject("tags", systemService.findTagsByTypeId("article"));		
	    //时间
	    mav.addObject("date",shareService.getDate());		
/*	    //时间
	    Date date = contentService.findContentByPrimaryKey(idKey).getUpdateTime().getTime();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    mav.addObject("date",df.format(date)); */
		mav.addObject("newFlag", true);
		List<CmsDocument> documents = documentService.findAllCmsDocuments();
		mav.addObject("documents", documents);
		mav.setViewName("system/article/editArticle.jsp");
		return mav;
	}
	/**
	 * 功能：新建或者编辑文章
	 */
	@RequestMapping("/editArticle")
	public ModelAndView editArticle(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		CmsArticle article = articleDAO.findCmsArticleByPrimaryKey(id);
		mav.addObject("newFlag", false);
		mav.addObject("article", article);
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("resources", systemService.loadResources());		
		List<CmsChannel>  channels = new ArrayList<CmsChannel>();
		List<CmsChannel>  channelss = systemService.loadChannels();
		for(CmsChannel  ch :channelss){
			if(ch.getCmsChannel()==null){
				channels.add(ch);
			}
		}
		mav.addObject("channels", channels); 		
		mav.addObject("tags", systemService.findTagsByTypeId("article"));		
		mav.addObject("sites", systemService.loadSites()); 
		mav.addObject("users", userDetailService.findAllUsers("1"));
			    //时间
	    Date date = article.getCreateTime().getTime();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    mav.addObject("date",df.format(date)); 
	    List<CmsDocument> documents = documentService.findAllCmsDocuments();
		mav.addObject("documents", documents);
		mav.setViewName("system/article/editArticle.jsp");
		return mav;
	}

	/*============================资源=======================================*/

	/**
	 * 功能：图片列表
	 */
	@RequestMapping("/resourcesList")	
	public ModelAndView  resourcesList(@ModelAttribute  CmsResource  resource,@RequestParam Integer type,@RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("type", type);
		Set<CmsResource> resources= systemService.findResourceByImageVideo(type);
		mav.addObject("resources" ,resources);
		int pageSize=20;
		int totalRecords=imageService.findAllImagesByImageVideo(resource).size();
		Map <String,Integer> pageModel =shareService.getPage(page, pageSize , totalRecords);
		List<CmsResource>listImage=imageService.findAllImagesByImageVideo(resource, page, pageSize,type);
		mav.addObject("listImage", listImage);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("system/resource/resourcesList.jsp");
		return mav;
	}
	
	/**
	 * 功能：图片列表
	 */
	@RequestMapping("/resourceEdit")	
	public ModelAndView  resourcesEdit(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		systemService.getOneSiteIfNotHave();
		mav.addObject("resource", resourceDAO.findCmsResourceByPrimaryKey(id)); 
		mav.setViewName("system/resource/editResource.jsp");
		return mav;
	}	
	
	/**
	 * 功能：添加一张图片
	 */
	@RequestMapping("/addNewImage")	
	public ModelAndView  addNewImage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		systemService.getOneSiteIfNotHave();
		CmsResource resouce = new CmsResource();
		resouce.setImageVideo(0);
		mav.addObject("resource", resouce); 
		mav.setViewName("system/resource/editResource.jsp");
		return mav;
	}	
	
	/**
	 * 功能：添加一张图片
	 */
	@RequestMapping("/addNewVideo")	
	public ModelAndView  addNewVideo() {
		systemService.getOneSiteIfNotHave();
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		CmsResource resouce = new CmsResource();
		resouce.setImageVideo(1);
		mav.addObject("resource", resouce); 
		mav.setViewName("system/resource/editResource.jsp");
		return mav;
	}	
	
	
	/**
	 * Save an existing Image entity
	 * 
	 */
	@RequestMapping("/saveResource")
	public String saveResource(@ModelAttribute CmsResource resource,@RequestParam Integer type,HttpServletRequest request,Integer page) {
		systemService.getOneSiteIfNotHave();
		//上传文件的url
		String resourceUrl=shareService.getUpdateFileSavePath(request);
		//设置url
		if(resourceUrl!=null&&resourceUrl.length()>1){
			resource.setUrl(resourceUrl);
		}else{
			resource.setUrl(resourceDAO.findCmsResourceByPrimaryKey(resource.getId()).getUrl());
		}
		systemService.savaResource(resource);
		return "redirect:/admin/resourcesList?type="+type+"&page=1";
	}	
	
	
	/*
	 * 删除某一个资源
	 * /
	 */
	@RequestMapping("/deleteOneResource")
	public String deleteOneResource(@RequestParam Integer Id){
		systemService.getOneSiteIfNotHave();
		CmsResource resource  = resourceDAO.findCmsResourceByPrimaryKey(Id);
		systemService.deleteResource(resource);
		return "redirect:/admin/resourcesList?type="+resource.getImageVideo().intValue()+"&page=1";
	}
	
	/*
	 * 批量删除资源
	 * /
	 */
	@RequestMapping("/deleteMultipleResource")
	public String deleteMultipleResource(@RequestParam  int[]  array,Integer type){
		systemService.getOneSiteIfNotHave();
		for(int i:array){
			systemService.deleteResource(i);
		}
		return "redirect:/admin/resourcesList?type="+type+"&page=1";
	}
	
	/**
	 * 功能：附件列表
	 */
	@RequestMapping("/documentsList")	
	public ModelAndView  documentsList(@RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("documents", documentService.findAllCmsDocuments());
		int pageSize=20;
		int totalRecords=documentService.findAllCmsDocuments().size();
		Map <String,Integer> pageModel =shareService.getPage(page, pageSize , totalRecords);
		List<CmsDocument> listDocument=documentService.findAllCmsDocuments( page, pageSize);
		mav.addObject("listDocument", listDocument);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		mav.setViewName("system/document/documentsList.jsp");
		return mav;
	}
	/**
	 * 新增附件
	 * 
	 */
	@RequestMapping("/newDocument")
	public ModelAndView newDocument() {
		ModelAndView mav = new ModelAndView();
		int type = 1;
		mav.addObject("user", shareService.getUser()); 
		systemService.getOneSiteIfNotHave();
		CmsDocument document = new CmsDocument();
		mav.addObject("document", document); 
		mav.addObject("type", type);
		mav.addObject("newFlag", true);
		//返回所有栏目
		mav.setViewName("system/document/editDocument.jsp");

		return mav;
	}
	/**
	 * 编辑附件
	 * 
	 */
	@RequestMapping("/editDocument")
	public ModelAndView editDocument(@RequestParam Integer documentIdKey) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		int type = 2;
		mav.addObject("document", documentDAO.findCmsDocumentByPrimaryKey(documentIdKey));
		//返回所有栏目
	    mav.addObject("type", type);
		mav.setViewName("system/document/editDocument.jsp");
		return mav;
	}
	/**
	 * 保存附件
	 * 
	 */
	@RequestMapping("/saveDocument")
	public String saveDocument(@ModelAttribute CmsDocument document,HttpServletRequest request,int type) {
	    //如果是新建的话，就先保存，然后在上传图片
	    if(type==1){
	    	//更新父栏目
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	    	document.setCreateTime(df.getCalendar());
	    	documentDAO.store(document);
			int idkey = documentService.lastDocument();
			return "redirect:/admin/editDocument?documentIdKey="+idkey;
	    }else{
	    	documentDAO.store(document);
			return "redirect:/admin/documentsList?page=1";
	    }
	}

	/*
	 * 删除某一个附件
	 * /
	 */
	@RequestMapping("/deleteDocument")
	public String deleteDocument(HttpServletRequest request,@RequestParam Integer Id){
		CmsDocument document = documentDAO.findCmsDocumentByPrimaryKey(Id);
		List<CmsArticle>  articles = articleService.findArticlesByDocumentId(Id);
		if(articles.size()!=0){
			for(CmsArticle a:articles){
				a.setCmsDocument(null);
				articleDAO.store(a);
			}
		}
		//删除服务器上的文件
		String sep = System.getProperty("file.separator"); 
		String fileDir = request.getSession().getServletContext().getRealPath( "/") + "upload"+ sep +"document" + sep +Id;
		
		File file = new File(fileDir);
		   if(file.exists()){                    //判断文件是否存在
			    if(file.isFile()){                    //判断是否是文件
			     file.delete();                       //delete()方法 你应该知道 是删除的意思;
			    }else if(file.isDirectory()){              //否则如果它是一个目录
			     File files[] = file.listFiles();               //声明目录下所有的文件 files[];
			     for(int i=0;i<files.length;i++){            //遍历目录下所有的文件
			     files[i].delete();             //把每个文件 用这个方法进行迭代
			     } 
			    } 
			    file.delete(); 
			   }else{ 
			    System.out.println("所删除的文件不存在！"+'\n'); 
			   }
		documentDAO.remove(document);
		return "redirect:/admin/documentsList?page=1";
	}
	/*=================================用户====================================*/
	
	/*
	 * 得到所有的用户
	 */
	@RequestMapping("/usersList")
	public ModelAndView  usersList(){
		systemService.getOneSiteIfNotHave();
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("users", userDetailService.findAllUsers(null));
		mav.setViewName("system/user/usersList.jsp");
		return mav;
	}
	
	
	/*
	 * 编辑某一个用户
	 */
	@RequestMapping("/userEdit")
	public ModelAndView  userEdit(@RequestParam String  userid){ 
		systemService.getOneSiteIfNotHave();
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("userA", userDAO.findUserByPrimaryKey(userid));
		mav.setViewName("system/user/editUser.jsp");
		return mav;
	}
	
	/*
	 * 编辑某一个用户
	 */
	@RequestMapping("/userSave")
	public String  userSave(@ModelAttribute User userA){ 
		systemService.getOneSiteIfNotHave();
		if(userA.getEnabled()==null)
			userA.setEnabled(false);
		systemService.saveUser(userA);
		return "redirect:/admin/usersList";
	}	
	
	/*
	 * 编辑某一个用户
	 */
	@RequestMapping("/userNew")
	public ModelAndView  userNew(){ 
		systemService.getOneSiteIfNotHave();
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("userA", new User());
		mav.setViewName("system/user/editUser.jsp");
		return mav;
	}	
	
	
	/*
	 * ajax检查用户名是否可用
	 */	
	@RequestMapping("/checkUserNameAlex")
	@ResponseBody
	public  String checkUserNameAlex(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException{
			response.setContentType("text/html;charset=utf-8");
			String username=request.getParameter("username");
			Set<User> users = userDAO.findAllUsers();
			int flag=0;
			for(User u :users){
				if(u.getUsername().equals(username))
					flag=1;
			}
			if(flag==1){
				return "该用户名已经被注册!";
			}else{
				return "用户名可用";
			}
	 }

	
	/*
	 * 删除用户
	 */	
	@RequestMapping("/deleteExistUser")
	public String deleteExistUser(String userid){
		systemService.deleteUser(userid);
		return "redirect:/admin/usersList";
	}
	
/*===============================链接=====================================*/	
	/*
	 * 得到当前网站的所有链接
	 */
	@RequestMapping("/linksList")
	public ModelAndView  linksList(){
		systemService.getOneSiteIfNotHave();
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("links", systemService.findCurrentSiteLinks());
		mav.setViewName("system/link/linksList.jsp");
		return mav;
	}
	
	/*
	 * 编辑某一个链接
	 */
	@RequestMapping("/linkEdit")
	public ModelAndView  linkEdit(@RequestParam Integer  id){ 
		ModelAndView mav = new ModelAndView();
		mav.addObject("link", systemService.findLinkByPrimaryKeyId(id)); 
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("resources", systemService.loadResources());		
		mav.addObject("sites", systemService.loadSites()); 	
		mav.addObject("tags", systemService.findTagsByTypeId("link")); 
		mav.setViewName("system/link/editLink.jsp");
		return mav;
	}
	
	/*
	 * 新建某一个链接
	 */
	@RequestMapping("/linkNew")
	public ModelAndView  linkNew(){ 
		ModelAndView mav = new ModelAndView();
		CmsLink link = new CmsLink();
		systemService.getOneSiteIfNotHave();
		mav.addObject("user", shareService.getUser()); 
		link.setCmsSite(systemService.findCurrentSite());
		mav.addObject("link", link); 
		mav.addObject("resources", systemService.loadResources());		
		mav.addObject("sites", systemService.loadSites()); 	
		mav.addObject("tags", systemService.findTagsByTypeId("link")); 
		mav.setViewName("system/link/editLink.jsp");
		return mav;
	}	
	
	/*
	 * 编辑某一个用户
	 */
	@RequestMapping("/linkSave")
	public String  linkSave(@ModelAttribute CmsLink link){ 
		systemService.getOneSiteIfNotHave();
		link.setCreateTime(Calendar.getInstance());
		systemService.saveLink(link);
		return "redirect:/admin/linksList";
	}	
	
	/*
	 * 批量删除链接
	 */
	@RequestMapping("/deleteTheLink")
	public String  deleteTheLink(@RequestParam Integer IdKey){
		systemService.deleteLink(IdKey);		
		return "redirect:/admin/linksList";				
	}
	
	/*
	 * 批量删除链接
	 */
	@RequestMapping("/deleteMultipleLinks")
	public String deleteMultipleLinks(@RequestParam  int[]  array){
		for(int i :array){
			this.deleteTheLink(i);
		}
		return "redirect:/admin/linksList";		
	}
	
	
	/*
	===============================校友管理=====================================	
	*//**
	 * @module 校友列表
	 * @param alumniInfo
	 * @param page
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:52:15
	 *//*
	@RequestMapping("alumni")
	public ModelAndView alumni(@ModelAttribute AlumniInfo alumniInfo,@RequestParam int page){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//查询表单的对象
		mav.addObject("alumniInfo", alumniInfo);
		//每页显示20条记录
		int pageSize=20;
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
		
		mav.setViewName("system/alumni/alumniList.jsp");
		
		return mav;
	}
	
	*//**
	 * @module 新建校友
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:51:47
	 *//*
	@RequestMapping("newAlumni")
	public ModelAndView newAlumni(){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//页面表单的对象
		mav.addObject("alumniInfo", new AlumniInfo());
		mav.setViewName("system/alumni/editAlumni.jsp");
		
		return mav;
	}
	
	*//**
	 * @module 保存校友信息
	 * @param alumniInfo
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:54:50
	 *//*
	@RequestMapping("/saveAlumni")
	public String  saveAlumni(@ModelAttribute AlumniInfo alumniInfo){ 
		alumniService.saveAlumni(alumniInfo);
		return "redirect:/admin/alumni?page=1";
	}	
	
	*//**
	 * @module 修改校友信息
	 * @param id
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午1:36:35
	 *//*
	@RequestMapping("/editAlumni")
	public ModelAndView  editAlumni(@RequestParam Integer  id){ 
		ModelAndView mav = new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		mav.addObject("alumniInfo", alumniService.findAlumniInfoByPrimaryKey(id));
		mav.setViewName("system/alumni/editAlumni.jsp");
		return mav;
	}
	
	*//**
	 * @module 单个删除校友
	 * @param id
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午2:21:06
	 *//*
	@RequestMapping("/deleteAlumni")
	public String  deleteAlumni(@RequestParam Integer id){
		alumniService.deleteAlumni(id);		
		return "redirect:/admin/alumni?page=1";	
	}
	
	*//**
	 * @module 批量删除校友
	 * @param array
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午2:21:25
	 *//*
	@RequestMapping("/deleteMultipleAlumni")
	public String deleteMultipleAlumni(@RequestParam  int[]  array){
		for(int i :array){
			this.deleteAlumni(i);
		}
		return "redirect:/admin/alumni?page=1";		
	}
	
	===============================募集项目管理=====================================
	*//**
	 * @module 募集项目列表
	 * @param project、page
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:52:15
	 *//*
	@RequestMapping("project")
	public ModelAndView project(@ModelAttribute DonationProject project,@RequestParam int page){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//查询表单的对象
		mav.addObject("projectInfo", project);
		//每页显示20条记录
		int pageSize=20;
		//查询出来的总记录条数
		int totalRecords=donationService.findAllProjectByProject(project).size();
		//分页信息
		Map<String,Integer> pageModel =shareService.getPage(page, pageSize,totalRecords);
		//根据分页信息查询出来的记录
		List<DonationProject> listProject=donationService.findAllProjectByProject(project, page, pageSize);
		//用于存放总的捐款金额
		Map<Integer,String> o = new HashMap<Integer,String>();
		//用于计算捐赠的金额
		BigDecimal count = new BigDecimal(0);		
		//计算总的捐款金额
		for(DonationProject p:donationService.findAllProjectByProject(project)){		
			//新建一个sql语句
			String sql="select sum(i.donationMoney) from DonationInfo i where i.donationProject.id = " + p.getId();
			//进行sql语句判断，如果结果为空则进行强制转换，否则就设置为"0"
			if(infoDAO.createQuerySingleResult(sql).getSingleResult()!=null){
				//进行强制转换
				count = (BigDecimal) infoDAO.createQuerySingleResult(sql).getSingleResult();
				//将计算后的值放入Map中
				o.put(p.getId(),count+"");
			} else {
				//进行空值赋"0"
				o.put(p.getId(),"0");
			}
		}
		mav.addObject("count",o);
		mav.addObject("project",listProject);
		mav.addObject("pageModel",pageModel);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("page", page);
		mav.addObject("pageSize", pageSize);
		
		mav.setViewName("system/donation/projectList.jsp");
		
		return mav;
	}
	
	*//**
	 * @module 新建募集项目
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:51:47
	 *//*
	@RequestMapping("newProject")
	public ModelAndView newProject(){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取当前的登陆用户
		mav.addObject("user", shareService.getUser()); 
		//页面表单的对象
		mav.addObject("project", new DonationProject());
		mav.setViewName("system/donation/editProject.jsp");
		
		return mav;
	}
	
	*//**
	 * @module 保存募集项目信息
	 * @param project
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:54:50
	 *//*
	@RequestMapping("/saveProject")
	public String  saveProject(@ModelAttribute DonationProject project){ 
		donationService.saveProject(project);
		return "redirect:/admin/project?page=1";
	}	
	
	*//**
	 * @module 修改募集项目信息
	 * @param id
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午1:36:35
	 *//*
	@RequestMapping("/editProject")
	public ModelAndView  editProject(@RequestParam Integer  id){ 
		ModelAndView mav = new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//创建时间
		if(donationService.findProjectByPrimaryKey(id).getCreateDate()!=null){
			Date date = donationService.findProjectByPrimaryKey(id).getCreateDate().getTime();
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    mav.addObject("date",df.format(date)); 
		}	    
		mav.addObject("project", donationService.findProjectByPrimaryKey(id));
		mav.setViewName("system/donation/editProject.jsp");
		return mav;
	}
	
	*//**
	 * @module 单个删除募集项目
	 * @param id
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午2:21:06
	 *//*
	@RequestMapping("/deleteProject")
	public String  deleteProject(@RequestParam Integer id){
		donationService.deleteProject(id);		
		return "redirect:/admin/project?page=1";	
	}
	
	*//**
	 * @module 批量删除募集项目
	 * @param array
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午2:21:25
	 *//*
	@RequestMapping("/deleteMultipleProject")
	public String deleteMultipleProject(@RequestParam  int[]  array){
		for(int i :array){
			this.deleteProject(i);
		}
		return "redirect:/admin/project?page=1";		
	}
	
	===============================募集信息管理=====================================
	*//**
	 * @module 募集信息列表
	 * @param info
	 * @param page
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:52:15
	 *//*
	@RequestMapping("info")
	public ModelAndView info(@ModelAttribute DonationInfo info,@RequestParam int page){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//查询表单的对象
		mav.addObject("donationInfo", info);
		//每页显示20条记录
		int pageSize=20;
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
		
		mav.setViewName("system/donation/infoList.jsp");
		
		return mav;
	}
	
	*//**
	 * @module 新建募集信息
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:51:47
	 *//*
	@RequestMapping("newInfo")
	public ModelAndView newInfo(){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//获取所有的捐赠项目
		mav.addObject("project", donationService.findAllProject()); 
		//获取所有的捐赠人的属性
		mav.addObject("property", donationService.findAllProperty()); 
		//页面表单的对象
		mav.addObject("info", new DonationInfo());
		mav.setViewName("system/donation/editInfo.jsp");
		
		return mav;
	}
	
	*//**
	 * @module 保存募集信息
	 * @param info
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-17 下午10:54:50
	 *//*
	@RequestMapping("/saveInfo")
	public String  saveInfo(@ModelAttribute DonationInfo info){ 
		donationService.saveInfo(info);
		return "redirect:/admin/info?page=1";
	}	
	
	*//**
	 * @module 修改募集信息
	 * @param id
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午1:36:35
	 *//*
	@RequestMapping("/editInfo")
	public ModelAndView  editInfo(@RequestParam Integer  id){ 
		ModelAndView mav = new ModelAndView();
		//获取当前的登录用户
		mav.addObject("user", shareService.getUser()); 
		//获取所有的捐赠项目
		mav.addObject("project", donationService.findAllProject()); 
		//获取所有的捐赠人的属性
		mav.addObject("property", donationService.findAllProperty());
		//创建时间
		if(donationService.findInfoByPrimaryKey(id).getCreateDate()!=null){
			Date date = donationService.findInfoByPrimaryKey(id).getCreateDate().getTime();
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    mav.addObject("date",df.format(date)); 
		}	
		mav.addObject("info", donationService.findInfoByPrimaryKey(id));
		mav.setViewName("system/donation/editInfo.jsp");
		return mav;
	}
	
	*//**
	 * @module 单个删除募集信息
	 * @param id
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午2:21:06
	 *//*
	@RequestMapping("/deleteInfo")
	public String  deleteInfo(@RequestParam Integer id){
		donationService.deleteInfo(id);		
		return "redirect:/admin/info?page=1";	
	}
	
	*//**
	 * @module 批量删除募集项目
	 * @param array
	 * @return
	 * @author 叶明盾
	 * @date 2015-6-18 下午2:21:25
	 *//*
	@RequestMapping("/deleteMultipleInfo")
	public String deleteMultipleInfo(@RequestParam  int[]  array){
		for(int i :array){
			this.deleteInfo(i);
		}
		return "redirect:/admin/info?page=1";		
	}
	*/
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
	/****************************************************************************
	 * 功能：上传附件
	 * 作者：裴继超
	 * 时间：2015年12月18日12:57:33
	 ****************************************************************************/
	@RequestMapping("/uploadDocument")
	public @ResponseBody String uploadDocument(HttpServletRequest request, HttpServletResponse response, BindException errors,Integer id,Integer type) throws Exception {
		documentService.uploadDocument(request, response,id,type);
		return "ok";
	}
	/****************************************************************************
	 * 功能：删除已上传附件
	 * 作者：裴继超
	 ****************************************************************************/
	@RequestMapping("/deleteUploadDocument")
	public ModelAndView deleteUploadImage(HttpServletRequest request,@RequestParam Integer id){
		//新建ModelAndView对象；
		ModelAndView mav=new ModelAndView();
		//id对应的附件
		CmsDocument document = documentDAO.findCmsDocumentByPrimaryKey(id);
		String url = document.getUrl();
		//删除服务器上的文件
		String sep = System.getProperty("file.separator"); 
		String fileDir = request.getSession().getServletContext().getRealPath( "/") +  url;
		File file = new File(fileDir);
		   if(file.exists()){
		    boolean d = file.delete();
		    if(d){
		     System.out.print("删除成功！");
		    }else{
		     System.out.print("删除失败！");
		    }
		   }  
		   
		document.setUrl("");
		documentDAO.store(document);
		
		
		mav.setViewName("redirect:/admin/editDocument?documentIdKey="+id);
		return mav;
	}
	
	
	/**
	 * 功能:登陆后的默认页面
	 * 参数:
	 */
	@RequestMapping("/logRes")
	public ModelAndView logRes() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/cms/turn/indexRes.jsp");
		return mav;
	}
	
	/**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logManage")
    public ModelAndView logManage() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexManage.jsp");
        return mav;
    }

    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logDevMag")
    public ModelAndView logDevMag() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexDevMag.jsp");
        return mav;
    }
    
    @RequestMapping("/logItemShow")
    public ModelAndView logItemShow() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexItemShow.jsp");
        return mav;
    }


    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logDevRes")
    public ModelAndView logDevRes() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexDevRes.jsp");
        return mav;
    }

    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logTime")
    public ModelAndView logTime() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexTime.jsp");
        return mav;
    }

    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logAsset")
    public ModelAndView logAsset() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexAsset.jsp");
        return mav;
    }

    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logSystem")
    public ModelAndView logSystem() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexSystem.jsp");
        return mav;
    }
    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logAssetRes")
    public ModelAndView logAssetRes() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexAssetRes.jsp");
        return mav;
    }

    /**
     * 功能:登陆后的默认页面
     * 参数:
     */
    @RequestMapping("/logKnowMap")
    public ModelAndView logKnowMap() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/cms/turn/indexKnowMap.jsp");
        return mav;
    }
     
}