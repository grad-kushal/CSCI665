/**
 * @author		Kushal Kale
 * @author      Abhishek Shah
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Match {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> integerPrefList1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> integerPrefList2 = new ArrayList<>();
        Stack<Integer> freeAskers = new Stack<>();

        try{
            Scanner scanner = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = scanner.nextInt();

            int [] currentPartners1 = new int[n];
            int [] currentPartners2 = new int[n];
            Arrays.fill(currentPartners1, -1);
            Arrays.fill(currentPartners2, -1);

            for (int i = 0; i < 2*n; i++) {
                ArrayList<Integer> tempPrefList = new ArrayList<>();
                String temp = br.readLine();
                for (String p: temp.split(" ")) {
                    tempPrefList.add(Integer.parseInt(p));
                }
                if (i < n){

                    integerPrefList1.add(tempPrefList);
                    freeAskers.push(i);
                }
                else {
                    integerPrefList2.add(tempPrefList);
                }
            }

//            for (ArrayList<Integer> arr: integerPrefList1) {
//                for(int i: arr){
//                    System.out.print(i + " ");
//                }
//                System.out.println("");
//            }
//            for (ArrayList<Integer> arr: integerPrefList2) {
//                for(int i: arr){
//                    System.out.print(i + " ");
//                }
//                System.out.println("");
//            }

            while (!freeAskers.isEmpty()){
                int asker = freeAskers.pop();                                           //take the 1st free asker
                System.out.println("LOGGER: Asker :" + asker);
                ArrayList<Integer> askerPrefList = integerPrefList1.get(asker);
                for (int responder : askerPrefList){                                    // loop through asker's pref list
                    System.out.println("LOGGER: Responder: " + responder);
                    ArrayList<Integer> responderPrefList = null;
                    if (responder != -1)
                        responderPrefList = integerPrefList2.get(responder);
                    if (!isAlreadyAsked(responder)) {                                   //not already asked
                        askerPrefList.set(askerPrefList.indexOf(responder), -1);
                        int currentMatchFlag = getMatch(currentPartners2, responder);
                        System.out.println("currentMatchFlag: " + currentMatchFlag);
                        if (currentMatchFlag == -1){                                    //responder is free
                            System.out.println("IF");
                            currentPartners1[asker] = responder;
                            currentPartners2[responder] = asker;
                            break;
                        }
                        else if (responderPrefList.indexOf(asker) < currentMatchFlag && currentMatchFlag != -1){
                            System.out.println("ELSE IF");                              //responder prefers current asker
                            currentPartners1[asker] = responder;
                            currentPartners2[responder] = asker;
                            currentPartners1[currentMatchFlag] = -1;
                            freeAskers.push(currentMatchFlag);
                            break;
                        }
                        else {                                                          //responder rejects current asker
                            System.out.println("ELSE");
                            continue;
                        }
                    }
                }
                for (int m: currentPartners1) {
                    System.out.println(m);
                }
            }
            for (int m: currentPartners1) {
                System.out.println(m);
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
