package net.xidlims.service.visualization;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.LabRoom;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.SystemRoom;

public interface VisualizationService {
	
	/**
	 * 根据楼宇和楼层查找楼层房间列表
	 * 裴继超
	 * 2016年1月23日
	 */
	public List<LabRoom> getLabRoomsByBuildAndFloor(String buildNumber,String floor,
			Integer page,int pageSize) ;
	
	/**
	 * 根据id查找实验室
	 * 裴继超
	 * 2016年1月23日
	 */
	public LabRoom findLabRoomByPrimaryKey(int id) ;
	
	/**
	 * 保存实验室设备
	 * 裴继超
	 * 2016年1月23日
	 */
	public void saveLabRoomDevice(LabRoomDevice labRoomDevice);
	
	/**
	 * 保存设备字典
	 * 裴继超
	 * 2016年1月27日
	 */
	public SchoolDevice saveSchoolDevice(LabRoomDevice labRoomDevice);
	
	/**
	 * 根据id查找实验室设备
	 * 裴继超
	 * 2016年1月27日
	 */
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(int id);
	
	/**
	 * 删除设备字典
	 * 裴继超
	 * 2016年1月28日
	 */
	public void deletSchoolDevice(String schoolDeviceNumber);
	
	/**
	 * 删除实验室设备位置标记
	 * 裴继超
	 * 2016年1月28日
	 */
	public void deletLabRoomDeviceXY(int id);
	
	/**
	 * 根据是否存在坐标查找实验室设备
	 * 裴继超
	 * 2016年3月24日11:09:34
	 */
	public List<LabRoomDevice> findLabRoomDevicesByLabRoomIdAndXY(int labRoomId);
	
	/**
	 * floor页面替换实验室详细信息map
	 * 裴继超
	 * 2016年3月25日
	 */
	public Map findLabRoomMap(LabRoom labRoom);
	
	/**
	 * 根据tag查找channel
	 * 裴继超
	 * 2016年3月25日
	 *//*
	public List<Channel> findChannelsByTag(int tag);*/
	
	/****************************************************************************
	 * 功能：保存实验分室
	 * 作者：李小龙
	 * 时间：2014-07-29
	 ****************************************************************************/
	public LabRoom save(LabRoom labRoom);
	
	/**
	 * 根据主键查找字典实验室
	 * 裴继超
	 * 2016年5月27日
	 */
	public SystemRoom findSystemRoomByPrimaryKey(String nummber) ;
	
	/****************************************************************************
	 * 功能：给实验室上传图片
	 * 作者：裴继超
	 * 时间：2016年5月27日
	 ****************************************************************************/
	public void uploadImageForLabRoom(HttpServletRequest request,
			HttpServletResponse response, Integer id,Integer type);

	/****************************************************************************
	 * description:删除图片
	 * 
	 * author:于侃
	 * date:2016年9月21日 14:45:13
	 ****************************************************************************/
	public void deleteImageForLabRoom(Integer labRoomid,Integer type,HttpServletRequest request);
	
	/****************************************************************************
	 * description:下载图片
	 * 
	 * author:于侃
	 * date:2016年9月22日 14:21:28
	 ****************************************************************************/
	public void downloadImageForLabRoom(Integer id,HttpServletRequest request,
			HttpServletResponse response);
}
