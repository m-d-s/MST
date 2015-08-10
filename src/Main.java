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
        int length = A.size(),
            totalWeight = 0;

        System.out.println("Minimum Spanning Tree Edge List:");
        for(int i = 0; i < length; ++i) {
            totalWeight += A.get(i).print();
        }
        System.out.println("Minimum Spanning Tree size: " + length);
        System.out.println("Total Weight of minimum spanning tree: " + totalWeight + "\n\n");
    }
    public static void TestCarrierSet(){
        System.out.println("Carrier-Set Test");

        CarrierSet a,b,c,d,e, test;
        a = new CarrierSet("a");
        b = new CarrierSet("b");
        c = new CarrierSet("c");
        d = new CarrierSet("d");
        e = new CarrierSet("e");
        // a is the canonical element of the set to which a belongs
        if(a.equals(a.find())) {
            System.out.println("Find Test 1 Passed");
        } else {
            System.out.println("***Find Test 1 FAILED***");
        }
        // the canonical element of the set to which c belongs is not b
        if(!b.equals((c.find()))) {
            System.out.println("Find Test 2 Passed");
        } else {
            System.out.println("***Find Test 2 FAILED***");
        }

        a.union(b);
        // b is the canonical element of the set to which a belongs
        if(b.equals(a.find())) {
            System.out.println("Union Test 1 Passed");
        } else {
            System.out.println("***Union Test 1 FAILED***");
        }
        // c is the canonical element of the set to which a belongs
        b.union(c);
        if(c.equals(a.find())) {
            System.out.println("Union Test 2 Passed");
        } else {
            System.out.println("***Union Test 2 FAILED***");
        }
        // e is not the canonical element of the set to which c belongs
        d.union(e);
        if(!e.equals(c.find())) {
            System.out.println("Union Test 3 Passed");
        } else {
            System.out.println("***Union Test 3 FAILED***");
        }
        // c is the canonical element of the set to which a belongs
        d.union(a);
        if(c.equals(a.find())) {

            System.out.println("Union Test 4 Passed");
        } else {
            System.out.println("***Union Test 4 FAILED***");
        }
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

