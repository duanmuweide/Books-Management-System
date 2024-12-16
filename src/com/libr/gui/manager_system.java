package com.libr.gui;
//添加图书，修改个人信息，添加其他管理员账户，修改图书信息

//删除图书
//查询 按照编号 名称关键词 入库时间查询
//或根据 用户id查询记录 根据图书id和名称关键来查询
//显示借阅情况和在馆数量

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class manager_system extends JFrame {

	public manager_system() {
		this.setTitle("管理员界面");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		panel1.setBackground(Color.white);
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));  
        
        String[] buttonLabels_student = {
            "查询借书记录", "查询还书记录", "查看预约记录",
            "禁止预约座位", "禁止再次借书"
        };

    
        JTextField textFieldborrow = new JTextField("请输入学生id");
        JTextField textFieldreturn = new JTextField("请输入学生id");
        JTextField textFieldreversation = new JTextField("请输入学生id");
        JTextField textFieldbanrever = new JTextField("请输入学生id");
        JTextField textFieldbanborrow = new JTextField("请输入学生id");

        
        JButton buttonborrow = new JButton("查询借书记录");
        JButton buttonreturn = new JButton("查询还书记录");
        JButton buttonreversation = new JButton("查看预约记录");
        JButton buttonbanrever = new JButton("禁止预约座位");
        JButton buttonbanborrow = new JButton("禁止再次借书");

       
        panel1.add(textFieldborrow);
        panel1.add(buttonborrow);
        panel1.add(textFieldreturn);
        panel1.add(buttonreturn);
        panel1.add(textFieldreversation);
        panel1.add(buttonreversation);
        panel1.add(textFieldbanrever);
        panel1.add(buttonbanrever);
        panel1.add(textFieldbanborrow);
        panel1.add(buttonbanborrow);
        // 将面板添加到窗口内容面板
        getContentPane().add(panel1);

        // 设置窗口居中显示
        setLocationRelativeTo(null);
		

		panel2.setBackground(Color.white);
		JButton addbook=new JButton("添加图书");
		JButton changebook=new JButton("修改图书");
		JButton deletebook=new JButton("删除图书");
		JButton findbook=new JButton("查询图书");
		
		panel2.add(addbook);
		panel2.add(changebook);
		panel2.add(deletebook);
		panel2.add(findbook);
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		panel2.add(new JButton("其他功能"));
		
		
		panel3.setBackground(Color.white);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));  
        
		 JTextField textFieldaddman = new JTextField("请输入id");
	     JTextField textFielddeleteman = new JTextField("请输入id");
	     JTextField textFieldfindman = new JTextField("请输入id");
	     
	     JButton buttonaddman = new JButton("查询借书记录");
	     JButton buttondeleteman = new JButton("查询还书记录");
	     JButton buttonfindman = new JButton("查看预约记录");
	     
	       
	     panel3.add(textFieldaddman);
	     panel3.add(buttonaddman);
	     panel3.add(textFielddeleteman);
	     panel3.add(buttondeleteman);
	     panel3.add(textFieldfindman);
	     panel3.add(buttonfindman);
        // 将面板添加到窗口内容面板
        getContentPane().add(panel3);
   
		panel4.setBackground(Color.white);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
   
        JLabel nameLabel = new JLabel("名称：");
        JLabel passwordLabel = new JLabel("密码：");
        JLabel addressLabel = new JLabel("地址：");
        JLabel emailLabel = new JLabel("邮箱：");
        JLabel phoneLabel = new JLabel("号码：");

        
        JTextField nameField = new JTextField("anna",15);
        JTextField passwordField = new JTextField("110110",15);
        JTextField addressField = new JTextField("china",15);
        JTextField emailField = new JTextField("110110@qq.com",15);
        JTextField phoneField = new JTextField("110110",15);

      
        JButton nameButton = new JButton("修改");
        JButton passwordButton = new JButton("修改");
        JButton addressButton = new JButton("修改");
        JButton emailButton = new JButton("修改");
        JButton phoneButton = new JButton("修改");

        // 添加名称行
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(nameButton);
        panel4.add(namePanel);

        // 添加密码行
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(passwordButton);
        panel4.add(passwordPanel);

        // 添加地址行
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.X_AXIS));
        addressPanel.add(addressLabel);
        addressPanel.add(addressField);
        addressPanel.add(addressButton);
        panel4.add(addressPanel);

        // 添加邮箱行
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        emailPanel.add(emailButton);
        panel4.add(emailPanel);

        // 添加号码行
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.X_AXIS));
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);
        phonePanel.add(phoneButton);
        panel4.add(phonePanel);
		
		panel5.setBackground(Color.white);
		JButton changeuser=new JButton("切换用户");
		panel5.add(changeuser);
		JButton outlogin=new JButton("退出登录");
		panel5.add(outlogin);
		
		// 1. 创建页签面板
		// 可以通过构造函数传入一个参数，表示标签的位置
		// 顶部、底部、左、右
		// top、bottom、left、right
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		

		// 3. 将各个JPanel添加到页签面板中，作为一个页签，第一个参数用于指定页签的标题
		tabbedPane.addTab("学生管理", panel1);
		tabbedPane.addTab("图书管理", panel2);
		tabbedPane.addTab("管理员设置", panel3);
		tabbedPane.addTab("个人管理", panel4);
		tabbedPane.addTab("系统设置", panel5);
		
		
		// 2. 将页签面板添加到窗体中
		this.add(tabbedPane);
				
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		
//panel1	
		buttonborrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_system.this.dispose();
				stu_borrowRecord frame=new stu_borrowRecord();
				frame.setVisible(true);
			}
		});
		buttonreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_system.this.dispose();
				stu_returnRecord frame=new stu_returnRecord();
				frame.setVisible(true);
			}
		});
		
		buttonbanborrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

//panel2
		addbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_system.this.dispose();
				addbook frame = new addbook();
				frame.setVisible(true);
			}
		});
		
		changebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_system.this.dispose();
				changebook frame = new changebook();
				frame.setVisible(true);
			}
		});
		
		deletebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_system.this.dispose();
				deletebook frame = new deletebook();
				frame.setVisible(true);
			}
		});
		
		findbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager_system.this.dispose();
				find_man frame = new find_man();
				frame.setVisible(true);
			}
		});
//panel3
	     buttonaddman.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				}
			});
	     buttondeleteman.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    	 }
	     });
	     buttonfindman.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    	 }
	     });
//panel4
	     nameButton.addActionListener(new ActionListener() {
		    	 public void actionPerformed(ActionEvent e) {
		    		 
		    	 }
		     });
	     passwordButton.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    	 }
	     });
	     addressButton.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    	 }
	     });
	     emailButton.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    	 }
	     });
	     phoneButton.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    	 }
	     });
//panel5
	     changeuser.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 manager_system.this.dispose();
	    		 login frame = new login();
	    		 frame.setVisible(true);
	    	 }
	     });
	     outlogin.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 manager_system.this.dispose();
	    		 login frame = new login();
	    		 frame.setVisible(true);
	    	 }
	     });

	}



	public static void main(String[] args) {
		manager_system frame = new manager_system();
		frame.setVisible(true);
	}

}
