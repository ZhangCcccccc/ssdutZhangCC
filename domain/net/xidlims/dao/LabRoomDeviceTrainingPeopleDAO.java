package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomDeviceTrainingPeople;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceTrainingPeople entities.
 * 
 */
public interface LabRoomDeviceTrainingPeopleDAO extends
		JpaDao<LabRoomDeviceTrainingPeople> {

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleByPrimaryKey
	 *
	 */
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleByPrimaryKey
	 *
	 */
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleById
	 *
	 */
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceTrainingPeopleById
	 *
	 */
	public LabRoomDeviceTrainingPeople findLabRoomDeviceTrainingPeopleById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainingPeoples
	 *
	 */
	public Set<LabRoomDeviceTrainingPeople> findAllLabRoomDeviceTrainingPeoples() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceTrainingPeoples
	 *
	 */
	public Set<LabRoomDeviceTrainingPeople> findAllLabRoomDeviceTrainingPeoples(int startResult, int maxRows) throws DataAccessException;

}