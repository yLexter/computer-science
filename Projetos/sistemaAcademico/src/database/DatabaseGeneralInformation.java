package database;

import general.Course;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGeneralInformation {

    List<Course> courses;

    public DatabaseGeneralInformation() {
        this.courses = new ArrayList<>();
    }

    public DatabaseGeneralInformation(List<Course> courses) {
        this.courses = courses;
    }


}
