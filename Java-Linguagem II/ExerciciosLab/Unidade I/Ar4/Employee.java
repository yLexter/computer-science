package Java.ExerciciosLab.Ar4;
import Java.ExerciciosLab.Ap1.CustomDate;

public class Employee extends Person {

    private String room;

    private double salary;

    private CustomDate hiringDate;

    Employee(String name, String adress, String telephone, String email) {
        super(name, adress, telephone, email);
    }

    @Override
    public String toString() {
        return String.format("%s | %s", this.getClass().getSimpleName(), this.getName());
    }


}
