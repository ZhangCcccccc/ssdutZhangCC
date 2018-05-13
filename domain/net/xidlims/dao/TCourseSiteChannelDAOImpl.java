package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSiteChannel;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSiteChannel entities.
 * 
 */
@Repository("TCourseSiteChannelDAO")
@Transactional
public class TCourseSiteChannelDAOImpl extends AbstractJpaDao<TCourseSiteChannel>
		implements TCourseSiteChannelDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSiteChannel.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteChannelDAOImpl
	 *
	 */
	public TCourseSiteChannelDAOImpl() {
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
	 * JPQL Query - findTCourseSiteChannelByLinkContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLinkContaining(String link) throws DataAccessException {

		return findTCourseSiteChannelByLinkContaining(link, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByLinkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLinkContaining(String link, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByLinkContaining", startResult, maxRows, link);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrlContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrlContaining(String imageUrl) throws DataAccessException {

		return findTCourseSiteChannelByImageUrlContaining(imageUrl, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrlContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrlContaining(String imageUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByImageUrlContaining", startResult, maxRows, imageUrl);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTCourseSiteChannels
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findAllTCourseSiteChannels() throws DataAccessException {

		return findAllTCourseSiteChannels(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSiteChannels
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findAllTCourseSiteChannels(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSiteChannels", startResult, maxRows);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelNameContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelNameContaining(String channelName) throws DataAccessException {

		return findTCourseSiteChannelByChannelNameContaining(channelName, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelNameContaining(String channelName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByChannelNameContaining", startResult, maxRows, channelName);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSiteChannel findTCourseSiteChannelByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteChannelByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSiteChannel findTCourseSiteChannelByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteChannelByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteChannel) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteChannelById
	 *
	 */
	@Transactional
	public TCourseSiteChannel findTCourseSiteChannelById(Integer id) throws DataAccessException {

		return findTCourseSiteChannelById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelById
	 *
	 */

	@Transactional
	public TCourseSiteChannel findTCourseSiteChannelById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteChannelById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteChannel) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByLink
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLink(String link) throws DataAccessException {

		return findTCourseSiteChannelByLink(link, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByLink
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLink(String link, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByLink", startResult, maxRows, link);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrl
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrl(String imageUrl) throws DataAccessException {

		return findTCourseSiteChannelByImageUrl(imageUrl, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrl
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrl(String imageUrl, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByImageUrl", startResult, maxRows, imageUrl);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelTextContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelTextContaining(String channelText) throws DataAccessException {

		return findTCourseSiteChannelByChannelTextContaining(channelText, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelTextContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelTextContaining(String channelText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByChannelTextContaining", startResult, maxRows, channelText);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelText
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelText(String channelText) throws DataAccessException {

		return findTCourseSiteChannelByChannelText(channelText, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelText
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelText(String channelText, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByChannelText", startResult, maxRows, channelText);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelName
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelName(String channelName) throws DataAccessException {

		return findTCourseSiteChannelByChannelName(channelName, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelName(String channelName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByChannelName", startResult, maxRows, channelName);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUser
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUser(String createUser) throws DataAccessException {

		return findTCourseSiteChannelByCreateUser(createUser, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUser
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUser(String createUser, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByCreateUser", startResult, maxRows, createUser);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUserContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUserContaining(String createUser) throws DataAccessException {

		return findTCourseSiteChannelByCreateUserContaining(createUser, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUserContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUserContaining(String createUser, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteChannelByCreateUserContaining", startResult, maxRows, createUser);
		return new LinkedHashSet<TCourseSiteChannel>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSiteChannel entity) {
		return true;
	}
}
