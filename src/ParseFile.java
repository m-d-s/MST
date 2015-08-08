/**
 * Created by msimpson on 8/5/15.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class ParseFile {
    public ArrayList<CarrierSet> forest;
    public ArrayList<WeightedEdge> edgeList;
    public PriorityQueue<WeightedEdge> weightedEdgeHeap;

    public ParseFile(String fileName){
        forest = new ArrayList<CarrierSet>();
        edgeList = new ArrayList<WeightedEdge>();
        weightedEdgeHeap = new PriorityQueue<WeightedEdge>();
        this.read(fileName);
        this.buildHeap();

    }


    private void read(String fileName) {
        WeightedEdge edgeToAdd;
        CarrierSet forestToAdd;
        String delims = "[ ]+";
        String compare = null;
        String line = null;
        String[] tokens;
        int length;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {

                tokens = line.split(delims);
                edgeToAdd = new WeightedEdge(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                edgeList.add(edgeToAdd);

                if(forest.size() > 0){
                    compare = forest.get(forest.size()-1).getLabel();
                }

                if(!tokens[0].equals(compare)) {
                    forestToAdd = new CarrierSet(tokens[0]);
                    forest.add(forestToAdd);
                }

            }
            bufferedReader.close();
            this.displayForest();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    private void buildHeap() {
        Comparator<WeightedEdge> comparator = new EdgeWeightComparator();
        int length;

        this.weightedEdgeHeap = new PriorityQueue<WeightedEdge>(this.edgeList.size(), comparator);
        length = this.edgeList.size();

        for(int i = 0; i < length; ++i) {
            this.weightedEdgeHeap.add(this.edgeList.get(i));
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

    public CarrierSet[] getCanonicalElements(WeightedEdge edge) {
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

    public void displayForest() {
        Iterator<CarrierSet> iter = this.forest.iterator();
        CarrierSet next;
        while(iter.hasNext()) {
            next = iter.next();
            next.print();
        }
        System.out.println();
    }

    public PriorityQueue<WeightedEdge> getHeap(){
        return this.weightedEdgeHeap;
    }
}
