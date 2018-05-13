package net.xidlims.dao;

import net.xidlims.domain.CmsTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CmsTemplate entities.
 * 
 */
@Repository("CmsTemplateDAO")
@Transactional
public class CmsTemplateDAOImpl extends AbstractJpaDao<CmsTemplate> implements
		CmsTemplateDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CmsTemplate.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CmsTemplateDAOImpl
	 *
	 */
	public CmsTemplateDAOImpl() {
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
	 * JPQL Query - findAllCmsTemplates
	 *
	 */
	@Transactional
	public Set<CmsTemplate> findAllCmsTemplates() throws DataAccessException {

		return findAllCmsTemplates(-1, -1);
	}

	/**
	 * JPQL Query - findAllCmsTemplates
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTemplate> findAllCmsTemplates(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCmsTemplates", startResult, maxRows);
		return new LinkedHashSet<CmsTemplate>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTemplateByProfileContaining
	 *
	 */
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByProfileContaining(String profile) throws DataAccessException {

		return findCmsTemplateByProfileContaining(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateByProfileContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByProfileContaining(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTemplateByProfileContaining", startResult, maxRows, profile);
		return new LinkedHashSet<CmsTemplate>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTemplateByNameContaining
	 *
	 */
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByNameContaining(String name) throws DataAccessException {

		return findCmsTemplateByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTemplateByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<CmsTemplate>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTemplateByName
	 *
	 */
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByName(String name) throws DataAccessException {

		return findCmsTemplateByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTemplateByName", startResult, maxRows, name);
		return new LinkedHashSet<CmsTemplate>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTemplateByProfile
	 *
	 */
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByProfile(String profile) throws DataAccessException {

		return findCmsTemplateByProfile(profile, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateByProfile
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByProfile(String profile, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTemplateByProfile", startResult, maxRows, profile);
		return new LinkedHashSet<CmsTemplate>(query.getResultList());
	}

	/**
	 * JPQL Query - findCmsTemplateById
	 *
	 */
	@Transactional
	public CmsTemplate findCmsTemplateById(Integer id) throws DataAccessException {

		return findCmsTemplateById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateById
	 *
	 */

	@Transactional
	public CmsTemplate findCmsTemplateById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsTemplateById", startResult, maxRows, id);
			return (net.xidlims.domain.CmsTemplate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsTemplateByPrimaryKey
	 *
	 */
	@Transactional
	public CmsTemplate findCmsTemplateByPrimaryKey(Integer id) throws DataAccessException {

		return findCmsTemplateByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateByPrimaryKey
	 *
	 */

	@Transactional
	public CmsTemplate findCmsTemplateByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCmsTemplateByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CmsTemplate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCmsTemplateByState
	 *
	 */
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByState(Integer state) throws DataAccessException {

		return findCmsTemplateByState(state, -1, -1);
	}

	/**
	 * JPQL Query - findCmsTemplateByState
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CmsTemplate> findCmsTemplateByState(Integer state, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCmsTemplateByState", startResult, maxRows, state);
		return new LinkedHashSet<CmsTemplate>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CmsTemplate entity) {
		return true;
	}
}
