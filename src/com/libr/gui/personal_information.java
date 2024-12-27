package com.libr.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class personal_information extends JFrame {
	private int userid;
	
	 // 自定义的JPanel，用于绘制背景图片
    private class BackgroundImagePanel extends JPanel {
        private Image backgroundImage;

        public BackgroundImagePanel(String filePath) throws IOException {
            // 加载背景图片
            backgroundImage = ImageIO.read(new File(filePath));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 绘制背景图片
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    public personal_information(int userID) {
    	userid=userID;
    	
    	// 创建一个自定义的JPanel作为内容面板
        BackgroundImagePanel contentPanel;
		try {
			contentPanel = new BackgroundImagePanel("com.libr.gui/背景1.jpg");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // 设置内容面板
        //setContentPane(contentPanel);
    	
        setTitle("修改个人信息");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 创建输入组件
        JTextField nameField = new JTextField("nihao",20);
        JTextField passwordField = new JTextField("1234",20);
        JTextField phonenumberField=new JTextField("111111111",20);
        JTextField realnameField=new JTextField("你好",20);
        realnameField.setFont(new Font(realnameField.getFont().getName(), Font.BOLD, realnameField.getFont().getSize()));
        JTextField addressField = new JTextField("中国",20);
        addressField.setFont(new Font(addressField.getFont().getName(), Font.BOLD, addressField.getFont().getSize()));
        JTextField emailField = new JTextField("1234@qq.com",20);
        JTextField majorField = new JTextField("软件外包",20);
        majorField.setFont(new Font(majorField.getFont().getName(), Font.BOLD, majorField.getFont().getSize()));
        

        // 添加组件到界面
        int y = 0;
        addLabelAndFieldWithButton("名称:", nameField, gbc, y++);
        addLabelAndFieldWithButton("密码:", passwordField, gbc, y++);
        addLabelAndFieldWithButton("电话:", phonenumberField, gbc, y++);
        addLabelAndFieldWithButton("真名:", realnameField, gbc, y++);
        addLabelAndFieldWithButton("地址:", addressField, gbc, y++);
        addLabelAndFieldWithButton("邮箱:", emailField, gbc, y++);
        addLabelAndFieldWithButton("专业:", majorField, gbc, y++);
        
        //添加修改按钮
        JButton changeButton = new JButton("修改");
        gbc.gridx = 2;
        gbc.gridy = y++;
        
        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
        add(changeButton, gbc);
        
        changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				UserInfo ui=new UserInfo();
//				UserServiceImpl user=new UserServiceImpl();
//				user.changeUserInfo(ui);
				
			}
		});
        
        // 添加返回按钮
        JButton backButton = new JButton("返回");
        gbc.gridx = 2;
        gbc.gridy = y;
        
        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
        add(backButton, gbc);
        
        backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				personal_information.this.dispose();
				student_system frame = new student_system(userid);
				frame.setVisible(true);
			}
		});
        
        // 设置窗口居中
        setLocationRelativeTo(null);
    }

    private void addLabelAndFieldWithButton(String labelText, JTextField field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        add(field, gbc);
    }

    public static void main(String[] args) {
    	personal_information frame=new personal_information(1);
    	frame.setVisible(true);
    }
}
