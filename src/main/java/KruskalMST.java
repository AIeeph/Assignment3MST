import java.util.*;

public class KruskalMST {
    private static class DSU {
        int[] p, r;
        DSU(int n) { p = new int[n]; r = new int[n]; for (int i = 0; i < n; i++) p[i] = i; }
        int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
        boolean unite(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            if (r[a] < r[b]) { int t = a; a = b; b = t; }
            p[b] = a;
            if (r[a] == r[b]) r[a]++;
            return true;
        }
    }

    public static MSTResult run(Graph g) {
        MSTResult res = new MSTResult();
        res.vertices = g.V;
        res.edges = g.edges.size();

        long comps = 0, unions = 0;
        long start = System.nanoTime();

        List<Edge> sorted = new ArrayList<>(g.edges);
        sorted.sort(Comparator.comparingDouble(e -> e.w));

        DSU dsu = new DSU(g.V);
        for (Edge e : sorted) {
            comps++;
            if (dsu.unite(e.u, e.v)) {
                res.mstEdges.add(e);
                res.totalCost += e.w;
                unions++;
            }
        }

        long end = System.nanoTime();
        res.timeMs = (end - start) / 1_000_000;
        res.comparisons = comps;
        res.unions = unions;
        return res;
    }
}
