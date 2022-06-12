package balancedBinaryTree;

import java.security.Key;

public class BST {
    public static void main(String[] args){
        Tree root = new Tree(null, null, null, 24, "test", false);
        Tree left = new Tree(root,null,null, 18, "test", true);
        Tree t1 =  new Tree(root,null,null, 26, "test", false);
        Tree t2 =  new Tree(left,null,null, 5, "test", false);
        Tree t3 =  new Tree(left,null,null, 20, "test", false);
        Tree t4 =  new Tree(t2,null,null, 2, "test", true);
        Tree t5 =  new Tree(t2,null,null, 7, "test", true);
        Tree t6 =  new Tree(t3,null,null, 23, "test", true);
        Tree t7 =  new Tree(t1,null,null, 27, "test", true);

        root.left = left;
        root.right = t1;
        t1.right = t7;
        left.left = t2;
        left.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right= t6;
        
        print(root);
        insert(root, new Tree(null, null, null, 21, "test", true));
        
        print(root);
    }

    
    public static void insert(Tree root, Tree x) {
        Tree y = null;
        Tree t = root;
        while(t != null) {
            y = t;
            if(x.key < t.key)
                t = t.left;
            else
                t = t.right;
        }
        System.out.println(y.key);
        x.parent = y;   
        if(x.key < y.key)
            y.left = x;
        else
            y.right = x;
        insertFix(x);
    }

    public static void insertFix(Tree z) {
        if(z.parent != null && z.parent.parent != null) {
            while(z.parent.isRed) {
                System.out.println(z.key);
                if(z.parent == z.parent.parent.left) {
                    Tree y = z.parent.parent.right;
                    if(y != null && y.isRed) {
                        z.parent.isRed = false;
                        y.isRed = false;
                        z.parent.parent.isRed = true;
                        z = z.parent.parent;
                    }
                    else {
                        if(z == z.parent.right) {
                            z = z.parent;
                            rotate(z, false);
                        }
                        z.parent.isRed = false;
                        z.parent.parent.isRed = true;
                        rotate(z.parent.parent, true);
                    }
                }
                else {
                    Tree y = z.parent.parent.left;
                    System.out.println(z.parent.parent.key);
                    if(y != null && y.isRed) {
                        z.parent.isRed = false;
                        y.isRed = false;
                        z.parent.parent.isRed = true;
                        z = z.parent.parent;
                    }
                    else {
                        if(z == z.parent.left) {
                            z = z.parent;
                            rotate(z, true);
                        }
                        z.parent.isRed = false;
                        z.parent.parent.isRed = true;
                        rotate(z.parent.parent, false);
                    }
                }
            }
            z.isRed = false;
        }
    }

    public static void transplant(Tree x, Tree y) {
        if(x.parent == null)
            {}
        else if(x.parent.right == x)
            x.parent.right = y;
        else 
            x.parent.left = y;
        if(y != null)
            y.parent = x.parent;
    }

    public static void remove(Tree root, Tree z) {
        if(z.left == null)
            transplant(z, z.right);
        else if(z.right == null)
            transplant(z, z.left);
        else {
            Tree y = min(z.right);
            if(y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
        removeFix(z.parent);
    }

    public static void removeFix(Tree x) {
        while(x != null && x.parent != null && !x.isRed) {
            if(x == x.parent.left) {
                Tree w = x.parent.right;
                if(w != null) {
                    if(w.isRed) {
                        w.isRed = false;
                        w.parent.isRed = true;
                        rotate(x.parent, false);
                        w = x.parent.right;
                    }
                    if (!w.left.isRed && !w.right.isRed) {
                        w.isRed = true;
                        x = x.parent;
                    }
                    else {
                        if (!w.right.isRed) {
                            w.left.isRed = false;
                            w.isRed = true;
                            rotate(w, true);
                            w = x.parent.right;
                        }
                        w.isRed = x.parent.isRed;
                        x.parent.isRed = false;
                        w.right.isRed = false;
                        rotate(x.parent, false);
                        Tree root = x;
                        while(root.parent != null) root = root.parent;
                        x = root;
                    }
                }
            }
            else {
                Tree w = x.parent.right;
                if(w != null) {
                    if(w.isRed) {
                        w.isRed = false;
                        w.parent.isRed = true;
                        rotate(x.parent, true);
                        w = x.parent.right;
                    }
                    if (w.left != null && w.right != null && !w.left.isRed && !w.right.isRed) {
                        w.isRed = true;
                        x = x.parent;
                    }
                    else {
                        if (w.left != null && !w.left.isRed) {
                            w.right.isRed = false;
                            w.isRed = true;
                            rotate(w, false);
                            w = x.parent.left;
                        }
                        w.isRed = x.parent.isRed;
                        x.parent.isRed = false;
                        if(w.left != null) w.left.isRed = false;
                        rotate(x.parent, true);
                        Tree root = x;
                        while(root.parent != null) root = root.parent;
                        x = root;
                    }
                }
            }
            x.isRed = false;
        }
    }

    public static Tree min(Tree root) {
        while(root.left != null)
            root = root.left;
        return root;
    }

    public static Tree search(Tree root, int key) {
        while(root.key != key || root == null)
            if(root.key < key) root = root.right;
            else root = root.left;
        return root;
    }

    public static void rotate(Tree x, boolean right) {
        if(right) {
            Tree y = x.left;
            x.left = y.right;
            if(y.right != null)
                y.right.parent = x;
            y.parent = x.parent;
            if(x.parent == null){}
            else if(x == x.parent.right)
                x.parent.right = y;
            else
                x.parent.left = y;
            y.right = x;
            x.parent = y;
        }
        else {
            Tree y = x.right;
            x.right = y.left;
            if(y.left != null)
                y.left.parent = x;
            y.parent = x.parent;
            if(x.parent == null){}
            else if(x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
            y.left = x;
            x.parent = y;
        }
    }

    public static void print(Tree root) {
        int keyLength =  String.valueOf(highestKey(root)).length();
        printLine(root, 0, keyLength);
    }

    public static void printLine(Tree x, int depth, int keyLength) {
        String keySpace = new String(new char[keyLength]).replace("\0", " ");
        String keyLine = new String(new char[keyLength]).replace("\0", "-");        
        String index = new String(new char[depth]).replace("\0", "|" + keySpace) + "└" + keyLine;
        String printString = (x.isRed) ? index + "\u001B[31m" + x.key + "\u001B[37m" : index + x.key;
        System.out.println(printString);
        if(x.left != null) printLine(x.left, depth+1, keyLength); 
        if(x.right != null) printLine(x.right, depth+1, keyLength );
    }

    public static int highestKey(Tree root) {
        while(root.right != null)
            root = root.right;
        return root.key;
    }

    public static int height(Tree root, int height) {
        int left = height, right = height;
        if(root.right != null)
            right = height(root.right, height+1);
        if(root.left != null)
            left = height(root.left, height+1);
        return (left < right) ? right : left;
    }
}