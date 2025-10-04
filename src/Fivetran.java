import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int val;
    Node next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

}

class CustomHashMap {
    HashMap<Character, Node> arr;
    Node sequence;

    CustomHashMap(Node seq) {
        this.arr = new HashMap<>();
        this.sequence = seq;
    }


}






public class Fivetran {

    private final int a = 0;

    public static void main(String[] args) {
        Node array = new Node(-1, -1);
        CustomHashMap customHashMap = new CustomHashMap(array);

        Node n1 = new Node(2, 2);
        customHashMap.arr.put('c', n1);

        array.next = n1;

        Node n2 = new Node(0, 0);
        customHashMap.arr.put('a', n2);
        array.next.next = n2;


        Node n3 = new Node(1, 1);
        customHashMap.arr.put('b', n3);
        array.next.next.next = n3;


        while(array != null) {
            System.out.println(array.key);
            array = array.next;
        }




    }

}

/*
c a b
 */
