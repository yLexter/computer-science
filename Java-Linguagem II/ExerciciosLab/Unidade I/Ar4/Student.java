package Java.ExerciciosLab.Ar4;


enum Category {
    VETERAN("veteran"),
    FRESHMAN("freshman");

    public final String category;

    Category(String category) {
        this.category = category;
    }
}

public class Student extends Person {

    private Category category;

    Student(String name, String adress, String telephone, String email, Category category) {
        super(name, adress, telephone, email);
        this.setCategory(category);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}


