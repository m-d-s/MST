/**
 * Created by msimpson on 8/5/15.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class ParseFile {


    public ArrayList<String[]> read(String fileName) {
        WeightedEdge edgeToAdd;
        CarrierSet forestToAdd;
        String delims = "[ ]+";
        String compare = null;
        String line = null;
        ArrayList<String[]> tokens = new ArrayList<String[]>();
        int length;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {

                tokens.add(line.split(delims));
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
