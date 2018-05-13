package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SystemSubject08;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemSubject08 entities.
 * 
 */
public interface SystemSubject08DAO extends JpaDao<SystemSubject08> {

	/**
	 * JPQL Query - findAllSystemSubject08s
	 *
	 */
	public Set<SystemSubject08> findAllSystemSubject08s() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemSubject08s
	 *
	 */
	public Set<SystemSubject08> findAllSystemSubject08s(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySName
	 *
	 */
	public Set<SystemSubject08> findSystemSubject08BySName(String SName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySName
	 *
	 */
	public Set<SystemSubject08> findSystemSubject08BySName(String SName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySNumberContaining
	 *
	 */
	public Set<SystemSubject08> findSystemSubject08BySNumberContaining(String SNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySNumberContaining
	 *
	 */
	public Set<SystemSubject08> findSystemSubject08BySNumberContaining(String SNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySNameContaining
	 *
	 */
	public Set<SystemSubject08> findSystemSubject08BySNameContaining(String SName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySNameContaining
	 *
	 */
	public Set<SystemSubject08> findSystemSubject08BySNameContaining(String SName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySNumber
	 *
	 */
	public SystemSubject08 findSystemSubject08BySNumber(String SNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08BySNumber
	 *
	 */
	public SystemSubject08 findSystemSubject08BySNumber(String SNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08ByPrimaryKey
	 *
	 */
	public SystemSubject08 findSystemSubject08ByPrimaryKey(String SNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemSubject08ByPrimaryKey
	 *
	 */
	public SystemSubject08 findSystemSubject08ByPrimaryKey(String SNumber_2, int startResult, int maxRows) throws DataAccessException;

}