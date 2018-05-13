package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.SchoolDeviceUse;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolDeviceUse entities.
 * 
 */
public interface SchoolDeviceUseDAO extends JpaDao<SchoolDeviceUse> {

	/**
	 * JPQL Query - findSchoolDeviceUseByPrimaryKey
	 *
	 */
	public SchoolDeviceUse findSchoolDeviceUseByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByPrimaryKey
	 *
	 */
	public SchoolDeviceUse findSchoolDeviceUseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByUseHours
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseHours(java.math.BigDecimal useHours) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByUseHours
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseHours(BigDecimal useHours, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByUseCount
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseCount(java.math.BigDecimal useCount) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByUseCount
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByUseCount(BigDecimal useCount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByTerm
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByTerm(Integer term) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByTerm
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByTerm(Integer term, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolDeviceUses
	 *
	 */
	public Set<SchoolDeviceUse> findAllSchoolDeviceUses() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolDeviceUses
	 *
	 */
	public Set<SchoolDeviceUse> findAllSchoolDeviceUses(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseById
	 *
	 */
	public SchoolDeviceUse findSchoolDeviceUseById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseById
	 *
	 */
	public SchoolDeviceUse findSchoolDeviceUseById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByPrice
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByPrice(java.math.BigDecimal price) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceUseByPrice
	 *
	 */
	public Set<SchoolDeviceUse> findSchoolDeviceUseByPrice(BigDecimal price, int startResult, int maxRows) throws DataAccessException;

}