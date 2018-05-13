package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TExerciseInfo;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TExerciseInfo entities.
 * 
 */
@Repository("TExerciseInfoDAO")
@Transactional
public class TExerciseInfoDAOImpl extends AbstractJpaDao<TExerciseInfo>
		implements TExerciseInfoDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExerciseInfo.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExerciseInfoDAOImpl
	 *
	 */
	public TExerciseInfoDAOImpl() {
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
	 * JPQL Query - findTExerciseInfoByStochasticString
	 *
	 */
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByStochasticString(String stochasticString) throws DataAccessException {

		return findTExerciseInfoByStochasticString(stochasticString, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseInfoByStochasticString
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByStochasticString(String stochasticString, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseInfoByStochasticString", startResult, maxRows, stochasticString);
		return new LinkedHashSet<TExerciseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseInfoByMistakeNumber
	 *
	 */
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByMistakeNumber(Integer mistakeNumber) throws DataAccessException {

		return findTExerciseInfoByMistakeNumber(mistakeNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseInfoByMistakeNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByMistakeNumber(Integer mistakeNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseInfoByMistakeNumber", startResult, maxRows, mistakeNumber);
		return new LinkedHashSet<TExerciseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseInfoByPrimaryKey
	 *
	 */
	@Transactional
	public TExerciseInfo findTExerciseInfoByPrimaryKey(Integer id) throws DataAccessException {

		return findTExerciseInfoByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseInfoByPrimaryKey
	 *
	 */

	@Transactional
	public TExerciseInfo findTExerciseInfoByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExerciseInfoByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExerciseInfo) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExerciseInfoById
	 *
	 */
	@Transactional
	public TExerciseInfo findTExerciseInfoById(Integer id) throws DataAccessException {

		return findTExerciseInfoById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseInfoById
	 *
	 */

	@Transactional
	public TExerciseInfo findTExerciseInfoById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExerciseInfoById", startResult, maxRows, id);
			return (net.xidlims.domain.TExerciseInfo) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExerciseInfoByStochasticNumber
	 *
	 */
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByStochasticNumber(Integer stochasticNumber) throws DataAccessException {

		return findTExerciseInfoByStochasticNumber(stochasticNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseInfoByStochasticNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByStochasticNumber(Integer stochasticNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseInfoByStochasticNumber", startResult, maxRows, stochasticNumber);
		return new LinkedHashSet<TExerciseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExerciseInfoByOrderNumber
	 *
	 */
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByOrderNumber(Integer orderNumber) throws DataAccessException {

		return findTExerciseInfoByOrderNumber(orderNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTExerciseInfoByOrderNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseInfo> findTExerciseInfoByOrderNumber(Integer orderNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExerciseInfoByOrderNumber", startResult, maxRows, orderNumber);
		return new LinkedHashSet<TExerciseInfo>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTExerciseInfos
	 *
	 */
	@Transactional
	public Set<TExerciseInfo> findAllTExerciseInfos() throws DataAccessException {

		return findAllTExerciseInfos(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExerciseInfos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExerciseInfo> findAllTExerciseInfos(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExerciseInfos", startResult, maxRows);
		return new LinkedHashSet<TExerciseInfo>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TExerciseInfo entity) {
		return true;
	}
}
