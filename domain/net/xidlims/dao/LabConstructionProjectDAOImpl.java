package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionProject;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionProject entities.
 * 
 */
@Repository("LabConstructionProjectDAO")
@Transactional
public class LabConstructionProjectDAOImpl extends AbstractJpaDao<LabConstructionProject>
		implements LabConstructionProjectDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionProject.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionProjectDAOImpl
	 *
	 */
	public LabConstructionProjectDAOImpl() {
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
	 * JPQL Query - findLabConstructionProjectByStage
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByStage(Integer stage) throws DataAccessException {

		return findLabConstructionProjectByStage(stage, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByStage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByStage(Integer stage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByStage", startResult, maxRows, stage);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByEmail
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByEmail(String email) throws DataAccessException {

		return findLabConstructionProjectByEmail(email, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByEmail
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByEmail(String email, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByEmail", startResult, maxRows, email);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectBudget
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectBudget(java.math.BigDecimal projectBudget) throws DataAccessException {

		return findLabConstructionProjectByProjectBudget(projectBudget, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectBudget
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectBudget(java.math.BigDecimal projectBudget, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectBudget", startResult, maxRows, projectBudget);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumber
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumber(String projectNumber) throws DataAccessException {

		return findLabConstructionProjectByProjectNumber(projectNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumber(String projectNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectNumber", startResult, maxRows, projectNumber);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionProject findLabConstructionProjectByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionProjectByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionProject findLabConstructionProjectByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionProjectByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionProject) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionProjectByCreatedAt
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findLabConstructionProjectByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumberContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumberContaining(String projectNumber) throws DataAccessException {

		return findLabConstructionProjectByProjectNumberContaining(projectNumber, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNumberContaining(String projectNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectNumberContaining", startResult, maxRows, projectNumber);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByAuditResults
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByAuditResults(Integer auditResults) throws DataAccessException {

		return findLabConstructionProjectByAuditResults(auditResults, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByAuditResults
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByAuditResults(Integer auditResults, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByAuditResults", startResult, maxRows, auditResults);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectById
	 *
	 */
	@Transactional
	public LabConstructionProject findLabConstructionProjectById(Integer id) throws DataAccessException {

		return findLabConstructionProjectById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectById
	 *
	 */

	@Transactional
	public LabConstructionProject findLabConstructionProjectById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionProjectById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionProject) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNameContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNameContaining(String projectName) throws DataAccessException {

		return findLabConstructionProjectByProjectNameContaining(projectName, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectNameContaining(String projectName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectNameContaining", startResult, maxRows, projectName);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectAnalysis
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectAnalysis(String projectAnalysis) throws DataAccessException {

		return findLabConstructionProjectByProjectAnalysis(projectAnalysis, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectAnalysis
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectAnalysis(String projectAnalysis, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectAnalysis", startResult, maxRows, projectAnalysis);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByEmailContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByEmailContaining(String email) throws DataAccessException {

		return findLabConstructionProjectByEmailContaining(email, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByEmailContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByEmailContaining(String email, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByEmailContaining", startResult, maxRows, email);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabConstructionProjects
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findAllLabConstructionProjects() throws DataAccessException {

		return findAllLabConstructionProjects(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionProjects
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findAllLabConstructionProjects(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionProjects", startResult, maxRows);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByTelephoneContaining
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByTelephoneContaining(String telephone) throws DataAccessException {

		return findLabConstructionProjectByTelephoneContaining(telephone, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByTelephoneContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByTelephoneContaining(String telephone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByTelephoneContaining", startResult, maxRows, telephone);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectName
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectName(String projectName) throws DataAccessException {

		return findLabConstructionProjectByProjectName(projectName, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectName(String projectName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectName", startResult, maxRows, projectName);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectScheme
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectScheme(String projectScheme) throws DataAccessException {

		return findLabConstructionProjectByProjectScheme(projectScheme, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByProjectScheme
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByProjectScheme(String projectScheme, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByProjectScheme", startResult, maxRows, projectScheme);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionProjectByTelephone
	 *
	 */
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByTelephone(String telephone) throws DataAccessException {

		return findLabConstructionProjectByTelephone(telephone, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionProjectByTelephone
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionProject> findLabConstructionProjectByTelephone(String telephone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabConstructionProjectByTelephone", startResult, maxRows, telephone);
		return new LinkedHashSet<LabConstructionProject>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabConstructionProject entity) {
		return true;
	}
}
