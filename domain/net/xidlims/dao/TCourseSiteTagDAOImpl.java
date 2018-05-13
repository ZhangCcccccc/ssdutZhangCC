package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSiteTag;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSiteTag entities.
 * 
 */
@Repository("TCourseSiteTagDAO")
@Transactional
public class TCourseSiteTagDAOImpl extends AbstractJpaDao<TCourseSiteTag>
		implements TCourseSiteTagDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSiteTag.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteTagDAOImpl
	 *
	 */
	public TCourseSiteTagDAOImpl() {
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
	 * JPQL Query - findTCourseSiteTagByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagByDescriptionContaining(String description) throws DataAccessException {

		return findTCourseSiteTagByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteTagByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSiteTag findTCourseSiteTagByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteTagByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSiteTag findTCourseSiteTagByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteTagByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteTag) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagText
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagText(String siteTagText) throws DataAccessException {

		return findTCourseSiteTagBySiteTagText(siteTagText, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagText(String siteTagText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagBySiteTagText", startResult, maxRows, siteTagText);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagTextContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagTextContaining(String siteTagText) throws DataAccessException {

		return findTCourseSiteTagBySiteTagTextContaining(siteTagText, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagTextContaining(String siteTagText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagBySiteTagTextContaining", startResult, maxRows, siteTagText);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTCourseSiteTags
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findAllTCourseSiteTags() throws DataAccessException {

		return findAllTCourseSiteTags(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSiteTags
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findAllTCourseSiteTags(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSiteTags", startResult, maxRows);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteTagById
	 *
	 */
	@Transactional
	public TCourseSiteTag findTCourseSiteTagById(Integer id) throws DataAccessException {

		return findTCourseSiteTagById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagById
	 *
	 */

	@Transactional
	public TCourseSiteTag findTCourseSiteTagById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteTagById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteTag) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTag
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTag(String siteTag) throws DataAccessException {

		return findTCourseSiteTagBySiteTag(siteTag, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTag(String siteTag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagBySiteTag", startResult, maxRows, siteTag);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteTagByDescription
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagByDescription(String description) throws DataAccessException {

		return findTCourseSiteTagByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagContaining(String siteTag) throws DataAccessException {

		return findTCourseSiteTagBySiteTagContaining(siteTag, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagContaining(String siteTag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagBySiteTagContaining", startResult, maxRows, siteTag);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteTagByType
	 *
	 */
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagByType(Integer type) throws DataAccessException {

		return findTCourseSiteTagByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteTagByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteTag> findTCourseSiteTagByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteTagByType", startResult, maxRows, type);
		return new LinkedHashSet<TCourseSiteTag>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSiteTag entity) {
		return true;
	}
}
