/****************************************************************************
 * @功能：该service为应对西电排课中的所有公用方法接口
 * @作者：贺子龙
 * @Date：2017-10-13
 ****************************************************************************/
package net.xidlims.service.newtimetable;

import java.util.List;

import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;

public interface NewTimetableShareService {

	/*************************************************************************************
	 * @description：生成一个分批
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public TimetableBatch createTimetableBatch(String batchName, String courseDetailNo, 
			int type, Integer labRoomId, int ifSelect, Integer itemId);
	
	/*************************************************************************************
	 * @description：根据分批和组数生成分组（通过容量和学生总数）
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public List<TimetableGroup> createTimetableGroups(int batchId, int studentNumber, int capacity, int roomId, List<String> studentsAll);
	
	/*************************************************************************************
	 * @description：根据分批和组数生成分组（通过分批数和学生总数）
	 * @author：贺子龙
	 * @date：2017-10-16
	 *************************************************************************************/
	public List<TimetableGroup> createTimetableGroupsByNumber(int batchId, int studentNumber, int groupNumber, List<String> studentsAll);
	
	/*************************************************************************************
	 * @description：根据分组和学生把学生放到分组内
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public List<TimetableGroupStudents> putStudentsIntoGroup(int group, List<String> students);
	
	/*************************************************************************************
	 * @description：根据分组和学生把学生放到分组内
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public void copyBatch(Integer itemId, int sourceBatchId);
	
}
