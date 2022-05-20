package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MinHeap {
    public ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public MinHeap(ArrayList<Integer> A) {
        heap = A;
        buildMinHeap(heap);
    }

    public void buildMinHeap(ArrayList<Integer> A) {
        for(int i = (A.size()-1)/2; i >= 0; i--)  {
            minHeapify(A, i);
        }
    }

    public ArrayList<Integer> sort() {
        ArrayList<Integer> sorted = (ArrayList<Integer>) heap.stream().collect(Collectors.toList());
        buildMinHeap(sorted);
        for(int i = heap.size(); i > 2; i--) {
            int temp = sorted.get(i);
            heap.set(i, heap.get(0));
            heap.set(0, temp);
            minHeapify(sorted, 0);
        }
        return sorted;
    }

    public void minHeapify(ArrayList<Integer> A, int i) {
        int smallest = i;
        int L = left(i);
        int R = right(i);
        if (L < A.size() && A.get(L) < A.get(i)) 
            smallest = L;
		  if (R < A.size() && A.get(R) < A.get(smallest)) 
					 smallest = R;
 
        if (smallest != i) {
            int tmp = A.get(i);
            A.set(i, heap.get(smallest));
            A.set(smallest, tmp);
			minHeapify(A,smallest);
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
