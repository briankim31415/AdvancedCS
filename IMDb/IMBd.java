import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 198495 on 10/11/2017.
 */
public class IMBd {
    public static void main(String[] args) throws FileNotFoundException{
        LinkedList actorList = new LinkedList();
        LinkedList movieList = new LinkedList();

        Scanner actorScan = new Scanner(new File("actors.txt"));
        Scanner movieScan = new Scanner(new File("movies.txt"));

        //Traverse Actors
        while(actorScan.hasNext()) {
            actorList.add(actorScan.nextLine());
        }


        //Traverse Movies
        while(movieScan.hasNext()) {
            String line = movieScan.nextLine();
            String dateIn = line.substring(0,4);
            int date = Integer.parseInt(dateIn);
            String title = line.substring(5,38).trim();

            String actorsIn = line.substring(38,84);
            String directorsIn = line.substring(89,line.length());

            String[] actors = actorsIn.split(", ");
            String[] directors = directorsIn.split(", ");

            LinkedList movieActors = new LinkedList();
            LinkedList movieDirectors = new LinkedList();

            for(int i = 0; i < actors.length; i++) {
                movieActors.add(actors[i].trim());
            }
            for(int i = 0; i < directors.length; i++) {
                movieDirectors.add(directors[i].trim());
            }

            movieList.add(new Movie(date, title, movieActors, movieDirectors));
        }

        //Test for same
        Node currentMovie = movieList.get(0);
        while(currentMovie != null) {
            LinkedList movieActors = ((Movie) currentMovie.get()).getActors();
            Node actorHead = movieActors.get(0);
            Node actorListHead = actorList.get(0);
            while(actorHead != null) {
                while(actorListHead != null) {
                    if(actorHead.get().toString().trim().equals(actorListHead.get().toString().trim())){
                        System.out.println("\n" + actorListHead.get().toString().trim());
                        System.out.println(currentMovie);
                    }
                    actorListHead = actorListHead.getNextPtr();
                }
                actorHead = actorHead.getNextPtr();
            }
            currentMovie = currentMovie.getNextPtr();
        }
    }
}
