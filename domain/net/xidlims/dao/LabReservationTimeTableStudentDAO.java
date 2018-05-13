package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabReservationTimeTableStudent;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabReservationTimeTableStudent entities.
 * 
 */
public interface LabReservationTimeTableStudentDAO extends
		JpaDao<LabReservationTimeTableStudent> {

	/**
	 * JPQL Query - findLabReservationTimeTableStudentByPrimaryKey
	 *
	 */
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationTimeTableStudentByPrimaryKey
	 *
	 */
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservationTimeTableStudents
	 *
	 */
	public Set<LabReservationTimeTableStudent> findAllLabReservationTimeTableStudents() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservationTimeTableStudents
	 *
	 */
	public Set<LabReservationTimeTableStudent> findAllLabReservationTimeTableStudents(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationTimeTableStudentById
	 *
	 */
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationTimeTableStudentById
	 *
	 */
	public LabReservationTimeTableStudent findLabReservationTimeTableStudentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}