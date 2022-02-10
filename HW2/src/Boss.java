import java.io.File;
import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Boss {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("/Users/kushalkale/Kushal/CSCI665/HW2/src/input2.txt");
        System.setIn(fis);
        PriorityQueue<Task> taskList = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();                                              //INPUT SIZE
        scanner.nextLine();                                                     //SKIP THE STRAY NEWLINE
        for (int i = 0; i < n; i++) {
            String[] taskDetails = scanner.nextLine().split(" ");
            int at = Integer.parseInt(taskDetails[0]);
            int dl = Integer.parseInt(taskDetails[1]);
            int tr = Integer.parseInt(taskDetails[2]);
            taskList.add(new Task(at, dl, tr));
        }
//        for (Task t : taskList) {
//            System.out.println(t);
//        }
        //boolean result = true;
        int counter = 0;
        while (!taskList.isEmpty()) {
            counter++;
            Task currentTask = taskList.poll();
            //System.out.println("Before: " + currentTask);
            int resultTemp = currentTask.perform(counter);
            if (resultTemp == 0) {
                continue;
            } else if (resultTemp == 1) {
                taskList.add(currentTask);
            } else if (resultTemp == -1) {
                break;
            }
            //System.out.println("After: " + currentTask);
            //System.out.println("-------------------------------CurrentTime: " + counter);
//            for (Task t : taskList) {
//                System.out.println(t);
//            }
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
        this.priority = availableTime - timeToComplete + arrivalTime;
    }

    public int perform(int currentTime) {
        if (currentTime > deadline) {
            return -1;
        }
        int result = 0;
        if (this.availableTime > 0 && this.timeToComplete > 0) {
            this.timeToComplete--;
            this.arrivalTime++;
            this.availableTime = deadline - arrivalTime;
            this.priority = availableTime - timeToComplete + arrivalTime;
            result = 1;
        } else if (this.availableTime >= 0 && this.timeToComplete == 0) {
            result = 0;
        } else if (this.availableTime <= 0 && this.timeToComplete > 0) {
            result = -1;
        }
        return result;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.priority, o.priority);
    }

}
