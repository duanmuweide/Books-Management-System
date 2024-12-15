package com.libr.dao;

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
	
	//这是根据用户id查借记录
	List<Borrow> viewBorrowRecordsById(int user_id);
	
	//这是根据用户id查还记录
	List<Borrow> viewReturnRecordsById(int user_id);
		
}
