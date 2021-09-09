public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7 };

        HeapSort ob = new HeapSort();

        ob.sort(arr);
        ob.print(arr);
    }

    private void sort(int[] arr) {
        //sort in ascending order

        int size = arr.length;

        //build max heap
        for(int i=arr.length/2 - 1;i>=0;i--) {
            heapify(arr,i,size);
        }

        //one by one extract max place it in the end reduce the size of the array
        for (int i=arr.length-1;i>=0;i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            heapify(arr,0,i);
        }

    }

    private void heapify(int[] arr, int index, int size) {

        //function to create max heap

        int max = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if (left < size && arr[left] > arr[max]) {
            max = left;
        }

        if (right < size && arr[right] > arr[max]) {
            max = right;
        }

        if (max != index) {
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;

            heapify(arr, max, size);
        }
    }

    private void print(int[] arr) {
        for(int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
