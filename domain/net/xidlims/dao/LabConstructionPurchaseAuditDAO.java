package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabConstructionPurchaseAudit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionPurchaseAudit entities.
 * 
 */
public interface LabConstructionPurchaseAuditDAO extends
		JpaDao<LabConstructionPurchaseAudit> {

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByComments
	 *
	 */
	public Set<LabConstructionPurchaseAudit> findLabConstructionPurchaseAuditByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByComments
	 *
	 */
	public Set<LabConstructionPurchaseAudit> findLabConstructionPurchaseAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByPrimaryKey
	 *
	 */
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditByPrimaryKey
	 *
	 */
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionPurchaseAudits
	 *
	 */
	public Set<LabConstructionPurchaseAudit> findAllLabConstructionPurchaseAudits() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionPurchaseAudits
	 *
	 */
	public Set<LabConstructionPurchaseAudit> findAllLabConstructionPurchaseAudits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditById
	 *
	 */
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionPurchaseAuditById
	 *
	 */
	public LabConstructionPurchaseAudit findLabConstructionPurchaseAuditById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}