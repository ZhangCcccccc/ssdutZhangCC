package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSitePage;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSitePage entities.
 * 
 */
@Repository("TCourseSitePageDAO")
@Transactional
public class TCourseSitePageDAOImpl extends AbstractJpaDao<TCourseSitePage>
		implements TCourseSitePageDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSitePage.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSitePageDAOImpl
	 *
	 */
	public TCourseSitePageDAOImpl() {
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
	 * JPQL Query - findTCourseSitePageByPopup
	 *
	 */
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageByPopup(Integer popup) throws DataAccessException {

		return findTCourseSitePageByPopup(popup, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSitePageByPopup
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageByPopup(Integer popup, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSitePageByPopup", startResult, maxRows, popup);
		return new LinkedHashSet<TCourseSitePage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSitePageByTitle
	 *
	 */
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageByTitle(String title) throws DataAccessException {

		return findTCourseSitePageByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSitePageByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSitePageByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TCourseSitePage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSitePageBySiteOrder
	 *
	 */
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageBySiteOrder(Integer siteOrder) throws DataAccessException {

		return findTCourseSitePageBySiteOrder(siteOrder, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSitePageBySiteOrder
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageBySiteOrder(Integer siteOrder, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSitePageBySiteOrder", startResult, maxRows, siteOrder);
		return new LinkedHashSet<TCourseSitePage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSitePageByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSitePage findTCourseSitePageByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSitePageByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSitePageByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSitePage findTCourseSitePageByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSitePageByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSitePage) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSitePageByTitleContaining
	 *
	 */
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageByTitleContaining(String title) throws DataAccessException {

		return findTCourseSitePageByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSitePageByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSitePage> findTCourseSitePageByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSitePageByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TCourseSitePage>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSitePageById
	 *
	 */
	@Transactional
	public TCourseSitePage findTCourseSitePageById(Integer id) throws DataAccessException {

		return findTCourseSitePageById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSitePageById
	 *
	 */

	@Transactional
	public TCourseSitePage findTCourseSitePageById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSitePageById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSitePage) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTCourseSitePages
	 *
	 */
	@Transactional
	public Set<TCourseSitePage> findAllTCourseSitePages() throws DataAccessException {

		return findAllTCourseSitePages(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSitePages
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSitePage> findAllTCourseSitePages(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSitePages", startResult, maxRows);
		return new LinkedHashSet<TCourseSitePage>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSitePage entity) {
		return true;
	}
}
