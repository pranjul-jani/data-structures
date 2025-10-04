import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyClass {
    static class Node {
        int val;
        int rank;
        Node parent;

        Node(int val, int rank) {
            this.val = val;
            this.rank = rank;
        }
    }
    static Map<Integer, Node> map = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("Hello World!");

        makeSet(1);
        makeSet(2);
        makeSet(3);
        makeSet(4);
        makeSet(5);
        makeSet(6);
        makeSet(7);

        union(1,2);
        union(2,3);

        System.out.println(Objects.requireNonNull(findSet(2)).val);
        System.out.println(Objects.requireNonNull(findSet(3)).val);
        System.out.println(Objects.requireNonNull(findSet(1)).val);
        System.out.println();

        union(4,5);
        union(6,7);
        union(5,6);

        System.out.println(Objects.requireNonNull(findSet(4)).val);
        System.out.println(Objects.requireNonNull(findSet(5)).val);
        System.out.println(Objects.requireNonNull(findSet(7)).val);
        System.out.println();

        union(3,7);

        System.out.println(Objects.requireNonNull(findSet(2)).val);
        System.out.println(Objects.requireNonNull(findSet(5)).val);

    }

    private static void makeSet(int data) {
        if(!map.containsKey(data)) {
            Node current = new Node(data, 1);
            current.parent = current;
            map.put(data, current);
        }
    }

    public static boolean union(int A, int B) {
        if(A == B) {
            return false;
        }

        if(!map.containsKey(A) || !map.containsKey(B)) {
            return false;
        }

        Node nodeA = map.get(A);
        Node nodeB = map.get(B);

        Node parentA = findSet(A);
        Node parentB = findSet(B);

        if(parentA == null || parentB == null || parentA == parentB) {
            return false;
        }

        if(parentA.rank >= parentB.rank) {
            parentA.rank = parentA.rank == parentB.rank ? parentA.rank + 1 : parentA.rank;
            parentB.parent = parentA;
        } else {
            parentA.parent = parentB;
        }
        return true;
    }

    private static Node findSet(int val) {
        if(!map.containsKey(val))
            return null;

        return findSet(map.get(val));
    }

    private static Node findSet(Node node) {
        Node parent = node.parent;
        if(node == parent) {
            return parent;
        }
        node.parent = findSet(parent);
        return node.parent;
    }
}