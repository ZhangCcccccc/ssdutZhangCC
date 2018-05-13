package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoom;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoom entities.
 * 
 */
@Repository("LabRoomDAO")
@Transactional
public class LabRoomDAOImpl extends AbstractJpaDao<LabRoom> implements
		LabRoomDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoom.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomDAOImpl
	 *
	 */
	public LabRoomDAOImpl() {
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
	 * JPQL Query - findLabRoomByLabRoomManagerAgenciesContaining
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomManagerAgenciesContaining(String labRoomManagerAgencies) throws DataAccessException {

		return findLabRoomByLabRoomManagerAgenciesContaining(labRoomManagerAgencies, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgenciesContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomManagerAgenciesContaining(String labRoomManagerAgencies, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomManagerAgenciesContaining", startResult, maxRows, labRoomManagerAgencies);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomCapacity
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomCapacity(Integer labRoomCapacity) throws DataAccessException {

		return findLabRoomByLabRoomCapacity(labRoomCapacity, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomCapacity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomCapacity(Integer labRoomCapacity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomCapacity", startResult, maxRows, labRoomCapacity);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomAddressContaining
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomAddressContaining(String labRoomAddress) throws DataAccessException {

		return findLabRoomByLabRoomAddressContaining(labRoomAddress, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomAddressContaining(String labRoomAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomAddressContaining", startResult, maxRows, labRoomAddress);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomNameContaining
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomNameContaining(String labRoomName) throws DataAccessException {

		return findLabRoomByLabRoomNameContaining(labRoomName, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomNameContaining(String labRoomName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomNameContaining", startResult, maxRows, labRoomName);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviationContaining
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoonAbbreviationContaining(String labRoonAbbreviation) throws DataAccessException {

		return findLabRoomByLabRoonAbbreviationContaining(labRoonAbbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoonAbbreviationContaining(String labRoonAbbreviation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoonAbbreviationContaining", startResult, maxRows, labRoonAbbreviation);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgencies
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomManagerAgencies(String labRoomManagerAgencies) throws DataAccessException {

		return findLabRoomByLabRoomManagerAgencies(labRoomManagerAgencies, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgencies
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomManagerAgencies(String labRoomManagerAgencies, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomManagerAgencies", startResult, maxRows, labRoomManagerAgencies);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomPrizeInformation
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomPrizeInformation(String labRoomPrizeInformation) throws DataAccessException {

		return findLabRoomByLabRoomPrizeInformation(labRoomPrizeInformation, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomPrizeInformation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomPrizeInformation(String labRoomPrizeInformation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomPrizeInformation", startResult, maxRows, labRoomPrizeInformation);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRooms
	 *
	 */
	@Transactional
	public Set<LabRoom> findAllLabRooms() throws DataAccessException {

		return findAllLabRooms(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRooms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findAllLabRooms(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRooms", startResult, maxRows);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoom findLabRoomByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoom findLabRoomByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoom) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomTimeCreate
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomTimeCreate(java.util.Calendar labRoomTimeCreate) throws DataAccessException {

		return findLabRoomByLabRoomTimeCreate(labRoomTimeCreate, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomTimeCreate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomTimeCreate(java.util.Calendar labRoomTimeCreate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomTimeCreate", startResult, maxRows, labRoomTimeCreate);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomReservation
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomReservation(Integer labRoomReservation) throws DataAccessException {

		return findLabRoomByLabRoomReservation(labRoomReservation, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomReservation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomReservation(Integer labRoomReservation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomReservation", startResult, maxRows, labRoomReservation);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomAudit
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomAudit(Integer labRoomAudit) throws DataAccessException {

		return findLabRoomByLabRoomAudit(labRoomAudit, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomAudit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomAudit(Integer labRoomAudit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomAudit", startResult, maxRows, labRoomAudit);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomIntroduction
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomIntroduction(String labRoomIntroduction) throws DataAccessException {

		return findLabRoomByLabRoomIntroduction(labRoomIntroduction, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomIntroduction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomIntroduction(String labRoomIntroduction, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomIntroduction", startResult, maxRows, labRoomIntroduction);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomArea
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomArea(java.math.BigDecimal labRoomArea) throws DataAccessException {

		return findLabRoomByLabRoomArea(labRoomArea, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomArea
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomArea(java.math.BigDecimal labRoomArea, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomArea", startResult, maxRows, labRoomArea);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomAddress
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomAddress(String labRoomAddress) throws DataAccessException {

		return findLabRoomByLabRoomAddress(labRoomAddress, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomAddress(String labRoomAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomAddress", startResult, maxRows, labRoomAddress);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomActive
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomActive(Integer labRoomActive) throws DataAccessException {

		return findLabRoomByLabRoomActive(labRoomActive, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomActive
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomActive(Integer labRoomActive, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomActive", startResult, maxRows, labRoomActive);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomName
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomName(String labRoomName) throws DataAccessException {

		return findLabRoomByLabRoomName(labRoomName, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomName(String labRoomName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomName", startResult, maxRows, labRoomName);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviation
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoonAbbreviation(String labRoonAbbreviation) throws DataAccessException {

		return findLabRoomByLabRoonAbbreviation(labRoonAbbreviation, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoonAbbreviation(String labRoonAbbreviation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoonAbbreviation", startResult, maxRows, labRoonAbbreviation);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomById
	 *
	 */
	@Transactional
	public LabRoom findLabRoomById(Integer id) throws DataAccessException {

		return findLabRoomById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomById
	 *
	 */

	@Transactional
	public LabRoom findLabRoomById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoom) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomEnName
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomEnName(String labRoomEnName) throws DataAccessException {

		return findLabRoomByLabRoomEnName(labRoomEnName, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomEnName(String labRoomEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomEnName", startResult, maxRows, labRoomEnName);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomEnNameContaining
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomEnNameContaining(String labRoomEnName) throws DataAccessException {

		return findLabRoomByLabRoomEnNameContaining(labRoomEnName, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomEnNameContaining(String labRoomEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomEnNameContaining", startResult, maxRows, labRoomEnName);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomNumber
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomNumber(String labRoomNumber) throws DataAccessException {

		return findLabRoomByLabRoomNumber(labRoomNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomNumber(String labRoomNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomNumber", startResult, maxRows, labRoomNumber);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByIsUsed
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByIsUsed(Integer isUsed) throws DataAccessException {

		return findLabRoomByIsUsed(isUsed, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByIsUsed
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByIsUsed(Integer isUsed, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByIsUsed", startResult, maxRows, isUsed);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomRegulations
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomRegulations(String labRoomRegulations) throws DataAccessException {

		return findLabRoomByLabRoomRegulations(labRoomRegulations, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomRegulations
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomRegulations(String labRoomRegulations, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomRegulations", startResult, maxRows, labRoomRegulations);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomNumberContaining
	 *
	 */
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomNumberContaining(String labRoomNumber) throws DataAccessException {

		return findLabRoomByLabRoomNumberContaining(labRoomNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomByLabRoomNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoom> findLabRoomByLabRoomNumberContaining(String labRoomNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomByLabRoomNumberContaining", startResult, maxRows, labRoomNumber);
		return new LinkedHashSet<LabRoom>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoom entity) {
		return true;
	}
}
