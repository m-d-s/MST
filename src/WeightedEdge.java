/**
 * Created by msimpson on 8/5/15.
 */
public class WeightedEdge {
    private String source;
    private String destination;
    private int weight;

    public WeightedEdge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getSource() {
        return this.source;
    }

    public String getDest() {
        return this.destination;
    }

    public int getWeight() {
        return this.weight;
    }

    public int print() {
        System.out.println("\t"+this.source + " " +this.destination + " " + this.weight);
        return this.weight;
    }
}
