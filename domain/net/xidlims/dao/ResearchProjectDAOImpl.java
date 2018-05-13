package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.ResearchProject;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage ResearchProject entities.
 * 
 */
@Repository("ResearchProjectDAO")
@Transactional
public class ResearchProjectDAOImpl extends AbstractJpaDao<ResearchProject>
		implements ResearchProjectDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ResearchProject.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ResearchProjectDAOImpl
	 *
	 */
	public ResearchProjectDAOImpl() {
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
	 * JPQL Query - findResearchProjectByName
	 *
	 */
	@Transactional
	public Set<ResearchProject> findResearchProjectByName(String name) throws DataAccessException {

		return findResearchProjectByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findResearchProjectByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ResearchProject> findResearchProjectByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findResearchProjectByName", startResult, maxRows, name);
		return new LinkedHashSet<ResearchProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findResearchProjectByCode
	 *
	 */
	@Transactional
	public Set<ResearchProject> findResearchProjectByCode(String code) throws DataAccessException {

		return findResearchProjectByCode(code, -1, -1);
	}

	/**
	 * JPQL Query - findResearchProjectByCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ResearchProject> findResearchProjectByCode(String code, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findResearchProjectByCode", startResult, maxRows, code);
		return new LinkedHashSet<ResearchProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findResearchProjectById
	 *
	 */
	@Transactional
	public ResearchProject findResearchProjectById(Integer id) throws DataAccessException {

		return findResearchProjectById(id, -1, -1);
	}

	/**
	 * JPQL Query - findResearchProjectById
	 *
	 */

	@Transactional
	public ResearchProject findResearchProjectById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findResearchProjectById", startResult, maxRows, id);
			return (net.xidlims.domain.ResearchProject) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findResearchProjectByNameContaining
	 *
	 */
	@Transactional
	public Set<ResearchProject> findResearchProjectByNameContaining(String name) throws DataAccessException {

		return findResearchProjectByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findResearchProjectByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ResearchProject> findResearchProjectByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findResearchProjectByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<ResearchProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findResearchProjectByCodeContaining
	 *
	 */
	@Transactional
	public Set<ResearchProject> findResearchProjectByCodeContaining(String code) throws DataAccessException {

		return findResearchProjectByCodeContaining(code, -1, -1);
	}

	/**
	 * JPQL Query - findResearchProjectByCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ResearchProject> findResearchProjectByCodeContaining(String code, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findResearchProjectByCodeContaining", startResult, maxRows, code);
		return new LinkedHashSet<ResearchProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllResearchProjects
	 *
	 */
	@Transactional
	public Set<ResearchProject> findAllResearchProjects() throws DataAccessException {

		return findAllResearchProjects(-1, -1);
	}

	/**
	 * JPQL Query - findAllResearchProjects
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ResearchProject> findAllResearchProjects(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllResearchProjects", startResult, maxRows);
		return new LinkedHashSet<ResearchProject>(query.getResultList());
	}

	/**
	 * JPQL Query - findResearchProjectByPrimaryKey
	 *
	 */
	@Transactional
	public ResearchProject findResearchProjectByPrimaryKey(Integer id) throws DataAccessException {

		return findResearchProjectByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findResearchProjectByPrimaryKey
	 *
	 */

	@Transactional
	public ResearchProject findResearchProjectByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findResearchProjectByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.ResearchProject) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(ResearchProject entity) {
		return true;
	}
}
