package com.libr.dao;

import java.util.Date;
import java.util.List;

import com.libr.entity.Book;

public interface BookDao extends BaseDao {
	//插入
	int insertBook(Book p);
	//更新
	int updateBook(Book p);
	
	int deleteById(int id);
	
	Book getOneById(int id);
	
	List<Book> getOneByDate(Date day);
	
	List<Book> getOneByAuthor(String name);
	
	List<Book> getAll();//把所有全列出来
	
	
	List<Book> getAllByKeyword(String keyword);
}