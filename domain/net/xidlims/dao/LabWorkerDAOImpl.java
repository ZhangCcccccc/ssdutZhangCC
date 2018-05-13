package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabWorker;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabWorker entities.
 * 
 */
@Repository("LabWorkerDAO")
@Transactional
public class LabWorkerDAOImpl extends AbstractJpaDao<LabWorker> implements
		LabWorkerDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabWorker.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabWorkerDAOImpl
	 *
	 */
	public LabWorkerDAOImpl() {
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
	 * JPQL Query - findLabWorkerById
	 *
	 */
	@Transactional
	public LabWorker findLabWorkerById(Integer id) throws DataAccessException {

		return findLabWorkerById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerById
	 *
	 */

	@Transactional
	public LabWorker findLabWorkerById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabWorkerById", startResult, maxRows, id);
			return (net.xidlims.domain.LabWorker) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabWorkerByLwSex
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSex(String lwSex) throws DataAccessException {

		return findLabWorkerByLwSex(lwSex, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwSex
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSex(String lwSex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwSex", startResult, maxRows, lwSex);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRewardTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRewardTime(java.util.Calendar lwRewardTime) throws DataAccessException {

		return findLabWorkerByLwRewardTime(lwRewardTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRewardTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRewardTime(java.util.Calendar lwRewardTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRewardTime", startResult, maxRows, lwRewardTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwPaperQuantity
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwPaperQuantity(Integer lwPaperQuantity) throws DataAccessException {

		return findLabWorkerByLwPaperQuantity(lwPaperQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwPaperQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwPaperQuantity(Integer lwPaperQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwPaperQuantity", startResult, maxRows, lwPaperQuantity);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwMajor
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwMajor(String lwMajor) throws DataAccessException {

		return findLabWorkerByLwMajor(lwMajor, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwMajor
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwMajor(String lwMajor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwMajor", startResult, maxRows, lwMajor);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTime(String lwTrainInformalInlandTime) throws DataAccessException {

		return findLabWorkerByLwTrainInformalInlandTime(lwTrainInformalInlandTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTime(String lwTrainInformalInlandTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainInformalInlandTime", startResult, maxRows, lwTrainInformalInlandTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayAfter
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBirthdayAfter(java.util.Calendar lwBirthday) throws DataAccessException {

		return findLabWorkerByLwBirthdayAfter(lwBirthday, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBirthdayAfter(java.util.Calendar lwBirthday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwBirthdayAfter", startResult, maxRows, lwBirthday);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOne
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumOne(String lwRemarkNumOne) throws DataAccessException {

		return findLabWorkerByLwRemarkNumOne(lwRemarkNumOne, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOne
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumOne(String lwRemarkNumOne, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRemarkNumOne", startResult, maxRows, lwRemarkNumOne);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpert
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCategoryExpert(String lwCategoryExpert) throws DataAccessException {

		return findLabWorkerByLwCategoryExpert(lwCategoryExpert, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpert
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCategoryExpert(String lwCategoryExpert, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwCategoryExpert", startResult, maxRows, lwCategoryExpert);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTimeContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTimeContaining(String lwTrainFormalInlandTime) throws DataAccessException {

		return findLabWorkerByLwTrainFormalInlandTimeContaining(lwTrainFormalInlandTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTimeContaining(String lwTrainFormalInlandTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainFormalInlandTimeContaining", startResult, maxRows, lwTrainFormalInlandTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationTime(java.util.Calendar lwGraduationTime) throws DataAccessException {

		return findLabWorkerByLwGraduationTime(lwGraduationTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationTime(java.util.Calendar lwGraduationTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwGraduationTime", startResult, maxRows, lwGraduationTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwSexContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSexContaining(String lwSex) throws DataAccessException {

		return findLabWorkerByLwSexContaining(lwSex, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwSexContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSexContaining(String lwSex, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwSexContaining", startResult, maxRows, lwSex);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustom
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCodeCustom(String lwCodeCustom) throws DataAccessException {

		return findLabWorkerByLwCodeCustom(lwCodeCustom, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustom
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCodeCustom(String lwCodeCustom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwCodeCustom", startResult, maxRows, lwCodeCustom);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByPrimaryKey
	 *
	 */
	@Transactional
	public LabWorker findLabWorkerByPrimaryKey(Integer id) throws DataAccessException {

		return findLabWorkerByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByPrimaryKey
	 *
	 */

	@Transactional
	public LabWorker findLabWorkerByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabWorkerByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabWorker) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialtyContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialtyContaining(String lwProfessionSpecialty) throws DataAccessException {

		return findLabWorkerByLwProfessionSpecialtyContaining(lwProfessionSpecialty, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialtyContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialtyContaining(String lwProfessionSpecialty, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwProfessionSpecialtyContaining", startResult, maxRows, lwProfessionSpecialty);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwBookQuantity
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBookQuantity(Integer lwBookQuantity) throws DataAccessException {

		return findLabWorkerByLwBookQuantity(lwBookQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwBookQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBookQuantity(Integer lwBookQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwBookQuantity", startResult, maxRows, lwBookQuantity);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchool
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationSchool(String lwGraduationSchool) throws DataAccessException {

		return findLabWorkerByLwGraduationSchool(lwGraduationSchool, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchool
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationSchool(String lwGraduationSchool, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwGraduationSchool", startResult, maxRows, lwGraduationSchool);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwLabProject
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwLabProject(String lwLabProject) throws DataAccessException {

		return findLabWorkerByLwLabProject(lwLabProject, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwLabProject
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwLabProject(String lwLabProject, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwLabProject", startResult, maxRows, lwLabProject);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadContent
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadContent(String lwTrainInformalAbroadContent) throws DataAccessException {

		return findLabWorkerByLwTrainInformalAbroadContent(lwTrainInformalAbroadContent, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadContent(String lwTrainInformalAbroadContent, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainInformalAbroadContent", startResult, maxRows, lwTrainInformalAbroadContent);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevelContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSkillLevelContaining(String lwSkillLevel) throws DataAccessException {

		return findLabWorkerByLwSkillLevelContaining(lwSkillLevel, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevelContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSkillLevelContaining(String lwSkillLevel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwSkillLevelContaining", startResult, maxRows, lwSkillLevel);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTime(String lwTrainFormalAbroadTime) throws DataAccessException {

		return findLabWorkerByLwTrainFormalAbroadTime(lwTrainFormalAbroadTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTime(String lwTrainFormalAbroadTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainFormalAbroadTime", startResult, maxRows, lwTrainFormalAbroadTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwWorkTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwWorkTime(java.util.Calendar lwWorkTime) throws DataAccessException {

		return findLabWorkerByLwWorkTime(lwWorkTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwWorkTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwWorkTime(java.util.Calendar lwWorkTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwWorkTime", startResult, maxRows, lwWorkTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwo
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwo(String lwRemarkNumTwo) throws DataAccessException {

		return findLabWorkerByLwRemarkNumTwo(lwRemarkNumTwo, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwo(String lwRemarkNumTwo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRemarkNumTwo", startResult, maxRows, lwRemarkNumTwo);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwBirthday
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBirthday(java.util.Calendar lwBirthday) throws DataAccessException {

		return findLabWorkerByLwBirthday(lwBirthday, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwBirthday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBirthday(java.util.Calendar lwBirthday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwBirthday", startResult, maxRows, lwBirthday);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandContent
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandContent(String lwTrainInformalInlandContent) throws DataAccessException {

		return findLabWorkerByLwTrainInformalInlandContent(lwTrainInformalInlandContent, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandContent(String lwTrainInformalInlandContent, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainInformalInlandContent", startResult, maxRows, lwTrainInformalInlandContent);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTime(String lwTrainInformalAbroadTime) throws DataAccessException {

		return findLabWorkerByLwTrainInformalAbroadTime(lwTrainInformalAbroadTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTime(String lwTrainInformalAbroadTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainInformalAbroadTime", startResult, maxRows, lwTrainInformalAbroadTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkOne
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkOne(String lwRemarkOne) throws DataAccessException {

		return findLabWorkerByLwRemarkOne(lwRemarkOne, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkOne
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkOne(String lwRemarkOne, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRemarkOne", startResult, maxRows, lwRemarkOne);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTimeContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTimeContaining(String lwTrainFormalAbroadTime) throws DataAccessException {

		return findLabWorkerByLwTrainFormalAbroadTimeContaining(lwTrainFormalAbroadTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalAbroadTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalAbroadTimeContaining(String lwTrainFormalAbroadTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainFormalAbroadTimeContaining", startResult, maxRows, lwTrainFormalAbroadTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTime(String lwTrainFormalInlandTime) throws DataAccessException {

		return findLabWorkerByLwTrainFormalInlandTime(lwTrainFormalInlandTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainFormalInlandTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainFormalInlandTime(String lwTrainFormalInlandTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainFormalInlandTime", startResult, maxRows, lwTrainFormalInlandTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwDutyContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwDutyContaining(String lwDuty) throws DataAccessException {

		return findLabWorkerByLwDutyContaining(lwDuty, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwDutyContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwDutyContaining(String lwDuty, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwDutyContaining", startResult, maxRows, lwDuty);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwMajorContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwMajorContaining(String lwMajor) throws DataAccessException {

		return findLabWorkerByLwMajorContaining(lwMajor, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwMajorContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwMajorContaining(String lwMajor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwMajorContaining", startResult, maxRows, lwMajor);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwName
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwName(String lwName) throws DataAccessException {

		return findLabWorkerByLwName(lwName, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwName(String lwName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwName", startResult, maxRows, lwName);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwProfessionTime
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwProfessionTime(java.util.Calendar lwProfessionTime) throws DataAccessException {

		return findLabWorkerByLwProfessionTime(lwProfessionTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwProfessionTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwProfessionTime(java.util.Calendar lwProfessionTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwProfessionTime", startResult, maxRows, lwProfessionTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwoContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwoContaining(String lwRemarkNumTwo) throws DataAccessException {

		return findLabWorkerByLwRemarkNumTwoContaining(lwRemarkNumTwo, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumTwoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumTwoContaining(String lwRemarkNumTwo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRemarkNumTwoContaining", startResult, maxRows, lwRemarkNumTwo);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTimeContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTimeContaining(String lwTrainInformalAbroadTime) throws DataAccessException {

		return findLabWorkerByLwTrainInformalAbroadTimeContaining(lwTrainInformalAbroadTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalAbroadTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalAbroadTimeContaining(String lwTrainInformalAbroadTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainInformalAbroadTimeContaining", startResult, maxRows, lwTrainInformalAbroadTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTimeContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTimeContaining(String lwTrainInformalInlandTime) throws DataAccessException {

		return findLabWorkerByLwTrainInformalInlandTimeContaining(lwTrainInformalInlandTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwTrainInformalInlandTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwTrainInformalInlandTimeContaining(String lwTrainInformalInlandTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwTrainInformalInlandTimeContaining", startResult, maxRows, lwTrainInformalInlandTime);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevel
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSkillLevel(String lwSkillLevel) throws DataAccessException {

		return findLabWorkerByLwSkillLevel(lwSkillLevel, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwSkillLevel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwSkillLevel(String lwSkillLevel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwSkillLevel", startResult, maxRows, lwSkillLevel);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpertContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCategoryExpertContaining(String lwCategoryExpert) throws DataAccessException {

		return findLabWorkerByLwCategoryExpertContaining(lwCategoryExpert, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwCategoryExpertContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCategoryExpertContaining(String lwCategoryExpert, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwCategoryExpertContaining", startResult, maxRows, lwCategoryExpert);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwWorkAge
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwWorkAge(Integer lwWorkAge) throws DataAccessException {

		return findLabWorkerByLwWorkAge(lwWorkAge, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwWorkAge
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwWorkAge(Integer lwWorkAge, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwWorkAge", startResult, maxRows, lwWorkAge);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajorContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationMajorContaining(String lwGraduationMajor) throws DataAccessException {

		return findLabWorkerByLwGraduationMajorContaining(lwGraduationMajor, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajorContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationMajorContaining(String lwGraduationMajor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwGraduationMajorContaining", startResult, maxRows, lwGraduationMajor);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkTwo
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkTwo(String lwRemarkTwo) throws DataAccessException {

		return findLabWorkerByLwRemarkTwo(lwRemarkTwo, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkTwo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkTwo(String lwRemarkTwo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRemarkTwo", startResult, maxRows, lwRemarkTwo);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabWorkers
	 *
	 */
	@Transactional
	public Set<LabWorker> findAllLabWorkers() throws DataAccessException {

		return findAllLabWorkers(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabWorkers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findAllLabWorkers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabWorkers", startResult, maxRows);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchoolContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationSchoolContaining(String lwGraduationSchool) throws DataAccessException {

		return findLabWorkerByLwGraduationSchoolContaining(lwGraduationSchool, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationSchoolContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationSchoolContaining(String lwGraduationSchool, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwGraduationSchoolContaining", startResult, maxRows, lwGraduationSchool);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustomContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCodeCustomContaining(String lwCodeCustom) throws DataAccessException {

		return findLabWorkerByLwCodeCustomContaining(lwCodeCustom, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwCodeCustomContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwCodeCustomContaining(String lwCodeCustom, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwCodeCustomContaining", startResult, maxRows, lwCodeCustom);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOneContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumOneContaining(String lwRemarkNumOne) throws DataAccessException {

		return findLabWorkerByLwRemarkNumOneContaining(lwRemarkNumOne, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwRemarkNumOneContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwRemarkNumOneContaining(String lwRemarkNumOne, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwRemarkNumOneContaining", startResult, maxRows, lwRemarkNumOne);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayBefore
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBirthdayBefore(java.util.Calendar lwBirthday) throws DataAccessException {

		return findLabWorkerByLwBirthdayBefore(lwBirthday, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwBirthdayBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwBirthdayBefore(java.util.Calendar lwBirthday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwBirthdayBefore", startResult, maxRows, lwBirthday);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwLabProjectContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwLabProjectContaining(String lwLabProject) throws DataAccessException {

		return findLabWorkerByLwLabProjectContaining(lwLabProject, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwLabProjectContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwLabProjectContaining(String lwLabProject, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwLabProjectContaining", startResult, maxRows, lwLabProject);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajor
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationMajor(String lwGraduationMajor) throws DataAccessException {

		return findLabWorkerByLwGraduationMajor(lwGraduationMajor, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwGraduationMajor
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwGraduationMajor(String lwGraduationMajor, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwGraduationMajor", startResult, maxRows, lwGraduationMajor);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwDuty
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwDuty(String lwDuty) throws DataAccessException {

		return findLabWorkerByLwDuty(lwDuty, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwDuty
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwDuty(String lwDuty, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwDuty", startResult, maxRows, lwDuty);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialty
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialty(String lwProfessionSpecialty) throws DataAccessException {

		return findLabWorkerByLwProfessionSpecialty(lwProfessionSpecialty, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwProfessionSpecialty
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwProfessionSpecialty(String lwProfessionSpecialty, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwProfessionSpecialty", startResult, maxRows, lwProfessionSpecialty);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabWorkerByLwNameContaining
	 *
	 */
	@Transactional
	public Set<LabWorker> findLabWorkerByLwNameContaining(String lwName) throws DataAccessException {

		return findLabWorkerByLwNameContaining(lwName, -1, -1);
	}

	/**
	 * JPQL Query - findLabWorkerByLwNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabWorker> findLabWorkerByLwNameContaining(String lwName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabWorkerByLwNameContaining", startResult, maxRows, lwName);
		return new LinkedHashSet<LabWorker>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabWorker entity) {
		return true;
	}
}
