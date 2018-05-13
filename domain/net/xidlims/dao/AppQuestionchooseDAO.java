package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AppQuestionchoose;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AppQuestionchoose entities.
 * 
 */
public interface AppQuestionchooseDAO extends JpaDao<AppQuestionchoose> {

	/**
	 * JPQL Query - findAppQuestionchooseByChoose
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByChoose(String choose) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByChoose
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByChoose(String choose, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByText
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByText(String text) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByText
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByText(String text, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAppQuestionchooses
	 *
	 */
	public Set<AppQuestionchoose> findAllAppQuestionchooses() throws DataAccessException;

	/**
	 * JPQL Query - findAllAppQuestionchooses
	 *
	 */
	public Set<AppQuestionchoose> findAllAppQuestionchooses(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByNum
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByNum(Integer num) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByNum
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByNum(Integer num, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByPrimaryKey
	 *
	 */
	public AppQuestionchoose findAppQuestionchooseByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByPrimaryKey
	 *
	 */
	public AppQuestionchoose findAppQuestionchooseByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByChooseContaining
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByChooseContaining(String choose_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByChooseContaining
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByChooseContaining(String choose_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseById
	 *
	 */
	public AppQuestionchoose findAppQuestionchooseById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseById
	 *
	 */
	public AppQuestionchoose findAppQuestionchooseById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByTextContaining
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByTextContaining(String text_1) throws DataAccessException;

	/**
	 * JPQL Query - findAppQuestionchooseByTextContaining
	 *
	 */
	public Set<AppQuestionchoose> findAppQuestionchooseByTextContaining(String text_1, int startResult, int maxRows) throws DataAccessException;

}