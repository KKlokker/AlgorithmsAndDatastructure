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
            minHeapify(i);
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
        while(i > 0 && A.get(parent(i))>A.get(i)) {
            int temp = A.get(i);
            A.set(i, A.get(parent(i)));
            A.set(parent(i), temp);
            i = parent(i);
        }
    }

    public void heapInsert(int key) {
        heap.add(key);
        heapIncreaseKey(heap, heap.size()-1, key);
    }

    public int extract() {
        return heap.remove(0);
    }
    

    
    public void insert(int key) {
        heap.add(key);
        int i = heap.size() - 1;
        while (i > 0 && heap.get((i-1) / 2) > heap.get(i)) {
            int tmp = heap.get(i);
            heap.set(i, heap.get((i-1) / 2));
            heap.set((i-1) / 2, tmp);
            i = (i - 1) / 2;
		  }
    }

    public int extractMin() {
       	int min = heap.get(0);
        	heap.remove(0);
			if(heap.size() > 0) {	
		 		heap.add(0, heap.get(heap.size()-1));
				heap.remove(heap.size()-1);
        		minHeapify(0);
			}
		return min;
	 }

    public void minHeapify(int i) {
        int smallest = i;
        int L = 2 * i + 1;
        int R = 2 * i + 2; 
        if (L < heap.size() && heap.get(L) < heap.get(i)) 
            smallest = L;

		  if (R < heap.size() && heap.get(R) < heap.get(smallest)) 
					 smallest = R;
 
        if (smallest != i) {
            int tmp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, tmp);
				minHeapify(smallest);
       }
		  
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
