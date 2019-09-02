import java.util.*;
import java.io.*;

public class HashLab {
    public static void main(String[] args) throws FileNotFoundException {
        String[] scanners = {"Large Data Set.txt", "Successful Search.txt", "Unsuccessful Search.txt"};
        File newFile = new File("H:/HashTable/test.csv");
        PrintWriter printWriter = new PrintWriter(newFile);
        for(String file: scanners) {
            Scanner kb = new Scanner(new File("H:/HashTable/input/" + file));
            ArrayList<String> arrayList = new ArrayList<>();
            while(kb.hasNext()) {
                arrayList.add(kb.nextLine());
            }
            printWriter.println("File: " + file);
            printWriter.println("Collision type: Linear Probing");
            printWriter.println("\n");
            printWriter.println("Load Factor,Collisions,Time");

            double[] loadFactors = {0.1, 0.5, 0.8, 0.9, 1.0};
            for(int i = 0; i < loadFactors.length; i++) {
                int index = (int) (arrayList.size() / loadFactors[i]);
                boolean isPrime = false;
                while(!isPrime) {
                    for(int j=2;j<=index/2;j++)
                    {
                        int temp=index%j;
                        if(temp!=0)
                        {
                            isPrime = true;
                            break;
                        }
                    }
                    index++;
                }
                HashTable hashTable = new HashTable(index);
                double avgTime = 0;
                double avgCol = 0;
                for(String line: arrayList) {
                    /*String[] split = line.split("\t");
                    Name n = new Name(split[0], split[1]);
                    Person p = new Person(n, split[3], split[2], Long.parseLong(split[4]));*/
                    String[] split = line.split(" ");
                    int n = Integer.parseInt(split[0]);
                    String value = split[1] + " " + split[2];
                    hashTable.put(n,value);
                    avgTime = avgTime + hashTable.getTime();
                    avgCol = avgCol + hashTable.getCollisionCounter();
                }
                System.out.println(file + " " + loadFactors[i] + "\nTotal time: " + avgTime);
                avgTime /= index;
                System.out.println("Average Time: " + avgTime + "\nTotal Collisions: " + avgCol);
                avgCol /= index;
                System.out.println("Average Collisions: " + avgCol + "\n-------------------------");
                printWriter.println(loadFactors[i] + "," + avgCol + "," + avgTime);
            }
            printWriter.println("\n--------------------------");
        }
        printWriter.close();
    }
}