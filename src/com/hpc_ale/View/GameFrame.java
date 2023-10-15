package com.hpc_ale.View;

import com.hpc_ale.Model.JTrashGame;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private JButton takeCard = new JButton("Pesca Carta");
    private JTrashGame jTrashGame;

    public GameFrame(JTrashGame jTG) throws HeadlessException {

        this.jTrashGame = jTG;

        //初始化界面
        initJFrame();

        //初始化图片
        initImage();

        this.setVisible(true);
    }

    //初始化界面
    public void initJFrame() {
        this.setSize(800, 828);
        this.setTitle("JTrast");
        this.setAlwaysOnTop(true);          //窗口置顶
        this.setLocationRelativeTo(null);   //窗口居中
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);               //取消默认居中
    }

    //初始化图片
    public void initImage() {
        //移除全部图片
        this.getContentPane().removeAll();

        //设置卡牌
        initCardBG();

        //抽卡按钮
        takeCard.setBounds(365, 362, 145, 50);
        this.getContentPane().add(takeCard);

        //设置背景图片
        JLabel bg = new JLabel(new ImageIcon("src/image/table.jpg"));
        bg.setBounds(0, 0, 800, 800);
        this.getContentPane().add(bg);

        //刷行图片
        this.getContentPane().repaint();
    }

    //加载卡牌
    public void initCardBG() {

        //抽卡
        JLabel cardBG = new JLabel(new ImageIcon("src/image/card/cardBG/cardBG-ob.png"));
        cardBG.setBounds(290, 338, 70, 98);
        this.add(cardBG);

        //下
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel cardBG_lr = new JLabel(new ImageIcon("src/image/card/cardBG/cardBG-ob.png"));
                cardBG_lr.setBounds(215 + (j * 75), 595 + (i * 103), 70, 98);
                this.getContentPane().add(cardBG_lr);
            }
        }
        //上
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel cardBG_ob = new JLabel(new ImageIcon("src/image/card/cardBG/cardBG-ob.png"));
                cardBG_ob.setBounds(215 + (j * 75), 5 + (i * 103), 70, 98);
                this.getContentPane().add(cardBG_ob);
            }
        }
        if (jTrashGame.getNumPlayer() > 2) {
            //左
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    JLabel cardBG_lr = new JLabel(new ImageIcon("src/image/card/cardBG/cardBG-lr.png"));
                    cardBG_lr.setBounds(5 + (i * 103), 215 + (j * 75), 98, 70);
                    this.getContentPane().add(cardBG_lr);
                }
            }
            if (jTrashGame.getNumPlayer() > 3) {
                //右
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 5; j++) {
                        JLabel cardBG_lr = new JLabel(new ImageIcon("src/image/card/cardBG/cardBG-lr.png"));
                        cardBG_lr.setBounds(595 + (i * 103), 215 + (j * 75), 98, 70);
                        this.getContentPane().add(cardBG_lr);
                    }
                }
            }
        }
    }
}

