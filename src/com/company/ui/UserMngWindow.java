package com.company.ui;

import com.company.data.dao.UserDAO;
import com.company.data.entity.User;
import sun.security.krb5.internal.KDCOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserMngWindow extends JFrame implements ActionListener {

    UserTableModel model = new UserTableModel();
    JTable table = new JTable(model);

    //    JButton addBtn = new JButton("Add");
    JButton addBtn = new JButton(new ImageIcon("icon/add.png"));
    JButton editBtn = new JButton(new ImageIcon("icon/edit.png"));
    JButton deleteBtn = new JButton(new ImageIcon("icon/delete.png"));

    UserDAO userDAO = new UserDAO();

    public UserMngWindow() throws HeadlessException, SQLException {
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.PAGE_START);
        model.setUsers(userDAO.findAll());
        add(new JScrollPane(table));
        UIUtility.setLocation(this, 500, 250);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenuItem changePasswordItem = new JMenuItem("Change password");
        bar.add(fileMenu);
        fileMenu.add(changePasswordItem);
        return bar;
    }

    private JToolBar createToolBar() {
        JToolBar bar = new JToolBar();
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        bar.add(addBtn);
        bar.add(editBtn);
        bar.add(deleteBtn);
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
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
