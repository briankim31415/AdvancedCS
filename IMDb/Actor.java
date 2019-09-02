/**
 * Created by 198495 on 9/25/2017.
 */
public class Actor {

    String name;

    public Actor() {
        name = "";
     }

     public Actor(String s) {
         this.name = s;
     }

     public String getName() {
        return name;
     }

     public void setName(String s) {
        this.name = s;
     }

     public String toString() {
         return name + " ";
     }
}
