package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.Expendable;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Expendable entities.
 * 
 */
@Repository("ExpendableDAO")
@Transactional
public class ExpendableDAOImpl extends AbstractJpaDao<Expendable> implements
		ExpendableDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Expendable.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ExpendableDAOImpl
	 *
	 */
	public ExpendableDAOImpl() {
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
	 * JPQL Query - findExpendableByPurchaseDate
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByPurchaseDate(java.util.Calendar purchaseDate) throws DataAccessException {

		return findExpendableByPurchaseDate(purchaseDate, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByPurchaseDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByPurchaseDate(java.util.Calendar purchaseDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByPurchaseDate", startResult, maxRows, purchaseDate);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableStatus
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableStatus(String expendableStatus) throws DataAccessException {

		return findExpendableByExpendableStatus(expendableStatus, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableStatus(String expendableStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableStatus", startResult, maxRows, expendableStatus);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByIfDangerous
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByIfDangerous(Integer ifDangerous) throws DataAccessException {

		return findExpendableByIfDangerous(ifDangerous, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByIfDangerous
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByIfDangerous(Integer ifDangerous, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByIfDangerous", startResult, maxRows, ifDangerous);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableStatusContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableStatusContaining(String expendableStatus) throws DataAccessException {

		return findExpendableByExpendableStatusContaining(expendableStatus, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableStatusContaining(String expendableStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableStatusContaining", startResult, maxRows, expendableStatus);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByArriveQuantity
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByArriveQuantity(String arriveQuantity) throws DataAccessException {

		return findExpendableByArriveQuantity(arriveQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByArriveQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByArriveQuantity(String arriveQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByArriveQuantity", startResult, maxRows, arriveQuantity);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableUnitContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableUnitContaining(String expendableUnit) throws DataAccessException {

		return findExpendableByExpendableUnitContaining(expendableUnit, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableUnitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableUnitContaining(String expendableUnit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableUnitContaining", startResult, maxRows, expendableUnit);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableTypeContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableTypeContaining(String expendableType) throws DataAccessException {

		return findExpendableByExpendableTypeContaining(expendableType, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableTypeContaining(String expendableType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableTypeContaining", startResult, maxRows, expendableType);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByArriveQuantityContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByArriveQuantityContaining(String arriveQuantity) throws DataAccessException {

		return findExpendableByArriveQuantityContaining(arriveQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByArriveQuantityContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByArriveQuantityContaining(String arriveQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByArriveQuantityContaining", startResult, maxRows, arriveQuantity);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableSpecificationContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableSpecificationContaining(String expendableSpecification) throws DataAccessException {

		return findExpendableByExpendableSpecificationContaining(expendableSpecification, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableSpecificationContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableSpecificationContaining(String expendableSpecification, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableSpecificationContaining", startResult, maxRows, expendableSpecification);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableBySupplierContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableBySupplierContaining(String supplier) throws DataAccessException {

		return findExpendableBySupplierContaining(supplier, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableBySupplierContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableBySupplierContaining(String supplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableBySupplierContaining", startResult, maxRows, supplier);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByFundAccountContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByFundAccountContaining(String fundAccount) throws DataAccessException {

		return findExpendableByFundAccountContaining(fundAccount, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByFundAccountContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByFundAccountContaining(String fundAccount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByFundAccountContaining", startResult, maxRows, fundAccount);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByDangerousTypeContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByDangerousTypeContaining(String dangerousType) throws DataAccessException {

		return findExpendableByDangerousTypeContaining(dangerousType, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByDangerousTypeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByDangerousTypeContaining(String dangerousType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByDangerousTypeContaining", startResult, maxRows, dangerousType);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByUnitPrice
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByUnitPrice(java.math.BigDecimal unitPrice) throws DataAccessException {

		return findExpendableByUnitPrice(unitPrice, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByUnitPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByUnitPrice(java.math.BigDecimal unitPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByUnitPrice", startResult, maxRows, unitPrice);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableSourceContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableSourceContaining(String expendableSource) throws DataAccessException {

		return findExpendableByExpendableSourceContaining(expendableSource, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableSourceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableSourceContaining(String expendableSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableSourceContaining", startResult, maxRows, expendableSource);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByOrderNumberContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByOrderNumberContaining(String orderNumber) throws DataAccessException {

		return findExpendableByOrderNumberContaining(orderNumber, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByOrderNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByOrderNumberContaining(String orderNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByOrderNumberContaining", startResult, maxRows, orderNumber);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableSource
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableSource(String expendableSource) throws DataAccessException {

		return findExpendableByExpendableSource(expendableSource, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableSource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableSource(String expendableSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableSource", startResult, maxRows, expendableSource);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableType
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableType(String expendableType) throws DataAccessException {

		return findExpendableByExpendableType(expendableType, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableType(String expendableType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableType", startResult, maxRows, expendableType);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByPlaceContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByPlaceContaining(String place) throws DataAccessException {

		return findExpendableByPlaceContaining(place, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByPlaceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByPlaceContaining(String place, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByPlaceContaining", startResult, maxRows, place);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableName
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableName(String expendableName) throws DataAccessException {

		return findExpendableByExpendableName(expendableName, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableName(String expendableName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableName", startResult, maxRows, expendableName);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByOrderNumber
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByOrderNumber(String orderNumber) throws DataAccessException {

		return findExpendableByOrderNumber(orderNumber, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByOrderNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByOrderNumber(String orderNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByOrderNumber", startResult, maxRows, orderNumber);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByPrimaryKey
	 *
	 */
	@Transactional
	public Expendable findExpendableByPrimaryKey(Integer id) throws DataAccessException {

		return findExpendableByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByPrimaryKey
	 *
	 */

	@Transactional
	public Expendable findExpendableByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.Expendable) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findExpendableByQuantityContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByQuantityContaining(String quantity) throws DataAccessException {

		return findExpendableByQuantityContaining(quantity, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByQuantityContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByQuantityContaining(String quantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByQuantityContaining", startResult, maxRows, quantity);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByDangerousType
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByDangerousType(String dangerousType) throws DataAccessException {

		return findExpendableByDangerousType(dangerousType, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByDangerousType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByDangerousType(String dangerousType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByDangerousType", startResult, maxRows, dangerousType);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableBySupplier
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableBySupplier(String supplier) throws DataAccessException {

		return findExpendableBySupplier(supplier, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableBySupplier
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableBySupplier(String supplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableBySupplier", startResult, maxRows, supplier);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByBrand
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByBrand(String brand) throws DataAccessException {

		return findExpendableByBrand(brand, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByBrand
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByBrand(String brand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByBrand", startResult, maxRows, brand);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByArriveTotalPrice
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByArriveTotalPrice(java.math.BigDecimal arriveTotalPrice) throws DataAccessException {

		return findExpendableByArriveTotalPrice(arriveTotalPrice, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByArriveTotalPrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByArriveTotalPrice(java.math.BigDecimal arriveTotalPrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByArriveTotalPrice", startResult, maxRows, arriveTotalPrice);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByBrandContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByBrandContaining(String brand) throws DataAccessException {

		return findExpendableByBrandContaining(brand, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByBrandContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByBrandContaining(String brand, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByBrandContaining", startResult, maxRows, brand);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByFlag
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByFlag(Integer flag) throws DataAccessException {

		return findExpendableByFlag(flag, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByFlag", startResult, maxRows, flag);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableUnit
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableUnit(String expendableUnit) throws DataAccessException {

		return findExpendableByExpendableUnit(expendableUnit, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableUnit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableUnit(String expendableUnit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableUnit", startResult, maxRows, expendableUnit);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByQuantity
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByQuantity(String quantity) throws DataAccessException {

		return findExpendableByQuantity(quantity, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByQuantity(String quantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByQuantity", startResult, maxRows, quantity);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByFundAccount
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByFundAccount(String fundAccount) throws DataAccessException {

		return findExpendableByFundAccount(fundAccount, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByFundAccount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByFundAccount(String fundAccount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByFundAccount", startResult, maxRows, fundAccount);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableSpecification
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableSpecification(String expendableSpecification) throws DataAccessException {

		return findExpendableByExpendableSpecification(expendableSpecification, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableSpecification
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableSpecification(String expendableSpecification, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableSpecification", startResult, maxRows, expendableSpecification);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByExpendableNameContaining
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByExpendableNameContaining(String expendableName) throws DataAccessException {

		return findExpendableByExpendableNameContaining(expendableName, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByExpendableNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByExpendableNameContaining(String expendableName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByExpendableNameContaining", startResult, maxRows, expendableName);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableByPlace
	 *
	 */
	@Transactional
	public Set<Expendable> findExpendableByPlace(String place) throws DataAccessException {

		return findExpendableByPlace(place, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableByPlace
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findExpendableByPlace(String place, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findExpendableByPlace", startResult, maxRows, place);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllExpendables
	 *
	 */
	@Transactional
	public Set<Expendable> findAllExpendables() throws DataAccessException {

		return findAllExpendables(-1, -1);
	}

	/**
	 * JPQL Query - findAllExpendables
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Expendable> findAllExpendables(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllExpendables", startResult, maxRows);
		return new LinkedHashSet<Expendable>(query.getResultList());
	}

	/**
	 * JPQL Query - findExpendableById
	 *
	 */
	@Transactional
	public Expendable findExpendableById(Integer id) throws DataAccessException {

		return findExpendableById(id, -1, -1);
	}

	/**
	 * JPQL Query - findExpendableById
	 *
	 */

	@Transactional
	public Expendable findExpendableById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findExpendableById", startResult, maxRows, id);
			return (net.xidlims.domain.Expendable) query.getSingleResult();
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
	public boolean canBeMerged(Expendable entity) {
		return true;
	}
}
