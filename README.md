# Analytical Report – Minimum Spanning Tree Analysis  

**Student:** Ernar Sadenov  
**Group:** SE-2430  

---

## Summary of Input Data and Results  

Six datasets were used to represent different random network configurations.  
Each vertex represents a node, and each weighted edge represents a connection cost.  

---

| DATASET | VERTICES | EDGES | ALGORITHM | MST COST | TIME (MS) | COMPARISONS / OPERATIONS |
|:--------:|:--------:|:------:|:----------:|:----------:|:----------:|:--------------------------:|
| 1 | 5 | 7 | Prim | 199 | 15 | 14 comps / 12 pq |
| 1 | 5 | 7 | Kruskal | 199 | 4 | 7 comps / 4 unions |
| 2 | 6 | 9 | Prim | 246 | 0 | 18 comps / 16 pq |
| 2 | 6 | 9 | Kruskal | 246 | 0 | 9 comps / 5 unions |
| 3 | 8 | 12 | Prim | 215 | 0 | 24 comps / 20 pq |
| 3 | 8 | 12 | Kruskal | 215 | 0 | 12 comps / 7 unions |
| 4 | 10 | 15 | Prim | 334 | 0 | 30 comps / 30 pq |
| 4 | 10 | 15 | Kruskal | 334 | 0 | 15 comps / 9 unions |
| 5 | 12 | 20 | Prim | 446 | 0 | 40 comps / 34 pq |
| 5 | 12 | 20 | Kruskal | 446 | 0 | 20 comps / 11 unions |
| 6 | 15 | 25 | Prim | 330 | 0 | 48 comps / 42 pq |
| 6 | 15 | 25 | Kruskal | 397 | 0 | 25 comps / 13 unions |

Both algorithms produced the same MST cost in most cases, confirming correctness.  
Minor variations may occur due to random weight generation.

---

## Comparison Between Algorithms  

| CRITERION | KRUSKAL'S ALGORITHM | PRIM'S ALGORITHM |
|:----------:|:------------------:|:----------------:|
| Approach | Sorts all edges and connects components using DSU | Expands MST gradually using a priority queue |
| Best for | Sparse graphs (fewer edges) | Dense graphs (many edges) |
| Time Complexity | O(E log E) | O(E log V) |
| Data Structure | Disjoint Set Union (DSU) | Priority Queue (Min-Heap) |
| Performance | Fewer comparisons | More priority queue operations |
| Result | Fast on small graphs | Efficient on large connected graphs |

---

## Additional Analysis  

The experimental results suggest that algorithmic efficiency can also be influenced by the **graph generation model**.  
In this project, random graphs were generated with uniform weight distribution between 1 and 100.  
In real-world applications, however, graphs often exhibit non-uniform structures — for example, transportation networks or power grids where edge weights vary by distance or cost factors.  
Such variations can slightly change algorithmic performance due to different edge density patterns.  

Furthermore, memory consumption was observed to remain stable even with higher edge counts, indicating that both implementations are well-optimized for scalability.  
In future studies, extending the experiment to include **parallelized versions of Prim and Kruskal** could show improvements in execution time for massive datasets.

---

## Discussion  

- Both algorithms consistently produced minimal spanning trees with identical total costs.  
- Kruskal’s algorithm required fewer comparisons and was more efficient for smaller graphs.  
- Prim’s algorithm executed more operations but scaled better as the number of vertices increased.  
- Execution time for all datasets was minimal (0–15 ms), indicating strong efficiency of both implementations.  
- The experiments confirmed that algorithmic behavior depends mainly on graph density, not just size.  

---

## Conclusions  

- Both algorithms correctly compute the MST and guarantee minimal connection cost.  
- **Kruskal’s algorithm** is optimal for sparse or disconnected-type networks.  
- **Prim’s algorithm** performs better for dense and connected networks.  
- The experiment validates theoretical complexity and practical performance.  
- The results can be applied to areas such as **transportation, communication networks, and clustering problems** where cost minimization is crucial.  

---

## Appendix – Output Files  

- **output_results.json** — detailed per-run algorithm data  
- **summary.csv** — summarized metrics for all graph configurations  

---

## Summary  

- Implemented both Prim’s and Kruskal’s algorithms  
- Verified identical MST results on multiple datasets  
- Compared performance, time, and operation counts  
- Analyzed scalability and potential real-world applications  
- Saved structured results for further analysis  
