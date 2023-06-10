package menu;
import erros.*;
import interfaces.*;
import utils.DataInput;
import utils.Global;

import java.util.*;
public class HomeMenu implements ISubMenu {
    private Map<Integer, ISubMenuOption> options = null;
    private record OptionLogin(String label) implements ISubMenuOption {

        @Override
        public void run() {

            Scanner scanner = Global.getScanner();
            String registration, password;

            try {
                registration = DataInput.getDataByUser("Digite a matricula", DataInput::validStringInput);


            } catch (UserLeftMenuException err) {
                return;
            }

        }
    }
    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        if (options != null)
            return options;

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionLogin("Fazer Login"));

        return options = newOptions;
    }

    @Override
    public void run() {
        Menu menu = new Menu(getOptions());
        menu.run();
    }




}