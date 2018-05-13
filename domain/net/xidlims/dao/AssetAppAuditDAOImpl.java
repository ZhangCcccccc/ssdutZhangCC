package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetAppAudit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetAppAudit entities.
 * 
 */
@Repository("AssetAppAuditDAO")
@Transactional
public class AssetAppAuditDAOImpl extends AbstractJpaDao<AssetAppAudit>
		implements AssetAppAuditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetAppAudit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetAppAuditDAOImpl
	 *
	 */
	public AssetAppAuditDAOImpl() {
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
	 * JPQL Query - findAllAssetAppAudits
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAllAssetAppAudits() throws DataAccessException {

		return findAllAssetAppAudits(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetAppAudits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAllAssetAppAudits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetAppAudits", startResult, maxRows);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditByMem
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByMem(String mem) throws DataAccessException {

		return findAssetAppAuditByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppAuditByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditByStatus
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByStatus(Integer status) throws DataAccessException {

		return findAssetAppAuditByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppAuditByStatus", startResult, maxRows, status);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditByPrimaryKey
	 *
	 */
	@Transactional
	public AssetAppAudit findAssetAppAuditByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetAppAuditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByPrimaryKey
	 *
	 */

	@Transactional
	public AssetAppAudit findAssetAppAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppAuditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAppAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAppAuditByResult
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByResult(String result) throws DataAccessException {

		return findAssetAppAuditByResult(result, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByResult
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByResult(String result, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppAuditByResult", startResult, maxRows, result);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditByCreateDate
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findAssetAppAuditByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppAuditByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditByResultContaining
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByResultContaining(String result) throws DataAccessException {

		return findAssetAppAuditByResultContaining(result, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByResultContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByResultContaining(String result, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppAuditByResultContaining", startResult, maxRows, result);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditByAuditRoles
	 *
	 */
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByAuditRoles(Integer auditRoles) throws DataAccessException {

		return findAssetAppAuditByAuditRoles(auditRoles, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditByAuditRoles
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppAudit> findAssetAppAuditByAuditRoles(Integer auditRoles, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppAuditByAuditRoles", startResult, maxRows, auditRoles);
		return new LinkedHashSet<AssetAppAudit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppAuditById
	 *
	 */
	@Transactional
	public AssetAppAudit findAssetAppAuditById(Integer id) throws DataAccessException {

		return findAssetAppAuditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppAuditById
	 *
	 */

	@Transactional
	public AssetAppAudit findAssetAppAuditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppAuditById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAppAudit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetAppAudit entity) {
		return true;
	}
}
