package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentItemComponent;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentItemComponent entities.
 * 
 */
@Repository("TAssignmentItemComponentDAO")
@Transactional
public class TAssignmentItemComponentDAOImpl extends AbstractJpaDao<TAssignmentItemComponent>
		implements TAssignmentItemComponentDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentItemComponent.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentItemComponentDAOImpl
	 *
	 */
	public TAssignmentItemComponentDAOImpl() {
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
	 * JPQL Query - findTAssignmentItemComponentById
	 *
	 */
	@Transactional
	public TAssignmentItemComponent findTAssignmentItemComponentById(Integer id) throws DataAccessException {

		return findTAssignmentItemComponentById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentById
	 *
	 */

	@Transactional
	public TAssignmentItemComponent findTAssignmentItemComponentById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemComponentById", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItemComponent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemScore
	 *
	 */
	@Transactional
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemScore(java.math.BigDecimal itemScore) throws DataAccessException {

		return findTAssignmentItemComponentByItemScore(itemScore, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemScore(java.math.BigDecimal itemScore, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemComponentByItemScore", startResult, maxRows, itemScore);
		return new LinkedHashSet<TAssignmentItemComponent>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemType
	 *
	 */
	@Transactional
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemType(Integer itemType) throws DataAccessException {

		return findTAssignmentItemComponentByItemType(itemType, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemType(Integer itemType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemComponentByItemType", startResult, maxRows, itemType);
		return new LinkedHashSet<TAssignmentItemComponent>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentItemComponent findTAssignmentItemComponentByPrimaryKey(Integer id) throws DataAccessException {

		return findTAssignmentItemComponentByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentItemComponent findTAssignmentItemComponentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentItemComponentByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TAssignmentItemComponent) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemQuantity
	 *
	 */
	@Transactional
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemQuantity(Integer itemQuantity) throws DataAccessException {

		return findTAssignmentItemComponentByItemQuantity(itemQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemQuantity(Integer itemQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentItemComponentByItemQuantity", startResult, maxRows, itemQuantity);
		return new LinkedHashSet<TAssignmentItemComponent>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentItemComponents
	 *
	 */
	@Transactional
	public Set<TAssignmentItemComponent> findAllTAssignmentItemComponents() throws DataAccessException {

		return findAllTAssignmentItemComponents(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentItemComponents
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentItemComponent> findAllTAssignmentItemComponents(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentItemComponents", startResult, maxRows);
		return new LinkedHashSet<TAssignmentItemComponent>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentItemComponent entity) {
		return true;
	}
}
