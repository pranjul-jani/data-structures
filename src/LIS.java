import java.util.ArrayList;
import java.util.List;

public class LIS {

    public static void main(String[] args) {
        LIS ob = new LIS();

        int[] arr = {10,9,2,5,3,7,101,18};



    }
    public List<Integer> printLIS(int[] arr) {

        /*
        if(arr == null || arr.length == 0) {
            return 0;
        }

         */

        int dp[] = new int[arr.length];
        int maxSub = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++) {
            dp[i] = 1;
            for(int j=i-1;j>=0;j--) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxSub = Math.max(maxSub, dp[i]);
        }
        return new ArrayList<>();
        //return maxSub;
    }
}
