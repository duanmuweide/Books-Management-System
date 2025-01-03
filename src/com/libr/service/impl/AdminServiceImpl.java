package com.libr.service.impl;

import java.sql.Connection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.libr.dao.BookDao;
import com.libr.dao.BorrowDao;
import com.libr.dao.impl.BookDaoImpl;
import com.libr.dao.impl.BorrowDaoImpl;
import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.entity.UserInfo;
import com.libr.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private BookDao BookDao;

	// 构造函数，传入数据库连接对象
	public AdminServiceImpl(Connection connection) {
		this.BookDao = new BookDaoImpl();

	}

	// 修改管理员密码根据id
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

	// 添加管理员
	@Override
	public void addAdmin(UserInfo newAdmin) {
		// 更新管理员状态(true->false,flase->true)
		UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
		if(newAdmin.getUserAdmin()) {
			newAdmin.setUserAdmin(false);
		}else {
			newAdmin.setUserAdmin(true);
		}
		userInfoDaoImpl.update(newAdmin);
	}

	// 添加新图书
	@Override
	public void addBook(Book book) {
		System.out.println(book);
		BookDao.insertBook(book);
	}

	// 修改图书信息
	@Override
	public void modifyBook(int bookId, String newName, String newType, int newNumber) {
		Book book = BookDao.getOneById(bookId);
		if (book != null) {
			book.setBookName(newName);
			book.setBookType(newType);
			book.setBookNumber(newNumber);
			BookDao.updateBook(book);
			System.out.println("图书信息修改成功: " + book.getBookName());
		} else {
			System.out.println("没有找到ID为 " + bookId + " 的图书");
		}
	}

	// 删除图书
	@Override
	public void deleteBook(int bookId) {
		BookDao.deleteById(bookId);
	}

	// 根据ID查询图书
	@Override
	public Book searchBookById(int bookId) {
		Book book = BookDao.getOneById(bookId);
		if (book != null) {
			System.out.println("找到图书: " + book);
			return book;
		} else {
			System.out.println("没有找到ID为 " + bookId + " 的图书");
		}
		return null;
	}

	// 根据名称搜索图书
	@Override
	public List<Book> searchBookByName(String keyword) {
		List<Book> books = BookDao.getOneByBookName(keyword);
		if (books.isEmpty()) {
			System.out.println("没有找到包含关键词 '" + keyword + "' 的图书");
			return Collections.emptyList();
		} else {
			books.forEach(book -> System.out.println("找到图书: " + book));// lambuda
			return books;
		}
	}

	// 根据日期搜索图书
//	@Override
//	public List<Book> searchBookByDate(Date date) {
//		List<Book> books = BookDao.getOneByDate(date);
//		if (books.isEmpty()) {
//			System.out.println("没有找到日期为 " + date + " 的图书");
//			return Collections.emptyList();
//		} else {
//			books.forEach(book -> System.out.println("找到图书: " + book));
//			return books;
//		}
//	}

	@Override // 借书记录
	public List<Borrow> serachBorrowRecordById(int userId) {
		BorrowDao bd = new BorrowDaoImpl();
		List<Borrow> borrowRecords = bd.viewReturnRecordsById(userId);
		return borrowRecords;
	}

	@Override // 还书记录
	public List<Borrow> serachReturnRecordById(int userId) {
		BorrowDao bd = new BorrowDaoImpl();
		List<Borrow> returnRecords = bd.viewReturnRecordsById(userId);
		return returnRecords;
	}

	@Override // 根据图书id查询图书的在馆的数量，位置
	public List<Book> viewBookStatementByid(int bid) {
		BookDao bd = new BookDaoImpl();
		List<Book> bookStatement = bd.viewBookStatement(bid);
		return bookStatement;
	}

	@Override // 根据关键词查询图书的在馆的数量，位置
	public List<Book> viewBookStatementBykeywords(String keywords) {
		BookDao bd = new BookDaoImpl();
		List<Book> bookStatement = bd.viewBookStatementByKeywords(keywords);
		return bookStatement;
	}

	@Override // 根据用户id查询用户未还的图书记录
	public List<Book> viewUnreturnedBookById(int uid) {
		BookDao bd = new BookDaoImpl();
		List<Book> unReturnedBook = bd.viewUnreturnedRecordsById(uid);
		return unReturnedBook;
	}

	@Override//更具用户id返回一个用户对象
	public UserInfo getUserInfoById(int userID) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
		UserInfo user=new UserInfo();
		user=uidi.getOneById(userID);
		return user;
	}

}
