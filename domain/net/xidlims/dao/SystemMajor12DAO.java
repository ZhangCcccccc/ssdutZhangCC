package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SystemMajor12;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemMajor12 entities.
 * 
 */
public interface SystemMajor12DAO extends JpaDao<SystemMajor12> {

	/**
	 * JPQL Query - findAllSystemMajor12s
	 *
	 */
	public Set<SystemMajor12> findAllSystemMajor12s() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemMajor12s
	 *
	 */
	public Set<SystemMajor12> findAllSystemMajor12s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMNumberContaining
	 *
	 */
	public Set<SystemMajor12> findSystemMajor12ByMNumberContaining(String MNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMNumberContaining
	 *
	 */
	public Set<SystemMajor12> findSystemMajor12ByMNumberContaining(String MNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByPrimaryKey
	 *
	 */
	public SystemMajor12 findSystemMajor12ByPrimaryKey(String MNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByPrimaryKey
	 *
	 */
	public SystemMajor12 findSystemMajor12ByPrimaryKey(String MNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMNameContaining
	 *
	 */
	public Set<SystemMajor12> findSystemMajor12ByMNameContaining(String MName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMNameContaining
	 *
	 */
	public Set<SystemMajor12> findSystemMajor12ByMNameContaining(String MName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMNumber
	 *
	 */
	public SystemMajor12 findSystemMajor12ByMNumber(String MNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMNumber
	 *
	 */
	public SystemMajor12 findSystemMajor12ByMNumber(String MNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMName
	 *
	 */
	public Set<SystemMajor12> findSystemMajor12ByMName(String MName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor12ByMName
	 *
	 */
	public Set<SystemMajor12> findSystemMajor12ByMName(String MName_1, int startResult, int maxRows) throws DataAccessException;

}