package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomDeviceReservationResult;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceReservationResult entities.
 * 
 */
public interface LabRoomDeviceReservationResultDAO extends
		JpaDao<LabRoomDeviceReservationResult> {

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByRemark
	 *
	 */
	public Set<LabRoomDeviceReservationResult> findLabRoomDeviceReservationResultByRemark(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByRemark
	 *
	 */
	public Set<LabRoomDeviceReservationResult> findLabRoomDeviceReservationResultByRemark(String remark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceReservationResults
	 *
	 */
	public Set<LabRoomDeviceReservationResult> findAllLabRoomDeviceReservationResults() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceReservationResults
	 *
	 */
	public Set<LabRoomDeviceReservationResult> findAllLabRoomDeviceReservationResults(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByPrimaryKey
	 *
	 */
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultByPrimaryKey
	 *
	 */
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultById
	 *
	 */
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationResultById
	 *
	 */
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResultById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}