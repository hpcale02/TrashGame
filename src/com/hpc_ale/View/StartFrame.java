package com.hpc_ale.View;

import javax.swing.*;

public class StartFrame extends JFrame {

    private JButton oneVsOne = new JButton("1-VS-1");
    private JButton oneVsTwo = new JButton("1-VS-2");
    private JButton oneVsTree = new JButton("1-VS-3");

    public StartFrame() {
        initFrame();

        initImage();

        this.setVisible(true);
    }

    public JButton getOneVsOne() {
        return oneVsOne;
    }

    public JButton getOneVsTwo() {
        return oneVsTwo;
    }

    public JButton getOneVsTree() {
        return oneVsTree;
    }

    public void initImage() {
        //移除全部图片
        this.getContentPane().removeAll();

        //添加按钮
        oneVsOne.setBounds(150, 90, 200, 90);
        oneVsTwo.setBounds(150, 200, 200, 90);
        oneVsTree.setBounds(150, 310, 200, 90);

        this.getContentPane().add(oneVsOne);
        this.getContentPane().add(oneVsTwo);
        this.getContentPane().add(oneVsTree);

        //添加背景图片
        JLabel bg = new JLabel(new ImageIcon("src/image/table.jpg"));
        bg.setBounds(0, 0, 500, 500);
        this.getContentPane().add(bg);

        //刷行图片
        this.getContentPane().repaint();
    }

    public void initFrame() {
        this.setSize(500, 528);
        this.setTitle("StartJTrast");
        this.setAlwaysOnTop(true);          //窗口置顶
        this.setLocationRelativeTo(null);   //窗口居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);                //取消默认居中
    }


}
