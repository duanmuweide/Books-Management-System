package com.libr.dao;

import java.util.List;

import com.libr.entity.Borrow;

public interface BorrowDao extends BaseDao {

	int insert(Borrow p);
	
	int update(Borrow p);
	
	int deleteById(String id);
	
	Borrow getOneById(String id);
	
	List<Borrow> getAll();
	
	/**
	 * 根据名称关键字查询产品列表
	 * @param keyword 检索关键字
	 * @return 一个满足查询条件的产品列表
	 */
	List<Borrow> getAllByKeyword(String keyword);
}
