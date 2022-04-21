import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class MyGraph {
    ArrayList<ArrayList<Edge>> adjacencyList;
    int numberOfNodes;

    public MyGraph(int n) {
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.adjacencyList.add(new ArrayList<>());
        }
        this.numberOfNodes = this.adjacencyList.size();
    }

    public void addEdge(String s) {
        String [] arr = s.split(" ");
        int initiator = Integer.parseInt(arr[0]);
        int recipient = Integer.parseInt(arr[1]);
        int weight = Integer.parseInt(arr[2]);
        this.adjacencyList.get(initiator).add(new Edge(recipient, weight));
    }

    @Override
    public String toString() {
        return "MyGraph{" +
                "adjacencyList=" + adjacencyList +
                '}';
    }

    public ArrayList<Integer> bellmanFord(int source) {
        ArrayList<Integer> distances = new ArrayList<>();

        for (int j = 0; j < this.numberOfNodes; j++) {
            distances.add(Integer.MAX_VALUE);
        }
        distances.set(source, 0);

        for (int i = 0; i < numberOfNodes - 1; i++) {
            int count = 0;
            for (ArrayList<Edge> adjLists : this.adjacencyList) {
                int u = count;
                for (Edge edge : adjLists) {
                    int v = edge.recipient;
                    if (distances.get(v) > distances.get(u) + edge.weight) {
                        distances.set(v, distances.get(u) + edge.weight);
                    }
                }
                count++;
            }
        }
        int count = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for (ArrayList<Edge> adjLists : this.adjacencyList) {
            int u = count;
            for (Edge edge : adjLists) {
                int v = edge.recipient;
                if (distances.get(v) > distances.get(u) + edge.weight) {
                    result.add(edge.recipient);
                }
            }
            count++;
        }
        return result;
    }
}

class Edge {
    int recipient;
    int weight;

    public Edge(int recipient, int weight) {
        this.recipient = recipient;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "recipient=" + recipient +
                ", weight=" + weight;
    }
}

public class Morale {
    public static void main (String [] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream("src/input.txt");
//        System.setIn(fis);
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        //System.out.println(n);
        MyGraph g = new MyGraph(n);
        int m = s.nextInt();
        s.nextLine();
        for (int i = 0; i < m; i++) {
            g.addEdge(s.nextLine());
        }
        ArrayList<Integer> cycleNodes = g.bellmanFord(0);
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < g.numberOfNodes; i++) {
            visited.add(Boolean.FALSE);
        }
        int answer = 0;
        for (Integer v : cycleNodes) {
            dfs(g, v, visited);
        }
        for (Boolean b : visited) {
            if (b) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static void dfs(MyGraph g, Integer v, ArrayList<Boolean> visited) {
        int result = 0;
        visited.set(v, Boolean.TRUE);
        for (Edge e : g.adjacencyList.get(v)) {
            if (!visited.get(e.recipient)) {
                dfs(g, e.recipient, visited);
            }
        }
    }
}
