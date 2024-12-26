package com.libr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.service.impl.AdminServiceImpl;
import com.libr.service.impl.UserServiceImpl;
import com.libr.util.DatabaseUtil;


public class manager_system extends JFrame {
	JPanel panel2 = new JPanel();
	public manager_system() {
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("管理员页面");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		this.add(container);

		JPanel panel1 = new JPanel();
		
		panel1.setPreferredSize(new Dimension(0, 40));
		panel1.setBackground(Color.orange);

		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());

		container.add(panel1, BorderLayout.NORTH);
		container.add(panel2, BorderLayout.CENTER);

//		ImageIcon backgroundImage = new ImageIcon("src\\pictures\\back1.jpg"); // 替换为你的图片路径
//        Image image = backgroundImage.getImage(); // 转换为Image
//        Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // 缩放图片
//        backgroundImage = new ImageIcon(newimg); // 重新设置图片
//
//        // 创建JLabel并设置背景图片
//        JLabel backgroundLabel = new JLabel(backgroundImage);
//        backgroundLabel.setLayout(new BorderLayout()); // 设置布局管理器
//        panel2.add(backgroundLabel);
        
		// 用一维数组存储菜单
		String menus[] = { "学生设置", "图书管理", "管理员设置" ,"个人设置"};
		//用二维数组存储菜单项
		String menuItems[][] = { 
				{ "查询借阅记录"}, 
				{ "添加图书", "修改图书", "删除图书","查询图书" }, 
				{ "添加管理员", "删除管理员","查询管理员"},
				{ "修改个人信息"},
		};

		// 1. 创建菜单栏JMenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("宋体", Font.BOLD, 16));
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 默认情况下，菜单项的ActionCommand就是菜单的显示文本
				String cmd = e.getActionCommand();

				switch (cmd) {
				case "查询借阅记录":
					showBorrowRecords();
					break;
				case "添加图书":
					addBook();
					break;
				case "修改图书":
					changeBook();
					break;
				case "删除图书":
					deleteBook();
					break;
				case "查询图书":
					findBook();
					break;
				case "添加管理员":
					//addManager();
					break;
				case "删除管理员":
					//deleteManager();
					break;
				case "查询管理员":
					//findManager();
					break;
				case "修改个人信息":
					//changepersonalinformation();
					break;
				}

				
			}
		};

		for (int i = 0; i < menus.length; i++) {
			// 创建菜单
			JMenu menu = new JMenu(menus[i]);
			menu.setFont(new Font("宋体", Font.BOLD, 16));
			JSeparator separator = new JSeparator(JSeparator.VERTICAL);
			separator.setMaximumSize(new Dimension(5, 40)); // 设置分隔符的宽
			menuBar.add(separator);
			// 将菜单添加到菜单栏
			menuBar.add(menu);
			menuBar.add(separator);
			
			for (int j = 0; j < menuItems[i].length; j++) {
				// 创建菜单项
				JMenuItem menuItem = new JMenuItem(menuItems[i][j]);
				menuItem.setFont(new Font("宋体", Font.BOLD, 16));
				// 将菜单项添加到菜单
				menu.add(menuItem);
				menu.add(new JSeparator()); // 添加分隔符/线
				// 给菜单项注册动作监听器，监听菜单项的点击事件，也就是动作事件
				menuItem.addActionListener(listener);
			}
		}
		// 6. 将菜单栏添加到panel1容器
		panel1.add(menuBar);
		panel2.add(new JScrollPane(new JTextArea()));
	}
	
	private void showBorrowRecords() {
        // 创建一个新的面板用于显示借阅记录
        JPanel borrowRecordsPanel = new JPanel(new BorderLayout());
        JPanel searchbyidjp=new JPanel();
        JLabel searchbyidjl=new JLabel("学生学号");
        JTextField searchbyidjt=new JTextField(20);
        JButton searchbyidjb=new JButton("查询");
        searchbyidjp.add(searchbyidjl);
        searchbyidjp.add(searchbyidjt);
        searchbyidjp.add(searchbyidjb);
        
        String[] columnNames = { "学生学号", "图书编号", "借阅时间", "图书状态", "归还时间", "数量", "借阅编号" };
        Object[][] record = null;
        
        DefaultTableModel model = new DefaultTableModel(record, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);     
        
            table.setFont(new Font("宋体", Font.PLAIN, 14));
            
            // 创建滚动面板，包含表格
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            borrowRecordsPanel.add(searchbyidjp, BorderLayout.NORTH);
            borrowRecordsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 更新中心区域的内容
        panel2.removeAll();
        panel2.add(borrowRecordsPanel, BorderLayout.CENTER);
        panel2.revalidate(); // 重新验证布局
        panel2.repaint(); // 重绘组件
        
        searchbyidjb.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
	            UserServiceImpl user = new UserServiceImpl();
				 int userid=Integer.parseInt(searchbyidjt.getText());
	            List<Borrow> listbor = user.serachBorrowRecordById(userid);
	            for (Borrow bor : listbor) {
	                int i = 0;
	                record[i][0] = bor.getUseId();
	                record[i][1] = bor.getBookId();
	                record[i][2] = bor.getBorrowTime();
	                record[i][3] = bor.getBookStatement() ? "未被借阅" : "已被借阅";
	                record[i][4] = bor.getBorrowReturnTime();
	                record[i][5] = bor.getBookNumber();
	                record[i][6] = bor.getBorrowId();
	                i++;
	            } 
			}
		});
        
    }
	
	private void addBook() {

		    	JPanel mainPanel; // 主面板
		        mainPanel = new JPanel(new GridBagLayout()); // 创建主面板
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
		        JTextField numberField = new JTextField(20);

		        // 添加组件到主面板
		        int y = 0;
		        addLabelAndField("ID:", idField, gbc, y++,mainPanel);
		        addLabelAndField("名称:", nameField, gbc, y++,mainPanel);
		        addLabelAndField("类型:", typeField, gbc, y++,mainPanel);
		        addLabelAndField("作者:", authorField, gbc, y++,mainPanel);
		        addLabelAndField("数量:", numberField, gbc, y++,mainPanel);
		        addLabelAndField("位置:", locationField, gbc, y++,mainPanel);

		        // 添加提交按钮
		        JButton submitButton = new JButton("添加书籍");
		        gbc.gridx = 0;
		        gbc.gridy = y++;
		        gbc.gridwidth = 3; // 设置宽度占满整个布局
		        gbc.fill = GridBagConstraints.NONE; // 不填充整个区域
		        gbc.anchor = GridBagConstraints.CENTER; // 居中对齐
		        mainPanel.add(submitButton, gbc);
		        
		        // 更新中心区域的内容
		        panel2.removeAll();
		        panel2.add(mainPanel, BorderLayout.CENTER);
		        panel2.revalidate(); // 重新验证布局
		        panel2.repaint(); // 重绘组件
		        
		        // 添加按钮监听器
		        submitButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	submitButton.addActionListener(new ActionListener() {
		       	    	 public void actionPerformed(ActionEvent e) {	 
		       	    	        String idString=idField.getText();
		       	    	        int id= Integer.parseInt(idString);
		       	    	        String name=nameField.getText();
		       	    	        String type=typeField.getText();
		       	    	        String author=authorField.getText();
		       	    	        String numberString=numberField.getText();
		       	    	        int number= Integer.parseInt(numberString);
		       	    	        boolean bookStatement=false;
		       	    	        String location=locationField.getText();
		       	    	        Date bookTime = new Date();
		       	    	        //添加到数据库中 
		       	    	        Book newbook=new Book(id,name,type,author,number,bookStatement,location,bookTime);		    	       
		       	    	        Connection con=DatabaseUtil.getConnection();
		       	    	        AdminServiceImpl adm=new AdminServiceImpl(con);
		       	    	        adm.addBook(newbook);
		       	    	 }
		       	     });
		            }
		        });

	}
	private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int y,JPanel mainPanel) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        mainPanel.add(field, gbc);
    }
	
	void changeBook() {
			JTextField idField = new JTextField("请输入ID进行查询",20);
			JTextField nameField = new JTextField(20);
		    JTextField authorField = new JTextField(20);
		    JTextField typeField = new JTextField(20);
		    JTextField statusField = new JTextField(20);
		    JTextField locationField = new JTextField(20);
		    JTextField numberField = new JTextField(20);
		    JTextField bookTimeField=new JTextField(20);
		    
		    JPanel mainPanel; // 主面板
	        mainPanel = new JPanel(new GridBagLayout()); // 创建主面板
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
		        addLabelAndFieldWithButton("ID:",gbc, y++,
		        		idField,	nameField,	authorField,typeField,statusField,
		        		locationField,numberField,bookTimeField,mainPanel); // 第一行有按钮
		        addLabelAndField("名称:", nameField, gbc, y++,mainPanel);
		        addLabelAndField("类型:", typeField, gbc, y++,mainPanel);
		        addLabelAndField("作者:", authorField, gbc, y++,mainPanel);
		        addLabelAndField("数量:", numberField, gbc, y++,mainPanel);
		        addLabelAndField("位置:", locationField, gbc, y++,mainPanel);
		        addLabelAndField("借阅状态:", statusField, gbc, y++,mainPanel);
		        addLabelAndField("入库时间:", bookTimeField, gbc, y++,mainPanel);
		        
		        
		        // 添加修改按钮
		        JButton changeButton = new JButton("修改");
		        gbc.gridx = 1;
		        gbc.gridy = y++;
		        gbc.gridwidth = 2;
		        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
		        mainPanel.add(changeButton, gbc);
		        
		        // 更新中心区域的内容
		        panel2.removeAll();
		        panel2.add(mainPanel, BorderLayout.CENTER);
		        panel2.revalidate(); // 重新验证布局
		        panel2.repaint(); // 重绘组件
		        
		        //修改按钮监听事件
		        changeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String idString=idField.getText();
						 int id= Integer.parseInt(idString);
			    	     String name=nameField.getText();
			    	     String type=typeField.getText();
			    	     String number1=numberField.getText();
			    	     int number= Integer.parseInt(number1);
			    	     
			    	     if(!Pattern.matches("[1-9][0-9]*",idString)) {
								JOptionPane.showMessageDialog(manager_system.this, "id不能为空");
							}
							else if(!Pattern.matches("([\\u4E00-\\u9FA5]|[a-zA-Z]|[0-9])+",name)) {
								JOptionPane.showMessageDialog(manager_system.this, "名称只能是汉字字母数字，且不能为空");
							}else if(!Pattern.matches("[\u4E00-\\u9FA5]+",type)) {
								JOptionPane.showMessageDialog(manager_system.this, "类型不能为空");
							}else if(!Pattern.matches("[1-9]+",number1)) {
								JOptionPane.showMessageDialog(manager_system.this, "数量不能为空");
							}else{
								//执行修改操作
				    	        Connection con=DatabaseUtil.getConnection();
				    	        AdminServiceImpl adm=new AdminServiceImpl(con);
				    	        adm.modifyBook(id,name,type,number);
							}

					}
				});  
		        
		    }
	private void addLabelAndFieldWithButton(String labelText,GridBagConstraints gbc, int y
			,JTextField idField,JTextField nameField,JTextField authorField,JTextField typeField,
			JTextField statusField,JTextField locationField,JTextField numberField,JTextField bookTimeField,JPanel mainPanel) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        mainPanel.add(idField, gbc);

        JButton editButton = new JButton("查看");
        gbc.gridx = 2;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        mainPanel.add(editButton, gbc);
        
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
	
	void deleteBook() {
		JTextField idField = new JTextField("请输入ID进行查询",20);
		JTextField nameField = new JTextField(20);
	    JTextField authorField = new JTextField(20);
	    JTextField typeField = new JTextField(20);
	    JTextField statusField = new JTextField(20);
	    JTextField locationField = new JTextField(20);
	    JTextField numberField = new JTextField(20);
	    JTextField bookTimeField=new JTextField(20);
	    
	    JPanel mainPanel; // 主面板
        mainPanel = new JPanel(new GridBagLayout()); // 创建主面板
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
	        addLabelAndFieldWithButton("ID:",gbc, y++,
	        		idField,	nameField,	authorField,typeField,statusField,
	        		locationField,numberField,bookTimeField,mainPanel); // 第一行有按钮
	        addLabelAndField("名称:", nameField, gbc, y++,mainPanel);
	        addLabelAndField("类型:", typeField, gbc, y++,mainPanel);
	        addLabelAndField("作者:", authorField, gbc, y++,mainPanel);
	        addLabelAndField("数量:", numberField, gbc, y++,mainPanel);
	        addLabelAndField("位置:", locationField, gbc, y++,mainPanel);
	        addLabelAndField("借阅状态:", statusField, gbc, y++,mainPanel);
	        addLabelAndField("入库时间:", bookTimeField, gbc, y++,mainPanel);
	        
	        
	        // 添加删除按钮
	        JButton deleteButton = new JButton("删除");
	        gbc.gridx = 1;
	        gbc.gridy = y++;
	        gbc.gridwidth = 2;
	        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
	        mainPanel.add(deleteButton, gbc);
	        
	        // 更新中心区域的内容
	        panel2.removeAll();
	        panel2.add(mainPanel, BorderLayout.CENTER);
	        panel2.revalidate(); // 重新验证布局
	        panel2.repaint(); // 重绘组件
	        
	        //修改按钮监听事件
	        deleteButton.addActionListener(new ActionListener() {
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
	
	private void findBook() {
		
		    JTextField bookNameField;
		    JTextField authorNameField;
		    JTextField IDField;
		    JFormattedTextField formattedTextField;
		    JTable table;
		    DefaultTableModel model;

		        // 创建JPanel
		        JPanel panel = new JPanel(new BorderLayout());

		        // 创建输入字段
		        bookNameField = new JTextField(15);
		        authorNameField = new JTextField(15);
		        IDField = new JTextField(15);
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        formattedTextField = new JFormattedTextField(dateFormat);
		        formattedTextField.setColumns(15);

		        // 创建按钮
		        JButton searchButton = new JButton("查找");

		        // 创建表格
		        String[] columnNames = { "编号", "书名", "作者名", "类型", "借阅状态", "位置", "数量", "入库时间" };
		        Object[][] data = new Object[0][0]; // 初始化为空数组
		        model = new DefaultTableModel(data, columnNames);
		        table = new JTable(model);
		        JScrollPane scrollPane = new JScrollPane(table);

		        // north部分：输入图书名称和作者名称
		        JPanel northPanel = new JPanel();
		        northPanel.setPreferredSize(new Dimension(0, 55));
		        northPanel.add(new JLabel("图书名称:"));
		        northPanel.add(bookNameField);
		        northPanel.add(new JLabel("作者名称:"));
		        northPanel.add(authorNameField);
		        northPanel.add(new JLabel("图书ID:"));
		        northPanel.add(IDField);
		        northPanel.add(new JLabel("入库时间:"));
		        northPanel.add(formattedTextField);
		        northPanel.add(searchButton);
	
		        // 为查找按钮添加事件监听器
		        searchButton.addActionListener(new ActionListener() {		          
		            public void actionPerformed(ActionEvent e) {

						String bookName = bookNameField.getText();
						String authorName = authorNameField.getText();
						String idString = IDField.getText();
						int id;
						if(idString.isEmpty()) {
							id=-1;
						}else {
							id = Integer.parseInt(idString);
						}
						
						Date date = (Date) formattedTextField.getValue();
										
						Connection con = DatabaseUtil.getConnection();
						AdminServiceImpl adm = new AdminServiceImpl(con);
						UserServiceImpl user = new UserServiceImpl();

						// 获取查询到的书
						List<Book> bookid = new ArrayList<>();
						Book bookID=adm.searchBookById(id);
						bookid.add(bookID);
						
						List<Book> bookname = adm.searchBookByName(bookName);
						List<Book> authorname = user.serachBookByWriter(authorName);
						List<Book> bookdate = adm.searchBookByDate(date);
						
						//如何将按不同方式进行查询的书进行合并,得到最后的数组
						
						List<Book> finalbook;
						if(id==-1) {
							finalbook = bookname.stream()
						            .filter(book -> authorname.stream().anyMatch(authorBook -> authorBook.getBookId() == book.getBookId()))
						            .filter(book -> bookdate.stream().anyMatch(dateBook -> dateBook.getBookId() == book.getBookId())) 
						            .collect(Collectors.toList());
						}else {
							finalbook = bookname.stream()
					            .filter(book -> authorname.stream().anyMatch(authorBook -> authorBook.getBookId() == book.getBookId()))
					            .filter(book -> bookdate.stream().anyMatch(dateBook -> dateBook.getBookId() == book.getBookId()))
					            .filter(book -> bookid.stream().anyMatch(idBook -> idBook.getBookId() == book.getBookId()))
					            .collect(Collectors.toList());
						
						}
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
		      
		        // 将组件添加到JPanel
		        panel.add(northPanel, BorderLayout.NORTH);
		        panel.add(scrollPane, BorderLayout.CENTER);
		       
		        // 更新中心区域的内容
		        panel2.removeAll();
		        panel2.add(panel, BorderLayout.CENTER);
		        panel2.revalidate(); // 重新验证布局
		        panel2.repaint(); // 重绘组件

	}
	
	
	public static void main(String[] args) {
		manager_system frame = new manager_system();
		frame.setVisible(true);
	}

}
