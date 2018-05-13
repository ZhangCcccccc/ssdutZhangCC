package net.xidlims.dao;

import net.xidlims.domain.TExperimentLabRoom;

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
 * DAO to manage TExperimentLabRoom entities.
 * 
 */
@Repository("TExperimentLabRoomDAO")
@Transactional
public class TExperimentLabRoomDAOImpl extends AbstractJpaDao<TExperimentLabRoom>
		implements TExperimentLabRoomDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TExperimentLabRoom.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TExperimentLabRoomDAOImpl
	 *
	 */
	public TExperimentLabRoomDAOImpl() {
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
	 * JPQL Query - findTExperimentLabRoomByExperimentSkillId
	 *
	 */
	@Transactional
	public Set<TExperimentLabRoom> findTExperimentLabRoomByExperimentSkillId(Integer experimentSkillId) throws DataAccessException {

		return findTExperimentLabRoomByExperimentSkillId(experimentSkillId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabRoomByExperimentSkillId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentLabRoom> findTExperimentLabRoomByExperimentSkillId(Integer experimentSkillId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentLabRoomByExperimentSkillId", startResult, maxRows, experimentSkillId);
		return new LinkedHashSet<TExperimentLabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentLabRoomByPrimaryKey
	 *
	 */
	@Transactional
	public TExperimentLabRoom findTExperimentLabRoomByPrimaryKey(Integer id) throws DataAccessException {

		return findTExperimentLabRoomByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabRoomByPrimaryKey
	 *
	 */

	@Transactional
	public TExperimentLabRoom findTExperimentLabRoomByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentLabRoomByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentLabRoom) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTExperimentLabRooms
	 *
	 */
	@Transactional
	public Set<TExperimentLabRoom> findAllTExperimentLabRooms() throws DataAccessException {

		return findAllTExperimentLabRooms(-1, -1);
	}

	/**
	 * JPQL Query - findAllTExperimentLabRooms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentLabRoom> findAllTExperimentLabRooms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTExperimentLabRooms", startResult, maxRows);
		return new LinkedHashSet<TExperimentLabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentLabRoomByLabRoomId
	 *
	 */
	@Transactional
	public Set<TExperimentLabRoom> findTExperimentLabRoomByLabRoomId(Integer labRoomId) throws DataAccessException {

		return findTExperimentLabRoomByLabRoomId(labRoomId, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabRoomByLabRoomId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TExperimentLabRoom> findTExperimentLabRoomByLabRoomId(Integer labRoomId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTExperimentLabRoomByLabRoomId", startResult, maxRows, labRoomId);
		return new LinkedHashSet<TExperimentLabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findTExperimentLabRoomById
	 *
	 */
	@Transactional
	public TExperimentLabRoom findTExperimentLabRoomById(Integer id) throws DataAccessException {

		return findTExperimentLabRoomById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTExperimentLabRoomById
	 *
	 */

	@Transactional
	public TExperimentLabRoom findTExperimentLabRoomById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTExperimentLabRoomById", startResult, maxRows, id);
			return (net.xidlims.domain.TExperimentLabRoom) query.getSingleResult();
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
	public boolean canBeMerged(TExperimentLabRoom entity) {
		return true;
	}
}
