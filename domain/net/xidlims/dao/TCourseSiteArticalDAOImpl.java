package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSiteArtical;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSiteArtical entities.
 * 
 */
@Repository("TCourseSiteArticalDAO")
@Transactional
public class TCourseSiteArticalDAOImpl extends AbstractJpaDao<TCourseSiteArtical>
		implements TCourseSiteArticalDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSiteArtical.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteArticalDAOImpl
	 *
	 */
	public TCourseSiteArticalDAOImpl() {
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
	 * JPQL Query - findAllTCourseSiteArticals
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findAllTCourseSiteArticals() throws DataAccessException {

		return findAllTCourseSiteArticals(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSiteArticals
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findAllTCourseSiteArticals(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSiteArticals", startResult, maxRows);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSiteArtical findTCourseSiteArticalByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteArticalByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSiteArtical findTCourseSiteArticalByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteArticalByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteArtical) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByName
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByName(String name) throws DataAccessException {

		return findTCourseSiteArticalByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByName", startResult, maxRows, name);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrl
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrl(String imageUrl) throws DataAccessException {

		return findTCourseSiteArticalByImageUrl(imageUrl, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrl(String imageUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByImageUrl", startResult, maxRows, imageUrl);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrlContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrlContaining(String imageUrl) throws DataAccessException {

		return findTCourseSiteArticalByImageUrlContaining(imageUrl, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrlContaining(String imageUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByImageUrlContaining", startResult, maxRows, imageUrl);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByNameContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByNameContaining(String name) throws DataAccessException {

		return findTCourseSiteArticalByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByTextContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByTextContaining(String text) throws DataAccessException {

		return findTCourseSiteArticalByTextContaining(text, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByTextContaining(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByTextContaining", startResult, maxRows, text);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalBySort
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalBySort(Integer sort) throws DataAccessException {

		return findTCourseSiteArticalBySort(sort, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalBySort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalBySort(Integer sort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalBySort", startResult, maxRows, sort);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByText
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByText(String text) throws DataAccessException {

		return findTCourseSiteArticalByText(text, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByText(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByText", startResult, maxRows, text);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByCreateDate
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findTCourseSiteArticalByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteArticalById
	 *
	 */
	@Transactional
	public TCourseSiteArtical findTCourseSiteArticalById(Integer id) throws DataAccessException {

		return findTCourseSiteArticalById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalById
	 *
	 */

	@Transactional
	public TCourseSiteArtical findTCourseSiteArticalById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteArticalById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteArtical) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByContent
	 *
	 */
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByContent(String content) throws DataAccessException {

		return findTCourseSiteArticalByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteArticalByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteArtical> findTCourseSiteArticalByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteArticalByContent", startResult, maxRows, content);
		return new LinkedHashSet<TCourseSiteArtical>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSiteArtical entity) {
		return true;
	}
}
