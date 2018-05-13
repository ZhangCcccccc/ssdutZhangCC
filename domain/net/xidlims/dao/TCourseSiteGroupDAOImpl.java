package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSiteGroup;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSiteGroup entities.
 * 
 */
@Repository("TCourseSiteGroupDAO")
@Transactional
public class TCourseSiteGroupDAOImpl extends AbstractJpaDao<TCourseSiteGroup>
		implements TCourseSiteGroupDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSiteGroup.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteGroupDAOImpl
	 *
	 */
	public TCourseSiteGroupDAOImpl() {
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
	 * JPQL Query - findTCourseSiteGroupById
	 *
	 */
	@Transactional
	public TCourseSiteGroup findTCourseSiteGroupById(Integer id) throws DataAccessException {

		return findTCourseSiteGroupById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteGroupById
	 *
	 */

	@Transactional
	public TCourseSiteGroup findTCourseSiteGroupById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteGroupById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteGroup) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByDescription
	 *
	 */
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescription(String description) throws DataAccessException {

		return findTCourseSiteGroupByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteGroupByDescription", startResult, maxRows, description);
		return new LinkedHashSet<TCourseSiteGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitleContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitleContaining(String groupTitle) throws DataAccessException {

		return findTCourseSiteGroupByGroupTitleContaining(groupTitle, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitleContaining(String groupTitle, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteGroupByGroupTitleContaining", startResult, maxRows, groupTitle);
		return new LinkedHashSet<TCourseSiteGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSiteGroup findTCourseSiteGroupByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteGroupByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSiteGroup findTCourseSiteGroupByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteGroupByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteGroup) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescriptionContaining(String description) throws DataAccessException {

		return findTCourseSiteGroupByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteGroupByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<TCourseSiteGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTCourseSiteGroups
	 *
	 */
	@Transactional
	public Set<TCourseSiteGroup> findAllTCourseSiteGroups() throws DataAccessException {

		return findAllTCourseSiteGroups(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSiteGroups
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteGroup> findAllTCourseSiteGroups(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSiteGroups", startResult, maxRows);
		return new LinkedHashSet<TCourseSiteGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitle
	 *
	 */
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitle(String groupTitle) throws DataAccessException {

		return findTCourseSiteGroupByGroupTitle(groupTitle, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitle(String groupTitle, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteGroupByGroupTitle", startResult, maxRows, groupTitle);
		return new LinkedHashSet<TCourseSiteGroup>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSiteGroup entity) {
		return true;
	}
}
