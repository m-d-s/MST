/**
 * Created by msimpson on 8/5/15.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<WeightedEdge> A = kruskals();
        //print(queue);
        print(A);
    }

    public static ArrayList<WeightedEdge> kruskals() {
        ArrayList<WeightedEdge> A = new ArrayList<WeightedEdge>();
        ParseFile data = new ParseFile("city-pairs.txt");
        PriorityQueue<WeightedEdge> queue;
        WeightedEdge smallestEdgeInQueue;
        CarrierSet[] UV;

        queue = data.getHeap();

        while(queue.peek() != null) {
            //get the shortest edge
            smallestEdgeInQueue = queue.poll();
            UV = data.getCanonicalElements(smallestEdgeInQueue);
            if(!UV[0].equals(UV[1])){
                A.add(smallestEdgeInQueue);
                UV[1].union(UV[0], UV[1]);
            }
        }
        return A;
    }



  
    public static void print(PriorityQueue<WeightedEdge> C) {
        WeightedEdge next;
        Iterator<WeightedEdge> iter = C.iterator();
        int i = 0;
        while(!C.isEmpty()) {
            next = iter.next();
            next.print();
        }
        System.out.println();
    }

    public static void print(ArrayList<WeightedEdge> A) {
        int length = A.size();
        System.out.println("Minimum Spanning Tree size: " + length);
        for(int i = 0; i < length; ++i) {
            A.get(i).print();
        }
    }

}

