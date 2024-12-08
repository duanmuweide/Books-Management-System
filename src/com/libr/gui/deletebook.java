package com.libr.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class deletebook extends JFrame {
    public deletebook() {
        setTitle("修改个人信息");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 创建输入组件
        JTextField idField = new JTextField("请输入ID",20);
        JTextField nameField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JTextField typeField = new JTextField(20);
        JTextField statusField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JTextField numberField = new JTextField(20);
        // 添加组件到界面
        int y = 0;
        
        addLabelAndFieldWithButton("ID:", idField, gbc, y++); // 第一行有按钮
        addLabelAndField("名称:", nameField, gbc, y++);
        addLabelAndField("作者:", authorField, gbc, y++);
        addLabelAndField("类型:", typeField, gbc, y++);
        addLabelAndField("状态:", statusField, gbc, y++);
        addLabelAndField("位置:", locationField, gbc, y++);
        addLabelAndField("数量:", numberField, gbc, y++);
        
        // 添加删除按钮
        JButton deleteButton = new JButton("删除");
        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
        add(deleteButton, gbc);

        // 设置窗口居中
        setLocationRelativeTo(null);
        
        
        //删除按钮监听事件
        deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

        
    }

    private void addLabelAndFieldWithButton(String labelText, JTextField field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(field, gbc);

        JButton editButton = new JButton("查看");
        gbc.gridx = 2;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        add(editButton, gbc);

  
        editButton.addActionListener(new ActionListener() {
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
        deletebook frame=new deletebook();
        frame.setVisible(true);
    }
}