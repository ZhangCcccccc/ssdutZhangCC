package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.SystemBuild;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemBuild entities.
 * 
 */
public interface SystemBuildDAO extends JpaDao<SystemBuild> {

	/**
	 * JPQL Query - findSystemBuildByBuildNameContaining
	 *
	 */
	public Set<SystemBuild> findSystemBuildByBuildNameContaining(String buildName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildNameContaining
	 *
	 */
	public Set<SystemBuild> findSystemBuildByBuildNameContaining(String buildName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemBuilds
	 *
	 */
	public Set<SystemBuild> findAllSystemBuilds() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemBuilds
	 *
	 */
	public Set<SystemBuild> findAllSystemBuilds(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildNumberContaining
	 *
	 */
	public Set<SystemBuild> findSystemBuildByBuildNumberContaining(String buildNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildNumberContaining
	 *
	 */
	public Set<SystemBuild> findSystemBuildByBuildNumberContaining(String buildNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildNumber
	 *
	 */
	public SystemBuild findSystemBuildByBuildNumber(String buildNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildNumber
	 *
	 */
	public SystemBuild findSystemBuildByBuildNumber(String buildNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByPrimaryKey
	 *
	 */
	public SystemBuild findSystemBuildByPrimaryKey(String buildNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByPrimaryKey
	 *
	 */
	public SystemBuild findSystemBuildByPrimaryKey(String buildNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildName
	 *
	 */
	public Set<SystemBuild> findSystemBuildByBuildName(String buildName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemBuildByBuildName
	 *
	 */
	public Set<SystemBuild> findSystemBuildByBuildName(String buildName_1, int startResult, int maxRows) throws DataAccessException;

}