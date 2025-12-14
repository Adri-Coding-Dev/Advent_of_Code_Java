package utils_2025;

public class Utils_Day08 {

    /**
     * Finds the representative (root) of the set containing element i.
     * Implements path compression to optimize future operations.
     *
     * @param i      -> the element whose set is being found
     * @param parent -> the parent array representing the forest
     * @return -> the root representative of the set containing i
     */
    public static int find(int i, int[] parent) {
        if (parent[i] != i) {
            parent[i] = find(parent[i], parent);
        }
        return parent[i];
    }
}