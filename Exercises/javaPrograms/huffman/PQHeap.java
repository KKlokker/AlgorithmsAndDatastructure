package huffman;


/*
Kristoffer Fischer Klokker   - krklo21@student.sdu.dk
Philip Vestervang Nagel      - phnag21@student.sdu.dk
Rasmus Wissing Kallehauge    - rakal21@student.sdu.dk
*/

import java.util.ArrayList;

public class PQHeap implements PQ {

    private ArrayList<Element> heap;

    public PQHeap() {
        heap = new ArrayList<>(); 
    }

    public void insert(Element E) {
        heap.add(E);
        int i = heap.size() - 1;
        while (i > 0 && heap.get((i-1) / 2).getKey() > heap.get(i).getKey()) {
            Element tmp = heap.get(i);
            heap.set(i, heap.get((i-1) / 2));
            heap.set((i-1) / 2, tmp);
            i = (i - 1) / 2;
		  }
    }

    public Element extractMin() {
       	Element min = heap.get(0);
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
        if (L < heap.size() && heap.get(L).getKey() < heap.get(i).getKey()) 
            smallest = L;

		  if (R < heap.size() && heap.get(R).getKey() < heap.get(smallest).getKey()) 
					 smallest = R;
 
        if (smallest != i) {
            Element tmp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, tmp);
				minHeapify(smallest);
       }
		  
    }

    public int size() {
        return heap.size();
    }
}
