package com.libr.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

		JLabel label3 = new JLabel("身份： ");
		label3.setFont(new Font("楷体", Font.PLAIN, 18));
		label1.setFont(new Font("楷体", Font.PLAIN, 18));

		JTextField tf_id = new JTextField(28);
		JPasswordField pf_pwd = new JPasswordField(28);

		JButton btn_Login = new JButton();
		btn_Login.setText("登录");
		JButton btn_register = new JButton("注册");

		label1.setBounds(23, 154, 87, 30);
		label2.setBounds(23, 213, 77, 30);
		label3.setBounds(23, 100, 77, 30);
		tf_id.setBounds(120, 156, 220, 30);
		pf_pwd.setBounds(120, 213, 220, 30);
		btn_Login.setBounds(50, 253, 120, 43);
		btn_register.setBounds(228, 253, 135, 43);

		container.add(label1);
		container.add(label3);
		container.add(tf_id);
		container.add(label2);
		container.add(pf_pwd);
		container.add(btn_Login);
		container.add(btn_register);

		getContentPane().add(container);

		JLabel TitleLabel = new JLabel("图书管理系统");
		TitleLabel.setFont(new Font("楷体", Font.PLAIN, 25));
		TitleLabel.setBounds(110, 26, 185, 43);
		container.add(TitleLabel);
		// 复选框
		String[] items = { "请选择你的身份", "学生", "管理员" };
		JComboBox comboBox = new JComboBox(items);
		comboBox.setBounds(120, 100, 220, 28);
		container.add(comboBox);
		// 增加图片
		JPanel logo = new BackgroundPanel("src/pictures/dragon1.png");
		logo.setBounds(0, 0, 100, 100);
		container.add(logo);
		
		JPanel back = new BackgroundPanel("src/pictures/back1.jpg");
		back.setBounds(0, 0, 400, 350);
		container.add(back);
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
	
	btn_Login.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedIdentity = (String) comboBox.getSelectedItem();
			
			// 获取文本框的内容，存储到一个变量中
			String id = tf_id.getText();
			// getPassword()返回的是一个char[]
			char[] chs = pf_pwd.getPassword();
			// 这里将一个char[]转成一个字符串
			String pwd = new String(chs);
			
			if ("anna".equals(id) && "1234".equals(pwd) && selectedIdentity.equals("学生")) {
				// 显示新窗体的同时，可以将当前窗体关闭
				// dispose()方法表示销毁一个窗体
				Login.this.dispose();
				// 如果希望显示另一个窗体，直接new一个另外的窗体，设置为可见即可
				// 如何实现将本窗体输入的账户传递给下一个窗体
				// 可以考虑通过构造函数传递数据给下一个窗体
				// 传递的数据可以是任何类型，如String、int、List、数组等等

				student_system frame = new student_system("anna");
				frame.setVisible(true);
				System.out.println(selectedIdentity);
			} else if ("anna".equals(id) && "1234".equals(pwd) && selectedIdentity.equals("管理员")) {
				Login.this.dispose();
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
				JOptionPane.showMessageDialog(Login.this, "用户名或密码不正确，请重新输入！");
			}
		}
	});

	btn_register.addActionListener(new ActionListener() {

		// 这个方法是事件处理程序，响应事件的发生被调用
		@Override
		public void actionPerformed(ActionEvent e) {
			// 显示新窗体的同时，可以将当前窗体关闭
			// dispose()方法表示销毁一个窗体
			Login.this.dispose();
			// 如果希望显示另一个窗体，直接new一个另外的窗体，设置为可见即可
			// 如何实现将本窗体输入的账户传递给下一个窗体
			// 可以考虑通过构造函数传递数据给下一个窗体
			// 传递的数据可以是任何类型，如String、int、List、数组等等
			Register frame = new Register();
			frame.setVisible(true);

		}
	});

	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
	}
}
