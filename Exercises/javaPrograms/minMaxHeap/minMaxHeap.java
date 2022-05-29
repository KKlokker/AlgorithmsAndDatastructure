package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class minMaxHeap {
    
    public static void main(String[] args) {
        ArrayList<Integer> heap = (ArrayList<Integer>) Stream.of(2,4,3,7,7,5,6,8,9).collect(Collectors.toList());
        MinHeap minHeap = new MinHeap(heap);
        minHeap.heap.stream().forEach(i -> System.out.print(i+", "));
        System.out.println("x");
        minHeap.extractMin();
        minHeap.insert(1);
        minHeap.heap.stream().forEach(i -> System.out.print(i+", "));
    }


}
