package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolDeviceUse;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolDeviceUse entities.
 * 
 */
@Repository("SchoolDeviceUseDAO")
@Transactional
public class SchoolDeviceUseDAOImpl extends AbstractJpaDao<SchoolDeviceUse>
		implements SchoolDeviceUseDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolDeviceUse.class }));

	/**
	 * EntityManager injected by Spring for persistence unit dhulimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolDeviceUseDAOImpl
	 *
	 */
	public SchoolDeviceUseDAOImpl() {
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
	 * JPQL Query - findSchoolDeviceUseByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolDeviceUse findSchoolDeviceUseByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolDeviceUseByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolDeviceUse findSchoolDeviceUseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolDeviceUseByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolDeviceUse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByUseHours
	 *
	 */
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseHours(java.math.BigDecimal useHours) throws DataAccessException {

		return findSchoolDeviceUseByUseHours(useHours, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByUseHours
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseHours(java.math.BigDecimal useHours, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceUseByUseHours", startResult, maxRows, useHours);
		return new LinkedHashSet<SchoolDeviceUse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByUseCount
	 *
	 */
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseCount(java.math.BigDecimal useCount) throws DataAccessException {

		return findSchoolDeviceUseByUseCount(useCount, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByUseCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseCount(java.math.BigDecimal useCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceUseByUseCount", startResult, maxRows, useCount);
		return new LinkedHashSet<SchoolDeviceUse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByTerm
	 *
	 */
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByTerm(Integer term) throws DataAccessException {

		return findSchoolDeviceUseByTerm(term, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByTerm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByTerm(Integer term, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceUseByTerm", startResult, maxRows, term);
		return new LinkedHashSet<SchoolDeviceUse>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolDeviceUses
	 *
	 */
	@Transactional
	public Set<SchoolDeviceUse> findAllSchoolDeviceUses() throws DataAccessException {

		return findAllSchoolDeviceUses(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolDeviceUses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDeviceUse> findAllSchoolDeviceUses(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolDeviceUses", startResult, maxRows);
		return new LinkedHashSet<SchoolDeviceUse>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceUseById
	 *
	 */
	@Transactional
	public SchoolDeviceUse findSchoolDeviceUseById(Integer id) throws DataAccessException {

		return findSchoolDeviceUseById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceUseById
	 *
	 */

	@Transactional
	public SchoolDeviceUse findSchoolDeviceUseById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolDeviceUseById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolDeviceUse) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByPrice
	 *
	 */
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByPrice(java.math.BigDecimal price) throws DataAccessException {

		return findSchoolDeviceUseByPrice(price, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceUseByPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDeviceUse> findSchoolDeviceUseByPrice(java.math.BigDecimal price, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceUseByPrice", startResult, maxRows, price);
		return new LinkedHashSet<SchoolDeviceUse>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolDeviceUse entity) {
		return true;
	}
}
