import java.util.ArrayList;

public class Q4 {
    public int largestTour(ArrayList<ArrayList<Integer>> adjList) {
        int V = adjList.size();
        int maxNodes = 0;
        for (int i = 0; i < V; i++) {
            int nodes = dfs(i, new boolean[V], adjList);
            maxNodes = Math.max(maxNodes, nodes);
        }
        if (maxNodes == 1) {
            return 1;
        }
        return maxNodes - 1;
    }

    private int dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = true;
        int count = 1;
        for (int neighbor : adjList.get(node)) {
            System.out.println("Node " + node + " neighbor: " + neighbor);
            if (!visited[neighbor]) {
                count += dfs(neighbor, visited, adjList);
            }
        }
        System.out.println("---------------count: " + count);
        return count;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            adjList.add(new ArrayList<>());
        }
        
        adjList.get(4).add(5);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(3).add(4);
        adjList.get(0).add(1);

        Q4 q2 = new Q4();
        System.out.println(q2.largestTour(adjList));
    }
}
