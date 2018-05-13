package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentSection;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentSection entities.
 * 
 */
@Repository("TAssignmentSectionDAO")
@Transactional
public class TAssignmentSectionDAOImpl extends AbstractJpaDao<TAssignmentSection>
		implements TAssignmentSectionDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentSection.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentSectionDAOImpl
	 *
	 */
	public TAssignmentSectionDAOImpl() {
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
	 * JPQL Query - findTAssignmentSectionByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentSection findTAssignmentSectionByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentSectionByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentSection findTAssignmentSectionByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentSectionByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentSection) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentSectionByDescription
	 *
	 */
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByDescription(String description) throws DataAccessException {

		return findTAssignmentSectionByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentSectionByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TAssignmentSection>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentSectionByStatus
	 *
	 */
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByStatus(Integer status) throws DataAccessException {

		return findTAssignmentSectionByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentSectionByStatus", startResult, maxRows, status);
		return new LinkedHashSet<TAssignmentSection>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentSectionById
	 *
	 */
	@Transactional
	public TAssignmentSection findTAssignmentSectionById(Integer id) throws DataAccessException {

		return findTAssignmentSectionById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionById
	 *
	 */

	@Transactional
	public TAssignmentSection findTAssignmentSectionById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentSectionById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentSection) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentSectionByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByDescriptionContaining(String description) throws DataAccessException {

		return findTAssignmentSectionByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentSectionByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<TAssignmentSection>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentSections
	 *
	 */
	@Transactional
	public Set<TAssignmentSection> findAllTAssignmentSections() throws DataAccessException {

		return findAllTAssignmentSections(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentSections
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentSection> findAllTAssignmentSections(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentSections", startResult, maxRows);
		return new LinkedHashSet<TAssignmentSection>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentSectionBySequence
	 *
	 */
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionBySequence(Integer sequence) throws DataAccessException {

		return findTAssignmentSectionBySequence(sequence, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionBySequence
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentSectionBySequence", startResult, maxRows, sequence);
		return new LinkedHashSet<TAssignmentSection>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentSectionByCreatedTime
	 *
	 */
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTAssignmentSectionByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentSectionByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentSection> findTAssignmentSectionByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentSectionByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TAssignmentSection>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentSection entity) {
		return true;
	}
}
