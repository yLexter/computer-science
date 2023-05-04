package Java.ExerciciosLab.Ar4;

import java.security.SecureRandom;
import java.util.Arrays;

public class Queue {

    private Integer[] queue;

    public Queue() {
        this.queue = new Integer[8];
    }

    public Queue(int size) {
        this.queue = new Integer[size];
    }

    public Integer[] getData() {
        return queue;
    }

    public void setData(Integer[] data) {
        this.queue = data;
    }

    public void doubleArraySize() {

        Integer[] newQueue = new Integer[queue.length * 2];

        System.arraycopy(queue, 0, newQueue, 0, queue.length - 1);

        this.setData(newQueue);
    }

    public void showQueue() {

        Arrays.stream(queue).forEach(x -> {
            System.out.printf("%d ", x);
        });

        System.out.println();
    }

    public Queue enQueue(int value) {

        if(queue[queue.length - 1] != null)
            doubleArraySize();

        Integer[] newQueue = new Integer[queue.length];
        newQueue[0] = value;

        System.arraycopy(queue, 0, newQueue, 1, queue.length - 1);

        this.setData(newQueue);

        return this;
    }

    public int deQueue() {

        Integer valueRemoved = queue[0];

        if (valueRemoved == null)
            throw new RuntimeException("Lista Vazia");

        Integer[] newQueue = new Integer[queue.length];

        System.arraycopy(queue, 1, newQueue, 0, queue.length - 1);

        this.setData(newQueue);

        return valueRemoved;
    }

    public boolean isEmpty() {
        return this.queue.length == 0;
    }

    public int getSize() {
        return this.queue.length;
    }

    public static void main(String[] args) {

        SecureRandom random = new SecureRandom();
        Queue q1 = new Queue();
        int total = 10;

        for(int x = 0; x < total; x++) {
            q1.enQueue(random.nextInt(100));
            q1.showQueue();
        }

        System.out.println();

        for(int x = 0; x < total; x++) {
            q1.deQueue();
            q1.showQueue();
        }


    }


}
