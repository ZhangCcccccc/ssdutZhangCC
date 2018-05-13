package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TAssignmentControl;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TAssignmentControl entities.
 * 
 */
public interface TAssignmentControlDAO extends JpaDao<TAssignmentControl> {

	/**
	 * JPQL Query - findTAssignmentControlBySubmessage
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlBySubmessage(String submessage) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlBySubmessage
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlBySubmessage(String submessage, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByStartdate
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlByStartdate(java.util.Calendar startdate) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByStartdate
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlByStartdate(Calendar startdate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByAssignmentId
	 *
	 */
	public TAssignmentControl findTAssignmentControlByAssignmentId(Integer assignmentId) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByAssignmentId
	 *
	 */
	public TAssignmentControl findTAssignmentControlByAssignmentId(Integer assignmentId, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByPrimaryKey
	 *
	 */
	public TAssignmentControl findTAssignmentControlByPrimaryKey(Integer assignmentId_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByPrimaryKey
	 *
	 */
	public TAssignmentControl findTAssignmentControlByPrimaryKey(Integer assignmentId_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByDuedate
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlByDuedate(java.util.Calendar duedate) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByDuedate
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlByDuedate(Calendar duedate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlBySubmessageContaining
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlBySubmessageContaining(String submessage_1) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlBySubmessageContaining
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlBySubmessageContaining(String submessage_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByTimelimit
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlByTimelimit(Integer timelimit) throws DataAccessException;

	/**
	 * JPQL Query - findTAssignmentControlByTimelimit
	 *
	 */
	public Set<TAssignmentControl> findTAssignmentControlByTimelimit(Integer timelimit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentControls
	 *
	 */
	public Set<TAssignmentControl> findAllTAssignmentControls() throws DataAccessException;

	/**
	 * JPQL Query - findAllTAssignmentControls
	 *
	 */
	public Set<TAssignmentControl> findAllTAssignmentControls(int startResult, int maxRows) throws DataAccessException;

}