package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.WkLesson;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage WkLesson entities.
 * 
 */
public interface WkLessonDAO extends JpaDao<WkLesson> {

	/**
	 * JPQL Query - findWkLessonByVideoUrlContaining
	 *
	 */
	public Set<WkLesson> findWkLessonByVideoUrlContaining(String videoUrl) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByVideoUrlContaining
	 *
	 */
	public Set<WkLesson> findWkLessonByVideoUrlContaining(String videoUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTestId
	 *
	 */
	public Set<WkLesson> findWkLessonByTestId(Integer testId) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTestId
	 *
	 */
	public Set<WkLesson> findWkLessonByTestId(Integer testId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByCreateTime
	 *
	 */
	public Set<WkLesson> findWkLessonByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByCreateTime
	 *
	 */
	public Set<WkLesson> findWkLessonByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTitleContaining
	 *
	 */
	public Set<WkLesson> findWkLessonByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTitleContaining
	 *
	 */
	public Set<WkLesson> findWkLessonByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByVideoUrl
	 *
	 */
	public Set<WkLesson> findWkLessonByVideoUrl(String videoUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByVideoUrl
	 *
	 */
	public Set<WkLesson> findWkLessonByVideoUrl(String videoUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTypeContaining
	 *
	 */
	public Set<WkLesson> findWkLessonByTypeContaining(String type) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTypeContaining
	 *
	 */
	public Set<WkLesson> findWkLessonByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonById
	 *
	 */
	public WkLesson findWkLessonById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonById
	 *
	 */
	public WkLesson findWkLessonById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByFileId
	 *
	 */
	public Set<WkLesson> findWkLessonByFileId(Integer fileId) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByFileId
	 *
	 */
	public Set<WkLesson> findWkLessonByFileId(Integer fileId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllWkLessons
	 *
	 */
	public Set<WkLesson> findAllWkLessons() throws DataAccessException;

	/**
	 * JPQL Query - findAllWkLessons
	 *
	 */
	public Set<WkLesson> findAllWkLessons(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByContent
	 *
	 */
	public Set<WkLesson> findWkLessonByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByContent
	 *
	 */
	public Set<WkLesson> findWkLessonByContent(String content, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByType
	 *
	 */
	public Set<WkLesson> findWkLessonByType(String type_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByType
	 *
	 */
	public Set<WkLesson> findWkLessonByType(String type_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonBySeq
	 *
	 */
	public Set<WkLesson> findWkLessonBySeq(Integer seq) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonBySeq
	 *
	 */
	public Set<WkLesson> findWkLessonBySeq(Integer seq, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByViewedNum
	 *
	 */
	public Set<WkLesson> findWkLessonByViewedNum(Integer viewedNum) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByViewedNum
	 *
	 */
	public Set<WkLesson> findWkLessonByViewedNum(Integer viewedNum, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByPrimaryKey
	 *
	 */
	public WkLesson findWkLessonByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByPrimaryKey
	 *
	 */
	public WkLesson findWkLessonByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonBySourceList
	 *
	 */
	public Set<WkLesson> findWkLessonBySourceList(String sourceList) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonBySourceList
	 *
	 */
	public Set<WkLesson> findWkLessonBySourceList(String sourceList, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonBySourceListContaining
	 *
	 */
	public Set<WkLesson> findWkLessonBySourceListContaining(String sourceList_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonBySourceListContaining
	 *
	 */
	public Set<WkLesson> findWkLessonBySourceListContaining(String sourceList_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByDeep
	 *
	 */
	public Set<WkLesson> findWkLessonByDeep(String deep) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByDeep
	 *
	 */
	public Set<WkLesson> findWkLessonByDeep(String deep, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTitle
	 *
	 */
	public Set<WkLesson> findWkLessonByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkLessonByTitle
	 *
	 */
	public Set<WkLesson> findWkLessonByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

}