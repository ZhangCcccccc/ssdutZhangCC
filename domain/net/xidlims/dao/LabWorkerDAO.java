package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabWorker;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabWorker entities.
 * 
 */
public interface LabWorkerDAO extends JpaDao<LabWorker> {

	/**
	 * JPQL Query - findLabWorkerById
	 *
	 */
	public LabWorker findLabWorkerById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerById
	 *
	 */
	public LabWorker findLabWorkerById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSex
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSex(String lwSex) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSex
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSex(String lwSex, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRewardTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRewardTime(java.util.Calendar lwRewardTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRewardTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRewardTime(Calendar lwRewardTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwPaperQuantity
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwPaperQuantity(Integer lwPaperQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwPaperQuantity
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwPaperQuantity(Integer lwPaperQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwMajor
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwMajor(String lwMajor) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwMajor
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwMajor(String lwMajor, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTime(String lwTrainInformalInlandTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTime(String lwTrainInformalInlandTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayAfter
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBirthdayAfter(java.util.Calendar lwBirthday) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayAfter
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBirthdayAfter(Calendar lwBirthday, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOne
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumOne(String lwRemarkNumOne) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOne
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumOne(String lwRemarkNumOne, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpert
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCategoryExpert(String lwCategoryExpert) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpert
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCategoryExpert(String lwCategoryExpert, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTimeContaining(String lwTrainFormalInlandTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTimeContaining(String lwTrainFormalInlandTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationTime(java.util.Calendar lwGraduationTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationTime(Calendar lwGraduationTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSexContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSexContaining(String lwSex_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSexContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSexContaining(String lwSex_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustom
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCodeCustom(String lwCodeCustom) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustom
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCodeCustom(String lwCodeCustom, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByPrimaryKey
	 *
	 */
	public LabWorker findLabWorkerByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByPrimaryKey
	 *
	 */
	public LabWorker findLabWorkerByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialtyContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialtyContaining(String lwProfessionSpecialty) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialtyContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialtyContaining(String lwProfessionSpecialty, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBookQuantity
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBookQuantity(Integer lwBookQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBookQuantity
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBookQuantity(Integer lwBookQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchool
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationSchool(String lwGraduationSchool) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchool
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationSchool(String lwGraduationSchool, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwLabProject
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwLabProject(String lwLabProject) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwLabProject
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwLabProject(String lwLabProject, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadContent
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadContent(String lwTrainInformalAbroadContent) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadContent
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadContent(String lwTrainInformalAbroadContent, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevelContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSkillLevelContaining(String lwSkillLevel) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevelContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSkillLevelContaining(String lwSkillLevel, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTime(String lwTrainFormalAbroadTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTime(String lwTrainFormalAbroadTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwWorkTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwWorkTime(java.util.Calendar lwWorkTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwWorkTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwWorkTime(Calendar lwWorkTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwo
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwo(String lwRemarkNumTwo) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwo
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwo(String lwRemarkNumTwo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBirthday
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBirthday(java.util.Calendar lwBirthday_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBirthday
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBirthday(Calendar lwBirthday_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandContent
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandContent(String lwTrainInformalInlandContent) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandContent
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandContent(String lwTrainInformalInlandContent, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTime(String lwTrainInformalAbroadTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTime(String lwTrainInformalAbroadTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkOne
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkOne(String lwRemarkOne) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkOne
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkOne(String lwRemarkOne, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTimeContaining(String lwTrainFormalAbroadTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTimeContaining(String lwTrainFormalAbroadTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTime(String lwTrainFormalInlandTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTime(String lwTrainFormalInlandTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwDutyContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwDutyContaining(String lwDuty) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwDutyContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwDutyContaining(String lwDuty, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwMajorContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwMajorContaining(String lwMajor_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwMajorContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwMajorContaining(String lwMajor_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwName
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwName(String lwName) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwName
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwName(String lwName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwProfessionTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwProfessionTime(java.util.Calendar lwProfessionTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwProfessionTime
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwProfessionTime(Calendar lwProfessionTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwoContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwoContaining(String lwRemarkNumTwo_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwoContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwoContaining(String lwRemarkNumTwo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTimeContaining(String lwTrainInformalAbroadTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTimeContaining(String lwTrainInformalAbroadTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTimeContaining(String lwTrainInformalInlandTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTimeContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTimeContaining(String lwTrainInformalInlandTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevel
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSkillLevel(String lwSkillLevel_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevel
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwSkillLevel(String lwSkillLevel_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpertContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCategoryExpertContaining(String lwCategoryExpert_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpertContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCategoryExpertContaining(String lwCategoryExpert_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwWorkAge
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwWorkAge(Integer lwWorkAge) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwWorkAge
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwWorkAge(Integer lwWorkAge, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajorContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationMajorContaining(String lwGraduationMajor) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajorContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationMajorContaining(String lwGraduationMajor, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkTwo
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkTwo(String lwRemarkTwo) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkTwo
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkTwo(String lwRemarkTwo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabWorkers
	 *
	 */
	public Set<LabWorker> findAllLabWorkers() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabWorkers
	 *
	 */
	public Set<LabWorker> findAllLabWorkers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchoolContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationSchoolContaining(String lwGraduationSchool_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchoolContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationSchoolContaining(String lwGraduationSchool_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustomContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCodeCustomContaining(String lwCodeCustom_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustomContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwCodeCustomContaining(String lwCodeCustom_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOneContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumOneContaining(String lwRemarkNumOne_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOneContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwRemarkNumOneContaining(String lwRemarkNumOne_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayBefore
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBirthdayBefore(java.util.Calendar lwBirthday_2) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayBefore
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwBirthdayBefore(Calendar lwBirthday_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwLabProjectContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwLabProjectContaining(String lwLabProject_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwLabProjectContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwLabProjectContaining(String lwLabProject_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajor
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationMajor(String lwGraduationMajor_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajor
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwGraduationMajor(String lwGraduationMajor_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwDuty
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwDuty(String lwDuty_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwDuty
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwDuty(String lwDuty_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialty
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialty(String lwProfessionSpecialty_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialty
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialty(String lwProfessionSpecialty_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwNameContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwNameContaining(String lwName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabWorkerByLwNameContaining
	 *
	 */
	public Set<LabWorker> findLabWorkerByLwNameContaining(String lwName_1, int startResult, int maxRows) throws DataAccessException;

}