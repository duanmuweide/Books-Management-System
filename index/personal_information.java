package index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class personal_information extends JFrame {
    public personal_information() {
        setTitle("个人信息页面");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 已知的个人信息
        String id = "anna";
        String password = "1234";
        String phoneNumber = "110110110";
        String email = "110110110@qq.com";
        String gender = "女";

        // 创建主面板
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件之间的间距

        // 添加信息标签和修改按钮
        mainPanel.add(new JLabel("ID: " + id), gbc);
        gbc.gridx = 1;
        JButton modifyButton = new JButton("修改");
        mainPanel.add(modifyButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        mainPanel.add(new JLabel("密码: " + password), gbc);
        gbc.gridx = 1;
        modifyButton = new JButton("修改");
        mainPanel.add(modifyButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        mainPanel.add(new JLabel("手机号: " + phoneNumber), gbc);
        gbc.gridx = 1;
        modifyButton = new JButton("修改");
        mainPanel.add(modifyButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        mainPanel.add(new JLabel("邮箱: " + email), gbc);
        gbc.gridx = 1;
        modifyButton = new JButton("修改");
        mainPanel.add(modifyButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        mainPanel.add(new JLabel("性别: " + gender), gbc);
        gbc.gridx = 1;
        modifyButton = new JButton("修改");
        mainPanel.add(modifyButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        // 添加主面板到窗口
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new personal_information().setVisible(true);
            }
        });
    }
}
