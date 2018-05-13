package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableBatchItems;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableBatchItems entities.
 * 
 */
public interface TimetableBatchItemsDAO extends JpaDao<TimetableBatchItems> {

	/**
	 * JPQL Query - findTimetableBatchItemsById
	 *
	 */
	public TimetableBatchItems findTimetableBatchItemsById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchItemsById
	 *
	 */
	public TimetableBatchItems findTimetableBatchItemsById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchItemsByPrimaryKey
	 *
	 */
	public TimetableBatchItems findTimetableBatchItemsByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableBatchItemsByPrimaryKey
	 *
	 */
	public TimetableBatchItems findTimetableBatchItemsByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableBatchItemss
	 *
	 */
	public Set<TimetableBatchItems> findAllTimetableBatchItemss() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableBatchItemss
	 *
	 */
	public Set<TimetableBatchItems> findAllTimetableBatchItemss(int startResult, int maxRows) throws DataAccessException;

}