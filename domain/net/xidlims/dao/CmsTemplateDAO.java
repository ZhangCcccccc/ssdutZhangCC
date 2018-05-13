package net.xidlims.dao;

import net.xidlims.domain.CmsTemplate;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CmsTemplate entities.
 * 
 */
public interface CmsTemplateDAO extends JpaDao<CmsTemplate> {

	/**
	 * JPQL Query - findAllCmsTemplates
	 *
	 */
	public Set<CmsTemplate> findAllCmsTemplates() throws DataAccessException;

	/**
	 * JPQL Query - findAllCmsTemplates
	 *
	 */
	public Set<CmsTemplate> findAllCmsTemplates(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByProfileContaining
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByProfileContaining(String profile) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByProfileContaining
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByNameContaining
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByNameContaining(String name) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByNameContaining
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByNameContaining(String name, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByName
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByName(String name_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByName
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByName(String name_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByProfile
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByProfile(String profile_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByProfile
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByProfile(String profile_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateById
	 *
	 */
	public CmsTemplate findCmsTemplateById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateById
	 *
	 */
	public CmsTemplate findCmsTemplateById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByPrimaryKey
	 *
	 */
	public CmsTemplate findCmsTemplateByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByPrimaryKey
	 *
	 */
	public CmsTemplate findCmsTemplateByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByState
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByState(Integer state) throws DataAccessException;

	/**
	 * JPQL Query - findCmsTemplateByState
	 *
	 */
	public Set<CmsTemplate> findCmsTemplateByState(Integer state, int startResult, int maxRows) throws DataAccessException;

}