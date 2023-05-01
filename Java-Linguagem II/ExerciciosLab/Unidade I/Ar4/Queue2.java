package Java.ExerciciosLab.Ar4;
import java.security.SecureRandom;
import java.util.*;

public class Queue2<T> extends ArrayList<T> {

    public void enQueue(T value) {
       super.add(0, value);
    }

    public T deQueue() {
        return super.remove(0);
    }

    public int getSize() {
        return super.size();
    }

    public void showQueue() {

        for(T value : this)
            System.out.printf("%s ",  value.toString());

        System.out.println();
    }

    public static void main(String[] args) {

        SecureRandom random = new SecureRandom();
        Queue2<Integer> q1 = new Queue2<>();

        for(int x = 0; x < 25; x++)
            q1.enQueue(random.nextInt(100));

        q1.showQueue();

        for(int x = 0; x < 25; x++)
            q1.deQueue();

        q1.showQueue();

    }


}
