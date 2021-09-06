public class SegmentTree {

    public static void main(String[] args) {
        SegmentTree ob = new SegmentTree();
        int[] arr = {1, 2, 5, 6, 7, 9};
        int size = ob.findSizeOfSegmentTree(arr.length);
        int[] ST = new int[size];
        System.out.println(size);

        ob.constructSegmentTree(ST, arr, 0, 0, arr.length-1);
        ob.print(ST);

        int sum = ob.findRangeSum(ST,0,0,arr.length-1,2,4);
        System.out.println(sum);

        int diff = ob.updateArray(arr,3,14);
        ob.print(arr);
        ob.updateSegmentTree(ST,0,0,arr.length-1,3,diff);
        ob.print(ST);
    }

    private int findSizeOfSegmentTree(int length) {

        int treeHeight = (int) (Math.ceil(Math.log(length) / Math.log(2)));
        return 2 * (int) Math.pow(2,treeHeight) - 1;
    }

    private void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private void constructSegmentTree(int[] st, int[] arr, int node, int low, int high) {

        if(low == high) {
            st[node] = arr[low];
            return;
        }
        int mid = low + (high-low)/2;
        constructSegmentTree(st, arr, 2*node+1, low, mid);
        constructSegmentTree(st, arr, 2*node+2, mid+1, high);
        st[node] = st[2*node+1] + st[2*node+2];
    }

    private int updateArray(int[]arr, int pos, int value) {
        int diff = value - arr[pos];
        arr[pos] = value;
        return diff;
    }

    private int findRangeSum(int[] st, int node, int low, int high, int qlow, int qhigh) {

        //case-1 total overlap
        if(low>=qlow && high<=qhigh) {
            return st[node];
        }

        //case-2 no overlap
        if (high < qlow || low > qhigh) {
            return 0;
        }
        //case-3 partial overlap
        int mid = low + (high-low)/2;
        return findRangeSum(st,2*node+1,low,mid,qlow,qhigh)
                + findRangeSum(st,2*node+2,mid+1,high,qlow,qhigh);
    }

    private void updateSegmentTree(int[] st, int node, int low, int high, int pos, int diff) {
        //no overlap
        if(low>pos || high<pos) {
            return;
        }
        st[node] += diff;

        //check if leaf node to stop recursion
        if(low<high) {
            int mid = low + (high-low)/2;
            updateSegmentTree(st,2*node+1,low,mid,pos,diff);
            updateSegmentTree(st,2*node+2,mid+1,high,pos,diff);
        }
    }
}
