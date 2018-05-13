package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CStaticValue;

import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CStaticValue entities.
 * 
 */
@Repository("CStaticValueDAO")
@Transactional
public class CStaticValueDAOImpl extends AbstractJpaDao<CStaticValue> implements
		CStaticValueDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CStaticValue.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CStaticValueDAOImpl
	 *
	 */
	public CStaticValueDAOImpl() {
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
	 * JPQL Query - findCStaticValueById
	 *
	 */
	@Transactional
	public CStaticValue findCStaticValueById(Integer id) throws DataAccessException {

		return findCStaticValueById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueById
	 *
	 */

	@Transactional
	public CStaticValue findCStaticValueById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCStaticValueById", startResult, maxRows, id);
			return (net.xidlims.domain.CStaticValue) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCStaticValueByValueNameContaining
	 *
	 */
	@Transactional
	public Set<CStaticValue> findCStaticValueByValueNameContaining(String valueName) throws DataAccessException {

		return findCStaticValueByValueNameContaining(valueName, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByValueNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findCStaticValueByValueNameContaining(String valueName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCStaticValueByValueNameContaining", startResult, maxRows, valueName);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * JPQL Query - findCStaticValueByStaticValue
	 *
	 */
	@Transactional
	public Set<CStaticValue> findCStaticValueByStaticValue(String staticValue) throws DataAccessException {

		return findCStaticValueByStaticValue(staticValue, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByStaticValue
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findCStaticValueByStaticValue(String staticValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCStaticValueByStaticValue", startResult, maxRows, staticValue);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCStaticValues
	 *
	 */
	@Transactional
	public Set<CStaticValue> findAllCStaticValues() throws DataAccessException {

		return findAllCStaticValues(-1, -1);
	}

	/**
	 * JPQL Query - findAllCStaticValues
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findAllCStaticValues(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCStaticValues", startResult, maxRows);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * JPQL Query - findCStaticValueByCode
	 *
	 */
	@Transactional
	public Set<CStaticValue> findCStaticValueByCode(String code) throws DataAccessException {

		return findCStaticValueByCode(code, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findCStaticValueByCode(String code, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCStaticValueByCode", startResult, maxRows, code);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * JPQL Query - findCStaticValueByCodeContaining
	 *
	 */
	@Transactional
	public Set<CStaticValue> findCStaticValueByCodeContaining(String code) throws DataAccessException {

		return findCStaticValueByCodeContaining(code, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findCStaticValueByCodeContaining(String code, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCStaticValueByCodeContaining", startResult, maxRows, code);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * JPQL Query - findCStaticValueByPrimaryKey
	 *
	 */
	@Transactional
	public CStaticValue findCStaticValueByPrimaryKey(Integer id) throws DataAccessException {

		return findCStaticValueByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByPrimaryKey
	 *
	 */

	@Transactional
	public CStaticValue findCStaticValueByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCStaticValueByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CStaticValue) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCStaticValueByValueName
	 *
	 */
	@Transactional
	public Set<CStaticValue> findCStaticValueByValueName(String valueName) throws DataAccessException {

		return findCStaticValueByValueName(valueName, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByValueName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findCStaticValueByValueName(String valueName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCStaticValueByValueName", startResult, maxRows, valueName);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * JPQL Query - findCStaticValueByStaticValueContaining
	 *
	 */
	@Transactional
	public Set<CStaticValue> findCStaticValueByStaticValueContaining(String staticValue) throws DataAccessException {

		return findCStaticValueByStaticValueContaining(staticValue, -1, -1);
	}

	/**
	 * JPQL Query - findCStaticValueByStaticValueContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CStaticValue> findCStaticValueByStaticValueContaining(String staticValue, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCStaticValueByStaticValueContaining", startResult, maxRows, staticValue);
		return new LinkedHashSet<CStaticValue>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CStaticValue entity) {
		return true;
	}
}
