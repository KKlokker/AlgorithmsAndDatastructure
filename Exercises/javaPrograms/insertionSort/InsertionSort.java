import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InsertionSort {
    public static void main(String[] args){
        
        File file = new File("data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            for(int i = 0; i < 1000; i++) {
                int length = (int)(Math.random() * ((10000 - 1000) + 1)) + 1000;
                Integer[] test = new Integer[length];
                for(int j = 0; j < length; j++) 
                    test[j] = (int)(Math.random() * ((999) + 1));
                long time = System.currentTimeMillis();
                insertionSort(test);
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

    public static <T extends Comparable<T>> void insertionSort(T[] list) {
        for(int i = 0; i < list.length; i++) {
            int j = i;
            while(0 < j && list[j].compareTo(list[j-1]) < 0) {
                T val = list[j-1];
                list[j-1] = list[j];
                list[j] = val;
                j--;
            }
        }
    }
}