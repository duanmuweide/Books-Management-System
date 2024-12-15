package com.libr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.libr.dao.BorrowDao;
import com.libr.entity.Borrow;
import com.libr.util.DatabaseUtil;

public class BorrowDaoImpl extends BaseDaoImpl implements BorrowDao{

	@Override
	public int insert(Borrow p) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "insert into borrow values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getUseId());
			ps.setObject(2,p.getBookId());
			ps.setObject(3,p.getBorrowTime());
			ps.setObject(4,p.getBookStatement());
			ps.setObject(5,p.getBorrowReturnTime());
			ps.setObject(6,p.getBookNumber());
			ps.setObject(7,p.getBorrowId());
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
	public int update(Borrow p) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "update borrow set uid=?,bid=?,borrowtime=?,bstatement=?,breturntime=?,bnumber=?,borrowid=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getUseId());
			ps.setObject(2,p.getBookId());
			ps.setObject(3,p.getBorrowTime());
			ps.setObject(4,p.getBookStatement());
			ps.setObject(5,p.getBorrowReturnTime());
			ps.setObject(6,p.getBookNumber());
			ps.setObject(7,p.getBorrowId());
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
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "delete from borrow where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return rows;
	}

	@Override
	public Borrow getOneById(int id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Borrow borrow = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from borrow where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if (rs.next()) { 
				borrow = new Borrow(rs.getInt(1),rs.getInt(2),rs.getDate(3),
						rs.getBoolean(4),rs.getDate(5),rs.getInt(6),rs.getInt(7));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return borrow;
	}

	@Override
	public List<Borrow> getAll() {
		// TODO Auto-List<Book> list = new ArrayList<>();
		List<Borrow> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from borrow";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Borrow b=new Borrow(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getInt(7)
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
	public List<Borrow> getBorrowByUid(int user_id) {
		// TODO Auto-generated method stub
		List<Borrow> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from borrow where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,user_id);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Borrow b=new Borrow(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getInt(7)
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
	public List<Borrow> getAllByKeyword(String keyword) {
		// TODO Auto-generated method stub
		//根据书的名字查找借阅记录
		List<Borrow> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from borrow where bid in (select bid from book where bname=?)";
			ps = con.prepareStatement(sql);
			ps.setString(1,keyword);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Borrow b=new Borrow(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getBoolean(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getInt(7)
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
	public List<Borrow> viewBorrowRecordsById(int user_id) {
		List<Borrow> borrowList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select borrowtime from borrow where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,user_id);
			rs = ps.executeQuery();
			while(rs.next()) { 
				Date borrowTime = rs.getDate("borrowtime");
	            Date returnTime = rs.getDate("breturntime");
	            int bookId = rs.getInt("book_id");  // 我不知道你这边变量是什么，你看着改改
	            Borrow borrow = new Borrow();
	            borrow.setUseId(user_id);
	            borrow.setBorrowTime(borrowTime);
	            borrow.setBorrowReturnTime(returnTime);
	            borrow.setBookId(bookId);
	            borrowList.add(borrow);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return borrowList;
	}

	@Override
	public List<Borrow> viewReturnRecordsById(int user_id) {
	    List<Borrow> returnList = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        con = DatabaseUtil.getConnection();
	        String sql = "SELECT borrowtime, breturntime, book_id FROM borrow WHERE uid=? AND breturntime IS NOT NULL";
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, user_id);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Date borrowTime = rs.getDate("borrowtime");
	            Date returnTime = rs.getDate("breturntime");
	            int bookId = rs.getInt("book_id");
	            Borrow borrow = new Borrow();
	            borrow.setUseId(user_id);
	            borrow.setBorrowTime(borrowTime);
	            borrow.setBorrowReturnTime(returnTime);
	            borrow.setBookId(bookId);
	            returnList.add(borrow);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        DatabaseUtil.close(null, ps, con);
	    }
	    return returnList;
	}

}
