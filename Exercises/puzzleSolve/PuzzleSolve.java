import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PuzzleSolve {
    public static void main(String[] args){
        File file = new File("data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            for(int i = 0; i < 100000; i++) {
                ArrayList<Integer> perm = permutation(16);
                outputfile.write(cycles(perm)+ "\n");
            }
            // closing writer connection
            outputfile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> permutation(int size) {
        ArrayList<Integer> perm = new ArrayList<Integer>();
        for(int i = 0; i < size; i++)
            perm.add(i);
        Collections.shuffle(perm);
        return perm;
    }

    public static void printArrayList(ArrayList<Integer> perm) {
        String line = new String(new char[perm.size()*2+1]).replace("\0", "-");
        System.out.println(line);
        for(int i: perm)
            System.out.print("|"+i);
        System.out.println("|");
        System.out.println(line);
    }

    public static int cycles(ArrayList<Integer> perm) {
        int cycles = 0;
        for(int i = 0; i < perm.size(); i++)
            if(perm.get(i) != -1) {
                findCycle(perm, i);
                cycles++;
            }
        return cycles;
    }

    public static void findCycle(ArrayList<Integer> perm, int index) {
        if(perm.get(index) == -1)
            return;
        int next = perm.get(index);
        perm.set(index, -1);
        findCycle(perm, next);
    }
}
