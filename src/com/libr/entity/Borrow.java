package com.libr.entity;

import java.util.Date;

//储存书籍信息
public class Borrow {
	private Integer bookId;
	private Integer useId;
	private Date borrowTime;
	private Date borrowReturnTime;
	private Boolean bookStatement;
	private Integer bookNumber;
	public Borrow(Integer useId,Integer bookId, Date borrowTime,
			Boolean bookStatement, Date borrowReturnTime, Integer bookNumber) {
		super();
		this.useId = useId;
		this.bookId = bookId;
		this.borrowTime = borrowTime;
		this.bookStatement = bookStatement;
		this.borrowReturnTime = borrowReturnTime;
		this.bookNumber = bookNumber;
	}
	@Override
	public String toString() {
		return "Borrow [bookId=" + bookId + ", useId=" + useId + ",  borrowTime=" + borrowTime
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
	public Integer getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(Integer bookNumber) {
		this.bookNumber = bookNumber;
	}
	
}
