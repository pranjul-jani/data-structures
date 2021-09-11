public class ChainMatrixMultiplication {

    public static void main(String[] args) {
        ChainMatrixMultiplication ob = new ChainMatrixMultiplication();
        int[] arr = {2,3,6,4,5};

        /*
        result is stored diagonal wise
        for e.g 4 matrices considered
        
        1. (0,1),(1,2),(2,3),(3,4) --> single matrix considered
        2. (0,2),(1,3),(2,4) --> two matrices considered
        3. (0,3),(1,4) --> three matrices considered
        4. (0,4) --> four matrices considered
        */
        int cost = ob.findMinCost(arr);
        System.out.println(cost);
    }

    private int findMinCost(int[] arr) {
        /*
        For simplicity of the program,
        one extra row(last one), row index of 1st matrix is 0
        one extra column(first one), column index of 1st matrix is 1;
        arr[0][1] is self product of matrix 1; arr[0][2] matrix 1 * matrix 2;
        */
        int[][] dp = new int [arr.length][arr.length];

        /*length here is number of matrices involved in multiplication at the current time
        for eg: if arr length is 5 then 4 matrices are required to be multiplied
        then min 2 matrices have to be multiplied at once (that is the case always)
        and max is 4<5(arr.length) in this case
         */
        int cellValue = 0;
        for(int length=2;length<arr.length;length++) {

            /*
            consider last case in which 4 matrices at once are multiplied
            i will remain 0 in that 0<1 which will satisfy the condition
            */
            for(int i=0;i<arr.length-length;i++) {
                /* k will be the middle value,
                   j is the last value that can be reached
                */
                int j = i + length;
                // final cell whose value has to be evaluated
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i+1;k<j;k++) {
                    cellValue = dp[i][k] + dp[k][j] + arr[i]*arr[k]*arr[j];
                    if (cellValue < dp[i][j]) {
                        dp[i][j] = cellValue;
                    }
                }
            }
        }
        /*
        since the result is stored diagonal wise the last cell for
        cumulative min product is this
        */
        return dp[0][arr.length-1];
    }
}
