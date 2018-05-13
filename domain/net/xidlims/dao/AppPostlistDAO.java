package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AppPostlist;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppPostlist entities.
 * 
 */
public interface AppPostlistDAO extends JpaDao<AppPostlist> {

	/**
	 * JPQL Query - findAppPostlistByTitle
	 *
	 */
	public Set<AppPostlist> findAppPostlistByTitle(String title) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByTitle
	 *
	 */
	public Set<AppPostlist> findAppPostlistByTitle(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByImagelist
	 *
	 */
	public Set<AppPostlist> findAppPostlistByImagelist(String imagelist) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByImagelist
	 *
	 */
	public Set<AppPostlist> findAppPostlistByImagelist(String imagelist, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByImagelistContaining
	 *
	 */
	public Set<AppPostlist> findAppPostlistByImagelistContaining(String imagelist_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByImagelistContaining
	 *
	 */
	public Set<AppPostlist> findAppPostlistByImagelistContaining(String imagelist_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByTitleContaining
	 *
	 */
	public Set<AppPostlist> findAppPostlistByTitleContaining(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByTitleContaining
	 *
	 */
	public Set<AppPostlist> findAppPostlistByTitleContaining(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByTime
	 *
	 */
	public Set<AppPostlist> findAppPostlistByTime(java.util.Calendar time) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByTime
	 *
	 */
	public Set<AppPostlist> findAppPostlistByTime(Calendar time, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByType
	 *
	 */
	public Set<AppPostlist> findAppPostlistByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByType
	 *
	 */
	public Set<AppPostlist> findAppPostlistByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByContentContaining
	 *
	 */
	public Set<AppPostlist> findAppPostlistByContentContaining(String content) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByContentContaining
	 *
	 */
	public Set<AppPostlist> findAppPostlistByContentContaining(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByContent
	 *
	 */
	public Set<AppPostlist> findAppPostlistByContent(String content_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByContent
	 *
	 */
	public Set<AppPostlist> findAppPostlistByContent(String content_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByIsstick
	 *
	 */
	public Set<AppPostlist> findAppPostlistByIsstick(Integer isstick) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByIsstick
	 *
	 */
	public Set<AppPostlist> findAppPostlistByIsstick(Integer isstick, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistById
	 *
	 */
	public AppPostlist findAppPostlistById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistById
	 *
	 */
	public AppPostlist findAppPostlistById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByState
	 *
	 */
	public Set<AppPostlist> findAppPostlistByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByState
	 *
	 */
	public Set<AppPostlist> findAppPostlistByState(Integer state, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByPrimaryKey
	 *
	 */
	public AppPostlist findAppPostlistByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostlistByPrimaryKey
	 *
	 */
	public AppPostlist findAppPostlistByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostlists
	 *
	 */
	public Set<AppPostlist> findAllAppPostlists() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostlists
	 *
	 */
	public Set<AppPostlist> findAllAppPostlists(int startResult, int maxRows) throws DataAccessException;

}