package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TCourseSite;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSite entities.
 * 
 */
public interface TCourseSiteDAO extends JpaDao<TCourseSite> {

	/**
	 * JPQL Query - findTCourseSiteByCreatedTime
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByCreatedTime
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByTypeContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByTypeContaining(String type) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByTypeContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByTitleContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByTitleContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumber
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByAcademyNumber(String academyNumber) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumber
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByTitle
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByTitle
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteById
	 *
	 */
	public TCourseSite findTCourseSiteById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteById
	 *
	 */
	public TCourseSite findTCourseSiteById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumberContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByAcademyNumberContaining(String academyNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByAcademyNumberContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByAcademyNumberContaining(String academyNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByPrimaryKey
	 *
	 */
	public TCourseSite findTCourseSiteByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByPrimaryKey
	 *
	 */
	public TCourseSite findTCourseSiteByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByDescription
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByDescription
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByDescription(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByType
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByType(String type_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByType
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByType(String type_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteBySiteCode
	 *
	 */
	public Set<TCourseSite> findTCourseSiteBySiteCode(String siteCode) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteBySiteCode
	 *
	 */
	public Set<TCourseSite> findTCourseSiteBySiteCode(String siteCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSites
	 *
	 */
	public Set<TCourseSite> findAllTCourseSites() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSites
	 *
	 */
	public Set<TCourseSite> findAllTCourseSites(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteBySiteCodeContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteBySiteCodeContaining(String siteCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteBySiteCodeContaining
	 *
	 */
	public Set<TCourseSite> findTCourseSiteBySiteCodeContaining(String siteCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByModifiedTime
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByModifiedTime(java.util.Calendar modifiedTime) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteByModifiedTime
	 *
	 */
	public Set<TCourseSite> findTCourseSiteByModifiedTime(Calendar modifiedTime, int startResult, int maxRows) throws DataAccessException;

}