package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.CDictionary;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CDictionary entities.
 * 
 */
public interface CDictionaryDAO extends JpaDao<CDictionary> {

	/**
	 * JPQL Query - findCDictionaryByCNameContaining
	 *
	 */
	public Set<CDictionary> findCDictionaryByCNameContaining(String CName) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCNameContaining
	 *
	 */
	public Set<CDictionary> findCDictionaryByCNameContaining(String CName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCNumberContaining
	 *
	 */
	public Set<CDictionary> findCDictionaryByCNumberContaining(String CNumber) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCNumberContaining
	 *
	 */
	public Set<CDictionary> findCDictionaryByCNumberContaining(String CNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCCategory
	 *
	 */
	public Set<CDictionary> findCDictionaryByCCategory(String CCategory) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCCategory
	 *
	 */
	public Set<CDictionary> findCDictionaryByCCategory(String CCategory, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByEnabled
	 *
	 */
	public Set<CDictionary> findCDictionaryByEnabled(Boolean enabled) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByEnabled
	 *
	 */
	public Set<CDictionary> findCDictionaryByEnabled(Boolean enabled, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByPrimaryKey
	 *
	 */
	public CDictionary findCDictionaryByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByPrimaryKey
	 *
	 */
	public CDictionary findCDictionaryByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryById
	 *
	 */
	public CDictionary findCDictionaryById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryById
	 *
	 */
	public CDictionary findCDictionaryById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCNumber
	 *
	 */
	public Set<CDictionary> findCDictionaryByCNumber(String CNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCNumber
	 *
	 */
	public Set<CDictionary> findCDictionaryByCNumber(String CNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCName
	 *
	 */
	public Set<CDictionary> findCDictionaryByCName(String CName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCName
	 *
	 */
	public Set<CDictionary> findCDictionaryByCName(String CName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCCategoryContaining
	 *
	 */
	public Set<CDictionary> findCDictionaryByCCategoryContaining(String CCategory_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDictionaryByCCategoryContaining
	 *
	 */
	public Set<CDictionary> findCDictionaryByCCategoryContaining(String CCategory_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCDictionarys
	 *
	 */
	public Set<CDictionary> findAllCDictionarys() throws DataAccessException;

	/**
	 * JPQL Query - findAllCDictionarys
	 *
	 */
	public Set<CDictionary> findAllCDictionarys(int startResult, int maxRows) throws DataAccessException;

}