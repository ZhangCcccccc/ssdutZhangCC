package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AppGroup;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppGroup entities.
 * 
 */
public interface AppGroupDAO extends JpaDao<AppGroup> {

	/**
	 * JPQL Query - findAppGroupById
	 *
	 */
	public AppGroup findAppGroupById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupById
	 *
	 */
	public AppGroup findAppGroupById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupByNameContaining
	 *
	 */
	public Set<AppGroup> findAppGroupByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupByNameContaining
	 *
	 */
	public Set<AppGroup> findAppGroupByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppGroups
	 *
	 */
	public Set<AppGroup> findAllAppGroups() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppGroups
	 *
	 */
	public Set<AppGroup> findAllAppGroups(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupByPrimaryKey
	 *
	 */
	public AppGroup findAppGroupByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupByPrimaryKey
	 *
	 */
	public AppGroup findAppGroupByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupByName
	 *
	 */
	public Set<AppGroup> findAppGroupByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppGroupByName
	 *
	 */
	public Set<AppGroup> findAppGroupByName(String name_1, int startResult, int maxRows) throws DataAccessException;

}