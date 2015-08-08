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

    public Kruskals(String fileName) {
        Comparator<WeightedEdge> comparator = new EdgeWeightComparator();
        ReadFile rawData = new ReadFile(fileName);
        forest = new ArrayList<CarrierSet>();
        weightedEdgeHeap = new PriorityQueue<WeightedEdge>(comparator);
        ParseData(rawData.getRawData());
        this.displayForest();
    }

    public ArrayList<WeightedEdge> minimumSpanningTree() {
        ArrayList<WeightedEdge> A = new ArrayList<WeightedEdge>();
        WeightedEdge smallestEdgeInQueue;
        CarrierSet[] UV;

        while(weightedEdgeHeap.peek() != null) {
            //get the shortest edge
            smallestEdgeInQueue = weightedEdgeHeap.poll();
            UV = this.getCanonicalElements(smallestEdgeInQueue);
            if(!UV[0].equals(UV[1])){
                A.add(smallestEdgeInQueue);
                UV[1].union(UV[0], UV[1]);
            }
        }
        return A;
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

    private CarrierSet[] getCanonicalElements(WeightedEdge edge) {
        String src, dest;
        int sourceVertexIdx, destVertexIdx;
        CarrierSet [] UV = new CarrierSet[2];

        src = edge.getSource();
        dest = edge.getDest();

        sourceVertexIdx = indexOf(src);
        destVertexIdx = indexOf(dest);

        UV[0] = forest.get(sourceVertexIdx).find(forest.get(sourceVertexIdx));
        UV[1] = forest.get(destVertexIdx).find(forest.get(destVertexIdx));

        return UV;
    }

    private void displayForest() {
        Iterator<CarrierSet> iter = this.forest.iterator();
        CarrierSet next;
        while(iter.hasNext()) {
            next = iter.next();
            next.print();
        }
        System.out.println();
    }

//    private void printHeap() {
//        WeightedEdge next;
//        Iterator<WeightedEdge> iter = this.weightedEdgeHeap.iterator();
//        int i = 0;
//        while(iter.hasNext()) {
//            next = iter.next();
//            next.print();
//        }
//        System.out.println();
//    }
}
