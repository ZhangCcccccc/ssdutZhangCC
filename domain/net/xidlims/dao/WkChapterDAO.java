package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.WkChapter;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage WkChapter entities.
 * 
 */
public interface WkChapterDAO extends JpaDao<WkChapter> {

	/**
	 * JPQL Query - findWkChapterByName
	 *
	 */
	public Set<WkChapter> findWkChapterByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByName
	 *
	 */
	public Set<WkChapter> findWkChapterByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByFileListContaining
	 *
	 */
	public Set<WkChapter> findWkChapterByFileListContaining(String fileList) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByFileListContaining
	 *
	 */
	public Set<WkChapter> findWkChapterByFileListContaining(String fileList, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllWkChapters
	 *
	 */
	public Set<WkChapter> findAllWkChapters() throws DataAccessException;

	/**
	 * JPQL Query - findAllWkChapters
	 *
	 */
	public Set<WkChapter> findAllWkChapters(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterBySeq
	 *
	 */
	public Set<WkChapter> findWkChapterBySeq(Integer seq) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterBySeq
	 *
	 */
	public Set<WkChapter> findWkChapterBySeq(Integer seq, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterById
	 *
	 */
	public WkChapter findWkChapterById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterById
	 *
	 */
	public WkChapter findWkChapterById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByNameContaining
	 *
	 */
	public Set<WkChapter> findWkChapterByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByNameContaining
	 *
	 */
	public Set<WkChapter> findWkChapterByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByFileList
	 *
	 */
	public Set<WkChapter> findWkChapterByFileList(String fileList_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByFileList
	 *
	 */
	public Set<WkChapter> findWkChapterByFileList(String fileList_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByPrimaryKey
	 *
	 */
	public WkChapter findWkChapterByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findWkChapterByPrimaryKey
	 *
	 */
	public WkChapter findWkChapterByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}