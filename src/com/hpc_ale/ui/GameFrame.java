package com.hpc_ale.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameFrame extends JFrame implements ActionListener {
    private static Container container = null;
    private final JButton takeCard = new JButton("Pesca Carta");
    private final ArrayList<Poker> deck;
    private int numDeck = 1;
    private final int numPlayer;
    private int turn = 0;
    private int countCatd = 0;
    private final JLabel bg = new JLabel(new ImageIcon("src/image/table.jpg"));
    private final Player[] players;
    private boolean gameOver;
    private boolean cardIsTake = false;

    public GameFrame(int numPlayer) {
        this.gameOver = false;
        this.numPlayer = numPlayer;
        players = new Player[numPlayer];
        this.deck = new ArrayList<>();
        this.takeCard.addActionListener(this);

        this.bg.setBounds(0, 0, 800, 800);
        initJFrame();
        //initStartButton();
        initImg();
        distributeCard();
        try {
            startGame();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        endGame();
    }

    public static Container getContainer() {
        return container;
    }

    public void endGame() {
        container.removeAll();
        container.repaint();
    }

    public void startGame() throws InterruptedException {
        while (!gameOver) {
            if (turn != 0) {
                takeCard.setVisible(false);
                turnGame();
                if (turn != numPlayer - 1) {
                    turn++;
                } else {
                    turn = 0;
                }
            } else {
                takeCard.setVisible(true);
                if (cardIsTake) {
                    cardIsTake = false;
                    turnGame();
                    turn++;
                }
            }

            if (players[turn].isWinner() || countCatd >= numDeck * 54) {
                gameOver = true;
            }

        }
    }

    public void turnGame() {
        //获得卡组里的嘴上面的卡牌
        Poker c = deck.get(countCatd);
        container.setComponentZOrder(c, 0);
        countCatd++;

        //反转卡牌
        c.turnFront();

        //如果玩家在（左-右）转一下卡牌
        if (turn == 2 || turn == 3) {
            c.setNormalDirection(false);
        }

        //最终卡牌抛出的位置
        Point to = new Point(400, 338);

        //玩家抽取卡牌，如果卡牌在1-10抛出一张相对应的手卡
        Poker dropCard = players[turn].takeCard(c);

        //直到玩家没有翻出1-10之间的卡牌
        while (dropCard != null) {

            //获取翻出卡牌的坐标
            Point dropCardLocation = dropCard.getLocation();

            //反转翻出的卡牌
            dropCard.turnFront();
            container.setComponentZOrder(dropCard, 0);

            //移动这张卡牌到等待区域等待翻出下一张卡牌
            Animation.move(dropCard, dropCard.getLocation(), to, 50);

            //把从卡组里抽到的牌放进手牌里
            Animation.move(c, c.getLocation(), dropCardLocation, 50);

            //把收取的牌变成玩家抛出的那张
            c = dropCard;

            //玩家抽取上轮抛出的牌来判断是否继续
            dropCard = players[turn].takeCard(c);
        }

        //如果玩家在（左-右）转一下卡牌
        if (turn == 2 || turn == 3) {
            c.setNormalDirection(true);
        }
        Animation.move(c, c.getLocation(), to, 50);
    }

    /**
     * Inizializzare il frame a
     * 800px * 828px (28px sono i px del barra superiore)
     * il frame deve stsre sempre al top e al centro
     */
    public void initJFrame() {
        this.setSize(800, 828);
        this.setTitle("JTrast");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        container = this.getContentPane();
        this.setVisible(true);
    }

    /**
     * inizializzare l'immagine iniziale con il deck al centro
     */
    public void initImg() {
        if (numPlayer > 2) {
            numDeck++;
        }

        //inizializzare le carte metterni nel deck
        for (int d = 0; d < numDeck; d++) {
            //non servono i joker
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 13; j++) {
                    if (i == 5 && j > 2) {
                        break;
                    } else {
                        Poker poker = new Poker(i + "-" + j, false, true);
                        poker.setLocation(325, 338);
                        deck.add(poker);
                        GameFrame.getContainer().add(poker);
                    }
                }
            }
        }

        //mischiare il deck
        Collections.shuffle(deck);

        //inizializzare la bposizione del bottone estrarre una carta
        takeCard.setVisible(false);
        takeCard.setBounds(594, 650, 200, 98);
        container.add(takeCard);
        container.add(bg);
        container.repaint();
    }

    /**
     * distribuire le carte ai vari players
     */
    public void distributeCard() {

        //distruibuirlo a se stesso
        disCardsToPlayer(0, 215, 595, true);

        //distribuirlo a quello di fronte (sicuro c'è)
        disCardsToPlayer(1, 215, 5, true);

        //se ci sono più di 2 player distruibuirlo anche a quello di sinistra
        if (numPlayer > 2) {
            disCardsToPlayer(2, 5, 215, false);
            //se ci sono 4 players distribuire tutte le carte
            if (numPlayer > 3) {
                disCardsToPlayer(3, 595, 215, false);
            }
        }
    }

    /**
     * distribuire le carte e aggiungerli ai deck dei vari players
     * @param index index del player
     * @param x costanti che stabilisce la corinata x iniziale della posizione finale della carta
     * @param y costanti che stabilisce la corinata y iniziale della posizione finale della carta
     */
    public void disCardsToPlayer(int index, int x, int y, boolean direction) {
        int plyerCountCard = 0;
        Poker[] temp = new Poker[10];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Poker card = deck.get(countCatd);
                card.setNormalDirection(direction);
                temp[plyerCountCard] = card;
                plyerCountCard++;
                Point to = new Point();
                if (direction) {
                    to.setLocation(x + (j * 75), y + (i * 103));
                } else {
                    to.setLocation(x + (i * 103), y + (j * 75));
                }
                Animation.move(card, card.getLocation(), to, 10);
                countCatd++;
            }
        }
        players[index] = new Player(temp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == takeCard) {
            if (countCatd < numDeck * 54) {
                cardIsTake = true;
            } else {
                gameOver = true;
            }
        }
    }
}

