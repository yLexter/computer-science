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
        AllData allData = academicSystem.db.findAll();

        Admin adm1 = new Admin("Lucas", "Ferreira", 18, LocalDate.of(2003, 2, 2), "15151551");

        List<ClassRoom> cls = List.of(
            new ClassRoom("B454", 50, LocalTime.now(), DayOfWeek.FRIDAY)
        );

        System.out.println(adm1.getId());

        List<Admin> admins = List.of(adm1);

        academicSystem.db.teachers.updateAll(Examples.getTeachers());
        academicSystem.db.students.updateAll(Examples.getStudents());
        academicSystem.db.admin.updateAll(admins);
        academicSystem.db.subjects.updateAll(allData.subjects());

        new HomeMenu().run();
    }




}