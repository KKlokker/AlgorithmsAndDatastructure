package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MaxHeap {
    public static ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    public MaxHeap(ArrayList<Integer> A) {
        heap = A;
        buildMaxHeap(heap);
    }

    public void buildMaxHeap(ArrayList<Integer> A) {
        for(int i = (A.size()-1)/2; i >= 0; i--)  {
            maxHeapify(A, i);
        }
    }

    public ArrayList<Integer> sort() {
        ArrayList<Integer> sorted = (ArrayList<Integer>) heap.stream().collect(Collectors.toList());
        buildMaxHeap(sorted);
        for(int i = heap.size(); i > 2; i--) {
            int temp = sorted.get(i);
            heap.set(i, heap.get(0));
            heap.set(0, temp);
            maxHeapify(sorted, 0);
        }
        return sorted;
    }

    public int ExtractMax() {
        int temp = heap.get(heap.size()-1);
        heap.set(heap.size()-1, heap.get(0));
        heap.set(0, temp);
        
        int max = heap.remove(heap.size()-1);
        maxHeapify(heap, 0);
        return max;
    }

    public void maxHeapify(ArrayList<Integer> A, int i) {
        System.out.println(A.get(i));
        int l = left(i);
        int r = right(i);
        int largest = i;
        if(l<A.size() && A.get(l) > A.get(i)) 
            largest = l;
        if(r<A.size() && A.get(r) > A.get(largest))
            largest = r;
        if(largest != i) {
            int temp = A.get(i);
            A.set(i, A.get(largest));
            A.set(largest, temp);
            maxHeapify(A,largest);
        }
    }

    public void heapIncreaseKey(ArrayList<Integer> A, int i, int key) {
        if(key < A.get(i))
            return;
        A.set(i, key);
        while(i > 0 && A.get(parent(i))<A.get(i)) {
            int temp = A.get(i);
            A.set(i, A.get(parent(i)));
            A.set(parent(i), temp);
            System.out.println("Switched: " + A.get(i) + " " + A.get(parent(i)));
            i = parent(i);
        }
    }

    public void heapInsert(int key) {
        heap.add(key);
        heapIncreaseKey(heap,heap.size()-1, key);
    }

    private int left(int i) {
        return 2*i+1;
    }

    
    private int right(int i) {
        return 2*i+2;
    }

    
    private int parent(int i) {
        return (i-1)/2;
    }
}
