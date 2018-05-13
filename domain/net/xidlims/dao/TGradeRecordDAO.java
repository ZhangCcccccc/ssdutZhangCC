package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TGradeRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TGradeRecord entities.
 * 
 */
public interface TGradeRecordDAO extends JpaDao<TGradeRecord> {

	/**
	 * JPQL Query - findTGradeRecordById
	 *
	 */
	public TGradeRecord findTGradeRecordById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordById
	 *
	 */
	public TGradeRecord findTGradeRecordById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordByPoints
	 *
	 */
	public Set<TGradeRecord> findTGradeRecordByPoints(java.math.BigDecimal points) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordByPoints
	 *
	 */
	public Set<TGradeRecord> findTGradeRecordByPoints(BigDecimal points, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordByPrimaryKey
	 *
	 */
	public TGradeRecord findTGradeRecordByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordByPrimaryKey
	 *
	 */
	public TGradeRecord findTGradeRecordByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTGradeRecords
	 *
	 */
	public Set<TGradeRecord> findAllTGradeRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllTGradeRecords
	 *
	 */
	public Set<TGradeRecord> findAllTGradeRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordByRecordTime
	 *
	 */
	public Set<TGradeRecord> findTGradeRecordByRecordTime(java.util.Calendar recordTime) throws DataAccessException;

	/**
	 * JPQL Query - findTGradeRecordByRecordTime
	 *
	 */
	public Set<TGradeRecord> findTGradeRecordByRecordTime(Calendar recordTime, int startResult, int maxRows) throws DataAccessException;

}