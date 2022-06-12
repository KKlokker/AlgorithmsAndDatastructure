package quicksort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class mergeSort {
    public static void main(String[] args){
        System.out.println("sad");
        int[] A = new int[]{21,17,28,14,9,17,6,1,26,15,30,7,13,19,2};
        Partition(A, 0, 7);
        for(int i: A)
            System.out.print(i + ", ");
            System.out.println("sad");
            int[] A2 = new int[]{2,8,7,1,3,5,6,4};
            Partition(A2, 4, 13);
            for(int i: A2)
                System.out.print(i + ", ");
        /*
        File file = new File("data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            for(int i = 0; i < 1000; i++) {
                int length = ThreadLocalRandom.current().nextInt(100000, 500000);
                Integer[] test = new Integer[length];
                for(int j = 0; j < length; j++) 
                    test[j] = ThreadLocalRandom.current().nextInt(0, 1000);
                long time = System.currentTimeMillis();
                sort(test, 0, test.length-1);
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
    }
    //0.001153 is the average sorting time for element length, before optimzation and using the last element always as x
    //After optimazation it got not really better and now is 0.001155 due to the random number but in sorted arrays it should be better
    public static <T extends Comparable<T>> void sort(T[] array, int p, int r) {
        if(r <= p) return;
        int i = p;
        T x = array[r];
        if(15 < r-p) {
            int a = p+((r-p)/2);
            int space = 0;
            /**a < p
                p < r -> p mid
                a < r -> r mid
                a mid
            else
                a < r -> a midt
                p < r -> r midt
                p mid
            a.com(p) == -1 -> a < p
            */
            if(array[a].compareTo(array[p]) <= 0){
                if(array[p].compareTo(array[r]) <= 0) space = p;
                else if(array[a].compareTo(array[r]) <= 0) space = r;
                else space = a;
            }
            else {
                if(array[a].compareTo(array[r]) <= 0) space = a;
                else if(array[p].compareTo(array[r]) <= 0) space = r;
                else space = p;
            }
            x = array[space];
            T temp = array[r];
            array[r] = x;
            array[space] = temp;
        }
        for(int j = p; j < r; j++)
            if(array[j].compareTo(x) <= 0) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        array[r] = array[i];
        array[i] = x;
        //sort(array,p,i-1);
        //sort(array, i+1, r);
    }

    public static void Partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p-1;
        for(int j = p; j < r-1; j++) {
            if(A[j] <=x) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
    } 

    
}