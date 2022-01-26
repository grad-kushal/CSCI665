/**
 * @author		Kushal Kale
 */

import java.util.Scanner;

public class Evensum {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int number = scanner.nextInt();
        if(number > -1){
            for (int i = 0; i < number; i++){
                int additionTerm = scanner.nextInt();
                if(additionTerm > -1 && additionTerm % 2 == 0){
                    sum += additionTerm;
                }
            }
            System.out.println(sum);
        }
    }
}
