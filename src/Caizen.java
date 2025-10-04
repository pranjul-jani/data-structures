import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Caizen {

    static class Bracket {
        Map<Integer, Character> map = new HashMap<Integer, Character>();
        Set<Character> openBrackets = new HashSet<Character>();
        Set<Character> closedBrackets = new HashSet<Character>();
        AtomicInteger counter;

        Bracket() {
            map = new HashMap<>();
            openBrackets = new HashSet<>();
            closedBrackets = new HashSet<>();
            counter = new AtomicInteger(0);
        }

        public int addBracketPair(Character openBracket, Character closedBracket) {
            int currentVal = counter.incrementAndGet();
            map.put(currentVal, openBracket);
            openBrackets.add(openBracket);
            closedBrackets.add(closedBracket);
            return currentVal;
        }


    }

    public static void main(String[] args) {
        String s = " [] ";

        Bracket bracket = new Bracket();

        bracket.addBracketPair('(', ')');
        bracket.addBracketPair('{', '}');
        bracket.addBracketPair('[', ']');
        bracket.addBracketPair('7', '8');



        System.out.println(checkValidParam(s, bracket));

    }

    private static boolean checkValidParam(String s, Bracket bracket) {
        if (s == null || s.isEmpty() || s.trim().length() % 2 != 0) {
            return false;
        }

        int[] count = new int[bracket.map.size() + 1];

        Set<Character> openBracketsSet = bracket.openBrackets;
        Set<Character> closedBracketsSet = bracket.closedBrackets;


        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);

            if (openBracketsSet.contains(curr)) {
                count[bracket.map.get(curr)] += 1;
            } else if (closedBracketsSet.contains(curr)) {
                count[bracket.map.get(curr)] -= 1;
            } else {
                // found an invalid bracket/character
                return false;
            }

            for (int j = 0; j < count.length; j++) {
                if (count[j] < 0) {
                    return false;
                }
            }

        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        return true;

    }


}

/*
[
{
(

[() [{()}]] --> valid

[ ( { ) }  ] --> valid

[ ( { ] ) } --> valid

{ ) } --> invalid

{ { [ } ] --> invalid

[() [{()}]] --> valid

{ { [ } ]

squareBracketCounter = 0;
curlyBracketCounter = 1;
roundBracketCounter = 0;

indexes


n
O(128*n) ~~ O(n)


][


0
1
2
4

0 1 2 3 4

 */
