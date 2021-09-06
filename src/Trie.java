import java.util.HashMap;

public class Trie {

    private class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        Trie ob = new Trie();

        //adding words
        ob.addWord("abc");
        ob.addWord("abgl");
        ob.addWord("cdf");
        ob.addWord("abcd");
        ob.addWord("lmn");

        //searching for whole words
        System.out.println(ob.searchWord("lmn"));
        System.out.println(ob.searchWord("abc"));
        System.out.println(ob.searchWord("abcd"));
        System.out.println(ob.searchWord("abg"));
        System.out.println(ob.searchWord("lm"));
        System.out.println(ob.searchWord("abcgh"));
        System.out.println(ob.searchWord("adf"));

        //search using prefix
        System.out.println(ob.searchPrefix("abd"));

        //searching for words with a certain prefix

        System.out.println();

        System.out.println("delete1");
        //delete whole words
        //ob.deleteWord("ab");
        ob.deleteWord("abcd");
        System.out.println(ob.searchWord("abcd"));
        System.out.println(ob.searchWord("abc"));
        System.out.println();

        System.out.println("delete2");
        ob.deleteWord("abgl");
        System.out.println(ob.searchWord("abgl"));
        System.out.println(ob.searchWord("abc"));
        ob.deleteWord("abc");
        System.out.println(ob.searchWord("abc"));


    }

    private void addWord(String word) {

        TrieNode current = root;
        for(int i=0;i<word.length();i++) {
            TrieNode next = current.children.get(word.charAt(i));
            if(next == null) {
                next = new TrieNode();
                current.children.put(word.charAt(i),next);
            }
            current = next;
        }

        current.endOfWord = true;
    }

    private boolean searchWord(String word) {
        TrieNode current = root;
        for (int i=0;i<word.length();i++) {
            TrieNode next = current.children.get(word.charAt(i));
            if (next == null) {
                return false;
            }
            current = next;
        }
        return current.endOfWord;
    }

    private boolean searchPrefix(String prefix) {
        TrieNode current = root;
        for(int i=0;i<prefix.length();i++) {
            TrieNode next = current.children.get(prefix.charAt(i));
            if (next == null) {
                return false;
            }
            current = next;
        }
        return true;
    }

    private void deleteWord(String word) {
        delete(root,word,0);
    }

    private boolean deleteWord(TrieNode current, String word, int index) {
        if (index == word.length()) {

            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            return current.children.size() == 0;
        }
        char curr = word.charAt(index);
        TrieNode next = current.children.get(curr);
        // if word is not present then it cannot be deleted
        if (next == null) {
            return false;
        }
        boolean safeToDelete = deleteWord(next,word,index+1);
        if (safeToDelete) {
            current.children.put(curr,null);
            return current.children.size() == 0;
        }
        return false;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if currrent.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        //if true is returned then delete the mapping of character and trienode reference from map.
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);

            //if a word is ending there then do not delete it
            if(current.endOfWord)
                return false;

            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        }
        return false;
    }
}
