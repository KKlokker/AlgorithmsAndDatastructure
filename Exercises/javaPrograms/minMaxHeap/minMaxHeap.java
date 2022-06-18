package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class minMaxHeap {
    
    public static void main(String[] args) {
        MaxHeap max = new MaxHeap();
        Stream.of(18,9,16,4,8,12,13,1,2).forEach(i -> {max.heapInsert(i);});
          
        max.heap.stream().forEach(d -> System.out.print(d+", "));
        System.out.println();
        max.heapIncreaseKey(max.heap, 8, 15);
        max.ExtractMax();
        max.heap.stream().forEach(d -> System.out.print(d+", "));
        
    }


}
