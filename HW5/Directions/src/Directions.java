import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Directions {
    public static void main(String [] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(args[0]);
        System.setIn(fis);
        Scanner s = new Scanner(System.in);
        int nodes = s.nextInt();
        int edges = s.nextInt();
        Graph g = new Graph(nodes, edges);
        int origin = s.nextInt();
        int destination = s.nextInt();
        s.nextLine();
        for (int i = 0; i < edges; i++) {
            String t = s.nextLine();
            g.addEdge(t.split(" "));
        }
        //System.out.println(g.instance);
        int [] distances = g.bfs(origin, destination);
        System.out.println(Arrays.toString(distances));
    }
}

class Graph {
    int numberOfNodes;
    int numberOfEdges;
    ArrayList<Node> instance;

    public Graph(int numberOfNodes, int numberOfEdges) {
        this.numberOfNodes = numberOfNodes;
        this.instance = new ArrayList<>(numberOfNodes);
        for (int i =0 ; i < this.numberOfNodes; i++){
            this.instance.add(new Node("" + i));
        }
        this.numberOfEdges = numberOfEdges;
    }

    public Graph() {
        this.instance = new ArrayList<>();
    }

    public void addEdge(String[] s) {
        int source = Integer.parseInt(s[0]);
        int destination = Integer.parseInt(s[1]);
        this.instance.get(source).neighbors.add("" + destination);
        this.instance.get(destination).neighbors.add("" + source);
        this.instance.get(source).numberOfNeighbors++;
        this.instance.get(destination).numberOfNeighbors++;

    }

    public void addNode(String label){
        this.instance.add(new Node(label));
        this.numberOfNodes++;
    }

    public int[] bfs(int origin, int destination) {
        int [] visited = new int[this.numberOfNodes];
        int [] distances = new int[this.numberOfNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        int dist = 0;
        distances[origin] = dist;
        boolean flag = false;
        LinkedList<String> q = new LinkedList<>();
        visited[origin]++;
        q.add("" + origin);
        String current;
        while (!q.isEmpty()) {
            current = q.remove();
            if (current.equals("" + destination)) {
                flag = true;
                break;
            }
            ArrayList<String> neighbors = this.instance.get(Integer.parseInt(current)).neighbors;
            if (neighbors.size() > 1 && visited[Integer.parseInt(current)] != 0) {
                dist++;
            }
            while (!neighbors.isEmpty()) {
                String neighbor = neighbors.remove(0);
                if (visited[Integer.parseInt(neighbor)] == 0) {
                    if (dist < distances[Integer.parseInt(neighbor)])
                        distances[Integer.parseInt(neighbor)] = dist;
                    visited[Integer.parseInt(neighbor)]++;
                    ArrayList<String> newNeighbours = this.instance.get(Integer.parseInt(neighbor)).neighbors;
                    //newNeighbours.remove(current);
                    if (newNeighbours.size() - 1 > 1) {
                        boolean visitorVisitedFlag = true;
                        for (String nn : newNeighbours) {
                            if(visited[Integer.parseInt(nn)] == 0) {
                                visitorVisitedFlag = false;
                                break;
                            }
                        }
                        if (visitorVisitedFlag == false) {
                            q.add(neighbor);
                        }
                        //distances[Integer.parseInt(neighbor)] = distances[Integer.parseInt(current)]+1;
                    } else {
                        while (newNeighbours.size() -1 == 1) {
                            if (neighbor.equals("" + destination)) {
                                flag = true;
                                break;
                            }
                            dist = distances[Integer.parseInt(neighbor)];
                            String n1 = newNeighbours.get(0);
                            String n2 = newNeighbours.get(1);
                            if (!(visited[Integer.parseInt(n1)] > 0)) {
                                neighbor = n1;
                            } else if (!(visited[Integer.parseInt(n2)] > 0)) {
                                neighbor = n2;
                            } else {
                                break;
                            }
                            distances[Integer.parseInt(neighbor)] = dist;
                            visited[Integer.parseInt(neighbor)]++;
                            newNeighbours = this.instance.get(Integer.parseInt(neighbor)).neighbors;
                        }
                        if (neighbor.equals("" + destination)) {
                            flag = true;
                        }
                        if (flag != true) {
                            if (newNeighbours.size() - 1 > 1)
                                q.add(neighbor);
                            //distances[Integer.parseInt(neighbor)] = distances[Integer.parseInt(current)]++;
                        } else {
                            break;
                        }
                    }
                }
                if (flag == true)
                    break;
            }
        }
        if (flag == true) {
            return distances;
        } else {
            return distances;
        }
    }
}

class Node {
    String label;
    ArrayList<String> neighbors;
    int numberOfNeighbors;

    public Node(String label) {
        this.label = label;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
        String n = this.label;
        String neighbors = this.neighbors.toString();
        return "\n" + n + ": " + neighbors + "\n";
    }
}
