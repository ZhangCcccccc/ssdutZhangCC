package net.xidlims.service.cms;

import java.util.List;

import net.xidlims.dao.CmsResourceDAO;
import net.xidlims.domain.CmsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ImageService")
@Transactional
public class ImageServiceImp implements ImageService {

	@Autowired CmsResourceDAO resourceDAO;
	@Override
	public List<CmsResource> findAllImagesByImageVideo(CmsResource resource) {
		// TODO Auto-generated method stub
		String sql="select c from CmsResource c where c.imageVideo='0'";
		sql+=" order by createTime desc";
		return resourceDAO.executeQuery(sql, 0, -1);
	}

	@Override
	public List<CmsResource> findAllImagesByImageVideo(CmsResource resource,
			int page, int pageSize,int type) {
		// TODO Auto-generated method stub
		String sql="";
		if(type==1){
		 sql += "select c from CmsResource c where c.imageVideo='1'";}
		else{ sql +="select c from CmsResource c where c.imageVideo='0'";}
		sql+=" order by createTime desc";
		return resourceDAO.executeQuery(sql,(page-1)*pageSize,pageSize);
	}

	
}
