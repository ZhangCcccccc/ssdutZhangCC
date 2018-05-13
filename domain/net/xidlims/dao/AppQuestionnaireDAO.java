package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AppQuestionnaire;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppQuestionnaire entities.
 * 
 */
public interface AppQuestionnaireDAO extends JpaDao<AppQuestionnaire> {

	/**
	 * JPQL Query - findAppQuestionnaireByTitleContaining
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByTitleContaining(String title) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByTitleContaining
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByTitle
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByTitle(String title_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByTitle
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByTitle(String title_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByStat
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByStat(Integer stat) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByStat
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByStat(Integer stat, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppQuestionnaires
	 *
	 */
	public Set<AppQuestionnaire> findAllAppQuestionnaires() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppQuestionnaires
	 *
	 */
	public Set<AppQuestionnaire> findAllAppQuestionnaires(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByType
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByType
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByDescription
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByDescription
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByDescription(String description, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByDescriptionContaining
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByDescriptionContaining(String description_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByDescriptionContaining
	 *
	 */
	public Set<AppQuestionnaire> findAppQuestionnaireByDescriptionContaining(String description_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByPrimaryKey
	 *
	 */
	public AppQuestionnaire findAppQuestionnaireByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireByPrimaryKey
	 *
	 */
	public AppQuestionnaire findAppQuestionnaireByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireById
	 *
	 */
	public AppQuestionnaire findAppQuestionnaireById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionnaireById
	 *
	 */
	public AppQuestionnaire findAppQuestionnaireById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}