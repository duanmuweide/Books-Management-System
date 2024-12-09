package com.libr.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.libr.dao.BookDao;
import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.service.UserService;

public class UserServiceImpl implements UserService {

	// 根据id修改密码
	@Override
	    public void changePassword(int newPassword) {
	        BookDao.getPassword(String passwd){
	        }System.out.println("密码修改成功！");
		}

	// 注册
	@Override
	public void register() {

	}

	@Override
	public void loginIn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeUserInfo(int userPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serachBookByWriter(String bookWriterName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serachBookByBookName(String bookName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serachBorrowRecord() {
		// TODO Auto-generated method stub

	}

	@Override
	public void serachReturnRecord() {
		// TODO Auto-generated method stub

	}
}