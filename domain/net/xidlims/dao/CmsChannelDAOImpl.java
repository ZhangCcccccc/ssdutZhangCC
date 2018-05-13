package net.xidlims.dao;

import net.xidlims.domain.CmsChannel;

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
 * DAO to manage CmsChannel entities.
 * 
 */
@Repository("CmsChannelDAO")
@Transactional
public class CmsChannelDAOImpl extends AbstractJpaDao<CmsChannel> implements
		CmsChannelDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsChannel.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsChannelDAOImpl
	 *
	 */
	public CmsChannelDAOImpl() {
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
	 * JPQL Query - findCmsChannelByHyperlink
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByHyperlink(String hyperlink) throws DataAccessException {

		return findCmsChannelByHyperlink(hyperlink, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByHyperlink
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByHyperlink(String hyperlink, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByHyperlink", startResult, maxRows, hyperlink);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByHyperlinkContaining
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByHyperlinkContaining(String hyperlink) throws DataAccessException {

		return findCmsChannelByHyperlinkContaining(hyperlink, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByHyperlinkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByHyperlinkContaining(String hyperlink, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByHyperlinkContaining", startResult, maxRows, hyperlink);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByState
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByState(Integer state) throws DataAccessException {

		return findCmsChannelByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByState", startResult, maxRows, state);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByProfile
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByProfile(String profile) throws DataAccessException {

		return findCmsChannelByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByReadNum
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByReadNum(Integer readNum) throws DataAccessException {

		return findCmsChannelByReadNum(readNum, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByReadNum
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByReadNum(Integer readNum, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByReadNum", startResult, maxRows, readNum);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByPrimaryKey
	 *
	 */
	@Transactional
	public CmsChannel findCmsChannelByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsChannelByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByPrimaryKey
	 *
	 */

	@Transactional
	public CmsChannel findCmsChannelByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsChannelByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsChannel) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsChannelByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByProfileContaining(String profile) throws DataAccessException {

		return findCmsChannelByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelBySort
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelBySort(Integer sort) throws DataAccessException {

		return findCmsChannelBySort(sort, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelBySort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelBySort(Integer sort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelBySort", startResult, maxRows, sort);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByCreateTime
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findCmsChannelByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCmsChannels
	 *
	 */
	@Transactional
	public Set<CmsChannel> findAllCmsChannels() throws DataAccessException {

		return findAllCmsChannels(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsChannels
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findAllCmsChannels(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsChannels", startResult, maxRows);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelById
	 *
	 */
	@Transactional
	public CmsChannel findCmsChannelById(Integer id) throws DataAccessException {

		return findCmsChannelById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelById
	 *
	 */

	@Transactional
	public CmsChannel findCmsChannelById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsChannelById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsChannel) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsChannelByTitleContaining
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByTitleContaining(String title) throws DataAccessException {

		return findCmsChannelByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsChannelByTitle
	 *
	 */
	@Transactional
	public Set<CmsChannel> findCmsChannelByTitle(String title) throws DataAccessException {

		return findCmsChannelByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findCmsChannelByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsChannel> findCmsChannelByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsChannelByTitle", startResult, maxRows, title);
		return new LinkedHashSet<CmsChannel>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsChannel entity) {
		return true;
	}
}
