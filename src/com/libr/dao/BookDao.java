package com.libr.dao;

import java.util.List;

import com.libr.entity.Book;

public interface BookDao extends BaseDao {
	//插入
	int insert(Book p);
	//更新
	int update(Book p);
	
	int deleteById(String id);
	
	Book getOneById(String id);
	
	List<Book> getAll();
	
	/**
	 * 根据名称关键字查询产品列表
	 * @param keyword 检索关键字
	 * @return 一个满足查询条件的产品列表
	 */
	List<Book> getAllByKeyword(String keyword);
}
