package net.xidlims.dao;

import net.xidlims.domain.CmsLink;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsLink entities.
 * 
 */
public interface CmsLinkDAO extends JpaDao<CmsLink> {

	/**
	 * JPQL Query - findCmsLinkByLinkUrl
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkUrl(String linkUrl) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkUrl
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkUrl(String linkUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByCreateTime
	 *
	 */
	public Set<CmsLink> findCmsLinkByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByCreateTime
	 *
	 */
	public Set<CmsLink> findCmsLinkByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkBySort
	 *
	 */
	public Set<CmsLink> findCmsLinkBySort(Integer sort) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkBySort
	 *
	 */
	public Set<CmsLink> findCmsLinkBySort(Integer sort, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByPrimaryKey
	 *
	 */
	public CmsLink findCmsLinkByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByPrimaryKey
	 *
	 */
	public CmsLink findCmsLinkByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkNameContaining
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkNameContaining(String linkName) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkNameContaining
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkNameContaining(String linkName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkName
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkName(String linkName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkName
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkName(String linkName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByState
	 *
	 */
	public Set<CmsLink> findCmsLinkByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByState
	 *
	 */
	public Set<CmsLink> findCmsLinkByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkUrlContaining
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkUrlContaining(String linkUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByLinkUrlContaining
	 *
	 */
	public Set<CmsLink> findCmsLinkByLinkUrlContaining(String linkUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkById
	 *
	 */
	public CmsLink findCmsLinkById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkById
	 *
	 */
	public CmsLink findCmsLinkById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByProfileContaining
	 *
	 */
	public Set<CmsLink> findCmsLinkByProfileContaining(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByProfileContaining
	 *
	 */
	public Set<CmsLink> findCmsLinkByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsLinks
	 *
	 */
	public Set<CmsLink> findAllCmsLinks() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsLinks
	 *
	 */
	public Set<CmsLink> findAllCmsLinks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByProfile
	 *
	 */
	public Set<CmsLink> findCmsLinkByProfile(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsLinkByProfile
	 *
	 */
	public Set<CmsLink> findCmsLinkByProfile(String profile_1, int startResult, int maxRows) throws DataAccessException;

}