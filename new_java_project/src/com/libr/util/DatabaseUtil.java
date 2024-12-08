package com.libr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DatabaseUtil {
	
	private static final String DRIVER_CLASS;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	static {
		// ResourceBundle用于加载一个指定的资源，然后可以通过该对象操作该资源
		// 比如加载一个属性文件，需要指定属性文件的 包名.文件名，但是不需要写扩展名
		ResourceBundle rb = ResourceBundle.getBundle("com.book_system.util.dbconfig");
		// 根据属性名获取属性值，存入对应的常量，方便后续使用
		DRIVER_CLASS = rb.getString("jdbc.driver");
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
	}
	
	public static Connection getConnection() {

		Connection con = null;

		try {
			// 1. 加载驱动类
			Class.forName(DRIVER_CLASS);
			// 2. 获取数据库连接
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con; // 返回创建的连接
	}

	/**
	 ** 关闭要关闭的资源
	 * 
	 * @param rs   要关闭的结果集
	 * @param stmt 要关闭的语句对象
	 * @param con  要关闭的连接
	 */
	public static void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
			if (null != con)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
