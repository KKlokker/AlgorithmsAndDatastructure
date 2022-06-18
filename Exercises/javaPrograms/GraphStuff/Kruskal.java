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
    //a,b,c,d,e,f,g,h,i,j,k
    //0,1,2,3,4,5,6,7,8,9,10
    //a,d,e,g,b,c,f,h,i DFS
    /*
        g.add(0, 3, 4);
        g.add(0, 6, 8);
        g.add(1, 2, 11);
        g.add(2, 1, 8);
        g.add(2, 5, 7);
        g.add(3, 4, 4);
        g.add(3, 6, 2);
        g.add(4, 3, 9);
        g.add(4, 0, 14);
        g.add(5, 1, 10);
        g.add(5, 6, 2);
        g.add(6, 7, 1);
        g.add(6, 8, 6);
        g.add(6, 1, 7);
        g.add(7, 6, 7);
        g.add(7, 3, 7);
        g.add(7, 5, 7);
        g.add(8, 0, 7);
        g.add(8, 1, 7);
        /*
        g.add(0, 1, 1);
        g.add(0, 3, 1);
        g.add(1, 4, 1);
        g.add(4, 0, -3);
        g.add(2, 4, 1);
        g.add(2, 5, 1);
        g.add(3, 1, 1);
        g.add(4, 3, 1);
        */

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
        Graph ug = new Graph(true);
        //a,b,c,d,e,f,g,h,i
        //0,1,2,3,4,5,6,7,8
        /* G1
        ug.add(1, 0, 8);
        ug.add(1, 2, 9);
        ug.add(1, 5, 10);
        ug.add(2, 3, 0);
        ug.add(2, 6, 6);
        ug.add(3, 7, 7);
        ug.add(4, 3, 3);
        ug.add(4, 8, 11);
        ug.add(5, 0, 12);
        ug.add(6, 5, 2);
        ug.add(6, 1, 5);
        ug.add(7, 2, 5);
        ug.add(7, 8, 5);
        ug.add(8, 3, 5);
        */
        //a,b,c,d,e,f,g,h,i
        //0,1,2,3,4,5,6,7,8
        /* G2
        ug.add(0, 1, 8);
        ug.add(1, 5, 9);
        ug.add(1, 6, 10);
        ug.add(2, 1, 0);
        ug.add(2, 6, 6);
        ug.add(2, 3, 7);
        ug.add(3, 7, 3);
        ug.add(4, 8, 11);
        ug.add(4, 3, 12);
        ug.add(5, 0, 2);
        ug.add(5, 6, 5);
        ug.add(7, 2, 5);
        ug.add(7, 8, 5);
        ug.add(8, 3, 5);
        */
        //a,b,c,d,e,f,g,h,i
        //0,1,2,3,4,5,6,7,8
        Graph g3 = new Graph(false);        
        g3.add(0, 1, 17);
        g3.add(0, 4, 11);
        g3.add(0, 3, 8);
        g3.add(4, 1, 5);
        g3.add(4, 2, 3);
        g3.add(4, 3, 18);
        g3.add(2, 1, 14);
        g3.add(2, 3, 25);
        //a,b,c,d,e,f,g,h,i
        //0,1,2,3,4,5,6,7,8
        Graph g4 = new Graph(true);
        g4.add(0, 4, 17);
        g4.add(1, 0, 17);
        g4.add(1, 2, 11);
        g4.add(2, 8, 8);
        g4.add(3, 2, 5);
        g4.add(4, 3, 3);
        g4.add(4, 8, 18);
        g4.add(5, 4, 14);
        g4.add(5, 8, 25);
        g4.add(6, 5, 25);
        g4.add(6, 7, 25);
        g4.add(7, 0, 25);
        g4.add(8, 1, 25);
        g4.add(8, 3, 25);
        g4.add(8, 6, 25);
        g4.add(8, 7, 25);
        
        /*
        g4.add(2,1,1);
        g4.add(8,4,1);
        g4.add(6,1,2);
        g4.add(5,0,3);
        g4.add(1,0,4);
        g4.add(5,1,5);
        g4.add(6,2,6);
        g4.add(7,3,6);
        */
        char[] alf = new char[]{'a','b','c','d','e','f','g','h','i'};
        //int[] d = dijkstras(g, 0);
        //for(int i = 0; i < d.length; i++)
        //    System.out.println(alf[i] + " " + d[i]);
        /*
        System.out.println("SCC");
        for(List<Integer> l: SCC(ug)) {
            for(int d: l) System.out.print(d + ", ");
            System.out.println();
        }
        */
        //int[][] d = BFS(ug,2);
        //for(int i =0; i<d.length; i++)
        //    System.out.println(i + ": " + d[i][0] + " - " + d[i][1]);
        /*
        List<Edge> e = kruskal(g3);
        for(Edge b: e) 
            System.out.println(b.from + " - " + b.to);
            */
        /*
        List<Edge> l = prim(g3, 0);
        for(Edge e: l)
            System.out.println(alf[e.from] + " - "  + alf[e.to] + ": " + e.cost);
        */
        for(Integer[] i: DFS(g4,8))
            System.out.println(alf[i[0]] + ": " + i[1] + " - " + i[2]);
        
                
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

    public static int[] relaxHandler(Graph graph, int start) {
        int[] shortestPath = new int[graph.size()+1];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        
        for(int x = 0; x < graph.size; x++)
            for(int y = 0; y < graph.size(x); y++)
                relax(graph, x, y, shortestPath);
        return shortestPath;
    }

    public static int test = 0;
    public static void relax(Graph graph,int x, int y,int[] SP) {
        System.out.println(test++);
        if(graph.get(x, y) != null && SP[y] > SP[x] + graph.get(x, y)) 
            SP[y] =  SP[x] + graph.get(x, y);
    }

    public static boolean bellmanFord(Graph graph, int vertex) {
        int[] shortestPath = relaxHandler(graph, vertex);
        for(int x = 0; x < graph.size; x++)
            for(int y = 0; y < graph.size(x); y++)
                if(graph.get(x, y) != null && shortestPath[y] > shortestPath[x] + graph.get(x, y))
                    return false;
        return true;
    }

    public static int[] dijkstras(Graph graph, int start) {
        PriorityBlockingQueue<verticeAndDistance> vertexQueue = new PriorityBlockingQueue<>();
        int[] shortestPath = new int[graph.size()+1];
        List<verticeAndDistance> vertices = new ArrayList<>();

        for(int i = 0; i < graph.size()+1; i++) {
            vertices.add(new verticeAndDistance(i, 9999+i)); //+i to force order
            vertexQueue.add(vertices.get(i));
        }
        vertices.get(start).distance = 0;
        do{
            verticeAndDistance v;
            try {
                v = vertexQueue.take();
                //System.out.println("Looking at " + v.vertice);
                for(int i = 0; i < graph.size(v.vertice); i++) {
                    if(graph.get(v.vertice, i) != null && vertices.get(i).distance > v.distance + graph.get(v.vertice, i)) {
                        vertices.get(i).distance =  v.distance + graph.get(v.vertice, i);
                        System.out.println("Used: " + v.vertice + " - " + i);
                    }
                    
                    //else if(graph.get(v.vertice, i) != null) System.out.println("Did not use: " + v.vertice + " - " + i);
                }
          
            } catch (InterruptedException e) {
                e.printStackTrace();
            }                      
        }
        while(vertexQueue.size() > 0);
        for(int i = 0; i < vertices.size(); i++)
            shortestPath[i] = vertices.get(i).distance;
        return shortestPath;
    }

    static class verticeAndDistance implements Comparable {
        public int vertice, distance;
        
        public verticeAndDistance(int vertice, int distance) {
            this.vertice = vertice;
            this.distance = distance;
        }

        public int compareTo(Object v) {
            return this.distance - ((verticeAndDistance) v).distance;
        }

    }

    public static List<Integer> topologicalSort(Graph graph) {
        List<Integer[]> order = new ArrayList<>();
        
        for(Integer[] info: DFS(graph, 0))
            if(order.size() == 0) order.add(info);
            else 
                for(int i = 0; i < order.size(); i++) //quick and dirty linear sort
                    if(order.get(i)[2] < info[2]) {order.add(i,info); i = order.size();}
        List<Integer> orderedVertex = new ArrayList<>();
        for(Integer[] info: order)
            orderedVertex.add(info[0]);
        return orderedVertex;
    }

    public static Integer[][] DFS(Graph graph, int start) {
        Integer[][] dfs = new Integer[graph.size()+1][3];
        boolean[] visited = new boolean[graph.size()+1];
        Arrays.fill(visited, false);
        AtomicInteger time = new AtomicInteger(0);
        int end = (start == 0) ? graph.size() : start -1;
        for(int i = start; i <= start; i++)
            if(!visited[i])
                DFSVisit(time, graph, i,visited, dfs);
        return dfs;
    }

    private static void DFSVisit(AtomicInteger time, Graph graph, int vertex,boolean[] visited, Integer[][] dfs) {
        //[vertex][discovery][finish]
        dfs[vertex][0] = vertex;
        time.incrementAndGet();
        dfs[vertex][1] = time.get();
        visited[vertex] = true;
        for(int i = 0; i < graph.size(vertex); i++)
            if(graph.get(vertex, i) != null && !visited[i]) {
                DFSVisit(time, graph, i,visited, dfs);
                System.out.println("Tree edge: " +  vertex + " - " + i);
            }
            else if(graph.get(vertex, i) != null) {
                if(dfs[i][2] != null) {
                    if(dfs[vertex][1] < dfs[i][1]) 
                        System.out.println("Forward edge: " + vertex + " - " + i);
                    else 
                        System.out.println("Cross Edge: " + vertex + " - " + i);
                }
                else 
                    System.out.println("Back edge: " + vertex + " - " + i);
            }
        time.incrementAndGet();
        dfs[vertex][2] = time.get();
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

    private static int[][] BFS(Graph graph, int start) {
        //[distance][foundBy]
        int[][] bfs = new int[graph.size()+1][2];
        bfs[start][0] = 0;
        bfs[start][1] = -1;
        boolean[] visited = new boolean[graph.size()+1];
        Arrays.fill(visited, false);
        visited[start] = true;
        List<Integer> queue = new ArrayList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int u = queue.remove(0);
            System.out.print(u + ", ");
            for(int i = 0; i <= graph.size(u); i++) 
                if(graph.get(u, i) != null 
                && !visited[i]) {
                    visited[i] = true;
                    bfs[i][0] = bfs[u][0]+1;
                    if(bfs[i][0] == 3) System.out.println("Found: " +i);
                    bfs[i][1] = u;
                    queue.add(i);
                }
        }
        System.out.println();
        return bfs;
    }

    public static List<Edge> kruskal(Graph graph) {
        List<Set> vertex = new ArrayList<>();
        for(int i = 0; i<=graph.size; i++)
            vertex.add(new Set(i));
        List<Edge> MST = new ArrayList<>();
        List<Edge> sortedEdge = graph.sortedEdge();
        for(Edge edge: sortedEdge) {
            System.out.println("g4.add("+edge.from+","+edge.to+","+edge.cost+");");
            if(Set.find(vertex.get(edge.from)) != Set.find(vertex.get(edge.to))) {
                MST.add(edge);
                Set.link(Set.find(vertex.get(edge.from)),Set.find(vertex.get(edge.to)));
            }
            else {
                //System.out.println("Did not use: " + edge.from + " - " + edge.to);
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
                System.out.println("Took this from the queue: " + best.from + " - " + best.to);
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
