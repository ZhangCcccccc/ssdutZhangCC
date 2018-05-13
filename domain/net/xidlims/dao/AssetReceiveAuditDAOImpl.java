package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetReceiveAudit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetReceiveAudit entities.
 * 
 */
@Repository("AssetReceiveAuditDAO")
@Transactional
public class AssetReceiveAuditDAOImpl extends AbstractJpaDao<AssetReceiveAudit>
		implements AssetReceiveAuditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetReceiveAudit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetReceiveAuditDAOImpl
	 *
	 */
	public AssetReceiveAuditDAOImpl() {
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
	 * JPQL Query - findAssetReceiveAuditByAuditRoles
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByAuditRoles(Integer auditRoles) throws DataAccessException {

		return findAssetReceiveAuditByAuditRoles(auditRoles, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByAuditRoles
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByAuditRoles(Integer auditRoles, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAuditByAuditRoles", startResult, maxRows, auditRoles);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByMem
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByMem(String mem) throws DataAccessException {

		return findAssetReceiveAuditByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAuditByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByResult
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResult(String result) throws DataAccessException {

		return findAssetReceiveAuditByResult(result, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByResult
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResult(String result, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAuditByResult", startResult, maxRows, result);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAuditById
	 *
	 */
	@Transactional
	public AssetReceiveAudit findAssetReceiveAuditById(Integer id) throws DataAccessException {

		return findAssetReceiveAuditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditById
	 *
	 */

	@Transactional
	public AssetReceiveAudit findAssetReceiveAuditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveAuditById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceiveAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAssetReceiveAudits
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAllAssetReceiveAudits() throws DataAccessException {

		return findAllAssetReceiveAudits(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetReceiveAudits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAllAssetReceiveAudits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetReceiveAudits", startResult, maxRows);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByPrimaryKey
	 *
	 */
	@Transactional
	public AssetReceiveAudit findAssetReceiveAuditByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetReceiveAuditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByPrimaryKey
	 *
	 */

	@Transactional
	public AssetReceiveAudit findAssetReceiveAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveAuditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceiveAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByStatus
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByStatus(Integer status) throws DataAccessException {

		return findAssetReceiveAuditByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAuditByStatus", startResult, maxRows, status);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByCreateDate
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findAssetReceiveAuditByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAuditByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByResultContaining
	 *
	 */
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResultContaining(String result) throws DataAccessException {

		return findAssetReceiveAuditByResultContaining(result, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAuditByResultContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAudit> findAssetReceiveAuditByResultContaining(String result, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAuditByResultContaining", startResult, maxRows, result);
		return new LinkedHashSet<AssetReceiveAudit>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetReceiveAudit entity) {
		return true;
	}
}
