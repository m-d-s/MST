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
        WhightedEdge smallestEdgeInQueue;
        String src, dest;
        CarrierSet U, V;
        ArrayList A = new ArrayList<WeigntedEdge>();
        int length, sourceVertexIdx, destVertexIdx, forestSize;

        data.read("city-pairs.txt");
        queue = new PriorityQueue<WeightedEdge>(data.edgeList.size(), comparator);
        length = data.edgeList.size();
        forestSize = data.forest.size();
        for(int i = 0; i < length; ++i) {
            queue.add(data.edgeList.get(i));
        }
        
        while(queue.peek() != null) {
            smallestEdgeInQueue = queue.poll();
            src = smallestEdgeInQueue.getSource();
            dest = smallestEdgeInQueue.getDest();
            sourceVertexIdx = indexOf(src, data.forest, forestSize)
            destVertexIdx = indexOf(dest, data.forest, forestSize)
            U = data.forest[sourceVertex].find(data.forest[sourceVertex]);
            V = data.forest[destVertex].find(data.forest[destVertex]);
            if(U.equals(V) == false){
                A.add(smallestEdgeInQueue);
                V = V.union(U, V);
            }
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

    /**
     * Make this better. Do this somewhere else. Maybe function poniter
     * Maybe class overwrite.
     */
    public static int indexOf(String label, ArrayList Verticies, int length) {
        Iterator<CarrierSet> iter = C.iterator();
        CarrierSet next;
        while(iter.hasNext()) {
            next = iter.next();
            if(next.compareLabel(label) == true) {
                return i;
            }
        }
        return -1;
    }
}

