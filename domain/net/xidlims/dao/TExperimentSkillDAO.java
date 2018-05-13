package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TExperimentSkill;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExperimentSkill entities.
 * 
 */
public interface TExperimentSkillDAO extends JpaDao<TExperimentSkill> {

	/**
	 * JPQL Query - findTExperimentSkillByCreatedTime
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByCreatedTime(java.util.Calendar createdTime) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByCreatedTime
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByCreatedTime(Calendar createdTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillById
	 *
	 */
	public TExperimentSkill findTExperimentSkillById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillById
	 *
	 */
	public TExperimentSkill findTExperimentSkillById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersion
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersion(String experimentVersion) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersion
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersion(String experimentVersion, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoalContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoalContaining(String experimentGoal) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoalContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoalContaining(String experimentGoal, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByCreatedByContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByCreatedByContaining(String createdBy) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByCreatedByContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTExperimentSkills
	 *
	 */
	public Set<TExperimentSkill> findAllTExperimentSkills() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExperimentSkills
	 *
	 */
	public Set<TExperimentSkill> findAllTExperimentSkills(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByDuedate
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByDuedate(java.util.Calendar duedate) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByDuedate
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByDuedate(Calendar duedate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillBySiteId
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillBySiteId(Integer siteId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillBySiteId
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillBySiteId(Integer siteId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNoContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNoContaining(String experimentNo) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNoContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNoContaining(String experimentNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentName
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentName(String experimentName) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentName
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentName(String experimentName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNo
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNo(String experimentNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNo
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNo(String experimentNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoal
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoal(String experimentGoal_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentGoal
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentGoal(String experimentGoal_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByPrimaryKey
	 *
	 */
	public TExperimentSkill findTExperimentSkillByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByPrimaryKey
	 *
	 */
	public TExperimentSkill findTExperimentSkillByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNameContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNameContaining(String experimentName_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentNameContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentNameContaining(String experimentName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByCreatedBy
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByCreatedBy(String createdBy_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByCreatedBy
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByCreatedBy(String createdBy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByChapterId
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByChapterId(Integer chapterId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByChapterId
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByChapterId(Integer chapterId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersionContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersionContaining(String experimentVersion_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentVersionContaining
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentVersionContaining(String experimentVersion_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentStatus
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentStatus(Integer experimentStatus) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentStatus
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentStatus(Integer experimentStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByStartdate
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByStartdate(java.util.Calendar startdate) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByStartdate
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByStartdate(Calendar startdate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillBySort
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillBySort(Integer sort) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillBySort
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillBySort(Integer sort, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentDescribe
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentDescribe(String experimentDescribe) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillByExperimentDescribe
	 *
	 */
	public Set<TExperimentSkill> findTExperimentSkillByExperimentDescribe(String experimentDescribe, int startResult, int maxRows) throws DataAccessException;

}