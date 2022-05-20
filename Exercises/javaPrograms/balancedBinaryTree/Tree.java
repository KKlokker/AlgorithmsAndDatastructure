package balancedBinaryTree;

public class Tree {
    public Tree parent, left, right;
    public int key;
    public String data;
    public boolean isRed;

    public Tree(Tree parent, Tree left, Tree right, int key, String data, boolean isRed) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.data = data;
        this.key = key;
        this.data = data;
        this.isRed = isRed;
    }
}
