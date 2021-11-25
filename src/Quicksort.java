public class Quicksort {

    public static void main(String[] args) {
        int[] arr = {5,12,1,9,7,2,8};

        quicksort(arr,0,arr.length-1);


        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void quicksort(int[] arr, int low, int high) {

        if(low < high) {
            //part position will be sorted
            int part = partition(arr,low,high);

            //us position ke peeche wale and agge wale sort honge excluding that position
            quicksort(arr,low,part-1);
            quicksort(arr,part+1,high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int pos = low - 1;

        for(int i=low;i<high;i++) {

            if (arr[i] < pivot) {
                pos += 1;
                //swap
                int temp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = temp;
            }
        }

        // swap pivot
        pos += 1;
        int temp = arr[high];
        arr[high] = arr[pos];
        arr[pos] = temp;


        return pos;
    }
}
