package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomAdmin;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomAdmin entities.
 * 
 */
@Repository("LabRoomAdminDAO")
@Transactional
public class LabRoomAdminDAOImpl extends AbstractJpaDao<LabRoomAdmin> implements
		LabRoomAdminDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomAdmin.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomAdminDAOImpl
	 *
	 */
	public LabRoomAdminDAOImpl() {
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
	 * JPQL Query - findLabRoomAdminByTypeId
	 *
	 */
	@Transactional
	public Set<LabRoomAdmin> findLabRoomAdminByTypeId(Integer typeId) throws DataAccessException {

		return findLabRoomAdminByTypeId(typeId, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAdminByTypeId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAdmin> findLabRoomAdminByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomAdminByTypeId", startResult, maxRows, typeId);
		return new LinkedHashSet<LabRoomAdmin>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomAdmins
	 *
	 */
	@Transactional
	public Set<LabRoomAdmin> findAllLabRoomAdmins() throws DataAccessException {

		return findAllLabRoomAdmins(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomAdmins
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomAdmin> findAllLabRoomAdmins(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomAdmins", startResult, maxRows);
		return new LinkedHashSet<LabRoomAdmin>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomAdminByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomAdmin findLabRoomAdminByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomAdminByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAdminByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomAdmin findLabRoomAdminByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomAdminByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomAdmin) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomAdminById
	 *
	 */
	@Transactional
	public LabRoomAdmin findLabRoomAdminById(Integer id) throws DataAccessException {

		return findLabRoomAdminById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomAdminById
	 *
	 */

	@Transactional
	public LabRoomAdmin findLabRoomAdminById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomAdminById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomAdmin) query.getSingleResult();
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
	public boolean canBeMerged(LabRoomAdmin entity) {
		return true;
	}
}
