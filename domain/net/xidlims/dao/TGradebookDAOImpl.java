package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TGradebook;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TGradebook entities.
 * 
 */
@Repository("TGradebookDAO")
@Transactional
public class TGradebookDAOImpl extends AbstractJpaDao<TGradebook> implements
		TGradebookDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TGradebook.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TGradebookDAOImpl
	 *
	 */
	public TGradebookDAOImpl() {
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
	 * JPQL Query - findTGradebookByTitle
	 *
	 */
	@Transactional
	public Set<TGradebook> findTGradebookByTitle(String title) throws DataAccessException {

		return findTGradebookByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findTGradebookByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradebook> findTGradebookByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradebookByTitle", startResult, maxRows, title);
		return new LinkedHashSet<TGradebook>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradebookById
	 *
	 */
	@Transactional
	public TGradebook findTGradebookById(Integer id) throws DataAccessException {

		return findTGradebookById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTGradebookById
	 *
	 */

	@Transactional
	public TGradebook findTGradebookById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTGradebookById", startResult, maxRows, id);
			return (net.xidlims.domain.TGradebook) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTGradebooks
	 *
	 */
	@Transactional
	public Set<TGradebook> findAllTGradebooks() throws DataAccessException {

		return findAllTGradebooks(-1, -1);
	}

	/**
	 * JPQL Query - findAllTGradebooks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradebook> findAllTGradebooks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTGradebooks", startResult, maxRows);
		return new LinkedHashSet<TGradebook>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradebookByTitleContaining
	 *
	 */
	@Transactional
	public Set<TGradebook> findTGradebookByTitleContaining(String title) throws DataAccessException {

		return findTGradebookByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findTGradebookByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TGradebook> findTGradebookByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTGradebookByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<TGradebook>(query.getResultList());
	}

	/**
	 * JPQL Query - findTGradebookByPrimaryKey
	 *
	 */
	@Transactional
	public TGradebook findTGradebookByPrimaryKey(Integer id) throws DataAccessException {

		return findTGradebookByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTGradebookByPrimaryKey
	 *
	 */

	@Transactional
	public TGradebook findTGradebookByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTGradebookByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TGradebook) query.getSingleResult();
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
	public boolean canBeMerged(TGradebook entity) {
		return true;
	}
}
