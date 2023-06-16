package menu;
import database.Database;
import database.DatabaseMemory;
import general.AcademicSystem;
import general.Employee;
import interfaces.*;
import utils.DataInput;
import utils.Role;

import java.util.*;
public class HomeMenu implements ISubMenu {
    private Map<Integer, ISubMenuOption> options = null;
    private record OptionLogin(String label) implements ISubMenuOption {

        @Override
        public void run() {

            AcademicSystem db = new AcademicSystem(new Database());
            String registration, password;
            Employee employee;

            registration = DataInput.getDataByUser("Digite a matricula", DataInput::validStringInput);
            password = DataInput.getDataByUser("Digite a senha", DataInput::validStringInput);
            employee = db.autenticate(registration, password);

            loginEmployee(employee);
        }
    }
    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionLogin("Fazer Login"));

        return  newOptions;
    }

    @Override
    public void run() {
        Menu menu = new Menu(this);
        menu.run();
    }

    public static void loginEmployee(Employee employee) {

        if (employee.getRole().equals(Role.STUDENT)) {
            ISubMenu menu = new StudentMenu();
            Menu menuManager = new Menu(menu);
            menuManager.run();
            return;
        }

        if (employee.getRole().equals(Role.COORDINATOR)) {
            ISubMenu menu = new CoordinatorMenu();
            Menu menuManager = new Menu(menu);
            menuManager.run();
            return;
        }

        if (employee.getRole().equals(Role.TEACHER)){
            ISubMenu menu = new TeacherMenu();
            Menu menuManager = new Menu(menu);
            menuManager.run();
            return;
        }

        throw new RuntimeException("Role provided is invalid");
    }



}