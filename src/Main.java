/**
 * Created by msimpson on 8/5/15.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Comparator<WeightedEdge> comparator = new EdgeWeightComparator();
        PriorityQueue<WeightedEdge> queue;
        ParseFile data = new ParseFile();
        int length;

        data.read("city-pairs.txt");
        queue = new PriorityQueue<WeightedEdge>(data.edgeList.size(), comparator);
        length = data.edgeList.size();
        for(int i = 0; i < length; ++i) {
            queue.add(data.edgeList.get(i));
        }

        display(queue);

    }
    public static void display(PriorityQueue<WeightedEdge> C) {
        WeightedEdge next;
        while(!C.isEmpty()) {
            next = C.poll();
            next.print();
        }
        System.out.println();
    }
}


