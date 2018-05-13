package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TCourseSiteArtical;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TCourseSiteArtical entities.
 * 
 */
public interface TCourseSiteArticalDAO extends JpaDao<TCourseSiteArtical> {

	/**
	 * JPQL Query - findAllTCourseSiteArticals
	 *
	 */
	public Set<TCourseSiteArtical> findAllTCourseSiteArticals() throws DataAccessException;

	/**
	 * JPQL Query - findAllTCourseSiteArticals
	 *
	 */
	public Set<TCourseSiteArtical> findAllTCourseSiteArticals(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByPrimaryKey
	 *
	 */
	public TCourseSiteArtical findTCourseSiteArticalByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByPrimaryKey
	 *
	 */
	public TCourseSiteArtical findTCourseSiteArticalByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByName
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByName
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrl
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrl(String imageUrl) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrl
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrl(String imageUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrlContaining
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrlContaining(String imageUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByImageUrlContaining
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByImageUrlContaining(String imageUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByNameContaining
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByNameContaining
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByTextContaining
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByTextContaining(String text) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByTextContaining
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByTextContaining(String text, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalBySort
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalBySort(Integer sort) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalBySort
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalBySort(Integer sort, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByText
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByText(String text_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByText
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByText(String text_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByCreateDate
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByCreateDate
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalById
	 *
	 */
	public TCourseSiteArtical findTCourseSiteArticalById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalById
	 *
	 */
	public TCourseSiteArtical findTCourseSiteArticalById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByContent
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByContent(String content) throws DataAccessException;

	/**
	 * JPQL Query - findTCourseSiteArticalByContent
	 *
	 */
	public Set<TCourseSiteArtical> findTCourseSiteArticalByContent(String content, int startResult, int maxRows) throws DataAccessException;

}