import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OneWay {
    public static void main(String [] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(args[0]);
        System.setIn(fis);
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

        System.out.println(g.adjancencyLists);
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
        for (int i = 0; i < this.v; i++) {
            adjancencyLists.add(new ArrayList<Integer>());
        }
    }

    public MyGraph(int v) {
        this.v = v;
        adjancencyLists = new ArrayList<>(v);
        for (int i = 0; i < this.v; i++) {
            adjancencyLists.add(new ArrayList<Integer>());
        }
    }

    void addBiDirectionalEdge(int u, int v) {
        this.addUnidirectionalEdge(u, v);
        this.addUnidirectionalEdge(v, u);
    }

    void addUnidirectionalEdge(int u, int v) {
        this.adjancencyLists.get(u).add(v);
    }
}