import java.util.HashMap;

public class DisjointSet {

    HashMap<Long, Node> map = new HashMap<>();

    private class Node {
        long data;
        Node parent;
        int rank;

        Node(long data, int rank) {
            this.data = data;
            this.rank = rank;
        }
    }

    public static void main(String[] args) {
        DisjointSet ob = new DisjointSet();

        ob.makeSet(1);
        ob.makeSet(2);
        ob.makeSet(3);
        ob.makeSet(4);
        ob.makeSet(5);
        ob.makeSet(6);
        ob.makeSet(7);

        ob.union(1,2);
        ob.union(2,3);

        System.out.println(ob.findSet(2));
        System.out.println(ob.findSet(3));
        System.out.println(ob.findSet(1));
        System.out.println();

        ob.union(4,5);
        ob.union(6,7);
        ob.union(5,6);

        System.out.println(ob.findSet(4));
        System.out.println(ob.findSet(5));
        System.out.println(ob.findSet(7));
        System.out.println();

        ob.union(3,7);

        System.out.println(ob.findSet(2));
        System.out.println(ob.findSet(5));

    }

    private void makeSet(long data) {
        Node node = new Node(data,0);
        node.parent = node;
        map.put(data, node);
    }

    private void union(long dataA, long dataB) {

        if (dataA == dataB) {
            return;
        }

        Node parentA = findSet(map.get(dataA));
        Node parentB = findSet(map.get(dataB));

        if (parentA == parentB) {
            return;
        }

        if (parentA.rank >= parentB.rank) {
            parentA.rank = parentA.rank == parentB.rank ? parentA.rank + 1 : parentA.rank;
            parentB.parent = parentA;
        }
        else {
            parentA.parent = parentB;
        }
    }

    private long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    private Node findSet(Node node) {
        Node parent = node.parent;
        if (node == parent) {
            return parent;
        }
        node.parent = findSet(parent);
        return node.parent;
    }
}
