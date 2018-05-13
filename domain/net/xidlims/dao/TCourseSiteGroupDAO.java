package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TCourseSiteGroup;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSiteGroup entities.
 * 
 */
public interface TCourseSiteGroupDAO extends JpaDao<TCourseSiteGroup> {

	/**
	 * JPQL Query - findTCourseSiteGroupById
	 *
	 */
	public TCourseSiteGroup findTCourseSiteGroupById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupById
	 *
	 */
	public TCourseSiteGroup findTCourseSiteGroupById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByDescription
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByDescription
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescription(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitleContaining
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitleContaining(String groupTitle) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitleContaining
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitleContaining(String groupTitle, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByPrimaryKey
	 *
	 */
	public TCourseSiteGroup findTCourseSiteGroupByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByPrimaryKey
	 *
	 */
	public TCourseSiteGroup findTCourseSiteGroupByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByDescriptionContaining
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescriptionContaining(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByDescriptionContaining
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByDescriptionContaining(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteGroups
	 *
	 */
	public Set<TCourseSiteGroup> findAllTCourseSiteGroups() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteGroups
	 *
	 */
	public Set<TCourseSiteGroup> findAllTCourseSiteGroups(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitle
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitle(String groupTitle_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteGroupByGroupTitle
	 *
	 */
	public Set<TCourseSiteGroup> findTCourseSiteGroupByGroupTitle(String groupTitle_1, int startResult, int maxRows) throws DataAccessException;

}