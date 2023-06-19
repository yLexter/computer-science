package menu;
import general.*;
import interfaces.*;
import utils.DataInput;
import utils.Global;
import utils.Role;
import utils.DataEntryValidator;

import java.util.*;
public class HomeMenu implements ISubMenu {

    private void OptionLogin() {

        AcademicSystem db = Global.getAcademicSystem();
        String registration, password;
        Employee employee;

        registration = DataInput.getDataByUser("Digite a matricula", DataEntryValidator::validStringInput);
        password = DataInput.getDataByUser("Digite a senha", DataEntryValidator::validStringInput);
        employee = db.autenticate(registration.toLowerCase(), password);

        loginEmployee(employee);
    }

    @Override
    public List<ISubMenuOption> getOptions() {

        return List.of(
             new OptionMenu("Fazer Login", this::OptionLogin)
        );

    }

    @Override
    public void run() {
        new MenuExecutor(this).run();
    }

    private void loginEmployee(Employee employee) {

        if (employee.getRole().equals(Role.STUDENT)) {
            Student student = (Student) employee;
            ISubMenu menu = new StudentMenu(student);
            MenuExecutor menuExecutor = new MenuExecutor(menu);
            menuExecutor.run();
            return;
        }

        if (employee.getRole().equals(Role.ADMIN)) {
            Admin admin = (Admin) employee;
            ISubMenu menu = new AdminMenu(admin);
            MenuExecutor menuExecutor = new MenuExecutor(menu);
            menuExecutor.run();
            return;
        }

        if (employee.getRole().equals(Role.TEACHER)){
            Teacher teacher = (Teacher) employee;
            ISubMenu menu = new TeacherMenu(teacher);
            MenuExecutor menuExecutor = new MenuExecutor(menu);
            menuExecutor.run();
            return;
        }

        throw new RuntimeException("Role provided is invalid");
    }



}