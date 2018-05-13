package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolTermActive;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolTermActive entities.
 * 
 */
@Repository("SchoolTermActiveDAO")
@Transactional
public class SchoolTermActiveDAOImpl extends AbstractJpaDao<SchoolTermActive>
		implements SchoolTermActiveDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolTermActive.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolTermActiveDAOImpl
	 *
	 */
	public SchoolTermActiveDAOImpl() {
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
	 * JPQL Query - findSchoolTermActiveById
	 *
	 */
	@Transactional
	public SchoolTermActive findSchoolTermActiveById(Integer id) throws DataAccessException {

		return findSchoolTermActiveById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermActiveById
	 *
	 */

	@Transactional
	public SchoolTermActive findSchoolTermActiveById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolTermActiveById", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolTermActive) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllSchoolTermActives
	 *
	 */
	@Transactional
	public Set<SchoolTermActive> findAllSchoolTermActives() throws DataAccessException {

		return findAllSchoolTermActives(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolTermActives
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTermActive> findAllSchoolTermActives(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolTermActives", startResult, maxRows);
		return new LinkedHashSet<SchoolTermActive>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermActiveByActiveFinishtime
	 *
	 */
	@Transactional
	public Set<SchoolTermActive> findSchoolTermActiveByActiveFinishtime(java.util.Calendar activeFinishtime) throws DataAccessException {

		return findSchoolTermActiveByActiveFinishtime(activeFinishtime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermActiveByActiveFinishtime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTermActive> findSchoolTermActiveByActiveFinishtime(java.util.Calendar activeFinishtime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermActiveByActiveFinishtime", startResult, maxRows, activeFinishtime);
		return new LinkedHashSet<SchoolTermActive>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermActiveByActiveStarttime
	 *
	 */
	@Transactional
	public Set<SchoolTermActive> findSchoolTermActiveByActiveStarttime(java.util.Calendar activeStarttime) throws DataAccessException {

		return findSchoolTermActiveByActiveStarttime(activeStarttime, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermActiveByActiveStarttime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolTermActive> findSchoolTermActiveByActiveStarttime(java.util.Calendar activeStarttime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolTermActiveByActiveStarttime", startResult, maxRows, activeStarttime);
		return new LinkedHashSet<SchoolTermActive>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolTermActiveByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolTermActive findSchoolTermActiveByPrimaryKey(Integer id) throws DataAccessException {

		return findSchoolTermActiveByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolTermActiveByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolTermActive findSchoolTermActiveByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolTermActiveByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SchoolTermActive) query.getSingleResult();
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
	public boolean canBeMerged(SchoolTermActive entity) {
		return true;
	}
}
