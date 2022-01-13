import java.util.Scanner;

public class Cubes {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        for (int i =0; Math.pow(i, 3) < number; i++){
            System.out.println(i*i*i);
        }
    }
}
