package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabConstructionFundingAudit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionFundingAudit entities.
 * 
 */
public interface LabConstructionFundingAuditDAO extends
		JpaDao<LabConstructionFundingAudit> {

	/**
	 * JPQL Query - findLabConstructionFundingAuditByPrimaryKey
	 *
	 */
	public LabConstructionFundingAudit findLabConstructionFundingAuditByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingAuditByPrimaryKey
	 *
	 */
	public LabConstructionFundingAudit findLabConstructionFundingAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionFundingAudits
	 *
	 */
	public Set<LabConstructionFundingAudit> findAllLabConstructionFundingAudits() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionFundingAudits
	 *
	 */
	public Set<LabConstructionFundingAudit> findAllLabConstructionFundingAudits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingAuditById
	 *
	 */
	public LabConstructionFundingAudit findLabConstructionFundingAuditById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingAuditById
	 *
	 */
	public LabConstructionFundingAudit findLabConstructionFundingAuditById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingAuditByComments
	 *
	 */
	public Set<LabConstructionFundingAudit> findLabConstructionFundingAuditByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionFundingAuditByComments
	 *
	 */
	public Set<LabConstructionFundingAudit> findLabConstructionFundingAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException;

}