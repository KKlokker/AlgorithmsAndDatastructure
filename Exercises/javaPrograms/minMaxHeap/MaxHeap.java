package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MaxHeap {
    public ArrayList<Integer> heap;

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

    public void maxHeapify(ArrayList<Integer> A, int i) {
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
            i = parent(i);
        }
    }

    public void heapInsert(ArrayList<Integer> A, int key) {
        A.add(key);
        heapIncreaseKey(A, A.size(), key);
    }

    private int left(int i) {
        return 2*i+1;
    }

    
    private int right(int i) {
        return 2*i+2;
    }

    
    private int parent(int i) {
        return i/2;
    }
}
