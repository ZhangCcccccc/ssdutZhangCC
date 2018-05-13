package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.NDevicePurchaseDetail;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage NDevicePurchaseDetail entities.
 * 
 */
@Repository("NDevicePurchaseDetailDAO")
@Transactional
public class NDevicePurchaseDetailDAOImpl extends AbstractJpaDao<NDevicePurchaseDetail>
		implements NDevicePurchaseDetailDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { NDevicePurchaseDetail.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new NDevicePurchaseDetailDAOImpl
	 *
	 */
	public NDevicePurchaseDetailDAOImpl() {
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
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnit
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnit(String deviceUnit) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceUnit(deviceUnit, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnit(String deviceUnit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceUnit", startResult, maxRows, deviceUnit);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumberContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumberContaining(String deviceNumber) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceNumberContaining(deviceNumber, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumberContaining(String deviceNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceNumberContaining", startResult, maxRows, deviceNumber);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceQuantity
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceQuantity(Integer deviceQuantity) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceQuantity(deviceQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceQuantity(Integer deviceQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceQuantity", startResult, maxRows, deviceQuantity);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormat
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormat(String deviceFormat) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceFormat(deviceFormat, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormat
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormat(String deviceFormat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceFormat", startResult, maxRows, deviceFormat);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnNameContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnNameContaining(String deviceEnName) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceEnNameContaining(deviceEnName, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnNameContaining(String deviceEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceEnNameContaining", startResult, maxRows, deviceEnName);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModel
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModel(String deviceModel) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceModel(deviceModel, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModel(String deviceModel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceModel", startResult, maxRows, deviceModel);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnName
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnName(String deviceEnName) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceEnName(deviceEnName, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceEnName(String deviceEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceEnName", startResult, maxRows, deviceEnName);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceName
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceName(String deviceName) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceName(deviceName, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceName(String deviceName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceName", startResult, maxRows, deviceName);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormatContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormatContaining(String deviceFormat) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceFormatContaining(deviceFormat, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceFormatContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceFormatContaining(String deviceFormat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceFormatContaining", startResult, maxRows, deviceFormat);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNameContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNameContaining(String deviceName) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceNameContaining(deviceName, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNameContaining(String deviceName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceNameContaining", startResult, maxRows, deviceName);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnitContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnitContaining(String deviceUnit) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceUnitContaining(deviceUnit, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceUnitContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceUnitContaining(String deviceUnit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceUnitContaining", startResult, maxRows, deviceUnit);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirectionContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirectionContaining(String useDirection) throws DataAccessException {

		return findNDevicePurchaseDetailByUseDirectionContaining(useDirection, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirectionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirectionContaining(String useDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByUseDirectionContaining", startResult, maxRows, useDirection);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemark
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemark(String remark) throws DataAccessException {

		return findNDevicePurchaseDetailByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemarkContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemarkContaining(String remark) throws DataAccessException {

		return findNDevicePurchaseDetailByRemarkContaining(remark, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByRemarkContaining", startResult, maxRows, remark);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlace
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlace(String place) throws DataAccessException {

		return findNDevicePurchaseDetailByPlace(place, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlace
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlace(String place, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByPlace", startResult, maxRows, place);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModelContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModelContaining(String deviceModel) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceModelContaining(deviceModel, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceModelContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceModelContaining(String deviceModel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceModelContaining", startResult, maxRows, deviceModel);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPrimaryKey
	 *
	 */
	@Transactional
	public NDevicePurchaseDetail findNDevicePurchaseDetailByPrimaryKey(Integer id) throws DataAccessException {

		return findNDevicePurchaseDetailByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPrimaryKey
	 *
	 */

	@Transactional
	public NDevicePurchaseDetail findNDevicePurchaseDetailByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNDevicePurchaseDetailByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.NDevicePurchaseDetail) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplierContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplierContaining(String deviceSupplier) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceSupplierContaining(deviceSupplier, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplierContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplierContaining(String deviceSupplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceSupplierContaining", startResult, maxRows, deviceSupplier);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumber
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumber(String deviceNumber) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceNumber(deviceNumber, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceNumber(String deviceNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceNumber", startResult, maxRows, deviceNumber);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllNDevicePurchaseDetails
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findAllNDevicePurchaseDetails() throws DataAccessException {

		return findAllNDevicePurchaseDetails(-1, -1);
	}

	/**
	 * JPQL Query - findAllNDevicePurchaseDetails
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findAllNDevicePurchaseDetails(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllNDevicePurchaseDetails", startResult, maxRows);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirection
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirection(String useDirection) throws DataAccessException {

		return findNDevicePurchaseDetailByUseDirection(useDirection, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByUseDirection
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByUseDirection(String useDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByUseDirection", startResult, maxRows, useDirection);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlaceContaining
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlaceContaining(String place) throws DataAccessException {

		return findNDevicePurchaseDetailByPlaceContaining(place, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByPlaceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByPlaceContaining(String place, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByPlaceContaining", startResult, maxRows, place);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDevicePrice
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDevicePrice(java.math.BigDecimal devicePrice) throws DataAccessException {

		return findNDevicePurchaseDetailByDevicePrice(devicePrice, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDevicePrice
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDevicePrice(java.math.BigDecimal devicePrice, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDevicePrice", startResult, maxRows, devicePrice);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplier
	 *
	 */
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplier(String deviceSupplier) throws DataAccessException {

		return findNDevicePurchaseDetailByDeviceSupplier(deviceSupplier, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailByDeviceSupplier
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<NDevicePurchaseDetail> findNDevicePurchaseDetailByDeviceSupplier(String deviceSupplier, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findNDevicePurchaseDetailByDeviceSupplier", startResult, maxRows, deviceSupplier);
		return new LinkedHashSet<NDevicePurchaseDetail>(query.getResultList());
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailById
	 *
	 */
	@Transactional
	public NDevicePurchaseDetail findNDevicePurchaseDetailById(Integer id) throws DataAccessException {

		return findNDevicePurchaseDetailById(id, -1, -1);
	}

	/**
	 * JPQL Query - findNDevicePurchaseDetailById
	 *
	 */

	@Transactional
	public NDevicePurchaseDetail findNDevicePurchaseDetailById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findNDevicePurchaseDetailById", startResult, maxRows, id);
			return (net.xidlims.domain.NDevicePurchaseDetail) query.getSingleResult();
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
	public boolean canBeMerged(NDevicePurchaseDetail entity) {
		return true;
	}
}
