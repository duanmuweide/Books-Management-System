package com.libr.entity;

import java.time.LocalDate;

//储存书籍信息
public class Borrow {
	private Integer bookId;
	private Integer useId;
	private String bookType;
	private LocalDate borrowTime;
	private LocalDate borrowReturnTime;
	private String bookStatement;
	private Integer bookNumber;
	public Borrow(Integer bookId, Integer useId, String bookType, LocalDate borrowTime, LocalDate borrowReturnTime,
			String bookStatement, Integer bookNumber) {
		super();
		this.bookId = bookId;
		this.useId = useId;
		this.bookType = bookType;
		this.borrowTime = borrowTime;
		this.borrowReturnTime = borrowReturnTime;
		this.bookStatement = bookStatement;
		this.bookNumber = bookNumber;
	}
	@Override
	public String toString() {
		return "Borrow [bookId=" + bookId + ", useId=" + useId + ", bookType=" + bookType + ", borrowTime=" + borrowTime
				+ ", borrowReturnTime=" + borrowReturnTime + ", bookStatement=" + bookStatement + ", bookNumber="
				+ bookNumber + "]";
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getUseId() {
		return useId;
	}
	public void setUseId(Integer useId) {
		this.useId = useId;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public LocalDate getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(LocalDate borrowTime) {
		this.borrowTime = borrowTime;
	}
	public LocalDate getBorrowReturnTime() {
		return borrowReturnTime;
	}
	public void setBorrowReturnTime(LocalDate borrowReturnTime) {
		this.borrowReturnTime = borrowReturnTime;
	}
	public String getBookStatement() {
		return bookStatement;
	}
	public void setBookStatement(String bookStatement) {
		this.bookStatement = bookStatement;
	}
	public Integer getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(Integer bookNumber) {
		this.bookNumber = bookNumber;
	}
	
}
