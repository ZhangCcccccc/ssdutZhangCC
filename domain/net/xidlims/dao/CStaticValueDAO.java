package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.CStaticValue;

import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CStaticValue entities.
 * 
 */
public interface CStaticValueDAO extends JpaDao<CStaticValue> {

	/**
	 * JPQL Query - findCStaticValueById
	 *
	 */
	public CStaticValue findCStaticValueById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueById
	 *
	 */
	public CStaticValue findCStaticValueById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByValueNameContaining
	 *
	 */
	public Set<CStaticValue> findCStaticValueByValueNameContaining(String valueName) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByValueNameContaining
	 *
	 */
	public Set<CStaticValue> findCStaticValueByValueNameContaining(String valueName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByStaticValue
	 *
	 */
	public Set<CStaticValue> findCStaticValueByStaticValue(String staticValue) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByStaticValue
	 *
	 */
	public Set<CStaticValue> findCStaticValueByStaticValue(String staticValue, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCStaticValues
	 *
	 */
	public Set<CStaticValue> findAllCStaticValues() throws DataAccessException;

	/**
	 * JPQL Query - findAllCStaticValues
	 *
	 */
	public Set<CStaticValue> findAllCStaticValues(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByCode
	 *
	 */
	public Set<CStaticValue> findCStaticValueByCode(String code) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByCode
	 *
	 */
	public Set<CStaticValue> findCStaticValueByCode(String code, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByCodeContaining
	 *
	 */
	public Set<CStaticValue> findCStaticValueByCodeContaining(String code_1) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByCodeContaining
	 *
	 */
	public Set<CStaticValue> findCStaticValueByCodeContaining(String code_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByPrimaryKey
	 *
	 */
	public CStaticValue findCStaticValueByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByPrimaryKey
	 *
	 */
	public CStaticValue findCStaticValueByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByValueName
	 *
	 */
	public Set<CStaticValue> findCStaticValueByValueName(String valueName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByValueName
	 *
	 */
	public Set<CStaticValue> findCStaticValueByValueName(String valueName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByStaticValueContaining
	 *
	 */
	public Set<CStaticValue> findCStaticValueByStaticValueContaining(String staticValue_1) throws DataAccessException;

	/**
	 * JPQL Query - findCStaticValueByStaticValueContaining
	 *
	 */
	public Set<CStaticValue> findCStaticValueByStaticValueContaining(String staticValue_1, int startResult, int maxRows) throws DataAccessException;

}