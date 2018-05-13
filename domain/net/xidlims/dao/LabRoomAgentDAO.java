package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomAgent;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomAgent entities.
 * 
 */
public interface LabRoomAgentDAO extends JpaDao<LabRoomAgent> {

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIp
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIp(String hardwareIp) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIp
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIp(String hardwareIp, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomAgents
	 *
	 */
	public Set<LabRoomAgent> findAllLabRoomAgents() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomAgents
	 *
	 */
	public Set<LabRoomAgent> findAllLabRoomAgents(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIpContaining
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIpContaining(String hardwareIp_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareIpContaining
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareIpContaining(String hardwareIp_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePortContaining
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePortContaining(String hardwarePort) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePortContaining
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePortContaining(String hardwarePort, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByPrimaryKey
	 *
	 */
	public LabRoomAgent findLabRoomAgentByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByPrimaryKey
	 *
	 */
	public LabRoomAgent findLabRoomAgentByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentById
	 *
	 */
	public LabRoomAgent findLabRoomAgentById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentById
	 *
	 */
	public LabRoomAgent findLabRoomAgentById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemark
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemark(String hardwareRemark) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemark
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemark(String hardwareRemark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemarkContaining
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemarkContaining(String hardwareRemark_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwareRemarkContaining
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwareRemarkContaining(String hardwareRemark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePort
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePort(String hardwarePort_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAgentByHardwarePort
	 *
	 */
	public Set<LabRoomAgent> findLabRoomAgentByHardwarePort(String hardwarePort_1, int startResult, int maxRows) throws DataAccessException;

}