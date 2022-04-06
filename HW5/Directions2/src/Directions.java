import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Directions {
    public static void main(String [] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream(args[0]);
//        System.setIn(fis);
        Scanner s = new Scanner(System.in);
        int nodes = s.nextInt();
        int edges = s.nextInt();
        MyGraph g = new MyGraph(nodes, edges);
        int origin = s.nextInt();
        int destination = s.nextInt();
        s.nextLine();
        for (int i = 0; i < edges; i++) {
            String [] t = s.nextLine().split(" ");
            g.addEdge(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
        }
        int [] distances = g.bfs(origin, destination);
        System.out.println(distances[destination]);
    }
}

class MyGraph {
    int v;
    int e;
    ArrayList<ArrayList<Integer>> adjancencyLists;

    MyGraph (int v, int e) {
        this.v = v;
        this.e = e;
        adjancencyLists = new ArrayList<>(v);
        //System.out.println(adjancencyLists.size());
        for (int i = 0; i < this.v; i++) {
            adjancencyLists.add(new ArrayList<Integer>());
        }
        //System.out.println(adjancencyLists.size());
    }

    void addEdge(int u, int v) {
        this.adjancencyLists.get(u).add(v);
        this.adjancencyLists.get(v).add(u);
    }

    int [] bfs(int source, int destination) {
        boolean flag = false;
        int [] distances = new int[v];
        boolean [] visited = new boolean[this.v];
        LinkedList<Integer> q = new LinkedList<>();

        Arrays.fill(distances, Integer.MAX_VALUE);
        visited[source] = true;
        int dist = 0;
        q.add(source);
        int current;
        int level = 0;
        while (!q.isEmpty()) {
            current = q.remove();
            if (current == destination) {
                flag = true;
                break;
            }
            if (distances[current] > dist)
                distances[current] = dist;
            ArrayList<Integer> neighbors = this.adjancencyLists.get(current);
            int numberOfNeighbors;

            if (current == source)
                numberOfNeighbors = neighbors.size() + 1;
            else
                numberOfNeighbors = neighbors.size();
            //boolean updated = false;
            if (numberOfNeighbors - 1 > 1 && visited[current]) {
                dist = distances[current] + 1;
            }
            for (int neighbor : neighbors) {
                if (visited[neighbor])
                    continue;
                if (distances[neighbor] > dist) {
                    distances[neighbor] = dist;
                }
                ArrayList<Integer> subNeighbors = this.adjancencyLists.get(neighbor);
                int numberOfSubNeighbors = subNeighbors.size();
                if (numberOfSubNeighbors - 1 > 1) {
                    if (!visited[neighbor]) {
                        q.add(neighbor);
                        visited[neighbor] = true;
                    }
                } else if (numberOfSubNeighbors - 1 == 1) {
                    boolean subNeighborsVisitedFlag = false;
                    while (numberOfSubNeighbors - 1 == 1 && !subNeighborsVisitedFlag) {
                        if (visited[neighbor])
                            continue;
                        visited[neighbor] = true;
                        int n1 = subNeighbors.get(0);
                        int n2 = subNeighbors.get(1);
                        if (!visited[n1]) {
                            distances[n1] = distances[neighbor];
                            neighbor = n1;
                        } else if (!visited[n2]) {
                            distances[n2] = distances[neighbor];
                            neighbor = n2;
                        } else {
                            subNeighborsVisitedFlag = true;
                        }
                        if (subNeighborsVisitedFlag == false) {
                            if (neighbor == destination) {
                                flag = true;
                                break;
                            }
                            subNeighbors = this.adjancencyLists.get(neighbor);
                            numberOfSubNeighbors = subNeighbors.size();
                        }
                    }
                    if (!visited[neighbor]) {
                        if (flag == false) {
                            q.add(neighbor);
                            visited[neighbor] = true;
                        } else {
                            flag = true;
                            break;
                        }

                    }
                }
            }
            level++;
            if (flag == true) {
                break;
            }
        }
        return distances;
    }
}