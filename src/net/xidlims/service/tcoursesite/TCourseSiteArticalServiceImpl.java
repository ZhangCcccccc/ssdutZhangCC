package net.xidlims.service.tcoursesite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.constant.CommonConstantInterface;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.TCourseSiteArticalDAO;
import net.xidlims.dao.TCourseSiteChannelDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TCourseSiteTagDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.TCourseSiteArtical;
import net.xidlims.domain.TCourseSiteChannel;
import net.xidlims.domain.User;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TCourseSiteArticalService")
public class TCourseSiteArticalServiceImpl implements TCourseSiteArticalService{

	@Autowired
	private TCourseSiteTagDAO tCourseSiteTagDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TCourseSiteArticalDAO tCourseSiteArticalDAO;
	@Autowired
	private CDictionaryDAO cDictionaryDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private TCourseSiteChannelDAO tCourseSiteChannelDAO;
	@Autowired
	private TCourseSiteChannelService tCourseSiteChannelService;
	
	/**************************************************************************
	 * Description:根据已有课程栏目查询该栏目所包含的文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-4 
	 **************************************************************************/
	@Override
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannel(
			TCourseSiteChannel tCourseSiteChannel, Integer currpage,
			int pageSize) {
		StringBuffer sql = new StringBuffer("from TCourseSiteArtical c where c.TCourseSiteChannel.id = '" + tCourseSiteChannel.getId() +"' order by c.createDate asc");
		
		return tCourseSiteArticalDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**************************************************************************
	 * Description:保存课程文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-4 
	 **************************************************************************/
	@Override
	public void saveCourseSiteArtical(TCourseSiteArtical tCourseSiteArtical,HttpServletRequest request) {
		if(tCourseSiteArtical!=null&&tCourseSiteArtical.getTCourseSiteTag()!=null&&tCourseSiteArtical.getTCourseSiteTag().getId()!=null){
			tCourseSiteArtical.setTCourseSiteTag(tCourseSiteTagDAO.findTCourseSiteTagById(tCourseSiteArtical.getTCourseSiteTag().getId()));
		}
		//栏目
		TCourseSiteChannel channel=tCourseSiteChannelDAO.findTCourseSiteChannelByPrimaryKey(tCourseSiteArtical.getTCourseSiteChannel().getId());
		String filePath="/upload/cms/"+channel.getTCourseSite().getId()+"/channel";
		String imageUrl=shareService.fileUpload(request, filePath);
		if(imageUrl.equals("")){
			tCourseSiteArtical.setImageUrl(tCourseSiteArtical.getImageUrl());
		}else{
			tCourseSiteArtical.setImageUrl(imageUrl);
		}
		//课件地址
		/*String coursewarePath=shareService.fileUpload(request, "coursewareFile", filePath);
		if (!"".equals(coursewarePath)) {//如果课件有变动,删除原有文件，并替换新文件
			if (tCourseSiteArtical.getCoursewarePath()!=null&&!"".equals(tCourseSiteArtical.getCoursewarePath())) {
				String root=System.getProperty("xidlims.root");
				shareService.deleteFile(root+tCourseSiteArtical.getCoursewarePath());
			}
			tCourseSiteArtical.setCoursewarePath(coursewarePath);
		}*/
		
		tCourseSiteArtical.setCreateUser(userDAO.findUserByPrimaryKey(tCourseSiteArtical.getCreateUser().getUsername().trim()));
		tCourseSiteArtical.setCDictionary(cDictionaryDAO.findCDictionaryById(tCourseSiteArtical.getCDictionary().getId()));
		tCourseSiteArtical.setModifyDate(Calendar.getInstance());
		tCourseSiteArticalDAO.store(tCourseSiteArtical);
	}

	/**************************************************************************
	 * Description:根据文章id查询文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5 
	 **************************************************************************/
	@Override
	public TCourseSiteArtical findTCourseSiteArticalByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tCourseSiteArticalDAO.findTCourseSiteArticalById(id);
	}

	/**************************************************************************
	 * Description:删除课程文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-5 
	 **************************************************************************/
	@Override
	public void deleteTCourseSiteArtical(TCourseSiteArtical tCourseSiteArtical) {
		// TODO Auto-generated method stub
		tCourseSiteTagDAO.remove(tCourseSiteArtical);
	}

	/**************************************************************************
	 * Description:删除选中的课程文章
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 
	 **************************************************************************/
	@Override
	public void deleteCourseSiteArticalByIds(String ids) {
		// TODO Auto-generated method stub
		String[] tCourseSiteArticalIds = ids.split(",");
		for(String id : tCourseSiteArticalIds){
			TCourseSiteArtical tCourseSiteArtical = tCourseSiteArticalDAO.findTCourseSiteArticalByPrimaryKey(Integer.valueOf(id));
			//删除文章图片
			String imageUrl=tCourseSiteArtical.getImageUrl();
			shareService.deleteFile(imageUrl);
			tCourseSiteArticalDAO.remove(tCourseSiteArtical);
		}
	}

	/****************************************************************************
	 *Description:根据ChannelId和State查出Channel下的文章列表
	 *
	 *@author：贺子龙
	 *@date ：2015-8-26 
	 ****************************************************************************/
	@Override
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelIdAndState(Integer id,int i) {
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteArtical c where c.TCourseSiteChannel.id="+id+" and c.CDictionary.CCategory='"+CommonConstantInterface.STR_TCOURSESITE_CHANNELSTATE+"' and c.CDictionary.CNumber = "+i);
		return tCourseSiteArticalDAO.executeQuery(sbf.toString(), 0,-1);
	}
	/**************************************************************************
	 * Description:保存文章
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-8
	 **************************************************************************/
	@Override
	public void saveDonationArtical(TCourseSiteArtical artical,String channelName) {
		User user=shareService.getUser();
		artical.setCreateUser(user);
		//通过栏目名获取栏目id
		Integer channelId=tCourseSiteChannelService.findTCourseSiteChannelByName(channelName).getId();
		TCourseSiteChannel channel=tCourseSiteChannelDAO.findTCourseSiteChannelByPrimaryKey(channelId);
		//保存栏目外键
		artical.setTCourseSiteChannel(channel);
		//保存状态为正常活动（即1）
		artical.setCDictionary(shareService.getCDictionaryByCategory("c_tcoursesite_state", "1"));
		//保存创建时间
		artical.setModifyDate(Calendar.getInstance());
		artical.setCreateDate(Calendar.getInstance());
		tCourseSiteArticalDAO.store(artical);
	}
	/**************************************************************************
	 * Description:根据当前登录人和ChannelId查出Channel下的文章列表
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-9
	 **************************************************************************/	
	@Override
	public List<TCourseSiteArtical> findArticalByUser(String channelName) {
		
		//获取登录人
		User user=shareService.getUser();
		//通过栏目名获取栏目id
		Integer channelId=tCourseSiteChannelService.findTCourseSiteChannelByName(channelName).getId();
		String sql = "from TCourseSiteArtical c where c.TCourseSiteChannel.id='"+channelId+"' and c.createUser.username='"+user.getUsername()+"'";
		
		List<TCourseSiteArtical> list=tCourseSiteArticalDAO.executeQuery(sql);
		
		return list;
		
		
	}
	/**************************************************************************
	 * Description:根据ChannelId查出Channel下的文章列表(倒序)
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-10
	 **************************************************************************/
	@Override
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelId(Integer id) {
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteArtical c where c.TCourseSiteChannel.id="+id+" order by c.id desc" );
		 List<TCourseSiteArtical> artical =  tCourseSiteArticalDAO.executeQuery(sbf.toString(), 0,-1);
		 List<TCourseSiteArtical> articalas=new ArrayList<TCourseSiteArtical>();
		 for(TCourseSiteArtical TCourseSiteArticals:artical){
			if(tCourseSiteChannelService.findTCourseSiteChannelByPrimaryKey(id).getChannelName().equals("感悟心得")){
			 if(!TCourseSiteArticals.getText().equals("")&&TCourseSiteArticals.getText()!=null&&TCourseSiteArticals.getText().length()>3){
				 String sb=TCourseSiteArticals.getText();
			 TCourseSiteArticals.setText(sb.substring(0,3));
			 }
			}
		 }
		 articalas=artical;
		 return articalas;
	}
	/**************************************************************************
	 * Description:获取文章，分页，倒序
	 * 
	 * @author： 裴继超
	 * @date ：2015-10-14
	 **************************************************************************/
	@Override
	public List<TCourseSiteArtical> findTCourseSiteArticals(
			TCourseSiteChannel tCourseSiteChannel, Integer currpage,
			int pageSize) {
		StringBuffer sql = new StringBuffer("from TCourseSiteArtical c where c.TCourseSiteChannel.id = '" + tCourseSiteChannel.getId() +"' order by c.id desc");
		
		return tCourseSiteArticalDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
	}
	
	/**************************************************************************
	 * Description:根据栏目id及文章状态查询该栏目下的文章数
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-13 
	 **************************************************************************/	
	@Override
	public int countTCourseSiteArticalsByChannelIdAndState(Integer id,int state) {
		StringBuffer sbf=new StringBuffer("select count(c) from TCourseSiteArtical c where c.TCourseSiteChannel.id="+id+" and c.CDictionary.CCategory='"+CommonConstantInterface.STR_TCOURSESITE_CHANNELSTATE+"' and c.CDictionary.CNumber = "+state);
		int result = ((Long)tCourseSiteArticalDAO.createQuerySingleResult(sbf.toString()).getSingleResult()).intValue();
		return result;
	}
	/**************************************************************************
	 * Description:根据栏目id及文章状态查询该栏目下的文章(分页查询)
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-13 
	 **************************************************************************/
	@Override
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelIdAndState(
			Integer id, int state, Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteArtical c where c.TCourseSiteChannel.id="+id+" and c.CDictionary.CCategory='"+CommonConstantInterface.STR_TCOURSESITE_CHANNELSTATE+"' and c.CDictionary.CNumber = "+state);
		return tCourseSiteArticalDAO.executeQuery(sbf.toString(), (currpage-1)*pageSize, pageSize);
	}

	
	/**************************************************************************
	 * Description:分页根据ChannelId查出Channel下的文章列表(倒序)
	 * 
	 * @author： 姜新剑
	 * @date ：2016-04-12
	 **************************************************************************/	
	@Override
	public List<TCourseSiteArtical> findTCourseSiteArticalsByChannelId(
			Integer id, int page, int pagesize) {
		StringBuffer sbf=new StringBuffer("select c from TCourseSiteArtical c where c.TCourseSiteChannel.id="+id+" order by c.id desc" );
		return tCourseSiteArticalDAO.executeQuery(sbf.toString(),page*pagesize,pagesize);
	}
	
	/**************************************************************************
	 * Description:保存文章
	 * 
	 * @author： 裴继超
	 * @date ：2015-8-3
	 **************************************************************************/
	@Override
	public TCourseSiteArtical saveArtical(TCourseSiteArtical tCourseSiteArtical) {
		// TODO Auto-generated method stub
		return tCourseSiteArticalDAO.store(tCourseSiteArtical);
	}
}
