import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RightTree {

    public int count = 0;

    public static void main(String[] args) throws FileNotFoundException{
        Scanner kb = new Scanner(new File("righttree.dat"));
        int cases = kb.nextInt();
        kb.nextLine();
        for(int i = 1; i < cases+1; i++) {
            char[] line = kb.nextLine().toCharArray();
            boolean[] nums = new boolean[line.length];
            for(int j = 0; j < line.length; j++) {
                if(Character.getNumericValue(line[j]) == 1) {
                    nums[j] = true;
                } else {
                    nums[j] = false;
                }
            }
            RightTree tree = new RightTree();
            System.out.println((tree.isRightTree(nums) ? "Tree " + i + " is a right-tree." : "Tree " + i + " is not a right-tree."));
        }
    }

    public boolean isRightTree(boolean[] nums) {
        for(int i = 1; i < nums.length; i+=2) {
            if(nums[i]) {
                count = 0;
                int left = search(nums, i);
                count = 0;
                int right = 0;
                if(i < nums.length-1 && nums[i+1])
                    right = search(nums, i+1);
                if(left > right)
                    return false;
            }
        }
        return true;
    }

    public int search(boolean[] nums, int index) {
        count++;
        if(2*index+1 < nums.length && nums[2*index+1])
            search(nums, 2 * index + 1);
        if(2*index+2 < nums.length && nums[2*index+2])
            search(nums, 2 * index + 2);
        return count;
    }
}
