package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.CommonServer;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage CommonServer entities.
 * 
 */
@Repository("CommonServerDAO")
@Transactional
public class CommonServerDAOImpl extends AbstractJpaDao<CommonServer> implements
		CommonServerDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { CommonServer.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new CommonServerDAOImpl
	 *
	 */
	public CommonServerDAOImpl() {
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
	 * JPQL Query - findCommonServerByServerName
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerName(String serverName) throws DataAccessException {

		return findCommonServerByServerName(serverName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerName(String serverName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerName", startResult, maxRows, serverName);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerAddressContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerAddressContaining(String serverAddress) throws DataAccessException {

		return findCommonServerByServerAddressContaining(serverAddress, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerAddressContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerAddressContaining(String serverAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerAddressContaining", startResult, maxRows, serverAddress);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerById
	 *
	 */
	@Transactional
	public CommonServer findCommonServerById(Integer id) throws DataAccessException {

		return findCommonServerById(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerById
	 *
	 */

	@Transactional
	public CommonServer findCommonServerById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonServerById", startResult, maxRows, id);
			return (net.xidlims.domain.CommonServer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonServerByAcademyEnNameContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyEnNameContaining(String academyEnName) throws DataAccessException {

		return findCommonServerByAcademyEnNameContaining(academyEnName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByAcademyEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyEnNameContaining(String academyEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByAcademyEnNameContaining", startResult, maxRows, academyEnName);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByAcademyEnName
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyEnName(String academyEnName) throws DataAccessException {

		return findCommonServerByAcademyEnName(academyEnName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByAcademyEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyEnName(String academyEnName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByAcademyEnName", startResult, maxRows, academyEnName);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByReamarkContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByReamarkContaining(String reamark) throws DataAccessException {

		return findCommonServerByReamarkContaining(reamark, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByReamarkContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByReamarkContaining(String reamark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByReamarkContaining", startResult, maxRows, reamark);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerSn
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerSn(String serverSn) throws DataAccessException {

		return findCommonServerByServerSn(serverSn, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerSn
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerSn(String serverSn, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerSn", startResult, maxRows, serverSn);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllCommonServers
	 *
	 */
	@Transactional
	public Set<CommonServer> findAllCommonServers() throws DataAccessException {

		return findAllCommonServers(-1, -1);
	}

	/**
	 * JPQL Query - findAllCommonServers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findAllCommonServers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCommonServers", startResult, maxRows);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerIpContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerIpContaining(String serverIp) throws DataAccessException {

		return findCommonServerByServerIpContaining(serverIp, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerIpContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerIpContaining(String serverIp, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerIpContaining", startResult, maxRows, serverIp);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByAcademyNumberContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyNumberContaining(String academyNumber) throws DataAccessException {

		return findCommonServerByAcademyNumberContaining(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByAcademyNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByAcademyNumberContaining", startResult, maxRows, academyNumber);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerAddress
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerAddress(String serverAddress) throws DataAccessException {

		return findCommonServerByServerAddress(serverAddress, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerAddress
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerAddress(String serverAddress, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerAddress", startResult, maxRows, serverAddress);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerNameContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerNameContaining(String serverName) throws DataAccessException {

		return findCommonServerByServerNameContaining(serverName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerNameContaining(String serverName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerNameContaining", startResult, maxRows, serverName);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerSnContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerSnContaining(String serverSn) throws DataAccessException {

		return findCommonServerByServerSnContaining(serverSn, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerSnContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerSnContaining(String serverSn, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerSnContaining", startResult, maxRows, serverSn);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByAcademyNameContaining
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyNameContaining(String academyName) throws DataAccessException {

		return findCommonServerByAcademyNameContaining(academyName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByAcademyNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyNameContaining(String academyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByAcademyNameContaining", startResult, maxRows, academyName);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByPrimaryKey
	 *
	 */
	@Transactional
	public CommonServer findCommonServerByPrimaryKey(Integer id) throws DataAccessException {

		return findCommonServerByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByPrimaryKey
	 *
	 */

	@Transactional
	public CommonServer findCommonServerByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCommonServerByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.CommonServer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findCommonServerByReamark
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByReamark(String reamark) throws DataAccessException {

		return findCommonServerByReamark(reamark, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByReamark
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByReamark(String reamark, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByReamark", startResult, maxRows, reamark);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByAcademyNumber
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyNumber(String academyNumber) throws DataAccessException {

		return findCommonServerByAcademyNumber(academyNumber, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByAcademyNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByAcademyNumber", startResult, maxRows, academyNumber);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByServerIp
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByServerIp(String serverIp) throws DataAccessException {

		return findCommonServerByServerIp(serverIp, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByServerIp
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByServerIp(String serverIp, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByServerIp", startResult, maxRows, serverIp);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * JPQL Query - findCommonServerByAcademyName
	 *
	 */
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyName(String academyName) throws DataAccessException {

		return findCommonServerByAcademyName(academyName, -1, -1);
	}

	/**
	 * JPQL Query - findCommonServerByAcademyName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<CommonServer> findCommonServerByAcademyName(String academyName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCommonServerByAcademyName", startResult, maxRows, academyName);
		return new LinkedHashSet<CommonServer>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(CommonServer entity) {
		return true;
	}
}
