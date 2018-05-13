package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TMistakeItem;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TMistakeItem entities.
 * 
 */
@Repository("TMistakeItemDAO")
@Transactional
public class TMistakeItemDAOImpl extends AbstractJpaDao<TMistakeItem> implements
		TMistakeItemDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TMistakeItem.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TMistakeItemDAOImpl
	 *
	 */
	public TMistakeItemDAOImpl() {
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
	 * JPQL Query - findAllTMistakeItems
	 *
	 */
	@Transactional
	public Set<TMistakeItem> findAllTMistakeItems() throws DataAccessException {

		return findAllTMistakeItems(-1, -1);
	}

	/**
	 * JPQL Query - findAllTMistakeItems
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMistakeItem> findAllTMistakeItems(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTMistakeItems", startResult, maxRows);
		return new LinkedHashSet<TMistakeItem>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMistakeItemById
	 *
	 */
	@Transactional
	public TMistakeItem findTMistakeItemById(Integer id) throws DataAccessException {

		return findTMistakeItemById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMistakeItemById
	 *
	 */

	@Transactional
	public TMistakeItem findTMistakeItemById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMistakeItemById", startResult, maxRows, id);
			return (net.xidlims.domain.TMistakeItem) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMistakeItemByPrimaryKey
	 *
	 */
	@Transactional
	public TMistakeItem findTMistakeItemByPrimaryKey(Integer id) throws DataAccessException {

		return findTMistakeItemByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMistakeItemByPrimaryKey
	 *
	 */

	@Transactional
	public TMistakeItem findTMistakeItemByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMistakeItemByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TMistakeItem) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMistakeItemByErrorCount
	 *
	 */
	@Transactional
	public Set<TMistakeItem> findTMistakeItemByErrorCount(Integer errorCount) throws DataAccessException {

		return findTMistakeItemByErrorCount(errorCount, -1, -1);
	}

	/**
	 * JPQL Query - findTMistakeItemByErrorCount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMistakeItem> findTMistakeItemByErrorCount(Integer errorCount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMistakeItemByErrorCount", startResult, maxRows, errorCount);
		return new LinkedHashSet<TMistakeItem>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TMistakeItem entity) {
		return true;
	}
}
