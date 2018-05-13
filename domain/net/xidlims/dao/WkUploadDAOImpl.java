package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.WkUpload;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage WkUpload entities.
 * 
 */
@Repository("WkUploadDAO")
@Transactional
public class WkUploadDAOImpl extends AbstractJpaDao<WkUpload> implements
		WkUploadDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { WkUpload.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new WkUploadDAOImpl
	 *
	 */
	public WkUploadDAOImpl() {
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
	 * JPQL Query - findAllWkUploads
	 *
	 */
	@Transactional
	public Set<WkUpload> findAllWkUploads() throws DataAccessException {

		return findAllWkUploads(-1, -1);
	}

	/**
	 * JPQL Query - findAllWkUploads
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findAllWkUploads(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWkUploads", startResult, maxRows);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByUrl
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByUrl(String url) throws DataAccessException {

		return findWkUploadByUrl(url, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByUrl(String url, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByUrl", startResult, maxRows, url);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByNameContaining
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByNameContaining(String name) throws DataAccessException {

		return findWkUploadByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByDescription
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByDescription(String description) throws DataAccessException {

		return findWkUploadByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByDescription", startResult, maxRows, description);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByUpTime
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByUpTime(java.util.Calendar upTime) throws DataAccessException {

		return findWkUploadByUpTime(upTime, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByUpTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByUpTime(java.util.Calendar upTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByUpTime", startResult, maxRows, upTime);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadById
	 *
	 */
	@Transactional
	public WkUpload findWkUploadById(Integer id) throws DataAccessException {

		return findWkUploadById(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadById
	 *
	 */

	@Transactional
	public WkUpload findWkUploadById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkUploadById", startResult, maxRows, id);
			return (net.xidlims.domain.WkUpload) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkUploadByType
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByType(Integer type) throws DataAccessException {

		return findWkUploadByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByType", startResult, maxRows, type);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByPrimaryKey
	 *
	 */
	@Transactional
	public WkUpload findWkUploadByPrimaryKey(Integer id) throws DataAccessException {

		return findWkUploadByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByPrimaryKey
	 *
	 */

	@Transactional
	public WkUpload findWkUploadByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkUploadByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.WkUpload) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkUploadByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByDescriptionContaining(String description) throws DataAccessException {

		return findWkUploadByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByName
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByName(String name) throws DataAccessException {

		return findWkUploadByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByName", startResult, maxRows, name);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkUploadByUrlContaining
	 *
	 */
	@Transactional
	public Set<WkUpload> findWkUploadByUrlContaining(String url) throws DataAccessException {

		return findWkUploadByUrlContaining(url, -1, -1);
	}

	/**
	 * JPQL Query - findWkUploadByUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkUpload> findWkUploadByUrlContaining(String url, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkUploadByUrlContaining", startResult, maxRows, url);
		return new LinkedHashSet<WkUpload>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(WkUpload entity) {
		return true;
	}
}
