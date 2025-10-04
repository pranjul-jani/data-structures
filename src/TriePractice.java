import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean endOfWord;

    TrieNode() {
        this.children = new HashMap<>();
        this.endOfWord = false;
    }

}
public class TriePractice {
    private final TrieNode root;

    public TriePractice() {
        root = new TrieNode();
    }

    public static void main(String args) {

    }

}
