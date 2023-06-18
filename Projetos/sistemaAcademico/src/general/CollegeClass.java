package general;

import java.time.LocalDateTime;
import java.util.*;

public class CollegeClass extends Subject {
        private Teacher teacher;
        private LocalDateTime time;
        private List<SubjectStudent> students;
        private String classId;

        public CollegeClass(String code, String name, int hours, Teacher teacher, LocalDateTime time, List<SubjectStudent> students) {
                super(code, name, hours);
                this.teacher = teacher;
                this.time = time;
                this.students = students;
                this.classId = UUID.randomUUID().toString();
        }

        public CollegeClass(String code, String name, int hours, Teacher teacher, LocalDateTime time) {
                super(code, name, hours);
                this.teacher = teacher;
                this.time = time;
                this.classId = UUID.randomUUID().toString();
                this.students = new ArrayList<>();
        }


        public Teacher getTeacher() {
                return teacher;
        }

        public void setTeacher(Teacher teacher) {
                this.teacher = teacher;
        }

        public LocalDateTime getTime() {
                return time;
        }

        public void setTime(LocalDateTime time) {
                this.time = time;
        }

        public List<SubjectStudent> getStudents() {
                return students;
        }

        public void setStudents(List<SubjectStudent> students) {
                this.students = students;
        }

        public String getClassId() {
                return classId;
        }

        public void setClassId(String classId) {
                this.classId = classId;
        }
}
