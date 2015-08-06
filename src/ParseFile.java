/**
 * Created by msimpson on 8/5/15.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class ParseFile {
    public ArrayList<WeightedEdge> edgeList = new ArrayList<WeightedEdge>();
    public ArrayList<CarrierSet> forest = new ArrayList<CarrierSet>();

    public void read(String fileName) {
        WeightedEdge edgeToAdd;
        CarrierSet forestToAdd;
        String delims = "[ ]+";
        String compare = null;
        String line = null;
        String[] tokens;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                tokens = line.split(delims);
                edgeToAdd = new WeightedEdge(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                edgeList.add(edgeToAdd);
                if(forest.size() > 0) {
                    compare = forest.get(forest.size()-1).getLabel();
                }
                if(!tokens[0].equals(compare)) {
                        forestToAdd = new CarrierSet(tokens[0]);
                        forest.add(forestToAdd);
                }
            }
            bufferedReader.close();
            this.display(forest);
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

    public void display(ArrayList C) {
        Iterator<CarrierSet> iter = C.iterator();
        CarrierSet next;
        while(iter.hasNext()) {
            next = iter.next();
            next.print();
        }
        System.out.println();
    }
}
