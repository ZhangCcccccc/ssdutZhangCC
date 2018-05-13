package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.COperationOutlineCredit;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage COperationOutlineCredit entities.
 * 
 */
@Repository("COperationOutlineCreditDAO")
@Transactional
public class COperationOutlineCreditDAOImpl extends AbstractJpaDao<COperationOutlineCredit>
		implements COperationOutlineCreditDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { COperationOutlineCredit.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new COperationOutlineCreditDAOImpl
	 *
	 */
	public COperationOutlineCreditDAOImpl() {
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
	 * JPQL Query - findCOperationOutlineCreditByCredit
	 *
	 */
	@Transactional
	public Set<COperationOutlineCredit> findCOperationOutlineCreditByCredit(Integer credit) throws DataAccessException {

		return findCOperationOutlineCreditByCredit(credit, -1, -1);
	}

	/**
	 * JPQL Query - findCOperationOutlineCreditByCredit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<COperationOutlineCredit> findCOperationOutlineCreditByCredit(Integer credit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCOperationOutlineCreditByCredit", startResult, maxRows, credit);
		return new LinkedHashSet<COperationOutlineCredit>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCOperationOutlineCredits
	 *
	 */
	@Transactional
	public Set<COperationOutlineCredit> findAllCOperationOutlineCredits() throws DataAccessException {

		return findAllCOperationOutlineCredits(-1, -1);
	}

	/**
	 * JPQL Query - findAllCOperationOutlineCredits
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<COperationOutlineCredit> findAllCOperationOutlineCredits(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCOperationOutlineCredits", startResult, maxRows);
		return new LinkedHashSet<COperationOutlineCredit>(query.getResultList());
	}

	/**
	 * JPQL Query - findCOperationOutlineCreditById
	 *
	 */
	@Transactional
	public COperationOutlineCredit findCOperationOutlineCreditById(Integer id) throws DataAccessException {

		return findCOperationOutlineCreditById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCOperationOutlineCreditById
	 *
	 */

	@Transactional
	public COperationOutlineCredit findCOperationOutlineCreditById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCOperationOutlineCreditById", startResult, maxRows, id);
			return (net.xidlims.domain.COperationOutlineCredit) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCOperationOutlineCreditByPrimaryKey
	 *
	 */
	@Transactional
	public COperationOutlineCredit findCOperationOutlineCreditByPrimaryKey(Integer id) throws DataAccessException {

		return findCOperationOutlineCreditByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCOperationOutlineCreditByPrimaryKey
	 *
	 */

	@Transactional
	public COperationOutlineCredit findCOperationOutlineCreditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCOperationOutlineCreditByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.COperationOutlineCredit) query.getSingleResult();
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
	public boolean canBeMerged(COperationOutlineCredit entity) {
		return true;
	}
}
