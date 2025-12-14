package utils_2025;

public class Utils_Day08 {
    public static int find(int i, int[] parent) {
        if (parent[i] != i) {
            parent[i] = find(parent[i], parent);
        }
        return parent[i];
    }
}
