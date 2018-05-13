package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CommonVideo;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CommonVideo entities.
 * 
 */
@Repository("CommonVideoDAO")
@Transactional
public class CommonVideoDAOImpl extends AbstractJpaDao<CommonVideo> implements
		CommonVideoDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CommonVideo.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CommonVideoDAOImpl
	 *
	 */
	public CommonVideoDAOImpl() {
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
	 * JPQL Query - findCommonVideoById
	 *
	 */
	@Transactional
	public CommonVideo findCommonVideoById(Integer id) throws DataAccessException {

		return findCommonVideoById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonVideoById
	 *
	 */

	@Transactional
	public CommonVideo findCommonVideoById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonVideoById", startResult, maxRows, id);
			return (net.xidlims.domain.CommonVideo) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonVideoByPrimaryKey
	 *
	 */
	@Transactional
	public CommonVideo findCommonVideoByPrimaryKey(Integer id) throws DataAccessException {

		return findCommonVideoByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonVideoByPrimaryKey
	 *
	 */

	@Transactional
	public CommonVideo findCommonVideoByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonVideoByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CommonVideo) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonVideoByVideoNameContaining
	 *
	 */
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoNameContaining(String videoName) throws DataAccessException {

		return findCommonVideoByVideoNameContaining(videoName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonVideoByVideoNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoNameContaining(String videoName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonVideoByVideoNameContaining", startResult, maxRows, videoName);
		return new LinkedHashSet<CommonVideo>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCommonVideos
	 *
	 */
	@Transactional
	public Set<CommonVideo> findAllCommonVideos() throws DataAccessException {

		return findAllCommonVideos(-1, -1);
	}

	/**
	 * JPQL Query - findAllCommonVideos
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonVideo> findAllCommonVideos(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCommonVideos", startResult, maxRows);
		return new LinkedHashSet<CommonVideo>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonVideoByVideoUrlContaining
	 *
	 */
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoUrlContaining(String videoUrl) throws DataAccessException {

		return findCommonVideoByVideoUrlContaining(videoUrl, -1, -1);
	}

	/**
	 * JPQL Query - findCommonVideoByVideoUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoUrlContaining(String videoUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonVideoByVideoUrlContaining", startResult, maxRows, videoUrl);
		return new LinkedHashSet<CommonVideo>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonVideoByVideoUrl
	 *
	 */
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoUrl(String videoUrl) throws DataAccessException {

		return findCommonVideoByVideoUrl(videoUrl, -1, -1);
	}

	/**
	 * JPQL Query - findCommonVideoByVideoUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoUrl(String videoUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonVideoByVideoUrl", startResult, maxRows, videoUrl);
		return new LinkedHashSet<CommonVideo>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonVideoByVideoName
	 *
	 */
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoName(String videoName) throws DataAccessException {

		return findCommonVideoByVideoName(videoName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonVideoByVideoName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonVideo> findCommonVideoByVideoName(String videoName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonVideoByVideoName", startResult, maxRows, videoName);
		return new LinkedHashSet<CommonVideo>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CommonVideo entity) {
		return true;
	}
}
