package general;

import utils.Global;

import java.util.*;
import java.util.stream.Collectors;

public class CollegeClass extends Subject {
        private String teacherId;
        private ArrayList<SubjectStudent> students;
        private String collegeClassId;
        private List<RegisterClass> registerClasses;
        private List<String> classSchedules;

        public CollegeClass(String code, String name, int hours, String teacherId) {
                super(code, name, hours);
                this.teacherId = teacherId;
                this.collegeClassId = UUID.randomUUID().toString();
                this.students = new ArrayList<>();
                this.registerClasses = new ArrayList<>();
                this.classSchedules = new ArrayList<>();
        }

        public CollegeClass(String code, String name, int hours, String teacherId, String id) {
                super(code, name, hours);
                this.teacherId = teacherId;
                this.collegeClassId = UUID.fromString(id).toString();
                this.students = new ArrayList<>();
                this.registerClasses = new ArrayList<>();
                this.classSchedules = new ArrayList<>();
        }

        public void setStudents(ArrayList<SubjectStudent> students) {
                this.students = students;
        }

        public List<String> getClassSchedules() {
                return classSchedules;
        }

        public void setClassSchedules(List<String> classSchedules) {
                this.classSchedules = classSchedules;
        }

        public Teacher getTeacher() {
              AcademicSystem academicSystem = Global.getAcademicSystem();
              return academicSystem.db.teachers.findById(teacherId);
        }

        public void setTeacherId(String teacherId) {
                this.teacherId = teacherId;
        }

        public List<SubjectStudent> getStudents() {
                return students;
        }

        public void setStudents(List<SubjectStudent> students) {
                this.students = new ArrayList<>(students);
        }

        public String getCollegeClassId() {
             return collegeClassId;
        }

        public void setCollegeClassId(String collegeClassId) {
                this.collegeClassId = collegeClassId;
        }

        public List<RegisterClass> getRegisterClasses() {
                return registerClasses;
        }

        public void addSubjectStudent(SubjectStudent subjectStudent) {
             students.add(subjectStudent);
        }
        public String getTeacherId() {
                return teacherId;
        }

        public void setRegisterClasses(List<RegisterClass> registerClasses) {
                this.registerClasses = registerClasses;
        }

        public List<SubjectStudent> getStudentsInFinal() {
            AcademicSystem academicSystem = Global.getAcademicSystem();

            return students
                    .stream()
                    .filter(subjectStudent -> {
                       Float avarage = subjectStudent.getAverage();

                       if (avarage != null)
                         return avarage < academicSystem.getSettings().getMinimumAverage();

                       return false;
                    })
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return String.format("%s | CollegeClass: %s | Id: %s ", super.toString(), collegeClassId, collegeClassId);
        }


}

