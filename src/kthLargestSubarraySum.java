import java.util.PriorityQueue;

public class kthLargestSubarraySum {

    public static void main(String[] args) {
        kthLargestSubarraySum ob = new kthLargestSubarraySum();
        int[] arr = {10, -10, 20, -40 };
        System.out.print(ob.kthLargestSum(arr, 6));
    }

    private int kthLargestSum(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<arr.length;i++) {
            int sum = 0;
            for (int j=i;j< arr.length;j++) {
                sum += arr[j];

                if (pq.size() < k) {
                    pq.add(sum);
                }
                else {
                    if (sum > pq.peek()) {
                        pq.poll();
                        pq.add(sum);
                    }
                }
            }
        }

        return pq.peek();
    }

}
