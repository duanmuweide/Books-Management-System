package com.libr.service.impl;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import com.libr.dao.BookDao;
import com.libr.dao.impl.BookDaoImpl;
import com.libr.entity.Book;
import com.libr.service.AdminService;

public class AdminServiceImpl implements AdminService {

    private BookDao BookDao;

    // 构造函数，传入数据库连接对象
    public AdminServiceImpl(Connection connection) {
        this.BookDao = new BookDaoImpl();
    }

    // 修改管理员密码根据id
    @Override
    public void changePassword(int newPassword) {
        BookDao.getPassword(String passwd);
        System.out.println("密码修改成功！");
    }

    // 添加管理员
    @Override
    public void addManager() {
        // 这里可以添加更多的逻辑来处理管理员信息（如密码、角色等）
        System.out.println("管理员添加成功！");
    }

    // 添加新图书
    @Override
    public void addBook(Book book) {
        BookDao.addBook(book);
    }

    // 修改图书信息
    @Override
    public void modifyBook(int bookId, String newName, String newType, int newNumber) {
        Book book = BookDao.findBookById(bookId);
        if (book != null) {
            book.setBookName(newName);
            book.setBookType(newType);
            book.setBookNumber(newNumber);
            BookDao.modifyBook(book);
            System.out.println("图书信息修改成功: " + book.getBookName());
        } else {
            System.out.println("没有找到ID为 " + bookId + " 的图书");
        }
    }

    // 删除图书
    @Override
    public void deleteBook(int bookId) {
        BookDao.deleteBook(bookId);
    }

    // 根据ID查询图书
    @Override
    public void searchBookById(int bookId) {
        Book book = BookDao.findBookById(bookId);
        if (book != null) {
            System.out.println("找到图书: " + book);
        } else {
            System.out.println("没有找到ID为 " + bookId + " 的图书");
        }
    }

    // 根据名称搜索图书
    @Override
    public void searchBookByName(String keyword) {
        List<Book> books = BookDao.searchBooksByName(keyword);
        if (books.isEmpty()) {
            System.out.println("没有找到包含关键词 '" + keyword + "' 的图书");
        } else {
            books.forEach(book -> System.out.println("找到图书: " + book));
        }
    }

    // 根据日期搜索图书
    @Override
    public void searchBookByDate(LocalDate date) {
        List<Book> books = BookDao.searchBooksByDate(date);
        if (books.isEmpty()) {
            System.out.println("没有找到日期为 " + date + " 的图书");
        } else {
            books.forEach(book -> System.out.println("找到图书: " + book));
        }
    }

	@Override
	public void viewBorrowRecordsById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewUnreturnedRecordsById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookStatement() {
		// TODO Auto-generated method stub
		
	}

}
