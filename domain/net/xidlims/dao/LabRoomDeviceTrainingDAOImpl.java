package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceTraining;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceTraining entities.
 * 
 */
@Repository("LabRoomDeviceTrainingDAO")
@Transactional
public class LabRoomDeviceTrainingDAOImpl extends AbstractJpaDao<LabRoomDeviceTraining>
		implements LabRoomDeviceTrainingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceTraining.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceTrainingDAOImpl
	 *
	 */
	public LabRoomDeviceTrainingDAOImpl() {
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
	 * JPQL Query - findLabRoomDeviceTrainingByPassRateContaining
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRateContaining(String passRate) throws DataAccessException {

		return findLabRoomDeviceTrainingByPassRateContaining(passRate, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRateContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRateContaining(String passRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByPassRateContaining", startResult, maxRows, passRate);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddressContaining
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddressContaining(String address) throws DataAccessException {

		return findLabRoomDeviceTrainingByAddressContaining(address, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddressContaining(String address, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByAddressContaining", startResult, maxRows, address);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByJoinNumber
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByJoinNumber(Integer joinNumber) throws DataAccessException {

		return findLabRoomDeviceTrainingByJoinNumber(joinNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByJoinNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByJoinNumber(Integer joinNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByJoinNumber", startResult, maxRows, joinNumber);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainings
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findAllLabRoomDeviceTrainings() throws DataAccessException {

		return findAllLabRoomDeviceTrainings(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findAllLabRoomDeviceTrainings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceTrainings", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddress
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddress(String address) throws DataAccessException {

		return findLabRoomDeviceTrainingByAddress(address, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddress(String address, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByAddress", startResult, maxRows, address);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRate
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRate(String passRate) throws DataAccessException {

		return findLabRoomDeviceTrainingByPassRate(passRate, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRate(String passRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByPassRate", startResult, maxRows, passRate);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByStatus
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByStatus(Integer status) throws DataAccessException {

		return findLabRoomDeviceTrainingByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByStatus", startResult, maxRows, status);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingById
	 *
	 */
	@Transactional
	public LabRoomDeviceTraining findLabRoomDeviceTrainingById(Integer id) throws DataAccessException {

		return findLabRoomDeviceTrainingById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingById
	 *
	 */

	@Transactional
	public LabRoomDeviceTraining findLabRoomDeviceTrainingById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceTrainingById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceTraining) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByContent
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByContent(String content) throws DataAccessException {

		return findLabRoomDeviceTrainingByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByContent", startResult, maxRows, content);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByNumber
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByNumber(Integer number) throws DataAccessException {

		return findLabRoomDeviceTrainingByNumber(number, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByNumber(Integer number, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByNumber", startResult, maxRows, number);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoin
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoin(String accessJoin) throws DataAccessException {

		return findLabRoomDeviceTrainingByAccessJoin(accessJoin, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoin(String accessJoin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByAccessJoin", startResult, maxRows, accessJoin);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceTraining findLabRoomDeviceTrainingByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceTrainingByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceTraining findLabRoomDeviceTrainingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceTrainingByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceTraining) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByTime(java.util.Calendar time) throws DataAccessException {

		return findLabRoomDeviceTrainingByTime(time, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByTime(java.util.Calendar time, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByTime", startResult, maxRows, time);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoinContaining
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoinContaining(String accessJoin) throws DataAccessException {

		return findLabRoomDeviceTrainingByAccessJoinContaining(accessJoin, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoinContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoinContaining(String accessJoin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceTrainingByAccessJoinContaining", startResult, maxRows, accessJoin);
		return new LinkedHashSet<LabRoomDeviceTraining>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomDeviceTraining entity) {
		return true;
	}
}
