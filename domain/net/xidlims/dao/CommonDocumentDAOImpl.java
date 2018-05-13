package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CommonDocument;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CommonDocument entities.
 * 
 */
@Repository("CommonDocumentDAO")
@Transactional
public class CommonDocumentDAOImpl extends AbstractJpaDao<CommonDocument>
		implements CommonDocumentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CommonDocument.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CommonDocumentDAOImpl
	 *
	 */
	public CommonDocumentDAOImpl() {
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
	 * JPQL Query - findCommonDocumentByDocumentUrl
	 *
	 */
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentUrl(String documentUrl) throws DataAccessException {

		return findCommonDocumentByDocumentUrl(documentUrl, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentUrl(String documentUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonDocumentByDocumentUrl", startResult, maxRows, documentUrl);
		return new LinkedHashSet<CommonDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonDocumentByType
	 *
	 */
	@Transactional
	public Set<CommonDocument> findCommonDocumentByType(Integer type) throws DataAccessException {

		return findCommonDocumentByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonDocument> findCommonDocumentByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonDocumentByType", startResult, maxRows, type);
		return new LinkedHashSet<CommonDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrlContaining
	 *
	 */
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentUrlContaining(String documentUrl) throws DataAccessException {

		return findCommonDocumentByDocumentUrlContaining(documentUrl, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentUrlContaining(String documentUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonDocumentByDocumentUrlContaining", startResult, maxRows, documentUrl);
		return new LinkedHashSet<CommonDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonDocumentById
	 *
	 */
	@Transactional
	public CommonDocument findCommonDocumentById(Integer id) throws DataAccessException {

		return findCommonDocumentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentById
	 *
	 */

	@Transactional
	public CommonDocument findCommonDocumentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonDocumentById", startResult, maxRows, id);
			return (net.xidlims.domain.CommonDocument) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentNameContaining
	 *
	 */
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentNameContaining(String documentName) throws DataAccessException {

		return findCommonDocumentByDocumentNameContaining(documentName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentNameContaining(String documentName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonDocumentByDocumentNameContaining", startResult, maxRows, documentName);
		return new LinkedHashSet<CommonDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonDocumentByPrimaryKey
	 *
	 */
	@Transactional
	public CommonDocument findCommonDocumentByPrimaryKey(Integer id) throws DataAccessException {

		return findCommonDocumentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentByPrimaryKey
	 *
	 */

	@Transactional
	public CommonDocument findCommonDocumentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonDocumentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CommonDocument) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllCommonDocuments
	 *
	 */
	@Transactional
	public Set<CommonDocument> findAllCommonDocuments() throws DataAccessException {

		return findAllCommonDocuments(-1, -1);
	}

	/**
	 * JPQL Query - findAllCommonDocuments
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonDocument> findAllCommonDocuments(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCommonDocuments", startResult, maxRows);
		return new LinkedHashSet<CommonDocument>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentName
	 *
	 */
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentName(String documentName) throws DataAccessException {

		return findCommonDocumentByDocumentName(documentName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonDocumentByDocumentName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonDocument> findCommonDocumentByDocumentName(String documentName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonDocumentByDocumentName", startResult, maxRows, documentName);
		return new LinkedHashSet<CommonDocument>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CommonDocument entity) {
		return true;
	}
}
