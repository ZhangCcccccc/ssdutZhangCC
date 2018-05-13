package net.xidlims.dao;

import java.util.Arrays;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.ReportParameter;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage ReportParameter entities.
 * 
 */
@Repository("ReportParameterDAO")
@Transactional
public class ReportParameterDAOImpl extends AbstractJpaDao<ReportParameter>
		implements ReportParameterDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { ReportParameter.class }));

	/**
	 * EntityManager injected by Spring for persistence unit dhulimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new ReportParameterDAOImpl
	 *
	 */
	public ReportParameterDAOImpl() {
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
	 * JPQL Query - findReportParameterByDeviceAvgTime
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByDeviceAvgTime(Integer deviceAvgTime) throws DataAccessException {

		return findReportParameterByDeviceAvgTime(deviceAvgTime, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByDeviceAvgTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByDeviceAvgTime(Integer deviceAvgTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByDeviceAvgTime", startResult, maxRows, deviceAvgTime);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByLabAvgArea
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByLabAvgArea(Integer labAvgArea) throws DataAccessException {

		return findReportParameterByLabAvgArea(labAvgArea, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByLabAvgArea
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByLabAvgArea(Integer labAvgArea, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByLabAvgArea", startResult, maxRows, labAvgArea);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterById
	 *
	 */
	@Transactional
	public ReportParameter findReportParameterById(Integer id) throws DataAccessException {

		return findReportParameterById(id, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterById
	 *
	 */

	@Transactional
	public ReportParameter findReportParameterById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findReportParameterById", startResult, maxRows, id);
			return (net.xidlims.domain.ReportParameter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findReportParameterByTeacherAmount
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByTeacherAmount(Integer teacherAmount) throws DataAccessException {

		return findReportParameterByTeacherAmount(teacherAmount, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByTeacherAmount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByTeacherAmount(Integer teacherAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByTeacherAmount", startResult, maxRows, teacherAmount);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByUndergraduateAmount
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByUndergraduateAmount(Integer undergraduateAmount) throws DataAccessException {

		return findReportParameterByUndergraduateAmount(undergraduateAmount, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByUndergraduateAmount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByUndergraduateAmount(Integer undergraduateAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByUndergraduateAmount", startResult, maxRows, undergraduateAmount);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByAdminAmount
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByAdminAmount(Integer adminAmount) throws DataAccessException {

		return findReportParameterByAdminAmount(adminAmount, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByAdminAmount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByAdminAmount(Integer adminAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByAdminAmount", startResult, maxRows, adminAmount);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterBySubjectFactor
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterBySubjectFactor(java.math.BigDecimal subjectFactor) throws DataAccessException {

		return findReportParameterBySubjectFactor(subjectFactor, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterBySubjectFactor
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterBySubjectFactor(java.math.BigDecimal subjectFactor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterBySubjectFactor", startResult, maxRows, subjectFactor);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByPrimaryKey
	 *
	 */
	@Transactional
	public ReportParameter findReportParameterByPrimaryKey(Integer id) throws DataAccessException {

		return findReportParameterByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByPrimaryKey
	 *
	 */

	@Transactional
	public ReportParameter findReportParameterByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findReportParameterByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.ReportParameter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllReportParameters
	 *
	 */
	@Transactional
	public Set<ReportParameter> findAllReportParameters() throws DataAccessException {

		return findAllReportParameters(-1, -1);
	}

	/**
	 * JPQL Query - findAllReportParameters
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findAllReportParameters(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllReportParameters", startResult, maxRows);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByRatedCourseTime
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByRatedCourseTime(Integer ratedCourseTime) throws DataAccessException {

		return findReportParameterByRatedCourseTime(ratedCourseTime, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByRatedCourseTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByRatedCourseTime(Integer ratedCourseTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByRatedCourseTime", startResult, maxRows, ratedCourseTime);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByGraduateAmount
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByGraduateAmount(Integer graduateAmount) throws DataAccessException {

		return findReportParameterByGraduateAmount(graduateAmount, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByGraduateAmount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByGraduateAmount(Integer graduateAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByGraduateAmount", startResult, maxRows, graduateAmount);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * JPQL Query - findReportParameterByMajorAmount
	 *
	 */
	@Transactional
	public Set<ReportParameter> findReportParameterByMajorAmount(Integer majorAmount) throws DataAccessException {

		return findReportParameterByMajorAmount(majorAmount, -1, -1);
	}

	/**
	 * JPQL Query - findReportParameterByMajorAmount
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<ReportParameter> findReportParameterByMajorAmount(Integer majorAmount, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findReportParameterByMajorAmount", startResult, maxRows, majorAmount);
		return new LinkedHashSet<ReportParameter>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(ReportParameter entity) {
		return true;
	}
}
