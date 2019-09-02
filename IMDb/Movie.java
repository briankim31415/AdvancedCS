/**
 * Created by 198495 on 10/5/2017.
 */
public class Movie {
    int date;
    String title;
    LinkedList actors;
    LinkedList directors;

    public Movie() {
        date = 0;
        title = "";
        actors = null;
        directors = null;
    }

    public Movie(int dateIn, String titleIn, LinkedList actorsIn, LinkedList directorsIn) {
        date = dateIn;
        title = titleIn;
        actors = actorsIn;
        directors = directorsIn;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int dateIn) {
        this.date = dateIn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titleIn) {
        this.title = titleIn;
    }

    public LinkedList getActors() {
        return actors;
    }

    public void setActors(LinkedList actorsIn) {
        this.actors = actorsIn;
    }

    public LinkedList getDirectors() {
        return directors;
    }

    public void setDirectors(LinkedList directorsIn) {
        this.directors = directorsIn;
    }

    public String toString() {
        String output = "\nTitle: " + getTitle() + "\nDate: " + getDate() + "\nActors: " + actors.toString() + "\nDirectors: " + directors.toString();

        return output;
    }


}

