package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.InnerAcademyCenter;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage InnerAcademyCenter entities.
 * 
 */
@Repository("InnerAcademyCenterDAO")
@Transactional
public class InnerAcademyCenterDAOImpl extends AbstractJpaDao<InnerAcademyCenter>
		implements InnerAcademyCenterDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { InnerAcademyCenter.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new InnerAcademyCenterDAOImpl
	 *
	 */
	public InnerAcademyCenterDAOImpl() {
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
	 * JPQL Query - findAllInnerAcademyCenters
	 *
	 */
	@Transactional
	public Set<InnerAcademyCenter> findAllInnerAcademyCenters() throws DataAccessException {

		return findAllInnerAcademyCenters(-1, -1);
	}

	/**
	 * JPQL Query - findAllInnerAcademyCenters
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<InnerAcademyCenter> findAllInnerAcademyCenters(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllInnerAcademyCenters", startResult, maxRows);
		return new LinkedHashSet<InnerAcademyCenter>(query.getResultList());
	}

	/**
	 * JPQL Query - findInnerAcademyCenterByPrimaryKey
	 *
	 */
	@Transactional
	public InnerAcademyCenter findInnerAcademyCenterByPrimaryKey(Integer id) throws DataAccessException {

		return findInnerAcademyCenterByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findInnerAcademyCenterByPrimaryKey
	 *
	 */

	@Transactional
	public InnerAcademyCenter findInnerAcademyCenterByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findInnerAcademyCenterByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.InnerAcademyCenter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findInnerAcademyCenterById
	 *
	 */
	@Transactional
	public InnerAcademyCenter findInnerAcademyCenterById(Integer id) throws DataAccessException {

		return findInnerAcademyCenterById(id, -1, -1);
	}

	/**
	 * JPQL Query - findInnerAcademyCenterById
	 *
	 */

	@Transactional
	public InnerAcademyCenter findInnerAcademyCenterById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findInnerAcademyCenterById", startResult, maxRows, id);
			return (net.xidlims.domain.InnerAcademyCenter) query.getSingleResult();
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
	public boolean canBeMerged(InnerAcademyCenter entity) {
		return true;
	}
}
