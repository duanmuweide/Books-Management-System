package com.libr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.entity.UserInfo;
import com.libr.service.impl.AdminServiceImpl;
import com.libr.service.impl.UserServiceImpl;
import com.libr.util.DatabaseUtil;

public class manager_system extends JFrame {
	JPanel panel2 = new JPanel();
	int managerid;
	// 创建输入组件
	JTextField idField = new JTextField(20);
	JTextField nameField = new JTextField(20);
	JTextField authorField = new JTextField(20);
	JTextField typeField = new JTextField(20);
	JTextField statusField = new JTextField(20);
	JTextField locationField = new JTextField(20);
	JTextField numberField = new JTextField(20);
	JTextField bookTimeField = new JTextField(20);

	public manager_system(int id) {
		managerid=id;
		this.setSize(800, 600);
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

		// 用一维数组存储菜单
		String menus[] = { "学生设置", "图书管理", "管理员设置", "其他设置" };
		// 用二维数组存储菜单项
		String menuItems[][] = { { "查询借阅记录" }, { "添加图书", "修改图书", "删除图书", "查询图书" }, { "改变管理权限" },
				{ "修改个人信息", "退出登录" }, };

		// 1. 创建菜单栏JMenuBar
		JMenuBar menuBar = new JMenuBar();
		
		try {
			// 加载图片
			Image image = ImageIO.read(new File("src/pictures/back1.jpg"));
			ImageIcon imageIcon = new ImageIcon(image);
			JLabel backgroundLabel = new JLabel(imageIcon);
			backgroundLabel.setLayout(new BorderLayout());
			JLabel welcomeLabel = new JLabel("欢迎来到图书管理系统！请选择上方菜单功能。", JLabel.CENTER);
			welcomeLabel.setFont(new Font("楷体", Font.BOLD, 28));
			backgroundLabel.add(welcomeLabel, BorderLayout.CENTER);

			panel2.add(backgroundLabel);
		} catch (IOException e) {
			e.printStackTrace();
			// 处理图片加载异常
		}

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
				case "操作管理员":
					changeManager();
					break;
				case "修改个人信息":
					changepersonalinformation();
					break;
				case "退出登录":
					manager_system.this.dispose();
					Login frame = new Login();
					frame.setVisible(true);
					break;
				}

			}
		};

		for (int i = 0; i < menus.length; i++) {
			// 创建菜单
			JMenu menu = new JMenu(menus[i]);
			//menuBar.setBackground(Color.DARK_GRAY);
			menu.setFont(new Font("楷体", Font.BOLD, 20));
			JSeparator separator = new JSeparator(JSeparator.VERTICAL);
			separator.setMaximumSize(new Dimension(5, 40)); // 设置分隔符的宽
			menuBar.add(separator);
			// 将菜单添加到菜单栏
			menuBar.add(menu);
			menuBar.add(separator);

			for (int j = 0; j < menuItems[i].length; j++) {
				// 创建菜单项
				JMenuItem menuItem = new JMenuItem(menuItems[i][j]);
				menuItem.setFont(new Font("楷体", Font.BOLD, 16));
				// 将菜单项添加到菜单
				menu.add(menuItem);
				menu.add(new JSeparator()); // 添加分隔符/线
				// 给菜单项注册动作监听器，监听菜单项的点击事件，也就是动作事件
				menuItem.addActionListener(listener);
			}
		}
		// 6. 将菜单栏添加到panel1容器
		panel1.add(menuBar);

	}

	private void showBorrowRecords() {
		// 创建一个新的面板用于显示借阅记录
		JPanel borrowRecordsPanel = new JPanel(new BorderLayout());
		JPanel searchbyidjp = new JPanel();
		JLabel searchbyidjl = new JLabel("学生学号");
		searchbyidjl.setFont(new Font("楷体", Font.BOLD, 20));

		JTextField searchbyidjt = new JTextField(20);
		JButton searchbyidjb = new JButton("查询");
		searchbyidjp.add(searchbyidjl);
		searchbyidjp.add(searchbyidjt);
		searchbyidjp.add(searchbyidjb);

		String[] columnNames = { "学生学号", "图书编号", "借阅时间", "图书状态", "归还时间", "数量", "借阅编号" };
		Object[][] record = null;

		DefaultTableModel model = new DefaultTableModel(record, columnNames);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

		table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 16)); // 表头字体
		table.getTableHeader().setBackground(new Color(85, 130, 200)); // 表头背景色
		table.getTableHeader().setForeground(Color.WHITE); // 表头字体颜色
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
				List<Borrow> listbor = user.serachBorrowRecordById(managerid);
				// 清除现有数据
	            model.setRowCount(0);
				int i=0;
				for (Borrow bor : listbor) {
					record[i][0] = bor.getUseId();
					record[i][1] = bor.getBookId();
					record[i][2] = bor.getBorrowTime();
					record[i][3] = bor.getBookStatement() ? "未被借阅" : "已被借阅";
					record[i][4] = bor.getBorrowReturnTime();
					record[i][5] = bor.getBookNumber();
					record[i][6] = bor.getBorrowId();
					i++;
				}
				
//				 for (Borrow bor : listbor) {
//		                Object[] row = new Object[] {
//		                    bor.getUseId(),
//		                    bor.getBookId(),
//		                    bor.getBorrowTime(),
//		                    bor.getBookStatement() ? "未被借阅" : "已被借阅",
//		                    bor.getBorrowReturnTime(),
//		                    bor.getBookNumber(),
//		                    bor.getBorrowId()
//		                };
//		                model.addRow(row); // 添加行到模型
//		            }
				
			}
		});

	}

	// 自定义的JPanel类，用于绘制背景图片
	class BackgroundImagePanel extends JPanel {
		private Image backgroundImage;

		public BackgroundImagePanel(Image backgroundImage) {
			this.backgroundImage = backgroundImage;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	private void addBook() {
		try {
			Image backgroundImage;
			backgroundImage = ImageIO.read(new File("src/pictures/back1.jpg"));
			BackgroundImagePanel mainPanel = new BackgroundImagePanel(backgroundImage);
			mainPanel.setLayout(new GridBagLayout());

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);

			nameField.setFont(new Font(nameField.getFont().getName(), Font.BOLD, nameField.getFont().getSize()));
			typeField.setFont(new Font(typeField.getFont().getName(), Font.BOLD, typeField.getFont().getSize()));
			authorField.setFont(new Font(authorField.getFont().getName(), Font.BOLD, authorField.getFont().getSize()));
			locationField
					.setFont(new Font(locationField.getFont().getName(), Font.BOLD, locationField.getFont().getSize()));

			// 添加组件到主面板
			int y = 0;
			addLabelAndField("ID:", idField, gbc, y++, mainPanel);
			addLabelAndField("名称:", nameField, gbc, y++, mainPanel);
			addLabelAndField("类型:", typeField, gbc, y++, mainPanel);
			addLabelAndField("作者:", authorField, gbc, y++, mainPanel);
			addLabelAndField("数量:", numberField, gbc, y++, mainPanel);
			addLabelAndField("位置:", locationField, gbc, y++, mainPanel);

			// 添加提交按钮
			JButton submitButton = new JButton("添加书籍");
			submitButton.setFont(new Font("楷书", Font.BOLD, 15));
			gbc.gridx = 0;
			gbc.gridy = y++;
			gbc.gridwidth = 3; // 设置宽度占满整个布局
			gbc.fill = GridBagConstraints.NONE; // 不填充整个区域
			gbc.anchor = GridBagConstraints.CENTER; // 居中对齐
			mainPanel.add(submitButton, gbc);

			// 更新中心区域的内容
			panel2.removeAll();
			panel2.add(mainPanel, BorderLayout.CENTER);
			panel2.revalidate();
			panel2.repaint();

			// 添加按钮监听器
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String idString = idField.getText();
					int id = Integer.parseInt(idString);
					String name = nameField.getText();
					String type = typeField.getText();
					String author = authorField.getText();
					String numberString = numberField.getText();
					int number = Integer.parseInt(numberString);
					boolean bookStatement = false;
					String location = locationField.getText();
					Date bookTime = new Date();
					// 添加到数据库中
					Book newbook = new Book(id, name, type, author, number, bookStatement, location, bookTime);
					Connection con = DatabaseUtil.getConnection();
					AdminServiceImpl adm = new AdminServiceImpl(con);
					adm.addBook(newbook);

				}
			});

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int y, JPanel mainPanel) {
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		mainPanel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = y;
		gbc.gridwidth = 2;
		field.setFont(new Font("宋体", Font.BOLD, 18));
		field.setPreferredSize(new Dimension(200, 35));
		field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		mainPanel.add(field, gbc);

	}

	void changeBook() {
		try {
			Image backgroundImage;
			backgroundImage = ImageIO.read(new File("src/pictures/back1.jpg"));
			BackgroundImagePanel mainPanel = new BackgroundImagePanel(backgroundImage);
			mainPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);

			idField.setFont(new Font(idField.getFont().getName(), Font.BOLD, idField.getFont().getSize()));
			nameField.setFont(new Font(nameField.getFont().getName(), Font.BOLD, nameField.getFont().getSize()));
			authorField.setFont(new Font(authorField.getFont().getName(), Font.BOLD, authorField.getFont().getSize()));
			typeField.setFont(new Font(typeField.getFont().getName(), Font.BOLD, typeField.getFont().getSize()));
			statusField.setFont(new Font(statusField.getFont().getName(), Font.BOLD, statusField.getFont().getSize()));
			locationField
					.setFont(new Font(locationField.getFont().getName(), Font.BOLD, locationField.getFont().getSize()));

			// 添加组件到界面
			int y = 0;
			addLabelAndFieldWithButton("ID:", gbc, y++, idField, nameField, authorField, typeField, statusField,
					locationField, numberField, bookTimeField, mainPanel); // 第一行有按钮
			addLabelAndField("名称:", nameField, gbc, y++, mainPanel);
			addLabelAndField("类型:", typeField, gbc, y++, mainPanel);
			addLabelAndField("作者:", authorField, gbc, y++, mainPanel);
			addLabelAndField("数量:", numberField, gbc, y++, mainPanel);
			addLabelAndField("位置:", locationField, gbc, y++, mainPanel);
			addLabelAndField("借阅状态:", statusField, gbc, y++, mainPanel);
			addLabelAndField("入库时间:", bookTimeField, gbc, y++, mainPanel);

			// 添加修改按钮
			JButton changeButton = new JButton("修改");
			changeButton.setFont(new Font("楷书", Font.BOLD, 15));
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

			// 修改按钮监听事件
			changeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String idString = idField.getText();
					int id = Integer.parseInt(idString);
					String name = nameField.getText();
					String type = typeField.getText();
					String number1 = numberField.getText();
					int number = Integer.parseInt(number1);

					if (!Pattern.matches("[1-9][0-9]*", idString)) {
						JOptionPane.showMessageDialog(manager_system.this, "id不能为空");
					} else if (!Pattern.matches("([\\u4E00-\\u9FA5]|[a-zA-Z]|[0-9])+", name)) {
						JOptionPane.showMessageDialog(manager_system.this, "名称只能是汉字字母数字，且不能为空");
					} else if (!Pattern.matches("[\u4E00-\\u9FA5]+", type)) {
						JOptionPane.showMessageDialog(manager_system.this, "类型不能为空");
					} else if (!Pattern.matches("[1-9]+", number1)) {
						JOptionPane.showMessageDialog(manager_system.this, "数量不能为空");
					} else {
						// 执行修改操作
						Connection con = DatabaseUtil.getConnection();
						AdminServiceImpl adm = new AdminServiceImpl(con);
						adm.modifyBook(id, name, type, number);
					}

				}
			});

		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void addLabelAndFieldWithButton(String labelText, GridBagConstraints gbc, int y, JTextField idField,
			JTextField nameField, JTextField authorField, JTextField typeField, JTextField statusField,
			JTextField locationField, JTextField numberField, JTextField bookTimeField, JPanel mainPanel) {
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		mainPanel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		idField.setFont(new Font("宋体", Font.BOLD, 18));
		idField.setPreferredSize(new Dimension(200, 35));
		idField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		mainPanel.add(idField, gbc);

		JButton editButton = new JButton("查看");
		editButton.setFont(new Font("楷书", Font.BOLD, 15));
		gbc.gridx = 2;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		mainPanel.add(editButton, gbc);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取输入的id
				String idString = idField.getText();
				int id = Integer.parseInt(idString);

				// 在数据库中查找并显示书的相关信息
				Connection con = DatabaseUtil.getConnection();
				AdminServiceImpl adm = new AdminServiceImpl(con);
				Book book = adm.searchBookById(id);
				// 显示信息
				nameField.setText(book.getBookName());
				typeField.setText(book.getBookType());
				authorField.setText(book.getBookWriterName());
				numberField.setText(String.valueOf(book.getBookNumber()));
				locationField.setText(book.getBookPosition());
				if (book.getBookStatement() == false) {
					statusField.setText("已被借阅");
				} else {
					statusField.setText("未被借阅");
				}
				bookTimeField.setText(book.getBookTime().toString());

			}
		});

	}

	void deleteBook() {
		try {
			Image backgroundImage;
			backgroundImage = ImageIO.read(new File("src/pictures/back1.jpg"));
			BackgroundImagePanel mainPanel = new BackgroundImagePanel(backgroundImage);
			mainPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);

			idField.setFont(new Font(idField.getFont().getName(), Font.BOLD, idField.getFont().getSize()));
			nameField.setFont(new Font(nameField.getFont().getName(), Font.BOLD, nameField.getFont().getSize()));
			authorField.setFont(new Font(authorField.getFont().getName(), Font.BOLD, authorField.getFont().getSize()));
			typeField.setFont(new Font(typeField.getFont().getName(), Font.BOLD, typeField.getFont().getSize()));
			statusField.setFont(new Font(statusField.getFont().getName(), Font.BOLD, statusField.getFont().getSize()));
			locationField
					.setFont(new Font(locationField.getFont().getName(), Font.BOLD, locationField.getFont().getSize()));

			// 添加组件到界面
			int y = 0;
			addLabelAndFieldWithButton("ID:", gbc, y++, idField, nameField, authorField, typeField, statusField,
					locationField, numberField, bookTimeField, mainPanel); // 第一行有按钮
			addLabelAndField("名称:", nameField, gbc, y++, mainPanel);
			addLabelAndField("类型:", typeField, gbc, y++, mainPanel);
			addLabelAndField("作者:", authorField, gbc, y++, mainPanel);
			addLabelAndField("数量:", numberField, gbc, y++, mainPanel);
			addLabelAndField("位置:", locationField, gbc, y++, mainPanel);
			addLabelAndField("借阅状态:", statusField, gbc, y++, mainPanel);
			addLabelAndField("入库时间:", bookTimeField, gbc, y++, mainPanel);

			// 添加删除按钮
			JButton deleteButton = new JButton("删除");
			deleteButton.setFont(new Font("楷书", Font.BOLD, 15));
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

			// 删除按钮监听事件
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 获取输入的id
					String idString = idField.getText();
					int id = Integer.parseInt(idString);

					// 在数据库中查找并显示书的相关信息
					Connection con = DatabaseUtil.getConnection();
					AdminServiceImpl adm = new AdminServiceImpl(con);
					Book book = adm.searchBookById(id);
					// 显示信息
					nameField.setText(book.getBookName());
					typeField.setText(book.getBookType());
					authorField.setText(book.getBookWriterName());
					numberField.setText(String.valueOf(book.getBookNumber()));
					locationField.setText(book.getBookPosition());
					if (book.getBookStatement() == false) {
						statusField.setText("已被借阅");
					} else {
						statusField.setText("未被借阅");
					}
					bookTimeField.setText(book.getBookTime().toString());

				}
			});

		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void findBook() {

		JTextField bookNameField;
		JTextField authorNameField;
		JTextField IDField;
		JTable table;
		DefaultTableModel model;

		// 创建JPanel
		JPanel panel = new JPanel(new BorderLayout());

		// 创建输入字段
		bookNameField = new JTextField(15);
		authorNameField = new JTextField(15);
		IDField = new JTextField(15);

		// 创建按钮
		JButton searchButton = new JButton("查找");

		// 创建表格
		String[] columnNames = { "编号", "书名", "作者名", "类型", "借阅状态", "位置", "数量", "入库时间" };
		Object[][] data = new Object[0][0]; // 初始化为空数组
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 16)); // 表头字体
		table.getTableHeader().setBackground(new Color(85, 130, 200)); // 表头背景色
		table.getTableHeader().setForeground(Color.WHITE); // 表头字体颜色
		JScrollPane scrollPane = new JScrollPane(table);

		// north部分：输入图书名称和作者名称
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 55));

		JLabel bookLabel = new JLabel("图书名称:");
		bookLabel.setFont(new Font("楷体", Font.BOLD, 20));
		northPanel.add(bookLabel);
		northPanel.add(bookNameField);

		JLabel writerLabel = new JLabel("作者名称:");
		writerLabel.setFont(new Font("楷体", Font.BOLD, 20));
		northPanel.add(writerLabel);
		northPanel.add(authorNameField);

		JLabel numberLabel = new JLabel("图书编号:");
		numberLabel.setFont(new Font("楷体", Font.BOLD, 20));
		northPanel.add(numberLabel);
		northPanel.add(IDField);

		northPanel.add(searchButton);

		// 为查找按钮添加事件监听器
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookName = bookNameField.getText();
				String authorName = authorNameField.getText();
				String idString = IDField.getText();
				int id;
				if (idString.isEmpty()) {
					id = -1;
				} else {
					id = Integer.parseInt(idString);
				}

				Connection con = DatabaseUtil.getConnection();
				AdminServiceImpl adm = new AdminServiceImpl(con);
				UserServiceImpl user = new UserServiceImpl();

				// 获取查询到的书
				List<Book> bookid = new ArrayList<>();
				Book bookID = adm.searchBookById(id);
				bookid.add(bookID);

				List<Book> bookname = adm.searchBookByName(bookName);
				List<Book> authorname = user.serachBookByWriter(authorName);
				
				// 如何将按不同方式进行查询的书进行合并,得到最后的数组
				List<Book> finalbook;
				if (id==-1) {
					if(bookName.isEmpty()) {
						finalbook=authorname;
					}else if(authorName.isEmpty()) {
						finalbook=bookname;
					}else {
					finalbook = bookname.stream()
							.filter(book -> authorname.stream()
									.anyMatch(authorBook -> authorBook.getBookId() == book.getBookId()))							
							.collect(Collectors.toList());
					}
				} else {
					finalbook = bookid;
				}
				// 向数组里添加数据
				for (Book book : finalbook) {
					int i = 0;
					data[i][0] = book.getBookId();
					data[i][1] = book.getBookName();
					data[i][2] = book.getBookWriterName();
					data[i][3] = book.getBookType();
					data[i][4] = book.getBookStatement();
					data[i][5] = book.getBookPosition();
					data[i][6] = book.getBookNumber();
					data[i][7] = book.getBookTime();
					i++;
				}
				
//				 for (Borrow bor : listbor) {
//                Object[] row = new Object[] {
//                    bor.getUseId(),
//                    bor.getBookId(),
//                    bor.getBorrowTime(),
//                    bor.getBookStatement() ? "未被借阅" : "已被借阅",
//                    bor.getBorrowReturnTime(),
//                    bor.getBookNumber(),
//                    bor.getBorrowId()
//                };
//                model.addRow(row); // 添加行到模型
//            }
				

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

	private void changepersonalinformation() {

		try {
			Image backgroundImage;
			backgroundImage = ImageIO.read(new File("src/pictures/back1.jpg"));
			BackgroundImagePanel mainPanel = new BackgroundImagePanel(backgroundImage);
			mainPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);
	
			UserInfo before = new UserInfo();
			UserServiceImpl user = new UserServiceImpl();
			Connection con = DatabaseUtil.getConnection();
			AdminServiceImpl adm = new AdminServiceImpl(con);
			before=adm.getUserInfoById(managerid);
			
			// 创建输入组件
			JTextField nameField = new JTextField(before.getUserName(), 20);
			JTextField passwordField = new JTextField(before.getUserPassword(), 20);			
			JTextField userQuestion=new JTextField(before.getUserQuestion(),20);
			JTextField userAnswer=new JTextField(before.getUserAnswer(),20);
			JTextField phonenumberField = new JTextField(before.getUserContact(), 20);
			JTextField realnameField = new JTextField(before.getUserRealname(), 20);
			realnameField
					.setFont(new Font(realnameField.getFont().getName(), Font.BOLD, realnameField.getFont().getSize()));
			JTextField addressField = new JTextField(before.getUserAddress(), 20);
			addressField
					.setFont(new Font(addressField.getFont().getName(), Font.BOLD, addressField.getFont().getSize()));
			JTextField emailField = new JTextField(before.getUserEmail(), 20);
			JTextField majorField = new JTextField(before.getUserMajor(), 20);
			majorField.setFont(new Font(majorField.getFont().getName(), Font.BOLD, majorField.getFont().getSize()));

			// 添加组件到主面板
			int y = 0;
			addLabelAndFieldWithButton("名称:", nameField, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("密码:", passwordField, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("密保:", userQuestion, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("答案:", userAnswer, gbc, y++, mainPanel);			
			addLabelAndFieldWithButton("电话:", phonenumberField, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("真名:", realnameField, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("地址:", addressField, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("邮箱:", emailField, gbc, y++, mainPanel);
			addLabelAndFieldWithButton("专业:", majorField, gbc, y++, mainPanel);

			// 添加修改按钮
			JButton changeButton = new JButton("修改");
			changeButton.setFont(new Font("楷书", Font.BOLD, 15));
			gbc.gridx = 2;
			gbc.gridy = y++;
			gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
			mainPanel.add(changeButton, gbc); // 添加到主面板

			changeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UserInfo after = new UserInfo(managerid,nameField.getText(),
					passwordField.getText(),userQuestion.getText(),userAnswer.getText(),
					true,before.getUserGender(),phonenumberField.getText(),
					realnameField.getText(),addressField.getText(),emailField.getText(),
					majorField.getText()) ;
					user.changeUserInfo(after);
				}
			});

			// 更新中心区域的内容
			panel2.removeAll();
			panel2.add(mainPanel, BorderLayout.CENTER);
			panel2.revalidate(); // 重新验证布局
			panel2.repaint(); // 重绘组件

		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void addLabelAndFieldWithButton(String labelText, JTextField field, GridBagConstraints gbc, int y,
			JPanel mainPanel) {
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		mainPanel.add(label, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = y;
		gbc.gridwidth = 2;
		field.setFont(new Font("宋体", Font.BOLD, 18));
		field.setPreferredSize(new Dimension(200, 35));
		field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
		BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		mainPanel.add(field, gbc);
	}

	private void changeManager() {

		try {
			Image backgroundImage;
			backgroundImage = ImageIO.read(new File("src/pictures/back1.jpg"));
			BackgroundImagePanel mainPanel = new BackgroundImagePanel(backgroundImage);
			mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JTextField idField = new JTextField(20);
		JTextField nameField = new JTextField(20);
		JTextField genderField = new JTextField(20);
		JTextField contactField = new JTextField(20);
		JTextField realnameField = new JTextField(20);
		JTextField emailField = new JTextField(20);
		JTextField majorField = new JTextField(20);

		nameField.setFont(new Font(nameField.getFont().getName(), Font.BOLD, nameField.getFont().getSize()));
		genderField.setFont(new Font(genderField.getFont().getName(), Font.BOLD, genderField.getFont().getSize()));
		realnameField
				.setFont(new Font(realnameField.getFont().getName(), Font.BOLD, realnameField.getFont().getSize()));
		majorField.setFont(new Font(majorField.getFont().getName(), Font.BOLD, majorField.getFont().getSize()));

		// 添加组件到界面
		int y = 0;
		addLabelAndFieldWithButtonmanager("ID:", gbc, y++, idField, nameField, genderField, contactField, realnameField,
				emailField, majorField, mainPanel); // 第一行有按钮
		addLabelAndField("名称:", nameField, gbc, y++, mainPanel);
		addLabelAndField("性别:", genderField, gbc, y++, mainPanel);
		addLabelAndField("电话:", contactField, gbc, y++, mainPanel);
		addLabelAndField("真名:", realnameField, gbc, y++, mainPanel);
		addLabelAndField("邮箱:", emailField, gbc, y++, mainPanel);
		addLabelAndField("专业:", majorField, gbc, y++, mainPanel);

		// 管理员按钮
		JButton addManagerButton = new JButton("添加为管理员");
		addManagerButton.setFont(new Font("楷书", Font.BOLD, 15));
		gbc.gridx = 1;
		gbc.gridy = y++;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
		mainPanel.add(addManagerButton, gbc);

		JButton deleteManagerButton = new JButton("刪除其管理员");
		deleteManagerButton.setFont(new Font("楷书", Font.BOLD, 15));
		gbc.gridx = 1;
		gbc.gridy = y++;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
		mainPanel.add(deleteManagerButton, gbc);
		
		// 更新中心区域的内容
		panel2.removeAll();
		panel2.add(mainPanel, BorderLayout.CENTER);
		panel2.revalidate(); // 重新验证布局
		panel2.repaint(); // 重绘组件

		// 修改按钮监听事件
		addManagerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取输入的id
				String idString = idField.getText();
				int id = Integer.parseInt(idString);
				UserInfo before = new UserInfo();
				UserServiceImpl user = new UserServiceImpl();
				Connection con = DatabaseUtil.getConnection();
				AdminServiceImpl adm = new AdminServiceImpl(con);
				before=adm.getUserInfoById(id);
				adm.addAdmin(before);
			}
		});
		
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void addLabelAndFieldWithButtonmanager(String labelText, GridBagConstraints gbc, int y, JTextField idField,
			JTextField nameField, JTextField genderField, JTextField contactField, JTextField realnameField,
			JTextField emailField, JTextField majorField, JPanel mainPanel) {
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		mainPanel.add(label, gbc);

		gbc.gridx = 1;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		idField.setFont(new Font("宋体", Font.BOLD, 18));
		idField.setPreferredSize(new Dimension(200, 35));
		idField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		mainPanel.add(idField, gbc);

		JButton editButton = new JButton("查看");
		editButton.setFont(new Font("楷书", Font.BOLD, 15));
		gbc.gridx = 2;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		mainPanel.add(editButton, gbc);

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取输入的id
				String idString = idField.getText();
				int id = Integer.parseInt(idString);

				// 显示学生信息
				Connection con = DatabaseUtil.getConnection();
				AdminServiceImpl adm = new AdminServiceImpl(con);
			
				 UserInfo man=adm.getUserInfoById(id);
				 	nameField.setText(man.getUserName());
	    	    	genderField.setText(man.getUserGender());
	    	    	contactField.setText(man.getUserContact());
	    	    	realnameField.setText(man.getUserRealname());
	    	    	emailField.setText(man.getUserEmail());
	    	    	majorField.setText(man.getUserMajor());

			}
		});

	}

	
	public static void main(String[] args) {		
		manager_system frame = new manager_system(1);
		frame.setVisible(true);
	}

}
