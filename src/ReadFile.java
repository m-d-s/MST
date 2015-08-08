/**
 * Created by msimpson on 8/5/15.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class ReadFile {
    private ArrayList<String[]> tokenSet = new ArrayList<String[]>();

    public ReadFile(String fileName){
        this.read(fileName);
    }

    private void read(String fileName) {
        String delims = "[ ]+";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                this.tokenSet.add(line.split(delims));
            }
            bufferedReader.close();
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

    public ArrayList<String[]> getRawData(){
        return this.tokenSet;
    }
}
