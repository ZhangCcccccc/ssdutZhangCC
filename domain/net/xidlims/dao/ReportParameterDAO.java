package net.xidlims.dao;

import java.math.BigDecimal;


import java.util.Set;

import net.xidlims.domain.ReportParameter;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage ReportParameter entities.
 * 
 */
public interface ReportParameterDAO extends JpaDao<ReportParameter> {

	/**
	 * JPQL Query - findReportParameterByDeviceAvgTime
	 *
	 */
	public Set<ReportParameter> findReportParameterByDeviceAvgTime(Integer deviceAvgTime) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByDeviceAvgTime
	 *
	 */
	public Set<ReportParameter> findReportParameterByDeviceAvgTime(Integer deviceAvgTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByLabAvgArea
	 *
	 */
	public Set<ReportParameter> findReportParameterByLabAvgArea(Integer labAvgArea) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByLabAvgArea
	 *
	 */
	public Set<ReportParameter> findReportParameterByLabAvgArea(Integer labAvgArea, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterById
	 *
	 */
	public ReportParameter findReportParameterById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterById
	 *
	 */
	public ReportParameter findReportParameterById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByTeacherAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByTeacherAmount(Integer teacherAmount) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByTeacherAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByTeacherAmount(Integer teacherAmount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByUndergraduateAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByUndergraduateAmount(Integer undergraduateAmount) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByUndergraduateAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByUndergraduateAmount(Integer undergraduateAmount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByAdminAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByAdminAmount(Integer adminAmount) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByAdminAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByAdminAmount(Integer adminAmount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterBySubjectFactor
	 *
	 */
	public Set<ReportParameter> findReportParameterBySubjectFactor(java.math.BigDecimal subjectFactor) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterBySubjectFactor
	 *
	 */
	public Set<ReportParameter> findReportParameterBySubjectFactor(BigDecimal subjectFactor, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByPrimaryKey
	 *
	 */
	public ReportParameter findReportParameterByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByPrimaryKey
	 *
	 */
	public ReportParameter findReportParameterByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllReportParameters
	 *
	 */
	public Set<ReportParameter> findAllReportParameters() throws DataAccessException;

	/**
	 * JPQL Query - findAllReportParameters
	 *
	 */
	public Set<ReportParameter> findAllReportParameters(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByRatedCourseTime
	 *
	 */
	public Set<ReportParameter> findReportParameterByRatedCourseTime(Integer ratedCourseTime) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByRatedCourseTime
	 *
	 */
	public Set<ReportParameter> findReportParameterByRatedCourseTime(Integer ratedCourseTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByGraduateAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByGraduateAmount(Integer graduateAmount) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByGraduateAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByGraduateAmount(Integer graduateAmount, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByMajorAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByMajorAmount(Integer majorAmount) throws DataAccessException;

	/**
	 * JPQL Query - findReportParameterByMajorAmount
	 *
	 */
	public Set<ReportParameter> findReportParameterByMajorAmount(Integer majorAmount, int startResult, int maxRows) throws DataAccessException;

}