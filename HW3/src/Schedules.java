/**
 * @author              Kushal Kale
 * @author              Bhavin Oza
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Schedules {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println(calculateNumberOfSchedules(n));
        //System.out.println(8);

        BigInteger[] noOfSchedules = new BigInteger[n+1];
        noOfSchedules[0] = BigInteger.valueOf(1);
        noOfSchedules[1] = BigInteger.valueOf(2);
        noOfSchedules[2] = BigInteger.valueOf(7);
        for (int i = 3; i <= n; i++) {
            noOfSchedules[i] = noOfSchedules[i-1].multiply(BigInteger.valueOf(5));
        }
        System.out.println(noOfSchedules[n]);

    }

    private static Integer getNumberOfSchedules(int k, Integer [] n) {
        Integer result = 0;
        if (k == 0) {
            result = 1;
        } else if (k == 1) {
            result = 2;
        } else if (k == 2){
            result = 7;
        }
        return result;
    }

    private static int calculateNumberOfSchedules(int n) {
        int remainderHours = n%3, multiplier = 0;
        if (remainderHours == 0) {
            multiplier = 1;
        } else if (remainderHours == 1){
            multiplier = 2;
        } else {
            multiplier = 5;
        }
        return (int)Math.pow(13, (n/3)) * multiplier;
    }
}
