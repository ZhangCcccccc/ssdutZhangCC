package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabReservationAudit;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabReservationAudit entities.
 * 
 */
public interface LabReservationAuditDAO extends JpaDao<LabReservationAudit> {

	/**
	 * JPQL Query - findLabReservationAuditByPrimaryKey
	 *
	 */
	public LabReservationAudit findLabReservationAuditByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationAuditByPrimaryKey
	 *
	 */
	public LabReservationAudit findLabReservationAuditByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservationAudits
	 *
	 */
	public Set<LabReservationAudit> findAllLabReservationAudits() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabReservationAudits
	 *
	 */
	public Set<LabReservationAudit> findAllLabReservationAudits(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationAuditById
	 *
	 */
	public LabReservationAudit findLabReservationAuditById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationAuditById
	 *
	 */
	public LabReservationAudit findLabReservationAuditById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationAuditByComments
	 *
	 */
	public Set<LabReservationAudit> findLabReservationAuditByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findLabReservationAuditByComments
	 *
	 */
	public Set<LabReservationAudit> findLabReservationAuditByComments(String comments, int startResult, int maxRows) throws DataAccessException;

}