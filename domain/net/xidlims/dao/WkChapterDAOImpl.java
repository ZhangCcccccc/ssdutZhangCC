package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.WkChapter;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage WkChapter entities.
 * 
 */
@Repository("WkChapterDAO")
@Transactional
public class WkChapterDAOImpl extends AbstractJpaDao<WkChapter> implements
		WkChapterDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { WkChapter.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new WkChapterDAOImpl
	 *
	 */
	public WkChapterDAOImpl() {
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
	 * JPQL Query - findWkChapterByName
	 *
	 */
	@Transactional
	public Set<WkChapter> findWkChapterByName(String name) throws DataAccessException {

		return findWkChapterByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkChapter> findWkChapterByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkChapterByName", startResult, maxRows, name);
		return new LinkedHashSet<WkChapter>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkChapterByFileListContaining
	 *
	 */
	@Transactional
	public Set<WkChapter> findWkChapterByFileListContaining(String fileList) throws DataAccessException {

		return findWkChapterByFileListContaining(fileList, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterByFileListContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkChapter> findWkChapterByFileListContaining(String fileList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkChapterByFileListContaining", startResult, maxRows, fileList);
		return new LinkedHashSet<WkChapter>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllWkChapters
	 *
	 */
	@Transactional
	public Set<WkChapter> findAllWkChapters() throws DataAccessException {

		return findAllWkChapters(-1, -1);
	}

	/**
	 * JPQL Query - findAllWkChapters
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkChapter> findAllWkChapters(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWkChapters", startResult, maxRows);
		return new LinkedHashSet<WkChapter>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkChapterBySeq
	 *
	 */
	@Transactional
	public Set<WkChapter> findWkChapterBySeq(Integer seq) throws DataAccessException {

		return findWkChapterBySeq(seq, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterBySeq
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkChapter> findWkChapterBySeq(Integer seq, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkChapterBySeq", startResult, maxRows, seq);
		return new LinkedHashSet<WkChapter>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkChapterById
	 *
	 */
	@Transactional
	public WkChapter findWkChapterById(Integer id) throws DataAccessException {

		return findWkChapterById(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterById
	 *
	 */

	@Transactional
	public WkChapter findWkChapterById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkChapterById", startResult, maxRows, id);
			return (net.xidlims.domain.WkChapter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkChapterByNameContaining
	 *
	 */
	@Transactional
	public Set<WkChapter> findWkChapterByNameContaining(String name) throws DataAccessException {

		return findWkChapterByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkChapter> findWkChapterByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkChapterByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<WkChapter>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkChapterByFileList
	 *
	 */
	@Transactional
	public Set<WkChapter> findWkChapterByFileList(String fileList) throws DataAccessException {

		return findWkChapterByFileList(fileList, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterByFileList
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkChapter> findWkChapterByFileList(String fileList, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkChapterByFileList", startResult, maxRows, fileList);
		return new LinkedHashSet<WkChapter>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkChapterByPrimaryKey
	 *
	 */
	@Transactional
	public WkChapter findWkChapterByPrimaryKey(Integer id) throws DataAccessException {

		return findWkChapterByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkChapterByPrimaryKey
	 *
	 */

	@Transactional
	public WkChapter findWkChapterByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkChapterByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.WkChapter) query.getSingleResult();
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
	public boolean canBeMerged(WkChapter entity) {
		return true;
	}
}
