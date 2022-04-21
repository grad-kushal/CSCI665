import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class OneWay {
    public static void main(String [] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream(args[0]);
//        System.setIn(fis);
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        MyGraph g = new MyGraph(n);
        s.nextLine();
        for (int i = 1; i <= n; i++) {
            String [] list = s.nextLine().split(" ");
            for (String neighbor : list) {
                if (neighbor.equals("0"))
                    break;
                g.addUnidirectionalEdge(i-1, Integer.parseInt(neighbor)-1);
            }
        }

        //System.out.println(g.adjacencyLists);
        MyGraph reverse = g.reverse();
        //System.out.println(reverse.adjacencyLists);
        ArrayList<ArrayList<Integer>> scc = g.findStronglyConnectedComponents();
        if (scc.size() > 1) {
            int noZeroIn = 0;
            int noZeroOut = 0;
//            for (int i = 0; i < g.v; i++) {
//                int componentOfI = 0;
//                int count = 0;
////                for (ArrayList<Integer> c : scc) {
////                    if(c.contains(i)) {
////                        componentOfI = count;
////                        break;
////                    }
////                    count++;
////                }
//                boolean flag = false;
//                for(int ne : g.adjacencyLists.get(i)) {
//                    if (!scc.get(componentOfI).contains(ne)) {
//                        flag = true;
//                        break;
//                    }
//                }
//                if (flag = false) {
//                    noZeroOut++;
//                }
//            }
            if (noZeroIn == 1 && noZeroOut == 1) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            System.out.println("YES");
        }
    }
}

class MyGraph {
    static int timer = 0;

    int v;
    int e;
    ArrayList<ArrayList<Integer>> adjacencyLists;

    MyGraph (int v, int e) {
        this.v = v;
        this.e = e;
        adjacencyLists = new ArrayList<>(v);
        for (int i = 0; i < this.v; i++) {
            adjacencyLists.add(new ArrayList<Integer>());
        }
    }

    public MyGraph(int v) {
        this.v = v;
        adjacencyLists = new ArrayList<>(v);
        for (int i = 0; i < this.v; i++) {
            adjacencyLists.add(new ArrayList<Integer>());
        }
    }

    void addBiDirectionalEdge(int u, int v) {
        this.addUnidirectionalEdge(u, v);
        this.addUnidirectionalEdge(v, u);
    }

    void addUnidirectionalEdge(int u, int v) {
        this.adjacencyLists.get(u).add(v);
    }

    public MyGraph reverse() {
        MyGraph result = new MyGraph(this.v);
        int count = -1;
        for (ArrayList<Integer> adjList : this.adjacencyLists) {
            count++;
            for (Integer n : adjList) {
                result.adjacencyLists.get(n).add(count);
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> findStronglyConnectedComponents() {
        ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
        int [] finishTimes = this.finishTimesDfs(0);
        int [] finishOrder = new int[v];
        int rank = 0;
        for (int i = 0; i < v; i++) {
            finishOrder[finishTimes[i]-1] = i;
        }
        //System.out.println(Arrays.toString(finishTimes));
        MyGraph rev = this.reverse();
        ArrayList<Integer> revVisited = new ArrayList<>(this.v);
        for (int i = 0; i < this.v; i++) {
            revVisited.add(0);
        }
        int cnt = 0;
        int [] noUse = new int[1];
        for (int i = v-1; i >=0 ; i--) {
            if (revVisited.get(i) == 0) {
                scc.add(rev.dfs(i, revVisited));
            }

        }
        return scc;
    }

    private int[] finishTimesDfs(int source) {
        int [] finishTimes = new int[this.v];
        ArrayList<Integer> visited = new ArrayList<>(this.v);
        //System.out.println(visited.size());
        for (int i = 0; i < this.v; i++) {
            visited.add(0);
        }
        while (visited.contains(0)) {
            this.dfs(visited.indexOf(0), visited, finishTimes);
        }
        return finishTimes;
    }

    private ArrayList<Integer> dfs(int source, ArrayList<Integer> visited, int[] finishTimes) {
        //visited[source]++;
        ArrayList<Integer> res = new ArrayList<>();
        visited.set(source, 1);
        ArrayList<Integer> neighbors= this.adjacencyLists.get(source);
        for (int n : neighbors) {
            if (visited.get(n) == 0) {
                res.add(n);
                dfs(n, visited, finishTimes);
            }
        }
        timer++;
        finishTimes[source] = timer;
        return res;
    }

    private ArrayList<Integer> dfs(int source, ArrayList<Integer> visited) {
        //visited[source]++;
        ArrayList<Integer> res = new ArrayList<>();
        visited.set(source, 1);
        ArrayList<Integer> neighbors= this.adjacencyLists.get(source);
        for (int n : neighbors) {
            if (visited.get(n) == 0) {
                res.add(n);
                dfs(n, visited);
            }
        }
        timer++;
        return res;
    }
}