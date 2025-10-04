import java.util.HashSet;
import java.util.Set;


/*

 */

public class IndMoney {

    public static void main(String[] args) {
        String s = minWindow("bdab", "ab");
        System.out.println(s + "->" + s.length());
    }

    private static String minWindow(String s, String t) {
        if(s == null || t == null || s.isEmpty() || t.isEmpty() || t.length() > s.length()) {
            return "";
        }

        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();

        int[] count = new int[26];

        Set<Character> set = populateCountArray(ch2, count);

        int minStart = Integer.MIN_VALUE;
        int minEnd = Integer.MAX_VALUE;

        int currStart = -1;

        for(int i=0;i<s.length();i++) {
            char curr = ch1[i];
            if (currStart == -1 && !set.contains(curr)) {
                continue;
            }

            if (set.contains(curr)) {
                if (currStart == -1) {
                    currStart = i;
                }
                int index = (int) (curr - 'a');
                count[index] -= 1;
                if(checkCountArray(count)) {
                    int currEnd = i;


                    if(minStart == Integer.MIN_VALUE || (currEnd - currStart + 1 < minEnd - minStart + 1)) {
                        minStart = currStart;
                        minEnd = currEnd;
                    }

                    populateCountArray(ch2, count);
                    currStart = -1;
                    currEnd = -1;
                }
            }

        }


        return minStart == Integer.MIN_VALUE ? "" : s.substring(minStart, minEnd+1);



    }

    private static boolean checkCountArray(int[] count) {
        for(int current : count) {
            if(current != 0) {
                return false;
            }
        }
        return true;
    }

    private static Set<Character> populateCountArray(char[] ch, int[] count) {
        Set<Character> set = new HashSet<>();
        for (int i=0;i<ch.length;i++) {
            int curr = ch[i] - 'a';
            count[curr] += 1;
            set.add(ch[i]);
        }

        return set;

    }
}







/*


Ip: x -> gameStart
100


cassandra

123
456
789


123
456
789
3


123_456
456_789
123_789




String s1 = "praznjuljxyani"
String s2 = "naj"

o(26*m)
O(n)

aznj

jxyan

Size m and n
















*/
