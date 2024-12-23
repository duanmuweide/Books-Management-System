package com.libr.service;

import java.util.List;

import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.entity.UserInfo;

//UserService接口： 声明用户相关的业务功能
public interface UserService {
	// 注册
	public void registerUser(UserInfo userInfo);

	// 登录
	public void loginIn(int userId, String password);

	// 通过id修改密码
	public void changePassword(int userId,String userPassword);

	// 修改个人信息
	public UserInfo changeUserInfo(UserInfo user);

	// 通过作者查询书籍信息
	public List<Book> serachBookByWriter(String bookWriterName);

	// 通过关键字查询书籍信息
	public List<Book> serachBookByBookName(String bookName);
	
	//查询借书记录
	public List<Borrow> serachBorrowRecordById(int userId);
	
	//查询还书记录
	public List<Borrow> serachReturnRecordById(int userId);
	
}
