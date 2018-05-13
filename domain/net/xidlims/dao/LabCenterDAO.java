package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabCenter;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabCenter entities.
 * 
 */
public interface LabCenterDAO extends JpaDao<LabCenter> {

	/**
	 * JPQL Query - findLabCenterByCenterAddress
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterAddress(String centerAddress) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterAddress
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterAddress(String centerAddress, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByPrimaryKey
	 *
	 */
	public LabCenter findLabCenterByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByPrimaryKey
	 *
	 */
	public LabCenter findLabCenterByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByUpdatedAt
	 *
	 */
	public Set<LabCenter> findLabCenterByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByUpdatedAt
	 *
	 */
	public Set<LabCenter> findLabCenterByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterNumber
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterNumber(String centerNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterNumber
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterNumber(String centerNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterById
	 *
	 */
	public LabCenter findLabCenterById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterById
	 *
	 */
	public LabCenter findLabCenterById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByEnabled
	 *
	 */
	public Set<LabCenter> findLabCenterByEnabled(Integer enabled) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByEnabled
	 *
	 */
	public Set<LabCenter> findLabCenterByEnabled(Integer enabled, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCreatedAt
	 *
	 */
	public Set<LabCenter> findLabCenterByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCreatedAt
	 *
	 */
	public Set<LabCenter> findLabCenterByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterAddressContaining
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterAddressContaining(String centerAddress_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterAddressContaining
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterAddressContaining(String centerAddress_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterName
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterName(String centerName) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterName
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterName(String centerName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterNameContaining
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterNameContaining(String centerName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterNameContaining
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterNameContaining(String centerName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterNumberContaining
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterNumberContaining(String centerNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabCenterByCenterNumberContaining
	 *
	 */
	public Set<LabCenter> findLabCenterByCenterNumberContaining(String centerNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabCenters
	 *
	 */
	public Set<LabCenter> findAllLabCenters() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabCenters
	 *
	 */
	public Set<LabCenter> findAllLabCenters(int startResult, int maxRows) throws DataAccessException;

}