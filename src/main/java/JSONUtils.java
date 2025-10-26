import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Utility class for reading and writing JSON files used in MST experiments.
 * Handles serialization of results (Prim vs Kruskal) into JSON format.
 */
public class JSONUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Writes a list of MSTResult objects into a JSON file.
     *
     * @param results  list of results to serialize
     * @param filename path to output JSON file
     * @throws IOException if writing fails
     */
    public static void writeResults(List<MSTResult> results, String filename) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(results, writer);
        }
    }
}
