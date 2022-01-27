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


        /*-------------------INPUT AND PREPROCESSING------------------------------*/
        ArrayList<ArrayList<Integer>> integerPrefList1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> integerPrefList2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> integerPrefList2Inverse = new ArrayList<>();
        ArrayList<ArrayList<Integer>> integerPrefList1Inverse = new ArrayList<>();
        Stack<Integer> freeAskers = new Stack<>();
        Stack<Integer> freeResponders = new Stack<>();

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
                ArrayList<Integer> tempPrefListInverse = new ArrayList<>(n);
                String temp = br.readLine();
                for (String p: temp.split(" ")) {
                    int num = Integer.parseInt(p);
                    tempPrefList.add(num);
                }
                if (i < n){
                    integerPrefList1.add(tempPrefList);
                    for(int j = 0; j<tempPrefList.size(); j++){
                        tempPrefListInverse.add(-1);
                    }
                    for(int j = 0; j<tempPrefList.size(); j++){                                 // Create inverse list
                        tempPrefListInverse.set(tempPrefList.get(j), j);
                    }
                    integerPrefList1Inverse.add(tempPrefListInverse);
                    freeAskers.push(i);
                }
                else {
                    integerPrefList2.add(tempPrefList);
                    for(int j = 0; j<tempPrefList.size(); j++){
                        tempPrefListInverse.add(-1);
                    }
                    for(int j = 0; j<tempPrefList.size(); j++){                                 // Create inverse list
                        tempPrefListInverse.set(tempPrefList.get(j), j);
                    }
                    integerPrefList2Inverse.add(tempPrefListInverse);

                    freeResponders.push(i - n);
                }
            }

            /*-------------------Execute the GS Algorithm------------------------------*/
            int [] result1 = executeGayleShapely(freeAskers, integerPrefList1, integerPrefList2Inverse, currentPartners1, currentPartners2);
            Arrays.fill(currentPartners1, -1);
            Arrays.fill(currentPartners2, -1);

            /*-------------------Execute the GS Algorithm on flipped input------------------------------*/
            int [] result2 = executeGayleShapely(freeResponders, integerPrefList2, integerPrefList1Inverse, currentPartners2, currentPartners1);

            int count = 0;
            for (int r = 0; r<result2.length; r++) {
                int temp = result2[r];
                if(r != result1[temp]){
                    break;
                }
                count++;
            }

            System.out.println(count == n ? "NO" : "YES");

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

    public static int[] executeGayleShapely(Stack<Integer> freeAskers, ArrayList<ArrayList<Integer>> integerPrefList1,
                                           ArrayList<ArrayList<Integer>> integerPrefList2Inverse, int [] currentPartners1, int [] currentPartners2) {
        while (!freeAskers.isEmpty()){
            int asker = freeAskers.pop();                                           //take the 1st free asker
            ArrayList<Integer> askerPrefList = integerPrefList1.get(asker);
            for (int responder : askerPrefList){                                    // loop through asker's pref list
                ArrayList<Integer> responderPrefListInverse = null;

                if (!isAlreadyAsked(responder)) {                                   //not already asked
                    responderPrefListInverse = integerPrefList2Inverse.get(responder);
                    askerPrefList.set(askerPrefList.indexOf(responder), -1);
                    int currentMatchFlag = getMatch(currentPartners2, responder);
                    if (currentMatchFlag == -1){                                    //responder is free
                        currentPartners1[asker] = responder;
                        currentPartners2[responder] = asker;
                        break;
                    }
                    else if (responderPrefListInverse.get(asker) < currentMatchFlag && currentMatchFlag != -1){
                                                                                    //responder prefers current asker
                        currentPartners1[asker] = responder;
                        currentPartners2[responder] = asker;
                        currentPartners1[currentMatchFlag] = -1;
                        freeAskers.push(currentMatchFlag);
                        break;
                    }
                    else {                                                          //responder rejects current asker
                        continue;
                    }
                }
            }
        }
        return currentPartners1;
    }
}
