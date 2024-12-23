package com.libr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.libr.entity.Book;
import com.libr.service.impl.AdminServiceImpl;
import com.libr.service.impl.UserServiceImpl;
import com.libr.util.DatabaseUtil;

public class find_stu extends JFrame {

    private JTextField bookNameField;
    private JTextField authorNameField;
    private String userid;
    
    // 创建表格列名
 	String[] columnNames = { "编号", "书名", "作者名", "类型", "借阅状态", "位置", "数量", "入库时间" };

 	// 创建表格数据
 	Object[][] data;
 	// 创建表格模型
 	DefaultTableModel model = new DefaultTableModel(data, columnNames);
 	// 创建表格
 	JTable table = new JTable(model);
 	JScrollPane scrollPane = new JScrollPane(table);
 	
    public find_stu(String useridd) {
    	userid=useridd;
        // 设置窗口标题和默认关闭操作
        setTitle("学生图书查找页面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // north部分：输入图书名称和作者名称
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(0, 55));
        northPanel.add(new JLabel("图书名称:"));
        bookNameField = new JTextField(20);
        bookNameField.setFont(new Font(bookNameField.getFont().getName(), Font.BOLD, bookNameField.getFont().getSize()));
        northPanel.add(bookNameField);

        northPanel.add(new JLabel("作者名称:"));
        authorNameField = new JTextField(20);
        authorNameField.setFont(new Font(authorNameField.getFont().getName(), Font.BOLD, authorNameField.getFont().getSize()));
        northPanel.add(authorNameField);
        
        JButton searchButton = new JButton("查找");
        northPanel.add(searchButton);

     // 为查找按钮添加事件监听器
     		searchButton.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {

     				String bookName = bookNameField.getText();
     				String authorName = authorNameField.getText();
     				
     				Connection con = DatabaseUtil.getConnection();
     				AdminServiceImpl adm = new AdminServiceImpl(con);
     				UserServiceImpl user = new UserServiceImpl();

     				// 获取查询到的书	
     				List<Book> bookname = adm.searchBookByName(bookName);
     				List<Book> authorname = user.serachBookByWriter(authorName);

     				//如何将按不同方式进行查询的书进行合并,得到最后的数组     				
     				List<Book> finalbook = bookname.stream()
     			            .filter(book -> authorname.stream().anyMatch(authorBook -> authorBook.getBookId() == book.getBookId()))     			           
     			            .collect(Collectors.toList());
     			
     				//向数组里添加数据
     				for(Book book:finalbook) {
     					int i=0;
     						data[i][0]=book.getBookId();
     						data[i][1]=book.getBookName();
     						data[i][2]=book.getBookWriterName();
     						data[i][3]=book.getBookType();
     						data[i][4]=book.getBookStatement();
     						data[i][5]=book.getBookPosition();
     						data[i][6]=book.getBookNumber();
     						data[i][7]=book.getBookTime();	
     					i++;
     				}
     			
     				table.setFont(new Font("宋体", Font.PLAIN, 14));

     				// 创建滚动面板，包含表格
     				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     			}
     		});

     		add(northPanel, BorderLayout.NORTH);
     		// 添加滚动面板到JPanel
     		add(scrollPane, BorderLayout.CENTER);
        
     		// south部分：添加返回按钮
    		JPanel southPanel = new JPanel();
    		southPanel.setPreferredSize(new Dimension(0, 35)); // 设置大小
    		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    		JButton returnButton = new JButton("返回");
    		southPanel.add(returnButton);

    		// 为返回按钮添加事件监听器
    		returnButton.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				find_stu.this.dispose();
    				student_system frame = new student_system(userid);
    				frame.setVisible(true);
    			}
    		});

    		add(southPanel, BorderLayout.SOUTH);
     		
     		

        // 设置窗口大小和可见性
        setSize(600, 400);
        setLocationRelativeTo(null); // 居中显示
    }

    public static void main(String[] args) {
        // 直接在main方法中创建并显示GUI
        find_stu frame = new find_stu("");
        frame.setVisible(true);
    }
}
