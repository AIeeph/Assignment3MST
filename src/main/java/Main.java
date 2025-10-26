import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Random rnd = new Random();
        List<Map<String, Object>> results = new ArrayList<>();


        int[][] configs = {
                {5, 7},
                {6, 9},
                {8, 12},
                {10, 15},
                {12, 20},
                {15, 25}
        };

        for (int[] cfg : configs) {
            int V = cfg[0], E = cfg[1];
            Graph g = Graph.generateRandom(V, E, rnd);

            MSTResult prim = PrimMST.run(g);
            MSTResult kruskal = KruskalMST.run(g);

            // если MST пустой — пропускаем (чтобы не было пустых картинок)
            if (prim.mstEdges.isEmpty()) continue;

            Map<String, Object> row = new LinkedHashMap<>();
            row.put("V", V);
            row.put("E", E);
            row.put("PrimCost", prim.totalCost);
            row.put("KruskalCost", kruskal.totalCost);
            row.put("PrimTime", prim.timeMs);
            row.put("KruskalTime", kruskal.timeMs);
            row.put("mstEdges", prim.mstEdges);
            results.add(row);

            System.out.printf("Graph(V=%d,E=%d): Prim=%s | Kruskal=%s%n",
                    V, E, prim, kruskal);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fw = new FileWriter("output_results.json")) {
            gson.toJson(results, fw);
        }

        try (PrintWriter pw = new PrintWriter("summary.csv")) {
            pw.println("V,E,PrimCost,PrimTime,KruskalCost,KruskalTime");
            for (Map<String, Object> r : results) {
                pw.printf("%d,%d,%.2f,%d,%.2f,%d%n",
                        r.get("V"), r.get("E"),
                        r.get("PrimCost"), r.get("PrimTime"),
                        r.get("KruskalCost"), r.get("KruskalTime"));
            }
        }

        System.out.println("\n✅ Results saved to output_results.json and summary.csv");
    }
}
