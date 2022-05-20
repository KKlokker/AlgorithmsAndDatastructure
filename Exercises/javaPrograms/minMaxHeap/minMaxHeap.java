package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class minMaxHeap {
    
    public static void main(String[] args) {
        ArrayList<Integer> heap = (ArrayList<Integer>) Stream.of(5,4,3,2,1,10,9,8,7,6).collect(Collectors.toList());
        MaxHeap maxHeap = new MaxHeap(heap);
        maxHeap.heap.stream().forEach((i) -> System.out.print(i + ", "));
    }


}
