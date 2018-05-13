package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SystemLog;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemLog entities.
 * 
 */
public interface SystemLogDAO extends JpaDao<SystemLog> {

	/**
	 * JPQL Query - findAllSystemLogs
	 *
	 */
	public Set<SystemLog> findAllSystemLogs() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemLogs
	 *
	 */
	public Set<SystemLog> findAllSystemLogs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByObjectiveDetail
	 *
	 */
	public Set<SystemLog> findSystemLogByObjectiveDetail(String objectiveDetail) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByObjectiveDetail
	 *
	 */
	public Set<SystemLog> findSystemLogByObjectiveDetail(String objectiveDetail, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserIp
	 *
	 */
	public Set<SystemLog> findSystemLogByUserIp(String userIp) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserIp
	 *
	 */
	public Set<SystemLog> findSystemLogByUserIp(String userIp, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserIpContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByUserIpContaining(String userIp_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserIpContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByUserIpContaining(String userIp_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByChildModule
	 *
	 */
	public Set<SystemLog> findSystemLogByChildModule(String childModule) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByChildModule
	 *
	 */
	public Set<SystemLog> findSystemLogByChildModule(String childModule, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByObjectiveDetailContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByObjectiveDetailContaining(String objectiveDetail_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByObjectiveDetailContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByObjectiveDetailContaining(String objectiveDetail_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserDetail
	 *
	 */
	public Set<SystemLog> findSystemLogByUserDetail(String userDetail) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserDetail
	 *
	 */
	public Set<SystemLog> findSystemLogByUserDetail(String userDetail, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogById
	 *
	 */
	public SystemLog findSystemLogById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogById
	 *
	 */
	public SystemLog findSystemLogById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByCalanderTime
	 *
	 */
	public Set<SystemLog> findSystemLogByCalanderTime(java.util.Calendar calanderTime) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByCalanderTime
	 *
	 */
	public Set<SystemLog> findSystemLogByCalanderTime(Calendar calanderTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogBySuperModuleContaining
	 *
	 */
	public Set<SystemLog> findSystemLogBySuperModuleContaining(String superModule) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogBySuperModuleContaining
	 *
	 */
	public Set<SystemLog> findSystemLogBySuperModuleContaining(String superModule, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserDetailContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByUserDetailContaining(String userDetail_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByUserDetailContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByUserDetailContaining(String userDetail_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByOperationAction
	 *
	 */
	public Set<SystemLog> findSystemLogByOperationAction(Integer operationAction) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByOperationAction
	 *
	 */
	public Set<SystemLog> findSystemLogByOperationAction(Integer operationAction, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByPrimaryKey
	 *
	 */
	public SystemLog findSystemLogByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByPrimaryKey
	 *
	 */
	public SystemLog findSystemLogByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogBySuperModule
	 *
	 */
	public Set<SystemLog> findSystemLogBySuperModule(String superModule_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogBySuperModule
	 *
	 */
	public Set<SystemLog> findSystemLogBySuperModule(String superModule_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByChildModuleContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByChildModuleContaining(String childModule_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemLogByChildModuleContaining
	 *
	 */
	public Set<SystemLog> findSystemLogByChildModuleContaining(String childModule_1, int startResult, int maxRows) throws DataAccessException;

}