package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabConstructionFunding;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionFunding entities.
 * 
 */
public interface LabConstructionFundingDAO extends
		JpaDao<LabConstructionFunding> {

	/**
	 * JPQL Query - findLabConstructionFundingByStage
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByStage(Integer stage) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByStage
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByStage(Integer stage, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByPrimaryKey
	 *
	 */
	public LabConstructionFunding findLabConstructionFundingByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByPrimaryKey
	 *
	 */
	public LabConstructionFunding findLabConstructionFundingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumber
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumber(String fundingNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumber
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumber(String fundingNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingById
	 *
	 */
	public LabConstructionFunding findLabConstructionFundingById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingById
	 *
	 */
	public LabConstructionFunding findLabConstructionFundingById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByAuditResults
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByAuditResults(Integer auditResults) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByAuditResults
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionFundings
	 *
	 */
	public Set<LabConstructionFunding> findAllLabConstructionFundings() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionFundings
	 *
	 */
	public Set<LabConstructionFunding> findAllLabConstructionFundings(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumberContaining
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumberContaining(String fundingNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingByFundingNumberContaining
	 *
	 */
	public Set<LabConstructionFunding> findLabConstructionFundingByFundingNumberContaining(String fundingNumber_1, int startResult, int maxRows) throws DataAccessException;

}