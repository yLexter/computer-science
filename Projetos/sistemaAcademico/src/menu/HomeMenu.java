package menu;
import erros.*;
import interfaces.*;
import utils.DataInput;

import java.util.*;
public class HomeMenu implements ISubMenu {
    private record Option1(String label) implements ISubMenuOption {
        @Override
        public void run() {

            String registration, password;

            try {
                registration = DataInput.getStringByUser("Digite a matricula", DataInput::validStringInput);
                password = DataInput.getStringByUser("Digite a senha", DataInput::validStringInput);
            } catch (UserLeftMenuException err) {
                return;
            }

        }
    }
    @Override
    public Map<Integer, ISubMenuOption> getOptions() {
        Map<Integer, ISubMenuOption>  options = new LinkedHashMap<>();

        options.put(1, new Option1("Fazer Login"));

        return options;
    }

    @Override
    public void run() {
        Menu subMenu = new Menu(getOptions());
        subMenu.run();
    }




}