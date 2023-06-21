package general;

import utils.Global;

import java.time.LocalDateTime;
import java.util.*;

public class CollegeClass extends Subject {
        private String teacherId;
        private List<SubjectStudent> students;
        private String classId;
        private List<RegisterClass> registerClasses;
        private String classRoomId;

        public CollegeClass(String code, String name, int hours, String teacherId, List<SubjectStudent> students, String classRoomId) {
                super(code, name, hours);
                this.teacherId = teacherId;
                this.classRoomId = classRoomId;
                this.students = students;
                this.classId = UUID.randomUUID().toString();
                this.registerClasses = new ArrayList<>();
        }

        public CollegeClass(String code, String name, int hours, String teacherId, String classRoomId) {
                super(code, name, hours);
                this.teacherId = teacherId;
                this.classRoomId = classRoomId;
                this.classId = UUID.randomUUID().toString();
                this.students = new ArrayList<>();
                this.registerClasses = new ArrayList<>();
        }

        public ClassRoom getClassRoom() {
            AcademicSystem academicSystem = Global.getAcademicSystem();
            return academicSystem.db.classRooms.findById(classRoomId);
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
                this.students = students;
        }

        public String getClassId() {
                return classId;
        }

        public void setClassId(String classId) {
                this.classId = classId;
        }

        public List<RegisterClass> getRegisterClasses() {
                return registerClasses;
        }

        public void setRegisterClasses(List<RegisterClass> registerClasses) {
                this.registerClasses = registerClasses;
        }


}

