package net.xidlims.dao;

import net.xidlims.domain.CmsDocument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CmsDocument entities.
 * 
 */
@Repository("CmsDocumentDAO")
@Transactional
public class CmsDocumentDAOImpl extends AbstractJpaDao<CmsDocument> implements
		CmsDocumentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsDocument.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsDocumentDAOImpl
	 *
	 */
	public CmsDocumentDAOImpl() {
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
	 * JPQL Query - findCmsDocumentByName
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByName(String name) throws DataAccessException {

		return findCmsDocumentByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByName", startResult, maxRows, name);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByUrlContaining
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByUrlContaining(String url) throws DataAccessException {

		return findCmsDocumentByUrlContaining(url, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByUrlContaining(String url, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByUrlContaining", startResult, maxRows, url);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByUrl
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByUrl(String url) throws DataAccessException {

		return findCmsDocumentByUrl(url, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByUrl(String url, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByUrl", startResult, maxRows, url);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByProfileContaining(String profile) throws DataAccessException {

		return findCmsDocumentByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCmsDocuments
	 *
	 */
	@Transactional
	public Set<CmsDocument> findAllCmsDocuments() throws DataAccessException {

		return findAllCmsDocuments(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsDocuments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findAllCmsDocuments(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsDocuments", startResult, maxRows);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByPrimaryKey
	 *
	 */
	@Transactional
	public CmsDocument findCmsDocumentByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsDocumentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByPrimaryKey
	 *
	 */

	@Transactional
	public CmsDocument findCmsDocumentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsDocumentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsDocument) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsDocumentById
	 *
	 */
	@Transactional
	public CmsDocument findCmsDocumentById(Integer id) throws DataAccessException {

		return findCmsDocumentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentById
	 *
	 */

	@Transactional
	public CmsDocument findCmsDocumentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsDocumentById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsDocument) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsDocumentByTag
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByTag(Integer tag) throws DataAccessException {

		return findCmsDocumentByTag(tag, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByTag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByTag(Integer tag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByTag", startResult, maxRows, tag);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByCreateTime
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findCmsDocumentByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByProfile
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByProfile(String profile) throws DataAccessException {

		return findCmsDocumentByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsDocumentByNameContaining
	 *
	 */
	@Transactional
	public Set<CmsDocument> findCmsDocumentByNameContaining(String name) throws DataAccessException {

		return findCmsDocumentByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsDocumentByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsDocument> findCmsDocumentByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsDocumentByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<CmsDocument>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsDocument entity) {
		return true;
	}
}
