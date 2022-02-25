public class BinarySearch {
    public static void main(String[] args){
        Integer[] test = new Integer[]{1,2,3,4,5,6,7};
        for(Integer i: test){
            if(test[binarySearch(test, i)] != i)
                System.out.println("Error: " + i);
        }
        
    }

    public static <T extends Comparable<T>> int binarySearch(T[] list, T value) {
        int i = list.length/2;
        int j = list.length/2 + list.length%2;
        while(j != 0 && list[i] != value) {
            j = j/2 + j%2;
            if(list[i].compareTo(value) < 0)
                i = i + j;
            else
                i = i - j;
        }
        return i;
    }
}