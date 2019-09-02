public class Card {

    int rank;
    String suit;

    public Card() {
        rank = -1;
        suit = "";
    }

    public Card(String n, String s) {
        suit = s;
        if (n.equals("T")) {
            rank = 10;
        }
        else if (n.equals("J")) {
            rank = 11;
        }
        else if (n.equals("Q")) {
            rank = 12;
        }
        else if (n.equals("K")) {
            rank = 13;
        }
        else if (n.equals("A")) {
            rank = 14;
        }
        else {
            rank = Integer.parseInt(n);
        }
    }

    public int getrank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public boolean compare(Card c) {
        if (suit.equals(c.getSuit()) || rank == c.getrank()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "[" + getrank() + getSuit() + "]";
    }


}