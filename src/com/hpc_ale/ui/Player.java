package com.hpc_ale.ui;

public class Player {
    public Poker[] cards;

    public Player(Poker[] cards) {
        this.cards = cards;
    }

    public boolean isWinner() {
        for (Poker card : cards) {
            if (card.isRear()) {
                return false;
            }
        }
        return true;
    }

    public Poker takeCard(Poker card) {
        int cardNum = card.getNum();
        if (cardNum > 0 && cardNum <= 10) {
            if (!card.getName().equals("5-1") && !card.getName().equals("5-2")) {
                if (cards[cardNum - 1].isRear()) {
                    Poker temp = cards[cardNum - 1];
                    cards[cardNum - 1] = card;
                    return temp;
                }
            }
        }
        return null;
    }
}
