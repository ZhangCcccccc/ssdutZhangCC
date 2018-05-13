package net.xidlims.dao;

import net.xidlims.domain.CmsSite;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsSite entities.
 * 
 */
public interface CmsSiteDAO extends JpaDao<CmsSite> {

	/**
	 * JPQL Query - findCmsSiteByState
	 *
	 */
	public Set<CmsSite> findCmsSiteByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByState
	 *
	 */
	public Set<CmsSite> findCmsSiteByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByCreateTime
	 *
	 */
	public Set<CmsSite> findCmsSiteByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByCreateTime
	 *
	 */
	public Set<CmsSite> findCmsSiteByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByBottomContent
	 *
	 */
	public Set<CmsSite> findCmsSiteByBottomContent(String bottomContent) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByBottomContent
	 *
	 */
	public Set<CmsSite> findCmsSiteByBottomContent(String bottomContent, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsSites
	 *
	 */
	public Set<CmsSite> findAllCmsSites() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsSites
	 *
	 */
	public Set<CmsSite> findAllCmsSites(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByProfileContaining
	 *
	 */
	public Set<CmsSite> findCmsSiteByProfileContaining(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByProfileContaining
	 *
	 */
	public Set<CmsSite> findCmsSiteByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByImageResource
	 *
	 */
	public Set<CmsSite> findCmsSiteByImageResource(Integer imageResource) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByImageResource
	 *
	 */
	public Set<CmsSite> findCmsSiteByImageResource(Integer imageResource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByPrimaryKey
	 *
	 */
	public CmsSite findCmsSiteByPrimaryKey(String siteurl) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByPrimaryKey
	 *
	 */
	public CmsSite findCmsSiteByPrimaryKey(String siteurl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByProfile
	 *
	 */
	public Set<CmsSite> findCmsSiteByProfile(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByProfile
	 *
	 */
	public Set<CmsSite> findCmsSiteByProfile(String profile_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByVideoResource
	 *
	 */
	public Set<CmsSite> findCmsSiteByVideoResource(Integer videoResource) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByVideoResource
	 *
	 */
	public Set<CmsSite> findCmsSiteByVideoResource(Integer videoResource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByNameContaining
	 *
	 */
	public Set<CmsSite> findCmsSiteByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByNameContaining
	 *
	 */
	public Set<CmsSite> findCmsSiteByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByCurrent
	 *
	 */
	public Set<CmsSite> findCmsSiteByCurrent(Integer current) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByCurrent
	 *
	 */
	public Set<CmsSite> findCmsSiteByCurrent(Integer current, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByName
	 *
	 */
	public Set<CmsSite> findCmsSiteByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteByName
	 *
	 */
	public Set<CmsSite> findCmsSiteByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteBySiteurl
	 *
	 */
	public CmsSite findCmsSiteBySiteurl(String siteurl_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteBySiteurl
	 *
	 */
	public CmsSite findCmsSiteBySiteurl(String siteurl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteBySiteurlContaining
	 *
	 */
	public Set<CmsSite> findCmsSiteBySiteurlContaining(String siteurl_2) throws DataAccessException;

	/**
	 * JPQL Query - findCmsSiteBySiteurlContaining
	 *
	 */
	public Set<CmsSite> findCmsSiteBySiteurlContaining(String siteurl_2, int startResult, int maxRows) throws DataAccessException;

}