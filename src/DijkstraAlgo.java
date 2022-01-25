import java.util.*;

public class DijkstraAlgo {

    public static void main(String[] args) {


        DijkstraAlgo ob = new DijkstraAlgo();
        HashMap<Integer, List<int[]>> adj = new HashMap<>();


        // number of vertices
        int N = 5;
        for (int i=0;i<N;i++) {
            adj.put(i, new ArrayList<>());
        }

        ob.addEdge(adj,0,1,2);
        ob.addEdge(adj,1,4,5);
        ob.addEdge(adj,0,3,1);
        ob.addEdge(adj,3,2,3);
        ob.addEdge(adj,1,2,4);
        ob.addEdge(adj,2,4,1);


        int[] output = ob.dijkstra(adj,5,0);

        for (int out : output) {
            System.out.print(out + " ");
        }
    }

    private int[] dijkstra(HashMap<Integer, List<int[]>> adj, int N, int source) {

        int[] dist = new int[N];
        HashSet<Integer> visited = new HashSet<>();

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;

        while(visited.size() < N) {

            int current = getMinDistNode(dist, visited);
            visited.add(current);

            List<int[]> neighbors = adj.getOrDefault(current, new ArrayList<>());

            for (int[] neighbor: neighbors) {

                if (!visited.contains(neighbor[0]) && dist[neighbor[0]] > dist[current] + neighbor[1]) {
                    dist[neighbor[0]] = dist[current] + neighbor[1];
                }
            }
        }
        return dist;
    }

    private int getMinDistNode(int[] dist, HashSet<Integer> visited) {

        int current = -1;
        int min = Integer.MAX_VALUE;

        for(int i=0;i<dist.length;i++) {
            if (!visited.contains(i) && dist[i] < min) {
                min = dist[i];
                current = i;
            }
        }
        return current;
    }

    private void addEdge(HashMap<Integer, List<int[]>> adj, int src, int des, int wt) {

        List<int[]> neighbors = adj.getOrDefault(src, new ArrayList<>());
        neighbors.add(new int[]{des, wt});
        adj.put(src, neighbors);

    }
}
