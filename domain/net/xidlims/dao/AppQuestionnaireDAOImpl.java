package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppQuestionnaire;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppQuestionnaire entities.
 * 
 */
@Repository("AppQuestionnaireDAO")
@Transactional
public class AppQuestionnaireDAOImpl extends AbstractJpaDao<AppQuestionnaire>
		implements AppQuestionnaireDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppQuestionnaire.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppQuestionnaireDAOImpl
	 *
	 */
	public AppQuestionnaireDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findAppQuestionnaireByTitleContaining
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByTitleContaining(String title) throws DataAccessException {

		return findAppQuestionnaireByTitleContaining(title, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByTitleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByTitleContaining(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionnaireByTitleContaining", startResult, maxRows, title);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionnaireByTitle
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByTitle(String title) throws DataAccessException {

		return findAppQuestionnaireByTitle(title, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByTitle
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByTitle(String title, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionnaireByTitle", startResult, maxRows, title);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionnaireByStat
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByStat(Integer stat) throws DataAccessException {

		return findAppQuestionnaireByStat(stat, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByStat
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByStat(Integer stat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionnaireByStat", startResult, maxRows, stat);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppQuestionnaires
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAllAppQuestionnaires() throws DataAccessException {

		return findAllAppQuestionnaires(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppQuestionnaires
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAllAppQuestionnaires(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppQuestionnaires", startResult, maxRows);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionnaireByType
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByType(Integer type) throws DataAccessException {

		return findAppQuestionnaireByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionnaireByType", startResult, maxRows, type);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionnaireByDescription
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByDescription(String description) throws DataAccessException {

		return findAppQuestionnaireByDescription(description, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByDescription
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByDescription(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionnaireByDescription", startResult, maxRows, description);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionnaireByDescriptionContaining
	 *
	 */
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByDescriptionContaining(String description) throws DataAccessException {

		return findAppQuestionnaireByDescriptionContaining(description, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByDescriptionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppQuestionnaire> findAppQuestionnaireByDescriptionContaining(String description, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppQuestionnaireByDescriptionContaining", startResult, maxRows, description);
		return new LinkedHashSet<AppQuestionnaire>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppQuestionnaireByPrimaryKey
	 *
	 */
	@Transactional
	public AppQuestionnaire findAppQuestionnaireByPrimaryKey(Integer id) throws DataAccessException {

		return findAppQuestionnaireByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireByPrimaryKey
	 *
	 */

	@Transactional
	public AppQuestionnaire findAppQuestionnaireByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppQuestionnaireByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppQuestionnaire) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppQuestionnaireById
	 *
	 */
	@Transactional
	public AppQuestionnaire findAppQuestionnaireById(Integer id) throws DataAccessException {

		return findAppQuestionnaireById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppQuestionnaireById
	 *
	 */

	@Transactional
	public AppQuestionnaire findAppQuestionnaireById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppQuestionnaireById", startResult, maxRows, id);
			return (net.xidlims.domain.AppQuestionnaire) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppQuestionnaire entity) {
		return true;
	}
}
