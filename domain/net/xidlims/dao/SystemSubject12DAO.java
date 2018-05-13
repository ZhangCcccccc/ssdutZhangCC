package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SystemSubject12;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemSubject12 entities.
 * 
 */
public interface SystemSubject12DAO extends JpaDao<SystemSubject12> {

	/**
	 * JPQL Query - findSystemSubject12BySNumberContaining
	 *
	 */
	public Set<SystemSubject12> findSystemSubject12BySNumberContaining(String SNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySNumberContaining
	 *
	 */
	public Set<SystemSubject12> findSystemSubject12BySNumberContaining(String SNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySName
	 *
	 */
	public Set<SystemSubject12> findSystemSubject12BySName(String SName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySName
	 *
	 */
	public Set<SystemSubject12> findSystemSubject12BySName(String SName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12ByPrimaryKey
	 *
	 */
	public SystemSubject12 findSystemSubject12ByPrimaryKey(String SNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12ByPrimaryKey
	 *
	 */
	public SystemSubject12 findSystemSubject12ByPrimaryKey(String SNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemSubject12s
	 *
	 */
	public Set<SystemSubject12> findAllSystemSubject12s() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemSubject12s
	 *
	 */
	public Set<SystemSubject12> findAllSystemSubject12s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySNumber
	 *
	 */
	public SystemSubject12 findSystemSubject12BySNumber(String SNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySNumber
	 *
	 */
	public SystemSubject12 findSystemSubject12BySNumber(String SNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySNameContaining
	 *
	 */
	public Set<SystemSubject12> findSystemSubject12BySNameContaining(String SName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject12BySNameContaining
	 *
	 */
	public Set<SystemSubject12> findSystemSubject12BySNameContaining(String SName_1, int startResult, int maxRows) throws DataAccessException;

}