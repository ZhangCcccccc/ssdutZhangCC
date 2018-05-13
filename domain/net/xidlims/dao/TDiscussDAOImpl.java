package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TDiscuss;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TDiscuss entities.
 * 
 */
@Repository("TDiscussDAO")
@Transactional
public class TDiscussDAOImpl extends AbstractJpaDao<TDiscuss> implements
		TDiscussDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TDiscuss.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims2
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TDiscussDAOImpl
	 *
	 */
	public TDiscussDAOImpl() {
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
	 * JPQL Query - findTDiscussByDiscussTime
	 *
	 */
	@Transactional
	public Set<TDiscuss> findTDiscussByDiscussTime(java.util.Calendar discussTime) throws DataAccessException {

		return findTDiscussByDiscussTime(discussTime, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByDiscussTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findTDiscussByDiscussTime(java.util.Calendar discussTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTDiscussByDiscussTime", startResult, maxRows, discussTime);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTDiscusss
	 *
	 */
	@Transactional
	public Set<TDiscuss> findAllTDiscusss() throws DataAccessException {

		return findAllTDiscusss(-1, -1);
	}

	/**
	 * JPQL Query - findAllTDiscusss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findAllTDiscusss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTDiscusss", startResult, maxRows);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * JPQL Query - findTDiscussByContent
	 *
	 */
	@Transactional
	public Set<TDiscuss> findTDiscussByContent(String content) throws DataAccessException {

		return findTDiscussByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findTDiscussByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTDiscussByContent", startResult, maxRows, content);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * JPQL Query - findTDiscussById
	 *
	 */
	@Transactional
	public TDiscuss findTDiscussById(Integer id) throws DataAccessException {

		return findTDiscussById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussById
	 *
	 */

	@Transactional
	public TDiscuss findTDiscussById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTDiscussById", startResult, maxRows, id);
			return (net.xidlims.domain.TDiscuss) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTDiscussByIp
	 *
	 */
	@Transactional
	public Set<TDiscuss> findTDiscussByIp(String ip) throws DataAccessException {

		return findTDiscussByIp(ip, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByIp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findTDiscussByIp(String ip, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTDiscussByIp", startResult, maxRows, ip);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * JPQL Query - findTDiscussByPrimaryKey
	 *
	 */
	@Transactional
	public TDiscuss findTDiscussByPrimaryKey(Integer id) throws DataAccessException {

		return findTDiscussByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByPrimaryKey
	 *
	 */

	@Transactional
	public TDiscuss findTDiscussByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTDiscussByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TDiscuss) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTDiscussByContentContaining
	 *
	 */
	@Transactional
	public Set<TDiscuss> findTDiscussByContentContaining(String content) throws DataAccessException {

		return findTDiscussByContentContaining(content, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByContentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findTDiscussByContentContaining(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTDiscussByContentContaining", startResult, maxRows, content);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * JPQL Query - findTDiscussByIpContaining
	 *
	 */
	@Transactional
	public Set<TDiscuss> findTDiscussByIpContaining(String ip) throws DataAccessException {

		return findTDiscussByIpContaining(ip, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByIpContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findTDiscussByIpContaining(String ip, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTDiscussByIpContaining", startResult, maxRows, ip);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * JPQL Query - findTDiscussByType
	 *
	 */
	@Transactional
	public Set<TDiscuss> findTDiscussByType(Integer type) throws DataAccessException {

		return findTDiscussByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findTDiscussByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TDiscuss> findTDiscussByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTDiscussByType", startResult, maxRows, type);
		return new LinkedHashSet<TDiscuss>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TDiscuss entity) {
		return true;
	}
}
