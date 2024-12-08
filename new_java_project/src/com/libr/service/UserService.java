package com.libr.service;

import java.time.LocalDate;

import com.libr.entity.Book;

//UserService接口： 声明用户相关的业务功能
public interface UserService {
	// 注册
	public void register();

	// 登录
	public void loginIn();

	// 修改密码
	public void changePassword(int userPassword);

	// 修改个人信息
	public void changeUserInfo(int userPassword);

	// 通过作者查询书籍信息
	public void serachBookByWriter(String bookWriterName);

	// 通过关键字查询书籍信息
	public void serachBookByBookName(String bookName);
	
	//查询借书记录
	public void serachBorrowRecord();
	
	//查询还书记录
	public void serachReturnRecord();
	
}
