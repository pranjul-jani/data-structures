import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class PragmaticPlay implements PPG {

    public synchronized static List<List<String>> groupAnagrams(List<String> list) {

        if(list == null || list.size() == 0) {
            return new ArrayList<>();
        }

        List<List<String>> output = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for(int i=0;i<list.size();i++) {
            String curr = list.get(i);
            int[] countArr = getCountArray(curr);
            String sortedString = getStringFromCountArray(countArr);
            List<String> currList = map.getOrDefault(sortedString, new ArrayList<>());
            currList.add(curr);
            map.put(sortedString, currList);
        }

        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            output.add(entry.getValue());
        }

        return output;

    }

    private static String getStringFromCountArray(int[] countArr) {
        StringBuffer bf = new StringBuffer();
        for (int i=0;i<countArr.length;i++) {
            if (countArr[i] != 0) {
                char curr = (char) ('a' + i);
                int count = countArr[i];
                bf.append(curr);
                bf.append(count);
            }
        }
        return bf.toString();
    }

    private static int[] getCountArray(String curr) {
        int[] count = new int[26];
        for(int i = 0;i<curr.length();i++) {
            char ch = curr.charAt(i);
            count[ch - 'a'] += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        List<String> anagrams = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat", "tab");
        List<List<String>> output = groupAnagrams(anagrams);
        System.out.println(PPG.getGames());

        System.out.println(output);

//        for(int i=0;i<output.size();i++) {
//            for (int j=0;j<output.get(i).size();j++) {
//                System.out.println(output.get(i).get(j) + "->");
//            }
//            System.out.println();
//        }
    }

    @Override
    public int getGameCount() {
        return 1;
    }


}

/*
Given an array of strings, group the anagrams together
input: {"eat", "tea", "tan", "ate", "nat", "bat"}
output: [[eat, tea, ate], [tan, nat], [bat]]

a1e1t1

eat: []
tea: []

aet: List<eat, tea, ate>
ant: List<tan, nat>
abt: List<bat>







 */


