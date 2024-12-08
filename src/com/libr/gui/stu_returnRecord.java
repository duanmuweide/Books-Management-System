package com.libr.gui;

import javax.swing.JFrame;

public class stu_returnRecord extends JFrame{
	 public stu_returnRecord() {
	        setTitle("还书记录界面");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	 }
	public static void main(String[] args) {
		stu_returnRecord frame=new stu_returnRecord();
		frame.setVisible(true);
	}
}
