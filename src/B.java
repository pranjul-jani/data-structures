import java.util.HashMap;

public class B {

    public static void main(String[] args) {
        System.out.println(maxNonRepeatingSubstring("abcdbef"));
    }

    private static String maxNonRepeatingSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] ch = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        int maxLength = 0;
        int start = 0;
        int bestStart = 0;
        map.put(ch[0], 0);

        for(int i=1;i<ch.length;i++) {
            char curr = ch[i];
            if (map.containsKey(curr)) {
                if (maxLength < i-start-1) {
                    maxLength = i - start;
                    bestStart = start;
                }
                start = map.get(curr) + 1;
                map.put(curr, i);
            } else {
                map.put(curr, i);
                if (maxLength > i-start) {
                    maxLength = Math.max(maxLength, i-start);
                    bestStart = start;
                }
            }
        }

        return s.substring(bestStart, maxLength+start+1);
    }

}

/*
babcdabef
 */
