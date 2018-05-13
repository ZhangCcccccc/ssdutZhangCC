package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SystemCampus;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemCampus entities.
 * 
 */
public interface SystemCampusDAO extends JpaDao<SystemCampus> {

	/**
	 * JPQL Query - findSystemCampusByCampusDefault
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusDefault(Boolean campusDefault) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusDefault
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusDefault(Boolean campusDefault, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtAfter
	 *
	 */
	public Set<SystemCampus> findSystemCampusByUpdatedAtAfter(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtAfter
	 *
	 */
	public Set<SystemCampus> findSystemCampusByUpdatedAtAfter(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusName
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusName(String campusName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusName
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusName(String campusName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCreatedAtBefore
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCreatedAtBefore(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCreatedAtBefore
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCreatedAtBefore(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCreatedAtAfter
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCreatedAtAfter(java.util.Calendar createdAt_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCreatedAtAfter
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCreatedAtAfter(Calendar createdAt_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByPrimaryKey
	 *
	 */
	public SystemCampus findSystemCampusByPrimaryKey(String campusNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByPrimaryKey
	 *
	 */
	public SystemCampus findSystemCampusByPrimaryKey(String campusNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusNumberContaining
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusNumberContaining(String campusNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusNumberContaining
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusNumberContaining(String campusNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCreatedAt
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCreatedAt(java.util.Calendar createdAt_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCreatedAt
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCreatedAt(Calendar createdAt_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByUpdatedAt
	 *
	 */
	public Set<SystemCampus> findSystemCampusByUpdatedAt(java.util.Calendar updatedAt_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByUpdatedAt
	 *
	 */
	public Set<SystemCampus> findSystemCampusByUpdatedAt(Calendar updatedAt_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtBefore
	 *
	 */
	public Set<SystemCampus> findSystemCampusByUpdatedAtBefore(java.util.Calendar updatedAt_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByUpdatedAtBefore
	 *
	 */
	public Set<SystemCampus> findSystemCampusByUpdatedAtBefore(Calendar updatedAt_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusNameContaining
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusNameContaining(String campusName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusNameContaining
	 *
	 */
	public Set<SystemCampus> findSystemCampusByCampusNameContaining(String campusName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemCampuss
	 *
	 */
	public Set<SystemCampus> findAllSystemCampuss() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemCampuss
	 *
	 */
	public Set<SystemCampus> findAllSystemCampuss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusNumber
	 *
	 */
	public SystemCampus findSystemCampusByCampusNumber(String campusNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemCampusByCampusNumber
	 *
	 */
	public SystemCampus findSystemCampusByCampusNumber(String campusNumber_2, int startResult, int maxRows) throws DataAccessException;

}