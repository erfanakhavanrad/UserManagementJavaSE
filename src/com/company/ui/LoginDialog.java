package com.company.ui;

import com.company.data.dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog implements ActionListener {

    JLabel userLabel = new JLabel("User");
    JLabel passwordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel("");

    JTextField userField = new JTextField("admin");
    JPasswordField passwordField = new JPasswordField("admin");

    JButton loginButton = new JButton("Login");
    JButton cancelButton = new JButton("Cancel");

    UserDAO userDAO = new UserDAO();

    public LoginDialog() {
        setTitle("Login");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        JPanel jPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        jPanel.add(userLabel);
        jPanel.add(userField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);
        jPanel.add(loginButton);
        jPanel.add(cancelButton);
        add(jPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.NORTH);
//        setSize(200, 200);
        UIUtility.setLocation(this, 350, 220);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loginButton) {
                String user = userField.getText();
                String password = new String(passwordField.getPassword());
                if (userDAO.login(user.trim(), password.trim())) {
                    this.dispose();
                    UserMngWindow userMngWindow = new UserMngWindow();
                } else {
                    messageLabel.setText("Username/Password is invalid!");
                }

            } else {
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
