package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomAgent;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomAgent entities.
 * 
 */
@Repository("LabRoomAgentDAO")
@Transactional
public class LabRoomAgentDAOImpl extends AbstractJpaDao<LabRoomAgent> implements
		LabRoomAgentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomAgent.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomAgentDAOImpl
	 *
	 */
	public LabRoomAgentDAOImpl() {
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
	 * JPQL Query - findLabRoomAgentByHardwareIp
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIp(String hardwareIp) throws DataAccessException {

		return findLabRoomAgentByHardwareIp(hardwareIp, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIp(String hardwareIp, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAgentByHardwareIp", startResult, maxRows, hardwareIp);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomAgents
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findAllLabRoomAgents() throws DataAccessException {

		return findAllLabRoomAgents(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomAgents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findAllLabRoomAgents(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomAgents", startResult, maxRows);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIpContaining
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIpContaining(String hardwareIp) throws DataAccessException {

		return findLabRoomAgentByHardwareIpContaining(hardwareIp, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIpContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIpContaining(String hardwareIp, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAgentByHardwareIpContaining", startResult, maxRows, hardwareIp);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePortContaining
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePortContaining(String hardwarePort) throws DataAccessException {

		return findLabRoomAgentByHardwarePortContaining(hardwarePort, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePortContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePortContaining(String hardwarePort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAgentByHardwarePortContaining", startResult, maxRows, hardwarePort);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomAgentByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomAgent findLabRoomAgentByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomAgentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomAgent findLabRoomAgentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomAgentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomAgent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomAgentById
	 *
	 */
	@Transactional
	public LabRoomAgent findLabRoomAgentById(Integer id) throws DataAccessException {

		return findLabRoomAgentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentById
	 *
	 */

	@Transactional
	public LabRoomAgent findLabRoomAgentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomAgentById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomAgent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemark
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemark(String hardwareRemark) throws DataAccessException {

		return findLabRoomAgentByHardwareRemark(hardwareRemark, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemark(String hardwareRemark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAgentByHardwareRemark", startResult, maxRows, hardwareRemark);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemarkContaining
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemarkContaining(String hardwareRemark) throws DataAccessException {

		return findLabRoomAgentByHardwareRemarkContaining(hardwareRemark, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemarkContaining(String hardwareRemark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAgentByHardwareRemarkContaining", startResult, maxRows, hardwareRemark);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePort
	 *
	 */
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePort(String hardwarePort) throws DataAccessException {

		return findLabRoomAgentByHardwarePort(hardwarePort, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePort
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePort(String hardwarePort, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAgentByHardwarePort", startResult, maxRows, hardwarePort);
		return new LinkedHashSet<LabRoomAgent>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomAgent entity) {
		return true;
	}
}
