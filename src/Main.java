/**
 * Created by msimpson on 8/5/15.
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<WeightedEdge> A = new Kruskals("testMST.txt").getMinimumSpanningTree();
        print(A);
    }

    public static void print(ArrayList<WeightedEdge> A) {
        int length = A.size();
        System.out.println("Minimum Spanning Tree size: " + length);
        for(int i = 0; i < length; ++i) {
            A.get(i).print();
        }
    }

}

