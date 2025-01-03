package com.libr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.libr.dao.UserInfoDao;
import com.libr.entity.UserInfo;
import com.libr.util.DatabaseUtil;

public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao{

	@Override
	public int insert(UserInfo p) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "insert into user_info values(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getUserId());
			ps.setObject(2,p.getUserName());
			ps.setObject(3,p.getUserPassword());
			ps.setObject(4,p.getUserQuestion());
			ps.setObject(5,p.getUserAnswer());
			ps.setObject(6,p.getUserAdmin());
			ps.setObject(7,p.getUserGender());
			ps.setObject(8,p.getUserContact());
			ps.setObject(9,p.getUserRealname());
			ps.setObject(10,p.getUserAddress());
			ps.setObject(11,p.getUserEmail());
			ps.setObject(12,p.getUserMajor());
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
	public int update(UserInfo p) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			String sql = "update user_info set uname=?,upwd=?,uquestion=?,uanswer=?,uadmin=?,ugender=?,ucontact=?,urealname=?,uaddress=?,uemail=?,major=? where uid=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1,p.getUserName());
			ps.setObject(2,p.getUserPassword());
			ps.setObject(3,p.getUserQuestion());
			ps.setObject(4,p.getUserAnswer());
			ps.setObject(5,p.getUserAdmin());
			ps.setObject(6,p.getUserGender());
			ps.setObject(7,p.getUserContact());
			ps.setObject(8,p.getUserRealname());
			ps.setObject(9,p.getUserAddress());
			ps.setObject(10,p.getUserEmail());
			ps.setObject(11,p.getUserMajor());
			ps.setObject(12,p.getUserId());
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		if (rows == 0) {
			System.out.println("更新失败！！！");
		} else {
			System.out.println("更新成功~~~~~~~");
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
			String sql = "delete from user_info where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		if (rows == 0) {
			System.out.println("删除失败！！！");
		} else {
			System.out.println("删除成功~~~~~~~");
		}
		return rows;
	}

	@Override
	public UserInfo getOneById(int id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfo user_info = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from user_info where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if (rs.next()) { 
				user_info = new UserInfo(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return user_info;
	}

	@Override
	public List<UserInfo> getAll() {
		// TODO Auto-generated method stub
		List<UserInfo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfo user_info = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from user_info";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) { 
				user_info = new UserInfo(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
				list.add(user_info);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}

	@Override
	public List<UserInfo> getAllByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<UserInfo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfo user_info = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select * from user_info where uname LIKE CONCAT('%', ?, '%')";
			ps = con.prepareStatement(sql);
			ps.setString(1,keyword);
			rs = ps.executeQuery();
			while (rs.next()) { 
				user_info = new UserInfo(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
				list.add(user_info);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return list;
	}
	@Override
	public String getPassword(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String passwd = null; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select upwd from user_info where uid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if (rs.next()) { 
				passwd = rs.getString(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return passwd;
	}

	@Override
	public int getLastUserID() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int uid = -1; 
		try {
			con = DatabaseUtil.getConnection();
			String sql = "select max(uid) from user_info";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) { 
				uid = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return uid;
	}
}
