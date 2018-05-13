package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceReservation;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceReservation entities.
 * 
 */
@Repository("LabRoomDeviceReservationDAO")
@Transactional
public class LabRoomDeviceReservationDAOImpl extends AbstractJpaDao<LabRoomDeviceReservation>
		implements LabRoomDeviceReservationDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceReservation.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceReservationDAOImpl
	 *
	 */
	public LabRoomDeviceReservationDAOImpl() {
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
	 * JPQL Query - findLabRoomDeviceReservationById
	 *
	 */
	@Transactional
	public LabRoomDeviceReservation findLabRoomDeviceReservationById(Integer id) throws DataAccessException {

		return findLabRoomDeviceReservationById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationById
	 *
	 */

	@Transactional
	public LabRoomDeviceReservation findLabRoomDeviceReservationById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceReservationById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceReservation) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByContent
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByContent(String content) throws DataAccessException {

		return findLabRoomDeviceReservationByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByContent", startResult, maxRows, content);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhoneContaining
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhoneContaining(String phone) throws DataAccessException {

		return findLabRoomDeviceReservationByPhoneContaining(phone, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhoneContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhoneContaining(String phone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByPhoneContaining", startResult, maxRows, phone);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByEndtime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByEndtime(java.util.Calendar endtime) throws DataAccessException {

		return findLabRoomDeviceReservationByEndtime(endtime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByEndtime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByEndtime(java.util.Calendar endtime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByEndtime", startResult, maxRows, endtime);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByBegintime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByBegintime(java.util.Calendar begintime) throws DataAccessException {

		return findLabRoomDeviceReservationByBegintime(begintime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByBegintime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByBegintime(java.util.Calendar begintime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByBegintime", startResult, maxRows, begintime);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhone
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhone(String phone) throws DataAccessException {

		return findLabRoomDeviceReservationByPhone(phone, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhone
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhone(String phone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByPhone", startResult, maxRows, phone);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByTime(java.util.Calendar time) throws DataAccessException {

		return findLabRoomDeviceReservationByTime(time, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByTime(java.util.Calendar time, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByTime", startResult, maxRows, time);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStage
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStage(String stage) throws DataAccessException {

		return findLabRoomDeviceReservationByStage(stage, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStage(String stage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByStage", startResult, maxRows, stage);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStageContaining
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStageContaining(String stage) throws DataAccessException {

		return findLabRoomDeviceReservationByStageContaining(stage, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStageContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStageContaining(String stage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceReservationByStageContaining", startResult, maxRows, stage);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceReservations
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceReservation> findAllLabRoomDeviceReservations() throws DataAccessException {

		return findAllLabRoomDeviceReservations(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceReservations
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceReservation> findAllLabRoomDeviceReservations(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceReservations", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceReservation findLabRoomDeviceReservationByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceReservationByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceReservation findLabRoomDeviceReservationByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceReservationByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceReservation) query.getSingleResult();
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
	public boolean canBeMerged(LabRoomDeviceReservation entity) {
		return true;
	}
}
