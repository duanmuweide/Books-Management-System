package com.libr.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.UserInfo;

// 创建一个登录界面
class Login extends JFrame {

	public Login() {
		setSize(400, 350); // 设置窗体大小
		setTitle("登录界面"); // 设置窗体的标题
		// 关闭窗体，结束整个程序
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel container = new JPanel();
		container.setBackground(Color.white);
		container.setLayout(null);// 使用绝对布局

		JLabel label1 = new JLabel("用户名： ");
		label1.setFont(new Font("楷体", Font.PLAIN, 18));

		JLabel label2 = new JLabel("密码： ");
		label2.setFont(new Font("楷体", Font.PLAIN, 18));
		label1.setFont(new Font("楷体", Font.PLAIN, 18));

		JTextField tf_id = new JTextField(28);
		JPasswordField pf_pwd = new JPasswordField(28);

		JButton btn_Login = new JButton();
		btn_Login.setText("登录");
		JButton btn_register = new JButton("注册");

		label1.setBounds(23, 96, 87, 30);
		label2.setBounds(23, 154, 77, 30);
		tf_id.setBounds(120, 98, 220, 30);
		pf_pwd.setBounds(120, 156, 220, 30);
		btn_Login.setBounds(46, 230, 120, 43);
		btn_register.setBounds(222, 230, 135, 43);

		container.add(label1);
		container.add(tf_id);
		container.add(label2);
		container.add(pf_pwd);
		container.add(btn_Login);
		container.add(btn_register);

		getContentPane().add(container);

		JLabel TitleLabel = new JLabel("图书管理系统");
		TitleLabel.setFont(new Font("楷体", Font.PLAIN, 25));
		TitleLabel.setBounds(140, 23, 185, 43);
		container.add(TitleLabel);
		// 增加图片
		JPanel logo = new BackgroundPanel("src/pictures/dragon1.png");
		logo.setBounds(0, 0, 100, 100);
		container.add(logo);

		JPanel back = new BackgroundPanel("src/pictures/back1.jpg");
		back.setBounds(0, 0, 400, 350);
		container.add(back);

		// 添加按钮监听器
		btn_Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 登录按钮的点击事件
				int userId = Integer.parseInt(tf_id.getText());// 把String转换为int
				String password = new String(pf_pwd.getPassword());

				UserInfoDaoImpl uidi = new UserInfoDaoImpl();
				UserInfo userInfo = uidi.getOneById(userId);
				if (userId != 0 && password != null && userId == userInfo.getUserId()
						&& userInfo.getUserPassword().equals(password)) {
					if (userInfo.getUserAdmin()) {
						Login.this.dispose();
						manager_system managerSystem = new manager_system(userId);
						managerSystem.setVisible(true);
					} else {
						Login.this.dispose();
						student_system studentSystem = new student_system(userId);
						studentSystem.setVisible(true);
					}
				} else {
					// 如果登录失败，可以提示用户
					System.out.println("用户名或密码错误！");
				}
			}
		});

		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
				Register register = new Register();
			    register.setVisible(true);
				
			}
		});
	}

	class BackgroundPanel extends JPanel {

		private String imgPath; // 用于指定图片路径

		public BackgroundPanel(String imgPath) {
			this.imgPath = imgPath;
		}

		@Override
		protected void paintComponent(Graphics g) {
			ImageIcon icon = new ImageIcon(imgPath);
			g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
	}

}
