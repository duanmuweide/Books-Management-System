package com.libr.service;

import java.util.Date;
import java.util.List;

import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.entity.UserInfo;

//AdminService接口： 声明产品相关的业务功能
public interface AdminService {
	// 修改密码
	public void changePassword(int userId, String newPassword);

	// 添加其他管理员账户
	public void addAdmin(UserInfo newAdmin);

	// 添加新图书
	public void addBook(Book book);

	// 修改图书信息
	public void modifyBook(int bookId, String newName, String newType, int newNumber);

	// 根据ID删除指定的图书
	public void deleteBook(int bookId);

	// 根据ID查询书籍
	public Book searchBookById(int bookId);

	// 根据关键词搜索
	public List<Book> searchBookByName(String keyword);

	// 根据日期搜寻
//	public List<Book> searchBookByDate(Date date);

	// 通过id查询借阅记录
	public List<Borrow> serachBorrowRecordById(int userId);

	// 通过id查询还书记录
	public List<Borrow> serachReturnRecordById(int userId);

	// 根据用户id查询用户未还的图书记录
	public List<Book> viewUnreturnedBookById(int uid);
	
	// 根据图书id或名称关键字查询图书的借阅情况
	public List<Book> viewBookStatementByid(int bid);

	// 根据图书名称关键字查询图书的借阅情况
	public List<Book> viewBookStatementBykeywords(String keywords);
	
	// 根据用户ID获取用户对象
	public UserInfo getUserInfoById(int userID);
}
