import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Babysit {
    public static void main(String [] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(args[0]);
        System.setIn(fis);
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        System.out.println(n);
        ArrayList<BabySittingJob> babySittingJobs = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String[] input = s.nextLine().split(" ");
            babySittingJobs.add(new BabySittingJob(input));
        }
        babySittingJobs.removeIf(b -> b.startTime < 600);
        babySittingJobs.removeIf(b -> b.endTime > 2399);
        Collections.sort(babySittingJobs);
        for (BabySittingJob b : babySittingJobs) {
            System.out.println(b);
        }

        int [][] income = new int[n][n];
    }

    private static int[] P(ArrayList<BabySittingJob> n) {
        int [] pValues  = new int[n.size()];
        for (int i = 1; i < n.size() + 1; i++){
            int c = i-1;
            for (int j = i-1; j >= 0; j--) {
                c--;
                if (n.get(i-1).startTime >= n.get(j).endTime)
                    break;
            }
            pValues[i-1] = c;
        }
        return pValues;
    }
}

class BabySittingJob implements Comparable{
    int day;
    int startTime;
    int endTime;
    int numberOfChildren;
    int value;

    public BabySittingJob(String [] input) {
        this.day = Integer.parseInt(input[0]);
        int s = Integer.parseInt(input[1]);
        if (s >= 0600) {
            this.startTime = s;
        }
        int e = Integer.parseInt(input[2]);
        if (e <=2300) {
            this.endTime = e;
        }
        this.numberOfChildren = Integer.parseInt(input[3]);
        this.value = Integer.parseInt(input[4]) * (this.endTime - this.startTime) / 100;
    }

    @Override
    public String toString() {
        return "BabySittingJob{" +
                "day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", numberOfChildren=" + numberOfChildren +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        BabySittingJob b = (BabySittingJob) o;
        if (this.endTime == b.endTime)
            return 0;
        else if (this.endTime > b.endTime)
            return 1;
        else
            return  -1;
    }
}
