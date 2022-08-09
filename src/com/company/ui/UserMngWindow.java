package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMngWindow extends JFrame implements ActionListener {

    UserTableModel model = new UserTableModel();
    JTable table = new JTable(model);

    //    JButton addBtn = new JButton("Add");
    JButton addBtn = new JButton(new ImageIcon("icon/add.png"));
    JButton editBtn = new JButton(new ImageIcon("icon/edit.png"));
    JButton deleteBtn = new JButton(new ImageIcon("icon/delete.png"));

    public UserMngWindow() throws HeadlessException {
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.PAGE_START);
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
        if (e.getSource() == addBtn) {
            UserDialog dialog = new UserDialog();
        }
    }
}
