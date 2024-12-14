package com.libr.dao;

import java.util.Date;
import java.util.List;

import com.libr.entity.Borrow;

public interface BorrowDao extends BaseDao {

	int insert(Borrow p);
	
	int update(Borrow p);
	
	int deleteById(int id);
	
	Borrow getOneById(int id);
	
	List<Borrow> getAll();
	
	
	List<Borrow> getAllByKeyword(String keyword);

	List<Borrow> getBorrowByUid(int userId);
	
	List<List<Date>> viewBorrowRecordsById(int user_id);
	
	
		
}
