import java.util.*;

public class PrimMST {
    public static MSTResult run(Graph g) {
        MSTResult res = new MSTResult();
        res.vertices = g.V;
        res.edges = g.edges.size();

        if (g.V == 0) return res;

        boolean[] used = new boolean[g.V];
        double[] key = new double[g.V];
        int[] parent = new int[g.V];
        Arrays.fill(key, Double.POSITIVE_INFINITY);
        Arrays.fill(parent, -1);
        key[0] = 0;

        long comps = 0, pqOps = 0;
        long start = System.nanoTime();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        pq.add(new int[]{0, 0}); pqOps++;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            pqOps++;
            int v = cur[1];
            if (used[v]) continue;
            used[v] = true;

            if (parent[v] != -1) {
                for (Edge e : g.adj.get(v)) {
                    if ((e.u == v && e.v == parent[v]) || (e.v == v && e.u == parent[v])) {
                        res.mstEdges.add(e);
                        res.totalCost += e.w;
                        break;
                    }
                }
            }

            for (Edge e : g.adj.get(v)) {
                int to = (e.u == v) ? e.v : e.u;
                comps++;
                if (!used[to] && e.w < key[to]) {
                    key[to] = e.w;
                    parent[to] = v;
                    pq.add(new int[]{(int)(e.w * 1000), to});
                    pqOps++;
                }
            }
        }

        long end = System.nanoTime();
        res.timeMs = (end - start) / 1_000_000;
        res.comparisons = comps;
        res.pqOps = pqOps;
        return res;
    }
}
