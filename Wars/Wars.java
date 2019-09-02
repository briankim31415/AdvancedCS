import java.io.File;
import java.util.Scanner;
import java.io.*;

public class Wars {

    public static void main (String[] args)throws FileNotFoundException {


        Scanner kb = new Scanner(new File("input.txt"));



        while(kb.hasNext()) {
            RingBuffer playerOne = new RingBuffer(52);
            RingBuffer playerTwo = new RingBuffer(52);

            for(int i = 0; i < 52; i++) {
                char newCard = kb.next().charAt(0);
                int cardVal;

                switch (newCard) {
                    case 'T':
                        cardVal = 10;
                        break;
                    case 'J':
                        cardVal = 11;
                        break;
                    case 'Q':
                        cardVal = 12;
                        break;
                    case 'K':
                        cardVal = 13;
                        break;
                    case 'A':
                        cardVal = 14;
                        break;
                    default:
                        cardVal = newCard-48;
                }

                if (i < 26) {
                    playerOne.enqueue(cardVal);
                } else {
                    playerTwo.enqueue(cardVal);
                }
            }
            Game g = new Game(playerOne, playerTwo);
            System.out.println(g.Win());
        }
    }

}
/*
import java.util.Scanner;

public class Wars {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        while (kb.hasNext()) {
            RingBuffer playerOne = new RingBuffer(52);
            RingBuffer playerTwo = new RingBuffer(52);

            for (int i = 0; i < 52; i++) {
                char cardIn = kb.next().charAt(0);
                int cardVal = 0;
                switch (cardIn) {
                    case 'T':
                        cardVal = 10;
                        break;
                    case 'J':
                        cardVal = 11;
                        break;
                    case 'Q':
                        cardVal = 12;
                        break;
                    case 'K':
                        cardVal = 13;
                        break;
                    case 'A':
                        cardVal = 14;
                        break;
                    default:
                        cardVal = cardIn - 48;
                }

                if (i < 26) {
                    playerOne.enqueue(cardVal);
                } else {
                    playerTwo.enqueue(cardVal);
                }
            }

            Game p = new Game(playerOne, playerTwo);
            System.out.println(p.Win());
        }
    }
}*/