package menu;

import interfaces.ISubMenu;
import interfaces.ISubMenuOption;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentMenu implements ISubMenu {

    private Map<Integer, ISubMenuOption> options = null;

    private record OptionShowRDM(String label) implements ISubMenuOption {

        @Override
        public void run() {

        }
    }

    private record OptionShowCurriculum(String label) implements ISubMenuOption {

        @Override
        public void run() {

        }

    }

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        if (options != null)
            return options;

        Map<Integer, ISubMenuOption>  newOptions = new LinkedHashMap<>();

        newOptions.put(1, new OptionShowRDM("Ver RDM"));
        newOptions.put(2, new OptionShowCurriculum("Ver Grade Curricular"));

        return options = newOptions;
    }

    @Override
    public void run() {
        Menu menu = new Menu(getOptions());
        menu.run();
    }


}
