import java.util.*;
import java.io.*;

/**
 * Created by 198495 on 8/31/2017.
 */
public class Parenthesis {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("input.txt");
        Scanner kb = new Scanner(file);

        int rows = kb.nextInt();
        String rowString = kb.nextLine();

        for(int i = 0; i < rows; i++) {
            rowString = kb.nextLine();
            ArrayStack stack = new ArrayStack(10);
            char[] chars = rowString.toCharArray();

            boolean isTrue = true;

            if(chars.length % 2 == 1) {
                System.out.println("No");
                continue;
            }

            for(int j = 0; j < chars.length; j++) {
                if(chars[j] == '(') {
                    stack.push(1);
                }
                if(chars[j] == '[') {
                    stack.push(2);
                }
                if(chars[j] == ')') {
                    double top = stack.pop();
                    if(top == 2 || top == 0) {
                        System.out.println("No");
                        isTrue = false;
                        break;
                    }
                }
                if(chars[j] == ']') {
                    double top = stack.pop();
                    if(top == 1 || top == 0) {
                        System.out.println("No");
                        isTrue = false;
                        break;
                    }
                }
            }

            if(isTrue) {
                System.out.println("Yes");
            }
        }
    }

}
