package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSite;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSite entities.
 * 
 */
@Repository("TCourseSiteDAO")
@Transactional
public class TCourseSiteDAOImpl extends AbstractJpaDao<TCourseSite> implements
		TCourseSiteDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSite.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteDAOImpl
	 *
	 */
	public TCourseSiteDAOImpl() {
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
	 * JPQL Query - findTCourseSiteByCreatedTime
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByCreatedTime(java.util.Calendar createdTime) throws DataAccessException {

		return findTCourseSiteByCreatedTime(createdTime, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByCreatedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByCreatedTime(java.util.Calendar createdTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByCreatedTime", startResult, maxRows, createdTime);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByTypeContaining
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByTypeContaining(String type) throws DataAccessException {

		return findTCourseSiteByTypeContaining(type, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByTypeContaining", startResult, maxRows, type);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByTitleContaining
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByTitleContaining(String title) throws DataAccessException {

		return findTCourseSiteByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumber
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByAcademyNumber(String academyNumber) throws DataAccessException {

		return findTCourseSiteByAcademyNumber(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByAcademyNumber", startResult, maxRows, academyNumber);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByTitle
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByTitle(String title) throws DataAccessException {

		return findTCourseSiteByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteById
	 *
	 */
	@Transactional
	public TCourseSite findTCourseSiteById(Integer id) throws DataAccessException {

		return findTCourseSiteById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteById
	 *
	 */

	@Transactional
	public TCourseSite findTCourseSiteById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSite) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumberContaining
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByAcademyNumberContaining(String academyNumber) throws DataAccessException {

		return findTCourseSiteByAcademyNumberContaining(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByAcademyNumberContaining", startResult, maxRows, academyNumber);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSite findTCourseSiteByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSite findTCourseSiteByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSite) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteByDescription
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByDescription(String description) throws DataAccessException {

		return findTCourseSiteByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByType
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByType(String type) throws DataAccessException {

		return findTCourseSiteByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByType(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByType", startResult, maxRows, type);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteBySiteCode
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteBySiteCode(String siteCode) throws DataAccessException {

		return findTCourseSiteBySiteCode(siteCode, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteBySiteCode
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteBySiteCode(String siteCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteBySiteCode", startResult, maxRows, siteCode);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTCourseSites
	 *
	 */
	@Transactional
	public Set<TCourseSite> findAllTCourseSites() throws DataAccessException {

		return findAllTCourseSites(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSites
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findAllTCourseSites(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSites", startResult, maxRows);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteBySiteCodeContaining
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteBySiteCodeContaining(String siteCode) throws DataAccessException {

		return findTCourseSiteBySiteCodeContaining(siteCode, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteBySiteCodeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteBySiteCodeContaining(String siteCode, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteBySiteCodeContaining", startResult, maxRows, siteCode);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteByModifiedTime
	 *
	 */
	@Transactional
	public Set<TCourseSite> findTCourseSiteByModifiedTime(java.util.Calendar modifiedTime) throws DataAccessException {

		return findTCourseSiteByModifiedTime(modifiedTime, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteByModifiedTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSite> findTCourseSiteByModifiedTime(java.util.Calendar modifiedTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteByModifiedTime", startResult, maxRows, modifiedTime);
		return new LinkedHashSet<TCourseSite>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSite entity) {
		return true;
	}
}
