package com.hpc_ale.Control;

import com.hpc_ale.Model.JTrashGame;
import com.hpc_ale.View.GameFrame;
import com.hpc_ale.View.StartFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private StartFrame startFrame;
    private JTrashGame jTrashGame;

    public Controller(StartFrame sF, JTrashGame jTG) {
        this.startFrame = sF;
        this.jTrashGame = jTG;

        //添加监听事件
        startFrame.getOneVsOne().addActionListener(this);
        startFrame.getOneVsTwo().addActionListener(this);
        startFrame.getOneVsTree().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Congtroller");

        Object obj = e.getSource();

        startFrame.removeAll();
        startFrame.setVisible(false);

        if (obj == startFrame.getOneVsOne()) {
            jTrashGame.setNumPlayer(2);
        } else if (obj == startFrame.getOneVsTwo()) {
            jTrashGame.setNumPlayer(3);
        } else if (obj == startFrame.getOneVsTree()) {
            jTrashGame.setNumPlayer(4);
        }

        new GameFrame(jTrashGame);
    }
}
