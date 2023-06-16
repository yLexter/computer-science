package general;

import java.time.LocalDateTime;
import java.util.*;

public class CollegeClass {

        private String name;
        private String id;
        private String teacher;
        private Subject subject;
        private LocalDateTime time;
        private List<Student> students;

        public CollegeClass(String name, String id, String teacher, Subject subject, LocalDateTime time, List<Student> students) {
                this.name = name;
                this.id = id;
                this.teacher = teacher;
                this.subject = subject;
                this.time = time;
                this.students = students;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getTeacher() {
                return teacher;
        }

        public void setTeacher(String teacher) {
                this.teacher = teacher;
        }

        public Subject getSubject() {
                return subject;
        }

        public void setSubject(Subject subject) {
                this.subject = subject;
        }

        public LocalDateTime getTime() {
                return time;
        }

        public void setTime(LocalDateTime time) {
                this.time = time;
        }

        public List<Student> getStudents() {
                return students;
        }

        public void setStudents(List<Student> students) {
                this.students = students;
        }
}
