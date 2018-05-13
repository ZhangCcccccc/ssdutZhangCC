package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemLog;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemLog entities.
 * 
 */
@Repository("SystemLogDAO")
@Transactional
public class SystemLogDAOImpl extends AbstractJpaDao<SystemLog> implements
		SystemLogDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemLog.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemLogDAOImpl
	 *
	 */
	public SystemLogDAOImpl() {
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
	 * JPQL Query - findAllSystemLogs
	 *
	 */
	@Transactional
	public Set<SystemLog> findAllSystemLogs() throws DataAccessException {

		return findAllSystemLogs(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemLogs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findAllSystemLogs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemLogs", startResult, maxRows);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByObjectiveDetail
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByObjectiveDetail(String objectiveDetail) throws DataAccessException {

		return findSystemLogByObjectiveDetail(objectiveDetail, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByObjectiveDetail
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByObjectiveDetail(String objectiveDetail, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByObjectiveDetail", startResult, maxRows, objectiveDetail);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByUserIp
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByUserIp(String userIp) throws DataAccessException {

		return findSystemLogByUserIp(userIp, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByUserIp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByUserIp(String userIp, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByUserIp", startResult, maxRows, userIp);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByUserIpContaining
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByUserIpContaining(String userIp) throws DataAccessException {

		return findSystemLogByUserIpContaining(userIp, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByUserIpContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByUserIpContaining(String userIp, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByUserIpContaining", startResult, maxRows, userIp);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByChildModule
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByChildModule(String childModule) throws DataAccessException {

		return findSystemLogByChildModule(childModule, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByChildModule
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByChildModule(String childModule, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByChildModule", startResult, maxRows, childModule);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByObjectiveDetailContaining
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByObjectiveDetailContaining(String objectiveDetail) throws DataAccessException {

		return findSystemLogByObjectiveDetailContaining(objectiveDetail, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByObjectiveDetailContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByObjectiveDetailContaining(String objectiveDetail, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByObjectiveDetailContaining", startResult, maxRows, objectiveDetail);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByUserDetail
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByUserDetail(String userDetail) throws DataAccessException {

		return findSystemLogByUserDetail(userDetail, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByUserDetail
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByUserDetail(String userDetail, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByUserDetail", startResult, maxRows, userDetail);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogById
	 *
	 */
	@Transactional
	public SystemLog findSystemLogById(Integer id) throws DataAccessException {

		return findSystemLogById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogById
	 *
	 */

	@Transactional
	public SystemLog findSystemLogById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemLogById", startResult, maxRows, id);
			return (net.xidlims.domain.SystemLog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemLogByCalanderTime
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByCalanderTime(java.util.Calendar calanderTime) throws DataAccessException {

		return findSystemLogByCalanderTime(calanderTime, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByCalanderTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByCalanderTime(java.util.Calendar calanderTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByCalanderTime", startResult, maxRows, calanderTime);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogBySuperModuleContaining
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogBySuperModuleContaining(String superModule) throws DataAccessException {

		return findSystemLogBySuperModuleContaining(superModule, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogBySuperModuleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogBySuperModuleContaining(String superModule, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogBySuperModuleContaining", startResult, maxRows, superModule);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByUserDetailContaining
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByUserDetailContaining(String userDetail) throws DataAccessException {

		return findSystemLogByUserDetailContaining(userDetail, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByUserDetailContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByUserDetailContaining(String userDetail, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByUserDetailContaining", startResult, maxRows, userDetail);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByOperationAction
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByOperationAction(Integer operationAction) throws DataAccessException {

		return findSystemLogByOperationAction(operationAction, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByOperationAction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByOperationAction(Integer operationAction, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByOperationAction", startResult, maxRows, operationAction);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByPrimaryKey
	 *
	 */
	@Transactional
	public SystemLog findSystemLogByPrimaryKey(Integer id) throws DataAccessException {

		return findSystemLogByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByPrimaryKey
	 *
	 */

	@Transactional
	public SystemLog findSystemLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemLogByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SystemLog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemLogBySuperModule
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogBySuperModule(String superModule) throws DataAccessException {

		return findSystemLogBySuperModule(superModule, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogBySuperModule
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogBySuperModule(String superModule, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogBySuperModule", startResult, maxRows, superModule);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemLogByChildModuleContaining
	 *
	 */
	@Transactional
	public Set<SystemLog> findSystemLogByChildModuleContaining(String childModule) throws DataAccessException {

		return findSystemLogByChildModuleContaining(childModule, -1, -1);
	}

	/**
	 * JPQL Query - findSystemLogByChildModuleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemLog> findSystemLogByChildModuleContaining(String childModule, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemLogByChildModuleContaining", startResult, maxRows, childModule);
		return new LinkedHashSet<SystemLog>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemLog entity) {
		return true;
	}
}
