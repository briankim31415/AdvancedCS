import java.util.*;
import java.io.*;

public class RPNCalc {

    public static void main (String[] args) throws FileNotFoundException{

        File file = new File(System.getProperty("user.dir") + "\\src\\input.txt");
        Scanner kb = new Scanner(file);
        ArrayStack stack = new ArrayStack(10);
        String x;

        while(kb.hasNext()) {
            x = kb.next();
            if(!x.equals("/") && !x.equals("*") && !x.equals("+") && !x.equals("-")) {
                stack.push(Double.parseDouble(x));
            } else {
                double secondTerm = stack.pop();
                double firstTerm = stack.pop();
                if (x.equals("/")){
                    stack.push(firstTerm/secondTerm);
                } if(x.equals("*")) {
                    stack.push(firstTerm*secondTerm);
                } if(x.equals("+")) {
                    stack.push(firstTerm+secondTerm);
                } if(x.equals("-")) {
                    stack.push(firstTerm-secondTerm);
                }
            }
        }
        System.out.println("Answer: " + stack);
    }
}