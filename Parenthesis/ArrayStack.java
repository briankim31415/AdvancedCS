public class ArrayStack implements IStack{

    double stack[];
    int NumberOfElements = 0;

    public ArrayStack (int size) {
        stack = new double[size];
    }

    public void push(double d) {
        if(!isFull()) {
            stack[NumberOfElements] = d;
            NumberOfElements++;
        }
    }

    public double pop() {
        if(!isEmpty()) {

            double newTop = stack[NumberOfElements-1];
            stack[NumberOfElements] = 0;
            NumberOfElements--;
            return newTop;
        }
        return 0;
    }

    public double peek() {
        if(!isEmpty()) {
            return stack[NumberOfElements-1];
        }
        return 0;
    }

    public int size() {
        return stack.length;
    }

    public boolean isEmpty() {
        if(NumberOfElements == 0)
            return true;
        return false;
    }

    public boolean isFull() {
        if(NumberOfElements == stack.length-1) {
            return true;
        }
        return false;
    }

    public void clear() {
        NumberOfElements = 0;
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < NumberOfElements; i++) {
            s += stack[i] + " ";
        }
        return s;
    }
}
