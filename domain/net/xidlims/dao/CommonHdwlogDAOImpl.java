package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CommonHdwlog;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CommonHdwlog entities.
 * 
 */
@Repository("CommonHdwlogDAO")
@Transactional
public class CommonHdwlogDAOImpl extends AbstractJpaDao<CommonHdwlog> implements
		CommonHdwlogDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CommonHdwlog.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CommonHdwlogDAOImpl
	 *
	 */
	public CommonHdwlogDAOImpl() {
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
	 * JPQL Query - findCommonHdwlogByCardname
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardname(String cardname) throws DataAccessException {

		return findCommonHdwlogByCardname(cardname, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardname
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardname(String cardname, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByCardname", startResult, maxRows, cardname);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumber
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumber(String academyNumber) throws DataAccessException {

		return findCommonHdwlogByAcademyNumber(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByAcademyNumber", startResult, maxRows, academyNumber);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardnumber
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardnumber(String cardnumber) throws DataAccessException {

		return findCommonHdwlogByCardnumber(cardnumber, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardnumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardnumber(String cardnumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByCardnumber", startResult, maxRows, cardnumber);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByDevicenoContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByDevicenoContaining(String deviceno) throws DataAccessException {

		return findCommonHdwlogByDevicenoContaining(deviceno, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByDevicenoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByDevicenoContaining(String deviceno, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByDevicenoContaining", startResult, maxRows, deviceno);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedat
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedat(String updatedat) throws DataAccessException {

		return findCommonHdwlogByUpdatedat(updatedat, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedat
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedat(String updatedat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByUpdatedat", startResult, maxRows, updatedat);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheck
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheck(String hdwCheck) throws DataAccessException {

		return findCommonHdwlogByHdwCheck(hdwCheck, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheck
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheck(String hdwCheck, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByHdwCheck", startResult, maxRows, hdwCheck);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByRemarkContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByRemarkContaining(String remark) throws DataAccessException {

		return findCommonHdwlogByRemarkContaining(remark, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByRemarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByRemarkContaining", startResult, maxRows, remark);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByHardwareidContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHardwareidContaining(String hardwareid) throws DataAccessException {

		return findCommonHdwlogByHardwareidContaining(hardwareid, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByHardwareidContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHardwareidContaining(String hardwareid, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByHardwareidContaining", startResult, maxRows, hardwareid);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardnameContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardnameContaining(String cardname) throws DataAccessException {

		return findCommonHdwlogByCardnameContaining(cardname, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardnameContaining(String cardname, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByCardnameContaining", startResult, maxRows, cardname);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByStatusContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByStatusContaining(String status) throws DataAccessException {

		return findCommonHdwlogByStatusContaining(status, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByStatusContaining(String status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByStatusContaining", startResult, maxRows, status);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByRemark
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByRemark(String remark) throws DataAccessException {

		return findCommonHdwlogByRemark(remark, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByRemark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByRemark(String remark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByStatus
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByStatus(String status) throws DataAccessException {

		return findCommonHdwlogByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByStatus(String status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByStatus", startResult, maxRows, status);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByPrimaryKey
	 *
	 */
	@Transactional
	public CommonHdwlog findCommonHdwlogByPrimaryKey(Integer id) throws DataAccessException {

		return findCommonHdwlogByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByPrimaryKey
	 *
	 */

	@Transactional
	public CommonHdwlog findCommonHdwlogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonHdwlogByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CommonHdwlog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonHdwlogByDatetime
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByDatetime(java.util.Calendar datetime) throws DataAccessException {

		return findCommonHdwlogByDatetime(datetime, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByDatetime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByDatetime(java.util.Calendar datetime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByDatetime", startResult, maxRows, datetime);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedatContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedatContaining(String updatedat) throws DataAccessException {

		return findCommonHdwlogByUpdatedatContaining(updatedat, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedatContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedatContaining(String updatedat, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByUpdatedatContaining", startResult, maxRows, updatedat);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByDeviceno
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByDeviceno(String deviceno) throws DataAccessException {

		return findCommonHdwlogByDeviceno(deviceno, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByDeviceno
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByDeviceno(String deviceno, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByDeviceno", startResult, maxRows, deviceno);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheckContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheckContaining(String hdwCheck) throws DataAccessException {

		return findCommonHdwlogByHdwCheckContaining(hdwCheck, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheckContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheckContaining(String hdwCheck, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByHdwCheckContaining", startResult, maxRows, hdwCheck);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByHardwareid
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHardwareid(String hardwareid) throws DataAccessException {

		return findCommonHdwlogByHardwareid(hardwareid, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByHardwareid
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByHardwareid(String hardwareid, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByHardwareid", startResult, maxRows, hardwareid);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCommonHdwlogs
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findAllCommonHdwlogs() throws DataAccessException {

		return findAllCommonHdwlogs(-1, -1);
	}

	/**
	 * JPQL Query - findAllCommonHdwlogs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findAllCommonHdwlogs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCommonHdwlogs", startResult, maxRows);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumberContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumberContaining(String academyNumber) throws DataAccessException {

		return findCommonHdwlogByAcademyNumberContaining(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByAcademyNumberContaining", startResult, maxRows, academyNumber);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonHdwlogById
	 *
	 */
	@Transactional
	public CommonHdwlog findCommonHdwlogById(Integer id) throws DataAccessException {

		return findCommonHdwlogById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogById
	 *
	 */

	@Transactional
	public CommonHdwlog findCommonHdwlogById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonHdwlogById", startResult, maxRows, id);
			return (net.xidlims.domain.CommonHdwlog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardnumberContaining
	 *
	 */
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardnumberContaining(String cardnumber) throws DataAccessException {

		return findCommonHdwlogByCardnumberContaining(cardnumber, -1, -1);
	}

	/**
	 * JPQL Query - findCommonHdwlogByCardnumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonHdwlog> findCommonHdwlogByCardnumberContaining(String cardnumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonHdwlogByCardnumberContaining", startResult, maxRows, cardnumber);
		return new LinkedHashSet<CommonHdwlog>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CommonHdwlog entity) {
		return true;
	}
}
