package Java.ExerciciosLab.Ar4;

public class Person {

    private String name;
    private String adress;

    private String telephone;
    private String email;

    public Person(String name, String adress, String telephone, String email) {
        setName(name);
        setAdress(adress);
        setTelephone(telephone);
        setEmail(email);
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
