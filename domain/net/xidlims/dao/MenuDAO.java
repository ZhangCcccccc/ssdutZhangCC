package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.Menu;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Menu entities.
 * 
 */
public interface MenuDAO extends JpaDao<Menu> {

	/**
	 * JPQL Query - findMenuByMemoContaining
	 *
	 */
	public Set<Menu> findMenuByMemoContaining(String memo) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByMemoContaining
	 *
	 */
	public Set<Menu> findMenuByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMenuById
	 *
	 */
	public Menu findMenuById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findMenuById
	 *
	 */
	public Menu findMenuById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByMemo
	 *
	 */
	public Set<Menu> findMenuByMemo(String memo_1) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByMemo
	 *
	 */
	public Set<Menu> findMenuByMemo(String memo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByPrimaryKey
	 *
	 */
	public Menu findMenuByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByPrimaryKey
	 *
	 */
	public Menu findMenuByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByName
	 *
	 */
	public Set<Menu> findMenuByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByName
	 *
	 */
	public Set<Menu> findMenuByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByNameContaining
	 *
	 */
	public Set<Menu> findMenuByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findMenuByNameContaining
	 *
	 */
	public Set<Menu> findMenuByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllMenus
	 *
	 */
	public Set<Menu> findAllMenus() throws DataAccessException;

	/**
	 * JPQL Query - findAllMenus
	 *
	 */
	public Set<Menu> findAllMenus(int startResult, int maxRows) throws DataAccessException;

}