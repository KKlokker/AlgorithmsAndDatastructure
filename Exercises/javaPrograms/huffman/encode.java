package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/*
Kristoffer Fischer Klokker   - krklo21@student.sdu.dk
Rasmus Wissing Kallehauge    - rakal21@student.sdu.dk
*/

public class encode {
    public static void main(String[] args){


        Element[] frequency = new Element[]{
            new Element(400,new Tree(null,null,'a')),
            new Element(750,new Tree(null,null,'e')),
            new Element(300,new Tree(null,null,'i')),
            new Element(150,new Tree(null,null,'o')),
            new Element(200,new Tree(null,null,'u')),
            new Element(100,new Tree(null,null,'y'))};


        Tree huff = new Tree(frequency);
        Tree.print(huff);
        findPath(huff).forEach((value, code) -> System.out.println(value + ": " + code));
        System.out.println("The filesize after compression is: " + fileSize(huff, frequency));
    }

    /**
     * Creates a huffman tree from the given frequency map, and handles recursive traversal
     * @param map Frequency map of byte occurences
     * @return A string map for conversion where Byte -> Compressed byte
     */
    private static Map<Character,String> findPath(Tree huffman) {
        Map<Character,String> newMap = new HashMap<>();
        recursiveTraversal(newMap, new Element(-1, huffman) , "");
        return newMap;
    }   
    
/** 
 * @param output ArrayList in which the output will be saved in.
 * @param T The tree we are currently traversing.
 * @return Returns traversed Tree in the given ArrayList
 */
    private static void recursiveTraversal(Map<Character,String> output, Element e, String code) {
        if(e!= null){
            Tree t = (Tree) e.getData();
            recursiveTraversal(output, t.left(),code+"0");
            if(t.byteValue() != '\0') output.put(t.byteValue(), code);
            recursiveTraversal(output, t.right(),code+"1");
        }
    }

    private static int fileSize(Tree huffman, Element[] freq) {
        int size = 0;
        Map<Character,String> map = findPath(huffman);
        for(Element el: freq)
            size = size + map.get(((Tree) el.getData()).byteValue()).length() * el.getKey();
        return size;
    }

    
    
}