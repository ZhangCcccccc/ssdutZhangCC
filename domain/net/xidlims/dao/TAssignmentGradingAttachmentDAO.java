package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentGradingAttachment;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentGradingAttachment entities.
 * 
 */
public interface TAssignmentGradingAttachmentDAO extends
		JpaDao<TAssignmentGradingAttachment> {

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId
	 *
	 */
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(Integer TAssignmentGradingAttachmentId) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId
	 *
	 */
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByTAssignmentGradingAttachmentId(Integer TAssignmentGradingAttachmentId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrlContaining
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrlContaining(String gradeUrl) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrlContaining
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrlContaining(String gradeUrl, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingId
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByTAssignmentGradingId(Integer TAssignmentGradingId) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByTAssignmentGradingId
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByTAssignmentGradingId(Integer TAssignmentGradingId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedByContaining
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedByContaining
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedBy
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedBy
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGroupId
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGroupId(Integer groupId) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGroupId
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGroupId(Integer groupId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedTime
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByCreatedTime
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByPrimaryKey
	 *
	 */
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByPrimaryKey(Integer TAssignmentGradingAttachmentId_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByPrimaryKey
	 *
	 */
	public TAssignmentGradingAttachment findTAssignmentGradingAttachmentByPrimaryKey(Integer TAssignmentGradingAttachmentId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrl
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrl(String gradeUrl_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByGradeUrl
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByGradeUrl(String gradeUrl_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByType
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByType(Integer type) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentGradingAttachmentByType
	 *
	 */
	public Set<TAssignmentGradingAttachment> findTAssignmentGradingAttachmentByType(Integer type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentGradingAttachments
	 *
	 */
	public Set<TAssignmentGradingAttachment> findAllTAssignmentGradingAttachments() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentGradingAttachments
	 *
	 */
	public Set<TAssignmentGradingAttachment> findAllTAssignmentGradingAttachments(int startResult, int maxRows) throws DataAccessException;

}