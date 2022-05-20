package MST;

public class Edge implements Comparable{
    public int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.to = to;
        this.from = from;
        this.cost = cost;
    }

    public int compareTo(Object o) {
        Edge e = (Edge) o;
        if(cost == e.cost)
            return to - e.to;
        return cost - e.cost;
    }
    
}
