/**
 * Created by msimpson on 8/5/15.
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TestCarrierSet();
        TestKruskals();
    }

    public static void print(ArrayList<WeightedEdge> A) {
        int length = A.size();
        System.out.println("Minimum Spanning Tree size: " + length);
        for(int i = 0; i < length; ++i) {
            A.get(i).print();
        }
    }
    public static void TestCarrierSet(){
        System.out.println("Carrier-Set Test");

        CarrierSet a,b,c,d,e, test;
        a = new CarrierSet("a");
        b = new CarrierSet("b");
        c = new CarrierSet("c");
        d = new CarrierSet("d");
        e = new CarrierSet("e");

        if(a.equals(a.find())) {
            System.out.println("Find Test 1 Passed");
        } else {
            System.out.println("***Find Test 1 FAILED***");
        }

        if(!b.equals((c.find()))) {
            System.out.println("Find Test 2 Passed");
        } else {
            System.out.println("***Find Test 2 FAILED***");
        }

        a.union(b);
        if(b.equals(a.find())) {
            System.out.println("Union Test 1 Passed");
        } else {
            System.out.println("***Union Test 1 FAILED***");
        }

        b.union(c);
        if(c.equals(a.find())) {
            System.out.println("Union Test 2 Passed");
        } else {
            System.out.println("***Union Test 2 FAILED***");
        }

        d.union(e);
        if(!e.equals(c.find())) {
            System.out.println("Union Test 3 Passed");
        } else {
            System.out.println("***Union Test 3 FAILED***");
        }

        d.union(a);
        if(e.equals(a.find())) {

            System.out.println("Union Test 4 Passed");
        } else {
            System.out.println("***Union Test 4 FAILED***");
        }

        System.out.println(a.find().getLabel());
    }

    public static void TestKruskals() {
        ArrayList<WeightedEdge> A;
        String[] fileNames = {"city-pairs.txt", "someEdges.txt", "testMST.txt", "list.txt", "empty.txt"};
        int numFiles = fileNames.length;
        System.out.println("Kruskal's Minimum Spanning Trees Tests\n");

        for(int i = 0; i < numFiles; ++i){
            System.out.println("Testing Ouput for: " + fileNames[i]);
            A = new Kruskals(fileNames[i]).getMinimumSpanningTree();
            print(A);
        }

    }

}

