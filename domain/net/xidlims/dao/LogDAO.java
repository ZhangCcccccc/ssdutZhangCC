package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.Log;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Log entities.
 * 
 */
public interface LogDAO extends JpaDao<Log> {

	/**
	 * JPQL Query - findLogByPrimaryKey
	 *
	 */
	public Log findLogByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLogByPrimaryKey
	 *
	 */
	public Log findLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByUseridContaining
	 *
	 */
	public Set<Log> findLogByUseridContaining(String userid) throws DataAccessException;

	/**
	 * JPQL Query - findLogByUseridContaining
	 *
	 */
	public Set<Log> findLogByUseridContaining(String userid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByCreateTime
	 *
	 */
	public Set<Log> findLogByCreateTime(String createTime) throws DataAccessException;

	/**
	 * JPQL Query - findLogByCreateTime
	 *
	 */
	public Set<Log> findLogByCreateTime(String createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByIp
	 *
	 */
	public Set<Log> findLogByIp(String ip) throws DataAccessException;

	/**
	 * JPQL Query - findLogByIp
	 *
	 */
	public Set<Log> findLogByIp(String ip, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByActionContaining
	 *
	 */
	public Set<Log> findLogByActionContaining(String action) throws DataAccessException;

	/**
	 * JPQL Query - findLogByActionContaining
	 *
	 */
	public Set<Log> findLogByActionContaining(String action, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByIpContaining
	 *
	 */
	public Set<Log> findLogByIpContaining(String ip_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogByIpContaining
	 *
	 */
	public Set<Log> findLogByIpContaining(String ip_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByDataContaining
	 *
	 */
	public Set<Log> findLogByDataContaining(String data) throws DataAccessException;

	/**
	 * JPQL Query - findLogByDataContaining
	 *
	 */
	public Set<Log> findLogByDataContaining(String data, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByUserid
	 *
	 */
	public Set<Log> findLogByUserid(String userid_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogByUserid
	 *
	 */
	public Set<Log> findLogByUserid(String userid_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByAction
	 *
	 */
	public Set<Log> findLogByAction(String action_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogByAction
	 *
	 */
	public Set<Log> findLogByAction(String action_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByModule
	 *
	 */
	public Set<Log> findLogByModule(String module) throws DataAccessException;

	/**
	 * JPQL Query - findLogByModule
	 *
	 */
	public Set<Log> findLogByModule(String module, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByCreateTimeContaining
	 *
	 */
	public Set<Log> findLogByCreateTimeContaining(String createTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogByCreateTimeContaining
	 *
	 */
	public Set<Log> findLogByCreateTimeContaining(String createTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByModuleContaining
	 *
	 */
	public Set<Log> findLogByModuleContaining(String module_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogByModuleContaining
	 *
	 */
	public Set<Log> findLogByModuleContaining(String module_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogById
	 *
	 */
	public Log findLogById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogById
	 *
	 */
	public Log findLogById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLogs
	 *
	 */
	public Set<Log> findAllLogs() throws DataAccessException;

	/**
	 * JPQL Query - findAllLogs
	 *
	 */
	public Set<Log> findAllLogs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLogByData
	 *
	 */
	public Set<Log> findLogByData(String data_1) throws DataAccessException;

	/**
	 * JPQL Query - findLogByData
	 *
	 */
	public Set<Log> findLogByData(String data_1, int startResult, int maxRows) throws DataAccessException;

}