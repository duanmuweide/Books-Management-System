package com.libr.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class register extends JFrame {

	public register() {
		this.setSize(400, 300);
		this.setTitle("注册页面");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		container.setBackground(Color.white);

		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		// 将大容器添加到窗体上
		this.add(container);

		JPanel id1 = new JPanel();
		// JPanel作为容器组件，它里面可以添加任何其他组件，包括JPanel
		JLabel id2 = new JLabel("名称： ");
		id1.add(id2);

		JTextField id3 = new JTextField(28);
		id1.add(id3);

		JPanel pwd1 = new JPanel();
		JLabel pwd2 = new JLabel("密码： ");
		pwd1.add(pwd2);

		JTextField pwd3 = new JTextField(28);
		pwd1.add(pwd3);

		JPanel phone1 = new JPanel();
		JLabel phone2 = new JLabel("手机号： ");
		phone1.add(phone2);

		JTextField phone3 = new JTextField(28);
		phone1.add(phone3);

		JPanel email1 = new JPanel();
		JLabel email2 = new JLabel("邮箱： ");
		email1.add(email2);

		JTextField email3 = new JTextField(28);
		email1.add(email3);

		JPanel gender = new JPanel();
		JLabel gender1 = new JLabel("性别： ");
		gender.add(gender1);
		String[] genders = { "男", "女" };
		JComboBox<String> gender2 = new JComboBox<>(genders);
		gender2.setPreferredSize(new Dimension(257, 20));
		gender.add(gender2);

		JButton submit = new JButton("提交");

		id1.setBackground(Color.white);
		pwd1.setBackground(Color.white);
		phone1.setBackground(Color.white);
		email1.setBackground(Color.white);
		gender.setBackground(Color.white);

		container.add(id1);
		container.add(pwd1);
		container.add(phone1);
		container.add(email1);
		container.add(gender);
		container.add(submit);

	}

	public static void main(String[] args) {
		register frame = new register();
		frame.setVisible(true);
	}

}
