import java.util.LinkedList;

public class sort {
    public static void main(String[] args) {
        runSimulation(100, 4);
    }

    /* Runs Simulation given: 
     - n: number of elements
     - s: sorting algorithm
    */ 
    public static void runSimulation(int n, int s) {
        int[] arr = createArray(n);
        printArray(arr);

        // find correct sorting algorithm
        if (s == 0) {
            System.out.println("Running Bubble Sort: ");
            bubbleSort(n, arr);
        } else if (s == 1) {
            System.out.println("Running Selection Sort: ");
            selectionSort(n, arr);
        } else if (s == 2) {
            System.out.println("Running Insertion Sort: ");
            insertionSort(n, arr);
        } else if (s == 3) {
            System.out.println("Running Quick Sort:");
            quickSort(n, arr);
        } else if (s == 4) {
            System.out.println("Running Merge Sort:");
            mergeSort(n, arr);
        }

        printArray(arr);
    }

    // prints array
    private static void printArray(int[] arr) {
        for (int elt : arr) {
            System.out.print(elt + " ");
        } 
        System.out.println(); 
    }

    // Creates array of size 1 to n, with elements of length 1 to n
    private static int[] createArray(int n) {
        int[] arr = new int[n];

        LinkedList<Integer> nums = new LinkedList<Integer>();

        // fill nums with numbers
        for (int i = 0; i < n; i++) {
            nums.add(i+1);
        }

        // pick a random index in nums and put it into arr backwards
        for (int i = n - 1; i >= 0; i--) {
            int index = (int) (Math.random()*(i+1));
            int element = nums.remove(index);
            arr[i] = element;
        }

        return arr;
    }

    // Swaps elements at indices a and b
    private static boolean swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return true;
    }
    
    // Sorting algorithm 0: bubble sort
    private static void bubbleSort(int n, int[] arr) {
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapped = swap(arr, j, j+1);
                }
            }
            if (!swapped)
                break;
        }
    }
    
    // Sorting algorithm 1: selection sort
    private static void selectionSort(int n, int[] arr) {
        for (int i = 0; i < n-1; i++) {
            // Index of minimum elt
            int min = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            
            swap(arr, i, min);
        }
    }

    // Sorting algorithm 2: insertion sort
    private static void insertionSort(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            int insert = arr[i];
            int j = i-1;

            while ((j >= 0) && (arr[j] > insert)) {  
                arr[j+1] = arr[j];  
                j--;  
            }  

            arr[j+1] = insert;
        }
    }

    // Sorting algorithm 3: quick sort
    private static void quickSort(int n, int[] arr) {
        quickSortHelper(arr, 0, n-1);
    }

    // Partition list for quick sort
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // quick sort recursive helper method
    static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
        }
    }

    // Sorting algorithm 4: merge sort
    private static void mergeSort(int n, int[] arr) {
        mergeSortHelper(arr, 0, n-1);
    }

    private static void mergeSortHelper(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSortHelper(arr, l, m);
            mergeSortHelper(arr, m + 1, r);
 
            merge(arr, l, m, r);
        }
    }

    private static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        // Copy the values into temporary left and right arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[l] = L[i];
                i++;
            } else {
                arr[l] = R[j];
                j++;
            }
            l++;
        }
        
        while (i < n1) {
            arr[l] = L[i];
            i++;
            l++;
        }
 
        while (j < n2) {
            arr[l] = R[j];
            j++;
            l++;
        }
    }
}