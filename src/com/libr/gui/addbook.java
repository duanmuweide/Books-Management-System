package com.libr.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addbook extends JFrame {
    public addbook() {
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
        JTextField typeField = new JTextField(20);
        typeField.setFont(new Font(typeField.getFont().getName(), Font.BOLD, typeField.getFont().getSize()));
        JTextField authorField = new JTextField(20);
        authorField.setFont(new Font(authorField.getFont().getName(), Font.BOLD, authorField.getFont().getSize()));
        JTextField locationField = new JTextField(20);
        locationField.setFont(new Font(locationField.getFont().getName(), Font.BOLD, locationField.getFont().getSize()));
        JTextField numberield = new JTextField(20);

        // 添加组件到界面
        int y = 0;
        addLabelAndField("ID:", idField, gbc, y++);
        addLabelAndField("名称:", nameField, gbc, y++);
        addLabelAndField("类型:", typeField, gbc, y++);
        addLabelAndField("作者:", authorField, gbc, y++);
        addLabelAndField("位置:", locationField, gbc, y++);
        addLabelAndField("数量:", numberield, gbc, y++);

        // 添加提交按钮
        JButton submitButton = new JButton("添加书籍");
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

    public static void main(String[] args) {
    	addbook frame=new addbook(); 
       frame.setVisible(true);
    }
}