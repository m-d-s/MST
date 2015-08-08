import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by msimpson on 8/5/15.
 */
public class CarrierSet {
    private String label;
    private CarrierSet parent;

    /**
     * This is the init operation. The canonical element in the carrier set will
     * always have it's parent reference set to null. Since upon initialization this
     * is the only element in the set, its parent is set to null
     */
    public CarrierSet(String label) {
        this.label = label;
        this.parent = null;
    }

    /**
     * Locates the canonical element of carrier set U. Also reorders the structure so that
     * each elements parent reference, excluding the canonical's parent reference, points to the canonical
     * element.
     */
    public CarrierSet find() {
        CarrierSet U = this,
                   next = this.parent,
                   prev = this;

        try {
            // find the canonical element
            while (U.parent != null) {
                U = U.parent;
            }
            // set every other parent reference in the structure to null
            while (next != null) {
                prev.parent = U;
                prev = next;
                next = next.parent;
            }

        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }
        return U;
    }


    /**
     * Finds the canonical elements of both U and V. Then refers the parent reference of U to V,
     * thus creating the union of U and V.
     */
    public void union(CarrierSet V) {
        this.parent = V;
    }

    public void print() {
        System.out.println(this.label);
    }

    public String getLabel() {
        return this.label;
    }

    public Boolean compareLabel(String toCompare) {
        return this.label.equals(toCompare);
    }
}
