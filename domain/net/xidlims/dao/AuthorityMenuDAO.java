package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AuthorityMenu;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AuthorityMenu entities.
 * 
 */
public interface AuthorityMenuDAO extends JpaDao<AuthorityMenu> {

	/**
	 * JPQL Query - findAllAuthorityMenus
	 *
	 */
	public Set<AuthorityMenu> findAllAuthorityMenus() throws DataAccessException;

	/**
	 * JPQL Query - findAllAuthorityMenus
	 *
	 */
	public Set<AuthorityMenu> findAllAuthorityMenus(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityMenuById
	 *
	 */
	public AuthorityMenu findAuthorityMenuById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityMenuById
	 *
	 */
	public AuthorityMenu findAuthorityMenuById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityMenuByPrimaryKey
	 *
	 */
	public AuthorityMenu findAuthorityMenuByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityMenuByPrimaryKey
	 *
	 */
	public AuthorityMenu findAuthorityMenuByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}