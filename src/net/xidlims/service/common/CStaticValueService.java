package net.xidlims.service.common;

import net.xidlims.domain.CStaticValue;

public interface CStaticValueService {

	/***********************************************************************************************
	 * @功能：根据所选实验中心，获取该学院的实验室设备资源在排课中的对象
	 * @作者：魏诚
	 * @日期：2015-11-03
	 ***********************************************************************************************/

	public CStaticValue getCStaticValueByTimetableLabDevice(int cid) ;
}