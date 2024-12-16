package com.libr.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class changebook extends JFrame {
	JTextField idField = new JTextField("请输入ID",20);
	JTextField nameField = new JTextField(20);
    JTextField authorField = new JTextField(20);
    JTextField typeField = new JTextField(20);
    JTextField statusField = new JTextField(20);
    JTextField locationField = new JTextField(20);
    JTextField numberField = new JTextField(20);
    public changebook() {
        setTitle("修改图书信息");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
      
        // 添加组件到界面
        int y = 0;
        
        addLabelAndFieldWithButton("ID:", idField, gbc, y++); // 第一行有按钮
        addLabelAndField("名称:", nameField, gbc, y++);
        addLabelAndField("类型:", typeField, gbc, y++);
        addLabelAndField("作者:", authorField, gbc, y++);
        addLabelAndField("位置:", locationField, gbc, y++);
        addLabelAndField("数量:", numberField, gbc, y++);
        addLabelAndField("状态:", statusField, gbc, y++);
        
        // 添加修改按钮
        JButton changeButton = new JButton("修改");
        gbc.gridx = 1;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
        add(changeButton, gbc);
        
        //添加返回按钮
        JButton backButton = new JButton("返回");
        gbc.gridy = y;
        add(backButton, gbc);
        
        // 设置窗口居中
        setLocationRelativeTo(null);
        
        
        //修改按钮监听事件
        changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String id=idField.getText();
	    	     String name=nameField.getText();
	    	     String type=typeField.getText();
	    	     String author=authorField.getText();
	    	     String location=locationField.getText();
	    	     String number=numberField.getText();
	    	     String status=statusField.getText();
	    	     if(!Pattern.matches("[1-9][0-9]*",id)) {
						JOptionPane.showMessageDialog(changebook.this, "id不能为空");
					}
					else if(!Pattern.matches("([\\u4E00-\\u9FA5]|[a-zA-Z]|[0-9])+",name)) {
						JOptionPane.showMessageDialog(changebook.this, "名称只能是汉字字母数字，且不能为空");
					}else if(!Pattern.matches("[\u4E00-\\u9FA5]+",type)) {
						JOptionPane.showMessageDialog(changebook.this, "类型不能为空");
					}else if(!Pattern.matches("([\u4E00-\\u9FA5]|[a-zA-Z])+",author)) {
						JOptionPane.showMessageDialog(changebook.this, "作者只能是汉字字母，且不能为空");
					}else if(!Pattern.matches("([a-zA-Z]|[0-9])+",location)) {
						JOptionPane.showMessageDialog(changebook.this, "位置只能是字母数字，且不能为空");
					}else if(!Pattern.matches("[1-9]+",number)) {
						JOptionPane.showMessageDialog(changebook.this, "数量不能为空");
					}else  if(!Pattern.matches("[\u4E00-\\u9FA5]+",status)) {
						JOptionPane.showMessageDialog(changebook.this, "状态不能为空");
					}else{
						//执行修改操作
					}

			}
		});

        backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changebook.this.dispose();
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
            	//获取注册的内容
            	 String id=idField.getText();
	    	     String name=nameField.getText();
	    	     String type=typeField.getText();
	    	     String author=authorField.getText();
	    	     String location=locationField.getText();
	    	     String number=numberField.getText();
				//正则表达式判断信息是否合适
				if(!Pattern.matches("[0-9]+",id)) {
					JOptionPane.showMessageDialog(changebook.this, "id不能为空");
				}else  {
					//查看图书信息操作
					
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

    public static void main(String[] args) {
        changebook frame=new changebook();
        frame.setVisible(true);
    }
}
