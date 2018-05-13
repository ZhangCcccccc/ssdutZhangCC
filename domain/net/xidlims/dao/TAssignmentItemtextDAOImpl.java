package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentItemtext;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentItemtext entities.
 * 
 */
@Repository("TAssignmentItemtextDAO")
@Transactional
public class TAssignmentItemtextDAOImpl extends AbstractJpaDao<TAssignmentItemtext>
		implements TAssignmentItemtextDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentItemtext.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentItemtextDAOImpl
	 *
	 */
	public TAssignmentItemtextDAOImpl() {
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
	 * JPQL Query - findTAssignmentItemtextById
	 *
	 */
	@Transactional
	public TAssignmentItemtext findTAssignmentItemtextById(Integer id) throws DataAccessException {

		return findTAssignmentItemtextById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemtextById
	 *
	 */

	@Transactional
	public TAssignmentItemtext findTAssignmentItemtextById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemtextById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItemtext) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemtextByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentItemtext findTAssignmentItemtextByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentItemtextByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemtextByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentItemtext findTAssignmentItemtextByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemtextByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItemtext) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemtextBySequence
	 *
	 */
	@Transactional
	public Set<TAssignmentItemtext> findTAssignmentItemtextBySequence(Integer sequence) throws DataAccessException {

		return findTAssignmentItemtextBySequence(sequence, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemtextBySequence
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemtext> findTAssignmentItemtextBySequence(Integer sequence, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemtextBySequence", startResult, maxRows, sequence);
		return new LinkedHashSet<TAssignmentItemtext>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemtextByTextContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentItemtext> findTAssignmentItemtextByTextContaining(String text) throws DataAccessException {

		return findTAssignmentItemtextByTextContaining(text, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemtextByTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemtext> findTAssignmentItemtextByTextContaining(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemtextByTextContaining", startResult, maxRows, text);
		return new LinkedHashSet<TAssignmentItemtext>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemtextByText
	 *
	 */
	@Transactional
	public Set<TAssignmentItemtext> findTAssignmentItemtextByText(String text) throws DataAccessException {

		return findTAssignmentItemtextByText(text, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemtextByText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemtext> findTAssignmentItemtextByText(String text, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemtextByText", startResult, maxRows, text);
		return new LinkedHashSet<TAssignmentItemtext>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentItemtexts
	 *
	 */
	@Transactional
	public Set<TAssignmentItemtext> findAllTAssignmentItemtexts() throws DataAccessException {

		return findAllTAssignmentItemtexts(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentItemtexts
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemtext> findAllTAssignmentItemtexts(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentItemtexts", startResult, maxRows);
		return new LinkedHashSet<TAssignmentItemtext>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentItemtext entity) {
		return true;
	}
}
