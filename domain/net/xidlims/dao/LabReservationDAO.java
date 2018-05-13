package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabReservation;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabReservation entities.
 * 
 */
public interface LabReservationDAO extends JpaDao<LabReservation> {

	/**
	 * JPQL Query - findLabReservationByItemReleasese
	 *
	 */
	public Set<LabReservation> findLabReservationByItemReleasese(Integer itemReleasese) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByItemReleasese
	 *
	 */
	public Set<LabReservation> findLabReservationByItemReleasese(Integer itemReleasese, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByElectiveGroup
	 *
	 */
	public Set<LabReservation> findLabReservationByElectiveGroup(String electiveGroup) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByElectiveGroup
	 *
	 */
	public Set<LabReservation> findLabReservationByElectiveGroup(String electiveGroup, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservations
	 *
	 */
	public Set<LabReservation> findAllLabReservations() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservations
	 *
	 */
	public Set<LabReservation> findAllLabReservations(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByEnvironmentalRequirements
	 *
	 */
	public Set<LabReservation> findLabReservationByEnvironmentalRequirements(String environmentalRequirements) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByEnvironmentalRequirements
	 *
	 */
	public Set<LabReservation> findLabReservationByEnvironmentalRequirements(String environmentalRequirements, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByRemarksContaining
	 *
	 */
	public Set<LabReservation> findLabReservationByRemarksContaining(String remarks) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByRemarksContaining
	 *
	 */
	public Set<LabReservation> findLabReservationByRemarksContaining(String remarks, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationById
	 *
	 */
	public LabReservation findLabReservationById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationById
	 *
	 */
	public LabReservation findLabReservationById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByNumber
	 *
	 */
	public Set<LabReservation> findLabReservationByNumber(Integer number) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByNumber
	 *
	 */
	public Set<LabReservation> findLabReservationByNumber(Integer number, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByEventName
	 *
	 */
	public Set<LabReservation> findLabReservationByEventName(String eventName) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByEventName
	 *
	 */
	public Set<LabReservation> findLabReservationByEventName(String eventName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByElectiveGroupContaining
	 *
	 */
	public Set<LabReservation> findLabReservationByElectiveGroupContaining(String electiveGroup_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByElectiveGroupContaining
	 *
	 */
	public Set<LabReservation> findLabReservationByElectiveGroupContaining(String electiveGroup_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByEventNameContaining
	 *
	 */
	public Set<LabReservation> findLabReservationByEventNameContaining(String eventName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByEventNameContaining
	 *
	 */
	public Set<LabReservation> findLabReservationByEventNameContaining(String eventName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationBySelecteNumber
	 *
	 */
	public Set<LabReservation> findLabReservationBySelecteNumber(Integer selecteNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationBySelecteNumber
	 *
	 */
	public Set<LabReservation> findLabReservationBySelecteNumber(Integer selecteNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByReservations
	 *
	 */
	public Set<LabReservation> findLabReservationByReservations(String reservations) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByReservations
	 *
	 */
	public Set<LabReservation> findLabReservationByReservations(String reservations, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByAuditResults
	 *
	 */
	public Set<LabReservation> findLabReservationByAuditResults(Integer auditResults) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByAuditResults
	 *
	 */
	public Set<LabReservation> findLabReservationByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByRemarks
	 *
	 */
	public Set<LabReservation> findLabReservationByRemarks(String remarks_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByRemarks
	 *
	 */
	public Set<LabReservation> findLabReservationByRemarks(String remarks_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByPrimaryKey
	 *
	 */
	public LabReservation findLabReservationByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationByPrimaryKey
	 *
	 */
	public LabReservation findLabReservationByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}