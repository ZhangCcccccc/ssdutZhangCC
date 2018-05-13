package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.UserCard;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage UserCard entities.
 * 
 */
public interface UserCardDAO extends JpaDao<UserCard> {

	/**
	 * JPQL Query - findUserCardByEnabledContaining
	 *
	 */
	public Set<UserCard> findUserCardByEnabledContaining(String enabled) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByEnabledContaining
	 *
	 */
	public Set<UserCard> findUserCardByEnabledContaining(String enabled, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByCardNo
	 *
	 */
	public Set<UserCard> findUserCardByCardNo(String cardNo) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByCardNo
	 *
	 */
	public Set<UserCard> findUserCardByCardNo(String cardNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllUserCards
	 *
	 */
	public Set<UserCard> findAllUserCards() throws DataAccessException;

	/**
	 * JPQL Query - findAllUserCards
	 *
	 */
	public Set<UserCard> findAllUserCards(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByCardNoContaining
	 *
	 */
	public Set<UserCard> findUserCardByCardNoContaining(String cardNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByCardNoContaining
	 *
	 */
	public Set<UserCard> findUserCardByCardNoContaining(String cardNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardById
	 *
	 */
	public UserCard findUserCardById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardById
	 *
	 */
	public UserCard findUserCardById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByPrimaryKey
	 *
	 */
	public UserCard findUserCardByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByPrimaryKey
	 *
	 */
	public UserCard findUserCardByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByEnabled
	 *
	 */
	public Set<UserCard> findUserCardByEnabled(String enabled_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserCardByEnabled
	 *
	 */
	public Set<UserCard> findUserCardByEnabled(String enabled_1, int startResult, int maxRows) throws DataAccessException;

}