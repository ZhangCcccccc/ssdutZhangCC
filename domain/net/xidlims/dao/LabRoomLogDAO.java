package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabRoomLog;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomLog entities.
 * 
 */
public interface LabRoomLogDAO extends JpaDao<LabRoomLog> {

	/**
	 * JPQL Query - findLabRoomLogByModule
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByModule(String module) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByModule
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByModule(String module, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDayS
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDayS(Integer dayS) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDayS
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDayS(Integer dayS, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogBySchoolCourse
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogBySchoolCourse(Integer schoolCourse) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogBySchoolCourse
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogBySchoolCourse(Integer schoolCourse, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomLogs
	 *
	 */
	public Set<LabRoomLog> findAllLabRoomLogs() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomLogs
	 *
	 */
	public Set<LabRoomLog> findAllLabRoomLogs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDateAfter
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDateAfter(java.util.Calendar date) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDateAfter
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDateAfter(Calendar date, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByCreateTime
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByCreateTime
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogBySectionSContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogBySectionSContaining(String sectionS) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogBySectionSContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogBySectionSContaining(String sectionS, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByActionContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByActionContaining(String action) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByActionContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByActionContaining(String action, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByPrimaryKey
	 *
	 */
	public LabRoomLog findLabRoomLogByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByPrimaryKey
	 *
	 */
	public LabRoomLog findLabRoomLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDateBefore
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDateBefore(java.util.Calendar date_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDateBefore
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDateBefore(Calendar date_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDate
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDate(java.util.Calendar date_2) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByDate
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByDate(Calendar date_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByStudentNoContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByStudentNoContaining(String studentNo) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByStudentNoContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByStudentNoContaining(String studentNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByStudentNo
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByStudentNo(String studentNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByStudentNo
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByStudentNo(String studentNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogBySectionS
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogBySectionS(String sectionS_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogBySectionS
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogBySectionS(String sectionS_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogById
	 *
	 */
	public LabRoomLog findLabRoomLogById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogById
	 *
	 */
	public LabRoomLog findLabRoomLogById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByWeekS
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByWeekS(Integer weekS) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByWeekS
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByWeekS(Integer weekS, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByAction
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByAction(String action_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByAction
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByAction(String action_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByModuleContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByModuleContaining(String module_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomLogByModuleContaining
	 *
	 */
	public Set<LabRoomLog> findLabRoomLogByModuleContaining(String module_1, int startResult, int maxRows) throws DataAccessException;

}