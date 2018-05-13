package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableTutorRelated;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableTutorRelated entities.
 * 
 */
public interface TimetableTutorRelatedDAO extends JpaDao<TimetableTutorRelated> {

	/**
	 * JPQL Query - findAllTimetableTutorRelateds
	 *
	 */
	public Set<TimetableTutorRelated> findAllTimetableTutorRelateds() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableTutorRelateds
	 *
	 */
	public Set<TimetableTutorRelated> findAllTimetableTutorRelateds(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTutorRelatedByPrimaryKey
	 *
	 */
	public TimetableTutorRelated findTimetableTutorRelatedByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTutorRelatedByPrimaryKey
	 *
	 */
	public TimetableTutorRelated findTimetableTutorRelatedByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTutorRelatedById
	 *
	 */
	public TimetableTutorRelated findTimetableTutorRelatedById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTutorRelatedById
	 *
	 */
	public TimetableTutorRelated findTimetableTutorRelatedById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}