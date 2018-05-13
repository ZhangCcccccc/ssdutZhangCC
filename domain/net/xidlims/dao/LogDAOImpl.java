package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.Log;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Log entities.
 * 
 */
@Repository("LogDAO")
@Transactional
public class LogDAOImpl extends AbstractJpaDao<Log> implements LogDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Log.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LogDAOImpl
	 *
	 */
	public LogDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findLogByPrimaryKey
	 *
	 */
	@Transactional
	public Log findLogByPrimaryKey(Integer id) throws DataAccessException {

		return findLogByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLogByPrimaryKey
	 *
	 */

	@Transactional
	public Log findLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLogByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.Log) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLogByUseridContaining
	 *
	 */
	@Transactional
	public Set<Log> findLogByUseridContaining(String userid) throws DataAccessException {

		return findLogByUseridContaining(userid, -1, -1);
	}

	/**
	 * JPQL Query - findLogByUseridContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByUseridContaining(String userid, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByUseridContaining", startResult, maxRows, userid);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByCreateTime
	 *
	 */
	@Transactional
	public Set<Log> findLogByCreateTime(String createTime) throws DataAccessException {

		return findLogByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findLogByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByCreateTime(String createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByIp
	 *
	 */
	@Transactional
	public Set<Log> findLogByIp(String ip) throws DataAccessException {

		return findLogByIp(ip, -1, -1);
	}

	/**
	 * JPQL Query - findLogByIp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByIp(String ip, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByIp", startResult, maxRows, ip);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByActionContaining
	 *
	 */
	@Transactional
	public Set<Log> findLogByActionContaining(String action) throws DataAccessException {

		return findLogByActionContaining(action, -1, -1);
	}

	/**
	 * JPQL Query - findLogByActionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByActionContaining(String action, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByActionContaining", startResult, maxRows, action);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByIpContaining
	 *
	 */
	@Transactional
	public Set<Log> findLogByIpContaining(String ip) throws DataAccessException {

		return findLogByIpContaining(ip, -1, -1);
	}

	/**
	 * JPQL Query - findLogByIpContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByIpContaining(String ip, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByIpContaining", startResult, maxRows, ip);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByDataContaining
	 *
	 */
	@Transactional
	public Set<Log> findLogByDataContaining(String data) throws DataAccessException {

		return findLogByDataContaining(data, -1, -1);
	}

	/**
	 * JPQL Query - findLogByDataContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByDataContaining(String data, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByDataContaining", startResult, maxRows, data);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByUserid
	 *
	 */
	@Transactional
	public Set<Log> findLogByUserid(String userid) throws DataAccessException {

		return findLogByUserid(userid, -1, -1);
	}

	/**
	 * JPQL Query - findLogByUserid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByUserid(String userid, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByUserid", startResult, maxRows, userid);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByAction
	 *
	 */
	@Transactional
	public Set<Log> findLogByAction(String action) throws DataAccessException {

		return findLogByAction(action, -1, -1);
	}

	/**
	 * JPQL Query - findLogByAction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByAction(String action, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByAction", startResult, maxRows, action);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByModule
	 *
	 */
	@Transactional
	public Set<Log> findLogByModule(String module) throws DataAccessException {

		return findLogByModule(module, -1, -1);
	}

	/**
	 * JPQL Query - findLogByModule
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByModule(String module, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByModule", startResult, maxRows, module);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByCreateTimeContaining
	 *
	 */
	@Transactional
	public Set<Log> findLogByCreateTimeContaining(String createTime) throws DataAccessException {

		return findLogByCreateTimeContaining(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findLogByCreateTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByCreateTimeContaining(String createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByCreateTimeContaining", startResult, maxRows, createTime);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByModuleContaining
	 *
	 */
	@Transactional
	public Set<Log> findLogByModuleContaining(String module) throws DataAccessException {

		return findLogByModuleContaining(module, -1, -1);
	}

	/**
	 * JPQL Query - findLogByModuleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByModuleContaining(String module, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByModuleContaining", startResult, maxRows, module);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogById
	 *
	 */
	@Transactional
	public Log findLogById(Integer id) throws DataAccessException {

		return findLogById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLogById
	 *
	 */

	@Transactional
	public Log findLogById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLogById", startResult, maxRows, id);
			return (net.xidlims.domain.Log) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLogs
	 *
	 */
	@Transactional
	public Set<Log> findAllLogs() throws DataAccessException {

		return findAllLogs(-1, -1);
	}

	/**
	 * JPQL Query - findAllLogs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findAllLogs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLogs", startResult, maxRows);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * JPQL Query - findLogByData
	 *
	 */
	@Transactional
	public Set<Log> findLogByData(String data) throws DataAccessException {

		return findLogByData(data, -1, -1);
	}

	/**
	 * JPQL Query - findLogByData
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Log> findLogByData(String data, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLogByData", startResult, maxRows, data);
		return new LinkedHashSet<Log>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Log entity) {
		return true;
	}
}
