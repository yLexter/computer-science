package LinkedList;

public class Main {

    public static void main(String[] args) {


     CustomLinkedList<Integer> linkedList = new CustomLinkedList<>();

     linkedList
             .add(5)
             .add(2)
             .add(3)
             .add(1)
             .add(10)
             .add(15)
             .add(10);

     linkedList.show();

     System.out.println("\n\n");

     linkedList.reverse();

     System.out.println("\n\n");

     linkedList.show();

    }


}
