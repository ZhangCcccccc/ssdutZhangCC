package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.Realm;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Realm entities.
 * 
 */
@Repository("RealmDAO")
@Transactional
public class RealmDAOImpl extends AbstractJpaDao<Realm> implements RealmDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Realm.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new RealmDAOImpl
	 *
	 */
	public RealmDAOImpl() {
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
	 * JPQL Query - findRealmByRealmTypeContaining
	 *
	 */
	@Transactional
	public Set<Realm> findRealmByRealmTypeContaining(String realmType) throws DataAccessException {

		return findRealmByRealmTypeContaining(realmType, -1, -1);
	}

	/**
	 * JPQL Query - findRealmByRealmTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Realm> findRealmByRealmTypeContaining(String realmType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRealmByRealmTypeContaining", startResult, maxRows, realmType);
		return new LinkedHashSet<Realm>(query.getResultList());
	}

	/**
	 * JPQL Query - findRealmByRealmType
	 *
	 */
	@Transactional
	public Set<Realm> findRealmByRealmType(String realmType) throws DataAccessException {

		return findRealmByRealmType(realmType, -1, -1);
	}

	/**
	 * JPQL Query - findRealmByRealmType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Realm> findRealmByRealmType(String realmType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRealmByRealmType", startResult, maxRows, realmType);
		return new LinkedHashSet<Realm>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllRealms
	 *
	 */
	@Transactional
	public Set<Realm> findAllRealms() throws DataAccessException {

		return findAllRealms(-1, -1);
	}

	/**
	 * JPQL Query - findAllRealms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Realm> findAllRealms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllRealms", startResult, maxRows);
		return new LinkedHashSet<Realm>(query.getResultList());
	}

	/**
	 * JPQL Query - findRealmById
	 *
	 */
	@Transactional
	public Realm findRealmById(Integer id) throws DataAccessException {

		return findRealmById(id, -1, -1);
	}

	/**
	 * JPQL Query - findRealmById
	 *
	 */

	@Transactional
	public Realm findRealmById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findRealmById", startResult, maxRows, id);
			return (net.xidlims.domain.Realm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findRealmByPrimaryKey
	 *
	 */
	@Transactional
	public Realm findRealmByPrimaryKey(Integer id) throws DataAccessException {

		return findRealmByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findRealmByPrimaryKey
	 *
	 */

	@Transactional
	public Realm findRealmByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findRealmByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.Realm) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findRealmByNameContaining
	 *
	 */
	@Transactional
	public Set<Realm> findRealmByNameContaining(String name) throws DataAccessException {

		return findRealmByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findRealmByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Realm> findRealmByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRealmByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Realm>(query.getResultList());
	}

	/**
	 * JPQL Query - findRealmByName
	 *
	 */
	@Transactional
	public Set<Realm> findRealmByName(String name) throws DataAccessException {

		return findRealmByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findRealmByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Realm> findRealmByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findRealmByName", startResult, maxRows, name);
		return new LinkedHashSet<Realm>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Realm entity) {
		return true;
	}
}
