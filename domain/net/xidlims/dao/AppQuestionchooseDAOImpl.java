package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppQuestionchoose;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppQuestionchoose entities.
 * 
 */
@Repository("AppQuestionchooseDAO")
@Transactional
public class AppQuestionchooseDAOImpl extends AbstractJpaDao<AppQuestionchoose>
		implements AppQuestionchooseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppQuestionchoose.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppQuestionchooseDAOImpl
	 *
	 */
	public AppQuestionchooseDAOImpl() {
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
	 * JPQL Query - findAppQuestionchooseByChoose
	 *
	 */
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByChoose(String choose) throws DataAccessException {

		return findAppQuestionchooseByChoose(choose, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseByChoose
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByChoose(String choose, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionchooseByChoose", startResult, maxRows, choose);
		return new LinkedHashSet<AppQuestionchoose>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionchooseByText
	 *
	 */
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByText(String text) throws DataAccessException {

		return findAppQuestionchooseByText(text, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseByText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByText(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionchooseByText", startResult, maxRows, text);
		return new LinkedHashSet<AppQuestionchoose>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppQuestionchooses
	 *
	 */
	@Transactional
	public Set<AppQuestionchoose> findAllAppQuestionchooses() throws DataAccessException {

		return findAllAppQuestionchooses(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppQuestionchooses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionchoose> findAllAppQuestionchooses(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppQuestionchooses", startResult, maxRows);
		return new LinkedHashSet<AppQuestionchoose>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionchooseByNum
	 *
	 */
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByNum(Integer num) throws DataAccessException {

		return findAppQuestionchooseByNum(num, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseByNum
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByNum(Integer num, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionchooseByNum", startResult, maxRows, num);
		return new LinkedHashSet<AppQuestionchoose>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionchooseByPrimaryKey
	 *
	 */
	@Transactional
	public AppQuestionchoose findAppQuestionchooseByPrimaryKey(Integer id) throws DataAccessException {

		return findAppQuestionchooseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseByPrimaryKey
	 *
	 */

	@Transactional
	public AppQuestionchoose findAppQuestionchooseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppQuestionchooseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppQuestionchoose) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppQuestionchooseByChooseContaining
	 *
	 */
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByChooseContaining(String choose) throws DataAccessException {

		return findAppQuestionchooseByChooseContaining(choose, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseByChooseContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByChooseContaining(String choose, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionchooseByChooseContaining", startResult, maxRows, choose);
		return new LinkedHashSet<AppQuestionchoose>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionchooseById
	 *
	 */
	@Transactional
	public AppQuestionchoose findAppQuestionchooseById(Integer id) throws DataAccessException {

		return findAppQuestionchooseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseById
	 *
	 */

	@Transactional
	public AppQuestionchoose findAppQuestionchooseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppQuestionchooseById", startResult, maxRows, id);
			return (net.xidlims.domain.AppQuestionchoose) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppQuestionchooseByTextContaining
	 *
	 */
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByTextContaining(String text) throws DataAccessException {

		return findAppQuestionchooseByTextContaining(text, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionchooseByTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionchoose> findAppQuestionchooseByTextContaining(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionchooseByTextContaining", startResult, maxRows, text);
		return new LinkedHashSet<AppQuestionchoose>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppQuestionchoose entity) {
		return true;
	}
}
