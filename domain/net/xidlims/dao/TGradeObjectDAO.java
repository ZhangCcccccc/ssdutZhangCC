package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TGradeObject;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TGradeObject entities.
 * 
 */
public interface TGradeObjectDAO extends JpaDao<TGradeObject> {

	/**
	 * JPQL Query - findTGradeObjectByTitle
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByTitle(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByTitle
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByTitle(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByReleased
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByReleased(Integer released) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByReleased
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByReleased(Integer released, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByType
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByType(String type) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByType
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByType(String type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByPointsPossible
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByPointsPossible(java.math.BigDecimal pointsPossible) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByPointsPossible
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByPointsPossible(BigDecimal pointsPossible, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByDueTime
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByDueTime(java.util.Calendar dueTime) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByDueTime
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByDueTime(Calendar dueTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByPrimaryKey
	 *
	 */
	public TGradeObject findTGradeObjectByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByPrimaryKey
	 *
	 */
	public TGradeObject findTGradeObjectByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByStartTime
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByStartTime(java.util.Calendar startTime) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByStartTime
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByStartTime(Calendar startTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectById
	 *
	 */
	public TGradeObject findTGradeObjectById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectById
	 *
	 */
	public TGradeObject findTGradeObjectById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByTitleContaining
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByTitleContaining(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByTitleContaining
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByTitleContaining(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByTypeContaining
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByTypeContaining(String type_1) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeObjectByTypeContaining
	 *
	 */
	public Set<TGradeObject> findTGradeObjectByTypeContaining(String type_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTGradeObjects
	 *
	 */
	public Set<TGradeObject> findAllTGradeObjects() throws DataAccessException;

	/**
	 * JPQL Query - findAllTGradeObjects
	 *
	 */
	public Set<TGradeObject> findAllTGradeObjects(int startResult, int maxRows) throws DataAccessException;

}