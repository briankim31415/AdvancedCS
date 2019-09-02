import java.util.*;
import java.io.*;

public class Heap {
    Goat[] arr;
    int size, nextIndex, max;

    public Heap(int max) {
        arr = new Goat[max];
        size = nextIndex = 0;
        this.max = max;
    }

    public int cycle() {
        if(size == 0)
            return 0;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < size; i++)
            nums.add(arr[i].cycle.length);
        int returnVal = 1;
        while(nums.size() > 0) {
            Collections.sort(nums);
            int smallest = nums.get(0);
            nums.remove(0);
            if(smallest == 1)
                continue;
            returnVal *= smallest;
            for (int i = 0; i < nums.size(); i++) {
                if(nums.get(i) % smallest == 0)
                    nums.set(i, nums.get(i)/smallest);
            }
        }
        return returnVal;
    }

    public boolean goatKilled() {
        if(size == 0|| arr[0].output() == arr[1].output() || arr[0].output() == arr[2].output())
            return false;
        size--;
        swap(0, nextIndex-1);
        nextIndex--;
        return true;
    }

    public void nextDay() {
        Goat[] tempArray = Arrays.copyOfRange(arr, 0, nextIndex);
        arr = new Goat[arr.length];
        size = nextIndex = 0;
        for(Goat goat : tempArray) {
            goat.nextDay();
            addGoat(goat);
        }
    }

    public void addGoat(Goat goat) {
        size++;
        int currentIndex = nextIndex;
        nextIndex++;
        arr[currentIndex] = goat;
        while(arr[currentIndex].compareTo(arr[(currentIndex-1)/2]) < 0) {
            swap(currentIndex, (currentIndex-1)/2);
            currentIndex = (currentIndex-1)/2;
        }
    }

    private void swap(int a, int b) {
        Goat temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
