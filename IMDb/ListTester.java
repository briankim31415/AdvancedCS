/**
 * Created by 198495 on 9/27/2017.
 */
import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class ListTester {
    public static void main(String[] args) throws FileNotFoundException{
        LinkedList movies = new LinkedList();
        Scanner kb = new Scanner(new File("movies.txt"));

        while(kb.hasNext()) {
            String line = kb.nextLine();
            String dateIn = line.substring(0,4);
            int date = Integer.parseInt(dateIn);
            String title = line.substring(5,38).trim();

            String actorsIn = line.substring(38,84);
            String directorsIn = line.substring(89,line.length());

            String[] actors = actorsIn.split(", ");
            String[] directors = directorsIn.split(", ");

            LinkedList actorList = new LinkedList();
            LinkedList directorList = new LinkedList();

            for(int i = 0; i < actors.length; i++) {
                actorList.add(actors[i].trim());
            }
            for(int i = 0; i < directors.length; i++) {
                directorList.add(directors[i].trim());
            }

            movies.add(new Movie(date, title, actorList, directorList));
        }

        Node current = movies.get(0);

        while(current != null) {
            System.out.println(current);

            current = current.getNextPtr();
        }

        System.out.println("\nHead of list is " + ((Movie)(movies.get(0).get())).getTitle());
    }
}
