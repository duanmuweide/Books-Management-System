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
<<<<<<< HEAD

	List<Borrow> getBorrowByUid(int user_id);
=======
>>>>>>> 1e73c4f3e56b3e606a9a17dca969424616b56166
}
