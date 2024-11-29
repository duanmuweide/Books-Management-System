package com.libr.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.libr.entity.Book;
import com.libr.entity.Borrow;

public class Admin {

	// 用列表模拟数据库
	private List<Book> books = new ArrayList<>();
	private List<Borrow> borrowRecords = new ArrayList<>();

	// 添加书
	public void addBook(Book book) {
		books.add(book);
		System.out.println("Book added successfully: " + book);
	}

	// 更改书籍信息
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
	public void deleteBook(int bookId) {
		books.removeIf(book -> book.getBookId() == bookId);
		System.out.println("Book deleted successfully if existed. ID: " + bookId);
	}

	// 查询借阅记录
	public void viewBorrowRecords() {
		for (Borrow record : borrowRecords) {
			System.out.println(record);
		}
	}

	// 根据ID查询书籍
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
