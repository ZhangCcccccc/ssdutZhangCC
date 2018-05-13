package net.xidlims.dao;

import net.xidlims.domain.CmsChannel;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsChannel entities.
 * 
 */
public interface CmsChannelDAO extends JpaDao<CmsChannel> {

	/**
	 * JPQL Query - findCmsChannelByHyperlink
	 *
	 */
	public Set<CmsChannel> findCmsChannelByHyperlink(String hyperlink) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByHyperlink
	 *
	 */
	public Set<CmsChannel> findCmsChannelByHyperlink(String hyperlink, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByHyperlinkContaining
	 *
	 */
	public Set<CmsChannel> findCmsChannelByHyperlinkContaining(String hyperlink_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByHyperlinkContaining
	 *
	 */
	public Set<CmsChannel> findCmsChannelByHyperlinkContaining(String hyperlink_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByState
	 *
	 */
	public Set<CmsChannel> findCmsChannelByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByState
	 *
	 */
	public Set<CmsChannel> findCmsChannelByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByProfile
	 *
	 */
	public Set<CmsChannel> findCmsChannelByProfile(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByProfile
	 *
	 */
	public Set<CmsChannel> findCmsChannelByProfile(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByReadNum
	 *
	 */
	public Set<CmsChannel> findCmsChannelByReadNum(Integer readNum) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByReadNum
	 *
	 */
	public Set<CmsChannel> findCmsChannelByReadNum(Integer readNum, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByPrimaryKey
	 *
	 */
	public CmsChannel findCmsChannelByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByPrimaryKey
	 *
	 */
	public CmsChannel findCmsChannelByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByProfileContaining
	 *
	 */
	public Set<CmsChannel> findCmsChannelByProfileContaining(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByProfileContaining
	 *
	 */
	public Set<CmsChannel> findCmsChannelByProfileContaining(String profile_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelBySort
	 *
	 */
	public Set<CmsChannel> findCmsChannelBySort(Integer sort) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelBySort
	 *
	 */
	public Set<CmsChannel> findCmsChannelBySort(Integer sort, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByCreateTime
	 *
	 */
	public Set<CmsChannel> findCmsChannelByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByCreateTime
	 *
	 */
	public Set<CmsChannel> findCmsChannelByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsChannels
	 *
	 */
	public Set<CmsChannel> findAllCmsChannels() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsChannels
	 *
	 */
	public Set<CmsChannel> findAllCmsChannels(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelById
	 *
	 */
	public CmsChannel findCmsChannelById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelById
	 *
	 */
	public CmsChannel findCmsChannelById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByTitleContaining
	 *
	 */
	public Set<CmsChannel> findCmsChannelByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByTitleContaining
	 *
	 */
	public Set<CmsChannel> findCmsChannelByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByTitle
	 *
	 */
	public Set<CmsChannel> findCmsChannelByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsChannelByTitle
	 *
	 */
	public Set<CmsChannel> findCmsChannelByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

}