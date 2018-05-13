package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabReservationTimeTableStudent;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabReservationTimeTableStudent entities.
 * 
 */
@Repository("LabReservationTimeTableStudentDAO")
@Transactional
public class LabReservationTimeTableStudentDAOImpl extends AbstractJpaDao<LabReservationTimeTableStudent>
		implements LabReservationTimeTableStudentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabReservationTimeTableStudent.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabReservationTimeTableStudentDAOImpl
	 *
	 */
	public LabReservationTimeTableStudentDAOImpl() {
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
	 * JPQL Query - findLabReservationTimeTableStudentByPrimaryKey
	 *
	 */
	@Transactional
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentByPrimaryKey(Integer id) throws DataAccessException {

		return findLabReservationTimeTableStudentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationTimeTableStudentByPrimaryKey
	 *
	 */

	@Transactional
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationTimeTableStudentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservationTimeTableStudent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabReservationTimeTableStudents
	 *
	 */
	@Transactional
	public Set<LabReservationTimeTableStudent> findAllLabReservationTimeTableStudents() throws DataAccessException {

		return findAllLabReservationTimeTableStudents(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabReservationTimeTableStudents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservationTimeTableStudent> findAllLabReservationTimeTableStudents(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabReservationTimeTableStudents", startResult, maxRows);
		return new LinkedHashSet<LabReservationTimeTableStudent>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationTimeTableStudentById
	 *
	 */
	@Transactional
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentById(Integer id) throws DataAccessException {

		return findLabReservationTimeTableStudentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationTimeTableStudentById
	 *
	 */

	@Transactional
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationTimeTableStudentById", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservationTimeTableStudent) query.getSingleResult();
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
	public boolean canBeMerged(LabReservationTimeTableStudent entity) {
		return true;
	}
}
