package com.libr.entity;

import java.util.Date;

//储存书籍信息
public class Borrow {
	private int bookId;
	private int useId;
	private Date borrowTime;
	private Date borrowReturnTime;
	private Boolean bookStatement;
	private int bookNumber;
	private int borrowId;
	public Borrow(int useId,int bookId, Date borrowTime,
			Boolean bookStatement, Date borrowReturnTime, int bookNumber,int borrowId) {
		super();
		this.useId = useId;
		this.bookId = bookId;
		this.borrowTime = borrowTime;
		this.bookStatement = bookStatement;
		this.borrowReturnTime = borrowReturnTime;
		this.bookNumber = bookNumber;
		this.borrowId = borrowId;
	}
	public Borrow() {
	}
	@Override
	public String toString() {
		return "Borrow [borrowId="+borrowId+",bookId=" + bookId + ", useId=" + useId + ",  borrowTime=" + borrowTime
				+ ", borrowReturnTime=" + borrowReturnTime + ", bookStatement=" + bookStatement + ", bookNumber="
				+ bookNumber + "]";
	}
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Date getBorrowReturnTime() {
		return borrowReturnTime;
	}
	public void setBorrowReturnTime(Date borrowReturnTime) {
		this.borrowReturnTime = borrowReturnTime;
	}
	public Boolean getBookStatement() {
		return bookStatement;
	}
	public void setBookStatement(Boolean bookStatement) {
		this.bookStatement = bookStatement;
	}
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
}
