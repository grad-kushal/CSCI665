import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Queens {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int [] r = new int[n];
        int [] c = new int[n];
        int [] d1 = new int[2*n - 1];
        int [] d2 = new int[2*n -1];

        for (int i = 0; i < n; i++) {
            String [] queenPosition = scanner.nextLine().split(" ");
            int xTemp = Integer.parseInt(queenPosition[0])-1;
            int yTemp = Integer.parseInt(queenPosition[1])-1;
            r[xTemp]++;
            c[yTemp]++;
            d1[xTemp - yTemp + (n-1)]++;
            d2[xTemp + yTemp]++;
        }
        boolean result = true;
        for (int i = 0; i < 2 * n - 1; i++) {
            if (d1[i] > 1 || d2[i] > 1) {
                result = false;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (r[i] != 1 || c[i] != 1) {
                result = false;
                break;
            }
        }
        System.out.println(result == true ? "YES" : "NO");
//        System.out.println(Arrays.toString(r));
//        System.out.println(Arrays.toString(c));
//        System.out.println(Arrays.toString(d1));
//        System.out.println(Arrays.toString(d2));
//        Board b = new Board(n);
//        for (int i = 0; i < n; i++) {
//            String [] queenPosition = scanner.nextLine().split(" ");
//            boolean flag = b.placeQueen(Integer.parseInt(queenPosition[0]), Integer.parseInt(queenPosition[1]));
//            if (flag == false) {
//                break;
//            }
//            b.printBoard();
//        }
//        b.printBoard();

    }
}

//class Board {
//    public Board(int size) {
//        this.layout = new char[size][size];
//        this.size = size;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public boolean placeQueen(int x, int y) {
//        int hIndex = x-1;
//        int vIndex = y-1;
//        boolean result = false;
//        if (this.layout[hIndex][vIndex] != '.' && this.layout[x-1][y-1] != 'Q') {
//            this.layout[hIndex][vIndex] = 'Q';
//            result = true;
//        }
////        for (int i = 0; i < this.size; i++) {
////            this.layout[hIndex][(vIndex + i + 1)%size] = '.';
////            this.layout[(hIndex + i + 1)%size][vIndex] = '.';
////            this.layout[(hIndex + i + 1)%size][(vIndex + i + 1)%size] = '.';
////        }
//        return result;
//    }
//
//    int size;
//    char [][] layout;
//
//    public void printBoard() {
//        for (int i = 0; i < this.size; i++) {
//            for (int j = 0; j < this.size; j++) {
//                System.out.print("\t" + this.layout[i][j]);
//            }
//            System.out.println("");
//        }
//    }
//}

