package com.libr.service;

import java.time.LocalDate;

import com.libr.entity.Book;

//AdminService接口： 声明产品相关的业务功能
public interface AdminService {
	// 修改密码
	public void changePassword(int userPassword);

	// 添加其他管理员账户
	public void addAdmin();

	// 添加新图书
	public void addBook(Book book);

	// 修改图书信息
	public void modifyBook(int bookId, String newName, String newType, int newNumber);

	// 根据ID删除指定的图书
	public void deleteBook(int bookId);

	// 根据ID查询书籍
	public void searchBookById(int bookId);

	// 根据关键词搜索
	public void searchBookByName(String keyword);

	// 根据日期搜寻
	public void searchBookByDate(LocalDate date);
	
	// 通过id查询借阅记录和还书记录
	public void viewBorrowRecordsById();
	
	// 根据用户id查询用户未还的图书记录
	public void viewUnreturnedRecordsById();
	
	// 根据图书id或名称关键字查询图书的借阅情况，如在馆的数量还有多少，位置等
	public void viewBookStatement();

}
