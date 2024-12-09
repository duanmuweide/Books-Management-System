package com.libr.entity;

import java.util.Date;

//储存书籍信息
public class Book {
	private int bookId;
	private String bookName;
	private String bookType;
	private String bookWriterName;
	private int bookNumber;
	private String bookStatement;
	private String bookPosition;
	private Date bookTime;
	
	
	public Book(int bookId, String bookName, String bookType, String bookWriterName, int bookNumber,
			String bookStatement, String bookPosition, Date bookTime) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookWriterName = bookWriterName;
		this.bookNumber = bookNumber;
		this.bookStatement = bookStatement;
		this.bookPosition = bookPosition;
		this.bookTime = bookTime;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookWriterName() {
		return bookWriterName;
	}
	public void setBookWriterName(String bookWriterName) {
		this.bookWriterName = bookWriterName;
	}
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getBookStatement() {
		return bookStatement;
	}
	public void setBookStatement(String bookStatement) {
		this.bookStatement = bookStatement;
	}
	public String getBookPosition() {
		return bookPosition;
	}
	public void setBookPosition(String bookPosition) {
		this.bookPosition = bookPosition;
	}
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookWriterName="
				+ bookWriterName + ", bookNumber=" + bookNumber + ", bookStatement=" + bookStatement + ", bookPosition="
				+ bookPosition + ", bookTime=" + bookTime + "]";
	} 
	
}
