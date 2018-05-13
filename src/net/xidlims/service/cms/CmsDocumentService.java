package net.xidlims.service.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.CmsDocument;
import net.xidlims.domain.CmsResource;


public interface CmsDocumentService {

	/**
	 * 查询图片资源
	 * @param resource
	 * @return
	 */
	public List <CmsDocument> findAllCmsDocuments();
	
	/**
	 * 
	 * 根据查出来的图片进行分页操作
	 * 王建羽 2105年9月30日
	 * @param resource
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<CmsDocument> findAllCmsDocuments(int page, int pageSize);
	//查找新生成的附件
	public int lastDocument() ;
	/****************************************************************************
	 * 功能：上传附件
	 * 作者：裴继超
	 * 时间：2015年12月18日13:01:51
	 ****************************************************************************/
	public void uploadDocument(HttpServletRequest request,
			HttpServletResponse response, Integer id,Integer type) ;
	/**********************************************************************************
	 * 下载附件
	 *
	 **********************************************************************************/
	public void downloadFile(CmsDocument document, HttpServletRequest request,
			HttpServletResponse response) ;

}
