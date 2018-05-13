package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemRoom;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemRoom entities.
 * 
 */
@Repository("SystemRoomDAO")
@Transactional
public class SystemRoomDAOImpl extends AbstractJpaDao<SystemRoom> implements
		SystemRoomDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemRoom.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemRoomDAOImpl
	 *
	 */
	public SystemRoomDAOImpl() {
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
	 * JPQL Query - findSystemRoomByDepartmentNumber
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByDepartmentNumber(String departmentNumber) throws DataAccessException {

		return findSystemRoomByDepartmentNumber(departmentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByDepartmentNumber(String departmentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByDepartmentNumber", startResult, maxRows, departmentNumber);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByRoomUse
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomUse(String roomUse) throws DataAccessException {

		return findSystemRoomByRoomUse(roomUse, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomUse
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomUse(String roomUse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomUse", startResult, maxRows, roomUse);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSystemRoomByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByPrimaryKey
	 *
	 */
	@Transactional
	public SystemRoom findSystemRoomByPrimaryKey(String roomNumber) throws DataAccessException {

		return findSystemRoomByPrimaryKey(roomNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByPrimaryKey
	 *
	 */

	@Transactional
	public SystemRoom findSystemRoomByPrimaryKey(String roomNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemRoomByPrimaryKey", startResult, maxRows, roomNumber);
			return (net.xidlims.domain.SystemRoom) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNameContaining
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNameContaining(String roomName) throws DataAccessException {

		return findSystemRoomByRoomNameContaining(roomName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNameContaining(String roomName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomNameContaining", startResult, maxRows, roomName);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNumber
	 *
	 */
	@Transactional
	public SystemRoom findSystemRoomByRoomNumber(String roomNumber) throws DataAccessException {

		return findSystemRoomByRoomNumber(roomNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNumber
	 *
	 */

	@Transactional
	public SystemRoom findSystemRoomByRoomNumber(String roomNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemRoomByRoomNumber", startResult, maxRows, roomNumber);
			return (net.xidlims.domain.SystemRoom) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNo
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNo(String roomNo) throws DataAccessException {

		return findSystemRoomByRoomNo(roomNo, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNo(String roomNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomNo", startResult, maxRows, roomNo);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByDepartmentNumberContaining(String departmentNumber) throws DataAccessException {

		return findSystemRoomByDepartmentNumberContaining(departmentNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByDepartmentNumberContaining(String departmentNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByDepartmentNumberContaining", startResult, maxRows, departmentNumber);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSystemRooms
	 *
	 */
	@Transactional
	public Set<SystemRoom> findAllSystemRooms() throws DataAccessException {

		return findAllSystemRooms(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemRooms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findAllSystemRooms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemRooms", startResult, maxRows);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByRoomUseContaining
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomUseContaining(String roomUse) throws DataAccessException {

		return findSystemRoomByRoomUseContaining(roomUse, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomUseContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomUseContaining(String roomUse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomUseContaining", startResult, maxRows, roomUse);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByCreatedAt
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSystemRoomByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByRoomName
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomName(String roomName) throws DataAccessException {

		return findSystemRoomByRoomName(roomName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomName(String roomName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomName", startResult, maxRows, roomName);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNoContaining
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNoContaining(String roomNo) throws DataAccessException {

		return findSystemRoomByRoomNoContaining(roomNo, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNoContaining(String roomNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomNoContaining", startResult, maxRows, roomNo);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNumberContaining
	 *
	 */
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNumberContaining(String roomNumber) throws DataAccessException {

		return findSystemRoomByRoomNumberContaining(roomNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSystemRoomByRoomNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemRoom> findSystemRoomByRoomNumberContaining(String roomNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemRoomByRoomNumberContaining", startResult, maxRows, roomNumber);
		return new LinkedHashSet<SystemRoom>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemRoom entity) {
		return true;
	}
}
