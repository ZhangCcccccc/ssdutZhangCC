/****************************************************************************
 * @功能：该service为应对西电排课中的所有公用方法实现
 * @作者：贺子龙
 * @Date：2017-10-13
 ****************************************************************************/
package net.xidlims.service.newtimetable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.luxunsh.util.EmptyUtil;
import net.xidlims.dao.LabRoomDAO;
import net.xidlims.dao.OperationItemDAO;
import net.xidlims.dao.TimetableBatchDAO;
import net.xidlims.dao.TimetableGroupDAO;
import net.xidlims.dao.TimetableGroupStudentsDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.LabRoom;
import net.xidlims.domain.TimetableBatch;
import net.xidlims.domain.TimetableGroup;
import net.xidlims.domain.TimetableGroupStudents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 贺子龙
 *
 */
@Service("NewTimetableShareService")
public class NewTimetableShareServiceImpl implements NewTimetableShareService{
	
	@Autowired
	private TimetableBatchDAO timetableBatchDAO;
	@Autowired
	private OperationItemDAO operationItemDAO;
	@Autowired
	private LabRoomDAO labRoomDAO;
	@Autowired
	private TimetableGroupDAO timetableGroupDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TimetableGroupStudentsDAO timetableGroupStudentsDAO;
	@Autowired
	private NewTimetableCourseSchedulingService newTimetableCourseSchedulingService;
	/*************************************************************************************
	 * @description：生成一个分批
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public TimetableBatch createTimetableBatch(String batchName, String courseDetailNo, 
			int type, Integer labRoomId, int ifSelect, Integer itemId){
		
		TimetableBatch timetableBatch = new TimetableBatch();
        timetableBatch.setBatchName(batchName);
        timetableBatch.setCourseCode(courseDetailNo);
        timetableBatch.setType(type);
        timetableBatch.setIfselect(ifSelect);
        timetableBatch.setOperationItem(operationItemDAO
                .findOperationItemById(itemId));
        timetableBatch = timetableBatchDAO.store(timetableBatch);
		return timetableBatch;
	}
	
	/*************************************************************************************
	 * @description：根据分批和组数生成分组（通过容量和学生总数）
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public List<TimetableGroup> createTimetableGroups(int batchId, int studentNumber, int capacity, int roomId, List<String> studentsAll){
		// 获取分批
		TimetableBatch timetableBatch = timetableBatchDAO.findTimetableBatchById(batchId);
		// 根据学生和容量确定分组
		int groupNumber = studentNumber / capacity;
        if (studentNumber % capacity != 0){
        	groupNumber += 1;
        }
		// 获取实验室
		LabRoom labRoom = labRoomDAO.findLabRoomById(roomId);
		// 新建分组list
		List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
		Iterator<String> iterator = studentsAll.iterator();
		// 遍历依次创建group
        for (int i = 1; i < groupNumber + 1; i++) {
            TimetableGroup timetableGroup = new TimetableGroup();
            timetableGroup.setTimetableBatch(timetableBatch);
            timetableGroup.setGroupName("第" + i + "批");
            timetableGroup.setTimetableStyle(timetableBatch.getType());
            timetableGroup.setLabRoom(labRoom);
            if (i != groupNumber) {
                timetableGroup.setNumbers(capacity);
            } else {
                timetableGroup.setNumbers(studentNumber - capacity * (groupNumber - 1));
            }
            timetableGroup = timetableGroupDAO.store(timetableGroup);
            groups.add(timetableGroup);
            if (iterator != null && iterator.hasNext()) {
            	for (int j = 1; j <= timetableGroup.getNumbers(); j++) {
            		TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
            		timetableGroupStudents.setUser(userDAO
            				.findUserByPrimaryKey(iterator.next()));
            		timetableGroupStudents.setTimetableGroup(timetableGroup);
            		timetableGroupStudentsDAO.store(timetableGroupStudents);
            		timetableGroupStudentsDAO.flush();
            	}
            }
        }
        return groups;
	}
	
	/*************************************************************************************
	 * @description：根据分批和组数生成分组（通过分批数和学生总数）
	 * @author：贺子龙
	 * @date：2017-10-16
	 *************************************************************************************/
	public List<TimetableGroup> createTimetableGroupsByNumber(int batchId, int studentNumber, int groupNumber, List<String> studentsAll){
		// 获取分批
		TimetableBatch timetableBatch = timetableBatchDAO.findTimetableBatchById(batchId);
		Integer everyGroupNumbers = studentNumber / groupNumber;
		Iterator<String> iterator = null;
		if (studentsAll!=null && studentsAll.size()>0) {
			iterator = studentsAll.iterator();
		}
		// 新建分组list
		List<TimetableGroup> groups = new LinkedList<TimetableGroup>();
		for (int i = 1; i < groupNumber + 1; i++) {
			TimetableGroup timetableGroup = new TimetableGroup();
			timetableGroup.setTimetableBatch(timetableBatch);
			timetableGroup.setGroupName("第" + i + "批");
			timetableGroup.setTimetableStyle(timetableBatch.getType());
			if (studentsAll!=null && studentsAll.size()>0) {
				if (i != groupNumber) {
					timetableGroup.setNumbers(everyGroupNumbers);
				} else {
					timetableGroup.setNumbers(studentNumber - everyGroupNumbers
							* (groupNumber - 1));
				}
			}
			timetableGroup = timetableGroupDAO.store(timetableGroup);
			groups.add(timetableGroup);
			if (iterator != null && iterator.hasNext()) {
				for (int j = 1; j <= timetableGroup.getNumbers(); j++) {
					TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
					String username = iterator.next();
					timetableGroupStudents.setUser(userDAO.findUserByPrimaryKey(username));
					timetableGroupStudents.setTimetableGroup(timetableGroup);
					timetableGroupStudentsDAO.store(timetableGroupStudents);
					timetableGroupStudentsDAO.flush();
				}
			}
		}
		return groups;
	}
	
	
	/*************************************************************************************
	 * @description：根据分组和学生把学生放到分组内
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public List<TimetableGroupStudents> putStudentsIntoGroup(int group, List<String> studentsAll){
		List<TimetableGroupStudents> students = new LinkedList<TimetableGroupStudents>();
		Iterator<String> iterator = studentsAll.iterator();
        TimetableGroup timetableGroup = timetableGroupDAO
        		.findTimetableGroupById(group);
        if (timetableGroup.getTimetableGroupStudentses() == null
        		|| timetableGroup.getTimetableGroupStudentses() != null
        		&& timetableGroup.getTimetableGroupStudentses().size() != timetableGroup
        		.getNumbers()) {
        	for (int j = 1; j <= timetableGroup.getNumbers(); j++) {
        		TimetableGroupStudents timetableGroupStudents = new TimetableGroupStudents();
        		timetableGroupStudents.setUser(userDAO
        				.findUserByPrimaryKey(iterator.next()));
        		timetableGroupStudents
        		.setTimetableGroup(timetableGroup);
        		timetableGroupStudents = timetableGroupStudentsDAO.store(timetableGroupStudents);
        		timetableGroupStudentsDAO.flush();
        		students.add(timetableGroupStudents);
        	}
        }
        return students;
	}
	
	/*************************************************************************************
	 * @description：根据分组和学生把学生放到分组内
	 * @author：贺子龙
	 * @date：2017-10-13
	 *************************************************************************************/
	public void copyBatch(Integer itemId, int sourceBatchId){
		// 找到被复制的批次
    	TimetableBatch sourceBatch = timetableBatchDAO.findTimetableBatchById(sourceBatchId);
    	// 新建批次
    	TimetableBatch newBatch = new TimetableBatch();
    	newBatch.copy(sourceBatch);
    	newBatch.setOperationItem(operationItemDAO.findOperationItemById(itemId));
    	newBatch = timetableBatchDAO.store(newBatch);
    	// 找到被复制的组
    	List<TimetableGroup> sourceGroups = newTimetableCourseSchedulingService.findTimetableGroupsByBacthId(sourceBatchId);
    	if (sourceGroups.size()>0) {
    		for (TimetableGroup sourceGroup : sourceGroups) {
    			TimetableGroup newGroup = new TimetableGroup();
    			newGroup.copy(sourceGroup);
    			newGroup.setTimetableBatch(newBatch);
    			newGroup.setTimetableGroupStudentses(null);
    			newGroup = timetableGroupDAO.store(newGroup);
    			// 生成分组下的学生
    			this.putStudentsIntoGroup(newGroup.getId(), 
    					newTimetableCourseSchedulingService.findUserBygroupId(sourceGroup.getId()));
    		}
		}
	}

}
