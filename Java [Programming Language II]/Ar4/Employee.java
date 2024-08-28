package Java.ExerciciosLab.Ar4;

public class Employee extends Person {

    protected String office;
    protected double salary;
    protected String hireDate;

    public Employee (String name, String address, String phoneNumber, String email, String office, double salary, String hireDate) {
        super(name, address, phoneNumber, email);
        this.office = office;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }


    public String toString() {
        return "Employee: " + name;
    }

}
