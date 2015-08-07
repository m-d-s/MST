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
        WeightedEdge smallestEdgeInQueue;
        
        String src, dest;
        CarrierSet U, V;
        ArrayList<WeightedEdge> A = new ArrayList<WeightedEdge>();
        int length, sourceVertexIdx, destVertexIdx, forestSize;

        data.read("testMST.txt");
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
            sourceVertexIdx = indexOf(src, data.forest, forestSize);
            destVertexIdx = indexOf(dest, data.forest, forestSize);
            U = data.forest.get(sourceVertexIdx).find(data.forest.get(sourceVertexIdx));
            V = data.forest.get(destVertexIdx).find(data.forest.get(destVertexIdx));
            if(!U.equals(V)){
                A.add(smallestEdgeInQueue);
                V = V.union(U, V);
            }
        }

        //print(queue);
        print(A);
    }

    public static void buildHeap(ArrayList<WeightedEdge> edgeList){

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

    /**
     * Make this better. Do this somewhere else. Maybe function poniter
     * Maybe class overwrite.
     */
    public static int indexOf(String label, ArrayList Verticies, int length) {
        Iterator<CarrierSet> iter = Verticies.iterator();
        CarrierSet next;
        for(int i =0; i < length; ++i) {
            next = iter.next();
            if(next.compareLabel(label) == true) {
                return i;
            }
        }
        return -1;
    }
}

