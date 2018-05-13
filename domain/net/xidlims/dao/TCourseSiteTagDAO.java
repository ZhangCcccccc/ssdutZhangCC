package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TCourseSiteTag;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSiteTag entities.
 * 
 */
public interface TCourseSiteTagDAO extends JpaDao<TCourseSiteTag> {

	/**
	 * JPQL Query - findTCourseSiteTagByDescriptionContaining
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagByDescriptionContaining(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByDescriptionContaining
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByPrimaryKey
	 *
	 */
	public TCourseSiteTag findTCourseSiteTagByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByPrimaryKey
	 *
	 */
	public TCourseSiteTag findTCourseSiteTagByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagText
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagText(String siteTagText) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagText
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagText(String siteTagText, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagTextContaining
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagTextContaining(String siteTagText_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagTextContaining
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagTextContaining(String siteTagText_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteTags
	 *
	 */
	public Set<TCourseSiteTag> findAllTCourseSiteTags() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteTags
	 *
	 */
	public Set<TCourseSiteTag> findAllTCourseSiteTags(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagById
	 *
	 */
	public TCourseSiteTag findTCourseSiteTagById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagById
	 *
	 */
	public TCourseSiteTag findTCourseSiteTagById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTag
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTag(String siteTag) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTag
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTag(String siteTag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByDescription
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagByDescription(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByDescription
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagByDescription(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagContaining
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagContaining(String siteTag_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagBySiteTagContaining
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagBySiteTagContaining(String siteTag_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByType
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteTagByType
	 *
	 */
	public Set<TCourseSiteTag> findTCourseSiteTagByType(Integer type, int startResult, int maxRows) throws DataAccessException;

}