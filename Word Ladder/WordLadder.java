import java.util.*;
import java.io.*;

public class WordLadder {
    public static void main (String[] args) throws FileNotFoundException{
        Scanner dictionaryIn = new Scanner(new File("dictionary.txt"));
        Scanner input = new Scanner(new File("input.txt"));

        HashSet<String> dictionary = new HashSet<String>();

        while (dictionaryIn.hasNext()) {                            //Create dictionary HashSet
            dictionary.add(dictionaryIn.nextLine());
        }

        while (input.hasNext()){                            //Run while loop for each par of inputs
            HashSet<String> usedWords = new HashSet<String>();

            boolean go = true;
            boolean working = false;

            LinkedList result = new LinkedList();
            LinkedList queue = new LinkedList();

            String in = input.next();
            String out = input.next();

            if (!dictionary.contains(in) || !dictionary.contains(out) || in.length() != out.length()){      //Eliminates impossible words
                go = false;
            } else if (in.equals(out)){             //If in is already equal to out, set result to in
                result.push(in);
                go = false;
                working = true;
            }

            usedWords.add(in);
            String[] oneDiffFirst = oneLetterDiff(in);      //Creates String[] with all possible words that are one letter different

            for (int i = 0; i < oneDiffFirst.length; i++){              //First check of words because stack only contains firstWord; also sees if in/out are only 1 letter apart
                if (!usedWords.contains(oneDiffFirst[i]) && dictionary.contains(oneDiffFirst[i])){
                    usedWords.add(oneDiffFirst[i]);         //Adds word into usedWords HashSet

                    LinkedList stack = new LinkedList();
                    stack.push(in);                 //Pushes word into stack
                    stack.push(oneDiffFirst[i]);    //Pushes one letter different word into stack

                    queue.enqueue(stack);           //Enqueues stack into queue

                    if (oneDiffFirst[i].equals(out)){
                        result = stack.reverse();

                        go = false;
                        working = true;

                        break;
                    }
                }
            }

            String top = "";        //Initializes top string that gets string at the top of the queue
            if (queue.size() <= 0){
                go = false;
            }

            if (go){
                top = (String)((LinkedList) queue.getHead().get()).getHead().get();
            }


            int whileCount = 0;
            while (!top.equals(out) && go){                         //Checks for other possible combinations
                LinkedList dequeue = (LinkedList) (queue.dequeue().get());
                String[] oneDiff = oneLetterDiff(top);//oneLetterDiff(top);}

                for (int i = 0; i < oneDiff.length; i++){
                    if (!usedWords.contains(oneDiff[i]) && dictionary.contains(oneDiff[i])){
                        usedWords.add(oneDiff[i]);              //Adds word into usedWords HashSet

                        LinkedList stack = dequeue.duplicate();         //Creates copy of stack
                        stack.push(oneDiff[i]);         //Pushes one letter different word into stack

                        queue.enqueue(stack);           //Enqueues stack into queue

                        if (oneDiff[i].equals(out)){        //Checks if top is equal to the desired word
                            result = stack.reverse();
                            go = false;
                            working = true;
                            break;
                        }
                    }
                }
                top = ((String)((LinkedList) queue.getHead().get()).getHead().get());
            }
            if (!working){              //Printing results
                System.out.println("There is no word ladder between " + in + " and " + out + "!");
            } else if (working){
                System.out.println(result);
            }
        }
    }

    public static String[] oneLetterDiff(String in){        //Creates String[] that contains all the possible letter combinations for the word
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String[] out = new String[25 * in.length()];
        int count = 0;

        for (int i = 0; i < in.length(); i++){
            for (int j = 0; j < 26; j++){
                String s = in.substring(0, i);
                s += letters.substring(j, j+1);
                s += in.substring(i+1);
                if (!s.equals(in)) {
                    out[count] = s;
                    count++;
                }
            }
        }

        return out;
    }
}