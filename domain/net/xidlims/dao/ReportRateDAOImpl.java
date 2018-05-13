package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.ReportRate;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage ReportRate entities.
 * 
 */
@Repository("ReportRateDAO")
@Transactional
public class ReportRateDAOImpl extends AbstractJpaDao<ReportRate> implements
		ReportRateDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ReportRate.class }));

	/**
	 * EntityManager injected by Spring for persistence unit dhulimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ReportRateDAOImpl
	 *
	 */
	public ReportRateDAOImpl() {
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
	 * JPQL Query - findReportRateByTermsContaining
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByTermsContaining(String terms) throws DataAccessException {

		return findReportRateByTermsContaining(terms, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByTermsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByTermsContaining(String terms, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByTermsContaining", startResult, maxRows, terms);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByScore
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByScore(java.math.BigDecimal score) throws DataAccessException {

		return findReportRateByScore(score, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByScore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByScore(java.math.BigDecimal score, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByScore", startResult, maxRows, score);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByLargeDeviceTimeRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByLargeDeviceTimeRate(java.math.BigDecimal largeDeviceTimeRate) throws DataAccessException {

		return findReportRateByLargeDeviceTimeRate(largeDeviceTimeRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByLargeDeviceTimeRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByLargeDeviceTimeRate(java.math.BigDecimal largeDeviceTimeRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByLargeDeviceTimeRate", startResult, maxRows, largeDeviceTimeRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByRank
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByRank(Integer rank) throws DataAccessException {

		return findReportRateByRank(rank, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByRank(Integer rank, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByRank", startResult, maxRows, rank);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByItemsRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByItemsRate(java.math.BigDecimal itemsRate) throws DataAccessException {

		return findReportRateByItemsRate(itemsRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByItemsRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByItemsRate(java.math.BigDecimal itemsRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByItemsRate", startResult, maxRows, itemsRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByPrimaryKey
	 *
	 */
	@Transactional
	public ReportRate findReportRateByPrimaryKey(Integer id) throws DataAccessException {

		return findReportRateByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByPrimaryKey
	 *
	 */

	@Transactional
	public ReportRate findReportRateByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findReportRateByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.ReportRate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findReportRateByTeacherItemRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByTeacherItemRate(java.math.BigDecimal teacherItemRate) throws DataAccessException {

		return findReportRateByTeacherItemRate(teacherItemRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByTeacherItemRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByTeacherItemRate(java.math.BigDecimal teacherItemRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByTeacherItemRate", startResult, maxRows, teacherItemRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByLargeDeviceUsedRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByLargeDeviceUsedRate(java.math.BigDecimal largeDeviceUsedRate) throws DataAccessException {

		return findReportRateByLargeDeviceUsedRate(largeDeviceUsedRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByLargeDeviceUsedRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByLargeDeviceUsedRate(java.math.BigDecimal largeDeviceUsedRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByLargeDeviceUsedRate", startResult, maxRows, largeDeviceUsedRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByLabAdminRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByLabAdminRate(java.math.BigDecimal labAdminRate) throws DataAccessException {

		return findReportRateByLabAdminRate(labAdminRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByLabAdminRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByLabAdminRate(java.math.BigDecimal labAdminRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByLabAdminRate", startResult, maxRows, labAdminRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByTerms
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByTerms(String terms) throws DataAccessException {

		return findReportRateByTerms(terms, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByTerms
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByTerms(String terms, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByTerms", startResult, maxRows, terms);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByLabRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByLabRate(java.math.BigDecimal labRate) throws DataAccessException {

		return findReportRateByLabRate(labRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByLabRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByLabRate(java.math.BigDecimal labRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByLabRate", startResult, maxRows, labRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateByComplexItemRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByComplexItemRate(java.math.BigDecimal complexItemRate) throws DataAccessException {

		return findReportRateByComplexItemRate(complexItemRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByComplexItemRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByComplexItemRate(java.math.BigDecimal complexItemRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByComplexItemRate", startResult, maxRows, complexItemRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllReportRates
	 *
	 */
	@Transactional
	public Set<ReportRate> findAllReportRates() throws DataAccessException {

		return findAllReportRates(-1, -1);
	}

	/**
	 * JPQL Query - findAllReportRates
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findAllReportRates(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllReportRates", startResult, maxRows);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportRateById
	 *
	 */
	@Transactional
	public ReportRate findReportRateById(Integer id) throws DataAccessException {

		return findReportRateById(id, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateById
	 *
	 */

	@Transactional
	public ReportRate findReportRateById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findReportRateById", startResult, maxRows, id);
			return (net.xidlims.domain.ReportRate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findReportRateByStudentTrainRate
	 *
	 */
	@Transactional
	public Set<ReportRate> findReportRateByStudentTrainRate(java.math.BigDecimal studentTrainRate) throws DataAccessException {

		return findReportRateByStudentTrainRate(studentTrainRate, -1, -1);
	}

	/**
	 * JPQL Query - findReportRateByStudentTrainRate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportRate> findReportRateByStudentTrainRate(java.math.BigDecimal studentTrainRate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportRateByStudentTrainRate", startResult, maxRows, studentTrainRate);
		return new LinkedHashSet<ReportRate>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(ReportRate entity) {
		return true;
	}
}
