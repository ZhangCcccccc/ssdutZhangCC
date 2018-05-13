package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomCourseCapacity;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomCourseCapacity entities.
 * 
 */
public interface LabRoomCourseCapacityDAO extends JpaDao<LabRoomCourseCapacity> {

	/**
	 * JPQL Query - findLabRoomCourseCapacityById
	 *
	 */
	public LabRoomCourseCapacity findLabRoomCourseCapacityById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomCourseCapacityById
	 *
	 */
	public LabRoomCourseCapacity findLabRoomCourseCapacityById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomCourseCapacitys
	 *
	 */
	public Set<LabRoomCourseCapacity> findAllLabRoomCourseCapacitys() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomCourseCapacitys
	 *
	 */
	public Set<LabRoomCourseCapacity> findAllLabRoomCourseCapacitys(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomCourseCapacityByCapacity
	 *
	 */
	public Set<LabRoomCourseCapacity> findLabRoomCourseCapacityByCapacity(Integer capacity) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomCourseCapacityByCapacity
	 *
	 */
	public Set<LabRoomCourseCapacity> findLabRoomCourseCapacityByCapacity(Integer capacity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomCourseCapacityByPrimaryKey
	 *
	 */
	public LabRoomCourseCapacity findLabRoomCourseCapacityByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomCourseCapacityByPrimaryKey
	 *
	 */
	public LabRoomCourseCapacity findLabRoomCourseCapacityByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}