package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SchoolDevice;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SchoolDevice entities.
 * 
 */
@Repository("SchoolDeviceDAO")
@Transactional
public class SchoolDeviceDAOImpl extends AbstractJpaDao<SchoolDevice> implements
		SchoolDeviceDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SchoolDevice.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SchoolDeviceDAOImpl
	 *
	 */
	public SchoolDeviceDAOImpl() {
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
	 * JPQL Query - findSchoolDeviceByDeviceEnNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnNameContaining(String deviceEnName) throws DataAccessException {

		return findSchoolDeviceByDeviceEnNameContaining(deviceEnName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnNameContaining(String deviceEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceEnNameContaining", startResult, maxRows, deviceEnName);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByCreatedAt
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findSchoolDeviceByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddressContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddressContaining(String deviceAddress) throws DataAccessException {

		return findSchoolDeviceByDeviceAddressContaining(deviceAddress, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddressContaining(String deviceAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceAddressContaining", startResult, maxRows, deviceAddress);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceName
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceName(String deviceName) throws DataAccessException {

		return findSchoolDeviceByDeviceName(deviceName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceName(String deviceName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceName", startResult, maxRows, deviceName);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemoContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemoContaining(String academyMemo) throws DataAccessException {

		return findSchoolDeviceByAcademyMemoContaining(academyMemo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemoContaining(String academyMemo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByAcademyMemoContaining", startResult, maxRows, academyMemo);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByProjectSource
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByProjectSource(String projectSource) throws DataAccessException {

		return findSchoolDeviceByProjectSource(projectSource, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByProjectSource
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByProjectSource(String projectSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByProjectSource", startResult, maxRows, projectSource);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnName
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnName(String deviceEnName) throws DataAccessException {

		return findSchoolDeviceByDeviceEnName(deviceEnName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceEnName(String deviceEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceEnName", startResult, maxRows, deviceEnName);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplierContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplierContaining(String deviceSupplier) throws DataAccessException {

		return findSchoolDeviceByDeviceSupplierContaining(deviceSupplier, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplierContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplierContaining(String deviceSupplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceSupplierContaining", startResult, maxRows, deviceSupplier);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDevicePattern
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDevicePattern(String devicePattern) throws DataAccessException {

		return findSchoolDeviceByDevicePattern(devicePattern, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDevicePattern
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDevicePattern(String devicePattern, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDevicePattern", startResult, maxRows, devicePattern);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDevicePrice
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDevicePrice(java.math.BigDecimal devicePrice) throws DataAccessException {

		return findSchoolDeviceByDevicePrice(devicePrice, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDevicePrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDevicePrice(java.math.BigDecimal devicePrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDevicePrice", startResult, maxRows, devicePrice);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByPrimaryKey
	 *
	 */
	@Transactional
	public SchoolDevice findSchoolDeviceByPrimaryKey(String deviceNumber) throws DataAccessException {

		return findSchoolDeviceByPrimaryKey(deviceNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByPrimaryKey
	 *
	 */

	@Transactional
	public SchoolDevice findSchoolDeviceByPrimaryKey(String deviceNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolDeviceByPrimaryKey", startResult, maxRows, deviceNumber);
			return (net.xidlims.domain.SchoolDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemo
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemo(String academyMemo) throws DataAccessException {

		return findSchoolDeviceByAcademyMemo(academyMemo, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByAcademyMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByAcademyMemo(String academyMemo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByAcademyMemo", startResult, maxRows, academyMemo);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByProjectSourceContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByProjectSourceContaining(String projectSource) throws DataAccessException {

		return findSchoolDeviceByProjectSourceContaining(projectSource, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByProjectSourceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByProjectSourceContaining(String projectSource, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByProjectSourceContaining", startResult, maxRows, projectSource);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountryContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountryContaining(String deviceCountry) throws DataAccessException {

		return findSchoolDeviceByDeviceCountryContaining(deviceCountry, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountryContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountryContaining(String deviceCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceCountryContaining", startResult, maxRows, deviceCountry);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplier
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplier(String deviceSupplier) throws DataAccessException {

		return findSchoolDeviceByDeviceSupplier(deviceSupplier, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceSupplier
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceSupplier(String deviceSupplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceSupplier", startResult, maxRows, deviceSupplier);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatus
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatus(String deviceStatus) throws DataAccessException {

		return findSchoolDeviceByDeviceStatus(deviceStatus, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatus(String deviceStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceStatus", startResult, maxRows, deviceStatus);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirection
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirection(String deviceUseDirection) throws DataAccessException {

		return findSchoolDeviceByDeviceUseDirection(deviceUseDirection, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirection
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirection(String deviceUseDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceUseDirection", startResult, maxRows, deviceUseDirection);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByManufacturerContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByManufacturerContaining(String manufacturer) throws DataAccessException {

		return findSchoolDeviceByManufacturerContaining(manufacturer, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByManufacturerContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByManufacturerContaining(String manufacturer, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByManufacturerContaining", startResult, maxRows, manufacturer);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSchoolDevices
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findAllSchoolDevices() throws DataAccessException {

		return findAllSchoolDevices(-1, -1);
	}

	/**
	 * JPQL Query - findAllSchoolDevices
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findAllSchoolDevices(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSchoolDevices", startResult, maxRows);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDevicePatternContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDevicePatternContaining(String devicePattern) throws DataAccessException {

		return findSchoolDeviceByDevicePatternContaining(devicePattern, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDevicePatternContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDevicePatternContaining(String devicePattern, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDevicePatternContaining", startResult, maxRows, devicePattern);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountry
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountry(String deviceCountry) throws DataAccessException {

		return findSchoolDeviceByDeviceCountry(deviceCountry, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceCountry
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceCountry(String deviceCountry, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceCountry", startResult, maxRows, deviceCountry);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNameContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceNameContaining(String deviceName) throws DataAccessException {

		return findSchoolDeviceByDeviceNameContaining(deviceName, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceNameContaining(String deviceName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceNameContaining", startResult, maxRows, deviceName);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDate
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDate(java.util.Calendar deviceAccountedDate) throws DataAccessException {

		return findSchoolDeviceByDeviceAccountedDate(deviceAccountedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDate(java.util.Calendar deviceAccountedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceAccountedDate", startResult, maxRows, deviceAccountedDate);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormat
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormat(String deviceFormat) throws DataAccessException {

		return findSchoolDeviceByDeviceFormat(deviceFormat, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormat
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormat(String deviceFormat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceFormat", startResult, maxRows, deviceFormat);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirectionContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirectionContaining(String deviceUseDirection) throws DataAccessException {

		return findSchoolDeviceByDeviceUseDirectionContaining(deviceUseDirection, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceUseDirectionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceUseDirectionContaining(String deviceUseDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceUseDirectionContaining", startResult, maxRows, deviceUseDirection);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateBefore
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateBefore(java.util.Calendar deviceBuyDate) throws DataAccessException {

		return findSchoolDeviceByDeviceBuyDateBefore(deviceBuyDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateBefore(java.util.Calendar deviceBuyDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceBuyDateBefore", startResult, maxRows, deviceBuyDate);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceById
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceById(Integer id) throws DataAccessException {

		return findSchoolDeviceById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceById
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceById", startResult, maxRows, id);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateAfter
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateAfter(java.util.Calendar deviceBuyDate) throws DataAccessException {

		return findSchoolDeviceByDeviceBuyDateAfter(deviceBuyDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDateAfter(java.util.Calendar deviceBuyDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceBuyDateAfter", startResult, maxRows, deviceBuyDate);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDate
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDate(java.util.Calendar deviceBuyDate) throws DataAccessException {

		return findSchoolDeviceByDeviceBuyDate(deviceBuyDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceBuyDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceBuyDate(java.util.Calendar deviceBuyDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceBuyDate", startResult, maxRows, deviceBuyDate);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoom
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceBySystemRoom(String systemRoom) throws DataAccessException {

		return findSchoolDeviceBySystemRoom(systemRoom, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoom
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceBySystemRoom(String systemRoom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceBySystemRoom", startResult, maxRows, systemRoom);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumber
	 *
	 */
	@Transactional
	public SchoolDevice findSchoolDeviceByDeviceNumber(String deviceNumber) throws DataAccessException {

		return findSchoolDeviceByDeviceNumber(deviceNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumber
	 *
	 */

	@Transactional
	public SchoolDevice findSchoolDeviceByDeviceNumber(String deviceNumber, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSchoolDeviceByDeviceNumber", startResult, maxRows, deviceNumber);
			return (net.xidlims.domain.SchoolDevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateAfter
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateAfter(java.util.Calendar deviceAccountedDate) throws DataAccessException {

		return findSchoolDeviceByDeviceAccountedDateAfter(deviceAccountedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateAfter(java.util.Calendar deviceAccountedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceAccountedDateAfter", startResult, maxRows, deviceAccountedDate);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatusContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatusContaining(String deviceStatus) throws DataAccessException {

		return findSchoolDeviceByDeviceStatusContaining(deviceStatus, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceStatusContaining(String deviceStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceStatusContaining", startResult, maxRows, deviceStatus);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumberContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceNumberContaining(String deviceNumber) throws DataAccessException {

		return findSchoolDeviceByDeviceNumberContaining(deviceNumber, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceNumberContaining(String deviceNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceNumberContaining", startResult, maxRows, deviceNumber);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByUpdatedAt
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findSchoolDeviceByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateBefore
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateBefore(java.util.Calendar deviceAccountedDate) throws DataAccessException {

		return findSchoolDeviceByDeviceAccountedDateBefore(deviceAccountedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAccountedDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAccountedDateBefore(java.util.Calendar deviceAccountedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceAccountedDateBefore", startResult, maxRows, deviceAccountedDate);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoomContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceBySystemRoomContaining(String systemRoom) throws DataAccessException {

		return findSchoolDeviceBySystemRoomContaining(systemRoom, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceBySystemRoomContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceBySystemRoomContaining(String systemRoom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceBySystemRoomContaining", startResult, maxRows, systemRoom);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormatContaining
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormatContaining(String deviceFormat) throws DataAccessException {

		return findSchoolDeviceByDeviceFormatContaining(deviceFormat, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceFormatContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceFormatContaining(String deviceFormat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceFormatContaining", startResult, maxRows, deviceFormat);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByManufacturer
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByManufacturer(String manufacturer) throws DataAccessException {

		return findSchoolDeviceByManufacturer(manufacturer, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByManufacturer
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByManufacturer(String manufacturer, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByManufacturer", startResult, maxRows, manufacturer);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddress
	 *
	 */
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddress(String deviceAddress) throws DataAccessException {

		return findSchoolDeviceByDeviceAddress(deviceAddress, -1, -1);
	}

	/**
	 * JPQL Query - findSchoolDeviceByDeviceAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SchoolDevice> findSchoolDeviceByDeviceAddress(String deviceAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSchoolDeviceByDeviceAddress", startResult, maxRows, deviceAddress);
		return new LinkedHashSet<SchoolDevice>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SchoolDevice entity) {
		return true;
	}
}
