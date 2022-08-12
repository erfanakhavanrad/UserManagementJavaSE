package com.company.ui;

import com.company.data.dao.UserDAO;
import com.company.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog implements ActionListener {

    JLabel userLabel = UIUtility.createLabel("general.user");
    JLabel passwordLabel = UIUtility.createLabel("general.password");
    JLabel messageLabel = new JLabel("");

    JTextField userField = UIUtility.createField("admin");
    JPasswordField passwordField = UIUtility.createPasswordField("admin");

    JButton loginButton = UIUtility.createButton("login.btn.login");
    JButton cancelButton = UIUtility.createButton("login.btn.cancel");

    UserDAO userDAO = new UserDAO();
    // Note: UserDao should be replaced by UserService where ever it is used in the project.
//    UserService userService = new UserService();

    public LoginDialog() {
        setTitle(I18NUtility.getMessage("login.title"));
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
                    messageLabel.setText(I18NUtility.getMessage("login.user.password.invalid"));
                }

            } else {
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
