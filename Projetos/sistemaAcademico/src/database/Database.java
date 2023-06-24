package database;

import general.*;
import interfaces.database.*;

import java.util.List;

public class Database {
    public record AllData(List<Student> students, List<Teacher> teachers, List<Admin> admins, List<Subject> subjects, GeneralInformation generalInformation, List<CollegeClass> collegeClasses, List<ClassRoom> classRooms, List<Room> rooms, List<ClassSchedule> classSchedules) {
        @Override
        public List<Student> students() {
            return students;
        }

        @Override
        public List<Teacher> teachers() {
            return teachers;
        }

        @Override
        public List<Admin> admins() {
            return admins;
        }

        @Override
        public List<Subject> subjects() {
            return subjects;
        }

        @Override
        public GeneralInformation generalInformation() {
            return generalInformation;
        }
        @Override
        public List<CollegeClass> collegeClasses() {
            return collegeClasses;
        }

        @Override
        public List<ClassRoom> classRooms() {
            return classRooms;
        }

        @Override
        public List<Room> rooms() {
            return rooms;
        }

        @Override
        public List<ClassSchedule> classSchedules() {
            return classSchedules;
        }
    }
    public final IDatabaseStudent students;
    public final IDatabaseTeacher teachers;
    public final IDatabaseAdmin admin;
    public final IDatabaseSubject subjects;
    public final IDatabaseGeneralInformation generalInformation;
    public final IDatabaseCollegeClass collegeClass;
    public final IDatabaseClassRoom classRooms;
    public final IDatabaseRoom rooms;

    public final IDatabaseClassSchedule classSchedule;

    public Database(IDatabaseStudent students, IDatabaseTeacher teachers, IDatabaseAdmin admin, IDatabaseSubject subjects, IDatabaseGeneralInformation generalInformation, IDatabaseCollegeClass collegeClass, IDatabaseClassRoom classRooms, IDatabaseRoom rooms, IDatabaseClassSchedule classSchedule) {
        this.students = students;
        this.teachers = teachers;
        this.admin = admin;
        this.subjects = subjects;
        this.generalInformation = generalInformation;
        this.collegeClass = collegeClass;
        this.classRooms = classRooms;
        this.rooms = rooms;
        this.classSchedule = classSchedule;
    }

    public AllData findAll() {
        return new AllData(
           students.getAll(),
           teachers.getAll(),
           admin.getAll(),
           subjects.getAll(),
           generalInformation.getData(),
           collegeClass.getAll(),
           classRooms.getAll(),
           rooms.getAll(),
           classSchedule.getAll()
        );
    }

}
