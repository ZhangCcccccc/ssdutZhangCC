package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabReservationTimeTable;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabReservationTimeTable entities.
 * 
 */
@Repository("LabReservationTimeTableDAO")
@Transactional
public class LabReservationTimeTableDAOImpl extends AbstractJpaDao<LabReservationTimeTable>
		implements LabReservationTimeTableDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabReservationTimeTable.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabReservationTimeTableDAOImpl
	 *
	 */
	public LabReservationTimeTableDAOImpl() {
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
	 * JPQL Query - findLabReservationTimeTableByPrimaryKey
	 *
	 */
	@Transactional
	public LabReservationTimeTable findLabReservationTimeTableByPrimaryKey(Integer id) throws DataAccessException {

		return findLabReservationTimeTableByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationTimeTableByPrimaryKey
	 *
	 */

	@Transactional
	public LabReservationTimeTable findLabReservationTimeTableByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationTimeTableByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservationTimeTable) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabReservationTimeTables
	 *
	 */
	@Transactional
	public Set<LabReservationTimeTable> findAllLabReservationTimeTables() throws DataAccessException {

		return findAllLabReservationTimeTables(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabReservationTimeTables
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservationTimeTable> findAllLabReservationTimeTables(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabReservationTimeTables", startResult, maxRows);
		return new LinkedHashSet<LabReservationTimeTable>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationTimeTableById
	 *
	 */
	@Transactional
	public LabReservationTimeTable findLabReservationTimeTableById(Integer id) throws DataAccessException {

		return findLabReservationTimeTableById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationTimeTableById
	 *
	 */

	@Transactional
	public LabReservationTimeTable findLabReservationTimeTableById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationTimeTableById", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservationTimeTable) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabReservationTimeTable entity) {
		return true;
	}
}
