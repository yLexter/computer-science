package menu;

import interfaces.ISubMenu;
import interfaces.ISubMenuOption;

import java.util.LinkedHashMap;
import java.util.Map;

public class TeacherMenu implements ISubMenu {

    @Override
    public Map<Integer, ISubMenuOption> getOptions() {

        Map<Integer, ISubMenuOption> newOptions = new LinkedHashMap<>();

        newOptions.put(1, null);

        return  newOptions;
    }

    @Override
    public void run() {

    }

}
