package net.xidlims.dao;

import java.math.BigDecimal;


import java.util.Set;

import net.xidlims.domain.ReportRate;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ReportRate entities.
 * 
 */
public interface ReportRateDAO extends JpaDao<ReportRate> {

	/**
	 * JPQL Query - findReportRateByTermsContaining
	 *
	 */
	public Set<ReportRate> findReportRateByTermsContaining(String terms) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByTermsContaining
	 *
	 */
	public Set<ReportRate> findReportRateByTermsContaining(String terms, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByScore
	 *
	 */
	public Set<ReportRate> findReportRateByScore(java.math.BigDecimal score) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByScore
	 *
	 */
	public Set<ReportRate> findReportRateByScore(BigDecimal score, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLargeDeviceTimeRate
	 *
	 */
	public Set<ReportRate> findReportRateByLargeDeviceTimeRate(java.math.BigDecimal largeDeviceTimeRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLargeDeviceTimeRate
	 *
	 */
	public Set<ReportRate> findReportRateByLargeDeviceTimeRate(BigDecimal largeDeviceTimeRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByRank
	 *
	 */
	public Set<ReportRate> findReportRateByRank(Integer rank) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByRank
	 *
	 */
	public Set<ReportRate> findReportRateByRank(Integer rank, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByItemsRate
	 *
	 */
	public Set<ReportRate> findReportRateByItemsRate(java.math.BigDecimal itemsRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByItemsRate
	 *
	 */
	public Set<ReportRate> findReportRateByItemsRate(BigDecimal itemsRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByPrimaryKey
	 *
	 */
	public ReportRate findReportRateByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByPrimaryKey
	 *
	 */
	public ReportRate findReportRateByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByTeacherItemRate
	 *
	 */
	public Set<ReportRate> findReportRateByTeacherItemRate(java.math.BigDecimal teacherItemRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByTeacherItemRate
	 *
	 */
	public Set<ReportRate> findReportRateByTeacherItemRate(BigDecimal teacherItemRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLargeDeviceUsedRate
	 *
	 */
	public Set<ReportRate> findReportRateByLargeDeviceUsedRate(java.math.BigDecimal largeDeviceUsedRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLargeDeviceUsedRate
	 *
	 */
	public Set<ReportRate> findReportRateByLargeDeviceUsedRate(BigDecimal largeDeviceUsedRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLabAdminRate
	 *
	 */
	public Set<ReportRate> findReportRateByLabAdminRate(java.math.BigDecimal labAdminRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLabAdminRate
	 *
	 */
	public Set<ReportRate> findReportRateByLabAdminRate(BigDecimal labAdminRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByTerms
	 *
	 */
	public Set<ReportRate> findReportRateByTerms(String terms_1) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByTerms
	 *
	 */
	public Set<ReportRate> findReportRateByTerms(String terms_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLabRate
	 *
	 */
	public Set<ReportRate> findReportRateByLabRate(java.math.BigDecimal labRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByLabRate
	 *
	 */
	public Set<ReportRate> findReportRateByLabRate(BigDecimal labRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByComplexItemRate
	 *
	 */
	public Set<ReportRate> findReportRateByComplexItemRate(java.math.BigDecimal complexItemRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByComplexItemRate
	 *
	 */
	public Set<ReportRate> findReportRateByComplexItemRate(BigDecimal complexItemRate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllReportRates
	 *
	 */
	public Set<ReportRate> findAllReportRates() throws DataAccessException;

	/**
	 * JPQL Query - findAllReportRates
	 *
	 */
	public Set<ReportRate> findAllReportRates(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateById
	 *
	 */
	public ReportRate findReportRateById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateById
	 *
	 */
	public ReportRate findReportRateById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByStudentTrainRate
	 *
	 */
	public Set<ReportRate> findReportRateByStudentTrainRate(java.math.BigDecimal studentTrainRate) throws DataAccessException;

	/**
	 * JPQL Query - findReportRateByStudentTrainRate
	 *
	 */
	public Set<ReportRate> findReportRateByStudentTrainRate(BigDecimal studentTrainRate, int startResult, int maxRows) throws DataAccessException;

}