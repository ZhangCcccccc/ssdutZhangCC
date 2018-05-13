package net.xidlims.dao;

import net.xidlims.domain.TExperimentLabRoom;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExperimentLabRoom entities.
 * 
 */
public interface TExperimentLabRoomDAO extends JpaDao<TExperimentLabRoom> {

	/**
	 * JPQL Query - findTExperimentLabRoomByExperimentSkillId
	 *
	 */
	public Set<TExperimentLabRoom> findTExperimentLabRoomByExperimentSkillId(Integer experimentSkillId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomByExperimentSkillId
	 *
	 */
	public Set<TExperimentLabRoom> findTExperimentLabRoomByExperimentSkillId(Integer experimentSkillId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomByPrimaryKey
	 *
	 */
	public TExperimentLabRoom findTExperimentLabRoomByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomByPrimaryKey
	 *
	 */
	public TExperimentLabRoom findTExperimentLabRoomByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTExperimentLabRooms
	 *
	 */
	public Set<TExperimentLabRoom> findAllTExperimentLabRooms() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExperimentLabRooms
	 *
	 */
	public Set<TExperimentLabRoom> findAllTExperimentLabRooms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomByLabRoomId
	 *
	 */
	public Set<TExperimentLabRoom> findTExperimentLabRoomByLabRoomId(Integer labRoomId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomByLabRoomId
	 *
	 */
	public Set<TExperimentLabRoom> findTExperimentLabRoomByLabRoomId(Integer labRoomId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomById
	 *
	 */
	public TExperimentLabRoom findTExperimentLabRoomById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabRoomById
	 *
	 */
	public TExperimentLabRoom findTExperimentLabRoomById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}