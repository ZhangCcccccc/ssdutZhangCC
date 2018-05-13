package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.NDevicePurchaseDetail;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage NDevicePurchaseDetail entities.
 * 
 */
public interface NDevicePurchaseDetailDAO extends JpaDao<NDevicePurchaseDetail> {

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnit
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnit(String deviceUnit) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnit
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnit(String deviceUnit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumberContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumberContaining(String deviceNumber) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumberContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumberContaining(String deviceNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceQuantity
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceQuantity(Integer deviceQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceQuantity
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceQuantity(Integer deviceQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormat
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormat(String deviceFormat) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormat
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormat(String deviceFormat, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnNameContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnNameContaining(String deviceEnName) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnNameContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnNameContaining(String deviceEnName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModel
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModel(String deviceModel) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModel
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModel(String deviceModel, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnName
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnName(String deviceEnName_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnName
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnName(String deviceEnName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceName
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceName(String deviceName) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceName
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceName(String deviceName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormatContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormatContaining(String deviceFormat_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormatContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormatContaining(String deviceFormat_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNameContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNameContaining(String deviceName_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNameContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNameContaining(String deviceName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnitContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnitContaining(String deviceUnit_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnitContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnitContaining(String deviceUnit_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirectionContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirectionContaining(String useDirection) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirectionContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirectionContaining(String useDirection, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemark
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemark(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemark
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemark(String remark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemarkContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemarkContaining(String remark_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemarkContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemarkContaining(String remark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlace
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlace(String place) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlace
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlace(String place, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModelContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModelContaining(String deviceModel_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModelContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModelContaining(String deviceModel_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPrimaryKey
	 *
	 */
	public NDevicePurchaseDetail findNDevicePurchaseDetailByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPrimaryKey
	 *
	 */
	public NDevicePurchaseDetail findNDevicePurchaseDetailByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplierContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplierContaining(String deviceSupplier) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplierContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplierContaining(String deviceSupplier, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumber
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumber(String deviceNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumber
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumber(String deviceNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllNDevicePurchaseDetails
	 *
	 */
	public Set<NDevicePurchaseDetail> findAllNDevicePurchaseDetails() throws DataAccessException;

	/**
	 * JPQL Query - findAllNDevicePurchaseDetails
	 *
	 */
	public Set<NDevicePurchaseDetail> findAllNDevicePurchaseDetails(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirection
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirection(String useDirection_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirection
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirection(String useDirection_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlaceContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlaceContaining(String place_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlaceContaining
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlaceContaining(String place_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDevicePrice
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDevicePrice(java.math.BigDecimal devicePrice) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDevicePrice
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDevicePrice(BigDecimal devicePrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplier
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplier(String deviceSupplier_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplier
	 *
	 */
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplier(String deviceSupplier_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailById
	 *
	 */
	public NDevicePurchaseDetail findNDevicePurchaseDetailById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findNDevicePurchaseDetailById
	 *
	 */
	public NDevicePurchaseDetail findNDevicePurchaseDetailById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}