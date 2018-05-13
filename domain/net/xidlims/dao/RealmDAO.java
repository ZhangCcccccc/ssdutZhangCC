package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.Realm;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Realm entities.
 * 
 */
public interface RealmDAO extends JpaDao<Realm> {

	/**
	 * JPQL Query - findRealmByRealmTypeContaining
	 *
	 */
	public Set<Realm> findRealmByRealmTypeContaining(String realmType) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByRealmTypeContaining
	 *
	 */
	public Set<Realm> findRealmByRealmTypeContaining(String realmType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByRealmType
	 *
	 */
	public Set<Realm> findRealmByRealmType(String realmType_1) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByRealmType
	 *
	 */
	public Set<Realm> findRealmByRealmType(String realmType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllRealms
	 *
	 */
	public Set<Realm> findAllRealms() throws DataAccessException;

	/**
	 * JPQL Query - findAllRealms
	 *
	 */
	public Set<Realm> findAllRealms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRealmById
	 *
	 */
	public Realm findRealmById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findRealmById
	 *
	 */
	public Realm findRealmById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByPrimaryKey
	 *
	 */
	public Realm findRealmByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByPrimaryKey
	 *
	 */
	public Realm findRealmByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByNameContaining
	 *
	 */
	public Set<Realm> findRealmByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByNameContaining
	 *
	 */
	public Set<Realm> findRealmByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByName
	 *
	 */
	public Set<Realm> findRealmByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findRealmByName
	 *
	 */
	public Set<Realm> findRealmByName(String name_1, int startResult, int maxRows) throws DataAccessException;

}