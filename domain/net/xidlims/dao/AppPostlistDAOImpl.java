package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppPostlist;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppPostlist entities.
 * 
 */
@Repository("AppPostlistDAO")
@Transactional
public class AppPostlistDAOImpl extends AbstractJpaDao<AppPostlist> implements
		AppPostlistDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppPostlist.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppPostlistDAOImpl
	 *
	 */
	public AppPostlistDAOImpl() {
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
	 * JPQL Query - findAppPostlistByTitle
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByTitle(String title) throws DataAccessException {

		return findAppPostlistByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByTitle", startResult, maxRows, title);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByImagelist
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByImagelist(String imagelist) throws DataAccessException {

		return findAppPostlistByImagelist(imagelist, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByImagelist
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByImagelist(String imagelist, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByImagelist", startResult, maxRows, imagelist);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByImagelistContaining
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByImagelistContaining(String imagelist) throws DataAccessException {

		return findAppPostlistByImagelistContaining(imagelist, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByImagelistContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByImagelistContaining(String imagelist, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByImagelistContaining", startResult, maxRows, imagelist);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByTitleContaining
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByTitleContaining(String title) throws DataAccessException {

		return findAppPostlistByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByTime
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByTime(java.util.Calendar time) throws DataAccessException {

		return findAppPostlistByTime(time, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByTime(java.util.Calendar time, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByTime", startResult, maxRows, time);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByType
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByType(Integer type) throws DataAccessException {

		return findAppPostlistByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByType", startResult, maxRows, type);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByContentContaining
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByContentContaining(String content) throws DataAccessException {

		return findAppPostlistByContentContaining(content, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByContentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByContentContaining(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByContentContaining", startResult, maxRows, content);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByContent
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByContent(String content) throws DataAccessException {

		return findAppPostlistByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByContent", startResult, maxRows, content);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByIsstick
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByIsstick(Integer isstick) throws DataAccessException {

		return findAppPostlistByIsstick(isstick, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByIsstick
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByIsstick(Integer isstick, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByIsstick", startResult, maxRows, isstick);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistById
	 *
	 */
	@Transactional
	public AppPostlist findAppPostlistById(Integer id) throws DataAccessException {

		return findAppPostlistById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistById
	 *
	 */

	@Transactional
	public AppPostlist findAppPostlistById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostlistById", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostlist) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppPostlistByState
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAppPostlistByState(Integer state) throws DataAccessException {

		return findAppPostlistByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAppPostlistByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostlistByState", startResult, maxRows, state);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostlistByPrimaryKey
	 *
	 */
	@Transactional
	public AppPostlist findAppPostlistByPrimaryKey(Integer id) throws DataAccessException {

		return findAppPostlistByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostlistByPrimaryKey
	 *
	 */

	@Transactional
	public AppPostlist findAppPostlistByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostlistByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostlist) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAppPostlists
	 *
	 */
	@Transactional
	public Set<AppPostlist> findAllAppPostlists() throws DataAccessException {

		return findAllAppPostlists(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppPostlists
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostlist> findAllAppPostlists(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppPostlists", startResult, maxRows);
		return new LinkedHashSet<AppPostlist>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppPostlist entity) {
		return true;
	}
}
