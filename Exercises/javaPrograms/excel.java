import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class excel {
    public static void main(String[] args) {
        File file = new File("pictogram.csv");
        File file2 = new File("pictogramFix.csv");
        List<String> forbiddenWords = Stream.of("ET","EN","OG","I","PÅ","AF","DEN","DER","VED","SIDEN","MED","ANDET",".","/","SOM","TIL").collect(Collectors.toList());
            try {
                Scanner sc = new Scanner(file);  
                sc.useDelimiter(","); 
                String id = "";
                FileWriter outputfile = new FileWriter(file2);
                while (sc.hasNext()) 
                {
                    String next = sc.nextLine();
                    String[] keywords = next.split(",");
                    id = keywords[0];
                    ArrayList<String> usedWords = new ArrayList<>();
                    for(int i = 1; i < keywords.length; i++){
                        String[] words = keywords[i].split(" ");
                        for(String word: words) {
                            word = word.toUpperCase();
                            if(usedWords.indexOf(word) == -1 && forbiddenWords.indexOf(word) == -1) {
                                outputfile.write(id + "," + word + "\n");
                                usedWords.add(word);
                            } 
                        }
                    }
                }

                outputfile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }
}
