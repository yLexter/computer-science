package general;

import utils.Global;

import java.util.*;

public class CollegeClass extends Subject {
        private String teacherId;
        private List<SubjectStudent> students;
        private String collegeClassId;
        private List<RegisterClass> registerClasses;
        private String classRoomId;

        public CollegeClass(String code, String name, int hours, String teacherId, List<SubjectStudent> students, String classRoomId) {
                super(code, name, hours);
                this.teacherId = teacherId;
                this.classRoomId = classRoomId;
                this.students = students;
                this.collegeClassId = UUID.randomUUID().toString();
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

        public String getCollegeClassId() {
                return collegeClassId;
        }

        public void setCollegeClassId(String collegeClassId) {
                this.collegeClassId = collegeClassId;
        }

        public List<RegisterClass> getRegisterClasses() {
                return registerClasses;
        }

        public String getTeacherId() {
                return teacherId;
        }

        public String getClassRoomId() {
                return classRoomId;
        }

        public void setClassRoomId(String classRoomId) {
                this.classRoomId = classRoomId;
        }

        public void setRegisterClasses(List<RegisterClass> registerClasses) {
                this.registerClasses = registerClasses;
        }

        @Override
        public String toString() {
            return String.format("%s | ClassID: %s | RoomId: %s ", super.toString(), collegeClassId, classRoomId);
        }


}

