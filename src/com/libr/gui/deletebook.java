package com.libr.gui;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.libr.entity.Book;
import com.libr.service.impl.AdminServiceImpl;
import com.libr.util.DatabaseUtil;

public class deletebook extends JFrame {
	  // 创建输入组件
	JTextField idField = new JTextField("请输入ID进行查询",20);
	JTextField nameField = new JTextField(20);
    JTextField authorField = new JTextField(20);
    JTextField typeField = new JTextField(20);
    JTextField statusField = new JTextField(20);
    JTextField locationField = new JTextField(20);
    JTextField numberField = new JTextField(20);
    JTextField bookTimeField=new JTextField(20);
    public deletebook() {
        setTitle("删除图书");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        idField.setFont(new Font(idField.getFont().getName(), Font.BOLD, idField.getFont().getSize()));
        nameField.setFont(new Font(nameField.getFont().getName(), Font.BOLD, nameField.getFont().getSize()));
        authorField.setFont(new Font(authorField.getFont().getName(), Font.BOLD, authorField.getFont().getSize()));
        typeField.setFont(new Font(typeField.getFont().getName(), Font.BOLD, typeField.getFont().getSize()));
        statusField.setFont(new Font(statusField.getFont().getName(), Font.BOLD, statusField.getFont().getSize()));
        locationField.setFont(new Font(locationField.getFont().getName(), Font.BOLD, locationField.getFont().getSize()));
        
        // 添加组件到界面
        int y = 0;
        
        addLabelAndFieldWithButton("ID:", idField, gbc, y++); // 第一行有按钮
        addLabelAndField("名称:", nameField, gbc, y++);
        addLabelAndField("作者:", authorField, gbc, y++);
        addLabelAndField("类型:", typeField, gbc, y++);
        addLabelAndField("借阅状态:", statusField, gbc, y++);
        addLabelAndField("位置:", locationField, gbc, y++);
        addLabelAndField("数量:", numberField, gbc, y++);
        addLabelAndField("入库时间:", bookTimeField, gbc, y++);
        
        // 添加删除按钮
        JButton deleteButton = new JButton("删除");
        gbc.gridx = 1;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
        add(deleteButton, gbc);
        
      //添加返回按钮
        JButton backButton = new JButton("返回");
        gbc.gridy = y;
        add(backButton, gbc);

        // 设置窗口居中
        setLocationRelativeTo(null);
        
        //删除按钮监听事件
        deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String idString=idField.getText();
				 int id=Integer.parseInt(idString);
	    	     //执行删除操作
				 Connection con=DatabaseUtil.getConnection();
	    	     AdminServiceImpl adm=new AdminServiceImpl(con);
	    	     adm.deleteBook(id);
			}
		});
        
        backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletebook.this.dispose();
				manager_system frame = new manager_system();
				frame.setVisible(true);

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
            	//获取输入的id
            	String idString=idField.getText();
    	        int id= Integer.parseInt(idString);
    	       
    	        //在数据库中查找并显示书的相关信息   	       
    	        Connection con=DatabaseUtil.getConnection();
    	        AdminServiceImpl adm=new AdminServiceImpl(con);
    	        Book book=adm.searchBookById(id);
				//显示信息
    	    	nameField.setText(book.getBookName());
    	        typeField.setText(book.getBookType());
    	        authorField.setText(book.getBookWriterName());
    	        numberField.setText(String.valueOf(book.getBookNumber()));
    	        locationField.setText(book.getBookPosition());
    	        if(book.getBookStatement()==false) {
    	        statusField.setText("已被借阅");
    	        }else {
    	        statusField.setText("未被借阅");
    	        }
    	        bookTimeField.setText(book.getBookTime().toString());
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
