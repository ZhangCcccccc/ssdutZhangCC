package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TCourseSitePage;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSitePage entities.
 * 
 */
public interface TCourseSitePageDAO extends JpaDao<TCourseSitePage> {

	/**
	 * JPQL Query - findTCourseSitePageByPopup
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageByPopup(Integer popup) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByPopup
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageByPopup(Integer popup, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByTitle
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageByTitle(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByTitle
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageByTitle(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageBySiteOrder
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageBySiteOrder(Integer siteOrder) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageBySiteOrder
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageBySiteOrder(Integer siteOrder, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByPrimaryKey
	 *
	 */
	public TCourseSitePage findTCourseSitePageByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByPrimaryKey
	 *
	 */
	public TCourseSitePage findTCourseSitePageByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByTitleContaining
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageByTitleContaining(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageByTitleContaining
	 *
	 */
	public Set<TCourseSitePage> findTCourseSitePageByTitleContaining(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageById
	 *
	 */
	public TCourseSitePage findTCourseSitePageById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSitePageById
	 *
	 */
	public TCourseSitePage findTCourseSitePageById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSitePages
	 *
	 */
	public Set<TCourseSitePage> findAllTCourseSitePages() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSitePages
	 *
	 */
	public Set<TCourseSitePage> findAllTCourseSitePages(int startResult, int maxRows) throws DataAccessException;

}