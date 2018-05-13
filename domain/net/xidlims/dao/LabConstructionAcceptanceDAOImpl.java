package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabConstructionAcceptance;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabConstructionAcceptance entities.
 * 
 */
@Repository("LabConstructionAcceptanceDAO")
@Transactional
public class LabConstructionAcceptanceDAOImpl extends AbstractJpaDao<LabConstructionAcceptance>
		implements LabConstructionAcceptanceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabConstructionAcceptance.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabConstructionAcceptanceDAOImpl
	 *
	 */
	public LabConstructionAcceptanceDAOImpl() {
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
	 * JPQL Query - findAllLabConstructionAcceptances
	 *
	 */
	@Transactional
	public Set<LabConstructionAcceptance> findAllLabConstructionAcceptances() throws DataAccessException {

		return findAllLabConstructionAcceptances(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabConstructionAcceptances
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabConstructionAcceptance> findAllLabConstructionAcceptances(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabConstructionAcceptances", startResult, maxRows);
		return new LinkedHashSet<LabConstructionAcceptance>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabConstructionAcceptanceById
	 *
	 */
	@Transactional
	public LabConstructionAcceptance findLabConstructionAcceptanceById(Integer id) throws DataAccessException {

		return findLabConstructionAcceptanceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionAcceptanceById
	 *
	 */

	@Transactional
	public LabConstructionAcceptance findLabConstructionAcceptanceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionAcceptanceById", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionAcceptance) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabConstructionAcceptanceByPrimaryKey
	 *
	 */
	@Transactional
	public LabConstructionAcceptance findLabConstructionAcceptanceByPrimaryKey(Integer id) throws DataAccessException {

		return findLabConstructionAcceptanceByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabConstructionAcceptanceByPrimaryKey
	 *
	 */

	@Transactional
	public LabConstructionAcceptance findLabConstructionAcceptanceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabConstructionAcceptanceByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabConstructionAcceptance) query.getSingleResult();
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
	public boolean canBeMerged(LabConstructionAcceptance entity) {
		return true;
	}
}
