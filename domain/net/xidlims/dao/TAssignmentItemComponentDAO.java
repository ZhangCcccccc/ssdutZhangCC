package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.TAssignmentItemComponent;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentItemComponent entities.
 * 
 */
public interface TAssignmentItemComponentDAO extends
		JpaDao<TAssignmentItemComponent> {

	/**
	 * JPQL Query - findTAssignmentItemComponentById
	 *
	 */
	public TAssignmentItemComponent findTAssignmentItemComponentById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentById
	 *
	 */
	public TAssignmentItemComponent findTAssignmentItemComponentById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemScore
	 *
	 */
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemScore(java.math.BigDecimal itemScore) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemScore
	 *
	 */
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemScore(BigDecimal itemScore, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemType
	 *
	 */
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemType(Integer itemType) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemType
	 *
	 */
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemType(Integer itemType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByPrimaryKey
	 *
	 */
	public TAssignmentItemComponent findTAssignmentItemComponentByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByPrimaryKey
	 *
	 */
	public TAssignmentItemComponent findTAssignmentItemComponentByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemQuantity
	 *
	 */
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemQuantity(Integer itemQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentItemComponentByItemQuantity
	 *
	 */
	public Set<TAssignmentItemComponent> findTAssignmentItemComponentByItemQuantity(Integer itemQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItemComponents
	 *
	 */
	public Set<TAssignmentItemComponent> findAllTAssignmentItemComponents() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentItemComponents
	 *
	 */
	public Set<TAssignmentItemComponent> findAllTAssignmentItemComponents(int startResult, int maxRows) throws DataAccessException;

}