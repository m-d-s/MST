/**
 * Created by msimpson on 8/5/15.
 */
import java.util.Comparator;
public class EdgeWeightComparator implements Comparator<WeightedEdge> {
    @Override
    public int compare(WeightedEdge x, WeightedEdge y) {
        if (x.getWeight() < y.getWeight()) {
            return -1;
        }
        if (x.getWeight() > y.getWeight()) {
            return 1;
        }
        return 0;
    }
}
