package menu;

import interfaces.ISubMenu;
import interfaces.ISubMenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

public class TeacherMenu implements ISubMenu {

    private Map<Integer, ISubMenuOption> options = null;
    @Override
    public Map<Integer, ISubMenuOption> getOptions() {
        if (options != null)
            return options;

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        return options = newOptions;
    }

    @Override
    public void run() {

    }

}
