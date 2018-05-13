package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceTrainingPeople;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceTrainingPeople entities.
 * 
 */
@Repository("LabRoomDeviceTrainingPeopleDAO")
@Transactional
public class LabRoomDeviceTrainingPeopleDAOImpl extends AbstractJpaDao<LabRoomDeviceTrainingPeople>
		implements LabRoomDeviceTrainingPeopleDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceTrainingPeople.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceTrainingPeopleDAOImpl
	 *
	 */
	public LabRoomDeviceTrainingPeopleDAOImpl() {
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
	 * JPQL Query - findLabRoomDeviceTrainingPeopleByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceTrainingPeopleByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceTrainingPeopleByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceTrainingPeople) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleById
	 *
	 */
	@Transactional
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleById(Integer id) throws DataAccessException {

		return findLabRoomDeviceTrainingPeopleById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleById
	 *
	 */

	@Transactional
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceTrainingPeopleById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceTrainingPeople) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainingPeoples
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTrainingPeople> findAllLabRoomDeviceTrainingPeoples() throws DataAccessException {

		return findAllLabRoomDeviceTrainingPeoples(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainingPeoples
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTrainingPeople> findAllLabRoomDeviceTrainingPeoples(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceTrainingPeoples", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceTrainingPeople>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomDeviceTrainingPeople entity) {
		return true;
	}
}
