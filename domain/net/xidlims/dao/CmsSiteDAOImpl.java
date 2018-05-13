package net.xidlims.dao;

import net.xidlims.domain.CmsSite;

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
 * DAO to manage CmsSite entities.
 * 
 */
@Repository("CmsSiteDAO")
@Transactional
public class CmsSiteDAOImpl extends AbstractJpaDao<CmsSite> implements
		CmsSiteDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsSite.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsSiteDAOImpl
	 *
	 */
	public CmsSiteDAOImpl() {
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
	 * JPQL Query - findCmsSiteByState
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByState(Integer state) throws DataAccessException {

		return findCmsSiteByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByState", startResult, maxRows, state);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByCreateTime
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findCmsSiteByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByBottomContent
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByBottomContent(String bottomContent) throws DataAccessException {

		return findCmsSiteByBottomContent(bottomContent, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByBottomContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByBottomContent(String bottomContent, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByBottomContent", startResult, maxRows, bottomContent);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCmsSites
	 *
	 */
	@Transactional
	public Set<CmsSite> findAllCmsSites() throws DataAccessException {

		return findAllCmsSites(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsSites
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findAllCmsSites(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsSites", startResult, maxRows);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByProfileContaining(String profile) throws DataAccessException {

		return findCmsSiteByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByImageResource
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByImageResource(Integer imageResource) throws DataAccessException {

		return findCmsSiteByImageResource(imageResource, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByImageResource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByImageResource(Integer imageResource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByImageResource", startResult, maxRows, imageResource);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByPrimaryKey
	 *
	 */
	@Transactional
	public CmsSite findCmsSiteByPrimaryKey(String siteurl) throws DataAccessException {

		return findCmsSiteByPrimaryKey(siteurl, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByPrimaryKey
	 *
	 */

	@Transactional
	public CmsSite findCmsSiteByPrimaryKey(String siteurl, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsSiteByPrimaryKey", startResult, maxRows, siteurl);
			return (net.xidlims.domain.CmsSite) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsSiteByProfile
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByProfile(String profile) throws DataAccessException {

		return findCmsSiteByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByVideoResource
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByVideoResource(Integer videoResource) throws DataAccessException {

		return findCmsSiteByVideoResource(videoResource, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByVideoResource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByVideoResource(Integer videoResource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByVideoResource", startResult, maxRows, videoResource);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByNameContaining
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByNameContaining(String name) throws DataAccessException {

		return findCmsSiteByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByCurrent
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByCurrent(Integer current) throws DataAccessException {

		return findCmsSiteByCurrent(current, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByCurrent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByCurrent(Integer current, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByCurrent", startResult, maxRows, current);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteByName
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteByName(String name) throws DataAccessException {

		return findCmsSiteByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteByName", startResult, maxRows, name);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsSiteBySiteurl
	 *
	 */
	@Transactional
	public CmsSite findCmsSiteBySiteurl(String siteurl) throws DataAccessException {

		return findCmsSiteBySiteurl(siteurl, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteBySiteurl
	 *
	 */

	@Transactional
	public CmsSite findCmsSiteBySiteurl(String siteurl, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsSiteBySiteurl", startResult, maxRows, siteurl);
			return (net.xidlims.domain.CmsSite) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsSiteBySiteurlContaining
	 *
	 */
	@Transactional
	public Set<CmsSite> findCmsSiteBySiteurlContaining(String siteurl) throws DataAccessException {

		return findCmsSiteBySiteurlContaining(siteurl, -1, -1);
	}

	/**
	 * JPQL Query - findCmsSiteBySiteurlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsSite> findCmsSiteBySiteurlContaining(String siteurl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsSiteBySiteurlContaining", startResult, maxRows, siteurl);
		return new LinkedHashSet<CmsSite>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsSite entity) {
		return true;
	}
}
