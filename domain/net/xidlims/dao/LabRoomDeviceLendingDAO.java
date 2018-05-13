package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabRoomDeviceLending;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceLending entities.
 * 
 */
public interface LabRoomDeviceLendingDAO extends JpaDao<LabRoomDeviceLending> {

	/**
	 * JPQL Query - findAllLabRoomDeviceLendings
	 *
	 */
	public Set<LabRoomDeviceLending> findAllLabRoomDeviceLendings() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceLendings
	 *
	 */
	public Set<LabRoomDeviceLending> findAllLabRoomDeviceLendings(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByBackTime
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByBackTime(java.util.Calendar backTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByBackTime
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByBackTime(Calendar backTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingById
	 *
	 */
	public LabRoomDeviceLending findLabRoomDeviceLendingById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingById
	 *
	 */
	public LabRoomDeviceLending findLabRoomDeviceLendingById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByContent
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByContent
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByPrimaryKey
	 *
	 */
	public LabRoomDeviceLending findLabRoomDeviceLendingByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByPrimaryKey
	 *
	 */
	public LabRoomDeviceLending findLabRoomDeviceLendingByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByLendingTime
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByLendingTime(java.util.Calendar lendingTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByLendingTime
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByLendingTime(Calendar lendingTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByReturnTime
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByReturnTime(java.util.Calendar returnTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingByReturnTime
	 *
	 */
	public Set<LabRoomDeviceLending> findLabRoomDeviceLendingByReturnTime(Calendar returnTime, int startResult, int maxRows) throws DataAccessException;

}