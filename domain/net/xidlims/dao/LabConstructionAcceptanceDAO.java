package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabConstructionAcceptance;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionAcceptance entities.
 * 
 */
public interface LabConstructionAcceptanceDAO extends
		JpaDao<LabConstructionAcceptance> {

	/**
	 * JPQL Query - findAllLabConstructionAcceptances
	 *
	 */
	public Set<LabConstructionAcceptance> findAllLabConstructionAcceptances() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionAcceptances
	 *
	 */
	public Set<LabConstructionAcceptance> findAllLabConstructionAcceptances(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionAcceptanceById
	 *
	 */
	public LabConstructionAcceptance findLabConstructionAcceptanceById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionAcceptanceById
	 *
	 */
	public LabConstructionAcceptance findLabConstructionAcceptanceById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionAcceptanceByPrimaryKey
	 *
	 */
	public LabConstructionAcceptance findLabConstructionAcceptanceByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionAcceptanceByPrimaryKey
	 *
	 */
	public LabConstructionAcceptance findLabConstructionAcceptanceByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}