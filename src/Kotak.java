import java.util.HashSet;
import java.util.Set;

public class Kotak {

    private static int maxSequenceLength(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            set.add(num);
        }

        int maxLength = 0;
        int maxStart = -1;

        for(int num : set) {
            if (!set.contains(num-1)) {
                int length = 1;
                int start = num;
                int curr = num + 1;
                while(set.contains(curr)) {
                    length += 1;
                    curr += 1;
                }
                if(maxLength < length) {
                    maxLength = Math.max(maxLength, length);
                    maxStart = start;
                }
            }
        }

        for(int i=0;i<maxLength;i++) {
            System.out.print(maxStart++);
        }

        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(maxSequenceLength(new int[]{0,0,0,0}));
    }

}


/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]Output: 4Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]Output: 9
Example 3:
Input: nums = [1,0,1,2]Output: 3



100, 4, 200, 1, 3, 2


(100, 4, 200, 1, 3, 2)


100

if(!set.contains(n-1)) {
    length = 1;
    curr = 1;

    while(set.contains(curr+1)) {
        length += 1;
        curr += 1;
    {
}

currLength = 1;










 */




