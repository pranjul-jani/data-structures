import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectedGraphCycleDetection {

    public static void main(String[] args) {

        DirectedGraphCycleDetection ob = new DirectedGraphCycleDetection();
        HashMap<Integer, List<Integer>> adj = new HashMap<>();


        // number of vertices
        int N = 5;
        for (int i=0;i<N;i++) {
            adj.put(i, new ArrayList<>());
        }

        ob.addEdge(adj,0,1);
        ob.addEdge(adj,1,2);
        ob.addEdge(adj,2,3);
        ob.addEdge(adj,3,4);
        ob.addEdge(adj,0,2);

        if (ob.isCyclicDFS(adj,N)) System.out.println("DFS cycle");
        else System.out.println("DFS not cycle");

        //if (ob.isCyclicBFS(adj,N)) System.out.println("BFS cycle");
        //else System.out.println("BFS not cycle");

    }

    private boolean isCyclicDFS(HashMap<Integer, List<Integer>> adj, int N) {
        // this uses 0,1,2 approach

        int[] visited = new int[N];

        for (int i=0;i<N;i++) {
            if (visited[i] == 0) {
                if (dfs(i, visited, adj)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int current, int[] visited, HashMap<Integer, List<Integer>> adj) {
        if (visited[current] == 1) {
            return true;
        }

        if (visited[current] == 2) {
            return false;
        }

        visited[current] = 1;
        List<Integer> neighbors = adj.getOrDefault(current, new ArrayList<Integer>());

        for(int neighbor : neighbors) {
            if (dfs(neighbor,visited,adj)) {
                return true;
            }
        }

        visited[current] = 2;

        return false;
    }


    private void addEdge(HashMap<Integer, List<Integer>> adj, int src, int des) {
        List<Integer> neighbors = adj.getOrDefault(src, new ArrayList<>());
        neighbors.add(des);
        adj.put(src, neighbors);
    }

}
