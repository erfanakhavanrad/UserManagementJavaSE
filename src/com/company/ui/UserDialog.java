package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDialog extends JDialog implements ActionListener {

    JLabel messageLabel = new JLabel("");
    JLabel nameLabel = new JLabel("Name");
    JLabel passwordLabel = new JLabel("Password");
    JLabel emailLabel = new JLabel("Email");
    JLabel birthdayLabel = new JLabel("Birthday");
    JLabel salaryLabel = new JLabel("Salary");

    JTextField nameField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField birthdayField = new JTextField();
    JTextField salaryField = new JTextField();

    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    public UserDialog() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(birthdayLabel);
        panel.add(birthdayField);
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(okButton);
        panel.add(cancelButton);
        add(panel, BorderLayout.CENTER);
        UIUtility.setLocation(this, 350, 230);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
