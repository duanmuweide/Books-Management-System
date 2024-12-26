package com.libr.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.libr.entity.Book;
import com.libr.service.impl.AdminServiceImpl;
import com.libr.service.impl.UserServiceImpl;
import com.libr.util.DatabaseUtil;

public class student_system extends JFrame {
	private String id;
	private JPanel mainPanel; // 主面板，用于切换显示的内容

	public student_system(String userid) {
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

		JButton btnReturnRecord = new JButton("查看还书记录");
		btnReturnRecord.setBackground(new Color(70, 130, 180));
		btnReturnRecord.setForeground(Color.WHITE);
		btnReturnRecord.setFont(new Font("楷体", Font.BOLD, 18));

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
		menuPanel.add(btnReturnRecord);
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
		btnBorrowRecord.addActionListener(e -> showBorrowRecordPanel());
		btnReturnRecord.addActionListener(e -> showReturnRecordPanel());
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
	    mainPanel.removeAll();

	    // 创建输入字段
	    JTextField bookNameField = new JTextField(30);
	    JTextField authorNameField = new JTextField(30);
	    JTextField IDField = new JTextField(30);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    JFormattedTextField formattedTextField = new JFormattedTextField(dateFormat);
	    formattedTextField.setColumns(30);

	    // 创建按钮
	    JButton searchButton = new JButton("查找");

	    // 创建表格
	    String[] columnNames = { "编号", "书名", "作者名", "类型", "借阅状态", "位置", "数量", "入库时间" };
	    DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
	    JTable table = new JTable(model);
	    table.setFont(new Font("楷体", Font.PLAIN, 20));  // 设置字体
        table.setRowHeight(30);
        table.setBackground(new Color(245, 245, 245));
	    JScrollPane scrollPane = new JScrollPane(table);
	    //表头
	    table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 16));  // 表头字体
        table.getTableHeader().setBackground(new Color(85, 130, 200));  // 表头背景色
        table.getTableHeader().setForeground(Color.WHITE);  // 表头字体颜色
        table.getTableHeader().setReorderingAllowed(false);
	    // north部分：输入图书名称和作者名称
	    JPanel northPanel = new JPanel();
	    northPanel.setPreferredSize(new Dimension(0, 55));
	    
	    JLabel bookLabel = new JLabel("图书名称:");
	    bookLabel.setFont(new Font("楷体", Font.BOLD, 20));
        northPanel.add(bookLabel);
	    northPanel.add(bookNameField);
	    
	    JLabel writerLabel =new JLabel("作者名称:");
	    writerLabel.setFont(new Font("楷体", Font.BOLD, 20));
	    northPanel.add(writerLabel);
	    northPanel.add(authorNameField);
	    
	    JLabel numberLabel =new JLabel("图书编号:");
	    numberLabel.setFont(new Font("楷体", Font.BOLD, 20));
	    northPanel.add(numberLabel);
	    northPanel.add(IDField);
	    
	    JLabel timeLabel =new JLabel("入库时间:");
	    timeLabel.setFont(new Font("楷体", Font.BOLD, 20));
	    northPanel.add(timeLabel);
	    northPanel.add(formattedTextField);
	    northPanel.add(searchButton);

	    // 为查找按钮添加事件监听器
	    searchButton.addActionListener(e -> {
	        String bookName = bookNameField.getText();
	        String authorName = authorNameField.getText();
	        String idString = IDField.getText();
	        int id;
	        if (idString.isEmpty()) {
	            id = -1; // 如果ID为空，则设置为无效值
	        } else {
	            id = Integer.parseInt(idString);
	        }

	        Date date = (Date) formattedTextField.getValue();
	        Connection con = DatabaseUtil.getConnection();
	        AdminServiceImpl adm = new AdminServiceImpl(con);
	        UserServiceImpl user = new UserServiceImpl();

	        // 获取查询到的书
	        List<Book> bookid = new ArrayList<>();
	        if (id != -1) {
	            Book bookID = adm.searchBookById(id);
	            if (bookID != null) {
	                bookid.add(bookID);
	            }
	        }
	        List<Book> bookname = adm.searchBookByName(bookName);
	        List<Book> authorname = user.serachBookByWriter(authorName);
	        List<Book> bookdate = adm.searchBookByDate(date);

	        // 合并查询结果
	        List<Book> finalbook;
	        if (id == -1) {
	            finalbook = bookname.stream()
	                    .filter(book -> authorname.stream().anyMatch(authorBook -> authorBook.getBookId() == book.getBookId()))
	                    .filter(book -> bookdate.stream().anyMatch(dateBook -> dateBook.getBookId() == book.getBookId()))
	                    .collect(Collectors.toList());
	        } else {
	            finalbook = bookname.stream()
	                    .filter(book -> authorname.stream().anyMatch(authorBook -> authorBook.getBookId() == book.getBookId()))
	                    .filter(book -> bookdate.stream().anyMatch(dateBook -> dateBook.getBookId() == book.getBookId()))
	                    .filter(book -> bookid.stream().anyMatch(idBook -> idBook.getBookId() == book.getBookId()))
	                    .collect(Collectors.toList());
	        }

	        // 更新表格数据
	        model.setRowCount(0); // 清空现有数据
	        for (Book book : finalbook) {
	            model.addRow(new Object[]{
	                    book.getBookId(),
	                    book.getBookName(),
	                    book.getBookWriterName(),
	                    book.getBookType(),
	                    book.getBookStatement(),
	                    book.getBookPosition(),
	                    book.getBookNumber(),
	                    book.getBookTime()
	            });
	        }

	        table.setFont(new Font("宋体", Font.PLAIN, 14));
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    });

	    // 将组件添加到主面板
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.add(northPanel, BorderLayout.NORTH);
	    panel.add(scrollPane, BorderLayout.CENTER);

	    mainPanel.add(panel, BorderLayout.CENTER);
	    mainPanel.revalidate(); // 重新验证布局
	    mainPanel.repaint(); // 重绘组件
	}

	// 显示借书记录界面
	private void showBorrowRecordPanel() {
		mainPanel.removeAll();
		JLabel borrowRecordLabel = new JLabel("查看借书记录界面", JLabel.CENTER);
		borrowRecordLabel.setFont(new Font("楷体", Font.BOLD, 28));
		mainPanel.add(borrowRecordLabel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	// 显示还书记录界面
	private void showReturnRecordPanel() {
		mainPanel.removeAll();
		JLabel returnRecordLabel = new JLabel("查看还书记录界面", JLabel.CENTER);
		returnRecordLabel.setFont(new Font("楷体", Font.BOLD, 28));
		mainPanel.add(returnRecordLabel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	// 显示修改个人信息界面
	private void showChangeInfoPanel() {
		mainPanel.removeAll();
		JLabel changeInfoLabel = new JLabel("修改个人信息界面", JLabel.CENTER);
		changeInfoLabel.setFont(new Font("楷体", Font.BOLD, 28));
		mainPanel.add(changeInfoLabel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
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
		student_system frame = new student_system("123456"); // 示例用户 ID
		frame.setVisible(true);
	}
}
