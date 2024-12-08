package com.libr.gui;

import javax.swing.*;
import java.awt.*;

public class personal_information extends JFrame {
    public personal_information() {
        setTitle("修改个人信息");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 创建输入组件
        JTextField nameField = new JTextField("anna",20);
        JTextField passwordField = new JTextField("1234",20);
        JTextField addressField = new JTextField("China",20);
        JTextField emailField = new JTextField("1234@qq.com",20);
        JTextField majorField = new JTextField("软件外包",20);
        majorField.setFont(new Font(majorField.getFont().getName(), Font.BOLD, majorField.getFont().getSize()));
        

        // 添加组件到界面
        int y = 0;
        addLabelAndFieldWithButton("名称:", nameField, gbc, y++);
        addLabelAndFieldWithButton("密码:", passwordField, gbc, y++);
        addLabelAndFieldWithButton("地址:", addressField, gbc, y++);
        addLabelAndFieldWithButton("邮箱:", emailField, gbc, y++);
        addLabelAndFieldWithButton("专业:", majorField, gbc, y++);

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

        JButton editButton = new JButton("修改");
        gbc.gridx = 3;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(editButton, gbc);
    }

    public static void main(String[] args) {
    	personal_information frame=new personal_information();
    	frame.setVisible(true);
    }
}
