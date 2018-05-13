package net.xidlims.service.cms;

import java.util.List;

import net.xidlims.domain.CmsResource;


public interface ImageService {

	/**
	 * 查询图片资源
	 * @param resource
	 * @return
	 */
	public List <CmsResource> findAllImagesByImageVideo(CmsResource resource);
	
	/**
	 * 
	 * 根据查出来的图片进行分页操作
	 * 王建羽 2105年9月30日
	 * @param resource
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<CmsResource>findAllImagesByImageVideo(CmsResource resource,int page, int pageSize,int type);

}
