package minMaxHeap;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class minMaxHeap {
    
    public static void main(String[] args) {
        MaxHeap max = new MaxHeap();
        Stream.of(6,12,13,4,8,14,5).forEach(i -> {max.heapInsert(i);
          
            max.heap.stream().forEach(d -> System.out.print(d+", "));
        System.out.println();
        });
    }


}
