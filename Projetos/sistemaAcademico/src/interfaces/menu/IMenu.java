package interfaces.menu;

import java.util.List;

public interface IMenu {
    void run();
    List<ISubMenuOption> getOptions();
}
