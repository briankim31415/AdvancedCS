public class RingBuffer {

    int first, last, size, capacity;
    double queue[];

    public RingBuffer(int maxCapacity) {
        queue = new double[maxCapacity];
        first = 0;
        last = 0;
        size = 0;

        capacity = maxCapacity;
    }

    public int size() {
        if(size == 0) {
            return 0;
        } else {
            return size;
        }
    }

    public boolean isEmpty() {
        if(size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if(queue.length == size) {
            return true;
        } else {
            return false;
        }
    }

    public void enqueue(double x) {
        //System.out.println("Enqueued " + queue[last]);

        if(isFull()) {
            System.out.println("Queue is full");
        } else {
            queue[(first + size) % getCapacity()] = x;
            size++;
        }

    }

    public double dequeue() {
        if(isEmpty()) {
            return 0;
        } else {

            //System.out.println("Going to dequeue " + queue[first] + " " + first);

            double firstDouble = queue[first];
            size --;
            if(first == capacity-1) {
                first = 0;
            } else {
                first ++;
            }
            return firstDouble;
        }
    }

    public double peek() {
        if(size == 0){
            return 0;
        } else {
            return queue[first];
        }
    }

    /*public String toString() {
        String s = "";
        for (int i = 0; i < queue.length; i++) {
            s += queue[i] + " ";
        }
        return s;
    }*/

    public String toString() {
        String intoString = "";
        if(first < last) {
            for (int i = first; i < last; i++) {
                intoString += queue[i] + " ";
            }
        } else if(last < first) {
            for (int i = first; i < capacity; i++) {
                intoString += queue[i] + " ";
            }
            for (int j = 0; j < last; j++) {
                intoString += queue[j] + " ";
            }
        }

        return intoString;
    }

    public void clear() {
        first = 0;
        last = 0;
        size = 0;
    }

    public int getCapacity() {
        return capacity;
    }
}