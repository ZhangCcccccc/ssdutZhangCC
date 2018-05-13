package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomDeviceLendingResult;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomDeviceLendingResult entities.
 * 
 */
@Repository("LabRoomDeviceLendingResultDAO")
@Transactional
public class LabRoomDeviceLendingResultDAOImpl extends AbstractJpaDao<LabRoomDeviceLendingResult>
		implements LabRoomDeviceLendingResultDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomDeviceLendingResult.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDeviceLendingResultDAOImpl
	 *
	 */
	public LabRoomDeviceLendingResultDAOImpl() {
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
	 * JPQL Query - findAllLabRoomDeviceLendingResults
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLendingResult> findAllLabRoomDeviceLendingResults() throws DataAccessException {

		return findAllLabRoomDeviceLendingResults(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomDeviceLendingResults
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLendingResult> findAllLabRoomDeviceLendingResults(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomDeviceLendingResults", startResult, maxRows);
		return new LinkedHashSet<LabRoomDeviceLendingResult>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomDeviceLendingResultByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceLendingResultByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceLendingResult) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultById
	 *
	 */
	@Transactional
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultById(Integer id) throws DataAccessException {

		return findLabRoomDeviceLendingResultById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultById
	 *
	 */

	@Transactional
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomDeviceLendingResultById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomDeviceLendingResult) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByRemark
	 *
	 */
	@Transactional
	public Set<LabRoomDeviceLendingResult> findLabRoomDeviceLendingResultByRemark(String remark) throws DataAccessException {

		return findLabRoomDeviceLendingResultByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomDeviceLendingResult> findLabRoomDeviceLendingResultByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomDeviceLendingResultByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<LabRoomDeviceLendingResult>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomDeviceLendingResult entity) {
		return true;
	}
}
