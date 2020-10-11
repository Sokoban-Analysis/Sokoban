package com.zetcode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class MainUI extends JPanel {
    private PanelChange change;

    public MainUI(PanelChange change){
        setLayout(null);
        this.change = change;
        JButton btn = new JButton("버튼");
        btn.setSize(200,100);
        add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                change.initBoard();
                change.changePanel();
            }
        });
    }
}
