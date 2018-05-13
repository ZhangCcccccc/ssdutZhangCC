package net.xidlims.dao;

import net.xidlims.domain.CmsResource;

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
 * DAO to manage CmsResource entities.
 * 
 */
@Repository("CmsResourceDAO")
@Transactional
public class CmsResourceDAOImpl extends AbstractJpaDao<CmsResource> implements
		CmsResourceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsResource.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsResourceDAOImpl
	 *
	 */
	public CmsResourceDAOImpl() {
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
	 * JPQL Query - findCmsResourceById
	 *
	 */
	@Transactional
	public CmsResource findCmsResourceById(Integer id) throws DataAccessException {

		return findCmsResourceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceById
	 *
	 */

	@Transactional
	public CmsResource findCmsResourceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsResourceById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsResource) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsResourceByUrl
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByUrl(String url) throws DataAccessException {

		return findCmsResourceByUrl(url, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByUrl(String url, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByUrl", startResult, maxRows, url);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByProfileContaining(String profile) throws DataAccessException {

		return findCmsResourceByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByProfile
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByProfile(String profile) throws DataAccessException {

		return findCmsResourceByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByCreateTime
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findCmsResourceByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByName
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByName(String name) throws DataAccessException {

		return findCmsResourceByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByName", startResult, maxRows, name);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByNameContaining
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByNameContaining(String name) throws DataAccessException {

		return findCmsResourceByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCmsResources
	 *
	 */
	@Transactional
	public Set<CmsResource> findAllCmsResources() throws DataAccessException {

		return findAllCmsResources(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsResources
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findAllCmsResources(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsResources", startResult, maxRows);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByUrlContaining
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByUrlContaining(String url) throws DataAccessException {

		return findCmsResourceByUrlContaining(url, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByUrlContaining(String url, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByUrlContaining", startResult, maxRows, url);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsResourceByPrimaryKey
	 *
	 */
	@Transactional
	public CmsResource findCmsResourceByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsResourceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByPrimaryKey
	 *
	 */

	@Transactional
	public CmsResource findCmsResourceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsResourceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsResource) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsResourceByImageVideo
	 *
	 */
	@Transactional
	public Set<CmsResource> findCmsResourceByImageVideo(Integer imageVideo) throws DataAccessException {

		return findCmsResourceByImageVideo(imageVideo, -1, -1);
	}

	/**
	 * JPQL Query - findCmsResourceByImageVideo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsResource> findCmsResourceByImageVideo(Integer imageVideo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsResourceByImageVideo", startResult, maxRows, imageVideo);
		return new LinkedHashSet<CmsResource>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsResource entity) {
		return true;
	}
}
