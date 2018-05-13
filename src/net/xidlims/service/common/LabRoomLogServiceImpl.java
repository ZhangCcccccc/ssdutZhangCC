package net.xidlims.service.common;

import java.util.List;
import java.util.Set;

import net.xidlims.dao.LabRoomLogDAO;

import net.xidlims.domain.LabRoomLog;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for LabRoomLog entities
 * 
 */

@Service("LabRoomLogService")
@Transactional
public class LabRoomLogServiceImpl implements LabRoomLogService {

	/**
	 * DAO injected by Spring that manages LabRoomLog entities
	 * 
	 */
	@Autowired
	private LabRoomLogDAO labRoomLogDAO;

	/**
	 * Instantiates a new LabRoomLogServiceImpl.
	 *
	 */
	public LabRoomLogServiceImpl() {
	}

	/**
	 */
	@Transactional
	public LabRoomLog findLabRoomLogByPrimaryKey(Integer id) {
		return labRoomLogDAO.findLabRoomLogByPrimaryKey(id);
	}

	/**
	 * Load an existing LabRoomLog entity
	 * 
	 */
	@Transactional
	public Set<LabRoomLog> loadLabRoomLogs() {
		return labRoomLogDAO.findAllLabRoomLogs();
	}

	/**
	 * Delete an existing LabRoomLog entity
	 * 
	 */
	@Transactional
	public void deleteLabRoomLog(LabRoomLog labroomlog) {
		labRoomLogDAO.remove(labroomlog);
		labRoomLogDAO.flush();
	}

	/**
	 * Return a count of all LabRoomLog entity
	 * 
	 */
	@Transactional
	public Integer countLabRoomLogs() {
		return ((Long) labRoomLogDAO.createQuerySingleResult("select count(o) from LabRoomLog o").getSingleResult()).intValue();
	}

	/**
	 * Save an existing LabRoomLog entity
	 * 
	 */
	@Transactional
	public void saveLabRoomLog(LabRoomLog labroomlog) {
		LabRoomLog existingLabRoomLog = labRoomLogDAO.findLabRoomLogByPrimaryKey(labroomlog.getId());

		if (existingLabRoomLog != null) {
			if (existingLabRoomLog != labroomlog) {
				existingLabRoomLog.setId(labroomlog.getId());
				existingLabRoomLog.setAction(labroomlog.getAction());
				existingLabRoomLog.setModule(labroomlog.getModule());
				existingLabRoomLog.setDate(labroomlog.getDate());
				existingLabRoomLog.setCreateTime(labroomlog.getCreateTime());
			}
			labroomlog = labRoomLogDAO.store(existingLabRoomLog);
		} else {
			labroomlog = labRoomLogDAO.store(labroomlog);
		}
		labRoomLogDAO.flush();
	}

	/**
	 * Return all LabRoomLog entity
	 * 
	 */
	@Transactional
	public List<LabRoomLog> findAllLabRoomLogs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<LabRoomLog>(labRoomLogDAO.findAllLabRoomLogs(startResult, maxRows));
	}
}
