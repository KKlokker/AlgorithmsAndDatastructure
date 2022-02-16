import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class mergeSort {
    public static void main(String[] args){
        File file = new File("data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            for(int i = 0; i < 1000; i++) {
                int length = ThreadLocalRandom.current().nextInt(100000, 1000000);
                Integer[] test = new Integer[length];
                for(int j = 0; j < length; j++) 
                    test[j] = ThreadLocalRandom.current().nextInt(0, 1000);
                long time = System.currentTimeMillis();
                sort(test);
                outputfile.write(System.currentTimeMillis() - time + "," + length +"\n");
                System.out.println(i );
            }
            // closing writer connection
            outputfile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        if(array.length  == 1)
            return;
        if(array.length == 2) {
            T first = array[0];
            if (array[1].compareTo(first) < 0) { //Swap places if in wrong order 
                array[0] = array[1];
                array[1] = first;
            }
            return;
        }
        int length = array.length;
        T[] first = Arrays.copyOfRange(array, 0, length/2);
        T[] second = Arrays.copyOfRange(array,length/2, length); //Round up the second in case of none even array
        sort(first);
        sort(second);
        int f = 0, s = 0;
        for(T element: array) {
            if(first.length == f || second.length != s && second[s].compareTo(first[f]) <= 0) {
                array[s+f] = second[s];
                s++;
            }
            else if(second.length == s|| first[f].compareTo(second[s]) < 0) {
                array[s+f] = first[f];
                f++;
            }
        }
    }
}