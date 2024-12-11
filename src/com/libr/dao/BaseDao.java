package com.libr.dao;

import java.util.List;


public interface BaseDao {
	
	// 查询单条记录（管理员查询借书还书记录）
	Object[] getOne(String sql, Object... params);
	
	// 查询多条记录（管理员查询借书还书记录）
	List<Object[]> getMany(String sql, Object... params);
	
	//执行插入（包含用户注册的插入，管理员添加书的插入）
	int executeInsert(String sql, Object... params);
	
	//执行管理员对图书的删除(返回受影响的行数)
	int executeDelete(String sql, Object... params);
	
	//执行管理员对图书的更新或用户对个人信息的修改
	int executeUpdate(String sql, Object... params);

}