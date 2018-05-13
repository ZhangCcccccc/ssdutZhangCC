package net.xidlims.dao;

import net.xidlims.domain.TExperimentLabDevice;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExperimentLabDevice entities.
 * 
 */
public interface TExperimentLabDeviceDAO extends JpaDao<TExperimentLabDevice> {

	/**
	 * JPQL Query - findAllTExperimentLabDevices
	 *
	 */
	public Set<TExperimentLabDevice> findAllTExperimentLabDevices() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExperimentLabDevices
	 *
	 */
	public Set<TExperimentLabDevice> findAllTExperimentLabDevices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceByLabDeviceId
	 *
	 */
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByLabDeviceId(Integer labDeviceId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceByLabDeviceId
	 *
	 */
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByLabDeviceId(Integer labDeviceId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceByPrimaryKey
	 *
	 */
	public TExperimentLabDevice findTExperimentLabDeviceByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceByPrimaryKey
	 *
	 */
	public TExperimentLabDevice findTExperimentLabDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceById
	 *
	 */
	public TExperimentLabDevice findTExperimentLabDeviceById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceById
	 *
	 */
	public TExperimentLabDevice findTExperimentLabDeviceById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceByExperimentSkillId
	 *
	 */
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByExperimentSkillId(Integer experimentSkillId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentLabDeviceByExperimentSkillId
	 *
	 */
	public Set<TExperimentLabDevice> findTExperimentLabDeviceByExperimentSkillId(Integer experimentSkillId, int startResult, int maxRows) throws DataAccessException;

}