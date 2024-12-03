package com.libr.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.service.AdminService;

public class AdminServiceImpl implements AdminService {

	// 用列表模拟数据库
	private List<Book> books = new ArrayList<>();
	private List<Borrow> borrowRecords = new ArrayList<>();
	private List<String> managers = new ArrayList<>();
	private String userPassword = "defaultPassword";

	// 修改密码
	@Override
	public void changePassword(int newPassword) {
		// Assuming the password is just an integer and updating it.
		this.userPassword = String.valueOf(newPassword);
		System.out.println("Password changed successfully!");
	}

	// 添加其他管理员账户
	@Override
	public void addManager() {
		// You can add more logic to handle adding manager information (e.g., password,
		// role, etc.)
		String managerName = "Manager" + (managers.size() + 1); // Simple example for naming managers
		managers.add(managerName);
		System.out.println("Manager added successfully: " + managerName);
	}

	// 添加新图书
	@Override
	public void addBook(Book book) {
		books.add(book);
		System.out.println("Book added successfully: " + book);
	}

	// 修改图书信息
	@Override
	public void modifyBook(int bookId, String newName, String newType, int newNumber) {
		for (Book book : books) {
			if (book.getBookId() == bookId) {
				book.setBookName(newName);
				book.setBookType(newType);
				book.setBookNumber(newNumber);
				System.out.println("Book modified successfully: " + book);
				return;
			}
		}
		System.out.println("Book not found with ID: " + bookId);
	}

	// 根据ID删除信息
	@Override
	public void deleteBook(int bookId) {
		books.removeIf(book -> book.getBookId() == bookId);
		System.out.println("Book deleted successfully if existed. ID: " + bookId);
	}

	// 查询借阅记录
	@Override
	public void viewBorrowRecords() {
		for (Borrow record : borrowRecords) {
			System.out.println(record);
		}
	}

	// 根据ID查询书籍
	@Override
	public void searchBookById(int bookId) {
		for (Book book : books) {
			if (book.getBookId() == bookId) {
				System.out.println("Book found: " + book);
				return;
			}
		}
		System.out.println("No book found with ID: " + bookId);
	}

	// 根据关键词搜索
	@Override
	public void searchBookByName(String keyword) {
		boolean found = false;
		for (Book book : books) {
			if (book.getBookName().toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println("Book found: " + book);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No books found with keyword: " + keyword);
		}
	}

	// 根据日期搜寻
	@Override
	public void searchBookByDate(LocalDate date) {
		boolean found = false;
		for (Book book : books) {
			if (book.getBookTime().equals(date)) {
				System.out.println("Book found: " + book);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No books found with entry date: " + date);
		}
	}
}