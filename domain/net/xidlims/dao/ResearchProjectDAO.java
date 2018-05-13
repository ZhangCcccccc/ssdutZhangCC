package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.ResearchProject;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ResearchProject entities.
 * 
 */
public interface ResearchProjectDAO extends JpaDao<ResearchProject> {

	/**
	 * JPQL Query - findResearchProjectByName
	 *
	 */
	public Set<ResearchProject> findResearchProjectByName(String name) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByName
	 *
	 */
	public Set<ResearchProject> findResearchProjectByName(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByCode
	 *
	 */
	public Set<ResearchProject> findResearchProjectByCode(String code) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByCode
	 *
	 */
	public Set<ResearchProject> findResearchProjectByCode(String code, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectById
	 *
	 */
	public ResearchProject findResearchProjectById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectById
	 *
	 */
	public ResearchProject findResearchProjectById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByNameContaining
	 *
	 */
	public Set<ResearchProject> findResearchProjectByNameContaining(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByNameContaining
	 *
	 */
	public Set<ResearchProject> findResearchProjectByNameContaining(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByCodeContaining
	 *
	 */
	public Set<ResearchProject> findResearchProjectByCodeContaining(String code_1) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByCodeContaining
	 *
	 */
	public Set<ResearchProject> findResearchProjectByCodeContaining(String code_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllResearchProjects
	 *
	 */
	public Set<ResearchProject> findAllResearchProjects() throws DataAccessException;

	/**
	 * JPQL Query - findAllResearchProjects
	 *
	 */
	public Set<ResearchProject> findAllResearchProjects(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByPrimaryKey
	 *
	 */
	public ResearchProject findResearchProjectByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findResearchProjectByPrimaryKey
	 *
	 */
	public ResearchProject findResearchProjectByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}