package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableItemRelated;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableItemRelated entities.
 * 
 */
public interface TimetableItemRelatedDAO extends JpaDao<TimetableItemRelated> {

	/**
	 * JPQL Query - findTimetableItemRelatedById
	 *
	 */
	public TimetableItemRelated findTimetableItemRelatedById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableItemRelatedById
	 *
	 */
	public TimetableItemRelated findTimetableItemRelatedById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableItemRelatedByPrimaryKey
	 *
	 */
	public TimetableItemRelated findTimetableItemRelatedByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableItemRelatedByPrimaryKey
	 *
	 */
	public TimetableItemRelated findTimetableItemRelatedByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableItemRelateds
	 *
	 */
	public Set<TimetableItemRelated> findAllTimetableItemRelateds() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableItemRelateds
	 *
	 */
	public Set<TimetableItemRelated> findAllTimetableItemRelateds(int startResult, int maxRows) throws DataAccessException;

}