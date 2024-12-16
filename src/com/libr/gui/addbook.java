package com.libr.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class find_man extends JFrame {

    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextField IDField;
    private JTextArea resultArea;

    public find_man() {
        // 设置窗口标题和默认关闭操作
        setTitle("图书查找页面");
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

        northPanel.add(new JLabel("图书ID:"));
        IDField = new JTextField(20);
        IDField.setFont(new Font(IDField.getFont().getName(), Font.BOLD, IDField.getFont().getSize()));
        northPanel.add(IDField);
        
        JButton searchButton = new JButton("查找");
        northPanel.add(searchButton);

        // 为查找按钮添加事件监听器
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                String bookName = bookNameField.getText();
                String authorName = authorNameField.getText();
                resultArea.setText("查找图书名称包含 \"" + bookName + "\" 且作者名称包含 \"" + authorName + "\" 的图书信息。");
            }
        });

        add(northPanel, BorderLayout.NORTH);

        // center部分：显示查找到的图书信息
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 设置窗口大小和可见性
        setSize(600, 400);
        setLocationRelativeTo(null); // 居中显示
    }

    public static void main(String[] args) {
        // 直接在main方法中创建并显示GUI
        find_man frame = new find_man();
        frame.setVisible(true);
    }
}
