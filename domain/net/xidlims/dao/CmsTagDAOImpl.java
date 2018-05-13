package net.xidlims.dao;

import net.xidlims.domain.CmsTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CmsTag entities.
 * 
 */
@Repository("CmsTagDAO")
@Transactional
public class CmsTagDAOImpl extends AbstractJpaDao<CmsTag> implements CmsTagDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsTag.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsTagDAOImpl
	 *
	 */
	public CmsTagDAOImpl() {
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
	 * JPQL Query - findCmsTagByDescription
	 *
	 */
	@Transactional
	public Set<CmsTag> findCmsTagByDescription(String description) throws DataAccessException {

		return findCmsTagByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findCmsTagByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTagByDescription", startResult, maxRows, description);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCmsTags
	 *
	 */
	@Transactional
	public Set<CmsTag> findAllCmsTags() throws DataAccessException {

		return findAllCmsTags(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsTags
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findAllCmsTags(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsTags", startResult, maxRows);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTagByNameContaining
	 *
	 */
	@Transactional
	public Set<CmsTag> findCmsTagByNameContaining(String name) throws DataAccessException {

		return findCmsTagByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findCmsTagByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTagByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTagByCategoryContaining
	 *
	 */
	@Transactional
	public Set<CmsTag> findCmsTagByCategoryContaining(String category) throws DataAccessException {

		return findCmsTagByCategoryContaining(category, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByCategoryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findCmsTagByCategoryContaining(String category, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTagByCategoryContaining", startResult, maxRows, category);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTagByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<CmsTag> findCmsTagByDescriptionContaining(String description) throws DataAccessException {

		return findCmsTagByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findCmsTagByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTagByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTagByName
	 *
	 */
	@Transactional
	public Set<CmsTag> findCmsTagByName(String name) throws DataAccessException {

		return findCmsTagByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findCmsTagByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTagByName", startResult, maxRows, name);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTagByPrimaryKey
	 *
	 */
	@Transactional
	public CmsTag findCmsTagByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsTagByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByPrimaryKey
	 *
	 */

	@Transactional
	public CmsTag findCmsTagByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsTagByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsTag) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsTagById
	 *
	 */
	@Transactional
	public CmsTag findCmsTagById(Integer id) throws DataAccessException {

		return findCmsTagById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagById
	 *
	 */

	@Transactional
	public CmsTag findCmsTagById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsTagById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsTag) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsTagByCategory
	 *
	 */
	@Transactional
	public Set<CmsTag> findCmsTagByCategory(String category) throws DataAccessException {

		return findCmsTagByCategory(category, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTagByCategory
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTag> findCmsTagByCategory(String category, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTagByCategory", startResult, maxRows, category);
		return new LinkedHashSet<CmsTag>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsTag entity) {
		return true;
	}
}
