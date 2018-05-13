package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.COperationOutlineCredit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage COperationOutlineCredit entities.
 * 
 */
public interface COperationOutlineCreditDAO extends
		JpaDao<COperationOutlineCredit> {

	/**
	 * JPQL Query - findCOperationOutlineCreditByCredit
	 *
	 */
	public Set<COperationOutlineCredit> findCOperationOutlineCreditByCredit(Integer credit) throws DataAccessException;

	/**
	 * JPQL Query - findCOperationOutlineCreditByCredit
	 *
	 */
	public Set<COperationOutlineCredit> findCOperationOutlineCreditByCredit(Integer credit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCOperationOutlineCredits
	 *
	 */
	public Set<COperationOutlineCredit> findAllCOperationOutlineCredits() throws DataAccessException;

	/**
	 * JPQL Query - findAllCOperationOutlineCredits
	 *
	 */
	public Set<COperationOutlineCredit> findAllCOperationOutlineCredits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCOperationOutlineCreditById
	 *
	 */
	public COperationOutlineCredit findCOperationOutlineCreditById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCOperationOutlineCreditById
	 *
	 */
	public COperationOutlineCredit findCOperationOutlineCreditById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCOperationOutlineCreditByPrimaryKey
	 *
	 */
	public COperationOutlineCredit findCOperationOutlineCreditByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCOperationOutlineCreditByPrimaryKey
	 *
	 */
	public COperationOutlineCredit findCOperationOutlineCreditByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}