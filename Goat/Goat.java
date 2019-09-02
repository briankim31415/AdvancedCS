public class Goat implements Comparable<Goat>{
    int[] cycle;
    int index;

    public Goat(int[] cycle) {
        this.cycle = cycle;
    }

    public void nextDay() {
        index = (index + 1) % cycle.length;
    }

    public int output() {
        return cycle[index];
    }

    public int compareTo(Goat otherGoat) {
        return this.output() - otherGoat.output();
    }
}
