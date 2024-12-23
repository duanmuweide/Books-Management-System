package com.libr.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class register extends JFrame {
    public register() {
        setTitle("注册界面");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 创建输入组件
        JTextField idField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font(nameField.getFont().getName(), Font.BOLD, nameField.getFont().getSize()));
        JPasswordField passwordField = new JPasswordField(20);
        JTextField realNameField = new JTextField(20);
        realNameField.setFont(new Font(realNameField.getFont().getName(), Font.BOLD, realNameField.getFont().getSize()));
        JTextField addressField = new JTextField(20);
        addressField.setFont(new Font(addressField.getFont().getName(), Font.BOLD, addressField.getFont().getSize()));
        JTextField emailField = new JTextField(20);
        JTextField majorField = new JTextField(20);
        majorField.setFont(new Font(majorField.getFont().getName(), Font.BOLD, majorField.getFont().getSize()));
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"男", "女"});

        // 添加组件到界面
        int y = 0;
        addLabelAndField("ID:", idField, gbc, y++);
        addLabelAndField("名称:", nameField, gbc, y++);
        addLabelAndField("密码:", passwordField, gbc, y++);
        addLabelAndField("真名:", realNameField, gbc, y++);
        addLabelAndField("地址:", addressField, gbc, y++);
        addLabelAndField("邮箱:", emailField, gbc, y++);
        addLabelAndField("专业:", majorField, gbc, y++);
        addLabelAndComponent("性别:", genderComboBox, gbc, y++);

        // 添加提交按钮
        JButton submitButton = new JButton("提交");
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 3; // 设置宽度占满整个布局
        gbc.fill = GridBagConstraints.NONE; // 不填充整个区域
        gbc.anchor = GridBagConstraints.CENTER; // 居中对齐
        add(submitButton, gbc);

        // 设置窗口居中
        setLocationRelativeTo(null);
        
        submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取注册的内容
				String id = idField.getText();
				String name=nameField.getText();
				String realname=realNameField.getText();
				String address=addressField.getText();
				String email=emailField.getText();
				String major=majorField.getText();
				String selectedgender = (String) genderComboBox.getSelectedItem();
				// getPassword()返回的是一个char[]
				char[] chs = passwordField.getPassword();
				// 这里将一个char[]转成一个字符串
				String pwd = new String(chs);
				
				//正则表达式判断信息是否合适
				if(!Pattern.matches("^[1-9]\\d{9}$",id)) {
					JOptionPane.showMessageDialog(register.this, "id应有十位数字且不能以0开头");
				}
				else if(!Pattern.matches("([\\u4E00-\\u9FA5]|[a-zA-Z]|[0-9])+",name)) {
					JOptionPane.showMessageDialog(register.this, "名称只能是汉字字母数字，且不能为空");
				}else if(!Pattern.matches("^(?=\\S*[0-9])(?=\\S*[A-Za-z])\\S{8,}$",pwd)) {
					JOptionPane.showMessageDialog(register.this, "密码至少八位且要包含数字和字母");
				}else if(!Pattern.matches("([\\u4E00-\\u9FA5]|[a-zA-Z])+",realname)) {
					JOptionPane.showMessageDialog(register.this, "真名只能是汉字字母，且不能为空");
				}else if(!Pattern.matches("([\\u4E00-\\u9FA5]|[0-9])+",address)) {
					JOptionPane.showMessageDialog(register.this, "地址只能是汉字数字，且不能为空");
				}else 
					if(!Pattern.matches("\\\\w+@\\\\w+(\\\\.\\\\w+)+",email)) {
					JOptionPane.showMessageDialog(register.this, "邮箱格式不对");
				}else if(!Pattern.matches("[\\u4E00-\\u9FA5]+",major)) {
					JOptionPane.showMessageDialog(register.this, "专业只能为且不能为空");
				}else  {
					//添加到数据库中
					
				}
				
				
			}
		});
        
    }

    private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        add(field, gbc);
    }

    private void addLabelAndComponent(String labelText, JComponent component, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        add(component, gbc);
    }

    public static void main(String[] args) {
       register frame=new register(); 
       frame.setVisible(true);
    }
}

