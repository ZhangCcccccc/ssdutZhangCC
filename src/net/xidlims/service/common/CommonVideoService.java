package net.xidlims.service.common;

import java.util.List;

import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.CommonVideo;



public interface CommonVideoService {
	
	/***********************************************************************************************
	 * 根据实验室id查询视频
	 * 作者：李小龙 
	 ***********************************************************************************************/
	public List<CommonVideo> findVideoByLabAnnexId(Integer id);
	/***********************************************************************************************
	 * 根据视频主键查询视频
	 * 作者：李小龙 
	 ***********************************************************************************************/
	public CommonVideo findVideoByPrimaryKey(Integer id);
	/***********************************************************************************************
	 * 删除视频
	 * 作者：李小龙 
	 ***********************************************************************************************/
	public void deleteCommonVideo(CommonVideo video);
	
	

	

}