import java.util.*;
import java.io.*;

/**
 * Created by 198495 on 3/1/2018.
 */
public class Compression {
    public static HashMap<Character, String> bitCodes = new HashMap<>();
    public static void main(String[] args) throws IOException {
        ArrayList<String> listFiles = new ArrayList<>();
        /*File[] files = new File("H:\\Huffman\\Text Files").listFiles();
        for (File file : files)
            if (file.isFile())
                listFiles.add(file.getName());*/
        listFiles.add("input.txt");


        for (String file: listFiles) {
            BitInputStream bitInputStream = new BitInputStream(new File("H:\\Huffman\\Text Files\\" + file));
            int input = 0;
            int[] inputFreq = new int[256];
            while(true) {
                try {
                    input = bitInputStream.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(input == -1) break;
                inputFreq[input]++;
            }

            PriorityQueue<Node> frequencies = new PriorityQueue<>();

            for (int i = 0; i < inputFreq.length; i++)
                if(inputFreq[i] != 0)
                    frequencies.add(new Node((char)i, inputFreq[i]));

            while(frequencies.size() > 1) {
                frequencies.add(new Node(frequencies.poll(), frequencies.poll()));
            }

            BitOutputStream bitOutputStream = new BitOutputStream("H:\\Huffman\\Output Files\\" + file + "_Compressed.txt");
            bitInputStream.reset();
            input = 0;
            int length = 0;
            addToHashMap(frequencies.peek(), "");
            while(true) {
                try {
                    input = bitInputStream.read();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                if(input == -1) break;
                String code = bitCodes.get((char) input);
                System.out.println(code);
                length += code.length();
                bitOutputStream.writeBits(code.length(), Integer.parseInt(code));
            }
            bitOutputStream.close();

            BitInputStream compressedInput = new BitInputStream("H:\\Huffman\\Output Files\\" + file + "_Compressed.txt");
            PrintWriter writer = new PrintWriter("H:\\Huffman\\Output Files\\" + file + "_Decompressed.txt");
            Node current = frequencies.peek();
            for (int i = 0; i < length; i++) {
                current = (compressedInput.readBits(1) == 0 ? current.left : current.right);
                if(current.isLeaf()) {
                    writer.write(current.value);
                    current = frequencies.peek();
                }
            }
            writer.close();
        }
    }

    public static void addToHashMap(Node root, String code) {
        if(root.isLeaf()) {
            bitCodes.put(root.value, code);
            return;
        }
        addToHashMap(root.left, code + "0");
        addToHashMap(root.right, code + "1");
    }
}

class Node implements Comparable<Node>{
    Node left, right;
    char value;
    int freq;

    public Node(char c, int f) {
        value = c;
        freq = f;
        left = right = null;
    }

    public Node(Node l, Node r) {
        left = l;
        right = r;
        freq = left.freq + right.freq;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public int compareTo(Node n) {
        return freq - n.freq;
    }
}
