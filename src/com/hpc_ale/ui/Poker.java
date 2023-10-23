package com.hpc_ale.ui;

import javax.swing.*;

public class Poker extends JLabel {

    private String name;
    private boolean up;
    private boolean normalDirection;

    public Poker(String name, boolean up, boolean normalDirection) {
        this.name = name;
        this.up = up;
        this.normalDirection = normalDirection;

        if (this.up) {
            turnFront();
        } else {
            turnRear();
        }

        if (this.normalDirection) {
            this.setSize(70, 98);
        } else {
            this.setSize(98, 70);
        }
        this.setVisible(true);
    }

    /**
     * Mostrare il fronte
     */
    public void turnFront() {
        if (normalDirection) {
            this.setIcon(new ImageIcon("src/image/card/obCard/" + name + ".png"));
        } else {
            this.setIcon(new ImageIcon("src/image/card/lrCard/" + name + ".png"));
        }
        this.up = true;
    }

    /**
     * mostrare il retro
     */
    public void turnRear() {
        if (normalDirection) {
            this.setIcon(new ImageIcon("src/image/card/obCard/cardBG.png"));
        } else {
            this.setIcon(new ImageIcon("src/image/card/lrCard/cardBG.png"));
        }
        this.up = false;
    }

    public boolean isRear() {
        return !up;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setUp(boolean up) {
        this.up = up;
    }


    public void setNormalDirection(boolean normalDirection) {
        this.normalDirection = normalDirection;
        if (normalDirection) {
            this.setSize(70, 98);
            if (up) {
                this.setIcon(new ImageIcon("src/image/card/obCard/" + name + ".png"));
            } else {
                this.setIcon(new ImageIcon("src/image/card/obCard/cardBG.png"));
            }
        } else {
            this.setSize(98, 70);
            if (up) {
                this.setIcon(new ImageIcon("src/image/card/lrCard/" + name + ".png"));
            } else {
                this.setIcon(new ImageIcon("src/image/card/lrCard/cardBG.png"));
            }
        }
    }

    public int getNum() {
        try {
            return Integer.parseInt(name.substring(2));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
