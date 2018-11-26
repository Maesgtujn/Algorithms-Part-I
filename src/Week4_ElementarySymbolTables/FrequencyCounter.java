package Week4_ElementarySymbolTables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args){
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> BST = new BST<>();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < minlen) continue;
            if (!BST.contains(word)) BST.put(word, 1);
            else BST.put(word, BST.get(word) + 1);
        }
        String max = "";
        BST.put(max, 0);
        for (String word : BST.keys())
            if (BST.get(word) > BST.get(max))
                max = word;
        StdOut.println(max + " " + BST.get(max));
    }
}
