import java.util.*;
import java.io.*;

public class Squeezebox {

    public static void main(String[] args) throws FileNotFoundException{

        Scanner kb = new Scanner(new File("squeezebox.dat"));
        String s = kb.next();

        while (!s.equals("#")) {
            ArrayList deckofcards = new ArrayList();
            deckofcards.add(new ArrayDeque());
            ((ArrayDeque)deckofcards.get(0)).add(new Card(s.substring(0,1), s.substring(1)));
            for (int i = 0; i < 51; i++) {
                s = kb.next();
                deckofcards.add(new ArrayDeque());
                ((ArrayDeque)deckofcards.get(deckofcards.size()-1)).add(new Card(s.substring(0,1), s.substring(1)));
                int index = 0;
                while (index < deckofcards.size()) {
                    ArrayDeque current = (ArrayDeque)deckofcards.get(index);
                    if (current == null) {
                        index++;
                        continue;
                    }
                    int threeaway = index - 3;
                    int oneaway = index - 1;
                    if (threeaway > -1 && !current.isEmpty()) {
                        ArrayDeque cardthreeaway = (ArrayDeque)deckofcards.get(threeaway);
                        if (((Card)cardthreeaway.getLast()).compare((Card)current.getLast())) {
                            cardthreeaway.addLast(current.removeLast());
                            if (current.size() == 0){
                                deckofcards.set(index, null);
                                moveLeft(deckofcards, index);
                            }
                            index = 0;
                            continue;
                        }
                    }
                    if (oneaway > -1 && !current.isEmpty()) {
                        ArrayDeque cardoneaway = (ArrayDeque)deckofcards.get(oneaway);
                        if (((Card)cardoneaway.getLast()).compare((Card)current.getLast())) {
                            cardoneaway.addLast(current.removeLast());
                            if (current.size() == 0){
                                deckofcards.set(index, null);
                                moveLeft(deckofcards, index);
                            }
                            index = 0;
                            continue;
                        }
                    }
                    index++;
                }
            }
            System.out.print("\n" + deckofcards.size() + " piles remaining: " );
            for (int i = 0; i < deckofcards.size(); i++) {
                System.out.print(((ArrayDeque)deckofcards.get(i)).size() + " ");
            }
            s = kb.next();
        }
    }

    public static void moveLeft(ArrayList in, int n) {
        for (int i = n; i < in.size()-1; i++) {
            Object temp = in.get(i+1);
            in.set(i, temp);
        }
        in.remove(in.size()-1);
    }

}