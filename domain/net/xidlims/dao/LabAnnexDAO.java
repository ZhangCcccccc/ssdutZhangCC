package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabAnnex;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabAnnex entities.
 * 
 */
public interface LabAnnexDAO extends JpaDao<LabAnnex> {

	/**
	 * JPQL Query - findLabAnnexByLabNameContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabNameContaining(String labName) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabNameContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabNameContaining(String labName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabName
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabName(String labName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabName
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabName(String labName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabShortName
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabShortName(String labShortName) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabShortName
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabShortName(String labShortName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabDescription
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabDescription(String labDescription) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabDescription
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabDescription(String labDescription, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabActive
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabActive(Integer labActive) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabActive
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabActive(Integer labActive, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabCapacity
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabCapacity(String labCapacity) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabCapacity
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabCapacity(String labCapacity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabAttention
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabAttention(String labAttention) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabAttention
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabAttention(String labAttention, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabSubjectContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabSubjectContaining(String labSubject) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabSubjectContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabSubjectContaining(String labSubject, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabSubject
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabSubject(String labSubject_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabSubject
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabSubject(String labSubject_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabUseAreaContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabUseAreaContaining(String labUseArea) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabUseAreaContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabUseAreaContaining(String labUseArea, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabEnName
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabEnName(String labEnName) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabEnName
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabEnName(String labEnName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByContact
	 *
	 */
	public Set<LabAnnex> findLabAnnexByContact(String contact) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByContact
	 *
	 */
	public Set<LabAnnex> findLabAnnexByContact(String contact, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByBelongDepartment
	 *
	 */
	public Set<LabAnnex> findLabAnnexByBelongDepartment(String belongDepartment) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByBelongDepartment
	 *
	 */
	public Set<LabAnnex> findLabAnnexByBelongDepartment(String belongDepartment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByUpdatedAt
	 *
	 */
	public Set<LabAnnex> findLabAnnexByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByUpdatedAt
	 *
	 */
	public Set<LabAnnex> findLabAnnexByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByAwardInformation
	 *
	 */
	public Set<LabAnnex> findLabAnnexByAwardInformation(String awardInformation) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByAwardInformation
	 *
	 */
	public Set<LabAnnex> findLabAnnexByAwardInformation(String awardInformation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabEnNameContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabEnNameContaining(String labEnName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabEnNameContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabEnNameContaining(String labEnName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabCapacityContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabCapacityContaining(String labCapacity_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabCapacityContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabCapacityContaining(String labCapacity_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabNumber
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabNumber(String labNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabNumber
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabNumber(String labNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexById
	 *
	 */
	public LabAnnex findLabAnnexById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexById
	 *
	 */
	public LabAnnex findLabAnnexById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabUseArea
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabUseArea(String labUseArea_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabUseArea
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabUseArea(String labUseArea_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByBelongDepartmentContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByBelongDepartmentContaining(String belongDepartment_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByBelongDepartmentContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByBelongDepartmentContaining(String belongDepartment_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByPrimaryKey
	 *
	 */
	public LabAnnex findLabAnnexByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByPrimaryKey
	 *
	 */
	public LabAnnex findLabAnnexByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByCreatedAt
	 *
	 */
	public Set<LabAnnex> findLabAnnexByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByCreatedAt
	 *
	 */
	public Set<LabAnnex> findLabAnnexByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByContactContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByContactContaining(String contact_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByContactContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByContactContaining(String contact_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabShortNameContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabShortNameContaining(String labShortName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabShortNameContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabShortNameContaining(String labShortName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabAnnexs
	 *
	 */
	public Set<LabAnnex> findAllLabAnnexs() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabAnnexs
	 *
	 */
	public Set<LabAnnex> findAllLabAnnexs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabNumberContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabNumberContaining(String labNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabAnnexByLabNumberContaining
	 *
	 */
	public Set<LabAnnex> findLabAnnexByLabNumberContaining(String labNumber_1, int startResult, int maxRows) throws DataAccessException;

}