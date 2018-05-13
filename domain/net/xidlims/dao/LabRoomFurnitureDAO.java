package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomFurniture;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomFurniture entities.
 * 
 */
public interface LabRoomFurnitureDAO extends JpaDao<LabRoomFurniture> {

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNoContaining
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNoContaining(String furnitureNo) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNoContaining
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNoContaining(String furnitureNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNameContaining
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNameContaining(String furnitureName) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNameContaining
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNameContaining(String furnitureName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNo
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNo(String furnitureNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureNo
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureNo(String furnitureNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByPrimaryKey
	 *
	 */
	public LabRoomFurniture findLabRoomFurnitureByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByPrimaryKey
	 *
	 */
	public LabRoomFurniture findLabRoomFurnitureByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomFurnitures
	 *
	 */
	public Set<LabRoomFurniture> findAllLabRoomFurnitures() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomFurnitures
	 *
	 */
	public Set<LabRoomFurniture> findAllLabRoomFurnitures(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureName
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureName(String furnitureName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureByFurnitureName
	 *
	 */
	public Set<LabRoomFurniture> findLabRoomFurnitureByFurnitureName(String furnitureName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureById
	 *
	 */
	public LabRoomFurniture findLabRoomFurnitureById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomFurnitureById
	 *
	 */
	public LabRoomFurniture findLabRoomFurnitureById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}