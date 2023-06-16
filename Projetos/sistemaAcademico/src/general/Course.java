package general;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String code;
    private List<Subject> subjects;
    public Course(String code, List<Subject> subjects) {
        this.code = code;
        this.subjects = subjects;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
