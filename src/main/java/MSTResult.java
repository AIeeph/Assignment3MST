import java.util.*;

public class MSTResult {
    public List<Edge> mstEdges = new ArrayList<>();
    public double totalCost = 0;
    public long timeMs = 0;
    public long comparisons = 0;
    public long pqOps = 0;
    public long unions = 0;
    public int vertices, edges;

    @Override
    public String toString() {
        return String.format("Cost=%.2f, Time=%dms, Edges=%d, V=%d, comps=%d, pq=%d, unions=%d",
                totalCost, timeMs, mstEdges.size(), vertices, comparisons, pqOps, unions);
    }
}
