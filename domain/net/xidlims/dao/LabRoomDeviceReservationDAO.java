package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabRoomDeviceReservation;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceReservation entities.
 * 
 */
public interface LabRoomDeviceReservationDAO extends
		JpaDao<LabRoomDeviceReservation> {

	/**
	 * JPQL Query - findLabRoomDeviceReservationById
	 *
	 */
	public LabRoomDeviceReservation findLabRoomDeviceReservationById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationById
	 *
	 */
	public LabRoomDeviceReservation findLabRoomDeviceReservationById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByContent
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByContent
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhoneContaining
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhoneContaining(String phone) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhoneContaining
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhoneContaining(String phone, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByEndtime
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByEndtime(java.util.Calendar endtime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByEndtime
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByEndtime(Calendar endtime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByBegintime
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByBegintime(java.util.Calendar begintime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByBegintime
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByBegintime(Calendar begintime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhone
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhone(String phone_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPhone
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByPhone(String phone_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByTime
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByTime(java.util.Calendar time) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByTime
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByTime(Calendar time, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStage
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStage(String stage) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStage
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStage(String stage, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStageContaining
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStageContaining(String stage_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByStageContaining
	 *
	 */
	public Set<LabRoomDeviceReservation> findLabRoomDeviceReservationByStageContaining(String stage_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceReservations
	 *
	 */
	public Set<LabRoomDeviceReservation> findAllLabRoomDeviceReservations() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceReservations
	 *
	 */
	public Set<LabRoomDeviceReservation> findAllLabRoomDeviceReservations(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPrimaryKey
	 *
	 */
	public LabRoomDeviceReservation findLabRoomDeviceReservationByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceReservationByPrimaryKey
	 *
	 */
	public LabRoomDeviceReservation findLabRoomDeviceReservationByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}