package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceLending;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceLending entities.
 * 
 */
@Repository("LabRoomDeviceLendingDAO")
@Transactional
public class LabRoomDeviceLendingDAOImpl extends AbstractJpaDao<LabRoomDeviceLending>
		implements LabRoomDeviceLendingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceLending.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceLendingDAOImpl
	 *
	 */
	public LabRoomDeviceLendingDAOImpl() {
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
	 * JPQL Query - findAllLabRoomDeviceLendings
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLending> findAllLabRoomDeviceLendings() throws DataAccessException {

		return findAllLabRoomDeviceLendings(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceLendings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLending> findAllLabRoomDeviceLendings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceLendings", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceLending>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByBackTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByBackTime(java.util.Calendar backTime) throws DataAccessException {

		return findLabRoomDeviceLendingByBackTime(backTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByBackTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByBackTime(java.util.Calendar backTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceLendingByBackTime", startResult, maxRows, backTime);
		return new LinkedHashSet<LabRoomDeviceLending>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingById
	 *
	 */
	@Transactional
	public LabRoomDeviceLending findLabRoomDeviceLendingById(Integer id) throws DataAccessException {

		return findLabRoomDeviceLendingById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingById
	 *
	 */

	@Transactional
	public LabRoomDeviceLending findLabRoomDeviceLendingById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceLendingById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceLending) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByContent
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByContent(String content) throws DataAccessException {

		return findLabRoomDeviceLendingByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceLendingByContent", startResult, maxRows, content);
		return new LinkedHashSet<LabRoomDeviceLending>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceLending findLabRoomDeviceLendingByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceLendingByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceLending findLabRoomDeviceLendingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceLendingByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceLending) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByLendingTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByLendingTime(java.util.Calendar lendingTime) throws DataAccessException {

		return findLabRoomDeviceLendingByLendingTime(lendingTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByLendingTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByLendingTime(java.util.Calendar lendingTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceLendingByLendingTime", startResult, maxRows, lendingTime);
		return new LinkedHashSet<LabRoomDeviceLending>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByReturnTime
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByReturnTime(java.util.Calendar returnTime) throws DataAccessException {

		return findLabRoomDeviceLendingByReturnTime(returnTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingByReturnTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByReturnTime(java.util.Calendar returnTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceLendingByReturnTime", startResult, maxRows, returnTime);
		return new LinkedHashSet<LabRoomDeviceLending>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomDeviceLending entity) {
		return true;
	}
}
