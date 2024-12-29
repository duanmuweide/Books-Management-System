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
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.libr.entity.Book;
import com.libr.entity.Borrow;
import com.libr.entity.UserInfo;
import com.libr.gui.manager_system.BackgroundImagePanel;
import com.libr.service.impl.AdminServiceImpl;
import com.libr.service.impl.UserServiceImpl;
import com.libr.util.DatabaseUtil;

public class student_system extends JFrame {
	private int id;
	private JPanel mainPanel; // 主面板，用于切换显示的内容

	public student_system(int userid) {
		id = userid;

		// 设置窗口标题和大小
		setTitle("学生系统");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 窗口居中
		setLayout(new BorderLayout());

		// 顶部功能菜单（水平排列按钮）
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

		JButton btnSearchBook = new JButton("查找图书");
		btnSearchBook.setBackground(new Color(70, 130, 180));
		btnSearchBook.setForeground(Color.WHITE);
		btnSearchBook.setFont(new Font("楷体", Font.BOLD, 18));

		JButton btnBorrowRecord = new JButton("查看借书记录");
		btnBorrowRecord.setBackground(new Color(70, 130, 180));
		btnBorrowRecord.setForeground(Color.WHITE);
		btnBorrowRecord.setFont(new Font("楷体", Font.BOLD, 18));

		JButton btnChangeInfo = new JButton("修改个人信息");
		btnChangeInfo.setBackground(new Color(70, 130, 180));
		btnChangeInfo.setForeground(Color.WHITE);
		btnChangeInfo.setFont(new Font("楷体", Font.BOLD, 18));

		JButton btnLogout = new JButton("退出登录");
		btnLogout.setBackground(new Color(70, 130, 180));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("楷体", Font.BOLD, 18));

		// 将按钮添加到顶部菜单面板
		menuPanel.add(btnSearchBook);
		menuPanel.add(btnBorrowRecord);
		menuPanel.add(btnChangeInfo);
		menuPanel.add(btnLogout);

		add(menuPanel, BorderLayout.NORTH);

		// 主面板（包含背景图片）
		mainPanel = new BackgroundPanel("src/pictures/back1.jpg");
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);

		// 默认显示欢迎界面
		showWelcomePanel();

		// 按钮点击事件监听器
		btnSearchBook.addActionListener(e -> showSearchBookPanel());
		btnBorrowRecord.addActionListener(e -> showBorrowRecordPanel(id));
		btnChangeInfo.addActionListener(e -> showChangeInfoPanel());
		btnLogout.addActionListener(e -> logout());
	}

	// 显示欢迎界面
	private void showWelcomePanel() {
		mainPanel.removeAll();
		JLabel welcomeLabel = new JLabel("欢迎来到学生系统！请选择上方菜单功能。", JLabel.CENTER);
		welcomeLabel.setFont(new Font("楷体", Font.BOLD, 28));
		mainPanel.add(welcomeLabel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	// 显示搜索页面
	private void showSearchBookPanel() {
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
		model = new DefaultTableModel(columnNames, 0); // 初始化为空模型
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
				// 清除现有数据
				model.setRowCount(0);

				String bookName = bookNameField.getText();
				String authorName = authorNameField.getText();
				String idString = IDField.getText();
				int id = -1;
				if (!idString.isEmpty()) {
					try {
						id = Integer.parseInt(idString);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(panel, "请输入有效的图书编号！", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				Connection con = null;
				try {
					con = DatabaseUtil.getConnection();
					AdminServiceImpl adm = new AdminServiceImpl(con);
					UserServiceImpl user = new UserServiceImpl();

					List<Book> finalbook = new ArrayList<>();
					Set<Integer> addedBookIds = new HashSet<>(); // 用于存储已添加书籍的ID

					if (id != -1) {
						Book bookID = adm.searchBookById(id);
						if (bookID != null && !addedBookIds.contains(bookID.getBookId())) {
							finalbook.add(bookID);
							addedBookIds.add(bookID.getBookId()); // 添加书籍ID到集合中
						}
					} else {
						if (!bookName.isEmpty()) {
							List<Book> booksByName = adm.searchBookByName(bookName);
							for (Book book : booksByName) {
								if (!addedBookIds.contains(book.getBookId())) {
									finalbook.add(book);
									addedBookIds.add(book.getBookId()); // 添加书籍ID到集合中
								}
							}
						}
						if (!authorName.isEmpty()) {
							List<Book> booksByAuthor = user.serachBookByWriter(authorName);
							for (Book book : booksByAuthor) {
								if (!addedBookIds.contains(book.getBookId())) {
									finalbook.add(book);
									addedBookIds.add(book.getBookId()); // 添加书籍ID到集合中
								}
							}
						}
						// 如果同时输入了书名和作者名，进行交集处理
						if (!bookName.isEmpty() && !authorName.isEmpty()) {
							finalbook = finalbook.stream()
									.filter(book -> book.getBookName().contains(bookName)
											&& book.getBookWriterName().contains(authorName))
									.collect(Collectors.toList());
							// 更新addedBookIds集合，以反映交集后的结果
							addedBookIds.clear();
							for (Book book : finalbook) {
								addedBookIds.add(book.getBookId());
							}
						}
					}

					
					if (finalbook.size() == 0) {
						JOptionPane.showMessageDialog(null, "查询不到相关书籍", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					
					// 向表格模型添加数据
					for (Book bor : finalbook) {
						Object[] row = new Object[] { bor.getBookId(), bor.getBookName(), bor.getBookWriterName(),
								bor.getBookType(), bor.getBookStatement() ? "未被借阅" : "已被借阅", bor.getBookPosition(),
								bor.getBookNumber(), bor.getBookTime() };
						model.addRow(row); // 添加行到模型
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(panel, "查询失败！", "错误", JOptionPane.ERROR_MESSAGE);
				} finally {
					DatabaseUtil.close(null, null, con);
				}
			}
		});

		// 将组件添加到JPanel
		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);

		// 更新中心区域的内容
		mainPanel.removeAll();
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.revalidate(); // 重新验证布局
		mainPanel.repaint(); // 重绘组件
	}

	// 显示借书记录界面
	private void showBorrowRecordPanel(int id) {
		// 创建一个新的面板用于显示借阅记录
		JPanel borrowRecordsPanel = new JPanel(new BorderLayout());
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
		borrowRecordsPanel.add(scrollPane, BorderLayout.CENTER);

		// 更新中心区域的内容
		mainPanel.removeAll();
		mainPanel.add(borrowRecordsPanel, BorderLayout.CENTER);
		mainPanel.revalidate(); // 重新验证布局
		mainPanel.repaint(); // 重绘组件

		UserServiceImpl user = new UserServiceImpl();
		List<Borrow> listbor = user.serachBorrowRecordById(id);
		if (listbor.size() == 0) {
			JOptionPane.showMessageDialog(null, "查询不到相关记录", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		// 清除现有数据
		model.setRowCount(0);
		for (Borrow bor : listbor) {
			Object[] row = new Object[] { bor.getUseId(), bor.getBookId(), bor.getBorrowTime(),
					bor.getBookStatement() ? "未被借阅" : "已被借阅", bor.getBorrowReturnTime(), bor.getBookNumber(),
					bor.getBorrowId() };
			model.addRow(row); // 添加行到模型
		}

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
	
	// 显示修改个人信息界面
	private void showChangeInfoPanel() {

		try {
			Image backgroundImage;
			backgroundImage = ImageIO.read(new File("src/pictures/back1.jpg"));
			BackgroundImagePanel mainPanel1 = new BackgroundImagePanel(backgroundImage);
			mainPanel1.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);
	
			
			UserServiceImpl user = new UserServiceImpl();
			Connection con = DatabaseUtil.getConnection();
			AdminServiceImpl adm = new AdminServiceImpl(con);
			final UserInfo before =adm.getUserInfoById(id);
			
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
			addLabelAndFieldWithButton("名称:", nameField, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("密码:", passwordField, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("密保:", userQuestion, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("答案:", userAnswer, gbc, y++, mainPanel1);			
			addLabelAndFieldWithButton("电话:", phonenumberField, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("真名:", realnameField, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("地址:", addressField, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("邮箱:", emailField, gbc, y++, mainPanel1);
			addLabelAndFieldWithButton("专业:", majorField, gbc, y++, mainPanel1);

			// 添加修改按钮
			JButton changeButton = new JButton("修改");
			changeButton.setFont(new Font("楷书", Font.BOLD, 15));
			gbc.gridx = 2;
			gbc.gridy = y++;
			gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
			mainPanel1.add(changeButton, gbc); // 添加到主面板

			changeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UserInfo after = new UserInfo(id,nameField.getText(),
					passwordField.getText(),userQuestion.getText(),userAnswer.getText(),
					false,before.getUserGender(),phonenumberField.getText(),
					realnameField.getText(),addressField.getText(),emailField.getText(),
					majorField.getText()) ;
					user.changeUserInfo(after);
					JOptionPane.showMessageDialog(null, "修改成功！");
				}
			});

			// 更新中心区域的内容
			mainPanel.removeAll();
			mainPanel.add(mainPanel1, BorderLayout.CENTER);
			mainPanel.revalidate(); // 重新验证布局
			mainPanel.repaint(); // 重绘组件

		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			 System.err.println("修改失败");
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
	

	// 退出登录
	private void logout() {
		this.dispose(); // 关闭当前窗口
		Login loginFrame = new Login(); // 回到登录界面
		loginFrame.setVisible(true);
	}

	class BackgroundPanel extends JPanel {

		private String imgPath; // 用于指定图片路径

		public BackgroundPanel(String imgPath) {
			this.imgPath = imgPath;
		}

		@Override
		protected void paintComponent(Graphics g) {
			ImageIcon icon = new ImageIcon(imgPath);
			g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public static void main(String[] args) {
		student_system frame = new student_system(123456); // 示例用户 ID
		frame.setVisible(true);
	}
}
