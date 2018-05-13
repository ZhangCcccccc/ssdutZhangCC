package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomFurniture;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomFurniture entities.
 * 
 */
@Repository("LabRoomFurnitureDAO")
@Transactional
public class LabRoomFurnitureDAOImpl extends AbstractJpaDao<LabRoomFurniture>
		implements LabRoomFurnitureDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomFurniture.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomFurnitureDAOImpl
	 *
	 */
	public LabRoomFurnitureDAOImpl() {
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
	 * JPQL Query - findLabRoomFurnitureByFurnitureNoContaining
	 *
	 */
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNoContaining(String furnitureNo) throws DataAccessException {

		return findLabRoomFurnitureByFurnitureNoContaining(furnitureNo, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNoContaining(String furnitureNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomFurnitureByFurnitureNoContaining", startResult, maxRows, furnitureNo);
		return new LinkedHashSet<LabRoomFurniture>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNameContaining
	 *
	 */
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNameContaining(String furnitureName) throws DataAccessException {

		return findLabRoomFurnitureByFurnitureNameContaining(furnitureName, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNameContaining(String furnitureName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomFurnitureByFurnitureNameContaining", startResult, maxRows, furnitureName);
		return new LinkedHashSet<LabRoomFurniture>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNo
	 *
	 */
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNo(String furnitureNo) throws DataAccessException {

		return findLabRoomFurnitureByFurnitureNo(furnitureNo, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNo(String furnitureNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomFurnitureByFurnitureNo", startResult, maxRows, furnitureNo);
		return new LinkedHashSet<LabRoomFurniture>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomFurniture findLabRoomFurnitureByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomFurnitureByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomFurniture findLabRoomFurnitureByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomFurnitureByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomFurniture) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllLabRoomFurnitures
	 *
	 */
	@Transactional
	public Set<LabRoomFurniture> findAllLabRoomFurnitures() throws DataAccessException {

		return findAllLabRoomFurnitures(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomFurnitures
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomFurniture> findAllLabRoomFurnitures(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomFurnitures", startResult, maxRows);
		return new LinkedHashSet<LabRoomFurniture>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureName
	 *
	 */
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureName(String furnitureName) throws DataAccessException {

		return findLabRoomFurnitureByFurnitureName(furnitureName, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureName(String furnitureName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomFurnitureByFurnitureName", startResult, maxRows, furnitureName);
		return new LinkedHashSet<LabRoomFurniture>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomFurnitureById
	 *
	 */
	@Transactional
	public LabRoomFurniture findLabRoomFurnitureById(Integer id) throws DataAccessException {

		return findLabRoomFurnitureById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomFurnitureById
	 *
	 */

	@Transactional
	public LabRoomFurniture findLabRoomFurnitureById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomFurnitureById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomFurniture) query.getSingleResult();
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
	public boolean canBeMerged(LabRoomFurniture entity) {
		return true;
	}
}
