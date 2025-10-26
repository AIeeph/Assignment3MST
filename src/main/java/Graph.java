

import java.util.*;
import com.google.gson.annotations.SerializedName;

public class Graph {
    public int V;
    public List<Edge> edges;
    public List<List<Edge>> adj;

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, double w) {
        Edge e = new Edge(u, v, w);
        edges.add(e);
        adj.get(u).add(e);
        adj.get(v).add(e);
    }

    public static Graph generateRandom(int V, int E, Random rnd) {
        Graph g = new Graph(V);
        Set<String> added = new HashSet<>();

        // ограничим E максимумом возможных рёбер
        int maxEdges = V * (V - 1) / 2;
        E = Math.min(E, maxEdges);

        while (g.edges.size() < E) {
            int u = rnd.nextInt(V);
            int v = rnd.nextInt(V);
            if (u == v) continue;
            String key = u < v ? u + "-" + v : v + "-" + u;
            if (added.contains(key)) continue;
            added.add(key);
            double w = 1 + rnd.nextInt(100);
            g.addEdge(u, v, w);
        }

        return g;
    }

    // (старый метод тоже можно оставить, если где-то используется)
    public static Graph randomGraph(int V, double density, double maxWeight, Random rnd) {
        Graph g = new Graph(V);
        for (int u = 0; u < V; u++) {
            for (int v = u + 1; v < V; v++) {
                if (rnd.nextDouble() < density) {
                    g.addEdge(u, v, 1 + rnd.nextInt((int) maxWeight));
                }
            }
        }
        return g;
    }
}
