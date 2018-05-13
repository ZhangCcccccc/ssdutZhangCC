package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabRoom;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoom entities.
 * 
 */
public interface LabRoomDAO extends JpaDao<LabRoom> {

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgenciesContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomManagerAgenciesContaining(String labRoomManagerAgencies) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgenciesContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomManagerAgenciesContaining(String labRoomManagerAgencies, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomCapacity
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomCapacity(Integer labRoomCapacity) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomCapacity
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomCapacity(Integer labRoomCapacity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomAddressContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomAddressContaining(String labRoomAddress) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomAddressContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomAddressContaining(String labRoomAddress, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomNameContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomNameContaining(String labRoomName) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomNameContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomNameContaining(String labRoomName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviationContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoonAbbreviationContaining(String labRoonAbbreviation) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviationContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoonAbbreviationContaining(String labRoonAbbreviation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgencies
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomManagerAgencies(String labRoomManagerAgencies_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomManagerAgencies
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomManagerAgencies(String labRoomManagerAgencies_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomPrizeInformation
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomPrizeInformation(String labRoomPrizeInformation) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomPrizeInformation
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomPrizeInformation(String labRoomPrizeInformation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRooms
	 *
	 */
	public Set<LabRoom> findAllLabRooms() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRooms
	 *
	 */
	public Set<LabRoom> findAllLabRooms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByPrimaryKey
	 *
	 */
	public LabRoom findLabRoomByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByPrimaryKey
	 *
	 */
	public LabRoom findLabRoomByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomTimeCreate
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomTimeCreate(java.util.Calendar labRoomTimeCreate) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomTimeCreate
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomTimeCreate(Calendar labRoomTimeCreate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomReservation
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomReservation(Integer labRoomReservation) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomReservation
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomReservation(Integer labRoomReservation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomAudit
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomAudit(Integer labRoomAudit) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomAudit
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomAudit(Integer labRoomAudit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomIntroduction
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomIntroduction(String labRoomIntroduction) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomIntroduction
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomIntroduction(String labRoomIntroduction, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomArea
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomArea(java.math.BigDecimal labRoomArea) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomArea
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomArea(BigDecimal labRoomArea, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomAddress
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomAddress(String labRoomAddress_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomAddress
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomAddress(String labRoomAddress_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomActive
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomActive(Integer labRoomActive) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomActive
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomActive(Integer labRoomActive, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomName
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomName(String labRoomName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomName
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomName(String labRoomName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviation
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoonAbbreviation(String labRoonAbbreviation_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoonAbbreviation
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoonAbbreviation(String labRoonAbbreviation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomById
	 *
	 */
	public LabRoom findLabRoomById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomById
	 *
	 */
	public LabRoom findLabRoomById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomEnName
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomEnName(String labRoomEnName) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomEnName
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomEnName(String labRoomEnName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomEnNameContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomEnNameContaining(String labRoomEnName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomEnNameContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomEnNameContaining(String labRoomEnName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomNumber
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomNumber(String labRoomNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomNumber
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomNumber(String labRoomNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByIsUsed
	 *
	 */
	public Set<LabRoom> findLabRoomByIsUsed(Integer isUsed) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByIsUsed
	 *
	 */
	public Set<LabRoom> findLabRoomByIsUsed(Integer isUsed, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomRegulations
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomRegulations(String labRoomRegulations) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomRegulations
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomRegulations(String labRoomRegulations, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomNumberContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomNumberContaining(String labRoomNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomByLabRoomNumberContaining
	 *
	 */
	public Set<LabRoom> findLabRoomByLabRoomNumberContaining(String labRoomNumber_1, int startResult, int maxRows) throws DataAccessException;

}