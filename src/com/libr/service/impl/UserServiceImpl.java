package com.libr.service.impl;

import java.util.Collections;
import java.util.List;

import com.libr.dao.BookDao;
import com.libr.dao.BorrowDao;
import com.libr.dao.impl.BookDaoImpl;
import com.libr.dao.impl.BorrowDaoImpl;
import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.entity.UserInfo;
import com.libr.service.UserService;

public class UserServiceImpl implements UserService {

	// 根据id修改密码
	@Override
	public void changePassword(int userId, String newPassword) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
		UserInfo userInfo = uidi.getOneById(userId);
		if (userInfo != null) {
			userInfo.setUserPassword(newPassword);
			uidi.update(userInfo);
			System.out.println("密码修改成功！");
		} else {
			System.out.println("用户不存在！");
		}
	}

	// 注册
	@Override
	public void registerUser(UserInfo userInfo) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
		int result = uidi.insert(userInfo);
		if (result > 0) {
			System.out.println("注册成功！");
		} else {
			System.out.println("注册失败！");
		}
	}

	// 根据作者寻找书
	@Override
	public List<Book> serachBookByWriter(String bookWriterName) {
		BookDao bd = new BookDaoImpl();
		List<Book> books = bd.getOneByAuthor(bookWriterName);
		if (books.isEmpty()) {
			System.out.println("没有找到包含关键词 '" + bookWriterName + "' 的图书");
			return Collections.emptyList();
		} else {
			books.forEach(book -> System.out.println("找到图书: " + book));// lambuda
			return books;
		}
	}

	// 通过书名寻找
	@Override
	public List<Book> serachBookByBookName(String bookName) {
		BookDao bd = new BookDaoImpl();
		List<Book> books = bd.getOneByBookName(bookName);
		if (books.isEmpty()) {
			System.out.println("没有找到包含关键词 '" + bookName + "' 的图书");
			return Collections.emptyList();
		} else {
			books.forEach(book -> System.out.println("找到图书: " + book));// lambuda
			return books;
		}
	}

	// 寻找借书记录
	@Override
	public List<Borrow> serachBorrowRecordById(int userId) {
		BorrowDao bd = new BorrowDaoImpl();
		List<Borrow> borrowRecords = bd.viewReturnRecordsById(userId);
		return borrowRecords;
	}

	// 寻找还书记录
	@Override
	public List<Borrow> serachReturnRecordById(int userId) {
		BorrowDao bd = new BorrowDaoImpl();
		List<Borrow> returnRecords = bd.viewReturnRecordsById(userId);
		return returnRecords;
	}

	// 修改个人信息

	@Override
	public UserInfo changeUserInfo(UserInfo user) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
		uidi.update(user);
		return user;
	}
}