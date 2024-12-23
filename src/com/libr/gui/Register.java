package com.libr.gui;

import java.awt.Image;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.libr.entity.UserInfo;
import com.libr.service.impl.UserServiceImpl;

public class Register extends JFrame {
//	private JTextField userNameField;
//	private JPasswordField userPasswordField;
//	private JTextField userQuestionField;
//	private JTextField userAnswerField;
//	private JComboBox<String> userGenderField;
//	private JTextField userContactField;
//	private JTextField userRealnameField;
//	private JTextField userAddressField;
	
//	private JTextField userEmailField;
//	private JTextField userMajorField;
//	private JTextField userIdField;

	public Register() {
		this.setSize(500, 600);
		this.setTitle("注册界面");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 设置绝对布局
		this.setLayout(null);
	}

	public static void main(String[] args) {
		Register frame = new Register();
		frame.setVisible(true);
	}
}
