import general.*;
import menu.HomeMenu;
import utils.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import database.Database.AllData;

public class Main {
    public static void main(String[] args) {

        teste3();

    }

    public static void teste3() {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        academicSystem.db.rooms.updateAll(Examples.getRooms());
        academicSystem.db.subjects.updateAll(Examples.getSubjects());
        academicSystem.db.teachers.updateAll(Examples.getTeachers());
        academicSystem.db.students.updateAll(Examples.getStudents());
        academicSystem.db.admin.updateAll(Examples.getAdmins());
        academicSystem.db.collegeClass.updateAll(Examples.getGollegesClass());

        new HomeMenu().run();
    }




}