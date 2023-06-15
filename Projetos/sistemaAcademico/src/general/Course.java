package general;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String code;
    private List<Subject> subjects;

    public Course(String code, List<Subject> subjects) {
        this.code = code;
        this.subjects = subjects;
    }

}
