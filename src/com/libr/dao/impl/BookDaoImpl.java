package com.libr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.libr.dao.BookDao;
import com.libr.entity.Book;
import com.libr.util.DatabaseUtil;



public class BookDaoImpl extends BaseDaoImpl implements BookDao {

	@Override
	public int insertBook(Book p) {
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "insert into book values(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getBookId());
			ps.setObject(2,p.getBookName());
			ps.setObject(3,p.getBookType());
			ps.setObject(4,p.getBookWriterName());
			ps.setObject(5,p.getBookNumber());
			ps.setObject(6,p.getBookStatement());
			ps.setObject(7,p.getBookPosition());
			ps.setObject(8,p.getBookTime());
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		if (rows == 0) {
			System.out.println("插入失败！！！");
		} else {
			System.out.println("插入成功~~~~~~~");
		}
		return rows;
	}

	@Override
	public int updateBook(Book p) {
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "update book set bid=?,bname=?,btype=?,bwname=?,bnumber=?,bstatement=?,bposition=?,btime=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getBookId());
			ps.setObject(2,p.getBookName());
			ps.setObject(3,p.getBookType());
			ps.setObject(4,p.getBookWriterName());
			ps.setObject(5,p.getBookNumber());
			ps.setObject(6,p.getBookStatement());
			ps.setObject(7,p.getBookPosition());
			ps.setObject(8,p.getBookTime());
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return rows;
	}

	@Override
	public int deleteById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "delete from book where bid=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,id);
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return rows;
	}

	@Override
	public Book getOneById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where bid=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,id);
			rs = ps.executeQuery();
			if (rs.next()) { 
				book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getInt(5),rs.getBoolean(6),rs.getString(7),rs.getDate(8));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return book;
	}

	@Override
	public List<Book> getAll() {
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
			list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}

	@Override
	public List<Book> getAllByKeyword(String keyword) {
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where bname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,keyword);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
			list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}

	@Override
	public List<Book> getOneByDate(Date d) {
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where btime=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,d);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
			list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}

	@Override
	public List<Book> getOneByBookName(String name)
	{
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where bname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,name);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
			list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}
	

	@Override
	public List<Book> getOneByAuthor(String d) {
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where bwname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,d);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
			list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}

	@Override
	public List<Book> viewBookStatement(int bid) {
		// TODO Auto-generated method stub
		// 根据图书id查询图书的在馆的数量，位置
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where bid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,bid);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
				list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}
	
	@Override
	public List<Book> viewUnreturnedRecordsById(int user_id) {
		// 根据用户id查询用户未还的图书记录
		List<String> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select bname from book where bid in (select bid from borrow where breturntime < CURDATE() and uid=?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1,user_id);
			rs = ps.executeQuery();
			while(rs.next()) { 
				list.add(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}

	@Override
	public List<Book> viewBookStatementByKeywords(String bname) {
		List<Book> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from book where bname like '%?%' ";
			ps = con.prepareStatement(sql);
			ps.setString(1,bname);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Book b=new Book(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getBoolean(6),
						rs.getString(7),
						rs.getDate(8)
					);
				list.add(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}
}
