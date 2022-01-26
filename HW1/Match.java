import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Match {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> integerPrefList1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> integerPrefList2 = new ArrayList<>();
        Stack<Integer> freeAskers = new Stack<>();

        try{
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();

            int [] currentPartners1 = new int[n];
            int [] currentPartners2 = new int[n];

            for (int i = 0; i < 2*n; i++) {
                ArrayList<Integer> tempPrefList = new ArrayList<>();
                String temp = scanner.nextLine();
                for (String p: temp.split(" ")) {
                    tempPrefList.add(Integer.parseInt(p));
                }
                if (i < n){

                    integerPrefList1.add(tempPrefList);
                }
                else {
                    integerPrefList2.add(tempPrefList);
                }
            }

            while (!freeAskers.isEmpty()){
                int asker = freeAskers.pop();
                ArrayList<Integer> askerPrefList = integerPrefList1.get(asker);
                for (int responder : askerPrefList){
                    if (!isAlreadyAsked(responder)) {
                        if (getMatch(currentPartners2, responder) == 0){            //responder is free
                            currentPartners1[asker] = responder;
                            currentPartners2[responder] = asker;
                        }
                        else if (getMatch(currentPartners2, responder) !=0 &&  ){

                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getMatch(int [] currentPartners, int i){
        return currentPartners[i];
    }

    public static boolean isAlreadyAsked(int i){
        if (i == -1)
            return true;
        else
            return false;
    }
}
