package com.company.ui;

import com.company.data.dao.UserDAO;
import com.company.data.entity.User;
import com.company.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserMngWindow extends JFrame implements ActionListener {
    Logger logger = Logger.getLogger(UserMngWindow.class.getName());


    UserTableModel model = new UserTableModel();
    JTable table = UIUtility.createTable(model);

    //    JButton addBtn = new JButton("Add");
    JButton addBtn = new JButton(new ImageIcon("icon/add.png"));
    JButton editBtn = new JButton(new ImageIcon("icon/edit.png"));
    JButton deleteBtn = new JButton(new ImageIcon("icon/delete.png"));
    JButton excelBtn = new JButton(new ImageIcon("icon/excel.png"));

    JMenuItem changePasswordItem = UIUtility.createMenuItem("main.menu.changePassword");
    JMenuItem farsiItem = UIUtility.createRadioButtonMenuItem("main.menu.farsi", !UIUtility.isLTR());
    JMenuItem englishItem = UIUtility.createRadioButtonMenuItem("main.menu.english", UIUtility.isLTR());

        UserDAO userDAO = new UserDAO();
//    UserService userService = new UserService();

    public UserMngWindow() throws HeadlessException, SQLException {
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.PAGE_START);
        model.setUsers(userDAO.findAll());
        add(new JScrollPane(table));
        UIUtility.setLocation(this, 500, 250);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = UIUtility.createMenuBar();
        JMenu fileMenu = UIUtility.createMenu("main.menu.file");
        ButtonGroup group = new ButtonGroup();
        group.add(farsiItem);
        group.add(englishItem);
        bar.add(fileMenu);
        fileMenu.add(changePasswordItem);
        fileMenu.add(farsiItem);
        fileMenu.add(englishItem);
        farsiItem.addActionListener(this);
        englishItem.addActionListener(this);
        changePasswordItem.addActionListener(this);
        return bar;
    }

    private JToolBar createToolBar() {
        JToolBar bar = new JToolBar();
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        excelBtn.addActionListener(this);
        bar.add(addBtn);
        bar.add(editBtn);
        bar.add(deleteBtn);
        bar.addSeparator();
        bar.add(excelBtn);
        return bar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addBtn) {
                UserDialog dialog = new UserDialog();
                if (dialog.isOk()) {
                    User user = dialog.getUser();
                    dialog.dispose();
                    userDAO.save(user);
                    model.setUsers(userDAO.findAll());
                }
            } else if (e.getSource() == editBtn) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    User user = model.getSelectedUser(row);
                    UserDialog dialog = new UserDialog(user);
                    if (dialog.isOk()) {
                        userDAO.update(user);
                        dialog.dispose();
                        model.setUsers(userDAO.findAll());
                    }
                }
            } else if (e.getSource() == deleteBtn) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    User user = model.getSelectedUser(row);
                    int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        userDAO.delete(user);
                        model.setUsers(userDAO.findAll());
                    }
                }
            } else if (e.getSource() == farsiItem) {
                if (!I18NUtility.getLanguage().equals("fa")) {
                    I18NUtility.setLanguage("fa");
                    farsiItem.setSelected(true);
                    this.setVisible(true);
                    new UserMngWindow();
                    this.dispose();
                }
            } else if (e.getSource() == englishItem) {
                if (!I18NUtility.getLanguage().equals("en")) {
                    I18NUtility.setLanguage("en");
                    englishItem.setSelected(true);
                    this.setVisible(true);
                    new UserMngWindow();
                    this.dispose();
                }
            } else if (e.getSource() == excelBtn) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ExcelReporter.export(model.getUsers(), file);
                }
            }
        } catch (Exception exception) {
//            exception.printStackTrace();
            logger.severe("Error: " + exception.getMessage());
            JOptionPane.showMessageDialog(this, "Error: " + exception.getMessage(), "ErrorTitle", JOptionPane.WARNING_MESSAGE);
        }
    }
}
