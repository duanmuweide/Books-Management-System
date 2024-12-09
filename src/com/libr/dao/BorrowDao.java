package com.libr.dao;

import java.util.List;

import com.libr.entity.Borrow;

public interface BorrowDao extends BaseDao {

	int insert(Borrow p);
	
	int update(Borrow p);
	
	int deleteById(String id);
	
	Borrow getOneById(String id);
	
	List<Borrow> getAll();
	
	
	List<Borrow> getAllByKeyword(String keyword);
}
