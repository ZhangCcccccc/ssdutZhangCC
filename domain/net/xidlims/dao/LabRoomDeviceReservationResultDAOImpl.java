package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceReservationResult;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceReservationResult entities.
 * 
 */
@Repository("LabRoomDeviceReservationResultDAO")
@Transactional
public class LabRoomDeviceReservationResultDAOImpl extends AbstractJpaDao<LabRoomDeviceReservationResult>
		implements LabRoomDeviceReservationResultDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceReservationResult.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceReservationResultDAOImpl
	 *
	 */
	public LabRoomDeviceReservationResultDAOImpl() {
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
	 * JPQL Query - findLabRoomDeviceReservationResultByRemark
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservationResult> findLabRoomDeviceReservationResultByRemark(String remark) throws DataAccessException {

		return findLabRoomDeviceReservationResultByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservationResult> findLabRoomDeviceReservationResultByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationResultByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<LabRoomDeviceReservationResult>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceReservationResults
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservationResult> findAllLabRoomDeviceReservationResults() throws DataAccessException {

		return findAllLabRoomDeviceReservationResults(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceReservationResults
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservationResult> findAllLabRoomDeviceReservationResults(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceReservationResults", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceReservationResult>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceReservationResultByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceReservationResultByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceReservationResult) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultById
	 *
	 */
	@Transactional
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultById(Integer id) throws DataAccessException {

		return findLabRoomDeviceReservationResultById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultById
	 *
	 */

	@Transactional
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceReservationResultById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceReservationResult) query.getSingleResult();
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
	public boolean canBeMerged(LabRoomDeviceReservationResult entity) {
		return true;
	}
}
