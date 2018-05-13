package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabAnnex;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabAnnex entities.
 * 
 */
@Repository("LabAnnexDAO")
@Transactional
public class LabAnnexDAOImpl extends AbstractJpaDao<LabAnnex> implements
		LabAnnexDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabAnnex.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabAnnexDAOImpl
	 *
	 */
	public LabAnnexDAOImpl() {
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
	 * JPQL Query - findLabAnnexByLabNameContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabNameContaining(String labName) throws DataAccessException {

		return findLabAnnexByLabNameContaining(labName, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabNameContaining(String labName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabNameContaining", startResult, maxRows, labName);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabName
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabName(String labName) throws DataAccessException {

		return findLabAnnexByLabName(labName, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabName(String labName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabName", startResult, maxRows, labName);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabShortName
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabShortName(String labShortName) throws DataAccessException {

		return findLabAnnexByLabShortName(labShortName, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabShortName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabShortName(String labShortName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabShortName", startResult, maxRows, labShortName);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabDescription
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabDescription(String labDescription) throws DataAccessException {

		return findLabAnnexByLabDescription(labDescription, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabDescription(String labDescription, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabDescription", startResult, maxRows, labDescription);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabActive
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabActive(Integer labActive) throws DataAccessException {

		return findLabAnnexByLabActive(labActive, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabActive
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabActive(Integer labActive, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabActive", startResult, maxRows, labActive);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabCapacity
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabCapacity(String labCapacity) throws DataAccessException {

		return findLabAnnexByLabCapacity(labCapacity, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabCapacity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabCapacity(String labCapacity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabCapacity", startResult, maxRows, labCapacity);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabAttention
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabAttention(String labAttention) throws DataAccessException {

		return findLabAnnexByLabAttention(labAttention, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabAttention
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabAttention(String labAttention, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabAttention", startResult, maxRows, labAttention);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabSubjectContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabSubjectContaining(String labSubject) throws DataAccessException {

		return findLabAnnexByLabSubjectContaining(labSubject, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabSubjectContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabSubjectContaining(String labSubject, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabSubjectContaining", startResult, maxRows, labSubject);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabSubject
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabSubject(String labSubject) throws DataAccessException {

		return findLabAnnexByLabSubject(labSubject, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabSubject
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabSubject(String labSubject, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabSubject", startResult, maxRows, labSubject);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabUseAreaContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabUseAreaContaining(String labUseArea) throws DataAccessException {

		return findLabAnnexByLabUseAreaContaining(labUseArea, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabUseAreaContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabUseAreaContaining(String labUseArea, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabUseAreaContaining", startResult, maxRows, labUseArea);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabEnName
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabEnName(String labEnName) throws DataAccessException {

		return findLabAnnexByLabEnName(labEnName, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabEnName(String labEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabEnName", startResult, maxRows, labEnName);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByContact
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByContact(String contact) throws DataAccessException {

		return findLabAnnexByContact(contact, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByContact
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByContact(String contact, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByContact", startResult, maxRows, contact);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByBelongDepartment
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByBelongDepartment(String belongDepartment) throws DataAccessException {

		return findLabAnnexByBelongDepartment(belongDepartment, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByBelongDepartment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByBelongDepartment(String belongDepartment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByBelongDepartment", startResult, maxRows, belongDepartment);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByUpdatedAt
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findLabAnnexByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByAwardInformation
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByAwardInformation(String awardInformation) throws DataAccessException {

		return findLabAnnexByAwardInformation(awardInformation, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByAwardInformation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByAwardInformation(String awardInformation, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByAwardInformation", startResult, maxRows, awardInformation);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabEnNameContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabEnNameContaining(String labEnName) throws DataAccessException {

		return findLabAnnexByLabEnNameContaining(labEnName, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabEnNameContaining(String labEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabEnNameContaining", startResult, maxRows, labEnName);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabCapacityContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabCapacityContaining(String labCapacity) throws DataAccessException {

		return findLabAnnexByLabCapacityContaining(labCapacity, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabCapacityContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabCapacityContaining(String labCapacity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabCapacityContaining", startResult, maxRows, labCapacity);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabNumber
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabNumber(String labNumber) throws DataAccessException {

		return findLabAnnexByLabNumber(labNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabNumber(String labNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabNumber", startResult, maxRows, labNumber);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexById
	 *
	 */
	@Transactional
	public LabAnnex findLabAnnexById(Integer id) throws DataAccessException {

		return findLabAnnexById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexById
	 *
	 */

	@Transactional
	public LabAnnex findLabAnnexById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabAnnexById", startResult, maxRows, id);
			return (net.xidlims.domain.LabAnnex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabAnnexByLabUseArea
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabUseArea(String labUseArea) throws DataAccessException {

		return findLabAnnexByLabUseArea(labUseArea, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabUseArea
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabUseArea(String labUseArea, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabUseArea", startResult, maxRows, labUseArea);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByBelongDepartmentContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByBelongDepartmentContaining(String belongDepartment) throws DataAccessException {

		return findLabAnnexByBelongDepartmentContaining(belongDepartment, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByBelongDepartmentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByBelongDepartmentContaining(String belongDepartment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByBelongDepartmentContaining", startResult, maxRows, belongDepartment);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByPrimaryKey
	 *
	 */
	@Transactional
	public LabAnnex findLabAnnexByPrimaryKey(Integer id) throws DataAccessException {

		return findLabAnnexByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByPrimaryKey
	 *
	 */

	@Transactional
	public LabAnnex findLabAnnexByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabAnnexByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabAnnex) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabAnnexByCreatedAt
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findLabAnnexByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByContactContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByContactContaining(String contact) throws DataAccessException {

		return findLabAnnexByContactContaining(contact, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByContactContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByContactContaining(String contact, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByContactContaining", startResult, maxRows, contact);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabShortNameContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabShortNameContaining(String labShortName) throws DataAccessException {

		return findLabAnnexByLabShortNameContaining(labShortName, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabShortNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabShortNameContaining(String labShortName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabShortNameContaining", startResult, maxRows, labShortName);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabAnnexs
	 *
	 */
	@Transactional
	public Set<LabAnnex> findAllLabAnnexs() throws DataAccessException {

		return findAllLabAnnexs(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabAnnexs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findAllLabAnnexs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabAnnexs", startResult, maxRows);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabAnnexByLabNumberContaining
	 *
	 */
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabNumberContaining(String labNumber) throws DataAccessException {

		return findLabAnnexByLabNumberContaining(labNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabAnnexByLabNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabAnnex> findLabAnnexByLabNumberContaining(String labNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabAnnexByLabNumberContaining", startResult, maxRows, labNumber);
		return new LinkedHashSet<LabAnnex>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabAnnex entity) {
		return true;
	}
}
