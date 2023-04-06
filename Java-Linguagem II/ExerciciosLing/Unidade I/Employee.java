
package Java.ExerciciosLing;
public class Employee {
    private String id;
    private String name;
    private float salary;
    private int workload;
    private static final int baseSalary = 500;

    Employee(String id, String name, int workload) {
        this.id = id;
        this.name = name;

        if (workload > 0) {
            this.salary = Employee.baseSalary * workload;
            this.workload = workload;
        }

    }

    Employee(String id, String name) {
        this.id = id;
        this.name = name;
        this.salary = 20;
    }

    public float getSalary() {
        return this.salary;
    }

    public static void test() {

        Employee f1 = new Employee("44848", "Marcos", 50);
        Employee f2 = new Employee("44848", "Marcos", -5);
        Employee f3 = new Employee("44848", "Marcos");

    }

}