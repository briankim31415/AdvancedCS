import java.util.*;
import java.io.*;

public class Rail {

    public static void main (String[] args) throws FileNotFoundException{

        Scanner kb = new Scanner(new File("input.txt"));
        int inSize = kb.nextInt();

        while(inSize != 0) {

            double firstCar = kb.nextDouble();
            double[] cars = new double[inSize];

            while(firstCar != 0) {
                double[] carOut = new double[ inSize];
                cars[0] = firstCar;
                for (int i = 1; i < inSize; i++) {
                    cars[i] = kb.nextDouble();
                }

                int train = 1;

                ArrayStack station = new ArrayStack( inSize +1);

                for (int i = 0; i < inSize; i++) {
                    if (train <= cars[i]) {
                        for (int j = train; j <= cars[i]; j++) {
                            station.push((double) j);
                        }
                        train = (int) cars[i]+1;
                    }
                    carOut[i] = station.pop();
                }

                boolean isTrue = true;
                for (int i = 0; i < inSize; i++) {
                    if (carOut[i] != cars[i]) {
                        isTrue = false;
                    }
                }

                if (isTrue) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
                firstCar = kb.nextDouble();
            }
            inSize = kb.nextInt();
            System.out.println();
        }

    }
}