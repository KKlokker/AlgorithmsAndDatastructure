package GraphStuff;

public class Set {
    public Set parent;
    public int rank;
    public int vertex;
    
    public Set(int vertex) {
        parent = this;
        rank = 0;
        this.vertex = vertex;
    }


    public static void link(Set x, Set y) {
        if(x.rank > y.rank)
            y.parent = x;
        else {
            x.parent = y;
            if(x.rank == y.rank) y.rank += 1;
        }
    }

    public static Set find(Set x) {
        if(x.parent != x)
            x.parent = find(x.parent); //Optimizing with path compression
        return x.parent;
    }
    
}
