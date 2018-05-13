package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppClouddickDownloadFile;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppClouddickDownloadFile entities.
 * 
 */
@Repository("AppClouddickDownloadFileDAO")
@Transactional
public class AppClouddickDownloadFileDAOImpl extends AbstractJpaDao<AppClouddickDownloadFile>
		implements AppClouddickDownloadFileDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppClouddickDownloadFile.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppClouddickDownloadFileDAOImpl
	 *
	 */
	public AppClouddickDownloadFileDAOImpl() {
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
	 * JPQL Query - findAppClouddickDownloadFileByLocation
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocation(String location) throws DataAccessException {

		return findAppClouddickDownloadFileByLocation(location, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocation
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocation(String location, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFileByLocation", startResult, maxRows, location);
		return new LinkedHashSet<AppClouddickDownloadFile>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocationContaining
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocationContaining(String location) throws DataAccessException {

		return findAppClouddickDownloadFileByLocationContaining(location, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByLocationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByLocationContaining(String location, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFileByLocationContaining", startResult, maxRows, location);
		return new LinkedHashSet<AppClouddickDownloadFile>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByPrimaryKey
	 *
	 */
	@Transactional
	public AppClouddickDownloadFile findAppClouddickDownloadFileByPrimaryKey(Integer id) throws DataAccessException {

		return findAppClouddickDownloadFileByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByPrimaryKey
	 *
	 */

	@Transactional
	public AppClouddickDownloadFile findAppClouddickDownloadFileByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppClouddickDownloadFileByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppClouddickDownloadFile) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileById
	 *
	 */
	@Transactional
	public AppClouddickDownloadFile findAppClouddickDownloadFileById(Integer id) throws DataAccessException {

		return findAppClouddickDownloadFileById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileById
	 *
	 */

	@Transactional
	public AppClouddickDownloadFile findAppClouddickDownloadFileById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppClouddickDownloadFileById", startResult, maxRows, id);
			return (net.xidlims.domain.AppClouddickDownloadFile) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAppClouddickDownloadFiles
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFile> findAllAppClouddickDownloadFiles() throws DataAccessException {

		return findAllAppClouddickDownloadFiles(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppClouddickDownloadFiles
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFile> findAllAppClouddickDownloadFiles(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppClouddickDownloadFiles", startResult, maxRows);
		return new LinkedHashSet<AppClouddickDownloadFile>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilenameContaining
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilenameContaining(String filename) throws DataAccessException {

		return findAppClouddickDownloadFileByFilenameContaining(filename, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilenameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilenameContaining(String filename, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFileByFilenameContaining", startResult, maxRows, filename);
		return new LinkedHashSet<AppClouddickDownloadFile>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilename
	 *
	 */
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilename(String filename) throws DataAccessException {

		return findAppClouddickDownloadFileByFilename(filename, -1, -1);
	}

	/**
	 * JPQL Query - findAppClouddickDownloadFileByFilename
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppClouddickDownloadFile> findAppClouddickDownloadFileByFilename(String filename, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppClouddickDownloadFileByFilename", startResult, maxRows, filename);
		return new LinkedHashSet<AppClouddickDownloadFile>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppClouddickDownloadFile entity) {
		return true;
	}
}
