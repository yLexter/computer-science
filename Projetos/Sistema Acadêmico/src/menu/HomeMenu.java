package menu;
import general.*;
import interfaces.menu.IMenu;
import interfaces.menu.IMenuEmployee;
import interfaces.menu.ISubMenuOption;
import utils.DataInput;
import utils.Global;
import utils.Role;
import utils.DataEntryValidator;

import java.util.*;
public class HomeMenu implements IMenu {
    private void OptionLogin() {

        AcademicSystem db = Global.getAcademicSystem();
        String registration, password;
        Employee employee;

        registration = DataInput.getDataByUser("Digite a matricula", DataEntryValidator::validStringIsNotEmpty);
        password = DataInput.getDataByUser("Digite a senha | apenas números", DataEntryValidator::validStringIsNotEmpty);
        employee = db.autenticate(registration.toLowerCase(), password);

        loginEmployee(employee);
    }

   /**
     * Retorna as opções do menu.
     *
     * @return as opções do menu
     */
    @Override
    public List<ISubMenuOption> getOptions() {

        return List.of(
             new OptionMenu("Fazer Login", this::OptionLogin)
        );

    }

    /**
     * Obtem o cabeçalho do menu.
     */
    @Override
    public String getHeader() {
        List<String> lines = List.of(
          "|------------------------|",
          "|       Bem Vindo        |",
          "|     Ao AcademyPlus     |",
          "|                        |",
          "|------------------------|"
        );

      return String.join("\n", lines);
    }

   /**
     * Executa o menu
     */
    @Override
    public void run() {
        new MenuExecutor(this).run();
    }

  /**
     * Realiza o login do funcionário no sistema.
     * Redireciona para o menu correspondente com base no papel do funcionário.
     *
     * @param employee o funcionário logado
     */
    private void loginEmployee(Employee employee) {

        if (employee.getRole().equals(Role.STUDENT)) {
            IMenuEmployee<Student> menu = new StudentMenu(employee.getId());
            MenuExecutor menuExecutor = new MenuExecutor(menu);
            menuExecutor.run();
            return;
        }

        if (employee.getRole().equals(Role.ADMIN)) {
            IMenuEmployee<Admin> menu = new AdminMenu(employee.getId());
            MenuExecutor menuExecutor = new MenuExecutor(menu);
            menuExecutor.run();
            return;
        }

        if (employee.getRole().equals(Role.TEACHER)){
            IMenuEmployee<Teacher> menu = new TeacherMenu(employee.getId());
            MenuExecutor menuExecutor = new MenuExecutor(menu);
            menuExecutor.run();
            return;
        }

        throw new RuntimeException("Role provided is invalid");
    }


}