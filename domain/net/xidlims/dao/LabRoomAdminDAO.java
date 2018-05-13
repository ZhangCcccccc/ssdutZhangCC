package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomAdmin;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomAdmin entities.
 * 
 */
public interface LabRoomAdminDAO extends JpaDao<LabRoomAdmin> {

	/**
	 * JPQL Query - findLabRoomAdminByTypeId
	 *
	 */
	public Set<LabRoomAdmin> findLabRoomAdminByTypeId(Integer typeId) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAdminByTypeId
	 *
	 */
	public Set<LabRoomAdmin> findLabRoomAdminByTypeId(Integer typeId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomAdmins
	 *
	 */
	public Set<LabRoomAdmin> findAllLabRoomAdmins() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomAdmins
	 *
	 */
	public Set<LabRoomAdmin> findAllLabRoomAdmins(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAdminByPrimaryKey
	 *
	 */
	public LabRoomAdmin findLabRoomAdminByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAdminByPrimaryKey
	 *
	 */
	public LabRoomAdmin findLabRoomAdminByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAdminById
	 *
	 */
	public LabRoomAdmin findLabRoomAdminById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomAdminById
	 *
	 */
	public LabRoomAdmin findLabRoomAdminById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}