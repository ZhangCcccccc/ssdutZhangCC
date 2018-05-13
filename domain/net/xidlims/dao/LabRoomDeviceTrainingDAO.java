package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabRoomDeviceTraining;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceTraining entities.
 * 
 */
public interface LabRoomDeviceTrainingDAO extends JpaDao<LabRoomDeviceTraining> {

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRateContaining
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRateContaining(String passRate) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRateContaining
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRateContaining(String passRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddressContaining
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddressContaining(String address) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddressContaining
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddressContaining(String address, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByJoinNumber
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByJoinNumber(Integer joinNumber) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByJoinNumber
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByJoinNumber(Integer joinNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainings
	 *
	 */
	public Set<LabRoomDeviceTraining> findAllLabRoomDeviceTrainings() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainings
	 *
	 */
	public Set<LabRoomDeviceTraining> findAllLabRoomDeviceTrainings(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddress
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddress(String address_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAddress
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAddress(String address_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRate
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRate(String passRate_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPassRate
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByPassRate(String passRate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByStatus
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByStatus(Integer status) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByStatus
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByStatus(Integer status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingById
	 *
	 */
	public LabRoomDeviceTraining findLabRoomDeviceTrainingById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingById
	 *
	 */
	public LabRoomDeviceTraining findLabRoomDeviceTrainingById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByContent
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByContent
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByNumber
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByNumber(Integer number) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByNumber
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByNumber(Integer number, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoin
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoin(String accessJoin) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoin
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoin(String accessJoin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPrimaryKey
	 *
	 */
	public LabRoomDeviceTraining findLabRoomDeviceTrainingByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByPrimaryKey
	 *
	 */
	public LabRoomDeviceTraining findLabRoomDeviceTrainingByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByTime
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByTime(java.util.Calendar time) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByTime
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByTime(Calendar time, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoinContaining
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoinContaining(String accessJoin_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingByAccessJoinContaining
	 *
	 */
	public Set<LabRoomDeviceTraining> findLabRoomDeviceTrainingByAccessJoinContaining(String accessJoin_1, int startResult, int maxRows) throws DataAccessException;

}