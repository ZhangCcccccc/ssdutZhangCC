package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TDiscuss;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TDiscuss entities.
 * 
 */
public interface TDiscussDAO extends JpaDao<TDiscuss> {

	/**
	 * JPQL Query - findTDiscussByDiscussTime
	 *
	 */
	public Set<TDiscuss> findTDiscussByDiscussTime(java.util.Calendar discussTime) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByDiscussTime
	 *
	 */
	public Set<TDiscuss> findTDiscussByDiscussTime(Calendar discussTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTDiscusss
	 *
	 */
	public Set<TDiscuss> findAllTDiscusss() throws DataAccessException;

	/**
	 * JPQL Query - findAllTDiscusss
	 *
	 */
	public Set<TDiscuss> findAllTDiscusss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByContent
	 *
	 */
	public Set<TDiscuss> findTDiscussByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByContent
	 *
	 */
	public Set<TDiscuss> findTDiscussByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussById
	 *
	 */
	public TDiscuss findTDiscussById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussById
	 *
	 */
	public TDiscuss findTDiscussById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByIp
	 *
	 */
	public Set<TDiscuss> findTDiscussByIp(String ip) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByIp
	 *
	 */
	public Set<TDiscuss> findTDiscussByIp(String ip, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByPrimaryKey
	 *
	 */
	public TDiscuss findTDiscussByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByPrimaryKey
	 *
	 */
	public TDiscuss findTDiscussByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByContentContaining
	 *
	 */
	public Set<TDiscuss> findTDiscussByContentContaining(String content_1) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByContentContaining
	 *
	 */
	public Set<TDiscuss> findTDiscussByContentContaining(String content_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByIpContaining
	 *
	 */
	public Set<TDiscuss> findTDiscussByIpContaining(String ip_1) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByIpContaining
	 *
	 */
	public Set<TDiscuss> findTDiscussByIpContaining(String ip_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByType
	 *
	 */
	public Set<TDiscuss> findTDiscussByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findTDiscussByType
	 *
	 */
	public Set<TDiscuss> findTDiscussByType(Integer type, int startResult, int maxRows) throws DataAccessException;

}