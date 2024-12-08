package com.libr.gui;

import javax.swing.JFrame;

public class stu_borrowRecord extends JFrame{
	 public stu_borrowRecord() {
	        setTitle("借书记录界面");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	 }
	public static void main(String[] args) {
		stu_borrowRecord frame=new stu_borrowRecord();
		frame.setVisible(true);
	}
}
