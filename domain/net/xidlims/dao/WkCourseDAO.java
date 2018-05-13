package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.WkCourse;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage WkCourse entities.
 * 
 */
public interface WkCourseDAO extends JpaDao<WkCourse> {

	/**
	 * JPQL Query - findWkCourseByEvaluationContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByEvaluationContaining(String evaluation) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByEvaluationContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByEvaluationContaining(String evaluation, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByOutcomesContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByOutcomesContaining(String outcomes) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByOutcomesContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByOutcomesContaining(String outcomes, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllWkCourses
	 *
	 */
	public Set<WkCourse> findAllWkCourses() throws DataAccessException;

	/**
	 * JPQL Query - findAllWkCourses
	 *
	 */
	public Set<WkCourse> findAllWkCourses(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByNameContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByNameContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByName
	 *
	 */
	public Set<WkCourse> findWkCourseByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByName
	 *
	 */
	public Set<WkCourse> findWkCourseByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByOutcomes
	 *
	 */
	public Set<WkCourse> findWkCourseByOutcomes(String outcomes_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByOutcomes
	 *
	 */
	public Set<WkCourse> findWkCourseByOutcomes(String outcomes_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByLogoUrlContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByLogoUrlContaining(String logoUrl) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByLogoUrlContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByLogoUrlContaining(String logoUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseById
	 *
	 */
	public WkCourse findWkCourseById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseById
	 *
	 */
	public WkCourse findWkCourseById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseBySyllabus
	 *
	 */
	public Set<WkCourse> findWkCourseBySyllabus(String syllabus) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseBySyllabus
	 *
	 */
	public Set<WkCourse> findWkCourseBySyllabus(String syllabus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByTagList
	 *
	 */
	public Set<WkCourse> findWkCourseByTagList(String tagList) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByTagList
	 *
	 */
	public Set<WkCourse> findWkCourseByTagList(String tagList, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByIsOpen
	 *
	 */
	public Set<WkCourse> findWkCourseByIsOpen(Integer isOpen) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByIsOpen
	 *
	 */
	public Set<WkCourse> findWkCourseByIsOpen(Integer isOpen, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByCodeContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByCodeContaining(String code) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByCodeContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByCodeContaining(String code, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByManager
	 *
	 */
	public Set<WkCourse> findWkCourseByManager(String manager) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByManager
	 *
	 */
	public Set<WkCourse> findWkCourseByManager(String manager, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByTagListContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByTagListContaining(String tagList_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByTagListContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByTagListContaining(String tagList_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByIntroductionContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByIntroductionContaining(String introduction) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByIntroductionContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByIntroductionContaining(String introduction, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByManagerContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByManagerContaining(String manager_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByManagerContaining
	 *
	 */
	public Set<WkCourse> findWkCourseByManagerContaining(String manager_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByPrimaryKey
	 *
	 */
	public WkCourse findWkCourseByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByPrimaryKey
	 *
	 */
	public WkCourse findWkCourseByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByEvaluation
	 *
	 */
	public Set<WkCourse> findWkCourseByEvaluation(String evaluation_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByEvaluation
	 *
	 */
	public Set<WkCourse> findWkCourseByEvaluation(String evaluation_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByCode
	 *
	 */
	public Set<WkCourse> findWkCourseByCode(String code_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByCode
	 *
	 */
	public Set<WkCourse> findWkCourseByCode(String code_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByLogoUrl
	 *
	 */
	public Set<WkCourse> findWkCourseByLogoUrl(String logoUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByLogoUrl
	 *
	 */
	public Set<WkCourse> findWkCourseByLogoUrl(String logoUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByVideoId
	 *
	 */
	public Set<WkCourse> findWkCourseByVideoId(Integer videoId) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByVideoId
	 *
	 */
	public Set<WkCourse> findWkCourseByVideoId(Integer videoId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByFilesList
	 *
	 */
	public Set<WkCourse> findWkCourseByFilesList(String filesList) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByFilesList
	 *
	 */
	public Set<WkCourse> findWkCourseByFilesList(String filesList, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByIntroduction
	 *
	 */
	public Set<WkCourse> findWkCourseByIntroduction(String introduction_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByIntroduction
	 *
	 */
	public Set<WkCourse> findWkCourseByIntroduction(String introduction_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByCerateTime
	 *
	 */
	public Set<WkCourse> findWkCourseByCerateTime(java.util.Calendar cerateTime) throws DataAccessException;

	/**
	 * JPQL Query - findWkCourseByCerateTime
	 *
	 */
	public Set<WkCourse> findWkCourseByCerateTime(Calendar cerateTime, int startResult, int maxRows) throws DataAccessException;

}