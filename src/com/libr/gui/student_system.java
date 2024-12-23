package com.libr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class student_system extends JFrame {
	private String id;

	public student_system(String userid) {
		id = userid;
		// 设置窗口标题和大小
		setTitle("功能选择界面");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 窗口居中
		setLayout(new BorderLayout()); // 使用边界布局

		// 创建用户头像和名称的标签
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(0, 40));

		JLabel label_hint = new JLabel("当前登录用户：");
		label_hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel userLabel = new JLabel("用户名：");// , SwingConstants.CENTER
		userLabel.setFont(new Font("Serif", Font.BOLD, 20));
		userLabel.setText(id);

		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(label_hint);
		p1.add(userLabel);
		add(p1, BorderLayout.NORTH);

		// 创建一个面板来放置按钮
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1, 10, 10)); // 使用网格布局

		// 创建功能按钮
		JButton btnSearchBook = new JButton("查找图书");
		Font currentFont = btnSearchBook.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), 25);
        btnSearchBook.setFont(newFont);
        
		JButton btnViewBorrowRecord = new JButton("查看借书记录");
		currentFont = btnViewBorrowRecord.getFont();
        newFont = new Font(currentFont.getName(), currentFont.getStyle(), 25);
        btnViewBorrowRecord.setFont(newFont);
        
		JButton btnViewReturnRecord = new JButton("查看还书记录");
		currentFont = btnViewReturnRecord.getFont();
        newFont = new Font(currentFont.getName(), currentFont.getStyle(), 25);
        btnViewReturnRecord.setFont(newFont);
		
		JButton btnChangeInformation = new JButton("修改个人信息");
		currentFont = btnChangeInformation.getFont();
        newFont = new Font(currentFont.getName(), currentFont.getStyle(), 25);
        btnChangeInformation.setFont(newFont);
        
        JButton outlogin = new JButton("退出登录");
		currentFont = outlogin.getFont();
        newFont = new Font(currentFont.getName(), currentFont.getStyle(), 25);
        outlogin.setFont(newFont);

		// 将按钮添加到面板中
		buttonPanel.add(btnSearchBook);
		buttonPanel.add(btnViewBorrowRecord);
		buttonPanel.add(btnViewReturnRecord);
		buttonPanel.add(btnChangeInformation);
		buttonPanel.add(outlogin);

		// 将面板添加到窗口中
		add(buttonPanel, BorderLayout.CENTER);

		btnChangeInformation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				student_system.this.dispose();
				personal_information frame = new personal_information(userid);
				frame.setVisible(true);

			}
		});
		
		btnSearchBook.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				student_system.this.dispose();
				find_stu frame = new find_stu(userid);
				frame.setVisible(true);
				
			}
		});
		
		btnViewBorrowRecord.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				student_system.this.dispose();
				stu_borrowRecord frame = new stu_borrowRecord();
				frame.setVisible(true);
				
			}
		});
		
		btnViewReturnRecord.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				student_system.this.dispose();
				stu_returnRecord frame = new stu_returnRecord();
				frame.setVisible(true);
				
			}
		});
		
		outlogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				student_system.this.dispose();
				login frame = new login();
				frame.setVisible(true);
				
			}
		});

	}

	public static void main(String[] args) {
		// 创建窗口实例并设置为可见
		student_system frame = new student_system("");
		frame.setVisible(true);
	}
}
