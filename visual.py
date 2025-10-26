import json
import networkx as nx
import matplotlib.pyplot as plt

# Загружаем данные из JSON
with open("output_results.json") as f:
    data = json.load(f)

for i, d in enumerate(data, start=1):
    V = d["V"]
    mst_edges = d["mstEdges"]

    # создаём исходный граф и MST-граф
    G = nx.Graph()
    for e in mst_edges:
        u, v, w = e["u"], e["v"], e["w"]
        G.add_edge(u, v, weight=w)

    pos = nx.spring_layout(G, seed=42)

    # рёбра MST (подсвечиваем зелёным)
    nx.draw(
        G, pos, with_labels=True, node_color="lightblue", node_size=800,
        font_weight="bold", width=2.5, edge_color="green"
    )

    weights = nx.get_edge_attributes(G, "weight")
    nx.draw_networkx_edge_labels(G, pos, edge_labels=weights)

    plt.title(f"Dataset {i}: MST (V={V}, Cost={d['PrimCost']:.2f})")
    plt.tight_layout()
    plt.savefig(f"dataset_{i}_MST.png", dpi=200)
    plt.clf()

print("✅ All MST images saved as dataset_X_MST.png")
