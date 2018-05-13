package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabConstructionProject;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionProject entities.
 * 
 */
public interface LabConstructionProjectDAO extends
		JpaDao<LabConstructionProject> {

	/**
	 * JPQL Query - findLabConstructionProjectByStage
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByStage(Integer stage) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByStage
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByStage(Integer stage, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByEmail
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByEmail(String email) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByEmail
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByEmail(String email, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectBudget
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectBudget(java.math.BigDecimal projectBudget) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectBudget
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectBudget(BigDecimal projectBudget, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumber
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumber(String projectNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumber
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumber(String projectNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByPrimaryKey
	 *
	 */
	public LabConstructionProject findLabConstructionProjectByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByPrimaryKey
	 *
	 */
	public LabConstructionProject findLabConstructionProjectByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByCreatedAt
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByCreatedAt
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumberContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumberContaining(String projectNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumberContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumberContaining(String projectNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByAuditResults
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByAuditResults(Integer auditResults) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByAuditResults
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectById
	 *
	 */
	public LabConstructionProject findLabConstructionProjectById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectById
	 *
	 */
	public LabConstructionProject findLabConstructionProjectById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNameContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNameContaining(String projectName) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNameContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNameContaining(String projectName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectAnalysis
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectAnalysis(String projectAnalysis) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectAnalysis
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectAnalysis(String projectAnalysis, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByEmailContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByEmailContaining(String email_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByEmailContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByEmailContaining(String email_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionProjects
	 *
	 */
	public Set<LabConstructionProject> findAllLabConstructionProjects() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionProjects
	 *
	 */
	public Set<LabConstructionProject> findAllLabConstructionProjects(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByTelephoneContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByTelephoneContaining(String telephone) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByTelephoneContaining
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByTelephoneContaining(String telephone, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectName
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectName(String projectName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectName
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectName(String projectName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectScheme
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectScheme(String projectScheme) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByProjectScheme
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByProjectScheme(String projectScheme, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByTelephone
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByTelephone(String telephone_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionProjectByTelephone
	 *
	 */
	public Set<LabConstructionProject> findLabConstructionProjectByTelephone(String telephone_1, int startResult, int maxRows) throws DataAccessException;

}