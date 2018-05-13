package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.CommonHdwlog;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CommonHdwlog entities.
 * 
 */
public interface CommonHdwlogDAO extends JpaDao<CommonHdwlog> {

	/**
	 * JPQL Query - findCommonHdwlogByCardname
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardname(String cardname) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardname
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardname(String cardname, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumber
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumber(String academyNumber) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumber
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumber(String academyNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardnumber
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardnumber(String cardnumber) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardnumber
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardnumber(String cardnumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByDevicenoContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByDevicenoContaining(String deviceno) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByDevicenoContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByDevicenoContaining(String deviceno, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedat
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedat(String updatedat) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedat
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedat(String updatedat, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheck
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheck(String hdwCheck) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheck
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheck(String hdwCheck, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByRemarkContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByRemarkContaining(String remark) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByRemarkContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByRemarkContaining(String remark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHardwareidContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHardwareidContaining(String hardwareid) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHardwareidContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHardwareidContaining(String hardwareid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardnameContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardnameContaining(String cardname_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardnameContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardnameContaining(String cardname_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByStatusContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByStatusContaining(String status) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByStatusContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByStatusContaining(String status, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByRemark
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByRemark(String remark_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByRemark
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByRemark(String remark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByStatus
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByStatus(String status_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByStatus
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByStatus(String status_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByPrimaryKey
	 *
	 */
	public CommonHdwlog findCommonHdwlogByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByPrimaryKey
	 *
	 */
	public CommonHdwlog findCommonHdwlogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByDatetime
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByDatetime(java.util.Calendar datetime) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByDatetime
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByDatetime(Calendar datetime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedatContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedatContaining(String updatedat_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByUpdatedatContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByUpdatedatContaining(String updatedat_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByDeviceno
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByDeviceno(String deviceno_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByDeviceno
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByDeviceno(String deviceno_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheckContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheckContaining(String hdwCheck_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHdwCheckContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHdwCheckContaining(String hdwCheck_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHardwareid
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHardwareid(String hardwareid_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByHardwareid
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByHardwareid(String hardwareid_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonHdwlogs
	 *
	 */
	public Set<CommonHdwlog> findAllCommonHdwlogs() throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonHdwlogs
	 *
	 */
	public Set<CommonHdwlog> findAllCommonHdwlogs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumberContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumberContaining(String academyNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByAcademyNumberContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByAcademyNumberContaining(String academyNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogById
	 *
	 */
	public CommonHdwlog findCommonHdwlogById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogById
	 *
	 */
	public CommonHdwlog findCommonHdwlogById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardnumberContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardnumberContaining(String cardnumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonHdwlogByCardnumberContaining
	 *
	 */
	public Set<CommonHdwlog> findCommonHdwlogByCardnumberContaining(String cardnumber_1, int startResult, int maxRows) throws DataAccessException;

}