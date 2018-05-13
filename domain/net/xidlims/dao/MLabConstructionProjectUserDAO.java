package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.MLabConstructionProjectUser;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage MLabConstructionProjectUser entities.
 * 
 */
public interface MLabConstructionProjectUserDAO extends
		JpaDao<MLabConstructionProjectUser> {

	/**
	 * JPQL Query - findMLabConstructionProjectUserById
	 *
	 */
	public MLabConstructionProjectUser findMLabConstructionProjectUserById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findMLabConstructionProjectUserById
	 *
	 */
	public MLabConstructionProjectUser findMLabConstructionProjectUserById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMLabConstructionProjectUserByPrimaryKey
	 *
	 */
	public MLabConstructionProjectUser findMLabConstructionProjectUserByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findMLabConstructionProjectUserByPrimaryKey
	 *
	 */
	public MLabConstructionProjectUser findMLabConstructionProjectUserByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllMLabConstructionProjectUsers
	 *
	 */
	public Set<MLabConstructionProjectUser> findAllMLabConstructionProjectUsers() throws DataAccessException;

	/**
	 * JPQL Query - findAllMLabConstructionProjectUsers
	 *
	 */
	public Set<MLabConstructionProjectUser> findAllMLabConstructionProjectUsers(int startResult, int maxRows) throws DataAccessException;

}