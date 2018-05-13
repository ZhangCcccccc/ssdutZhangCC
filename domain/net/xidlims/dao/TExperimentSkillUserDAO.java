package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TExperimentSkillUser;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TExperimentSkillUser entities.
 * 
 */
public interface TExperimentSkillUserDAO extends JpaDao<TExperimentSkillUser> {

	/**
	 * JPQL Query - findAllTExperimentSkillUsers
	 *
	 */
	public Set<TExperimentSkillUser> findAllTExperimentSkillUsers() throws DataAccessException;

	/**
	 * JPQL Query - findAllTExperimentSkillUsers
	 *
	 */
	public Set<TExperimentSkillUser> findAllTExperimentSkillUsers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByUsername
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsername(String username) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByUsername
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsername(String username, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByCreateTime
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByCreateTime
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacher
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacher(String gradeTeacher) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacher
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacher(String gradeTeacher, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByPrimaryKey
	 *
	 */
	public TExperimentSkillUser findTExperimentSkillUserByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByPrimaryKey
	 *
	 */
	public TExperimentSkillUser findTExperimentSkillUserByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacherContaining
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacherContaining(String gradeTeacher_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTeacherContaining
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTeacherContaining(String gradeTeacher_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByUsernameContaining
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsernameContaining(String username_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByUsernameContaining
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByUsernameContaining(String username_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserBySkillId
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserBySkillId(Integer skillId) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserBySkillId
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserBySkillId(Integer skillId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByRealGrade
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByRealGrade(Integer realGrade) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByRealGrade
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByRealGrade(Integer realGrade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTime
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTime(java.util.Calendar gradeTime) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByGradeTime
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByGradeTime(Calendar gradeTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserById
	 *
	 */
	public TExperimentSkillUser findTExperimentSkillUserById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserById
	 *
	 */
	public TExperimentSkillUser findTExperimentSkillUserById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByFinalGrade
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByFinalGrade(Integer finalGrade) throws DataAccessException;

	/**
	 * JPQL Query - findTExperimentSkillUserByFinalGrade
	 *
	 */
	public Set<TExperimentSkillUser> findTExperimentSkillUserByFinalGrade(Integer finalGrade, int startResult, int maxRows) throws DataAccessException;

}