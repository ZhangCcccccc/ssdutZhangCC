package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabConstructionProjectAudit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionProjectAudit entities.
 * 
 */
public interface LabConstructionProjectAuditDAO extends
		JpaDao<LabConstructionProjectAudit> {

	/**
	 * JPQL Query - findLabConstructionProjectAuditByComments
	 *
	 */
	public Set<LabConstructionProjectAudit> findLabConstructionProjectAuditByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectAuditByComments
	 *
	 */
	public Set<LabConstructionProjectAudit> findLabConstructionProjectAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionProjectAudits
	 *
	 */
	public Set<LabConstructionProjectAudit> findAllLabConstructionProjectAudits() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionProjectAudits
	 *
	 */
	public Set<LabConstructionProjectAudit> findAllLabConstructionProjectAudits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectAuditByPrimaryKey
	 *
	 */
	public LabConstructionProjectAudit findLabConstructionProjectAuditByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectAuditByPrimaryKey
	 *
	 */
	public LabConstructionProjectAudit findLabConstructionProjectAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectAuditById
	 *
	 */
	public LabConstructionProjectAudit findLabConstructionProjectAuditById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectAuditById
	 *
	 */
	public LabConstructionProjectAudit findLabConstructionProjectAuditById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}