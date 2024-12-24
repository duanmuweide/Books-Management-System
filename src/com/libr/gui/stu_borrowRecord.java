package com.libr.gui;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.libr.entity.Borrow;
import com.libr.service.impl.UserServiceImpl;

public class stu_borrowRecord extends JFrame{
	private String userid;
	private int bookId;
	private int useId;
	private Date borrowTime;
	private Date borrowReturnTime;
	private Boolean bookStatement;
	private int bookNumber;
	private int borrowId;
	
	// 创建表格列名
	String[] columnNames = { "学生学号", "图书编号", "借阅时间", "图书状态","归还时间", "数量","借阅编号" };

	// 创建表格数据
	Object[][] record;
	// 创建表格模型
	DefaultTableModel model = new DefaultTableModel(record, columnNames);
	// 创建表格
	JTable table = new JTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	
	 public stu_borrowRecord(String userID) {
		 	int id=1;
	        setTitle("借书记录界面");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        
	        UserServiceImpl user=new UserServiceImpl();
	        List<Borrow> listbor=user.serachBorrowRecordById(id);
	        for(Borrow bor:listbor) {
	        	int i=0;
				record[i][0]=bor.getUseId();
				record[i][1]=bor.getBookId();
				record[i][2]=bor.getBorrowTime();
				if(bor.getBookStatement()==true) {
					record[i][3]="未被借阅";
				}else {
					record[i][3]="已被借阅";
				}
				record[i][4]=bor.getBorrowReturnTime();
				record[i][5]=bor.getBookNumber();
				record[i][6]=bor.getBorrowId();	
			i++;
	        }
	        
	        table.setFont(new Font("宋体", Font.PLAIN, 14));

			// 创建滚动面板，包含表格
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        
	 }
	public static void main(String[] args) {
		stu_borrowRecord frame=new stu_borrowRecord("");
		frame.setVisible(true);
	}
}
