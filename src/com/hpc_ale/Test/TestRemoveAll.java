package com.hpc_ale.Test;

import com.hpc_ale.ui.Animation;
import com.hpc_ale.ui.Poker;

import javax.swing.*;
import java.awt.*;

public class TestRemoveAll extends JFrame {
    public TestRemoveAll() throws HeadlessException {
        this.setSize(800, 800);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        Poker p = new Poker("dd", false, true);
        this.getContentPane().add(p);
        Point to = new Point(400, 200);
        this.getContentPane().add(new JLabel(new ImageIcon("src/image/table.jpg")));

        this.setVisible(true);

        Animation.move(p, p.getLocation(), to, 10);
    }

    public static void main(String[] args) {
        new TestRemoveAll();
    }
}
