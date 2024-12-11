package com.libr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libr.dao.BaseDao;
import com.libr.entity.Borrow;
import com.libr.util.DatabaseUtil;

public class BaseDaoImpl implements BaseDao {

	@Override
	public Object[] getOne(String sql, Object... params) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 定义一个列表，存储查询到的多个记录

		con = DatabaseUtil.getConnection();
		
		Object[] data = null; //定义一个数组用于存储查询结果

		try {
			ps = con.prepareStatement(sql);
			
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			
			rs = ps.executeQuery();
			
			//获取查询到的列的数量
			int count=rs.getMetaData().getColumnCount(); 
			data=new Object[count];
			
			// 循环读取每一行记录
			if (rs.next()) {
				for(int i=0;i<count;i++) {
					data[i]=rs.getObject(i+1); //获取对应列的数据，存入数组
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(rs, ps, con);
		}

		return data; //将存储了查询结果的数组返回
	}

	@Override
	public List<Object[]> getMany(String sql, Object... params) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		con = DatabaseUtil.getConnection();
		
		List<Object[]> list=new ArrayList<>();

		try {
			ps = con.prepareStatement(sql);
			
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			
			// 执行查询，获得结果集
			rs = ps.executeQuery();
			
			int count=rs.getMetaData().getColumnCount(); //获取查询到的列的数量
			
			// 循环读取每一行记录
			while (rs.next()) {
				
				Object[] data=new Object[count];
				
				for(int i=0;i<count;i++) {
					data[i]=rs.getObject(i+1); //获取对应列的数据，存入数组
				}
				
				list.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(rs, ps, con);
		}

		return list; //将存储了查询结果的数组列表返回
	}

	@Override
	public int executeInsert(String sql, Object... params) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
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
	public int executeDelete(String sql, Object... params) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
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
	public int executeUpdate(String sql, Object... params) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0; // 定义一个变量，存储受影响的行数
		try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rows = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(null, ps, con);
		}
		return rows;
	}
}
