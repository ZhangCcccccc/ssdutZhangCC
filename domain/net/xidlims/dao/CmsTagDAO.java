package net.xidlims.dao;

import net.xidlims.domain.CmsTag;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsTag entities.
 * 
 */
public interface CmsTagDAO extends JpaDao<CmsTag> {

	/**
	 * JPQL Query - findCmsTagByDescription
	 *
	 */
	public Set<CmsTag> findCmsTagByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByDescription
	 *
	 */
	public Set<CmsTag> findCmsTagByDescription(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsTags
	 *
	 */
	public Set<CmsTag> findAllCmsTags() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsTags
	 *
	 */
	public Set<CmsTag> findAllCmsTags(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByNameContaining
	 *
	 */
	public Set<CmsTag> findCmsTagByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByNameContaining
	 *
	 */
	public Set<CmsTag> findCmsTagByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByCategoryContaining
	 *
	 */
	public Set<CmsTag> findCmsTagByCategoryContaining(String category) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByCategoryContaining
	 *
	 */
	public Set<CmsTag> findCmsTagByCategoryContaining(String category, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByDescriptionContaining
	 *
	 */
	public Set<CmsTag> findCmsTagByDescriptionContaining(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByDescriptionContaining
	 *
	 */
	public Set<CmsTag> findCmsTagByDescriptionContaining(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByName
	 *
	 */
	public Set<CmsTag> findCmsTagByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByName
	 *
	 */
	public Set<CmsTag> findCmsTagByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByPrimaryKey
	 *
	 */
	public CmsTag findCmsTagByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByPrimaryKey
	 *
	 */
	public CmsTag findCmsTagByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagById
	 *
	 */
	public CmsTag findCmsTagById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagById
	 *
	 */
	public CmsTag findCmsTagById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByCategory
	 *
	 */
	public Set<CmsTag> findCmsTagByCategory(String category_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTagByCategory
	 *
	 */
	public Set<CmsTag> findCmsTagByCategory(String category_1, int startResult, int maxRows) throws DataAccessException;

}