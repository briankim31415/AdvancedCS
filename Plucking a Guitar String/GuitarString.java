import java.util.Random;

/**
 * Created by 153144 on 9/11/2017.
 */
public class GuitarString {

    RingBuffer rb;
    Random r = new Random();
    int numberOfTics =  0;
    double decayFactor = 0.994;

    public GuitarString (double frequency) {
        rb = new RingBuffer((int)(44100 / frequency));
    }

    public GuitarString (double[] input) {
        r = new Random();
        rb = new RingBuffer(input.length);
        for (int i = 0; i < input.length; i++) {
            rb.enqueue(input[i]);
        }
    }

    public void pluck() {
        rb.clear();
        for (int x = 0; x < rb.getCapacity(); x++) {
            rb.enqueue(r.nextDouble() - .5);
        }
    }

    public void tic() {
        numberOfTics++;
        rb.enqueue((rb.dequeue() + rb.peek()) / 2 * decayFactor);
    }

    public double sample() {
        return rb.peek();
    }

    public int time() {
        return numberOfTics;
    }

}