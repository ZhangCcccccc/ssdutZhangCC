package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomLog;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomLog entities.
 * 
 */
@Repository("LabRoomLogDAO")
@Transactional
public class LabRoomLogDAOImpl extends AbstractJpaDao<LabRoomLog> implements
		LabRoomLogDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomLog.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomLogDAOImpl
	 *
	 */
	public LabRoomLogDAOImpl() {
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
	 * JPQL Query - findLabRoomLogByModule
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByModule(String module) throws DataAccessException {

		return findLabRoomLogByModule(module, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByModule
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByModule(String module, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByModule", startResult, maxRows, module);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByDayS
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDayS(Integer dayS) throws DataAccessException {

		return findLabRoomLogByDayS(dayS, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByDayS
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDayS(Integer dayS, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByDayS", startResult, maxRows, dayS);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogBySchoolCourse
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogBySchoolCourse(Integer schoolCourse) throws DataAccessException {

		return findLabRoomLogBySchoolCourse(schoolCourse, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogBySchoolCourse
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogBySchoolCourse(Integer schoolCourse, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogBySchoolCourse", startResult, maxRows, schoolCourse);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomLogs
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findAllLabRoomLogs() throws DataAccessException {

		return findAllLabRoomLogs(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomLogs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findAllLabRoomLogs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomLogs", startResult, maxRows);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByDateAfter
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDateAfter(java.util.Calendar date) throws DataAccessException {

		return findLabRoomLogByDateAfter(date, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDateAfter(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByDateAfter", startResult, maxRows, date);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByCreateTime
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findLabRoomLogByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogBySectionSContaining
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogBySectionSContaining(String sectionS) throws DataAccessException {

		return findLabRoomLogBySectionSContaining(sectionS, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogBySectionSContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogBySectionSContaining(String sectionS, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogBySectionSContaining", startResult, maxRows, sectionS);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByActionContaining
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByActionContaining(String action) throws DataAccessException {

		return findLabRoomLogByActionContaining(action, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByActionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByActionContaining(String action, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByActionContaining", startResult, maxRows, action);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomLog findLabRoomLogByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomLogByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomLog findLabRoomLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomLogByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomLog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomLogByDateBefore
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDateBefore(java.util.Calendar date) throws DataAccessException {

		return findLabRoomLogByDateBefore(date, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDateBefore(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByDateBefore", startResult, maxRows, date);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByDate
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDate(java.util.Calendar date) throws DataAccessException {

		return findLabRoomLogByDate(date, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByDate(java.util.Calendar date, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByDate", startResult, maxRows, date);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByStudentNoContaining
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByStudentNoContaining(String studentNo) throws DataAccessException {

		return findLabRoomLogByStudentNoContaining(studentNo, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByStudentNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByStudentNoContaining(String studentNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByStudentNoContaining", startResult, maxRows, studentNo);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByStudentNo
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByStudentNo(String studentNo) throws DataAccessException {

		return findLabRoomLogByStudentNo(studentNo, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByStudentNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByStudentNo(String studentNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByStudentNo", startResult, maxRows, studentNo);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogBySectionS
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogBySectionS(String sectionS) throws DataAccessException {

		return findLabRoomLogBySectionS(sectionS, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogBySectionS
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogBySectionS(String sectionS, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogBySectionS", startResult, maxRows, sectionS);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogById
	 *
	 */
	@Transactional
	public LabRoomLog findLabRoomLogById(Integer id) throws DataAccessException {

		return findLabRoomLogById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogById
	 *
	 */

	@Transactional
	public LabRoomLog findLabRoomLogById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomLogById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomLog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomLogByWeekS
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByWeekS(Integer weekS) throws DataAccessException {

		return findLabRoomLogByWeekS(weekS, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByWeekS
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByWeekS(Integer weekS, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByWeekS", startResult, maxRows, weekS);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByAction
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByAction(String action) throws DataAccessException {

		return findLabRoomLogByAction(action, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByAction
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByAction(String action, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByAction", startResult, maxRows, action);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLogByModuleContaining
	 *
	 */
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByModuleContaining(String module) throws DataAccessException {

		return findLabRoomLogByModuleContaining(module, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLogByModuleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLog> findLabRoomLogByModuleContaining(String module, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLogByModuleContaining", startResult, maxRows, module);
		return new LinkedHashSet<LabRoomLog>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomLog entity) {
		return true;
	}
}
