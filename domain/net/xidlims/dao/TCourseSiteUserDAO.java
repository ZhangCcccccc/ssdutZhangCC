package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TCourseSiteUser;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSiteUser entities.
 * 
 */
public interface TCourseSiteUserDAO extends JpaDao<TCourseSiteUser> {

	/**
	 * JPQL Query - findTCourseSiteUserByPrimaryKey
	 *
	 */
	public TCourseSiteUser findTCourseSiteUserByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteUserByPrimaryKey
	 *
	 */
	public TCourseSiteUser findTCourseSiteUserByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteUserByPermission
	 *
	 */
	public Set<TCourseSiteUser> findTCourseSiteUserByPermission(Integer permission) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteUserByPermission
	 *
	 */
	public Set<TCourseSiteUser> findTCourseSiteUserByPermission(Integer permission, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteUsers
	 *
	 */
	public Set<TCourseSiteUser> findAllTCourseSiteUsers() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteUsers
	 *
	 */
	public Set<TCourseSiteUser> findAllTCourseSiteUsers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteUserById
	 *
	 */
	public TCourseSiteUser findTCourseSiteUserById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteUserById
	 *
	 */
	public TCourseSiteUser findTCourseSiteUserById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}