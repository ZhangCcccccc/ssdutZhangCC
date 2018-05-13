package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolWeekday;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolWeekday entities.
 * 
 */
@Repository("SchoolWeekdayDAO")
@Transactional
public class SchoolWeekdayDAOImpl extends AbstractJpaDao<SchoolWeekday>
		implements SchoolWeekdayDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolWeekday.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolWeekdayDAOImpl
	 *
	 */
	public SchoolWeekdayDAOImpl() {
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
	 * JPQL Query - findSchoolWeekdayByWeekdayName
	 *
	 */
	@Transactional
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayName(String weekdayName) throws DataAccessException {

		return findSchoolWeekdayByWeekdayName(weekdayName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayName(String weekdayName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekdayByWeekdayName", startResult, maxRows, weekdayName);
		return new LinkedHashSet<SchoolWeekday>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekdayById
	 *
	 */
	@Transactional
	public SchoolWeekday findSchoolWeekdayById(Integer id) throws DataAccessException {

		return findSchoolWeekdayById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekdayById
	 *
	 */

	@Transactional
	public SchoolWeekday findSchoolWeekdayById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolWeekdayById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolWeekday) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllSchoolWeekdays
	 *
	 */
	@Transactional
	public Set<SchoolWeekday> findAllSchoolWeekdays() throws DataAccessException {

		return findAllSchoolWeekdays(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolWeekdays
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeekday> findAllSchoolWeekdays(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolWeekdays", startResult, maxRows);
		return new LinkedHashSet<SchoolWeekday>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolWeekdayByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolWeekday findSchoolWeekdayByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolWeekdayByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekdayByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolWeekday findSchoolWeekdayByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolWeekdayByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolWeekday) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayNameContaining(String weekdayName) throws DataAccessException {

		return findSchoolWeekdayByWeekdayNameContaining(weekdayName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolWeekdayByWeekdayNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolWeekday> findSchoolWeekdayByWeekdayNameContaining(String weekdayName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolWeekdayByWeekdayNameContaining", startResult, maxRows, weekdayName);
		return new LinkedHashSet<SchoolWeekday>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolWeekday entity) {
		return true;
	}
}
