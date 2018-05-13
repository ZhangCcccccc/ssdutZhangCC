package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TMessageUser;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TMessageUser entities.
 * 
 */
@Repository("TMessageUserDAO")
@Transactional
public class TMessageUserDAOImpl extends AbstractJpaDao<TMessageUser> implements
		TMessageUserDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TMessageUser.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TMessageUserDAOImpl
	 *
	 */
	public TMessageUserDAOImpl() {
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
	 * JPQL Query - findTMessageUserByUsername
	 *
	 */
	@Transactional
	public Set<TMessageUser> findTMessageUserByUsername(String username) throws DataAccessException {

		return findTMessageUserByUsername(username, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByUsername
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findTMessageUserByUsername(String username, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageUserByUsername", startResult, maxRows, username);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageUserByCreateTime
	 *
	 */
	@Transactional
	public Set<TMessageUser> findTMessageUserByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findTMessageUserByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findTMessageUserByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageUserByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageUserByCreateBy
	 *
	 */
	@Transactional
	public Set<TMessageUser> findTMessageUserByCreateBy(String createBy) throws DataAccessException {

		return findTMessageUserByCreateBy(createBy, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByCreateBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findTMessageUserByCreateBy(String createBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageUserByCreateBy", startResult, maxRows, createBy);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageUserById
	 *
	 */
	@Transactional
	public TMessageUser findTMessageUserById(Integer id) throws DataAccessException {

		return findTMessageUserById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserById
	 *
	 */

	@Transactional
	public TMessageUser findTMessageUserById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMessageUserById", startResult, maxRows, id);
			return (net.xidlims.domain.TMessageUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTMessageUsers
	 *
	 */
	@Transactional
	public Set<TMessageUser> findAllTMessageUsers() throws DataAccessException {

		return findAllTMessageUsers(-1, -1);
	}

	/**
	 * JPQL Query - findAllTMessageUsers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findAllTMessageUsers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTMessageUsers", startResult, maxRows);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageUserByPrimaryKey
	 *
	 */
	@Transactional
	public TMessageUser findTMessageUserByPrimaryKey(Integer id) throws DataAccessException {

		return findTMessageUserByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByPrimaryKey
	 *
	 */

	@Transactional
	public TMessageUser findTMessageUserByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTMessageUserByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TMessageUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTMessageUserByUsernameContaining
	 *
	 */
	@Transactional
	public Set<TMessageUser> findTMessageUserByUsernameContaining(String username) throws DataAccessException {

		return findTMessageUserByUsernameContaining(username, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByUsernameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findTMessageUserByUsernameContaining(String username, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageUserByUsernameContaining", startResult, maxRows, username);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageUserByCreateByContaining
	 *
	 */
	@Transactional
	public Set<TMessageUser> findTMessageUserByCreateByContaining(String createBy) throws DataAccessException {

		return findTMessageUserByCreateByContaining(createBy, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByCreateByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findTMessageUserByCreateByContaining(String createBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageUserByCreateByContaining", startResult, maxRows, createBy);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTMessageUserByMessageId
	 *
	 */
	@Transactional
	public Set<TMessageUser> findTMessageUserByMessageId(Integer messageId) throws DataAccessException {

		return findTMessageUserByMessageId(messageId, -1, -1);
	}

	/**
	 * JPQL Query - findTMessageUserByMessageId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TMessageUser> findTMessageUserByMessageId(Integer messageId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTMessageUserByMessageId", startResult, maxRows, messageId);
		return new LinkedHashSet<TMessageUser>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TMessageUser entity) {
		return true;
	}
	
	/**
	 * 根据message_id和username查询
	 * 作者：张佳鸣
	 * 时间：2017-10-16
	 */
	public TMessageUser findTMessageUserByMessageIdAndUsername(Integer tMessageId,String username){
		
		//定义查询
		String sql = "select t from TMessageUser t where 1=1";
		//查询条件
		sql += " and message_id ="+tMessageId;
		sql += " and username ='"+username+"'";
		//执行查询并返回
		if(this.executeQuery(sql, 0, -1)!=null&&!this.executeQuery(sql, 0, -1).isEmpty()){
			return this.executeQuery(sql, 0, -1).get(0);
		}else{
			return null;
		}
	}
}
