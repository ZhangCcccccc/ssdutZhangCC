package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TWeightSetting;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TWeightSetting entities.
 * 
 */
@Repository("TWeightSettingDAO")
@Transactional
public class TWeightSettingDAOImpl extends AbstractJpaDao<TWeightSetting>
		implements TWeightSettingDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TWeightSetting.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TWeightSettingDAOImpl
	 *
	 */
	public TWeightSettingDAOImpl() {
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
	 * JPQL Query - findAllTWeightSettings
	 *
	 */
	@Transactional
	public Set<TWeightSetting> findAllTWeightSettings() throws DataAccessException {

		return findAllTWeightSettings(-1, -1);
	}

	/**
	 * JPQL Query - findAllTWeightSettings
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TWeightSetting> findAllTWeightSettings(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTWeightSettings", startResult, maxRows);
		return new LinkedHashSet<TWeightSetting>(query.getResultList());
	}

	/**
	 * JPQL Query - findTWeightSettingByPrimaryKey
	 *
	 */
	@Transactional
	public TWeightSetting findTWeightSettingByPrimaryKey(Integer id) throws DataAccessException {

		return findTWeightSettingByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingByPrimaryKey
	 *
	 */

	@Transactional
	public TWeightSetting findTWeightSettingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTWeightSettingByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TWeightSetting) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTWeightSettingByModifyDate
	 *
	 */
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByModifyDate(java.util.Calendar modifyDate) throws DataAccessException {

		return findTWeightSettingByModifyDate(modifyDate, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingByModifyDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByModifyDate(java.util.Calendar modifyDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTWeightSettingByModifyDate", startResult, maxRows, modifyDate);
		return new LinkedHashSet<TWeightSetting>(query.getResultList());
	}

	/**
	 * JPQL Query - findTWeightSettingByCreateDate
	 *
	 */
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findTWeightSettingByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTWeightSettingByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<TWeightSetting>(query.getResultList());
	}

	/**
	 * JPQL Query - findTWeightSettingByTypeContaining
	 *
	 */
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByTypeContaining(String type) throws DataAccessException {

		return findTWeightSettingByTypeContaining(type, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingByTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTWeightSettingByTypeContaining", startResult, maxRows, type);
		return new LinkedHashSet<TWeightSetting>(query.getResultList());
	}

	/**
	 * JPQL Query - findTWeightSettingById
	 *
	 */
	@Transactional
	public TWeightSetting findTWeightSettingById(Integer id) throws DataAccessException {

		return findTWeightSettingById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingById
	 *
	 */

	@Transactional
	public TWeightSetting findTWeightSettingById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTWeightSettingById", startResult, maxRows, id);
			return (net.xidlims.domain.TWeightSetting) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTWeightSettingByWeight
	 *
	 */
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByWeight(java.math.BigDecimal weight) throws DataAccessException {

		return findTWeightSettingByWeight(weight, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingByWeight
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByWeight(java.math.BigDecimal weight, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTWeightSettingByWeight", startResult, maxRows, weight);
		return new LinkedHashSet<TWeightSetting>(query.getResultList());
	}

	/**
	 * JPQL Query - findTWeightSettingByType
	 *
	 */
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByType(String type) throws DataAccessException {

		return findTWeightSettingByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findTWeightSettingByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TWeightSetting> findTWeightSettingByType(String type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTWeightSettingByType", startResult, maxRows, type);
		return new LinkedHashSet<TWeightSetting>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TWeightSetting entity) {
		return true;
	}
}
