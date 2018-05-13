package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.Expendable;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Expendable entities.
 * 
 */
public interface ExpendableDAO extends JpaDao<Expendable> {

	/**
	 * JPQL Query - findExpendableByPurchaseDate
	 *
	 */
	public Set<Expendable> findExpendableByPurchaseDate(java.util.Calendar purchaseDate) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPurchaseDate
	 *
	 */
	public Set<Expendable> findExpendableByPurchaseDate(Calendar purchaseDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableStatus
	 *
	 */
	public Set<Expendable> findExpendableByExpendableStatus(String expendableStatus) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableStatus
	 *
	 */
	public Set<Expendable> findExpendableByExpendableStatus(String expendableStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByIfDangerous
	 *
	 */
	public Set<Expendable> findExpendableByIfDangerous(Integer ifDangerous) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByIfDangerous
	 *
	 */
	public Set<Expendable> findExpendableByIfDangerous(Integer ifDangerous, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableStatusContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableStatusContaining(String expendableStatus_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableStatusContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableStatusContaining(String expendableStatus_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByArriveQuantity
	 *
	 */
	public Set<Expendable> findExpendableByArriveQuantity(String arriveQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByArriveQuantity
	 *
	 */
	public Set<Expendable> findExpendableByArriveQuantity(String arriveQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableUnitContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableUnitContaining(String expendableUnit) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableUnitContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableUnitContaining(String expendableUnit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableTypeContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableTypeContaining(String expendableType) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableTypeContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableTypeContaining(String expendableType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByArriveQuantityContaining
	 *
	 */
	public Set<Expendable> findExpendableByArriveQuantityContaining(String arriveQuantity_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByArriveQuantityContaining
	 *
	 */
	public Set<Expendable> findExpendableByArriveQuantityContaining(String arriveQuantity_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSpecificationContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSpecificationContaining(String expendableSpecification) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSpecificationContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSpecificationContaining(String expendableSpecification, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableBySupplierContaining
	 *
	 */
	public Set<Expendable> findExpendableBySupplierContaining(String supplier) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableBySupplierContaining
	 *
	 */
	public Set<Expendable> findExpendableBySupplierContaining(String supplier, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByFundAccountContaining
	 *
	 */
	public Set<Expendable> findExpendableByFundAccountContaining(String fundAccount) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByFundAccountContaining
	 *
	 */
	public Set<Expendable> findExpendableByFundAccountContaining(String fundAccount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByDangerousTypeContaining
	 *
	 */
	public Set<Expendable> findExpendableByDangerousTypeContaining(String dangerousType) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByDangerousTypeContaining
	 *
	 */
	public Set<Expendable> findExpendableByDangerousTypeContaining(String dangerousType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByUnitPrice
	 *
	 */
	public Set<Expendable> findExpendableByUnitPrice(java.math.BigDecimal unitPrice) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByUnitPrice
	 *
	 */
	public Set<Expendable> findExpendableByUnitPrice(BigDecimal unitPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSourceContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSourceContaining(String expendableSource) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSourceContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSourceContaining(String expendableSource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByOrderNumberContaining
	 *
	 */
	public Set<Expendable> findExpendableByOrderNumberContaining(String orderNumber) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByOrderNumberContaining
	 *
	 */
	public Set<Expendable> findExpendableByOrderNumberContaining(String orderNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSource
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSource(String expendableSource_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSource
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSource(String expendableSource_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableType
	 *
	 */
	public Set<Expendable> findExpendableByExpendableType(String expendableType_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableType
	 *
	 */
	public Set<Expendable> findExpendableByExpendableType(String expendableType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPlaceContaining
	 *
	 */
	public Set<Expendable> findExpendableByPlaceContaining(String place) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPlaceContaining
	 *
	 */
	public Set<Expendable> findExpendableByPlaceContaining(String place, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableName
	 *
	 */
	public Set<Expendable> findExpendableByExpendableName(String expendableName) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableName
	 *
	 */
	public Set<Expendable> findExpendableByExpendableName(String expendableName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByOrderNumber
	 *
	 */
	public Set<Expendable> findExpendableByOrderNumber(String orderNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByOrderNumber
	 *
	 */
	public Set<Expendable> findExpendableByOrderNumber(String orderNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPrimaryKey
	 *
	 */
	public Expendable findExpendableByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPrimaryKey
	 *
	 */
	public Expendable findExpendableByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByQuantityContaining
	 *
	 */
	public Set<Expendable> findExpendableByQuantityContaining(String quantity) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByQuantityContaining
	 *
	 */
	public Set<Expendable> findExpendableByQuantityContaining(String quantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByDangerousType
	 *
	 */
	public Set<Expendable> findExpendableByDangerousType(String dangerousType_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByDangerousType
	 *
	 */
	public Set<Expendable> findExpendableByDangerousType(String dangerousType_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableBySupplier
	 *
	 */
	public Set<Expendable> findExpendableBySupplier(String supplier_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableBySupplier
	 *
	 */
	public Set<Expendable> findExpendableBySupplier(String supplier_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByBrand
	 *
	 */
	public Set<Expendable> findExpendableByBrand(String brand) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByBrand
	 *
	 */
	public Set<Expendable> findExpendableByBrand(String brand, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByArriveTotalPrice
	 *
	 */
	public Set<Expendable> findExpendableByArriveTotalPrice(java.math.BigDecimal arriveTotalPrice) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByArriveTotalPrice
	 *
	 */
	public Set<Expendable> findExpendableByArriveTotalPrice(BigDecimal arriveTotalPrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByBrandContaining
	 *
	 */
	public Set<Expendable> findExpendableByBrandContaining(String brand_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByBrandContaining
	 *
	 */
	public Set<Expendable> findExpendableByBrandContaining(String brand_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByFlag
	 *
	 */
	public Set<Expendable> findExpendableByFlag(Integer flag) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByFlag
	 *
	 */
	public Set<Expendable> findExpendableByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableUnit
	 *
	 */
	public Set<Expendable> findExpendableByExpendableUnit(String expendableUnit_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableUnit
	 *
	 */
	public Set<Expendable> findExpendableByExpendableUnit(String expendableUnit_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByQuantity
	 *
	 */
	public Set<Expendable> findExpendableByQuantity(String quantity_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByQuantity
	 *
	 */
	public Set<Expendable> findExpendableByQuantity(String quantity_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByFundAccount
	 *
	 */
	public Set<Expendable> findExpendableByFundAccount(String fundAccount_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByFundAccount
	 *
	 */
	public Set<Expendable> findExpendableByFundAccount(String fundAccount_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSpecification
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSpecification(String expendableSpecification_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableSpecification
	 *
	 */
	public Set<Expendable> findExpendableByExpendableSpecification(String expendableSpecification_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableNameContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableNameContaining(String expendableName_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByExpendableNameContaining
	 *
	 */
	public Set<Expendable> findExpendableByExpendableNameContaining(String expendableName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPlace
	 *
	 */
	public Set<Expendable> findExpendableByPlace(String place_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableByPlace
	 *
	 */
	public Set<Expendable> findExpendableByPlace(String place_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendables
	 *
	 */
	public Set<Expendable> findAllExpendables() throws DataAccessException;

	/**
	 * JPQL Query - findAllExpendables
	 *
	 */
	public Set<Expendable> findAllExpendables(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableById
	 *
	 */
	public Expendable findExpendableById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findExpendableById
	 *
	 */
	public Expendable findExpendableById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}