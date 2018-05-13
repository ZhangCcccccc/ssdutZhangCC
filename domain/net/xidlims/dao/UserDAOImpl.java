package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.User;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage User entities.
 * 
 */
@Repository("UserDAO")
@Transactional
public class UserDAOImpl extends AbstractJpaDao<User> implements UserDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { User.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new UserDAOImpl
	 *
	 */
	public UserDAOImpl() {
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
	 * JPQL Query - findUserByLastLogin
	 *
	 */
	@Transactional
	public Set<User> findUserByLastLogin(java.util.Calendar lastLogin) throws DataAccessException {

		return findUserByLastLogin(lastLogin, -1, -1);
	}

	/**
	 * JPQL Query - findUserByLastLogin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByLastLogin(java.util.Calendar lastLogin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByLastLogin", startResult, maxRows, lastLogin);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserStatus
	 *
	 */
	@Transactional
	public Set<User> findUserByUserStatus(String userStatus) throws DataAccessException {

		return findUserByUserStatus(userStatus, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserStatus(String userStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserStatus", startResult, maxRows, userStatus);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByTelephoneContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByTelephoneContaining(String telephone) throws DataAccessException {

		return findUserByTelephoneContaining(telephone, -1, -1);
	}

	/**
	 * JPQL Query - findUserByTelephoneContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByTelephoneContaining(String telephone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByTelephoneContaining", startResult, maxRows, telephone);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByEmail
	 *
	 */
	@Transactional
	public Set<User> findUserByEmail(String email) throws DataAccessException {

		return findUserByEmail(email, -1, -1);
	}

	/**
	 * JPQL Query - findUserByEmail
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByEmail(String email, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByEmail", startResult, maxRows, email);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllUsers
	 *
	 */
	@Transactional
	public Set<User> findAllUsers() throws DataAccessException {

		return findAllUsers(-1, -1);
	}

	/**
	 * JPQL Query - findAllUsers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findAllUsers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllUsers", startResult, maxRows);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByAttendanceTime
	 *
	 */
	@Transactional
	public Set<User> findUserByAttendanceTime(String attendanceTime) throws DataAccessException {

		return findUserByAttendanceTime(attendanceTime, -1, -1);
	}

	/**
	 * JPQL Query - findUserByAttendanceTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByAttendanceTime(String attendanceTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByAttendanceTime", startResult, maxRows, attendanceTime);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUsernameContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByUsernameContaining(String username) throws DataAccessException {

		return findUserByUsernameContaining(username, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUsernameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUsernameContaining(String username, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUsernameContaining", startResult, maxRows, username);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByCardno
	 *
	 */
	@Transactional
	public Set<User> findUserByCardno(String cardno) throws DataAccessException {

		return findUserByCardno(cardno, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCardno
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCardno(String cardno, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCardno", startResult, maxRows, cardno);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByPassword
	 *
	 */
	@Transactional
	public Set<User> findUserByPassword(String password) throws DataAccessException {

		return findUserByPassword(password, -1, -1);
	}

	/**
	 * JPQL Query - findUserByPassword
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByPassword(String password, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByPassword", startResult, maxRows, password);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByIfEnrollmentContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByIfEnrollmentContaining(String ifEnrollment) throws DataAccessException {

		return findUserByIfEnrollmentContaining(ifEnrollment, -1, -1);
	}

	/**
	 * JPQL Query - findUserByIfEnrollmentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByIfEnrollmentContaining(String ifEnrollment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByIfEnrollmentContaining", startResult, maxRows, ifEnrollment);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByCname
	 *
	 */
	@Transactional
	public Set<User> findUserByCname(String cname) throws DataAccessException {

		return findUserByCname(cname, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCname
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCname(String cname, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCname", startResult, maxRows, cname);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserStatusContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByUserStatusContaining(String userStatus) throws DataAccessException {

		return findUserByUserStatusContaining(userStatus, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserStatusContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserStatusContaining(String userStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserStatusContaining", startResult, maxRows, userStatus);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByCardnoContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByCardnoContaining(String cardno) throws DataAccessException {

		return findUserByCardnoContaining(cardno, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCardnoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCardnoContaining(String cardno, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCardnoContaining", startResult, maxRows, cardno);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserRole
	 *
	 */
	@Transactional
	public Set<User> findUserByUserRole(String userRole) throws DataAccessException {

		return findUserByUserRole(userRole, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserRole
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserRole(String userRole, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserRole", startResult, maxRows, userRole);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByMajorDirection
	 *
	 */
	@Transactional
	public Set<User> findUserByMajorDirection(String majorDirection) throws DataAccessException {

		return findUserByMajorDirection(majorDirection, -1, -1);
	}

	/**
	 * JPQL Query - findUserByMajorDirection
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByMajorDirection(String majorDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByMajorDirection", startResult, maxRows, majorDirection);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByMajorDirectionContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByMajorDirectionContaining(String majorDirection) throws DataAccessException {

		return findUserByMajorDirectionContaining(majorDirection, -1, -1);
	}

	/**
	 * JPQL Query - findUserByMajorDirectionContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByMajorDirectionContaining(String majorDirection, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByMajorDirectionContaining", startResult, maxRows, majorDirection);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByEmailContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByEmailContaining(String email) throws DataAccessException {

		return findUserByEmailContaining(email, -1, -1);
	}

	/**
	 * JPQL Query - findUserByEmailContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByEmailContaining(String email, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByEmailContaining", startResult, maxRows, email);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByCreatedAt
	 *
	 */
	@Transactional
	public Set<User> findUserByCreatedAt(java.util.Calendar createdAt) throws DataAccessException {

		return findUserByCreatedAt(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCreatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCreatedAt(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCreatedAt", startResult, maxRows, createdAt);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByCreatedAtBefore
	 *
	 */
	@Transactional
	public Set<User> findUserByCreatedAtBefore(java.util.Calendar createdAt) throws DataAccessException {

		return findUserByCreatedAtBefore(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCreatedAtBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCreatedAtBefore(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCreatedAtBefore", startResult, maxRows, createdAt);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByTeacherNumber
	 *
	 */
	@Transactional
	public Set<User> findUserByTeacherNumber(Integer teacherNumber) throws DataAccessException {

		return findUserByTeacherNumber(teacherNumber, -1, -1);
	}

	/**
	 * JPQL Query - findUserByTeacherNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByTeacherNumber(Integer teacherNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByTeacherNumber", startResult, maxRows, teacherNumber);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByPrimaryKey
	 *
	 */
	@Transactional
	public User findUserByPrimaryKey(String username) throws DataAccessException {

		return findUserByPrimaryKey(username, -1, -1);
	}

	/**
	 * JPQL Query - findUserByPrimaryKey
	 *
	 */

	@Transactional
	public User findUserByPrimaryKey(String username, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findUserByPrimaryKey", startResult, maxRows, username);
			return (net.xidlims.domain.User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findUserByCreatedAtAfter
	 *
	 */
	@Transactional
	public Set<User> findUserByCreatedAtAfter(java.util.Calendar createdAt) throws DataAccessException {

		return findUserByCreatedAtAfter(createdAt, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCreatedAtAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCreatedAtAfter(java.util.Calendar createdAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCreatedAtAfter", startResult, maxRows, createdAt);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByIfEnrollment
	 *
	 */
	@Transactional
	public Set<User> findUserByIfEnrollment(String ifEnrollment) throws DataAccessException {

		return findUserByIfEnrollment(ifEnrollment, -1, -1);
	}

	/**
	 * JPQL Query - findUserByIfEnrollment
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByIfEnrollment(String ifEnrollment, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByIfEnrollment", startResult, maxRows, ifEnrollment);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUpdatedAtBefore
	 *
	 */
	@Transactional
	public Set<User> findUserByUpdatedAtBefore(java.util.Calendar updatedAt) throws DataAccessException {

		return findUserByUpdatedAtBefore(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUpdatedAtBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUpdatedAtBefore(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUpdatedAtBefore", startResult, maxRows, updatedAt);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByGradeContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByGradeContaining(String grade) throws DataAccessException {

		return findUserByGradeContaining(grade, -1, -1);
	}

	/**
	 * JPQL Query - findUserByGradeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByGradeContaining(String grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByGradeContaining", startResult, maxRows, grade);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserType
	 *
	 */
	@Transactional
	public Set<User> findUserByUserType(Integer userType) throws DataAccessException {

		return findUserByUserType(userType, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserType(Integer userType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserType", startResult, maxRows, userType);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByEnrollmentStatus
	 *
	 */
	@Transactional
	public Set<User> findUserByEnrollmentStatus(Integer enrollmentStatus) throws DataAccessException {

		return findUserByEnrollmentStatus(enrollmentStatus, -1, -1);
	}

	/**
	 * JPQL Query - findUserByEnrollmentStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByEnrollmentStatus(Integer enrollmentStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByEnrollmentStatus", startResult, maxRows, enrollmentStatus);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUsername
	 *
	 */
	@Transactional
	public User findUserByUsername(String username) throws DataAccessException {

		return findUserByUsername(username, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUsername
	 *
	 */

	@Transactional
	public User findUserByUsername(String username, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findUserByUsername", startResult, maxRows, username);
			return (net.xidlims.domain.User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findUserByEnabled
	 *
	 */
	@Transactional
	public Set<User> findUserByEnabled(Boolean enabled) throws DataAccessException {

		return findUserByEnabled(enabled, -1, -1);
	}

	/**
	 * JPQL Query - findUserByEnabled
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByEnabled(Boolean enabled, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByEnabled", startResult, maxRows, enabled);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByGrade
	 *
	 */
	@Transactional
	public Set<User> findUserByGrade(String grade) throws DataAccessException {

		return findUserByGrade(grade, -1, -1);
	}

	/**
	 * JPQL Query - findUserByGrade
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByGrade(String grade, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByGrade", startResult, maxRows, grade);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByPasswordContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByPasswordContaining(String password) throws DataAccessException {

		return findUserByPasswordContaining(password, -1, -1);
	}

	/**
	 * JPQL Query - findUserByPasswordContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByPasswordContaining(String password, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByPasswordContaining", startResult, maxRows, password);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByTelephone
	 *
	 */
	@Transactional
	public Set<User> findUserByTelephone(String telephone) throws DataAccessException {

		return findUserByTelephone(telephone, -1, -1);
	}

	/**
	 * JPQL Query - findUserByTelephone
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByTelephone(String telephone, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByTelephone", startResult, maxRows, telephone);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUpdatedAtAfter
	 *
	 */
	@Transactional
	public Set<User> findUserByUpdatedAtAfter(java.util.Calendar updatedAt) throws DataAccessException {

		return findUserByUpdatedAtAfter(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUpdatedAtAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUpdatedAtAfter(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUpdatedAtAfter", startResult, maxRows, updatedAt);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByAttendanceTimeContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByAttendanceTimeContaining(String attendanceTime) throws DataAccessException {

		return findUserByAttendanceTimeContaining(attendanceTime, -1, -1);
	}

	/**
	 * JPQL Query - findUserByAttendanceTimeContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByAttendanceTimeContaining(String attendanceTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByAttendanceTimeContaining", startResult, maxRows, attendanceTime);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByMajorNumber
	 *
	 */
	@Transactional
	public Set<User> findUserByMajorNumber(String majorNumber) throws DataAccessException {

		return findUserByMajorNumber(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findUserByMajorNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByMajorNumber(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByMajorNumber", startResult, maxRows, majorNumber);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUpdatedAt
	 *
	 */
	@Transactional
	public Set<User> findUserByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException {

		return findUserByUpdatedAt(updatedAt, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUpdatedAt
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUpdatedAt(java.util.Calendar updatedAt, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUpdatedAt", startResult, maxRows, updatedAt);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserRoleContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByUserRoleContaining(String userRole) throws DataAccessException {

		return findUserByUserRoleContaining(userRole, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserRoleContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserRoleContaining(String userRole, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserRoleContaining", startResult, maxRows, userRole);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserSexy
	 *
	 */
	@Transactional
	public Set<User> findUserByUserSexy(String userSexy) throws DataAccessException {

		return findUserByUserSexy(userSexy, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserSexy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserSexy(String userSexy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserSexy", startResult, maxRows, userSexy);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByUserSexyContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByUserSexyContaining(String userSexy) throws DataAccessException {

		return findUserByUserSexyContaining(userSexy, -1, -1);
	}

	/**
	 * JPQL Query - findUserByUserSexyContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByUserSexyContaining(String userSexy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByUserSexyContaining", startResult, maxRows, userSexy);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByMajorNumberContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByMajorNumberContaining(String majorNumber) throws DataAccessException {

		return findUserByMajorNumberContaining(majorNumber, -1, -1);
	}

	/**
	 * JPQL Query - findUserByMajorNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByMajorNumberContaining(String majorNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByMajorNumberContaining", startResult, maxRows, majorNumber);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * JPQL Query - findUserByCnameContaining
	 *
	 */
	@Transactional
	public Set<User> findUserByCnameContaining(String cname) throws DataAccessException {

		return findUserByCnameContaining(cname, -1, -1);
	}

	/**
	 * JPQL Query - findUserByCnameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<User> findUserByCnameContaining(String cname, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findUserByCnameContaining", startResult, maxRows, cname);
		return new LinkedHashSet<User>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(User entity) {
		return true;
	}
}
