package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.InnerAcademyCenter;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage InnerAcademyCenter entities.
 * 
 */
public interface InnerAcademyCenterDAO extends JpaDao<InnerAcademyCenter> {

	/**
	 * JPQL Query - findAllInnerAcademyCenters
	 *
	 */
	public Set<InnerAcademyCenter> findAllInnerAcademyCenters() throws DataAccessException;

	/**
	 * JPQL Query - findAllInnerAcademyCenters
	 *
	 */
	public Set<InnerAcademyCenter> findAllInnerAcademyCenters(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findInnerAcademyCenterByPrimaryKey
	 *
	 */
	public InnerAcademyCenter findInnerAcademyCenterByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findInnerAcademyCenterByPrimaryKey
	 *
	 */
	public InnerAcademyCenter findInnerAcademyCenterByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findInnerAcademyCenterById
	 *
	 */
	public InnerAcademyCenter findInnerAcademyCenterById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findInnerAcademyCenterById
	 *
	 */
	public InnerAcademyCenter findInnerAcademyCenterById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}