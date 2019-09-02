/**
 * Created by 198495 on 12/7/2017.
 */
public class Name {
    String first;
    String last;
    int hash;

    public Name() {
        first = "";
        last = "";
    }

    public Name(String a, String b) {
        first = a;
        first = a;
        last = b;
    }

    public int hashCode() {
        int hash = 1;
        for(int i = 0; i < first.length(); i++) {
            hash *= first.charAt(i);
        }
        for(int i = 0; i < last.length(); i++) {
            hash *= last.charAt(i);
        }
        return Math.abs(hash);
    }

    public int getHash() {
        return hash;
    }

    public boolean equals(Name n) {
        return (first.equals(n.first) && last.equals(n.last));
    }

    public boolean is(Name n) {return true;}
}
