package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.UserCard;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage UserCard entities.
 * 
 */
@Repository("UserCardDAO")
@Transactional
public class UserCardDAOImpl extends AbstractJpaDao<UserCard> implements
		UserCardDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { UserCard.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new UserCardDAOImpl
	 *
	 */
	public UserCardDAOImpl() {
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
	 * JPQL Query - findUserCardByEnabledContaining
	 *
	 */
	@Transactional
	public Set<UserCard> findUserCardByEnabledContaining(String enabled) throws DataAccessException {

		return findUserCardByEnabledContaining(enabled, -1, -1);
	}

	/**
	 * JPQL Query - findUserCardByEnabledContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<UserCard> findUserCardByEnabledContaining(String enabled, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserCardByEnabledContaining", startResult, maxRows, enabled);
		return new LinkedHashSet<UserCard>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserCardByCardNo
	 *
	 */
	@Transactional
	public Set<UserCard> findUserCardByCardNo(String cardNo) throws DataAccessException {

		return findUserCardByCardNo(cardNo, -1, -1);
	}

	/**
	 * JPQL Query - findUserCardByCardNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<UserCard> findUserCardByCardNo(String cardNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserCardByCardNo", startResult, maxRows, cardNo);
		return new LinkedHashSet<UserCard>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllUserCards
	 *
	 */
	@Transactional
	public Set<UserCard> findAllUserCards() throws DataAccessException {

		return findAllUserCards(-1, -1);
	}

	/**
	 * JPQL Query - findAllUserCards
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<UserCard> findAllUserCards(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllUserCards", startResult, maxRows);
		return new LinkedHashSet<UserCard>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserCardByCardNoContaining
	 *
	 */
	@Transactional
	public Set<UserCard> findUserCardByCardNoContaining(String cardNo) throws DataAccessException {

		return findUserCardByCardNoContaining(cardNo, -1, -1);
	}

	/**
	 * JPQL Query - findUserCardByCardNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<UserCard> findUserCardByCardNoContaining(String cardNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserCardByCardNoContaining", startResult, maxRows, cardNo);
		return new LinkedHashSet<UserCard>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserCardById
	 *
	 */
	@Transactional
	public UserCard findUserCardById(Integer id) throws DataAccessException {

		return findUserCardById(id, -1, -1);
	}

	/**
	 * JPQL Query - findUserCardById
	 *
	 */

	@Transactional
	public UserCard findUserCardById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findUserCardById", startResult, maxRows, id);
			return (net.xidlims.domain.UserCard) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findUserCardByPrimaryKey
	 *
	 */
	@Transactional
	public UserCard findUserCardByPrimaryKey(Integer id) throws DataAccessException {

		return findUserCardByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findUserCardByPrimaryKey
	 *
	 */

	@Transactional
	public UserCard findUserCardByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findUserCardByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.UserCard) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findUserCardByEnabled
	 *
	 */
	@Transactional
	public Set<UserCard> findUserCardByEnabled(String enabled) throws DataAccessException {

		return findUserCardByEnabled(enabled, -1, -1);
	}

	/**
	 * JPQL Query - findUserCardByEnabled
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<UserCard> findUserCardByEnabled(String enabled, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserCardByEnabled", startResult, maxRows, enabled);
		return new LinkedHashSet<UserCard>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(UserCard entity) {
		return true;
	}
}
