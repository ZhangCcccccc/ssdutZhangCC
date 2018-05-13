package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.CommonVideo;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CommonVideo entities.
 * 
 */
public interface CommonVideoDAO extends JpaDao<CommonVideo> {

	/**
	 * JPQL Query - findCommonVideoById
	 *
	 */
	public CommonVideo findCommonVideoById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoById
	 *
	 */
	public CommonVideo findCommonVideoById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByPrimaryKey
	 *
	 */
	public CommonVideo findCommonVideoByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByPrimaryKey
	 *
	 */
	public CommonVideo findCommonVideoByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoNameContaining
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoNameContaining(String videoName) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoNameContaining
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoNameContaining(String videoName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonVideos
	 *
	 */
	public Set<CommonVideo> findAllCommonVideos() throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonVideos
	 *
	 */
	public Set<CommonVideo> findAllCommonVideos(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoUrlContaining
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoUrlContaining(String videoUrl) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoUrlContaining
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoUrlContaining(String videoUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoUrl
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoUrl(String videoUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoUrl
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoUrl(String videoUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoName
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoName(String videoName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonVideoByVideoName
	 *
	 */
	public Set<CommonVideo> findCommonVideoByVideoName(String videoName_1, int startResult, int maxRows) throws DataAccessException;

}