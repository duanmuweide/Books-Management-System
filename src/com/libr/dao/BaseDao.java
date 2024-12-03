package com.libr.dao;

import java.util.List;

// BaseDao用于包含所有的Dao共有的操作
public interface BaseDao {

	/**
	 ** 根据传入的参数执行SQL语句，限于增删改语句
	 *
	 * @param sql 需要执行的sql语句， 如insert into student values(?,?,?,?,?)
	 * @param params sql语句需要的参数值，也就是各个问号的值
	 * @return 一个整数，表示受影响的行数，可用于判断增删改操作是否正常
	 */
	int executeUpdate(String sql, Object... params);
	
	// 查询单条记录
	Object[] getOne(String sql, Object... params);
	
	// 查询多条记录
	List<Object[]> getMany(String sql, Object... params);
}
