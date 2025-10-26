import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MSTTest {

    @Test
    public void testMSTCostEquality() {
        Graph g = Graph.randomGraph(6, 1.0, 20.0, new Random(1));
        MSTResult kruskal = KruskalMST.run(g);
        MSTResult prim = PrimMST.run(g);
        assertEquals(Math.round(kruskal.totalCost * 1000),
                Math.round(prim.totalCost * 1000),
                "Prim and Kruskal should produce the same MST cost");
    }

    @Test
    public void testEdgeCountIsVMinusOne() {
        Graph g = Graph.randomGraph(8, 0.7, 30.0, new Random(2));
        MSTResult res = KruskalMST.run(g);
        if (res.mstEdges.size() > 0) {
            assertEquals(g.V - 1, res.mstEdges.size(),
                    "MST must contain exactly V-1 edges");
        }
    }

    @Test
    public void testDisconnectedGraph() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 2.0);
        MSTResult kr = KruskalMST.run(g);
        assertTrue(kr.mstEdges.size() < g.V - 1,
                "Disconnected graph should not have a full MST");
    }

    @Test
    public void testPerformanceNonNegative() {
        Graph g = Graph.randomGraph(10, 0.5, 100.0, new Random(3));
        MSTResult kr = KruskalMST.run(g);
        MSTResult pr = PrimMST.run(g);
        assertTrue(kr.timeMs >= 0 && pr.timeMs >= 0);
        assertTrue(kr.comparisons >= 0 && pr.comparisons >= 0);
    }

    @Test
    public void testReproducibility() {
        Random rnd1 = new Random(5);
        Random rnd2 = new Random(5);
        Graph g1 = Graph.randomGraph(7, 0.8, 40.0, rnd1);
        Graph g2 = Graph.randomGraph(7, 0.8, 40.0, rnd2);
        MSTResult kr1 = KruskalMST.run(g1);
        MSTResult kr2 = KruskalMST.run(g2);
        assertEquals(Math.round(kr1.totalCost * 1000),
                Math.round(kr2.totalCost * 1000));
    }
}
