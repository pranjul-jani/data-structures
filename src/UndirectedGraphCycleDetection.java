import java.util.*;

public class UndirectedGraphCycleDetection {


    public static void main(String[] args) {

        UndirectedGraphCycleDetection ob = new UndirectedGraphCycleDetection();
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
        //ob.addEdge(adj,0,0);

        if (ob.isCyclicDFS(adj,N)) System.out.println("DFS cycle");
        else System.out.println("DFS not cycle");

        if (ob.isCyclicBFS(adj,N)) System.out.println("BFS cycle");
        else System.out.println("BFS not cycle");

    }

    private void addEdge(HashMap<Integer, List<Integer>> adj, int src, int des) {

        List<Integer> neighbors = adj.getOrDefault(src, new ArrayList<>());
        neighbors.add(des);
        adj.put(src, neighbors);

        neighbors = adj.getOrDefault(des, new ArrayList<>());
        neighbors.add(src);
        adj.put(des,neighbors);

    }

    private boolean isCyclicDFS(HashMap<Integer, List<Integer>> adj, int N) {

        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, visited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int current, int parent, boolean[] visited, HashMap<Integer, List<Integer>> adj) {
        if (!visited[current]) {
            visited[current] = true;
            List<Integer> neighbors = adj.getOrDefault(current, new ArrayList<>());

            for (int neighbor : neighbors) {
                if (neighbor != parent) {
                    if (dfs(neighbor, current, visited, adj)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    private boolean isCyclicBFS(HashMap<Integer, List<Integer>> adj, int N) {
        boolean[] visited = new boolean[N];

        for(int i=0;i<N;i++) {
            if (!visited[i]) {
                if (bfs(i,-1,visited,adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(int current, int parent, boolean[] visited, HashMap<Integer, List<Integer>> adj) {

        if (!visited[current]) {
            visited[current] = true;

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{current, parent});

            while (!q.isEmpty()) {
                int size = q.size();

                while(size-- > 0) {
                    int[] curr = q.poll();
                    List<Integer> neighbors = adj.getOrDefault(curr[0], new ArrayList<>());

                    for (int neighbor: neighbors) {

                        if (neighbor == curr[1]) {
                            continue;
                        }
                        if (neighbor != curr[1] && visited[neighbor]) {
                            return true;
                        }
                        else {
                            visited[neighbor] = true;
                            q.add(new int[]{neighbor,curr[0]});
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }


}
