package GraphStuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kruskal {
    //a,b,c,d,e,f,g,h,i,j,k
    //0,1,2,3,4,5,6,7,8,9,10
    public static void main(String[] args) {
        Graph g = new Graph(true);
        /*
        g.add(0, 1, 4);
        g.add(0, 7, 8);
        g.add(1, 7, 11);
        g.add(1, 2, 8);
        g.add(2, 3, 7);
        g.add(2, 5, 4);
        g.add(2, 8, 2);
        g.add(3, 4, 9);
        g.add(3, 5, 14);
        g.add(4, 5, 10);
        g.add(5, 6, 2);
        g.add(6, 7, 1);
        g.add(6, 8, 6);
        g.add(7, 8, 7);
        */
        g.add(0, 1, 1);
        g.add(0, 3, 1);
        g.add(1, 4, 1);
        g.add(4, 0, -3);
        g.add(2, 4, 1);
        g.add(2, 5, 1);
        g.add(3, 1, 1);
        g.add(4, 3, 1);
        /*
        int cost = 0;
        for(Edge e: prim(g,0)) {
            System.out.println(e.from + " - " + e.to + ": " + e.cost);
            cost += e.cost;
        }
        System.out.println("Cost: " + cost);
       
        for(int[] info: DFS(g))
            System.out.println(info[0] + ": " + info[1] + " - " + info[2]);
      
        for(int info: topologicalSort(g))
            System.out.println(info);

        g.print();
        
        for(List<Integer> scc: SCC(g)) {
            System.out.print("Connected: ");
            for(int i: scc)
                System.out.print(i + ", ");
            System.out.println("");
        }       
        */

        for(int i: relaxHandler(g,0))
            System.out.println("Length is: " + i);
        System.out.println(bellmanFord(g, 0));
    }

    public static List<List<Integer>> SCC(Graph graph) {
        List<List<Integer>> sccs = new ArrayList<>();

        List<Integer> top = topologicalSort(graph);
        Graph reversed = graph.reverse();

        boolean[] visited = new boolean[graph.size()+1];
        Arrays.fill(visited, false);

        for(int i: top) 
            if(!visited[i])sccs.add(DFSVisitForSCC(reversed,i,visited));
        
        return sccs;
    }

    public static List<Integer> relaxHandler(Graph graph, int start) {
        List<Integer> shortestPath = new ArrayList<>();
        for(int i = 0; i <= graph.size; i++)
            shortestPath.add(99999);
        shortestPath.set(start,0);
        
        for(int x = 0; x < graph.size; x++)
            for(int y = 0; y < graph.size(x); y++)
                relax(graph, x, y, shortestPath);
        return shortestPath;
    }

    
    public static void relax(Graph graph,int x, int y,List<Integer> SP) {
        if(graph.get(x, y) != null && SP.get(y) > SP.get(x) + graph.get(x, y))
            SP.set(y, SP.get(x) + graph.get(x, y));        
    }

    public static boolean bellmanFord(Graph graph, int vertex) {
        List<Integer> shortestPath = relaxHandler(graph, vertex);
        for(int x = 0; x < graph.size; x++)
            for(int y = 0; y < graph.size(x); y++)
                if(graph.get(x, y) != null && shortestPath.get(y) > shortestPath.get(x) + graph.get(x, y))
                    return false;
        return true;
    }
    //bellman ford and dijkstras

    public static List<Integer> topologicalSort(Graph graph) {
        List<int[]> order = new ArrayList<>();
        
        for(int[] info: DFS(graph))
            if(order.size() == 0) order.add(info);
            else 
                for(int i = 0; i < order.size(); i++) //quick and dirty linear sort
                    if(order.get(i)[2] < info[2]) {order.add(i,info); i = order.size();}
        List<Integer> orderedVertex = new ArrayList<>();
        for(int[] info: order)
            orderedVertex.add(info[0]);
        return orderedVertex;
    }

    public static List<int[]> DFS(Graph graph) {
        List<int[]> dfs = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()+1];
        Arrays.fill(visited, false);
        AtomicInteger time = new AtomicInteger(0);
        
        for(int i = 0; i < graph.size; i++)
            if(!visited[i])
                dfs.add(DFSVisit(time, graph, i,visited, dfs));
        return dfs;
    }

    private static int[] DFSVisit(AtomicInteger time, Graph graph, int vertex,boolean[] visited, List<int[]> dfs) {
        int[] edgeInfo = new int[3]; //vertex#, distance, finish
        edgeInfo[0] = vertex;
        time.incrementAndGet();
        edgeInfo[1] = time.get();
        visited[vertex] = true;
        for(int i = 0; i < graph.size(vertex); i++)
            if(graph.get(vertex, i) != null && !visited[i]) 
                dfs.add(DFSVisit(time, graph, i,visited, dfs));
        
        time.incrementAndGet();
        edgeInfo[2] = time.get();
        return edgeInfo;
    }

    private static List<Integer> DFSVisitForSCC(Graph graph, int vertex,boolean[] visited) {
        List<Integer> scc = new ArrayList<>();
        scc.add(vertex);
        visited[vertex] = true;
        for(int i = 0; i < graph.size(vertex); i++)
            if(graph.get(vertex, i) != null && !visited[i]) 
            scc = Stream.of(DFSVisitForSCC(graph, i,visited),scc).flatMap(Collection::stream).collect(Collectors.toList());
        
        return scc;
    }

    public static List<Edge> kruskal(Graph graph) {
        List<Set> vertex = new ArrayList<>();
        for(int i = 0; i<=graph.size; i++)
            vertex.add(new Set(i));
        List<Edge> MST = new ArrayList<>();
        List<Edge> sortedEdge = graph.sortedEdge();
        for(Edge edge: sortedEdge) {
            if(Set.find(vertex.get(edge.from)) != Set.find(vertex.get(edge.to))) {
                MST.add(edge);
                Set.link(Set.find(vertex.get(edge.from)),Set.find(vertex.get(edge.to)));
            }
        }
        return MST;
    }

    public static List<Edge> prim(Graph graph, int startVertex) {
        int[] discover = new int[graph.size+1];
        Boolean[] checked = new Boolean[graph.size+1];
        Arrays.fill(checked, false);
        List<Edge> MST = new ArrayList<>();
        PriorityBlockingQueue<Edge> vertexQueue = new PriorityBlockingQueue<>();
        do {
            for(int i = 0; i < graph.size(startVertex); i++) 
                if(graph.get(startVertex, i) != null && !checked[i])
                vertexQueue.add(new Edge(startVertex,i,graph.get(startVertex, i)));
            checked[startVertex] = true;
            Edge best;
            do
                best = vertexQueue.poll();
            while(checked[best.to] && vertexQueue.size() > 0);

            if(!checked[best.to]) {
                MST.add(best);
                discover[best.to] = best.from;
                startVertex = best.to;
            }            
        }
        while(!vertexQueue.isEmpty());
        for(int i = 0; i < discover.length; i++)
            System.out.println(i + " was discovered by " + discover[i]);
        return MST;
    }

}
