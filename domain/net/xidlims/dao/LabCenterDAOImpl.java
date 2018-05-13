package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabCenter;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabCenter entities.
 * 
 */
@Repository("LabCenterDAO")
@Transactional
public class LabCenterDAOImpl extends AbstractJpaDao<LabCenter> implements
		LabCenterDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabCenter.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabCenterDAOImpl
	 *
	 */
	public LabCenterDAOImpl() {
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
	 * JPQL Query - findLabCenterByCenterAddress
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCenterAddress(String centerAddress) throws DataAccessException {

		return findLabCenterByCenterAddress(centerAddress, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCenterAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCenterAddress(String centerAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCenterAddress", startResult, maxRows, centerAddress);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByPrimaryKey
	 *
	 */
	@Transactional
	public LabCenter findLabCenterByPrimaryKey(Integer id) throws DataAccessException {

		return findLabCenterByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByPrimaryKey
	 *
	 */

	@Transactional
	public LabCenter findLabCenterByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabCenterByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabCenter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabCenterByUpdatedAt
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findLabCenterByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByCenterNumber
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCenterNumber(String centerNumber) throws DataAccessException {

		return findLabCenterByCenterNumber(centerNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCenterNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCenterNumber(String centerNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCenterNumber", startResult, maxRows, centerNumber);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterById
	 *
	 */
	@Transactional
	public LabCenter findLabCenterById(Integer id) throws DataAccessException {

		return findLabCenterById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterById
	 *
	 */

	@Transactional
	public LabCenter findLabCenterById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabCenterById", startResult, maxRows, id);
			return (net.xidlims.domain.LabCenter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabCenterByEnabled
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByEnabled(Integer enabled) throws DataAccessException {

		return findLabCenterByEnabled(enabled, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByEnabled
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByEnabled(Integer enabled, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByEnabled", startResult, maxRows, enabled);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByCreatedAt
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findLabCenterByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByCenterAddressContaining
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCenterAddressContaining(String centerAddress) throws DataAccessException {

		return findLabCenterByCenterAddressContaining(centerAddress, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCenterAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCenterAddressContaining(String centerAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCenterAddressContaining", startResult, maxRows, centerAddress);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByCenterName
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCenterName(String centerName) throws DataAccessException {

		return findLabCenterByCenterName(centerName, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCenterName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCenterName(String centerName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCenterName", startResult, maxRows, centerName);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByCenterNameContaining
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCenterNameContaining(String centerName) throws DataAccessException {

		return findLabCenterByCenterNameContaining(centerName, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCenterNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCenterNameContaining(String centerName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCenterNameContaining", startResult, maxRows, centerName);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabCenterByCenterNumberContaining
	 *
	 */
	@Transactional
	public Set<LabCenter> findLabCenterByCenterNumberContaining(String centerNumber) throws DataAccessException {

		return findLabCenterByCenterNumberContaining(centerNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabCenterByCenterNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findLabCenterByCenterNumberContaining(String centerNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabCenterByCenterNumberContaining", startResult, maxRows, centerNumber);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabCenters
	 *
	 */
	@Transactional
	public Set<LabCenter> findAllLabCenters() throws DataAccessException {

		return findAllLabCenters(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabCenters
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabCenter> findAllLabCenters(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabCenters", startResult, maxRows);
		return new LinkedHashSet<LabCenter>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabCenter entity) {
		return true;
	}
}
