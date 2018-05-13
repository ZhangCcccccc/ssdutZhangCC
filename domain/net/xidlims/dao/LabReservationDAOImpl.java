package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabReservation;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabReservation entities.
 * 
 */
@Repository("LabReservationDAO")
@Transactional
public class LabReservationDAOImpl extends AbstractJpaDao<LabReservation>
		implements LabReservationDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabReservation.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabReservationDAOImpl
	 *
	 */
	public LabReservationDAOImpl() {
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
	 * JPQL Query - findLabReservationByItemReleasese
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByItemReleasese(Integer itemReleasese) throws DataAccessException {

		return findLabReservationByItemReleasese(itemReleasese, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByItemReleasese
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByItemReleasese(Integer itemReleasese, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByItemReleasese", startResult, maxRows, itemReleasese);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByElectiveGroup
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByElectiveGroup(String electiveGroup) throws DataAccessException {

		return findLabReservationByElectiveGroup(electiveGroup, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByElectiveGroup
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByElectiveGroup(String electiveGroup, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByElectiveGroup", startResult, maxRows, electiveGroup);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabReservations
	 *
	 */
	@Transactional
	public Set<LabReservation> findAllLabReservations() throws DataAccessException {

		return findAllLabReservations(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabReservations
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findAllLabReservations(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabReservations", startResult, maxRows);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByEnvironmentalRequirements
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByEnvironmentalRequirements(String environmentalRequirements) throws DataAccessException {

		return findLabReservationByEnvironmentalRequirements(environmentalRequirements, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByEnvironmentalRequirements
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByEnvironmentalRequirements(String environmentalRequirements, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByEnvironmentalRequirements", startResult, maxRows, environmentalRequirements);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByRemarksContaining
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByRemarksContaining(String remarks) throws DataAccessException {

		return findLabReservationByRemarksContaining(remarks, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByRemarksContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByRemarksContaining(String remarks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByRemarksContaining", startResult, maxRows, remarks);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationById
	 *
	 */
	@Transactional
	public LabReservation findLabReservationById(Integer id) throws DataAccessException {

		return findLabReservationById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationById
	 *
	 */

	@Transactional
	public LabReservation findLabReservationById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationById", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservation) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabReservationByNumber
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByNumber(Integer number) throws DataAccessException {

		return findLabReservationByNumber(number, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByNumber(Integer number, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByNumber", startResult, maxRows, number);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByEventName
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByEventName(String eventName) throws DataAccessException {

		return findLabReservationByEventName(eventName, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByEventName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByEventName(String eventName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByEventName", startResult, maxRows, eventName);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByElectiveGroupContaining
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByElectiveGroupContaining(String electiveGroup) throws DataAccessException {

		return findLabReservationByElectiveGroupContaining(electiveGroup, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByElectiveGroupContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByElectiveGroupContaining(String electiveGroup, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByElectiveGroupContaining", startResult, maxRows, electiveGroup);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByEventNameContaining
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByEventNameContaining(String eventName) throws DataAccessException {

		return findLabReservationByEventNameContaining(eventName, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByEventNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByEventNameContaining(String eventName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByEventNameContaining", startResult, maxRows, eventName);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationBySelecteNumber
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationBySelecteNumber(Integer selecteNumber) throws DataAccessException {

		return findLabReservationBySelecteNumber(selecteNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationBySelecteNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationBySelecteNumber(Integer selecteNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationBySelecteNumber", startResult, maxRows, selecteNumber);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByReservations
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByReservations(String reservations) throws DataAccessException {

		return findLabReservationByReservations(reservations, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByReservations
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByReservations(String reservations, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByReservations", startResult, maxRows, reservations);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByAuditResults
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByAuditResults(Integer auditResults) throws DataAccessException {

		return findLabReservationByAuditResults(auditResults, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByAuditResults
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByAuditResults", startResult, maxRows, auditResults);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByRemarks
	 *
	 */
	@Transactional
	public Set<LabReservation> findLabReservationByRemarks(String remarks) throws DataAccessException {

		return findLabReservationByRemarks(remarks, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByRemarks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabReservation> findLabReservationByRemarks(String remarks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabReservationByRemarks", startResult, maxRows, remarks);
		return new LinkedHashSet<LabReservation>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabReservationByPrimaryKey
	 *
	 */
	@Transactional
	public LabReservation findLabReservationByPrimaryKey(Integer id) throws DataAccessException {

		return findLabReservationByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabReservationByPrimaryKey
	 *
	 */

	@Transactional
	public LabReservation findLabReservationByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabReservationByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabReservation) query.getSingleResult();
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
	public boolean canBeMerged(LabReservation entity) {
		return true;
	}
}
