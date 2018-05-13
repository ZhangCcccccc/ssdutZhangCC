package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.OperationItemMaterialRecord;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage OperationItemMaterialRecord entities.
 * 
 */
@Repository("OperationItemMaterialRecordDAO")
@Transactional
public class OperationItemMaterialRecordDAOImpl extends AbstractJpaDao<OperationItemMaterialRecord>
		implements OperationItemMaterialRecordDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { OperationItemMaterialRecord.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new OperationItemMaterialRecordDAOImpl
	 *
	 */
	public OperationItemMaterialRecordDAOImpl() {
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
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmountContaining
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmountContaining(String lpmrAmount) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrAmountContaining(lpmrAmount, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmountContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmountContaining(String lpmrAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrAmountContaining", startResult, maxRows, lpmrAmount);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmount
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmount(String lpmrAmount) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrAmount(lpmrAmount, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrAmount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrAmount(String lpmrAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrAmount", startResult, maxRows, lpmrAmount);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrId
	 *
	 */
	@Transactional
	public OperationItemMaterialRecord findOperationItemMaterialRecordByLpmrId(Integer lpmrId) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrId(lpmrId, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrId
	 *
	 */

	@Transactional
	public OperationItemMaterialRecord findOperationItemMaterialRecordByLpmrId(Integer lpmrId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrId", startResult, maxRows, lpmrId);
			return (net.xidlims.domain.OperationItemMaterialRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModel
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModel(String lpmrModel) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrModel(lpmrModel, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModel(String lpmrModel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrModel", startResult, maxRows, lpmrModel);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllOperationItemMaterialRecords
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findAllOperationItemMaterialRecords() throws DataAccessException {

		return findAllOperationItemMaterialRecords(-1, -1);
	}

	/**
	 * JPQL Query - findAllOperationItemMaterialRecords
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findAllOperationItemMaterialRecords(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllOperationItemMaterialRecords", startResult, maxRows);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrTimeCreate
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrTimeCreate(java.util.Calendar lpmrTimeCreate) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrTimeCreate(lpmrTimeCreate, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrTimeCreate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrTimeCreate(java.util.Calendar lpmrTimeCreate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrTimeCreate", startResult, maxRows, lpmrTimeCreate);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrName
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrName(String lpmrName) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrName(lpmrName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrName(String lpmrName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrName", startResult, maxRows, lpmrName);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnit
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnit(String lpmrUnit) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrUnit(lpmrUnit, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnit(String lpmrUnit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrUnit", startResult, maxRows, lpmrUnit);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemarkContaining
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemarkContaining(String lpmrRemark) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrRemarkContaining(lpmrRemark, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemarkContaining(String lpmrRemark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrRemarkContaining", startResult, maxRows, lpmrRemark);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemark
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemark(String lpmrRemark) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrRemark(lpmrRemark, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrRemark(String lpmrRemark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrRemark", startResult, maxRows, lpmrRemark);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrNameContaining
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrNameContaining(String lpmrName) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrNameContaining(lpmrName, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrNameContaining(String lpmrName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrNameContaining", startResult, maxRows, lpmrName);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModelContaining
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModelContaining(String lpmrModel) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrModelContaining(lpmrModel, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrModelContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrModelContaining(String lpmrModel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrModelContaining", startResult, maxRows, lpmrModel);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByPrimaryKey
	 *
	 */
	@Transactional
	public OperationItemMaterialRecord findOperationItemMaterialRecordByPrimaryKey(Integer lpmrId) throws DataAccessException {

		return findOperationItemMaterialRecordByPrimaryKey(lpmrId, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByPrimaryKey
	 *
	 */

	@Transactional
	public OperationItemMaterialRecord findOperationItemMaterialRecordByPrimaryKey(Integer lpmrId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findOperationItemMaterialRecordByPrimaryKey", startResult, maxRows, lpmrId);
			return (net.xidlims.domain.OperationItemMaterialRecord) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnitContaining
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnitContaining(String lpmrUnit) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrUnitContaining(lpmrUnit, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrUnitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrUnitContaining(String lpmrUnit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrUnitContaining", startResult, maxRows, lpmrUnit);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantity
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantity(String lpmrQuantity) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrQuantity(lpmrQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantity(String lpmrQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrQuantity", startResult, maxRows, lpmrQuantity);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantityContaining
	 *
	 */
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantityContaining(String lpmrQuantity) throws DataAccessException {

		return findOperationItemMaterialRecordByLpmrQuantityContaining(lpmrQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findOperationItemMaterialRecordByLpmrQuantityContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<OperationItemMaterialRecord> findOperationItemMaterialRecordByLpmrQuantityContaining(String lpmrQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findOperationItemMaterialRecordByLpmrQuantityContaining", startResult, maxRows, lpmrQuantity);
		return new LinkedHashSet<OperationItemMaterialRecord>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(OperationItemMaterialRecord entity) {
		return true;
	}
}
