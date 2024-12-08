package com.libr.gui;

import javax.swing.*;
import java.awt.*;

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

