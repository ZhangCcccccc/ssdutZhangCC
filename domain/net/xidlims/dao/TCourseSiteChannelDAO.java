package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TCourseSiteChannel;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSiteChannel entities.
 * 
 */
public interface TCourseSiteChannelDAO extends JpaDao<TCourseSiteChannel> {

	/**
	 * JPQL Query - findTCourseSiteChannelByLinkContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLinkContaining(String link) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByLinkContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLinkContaining(String link, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrlContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrlContaining(String imageUrl) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrlContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrlContaining(String imageUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteChannels
	 *
	 */
	public Set<TCourseSiteChannel> findAllTCourseSiteChannels() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteChannels
	 *
	 */
	public Set<TCourseSiteChannel> findAllTCourseSiteChannels(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelNameContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelNameContaining(String channelName) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelNameContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelNameContaining(String channelName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByPrimaryKey
	 *
	 */
	public TCourseSiteChannel findTCourseSiteChannelByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByPrimaryKey
	 *
	 */
	public TCourseSiteChannel findTCourseSiteChannelByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelById
	 *
	 */
	public TCourseSiteChannel findTCourseSiteChannelById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelById
	 *
	 */
	public TCourseSiteChannel findTCourseSiteChannelById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByLink
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLink(String link_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByLink
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByLink(String link_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrl
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrl(String imageUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByImageUrl
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByImageUrl(String imageUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelTextContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelTextContaining(String channelText) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelTextContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelTextContaining(String channelText, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelText
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelText(String channelText_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelText
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelText(String channelText_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelName
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelName(String channelName_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByChannelName
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByChannelName(String channelName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUser
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUser(String createUser) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUser
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUser(String createUser, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUserContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUserContaining(String createUser_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteChannelByCreateUserContaining
	 *
	 */
	public Set<TCourseSiteChannel> findTCourseSiteChannelByCreateUserContaining(String createUser_1, int startResult, int maxRows) throws DataAccessException;

}