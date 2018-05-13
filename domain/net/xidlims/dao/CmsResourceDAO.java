package net.xidlims.dao;

import net.xidlims.domain.CmsResource;

import java.util.Calendar;
import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsResource entities.
 * 
 */
public interface CmsResourceDAO extends JpaDao<CmsResource> {

	/**
	 * JPQL Query - findCmsResourceById
	 *
	 */
	public CmsResource findCmsResourceById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceById
	 *
	 */
	public CmsResource findCmsResourceById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByUrl
	 *
	 */
	public Set<CmsResource> findCmsResourceByUrl(String url) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByUrl
	 *
	 */
	public Set<CmsResource> findCmsResourceByUrl(String url, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByProfileContaining
	 *
	 */
	public Set<CmsResource> findCmsResourceByProfileContaining(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByProfileContaining
	 *
	 */
	public Set<CmsResource> findCmsResourceByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByProfile
	 *
	 */
	public Set<CmsResource> findCmsResourceByProfile(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByProfile
	 *
	 */
	public Set<CmsResource> findCmsResourceByProfile(String profile_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByCreateTime
	 *
	 */
	public Set<CmsResource> findCmsResourceByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByCreateTime
	 *
	 */
	public Set<CmsResource> findCmsResourceByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByName
	 *
	 */
	public Set<CmsResource> findCmsResourceByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByName
	 *
	 */
	public Set<CmsResource> findCmsResourceByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByNameContaining
	 *
	 */
	public Set<CmsResource> findCmsResourceByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByNameContaining
	 *
	 */
	public Set<CmsResource> findCmsResourceByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsResources
	 *
	 */
	public Set<CmsResource> findAllCmsResources() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsResources
	 *
	 */
	public Set<CmsResource> findAllCmsResources(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByUrlContaining
	 *
	 */
	public Set<CmsResource> findCmsResourceByUrlContaining(String url_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByUrlContaining
	 *
	 */
	public Set<CmsResource> findCmsResourceByUrlContaining(String url_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByPrimaryKey
	 *
	 */
	public CmsResource findCmsResourceByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByPrimaryKey
	 *
	 */
	public CmsResource findCmsResourceByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByImageVideo
	 *
	 */
	public Set<CmsResource> findCmsResourceByImageVideo(Integer imageVideo) throws DataAccessException;

	/**
	 * JPQL Query - findCmsResourceByImageVideo
	 *
	 */
	public Set<CmsResource> findCmsResourceByImageVideo(Integer imageVideo, int startResult, int maxRows) throws DataAccessException;

}