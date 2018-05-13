package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomDeviceLendingResult;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceLendingResult entities.
 * 
 */
public interface LabRoomDeviceLendingResultDAO extends
		JpaDao<LabRoomDeviceLendingResult> {

	/**
	 * JPQL Query - findAllLabRoomDeviceLendingResults
	 *
	 */
	public Set<LabRoomDeviceLendingResult> findAllLabRoomDeviceLendingResults() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceLendingResults
	 *
	 */
	public Set<LabRoomDeviceLendingResult> findAllLabRoomDeviceLendingResults(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByPrimaryKey
	 *
	 */
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByPrimaryKey
	 *
	 */
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultById
	 *
	 */
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultById
	 *
	 */
	public LabRoomDeviceLendingResult findLabRoomDeviceLendingResultById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByRemark
	 *
	 */
	public Set<LabRoomDeviceLendingResult> findLabRoomDeviceLendingResultByRemark(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceLendingResultByRemark
	 *
	 */
	public Set<LabRoomDeviceLendingResult> findLabRoomDeviceLendingResultByRemark(String remark, int startResult, int maxRows) throws DataAccessException;

}