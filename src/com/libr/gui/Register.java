package com.libr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.UserInfo;
import com.libr.service.impl.UserServiceImpl;

public class Register extends JFrame {
	UserInfoDaoImpl uidi = new UserInfoDaoImpl();
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	private JTextField userQuestionField;
	private JTextField userAnswerField;
	private JComboBox<String> userGenderField;
	private JTextField userContactField;
	private JTextField userRealnameField;
	private JTextField userAddressField;
	private JTextField userEmailField;
	private JTextField userMajorField;

	public Register() {
		setTitle("用户注册界面");
		setSize(400, 570);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);// 上左下右

		// 标题部分
		JLabel titleLabel = new JLabel("注册界面");
		titleLabel.setFont(new Font("楷体", Font.BOLD, 30));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(titleLabel, gbc);

		// 创建输入字段
		userNameField = new JTextField(25);
		userPasswordField = new JPasswordField(25);
		userQuestionField = new JTextField(25);
		userAnswerField = new JTextField(25);
		userGenderField = new JComboBox<>(new String[] { "男", "女" });
		userContactField = new JTextField(25);
		userRealnameField = new JTextField(25);
		userAddressField = new JTextField(25);
		userEmailField = new JTextField(25);
		userMajorField = new JTextField(25);

		// 添加输入字段到界面
		int y = 2;
		addLabelAndField("*用户名:", userNameField, gbc, y++, mainPanel);
		addLabelAndField("*密码:", userPasswordField, gbc, y++, mainPanel);
		userPasswordField.setEchoChar('*');
		addLabelAndField("密保问题:", userQuestionField, gbc, y++, mainPanel);
		addLabelAndField("密保答案:", userAnswerField, gbc, y++, mainPanel);
		addLabelAndComponent("性别:", userGenderField, gbc, y++, mainPanel);
		addLabelAndField("联系方式:", userContactField, gbc, y++, mainPanel);
		addLabelAndField("真实姓名:", userRealnameField, gbc, y++, mainPanel);
		addLabelAndField("地址:", userAddressField, gbc, y++, mainPanel);
		addLabelAndField("邮箱:", userEmailField, gbc, y++, mainPanel);
		addLabelAndField("专业:", userMajorField, gbc, y++, mainPanel);

		// 提交按钮
		JButton submitButton = new JButton("提交");
		submitButton.setFont(new Font("楷体", Font.BOLD, 16));
		submitButton.setFocusPainted(false);
		submitButton.setBackground(new java.awt.Color(72, 118, 255));
		submitButton.setForeground(java.awt.Color.WHITE);
		submitButton.setOpaque(true);
		submitButton.setBorderPainted(false);

		gbc.gridy = y++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(submitButton, gbc);
		
		JButton returnButton = new JButton("返回");
		returnButton.setFont(new Font("楷体", Font.BOLD, 16));
		returnButton.setFocusPainted(false);
		returnButton.setBackground(new java.awt.Color(72, 118, 255));
		returnButton.setForeground(java.awt.Color.WHITE);
		returnButton.setOpaque(true);
		returnButton.setBorderPainted(false);
		
		gbc.gridy = y++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(returnButton, gbc);

		// 设置滚动条
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		// 设置窗口居中
		setLocationRelativeTo(null);

		// 增加背景图片
		JPanel back = new BackgroundPanel("src/pictures/back2.jpg");
		gbc.gridx = 0;
		gbc.gridy = 0; // 放置在顶部
		gbc.gridwidth = 4; // 跨越3列
		gbc.gridheight = 15; // 跨越15行
		gbc.fill = GridBagConstraints.BOTH; // 使其拉伸填充整个区域
		gbc.weightx = 1.0; // 给它足够的水平权重
		gbc.weighty = 1.0; // 给它足够的垂直权重
		mainPanel.add(back, gbc);

		// 提交按钮事件
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo user = new UserInfo();
				user.setUserName(userNameField.getText());
				user.setUserPassword(new String(userPasswordField.getPassword()));//getText不安全
				user.setUserId(generateUserId());
				user.setUserQuestion(userQuestionField.getText());
				user.setUserGender((String) userGenderField.getSelectedItem());
				user.setUserContact(userContactField.getText());
				user.setUserRealname(userRealnameField.getText());
				user.setUserAddress(userAddressField.getText());
				user.setUserEmail(userEmailField.getText());
				user.setUserMajor(userMajorField.getText());
				 // 用户名验证
		        if (user.getUserName() == null || !user.getUserName().matches("^[a-zA-Z0-9]{3,20}$")) {
		            JOptionPane.showMessageDialog(null, "用户名必须为3到20个字母或数字组合！");
		            return;
		        }

		        // 邮箱验证
		        if (user.getUserEmail() == null || !user.getUserEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
		            JOptionPane.showMessageDialog(null, "请输入有效的邮箱地址！");
		            return;
		        }

		        // 密码验证
		        if (user.getUserPassword() == null || !user.getUserPassword().matches("^(?=.*[a-zA-Z])(?=.*\\d).{6,}$")) {
		            JOptionPane.showMessageDialog(null, "密码必须至少6个字符，且包含字母和数字！");
		            return;
		        }

		        // 手机号验证
		        if (user.getUserContact() == null || !user.getUserContact().matches("^1[3-9]\\d{9}$")) {
		            JOptionPane.showMessageDialog(null, "请输入有效的手机号！");
		            return;
		        }

		        // 如果所有检查都通过，执行注册操作
		        UserServiceImpl usi = new UserServiceImpl();
		        usi.registerUser(user);
		        JOptionPane.showMessageDialog(null, "注册成功！");
			}
		});
		
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.this.dispose();
				Login frame=new Login();
				frame.setVisible(true);
			}
		});
		
	}

	public int generateUserId() {
		int uid = uidi.getLastUserID();
		if (uid <= 0) {
			return 2023000001;
		} else
			return uid;
	}

	private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int y, JPanel panel) {
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = y;
		gbc.gridwidth = 2;

		field.setFont(new Font("楷体", Font.PLAIN, 18));
		field.setPreferredSize(new Dimension(200, 30));
		field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		panel.add(field, gbc);
	}

	private void addLabelAndComponent(String labelText, JComponent component, GridBagConstraints gbc, int y,
			JPanel panel) {
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;

		JLabel label = new JLabel(labelText);
		label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = y;
		gbc.gridwidth = 2;

		component.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(component, gbc);
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
		Register frame = new Register();
		frame.setVisible(true);
	}
}
