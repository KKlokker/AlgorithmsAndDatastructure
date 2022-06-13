package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class minMaxHeap {
    
    public static void main(String[] args) {
        MaxHeap max = new MaxHeap();
        Stream.of(5,4,3,3,4,2,3,2,1).forEach(i -> {max.heapInsert(i);});
          
            //max.heap.stream().forEach(d -> System.out.print(d+", "));
        System.out.println();
        max.ExtractMax();
        max.heap.stream().forEach(d -> System.out.print(d+", "));
        
    }


}
