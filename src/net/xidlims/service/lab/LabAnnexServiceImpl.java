package net.xidlims.service.lab;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.service.common.ShareService;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.dao.CommonVideoDAO;
import net.xidlims.dao.LabAnnexDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonVideo;
import net.xidlims.domain.LabAnnex;
import net.xidlims.domain.LabRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("LabAnnexService")
public class LabAnnexServiceImpl implements  LabAnnexService {
	
	@Autowired LabAnnexDAO labAnnexDAO;
	@Autowired LabRoomDAO labRoomDAO;
	@Autowired CommonDocumentDAO documentDAO;
	@Autowired CommonVideoDAO videoDAO;
	@Autowired ShareService shareService;
	@Autowired LabCenterDAO labCenterDAO;
	
	
	/****************************************************************************
	 * 功能：查询出所有的实验室
	 * 作者：李小龙
	 * 时间：2014-07-24
	 ****************************************************************************/
	@Override
	public Set<LabAnnex> findAllLabAnnex() {
		// TODO Auto-generated method stub
		return labAnnexDAO.findAllLabAnnexs();
	}
	
	/****************************************************************************
	 * 功能：查询出某一中心下的所有的实验室
	 * 作者：贺子龙
	 * 时间：2015-11-30
	 ****************************************************************************/
	@Override
	public List<LabAnnex> findAllLabAnnexBySchoolAcademy(int cid){
		String academyNumber="";
        // 如果没有获取有效的实验分室列表-根据登录用户的所属学院
        if (cid != -1) {
    		//获取选择的实验中心
        	academyNumber = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
        }else{
        	academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
        }
		StringBuffer sb= new StringBuffer("select l from LabAnnex l where 1=1");
		sb.append(" and l.labCenter.schoolAcademy.academyNumber like '%"+academyNumber+"%'");
		 List<LabAnnex> labAnnexs=labAnnexDAO.executeQuery(sb.toString(), 0,-1);
		 return labAnnexs;
	}
	
	/****************************************************************************
	 * 功能：根据分页信息查询出所有的实验室
	 * 作者：李小龙
	 * 时间：2014-07-25
	 ****************************************************************************/
	@Override
	public List<LabAnnex> getAllLabAnnexs(int currpage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sb= new StringBuffer("select l from LabAnnex l where 1=1 order by l.createdAt desc");
		 //给语句添加分页机制；
		 List<LabAnnex> labAnnexs=labAnnexDAO.executeQuery(sb.toString(), (currpage-1)*pageSize, pageSize);
		 return labAnnexs;
	}
	@Override
	public List<LabAnnex> findLabAnnexByLabAnnex(LabAnnex labAnnex,Integer cid) {
		// TODO Auto-generated method stub
		String sql="select l from LabAnnex l where 1=1";
		if(labAnnex.getLabName()!=null&&!labAnnex.getLabName().equalsIgnoreCase("")){
			sql+=" and l.labName like '%"+labAnnex.getLabName()+"%'";
		}
		/*if(cid!=null){
			sql+=" and l.labCenter.id="+cid;
		}	*/			
		sql+=" order by l.createdAt desc";
		//给语句添加分页机制；
		List<LabAnnex> labAnnexs=labAnnexDAO.executeQuery(sql,0,-1);
		return labAnnexs;
	}
	@Override
	public List<LabAnnex> findLabAnnexByLabAnnex(LabAnnex labAnnex,Integer cid,int page,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql="select l from LabAnnex l where 1=1";
		if(labAnnex.getLabName()!=null&&!labAnnex.getLabName().equalsIgnoreCase("")){
			sql+=" and l.labName like '%"+labAnnex.getLabName()+"%'";
		}
		/*if(cid!=null){
			sql+=" and l.labCenter.id="+cid;
		}	*/
		sql+=" order by l.createdAt desc";
		//System.out.println(sql);
		//给语句添加分页机制；
		List<LabAnnex> labAnnexs=labAnnexDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
		return labAnnexs;
	}
	/****************************************************************************
	 * 功能：删除实验室对象
	 * 作者：李小龙
	 * 时间：2014-07-25
	 ****************************************************************************/
	@Override
	public void deleteLabAnnex(LabAnnex annex) {
		// TODO Auto-generated method stub
		labAnnexDAO.remove(annex);
	}
	/****************************************************************************
	 * 功能：查询出所有的实验室类别
	 * 作者：李小龙
	 * 时间：2014-07-27
	 ****************************************************************************/
	/*@Override
	public Set<CLabAnnexType> findAllCLabAnnexType() {
		// TODO Auto-generated method stub
		return cLabAnnexTypeDAO.findAllCLabAnnexTypes();
	}*/
	/****************************************************************************
	 * 功能：保存实验室对象
	 * 作者：李小龙
	 * 时间：2014-07-27
	 ****************************************************************************/
	@Override
	public LabAnnex save(LabAnnex labAnnex) {
		// TODO Auto-generated method stub
		return labAnnexDAO.store(labAnnex);
	}
	/****************************************************************************
	 * 功能：上传实验室图片
	 * 作者：李小龙
	 * 时间：2014-07-28
	 ****************************************************************************/
	@Override
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id) {
		String sep = System.getProperty("file.separator"); 
		String path = sep+ "upload"+ sep+"labannex"+sep+id;
		shareService.uploadFiles(request, path,"saveDocument",id);
	}
	/****************************************************************************
	 * 功能：保存实验室的文档
	 * 作者：李小龙
	 * 时间：2014-07-28
	 ****************************************************************************/
	public void saveDocument(String fileTrueName, Integer labAnnexid) {
		// TODO Auto-generated method stub
		//id对应的实验室
		LabAnnex annex=labAnnexDAO.findLabAnnexByPrimaryKey(labAnnexid);
		CommonDocument doc = new CommonDocument( );
		doc.setDocumentName(fileTrueName);
		String imageUrl="upload/labannex/"+labAnnexid+"/"+fileTrueName;
		doc.setDocumentUrl(imageUrl);
		doc.setLabAnnex(annex);
		
		documentDAO.store(doc);
	}
	/****************************************************************************
	 * 功能：上传实验室视频
	 * 作者：李小龙
	 * 时间：2014-07-28
	 ****************************************************************************/
	@Override
	public void videoUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id) {
		String sep = System.getProperty("file.separator"); 
		String path = sep+ "upload"+ sep+"labannex"+sep+id;
		shareService.uploadFiles(request, path,"saveVideo",id);
	}
	/****************************************************************************
	 * 功能：保存实验室的视频
	 * 作者：李小龙
	 * 时间：2014-07-28
	 ****************************************************************************/
	@Override
	public void saveVideo(String fileTrueName, Integer labAnnexid) {
		//id对应的实验室
		LabAnnex annex=labAnnexDAO.findLabAnnexByPrimaryKey(labAnnexid);
		CommonVideo video=new CommonVideo();
		video.setVideoName(fileTrueName);
		String videoUrl="upload/labannex/"+labAnnexid+"/"+fileTrueName;
		video.setVideoUrl(videoUrl);
		video.setLabAnnex(annex);
		
		videoDAO.store(video);
	}
	/****************************************************************************
	 * 功能：根据实验室id查询实验分室
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	@Override
	public List<LabRoom> findLabRoomByLabAnnexId(LabRoom labRoom,Integer id) {
		// TODO Auto-generated method stub
		String sql="select r from LabRoom r where r.labAnnex.id="+id+" and r.isUsed=1";
		if(labRoom.getLabRoomName()!=null&&!labRoom.getLabRoomName().equalsIgnoreCase("")){
			sql+=" and r.labRoomName like '%"+labRoom.getLabRoomName()+"%'";
		}
		sql+=" order by r.id desc";
		return labRoomDAO.executeQuery(sql);
	}
	/****************************************************************************
	 * 功能：根据实验室id查询实验分室并分页
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	@Override
	public List<LabRoom> findLabRoomByLabAnnexId(LabRoom labRoom, Integer id,
			Integer page, int pageSize) {
		String sql="select r from LabRoom r where r.labAnnex.id="+id+" and r.isUsed=1";
		if(labRoom.getLabRoomNumber()!=null&&!labRoom.getLabRoomNumber().equalsIgnoreCase("")){
			sql+=" and r.labRoomNumber like '%"+labRoom.getLabRoomNumber()+"%'";
		}
		if(labRoom.getId()!=null&&!labRoom.getId().equals("")){
			sql+=" and r.id = "+labRoom.getId();
		}
		sql+=" order by r.id desc";
		System.out.println(sql);
		return labRoomDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}
	/****************************************************************************
	 * 功能：联动查询-学院&实验室
	 * 作者：徐文
	 * 日期：2016-5-10
	 ****************************************************************************/
	public String LinkSchoolAcademyAndLabAnnex(String schoolAcademy){
		//查询当前中心下的实验室
		String sql="select distinct x from LabAnnex x where 1=1";
		sql+=" and x.labCenter.schoolAcademy.academyNumber like '"+schoolAcademy + "'";
		Map<Integer,String> labAnnexMap=new HashMap<Integer, String>();
		//遍历实验室放到map集合中
		for(LabAnnex labAnnexes:labAnnexDAO.executeQuery(sql, -1,0)){
			labAnnexMap.put(Integer.valueOf(labAnnexes.getId()),labAnnexes.getLabName());
		}
		String labAnnex="<option  value=''>全部实验室</option>";
		//获取map集合的迭代器
	    Iterator<Map.Entry<Integer, String>> it = labAnnexMap.entrySet().iterator();
	    while (it.hasNext()) {
		    Map.Entry<Integer, String> entry = it.next();
		    labAnnex+="<option  value='"+ entry.getKey() +"'>"+entry.getValue()+"</option>";
		  }
		String labAnnexValue= shareService.htmlEncode(labAnnex);
		return labAnnexValue;
	}
	
	/************************************************************************************
	 * 功能：查询labannex
	 * 姓名：徐文
	 * 日期：2016-4-26
	 **************************************************************************************/
	public List<LabAnnex> findAllLabAnnexByQuery(Integer currpage, Integer pageSize, LabAnnex labAnnex, Integer cid){
		StringBuffer hql = new StringBuffer("select w from LabAnnex w where 1=1 ");
		if(labAnnex.getLabName()!=null && !"".equals(labAnnex.getLabName()))
		{
			hql.append(" and w.labName like '%"+labAnnex.getLabName()+"%'");
		}
		/*if(cid!=null){
			hql.append(" and w.labCenter.id="+cid);
		}*/				
		hql.append(" order by w.createdAt desc");
		
		List<LabAnnex> labAnnexes = labAnnexDAO.executeQuery(hql.toString(), (currpage-1)*pageSize, pageSize);
		//if (labAnnexes.size()>0) {
			//for (LabAnnex labAnnex2 : labAnnexes) {
				//int annexId = labAnnex2.getId();
				//int labRooomCount =  method(annexId);
				//labAnnex2.setLabNumber(labNumber+"");
				//labAnnexService.save(labAnnex2);
			//}
		//}
		return labAnnexes;
	}

	/**************************************************************************************
	 * 功能：根据实验室主键查找实验室对象
	 * 姓名：徐文
	 * 日期：2016-4-27
	 **************************************************************************************/
 	@Override
	public LabAnnex findLabAnnexByPrimaryKey(Integer labAnnexId){
		return labAnnexDAO.findLabAnnexByPrimaryKey(labAnnexId);
	}
	/**************************************************************************************
	 * 功能：保存实验室数据
	 * 姓名：徐文
	 * 日期：2016-4-27
	 ***************************************************************************************/
	public LabAnnex saveLabAnnex(LabAnnex labAnnex){
		return labAnnexDAO.store(labAnnex);
	}
	/*************************************************************************************
	 *功能： 删除实验室数据
	 *姓名：徐文
	 *日期：2016-4-27
	 ***********************************************************************************/
	public boolean deleteLabAnnex(Integer labAnnexId){
		LabAnnex labAnnex = labAnnexDAO.findLabAnnexByPrimaryKey(labAnnexId);
		if(labAnnex!=null)
		{
			labAnnexDAO.remove(labAnnex);
			labAnnexDAO.flush();
			return true;
		}
		
		return false;
 	}
	
	
}