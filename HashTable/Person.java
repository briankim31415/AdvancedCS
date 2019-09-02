/**
 * Created by 198495 on 12/7/2017.
 */
public class Person {
    Name name;
    String email;
    String phoneNumber;
    long creditCard;

    public Person() {
        name = new Name();
        email = "";
        phoneNumber = "";
        creditCard = 0;
    }

    public Person(Name nameIn, String emailIn, String phoneIn, long creditIn) {
        name = nameIn;
        email = emailIn;
        phoneNumber = phoneIn;
        creditCard = creditIn;
    }
}
