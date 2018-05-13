package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CDictionary;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CDictionary entities.
 * 
 */
@Repository("CDictionaryDAO")
@Transactional
public class CDictionaryDAOImpl extends AbstractJpaDao<CDictionary> implements
		CDictionaryDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CDictionary.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CDictionaryDAOImpl
	 *
	 */
	public CDictionaryDAOImpl() {
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
	 * JPQL Query - findCDictionaryByCNameContaining
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByCNameContaining(String CName) throws DataAccessException {

		return findCDictionaryByCNameContaining(CName, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByCNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByCNameContaining(String CName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByCNameContaining", startResult, maxRows, CName);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDictionaryByCNumberContaining
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByCNumberContaining(String CNumber) throws DataAccessException {

		return findCDictionaryByCNumberContaining(CNumber, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByCNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByCNumberContaining(String CNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByCNumberContaining", startResult, maxRows, CNumber);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDictionaryByCCategory
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByCCategory(String CCategory) throws DataAccessException {

		return findCDictionaryByCCategory(CCategory, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByCCategory
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByCCategory(String CCategory, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByCCategory", startResult, maxRows, CCategory);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDictionaryByEnabled
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByEnabled(Boolean enabled) throws DataAccessException {

		return findCDictionaryByEnabled(enabled, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByEnabled
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByEnabled(Boolean enabled, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByEnabled", startResult, maxRows, enabled);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDictionaryByPrimaryKey
	 *
	 */
	@Transactional
	public CDictionary findCDictionaryByPrimaryKey(Integer id) throws DataAccessException {

		return findCDictionaryByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByPrimaryKey
	 *
	 */

	@Transactional
	public CDictionary findCDictionaryByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCDictionaryByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CDictionary) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCDictionaryById
	 *
	 */
	@Transactional
	public CDictionary findCDictionaryById(Integer id) throws DataAccessException {

		return findCDictionaryById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryById
	 *
	 */

	@Transactional
	public CDictionary findCDictionaryById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCDictionaryById", startResult, maxRows, id);
			return (net.xidlims.domain.CDictionary) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCDictionaryByCNumber
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByCNumber(String CNumber) throws DataAccessException {

		return findCDictionaryByCNumber(CNumber, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByCNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByCNumber(String CNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByCNumber", startResult, maxRows, CNumber);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDictionaryByCName
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByCName(String CName) throws DataAccessException {

		return findCDictionaryByCName(CName, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByCName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByCName(String CName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByCName", startResult, maxRows, CName);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findCDictionaryByCCategoryContaining
	 *
	 */
	@Transactional
	public Set<CDictionary> findCDictionaryByCCategoryContaining(String CCategory) throws DataAccessException {

		return findCDictionaryByCCategoryContaining(CCategory, -1, -1);
	}

	/**
	 * JPQL Query - findCDictionaryByCCategoryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findCDictionaryByCCategoryContaining(String CCategory, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCDictionaryByCCategoryContaining", startResult, maxRows, CCategory);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCDictionarys
	 *
	 */
	@Transactional
	public Set<CDictionary> findAllCDictionarys() throws DataAccessException {

		return findAllCDictionarys(-1, -1);
	}

	/**
	 * JPQL Query - findAllCDictionarys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CDictionary> findAllCDictionarys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCDictionarys", startResult, maxRows);
		return new LinkedHashSet<CDictionary>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CDictionary entity) {
		return true;
	}
}
