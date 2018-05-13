package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TMessageUser;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TMessageUser entities.
 * 
 */
public interface TMessageUserDAO extends JpaDao<TMessageUser> {

	/**
	 * JPQL Query - findTMessageUserByUsername
	 *
	 */
	public Set<TMessageUser> findTMessageUserByUsername(String username) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByUsername
	 *
	 */
	public Set<TMessageUser> findTMessageUserByUsername(String username, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByCreateTime
	 *
	 */
	public Set<TMessageUser> findTMessageUserByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByCreateTime
	 *
	 */
	public Set<TMessageUser> findTMessageUserByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByCreateBy
	 *
	 */
	public Set<TMessageUser> findTMessageUserByCreateBy(String createBy) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByCreateBy
	 *
	 */
	public Set<TMessageUser> findTMessageUserByCreateBy(String createBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserById
	 *
	 */
	public TMessageUser findTMessageUserById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserById
	 *
	 */
	public TMessageUser findTMessageUserById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTMessageUsers
	 *
	 */
	public Set<TMessageUser> findAllTMessageUsers() throws DataAccessException;

	/**
	 * JPQL Query - findAllTMessageUsers
	 *
	 */
	public Set<TMessageUser> findAllTMessageUsers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByPrimaryKey
	 *
	 */
	public TMessageUser findTMessageUserByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByPrimaryKey
	 *
	 */
	public TMessageUser findTMessageUserByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByUsernameContaining
	 *
	 */
	public Set<TMessageUser> findTMessageUserByUsernameContaining(String username_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByUsernameContaining
	 *
	 */
	public Set<TMessageUser> findTMessageUserByUsernameContaining(String username_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByCreateByContaining
	 *
	 */
	public Set<TMessageUser> findTMessageUserByCreateByContaining(String createBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByCreateByContaining
	 *
	 */
	public Set<TMessageUser> findTMessageUserByCreateByContaining(String createBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByMessageId
	 *
	 */
	public Set<TMessageUser> findTMessageUserByMessageId(Integer messageId) throws DataAccessException;

	/**
	 * JPQL Query - findTMessageUserByMessageId
	 *
	 */
	public Set<TMessageUser> findTMessageUserByMessageId(Integer messageId, int startResult, int maxRows) throws DataAccessException;
	
	/**
	 * 根据message_id和username查询
	 * 作者：张佳鸣
	 * 时间：2017-10-16
	 */
	public TMessageUser findTMessageUserByMessageIdAndUsername(Integer messageId,String username);

}