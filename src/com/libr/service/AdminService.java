package com.libr.service;

import java.time.LocalDate;

import com.libr.entity.Book;

//AdminService接口： 声明产品相关的业务功能
public interface AdminService {

	// 添加一个新书
	public void addBook(Book book);

	// 更改书籍信息
	public void modifyBook(int bookId, String newName, String newType, int newNumber);

	// 根据ID删除信息
	public void deleteBook(int bookId);

	// 查询借阅记录
	public void viewBorrowRecords();

	// 根据ID查询书籍
	public void searchBookById(int bookId);

	// 根据关键词搜索
	public void searchBookByName(String keyword);

	// 根据日期搜寻
	public void searchBookByDate(LocalDate date);

}
