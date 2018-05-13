package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableLabRelated;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableLabRelated entities.
 * 
 */
public interface TimetableLabRelatedDAO extends JpaDao<TimetableLabRelated> {

	/**
	 * JPQL Query - findTimetableLabRelatedById
	 *
	 */
	public TimetableLabRelated findTimetableLabRelatedById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedById
	 *
	 */
	public TimetableLabRelated findTimetableLabRelatedById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableLabRelateds
	 *
	 */
	public Set<TimetableLabRelated> findAllTimetableLabRelateds() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableLabRelateds
	 *
	 */
	public Set<TimetableLabRelated> findAllTimetableLabRelateds(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedByPrimaryKey
	 *
	 */
	public TimetableLabRelated findTimetableLabRelatedByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedByPrimaryKey
	 *
	 */
	public TimetableLabRelated findTimetableLabRelatedByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}