import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Boss {
    public static void main(String[] args) throws Exception {
        //FileInputStream fis = new FileInputStream("/Users/kushalkale/Kushal/CSCI665/HW2/src/input5.txt");
        //System.setIn(fis);
        ArrayList<Task> taskArrayList = new ArrayList<>();
        PriorityQueue<Task> taskList = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();                                              //INPUT SIZE
        scanner.nextLine();                                                     //SKIP THE STRAY NEWLINE
        for (int i = 0; i < n; i++) {
            String[] taskDetails = scanner.nextLine().split(" ");
            int at = Integer.parseInt(taskDetails[0]);
            int dl = Integer.parseInt(taskDetails[1]);
            int tr = Integer.parseInt(taskDetails[2]);
            taskArrayList.add(new Task(at, dl, tr));
        }
        //System.out.println(taskArrayList.size());
        int counter = 0;
        while (!taskList.isEmpty() || !taskArrayList.isEmpty()) {
            Task taskTemp;
            Task currentTask;
            if (taskArrayList.size()>0 && counter >= taskArrayList.get(0).arrivalTime) {
                taskTemp = taskArrayList.remove(0);
                taskTemp.priority = taskTemp.deadline - counter;
                taskList.add(taskTemp);
                currentTask = taskList.poll();
                //System.out.println("Before: " + currentTask);
                int resultTemp = currentTask.perform(counter);
                //System.out.println("After: " + currentTask);
                if (resultTemp == 1) {
                    taskList.add(currentTask);
                } else {
                    break;
                }
            }
            counter++;
        }
        System.out.println(taskList.isEmpty() ? "YES" : "NO");
    }
}
class Task implements Comparable<Task>{
    static private int idC = 0;

    int id;
    int arrivalTime;
    int deadline;
    int timeToComplete;

    int availableTime;

    int priority;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", deadline=" + deadline +
                ", timeToComplete=" + timeToComplete +
                ", availableTime=" + availableTime +
                ", priority=" + priority +
                '}';
    }

    public Task(int arrivalTime, int deadline, int timeToComplete) {
        this.id = ++idC;
        this.arrivalTime = arrivalTime;
        this.deadline = deadline;
        this.timeToComplete = timeToComplete;
        this.availableTime = (deadline - arrivalTime);
        this.priority = Integer.MAX_VALUE;
    }

    public int perform(int currentTime) {
        if (currentTime > this.deadline) {
            return -1;
        } else {
            this.timeToComplete--;
            this.availableTime = deadline - currentTime;
            this.priority = deadline - currentTime;
            return 1;
        }
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.priority, o.priority);
    }

}
