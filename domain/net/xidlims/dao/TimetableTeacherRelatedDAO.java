package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableTeacherRelated;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableTeacherRelated entities.
 * 
 */
public interface TimetableTeacherRelatedDAO extends
		JpaDao<TimetableTeacherRelated> {

	/**
	 * JPQL Query - findTimetableTeacherRelatedByPrimaryKey
	 *
	 */
	public TimetableTeacherRelated findTimetableTeacherRelatedByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTeacherRelatedByPrimaryKey
	 *
	 */
	public TimetableTeacherRelated findTimetableTeacherRelatedByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTeacherRelatedById
	 *
	 */
	public TimetableTeacherRelated findTimetableTeacherRelatedById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableTeacherRelatedById
	 *
	 */
	public TimetableTeacherRelated findTimetableTeacherRelatedById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableTeacherRelateds
	 *
	 */
	public Set<TimetableTeacherRelated> findAllTimetableTeacherRelateds() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableTeacherRelateds
	 *
	 */
	public Set<TimetableTeacherRelated> findAllTimetableTeacherRelateds(int startResult, int maxRows) throws DataAccessException;

}