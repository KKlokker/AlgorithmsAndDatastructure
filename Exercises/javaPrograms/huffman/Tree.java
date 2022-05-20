package huffman;

public class Tree {
    private Element left, right;
    private char byteValue;

    public Tree(Element left, Element right, char byteValue) {
        this.left = left;
        this.right = right;
        this.byteValue = byteValue;
    }

    public Element left() {
        return left;
    }

    public Element right() {
        return right;
    }

    public void setLeft(Element left) {
        this.left = left;
    }

    public void setRight(Element right) {
        this.right = right;
    }

    public char byteValue() {
        return byteValue;
    }

    public void setByteValue(char  byteValue) {
        this.byteValue = byteValue;        
    }

    public Tree(Element[] freqMap) {
        PQHeap trees = new PQHeap();

        //Makes every character to a tree with frequency
        for(int i = 0; i < freqMap.length; i++) {
            trees.insert(freqMap[i]);
        }
        //Merges trees until 1 is left
        while(trees.size()>1) {
            Element leastFrequent1 = trees.extractMin();
            Element leastFrequent2 = trees.extractMin();
            trees.insert(new Element((int) leastFrequent1.getKey() + (int) leastFrequent2.getKey(), new Tree(leastFrequent1, leastFrequent2,'\0')));
        }
        
        Tree huffman = (Tree) trees.extractMin().getData();

        this.left = huffman.left;
        this.right = huffman.right;
        this.byteValue = huffman.byteValue;
    }  

    public static void print(Tree root) {
        int keyLength =  String.valueOf(highestKey(root)).length();
        printLine(root, 0, keyLength);
    }

    public static void printLine(Tree x, int depth, int keyLength) {
        String keySpace = new String(new char[keyLength]).replace("\0", " ");
        String keyLine = new String(new char[keyLength]).replace("\0", "-");        
        String index = new String(new char[depth]).replace("\0", "|" + keySpace) + "└" + keyLine;
        System.out.println(index + x.byteValue());
        if(x.left != null) printLine((Tree) x.left.getData(), depth+1, keyLength); 
        if(x.right != null) printLine((Tree) x.right.getData(), depth+1, keyLength );
    }

    public static int highestKey(Tree root) {
        while(root.right != null)
            root = (Tree) root.right.getData();
        return root.byteValue;
    }

    public static int height(Tree root, int height) {
        int left = height, right = height;
        if(root.right != null)
            right = height((Tree) root.right.getData(), height+1);
        if(root.left != null)
            left = height((Tree) root.left.getData(), height+1);
        return (left < right) ? right : left;
    }
}