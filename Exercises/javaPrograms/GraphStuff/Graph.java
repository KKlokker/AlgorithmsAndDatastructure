package GraphStuff;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<List<Integer>> matrix;
    private boolean oriented;
    public int size;

    public Graph(boolean oriented){
        matrix = new ArrayList<>();
        this.oriented = oriented;
        this.size = 0;
    }

    private Graph(Graph g, List<List<Integer>> matrix) {
        this.matrix = matrix;
        size = g.size;
        oriented = g.oriented;
    }

    public void print() {
        int longestY = 0;
        for(List<Integer> list: matrix)
            longestY = (list.size() > longestY) ? list.size() : longestY;
        for(int i = 0; i < longestY; i++){
            for(List<Integer> list: matrix) 
                if(i<list.size()) {
                    if(list.get(i) != null) System.out.print(" " + list.get(i) + " ");
                    else System.out.print(" x ");
                }
                else System.out.print(" n ");
            
            System.out.println("");
        }
    }

    public Graph reverse() {
        int longestY = 0;
        for(List<Integer> list: matrix)
            longestY = (list.size() > longestY) ? list.size() : longestY;
            
        List<List<Integer>> reversed = new ArrayList<>();
        for(int x = 0; x < longestY; x++) {
            List<Integer> connections = new ArrayList<>();
            for(int y = 0; y  < matrix.size(); y++)
                if(matrix.get(y).size() > x)connections.add(this.get(y, x));
                else connections.add(null);
            reversed.add(connections);
        }

        return new Graph(this, reversed);
    }

    public void add(int x, int y, int value) {
        int highest = (x > y) ? x : y;
        size = (size < highest) ? highest : size;
        while(matrix.size() <= highest)
            matrix.add(new ArrayList<>());
        while(matrix.get(x).size() <= highest) 
            matrix.get(x).add(null);
        while(matrix.get(y).size() <= highest) 
            matrix.get(y).add(null);
        matrix.get(x).set(y, value);
        if(!oriented) matrix.get(y).set(x, value);
    }

    public Integer get(int x, int y) {
        if(matrix.size() <= x || matrix.get(x).size() <= y)
            return null;
        return matrix.get(x).get(y);
    }

    public int size() {
        return size;
    }

    public int size(int i) {
        return matrix.get(i).size();
    }

    public List<Edge> sortedEdge() {
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < matrix.size(); i++)
            for(int j = 0; j < i && !oriented || oriented && j < matrix.get(i).size(); j++) {
                if(matrix.get(i).get(j) != null)
                insert(edges,new Edge(i,j,matrix.get(i).get(j)));
            }
        return edges;
    }

    private void insert(List<Edge> edges, Edge edge) {
        if(edges.size() == 0) edges.add(edge);
        for(int i = 0; i<edges.size();i++)
            if(edge.cost < edges.get(i).cost){
                edges.add(i, edge);
                return;
            }
        edges.add(edge);
    }
}
