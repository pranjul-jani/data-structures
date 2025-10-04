import java.util.*;

class Logs {
    int timestamp;
    String value;

    Logs(int timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

}

public class A {

    List<Logs> sortLogs(List<Logs> input) {
        List<Logs> output = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return output;
        }

        PriorityQueue<Logs> minHeap = new PriorityQueue<>((a,b) -> {
            if (a.timestamp == b.timestamp) {
                if (a.value.length() == b.value.length()) {
                    return a.value.compareToIgnoreCase(b.value);
                } else {
                    return a.value.length() - b.value.length();
                }
            } else {
                return a.timestamp - b.timestamp;
            }
        });

        for (int i=0;i<input.size();i++) {
            minHeap.add(input.get(i));
        }

        while(minHeap.size() > 0) {
            output.add(minHeap.poll());
        }

        return output;


    }

    public static void main(String[] args) {
        A ob = new A();

        int[][] edges = {
                {0, 1, 8},
                {0, 3, 5},
                {1, 0, 8},
                {1, 2, 6},
                {3, 0, 5},
                {3, 2, 7},
                {2, 1, 6},
                {2, 3, 7},
                {2, 4, 7},
                {4, 2, 7}
        };
        System.out.println(ob.graphProblem(edges, 0, 4, 2));

    }

    private Map<Integer, Integer> graphProblem(int[][] graphs, int sourceNode, int noOfNodes, int depth) {
        if (graphs == null || graphs.length == 0 || graphs[0].length == 0) {
            return new HashMap<>();
        }

        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int i=0;i< graphs.length;i++) {
            int src = graphs[i][0];
            int des = graphs[i][1];
            int wt = graphs[i][2];

            System.out.println(src + " " + des + " "+ wt);

            List<int[]> list = adj.getOrDefault(src, new ArrayList<>());
            list.add(new int[]{des, wt});
            adj.put(src, list);

            List<int[]> list1 = adj.getOrDefault(des, new ArrayList<>());
            list1.add(new int[]{src, wt});
            adj.put(src, list1);
        }

        Map<Integer, Integer> map = new HashMap<>();

        dfs(sourceNode, sourceNode, 1, 0, depth, adj, map, new HashSet<Integer>());

        Set<Integer> set = new HashSet<>();

        List<int[]> list = adj.getOrDefault(sourceNode, new ArrayList<>());

        for (int i=0;i<list.size();i++) {
            int des = list.get(i)[0];
            int wt = list.get(i)[1];

            map.remove(des);

        }

        return map;

    }

    private void dfs(int sourceNode, int parent, int score, int k, int depth, Map<Integer, List<int[]>> adj, Map<Integer, Integer> map, Set<Integer> visited) {


        if (visited.contains(sourceNode)) {
            return;
        }
        visited.add(sourceNode);
        if (k == depth+1) {
            map.put(sourceNode, score);
        }

        List<int[]> nei = adj.getOrDefault(sourceNode, new ArrayList<>());

        for (int i=0;i<nei.size();i++) {
           int des = nei.get(i)[0];
           int wt = nei.get(i)[1];

           System.out.println(des + "->" + wt);

           if (des != parent) {
               dfs(des, sourceNode, score*wt, k+1, depth, adj, map, visited);
           }

        }

    }


}




/*

graph = {
    'A': [('B', 0.8), ('D', 0.5)],
    'B': [('A', 0.8), ('C', 0.6)],
    'D': [('A', 0.5), ('C', 0.7)],
    'C': [('B', 0.6), ('D', 0.7), ('E', 0.7)]
    'E': [('C', 0.7)]
}

m=2, n=4
# Expected: [('C', 0.83)]  (0.48 + 0.35)








 */