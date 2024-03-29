import general.*;
import menu.AdminMenu;
import menu.HomeMenu;
import menu.StudentMenu;
import menu.TeacherMenu;

import utils.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.Locale;
import java.util.UUID;

import database.Database.AllData;

class Main {
    public static void main(String[] args)  {

        AcademicSystem academicSystem = Global.getAcademicSystem();

        // Setar exemplos de instancias
        academicSystem.db.rooms.updateAll(Examples.getRooms());
        academicSystem.db.subjects.updateAll(Examples.getSubjects());
        academicSystem.db.teachers.updateAll(Examples.getTeachers());
        academicSystem.db.students.updateAll(Examples.getStudents());
        academicSystem.db.admin.updateAll(Examples.getAdmins());
        academicSystem.db.collegeClass.updateAll(Examples.getGollegesClass());


        new HomeMenu().run();
    }


}



