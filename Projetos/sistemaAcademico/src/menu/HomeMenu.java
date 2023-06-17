package menu;
import general.*;
import interfaces.*;
import utils.DataInput;
import utils.Role;

import java.util.*;
public class HomeMenu implements ISubMenu {

    public void OptionLogin() {

        AcademicSystem db = null;
        String registration, password;
        Employee employee;

        registration = DataInput.getDataByUser("Digite a matricula", DataInput::validStringInput);
        password = DataInput.getDataByUser("Digite a senha", DataInput::validStringInput);
        employee = db.autenticate(registration, password);

        loginEmployee(employee);
    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionMenu("Fazer Login", this::OptionLogin));

        return  newOptions;
    }

    @Override
    public void run() {
        new Menu(this).run();
    }

    public static void loginEmployee(Employee employee) {

        if (employee.getRole().equals(Role.STUDENT)) {
            Student student = (Student) employee;
            ISubMenu menu = new StudentMenu(student);
            Menu menuManager = new Menu(menu);
            menuManager.run();
            return;
        }

        if (employee.getRole().equals(Role.COORDINATOR)) {
            Admin admin = (Admin) employee;
            ISubMenu menu = new AdminMenu(admin);
            Menu menuManager = new Menu(menu);
            menuManager.run();
            return;
        }

        if (employee.getRole().equals(Role.TEACHER)){
            Teacher teacher = (Teacher) employee;
            ISubMenu menu = new TeacherMenu(teacher);
            Menu menuManager = new Menu(menu);
            menuManager.run();
            return;
        }

        throw new RuntimeException("Role provided is invalid");
    }



}