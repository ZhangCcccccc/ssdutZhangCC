package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SchoolDevice;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SchoolDevice entities.
 * 
 */
public interface SchoolDeviceDAO extends JpaDao<SchoolDevice> {

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnNameContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnNameContaining(String deviceEnName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnNameContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnNameContaining(String deviceEnName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByCreatedAt
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByCreatedAt
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddressContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddressContaining(String deviceAddress) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddressContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddressContaining(String deviceAddress, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceName
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceName(String deviceName) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceName
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceName(String deviceName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemoContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemoContaining(String academyMemo) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemoContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemoContaining(String academyMemo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByProjectSource
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByProjectSource(String projectSource) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByProjectSource
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByProjectSource(String projectSource, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnName
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnName(String deviceEnName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnName
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnName(String deviceEnName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplierContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplierContaining(String deviceSupplier) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplierContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplierContaining(String deviceSupplier, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDevicePattern
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDevicePattern(String devicePattern) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDevicePattern
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDevicePattern(String devicePattern, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDevicePrice
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDevicePrice(java.math.BigDecimal devicePrice) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDevicePrice
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDevicePrice(BigDecimal devicePrice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByPrimaryKey
	 *
	 */
	public SchoolDevice findSchoolDeviceByPrimaryKey(String deviceNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByPrimaryKey
	 *
	 */
	public SchoolDevice findSchoolDeviceByPrimaryKey(String deviceNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemo
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemo(String academyMemo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemo
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemo(String academyMemo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByProjectSourceContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByProjectSourceContaining(String projectSource_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByProjectSourceContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByProjectSourceContaining(String projectSource_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountryContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountryContaining(String deviceCountry) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountryContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountryContaining(String deviceCountry, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplier
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplier(String deviceSupplier_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplier
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplier(String deviceSupplier_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatus
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatus(String deviceStatus) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatus
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatus(String deviceStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirection
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirection(String deviceUseDirection) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirection
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirection(String deviceUseDirection, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByManufacturerContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByManufacturerContaining(String manufacturer) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByManufacturerContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByManufacturerContaining(String manufacturer, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolDevices
	 *
	 */
	public Set<SchoolDevice> findAllSchoolDevices() throws DataAccessException;

	/**
	 * JPQL Query - findAllSchoolDevices
	 *
	 */
	public Set<SchoolDevice> findAllSchoolDevices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDevicePatternContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDevicePatternContaining(String devicePattern_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDevicePatternContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDevicePatternContaining(String devicePattern_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountry
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountry(String deviceCountry_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountry
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountry(String deviceCountry_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNameContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceNameContaining(String deviceName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNameContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceNameContaining(String deviceName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDate
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDate(java.util.Calendar deviceAccountedDate) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDate
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDate(Calendar deviceAccountedDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormat
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormat(String deviceFormat) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormat
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormat(String deviceFormat, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirectionContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirectionContaining(String deviceUseDirection_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirectionContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirectionContaining(String deviceUseDirection_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateBefore
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateBefore(java.util.Calendar deviceBuyDate) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateBefore
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateBefore(Calendar deviceBuyDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceById
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceById
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateAfter
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateAfter(java.util.Calendar deviceBuyDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateAfter
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateAfter(Calendar deviceBuyDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDate
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDate(java.util.Calendar deviceBuyDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDate
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDate(Calendar deviceBuyDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoom
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceBySystemRoom(String systemRoom) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoom
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceBySystemRoom(String systemRoom, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumber
	 *
	 */
	public SchoolDevice findSchoolDeviceByDeviceNumber(String deviceNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumber
	 *
	 */
	public SchoolDevice findSchoolDeviceByDeviceNumber(String deviceNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateAfter
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateAfter(java.util.Calendar deviceAccountedDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateAfter
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateAfter(Calendar deviceAccountedDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatusContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatusContaining(String deviceStatus_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatusContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatusContaining(String deviceStatus_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumberContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceNumberContaining(String deviceNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumberContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceNumberContaining(String deviceNumber_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByUpdatedAt
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByUpdatedAt
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateBefore
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateBefore(java.util.Calendar deviceAccountedDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateBefore
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateBefore(Calendar deviceAccountedDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoomContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceBySystemRoomContaining(String systemRoom_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoomContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceBySystemRoomContaining(String systemRoom_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormatContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormatContaining(String deviceFormat_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormatContaining
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormatContaining(String deviceFormat_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByManufacturer
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByManufacturer(String manufacturer_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByManufacturer
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByManufacturer(String manufacturer_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddress
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddress(String deviceAddress_1) throws DataAccessException;

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddress
	 *
	 */
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddress(String deviceAddress_1, int startResult, int maxRows) throws DataAccessException;

}