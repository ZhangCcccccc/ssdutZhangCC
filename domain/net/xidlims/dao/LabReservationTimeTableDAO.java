package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabReservationTimeTable;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabReservationTimeTable entities.
 * 
 */
public interface LabReservationTimeTableDAO extends
		JpaDao<LabReservationTimeTable> {

	/**
	 * JPQL Query - findLabReservationTimeTableByPrimaryKey
	 *
	 */
	public LabReservationTimeTable findLabReservationTimeTableByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationTimeTableByPrimaryKey
	 *
	 */
	public LabReservationTimeTable findLabReservationTimeTableByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservationTimeTables
	 *
	 */
	public Set<LabReservationTimeTable> findAllLabReservationTimeTables() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservationTimeTables
	 *
	 */
	public Set<LabReservationTimeTable> findAllLabReservationTimeTables(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationTimeTableById
	 *
	 */
	public LabReservationTimeTable findLabReservationTimeTableById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationTimeTableById
	 *
	 */
	public LabReservationTimeTable findLabReservationTimeTableById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}