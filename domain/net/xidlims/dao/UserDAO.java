package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.User;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage User entities.
 * 
 */
public interface UserDAO extends JpaDao<User> {

	/**
	 * JPQL Query - findUserByLastLogin
	 *
	 */
	public Set<User> findUserByLastLogin(java.util.Calendar lastLogin) throws DataAccessException;

	/**
	 * JPQL Query - findUserByLastLogin
	 *
	 */
	public Set<User> findUserByLastLogin(Calendar lastLogin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserStatus
	 *
	 */
	public Set<User> findUserByUserStatus(String userStatus) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserStatus
	 *
	 */
	public Set<User> findUserByUserStatus(String userStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByTelephoneContaining
	 *
	 */
	public Set<User> findUserByTelephoneContaining(String telephone) throws DataAccessException;

	/**
	 * JPQL Query - findUserByTelephoneContaining
	 *
	 */
	public Set<User> findUserByTelephoneContaining(String telephone, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEmail
	 *
	 */
	public Set<User> findUserByEmail(String email) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEmail
	 *
	 */
	public Set<User> findUserByEmail(String email, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllUsers
	 *
	 */
	public Set<User> findAllUsers() throws DataAccessException;

	/**
	 * JPQL Query - findAllUsers
	 *
	 */
	public Set<User> findAllUsers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByAttendanceTime
	 *
	 */
	public Set<User> findUserByAttendanceTime(String attendanceTime) throws DataAccessException;

	/**
	 * JPQL Query - findUserByAttendanceTime
	 *
	 */
	public Set<User> findUserByAttendanceTime(String attendanceTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUsernameContaining
	 *
	 */
	public Set<User> findUserByUsernameContaining(String username) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUsernameContaining
	 *
	 */
	public Set<User> findUserByUsernameContaining(String username, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCardno
	 *
	 */
	public Set<User> findUserByCardno(String cardno) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCardno
	 *
	 */
	public Set<User> findUserByCardno(String cardno, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByPassword
	 *
	 */
	public Set<User> findUserByPassword(String password) throws DataAccessException;

	/**
	 * JPQL Query - findUserByPassword
	 *
	 */
	public Set<User> findUserByPassword(String password, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByIfEnrollmentContaining
	 *
	 */
	public Set<User> findUserByIfEnrollmentContaining(String ifEnrollment) throws DataAccessException;

	/**
	 * JPQL Query - findUserByIfEnrollmentContaining
	 *
	 */
	public Set<User> findUserByIfEnrollmentContaining(String ifEnrollment, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCname
	 *
	 */
	public Set<User> findUserByCname(String cname) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCname
	 *
	 */
	public Set<User> findUserByCname(String cname, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserStatusContaining
	 *
	 */
	public Set<User> findUserByUserStatusContaining(String userStatus_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserStatusContaining
	 *
	 */
	public Set<User> findUserByUserStatusContaining(String userStatus_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCardnoContaining
	 *
	 */
	public Set<User> findUserByCardnoContaining(String cardno_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCardnoContaining
	 *
	 */
	public Set<User> findUserByCardnoContaining(String cardno_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserRole
	 *
	 */
	public Set<User> findUserByUserRole(String userRole) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserRole
	 *
	 */
	public Set<User> findUserByUserRole(String userRole, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorDirection
	 *
	 */
	public Set<User> findUserByMajorDirection(String majorDirection) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorDirection
	 *
	 */
	public Set<User> findUserByMajorDirection(String majorDirection, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorDirectionContaining
	 *
	 */
	public Set<User> findUserByMajorDirectionContaining(String majorDirection_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorDirectionContaining
	 *
	 */
	public Set<User> findUserByMajorDirectionContaining(String majorDirection_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEmailContaining
	 *
	 */
	public Set<User> findUserByEmailContaining(String email_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEmailContaining
	 *
	 */
	public Set<User> findUserByEmailContaining(String email_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCreatedAt
	 *
	 */
	public Set<User> findUserByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCreatedAt
	 *
	 */
	public Set<User> findUserByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCreatedAtBefore
	 *
	 */
	public Set<User> findUserByCreatedAtBefore(java.util.Calendar createdAt_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCreatedAtBefore
	 *
	 */
	public Set<User> findUserByCreatedAtBefore(Calendar createdAt_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByTeacherNumber
	 *
	 */
	public Set<User> findUserByTeacherNumber(Integer teacherNumber) throws DataAccessException;

	/**
	 * JPQL Query - findUserByTeacherNumber
	 *
	 */
	public Set<User> findUserByTeacherNumber(Integer teacherNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByPrimaryKey
	 *
	 */
	public User findUserByPrimaryKey(String username_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByPrimaryKey
	 *
	 */
	public User findUserByPrimaryKey(String username_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCreatedAtAfter
	 *
	 */
	public Set<User> findUserByCreatedAtAfter(java.util.Calendar createdAt_2) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCreatedAtAfter
	 *
	 */
	public Set<User> findUserByCreatedAtAfter(Calendar createdAt_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByIfEnrollment
	 *
	 */
	public Set<User> findUserByIfEnrollment(String ifEnrollment_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByIfEnrollment
	 *
	 */
	public Set<User> findUserByIfEnrollment(String ifEnrollment_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUpdatedAtBefore
	 *
	 */
	public Set<User> findUserByUpdatedAtBefore(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUpdatedAtBefore
	 *
	 */
	public Set<User> findUserByUpdatedAtBefore(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByGradeContaining
	 *
	 */
	public Set<User> findUserByGradeContaining(String grade) throws DataAccessException;

	/**
	 * JPQL Query - findUserByGradeContaining
	 *
	 */
	public Set<User> findUserByGradeContaining(String grade, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserType
	 *
	 */
	public Set<User> findUserByUserType(Integer userType) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserType
	 *
	 */
	public Set<User> findUserByUserType(Integer userType, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEnrollmentStatus
	 *
	 */
	public Set<User> findUserByEnrollmentStatus(Integer enrollmentStatus) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEnrollmentStatus
	 *
	 */
	public Set<User> findUserByEnrollmentStatus(Integer enrollmentStatus, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUsername
	 *
	 */
	public User findUserByUsername(String username_2) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUsername
	 *
	 */
	public User findUserByUsername(String username_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEnabled
	 *
	 */
	public Set<User> findUserByEnabled(Boolean enabled) throws DataAccessException;

	/**
	 * JPQL Query - findUserByEnabled
	 *
	 */
	public Set<User> findUserByEnabled(Boolean enabled, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByGrade
	 *
	 */
	public Set<User> findUserByGrade(String grade_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByGrade
	 *
	 */
	public Set<User> findUserByGrade(String grade_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByPasswordContaining
	 *
	 */
	public Set<User> findUserByPasswordContaining(String password_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByPasswordContaining
	 *
	 */
	public Set<User> findUserByPasswordContaining(String password_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByTelephone
	 *
	 */
	public Set<User> findUserByTelephone(String telephone_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByTelephone
	 *
	 */
	public Set<User> findUserByTelephone(String telephone_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUpdatedAtAfter
	 *
	 */
	public Set<User> findUserByUpdatedAtAfter(java.util.Calendar updatedAt_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUpdatedAtAfter
	 *
	 */
	public Set<User> findUserByUpdatedAtAfter(Calendar updatedAt_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByAttendanceTimeContaining
	 *
	 */
	public Set<User> findUserByAttendanceTimeContaining(String attendanceTime_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByAttendanceTimeContaining
	 *
	 */
	public Set<User> findUserByAttendanceTimeContaining(String attendanceTime_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorNumber
	 *
	 */
	public Set<User> findUserByMajorNumber(String majorNumber) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorNumber
	 *
	 */
	public Set<User> findUserByMajorNumber(String majorNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUpdatedAt
	 *
	 */
	public Set<User> findUserByUpdatedAt(java.util.Calendar updatedAt_2) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUpdatedAt
	 *
	 */
	public Set<User> findUserByUpdatedAt(Calendar updatedAt_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserRoleContaining
	 *
	 */
	public Set<User> findUserByUserRoleContaining(String userRole_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserRoleContaining
	 *
	 */
	public Set<User> findUserByUserRoleContaining(String userRole_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserSexy
	 *
	 */
	public Set<User> findUserByUserSexy(String userSexy) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserSexy
	 *
	 */
	public Set<User> findUserByUserSexy(String userSexy, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserSexyContaining
	 *
	 */
	public Set<User> findUserByUserSexyContaining(String userSexy_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByUserSexyContaining
	 *
	 */
	public Set<User> findUserByUserSexyContaining(String userSexy_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorNumberContaining
	 *
	 */
	public Set<User> findUserByMajorNumberContaining(String majorNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByMajorNumberContaining
	 *
	 */
	public Set<User> findUserByMajorNumberContaining(String majorNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCnameContaining
	 *
	 */
	public Set<User> findUserByCnameContaining(String cname_1) throws DataAccessException;

	/**
	 * JPQL Query - findUserByCnameContaining
	 *
	 */
	public Set<User> findUserByCnameContaining(String cname_1, int startResult, int maxRows) throws DataAccessException;

}