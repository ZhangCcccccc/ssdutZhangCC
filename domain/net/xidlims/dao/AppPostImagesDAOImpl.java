package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppPostImages;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppPostImages entities.
 * 
 */
@Repository("AppPostImagesDAO")
@Transactional
public class AppPostImagesDAOImpl extends AbstractJpaDao<AppPostImages>
		implements AppPostImagesDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppPostImages.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppPostImagesDAOImpl
	 *
	 */
	public AppPostImagesDAOImpl() {
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
	 * JPQL Query - findAppPostImagesByImageurlContaining
	 *
	 */
	@Transactional
	public Set<AppPostImages> findAppPostImagesByImageurlContaining(String imageurl) throws DataAccessException {

		return findAppPostImagesByImageurlContaining(imageurl, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostImagesByImageurlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostImages> findAppPostImagesByImageurlContaining(String imageurl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostImagesByImageurlContaining", startResult, maxRows, imageurl);
		return new LinkedHashSet<AppPostImages>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostImagesById
	 *
	 */
	@Transactional
	public AppPostImages findAppPostImagesById(Integer id) throws DataAccessException {

		return findAppPostImagesById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostImagesById
	 *
	 */

	@Transactional
	public AppPostImages findAppPostImagesById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostImagesById", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostImages) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppPostImagesByImageurl
	 *
	 */
	@Transactional
	public Set<AppPostImages> findAppPostImagesByImageurl(String imageurl) throws DataAccessException {

		return findAppPostImagesByImageurl(imageurl, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostImagesByImageurl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostImages> findAppPostImagesByImageurl(String imageurl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostImagesByImageurl", startResult, maxRows, imageurl);
		return new LinkedHashSet<AppPostImages>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostImagesByUploadTime
	 *
	 */
	@Transactional
	public Set<AppPostImages> findAppPostImagesByUploadTime(java.util.Calendar uploadTime) throws DataAccessException {

		return findAppPostImagesByUploadTime(uploadTime, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostImagesByUploadTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostImages> findAppPostImagesByUploadTime(java.util.Calendar uploadTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostImagesByUploadTime", startResult, maxRows, uploadTime);
		return new LinkedHashSet<AppPostImages>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppPostImagess
	 *
	 */
	@Transactional
	public Set<AppPostImages> findAllAppPostImagess() throws DataAccessException {

		return findAllAppPostImagess(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppPostImagess
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostImages> findAllAppPostImagess(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppPostImagess", startResult, maxRows);
		return new LinkedHashSet<AppPostImages>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppPostImagesByPrimaryKey
	 *
	 */
	@Transactional
	public AppPostImages findAppPostImagesByPrimaryKey(Integer id) throws DataAccessException {

		return findAppPostImagesByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostImagesByPrimaryKey
	 *
	 */

	@Transactional
	public AppPostImages findAppPostImagesByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostImagesByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostImages) query.getSingleResult();
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
	public boolean canBeMerged(AppPostImages entity) {
		return true;
	}
}
