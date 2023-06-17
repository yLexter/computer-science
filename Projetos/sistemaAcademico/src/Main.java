import general.AcademicSystem;
import general.Admin;
import menu.AdminMenu;
import menu.HomeMenu;
import utils.Global;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        AcademicSystem academicSystem = Global.getAcademicSystem();
        Admin admin = new Admin("Lucas", "Ferreira", 18, LocalDate.of(2003, 02, 02), "151515");

        System.out.println(admin.getId());
        System.out.println(admin.getPassword());

        academicSystem.db.admin.save(admin);

        new HomeMenu().run();
    }
}