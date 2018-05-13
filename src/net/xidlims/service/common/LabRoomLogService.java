package net.xidlims.service.common;

import java.util.List;
import java.util.Set;

import net.xidlims.domain.LabRoomLog;

/**
 * Spring service that handles CRUD requests for LabRoomLog entities
 * 
 */
public interface LabRoomLogService {

	/**
	 */
	public LabRoomLog findLabRoomLogByPrimaryKey(Integer id);

	/**
	 * Load an existing LabRoomLog entity
	 * 
	 */
	public Set<LabRoomLog> loadLabRoomLogs();

	/**
	 * Delete an existing LabRoomLog entity
	 * 
	 */
	public void deleteLabRoomLog(LabRoomLog labroomlog);

	/**
	 * Return a count of all LabRoomLog entity
	 * 
	 */
	public Integer countLabRoomLogs();

	/**
	 * Save an existing LabRoomLog entity
	 * 
	 */
	public void saveLabRoomLog(LabRoomLog labroomlog_1);

	/**
	 * Return all LabRoomLog entity
	 * 
	 */
	public List<LabRoomLog> findAllLabRoomLogs(Integer startResult, Integer maxRows);
}