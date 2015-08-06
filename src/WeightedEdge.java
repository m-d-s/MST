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

    public void print() {
        System.out.println(this.source);
        System.out.println(this.destination);
        System.out.println(this.weight);
        System.out.println();
    }
}
