package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.Authority;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Authority entities.
 * 
 */
public interface AuthorityDAO extends JpaDao<Authority> {

	/**
	 * JPQL Query - findAuthorityByType
	 *
	 */
	public Set<Authority> findAuthorityByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByType
	 *
	 */
	public Set<Authority> findAuthorityByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByPrimaryKey
	 *
	 */
	public Authority findAuthorityByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByPrimaryKey
	 *
	 */
	public Authority findAuthorityByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAuthoritys
	 *
	 */
	public Set<Authority> findAllAuthoritys() throws DataAccessException;

	/**
	 * JPQL Query - findAllAuthoritys
	 *
	 */
	public Set<Authority> findAllAuthoritys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByCnameContaining
	 *
	 */
	public Set<Authority> findAuthorityByCnameContaining(String cname) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByCnameContaining
	 *
	 */
	public Set<Authority> findAuthorityByCnameContaining(String cname, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByAuthorityNameContaining
	 *
	 */
	public Set<Authority> findAuthorityByAuthorityNameContaining(String authorityName) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByAuthorityNameContaining
	 *
	 */
	public Set<Authority> findAuthorityByAuthorityNameContaining(String authorityName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityById
	 *
	 */
	public Authority findAuthorityById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityById
	 *
	 */
	public Authority findAuthorityById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByCname
	 *
	 */
	public Set<Authority> findAuthorityByCname(String cname_1) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByCname
	 *
	 */
	public Set<Authority> findAuthorityByCname(String cname_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByAuthorityName
	 *
	 */
	public Set<Authority> findAuthorityByAuthorityName(String authorityName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAuthorityByAuthorityName
	 *
	 */
	public Set<Authority> findAuthorityByAuthorityName(String authorityName_1, int startResult, int maxRows) throws DataAccessException;

}