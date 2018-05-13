package net.xidlims.dao;

import net.xidlims.domain.CmsLink;

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
 * DAO to manage CmsLink entities.
 * 
 */
@Repository("CmsLinkDAO")
@Transactional
public class CmsLinkDAOImpl extends AbstractJpaDao<CmsLink> implements
		CmsLinkDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsLink.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsLinkDAOImpl
	 *
	 */
	public CmsLinkDAOImpl() {
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
	 * JPQL Query - findCmsLinkByLinkUrl
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkUrl(String linkUrl) throws DataAccessException {

		return findCmsLinkByLinkUrl(linkUrl, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByLinkUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkUrl(String linkUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByLinkUrl", startResult, maxRows, linkUrl);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkByCreateTime
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findCmsLinkByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkBySort
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkBySort(Integer sort) throws DataAccessException {

		return findCmsLinkBySort(sort, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkBySort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkBySort(Integer sort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkBySort", startResult, maxRows, sort);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkByPrimaryKey
	 *
	 */
	@Transactional
	public CmsLink findCmsLinkByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsLinkByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByPrimaryKey
	 *
	 */

	@Transactional
	public CmsLink findCmsLinkByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsLinkByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsLink) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsLinkByLinkNameContaining
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkNameContaining(String linkName) throws DataAccessException {

		return findCmsLinkByLinkNameContaining(linkName, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByLinkNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkNameContaining(String linkName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByLinkNameContaining", startResult, maxRows, linkName);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkByLinkName
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkName(String linkName) throws DataAccessException {

		return findCmsLinkByLinkName(linkName, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByLinkName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkName(String linkName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByLinkName", startResult, maxRows, linkName);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkByState
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByState(Integer state) throws DataAccessException {

		return findCmsLinkByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByState", startResult, maxRows, state);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkByLinkUrlContaining
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkUrlContaining(String linkUrl) throws DataAccessException {

		return findCmsLinkByLinkUrlContaining(linkUrl, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByLinkUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByLinkUrlContaining(String linkUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByLinkUrlContaining", startResult, maxRows, linkUrl);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkById
	 *
	 */
	@Transactional
	public CmsLink findCmsLinkById(Integer id) throws DataAccessException {

		return findCmsLinkById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkById
	 *
	 */

	@Transactional
	public CmsLink findCmsLinkById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsLinkById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsLink) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsLinkByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByProfileContaining(String profile) throws DataAccessException {

		return findCmsLinkByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCmsLinks
	 *
	 */
	@Transactional
	public Set<CmsLink> findAllCmsLinks() throws DataAccessException {

		return findAllCmsLinks(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsLinks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findAllCmsLinks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsLinks", startResult, maxRows);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsLinkByProfile
	 *
	 */
	@Transactional
	public Set<CmsLink> findCmsLinkByProfile(String profile) throws DataAccessException {

		return findCmsLinkByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsLinkByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsLink> findCmsLinkByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsLinkByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsLink>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsLink entity) {
		return true;
	}
}
