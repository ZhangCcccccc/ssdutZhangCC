package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AppPostStatus;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppPostStatus entities.
 * 
 */
public interface AppPostStatusDAO extends JpaDao<AppPostStatus> {

	/**
	 * JPQL Query - findAppPostStatusById
	 *
	 */
	public AppPostStatus findAppPostStatusById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostStatusById
	 *
	 */
	public AppPostStatus findAppPostStatusById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostStatusByPrimaryKey
	 *
	 */
	public AppPostStatus findAppPostStatusByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostStatusByPrimaryKey
	 *
	 */
	public AppPostStatus findAppPostStatusByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostStatusByType
	 *
	 */
	public Set<AppPostStatus> findAppPostStatusByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findAppPostStatusByType
	 *
	 */
	public Set<AppPostStatus> findAppPostStatusByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostStatuss
	 *
	 */
	public Set<AppPostStatus> findAllAppPostStatuss() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppPostStatuss
	 *
	 */
	public Set<AppPostStatus> findAllAppPostStatuss(int startResult, int maxRows) throws DataAccessException;

}