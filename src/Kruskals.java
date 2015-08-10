import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by msimpson on 8/7/15.
 */
public class Kruskals {
    private ArrayList<CarrierSet> forest;
    private PriorityQueue<WeightedEdge> weightedEdgeHeap;
    ArrayList<WeightedEdge> minimumSpanningTree;

    public Kruskals(String fileName) {
        Comparator<WeightedEdge> comparator = new EdgeWeightComparator();
        ReadFile rawData = new ReadFile(fileName);
        this.forest = new ArrayList<CarrierSet>();
        this.weightedEdgeHeap = new PriorityQueue<WeightedEdge>(comparator);
        this.minimumSpanningTree = new ArrayList<WeightedEdge>();
        ParseData(rawData.getRawData());
        findMinimumSpanningTree();
    }

    public ArrayList<WeightedEdge> getMinimumSpanningTree() {
        return this.minimumSpanningTree;
    }

    private void findMinimumSpanningTree() {
        WeightedEdge smallestEdgeInQueue;
        CarrierSet U, V;
        // Loop while there are edges left in the queue
        while(weightedEdgeHeap.peek() != null) {
            // dequeue the shortest edge
            smallestEdgeInQueue = weightedEdgeHeap.poll();
            // identify the canonical element of U and V's respective forests
            U = this.getCanonicalElement(smallestEdgeInQueue.getSource());
            V = this.getCanonicalElement(smallestEdgeInQueue.getDest());
            // If U and V are in different forests
            if(!U.equals(V)){
                // Add the edge to the minimum spanning tree
                this.minimumSpanningTree.add(smallestEdgeInQueue);
                // Union the two forests
                U.union(V);
            }
        }
    }

    private void ParseData(ArrayList<String[]> tokenSet) {
        WeightedEdge edgeToAdd;
        String[] tokens;
        String compare = null;
        CarrierSet forestToAdd;
        int length = tokenSet.size();

        for(int i = 0; i < length; ++i){
            tokens = tokenSet.get(i);
            edgeToAdd = new WeightedEdge(tokens[0]
                                        ,tokens[1]
                                        ,Integer.parseInt(tokens[2]));

            this.weightedEdgeHeap.add(edgeToAdd);

            if(forest.size() > 0){
                compare = forest.get(forest.size()-1).getLabel();
            }

            if(!tokens[0].equals(compare)) {
                forestToAdd = new CarrierSet(tokens[0]);
                forest.add(forestToAdd);
            }
        }

    }

    private int indexOf(String label) {
        Iterator<CarrierSet> iter = this.forest.iterator();
        int length;
        CarrierSet next;
        length = this.forest.size();
        for (int i = 0; i < length; ++i) {
            next = iter.next();
            if (next.compareLabel(label)) {
                return i;
            }
        }
        return -1;
    }

    private CarrierSet getCanonicalElement(String label) {
        int vertexIdx = indexOf(label);
        return forest.get(vertexIdx).find();
    }

//  private void displayForest() {
//      Iterator<CarrierSet> iter = this.forest.iterator();
//      CarrierSet next;
//      while(iter.hasNext()) {
//          next = iter.next();
//          next.print();
//      }
//      System.out.println();
//  }

//  private void printHeap() {
//      WeightedEdge next;
//      Iterator<WeightedEdge> iter = this.weightedEdgeHeap.iterator();
//      int i = 0;
//      while(iter.hasNext()) {
//          next = iter.next();
//          next.print();
//      }
//      System.out.println();
//  }
}
