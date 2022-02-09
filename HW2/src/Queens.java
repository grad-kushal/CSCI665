import java.util.Scanner;

public class Queens {
    public static void main(String [] args) throws Exception{
//        FileInputStream fis = new FileInputStream(new File("/Users/kushalkale/Kushal/CSCI665/HW2/src/input.txt"));
//        System.setIn(fis);
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

    }
}