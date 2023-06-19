import general.*;
import menu.AdminMenu;
import menu.HomeMenu;
import menu.StudentMenu;
import utils.DataEntryValidator;
import utils.DataInput;
import utils.Global;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

         test1();

    }

    public static void test1() {
        AcademicSystem academicSystem = Global.getAcademicSystem();

        Admin admin = new Admin("Lucas", "Ferreira", 18, LocalDate.of(2003, 2, 2), "151515");

        System.out.println(admin.getId());
        System.out.println(admin.getPassword());

        academicSystem.db.admin.save(admin);

        new HomeMenu().run();
    }

    public static void test2() {

        AcademicSystem academicSystem = Global.getAcademicSystem();


        Student s1 = new Student(
                "Lucas",
                "Ferreira",
                18,
                LocalDate.of(2003, 2, 2),
                "15151",
                null,
                "CC",
                null
        );

        SubjectStudent class1 = new SubjectStudent("001", "Math", 4, s1.getId(), "111");
        SubjectStudent class2 = new SubjectStudent("002", "English", 3,  s1.getId(), "11");
        SubjectStudent class3 = new SubjectStudent("003", "Physics", 5, s1.getId(), "");

        List<SubjectStudent> list = new ArrayList<>(
                Arrays.asList(class1, class2, class3)
        );

        s1.setSubjects(list);

        new StudentMenu(s1).run();

    }


}