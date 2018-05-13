package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppClouddickDownloadFolder;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppClouddickDownloadFolder entities.
 * 
 */
@Repository("AppClouddickDownloadFolderDAO")
@Transactional
public class AppClouddickDownloadFolderDAOImpl extends AbstractJpaDao<AppClouddickDownloadFolder>
		implements AppClouddickDownloadFolderDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppClouddickDownloadFolder.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppClouddickDownloadFolderDAOImpl
	 *
	 */
	public AppClouddickDownloadFolderDAOImpl() {
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
	 * JPQL Query - findAppClouddickDownloadFolderByPrimaryKey
	 *
	 */
	@Transactional
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderByPrimaryKey(Integer id) throws DataAccessException {

		return findAppClouddickDownloadFolderByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByPrimaryKey
	 *
	 */

	@Transactional
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppClouddickDownloadFolderByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppClouddickDownloadFolder) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocation
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocation(String location) throws DataAccessException {

		return findAppClouddickDownloadFolderByLocation(location, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocation(String location, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFolderByLocation", startResult, maxRows, location);
		return new LinkedHashSet<AppClouddickDownloadFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocationContaining
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocationContaining(String location) throws DataAccessException {

		return findAppClouddickDownloadFolderByLocationContaining(location, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByLocationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByLocationContaining(String location, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFolderByLocationContaining", startResult, maxRows, location);
		return new LinkedHashSet<AppClouddickDownloadFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppClouddickDownloadFolders
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFolder> findAllAppClouddickDownloadFolders() throws DataAccessException {

		return findAllAppClouddickDownloadFolders(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppClouddickDownloadFolders
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFolder> findAllAppClouddickDownloadFolders(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppClouddickDownloadFolders", startResult, maxRows);
		return new LinkedHashSet<AppClouddickDownloadFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderById
	 *
	 */
	@Transactional
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderById(Integer id) throws DataAccessException {

		return findAppClouddickDownloadFolderById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderById
	 *
	 */

	@Transactional
	public AppClouddickDownloadFolder findAppClouddickDownloadFolderById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppClouddickDownloadFolderById", startResult, maxRows, id);
			return (net.xidlims.domain.AppClouddickDownloadFolder) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldername
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldername(String foldername) throws DataAccessException {

		return findAppClouddickDownloadFolderByFoldername(foldername, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldername
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldername(String foldername, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFolderByFoldername", startResult, maxRows, foldername);
		return new LinkedHashSet<AppClouddickDownloadFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldernameContaining
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldernameContaining(String foldername) throws DataAccessException {

		return findAppClouddickDownloadFolderByFoldernameContaining(foldername, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFolderByFoldernameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFolder> findAppClouddickDownloadFolderByFoldernameContaining(String foldername, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFolderByFoldernameContaining", startResult, maxRows, foldername);
		return new LinkedHashSet<AppClouddickDownloadFolder>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppClouddickDownloadFolder entity) {
		return true;
	}
}
