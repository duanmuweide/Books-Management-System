package com.libr.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//暂时账户只有输入anna，密码只有输入1234才能登录成功
public class login extends JFrame {

	public login() {
		setSize(400, 300);
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel container = new JPanel();
		container.setBackground(Color.white);
		container.setLayout(null);

		JLabel label1 = new JLabel("身份： ");
		label1.setBounds(50, 40, 50, 30);
		container.add(label1);

		// 创建下拉框
		String[] identities = { "学生", "教职工" };
		JComboBox<String> comboBox = new JComboBox<>(identities);
		comboBox.setBounds(110, 40, 220, 30);
		container.add(comboBox);

		JLabel label2 = new JLabel("账户： ");
		label2.setBounds(50, 80, 50, 30);
		container.add(label2);

		JTextField tf_id = new JTextField(28);
		tf_id.setBounds(110, 80, 220, 30);
		container.add(tf_id);

		JLabel label3 = new JLabel("密码： ");
		label3.setBounds(50, 130, 50, 30);
		container.add(label3);

		JPasswordField pf_pwd = new JPasswordField(28);
		pf_pwd.setBounds(110, 130, 220, 30);
		container.add(pf_pwd);

		JButton btn_login = new JButton("登录");
		btn_login.setBounds(70, 210, 120, 30);
		container.add(btn_login);

		JButton btn_register = new JButton("注册");
		btn_register.setBounds(200, 210, 120, 30);
		container.add(btn_register);

		// 1. 创建复选框，指定显示文本
		JCheckBox cb_autologin = new JCheckBox("自动登录");

		// 2. 创建复选框，指定显示文本和是否选中
		JCheckBox cb_remember = new JCheckBox("记住密码", true);

		// 3. 设置复选框透明
		cb_autologin.setOpaque(false); // setOpaque(false)用于将组件设置为透明
		cb_remember.setOpaque(false);

		cb_autologin.setBounds(90, 170, 120, 30);
		cb_remember.setBounds(220, 170, 120, 30);

		// 4. 添加复选框到容器panel1中
		container.add(cb_autologin);
		container.add(cb_remember);

		this.add(container);

		btn_login.addActionListener(new ActionListener() {

			// 这个方法是事件处理程序，响应事件的发生被调用
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取下拉列表的信息，但是注意要写在监听器里面
				String selectedIdentity = (String) comboBox.getSelectedItem();

				// 获取文本框的内容，存储到一个变量中
				String id = tf_id.getText();
				// getPassword()返回的是一个char[]
				char[] chs = pf_pwd.getPassword();
				// 这里将一个char[]转成一个字符串
				String pwd = new String(chs);

				// 注意：字符串判断是否相等要用equals()方法
				if ("anna".equals(id) && "1234".equals(pwd) && selectedIdentity.equals("学生")) {
					// 显示新窗体的同时，可以将当前窗体关闭
					// dispose()方法表示销毁一个窗体
					login.this.dispose();
					// 如果希望显示另一个窗体，直接new一个另外的窗体，设置为可见即可
					// 如何实现将本窗体输入的账户传递给下一个窗体
					// 可以考虑通过构造函数传递数据给下一个窗体
					// 传递的数据可以是任何类型，如String、int、List、数组等等

					student_system frame = new student_system("anna");
					frame.setVisible(true);
					System.out.println(selectedIdentity);
				} else if ("anna".equals(id) && "1234".equals(pwd) && selectedIdentity.equals("教职工")) {
					login.this.dispose();
					manager_system frame = new manager_system();
					frame.setVisible(true);
					System.out.println(selectedIdentity);
				} else {
					// showMessageDialog()用于弹出一个消息对话框
					// 至少传入两个参数：
					// 参数1： 对话框所属的组件，比如一个窗体
					// 参数2： 消息内容
					// 因为这里处于一个匿名监听器类内部，所以this表示当前监听器对象
					// 如果希望能够表示当前窗体，要用 窗体类.this
					JOptionPane.showMessageDialog(login.this, "用户名或密码不正确，请重新输入！");
				}
			}
		});

		btn_register.addActionListener(new ActionListener() {

			// 这个方法是事件处理程序，响应事件的发生被调用
			@Override
			public void actionPerformed(ActionEvent e) {
				// 显示新窗体的同时，可以将当前窗体关闭
				// dispose()方法表示销毁一个窗体
				login.this.dispose();
				// 如果希望显示另一个窗体，直接new一个另外的窗体，设置为可见即可
				// 如何实现将本窗体输入的账户传递给下一个窗体
				// 可以考虑通过构造函数传递数据给下一个窗体
				// 传递的数据可以是任何类型，如String、int、List、数组等等
				register frame = new register();
				frame.setVisible(true);

			}
		});

	}

	public static void main(String[] args) {
		login frame = new login();
		frame.setVisible(true);
	}
}
