package countingSort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CountingSort {
    public static void main(String[] args){
        /*File file = new File("data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            for(int i = 0; i < 1000; i++) {
                int length = ThreadLocalRandom.current().nextInt(0, 5000000);
                int[] test = new int[length];
                for(int j = 0; j < length; j++) 
                    test[j] = ThreadLocalRandom.current().nextInt(0, 1000);
                long time = System.currentTimeMillis();
                sort(test);
                outputfile.write(System.currentTimeMillis() - time + "," + length +"\n");
                System.out.println(i +  " " + (System.currentTimeMillis() - time));
            }
            // closing writer connection
            outputfile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        */
        int[] array = new int[]{3,1,4,3,5,0,3,1};
        int[] c = cArrayInSort(array,7);
        for(int i: c) System.out.print(i + ", ");
        System.out.println("");
        int[] sort = sort(array);
        for(int i: sort) System.out.print(i + ", ");
    }
    
    public static int[] sort(int[] array) {
        int largest = Integer.MIN_VALUE; //finds largest value itself instead of getting it as k
        int smallest = Integer.MAX_VALUE;
        for(int i: array)
            if(i < smallest)
                smallest = i;
            else if(largest < i)
                largest = i;
        ArrayList<Integer>[] sortArray = new ArrayList[largest-smallest+1];
        for(int i = 0; i < largest-smallest+1; i++)
            sortArray[i] = new ArrayList<Integer>();
        for(int i: array)
            sortArray[i-smallest].add(i);
        int[] sort = new int[array.length];
        int i = 0;
        for(ArrayList l: sortArray)
            for(Object j: l) {
                sort[i] = (int)j;
                i++;
            }
        return sort;
    }
    
    public static int[] cArrayInSort(int[] array, int k) {
        int[] c = new int[k];
        Arrays.fill(c, 0);
        for(int i: array)
            c[i]++;
        return c;
    }
}