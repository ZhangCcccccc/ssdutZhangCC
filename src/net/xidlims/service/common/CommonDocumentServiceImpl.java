package net.xidlims.service.common;

import java.util.List;

import net.xidlims.domain.LabRoom;
import net.xidlims.dao.CommonDocumentDAO;
import net.xidlims.domain.CommonDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommonDocumentService")
public class CommonDocumentServiceImpl implements CommonDocumentService {
	
	@Autowired CommonDocumentDAO commonDocumentDAO;
	/***********************************************************************************************
	 * 根据实验室id查询图片
	 * 作者：李小龙 
	 ***********************************************************************************************/
	@Override
	public List<CommonDocument> findImageByLabAnnexId(Integer id) {
		// TODO Auto-generated method stub
		String sql="select c from CommonDocument c where c.labRoom.id="+id;
		return commonDocumentDAO.executeQuery(sql) ;
	}
	/***********************************************************************************************
	 * 根据主键查询图片
	 * 作者：李小龙 
	 ***********************************************************************************************/
	@Override
	public CommonDocument findCommonDocumentByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return commonDocumentDAO.findCommonDocumentByPrimaryKey(id);
	}
	/***********************************************************************************************
	 * 删除图片对象
	 * 作者：李小龙 
	 ***********************************************************************************************/
	@Override
	public void deleteCommonDocument(CommonDocument doc) {
		// TODO Auto-generated method stub
		commonDocumentDAO.remove(doc);
	}
	

	/***********************************************************************************************
	 * 根据实验室编号查询实验室图片
	 * 作者：裴继超
	 * 2016年3月14日
	 ***********************************************************************************************/
	@Override
	public CommonDocument findCommonDocumentByLabRoom(LabRoom lr,int type) {
		// TODO Auto-generated method stub
		String sql = "select c from CommonDocument c where c.labRoom.id = "+lr.getId()+" and c.type = "+type;
		List<CommonDocument> list=commonDocumentDAO.executeQuery(sql);
		if(list.size()>0){
		return list.get(0);
		}else
		{
			return null;
		}
	}
	
	/***********************************************************************************************
	 * description:根据实验室编号查询实验室图片
	 * 
	 * author:于侃
	 * date:2016年9月21日 17:14:48
	 ***********************************************************************************************/
	@Override
	public List<CommonDocument> findCommonDocumentsByLabRoom(LabRoom lr,int type) {
		String sql = "select c from CommonDocument c where c.labRoom.id = "+lr.getId()+" and c.type = "+type;
		List<CommonDocument> list=commonDocumentDAO.executeQuery(sql);
		if(list.size()>0){
		return list;
		}else
		{
			return null;
		}
	}
}