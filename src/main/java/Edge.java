public class Edge {
    public int u, v;
    public double w;

    public Edge(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString() {
        return String.format("%d - %d (%.2f)", u, v, w);
    }
}
