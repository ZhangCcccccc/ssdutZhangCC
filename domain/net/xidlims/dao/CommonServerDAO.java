package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.CommonServer;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CommonServer entities.
 * 
 */
public interface CommonServerDAO extends JpaDao<CommonServer> {

	/**
	 * JPQL Query - findCommonServerByServerName
	 *
	 */
	public Set<CommonServer> findCommonServerByServerName(String serverName) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerName
	 *
	 */
	public Set<CommonServer> findCommonServerByServerName(String serverName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerAddressContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerAddressContaining(String serverAddress) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerAddressContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerAddressContaining(String serverAddress, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerById
	 *
	 */
	public CommonServer findCommonServerById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerById
	 *
	 */
	public CommonServer findCommonServerById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyEnNameContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyEnNameContaining(String academyEnName) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyEnNameContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyEnNameContaining(String academyEnName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyEnName
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyEnName(String academyEnName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyEnName
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyEnName(String academyEnName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByReamarkContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByReamarkContaining(String reamark) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByReamarkContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByReamarkContaining(String reamark, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerSn
	 *
	 */
	public Set<CommonServer> findCommonServerByServerSn(String serverSn) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerSn
	 *
	 */
	public Set<CommonServer> findCommonServerByServerSn(String serverSn, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonServers
	 *
	 */
	public Set<CommonServer> findAllCommonServers() throws DataAccessException;

	/**
	 * JPQL Query - findAllCommonServers
	 *
	 */
	public Set<CommonServer> findAllCommonServers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerIpContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerIpContaining(String serverIp) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerIpContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerIpContaining(String serverIp, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyNumberContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyNumberContaining(String academyNumber) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyNumberContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyNumberContaining(String academyNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerAddress
	 *
	 */
	public Set<CommonServer> findCommonServerByServerAddress(String serverAddress_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerAddress
	 *
	 */
	public Set<CommonServer> findCommonServerByServerAddress(String serverAddress_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerNameContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerNameContaining(String serverName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerNameContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerNameContaining(String serverName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerSnContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerSnContaining(String serverSn_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerSnContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByServerSnContaining(String serverSn_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyNameContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyNameContaining(String academyName) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyNameContaining
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyNameContaining(String academyName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByPrimaryKey
	 *
	 */
	public CommonServer findCommonServerByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByPrimaryKey
	 *
	 */
	public CommonServer findCommonServerByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByReamark
	 *
	 */
	public Set<CommonServer> findCommonServerByReamark(String reamark_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByReamark
	 *
	 */
	public Set<CommonServer> findCommonServerByReamark(String reamark_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyNumber
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyNumber(String academyNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyNumber
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyNumber(String academyNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerIp
	 *
	 */
	public Set<CommonServer> findCommonServerByServerIp(String serverIp_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByServerIp
	 *
	 */
	public Set<CommonServer> findCommonServerByServerIp(String serverIp_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyName
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyName(String academyName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCommonServerByAcademyName
	 *
	 */
	public Set<CommonServer> findCommonServerByAcademyName(String academyName_1, int startResult, int maxRows) throws DataAccessException;

}