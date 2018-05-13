package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SystemMajor08;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemMajor08 entities.
 * 
 */
public interface SystemMajor08DAO extends JpaDao<SystemMajor08> {

	/**
	 * JPQL Query - findSystemMajor08ByPrimaryKey
	 *
	 */
	public SystemMajor08 findSystemMajor08ByPrimaryKey(String SNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08ByPrimaryKey
	 *
	 */
	public SystemMajor08 findSystemMajor08ByPrimaryKey(String SNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySNumberContaining
	 *
	 */
	public Set<SystemMajor08> findSystemMajor08BySNumberContaining(String SNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySNumberContaining
	 *
	 */
	public Set<SystemMajor08> findSystemMajor08BySNumberContaining(String SNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemMajor08s
	 *
	 */
	public Set<SystemMajor08> findAllSystemMajor08s() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemMajor08s
	 *
	 */
	public Set<SystemMajor08> findAllSystemMajor08s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySName
	 *
	 */
	public Set<SystemMajor08> findSystemMajor08BySName(String SName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySName
	 *
	 */
	public Set<SystemMajor08> findSystemMajor08BySName(String SName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySNumber
	 *
	 */
	public SystemMajor08 findSystemMajor08BySNumber(String SNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySNumber
	 *
	 */
	public SystemMajor08 findSystemMajor08BySNumber(String SNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySNameContaining
	 *
	 */
	public Set<SystemMajor08> findSystemMajor08BySNameContaining(String SName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemMajor08BySNameContaining
	 *
	 */
	public Set<SystemMajor08> findSystemMajor08BySNameContaining(String SName_1, int startResult, int maxRows) throws DataAccessException;

}