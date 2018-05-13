package net.xidlims.dao;

import net.xidlims.domain.TExperimentLabDevice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TExperimentLabDevice entities.
 * 
 */
@Repository("TExperimentLabDeviceDAO")
@Transactional
public class TExperimentLabDeviceDAOImpl extends AbstractJpaDao<TExperimentLabDevice>
		implements TExperimentLabDeviceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExperimentLabDevice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExperimentLabDeviceDAOImpl
	 *
	 */
	public TExperimentLabDeviceDAOImpl() {
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
	 * JPQL Query - findAllTExperimentLabDevices
	 *
	 */
	@Transactional
	public Set<TExperimentLabDevice> findAllTExperimentLabDevices() throws DataAccessException {

		return findAllTExperimentLabDevices(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExperimentLabDevices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentLabDevice> findAllTExperimentLabDevices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExperimentLabDevices", startResult, maxRows);
		return new LinkedHashSet<TExperimentLabDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceByLabDeviceId
	 *
	 */
	@Transactional
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByLabDeviceId(Integer labDeviceId) throws DataAccessException {

		return findTExperimentLabDeviceByLabDeviceId(labDeviceId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceByLabDeviceId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByLabDeviceId(Integer labDeviceId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentLabDeviceByLabDeviceId", startResult, maxRows, labDeviceId);
		return new LinkedHashSet<TExperimentLabDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceByPrimaryKey
	 *
	 */
	@Transactional
	public TExperimentLabDevice findTExperimentLabDeviceByPrimaryKey(Integer id) throws DataAccessException {

		return findTExperimentLabDeviceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceByPrimaryKey
	 *
	 */

	@Transactional
	public TExperimentLabDevice findTExperimentLabDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentLabDeviceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentLabDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceById
	 *
	 */
	@Transactional
	public TExperimentLabDevice findTExperimentLabDeviceById(Integer id) throws DataAccessException {

		return findTExperimentLabDeviceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceById
	 *
	 */

	@Transactional
	public TExperimentLabDevice findTExperimentLabDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentLabDeviceById", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentLabDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceByExperimentSkillId
	 *
	 */
	@Transactional
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByExperimentSkillId(Integer experimentSkillId) throws DataAccessException {

		return findTExperimentLabDeviceByExperimentSkillId(experimentSkillId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabDeviceByExperimentSkillId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByExperimentSkillId(Integer experimentSkillId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentLabDeviceByExperimentSkillId", startResult, maxRows, experimentSkillId);
		return new LinkedHashSet<TExperimentLabDevice>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TExperimentLabDevice entity) {
		return true;
	}
}
