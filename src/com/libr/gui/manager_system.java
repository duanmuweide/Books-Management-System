package com.libr.gui;
//添加图书，修改个人信息，添加其他管理员账户，修改图书信息

//删除图书
//查询 按照编号 名称关键词 入库时间查询
//或根据 用户id查询记录 根据图书id和名称关键来查询
//显示借阅情况和在馆数量

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class manager_system extends JFrame {

	public manager_system() {
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("Swing组件-JTabbedPane");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		panel1.setBackground(Color.orange);
		panel1.add(new JButton("查询借书记录"));
		panel1.add(new JButton("查询还书记录"));
		panel1.add(new JButton("查看预约记录"));
		panel1.add(new JButton("禁止预约座位"));
		panel1.add(new JButton("禁止借书"));

		panel2.setBackground(Color.pink);
		panel2.add(new JButton("添加图书"));
		panel2.add(new JButton("修改图书"));
		panel2.add(new JButton("删除图书"));
		panel2.add(new JButton("查询图书"));// 显示图书详细信息

		panel3.setBackground(Color.gray);
		panel3.add(new JButton("添加管理员"));
		panel3.add(new JButton("删除管理员"));
		panel3.add(new JButton("查询管理员"));

		panel4.setBackground(Color.white);
		panel4.add(new JButton("修改信息"));
		panel4.add(new JButton(""));

		panel5.setBackground(Color.gray);

		// 1. 创建页签面板
		// 可以通过构造函数传入一个参数，表示标签的位置
		// 顶部、底部、左、右
		// top、bottom、left、right
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

		// 2. 将页签面板添加到窗体中
		this.add(tabbedPane);

		// 3. 将各个JPanel添加到页签面板中，作为一个页签，第一个参数用于指定页签的标题
		tabbedPane.addTab("学生管理", panel1);
		tabbedPane.addTab("图书管理", panel2);
		tabbedPane.addTab("教师管理", panel3);
		tabbedPane.addTab("个人管理", panel4);
		tabbedPane.addTab("系统设置", panel5);
	}

	public static void main(String[] args) {
		manager_system frame = new manager_system();
		frame.setVisible(true);
	}

}
