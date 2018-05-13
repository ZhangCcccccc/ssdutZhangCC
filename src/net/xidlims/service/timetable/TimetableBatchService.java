package net.xidlims.service.timetable;

import java.util.List;

import net.xidlims.domain.TimetableBatch;

public interface TimetableBatchService {

	/*************************************************************************************
	 * @內容：查看所有的时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public List<TimetableBatch> getTimetableBatchByQuery(int termId, int status,int curr,
			int size,int iLabCenter);

	/*************************************************************************************
	 * @內容：查看计数的所有时间列表安排
	 * @作者： 魏誠
	 * @日期：2014-07-24
	 *************************************************************************************/
	public int getCountTimetableBatchByQuery(int termId,int status,int iLabCenter);
}