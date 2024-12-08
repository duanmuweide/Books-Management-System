package com.libr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
			String sql = "insert into borrow values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getUseId());
			ps.setObject(2,p.getBookId());
			ps.setObject(3,p.getBorrowTime());
			ps.setObject(4,p.getBookStatement());
			ps.setObject(5,p.getBorrowReturnTime());
			ps.setObject(6,p.getBookNumber());
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
			String sql = "update borrow set uid=?,bid=?,borrowtime=?,bstatement=?,breturntime=?,bnumber=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getUseId());
			ps.setObject(2,p.getBookId());
			ps.setObject(3,p.getBorrowTime());
			ps.setObject(4,p.getBookStatement());
			ps.setObject(5,p.getBorrowReturnTime());
			ps.setObject(6,p.getBookNumber());
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return rows;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "delete from borrow where uid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,id);
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return rows;
	}

	@Override
	public Borrow getOneById(String id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Borrow borrow = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from borrow where bid=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,id);
			rs = ps.executeQuery();
			if (rs.next()) { 
				borrow = new Borrow(rs.getInt(1),rs.getInt(2),rs.getDate(3),
						rs.getBoolean(4),rs.getDate(5),rs.getInt(6));
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
						rs.getInt(6)
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
		List<Borrow> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from borrow where bid=?";
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
						rs.getInt(6)
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