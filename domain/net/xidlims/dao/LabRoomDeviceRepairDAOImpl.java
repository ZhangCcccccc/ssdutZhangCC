package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceRepair;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceRepair entities.
 * 
 */
@Repository("LabRoomDeviceRepairDAO")
@Transactional
public class LabRoomDeviceRepairDAOImpl extends AbstractJpaDao<LabRoomDeviceRepair>
		implements LabRoomDeviceRepairDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceRepair.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceRepairDAOImpl
	 *
	 */
	public LabRoomDeviceRepairDAOImpl() {
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
	 * JPQL Query - findLabRoomDeviceRepairByRepairRecords
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairRecords(String repairRecords) throws DataAccessException {

		return findLabRoomDeviceRepairByRepairRecords(repairRecords, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairRecords(String repairRecords, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairByRepairRecords", startResult, maxRows, repairRecords);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairTime(java.util.Calendar repairTime) throws DataAccessException {

		return findLabRoomDeviceRepairByRepairTime(repairTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairTime(java.util.Calendar repairTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairByRepairTime", startResult, maxRows, repairTime);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceRepair findLabRoomDeviceRepairByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceRepairByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceRepair findLabRoomDeviceRepairByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceRepairByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceRepair) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairBySoftwareFailure
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairBySoftwareFailure(String softwareFailure) throws DataAccessException {

		return findLabRoomDeviceRepairBySoftwareFailure(softwareFailure, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairBySoftwareFailure
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairBySoftwareFailure(String softwareFailure, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairBySoftwareFailure", startResult, maxRows, softwareFailure);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairById
	 *
	 */
	@Transactional
	public LabRoomDeviceRepair findLabRoomDeviceRepairById(Integer id) throws DataAccessException {

		return findLabRoomDeviceRepairById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairById
	 *
	 */

	@Transactional
	public LabRoomDeviceRepair findLabRoomDeviceRepairById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceRepairById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceRepair) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceRepairs
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findAllLabRoomDeviceRepairs() throws DataAccessException {

		return findAllLabRoomDeviceRepairs(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceRepairs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findAllLabRoomDeviceRepairs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceRepairs", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByCreateTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findLabRoomDeviceRepairByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRestoreTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRestoreTime(java.util.Calendar restoreTime) throws DataAccessException {

		return findLabRoomDeviceRepairByRestoreTime(restoreTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRestoreTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRestoreTime(java.util.Calendar restoreTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairByRestoreTime", startResult, maxRows, restoreTime);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByHardwareFailure
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByHardwareFailure(String hardwareFailure) throws DataAccessException {

		return findLabRoomDeviceRepairByHardwareFailure(hardwareFailure, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByHardwareFailure
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByHardwareFailure(String hardwareFailure, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairByHardwareFailure", startResult, maxRows, hardwareFailure);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByDescription
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByDescription(String description) throws DataAccessException {

		return findLabRoomDeviceRepairByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceRepairByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceRepairByDescription", startResult, maxRows, description);
		return new LinkedHashSet<LabRoomDeviceRepair>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomDeviceRepair entity) {
		return true;
	}
}
