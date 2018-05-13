package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolTermActive;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolTermActive entities.
 * 
 */
public interface SchoolTermActiveDAO extends JpaDao<SchoolTermActive> {

	/**
	 * JPQL Query - findSchoolTermActiveById
	 *
	 */
	public SchoolTermActive findSchoolTermActiveById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveById
	 *
	 */
	public SchoolTermActive findSchoolTermActiveById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolTermActives
	 *
	 */
	public Set<SchoolTermActive> findAllSchoolTermActives() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolTermActives
	 *
	 */
	public Set<SchoolTermActive> findAllSchoolTermActives(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveByActiveFinishtime
	 *
	 */
	public Set<SchoolTermActive> findSchoolTermActiveByActiveFinishtime(java.util.Calendar activeFinishtime) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveByActiveFinishtime
	 *
	 */
	public Set<SchoolTermActive> findSchoolTermActiveByActiveFinishtime(Calendar activeFinishtime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveByActiveStarttime
	 *
	 */
	public Set<SchoolTermActive> findSchoolTermActiveByActiveStarttime(java.util.Calendar activeStarttime) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveByActiveStarttime
	 *
	 */
	public Set<SchoolTermActive> findSchoolTermActiveByActiveStarttime(Calendar activeStarttime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveByPrimaryKey
	 *
	 */
	public SchoolTermActive findSchoolTermActiveByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolTermActiveByPrimaryKey
	 *
	 */
	public SchoolTermActive findSchoolTermActiveByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}