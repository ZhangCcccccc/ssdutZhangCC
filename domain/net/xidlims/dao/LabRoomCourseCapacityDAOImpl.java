package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomCourseCapacity;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomCourseCapacity entities.
 * 
 */
@Repository("LabRoomCourseCapacityDAO")
@Transactional
public class LabRoomCourseCapacityDAOImpl extends AbstractJpaDao<LabRoomCourseCapacity>
		implements LabRoomCourseCapacityDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomCourseCapacity.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomCourseCapacityDAOImpl
	 *
	 */
	public LabRoomCourseCapacityDAOImpl() {
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
	 * JPQL Query - findLabRoomCourseCapacityById
	 *
	 */
	@Transactional
	public LabRoomCourseCapacity findLabRoomCourseCapacityById(Integer id) throws DataAccessException {

		return findLabRoomCourseCapacityById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomCourseCapacityById
	 *
	 */

	@Transactional
	public LabRoomCourseCapacity findLabRoomCourseCapacityById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomCourseCapacityById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomCourseCapacity) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabRoomCourseCapacitys
	 *
	 */
	@Transactional
	public Set<LabRoomCourseCapacity> findAllLabRoomCourseCapacitys() throws DataAccessException {

		return findAllLabRoomCourseCapacitys(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomCourseCapacitys
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomCourseCapacity> findAllLabRoomCourseCapacitys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomCourseCapacitys", startResult, maxRows);
		return new LinkedHashSet<LabRoomCourseCapacity>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomCourseCapacityByCapacity
	 *
	 */
	@Transactional
	public Set<LabRoomCourseCapacity> findLabRoomCourseCapacityByCapacity(Integer capacity) throws DataAccessException {

		return findLabRoomCourseCapacityByCapacity(capacity, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomCourseCapacityByCapacity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomCourseCapacity> findLabRoomCourseCapacityByCapacity(Integer capacity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomCourseCapacityByCapacity", startResult, maxRows, capacity);
		return new LinkedHashSet<LabRoomCourseCapacity>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomCourseCapacityByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomCourseCapacity findLabRoomCourseCapacityByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomCourseCapacityByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomCourseCapacityByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomCourseCapacity findLabRoomCourseCapacityByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomCourseCapacityByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomCourseCapacity) query.getSingleResult();
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
	public boolean canBeMerged(LabRoomCourseCapacity entity) {
		return true;
	}
}
