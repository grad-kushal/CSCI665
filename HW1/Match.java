import java.util.ArrayList;
import java.util.Scanner;

public class Match {
    public static void main(String[] args) {
        ArrayList<String> plist1 = new ArrayList<>();
        ArrayList<String> plist2 = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            for (int i = 0; i < 2*n; i++) {
                if (i < n){
                    plist1.add(scanner.nextLine());
                }
                else {
                    plist2.add(scanner.nextLine());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
