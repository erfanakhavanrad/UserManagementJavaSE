package com.company.ui;

import com.company.data.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    User user;
    boolean ok = false;

    public UserDialog(User user) {
        this.user = user;
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
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        UIUtility.setLocation(this, 250, 220);
        setModal(true);
        if (user != null) {
            nameField.setText(user.getName());
            passwordField.setText(user.getPassword());
            emailField.setText(user.getEmail());
            birthdayField.setText(simpleDateFormat.format(user.getBirthday()));
            salaryField.setText(String.valueOf(user.getSalary()));
        }
        setVisible(true);
    }

    public UserDialog() {
        this(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == okButton) {
                ok = true;
                String name = nameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();
                String birthday = birthdayField.getText();
                String salary = salaryField.getText();
                if (user == null) user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setEmail(email);
                user.setBirthday(simpleDateFormat.parse(birthday));
                user.setSalary(Double.parseDouble(salary));
//                user = new User(name, password, email, simpleDateFormat.parse(birthday), Double.parseDouble(salary));
                this.setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }

    public boolean isOk() {
        return ok;
    }
}