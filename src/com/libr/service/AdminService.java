package com.libr.service;

import java.time.LocalDate;

import com.libr.entity.Book;

//AdminService接口： 声明产品相关的业务功能
public interface AdminService {
	// 修改密码
	public void changePassword(int userPassword);

	// 添加其他管理员账户
	public void addManager();

	// 添加新图书
	public void addBook(Book book);

	// 修改图书信息
	public void modifyBook(int bookId, String newName, String newType, int newNumber);

	// 根据ID删除指定的图书
	public void deleteBook(int bookId);

	// 查询借阅记录和还书记录
	public void viewBorrowRecords();

	// 根据ID查询书籍
	public void searchBookById(int bookId);

	// 根据关键词搜索
	public void searchBookByName(String keyword);

	// 根据日期搜寻
	public void searchBookByDate(LocalDate date);

}
