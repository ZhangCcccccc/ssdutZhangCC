package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDevicePermitUsers;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDevicePermitUsers entities.
 * 
 */
@Repository("LabRoomDevicePermitUsersDAO")
@Transactional
public class LabRoomDevicePermitUsersDAOImpl extends AbstractJpaDao<LabRoomDevicePermitUsers>
		implements LabRoomDevicePermitUsersDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDevicePermitUsers.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDevicePermitUsersDAOImpl
	 *
	 */
	public LabRoomDevicePermitUsersDAOImpl() {
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
	 * JPQL Query - findLabRoomDevicePermitUsersByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDevicePermitUsersByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDevicePermitUsersByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDevicePermitUsers) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabRoomDevicePermitUserss
	 *
	 */
	@Transactional
	public Set<LabRoomDevicePermitUsers> findAllLabRoomDevicePermitUserss() throws DataAccessException {

		return findAllLabRoomDevicePermitUserss(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDevicePermitUserss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDevicePermitUsers> findAllLabRoomDevicePermitUserss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDevicePermitUserss", startResult, maxRows);
		return new LinkedHashSet<LabRoomDevicePermitUsers>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersById
	 *
	 */
	@Transactional
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersById(Integer id) throws DataAccessException {

		return findLabRoomDevicePermitUsersById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersById
	 *
	 */

	@Transactional
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDevicePermitUsersById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDevicePermitUsers) query.getSingleResult();
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
	public boolean canBeMerged(LabRoomDevicePermitUsers entity) {
		return true;
	}
}
